package com.atguigu.maxwu.financesecurities.common;

/**
 * 作者: WuKai
 * 时间: 2017/6/21
 * 邮箱: 648838173@qq.com
 * 作用:
 */

public class NetConfig {

    public static final String HOST = "47.93.118.241";//提供ip地址

    //提供web应用的地址  http://47.93.118.241:8081/P2PInvest/index
    public static final String BASE_URL = "http://" + HOST + ":8081/P2PInvest/";

    public static final String INDEX = BASE_URL + "index";//访问首页数据

    public static final String LOGIN = BASE_URL + "login";//访问登录的url

    public static final String PRODUCT = BASE_URL + "product";//访问“所有理财”的url

    public static final String UPDATE = BASE_URL + "update.json";//访问服务器端当前应用的版本信息

    public static final String REGISTER = BASE_URL + "UserRegister";//注册

    public static final String FEEDBACK = BASE_URL + "FeedBack";//用户反馈
}
