package com.example.storedetaildemo.bean;

import java.util.List;

public class DetailSelectBean {
    /**
     * total : 8
     * product_select : [{"id":0,"title":"热销","is_hot":false,"is_must_order":false},{"id":1,"title":"必点","is_hot":false,"is_must_order":false},{"id":2,"title":"进店必买","is_hot":false,"is_must_order":false},{"id":3,"title":"肉馅馄炖","is_hot":false,"is_must_order":false},{"id":4,"title":"素馅馄炖","is_hot":false,"is_must_order":false},{"id":5,"title":"浓情馄炖","is_hot":false,"is_must_order":false},{"id":6,"title":"小菜","is_hot":false,"is_must_order":false},{"id":7,"title":"饮品","is_hot":false,"is_must_order":false}]
     */

    private int total;
    private List<ProductSelectBean> product_select;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductSelectBean> getProduct_select() {
        return product_select;
    }

    public void setProduct_select(List<ProductSelectBean> product_select) {
        this.product_select = product_select;
    }

    public static class ProductSelectBean {
        /**
         * id : 0
         * title : 热销
         * is_hot : false
         * is_must_order : false
         */

        private int id;
        private String title;
        private boolean is_hot;
        private boolean is_must_order;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isIs_hot() {
            return is_hot;
        }

        public void setIs_hot(boolean is_hot) {
            this.is_hot = is_hot;
        }

        public boolean isIs_must_order() {
            return is_must_order;
        }

        public void setIs_must_order(boolean is_must_order) {
            this.is_must_order = is_must_order;
        }
    }
}
