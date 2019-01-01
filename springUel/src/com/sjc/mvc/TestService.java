package com.sjc.mvc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sjc.mvc.Interface.SjcAutower;
import com.sjc.mvc.Interface.SjcController;
import com.sjc.mvc.Interface.SjcRequestMapping;
import com.sjc.mvc.Interface.SjcRuestParment;
import com.sjc.mvc.Interface.SjcServer;


public class TestService extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TestService() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dodispatch(request,response);
		
	}

	private void dodispatch(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HandMethod handler =getHandler(request);
			if(handler==null){response.getWriter().write("404");return;}
			
			//获取方法的参数列表
			Class<?> [] paramTyes = handler.method.getParameterTypes();
			
			//保存所有鱼药自动复制的参数值
			Object[] paramValues = new Object[paramTyes.length];
			
			Map<String,String[]> params = request.getParameterMap();
			for (Map.Entry<String, String[]> param : params.entrySet()) {
				if(!handler.paramindexMapping.containsKey(param.getKey())){continue;}
				String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", "");
				int index = handler.paramindexMapping.get(param.getKey());
				paramValues[index] = value;
			}
			
			for (int i = 0,num=paramTyes.length; i < num; i++) {
				if(paramTyes[i].isAssignableFrom(HttpServletRequest.class)){
					paramValues[i] = request;
				}else{
					if(paramTyes[i].isAssignableFrom(HttpServletRequest.class)){
						paramValues[i] = response;
					}
				}
			}
//			int reqIndex = handler.paramindexMapping.get(HttpServletRequest.class.getName());
//			paramValues[reqIndex] = request;
//			int respIndex = handler.paramindexMapping.get(HttpServletResponse.class.getName());
//			paramValues[respIndex] = response;

			handler.method.invoke(handler.controller, paramValues);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HandMethod getHandler(HttpServletRequest request) {
		if(handlerMapping.isEmpty()){return null;}
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		url = url.replace(contextPath, "").replaceAll("//", "").replace(".do", "");
		
		for (HandMethod handMethod:handlerMapping) {
			Matcher matcher = handMethod.pattern.matcher(url);
			if(!matcher.matches()){continue;}
			return handMethod;
		}
		
		return null;
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {

		logConfig(config);
		
		doScanner(contextConfig.get("base-package").toString());
		//初始化所有相关联的类
		doInstance();
		//执行依赖注入
		doAutowired();
		//构造handlerMapping,将url和mapping管联
		initHandlerMapping();
	}

	private List<String> classNames = new ArrayList<String>();

	private void doScanner(String basePackage) {
		
		String path = this.getClass().getResource("/").getPath()+basePackage.replace(".", "/");
		
		File fileDir = new File(path);
		if(!fileDir.exists()){
			System.out.println("文件夹不存在！");
		}else{
			File[] files = fileDir.listFiles();
			if(files.length==0){System.out.println("文件夹为空！");return;}
			for (File file : files) {
				if(file.isDirectory()){
					doScanner(basePackage+"."+file.getName());
				}else{
					String className = basePackage+"."+file.getName().replace(".class", "");
					classNames.add(className);
				}
			}
		}
		
	}
//	private List<Map<String,handMethod>> handlerMapping = new ArrayList<Map<String, handMethod>>();
	
	private List<HandMethod> handlerMapping = new ArrayList<HandMethod>();

	private void initHandlerMapping() {
		if(ioc.isEmpty()){return;}
		
		for (Map.Entry<String, Object> entry:ioc.entrySet()) {
			Class<?> clazz = entry.getValue().getClass();
			if(!clazz.isAnnotationPresent(SjcController.class)){continue;}
			
			String baseUrl = "";
			if(clazz.isAnnotationPresent(SjcRequestMapping.class)){
				SjcRequestMapping requestMapping = clazz.getAnnotation(SjcRequestMapping.class);
				baseUrl = requestMapping.value();
			}
			
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.isAnnotationPresent(SjcRequestMapping.class)){
					SjcRequestMapping requestMapping = method.getAnnotation(SjcRequestMapping.class);
					String url = requestMapping.value();
					url = (baseUrl + url).replace("//", "");
					
					Pattern pattern = Pattern.compile(url);
					
					handlerMapping.add(new HandMethod(method , entry.getValue(), pattern));
				}
			}
			
		}
		
	}
	
	class HandMethod{
		protected Method method; //映射的方法
		protected Object controller;//方法对应的实例
		protected Pattern pattern;//requestMapping存的URL
		protected Map<String,Integer> paramindexMapping;//参数顺序

		public HandMethod(Method method, Object controller, Pattern pattern) {
			super();
			this.method = method;
			this.controller = controller;
			this.pattern = pattern;
			this.paramindexMapping = new HashMap<String, Integer>();
			putParamIndexMapping(method);
		}
		private void putParamIndexMapping(Method method2) {
//			Class<?> [] paramTyes = method2.getParameterTypes();
//			for (int i = 0,num=paramTyes.length; i < num; i++) {
//				paramindexMapping.put(paramTyes[i].getName(), i);
//			}
			Annotation[][] pa=method2.getParameterAnnotations();
			for (int i = 0; i < pa.length; i++) {
				for (Annotation a : pa[i]) {
					if(a instanceof SjcRuestParment ){
						String paramName = ((SjcRuestParment)a).value();
						if(!"".equals(paramName.trim())){
							paramindexMapping.put(paramName, i);
						}
						
					}
				}
			}
		}
		
		
	}
	
	private void doAutowired() {

		if(ioc.isEmpty()){return;}
		
		try {
		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) {
				if(field.isAnnotationPresent(SjcAutower.class)){
					SjcAutower autower = field.getAnnotation(SjcAutower.class);
					String beanName = autower.value();
					if("".equals(beanName)){
						beanName=field.getType().getName();
					}
					//如果为私有也要注入
					field.setAccessible(true);
					field.set(entry.getValue(),ioc.get(beanName) );
					
				}
			}

		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	Map<String,Object> ioc = new HashMap<String, Object>();
	private void doInstance() {
		if(classNames.isEmpty()){return;}

		try {
		for(String className:classNames){
				Class<?> clazz = Class.forName(className);

				if(clazz.isAnnotationPresent(SjcController.class)){
					Object instace = clazz.newInstance();
					SjcController service = clazz.getAnnotation(SjcController.class);
					String beanName = service.value();
					if("".equals(beanName)){
						beanName = lowerFirstCase(clazz.getSimpleName());
					}
					ioc.put(beanName, instace);
				}else if(clazz.isAnnotationPresent(SjcServer.class)){
						Object instace = clazz.newInstance();
						SjcServer service = clazz.getAnnotation(SjcServer.class);
						String beanName = service.value();
						if("".equals(beanName)){
							beanName = lowerFirstCase(clazz.getSimpleName());
						}
						ioc.put(beanName, instace);

						Class<?>[] interfaces = clazz.getInterfaces();
						for (Class<?> i : interfaces) {
//							ioc.put(i.getSimpleName(), instace);
							ioc.put(i.getName(), instace);
						}
					}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private  String  lowerFirstCase(String simpleName) {
		char[] chars = simpleName.toCharArray();
		chars[0] +=32;
		return String.valueOf(chars);
	}
	
	Properties contextConfig = new Properties();

	private void logConfig(ServletConfig config) {

//		Enumeration<String> paramNames = config.getInitParameterNames();
//		while (paramNames.hasMoreElements()) {
//			String property = paramNames.nextElement();
//			Object value = config.getInitParameter(property);
//			System.out.println(value);
//		}
		
		String filePath = config.getInitParameter("filePath").replace("classpath:", "");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
		try {
			contextConfig.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
		}
	}
	

}

