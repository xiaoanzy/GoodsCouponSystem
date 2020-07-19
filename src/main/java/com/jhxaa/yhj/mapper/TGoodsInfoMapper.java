package com.jhxaa.yhj.mapper;

import com.jhxaa.yhj.pojo.TGoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TGoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TGoodsInfo record);

    TGoodsInfo selectByPrimaryKey(Integer id);

    List<TGoodsInfo> selectAll();

    int updateByPrimaryKey(TGoodsInfo record);


    /**
     * 查询总数
     *
     * @return
     */
    Integer selectDbCount();


    Integer selectGoodsInfoCountByGoodsName(@Param("goodsName") String goodsName);

    List<TGoodsInfo> findPageGoodsInfoList(String goodsName,
                                           Integer pageIndex,
                                           Integer pageSize,
                                           BigDecimal startPrice,
                                           BigDecimal endPrice,
                                           String screeningType,
                                           String navType
    );

//    List<TGoodsInfo> findCategoryPageGoodsInfoList(String goodsName,
//                                                   Integer pageIndex,
//                                                   Integer pageSize,
//                                                   BigDecimal startPrice,
//                                                   BigDecimal endPrice,
//                                                   String screeningType,
//                                                   String navType
//    );

    //List<Map<String, Integer>>
    List<String> findTopKeywordByRandom(List<Integer> randomList);

    Integer findLoveGoodsCountByCatoryName(String catoryName);

    //getRandomLoveGoodsList
    List<TGoodsInfo> findLoveGoodsByCatoryName(String catoryName,
                                               Integer pageIndex,
                                               Integer pageSize);


    List<TGoodsInfo> findPrice9CategoryGoodsInfoByConditions(String goodsName,
                                                             Integer pageIndex,
                                                             Integer pageSize,
                                                             BigDecimal startPrice,
                                                             BigDecimal endPrice,
                                                             String screeningType,
                                                             String navType
    );

    List<TGoodsInfo> findSentimentCategoryGoodsInfoByConditions(String goodsName,
                                                                Integer pageIndex,
                                                                Integer pageSize,
                                                                BigDecimal startPrice,
                                                                BigDecimal endPrice,
                                                                String screeningType,
                                                                String navType
    );

    List<TGoodsInfo> findNewCategoryGoodsInfoByConditions(String goodsName,
                                                          Integer pageIndex,
                                                          Integer pageSize,
                                                          BigDecimal startPrice,
                                                          BigDecimal endPrice,
                                                          String screeningType,
                                                          String navType
    );

    List<TGoodsInfo> findOverdueGoodsInfoAll();


    List<Integer> findOverdueGoodsIdAll();

    Integer batchUpdateGoodsInfoById(List<Integer> list);


    List<Long> findGoodsIdAllByGoodsId(List<TGoodsInfo> list);

    Integer insertBatch(List<TGoodsInfo> list);

    Integer updateBatch(List<TGoodsInfo> list);


    Integer findNowPreferentialGoodsCount();

    List<TGoodsInfo> findNowPreferentialGoodsList(Integer indexSize);

    Integer findNowTopGoodsCount();

    List<TGoodsInfo> findNowTopGoodsList(Integer indexSize);


    List<Map<String, Integer>> findGoodsInfoByEffectiveTypeGroup();

    Integer findGoodsInfoCountByCreateTime();

    Integer findGoodsInfoCount();


    List<Integer> findKeyByGoodsId(Long goodsId, Integer count);

    List<Map<String, Long>> statisticalRepeatGoodsId();


    Integer updateFailureGoodsInfoByPrimaryKey(List<Integer> list);


}

