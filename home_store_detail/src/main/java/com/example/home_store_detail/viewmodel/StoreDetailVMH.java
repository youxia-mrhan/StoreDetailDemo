package com.example.home_store_detail.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺详情 数据实体
 */
public class StoreDetailVMH extends ViewModel implements Serializable {

    /**
     * store_info : {"id":0,"title":"1973上海老街馄炖铺","store_image":"store_70_img","store_bg_image":"store_70_img","score":4.8,"sales_quantity":666,"distance":"500m","average_price":2400,"address":"浙江省杭州市下城区武林五163号132室","phone_number":12345567,"coupon":[{"id":0,"title":"满100减20"}],"announcement_str":"本店所有蛋糕均为下单后先做，支持提前1-7天提前预定，如商品出现质量问题无条件接受退换货，营业时间为早7:00-晚11:00，蛋糕最好是提前下单预定，错开高峰期，如遇恶劣天气送餐时间较慢，还望理解。","coupon_infos":[{"type_id":0,"type_title":"减","title":"满30减18;满40减20;满66减26;满88减28;满118减30"},{"type_id":1,"type_title":"神","title":"神卷满35减10,含本店补贴2"},{"type_id":2,"type_title":"新","title":"本店新用户立减2元"},{"type_id":3,"type_title":"卷","title":"多买多得,集点可获得商家优惠代金券"},{"type_id":4,"type_title":"折","title":"折扣商品1.5折起"},{"type_id":5,"type_title":"领","title":"领2元卷;领3元卷"},{"type_id":6,"type_title":"返","title":"实际支付1元返满36减2商家代金券"}],"limit_discounts_title":"47年店庆商品享9折","limit_discounts_time":1703901600,"evaluate_total_count":1572}
     * order_food_info : {"store_id": 0,"carousels":[{"id":0,"image_url":"carousel_image01"},{"id":1,"image_url":"carousel_image02"},{"id":2,"image_url":"carousel_image03"},{"id":3,"image_url":"carousel_image01"},{"id":4,"image_url":"carousel_image02"}],"product_type":[{"id":0,"title":"热销","is_hot":true,"is_must_order":false},{"id":1,"title":"必点","is_hot":false,"is_must_order":true},{"id":2,"title":"进店必买","is_hot":false,"is_must_order":false},{"id":3,"title":"肉馅馄炖","is_hot":false,"is_must_order":false},{"id":4,"title":"素馅馄炖","is_hot":false,"is_must_order":false},{"id":5,"title":"浓情馄炖","is_hot":false,"is_must_order":false},{"id":6,"title":"小菜","is_hot":false,"is_must_order":false},{"id":7,"title":"饮品","is_hot":false,"is_must_order":false},{"id":8,"title":"江湖川菜","is_hot":false,"is_must_order":false},{"id":9,"title":"吃肉荤菜","is_hot":false,"is_must_order":false},{"id":10,"title":"田园时蔬","is_hot":false,"is_must_order":false},{"id":11,"title":"特色干锅","is_hot":false,"is_must_order":false},{"id":12,"title":"优选套餐","is_hot":false,"is_must_order":false},{"id":13,"title":"美味小吃","is_hot":false,"is_must_order":false},{"id":14,"title":"营养汤羹","is_hot":false,"is_must_order":false},{"id":15,"title":"主食米饭","is_hot":false,"is_must_order":false},{"id":16,"title":"饭后水果","is_hot":false,"is_must_order":false},{"id":17,"title":"开元小抄","is_hot":false,"is_must_order":false},{"id":18,"title":"招牌套餐","is_hot":false,"is_must_order":false},{"id":19,"title":"聚餐家宴","is_hot":false,"is_must_order":false},{"id":20,"title":"开元热销","is_hot":false,"is_must_order":false},{"id":21,"title":"一人大菜","is_hot":false,"is_must_order":false},{"id":22,"title":"冰凉甜品","is_hot":false,"is_must_order":false},{"id":23,"title":"铁板炒饭","is_hot":false,"is_must_order":false},{"id":24,"title":"大碗汤面","is_hot":false,"is_must_order":false}],"products":[{"id":0,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":0,"type_id_name":"热销","type_id_describe":"广大食客之选","is_specification":true,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":1,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":0,"type_name":"热销","type_describe":"广大食客之选","is_specification":false,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":2,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":0,"type_name":"热销","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":3,"title":"经典昆仑酥蜜瓜","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":1,"type_name":"必点","type_describe":"广大食客之选","is_specification":false,"is_discount":true,"widget":"半只/1500g","image":"product_100_04","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":4,"title":"南非进口红西柚 一级果","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":1,"type_name":"必点","type_describe":"广大食客之选","is_specification":true,"is_discount":false,"widget":"半只/1500g","image":"product_100_05","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":5,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":1,"type_name":"必点","type_describe":"广大食客之选","is_specification":false,"is_discount":true,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":6,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":2,"type_name":"进店必买","type_describe":"广大食客之选","is_specification":true,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":7,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":2,"type_name":"进店必买","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":8,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":2,"type_name":"进店必买","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":9,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":3,"type_name":"肉馅馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":10,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":3,"type_name":"肉馅馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_05","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":11,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":3,"type_name":"肉馅馄炖","type_describe":"广大食客之选","is_specification":true,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":12,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":4,"type_name":"素馅馄炖","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_04","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":13,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":4,"type_name":"素馅馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":14,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":4,"type_name":"素馅馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":15,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":5,"type_name":"浓情馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":16,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":5,"type_name":"浓情馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":17,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":5,"type_name":"浓情馄炖","type_describe":"广大食客之选","is_specification":false,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":18,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":6,"type_name":"小菜","type_describe":"广大食客之选","is_specification":true,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":19,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":6,"type_name":"小菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":20,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":6,"type_name":"小菜","type_describe":"广大食客之选","is_specification":false,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":21,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":7,"type_name":"饮品","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":22,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":7,"type_name":"饮品","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":23,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":7,"type_name":"饮品","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":24,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":8,"type_name":"江湖川菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":25,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":8,"type_name":"江湖川菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":26,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":8,"type_name":"江湖川菜","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":27,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":9,"type_name":"吃肉荤菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":28,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":9,"type_name":"吃肉荤菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":29,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":9,"type_name":"吃肉荤菜","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":30,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":10,"type_name":"田园时蔬","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":31,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":10,"type_name":"田园时蔬","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":32,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":10,"type_name":"田园时蔬","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":33,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":11,"type_name":"特色干锅","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":34,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":11,"type_name":"特色干锅","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":35,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":11,"type_name":"特色干锅","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":36,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":12,"type_name":"优选套餐","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":37,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":12,"type_name":"优选套餐","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":38,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":12,"type_name":"优选套餐","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":39,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":13,"type_name":"美味小吃","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":40,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":13,"type_name":"美味小吃","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":41,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":13,"type_name":"美味小吃","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":42,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":14,"type_name":"营养汤羹","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":43,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":14,"type_name":"营养汤羹","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":44,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":14,"type_name":"营养汤羹","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":45,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":15,"type_name":"主食米饭","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":46,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":15,"type_name":"主食米饭","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":47,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":15,"type_name":"主食米饭","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":48,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":16,"type_name":"饭后水果","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":49,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":16,"type_name":"饭后水果","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":50,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":16,"type_name":"饭后水果","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":51,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":17,"type_name":"开元小抄","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":52,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":17,"type_name":"开元小抄","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":53,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":17,"type_name":"开元小抄","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":54,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":18,"type_name":"招牌套餐","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":55,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":18,"type_name":"招牌套餐","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":56,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":18,"type_name":"招牌套餐","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":57,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":19,"type_name":"聚餐家宴","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":58,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":19,"type_name":"聚餐家宴","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":59,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":19,"type_name":"聚餐家宴","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":60,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":20,"type_name":"开元热销","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":61,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":20,"type_name":"开元热销","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":62,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":20,"type_name":"开元热销","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":63,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":21,"type_name":"一人大菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":64,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":21,"type_name":"一人大菜","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":65,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":21,"type_name":"一人大菜","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":66,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":22,"type_name":"冰凉甜品","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":67,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":22,"type_name":"冰凉甜品","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":68,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":22,"type_name":"冰凉甜品","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":69,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":23,"type_name":"铁板炒饭","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":70,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":23,"type_name":"铁板炒饭","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":71,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":23,"type_name":"铁板炒饭","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":72,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":24,"type_name":"大碗汤面","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_01","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":73,"title":"老上海一口鲜小馄饨","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":24,"type_name":"大碗汤面","type_describe":"广大食客之选","is_specification":false,"is_discount":false,"widget":"半只/1500g","image":"product_100_03","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]},{"id":74,"title":"鲜虾鸡肉紫菜鸡蛋鲜汤大份","describe":"每日现包，鲜肉小馄炖","quantity":109,"price":1250,"original_price":2200,"discount_str":"已优惠8.5折","type_id":24,"type_name":"大碗汤面","type_describe":"广大食客之选","is_specification":true,"is_discount":true,"widget":"半只/1500g","image":"product_100_02","specifications":[{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]}]}
     * evaluate_info : {"evaluate_total_count":1572,"store_score":4.8,"smell_score":5,"package_score":5,"delivery":"100%","evaluates":[{"group_id":0,"group_name":"全部","totalCount":0,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]},{"group_id":1,"group_name":"最新","totalCount":0,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]},{"group_id":2,"group_name":"好评","totalCount":1552,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]},{"group_id":3,"group_name":"差评","totalCount":20,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]}]}
     * merchant_info : {"id":0,"store_title":"1973上海老街馄炖铺","store_images":[{"id":0,"image":"merchant_image01"},{"id":1,"image":"merchant_image02"},{"id":2,"image":"merchant_image03"},{"id":3,"image":"merchant_image04"},{"id":4,"image":"merchant_image02"}],"introduction":"暂无简介","business_time":"00:00 - 24:00"}
     */

