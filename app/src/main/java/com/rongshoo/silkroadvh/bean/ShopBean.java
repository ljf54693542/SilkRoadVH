package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/12.
 */

public class ShopBean {


    /**
     * Desc : 成功
     * data : {"irow":1,"recordcount":1,"pagesize":10,"pageindex":1,"rows":[{"sshopname":"芒果-北平楼","ishopid":"4","sphone":"18683781642","scustomerserviceno":"0","dscore":"0.0","sdes":"北平楼位于马家花园23号，是一家具有浓郁老北京风味的自助火锅店，店内装修古朴大方，宽敞明亮，店内背景音乐放的是国粹京剧，无限量供应各种老北京风味的美食，采用北方传统的铜锅，大型的DIY调料自助台，味道随心所欲任你想用。店内的北京烤鸭和涮羊肉可谓一绝，成都人民热爱的涮羊肉，驱寒味美营养健康，可谓一举几得呢~","daverage":"0.00","isales":"0","dtinsert":"2017-07-13 16:48:18","dtupdate":"2017-07-13 16:48:30","ishoptype":"3","sremarks":"","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170713164738_401.jpg","icentreid":"1","isellerid":"5","sshopurl":"","srange":"14","sshopno":"18683781642","simgtopurl":"","saddress":"水果中心城最美大道105号101","istate":"1"}]}
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
         * irow : 1
         * recordcount : 1
         * pagesize : 10
         * pageindex : 1
         * rows : [{"sshopname":"芒果-北平楼","ishopid":"4","sphone":"18683781642","scustomerserviceno":"0","dscore":"0.0","sdes":"北平楼位于马家花园23号，是一家具有浓郁老北京风味的自助火锅店，店内装修古朴大方，宽敞明亮，店内背景音乐放的是国粹京剧，无限量供应各种老北京风味的美食，采用北方传统的铜锅，大型的DIY调料自助台，味道随心所欲任你想用。店内的北京烤鸭和涮羊肉可谓一绝，成都人民热爱的涮羊肉，驱寒味美营养健康，可谓一举几得呢~","daverage":"0.00","isales":"0","dtinsert":"2017-07-13 16:48:18","dtupdate":"2017-07-13 16:48:30","ishoptype":"3","sremarks":"","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170713164738_401.jpg","icentreid":"1","isellerid":"5","sshopurl":"","srange":"14","sshopno":"18683781642","simgtopurl":"","saddress":"水果中心城最美大道105号101","istate":"1"}]
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
             * sshopname : 芒果-北平楼
             * ishopid : 4
             * sphone : 18683781642
             * scustomerserviceno : 0
             * dscore : 0.0
             * sdes : 北平楼位于马家花园23号，是一家具有浓郁老北京风味的自助火锅店，店内装修古朴大方，宽敞明亮，店内背景音乐放的是国粹京剧，无限量供应各种老北京风味的美食，采用北方传统的铜锅，大型的DIY调料自助台，味道随心所欲任你想用。店内的北京烤鸭和涮羊肉可谓一绝，成都人民热爱的涮羊肉，驱寒味美营养健康，可谓一举几得呢~
             * daverage : 0.00
             * isales : 0
             * dtinsert : 2017-07-13 16:48:18
             * dtupdate : 2017-07-13 16:48:30
             * ishoptype : 3
             * sremarks :
             * simgurl : http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170713164738_401.jpg
             * icentreid : 1
             * isellerid : 5
             * sshopurl :
             * srange : 14
             * sshopno : 18683781642
             * simgtopurl :
             * saddress : 水果中心城最美大道105号101
             * istate : 1
             */

            private String sshopname;
            private String ishopid;
            private String sphone;
            private String scustomerserviceno;
            private String dscore;
            private String sdes;
            private String daverage;
            private String isales;
            private String dtinsert;
            private String dtupdate;
            private String ishoptype;
            private String sremarks;
            private String simgurl;
            private String icentreid;
            private String isellerid;
            private String sshopurl;
            private String srange;
            private String sshopno;
            private String simgtopurl;
            private String saddress;
            private String istate;

            public String getSshopname() {
                return sshopname;
            }

            public void setSshopname(String sshopname) {
                this.sshopname = sshopname;
            }

            public String getIshopid() {
                return ishopid;
            }

            public void setIshopid(String ishopid) {
                this.ishopid = ishopid;
            }

            public String getSphone() {
                return sphone;
            }

            public void setSphone(String sphone) {
                this.sphone = sphone;
            }

            public String getScustomerserviceno() {
                return scustomerserviceno;
            }

            public void setScustomerserviceno(String scustomerserviceno) {
                this.scustomerserviceno = scustomerserviceno;
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

            public String getDaverage() {
                return daverage;
            }

            public void setDaverage(String daverage) {
                this.daverage = daverage;
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

            public String getSremarks() {
                return sremarks;
            }

            public void setSremarks(String sremarks) {
                this.sremarks = sremarks;
            }

            public String getSimgurl() {
                return simgurl;
            }

            public void setSimgurl(String simgurl) {
                this.simgurl = simgurl;
            }

            public String getIcentreid() {
                return icentreid;
            }

            public void setIcentreid(String icentreid) {
                this.icentreid = icentreid;
            }

            public String getIsellerid() {
                return isellerid;
            }

            public void setIsellerid(String isellerid) {
                this.isellerid = isellerid;
            }

            public String getSshopurl() {
                return sshopurl;
            }

            public void setSshopurl(String sshopurl) {
                this.sshopurl = sshopurl;
            }

            public String getSrange() {
                return srange;
            }

            public void setSrange(String srange) {
                this.srange = srange;
            }

            public String getSshopno() {
                return sshopno;
            }

            public void setSshopno(String sshopno) {
                this.sshopno = sshopno;
            }

            public String getSimgtopurl() {
                return simgtopurl;
            }

            public void setSimgtopurl(String simgtopurl) {
                this.simgtopurl = simgtopurl;
            }

            public String getSaddress() {
                return saddress;
            }

            public void setSaddress(String saddress) {
                this.saddress = saddress;
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
