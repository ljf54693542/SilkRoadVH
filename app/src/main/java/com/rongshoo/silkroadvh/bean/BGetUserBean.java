package com.rongshoo.silkroadvh.bean;

/**
 * Created by RS-KXH on 2017/5/12.
 */

public class BGetUserBean {

    /**
     * code : 000000
     * msg :
     * result : {"refreshexpires":1496908289,"tokencreated":1495612282,"addtime":"2017-04-24 17:12:48","mobile":"13628040450","nickname":"136****0450","tokenexpires":1495619489,"refreshtoken":"2d332MjrMJ1dlone1RtSr0Rnyn4n9XrUoexnCN50S8N5Er87jLfSMzHkYwSDFImV","id":13,"avatar":"http%3A%2F%2Fimg.oborttc.com%2Favatar%2F2017%2F05%2F22%2F2017052211121903721.jpg","wxopenid":"","email":"","token":"fBMSNsEojusuGzKtSb4kNN7d51HLqC6eCQb7vRl4hrv09Yy0MPEMRE8C8oe7UJqZ"}
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
         * refreshexpires : 1496908289
         * tokencreated : 1495612282
         * addtime : 2017-04-24 17:12:48
         * mobile : 13628040450
         * nickname : 136****0450
         * tokenexpires : 1495619489
         * refreshtoken : 2d332MjrMJ1dlone1RtSr0Rnyn4n9XrUoexnCN50S8N5Er87jLfSMzHkYwSDFImV
         * id : 13
         * avatar : http%3A%2F%2Fimg.oborttc.com%2Favatar%2F2017%2F05%2F22%2F2017052211121903721.jpg
         * wxopenid :
         * email :
         * token : fBMSNsEojusuGzKtSb4kNN7d51HLqC6eCQb7vRl4hrv09Yy0MPEMRE8C8oe7UJqZ
         */

        private int refreshexpires;
        private int tokencreated;
        private String addtime;
        private String mobile;
        private String nickname;
        private int tokenexpires;
        private String refreshtoken;
        private int id;
        private String avatar;
        private String wxopenid;
        private String email;
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

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getWxopenid() {
            return wxopenid;
        }

        public void setWxopenid(String wxopenid) {
            this.wxopenid = wxopenid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
