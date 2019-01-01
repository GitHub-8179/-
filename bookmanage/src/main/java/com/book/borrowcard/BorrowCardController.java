package com.book.borrowcard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.book.borrowcard.bean.BorrowCard;
import com.book.borrowcard.service.IBorrowCard;
import com.book.manage.bean.ManageInfo;
import com.common.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value="/borrowCard")
public class BorrowCardController {

	@Autowired
	private IBorrowCard borrowCardImpl;
	
	
	@RequestMapping(value="/selectByExample")
	@ResponseBody
	public Msg selectByExample(BorrowCard record,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
		PageHelper.startPage(page, rows);
		List<BorrowCard> p = borrowCardImpl.selectByExample(record,request);
		PageInfo pageInfo = new PageInfo(p);
		return Msg.success().add("date", pageInfo);
	}

	@RequestMapping(value="/selectByPrimaryKey")
	@ResponseBody
	public Msg selectByPrimaryKey(Integer cardId,HttpServletRequest request) {
		BorrowCard p = borrowCardImpl.selectByPrimaryKey(cardId,request);
		return Msg.success().add("data", p);
	}
	
	@ResponseBody
	@RequestMapping(value="/delManageInfoByKey/{ids}",method=RequestMethod.DELETE)
	public Msg delManageInfoByKey(@PathVariable("ids")String ids){
		int i = borrowCardImpl.delManageInfoByKey(ids);
		return Msg.success().add("date", i);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public Msg saveOrUpdate(BorrowCard record,HttpServletRequest request){

		int i=0;
		if(record.getCardId()==null||"".equals(record.getCardId())) {
			i = borrowCardImpl.insertManageInfo(record,request);
		}else {
			i = borrowCardImpl.updateManageInByKey(record,request);
		}
		return Msg.success().add("data", i);
	}
	
}
