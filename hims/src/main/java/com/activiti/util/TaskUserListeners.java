package com.activiti.util;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskUserListeners implements TaskListener {
	
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println("当前流程参与人："+delegateTask.getAssignee());
		System.out.println("创建时间："+delegateTask.getCreateTime());
		System.out.println("任务定义key："+delegateTask.getFormKey());
		System.out.println("当前任务id："+delegateTask.getId());
		System.out.println("当前任务名称："+delegateTask.getName());
		System.out.println("流程实例id:"+delegateTask.getProcessInstanceId());
		System.out.println("流程定义id:"+delegateTask.getProcessDefinitionId());
		System.out.println("执行对象ID:"+delegateTask.getExecutionId());
		System.out.println("执行对象名称："+delegateTask.getEventName());
		Object map = delegateTask.getVariable("userId");
		delegateTask.setAssignee("sss");
	}

}
