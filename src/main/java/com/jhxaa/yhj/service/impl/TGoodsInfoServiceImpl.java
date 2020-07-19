package com.jhxaa.yhj.service.impl;

import com.alibaba.fastjson.JSON;
import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.executor.FindCategoryGoodsInfoByConditionsExecutor;
import com.jhxaa.yhj.executor.impl.FindCategoryGoodsInfoByHotGoodsExecutorImpl;
import com.jhxaa.yhj.executor.impl.FindCategoryGoodsInfoByNewExecutorImpl;
import com.jhxaa.yhj.executor.impl.FindCategoryGoodsInfoByPrice9ExecutorImpl;
import com.jhxaa.yhj.manage.easyexcl.DataListener;
import com.jhxaa.yhj.manage.easyexcl.Excl;
import com.jhxaa.yhj.manage.easyexcl.excelEntity.GoodsInfoExcel;
import com.jhxaa.yhj.manage.redis.RedisUtil;
import com.jhxaa.yhj.mapper.TGoodsInfoMapper;
import com.jhxaa.yhj.pojo.Page;
import com.jhxaa.yhj.pojo.TGoodsInfo;
import com.jhxaa.yhj.request.SelectGoodsInfoListRequestBean;
import com.jhxaa.yhj.service.TGoodsInfoService;
import com.jhxaa.yhj.utli.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Service(value = "TGoodsInfoServiceImpl")
public class TGoodsInfoServiceImpl implements TGoodsInfoService {

    static Logger LOG = LogUtil.getLog(TGoodsInfoServiceImpl.class);

    @Autowired(required = false)
    TGoodsInfoMapper tGoodsInfoMapper;

    @Autowired(required = false)
    RedisUtil redisUtil;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(TGoodsInfo record) {
        return 0;
    }

    @Override
    public TGoodsInfo selectByPrimaryKey(Integer id) {
        TGoodsInfo tGoodsInfo = null;
        tGoodsInfo = tGoodsInfoMapper.selectByPrimaryKey(id);
        return tGoodsInfo;
    }

    @Override
    public List<TGoodsInfo> selectAll() {
        List<TGoodsInfo> goodsInfos = tGoodsInfoMapper.selectAll();
        return goodsInfos;
    }

    @Override
    public int updateByPrimaryKey(TGoodsInfo record) {
        return 0;
    }

    private String appendFilePath(String path, String dateStr, String fileName) {
        StringBuilder builder = new StringBuilder(path);
        builder.append(dateStr);
        builder.append(fileName);
        return builder.toString();
    }

    @Override
    public boolean batchReadExclInsertTGoodsInfo() {
        String fileNamePath = null;
        String path = PropertiesUtil.getString("file.path");
        String fileName = PropertiesUtil.getString("file.fileName");
        String toDateString = TimeUtil.toDateString(new Date(), TimeUtil.dateFormat.YYYY_MM_DD.value());
        fileNamePath = appendFilePath(path, toDateString, fileName);
        File file = new File(fileNamePath);
        Excl excl = null;
        if (file.isFile()) {
            LOG.info("有新的文件");
            excl = new Excl();
            excl.readExcl(fileNamePath);
            List<GoodsInfoExcel> goodsInfoExcelList = DataListener.list;
            List<TGoodsInfo> tGoodsInfos = convertTGoodsInfoList(goodsInfoExcelList);
            List<TGoodsInfo> tGoodsInfoList = CommonUtil.delRepeatList(tGoodsInfos);
            Integer selectDbCount = tGoodsInfoMapper.selectDbCount();
            Integer insertBatch = 0;
            List<TGoodsInfo> updateParamList = null;
            if (selectDbCount > 0) {
                updateParamList = new ArrayList<>();
                List<Long> goodsIdAllByGoodsId = tGoodsInfoMapper.findGoodsIdAllByGoodsId(tGoodsInfoList);
                LOG.info(String.format("=============:%s", goodsIdAllByGoodsId));
                int size = tGoodsInfoList.size();
                int size1 = goodsIdAllByGoodsId.size();
                TGoodsInfo tGoodsInfo = null;
                for (int i = 0; i < size; i++) {
                    tGoodsInfo = tGoodsInfoList.get(i);
                    Long tempLong = null;
                    for (int i1 = 0; i1 < size1; i1++) {
                        tempLong = goodsIdAllByGoodsId.get(i1);
                        if (tGoodsInfo.getfGoodsId().equals(tempLong)) {
                            updateParamList.add(tGoodsInfo);
                            tGoodsInfoList.remove(tGoodsInfo);
                            size = size - 1;
                            break;
                        }
                    }
                }
                Integer updateBatch = 0;
                if (updateParamList.size() > 0) {
                    updateBatch = tGoodsInfoMapper.updateBatch(updateParamList);
                }
                if (tGoodsInfoList.size() > 0) {
                    insertBatch = tGoodsInfoMapper.insertBatch(tGoodsInfoList);
                }
                LOG.info(String.format("更新数量[%s]:::添加数量[%s]", updateBatch, insertBatch));
            } else if (selectDbCount == 0 || selectDbCount <= 0) {
                insertBatch = tGoodsInfoMapper.insertBatch(tGoodsInfoList);
                LOG.info(String.format("没有数据，添加数量[%s]", insertBatch));
            }
        }
        excl = null;
        CommonUtil.clearList(DataListener.list);
        return true;
    }

