package com.jingdong.common.jdreactFramework;

import java.io.File;

/* loaded from: classes5.dex */
public class JDReactConstant {
    public static final String ASSERT_DIR = "jdreact";
    public static final String AVAILABILITY_APIDEMOS = "JDReactAPIDemos";
    public static final String AVAILABILITY_CARD_PWD_BUY = "JDReactCardPwdBuy";
    public static final String AVAILABILITY_CARD_PWD_DETAIL = "JDReactCardPwd";
    public static final String AVAILABILITY_COMMON = "JDReactCommonPage";
    public static final String AVAILABILITY_FAXIAN = "JDReactFaxian";
    public static final String AVAILABILITY_FLIGHTORDERDETAIL = "JDReactFlightOrderDetail";
    public static final String AVAILABILITY_GAME_PROP = "JDReactGamePropBuy";
    public static final String AVAILABILITY_JSHOP_MINE = "JDReactShopMine";
    public static final String AVAILABILITY_LIVING_PAY = "JDReactLivingPay";
    public static final String AVAILABILITY_LIVING_PAY_ORDER_DETAIL = "JDReactLivingPayOrderDetail";
    public static final String AVAILABILITY_LIVING_PAY_RECORDS = "JDReactLivingPayRecords";
    public static final String AVAILABILITY_MOVIE = "JDReactMovieHomePage";
    public static final String AVAILABILITY_NEW_PRODUCT = "JDReactNewProduct";
    public static final String AVAILABILITY_ONE_TREASURE = "JDReactOneTreasure";
    public static final String AVAILABILITY_PAYSUCCESS = "JDReactPaySuccess";
    public static final String AVAILABILITY_PLAY_GROUND = "JDReactNativePlayground";
    public static final String AVAILABILITY_SIGNRANK = "JDReactShopSignRankList";
    public static final String AVAILABILITY_UNION = "JDReactUnion";
    public static final String BAK_FULL_PATH_KEY = "bakFullPathKey";
    public static final String BAK_PATH_KEY = "bakPathKey";
    public static final String BAK_SPLIT_PATH_KEY = "bakSplitPathKey";
    public static final String BLOCK = "block";
    public static final String BUFF_DIR_FULL = "full";
    public static final String BUFF_DIR_ONE = "backup0";
    public static final String BUFF_DIR_SPLIT = "split";
    public static final String BUFF_DIR_TWO = "backup1";
    public static final String CHECK_COMPLETE_KEY = "checkComplete";
    public static final String COMMITID = "commitId";
    public static final String COMMON_ASSETS_BUNDLE_PATH = "jdreact/JDReactCommon/JDReactCommon.jsbundle";
    public static final String COMMON_VERSION_FLAG = "commonVersion";
    public static final String CUR_FULL_PATH_KEY = "curFullPathKey";
    public static final String CUR_PATH_KEY = "curPathKey";
    public static final String CUR_SPLIT_PATH_KEY = "curSplitPathKey";
    public static final long DEFAULT_CHECK_THRESHOLD = 300000;
    public static final long DEFAULT_MODULE_THRESHOLD = 300000;
    public static final int DEFAULT_UPDATE_LEVEL = -1;
    public static final String DOWNLOAD_TMPFILE_SUFFIX = ".tmp.download";
    public static final String D_FLAG = "downLoadStatus";
    public static final String FAILED = "failed";
    public static final int FORCE_UPDATE_LEVEL = 1;
    public static final String IN_PROGRESS = "progressing";
    public static final String JDFLUTTER_PACKAGE_NAME = "JDFlutterModules";
    public static final String JDREACT_DEBUG_TOOL_FLAG = "react_debug_tool_flag";
    public static final String JDREACT_DEVELOP_FLAG = "react_develop_flag";
    public static final String LAST_CHECK_COMPLETE_KEY = "lastCHeckComplete";
    public static final String LAST_CHECK_RESPONSE_STRING = "lastCHeckResponseStr";
    public static final String LAST_CHECK_TIME_KEY = "lastCHeckTime";
    public static final String LAST_MODULE_AVAILABILITY_RESULT = "availability_result";
    public static final String LAST_MODULE_AVAILABILITY_TIME = "availability_time";
    public static final String MESSAGE_TAG_CHARGE_SELECT_CITY = "message_tag_charge_select_city";
    public static final String MESSAGE_TAG_PICK_CONTACT = "message_tag_pick_contact";
    public static final String MESSAGE_TAG_PICK_PTHTO_CONTENT = "message_tag_pick_photo_content";
    public static final String MESSAGE_TAG_SCAN_CODE_CONTENT = "message_tag_scan_code_content";
    public static final String MODULE_COMMON_VERSION = "commonVersion";
    public static final String ModuleCode = "moduleCode";
    public static final String ModuleName = "moduleName";
    public static final String ModuleTag = "module";
    public static final String NET_REQUEST_FLAG_DOWNLOAD = "Download_JDreact";
    public static final String NET_REQUEST_FLAG_IMAGE = "Image_JDreact";
    public static final String NONE = "none";
    public static final int NON_FORCE_UPDATE_LEVEL = 2;
    public static final int NORMAL_PREPARE_LEVEL = 4;
    public static final int NORMAL_UPDATE_LEVEL = 3;
    public static final boolean NeedCrypt = false;
    public static final String PRELOAD_PACKAGE = "preloadpackage";
    public static final String PREPARE = "prepare";
    public static final String QUERY_REACTNATIVE = "getReactNativeVersion";
    public static final String REACT_APP_BUILD_NUMBER = "app_build_number";
    public static final String REACT_APP_BUILD_NUMBER_FOR_UPDATE = "app_build_number_update";
    public static final String REACT_ASSET_HEAD = "file:///android_asset/";
    public static final int REQUEST_CODE_CHARGE_SELECT_CARD = 1003;
    public static final int REQUEST_CODE_CHARGE_SELECT_CITY = 1002;
    public static final int REQUEST_CODE_JDPAY_SDK = 1004;
    public static final int REQUEST_CODE_PICK_CONTACT = 1001;
    public static final int REQUEST_CODE_PICK_CONTACT2 = 1005;
    public static final String SETTING_DEBUG_FLOAT_FLAG = "setting_debug_float_flag";
    public static final String SHARE_DEBUG_PREFRENCE_NAME = "react_debug_prefrence";
    public static final String SHARE_PREFRENCE_NAME = "react_prefrence";
    public static final String SP_REPORT_NAME = "react_sp_data_report";
    public static final String SUCESSS = "success";
    public static final long TEST_CHECK_THRESHOLD = 1;
    public static final String TMP_FILE = "tmp.zip";
    public static final String TPYE_FLAG = "isFull";
    public static final String UNZIP_FAILED = "unzip_failed";
    public static final String UNZIP_IN_PROGRESS = "unzip_progressing";
    public static final String UNZIP_SUCCESS = "unzip_success";
    public static final String UPDATE_LEVEL = "update_level";
    public static final String U_FLAG = "unZipStatus";
    public static final Boolean USE_JSON_VERSION_FILE = Boolean.TRUE;
    public static final String ReactPreload_PATH = "reactpreload";
    public static final File ReactPreloadPath = JDReactHelper.newInstance().getApplicationContext().getDir(ReactPreload_PATH, 0);
    public static final String ReactNative_PATH = "reactnative599";
    public static final File ReactDownloadPath = JDReactHelper.newInstance().getApplicationContext().getDir(ReactNative_PATH, 0);
    public static final String ReactNative_PATH_OLD = "reactnative";
    public static final File ReactDownloadPathOld = JDReactHelper.newInstance().getApplicationContext().getDir(ReactNative_PATH_OLD, 0);
    public static final String FLUTTER_PATH = "flutter";
    public static final File FLUTTERDownloadPath = JDReactHelper.newInstance().getApplicationContext().getDir(FLUTTER_PATH, 0);
    public static final String COMMON_BUNDLE_NAME = "JDReactCommon";
    public static final String COMMON_BUNDLE_RELATIVE_PATH = File.separator + COMMON_BUNDLE_NAME + ".jsbundle";

