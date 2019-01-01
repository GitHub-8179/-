package com.book.borrowcard.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.book.borrowcard.bean.BorrowCard;

public interface IBorrowCard {

	List<BorrowCard> selectByExample(BorrowCard record,HttpServletRequest request);
	BorrowCard selectByPrimaryKey(Integer cardId,HttpServletRequest request);

	
	int insertManageInfo(BorrowCard record,HttpServletRequest request);
	
	int delManageInfoByKey(String ids);
	
	int updateManageInByKey(BorrowCard record,HttpServletRequest request);
	
}
