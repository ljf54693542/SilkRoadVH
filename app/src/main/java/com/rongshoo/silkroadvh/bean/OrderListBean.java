package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/5.
 */

public class OrderListBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 新疆水果店
         * yfText : EMS￥30
         * istate : 0
         * yf : [{"name":"快递￥12","isCheck":false},{"name":"EMS￥30","isCheck":true},{"name":"顺丰￥20","isCheck":false}]
         */

        private String name;
        private String yfText;
        private int istate;
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

        public int getIstate() {
            return istate;
        }

        public void setIstate(int istate) {
            this.istate = istate;
        }

        public List<YfBean> getYf() {
            return yf;
        }

        public void setYf(List<YfBean> yf) {
            this.yf = yf;
        }

        @Override
        public String toString() {
            return super.toString();
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
                return super.toString();
            }
        }
    }
}
