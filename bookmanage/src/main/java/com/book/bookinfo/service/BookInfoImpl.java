package com.book.bookinfo.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.book.bookinfo.bean.BookInfo;
import com.book.bookinfo.bean.BookInfoExample;
import com.book.bookinfo.bean.BookInfoExample.Criteria;
import com.book.bookinfo.dao.BookInfoMapper;

@Service
public class BookInfoImpl implements IBookInfo{
	
	@Resource
	private BookInfoMapper bookInfoMapper;

	public List<BookInfo> selectByExample(BookInfo record, HttpServletRequest request) {
		BookInfoExample example = new BookInfoExample();
		Criteria c = example.createCriteria();
		return bookInfoMapper.selectByExample(example);
	}

	public int insertManageInfo(BookInfo record, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return bookInfoMapper.insertSelective(record);
	}

	public int delManageInfoByKey(String ids) {
		String[] bookIds = ids.split("-");
		return bookInfoMapper.deleteByPrimaryKey(bookIds);
	}

	public int updateManageInByKey(BookInfo record, HttpServletRequest request) {
		return bookInfoMapper.updateByPrimaryKey(record);
	}

	public BookInfo selectByPrimaryKey(Integer bookId, HttpServletRequest request) {
		return bookInfoMapper.selectByPrimaryKey(bookId);
	}

}
