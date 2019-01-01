package com.hims.flow.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.common.Msg;

public interface IFlow {

	ProcessInstance startProcessInstanceByKey(HttpServletRequest request);
	
	boolean complete(HttpServletRequest request);
	
	List<Task> findMyPersonalTask(HttpServletRequest request);
	
	List<ProcessDefinition> processDefinitionKey(HttpServletRequest request);
	
	
}
