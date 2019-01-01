package com.hims.login.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface ILoginUserDao {
	public List loginVerify(@Param("user")String user,@Param("pass")String pass);

}
