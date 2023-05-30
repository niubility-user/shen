package com.jingdong.jdma.minterface;

import java.util.HashMap;

/* loaded from: classes12.dex */
public class OrderInterfaceParam extends BaseEvent {
    public String cart_jdv;
    public String cart_seq;
    public String cart_sid;
    public String cart_ts;
    public String lv0_source_id;
    public String lv1_event_id;
    public String lv1_event_param;
    public String lv1_page_name;
    public String lv1_page_param;
    public String lv2_event_id;
    public String lv2_event_param;
    public String lv2_page_name;
    public String lv2_page_param;
    public String lv3_event_id;
    public String lv3_event_param;
    public String lv3_page_name;
    public String lv3_page_param;
    public String lv4_event_id;
    public String lv4_event_param;
    public String lv4_page_name;
    public String lv4_page_param;
    public String lv5_event_id;
    public String lv5_event_param;
    public String lv5_page_name;
    public String lv5_page_param;
    public HashMap<String, String> map;
    public String ord_ext;
    public String ord_type;
    public String order_total_fee;
    public String order_ts;
    public String prod_id;
    public String pv_seq;
    public String pv_sid;
    public String quantity;
    public String sale_ord_id;
    public String sku_tag;

    @Override // com.jingdong.jdma.minterface.BaseEvent
    protected void addDataToMap(HashMap<String, String> hashMap) {
        hashMap.put("order_ts", this.order_ts);
        hashMap.put("cart_ts", this.cart_ts);
        hashMap.put("cart_sid", this.cart_sid);
        hashMap.put("cart_seq", this.cart_seq);
        hashMap.put("cart_jdv", this.cart_jdv);
        hashMap.put("lv0_source_id", this.lv0_source_id);
        hashMap.put("lv1_event_id", this.lv1_event_id);
        hashMap.put("lv1_event_param", this.lv1_event_param);
        hashMap.put("lv1_page_name", this.lv1_page_name);
        hashMap.put("lv1_page_param", this.lv1_page_param);
        hashMap.put("lv2_event_id", this.lv2_event_id);
        hashMap.put("lv2_event_param", this.lv2_event_param);
        hashMap.put("lv2_page_name", this.lv2_page_name);
        hashMap.put("lv2_page_param", this.lv2_page_param);
        hashMap.put("lv3_event_id", this.lv3_event_id);
        hashMap.put("lv3_event_param", this.lv3_event_param);
        hashMap.put("lv3_page_name", this.lv3_page_name);
        hashMap.put("lv3_page_param", this.lv3_page_param);
        hashMap.put("lv4_event_id", this.lv4_event_id);
        hashMap.put("lv4_event_param", this.lv4_event_param);
        hashMap.put("lv4_page_name", this.lv4_page_name);
        hashMap.put("lv4_page_param", this.lv4_page_param);
        hashMap.put("lv5_event_id", this.lv5_event_id);
        hashMap.put("lv5_event_param", this.lv5_event_param);
        hashMap.put("lv5_page_name", this.lv5_page_name);
        hashMap.put("lv5_page_param", this.lv5_page_param);
        hashMap.put("sale_ord_id", this.sale_ord_id);
        hashMap.put("prod_id", this.prod_id);
        hashMap.put("quantity", this.quantity);
        hashMap.put("order_total_fee", this.order_total_fee);
        hashMap.put("pv_sid", this.pv_sid);
        hashMap.put("pv_seq", this.pv_seq);
        hashMap.put("sku_tag", this.sku_tag);
        hashMap.put("ord_type", this.ord_type);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLogType() {
        return "sr";
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLts() {
        return "od";
    }
}
