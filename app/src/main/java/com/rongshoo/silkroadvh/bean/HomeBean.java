package com.rongshoo.silkroadvh.bean;

import java.util.List;

/**
 * Created by liaoj on 2017/7/13.
 */

public class HomeBean {


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
        private List<ShoptypeBean> shoptype;
        private List<SpecialtypeBean> specialtype;

        public List<ShoptypeBean> getShoptype() {
            return shoptype;
        }

        public void setShoptype(List<ShoptypeBean> shoptype) {
            this.shoptype = shoptype;
        }

        public List<SpecialtypeBean> getSpecialtype() {
            return specialtype;
        }

        public void setSpecialtype(List<SpecialtypeBean> specialtype) {
            this.specialtype = specialtype;
        }

        public static class ShoptypeBean {

            private String ishoptype;
            private String title;
            private List<ProductBean> product;
            private List<AdBean> ad;

            public String getIshoptype() {
                return ishoptype;
            }

            public void setIshoptype(String ishoptype) {
                this.ishoptype = ishoptype;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<ProductBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBean> product) {
                this.product = product;
            }

            public List<AdBean> getAd() {
                return ad;
            }

            public void setAd(List<AdBean> ad) {
                this.ad = ad;
            }

            public static class ProductBean {
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
                private String iscorecnt;
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

                public String getIscorecnt() {
                    return iscorecnt;
                }

                public void setIscorecnt(String iscorecnt) {
                    this.iscorecnt = iscorecnt;
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

            public static class AdBean {


                private String iadtype;
                private String surl;
                private String sparameter;
                private String dtbegin;
                private String dtinsert;
                private String dtupdate;
                private String iadid;
                private String stitle;
                private String simgurl;
                private String saction;
                private String ipriority;
                private String dtend;
                private String istate;

                public String getIadtype() {
                    return iadtype;
                }

                public void setIadtype(String iadtype) {
                    this.iadtype = iadtype;
                }

                public String getSurl() {
                    return surl;
                }

                public void setSurl(String surl) {
                    this.surl = surl;
                }

                public String getSparameter() {
                    return sparameter;
                }

                public void setSparameter(String sparameter) {
                    this.sparameter = sparameter;
                }

                public String getDtbegin() {
                    return dtbegin;
                }

                public void setDtbegin(String dtbegin) {
                    this.dtbegin = dtbegin;
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

                public String getIadid() {
                    return iadid;
                }

                public void setIadid(String iadid) {
                    this.iadid = iadid;
                }

                public String getStitle() {
                    return stitle;
                }

                public void setStitle(String stitle) {
                    this.stitle = stitle;
                }

                public String getSimgurl() {
                    return simgurl;
                }

                public void setSimgurl(String simgurl) {
                    this.simgurl = simgurl;
                }

                public String getSaction() {
                    return saction;
                }

                public void setSaction(String saction) {
                    this.saction = saction;
                }

                public String getIpriority() {
                    return ipriority;
                }

                public void setIpriority(String ipriority) {
                    this.ipriority = ipriority;
                }

                public String getDtend() {
                    return dtend;
                }

                public void setDtend(String dtend) {
                    this.dtend = dtend;
                }

                public String getIstate() {
                    return istate;
                }

                public void setIstate(String istate) {
                    this.istate = istate;
                }
            }
        }

        public static class SpecialtypeBean {
            /**
             * ispecialtypeid : 8
             * simgurl : http://jszx.ylservice.com:8080/tomcat_test/upload/201707/20170711144444_869.jpg
             * sname : 旅游文创
             * icentreid : 1
             * ipriority : 14
             * istate : 1
             */

            private String ispecialtypeid;
            private String simgurl;
            private String sname;
            private String icentreid;
            private String ipriority;
            private String istate;

            public String getIspecialtypeid() {
                return ispecialtypeid;
            }

            public void setIspecialtypeid(String ispecialtypeid) {
                this.ispecialtypeid = ispecialtypeid;
            }

            public String getSimgurl() {
                return simgurl;
            }

            public void setSimgurl(String simgurl) {
                this.simgurl = simgurl;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getIcentreid() {
                return icentreid;
            }

            public void setIcentreid(String icentreid) {
                this.icentreid = icentreid;
            }

            public String getIpriority() {
                return ipriority;
            }

            public void setIpriority(String ipriority) {
                this.ipriority = ipriority;
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
