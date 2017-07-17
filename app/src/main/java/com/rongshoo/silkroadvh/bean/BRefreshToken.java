package com.rongshoo.silkroadvh.bean;

/**
 * Created by RS-KXH on 2017/5/12.
 */

public class BRefreshToken {


    /**
     * code : 000000
     * msg :
     * result : {"refreshexpires":1496892565,"tokencreated":1495596547,"tokenexpires":1495603765,"refreshtoken":"udO4Oiu9GpJpKEaQVlTtTepjBqm0MluE6De8pCpQ7tn1KwbaNy1IEHLUBrX9mTEj","token":"0MpfWG0XNTcgbfQMFU5xoq2gli5TTbEg7y3Cpd31slQJY9uHk9H4naazi2oiwl9n"}
     */

    private String code;
    private String msg;
    private ResultBean result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * refreshexpires : 1496892565
         * tokencreated : 1495596547
         * tokenexpires : 1495603765
         * refreshtoken : udO4Oiu9GpJpKEaQVlTtTepjBqm0MluE6De8pCpQ7tn1KwbaNy1IEHLUBrX9mTEj
         * token : 0MpfWG0XNTcgbfQMFU5xoq2gli5TTbEg7y3Cpd31slQJY9uHk9H4naazi2oiwl9n
         */

        private int refreshexpires;
        private int tokencreated;
        private int tokenexpires;
        private String refreshtoken;
        private String token;

        public int getRefreshexpires() {
            return refreshexpires;
        }

        public void setRefreshexpires(int refreshexpires) {
            this.refreshexpires = refreshexpires;
        }

        public int getTokencreated() {
            return tokencreated;
        }

        public void setTokencreated(int tokencreated) {
            this.tokencreated = tokencreated;
        }

        public int getTokenexpires() {
            return tokenexpires;
        }

        public void setTokenexpires(int tokenexpires) {
            this.tokenexpires = tokenexpires;
        }

        public String getRefreshtoken() {
            return refreshtoken;
        }

        public void setRefreshtoken(String refreshtoken) {
            this.refreshtoken = refreshtoken;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
