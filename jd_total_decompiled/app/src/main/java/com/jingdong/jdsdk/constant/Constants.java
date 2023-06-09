package com.jingdong.jdsdk.constant;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.RadioButton;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Constants {
    public static final String ADD_SHORT_CUT_FLAG = "add_short_cut_flag_708";
    public static final String AUTO_PLAY_VIDEO_WIFI_SETTED_KEY = "auto_play_video_wifi_setted";
    public static final int BindQQ = 4;
    public static final int BindWX = 3;
    public static final String CLEAR_HISTORY_FLAG = "com.360buy:clearHistoryFlag";
    public static final String CLEAR_TOP_FLAG = "com.360buy:clearTopFlag";
    public static final String COLOR_BUY_SELECTED_CATEGORIES = "color_buy_selected_categories";
    public static final String DEEP_DARK_FOLLOW_SYS_SWITCH = "deep_dark_follow_sys_switch";
    public static final String DEEP_DARK_GUIDE_DIALOG = "deep_dark_guide_dialog";
    public static final String DEEP_DARK_GUIDE_SWITCH = "deep_dark_guide_switch";
    public static final String DEEP_DARK_MODE = "deep_dark_mode";
    public static final String ENCRYPT_KEY = "jd_key4";
    public static final String EVALUATE_POINT_TYPE = "evaluate_point_type";
    public static final String EXIT_TYPE_BACKGROUND = "1";
    public static final String EXIT_TYPE_DIALOG = "3";
    public static final String EXIT_TYPE_TOAST = "2";
    public static final String FEEDBACK_CONTACTS = "FEEDBACKER_CONTACT";
    public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 32;
    public static final int FLOAT_TIER_ANIMATION_DURATION = 400;
    public static final String HOME_ACTIVITY_BUTTON_IS_DOWN = "home_button_down";
    public static final String HTTPS_PREFIX = "https://";
    public static final String HTTP_PREFIX = "http://";
    public static final String IM_PRODUCT_IMAGE_URL = "im_image_url";
    public static final String IM_PRODUCT_JD_PRICE = "im_jd_price";
    public static final String IM_PRODUCT_M_URL = "im_product_m_url";
    public static final String IM_PRODUCT_NAME = "im_product_name";
    public static final String IM_SKU_ID = "im_sku";
    public static final String IM_USER_PIN = "im_pin";
    public static final String INVOICE_PERSONAL_FIRST_SHOW = "invoice_personal_first_show";
    public static final String JDREMINDER_RECEIVER_ACTION_NAME = "com.jingdong.app.mall.reminder";
    public static final String JD_NIGHT_MODE_SWITCH = "jd_night_mode_switch";
    public static final String JD_NO_IAMGE_SWITCH = "jd_no_image_switch";
    public static final String JD_SHARE_PREFERENCE_CITY_ID_MODE_1 = "CITY_ID_MODE_1";
    public static final String JD_SHARE_PREFERENCE_PROVINCE_ID_MODE_1 = "PROVINCE_ID_MODE_1";
    public static final String JD_WIDGET_DELETED_FLAG = "jd_widget_deleted";
    public static final String JLOG_ACTIVITYNAME_PARAM_KEY = "activityName";
    public static final String JLOG_CATEGORYID_PARAM_KEY = "categoryId";
    public static final String JLOG_EVENT_ACHIVE = "shake_achieve";
    public static final String JLOG_EVENT_SHAKE = "shake_shake";
    public static final String JLOG_EVENT_SHAKEPAGE_ALL_SHAKE = "JD_ShakePage_ShakeAllBtn";
    public static final String JLOG_EVENT_SHAKEPAGE_GETCOUPON = "JD_ShakePage_GetCoupon";
    public static final String JLOG_EVENT_SHAKEPAGE_SHAKEING = "JD_ShakePage_Shaking";
    public static final String JLOG_EVENT_SHAKEPAGE_TIME = "JD_ShakePage_Time";
    public static final String JLOG_EVENT_SINGER = "shake_singer";
    public static final String JLOG_EVENT_SKU = "shake_click_sku";
    public static final String JLOG_EVENT_SNS = "share_sns";
    public static final String JLOG_ORDERID_PARAM_KEY = "orderId";
    public static final String JLOG_PAGEID_ANDROID_PHONE = "android_jd_";
    public static final String JLOG_PRODUCT_PARAM_KEY = "productId";
    public static final String JLOG_PRODUCT_QIHOO_PV_KEY = "ProductDetailFromQihoo";
    public static final String JLOG_PRODUCT_QIHOO_PV_VALUE = "qihoo.search";
    public static final String JLOG_SEARCH_PARAM_KEY = "searchKeyWord";
    public static final String JLOG_SHAKE_AHARE_ACHIEVE = "shake_achieve";
    public static final String JLOG_SHAKE_AHARE_SHAKE = "shake_shake";
    public static final String JLOG_SHAKE_ERR = "err";
    public static final String JLOG_SHAKE_PARSE_ERR = "-1";
    public static final String JLOG_SHAKE_TYPE = "type";
    public static final String LIB_STORY_JUMP_KEY = "subDes";
    public static final String LIB_STORY_SAVED_EDIT_INFO = "story_saved_info";
    public static final int LIB_STORY_SCAN_PICTURE = 4;
    public static final int LOG_IN = 1;
    public static final int LOG_OFF = 0;
    public static final String MAINACTIVITY_FULLNAME = "com.jingdong.app.mall.main.MainActivity";
    public static final int MAX_CART_PROD_COUNT = 50;
    public static final int MAX_DISCUSS_TEXT_LENGTH = 200;
    public static final int MAX_IMG_URL_CACHE_LIST = 20;
    public static final int MAX_LATEST_VISITED_PRODUCT = 20;
    public static final int MAX_SINGLE_PROD_COUNT = 1000;
    public static final int MESSAGE_TYPE_REACTIVATION = 4;
    public static final int NAVIGATION_CATEGORY = 1;
    public static final String NAVIGATION_DISPLAY_FLAG = "com.360buy:navigationDisplayFlag";
    public static final int NAVIGATION_DISPLAY_HIDE = -1;
    public static final int NAVIGATION_DISPLAY_SHOW = 0;
    public static final int NAVIGATION_FAXIAN = 2;
    public static final String NAVIGATION_FLAG = "com.360buy:navigationFlag";
    public static final int NAVIGATION_HOME = 0;
    public static final String NAVIGATION_ID = "com.360buy:navigationId";
    public static final int NAVIGATION_MORE = 5;
    public static final int NAVIGATION_MYJD = 4;
    public static final int NAVIGATION_SHOPPINGCAR = 3;
    public static final String NOTICE_SP_LASTEST_REFRESH_PUBTIME = "notice_lastest_pubTime";
    public static final int NOT_NOTIFICATION_HOUR_MAX = 21;
    public static final int NOT_NOTIFICATION_HOUR_MIN = 9;
    public static final int PLATFORM = 100;
    public static final String POST_TO_CONFIRM_SUCCESS_FLAG = "post_order_confrim_flag";
    public static final String REN_MIN_BI = "\u00a5";
    public static final String RESEND_FLAG = "com.360buy:resendFlag";
    public static final String SCAN_ICON_SHOW_STATE_KEY = "scanDisabled";
    public static final String SCAN_ICON_SHOW_STATE_VALUE = "0";
    public static final String SCREEN_SLEEP_SETTING_TIME = "sleep_setting_time";
    public static final String SERVICE_TO_ACTIVIATE_MESSAGE = "message";
    public static final String SHARED_PREFERENCES_CAMERA_DIR = "cameraFilePath";
    public static final String SHARED_PREFERENCES_CAMERA_PHOTO_INDEX = "photoTypeIndex";
    public static final String SHARED_PREFERENCES_CAMERA_PHOTO_TYPE = "photoType";
    public static final String SHARED_PREFERENCES_LOG_MSG_NOTIFY_ID_AND_TIME = "log_push_msg_notify_id_and_time";
    public static final String SHARED_PREFERENCES_LOG_MSG_UNREAD_IDS = "log_push_msg_unread_msg_ids";
    public static final String SHARED_PREFERENCES_MSG_AUTO_UN_PUSH_TIME_KEY = "msg_auto_update_switch";
    public static final String SHARED_PREFERENCES_MSG_DEVICE_TOKEN = "msg_auto_device_token";
    public static final String SHARED_PREFERENCES_MSG_HAS_BIND = "msg_auto_has_bind";
    public static final String SHARED_PREFERENCES_NIGHT_MODE_ALPHA = "nightModeAlpha";
    public static final String SHARED_PREFERENCES_NIGHT_MODE_SETTING_DEFAULT_FLAG = "nightModeSettingDefaultFlag";
    public static final String SHARED_PREFERENCES_NIGHT_MODE_SWITCH = "nightModeSwitch";
    public static final String SHARED_PREFERENCES_REACTIVATION_ALARM_TRIGGER_AT_TIME = "reActivationTriggerAtTime";
    public static final String SHARE_PREFRENCE_NAME = "lib_story_prefrence";
    public static final String SHOW_COST = "showCost";
    public static final String SINGLE_INSTANCE_FLAG = "com.360buy:singleInstanceFlag";
    public static final String STORY_SHARE_PAGE_ACTIVITY_DETAIL = "activity";
    public static final String STORY_SHARE_PAGE_ACTIVITY_LIST = "activityList";
    public static final String STORY_SHARE_PAGE_ACTIVITY_PK_DETAIL = "activityPK";
    public static final String STORY_SHARE_PAGE_CIRCLE_DETAIL = "circle";
    public static final String STORY_SHARE_PAGE_CIRCLE_LIST = "circleList";
    public static final String STORY_SHARE_PAGE_CIRCLE_PARAM_KEY = "circleID";
    public static final String STORY_SHARE_PAGE_DETAIL = "storyDetail";
    public static final String STORY_SHARE_PAGE_HOME = "mainStory";
    public static final String STORY_SHARE_PAGE_PARAM_KEY = "activityID";
    public static final String STORY_SP_LASTEST_REFRESH_PUBTIME = "story_lastest_pubTime";
    public static final String TASK_ID_FLAG = "com.360buy:taskIdFlag";
    public static final String TOPIC_SP_LASTEST_REFRESH_PUBTIME = "topic_lastest_pubTime";
    public static final String UPGRADE_BY_WIFI_KEY = "upgradeByWifi";
    public static final String UPGRADE_WIFI_AUTO_KEY = "upgrade_wifi_auto";
    public static final String UPGRADE_WIFI_SETTED_KEY = "upgrade_wifi_setted";
    public static final String USE_HISTORY_FLAG = "com.360buy:useHistoryFlag";
    public static boolean addNewTemplate = false;
    public static boolean bAddEasyBuy = false;
    @Deprecated
    public static boolean bEasyBuy = false;
    public static boolean bModifyEasyBuy = false;
    public static boolean bNoYouHui = false;
    public static boolean bPhone = false;
    public static boolean dSelected = false;
    public static String dTotalPrice = "0";
    public static String dYDeliveryServiceDetailPrice = "0";
    public static String dYTotalPrice = "0";
    public static JSONObject dongSel;
    public static long easyBuyProdId;
    public static boolean hasNewTocart;
    public static boolean hasNewway;
    public static boolean jSelected;
    public static JSONObject jbOrderNum;
    public static boolean jdSwitch;
    public static JSONArray jinSelArray;
    public static JSONArray liSelArray;
    public static boolean liSelected;
    public static int mMaxCount;
    public static String nSelectDongQuanId;
    public static ArrayList<String> nSelectLipinIDs;
    public static boolean noDong;
    public static boolean noJing;
    public static boolean noLi;
    public static ArrayList<String> nselectJingQuanIDs;
    public static RadioButton oldBtn;
    public static String payPwdKey;
    static boolean ret;
    public static JSONArrayPoxy skusOfSuites;
    public static JSONObject jbProvinces = new JSONObject();
    public static JSONObject jbCitys = new JSONObject();
    public static JSONObject jbAreas = new JSONObject();
    public static HashMap<String, SoftReference<Drawable>> gStrImgUrlCache = new HashMap<>();
    private static Intent gIntent = new Intent();
    public static boolean mustFreshMessage = false;
    public static boolean isCanOnTouch = true;

    public static String StringFilter(String str) {
        return Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~\uff01@#\uffe5%\u2026\u2026& amp;*\uff08\uff09\u2014\u2014+|{}\u3010\u3011\u2018\uff1b\uff1a\u201d\u201c\u2019\u3002\uff0c\u3001\uff1f]").matcher(str).replaceAll("").trim();
    }

    public static void clearOrderInfo() {
        liSelArray = new JSONArray();
        jinSelArray = new JSONArray();
        dongSel = new JSONObject();
        nselectJingQuanIDs = new ArrayList<>();
        nSelectLipinIDs = new ArrayList<>();
        nSelectDongQuanId = null;
        bNoYouHui = false;
        dSelected = false;
        liSelected = false;
        jSelected = false;
        skusOfSuites = null;
        skusOfSuites = new JSONArrayPoxy();
    }

    public static Intent getGlobalIntent() {
        Intent intent = gIntent;
        if (intent == null) {
            return null;
        }
        return intent;
    }
}
