package com.jhxaa.yhj.controller;

import com.alibaba.fastjson.JSON;
import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;
import com.jhxaa.yhj.pojo.FoodInfoEntity;
import com.jhxaa.yhj.pojo.Page;
import com.jhxaa.yhj.pojo.TGoodsInfo;
import com.jhxaa.yhj.request.SelectGoodsInfoListRequestBean;
import com.jhxaa.yhj.request.UserRequestBean;
import com.jhxaa.yhj.response.Result;
import com.jhxaa.yhj.service.AdminService;
import com.jhxaa.yhj.service.TGoodsInfoProblemService;
import com.jhxaa.yhj.service.TGoodsInfoService;
import com.jhxaa.yhj.service.TUserService;
import com.jhxaa.yhj.utli.*;
import com.jhxaa.yhj.vo.CountVo;
import com.jhxaa.yhj.vo.UserInfoVo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class GoodsInfoController {


    static Logger LOG = LogUtil.getLog(GoodsInfoController.class);

    @Autowired
    TUserService tUserService;

    @Autowired
    TGoodsInfoService tGoodsInfoService;

    @Autowired
    TGoodsInfoProblemService tGoodsInfoProblemService;

    @Autowired
    AdminService adminService;


    @RequestMapping("user/doLogin")
    public Object doLogin(HttpServletRequest request, UserRequestBean userRequestBean) {
        System.out.println(JSON.toJSONString(request.getParameterNames()));
        String userName = userRequestBean.getUserName();
        String userPassword = userRequestBean.getUserPassword();
        if (EmptyUtil.isEmptyString(userName) || EmptyUtil.isEmptyString(userPassword)) {
            throw new BusiException(ExceptionEnum.USER_CHECK_EMPTY_FAIL.value());
        }
        Map checkUser = tUserService.checkUser(userName, userPassword);
        Result<Object> objectResult = new Result();
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setData(checkUser);
        return objectResult;
    }


    @RequestMapping("user/getUserInfo")
    public Object getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoVo userInfo = tUserService.getUserInfo(token);
        Result<Object> objectResult = new Result();
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setData(userInfo);
        return objectResult;
    }


    @RequestMapping(method = RequestMethod.GET, value = "insertGoodsInfoProblem")

    public Result insertTGoodsInfoProblem(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        Integer id = selectGoodsInfoListRequestBean.getId();
        if (id == null || id == 0 || id <= 0) {
            throw new BusiException(ExceptionEnum.SERVICE_PARAM_EMPTY.value());
        }
        tGoodsInfoProblemService.insert(selectGoodsInfoListRequestBean);
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        return objectResult;
    }


    /**
     * 根据商品ID查询单个商品信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "selectOneGoodsInfo")

    public Object selectGoodsInfoOne(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        LOG.info(String.format("selectGoodsInfoOne方法入参数：%s", JSON.toJSONString(selectGoodsInfoListRequestBean)));
        Integer id = selectGoodsInfoListRequestBean.getId();
        if (id == null || id == 0) {
            throw new BusiException(ExceptionEnum.SERVICE_PARAM_EMPTY.value());
        }
        TGoodsInfo tGoodsInfo = null;
        tGoodsInfo = tGoodsInfoService.selectByPrimaryKey(id);
        Result<TGoodsInfo> objectResult = new Result<>();
        objectResult.setData(tGoodsInfo);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }


    /**
     * 商品list查询
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public Object list(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        String goodsName = CommonUtil.getURLDecoderString(selectGoodsInfoListRequestBean.getGoodsName());
        selectGoodsInfoListRequestBean.setGoodsName(goodsName);
        LOG.info(String.format("list方法入参数：%s", JSON.toJSONString(selectGoodsInfoListRequestBean)));
        Integer pageSize = selectGoodsInfoListRequestBean.getPageSize();
        Integer pageIndex = selectGoodsInfoListRequestBean.getPageIndex();
        if (pageSize == null || pageSize == 0) {
            selectGoodsInfoListRequestBean.setPageSize(Common.DEFAULT_PAGE_SIZE);
        }
        if (pageIndex == null || pageIndex == 0) {
            selectGoodsInfoListRequestBean.setPageIndex(Common.DEFAULT_PAGE_INDEX);
        }
        Page pageGoodsInfoList = tGoodsInfoService.findPageGoodsInfoList(selectGoodsInfoListRequestBean);
        Result<Page> objectResult = new Result<>();
        objectResult.setData(pageGoodsInfoList);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }


    /**
     * 获取
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "getRandomLoveGoodsList")
    public Object getRandomLoveGoodsList(SelectGoodsInfoListRequestBean selectGoodsInfoListRequestBean) {
        LOG.info("selectGoodsInfoListRequestBean方法入参数:" + JSON.toJSONString(selectGoodsInfoListRequestBean));
        Integer pageSize = selectGoodsInfoListRequestBean.getPageSize();
        if (pageSize == null || pageSize == 0) {
            selectGoodsInfoListRequestBean.setPageSize(20);
        }
        List<TGoodsInfo> stringList = tGoodsInfoService.findLoveGoodsListByCatoryName(selectGoodsInfoListRequestBean);
        Result<Object> objectResult = new Result<>();
        objectResult.setData(stringList);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }


    /**
     * 获取
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "getHotKeywordList")
    public Object findTopKeywordByRandom() {
        List<String> stringList = tGoodsInfoService.findTopKeywordByRandom();
        Result<Object> objectResult = new Result<>();
        objectResult.setData(stringList);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }


    /**
     * 查询9.9包邮商品
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "findNowPreferentialGoodsList")
    public Object findNowPreferentialGoodsList() {
        List<TGoodsInfo> nowTopGoodsList = tGoodsInfoService.findNowPreferentialGoodsList(10);
        Result<Object> objectResult = new Result<>();
        objectResult.setData(nowTopGoodsList);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }

    /**
     * 查询精选商品
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "findNowTopGoodsList")
    public Object findNowTopGoodsList() {
        List<TGoodsInfo> nowTopGoodsList = tGoodsInfoService.findNowTopGoodsList(20);
        Result<Object> objectResult = new Result<>();
        objectResult.setData(nowTopGoodsList);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }

    @RequestMapping("admin/findCount")
    public Result getCount() {
        Map<String, Integer> map = adminService.selectCount();
        Result<Object> objectResult = new Result<>();
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
//        List<CountVo> countVos = resultData(map);
//        objectResult.setData(countVos);
        objectResult.setData(map);
        return objectResult;
    }

    /**
     * 添加商品
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertGoodsInfo")
    public Object insertGoodsInfo() {
        boolean b = tGoodsInfoService.batchReadExclInsertTGoodsInfo();
        Result<Object> objectResult = new Result<>();
        objectResult.setData(b);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }


    @RequestMapping("/admin/getProblemGoodsList")
    public Object getProblemGoodsList(Integer index, Integer size) {
        if (null == index || 0 == index) {
            index = 1;
        }
        if (null == size || 0 == size) {
            size = 10;
        }
        Page list = tGoodsInfoProblemService.getTGoodsInfoProblemList(index, size);
        Result<Page> objectResult = new Result<>();
        objectResult.setData(list);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }

    @RequestMapping("/admin/modifyProblemGoodsState")
    public Object modifyProbliemGoodsState(Integer id, Integer state) {
        if (EmptyUtil.isEmptyInteger(id)) {
            throw new BusiException(ExceptionEnum.SERVICE_PARAM_EMPTY.value());
        }
        tGoodsInfoProblemService.modifyProbliemGoodsState(id, state);
        Result<Page> objectResult = new Result<>();
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }


    /**
     * 校验商品
     * *
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/admin/checkGoodsInfo")
    public Object checkGoodsInfo() {
        boolean b = tGoodsInfoService.checkTGoodsInfo();
        Result<Object> objectResult = new Result<>();
        objectResult.setData(b);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }

    /**
     * 校验商品
     * *
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/admin/clearRepeatGoods")
    public Object clearRepeatGoods() {
        adminService.clearRepeatGoods();
        Result<Object> objectResult = new Result<>();
//        objectResult.setData(b);
        objectResult.setMessage(Common.SYSTEM_RES_OK);
        objectResult.setCode(Common.SYSTEM_SESSUES_0);
        return objectResult;
    }

    @PostMapping("/uploadXls")
    public Object upload(FoodInfoEntity entity) {
        LOG.info("name " + entity.getName());
        LOG.info("des " + entity.getDes());
        MultipartFile[] files = entity.getFile();
        List<String> fileList = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            System.out.println("file Name " + files[i].getOriginalFilename());
            String fileName = files[i].getOriginalFilename();
            String filePath = PropertiesUtil.getString("file.path");
            File dest = new File(filePath + TimeUtil.toDateString(new Date(), TimeUtil.dateFormat.YYYY_MM_DD.value()) + fileName);
            try {
                files[i].transferTo(dest);
                //fileList.add(FoodConfig.IMAGE_BASE_URL+fileName);
            } catch (Exception e) {
                LOG.error(String.format("上传文件出错[%s]", e.getMessage()), e);
            }
        }

        return null;
        //return ResultUtils.success(imgs);
    }


    private List<CountVo> resultData(Map<String, Integer> map) {
        List<CountVo> voArrayList = new ArrayList<>();
        voArrayList.add(new CountVo("所有商品", "", map.get("goodsInfoCount"), "#2d8cf0"));
        voArrayList.add(new CountVo("有效商品", "", map.get("effective"), "#19be6b"));
        voArrayList.add(new CountVo("失效商品", "", map.get("invalid"), "#ff9900"));
        voArrayList.add(new CountVo("今日新增", "", map.get("newCount"), "#ed3f14"));
        voArrayList.add(new CountVo("问题商品", "", map.get("goodsInfoProblemCount"), "#E46CBB"));
        voArrayList.add(new CountVo("系统人数", "", map.get("userCount"), "#9A66E4"));
        return voArrayList;
    }
}