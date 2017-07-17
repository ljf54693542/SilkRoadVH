package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/4.
 */

public class YunFeiBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public static class DataBean {
        /**
         * name : 新疆水果店
         * yfText : EMS￥30
         * yf : [{"name":"快递￥12","isCheck":false},{"name":"EMS￥30","isCheck":true},{"name":"顺丰￥20","isCheck":false}]
         */

        private String name;
        private String yfText;
        private List<YfBean> yf;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getYfText() {
            return yfText;
        }

        public void setYfText(String yfText) {
            this.yfText = yfText;
        }

        public List<YfBean> getYf() {
            return yf;
        }

        public void setYf(List<YfBean> yf) {
            this.yf = yf;
        }

        @Override
        public String toString() {
            return  "DataBean: name=" + name + " yf=" + yf.toString()+" yfText="+yfText;
        }

        public static class YfBean {
            /**
             * name : 快递￥12
             * isCheck : false
             */

            private String name;
            private boolean isCheck;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isIsCheck() {
                return isCheck;
            }

            public void setIsCheck(boolean isCheck) {
                this.isCheck = isCheck;
            }

            @Override
            public String toString() {
                return "YFBean:name=" + name + " isCheck=" + isCheck + "\n";
            }
        }
    }
//



}
