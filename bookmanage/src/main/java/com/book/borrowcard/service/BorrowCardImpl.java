package com.book.borrowcard.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.book.borrowcard.bean.BorrowCard;
import com.book.borrowcard.bean.BorrowCardExample;
import com.book.borrowcard.bean.BorrowCardExample.Criteria;
import com.book.borrowcard.dao.BorrowCardMapper;

@Service
public class BorrowCardImpl implements IBorrowCard{
	
	@Resource
	private BorrowCardMapper borrowCardMapper;

	public List<BorrowCard> selectByExample(BorrowCard record, HttpServletRequest request) {
		BorrowCardExample example = new BorrowCardExample();
		Criteria c = example.createCriteria();
		String userName= record.getUsername();
		if(userName!=null && !"".equals(userName)) {
			c.andUsernameLike(userName+"%");
		}
		
		String phone= record.getPhone();
		if(phone!=null && !"".equals(phone)) {
			c.andPhoneLike(phone+"%");
		}

		return borrowCardMapper.selectByExample(example);
	}

	public int insertManageInfo(BorrowCard record, HttpServletRequest request) {
		record.setRank(5);
		return borrowCardMapper.insertSelective(record);
	}

	public int delManageInfoByKey(String ids) {
		String[] cardIds = ids.split("-");
		return borrowCardMapper.deleteByPrimaryKey(cardIds);
	}

	public int updateManageInByKey(BorrowCard record, HttpServletRequest request) {
		return borrowCardMapper.updateByPrimaryKeySelective(record);
	}

	public BorrowCard selectByPrimaryKey(Integer cardId, HttpServletRequest request) {
		return borrowCardMapper.selectByPrimaryKey(cardId);
	}

}
