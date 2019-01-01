package com.book.manage.dao;

import com.book.manage.bean.ManageInfo;
import com.book.manage.bean.ManageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageInfoMapper {
	//按条件计数
    long countByExample(ManageInfoExample example);

    //按条件删除
    int deleteByExample(ManageInfoExample example);

    //按主键删除
    int deleteByPrimaryKey(@Param("adminIds")String[] adminIds);

    	//插入数据（返回值为ID）
    int insert(ManageInfo record);

    //有选择的插入
    int insertSelective(ManageInfo record);

    //条件查询
    List<ManageInfo> selectByExample(ManageInfoExample example);
    
    List<ManageInfo> loginVerify(@Param("logname")String logname,@Param("logpass") String logpass);


   //主键查询
    ManageInfo selectByPrimaryKey(Integer adminId);

    //按照条件有选择的更新
    int updateByExampleSelective(@Param("record") ManageInfo record, @Param("example") ManageInfoExample example);

    //更新值所有的字段
    int updateByExample(@Param("record") ManageInfo record, @Param("example") ManageInfoExample example);

    //按照主键有选择的的字段
    int updateByPrimaryKeySelective(ManageInfo record);
    
    //按照主键全部更新字段
    int updateByPrimaryKey(ManageInfo record);
}