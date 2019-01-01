package com.book.bookinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.bookinfo.bean.BookInfo;
import com.book.bookinfo.service.IBookInfo;
import com.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/bookInfo")
public class BookInfoController {

	@Autowired
	private IBookInfo bookInfoImpl;
	
	
	@RequestMapping(value="/selectByExample")
	@ResponseBody
	public Msg selectByExample(BookInfo record,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		PageHelper.startPage(page, rows);
		List<BookInfo> p = bookInfoImpl.selectByExample(record,request);
		PageInfo pageInfo = new PageInfo(p);
		return Msg.success().add("date", pageInfo);
	}

	@RequestMapping(value="/selectByPrimaryKey")
	@ResponseBody
	public Msg selectByPrimaryKey(Integer bookId,HttpServletRequest request) {
		BookInfo p = bookInfoImpl.selectByPrimaryKey(bookId,request);
		return Msg.success().add("data", p);
	}
	
	@ResponseBody
	@RequestMapping(value="/delManageInfoByKey/{ids}",method=RequestMethod.DELETE)
	public Msg delManageInfoByKey(@PathVariable("ids")String ids){
		int i = bookInfoImpl.delManageInfoByKey(ids);
		return Msg.success().add("data", i);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public Msg saveOrUpdate(BookInfo record,HttpServletRequest request){

		int i=0;
		if(record.getBookId()==null||"".equals(record.getBookId())) {
			i = bookInfoImpl.insertManageInfo(record,request);
		}else {
			i = bookInfoImpl.updateManageInByKey(record,request);
		}
		return Msg.success().add("data", i);
	}
}
