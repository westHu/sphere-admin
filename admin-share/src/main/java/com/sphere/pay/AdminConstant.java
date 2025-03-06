package com.sphere.pay;

import java.time.format.DateTimeFormatter;

public class AdminConstant {

    /**
     * param
     */
    public final static String PARAM_LOGIN_USERNAME = "username";
    public final static String PARAM_UNKNOWN = "unKnown";
    public static final String SANDBOX = "sandbox-";
    /**
     * redis key prefix
     */
    public final static String KEY_PREFIX_LOCK_OPERATOR = "lock_operator_";

    /**
     * sql
     */
    public final static String LIMIT_SQL = "limit 1";
    public final static String VERSION_SQL = "version = version + 1";

    /**
     * admin
     */
    public final static String PASSWORD_DEFAULT = "Abc123456";

    /**
     * login
     */
    public final static String LOGIN_SYS_TOKEN = "LOGIN_SYS_TOKEN";
    public final static String LOGIN_SYS_SUBJECT = "SYS_SUBJECT";
    public final static String LOGIN_SYS_CLAIM = "SYS_CLAIM";
    public final static String LOGIN_CLAIM_SYS_USER = "CLAIM_SYS_USER";
    public final static String LOGIN_CLAIM_SYS_ROLE = "CLAIM_SYS_ROLE";
    public final static Integer TOKEN_EXPIRED = 3 * 60 * 60;


    public static final String LOGIN_EXPIRES_AT = "exp";
    public static final String LOGIN_CLAIM_MERCHANT = "LOGIN_CLAIM_MERCHANT";
    public static final String LOGIN_CLAIM_OPERATOR = "LOGIN_CLAIM_OPERATOR";
    public static final String LOGIN_CLAIM_LOGIN_FROM = "LOGIN_CLAIM_LOGIN_FROM";
    public static final String LOGIN_SUBJECT_MERCHANT = "LOGIN_SUBJECT_MERCHANT";

    public final static Integer LOCK_TERM = 3 * 60; //锁住账户多久自动解锁
    public final static Integer LOCK_TIMES = 5; //登录失败几次后锁住账户


    /**
     * `
     * mq
     */
    //from merchant
    public static final String MERCHANT_EXAMINE_TOPIC = "MERCHANT_EXAMINE_TOPIC";
    public static final String MERCHANT_EXAMINE_GROUP = "MERCHANT_EXAMINE_ADMIN_GROUP";
    //from trade
    public static final String TRADE_EXAMINE_TOPIC = "TRADE_EXAMINE_TOPIC";
    public static final String TRADE_EXAMINE_GROUP = "TRADE_EXAMINE_ADMIN_GROUP";
    //from merchant
    public static final String ACCOUNT_EXAMINE_TOPIC = "ACCOUNT_EXAMINE_TOPIC";
    public static final String ACCOUNT_EXAMINE_GROUP = "ACCOUNT_EXAMINE_ADMIN_GROUP";
    public static final String TG_SEND_MESSAGE_TOPIC = "UTILS_TG_SEND_MESSAGE_TOPIC";

    /**
     * date format
     */
    public static final String FORMAT0 = "yyyy-MM-dd HH:mm:ss";
    public final static DateTimeFormatter DF_0 = DateTimeFormatter.ofPattern(FORMAT0);

    /**
     * 存储用户登录信息
     */
    public static final String LOCAL_MERCHANT = "LOCAL_MERCHANT";
    public static final String LOCAL_MERCHANT_OPERATOR = "LOCAL_MERCHANT_OPERATOR";
    public static final String LOCAL_BASE_USER = "LOCAL_BASE_USER";
    public static final String LOCAL_BASE_ROLE = "LOCAL_BASE_ROLE";

    /**
     * init
     */
    public static final String ROLE_INIT = "administrator";
    public static final String USER_INIT = "admin";

    /**
     * cache
     */
    public static final String KEY_PREFIX_CACHE = "admin:cache";



    /**
     * URL
     */
    public static final String URL_PAYMENT = "lb://sphere-payment";
}
