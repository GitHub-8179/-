package com.book.bookinfo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.bookinfo.bean.BookInfo;

public interface IBookInfo {

	
	List<BookInfo> selectByExample(BookInfo record,HttpServletRequest request);
	BookInfo selectByPrimaryKey(Integer bookId,HttpServletRequest request);

	int insertManageInfo(BookInfo record,HttpServletRequest request);
	
	int delManageInfoByKey(String ids);
	
	int updateManageInByKey(BookInfo record,HttpServletRequest request);
	
}