    @Override
    public Page findPageGoodsInfoList(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        Page<TGoodsInfo> goodsInfoPage = new Page<>();
        String goodsName = selectGoodsInfoListRequestBean.getGoodsName();
        Integer pageIndex = selectGoodsInfoListRequestBean.getPageIndex();
        Integer pageSize = selectGoodsInfoListRequestBean.getPageSize();
        Double tempDoubleTypeStartPrice = selectGoodsInfoListRequestBean.getStartPrice();
        Double tempDoubleTypeEndPrice = selectGoodsInfoListRequestBean.getEndPrice();
        BigDecimal startPrice = null;
        BigDecimal endPrice = null;
        if (tempDoubleTypeStartPrice != null) {
            startPrice = CommonUtil.getBigDecimal(selectGoodsInfoListRequestBean.getStartPrice());
        }
        if (tempDoubleTypeEndPrice != null) {
            endPrice = CommonUtil.getBigDecimal(selectGoodsInfoListRequestBean.getEndPrice());
        }
        List<TGoodsInfo> pageGoodsInfoList = null;
        String screeningType = selectGoodsInfoListRequestBean.getScreeningType();
        String categoryType = selectGoodsInfoListRequestBean.getCategoryType();
        String navType = selectGoodsInfoListRequestBean.getNavType();
        if (categoryType != null && !"".equals(categoryType)) {
            goodsName = CommonUtil.SearchStrUtil.getSearchName(categoryType);
        }
        LOG.info(String.format("实体参数：%s", JSON.toJSONString(selectGoodsInfoListRequestBean)));
        if (navType != null && !"".equals(navType)) {
            FindCategoryGoodsInfoByConditionsExecutor.FindCategoryGoodsInfoByConditions
                    findCategoryGoodsInfoByConditions = new FindCategoryGoodsInfoByPrice9ExecutorImpl.FindCategoryGoodsInfoByConditions(
                    goodsName,
                    (pageIndex - 1),
                    pageSize,
                    startPrice,
                    endPrice,
                    screeningType,
                    selectGoodsInfoListRequestBean.getNavType()
            );
            switch (navType) {
                case "price9":
                    pageGoodsInfoList = new FindCategoryGoodsInfoByPrice9ExecutorImpl().findCategoryGoodsInfoByConditions(
                            tGoodsInfoMapper,
                            findCategoryGoodsInfoByConditions
                    );
                    break;
                case "hotGoods":
                    pageGoodsInfoList = new FindCategoryGoodsInfoByHotGoodsExecutorImpl().findCategoryGoodsInfoByConditions(
                            tGoodsInfoMapper,
                            findCategoryGoodsInfoByConditions
                    );
                    break;
                case "newGoods":
                    pageGoodsInfoList = new FindCategoryGoodsInfoByNewExecutorImpl().findCategoryGoodsInfoByConditions(
                            tGoodsInfoMapper,
                            findCategoryGoodsInfoByConditions
                    );
                    break;
                default:
                    break;
            }
        } else if (navType == null || "".equals(navType)) {
            pageGoodsInfoList = tGoodsInfoMapper.findPageGoodsInfoList(
                    goodsName,
                    (pageIndex - 1),
                    pageSize,
                    startPrice,
                    endPrice,
                    selectGoodsInfoListRequestBean.getScreeningType(),
                    selectGoodsInfoListRequestBean.getNavType()
            );
        }
        if (EmptyUtil.isListEmpty(pageGoodsInfoList)) {
            pageGoodsInfoList = new ArrayList<>(10);
        }
        Integer selectDbCount = 0;
        if (goodsName == null || "".equals(goodsName)) {
            selectDbCount = tGoodsInfoMapper.selectDbCount();
        } else {
            selectDbCount = tGoodsInfoMapper.selectGoodsInfoCountByGoodsName(goodsName);
        }
        goodsInfoPage.setPageSize(pageSize);
        goodsInfoPage.setCurrentPage(pageIndex);
        goodsInfoPage.setTotalCount(selectDbCount);
        goodsInfoPage.setList(pageGoodsInfoList);
        return goodsInfoPage;
    }

