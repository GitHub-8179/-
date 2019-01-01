package com.book.manage.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;

import com.book.borrowcard.bean.BorrowCard;
import com.book.manage.bean.ManageInfo;

public interface IManage {
	List<ManageInfo> selectByExample(ManageInfo record,HttpServletRequest request);
	ManageInfo selectByPrimaryKey(Integer adminId,HttpServletRequest request);
	List<ManageInfo> selectNameVerify(ManageInfo record,HttpServletRequest request);

	int insertManageInfo(ManageInfo record,HttpServletRequest request);
	
	int delManageInfoByKey(String ids);
	
	int updateManageInByKey(ManageInfo record,HttpServletRequest request);
	
	
	List<ManageInfo> loginVerify(String logname, String logpass);


}
