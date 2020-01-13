package cn.edu.hdu.SaltyFish.base;

import java.util.List;

public class HomeData {

    /**
     * 轮播图
     */
    public static class TypeBanner {
        //轮播连接
        private List<Integer> rsid;
        //轮播标题
        private List<String> titles;

        public List<Integer> getRsid() {
            return rsid;
        }

        public void setRsid(List<Integer> rsid) {
            this.rsid = rsid;
        }

        public List<String> getTitles() {
            return titles;
        }

        public void setTitles(List<String> titles) {
            this.titles = titles;
        }
    }


    /**
     * 四格
     */
    public static class TypeFocus {

        private String content;

        private Integer picFirst;
        private Integer picSecond;
        private Integer picThird;
        private Integer picForth;

        private String titleFirst;
        private String titleSecond;
        private String titleThird;
        private String titleFourth;
        private String titleFifth;
        private String titleSixth;
        private String titleSeventh;
        private String titleEighth;


        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getPicFirst() {
            return picFirst;
        }

        public void setPicFirst(Integer picFirst) {
            this.picFirst = picFirst;
        }

        public Integer getPicSecond() {
            return picSecond;
        }

        public void setPicSecond(Integer picSecond) {
            this.picSecond = picSecond;
        }

        public Integer getPicThird() {
            return picThird;
        }

        public void setPicThird(Integer picThird) {
            this.picThird = picThird;
        }

        public Integer getPicForth() {
            return picForth;
        }

        public void setPicForth(Integer picForth) {
            this.picForth = picForth;
        }

        public String getTitleFirst() {
            return titleFirst;
        }

        public void setTitleFirst(String titleFirst) {
            this.titleFirst = titleFirst;
        }

        public String getTitleSecond() {
            return titleSecond;
        }

        public void setTitleSecond(String titleSecond) {
            this.titleSecond = titleSecond;
        }

        public String getTitleThird() {
            return titleThird;
        }

        public void setTitleThird(String titleThird) {
            this.titleThird = titleThird;
        }

        public String getTitleFourth() {
            return titleFourth;
        }

        public void setTitleFourth(String titleFourth) {
            this.titleFourth = titleFourth;
        }

        public String getTitleFifth() {
            return titleFifth;
        }

        public void setTitleFifth(String titleFifth) {
            this.titleFifth = titleFifth;
        }

        public String getTitleSixth() {
            return titleSixth;
        }

        public void setTitleSixth(String titleSixth) {
            this.titleSixth = titleSixth;
        }

        public String getTitleSeventh() {
            return titleSeventh;
        }

        public void setTitleSeventh(String titleSeventh) {
            this.titleSeventh = titleSeventh;
        }

        public String getTitleEighth() {
            return titleEighth;
        }

        public void setTitleEighth(String titleEighth) {
            this.titleEighth = titleEighth;
        }
    }


    /**
     * 精选
     */
    public static class TypeBottom {

        private Integer url;
        private String title;
        private String content;
        private double price;

        public Integer getUrl() {
            return url;
        }

        public void setUrl(Integer url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public TypeBottom(Integer url, String title, String content, double price) {
            this.url = url;
            this.title = title;
            this.content = content;
            this.price = price;
        }
    }
}
