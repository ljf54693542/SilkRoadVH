package com.rongshoo.silkroadvh.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RS-KXH on 2017/3/7.
 */

public class CityBean implements Serializable{


    private List<CitylistBean> citylist;

    public static CityBean objectFromData(String str) {

        return new Gson().fromJson(str, CityBean.class);
    }

    public static CityBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CityBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CityBean> arrayCityBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CityBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CityBean> arrayCityBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CityBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public List<CitylistBean> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<CitylistBean> citylist) {
        this.citylist = citylist;
    }

    public static class CitylistBean {
        /**
         * p : 四川
         * c : [{"n":"成都","a":[{"s":"锦江区"},{"s":"青羊区"},{"s":"金牛区"},{"s":"武侯区"},{"s":"成华区"},{"s":"龙泉驿区"},{"s":"青白江区"},{"s":"新都区"},{"s":"温江区"},{"s":"金堂县"},{"s":"双流县"},{"s":"郫县"},{"s":"大邑县"},{"s":"蒲江县"},{"s":"新津县"},{"s":"都江堰市"},{"s":"彭州市"},{"s":"邛崃市"},{"s":"崇州市"}]},{"n":"自贡","a":[{"s":"自流井区"},{"s":"贡井区"},{"s":"大安区"},{"s":"沿滩区"},{"s":"荣县"},{"s":"富顺县"}]},{"n":"攀枝花","a":[{"s":"东区"},{"s":"西区"},{"s":"仁和区"},{"s":"米易县"},{"s":"盐边县"}]},{"n":"泸州","a":[{"s":"江阳区"},{"s":"纳溪区"},{"s":"龙马潭区"},{"s":"泸县"},{"s":"合江县"},{"s":"叙永县"},{"s":"古蔺县"}]},{"n":"德阳","a":[{"s":"旌阳区"},{"s":"中江县"},{"s":"罗江县"},{"s":"广汉市"},{"s":"什邡市"},{"s":"绵竹市"}]},{"n":"绵阳","a":[{"s":"涪城区"},{"s":"游仙区"},{"s":"三台县"},{"s":"盐亭县"},{"s":"安县"},{"s":"梓潼县"},{"s":"北川羌族自治县"},{"s":"平武县"},{"s":"江油市"}]},{"n":"广元","a":[{"s":"利州区"},{"s":"元坝区"},{"s":"朝天区"},{"s":"旺苍县"},{"s":"青川县"},{"s":"剑阁县"},{"s":"苍溪县"}]},{"n":"遂宁","a":[{"s":"船山区"},{"s":">安居区"},{"s":">蓬溪县"},{"s":">射洪县"},{"s":">大英县"}]},{"n":"内江","a":[{"s":"市中区"},{"s":"东兴区"},{"s":"威远县"},{"s":"资中县"},{"s":"隆昌县"}]},{"n":"乐山","a":[{"s":"市中区"},{"s":"沙湾区"},{"s":"五通桥区"},{"s":"金口河区"},{"s":"犍为县"},{"s":"井研县"},{"s":"夹江县"},{"s":"沐川县"},{"s":"峨边彝族自治县"},{"s":"马边彝族自治县"},{"s":"峨眉山市"}]},{"n":"南充","a":[{"s":"顺庆区"},{"s":"高坪区"},{"s":"嘉陵区"},{"s":"南部县"},{"s":"营山县"},{"s":"蓬安县"},{"s":"仪陇县"},{"s":"西充县"},{"s":"阆中市"}]},{"n":"眉山","a":[{"s":"东坡区"},{"s":"仁寿县"},{"s":"彭山县"},{"s":"洪雅县"},{"s":"丹棱县"},{"s":"青神县"}]},{"n":"宜宾","a":[{"s":"翠屏区"},{"s":"宜宾县"},{"s":"南溪县"},{"s":"江安县"},{"s":"长宁县"},{"s":"高县"},{"s":"珙县"},{"s":"筠连县"},{"s":"兴文县"},{"s":"屏山县"}]},{"n":"广安","a":[{"s":"广安区"},{"s":"岳池县"},{"s":"武胜县"},{"s":"邻水县"},{"s":"华蓥市"}]},{"n":"达川","a":[{"s":"通川区"},{"s":"达县"},{"s":"宣汉县"},{"s":"开江县"},{"s":"大竹县"},{"s":"渠县"},{"s":"万源市"}]},{"n":"雅安","a":[{"s":"雨城区"},{"s":"名山县"},{"s":"荥经县"},{"s":"汉源县"},{"s":"石棉县"},{"s":"天全县"},{"s":"芦山县"},{"s":"宝兴县"}]},{"n":"巴中","a":[{"s":"巴州区"},{"s":"通江县"},{"s":"南江县"},{"s":"平昌县"}]},{"n":"资阳","a":[{"s":"雁江区"},{"s":"安岳县"},{"s":"乐至县"},{"s":"简阳市"}]},{"n":"阿坝","a":[{"s":"汶川县"},{"s":"理县"},{"s":"茂县"},{"s":"松潘县"},{"s":"九寨沟县"},{"s":"金川县"},{"s":"小金县"},{"s":"黑水县"},{"s":"马尔康县"},{"s":"壤塘县"},{"s":"阿坝县"},{"s":"若尔盖县"},{"s":"红原县"}]},{"n":"甘孜","a":[{"s":"康定县"},{"s":"泸定县"},{"s":"丹巴县"},{"s":"九龙县"},{"s":"雅江县"},{"s":"道孚县"},{"s":"炉霍县"},{"s":"甘孜县"},{"s":"新龙县"},{"s":"德格县"},{"s":"白玉县"},{"s":"石渠县"},{"s":"色达县"},{"s":"理塘县"},{"s":"巴塘县"},{"s":"乡城县"},{"s":"稻城县"},{"s":"得荣县"}]},{"n":"凉山","a":[{"s":"西昌市"},{"s":"木里藏族自治县"},{"s":"盐源县"},{"s":"德昌县"},{"s":"会理县"},{"s":"会东县"},{"s":"宁南县"},{"s":"普格县"},{"s":"布拖县"},{"s":"金阳县"},{"s":"昭觉县"},{"s":"喜德县"},{"s":"冕宁县"},{"s":"越西县"},{"s":"甘洛县"},{"s":"美姑县"},{"s":"雷波县"}]}]
         */

        private String p;//province 省份
        private List<CBean> c;//city  市级内容

        public static CitylistBean objectFromData(String str) {

            return new Gson().fromJson(str, CitylistBean.class);
        }

        public static CitylistBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), CitylistBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<CitylistBean> arrayCitylistBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<CitylistBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<CitylistBean> arrayCitylistBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<CitylistBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public List<CBean> getC() {
            return c;
        }

        public void setC(List<CBean> c) {
            this.c = c;
        }

        public static class CBean implements Serializable{
            /**
             * n : 成都
             * a : [{"s":"锦江区"},{"s":"青羊区"},{"s":"金牛区"},{"s":"武侯区"},{"s":"成华区"},{"s":"龙泉驿区"},{"s":"青白江区"},{"s":"新都区"},{"s":"温江区"},{"s":"金堂县"},{"s":"双流县"},{"s":"郫县"},{"s":"大邑县"},{"s":"蒲江县"},{"s":"新津县"},{"s":"都江堰市"},{"s":"彭州市"},{"s":"邛崃市"},{"s":"崇州市"}]
             */

            private String n;//市名
            private List<ABean> a;// 县/区list

            public static CBean objectFromData(String str) {

                return new Gson().fromJson(str, CBean.class);
            }

            public static CBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), CBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<CBean> arrayCBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<CBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<CBean> arrayCBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<CBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public List<ABean> getA() {
                return a;
            }

            public void setA(List<ABean> a) {
                this.a = a;
            }

            public static class ABean implements Serializable{
                /**
                 * s : 锦江区
                 */

                private String s;//sanjak 县/区名

                public static ABean objectFromData(String str) {

                    return new Gson().fromJson(str, ABean.class);
                }

                public static ABean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ABean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ABean> arrayABeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ABean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ABean> arrayABeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ABean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public String getS() {
                    return s;
                }

                public void setS(String s) {
                    this.s = s;
                }
            }
        }
    }
}
