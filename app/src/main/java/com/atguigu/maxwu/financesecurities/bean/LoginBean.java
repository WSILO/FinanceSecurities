package com.atguigu.maxwu.financesecurities.bean;

/**
 * 作者: WuKai
 * 时间: 2017/6/26
 * 邮箱: 648838173@qq.com
 * 作用:
 */
public class LoginBean {
    /**
     * data : {"name":"xiaolongge","imageurl":"http://182.92.5.3:8081/P2PInvest/images/tx.png","iscredit":"true","phone":"15321970103"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : xiaolongge
         * imageurl : http://182.92.5.3:8081/P2PInvest/images/tx.png
         * iscredit : true
         * phone : 15321970103
         */

        private String name;
        private String imageurl;
        private String iscredit;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getIscredit() {
            return iscredit;
        }

        public void setIscredit(String iscredit) {
            this.iscredit = iscredit;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
