package com.hims.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.activiti.impl.WorkflowServiceImpl;
import com.activiti.test.BaseProcessService;
import com.hims.login.bean.User;
import com.hims.login.service.ILoginUser;


@Controller
@RequestMapping(value="/welcome")
public class LoginController {

	@Autowired
	private ILoginUser loginUserImpl;
	@Autowired
	private BaseProcessService BaseProcessServiceImpl;
	@Autowired
	private WorkflowServiceImpl workflowServiceImpl;
	
	@RequestMapping(value="/loginVerify")
	public ModelAndView loginVerify( HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		try {
			request.setCharacterEncoding("utf-8");
			User user = (User)request.getSession().getAttribute("user");  
			if(user!=null){
				mav.setViewName("forward:/jsp/index/index.jsp");
				 return mav;  
			}
//			String logname  =request.getParameter("logname");
//			String logpass  =request.getParameter("logpass");
			String logname  =request.getParameter("user");
			String logpass  =request.getParameter("pass");
//			System.out.println(logname+":"+logpass+"-----------------------------");
			List list = loginUserImpl.loginVerify(logname, logpass);
			
			if(list.size()>0){
				User ui =(User) list.get(0);
				request.getSession().setAttribute("user",ui);
				request.getSession().setAttribute("userName",ui.getName());
				mav.setViewName("forward:/jsp/index/index.jsp");
			}else{
//				mav.setViewName("forward:/jsp/login/login.jsp");
				mav.addObject("errorInfo", "锟剿号伙拷锟斤拷锟斤拷锟斤拷锟�");
			}
			
		} catch (Exception e) {
			mav.setViewName("forward:/jsp/login/login.jsp");
			mav.addObject("errorInfo", "锟剿号伙拷锟斤拷锟斤拷锟斤拷锟�");
			return mav;  
		}
		return mav;  
	}
	
	
	@RequestMapping(value="/test")
	public ModelAndView test( HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		System.out.println("ddddddddddd");
		try {
//			workflowServiceImpl.startProcessInstanceByKey("testProject");
//			workflowServiceImpl.complete(taskId, variables);
			 ProcessDefinition processDefinition  = BaseProcessServiceImpl.findLatestProcessDefinitionByPrcDefKey("testProject");
			 System.out.println(processDefinition.getId());
			 System.out.println(processDefinition.getName());
			 System.out.println(processDefinition.getKey());
			 System.out.println(processDefinition.getTenantId());
			 System.out.println(processDefinition.getDeploymentId());
			 System.out.println(processDefinition.getCategory());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ProcessEngines.init();
//		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//		System.out.println(processEngine);
//		processEngine.close();
		return mav;  
	}
	
	@RequestMapping(value="/saveUserInfo")
    @ResponseBody
	public boolean saveUserInfo(HttpServletRequest request){
		try {
			return loginUserImpl.save(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@RequestMapping(value="/deleteUserInfo")
    @ResponseBody
	public boolean deleteUserInfo(HttpServletRequest request){
		try {
			return loginUserImpl.delete(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@RequestMapping(value="/updateUserInfo")
    @ResponseBody
	public boolean updateUserInfo(HttpServletRequest request){
		try {
			
			return loginUserImpl.update(request);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping(value="/selUserInfo")
    @ResponseBody
	public List selUserInfo(HttpServletRequest request){
		try {
			return loginUserImpl.selUserInfor(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
