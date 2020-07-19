package com.jhxaa.yhj.utli;


//@Component
//@PropertySource("classpath:config.properties")
public class PddAccesTokenUtil {


    private final static String clientId = PropertiesUtil.getString("pdd.clientId");
    private final static String clientSecret = PropertiesUtil.getString("pdd.clientSecret");
    private final static String clientSecret1 = PropertiesUtil.getString("pdd.clientSecret");


    public static String getAccesToken() throws Exception {


        return "";
    }

}
