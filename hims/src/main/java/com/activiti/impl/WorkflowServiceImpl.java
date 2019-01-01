package com.activiti.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl {

	/**
	 * 	RepositoryService
		管理流程定义
	 */
	@Autowired
	private RepositoryService repositoryService;
	/**
	 * 	RuntimeService
	执行管理，包括启动、推进、删除流程实例等操作
	 */
	@Autowired
	private RuntimeService runtimeService;
	/**
	 * TaskService
	任务管理
	 */
	@Autowired
	private TaskService taskService;
	/**
	 * 	FormService
	一个可选服务，任务表单管理
	 */
	@Autowired
	private FormService formService;
	/**
	 * HistoryService
	历史管理(执行完的数据的管理)
	 */
	@Autowired
	private HistoryService historyService;
//	processEngine.getRepositoryService().createDeploymentQuery().list(); // 查询部署
//	processEngine.getRuntimeService().createProcessInstanceQuery().list(); // 查询流程实例
//	processEngine.getTaskService().createTaskQuery().list(); // 查询个人任务 
//	processEngine.getIdentityService().createUserQuery().list(); // 查询用户
//	processEngine.getHistoryService().createHistoricActivityInstanceQuery().list(); //查询历史
	
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	public RuntimeService getRuntimeService() {
		return runtimeService;
	}
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	public HistoryService getHistoryService() {
		return historyService;
	}
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	
	
	/**
	 * 启动一个流程实例
     * 涉及的表：
     * act_ru_execution(流程实例表), 管理流程进度
     * act_ru_task(任务表), 进行到哪一个流程的哪一个任务, 该由谁完成
	 * @param processDefinitionKey 启动流程定制key
	 * @param variables 流程参数
	 * @return 
	 * @throws Exception
	 */
	   public ProcessInstance startProcessInstanceByKey(String processDefinitionKey,Map<String, Object> variables) throws Exception{
	        //方式一：根据流程定义id启动流程实例
	        //String processDefinitionId = "qjlc:6:904";
	        //ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
//		   Map<String, Object> variables = new HashMap<String, Object>();
//	        variables.put("key", "value");
	        //方式二：根据流程定义Key启动流程实例   推荐!流程定义有多个版本时会选择最新版本
	        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
	        return processInstance;
	    }  
	   
	   /**
	    * 启动一个流程实例
	    * @param processDefinitionKey 根据流程定义Key启动流程实例   推荐!流程定义有多个版本时会选择最新版本
	    * @return
	    * @throws Exception
	    */
	   public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) throws Exception{
	        //方式一：根据流程定义id启动流程实例
	        //String processDefinitionId = "qjlc:6:904";
	        //ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinitionId);
//		   Map<String, Object> variables = new HashMap<String, Object>();
//	        variables.put("key", "value");
	        //方式二：根据流程定义Key启动流程实例   推荐!流程定义有多个版本时会选择最新版本
	        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
	        return processInstance;
	    }  
	   
	   /**
	    * 通过RuntimeService 设置流程变量
	    * @param executionId  流程实例id
	    * @param variables "key", "value"
	    */
	    public void setVariables(String executionId,Map<String, Object> variables) {
	        //processEngine.getRuntimeService().setVariable(executionId, variableName, value);
	        runtimeService.setVariables(executionId, variables);
	    }
	    /**
	     * 通过TaskService 设置流程变量
	     * @param taskId 流程实例id
	     * @param key key
	     * @param value value
	     */
	    public void setVariable(String taskId,String key,String value) {
	        taskService.setVariable(taskId , key, value);
	    }
	   
	   /**
	    * 办理任务, 办理后框架自动移动到下一任务
	    * 涉及的表: act_ru_execution(流程实例表)、act_ru_task(任务表)
	    * @param taskId 流程执行id
	    * @param variables 设置流程变量, 更实用
	    * @throws Exception
	    */
	    public void complete(String taskId,Map<String, Object> variables) throws Exception{
//	    	String[] empLoyees = {"冯小刚经纪人","范冰冰经纪人","冯小刚"};
//	    	taskService.addCandidateUser("userIds", Arrays.asList(empLoyees)); // 设置多个受理人
//	    	taskService.addCandidateGroup("", ""); //设置受理人组
	        taskService.complete(taskId,variables);
	    }
	
	    /**
	     * 通过RuntimeService 获取流程变量
	     * @param executionId 任务id
	     * @param key 
	     */
	    public Object getVariables(String executionId ,String key ) {
	        Object value = runtimeService.getVariable(executionId, key);
	        return value;
	    }
	    /**
	     *  通过TaskService 获取流程变量
	     * @param taskId 流程实例id
	     * @param key 
	     */
	    public Object test6(String taskId ,String key) {
	        Object value = taskService.getVariable(taskId, key);
	        return value;
	    }
	    
	    
	    /**
	     * 查询流程定义
	     * 涉及的表：act_re_procdef
	     * @param processDefinitionKey 流程定义key
	     * @param index 每页多少条
	     * @param page 第几页
	     * @return
	     */
	     public List<ProcessDefinition> processDefinitionKey(String processDefinitionKey,int index,int page){
	         ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
	         // 查询条件过滤
	         query.processDefinitionKey(processDefinitionKey);
	         query.orderByProcessDefinitionVersion().asc();
//	         List<ProcessDefinition> list = query.listPage(page,index);
	         List<ProcessDefinition> list = query.list();
//	         for (ProcessDefinition processDefinition : list) {
//	             System.out.println(processDefinition.getId());
//	         }
	         return list;
	     }
	     
	     /**
	      *  查询当前人的个人任务
	      * @param assignee "张三"
	      */
	 	public List<Task> findMyPersonalTask(String assignee){
	 		 List<Task> list = taskService//与正在执行的任务管理相关的Service
	 						.createTaskQuery()//创建任务查询
//	 						.taskAssignee(assignee)//指定个人任查询，指定办理人
//	 						.taskCandidateGroup(assignee)
//	 						.taskCandidateUser()
	 						.list();
	 		if(list!=null && list.size()>0){
	 			for(Task task:list){
	 				System.out.println("任务ID:"+task.getId());
	 				System.out.println("任务名称:"+task.getName());
	 				System.out.println("任务的创建时间:"+task.getCreateTime());
	 				System.out.println("任务的办理人:"+task.getAssignee());
	 				System.out.println("流程实例ID:"+task.getProcessInstanceId());
	 				System.out.println("执行对象ID:"+task.getExecutionId());
	 				System.out.println("流程定义ID:"+task.getProcessDefinitionId());
	 				System.out.println("############################################");
	 			}
	 		}
	 		return list;
	 	}
	     /**
	      * 删除流程定义
	      * @param deploymentId 部署id
	      * @param cascade 级联删除, 设置为true的话, 有正在跑的流程实例及任务也会被删除
	      */
	     public void deleteDeployment(String deploymentId,boolean cascade){
	         repositoryService.deleteDeployment(deploymentId, cascade);
	     }
	     /**
	      * 删除流程实例
	      * @param processInstanceId 流程id
	      * @param deleteReason 删除原因
	      * @throws Exception
	      */
	     public void deleteProcessInstance(String processInstanceId,String deleteReason ) throws Exception{
	         runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
	     }
	     // 根据部署id, 获取定义文件
	     public void test3() throws Exception{
//	         String deploymentId = "201"; //部署id
	         String deploymentId = "157501"; //部署id
//	         String deploymentId = "testProject:4:110004"; //流程定义id

	         
	         // 先获得定义文件的名字
	         List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
	         for (String name : names) {
	             InputStream in = repositoryService.getResourceAsStream(deploymentId, name);
//	             FileUtils.copyInputStreamToFile(in, new File("d:\\"+name));
	             
	             OutputStream outputStream=null;
		         File file = new File("d:\\"+name);
//		         file.mkdirs();
		         outputStream = new FileOutputStream(file);
		         int bytesWritten = 0;
		         int byteCount = 0;
		         byte[] bytes = new byte[1024];
		         while ((byteCount = in.read(bytes)) != -1)
		         {
		        	 
		        	 outputStream.write(bytes,0,byteCount);
//		             outputStream.write(bytes, bytesWritten, byteCount);
//		             bytesWritten += byteCount;
		         }
		         outputStream.close();
	             in.close();
	         }
	     }
	     // 根据流程定义id, 获取定义文件
	     public void test4() throws Exception{
//	         String processDefinitionId = "qjlc:6:904"; //流程定义id
	         String processDefinitionId = "testProject:4:110004"; //流程定义id

	         InputStream pngStream = repositoryService.getProcessDiagram(processDefinitionId);
	         
	         OutputStream outputStream=null;
	         File file = new File("d:\\abc.png");
	         outputStream = new FileOutputStream(file);
	         int bytesWritten = 0;
	         int byteCount = 0;
	         byte[] bytes = new byte[1024];
	         while ((byteCount = pngStream.read(bytes)) != -1)
	         {
	        	 
	        	 outputStream.write(bytes,0,byteCount);
//	             outputStream.write(bytes, bytesWritten, byteCount);
//	             bytesWritten += byteCount;
	         }
	         pngStream.close();
	         outputStream.close();
//	         FileUtils.copyInputStreamToFile(pngStream, new File("d:\\abc.png"));
	     }
	     
	     
	     
	     
	     
	     
	     @Test
	     public void queryHistoricInstance() {
	         List<HistoricProcessInstance> list = historyService
	                 .createHistoricProcessInstanceQuery()
//	                 .processDefinitionKey("testProject")
	                 .orderByProcessInstanceStartTime().asc()//排序
	                 .list();
	         if (list != null && list.size() > 0) {
	             for (HistoricProcessInstance hpi : list) {
	                 System.out.println("流程定义ID：" + hpi.getProcessDefinitionId());
	                 System.out.println("流程实例ID：" + hpi.getId());
	                 System.out.println("开始时间：" + hpi.getStartTime());
	                 System.out.println("结束时间：" + hpi.getEndTime());
	                 System.out.println("流程持续时间：" + hpi.getDurationInMillis());
	                 System.out.println("=======================================");
	             }
	         }
	     }
	     
	     /**
	      * 某一次流程执行了多少步
	      */
	     @Test
	     public void queryHistoricActivitiInstance() {
	         String processInstanceId = "160001";
	         List<HistoricActivityInstance> list = historyService
	                 .createHistoricActivityInstanceQuery()
	                 .processInstanceId(processInstanceId)
	                 .list();
	         if (list != null && list.size() > 0) {
	             for (HistoricActivityInstance hai : list) {
	                 System.out.println(hai.getId());
	                 System.out.println("步骤ID：" + hai.getActivityId());
	                 System.out.println("步骤名称：" + hai.getActivityName());
	                 System.out.println("执行人：" + hai.getAssignee());
	                 System.out.println("====================================");
	             }
	         }
	     }

	     /**
	      * 某一次流程的执行经历的多少任务
	      */
	     @Test
	     public void queryHistoricTask() {
	         String processInstanceId = "160001";
	         List<HistoricTaskInstance> list = historyService
	                 .createHistoricTaskInstanceQuery()
	                 .processInstanceId(processInstanceId)
	                 .list();
	         if (list != null && list.size() > 0) {
	             for (HistoricTaskInstance hti : list) {
	                 System.out.print("活动id taskId:" + hti.getId()+"，");
	                 System.out.print("活动名称 name:" + hti.getName()+"，");
	                 System.out.print("流程定义id pdId:" + hti.getProcessDefinitionId()+"，");
	                 System.out.print("参与人 assignee:" + hti.getAssignee()+"，");
	                 System.out.println();
	             }
	         }
	     }

	     /**
	      * 某一次流程的执行时设置的流程变量
	      */
	     @Test
	     public void queryHistoricVariables() {
	         String processInstanceId = "160001";
	         List<HistoricVariableInstance> list = historyService
	                 .createHistoricVariableInstanceQuery()
	                 .processInstanceId(processInstanceId)
	                 .list();
	         if(list != null && list.size()>0){
	             for(HistoricVariableInstance hvi : list){
	                 System.out.print("流程实例 id piId:"+hvi.getProcessInstanceId()+"，");
	                 System.out.print("参数类型variablesName:"+hvi.getVariableName()+"，");
	                 System.out.println("参数数据variablesValue:"+hvi.getValue()+";");
	             }
	         }
	     }
}
