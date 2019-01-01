package com.activiti;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

public class BpmTest {

	static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	public static void main(String[] args) throws IOException {
		Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
				.createDeployment() //创建一个部署对象
				.name("helloworld入门程序")//添加部署的名称
				.addClasspathResource("bpmn/test.bpmn")//从classpath的资源中加载，一次只能加载一个文件
				.addClasspathResource("bpmn/test.png")//从classpath的资源中加载，一次只能加载一个文件
				.deploy(); //完成部署
			System.out.println("部署ID:"+deployment.getId());  //1
			System.out.println("部署名称"+deployment.getName()); //helloworld入门程序
			System.out.println("dddddddddddd");

//	        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("testStart");

	}
	
	@Test
	/**
	 * 部署流程定义 
	 * 一套定义文件只有一个流程定义Key, 但可以被部署多次形成多个版本(部署表里多个id和流程定义表里多个id)
	 * 涉及的表：act_re_deployment(部署表)、act_re_procdef(流程定义表)、act_ge_bytearray(二进制表)
	 */
	public void actDeployement() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("actTest1.zip");
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		processEngine.getRepositoryService().createDeployment()
				.name("activiti测试").addZipInputStream(zipInputStream).deploy();
	}
	

	
	

}