    /* loaded from: classes5.dex */
    public interface InitErrorType {
        public static final int BUNDLE_PATH_EMPTY = 2;
        public static final int JS_EXCEPTION = 4;
        public static final int MODULE_NAME_EMPTY = 1;
        public static final int ROOT_VIEW_NULL = 3;
        public static final int SETUP_REACT_CONTEXT_FAIL = 5;
    }

    /* loaded from: classes5.dex */
    public interface IntentConstant {
        public static final String APP_NAME = "appname";
        public static final String BACKUP_URL = "backup_url";
        public static final String FORCE_CHECK_UPDATE = "forceCheckUpdate";
        public static final String FORCE_LOAD_AFTER_UPDATE_CHECK = "forceLoadAfterUpdateCheck";
        public static final String MODULE_NAME = "modulename";
        public static final String PARAM = "param";
        public static final String PROGRESSBAR_SIZE = "progressbar_size";
        public static final String PROGRESSBAR_SIZE_HEIGHT = "progressbar_size_height";
        public static final String PROGRESSBAR_SIZE_WIDTH = "progressbar_size_width";
    }

    /* loaded from: classes5.dex */
    public interface NetType {
        public static final String NET_2G = "2g";
        public static final String NET_3G = "3g";
        public static final String NET_4G = "4g";
        public static final String NET_WIFI = "wifi";
        public static final String UNKNOWN = "UNKNOWN";
    }
}
