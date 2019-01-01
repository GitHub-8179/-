package com.book.borrowrecord.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.book.borrowrecord.bean.BorrowRecord;
import com.book.borrowrecord.bean.BorrowRecordExample;
import com.book.borrowrecord.bean.BorrowRecordExample.Criteria;
import com.book.borrowrecord.dao.BorrowRecordMapper;
import com.book.manage.bean.ManageInfo;

@Service
public class BorrowRecordImpl implements IBorrowRecord {
	
	@Resource
	private BorrowRecordMapper borrowRecordMapper;

	public List<BorrowRecord> selectByExample(BorrowRecord record, HttpServletRequest request) {

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("data");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			java.util.Date date_util = sdf.parse(startTime);
//			record.setData(date_util);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //转换为util.date
		
		return borrowRecordMapper.selectInfo(record, startTime, endTime);
	}
	
	
	public int insertManageInfo(BorrowRecord record, HttpServletRequest request) {
		record.setData(new Date());
		ManageInfo user = (ManageInfo)request.getSession().getAttribute("user");  
		if(user!=null) {
			record.setAdminId(Integer.valueOf(user.getAdminId()));
		}
		return borrowRecordMapper.insertInfo(record);
	}

	public int delManageInfoByKey(String ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateManageInByKey(BorrowRecord record, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List<BorrowRecord> selBorrowReturn(BorrowRecord record, HttpServletRequest request) {
		String startTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String endTime = request.getParameter("endTime");
		return borrowRecordMapper.selectInfo(record, startTime, endTime);
	}

	

}
