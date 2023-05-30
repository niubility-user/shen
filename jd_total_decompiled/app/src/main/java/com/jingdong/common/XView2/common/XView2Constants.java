package com.jingdong.common.XView2.common;

/* loaded from: classes5.dex */
public class XView2Constants {
    public static final String ACTION = "action";
    public static final String ACTIVITY = "activity";
    public static final String APPEAR_ANIM_TYPE = "appearAnimType";
    public static final String APPENDURLPARAMS = "appendUrlParams";
    public static final String BUZID = "buzID";
    public static final String CACHE_FILE_ROOT = "XView";
    public static final String CHANGE_LAYOUT = "changeLayout";
    public static final String CHANNEL = "channel";
    public static final int CLICK_CLOSE = 1;
    public static final int CLICK_MATERIAL = 2;
    public static final int CLICK_OUTSIDE = 4;
    public static final int CLICK_WIDGET = 3;
    public static final String DRAGMODE_DEFAUlT = "0";
    public static final String DRAGMODE_DISABLED = "1";
    public static final String DRAGMODE_ENABLE = "2";
    public static final String DRAGMODE_HORIZONTAL = "4";
    public static final String DRAGMODE_VERTICAL = "3";
    public static final String FILL_CONTAINER = "fillContainer";
    public static final String FRAGMENT = "fragment";
    public static final String HEIGHT = "height";
    public static final String HOME_KEY = "com.jingdong.app.mall.MainFrameActivity";
    public static final String IGNORE_PRIORITY = "ignorePriority";
    public static final String INFO_NAME = "name";
    public static final String INFO_URL = "url";
    public static final String ISVISIBLE = "isVisible";
    public static final String IS_ALPHA = "isAlpha";
    public static final String JDGLOBAL_WINDOWPAGE = "JDGlobalWindowPage";
    public static final String JDHOMEFRAGMENT = "JDHomeFragment";
    public static final String LAYER_ID = "layerId";
    public static final String LAYER_RELOAD = "layerReLoad";
    public static final String LEFT = "left";
    public static final String LOGIC_KEY = "logicKey";
    public static final String MAIN_FRAME_ACTIVITY_TAG = "MainFrameActivity";
    public static final String METHOD_NAME = "methodName";
    public static final String MODULE_NAME = "moduleName";
    public static final String OPEN_LOCATION_PERMISSION = "openLocationPermission";
    public static final String OPEN_RECOMMEND = "openRecommend";
    public static final String OPEN_REPEAT_BUY = "openRepeatBuy";
    public static final String PAGE_ID = "XView2.0_Main";
    public static final String PARRAMS = "params";
    public static final String PLAY_TYPE = "73";
    public static final String PREVIEW = "preview";
    public static final String REMOVE_LAYER = "removeLayer";
    public static final String SET_LAYER_VISIBLE = "setLayerVisible";
    public static final String SHOW_LAYER = "showLayer";
    public static final String SPLASHFRAGMENT = "SplashFragment";
    public static final String STATE = "state";
    public static final String STYLEID = "styleId";
    public static final String TAG = "XView2";
    public static final String TARGETOPENAPP = "targetOpenApp";
    public static final String TOP = "top";
    public static final String TYPE = "type";
    public static final String WIDTH = "width";
    public static final String XVIEW2_ACTION_DIALOG = "dialog";
    public static final String XVIEW2_ACTION_INIT = "init";
    public static final String XVIEW2_ACTION_JUMP = "jump";
    public static final String XVIEW2_ACTION_ONCLICK = "onClick";
    public static final String XVIEW2_ACTION_REQUEST = "request";
    public static final String XVIEW2_ACTION_TOAST = "toast";
    public static final String XVIEW2_ACT_KEYWORD_ERROR = "__error";
    public static final String XVIEW2_ACT_KEYWORD_RESPONSE = "__response";
    public static final String XVIEW2_DIALOG_ACTION_CANCELTEXT = "cancelText";
    public static final String XVIEW2_DIALOG_ACTION_OKTXT = "okText";
    public static final String XVIEW2_DIALOG_ACTION_STATUS = "status";
    public static final String XVIEW2_DIALOG_ACTION_SUBTITLE = "subTitle";
    public static final String XVIEW2_DIALOG_ACTION_TITLE = "title";
    public static final String XVIEW2_FUNCTION_CONFIG = "xview2Config";
    public static final int XVIEW2_LAYER_CLOSE_ANIM_LOCATION = 2;
    public static final int XVIEW2_LAYER_POP_LOCATION = 1;
    public static final String XVIEW2_POP_EVENT_NAME = "com.jingdong.common.XView2.event.XView2DispatchEventManager";
    public static final String XVIEW2_REQUEST_ACTION_FUNCNAME = "funcName";
    public static final String XVIEW2_REQUEST_ACTION_OPS = "ops";
    public static final String XVIEW2_REQUEST_ACTION_PARAMS = "params";
    public static final String XVIEW2_REQUEST_ACTION_URL = "url";
    public static final String XVIEW2_STATES_ACTION = "status";
    public static final String XVIEW2_TOAST_ACTION_STATUS = "status";
    public static final String XVIEW2_TOAST_ACTION_SUBTITLE = "subTitle";
    public static final String XVIEW2_TOAST_ACTION_TITLE = "title";
    public static final String XVIEW_CLOSE = "Xview2.0_Close";
    public static final String XVIEW_CONTROL = "Xview2.0_Control";
    public static final String XVIEW_EXCEPTION_REPORT = "xviewExceptionReport";
    public static final String XVIEW_GLOBALMODEL = "xviewGlobalModel";
    public static final String XVIEW_INVOKE = "Xview2_Invoke";
    public static final String XVIEW_INVOKE_EXCEPTION = "xviewInvokeException";
    public static final String XVIEW_JUMP_CLICK = "Xview2.0_Jump_Click";
    public static final String XVIEW_LAYEREXPO = "Xview2.0_LayerExpo";
    public static final String XVIEW_PERFORMANCE = "Xview2.0_Performance";
    public static final String XVIEW_POP_REPORT = "xviewPopReport";
    public static final String XVIEW_PREDOWNLOAD = "xviewPreDownLoad2";
    public static final String XVIEW_PRELOAD = "Xview2_Preload";
    public static final String XVIEW_PRELOAD_SUCCESS = "xviewPreloadSuccess";
    public static CLOSE_TYPE mCloseType;
    public static final double renderLowRatio = 0.0d;

