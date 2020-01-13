package cn.edu.hdu.SaltyFish.base;

//发布商品
public class OutputShop {
    String OPcontext;
    String OPtelephone;
    String OPmoney;
    String OPtitle;
    String OPNO;

    public String getOPNO() {
        return OPNO;
    }

    public OutputShop(){

    }

    public OutputShop(String OPcontext,String OPmoney,String OPtelephone,String OPtitle,String OPNO){
        this.OPcontext = OPcontext;
        this.OPmoney = OPmoney;
        this.OPtelephone = OPtelephone;
        this.OPtitle = OPtitle;
        this.OPNO = OPNO;
    }

    public String getOPtitle() {
        return OPtitle;
    }

    public void setOPtitle(String OPtitle) {
        this.OPtitle = OPtitle;
    }

    public String getOPcontext() {
        return OPcontext;
    }

    public void setOPcontext(String OPcontext) {
        this.OPcontext = OPcontext;
    }

    public String getOPtelephone() {
        return OPtelephone;
    }

    public void setOPtelephone(String OPtelephone) {
        this.OPtelephone = OPtelephone;
    }

    public String getOPmoney() {
        return OPmoney;
    }

    public void setOPmoney(String OPmoney) {
        this.OPmoney = OPmoney;
    }
}
