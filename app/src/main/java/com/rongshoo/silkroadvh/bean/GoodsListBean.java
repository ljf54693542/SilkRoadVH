package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/10.
 */

public class GoodsListBean {

    /**
     * Desc : 成功
     * data : {"irow":3,"recordcount":3,"pagesize":10,"pageindex":1,"rows":[{"sshopname":"百货公司","ictype":"21","spic":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155229_800.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_679.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_360.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_492.png","ibrandid":"71","dscore":"0.0","sdes":"水电费水电费水电费水电费是地方收到","sfathername":" ","dteffect":"2017-07-08","ssellername":"大大商品测试","dtupdate":"2017-07-08 16:56:04","ishoptype":"1","sproductname":"无花果","icentreid":"1","sconfig":"","ifathertype":"16","drealprice":"15.00","dprice":"15.00","sbackroll":"","ishopid":"57","sbrandname":"阿斯顿发生2","sproducturl":"http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170708155415105.html","iproductid":"132","isales":"0","dtinsert":"2017-07-08 15:54:15","ifsub":"1","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155229_800.png","isellerid":"75","scname":"","istock":"600","dtend":"2017-07-08","ssubsign":"[{\"key\":\"容量\",\"value\":[\"8-10个\",\"6-8个\"]}]","istate":"1"},{"sshopname":"百货公司","ictype":"21","spic":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151430_554.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_294.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_627.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_971.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_940.png","ibrandid":"71","dscore":"0.0","sdes":"都是盖饭是的发送到发送到","sfathername":" ","dteffect":"2017-07-08","ssellername":"大大商品测试","dtupdate":"2017-07-08 16:55:49","ishoptype":"1","sproductname":"葡萄","icentreid":"1","sconfig":"","ifathertype":"16","drealprice":"30.00","dprice":"10.00","sbackroll":"","ishopid":"57","sbrandname":"阿斯顿发生2","sproducturl":"http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170708151749101.html","iproductid":"128","isales":"0","dtinsert":"2017-07-08 15:17:49","ifsub":"1","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151430_554.png","isellerid":"75","scname":"","istock":"800","dtend":"2017-07-08","ssubsign":"[{\"key\":\"容量\",\"value\":[\"500K/袋\",\"300K/袋\"]}]","istate":"1"},{"sshopname":"约诺他食品专营店 ","ictype":"21","spic":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180734_864.jpg,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180737_742.jpg,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180738_524.jpg,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180740_194.jpg","ibrandid":"71","dscore":"0.0","sdes":"","sfathername":" ","dteffect":"2017-07-05","ssellername":"约诺","dtupdate":"2017-07-07 18:17:53","ishoptype":"1","sproductname":"生日礼物进口零食大礼包组合整箱送女友女生爱吃的混装藤编礼盒","icentreid":"1","sconfig":"","ifathertype":"16","drealprice":"0.00","dprice":"0.00","sbackroll":"","ishopid":"22","sbrandname":"71","sproducturl":"http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170705181458115.html","iproductid":"56","isales":"0","dtinsert":"2017-07-05 18:14:59","ifsub":"1","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180734_864.jpg","isellerid":"49","scname":"","istock":"0","dtend":"2017-07-05","ssubsign":"[{\"key\":\"套餐\",\"value\":[\"B套餐\",\"升级版巧克力\",\"c套餐\",\"A套餐\"]}]","istate":"1"}]}
     * Code : 1
     */