    /* loaded from: classes5.dex */
    public enum CLOSE_TYPE {
        BACK_KEY,
        JUMP_CLOSE,
        CLOSE_BY_OUTSIDE
    }

    /* loaded from: classes.dex */
    public @interface CloseReason {
        public static final int CLOSE_BY_CLICK_BTN = 2;
        public static final int CLOSE_BY_CONTROL = 3;
        public static final int CLOSE_BY_COUNTTIME_FINISH = 16;
        public static final int CLOSE_BY_JUMP = 1;
        public static final int CLOSE_BY_LEAVE = 8;
        public static final int CLOSE_BY_OPENAPP = 9;
        public static final int CLOSE_BY_TIMER_END = 4;
        public static final int CLOSE_CONTENT_OUTSIDE = 11;
        public static final int LOTTIE_END = 5;
        public static final int RESOURCE_LOAD_FAIL = 14;
        public static final int TIMER_CLOSE_CLICK = 13;
        public static final int TIME_OVER = 12;
        public static final int VIDEO_COMPLETION = 6;
        public static final int VIDEO_ERROR = 7;
        public static final int WEBVIEW_LOAD_FAIL = 15;
        public static final int XVIEW_CLOSE_BY_SELF = 10;
    }

    /* loaded from: classes.dex */
    public @interface ErrorType {
    }
}
