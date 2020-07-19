package com.jhxaa.yhj.service.impl;

import com.jhxaa.yhj.dto.ProblemGoodsDto;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.mapper.TGoodsInfoProblemMapper;
import com.jhxaa.yhj.pojo.Page;
import com.jhxaa.yhj.pojo.TGoodsInfoProblem;
import com.jhxaa.yhj.request.SelectGoodsInfoListRequestBean;
import com.jhxaa.yhj.service.TGoodsInfoProblemService;
import com.jhxaa.yhj.utli.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TGoodsInfoProblemServiceImpl")
public class TGoodsInfoProblemServiceImpl implements TGoodsInfoProblemService {

    @Autowired(required = false)
    TGoodsInfoProblemMapper tGoodsInfoProblemMapper;

    public static void main(String[] args) {


    }

    @Override
    public void insert(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        Integer id = selectGoodsInfoListRequestBean.getId();
        Integer resultCount = null;
        resultCount = tGoodsInfoProblemMapper.findGoodsInfoProblemCountById(id);
        if (resultCount == null || resultCount >= 1) {
            throw new BusiException(ExceptionEnum.DB_REPEAT_DATA.value());
        }
        TGoodsInfoProblem tGoodsInfoProblem = new TGoodsInfoProblem(
                id,
                TimeUtil.getDate(),
                TimeUtil.getDate(),
                true
        );
        tGoodsInfoProblemMapper.insert(tGoodsInfoProblem);
    }

    @Override
    public Page getTGoodsInfoProblemList(Integer index, Integer size) {
        Integer count = tGoodsInfoProblemMapper.findGoodsInfoProblemCount();
        List<ProblemGoodsDto> dtoList = tGoodsInfoProblemMapper.findProbliemGoodsByPage(index - 1, size);
        Page<ProblemGoodsDto> page = new Page<>();
        page.setTotalCount(count);
        page.setCurrentPage(index);
        page.setPageSize(size);
        page.setList(dtoList);
        return page;
    }

    @Override
    public void modifyProbliemGoodsState(Integer id, Integer state) {
        tGoodsInfoProblemMapper.updateProbliemGoodsStateById(id, state);
    }

}
