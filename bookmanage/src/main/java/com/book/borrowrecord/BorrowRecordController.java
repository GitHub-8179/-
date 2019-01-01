package com.book.borrowrecord;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.borrowrecord.bean.BorrowRecord;
import com.book.borrowrecord.service.IBorrowRecord;
import com.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/borrowRecord")
public class BorrowRecordController {

	@Autowired
	private IBorrowRecord borrowRecordImpl;

	@RequestMapping(value="/selectByExample")
	@ResponseBody
	public Msg selectByExample(BorrowRecord record,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		PageHelper.startPage(page, rows);
		List<BorrowRecord> p = borrowRecordImpl.selectByExample(record,request);
		PageInfo pageInfo = new PageInfo(p);
		return Msg.success().add("date", pageInfo);
	}

	@RequestMapping(value="/selBorrowReturn")
	@ResponseBody
	public Msg selBorrowReturn(BorrowRecord record,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		PageHelper.startPage(page, rows);
		PageInfo pageInfo =null;
		if(record.getCardId()==null||"".equals(record.getCardId())) {
			pageInfo = new PageInfo(null);
		}else {
			List<BorrowRecord> p = borrowRecordImpl.selBorrowReturn(record,request);
			pageInfo = new PageInfo(p);
		}
		return Msg.success().add("date", pageInfo);
	}
	
	@ResponseBody
	@RequestMapping(value="/delManageInfoByKey/{ids}",method=RequestMethod.DELETE)
	public Msg delManageInfoByKey(@PathVariable("ids")String ids){
		int i = borrowRecordImpl.delManageInfoByKey(ids);
		return Msg.success().add("date", i);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public Msg saveOrUpdate(BorrowRecord record,HttpServletRequest request){

		int i=0;
//		if(record.getCardId()==null||"".equals(record.getCardId())) {
			i = borrowRecordImpl.insertManageInfo(record,request);
//		}else {
//			i = borrowRecordImpl.updateManageInByKey(record,request);
//		}
		return Msg.success().add("data", i);
	}
	
}
