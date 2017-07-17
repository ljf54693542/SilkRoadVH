package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/10.
 */

public class BrandBean {

    /**
     * Desc : 成功
     * data : {"record":[{"sbrandname":"sdfsd","ibrandid":"57"},{"sbrandname":"阿斯蒂芬","ibrandid":"70"},{"sbrandname":"阿斯顿发生2","ibrandid":"71"}]}
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

        public static class RecordBean extends CheckBean {
            /**
             * sbrandname : sdfsd
             * ibrandid : 57*/


            private String sbrandname;
            private String ibrandid;

            public String getSbrandname() {
                return sbrandname;
            }

            public void setSbrandname(String sbrandname) {
                this.sbrandname = sbrandname;
            }

            public String getIbrandid() {
                return ibrandid;
            }

            public void setIbrandid(String ibrandid) {
                this.ibrandid = ibrandid;
            }
        }
    }
}
