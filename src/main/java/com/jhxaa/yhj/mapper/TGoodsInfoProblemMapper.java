package com.jhxaa.yhj.mapper;

import com.jhxaa.yhj.dto.ProblemGoodsDto;
import com.jhxaa.yhj.pojo.TGoodsInfoProblem;

import java.util.List;

public interface TGoodsInfoProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsInfoProblem record);

    TGoodsInfoProblem selectByPrimaryKey(Integer id);

    List<TGoodsInfoProblem> selectAll();

    int updateByPrimaryKey(TGoodsInfoProblem record);

    int findGoodsInfoProblemCountById(Integer id);

    Integer findGoodsInfoProblemCount();

    List<ProblemGoodsDto> findProbliemGoodsByPage(Integer index, Integer size);


    Integer updateProbliemGoodsStateById(Integer id, Integer state);

}