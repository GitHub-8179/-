package com.book.borrowcard.dao;

import com.book.borrowcard.bean.BorrowCard;
import com.book.borrowcard.bean.BorrowCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowCardMapper {
    long countByExample(BorrowCardExample example);

    int deleteByExample(BorrowCardExample example);

    int deleteByPrimaryKey(@Param("cardIds")String[] cardIds);

    int insert(BorrowCard record);

    int insertSelective(BorrowCard record);

    List<BorrowCard> selectByExample(BorrowCardExample example);

    BorrowCard selectByPrimaryKey(Integer cardId);

    int updateByExampleSelective(@Param("record") BorrowCard record, @Param("example") BorrowCardExample example);

    int updateByExample(@Param("record") BorrowCard record, @Param("example") BorrowCardExample example);

    int updateByPrimaryKeySelective(BorrowCard record);

    int updateByPrimaryKey(BorrowCard record);
}