    private StoreInfoBean store_info;
    private OrderFoodInfoBean order_food_info;
    private EvaluateInfoBean evaluate_info;
    private MerchantInfoBean merchant_info;

    public StoreDetailVMH() {
    }

    public StoreDetailVMH(StoreDetailVMH storeDetailVMH) {
        store_info = storeDetailVMH.getStore_info();
        order_food_info = storeDetailVMH.getOrder_food_info();
        evaluate_info = storeDetailVMH.getEvaluate_info();
        merchant_info = storeDetailVMH.getMerchant_info();
    }

    public StoreInfoBean getStore_info() {
        return store_info;
    }

    public void setStore_info(StoreInfoBean store_info) {
        this.store_info = store_info;
    }

    public OrderFoodInfoBean getOrder_food_info() {
        return order_food_info;
    }

    public void setOrder_food_info(OrderFoodInfoBean order_food_info) {
        this.order_food_info = order_food_info;
    }

    public EvaluateInfoBean getEvaluate_info() {
        return evaluate_info;
    }

    public void setEvaluate_info(EvaluateInfoBean evaluate_info) {
        this.evaluate_info = evaluate_info;
    }

    public MerchantInfoBean getMerchant_info() {
        return merchant_info;
    }

    public void setMerchant_info(MerchantInfoBean merchant_info) {
        this.merchant_info = merchant_info;
    }

