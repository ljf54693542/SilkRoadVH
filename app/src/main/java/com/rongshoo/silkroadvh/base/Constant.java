package com.rongshoo.silkroadvh.base;

/**
 * Created by RS-KXH on 2017/4/18.
 */

public class Constant {
    public static final String TEST_USER="c2b21d4234874c579a804849ea0cd149";
    public static final String BIG_ROOT_HTTP="http://passport.oborttc.com/";
    public static final String LOGIN_RETURN_URL="https://www.baidu.com/";
    public static final String BIG_ROOT="https://passport.oborttc.com/";
    public static final String BIG_lOGIN=BIG_ROOT+"login?returnurl=";
    public static final String BIG_REGISTER=BIG_ROOT+"register?returnurl=";
    public static final String BIG_FIND_PASSWORD=BIG_ROOT+"findpassword?returnurl=";
    public static final String BIG_LOGOUT=BIG_ROOT+"logout?returnurl=";
    public static final String BIG_GET_USERINFOR=BIG_ROOT+"getuserinfo?token=";
    public static final String BIG_REFRESH_TOKEN=BIG_ROOT+"refreshtoken?refreshtoken=";
    public static final String BIG_UPDATEAVATAR=BIG_ROOT+"updateavatar";

    public static final String BASE_URL="http://jszx.ylservice.com:6161";

    //商品分類
    public static final String GOODS_KIND=BASE_URL+"/api/producttype/findsub";
    //品牌
    public static final String GOODS_BRAND=BASE_URL+"/api/producttype/findbrand";
    //商品列表
    public static final String GOODS_LIST=BASE_URL+"/api/product/listbyapp";
    //商品详情
    public static final String GOODS_DETAIL=BASE_URL+"/api/product/detailed";
    //商品评价列表
    public static final String GOODS_COMMENT=BASE_URL+"/api/judge/list";
    //店铺列表
    public static final String SHOP_LIST=BASE_URL+"/api/shop/listbyapp";
    //首页
    public static final String HOME=BASE_URL+"/api/application/ioshomepage";

    //酒店房间
    public static final String HOTEL_ROOM=BASE_URL+"/api/hotel/list";


}
