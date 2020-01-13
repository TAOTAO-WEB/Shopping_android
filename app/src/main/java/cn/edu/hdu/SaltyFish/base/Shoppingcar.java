package cn.edu.hdu.SaltyFish.base;

public class Shoppingcar {
    Integer imageid;
    String name;
    String money;
    String scno;

    public String getScno() {
        return scno;
    }

    public Shoppingcar(){

    }

    public Shoppingcar(Integer imageid,String name,String money,String scno){
        this.imageid = imageid;
        this.name = name;
        this.money = money;
        this.scno = scno;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