    public static class StoreDetailVMFactory extends ViewModelProvider.NewInstanceFactory {

        private StoreDetailVMH storeDetailVMH;

        public StoreDetailVMFactory(StoreDetailVMH storeDetailVMH) {
            this.storeDetailVMH = storeDetailVMH;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new StoreDetailVMH(storeDetailVMH);
        }
    }

    public static class StoreInfoBean implements Serializable {
        /**
         * id : 0
         * title : 1973上海老街馄炖铺
         * store_image : store_70_img
         * store_bg_image : store_70_img
         * score : 4.8
         * sales_quantity : 666
         * distance : 500m
         * average_price : 2400
         * address : 浙江省杭州市下城区武林五163号132室
         * phone_number : 12345567
         * coupon : [{"id":0,"title":"满100减20"}]
         * announcement_str : 本店所有蛋糕均为下单后先做，支持提前1-7天提前预定，如商品出现质量问题无条件接受退换货，营业时间为早7:00-晚11:00，蛋糕最好是提前下单预定，错开高峰期，如遇恶劣天气送餐时间较慢，还望理解。
         * coupon_infos : [{"type_id":0,"type_title":"减","title":"满30减18;满40减20;满66减26;满88减28;满118减30"},{"type_id":1,"type_title":"神","title":"神卷满35减10,含本店补贴2"},{"type_id":2,"type_title":"新","title":"本店新用户立减2元"},{"type_id":3,"type_title":"卷","title":"多买多得,集点可获得商家优惠代金券"},{"type_id":4,"type_title":"折","title":"折扣商品1.5折起"},{"type_id":5,"type_title":"领","title":"领2元卷;领3元卷"},{"type_id":6,"type_title":"返","title":"实际支付1元返满36减2商家代金券"}]
         * limit_discounts_title : 47年店庆商品享9折
         * limit_discounts_time : 1703901600
         * evaluate_total_count : 1572
         */

