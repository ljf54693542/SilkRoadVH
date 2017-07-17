package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/8.
 */

public class GoodsKindBean {

    /**
     * Desc : 成功
     * data : {"record":[{"ictype":"5","ishoptype":"1","dyj":"0.0020","ilevel":"2","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201706/20170630172057_502.jpg","scname":"服饰"},{"ictype":"16","ishoptype":"1","dyj":"0.0010","ilevel":"2","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201706/20170630172301_432.jpg","scname":"百货"},{"ictype":"17","ishoptype":"1","dyj":"0.0010","ilevel":"2","simgurl":"http://jszx.ylservice.com:8080/tomcat_test/upload/201706/20170630174200_118.jpg","scname":"儿童玩具"},{"ictype":"18","ishoptype":"1","dyj":"0.2000","ilevel":"2","simgurl":"","scname":"水果"},{"ictype":"22","ishoptype":"1","dyj":"0.0200","ilevel":"2","simgurl":"","scname":"饰品"}]}
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
        private List<RecordBean> record;

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class RecordBean extends CheckBean{
            /**
             * ictype : 5
             * ishoptype : 1
             * dyj : 0.0020
             * ilevel : 2
             * simgurl : http://jszx.ylservice.com:8080/tomcat_test/upload/201706/20170630172057_502.jpg
             * scname : 服饰
             */

            private String ictype;
            private String ishoptype;
            private String dyj;
            private String ilevel;
            private String simgurl;
            private String scname;

            public String getIctype() {
                return ictype;
            }

            public void setIctype(String ictype) {
                this.ictype = ictype;
            }

            public String getIshoptype() {
                return ishoptype;
            }

            public void setIshoptype(String ishoptype) {
                this.ishoptype = ishoptype;
            }

            public String getDyj() {
                return dyj;
            }

            public void setDyj(String dyj) {
                this.dyj = dyj;
            }

            public String getIlevel() {
                return ilevel;
            }

            public void setIlevel(String ilevel) {
                this.ilevel = ilevel;
            }

            public String getSimgurl() {
                return simgurl;
            }

            public void setSimgurl(String simgurl) {
                this.simgurl = simgurl;
            }

            public String getScname() {
                return scname;
            }

            public void setScname(String scname) {
                this.scname = scname;
            }
        }
    }
}
