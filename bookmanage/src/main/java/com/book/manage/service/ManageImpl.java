package com.book.manage.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.manage.bean.ManageInfo;
import com.book.manage.bean.ManageInfoExample;
import com.book.manage.bean.ManageInfoExample.Criteria;
import com.book.manage.bean.ManageInfoExample.Criterion;
import com.book.manage.dao.ManageInfoMapper;
import com.common.md5.Md5;

@Service
public class ManageImpl implements IManage{

	@Autowired
	private ManageInfoMapper manageInfoMapper;
	
	public List<ManageInfo> selectByExample(ManageInfo record,HttpServletRequest request) {
		ManageInfoExample example= new ManageInfoExample();
		Criteria c = example.createCriteria();
		String name = record.getName();
		if(name!=null&&!"".equals(name)) {
			c.andNameLike(name+"%");
		}
		String effectTime = record.getEffectTime();
		if(effectTime!=null&&!"".equals(effectTime)) {
//			c.andEffectTimeLessThanOrEqualTo(effectTime); //小于等于
			c.andEffectTimeGreaterThanOrEqualTo(effectTime); //大于等于当前日期

		}
		
		return manageInfoMapper.selectByExample(example);
	}

	public int insertManageInfo(ManageInfo record, HttpServletRequest request) {
		try {
			record.setPassword( Md5.EncoderByMd5(record.getPassword()) );
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manageInfoMapper.insertSelective(record);
	}

	public int delManageInfoByKey(String ids) {
		String[] adminId = ids.split("-");
		return manageInfoMapper.deleteByPrimaryKey(adminId);
	}

	public int updateManageInByKey(ManageInfo record, HttpServletRequest request) {

		return manageInfoMapper.updateByPrimaryKeySelective(record);
	}

	public List<ManageInfo> loginVerify(String logname, String logpass) {
		try {
			logpass = Md5.EncoderByMd5(logpass);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manageInfoMapper.loginVerify(logname, logpass);
	}

	public ManageInfo selectByPrimaryKey(Integer adminId, HttpServletRequest request) {
		return manageInfoMapper.selectByPrimaryKey(adminId);
	}

	public List<ManageInfo> selectNameVerify(ManageInfo record, HttpServletRequest request) {
		ManageInfoExample example= new ManageInfoExample();
		Criteria c = example.createCriteria();
		String name = record.getName();
		if(name!=null&&!"".equals(name)) {
			c.andNameEqualTo(name);
		}
		
		return manageInfoMapper.selectByExample(example);		
	}

}