        private int id;
        private String title;
        private String store_image;
        private String store_bg_image;
        private float score;
        private int sales_quantity;
        private String distance;
        private int average_price;
        private String address;
        private int phone_number;
        private String announcement_str;
        private String limit_discounts_title;
        private int limit_discounts_time;
        private int evaluate_total_count;
        private List<CouponBean> coupon;
        private List<CouponInfosBean> coupon_infos;

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

        public String getStore_image() {
            return store_image;
        }

        public void setStore_image(String store_image) {
            this.store_image = store_image;
        }

        public String getStore_bg_image() {
            return store_bg_image;
        }

        public void setStore_bg_image(String store_bg_image) {
            this.store_bg_image = store_bg_image;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }

        public int getSales_quantity() {
            return sales_quantity;
        }

        public void setSales_quantity(int sales_quantity) {
            this.sales_quantity = sales_quantity;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getAverage_price() {
            return average_price;
        }

        public void setAverage_price(int average_price) {
            this.average_price = average_price;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(int phone_number) {
            this.phone_number = phone_number;
        }

        public String getAnnouncement_str() {
            return announcement_str;
        }

        public void setAnnouncement_str(String announcement_str) {
            this.announcement_str = announcement_str;
        }

        public String getLimit_discounts_title() {
            return limit_discounts_title;
        }

        public void setLimit_discounts_title(String limit_discounts_title) {
            this.limit_discounts_title = limit_discounts_title;
        }

        public int getLimit_discounts_time() {
            return limit_discounts_time;
        }

        public void setLimit_discounts_time(int limit_discounts_time) {
            this.limit_discounts_time = limit_discounts_time;
        }

        public int getEvaluate_total_count() {
            return evaluate_total_count;
        }

        public void setEvaluate_total_count(int evaluate_total_count) {
            this.evaluate_total_count = evaluate_total_count;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public List<CouponInfosBean> getCoupon_infos() {
            return coupon_infos;
        }

        public void setCoupon_infos(List<CouponInfosBean> coupon_infos) {
            this.coupon_infos = coupon_infos;
        }

        public static class CouponBean implements Serializable {
            /**
             * id : 0
             * title : 满100减20
             */

            private int id;
            private String title;

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

        }

        public static class CouponInfosBean implements Serializable {
            /**
             * type_id : 0
             * type_title : 减
             * title : 满30减18;满40减20;满66减26;满88减28;满118减30
             */

            private int type_id;
            private String type_title;
            private String title;

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
            }

            public String getType_title() {
                return type_title;
            }

            public void setType_title(String type_title) {
                this.type_title = type_title;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }
    }

    public static class OrderFoodInfoBean implements Serializable {
        private int store_id;
        private List<CarouselsBean> carousels;
        private List<ProductTypeBean> product_type;
        private List<ProductsBean> products;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public List<CarouselsBean> getCarousels() {
            return carousels;
        }

        public void setCarousels(List<CarouselsBean> carousels) {
            this.carousels = carousels;
        }

        public List<ProductTypeBean> getProduct_type() {
            return product_type;
        }

        public void setProduct_type(List<ProductTypeBean> product_type) {
            this.product_type = product_type;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class CarouselsBean implements Serializable {
            /**
             * id : 0
             * image_url : carousel_image01
             */

            private int id;
            private String image_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

        }

        public static class ProductTypeBean implements Serializable {
            /**
             * id : 0
             * title : 热销
             * image_id : 0
             */

            private int id;
            private String title;
            private int image_id;

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

            public int getImage_id() {
                return image_id;
            }

            public void setImage_id(int image_id) {
                this.image_id = image_id;
            }

        }

        public static class ProductsBean implements Serializable {
            /**
             * id : 0
             * title : 老上海一口鲜小馄饨
             * describe : 每日现包，鲜肉小馄炖
             * quantity : 109
             * price : 1250
             * original_price : 2200
             * discount_str : 已优惠8.5折
             * type_id : 0
             * type_name : 热销
             * type_describe : 广大食客之选
             * is_specification : true
             * is_discount : false
             * widget : 半只/1500g
             * image : product_100_01
             * specifications : [{"id":0,"specification_title":"小份","attributes":[{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]},{"id":1,"specification_title":"中份","attributes":[{"id":0,"attribute_title":"属性内容B"},{"id":1,"attribute_title":"属性内容BB"},{"id":2,"attribute_title":"属性内容BBB"}]},{"id":2,"specification_title":"大份","attributes":[{"id":0,"attribute_title":"属性内容C"},{"id":1,"attribute_title":"属性内容CC"},{"id":2,"attribute_title":"属性内容CCC"}]},{"id":3,"specification_title":"超大份","attributes":[{"id":0,"attribute_title":"属性内容D"},{"id":1,"attribute_title":"属性内容DD"},{"id":2,"attribute_title":"属性内容DDD"}]}]
             * type_name : 热销
             * type_describe : 广大食客之选
             */

            private int id;
            private String title;
            private String describe;
            private int quantity;
            private int price;
            private int original_price;
            private String discount_str;
            private int type_id;
            private String type_name;
            private String type_describe;
            private boolean is_specification;
            private boolean is_discount;
            private String widget;
            private String image;
            private List<SpecificationsBean> specifications;

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

            public int getType_id() {
                return type_id;
            }

            public void setType_id(int type_id) {
                this.type_id = type_id;
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

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getType_describe() {
                return type_describe;
            }

            public void setType_describe(String type_describe) {
                this.type_describe = type_describe;
            }

            public List<SpecificationsBean> getSpecifications() {
                return specifications;
            }

            public void setSpecifications(List<SpecificationsBean> specifications) {
                this.specifications = specifications;
            }

            public static class SpecificationsBean implements Serializable {
                /**
                 * id : 0
                 * specification_title : 小份
                 * attributes : [{"id":0,"attribute_title":"属性内容A"},{"id":1,"attribute_title":"属性内容AA"},{"id":2,"attribute_title":"属性内容AAA"}]
                 */

                private int id;
                private String specification_title;
                private List<AttributesBean> attributes;

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

                public List<AttributesBean> getAttributes() {
                    return attributes;
                }

                public void setAttributes(List<AttributesBean> attributes) {
                    this.attributes = attributes;
                }

                public static class AttributesBean implements Serializable {
                    /**
                     * id : 0
                     * attribute_title : 属性内容A
                     */

                    private int id;
                    private String attribute_title;

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
    }

    public static class EvaluateInfoBean implements Serializable {
        /**
         * evaluate_total_count : 1572
         * store_score : 4.8
         * smell_score : 5.0
         * package_score : 5.0
         * delivery : 100%
         * evaluates : [{"group_id":0,"group_name":"全部","totalCount":0,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]},{"group_id":1,"group_name":"最新","totalCount":0,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]},{"group_id":2,"group_name":"好评","totalCount":1552,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]},{"group_id":3,"group_name":"差评","totalCount":20,"evaluate_items":[{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]}]
         */

        private int evaluate_total_count;
        private float store_score;
        private float smell_score;
        private float package_score;
        private String delivery;
        private List<EvaluatesBean> evaluates;

        public int getEvaluate_total_count() {
            return evaluate_total_count;
        }

        public void setEvaluate_total_count(int evaluate_total_count) {
            this.evaluate_total_count = evaluate_total_count;
        }

        public float getStore_score() {
            return store_score;
        }

        public void setStore_score(float store_score) {
            this.store_score = store_score;
        }

        public float getSmell_score() {
            return smell_score;
        }

        public void setSmell_score(float smell_score) {
            this.smell_score = smell_score;
        }

        public float getPackage_score() {
            return package_score;
        }

        public void setPackage_score(float package_score) {
            this.package_score = package_score;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public List<EvaluatesBean> getEvaluates() {
            return evaluates;
        }

        public void setEvaluates(List<EvaluatesBean> evaluates) {
            this.evaluates = evaluates;
        }

        public static class EvaluatesBean implements Serializable {
            /**
             * group_id : 0
             * group_name : 全部
             * totalCount : 0
             * evaluate_items : [{"id":0,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":1,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":2,"group_id":0,"url":"comment_image01","name":"David Newman","level":2,"level_title":"寿","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":3,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":4,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":5,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":6,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":7,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":8,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":9,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":10,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":11,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":12,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":13,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":14,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":15,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":16,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":17,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":18,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":3,"level_title":"禧","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":19,"group_id":0,"url":"comment_image01","name":"David Newman","level":0,"level_title":"福","time_str":"2020-05-12 14:28","score":5,"content":[{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]},{"id":20,"group_id":0,"url":"comment_image02","name":"Rosalie Abbott","level":1,"level_title":"禄","time_str":"2020-05-12 14:28","score":3.2,"content":[{"index":0,"type":0,"message":"在项目伊始时做一个用户研究的作用好过于在项目落地后再对50个用户进行测试","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]}]
             */

            private int group_id;
            private String group_name;
            private int totalCount;
            private List<EvaluateItemsBean> evaluate_items;

            public int getGroup_id() {
                return group_id;
            }

            public void setGroup_id(int group_id) {
                this.group_id = group_id;
            }

            public String getGroup_name() {
                return group_name;
            }

            public void setGroup_name(String group_name) {
                this.group_name = group_name;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public List<EvaluateItemsBean> getEvaluate_items() {
                return evaluate_items;
            }

            public void setEvaluate_items(List<EvaluateItemsBean> evaluate_items) {
                this.evaluate_items = evaluate_items;
            }

            public static class EvaluateItemsBean implements Serializable {
                /**
                 * id : 0
                 * group_id : 0
                 * url : comment_image01
                 * name : David Newman
                 * level : 0
                 * level_title : 福
                 * time_str : 2020-05-12 14:28
                 * score : 5.0
                 * content : [{"index":0,"type":0,"message":"根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如\u201c上\u201d连着\u201c下\u201d，\u201c水平\u201d连着\u201c垂直\u201d，等等","images":["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]},{"index":1,"type":1,"message":"设计工作上最重要的是针对人们习以为常，认为理所当然的事物来做一些思考","images":[]}]
                 */

                private int id;
                private int group_id;
                private String url;
                private String name;
                private int level;
                private String level_title;
                private String time_str;
                private float score;
                private List<ContentBean> content;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(int group_id) {
                    this.group_id = group_id;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getLevel_title() {
                    return level_title;
                }

                public void setLevel_title(String level_title) {
                    this.level_title = level_title;
                }

                public String getTime_str() {
                    return time_str;
                }

                public void setTime_str(String time_str) {
                    this.time_str = time_str;
                }

                public float getScore() {
                    return score;
                }

                public void setScore(float score) {
                    this.score = score;
                }

                public List<ContentBean> getContent() {
                    return content;
                }

                public void setContent(List<ContentBean> content) {
                    this.content = content;
                }

                public static class ContentBean implements Serializable {
                    /**
                     * index : 0
                     * type : 0
                     * message : 根据对比来思考并不会让人逻辑混乱，因为即使是对比也可以联结称一个和谐的整体。有些概念只有通过它的对立面才能成为现实，比如“上”连着“下”，“水平”连着“垂直”，等等
                     * images : ["evaluate_image_01","evaluate_image_02","evaluate_image_03","evaluate_image_04","evaluate_image_05"]
                     */

                    private int index;
                    private int type;
                    private String message;
                    private List<String> images;

                    public int getIndex() {
                        return index;
                    }

                    public void setIndex(int index) {
                        this.index = index;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getMessage() {
                        return message;
                    }

                    public void setMessage(String message) {
                        this.message = message;
                    }

                    public List<String> getImages() {
                        return images;
                    }

                    public void setImages(List<String> images) {
                        this.images = images;
                    }

                }
            }
        }
    }

    public static class MerchantInfoBean implements Serializable {
        /**
         * id : 0
         * store_title : 1973上海老街馄炖铺
         * store_images : [{"id":0,"image":"merchant_image01"},{"id":1,"image":"merchant_image02"},{"id":2,"image":"merchant_image03"},{"id":3,"image":"merchant_image04"},{"id":4,"image":"merchant_image02"}]
         * introduction : 暂无简介
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

        public static class StoreImagesBean implements Serializable {
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
}

