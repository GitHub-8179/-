package com.hims.flow.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.activiti.impl.WorkflowServiceImpl;
import com.activiti.test.BaseProcessService;

@Service
public class FlowImpl implements IFlow{

	@Autowired
	private WorkflowServiceImpl workflowServiceImpl;
	@Autowired
	private BaseProcessService baseProcessServiceImpl;

	@Override
	public ProcessInstance startProcessInstanceByKey(HttpServletRequest request) {
		
		try {
			Map map = new HashMap<String, Object>();
			String [] candidateUsers={"a","b","c"};
			map.put("userId", Arrays.asList(candidateUsers));

			ProcessInstance processInstance =workflowServiceImpl.startProcessInstanceByKey("testProject",map);
//			workflowServiceImpl.setVariables("62504",map);
			return processInstance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean complete(HttpServletRequest request) {
		Map<String, Object> variables = new HashMap<String, Object>();
		try {
//			baseProcessServiceImpl.claimTask("167509", "a");
//			String[] empLoyees = {"冯小刚经纪人","范冰冰经纪人","冯小刚"};
//			variables.put("userId",  Arrays.asList(empLoyees));
//			variables.put("type", 0);
//			variables.put("userId", "sss");

			workflowServiceImpl.complete("55017", variables);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Task> findMyPersonalTask(HttpServletRequest request) {
		try {
			ProcessInstance t= baseProcessServiceImpl.findProcessInstanceByProcInst("50001");
//			baseProcessServiceImpl.nextTaskDefinition(t.gett.getActivityId(),"");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Task> t = workflowServiceImpl.findMyPersonalTask("[冯小刚经纪人, 范冰冰经纪人, 冯小刚]");
		return null;
	}

	@Override
	public List<ProcessDefinition> processDefinitionKey(HttpServletRequest request) {
		try {
			
			workflowServiceImpl.queryHistoricInstance();
			workflowServiceImpl.queryHistoricActivitiInstance();
			workflowServiceImpl.queryHistoricTask();
			workflowServiceImpl.queryHistoricVariables();
//			Execution ps = baseProcessServiceImpl.findExecutionByProcInst("160001");
			
//			ProcessDefinition p = baseProcessServiceImpl.findLatestProcessDefinitionByPrcDefKey("testProject:1:60004");

//			System.out.println(ps.getActivityId());
//			TaskDefinition t =baseProcessServiceImpl.findTaskDefinition("testProject:1:60004");
//			Task task = baseProcessServiceImpl.findTaskByExecutionId("167501");
//			baseProcessServiceImpl.findPvmActivity("150002","jlsh");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workflowServiceImpl.processDefinitionKey("testProject:1:60004", 1, 3);
	}
	
}
