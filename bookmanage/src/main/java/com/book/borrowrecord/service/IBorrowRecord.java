package com.book.borrowrecord.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.borrowrecord.bean.BorrowRecord;

public interface IBorrowRecord {

	List<BorrowRecord> selectByExample(BorrowRecord record,HttpServletRequest request);
	
	List<BorrowRecord> selBorrowReturn(BorrowRecord record,HttpServletRequest request);

	
	int insertManageInfo(BorrowRecord record,HttpServletRequest request);
	
	int delManageInfoByKey(String ids);
	
	int updateManageInByKey(BorrowRecord record,HttpServletRequest request);
	
	
}
