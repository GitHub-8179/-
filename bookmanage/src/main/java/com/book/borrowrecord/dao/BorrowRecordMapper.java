package com.book.borrowrecord.dao;

import com.book.borrowrecord.bean.BorrowRecord;
import com.book.borrowrecord.bean.BorrowRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowRecordMapper {
    long countByExample(BorrowRecordExample example);

    int deleteByExample(BorrowRecordExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(BorrowRecord record);

    int insertSelective(BorrowRecord record);
    
    int insertInfo(BorrowRecord record);


    List<BorrowRecord> selectByExample(BorrowRecordExample example);
    
    List<BorrowRecord> selectInfo(@Param("record")BorrowRecord borrowRecord,@Param("startTime")String startTime,@Param("endTime")String endTime);


    BorrowRecord selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") BorrowRecord record, @Param("example") BorrowRecordExample example);

    int updateByExample(@Param("record") BorrowRecord record, @Param("example") BorrowRecordExample example);

    int updateByPrimaryKeySelective(BorrowRecord record);

    int updateByPrimaryKey(BorrowRecord record);
}