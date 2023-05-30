package com.jd.lib.productdetail.core.events;

/* loaded from: classes15.dex */
public class PDLayerEvent {
    public static final String ACTION_EVENT_FRESH_MENU_DIALOG = "pd_action_event_fresh_menu_dialog";
    public static final String ACTION_EVENT_LAYER_COMMON_D = "action_event_layer_common_d";
    public static final String ACTION_EVENT_LAYER_CREDITTEST_DIALOG = "action_event_layer_credittest_dialog";
    public static final String ACTION_EVENT_LAYER_FAMILY_COUPON = "action_event_layer_family_coupon";
    public static final String ACTION_EVENT_LAYER_KA_DIALOG = "action_event_layer_ka_dialog";
    public static final String ACTION_EVENT_LAYER_PLUS_EXPAND = "action_event_layer_plus_expand";
    public static final String ACTION_EVENT_LAYER_SUPER_BRAND = "action_event_layer_super_brand";
    public static final String ACTION_EVENT_LAYER_TRADEIN = "action_event_layer_tradein";
    public static final String ACTION_EVENT_LAYER_TRADEIN_OPEN_STYLE = "action_event_layer_tradein_open_style";
    public static final String ACTION_EVENT_LAYER_WEBVIEW_DIALOG = "action_event_layer_webview_dialog";
    public static final String ACTION_EVENT_PROMOTION_FUTURE_REMIND = "action_event_layer_promotion_future_remind";
    public static final String ACTION_EVENT_RECOMMEND_DIALOG = "action_event_layer_recommend_dialog";
    public static final String ACTION_EVENT_RECOMMEND_TOPIMAGE_DIALOG = "action_event_layer_recommend_dialog_topimage";
    public static final String ACTION_EVENT_SHOW_DYNAMIC_VIEW = "pd_action_event_show_dynamic_view";
    public static final String ACTIVON_EVENT_HEALTH_EXPLANATION_DIALOG = "action_event_layer_health_explanation_dialog";
    public static final String ACTIVON_EVENT_LALYER_3C_SPECIFICATIONS = "action_event_layer_3c_specifications";
    public static final String ACTIVON_EVENT_LALYER_ADDRESS = "action_event_layer_address";
    public static final String ACTIVON_EVENT_LALYER_BIGPLUS_GUIDE = "action_event_layer_bigplus_guide";
    public static final String ACTIVON_EVENT_LALYER_BUSINESS_PROMOTION = "action_event_layer_business_promotion";
    public static final String ACTIVON_EVENT_LALYER_BUSINESS_WHITEBAR = "action_event_layer_business_whitebar";
    public static final String ACTIVON_EVENT_LALYER_COLLAGE_JOIN = "action_event_layer_collage_join";
    public static final String ACTIVON_EVENT_LALYER_COMMON_AC = "action_event_layer_common_ac";
    public static final String ACTIVON_EVENT_LALYER_COMMON_B = "action_event_layer_common_b";
    public static final String ACTIVON_EVENT_LALYER_DAOJIA = "action_event_layer_daojia_tip";
    public static final String ACTIVON_EVENT_LALYER_DISMISS = "activon_event_lalyer_dismiss";
    public static final String ACTIVON_EVENT_LALYER_FRESH_RECOMMEND = "action_event_layer_add2car_fresh_recommend";
    public static final String ACTIVON_EVENT_LALYER_INSTALLMENT = "action_event_layer_installment";
    public static final String ACTIVON_EVENT_LALYER_KA = "action_event_layer_ka";
    public static final String ACTIVON_EVENT_LALYER_MEDICAL_INSURANCE = "activon_event_lalyer_medical_insurance";
    public static final String ACTIVON_EVENT_LALYER_OVERSEATAX = "action_event_layer_overseatax";
    public static final String ACTIVON_EVENT_LALYER_OVERSEA_SERVICE = "action_event_layer_oversea_service";
    public static final String ACTIVON_EVENT_LALYER_PLUS_MEMBER = "action_event_layer_plus_member";
    public static final String ACTIVON_EVENT_LALYER_PREFERENTIAL_GUIDE = "action_event_layer_preferential_guide";
    public static final String ACTIVON_EVENT_LALYER_RECOMMEND = "action_event_layer_add2car_recommend";
    public static final String ACTIVON_EVENT_LALYER_RULE = "action_event_layer_common_rule";
    public static final String ACTIVON_EVENT_LALYER_SERVICE = "action_event_layer_service";
    public static final String ACTIVON_EVENT_LALYER_SHOW = "activon_event_lalyer_show";
    public static final String ACTIVON_EVENT_LALYER_STYLE = "action_event_layer_style";
    public static final String ACTIVON_EVENT_LALYER_TOP_IMAGE_CF = "activon_event_lalyer_top_image_cf";
    public static final String ACTIVON_EVENT_LALYER_TOP_IMAGE_GIFT = "activon_event_lalyer_top_image_gift";
    public static final String ACTIVON_EVENT_LALYER_WHITE = "action_event_layer_white";
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
