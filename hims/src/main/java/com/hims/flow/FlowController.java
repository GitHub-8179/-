package com.hims.flow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Msg;
import com.hims.flow.service.IFlow;

@Controller
@RequestMapping(value="/flow")
public class FlowController {

	@Autowired
	private IFlow flowImpl;
	
	@RequestMapping(value="/startProcessInstanceByKey")
	@ResponseBody
	public Msg startProcessInstanceByKey(HttpServletRequest request) {
		System.out.println("startProcessInstanceByKey");
		ProcessInstance p = flowImpl.startProcessInstanceByKey(request);
		return Msg.success().add("data", p);
	}
	
	@RequestMapping(value="/complete")
	@ResponseBody
	public Msg complete(HttpServletRequest request) {
		if(flowImpl.complete(request)) {
			return Msg.success();
		}else {
			return Msg.fail();

		}
	}
	
	@RequestMapping(value="/findMyPersonalTask")
	@ResponseBody
	public Msg findMyPersonalTask(HttpServletRequest request) {
		List<Task> l = flowImpl.findMyPersonalTask(request);
		return Msg.success().add("data", l);
	}
	
	@RequestMapping(value="/processDefinitionKey")
	@ResponseBody
	public Msg processDefinitionKey(HttpServletRequest request) {
		List<ProcessDefinition> l= flowImpl.processDefinitionKey(request);
		return Msg.success().add("data", l);
	}
}
