package com.jingdong.app.mall.bundle.styleinfoview.events;

/* loaded from: classes3.dex */
public class PDLayerEvent {
    public static final String ACTION_EVENT_FRESH_MENU_DIALOG = "pd_action_event_fresh_menu_dialog";
    public static final String ACTION_EVENT_LAYER_COMMON_D = "action_event_layer_common_d";
    public static final String ACTION_EVENT_LAYER_FAMILY_COUPON = "action_event_layer_family_coupon";
    public static final String ACTION_EVENT_LAYER_PLUS_EXPAND = "action_event_layer_plus_expand";
    public static final String ACTION_EVENT_LAYER_REPLACE_DIALOG = "action_event_layer_replace_dialog";
    public static final String ACTION_EVENT_LAYER_REPLACE_OPEN_STYLE_DIALOG = "action_event_layer_replace_open_style_dialog";
    public static final String ACTION_EVENT_LAYER_SUPER_BRAND = "action_event_layer_super_brand";
    public static final String ACTIVON_EVENT_LALYER_3C_SPECIFICATIONS = "action_event_layer_3c_specifications";
    public static final String ACTIVON_EVENT_LALYER_ADDRESS = "action_event_layer_address";
    public static final String ACTIVON_EVENT_LALYER_BUSINESS_PROMOTION = "action_event_layer_business_promotion";
    public static final String ACTIVON_EVENT_LALYER_BUSINESS_WHITEBAR = "action_event_layer_business_whitebar";
    public static final String ACTIVON_EVENT_LALYER_COMMON_AC = "action_event_layer_common_ac";
    public static final String ACTIVON_EVENT_LALYER_COMMON_B = "action_event_layer_common_b";
    public static final String ACTIVON_EVENT_LALYER_COUPON = "action_event_layer_coupon";
    public static final String ACTIVON_EVENT_LALYER_DAOJIA = "action_event_layer_daojia_tip";
    public static final String ACTIVON_EVENT_LALYER_FRESH_RECOMMEND = "action_event_layer_add2car_fresh_recommend";
    public static final String ACTIVON_EVENT_LALYER_INSTALLMENT = "action_event_layer_installment";
    public static final String ACTIVON_EVENT_LALYER_OVERSEATAX = "action_event_layer_overseatax";
    public static final String ACTIVON_EVENT_LALYER_OVERSEA_SERVICE = "action_event_layer_oversea_service";
    public static final String ACTIVON_EVENT_LALYER_PLUS_MEMBER = "action_event_layer_plus_member";
    public static final String ACTIVON_EVENT_LALYER_PREFERENTIAL_GUIDE = "action_event_layer_preferential_guide";
    public static final String ACTIVON_EVENT_LALYER_RECOMMEND = "action_event_layer_add2car_recommend";
    public static final String ACTIVON_EVENT_LALYER_RULE = "action_event_layer_common_rule";
    public static final String ACTIVON_EVENT_LALYER_SERVICE = "action_event_layer_service";
    public static final String ACTIVON_EVENT_LALYER_STYLE = "action_event_layer_style";
    public String mKey;
    public String mManagerKey;
    public Object mObject;
    public Object[] mObjectArr;

    public PDLayerEvent(String str, Object obj, String str2) {
        this.mKey = str;
        this.mObject = obj;
        this.mManagerKey = str2;
    }

    public PDLayerEvent(String str, Object[] objArr, String str2) {
        this.mKey = str;
        this.mObjectArr = objArr;
        this.mManagerKey = str2;
    }
}
