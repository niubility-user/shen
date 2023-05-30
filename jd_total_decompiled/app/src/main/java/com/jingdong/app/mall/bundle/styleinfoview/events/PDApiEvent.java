package com.jingdong.app.mall.bundle.styleinfoview.events;

/* loaded from: classes3.dex */
public class PDApiEvent {
    public static final String ACTION_DETAIL_PAIPAI_REPLACE_DATA_KEY = "action_detail_paipai_replace_data_key";
    public static final String ACTION_DETAIL_PAIPAI_REPLACE_ERROR_KEY = "action_detail_paipai_replace_error_key";
    public static final String ACTION_DETAIL_PAIPAI_REPLACE_OTHER_DATA_KEY = "action_detail_paipai_replace_other_data_key";
    public static final String ACTION_DETAIL_PAIPAI_REPLACE_TYPE_3_DATA_KEY = "action_detail_paipai_replace_type_3_data_key";
    public static final String ACTION_DETAIL_PAIPAI_REPLACE_TYPE_4_DATA_KEY = "action_detail_paipai_replace_type_4_data_key";
    public static final String ACTION_EVENT_GIFT_OPEN = "pd_action_event_gift_open";
    public static final String ACTION_EVENT_WIDTH_VIEW = "pd_action_event_width_view";
    public static final String DETAIL_ADD2CAR_FRESH_RECOMMEND_KEY = "detail_add2car_fresh_recommend_key";
    public static final String DETAIL_ADD2CAR_RECOMMEND_KEY = "detail_add2car_recommend_key";
    public static final String DETAIL_AD_RECOMMEND_KEY = "detail_ad_recommend_key";
    public static final String DETAIL_APPOINT_HASSR_KEY = "detail_appoint_hassr_key";
    public static final String DETAIL_APPOINT_NOSR_KEY = "detail_appoint_nosr_key";
    public static final String DETAIL_BUY_STATUS_KEY = "detail_buy_status_key";
    public static final String DETAIL_CHANGE_SKIN_KEY = "detail_change_skin_key";
    public static final String DETAIL_COMMENT_OFFICER_REQUEST_KEY = "detail_comment_officer_key";
    public static final String DETAIL_COMMENT_REQUEST_KEY = "detail_comment_key";
    public static final String DETAIL_COMMENT_VIDEO_URL_KEY = "detail_comment_video_url_key";
    public static final String DETAIL_COUDAN_FREIGHT_KEY = "detail_coudan_freefreight_key";
    public static final String DETAIL_COUPON_LIST_KEY = "detail_coupon_list_key";
    public static final String DETAIL_COUPON_RECEIVE_ERROR_KEY = "detail_coupon_receive_error_key";
    public static final String DETAIL_COUPON_RECEIVE_KEY = "detail_coupon_receive_key";
    public static final String DETAIL_DSJ_TO_COUPON_KEY = "detail_dsj_to_coupon_key";
    public static final String DETAIL_EXCLUSIVE_COUPON_RECEIVE_KEY = "detail_exclusive_coupon_receive_key";
    public static final String DETAIL_EYE_SIGHT_KEY = "detail_buy_status_key";
    public static final String DETAIL_FAMILY_NUMBER_ADD_KEY = "detail_family_number_add_key";
    public static final String DETAIL_FAST_CHAT_CLOSE_KEY = "detail_fast_chat_close_key";
    public static final String DETAIL_FIRST_PURCHASE_KEY = "detail_first_purchase_key";
    public static final String DETAIL_GET_VIDEO_URL = "detail_get_video_url";
    public static final String DETAIL_HANDLE_APPOINT_KEY = "detail_appoint_handle_key";
    public static final String DETAIL_HONGBAO_RECEIVE_ERROR_KEY = "detail_hongbao_receive_error_key";
    public static final String DETAIL_HONGBAO_RECEIVE_KEY = "detail_hongbao_receive_key";
    public static final String DETAIL_LV_KEY = "detail_lv_key";
    public static final String DETAIL_OTHER_RECOMMEND_KEY = "detail_other_recommend_key";
    public static final String DETAIL_PACK_PRICE_KEY = "detail_pack_price_key";
    public static final String DETAIL_PACK_STYLE_KEY = "detail_pack_style_key";
    public static final String DETAIL_RANKLIST_KEY = "detail_ranklist_key";
    public static final String DETAIL_RECOMMEND_KEY = "detail_recommend_key";
    public static final String DETAIL_SHARE_CONTENT_KEY = "detail_share_content_key";
    public static final String DETAIL_SKU_NOTICE_KEY = "detail_skunotice_key";
    public static final String DETAIL_STYLE_WAREPRICE_KEY = "detail_style_wareprice_key";
    public static final String DETAIL_TO_MAINIMAGE_PRAISE_KEY = "detail_coudan_freefreight_key";
    public static final String DETAIL_WARE_BUSINESS_NEW_KEY = "detail_ware_business_new_key";
    public static final String ECARD_COUNT_KEY = "get_ecard_count_key";
    public static final String EVENT_CHANGE_SCREEN = "change_screen";
    public static final String EVENT_GET_RECOMMEND_DATA = "get_recommend_data";
    public static final String PRESCRIPTION_COUNT_KEY = "get_prescription_count_key";
    public static final String PRINTBAG_COUNT_KEY = "get_printbag_count_key";
    public static final String SKU_DETAIL_KEY = "sku_detail_key";
    public static final String SKU_DYINFO_KEY = "sku_dyinfo_key";
    public static final String THIRD_ADDRESS_KEY = "third_address_key";
    public String mKey;
    public String mManagerKey;
    public Object mObject;

    public PDApiEvent(String str, Object obj, String str2) {
        this.mKey = str;
        this.mObject = obj;
        this.mManagerKey = str2;
    }
}
