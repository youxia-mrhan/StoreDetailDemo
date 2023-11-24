package com.example.storedetaildemo.bean;

import java.util.List;

public class MerchantBean {

    /**
     * id : 0
     * store_title : 1973上海老街馄炖铺
     * store_images : [{"id":0,"image":"merchant_image01"},{"id":1,"image":"merchant_image02"},{"id":2,"image":"merchant_image03"},{"id":3,"image":"merchant_image04"},{"id":4,"image":"merchant_image02"}]
     * introduction :
     * business_time : 00:00 - 24:00
     */

    private int id;
    private String store_title;
    private String introduction;
    private String business_time;
    private List<StoreImagesBean> store_images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStore_title() {
        return store_title;
    }

    public void setStore_title(String store_title) {
        this.store_title = store_title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getBusiness_time() {
        return business_time;
    }

    public void setBusiness_time(String business_time) {
        this.business_time = business_time;
    }

    public List<StoreImagesBean> getStore_images() {
        return store_images;
    }

    public void setStore_images(List<StoreImagesBean> store_images) {
        this.store_images = store_images;
    }

    public static class StoreImagesBean {
        /**
         * id : 0
         * image : merchant_image01
         */

        private int id;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