    private String Desc;
    private DataBean data;
    private String Code;

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public static class DataBean {
        /**
         * irow : 3
         * recordcount : 3
         * pagesize : 10
         * pageindex : 1
         * rows : [{"sshopname":"百货公司","ictype":"21","spic":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155229_800.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_679.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_360.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_492.png","ibrandid":"71","dscore":"0.0","sdes":"水电费水电费水电费水电费是地方收到","sfathername":" ","dteffect":"2017-07-08","ssellername":"大大商品测试","dtupdate":"2017-07-08 16:56:04","ishoptype":"1","sproductname":"无花果","icentreid":"1","sconfig":"","ifathertype":"16","drealprice":"15.00","dprice":"15.00","sbackroll":"","ishopid":"57","sbrandname":"阿斯顿发生2","sproducturl":"http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170708155415105.html","iproductid":"132","isales":"0","dtinsert":"2017-07-08 15:54:15","ifsub":"1","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155229_800.png","isellerid":"75","scname":"","istock":"600","dtend":"2017-07-08","ssubsign":"[{\"key\":\"容量\",\"value\":[\"8-10个\",\"6-8个\"]}]","istate":"1"},{"sshopname":"百货公司","ictype":"21","spic":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151430_554.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_294.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_627.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_971.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151431_940.png","ibrandid":"71","dscore":"0.0","sdes":"都是盖饭是的发送到发送到","sfathername":" ","dteffect":"2017-07-08","ssellername":"大大商品测试","dtupdate":"2017-07-08 16:55:49","ishoptype":"1","sproductname":"葡萄","icentreid":"1","sconfig":"","ifathertype":"16","drealprice":"30.00","dprice":"10.00","sbackroll":"","ishopid":"57","sbrandname":"阿斯顿发生2","sproducturl":"http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170708151749101.html","iproductid":"128","isales":"0","dtinsert":"2017-07-08 15:17:49","ifsub":"1","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708151430_554.png","isellerid":"75","scname":"","istock":"800","dtend":"2017-07-08","ssubsign":"[{\"key\":\"容量\",\"value\":[\"500K/袋\",\"300K/袋\"]}]","istate":"1"},{"sshopname":"约诺他食品专营店 ","ictype":"21","spic":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180734_864.jpg,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180737_742.jpg,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180738_524.jpg,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180740_194.jpg","ibrandid":"71","dscore":"0.0","sdes":"","sfathername":" ","dteffect":"2017-07-05","ssellername":"约诺","dtupdate":"2017-07-07 18:17:53","ishoptype":"1","sproductname":"生日礼物进口零食大礼包组合整箱送女友女生爱吃的混装藤编礼盒","icentreid":"1","sconfig":"","ifathertype":"16","drealprice":"0.00","dprice":"0.00","sbackroll":"","ishopid":"22","sbrandname":"71","sproducturl":"http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170705181458115.html","iproductid":"56","isales":"0","dtinsert":"2017-07-05 18:14:59","ifsub":"1","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170705180734_864.jpg","isellerid":"49","scname":"","istock":"0","dtend":"2017-07-05","ssubsign":"[{\"key\":\"套餐\",\"value\":[\"B套餐\",\"升级版巧克力\",\"c套餐\",\"A套餐\"]}]","istate":"1"}]
         */

        private int irow;
        private int recordcount;
        private int pagesize;
        private int pageindex;
        private List<RowsBean> rows;

        public int getIrow() {
            return irow;
        }

        public void setIrow(int irow) {
            this.irow = irow;
        }

        public int getRecordcount() {
            return recordcount;
        }

        public void setRecordcount(int recordcount) {
            this.recordcount = recordcount;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPageindex() {
            return pageindex;
        }

        public void setPageindex(int pageindex) {
            this.pageindex = pageindex;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * sshopname : 百货公司
             * ictype : 21
             * spic : http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155229_800.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_679.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_360.png,http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155230_492.png
             * ibrandid : 71
             * dscore : 0.0
             * sdes : 水电费水电费水电费水电费是地方收到
             * sfathername :
             * dteffect : 2017-07-08
             * ssellername : 大大商品测试
             * dtupdate : 2017-07-08 16:56:04
             * ishoptype : 1
             * sproductname : 无花果
             * icentreid : 1
             * sconfig :
             * ifathertype : 16
             * drealprice : 15.00
             * dprice : 15.00
             * sbackroll :
             * ishopid : 57
             * sbrandname : 阿斯顿发生2
             * sproducturl : http://jszx.ylservice.com:8080/ueditor1_4_3-utf8-jsp/showhtml/20170708155415105.html
             * iproductid : 132
             * isales : 0
             * dtinsert : 2017-07-08 15:54:15
             * ifsub : 1
             * simgurl : http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170708155229_800.png
             * isellerid : 75
             * scname :
             * istock : 600
             * dtend : 2017-07-08
             * ssubsign : [{"key":"容量","value":["8-10个","6-8个"]}]
             * istate : 1
             */

            private String sshopname;
            private String ictype;
            private String spic;
            private String ibrandid;
            private String dscore;
            private String sdes;
            private String sfathername;
            private String dteffect;
            private String ssellername;
            private String dtupdate;
            private String ishoptype;
            private String sproductname;
            private String icentreid;
            private String sconfig;
            private String ifathertype;
            private String drealprice;
            private String dprice;
            private String sbackroll;
            private String ishopid;
            private String sbrandname;
            private String sproducturl;
            private String iproductid;
            private String isales;
            private String dtinsert;
            private String ifsub;
            private String simgurl;
            private String isellerid;
            private String scname;
            private String istock;
            private String dtend;
            private String ssubsign;
            private String istate;

            public String getSshopname() {
                return sshopname;
            }

            public void setSshopname(String sshopname) {
                this.sshopname = sshopname;
            }

            public String getIctype() {
                return ictype;
            }

            public void setIctype(String ictype) {
                this.ictype = ictype;
            }

            public String getSpic() {
                return spic;
            }

            public void setSpic(String spic) {
                this.spic = spic;
            }

            public String getIbrandid() {
                return ibrandid;
            }

            public void setIbrandid(String ibrandid) {
                this.ibrandid = ibrandid;
            }

            public String getDscore() {
                return dscore;
            }

            public void setDscore(String dscore) {
                this.dscore = dscore;
            }

            public String getSdes() {
                return sdes;
            }

            public void setSdes(String sdes) {
                this.sdes = sdes;
            }

            public String getSfathername() {
                return sfathername;
            }

            public void setSfathername(String sfathername) {
                this.sfathername = sfathername;
            }

            public String getDteffect() {
                return dteffect;
            }

            public void setDteffect(String dteffect) {
                this.dteffect = dteffect;
            }

            public String getSsellername() {
                return ssellername;
            }

            public void setSsellername(String ssellername) {
                this.ssellername = ssellername;
            }

            public String getDtupdate() {
                return dtupdate;
            }

            public void setDtupdate(String dtupdate) {
                this.dtupdate = dtupdate;
            }

            public String getIshoptype() {
                return ishoptype;
            }

            public void setIshoptype(String ishoptype) {
                this.ishoptype = ishoptype;
            }

            public String getSproductname() {
                return sproductname;
            }

            public void setSproductname(String sproductname) {
                this.sproductname = sproductname;
            }

            public String getIcentreid() {
                return icentreid;
            }

            public void setIcentreid(String icentreid) {
                this.icentreid = icentreid;
            }

            public String getSconfig() {
                return sconfig;
            }

            public void setSconfig(String sconfig) {
                this.sconfig = sconfig;
            }

            public String getIfathertype() {
                return ifathertype;
            }

            public void setIfathertype(String ifathertype) {
                this.ifathertype = ifathertype;
            }

            public String getDrealprice() {
                return drealprice;
            }

            public void setDrealprice(String drealprice) {
                this.drealprice = drealprice;
            }

            public String getDprice() {
                return dprice;
            }

            public void setDprice(String dprice) {
                this.dprice = dprice;
            }

            public String getSbackroll() {
                return sbackroll;
            }

            public void setSbackroll(String sbackroll) {
                this.sbackroll = sbackroll;
            }

            public String getIshopid() {
                return ishopid;
            }

            public void setIshopid(String ishopid) {
                this.ishopid = ishopid;
            }

            public String getSbrandname() {
                return sbrandname;
            }

            public void setSbrandname(String sbrandname) {
                this.sbrandname = sbrandname;
            }

            public String getSproducturl() {
                return sproducturl;
            }

            public void setSproducturl(String sproducturl) {
                this.sproducturl = sproducturl;
            }

            public String getIproductid() {
                return iproductid;
            }

            public void setIproductid(String iproductid) {
                this.iproductid = iproductid;
            }

            public String getIsales() {
                return isales;
            }

            public void setIsales(String isales) {
                this.isales = isales;
            }

            public String getDtinsert() {
                return dtinsert;
            }

            public void setDtinsert(String dtinsert) {
                this.dtinsert = dtinsert;
            }

            public String getIfsub() {
                return ifsub;
            }

            public void setIfsub(String ifsub) {
                this.ifsub = ifsub;
            }

            public String getSimgurl() {
                return simgurl;
            }

            public void setSimgurl(String simgurl) {
                this.simgurl = simgurl;
            }

            public String getIsellerid() {
                return isellerid;
            }

            public void setIsellerid(String isellerid) {
                this.isellerid = isellerid;
            }

            public String getScname() {
                return scname;
            }

            public void setScname(String scname) {
                this.scname = scname;
            }

            public String getIstock() {
                return istock;
            }

            public void setIstock(String istock) {
                this.istock = istock;
            }

            public String getDtend() {
                return dtend;
            }

            public void setDtend(String dtend) {
                this.dtend = dtend;
            }

            public String getSsubsign() {
                return ssubsign;
            }

            public void setSsubsign(String ssubsign) {
                this.ssubsign = ssubsign;
            }

            public String getIstate() {
                return istate;
            }

            public void setIstate(String istate) {
                this.istate = istate;
            }
        }
    }
}
