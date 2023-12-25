package com.example.provider.home.storedetail.shoppingcart.bean;

import com.example.provider.home.storedetail.shoppingcart.viewmodel.ShoppingCartVMH;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车商品
 */
public class ShoppingCartProduct implements Serializable {

    private int id;
    private String title;
    private String describe;
    private int quantity;
    private int price;
    private int original_price;
    private String discount_str;
    private int sort_group_id;
    private String sort_group_name;
    private String sort_group_describe;
    private boolean is_specification;
    private boolean is_discount;
    private String widget;
    private String image;
    private SpecificationsBean specification;
    private int count = 0;

    public int getCount() {
        return count;
    }

    /**
     * 增加商品数量
     */
    public void addCount() {
        count++;
    }

    /**
     * 减少商品数量
     */
    public int reduceCount(int storeId) {
        count--;
        if (count <= 0) {
            count = 0;

            // 从购物车中删除 当前商品
            ShoppingCartVMH shoppingCart = ShoppingCartListH.getStoreShoppingCart(storeId,false);
            if (shoppingCart == null) {
                return count;
            }

            List<ShoppingCartProduct> cartList = shoppingCart.products;
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getId() == this.getId() &&
                        cartList.get(i).getSpecification().getId() == this.getSpecification().getId() &&
                        cartList.get(i).getSpecification().getAttribute().getId() == this.getSpecification().getAttribute().getId()) {
                    cartList.remove(cartList.get(i));
                    break;
                }
            }

        }
        return count;
    }

    public ShoppingCartProduct() {
    }

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public String getDiscount_str() {
        return discount_str;
    }

    public void setDiscount_str(String discount_str) {
        this.discount_str = discount_str;
    }

    public int getSort_group_id() {
        return sort_group_id;
    }

    public void setSort_group_id(int sort_group_id) {
        this.sort_group_id = sort_group_id;
    }

    public String getSort_group_name() {
        return sort_group_name;
    }

    public void setSort_group_name(String sort_group_name) {
        this.sort_group_name = sort_group_name;
    }

    public String getSort_group_describe() {
        return sort_group_describe;
    }

    public void setSort_group_describe(String sort_group_describe) {
        this.sort_group_describe = sort_group_describe;
    }

    public boolean isIs_specification() {
        return is_specification;
    }

    public void setIs_specification(boolean is_specification) {
        this.is_specification = is_specification;
    }

    public boolean isIs_discount() {
        return is_discount;
    }

    public void setIs_discount(boolean is_discount) {
        this.is_discount = is_discount;
    }

    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SpecificationsBean getSpecification() {
        return specification;
    }

    public void setSpecifications(SpecificationsBean specification) {
        this.specification = specification;
    }

    public static class SpecificationsBean implements Serializable{

        private int id;
        private String specification_title;
        private AttributesBean attribute;

        public SpecificationsBean() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSpecification_title() {
            return specification_title;
        }

        public void setSpecification_title(String specification_title) {
            this.specification_title = specification_title;
        }

        public AttributesBean getAttribute() {
            return attribute;
        }

        public void setAttribute(AttributesBean attribute) {
            this.attribute = attribute;
        }

        public static class AttributesBean implements Serializable{

            private int id;
            private String attribute_title;

            public AttributesBean() {
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAttribute_title() {
                return attribute_title;
            }

            public void setAttribute_title(String attribute_title) {
                this.attribute_title = attribute_title;
            }

        }
    }
}