    @Override
    public List<String> findTopKeywordByRandom() {
        List<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Integer randomArrayIndex = TimeUtil.randomArrayIndex(5000);
            arrayList.add(randomArrayIndex);
        }
        List<String> topKeywordByRandom = null;
        topKeywordByRandom = tGoodsInfoMapper.findTopKeywordByRandom(arrayList);
        if (topKeywordByRandom == null || topKeywordByRandom.isEmpty()) {
            topKeywordByRandom = new ArrayList<>();
        }
        Set<String> hashSet = new HashSet<>();
        if (topKeywordByRandom.size() != 0) {
            for (int i = 0; i < topKeywordByRandom.size(); i++) {
                String[] split = topKeywordByRandom.get(i).split("/");
                for (int i1 = 0; i1 < split.length; i1++) {
                    hashSet.add(split[i1].trim());
                }
            }
            //   topKeywordByRandom.clear();
            //   topKeywordByRandom = new ArrayList<>(hashSet);
            List<String> tempList = new ArrayList<>(hashSet);
            List<String> stringArrayList = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Integer tempIndex = TimeUtil.randomArrayIndex(tempList.size());
                stringArrayList.add(tempList.get(tempIndex));
            }
            topKeywordByRandom.clear();
            topKeywordByRandom = stringArrayList;
        }
        return topKeywordByRandom;
    }

    @Override
    public List<String> findCategoryPageGoodsInfoList() {
        return null;
    }

    @Override
    public List<TGoodsInfo> findLoveGoodsListByCatoryName(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        Integer pageIndex = selectGoodsInfoListRequestBean.getPageIndex();
        Integer pageSize = selectGoodsInfoListRequestBean.getPageSize();
        String catoryName = selectGoodsInfoListRequestBean.getCatoryName();
        String[] splitArray = catoryName.split("/");
        catoryName = splitArray[TimeUtil.randomArrayIndex(splitArray.length)];
        if (pageIndex == null || pageIndex == 0) {
            Integer count = tGoodsInfoMapper.findLoveGoodsCountByCatoryName(catoryName);
            for (; ; ) {
                Integer arrayIndex = TimeUtil.randomArrayIndex(count);
                int iz = arrayIndex + pageSize;
                if (count >= iz) {
                    selectGoodsInfoListRequestBean.setPageIndex(arrayIndex);
                    break;
                }
                if (count <= pageSize) {
                    selectGoodsInfoListRequestBean.setPageIndex(arrayIndex);
                    break;
                }
            }
        }
        pageIndex = selectGoodsInfoListRequestBean.getPageIndex();
        List<TGoodsInfo> loveGoodsByCatoryName = tGoodsInfoMapper.findLoveGoodsByCatoryName(catoryName
                , pageIndex
                , pageSize);
        if (loveGoodsByCatoryName == null) {
            loveGoodsByCatoryName = new ArrayList<>();
        }
        return loveGoodsByCatoryName;
    }

    @Override
    public Boolean checkTGoodsInfo() {
        List<Integer> overdueGoodsIdAll = null;
        try {
            overdueGoodsIdAll = tGoodsInfoMapper.findOverdueGoodsIdAll();
            if (overdueGoodsIdAll != null && overdueGoodsIdAll.size() > 0) {
                Integer infoById = tGoodsInfoMapper.batchUpdateGoodsInfoById(overdueGoodsIdAll);
                LOG.info(String.format("受影响行数:::::%s", infoById));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new BusiException(ExceptionEnum.DB_UPDATE_ERRROR.value());
        }
        return true;
    }


    @Override
    public List<TGoodsInfo> findNowPreferentialGoodsList(Integer indexSize) {
        List<TGoodsInfo> nowPreferentialGoodsList = null;
        List<TGoodsInfo> objectList = null;
        objectList = redisUtil.lGet(Common.REDIS_DEI_NOW_MONEY9_GOODS);
        if (objectList.isEmpty() || objectList.size() == 0) {
            Integer goodsCount = tGoodsInfoMapper.findNowPreferentialGoodsCount();
            goodsCount = getCount(goodsCount);
            nowPreferentialGoodsList = tGoodsInfoMapper.findNowPreferentialGoodsList(goodsCount);
            redisUtil.lSet(Common.REDIS_DEI_NOW_MONEY9_GOODS, nowPreferentialGoodsList, 21600);
        }
        if (!objectList.isEmpty() && objectList.size() > 0) {
            nowPreferentialGoodsList = objectList;
        }
        LOG.info("长度：" + objectList.size());
        objectList = null;
        return nowPreferentialGoodsList;
    }

    @Override
    public List<TGoodsInfo> findNowTopGoodsList(Integer indexSize) {
        List<TGoodsInfo> tGoodsInfos = null;
        List<TGoodsInfo> objectList = null;
        objectList = redisUtil.lGet(Common.REDIS_DEI_NOW_JINGXUAN_GOODS);
        if (objectList.isEmpty() || objectList.size() == 0) {
            Integer goodsCount = tGoodsInfoMapper.findNowTopGoodsCount();
            goodsCount = getCount(goodsCount);
            tGoodsInfos = tGoodsInfoMapper.findNowTopGoodsList(goodsCount);
            redisUtil.lSet(Common.REDIS_DEI_NOW_JINGXUAN_GOODS, tGoodsInfos, 21600);
        }
        if (!objectList.isEmpty() && objectList.size() > 0) {
            tGoodsInfos = objectList;
        }
        LOG.info("长度：" + objectList.size());
        objectList = null;
        return tGoodsInfos;
    }


    //////////////////////////自定义方法//////////////////////////////////////////////////////

    private Integer getCount(Integer goodsCount) {
        for (; ; ) {
            Integer arrayIndex = TimeUtil.randomArrayIndex(goodsCount);
            int iz = arrayIndex + 10;
            if (goodsCount >= iz) {
                goodsCount = arrayIndex;
                break;
            }
            if (goodsCount <= 10) {
                goodsCount = arrayIndex;
                break;
            }
        }
        return goodsCount;
    }

    private List<TGoodsInfo> convertTGoodsInfoList(List<GoodsInfoExcel> goodsInfoExcelList) {
        List<TGoodsInfo> tGoodsInfos = new ArrayList<>();
        for (int i = 0; i < goodsInfoExcelList.size(); i++) {
            GoodsInfoExcel tempGoodsInfoExcel = goodsInfoExcelList.get(i);
            TGoodsInfo tempTGoodsInfo = new TGoodsInfo();
            tempTGoodsInfo.setfGoodsId(tempGoodsInfoExcel.getfGoodsId());
            tempTGoodsInfo.setfGoodsName(tempGoodsInfoExcel.getfGoodsName());
            tempTGoodsInfo.setfGoodsImage(tempGoodsInfoExcel.getfGoodsImage());
            tempTGoodsInfo.setfGoodsLevelOneCategoryName(tempGoodsInfoExcel.getfGoodsLevelOneCategoryName());
            tempTGoodsInfo.setfGoodsPrice(CommonUtil.getBigDecimal(tempGoodsInfoExcel.getfGoodsPrice()));
            tempTGoodsInfo.setfGoodsSalesCount(tempGoodsInfoExcel.getfGoodsSalesCount());
            tempTGoodsInfo.setfGoodsStoreName((tempGoodsInfoExcel.getfGoodsStoreName() == null || "".equals(tempGoodsInfoExcel.getfGoodsStoreName())) ? "(未知店铺名)" : tempGoodsInfoExcel.getfGoodsStoreName());
            tempTGoodsInfo.setfGoodsPlatformType(CommonUtil.getPlatformType(tempGoodsInfoExcel.getfGoodsPlatformType()));
            tempTGoodsInfo.setfGoodsTotalCouponCount(tempGoodsInfoExcel.getfGoodsTotalCouponCount());
            tempTGoodsInfo.setfGoodsCouponRemainingCount(tempGoodsInfoExcel.getfGoodsCouponRemainingCount());
            tempTGoodsInfo.setfGoodsCouponFaceValue(CommonUtil.handleStrGetBigDecimal(tempGoodsInfoExcel.getfGoodsCouponFaceValue()));
            tempTGoodsInfo.setfGoodsCouponStartTime(tempGoodsInfoExcel.getfGoodsCouponStartTime());
            tempTGoodsInfo.setfGoodsCouponEndTime(tempGoodsInfoExcel.getfGoodsCouponEndTime());
            tempTGoodsInfo.setfGoodsCouponPromoteLink(tempGoodsInfoExcel.getfGoodsCouponPromoteLink());
            tempTGoodsInfo.setfModifyTime(TimeUtil.getDate());
            tempTGoodsInfo.setfEffectiveType(Common.SYSTEM_FECTIVE_TYPE);
            //  tempTGoodsInfo.setfCreateTime(TimeUtil.getDate());
            tGoodsInfos.add(tempTGoodsInfo);
        }
        return tGoodsInfos;
    }

}
