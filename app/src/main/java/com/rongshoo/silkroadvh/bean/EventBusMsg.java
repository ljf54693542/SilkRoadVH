package com.rongshoo.silkroadvh.bean;

/**
 * Created by RS-KXH on 2017/2/17.
 */

public class EventBusMsg {


//    public static class SendReplyMsg {
//        private CommentItem commentItem;
//
//        public SendReplyMsg(CommentItem commentItem) {
//            this.commentItem = commentItem;
//        }
//
//        public CommentItem getCommentItem() {
//            return commentItem;
//        }
//    }

    public static class AddrChoicMsg {
        private String pName;
        private String cName;
        private String sName;

        public AddrChoicMsg(String pName, String cName, String sName) {
            this.pName = pName;
            this.cName = cName;
            this.sName = sName;
        }

        public String getcName() {
            return cName;
        }

        public String getpName() {
            return pName;
        }

        public String getsName() {
            return sName;
        }
    }

    public static class UserAvatarMsg {
        private Boolean isUpdate=true;

        public Boolean getUpdate() {
            return isUpdate;
        }
    }

//    public static class AddrAddMsg {
//        private AddrListBean.DataBean item;
//
//        public AddrAddMsg(AddrListBean.DataBean item) {
//            this.item = item;
//        }
//
//        public AddrListBean.DataBean getItem() {
//            return item;
//        }
//    }

    public static class AddrDelMsg {
        private String shippingAddressId;

        public AddrDelMsg(String shippingAddressId) {
            this.shippingAddressId = shippingAddressId;
        }

        public String getShippingAddressId() {
            return shippingAddressId;
        }
    }

}
