package com.book.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.book.manage.bean.ManageInfo;
import com.book.manage.service.IManage;
import com.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/manage")
public class ManageController {

	@Autowired
	private IManage manageImpl;
	
	@RequestMapping(value="/selectByExample")
	@ResponseBody
	public Msg selectByExample(ManageInfo record,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
//		PageHelper.startPage(Integer.valueOf(request.getParameter("page")), Integer.valueOf(request.getParameter("rows")));
		PageHelper.startPage(page, rows);
		List<ManageInfo> p = manageImpl.selectByExample(record,request);
		PageInfo pageInfo = new PageInfo(p);
		return Msg.success().add("date", pageInfo);
	}
	
	@RequestMapping(value="/selectNameVerify")
	@ResponseBody
	public Map selectNameVerify(ManageInfo record,HttpServletRequest request) {
		List<ManageInfo> p = manageImpl.selectNameVerify(record,request);
		 Map<String, Boolean> map = new HashMap<String, Boolean>();
		 map.put("valid", p.size()==0?true:false);
		return map;
	}
	
	@RequestMapping(value="/selectByPrimaryKey")
	@ResponseBody
	public Msg selectByPrimaryKey(Integer adminId,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		ManageInfo p = manageImpl.selectByPrimaryKey(adminId,request);
		return Msg.success().add("data", p);
	}

	@RequestMapping(value="/insertManageInfo")
	@ResponseBody
	public Msg insertManageInfo(ManageInfo record,HttpServletRequest request) {
		int p = manageImpl.insertManageInfo(record,request);
		return Msg.success().add("date", p);
	}
	
	@ResponseBody
	@RequestMapping(value="/delManageInfoByKey/{ids}",method=RequestMethod.DELETE)
	public Msg delManageInfoByKey(@PathVariable("ids")String ids){
		int i = manageImpl.delManageInfoByKey(ids);
		return Msg.success().add("date", i);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateManageInByKey",method=RequestMethod.POST)
	public Msg updateManageInByKey(ManageInfo record,HttpServletRequest request){
		
		return Msg.success();
	}
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public Msg saveOrUpdate(ManageInfo record,HttpServletRequest request){

		int i=0;
		System.out.println(record.getAdminId());
		if("".equals(record.getAdminId())) {
			record.setAdminId(null);
			i = manageImpl.insertManageInfo(record,request);
		}else {
			i = manageImpl.updateManageInByKey(record,request);
		}
		return Msg.success().add("data", i);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/loginVerify")
	public ModelAndView loginVerify(ModelAndView mav , @RequestParam("logname")String logname,@RequestParam("logpass") String logpass,HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			ManageInfo user = (ManageInfo)request.getSession().getAttribute("user");  
			if(user!=null){
				mav.setViewName("forward:../views/welcome/welcome1.jsp");
//				mav.setViewName("/login");
				 return mav;  
			}
			List<ManageInfo> list = manageImpl.loginVerify(logname, logpass);
			
			if(list.size()>0){
				ManageInfo ui =(ManageInfo) list.get(0);
				request.getSession().setAttribute("user",ui);
				request.getSession().setAttribute("userName",ui.getName());
				mav.setViewName("forward:/views/welcome/welcome1.jsp");
			}else{
//				mav.setViewName("forward:/login.jsp");
//				request.getRequestDispatcher("/login.jsp").forward(request, response);return null; 
				mav.setViewName("/login");
				mav.addObject("errorInfo", "账号或密码错误！");
			}
			
		} catch (Exception e) {
//			mav.setViewName("redirect:/jsp/index/index.jsp");
			mav.setViewName("forward:/login.jsp");
//			mav.setViewName("/login");

			mav.addObject("errorInfo", "账号或密码错误！");
			return mav;  
		}
		return mav;  
	}
}
