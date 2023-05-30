package com.jingdong.common.jump;

import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.app.NotificationCompat;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.R;
import com.jingdong.common.ad.AdUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.SPUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.ImageCompressUtils;
import com.jingdong.common.utils.JdWebViewFunctionUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.manto.sdk.api.IRequestPayment;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class OpenAppJumpController {
    public static final String COME_FROM = "floor";
    public static final int DEFAULT_NUM = 1;
    public static final String FROM_M_DESTINATION = "m_destination";
    private static final String HOST_KEPLER = "kepler";
    private static final String HOST_VIRTUAL = "virtual";
    public static String JDTHIRDLOGIN_THRIDPACKAGENAME = "";
    public static final String KEY_ACTION = "action";
    public static final String KEY_ACTIVITY_ID = "activityId";
    public static final String KEY_ACTIVITY_TYPE = "type";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_LAND_PAGE_ID = "landPageId";
    public static final String KEY_OPEN_LINK = "open_link_open_link_tag_";
    public static final String KEY_OPEN_LINK_PARAMS = "open_link_open_link_params_";
    public static final String KEY_PACKS_ID = "packsId";
    public static final String KEY_PACKS_NUM = "packsNum";
    public static final String KEY_PACKS_SKU = "packSku";
    public static final String KEY_PROMOTION_ID = "promotionId";
    public static final String KEY_SKULIST_ID = "skuList";
    public static final String KEY_SKU_ID = "skuId";
    public static final String KEY_SKU_NUM = "skuNum";
    public static final String KEY_SOURCE_WEBBZ = "from_webbz";
    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";
    public static final String KEY_WARELIST_ID = "wareList";
    public static final int MODULE_ID_ABOUT = 248;
    public static final int MODULE_ID_ACTIVITY = 103;
    public static final int MODULE_ID_AGGREGATE_PAGE = 218;
    public static final int MODULE_ID_AIREXORDER = 214;
    public static final int MODULE_ID_AIRINORDER = 213;
    public static final int MODULE_ID_AIRLINE = 124;
    public static final int MODULE_ID_AIRLINE_ORDER = 125;
    public static final int MODULE_ID_AIRLIST = 206;
    public static final int MODULE_ID_ALBUM_DETAIL = 294;
    public static final int MODULE_ID_ALBUM_LIST = 293;
    public static final int MODULE_ID_APPCENTER = 225;
    public static final int MODULE_ID_AR = 312;
    public static final int MODULE_ID_ARTICLE_DETAIL = 296;
    public static final int MODULE_ID_ASSISTANT_CHAT_BACKGROUND_SETTING = 320;
    public static final int MODULE_ID_BABEL = 245;
    public static final int MODULE_ID_BARCODE_PURCHASE = 7;
    public static final int MODULE_ID_CAMERA_PURCHASE = 251;
    public static final int MODULE_ID_CANCEL_PROGRESS = 315;
    public static final int MODULE_ID_CART = 102;
    public static final int MODULE_ID_CARTB = 123;
    public static final int MODULE_ID_CATEGORY = 13;
    public static final int MODULE_ID_COMMENT_DETAIL = 211;
    public static final int MODULE_ID_COMMENT_REPORT_DETAIL = 304;
    public static final int MODULE_ID_COMMUNE = 275;
    public static final int MODULE_ID_COUPON = 262;
    public static final int MODULE_ID_COUPON_CENTER = 129;
    public static final int MODULE_ID_DASH_MAIN = 254;
    public static final int MODULE_ID_DEFAULT_BROWSER = 246;
    public static final int MODULE_ID_DISCOVERY = 114;
    public static final int MODULE_ID_DM = 112;
    public static final int MODULE_ID_EVALUATE_CENTER = 258;
    public static final int MODULE_ID_EVALUATE_EDIT = 268;
    public static final int MODULE_ID_FAXIAN4EVENT = 128;
    public static final int MODULE_ID_FAXIAN_ARTICLE = 228;
    public static final int MODULE_ID_FAXIAN_AUTHOR = 227;
    public static final int MODULE_ID_FEEDBACK = 249;
    public static final int MODULE_ID_FILLORDER = 215;
    public static final int MODULE_ID_FIND_LIVE_LIST = 277;
    public static final int MODULE_ID_FIND_LIVE_PRE = 279;
    public static final int MODULE_ID_FSMUTISHOP = 306;
    public static final int MODULE_ID_GAME_CHONGZHI = 233;
    public static final int MODULE_ID_GENERIC_CHANNEL = 217;
    public static final int MODULE_ID_GENE_RECOM = 243;
    public static final int MODULE_ID_GET_COUPON = 105;
    public static final int MODULE_ID_GOODSTUFF = 255;
    public static final int MODULE_ID_GOODS_RECOMMEND = 127;
    public static final int MODULE_ID_GOOD_STUFF_PRODUCT = 265;
    public static final int MODULE_ID_GOOD_STUFF_TOPIC = 264;
    public static final int MODULE_ID_GO_CART = 109;
    public static final int MODULE_ID_GROUP_SHOPPING = 11;
    public static final int MODULE_ID_H5GAME = 240;
    public static final int MODULE_ID_HISTORYLIST = 250;
    public static final int MODULE_ID_HOME = 1;
    public static final int MODULE_ID_HOME_ICONS = 113;
    public static final int MODULE_ID_IM = 201;
    public static final int MODULE_ID_IM_O1 = 208;
    public static final int MODULE_ID_INDEX_GOODSHOP = 234;
    public static final int MODULE_ID_INTELLIGENT_ASSISTANT = 311;
    public static final int MODULE_ID_INTELLIGENT_ASSISTANT_CHAT_TIMBRE = 322;
    public static final int MODULE_ID_INTELLIGENT_ASSISTANT_MALL = 321;
    public static final int MODULE_ID_INTELLIGENT_ASSISTANT_SETTING = 323;
    public static final int MODULE_ID_INTELLIGENT_ASSISTANT_VOICE_REPORT_CONTROL = 324;
    public static final int MODULE_ID_JDAUTH = 216;
    public static final int MODULE_ID_JDGAME = 314;
    public static final int MODULE_ID_JDPAY_CODE = 318;
    public static final int MODULE_ID_JDREACT_COMMON = 263;
    public static final int MODULE_ID_JDREMINDER = 274;
    public static final int MODULE_ID_JDTHIRDLOGIN = 300;
    public static final int MODULE_ID_JIMI = 200;
    public static final int MODULE_ID_JSHOP = 110;
    public static final int MODULE_ID_JSHOP_BRAND_LIST = 204;
    public static final int MODULE_ID_JSHOP_DYNAMIC_DETAIL = 270;
    public static final int MODULE_ID_JSHOP_MEMBER = 284;
    public static final int MODULE_ID_JSHOP_PRODUCT_LIST = 313;
    public static final int MODULE_ID_JSHOP_SIGN = 310;
    public static final int MODULE_ID_JSHOP_SIGN_RANK = 247;
    public static final int MODULE_ID_LANDPAGE = 210;
    public static final int MODULE_ID_LIFE_CIRCLE = 230;
    public static final int MODULE_ID_LIFE_TRAVEL = 237;
    public static final int MODULE_ID_LIVE_ROOM = 281;
    public static final int MODULE_ID_LIVE_VERIFICATION = 301;
    public static final int MODULE_ID_LOGIN = 9;
    public static final int MODULE_ID_LOOK_SIMILAR = 269;
    public static final int MODULE_ID_LOTTERY = 12;
    public static final int MODULE_ID_M = 106;
    public static final int MODULE_ID_MAINFRAME = 100;
    public static final int MODULE_ID_MESSAGE = 2;
    public static final int MODULE_ID_MESSAGE_ACTION = 51;
    public static final int MODULE_ID_MESSAGE_LIST = 6;
    public static final int MODULE_ID_MIAOSHA = 8;
    public static final int MODULE_ID_MIAOSHA_BANNER = 276;
    public static final int MODULE_ID_MIAOSHA_BRAND = 236;
    public static final int MODULE_ID_MIAOSHA_LIANGFAN = 256;
    public static final int MODULE_ID_MIAOSHA_MYCONCERN = 267;
    public static final int MODULE_ID_MIAOSHA_NEWPRODUCT = 308;
    public static final int MODULE_ID_MOBILE_CHANNEL = 126;
    public static final int MODULE_ID_MORE = 252;
    public static final int MODULE_ID_MORESETTING = 307;
    public static final int MODULE_ID_MOVIE = 203;
    public static final int MODULE_ID_MYCOLLECT = 231;
    public static final int MODULE_ID_MYJD = 108;
    public static final int MODULE_ID_MYSTREET = 242;
    public static final int MODULE_ID_MYWALLET = 285;
    public static final int MODULE_ID_MY_DNA = 205;
    public static final int MODULE_ID_M_ACTION = 52;
    public static final int MODULE_ID_NEW_GOODSHOP = 244;
    public static final int MODULE_ID_NEW_RECHARGE = 286;
    public static final int MODULE_ID_NEW_THEMESTREET = 235;
    public static final int MODULE_ID_OFTEN_BUY = 287;
    public static final int MODULE_ID_ORDER_DETAIL = 229;
    public static final int MODULE_ID_ORDER_DETAIL_WEAR = 207;
    public static final int MODULE_ID_ORDER_INSTALL = 316;
    public static final int MODULE_ID_ORDER_LIST = 118;
    public static final int MODULE_ID_ORDER_TRACE = 119;
    public static final int MODULE_ID_ORDER_TRACK = 317;
    public static final int MODULE_ID_PHOTOBUY = 224;
    public static final int MODULE_ID_POP_IM = 202;
    public static final int MODULE_ID_PRODUCT = 4;
    public static final int MODULE_ID_PRODUCT_DETAIL = 101;
    public static final int MODULE_ID_PRODUCT_LIST = 115;
    public static final int MODULE_ID_PROMOTION = 107;
    public static final int MODULE_ID_PROMOTION_JUMP = 238;
    public static final int MODULE_ID_PROMOTION_NEW = 239;
    public static final int MODULE_ID_QQ_CHONGZHI = 232;
    public static final int MODULE_ID_RANKACTIVITY = 221;
    public static final int MODULE_ID_RANKLIST = 219;
    public static final int MODULE_ID_RANK_TOPIC = 253;
    public static final int MODULE_ID_REACTNATIVE_PAYSUCCESS = 241;
    public static final int MODULE_ID_REACT_VERSION = 305;
    public static final int MODULE_ID_RECHARGE = 10;
    public static final int MODULE_ID_RECODER = 303;
    public static final int MODULE_ID_SCAN = 226;
    public static final int MODULE_ID_SCAN_LOGIN = 1001;
    public static final int MODULE_ID_SEARCH = 3;
    public static final int MODULE_ID_SECKILL_LIVE_LIST = 278;
    public static final int MODULE_ID_SECKILL_LIVE_PRE = 280;
    public static final int MODULE_ID_SECK_KILL = 117;
    public static final int MODULE_ID_SHARE = 111;
    public static final int MODULE_ID_STOREREND = 223;
    public static final int MODULE_ID_STORY = 212;
    public static final int MODULE_ID_TEXT_CONTAINER = 104;
    public static final int MODULE_ID_TOKEN = 5;
    public static final int MODULE_ID_VIDEO_BUY = 309;
    public static final int MODULE_ID_WAITINGFOR_GOODS = 302;
    public static final int MODULE_ID_WORTHBUY_ALBUM = 292;
    public static final int MODULE_ID_WORTHBUY_AUTHOR = 297;
    public static final int MODULE_ID_WORTHBUY_DETAIL = 291;
    public static final int MODULE_ID_WORTHBUY_INVENTORY_DETAIL = 295;
    public static final int MODULE_ID_WORTHBUY_LIST = 290;
    public static final int MODULE_ID_WORTHBUY_TAG = 298;
    public static final int MODULE_ID_WU_LIU_CHA_XUN = 209;
    public static final int MODULE_ID_XIAOBING = 116;
    public static final int MODULE_ID_YIYUANQIANGBAO = 271;
    private static final String TAG = "OpenAppJumpController";
    private static final String TAG_OPENAPP = "Openapp-log";
    public static final String VALUE_ACTIVITY_CATEGORY_TYPE = "1";
    public static final String VALUE_ACTIVITY_HOME_TYPE = "2";
    public static String function;
    public static String keyword;
    public static String subunionId;
    public static Date timestamp;
    public static String type;
    public static String unionId;
    public static String usid;

    /* loaded from: classes5.dex */
    public static class Command {
        private static final String KEY_CATEGORY = "category";
        private static final String KEY_DES = "des";
        private static final String KEY_M_PARAM = "m_param";
        private static final String KEY_PARAMS = "params";
        private static final String KEY_SE = "SE";
        private static final String KEY_SI = "SI";
        private static final String KEY_SK = "kepler_param";
        private static final String VALUE_JUMP = "jump";
        private String des;
        private int moduleId;
        private Bundle outBundle;

        public Command(Uri uri) {
            this(null, uri, null);
        }

        private void outBundleToBundle(Bundle bundle, Bundle bundle2) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj instanceof String) {
                    bundle2.putString("param_" + str, (String) obj);
                } else if (obj instanceof Integer) {
                    bundle2.putInt("param_" + str, ((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    bundle2.putLong("param_" + str, ((Long) obj).longValue());
                }
            }
        }

        public String getDes() {
            return this.des;
        }

        public int getModuleId() {
            return this.moduleId;
        }

        public Bundle getOutBundle() {
            return this.outBundle;
        }

        public Bundle getTransformBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt("moduleId", this.moduleId);
            outBundleToBundle(this.outBundle, bundle);
            return bundle;
        }

        public Command(@Nullable Context context, Uri uri, Boolean bool) {
            String str;
            this.moduleId = 0;
            String str2 = "";
            this.des = "";
            this.outBundle = new Bundle();
            if (context != null) {
                str = "context: " + context.getClass().getSimpleName() + ",  ";
            } else {
                str = "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (bool != null) {
                str2 = "isFromInside: " + bool.toString() + ",  ";
            }
            sb.append(str2);
            String sb2 = sb.toString();
            if (JumpUtil.isJdScheme(uri.getScheme())) {
                String str3 = null;
                try {
                    str3 = uri.getQueryParameter("params");
                } catch (Exception e2) {
                    ExceptionReporter.reportGetQueryParameterException(uri, ExceptionReporter.getStackStringFromException(e2));
                    e2.printStackTrace();
                }
                if (OKLog.D) {
                    OKLog.d(OpenAppJumpController.TAG_OPENAPP, "params = " + str3);
                }
                if (TextUtils.isEmpty(str3)) {
                    ExceptionReporter.reportOpenAppJumpException("Openapp_EmptyParams", uri.toString(), sb2);
                    return;
                }
                str3 = str3.startsWith("\"") ? str3.substring(1) : str3;
                str3 = str3.endsWith("\"") ? str3.substring(0, str3.length() - 1) : str3;
                JSONObject string2JsonObject = JumpUtil.string2JsonObject(str3, uri);
                if ((string2JsonObject == null || string2JsonObject.length() < 1) && Build.VERSION.SDK_INT == 26) {
                    JDJSONObject string2JDJsonObject = JumpUtil.string2JDJsonObject(str3, uri);
                    if (string2JDJsonObject == null || string2JDJsonObject.isEmpty()) {
                        ExceptionReporter.reportOpenAppJumpException("Openapp_EmptyJDJsonObj", uri.toString(), sb2 + "params: " + str3);
                    }
                    string2JsonObject = JumpUtil.convertJDJsonToJson(string2JDJsonObject);
                }
                if (OKLog.D) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("json object = ");
                    sb3.append(string2JsonObject != null ? string2JsonObject.toString() : "(jsonObject is null)");
                    OKLog.d(OpenAppJumpController.TAG_OPENAPP, sb3.toString());
                }
                if (string2JsonObject != null && string2JsonObject.length() >= 1) {
                    JSONObjectProxy jSONObjectProxy = new JSONObjectProxy(string2JsonObject);
                    String optString = jSONObjectProxy.optString("category");
                    if (OKLog.D) {
                        OKLog.d(OpenAppJumpController.TAG, "handlerVirtualData category -->> " + optString);
                    }
                    if (TextUtils.isEmpty(optString)) {
                        ExceptionReporter.reportOpenAppJumpException("Openapp_EmptyCategory", uri.toString(), sb2);
                        return;
                    } else if (!"jump".equals(optString)) {
                        ExceptionReporter.reportOpenAppJumpException("Openapp_IllegalCategory", uri.toString(), sb2 + "category: " + optString);
                        return;
                    } else {
                        String optString2 = jSONObjectProxy.optString(KEY_SE);
                        String optString3 = jSONObjectProxy.optString(KEY_SI);
                        String optString4 = jSONObjectProxy.optString(KEY_SK);
                        String optString5 = jSONObjectProxy.optString("m_param");
                        if (OKLog.D) {
                            OKLog.d(OpenAppJumpController.TAG, "handlerVirtualData se -->> " + optString2);
                        }
                        if (!TextUtils.isEmpty(optString2) || !TextUtils.isEmpty(optString3) || !TextUtils.isEmpty(optString4) || !TextUtils.isEmpty(optString5)) {
                            AdvertUtils.initData(optString2, optString3, optString4, optString5);
                        }
                        this.des = jSONObjectProxy.optString("des");
                        if (OKLog.D) {
                            OKLog.d(OpenAppJumpController.TAG, "handlerVirtualData des -->> " + this.des);
                        }
                        if (TextUtils.isEmpty(this.des)) {
                            ExceptionReporter.reportOpenAppJumpException("Openapp_EmptyDes", uri.toString(), sb2);
                            return;
                        }
                        Bundle bundleFromJson = JumpUtil.getBundleFromJson(jSONObjectProxy);
                        this.outBundle = bundleFromJson;
                        bundleFromJson.putString("params", string2JsonObject.toString());
                        this.outBundle.putString("urlWithoutParam", OpenAppJumpController.getUrlWithoutParamQuery(uri));
                        if (OKLog.D) {
                            OKLog.d(OpenAppJumpController.TAG, " prepareCommandFromJson ---> bundle : " + this.outBundle + " , toString : F " + this.outBundle.toString());
                        }
                        if (TextUtils.isEmpty(this.outBundle.getString("keplerID"))) {
                            return;
                        }
                        if (OKLog.D) {
                            OKLog.d(OpenAppJumpController.TAG, "createCommand kepler command outBundle -->> " + this.outBundle);
                        }
                        KeplerJumpUtils.initData(this.outBundle);
                        this.outBundle.putBoolean(KeplerJumpUtils.KEY_IS_JUMP_FROM, true);
                        return;
                    }
                }
                ExceptionReporter.reportOpenAppJumpException("Openapp_EmptyJsonObj", uri.toString(), sb2 + "params: " + str3);
            }
        }

        public Command(int i2, Bundle bundle) {
            this.moduleId = 0;
            this.des = "";
            this.outBundle = new Bundle();
            this.moduleId = i2;
            this.outBundle = bundle;
        }

        public Command(String str, Bundle bundle) {
            this.moduleId = 0;
            this.des = "";
            this.outBundle = new Bundle();
            this.des = str;
            this.outBundle = bundle;
        }
    }

    private static void buildVirtualDataParams(Intent intent, Command command) {
        if (command == null || command.getOutBundle() == null) {
            return;
        }
        String str = "";
        String string = (intent == null || intent.getExtras() == null) ? "" : intent.getExtras().getString(MBaseKeyNames.KEY_REFERER);
        String string2 = (intent == null || intent.getExtras() == null) ? "" : intent.getExtras().getString(OpenAppConstant.FLAG_UserActivity);
        if (intent != null && intent.getExtras() != null) {
            str = intent.getExtras().getString("openAppActivityReferer");
        }
        if (!TextUtils.isEmpty(string)) {
            command.getOutBundle().putString(MBaseKeyNames.KEY_REFERER, string);
        }
        if (!TextUtils.isEmpty(string2)) {
            command.getOutBundle().putString(OpenAppConstant.FLAG_UserActivity, string2);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        command.getOutBundle().putString("openAppActivityReferer", str);
    }

    public static void cps() {
        cps(null, false);
    }

    public static Command createCommand(Intent intent) {
        return createCommand(null, intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:720:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:724:0x0723  */
    /* JADX WARN: Removed duplicated region for block: B:726:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x0731  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x0742  */
    /* JADX WARN: Removed duplicated region for block: B:804:0x08e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Command createOtherCommand(Uri uri, boolean z, JSONObject jSONObject) {
        String str;
        Bundle bundle;
        String str2;
        String str3;
        String str4;
        Uri uri2;
        String queryParameter;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String str16;
        String str17;
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        String str25;
        String str26;
        String str27;
        String str28;
        String str29;
        String str30;
        String str31;
        String str32;
        String str33;
        String str34;
        String str35;
        String str36;
        String str37;
        String str38;
        String str39;
        String str40;
        String str41;
        String str42;
        String str43;
        String str44;
        String str45;
        String str46;
        String str47;
        String str48;
        Exception exc;
        String str49;
        String str50;
        String str51;
        String str52;
        String str53;
        String str54;
        String str55;
        String str56;
        String str57;
        String str58;
        String str59;
        String str60;
        String str61;
        String str62;
        String str63;
        String str64;
        String str65;
        String str66;
        String str67;
        JSONObjectProxy jSONObjectProxy;
        String optString;
        HashMap hashMap;
        String str68;
        String jSONObject2;
        String str69;
        String str70;
        Bundle bundle2 = new Bundle();
        try {
            str = uri.getQueryParameter("params");
        } catch (Exception unused) {
            str = "{}";
        }
        JSONObject string2JsonObject = JumpUtil.string2JsonObject(str, uri);
        String str71 = "iconUrl";
        String str72 = str;
        String str73 = null;
        if (string2JsonObject != null) {
            try {
                jSONObjectProxy = new JSONObjectProxy(string2JsonObject);
                try {
                    String optString2 = jSONObjectProxy.optString(JshopConst.JSHOP_M_PARAM, "");
                    if (TextUtils.isEmpty(optString2)) {
                        str67 = "";
                    } else {
                        try {
                            str67 = "";
                            try {
                                JDMtaUtils.setMtaContent4OpenApp(JdSdk.getInstance().getApplication(), optString2, z);
                                AdvertUtils.initFlt(optString2);
                            } catch (Exception e2) {
                                e = e2;
                                exc = e;
                                str41 = Configuration.UNION_ID;
                                bundle = bundle2;
                                str42 = "shareUrl";
                                str3 = "1";
                                str43 = "landpageUrl";
                                str44 = "num";
                                str45 = "action";
                                str46 = "shareActionType";
                                str47 = "channel";
                                str48 = "from";
                                str49 = null;
                                str50 = null;
                                str51 = null;
                                str52 = null;
                                str53 = null;
                                str54 = null;
                                str55 = null;
                                str58 = null;
                                str59 = null;
                                str60 = null;
                                str61 = null;
                                str62 = null;
                                str63 = null;
                                str64 = null;
                                str65 = null;
                                str66 = null;
                                str6 = "token";
                                str71 = "iconUrl";
                                str2 = str67;
                                str56 = null;
                                str57 = null;
                                OKLog.e(TAG, exc);
                                queryParameter = str50;
                                str28 = str51;
                                str11 = str71;
                                str17 = str53;
                                str18 = str54;
                                str19 = str55;
                                str14 = str73;
                                str21 = str56;
                                str22 = str57;
                                str23 = str58;
                                str24 = str67;
                                str25 = str60;
                                str4 = str41;
                                str27 = str62;
                                str29 = str64;
                                str20 = str2;
                                str30 = str65;
                                str31 = str66;
                                str9 = str44;
                                uri2 = uri;
                                str32 = str42;
                                str12 = str48;
                                str33 = "content";
                                str34 = str59;
                                str35 = str61;
                                str15 = str49;
                                str26 = str52;
                                str8 = str47;
                                str5 = str46;
                                str7 = str45;
                                str10 = str43;
                                str16 = str63;
                                if (OKLog.D) {
                                }
                                Bundle bundle3 = bundle;
                                if (str16 != null) {
                                }
                                if (str34 != null) {
                                }
                                if (!TextUtils.isEmpty(str31)) {
                                }
                                if (!TextUtils.isEmpty(str40)) {
                                }
                                return new Command(str40, bundle3);
                            }
                        } catch (Exception e3) {
                            e = e3;
                            str67 = "";
                        }
                    }
                    try {
                        optString = jSONObjectProxy.optString("ext");
                        hashMap = new HashMap();
                    } catch (Exception e4) {
                        e = e4;
                        str41 = Configuration.UNION_ID;
                        bundle = bundle2;
                        str42 = "shareUrl";
                        str3 = "1";
                        str43 = "landpageUrl";
                        str44 = "num";
                        str45 = "action";
                        str46 = "shareActionType";
                        str47 = "channel";
                        str48 = "from";
                        str6 = "token";
                        str71 = "iconUrl";
                    }
                } catch (Exception e5) {
                    e = e5;
                    str41 = Configuration.UNION_ID;
                    bundle = bundle2;
                    str2 = "";
                    str42 = "shareUrl";
                    str3 = "1";
                    str43 = "landpageUrl";
                    str44 = "num";
                    str45 = "action";
                    str46 = "shareActionType";
                    str47 = "channel";
                    str48 = "from";
                    str6 = "token";
                    str71 = "iconUrl";
                    exc = e;
                    str49 = null;
                    str50 = null;
                    str51 = null;
                    str52 = null;
                    str53 = null;
                    str54 = null;
                    str55 = null;
                    str56 = null;
                    str57 = null;
                    str58 = null;
                    str59 = null;
                    str60 = null;
                    str61 = null;
                    str62 = null;
                    str63 = null;
                    str64 = null;
                    str65 = null;
                    str66 = null;
                    str67 = str2;
                    OKLog.e(TAG, exc);
                    queryParameter = str50;
                    str28 = str51;
                    str11 = str71;
                    str17 = str53;
                    str18 = str54;
                    str19 = str55;
                    str14 = str73;
                    str21 = str56;
                    str22 = str57;
                    str23 = str58;
                    str24 = str67;
                    str25 = str60;
                    str4 = str41;
                    str27 = str62;
                    str29 = str64;
                    str20 = str2;
                    str30 = str65;
                    str31 = str66;
                    str9 = str44;
                    uri2 = uri;
                    str32 = str42;
                    str12 = str48;
                    str33 = "content";
                    str34 = str59;
                    str35 = str61;
                    str15 = str49;
                    str26 = str52;
                    str8 = str47;
                    str5 = str46;
                    str7 = str45;
                    str10 = str43;
                    str16 = str63;
                    if (OKLog.D) {
                    }
                    Bundle bundle32 = bundle;
                    if (str16 != null) {
                    }
                    if (str34 != null) {
                    }
                    if (!TextUtils.isEmpty(str31)) {
                    }
                    if (!TextUtils.isEmpty(str40)) {
                    }
                    return new Command(str40, bundle32);
                }
                try {
                    hashMap.put("extension_id", optString);
                    Application application = JdSdk.getInstance().getApplication();
                    String jSONObject3 = string2JsonObject.toString();
                    str68 = TAG;
                    String str74 = z ? "1" : "0";
                    if (jSONObject != null) {
                        try {
                            jSONObject2 = jSONObject.toString();
                        } catch (Exception e6) {
                            exc = e6;
                            str41 = Configuration.UNION_ID;
                            bundle = bundle2;
                            str3 = "1";
                            str43 = "landpageUrl";
                            str44 = "num";
                            str45 = "action";
                            str46 = "shareActionType";
                            str47 = "channel";
                            str48 = "from";
                            str49 = null;
                            str50 = null;
                            str51 = null;
                            str52 = null;
                            str53 = null;
                            str54 = null;
                            str55 = null;
                            str59 = null;
                            str60 = null;
                            str61 = null;
                            str62 = null;
                            str63 = null;
                            str64 = null;
                            str65 = null;
                            str66 = null;
                            str6 = "token";
                            str71 = "iconUrl";
                            str42 = "shareUrl";
                            str2 = str67;
                            str56 = null;
                            str57 = null;
                            str58 = null;
                            OKLog.e(TAG, exc);
                            queryParameter = str50;
                            str28 = str51;
                            str11 = str71;
                            str17 = str53;
                            str18 = str54;
                            str19 = str55;
                            str14 = str73;
                            str21 = str56;
                            str22 = str57;
                            str23 = str58;
                            str24 = str67;
                            str25 = str60;
                            str4 = str41;
                            str27 = str62;
                            str29 = str64;
                            str20 = str2;
                            str30 = str65;
                            str31 = str66;
                            str9 = str44;
                            uri2 = uri;
                            str32 = str42;
                            str12 = str48;
                            str33 = "content";
                            str34 = str59;
                            str35 = str61;
                            str15 = str49;
                            str26 = str52;
                            str8 = str47;
                            str5 = str46;
                            str7 = str45;
                            str10 = str43;
                            str16 = str63;
                            if (OKLog.D) {
                            }
                            Bundle bundle322 = bundle;
                            if (str16 != null) {
                            }
                            if (str34 != null) {
                            }
                            if (!TextUtils.isEmpty(str31)) {
                            }
                            if (!TextUtils.isEmpty(str40)) {
                            }
                            return new Command(str40, bundle322);
                        }
                    } else {
                        jSONObject2 = str67;
                    }
                    JDMtaUtils.sendClickDataWithExt(application, "Startup_OpenAppParam_Status", jSONObject3, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, str68, str74, LangUtils.SINGLE_SPACE, jSONObject2, hashMap);
                    if (!z) {
                        JDMtaUtils.setSourceData(jSONObjectProxy.optString("sourceType"), jSONObjectProxy.optString("sourceValue"));
                        bundle2.putString("isFromOpenApp", "1");
                    }
                    String optString3 = jSONObjectProxy.optString("type");
                    try {
                        String optString4 = jSONObjectProxy.optString("keyword");
                        try {
                            String optString5 = jSONObjectProxy.optString(Configuration.UNION_ID);
                            try {
                                str59 = jSONObjectProxy.optString(Configuration.SUB_UNION_ID);
                                try {
                                    str60 = jSONObjectProxy.optString("payId");
                                    str41 = Configuration.UNION_ID;
                                    try {
                                        str50 = jSONObjectProxy.optString("tokenKey");
                                        try {
                                            str61 = jSONObjectProxy.optString("title");
                                        } catch (Exception e7) {
                                            bundle = bundle2;
                                            str69 = optString3;
                                            str70 = optString4;
                                            str63 = optString5;
                                            str3 = "1";
                                            str43 = "landpageUrl";
                                            str44 = "num";
                                            str45 = "action";
                                            str46 = "shareActionType";
                                            str47 = "channel";
                                            str48 = "from";
                                            str6 = "token";
                                            str71 = "iconUrl";
                                            str42 = "shareUrl";
                                            str2 = str67;
                                            exc = e7;
                                            str51 = null;
                                            str52 = str51;
                                            str53 = str52;
                                            str54 = str53;
                                            str55 = str54;
                                            str56 = str55;
                                            str57 = str56;
                                            str58 = str57;
                                            str61 = str58;
                                            str62 = str61;
                                            str64 = str62;
                                            str65 = str64;
                                            str66 = str65;
                                            str73 = str69;
                                            str49 = str70;
                                            OKLog.e(TAG, exc);
                                            queryParameter = str50;
                                            str28 = str51;
                                            str11 = str71;
                                            str17 = str53;
                                            str18 = str54;
                                            str19 = str55;
                                            str14 = str73;
                                            str21 = str56;
                                            str22 = str57;
                                            str23 = str58;
                                            str24 = str67;
                                            str25 = str60;
                                            str4 = str41;
                                            str27 = str62;
                                            str29 = str64;
                                            str20 = str2;
                                            str30 = str65;
                                            str31 = str66;
                                            str9 = str44;
                                            uri2 = uri;
                                            str32 = str42;
                                            str12 = str48;
                                            str33 = "content";
                                            str34 = str59;
                                            str35 = str61;
                                            str15 = str49;
                                            str26 = str52;
                                            str8 = str47;
                                            str5 = str46;
                                            str7 = str45;
                                            str10 = str43;
                                            str16 = str63;
                                            if (OKLog.D) {
                                            }
                                            Bundle bundle3222 = bundle;
                                            if (str16 != null) {
                                            }
                                            if (str34 != null) {
                                            }
                                            if (!TextUtils.isEmpty(str31)) {
                                            }
                                            if (!TextUtils.isEmpty(str40)) {
                                            }
                                            return new Command(str40, bundle3222);
                                        }
                                        try {
                                            str62 = jSONObjectProxy.optString("content");
                                            str69 = optString3;
                                            str42 = "shareUrl";
                                            try {
                                                str58 = jSONObjectProxy.optString(str42);
                                                str70 = optString4;
                                                str71 = "iconUrl";
                                                try {
                                                    str57 = jSONObjectProxy.optString(str71);
                                                    str63 = optString5;
                                                    str48 = "from";
                                                    try {
                                                        str55 = jSONObjectProxy.optString(str48);
                                                        str3 = "1";
                                                        try {
                                                            str54 = jSONObjectProxy.optString("channel");
                                                            bundle = bundle2;
                                                            str46 = "shareActionType";
                                                            str47 = "channel";
                                                            String str75 = str67;
                                                            try {
                                                                str67 = jSONObjectProxy.optString(str46, str75);
                                                                try {
                                                                    str64 = jSONObjectProxy.optString("status");
                                                                    str2 = str75;
                                                                    try {
                                                                        str56 = jSONObjectProxy.optString("token");
                                                                        try {
                                                                            str65 = jSONObjectProxy.optString("safe_token");
                                                                            try {
                                                                                str66 = jSONObjectProxy.optString("error_msg");
                                                                                str6 = "token";
                                                                                try {
                                                                                    str53 = jSONObjectProxy.optString("action");
                                                                                    str45 = "action";
                                                                                    try {
                                                                                        str52 = jSONObjectProxy.optString("num");
                                                                                        str44 = "num";
                                                                                        str43 = "landpageUrl";
                                                                                    } catch (Exception e8) {
                                                                                        str44 = "num";
                                                                                        str43 = "landpageUrl";
                                                                                        exc = e8;
                                                                                        str51 = null;
                                                                                        str52 = null;
                                                                                    }
                                                                                } catch (Exception e9) {
                                                                                    str45 = "action";
                                                                                    str43 = "landpageUrl";
                                                                                    str44 = "num";
                                                                                    exc = e9;
                                                                                    str51 = null;
                                                                                    str52 = null;
                                                                                    str53 = null;
                                                                                }
                                                                            } catch (Exception e10) {
                                                                                str6 = "token";
                                                                                str43 = "landpageUrl";
                                                                                str44 = "num";
                                                                                str45 = "action";
                                                                                exc = e10;
                                                                                str51 = null;
                                                                                str52 = null;
                                                                                str53 = null;
                                                                                str66 = null;
                                                                            }
                                                                        } catch (Exception e11) {
                                                                            str6 = "token";
                                                                            str43 = "landpageUrl";
                                                                            str44 = "num";
                                                                            str45 = "action";
                                                                            exc = e11;
                                                                            str51 = null;
                                                                            str52 = null;
                                                                            str53 = null;
                                                                            str65 = null;
                                                                            str66 = str65;
                                                                            str73 = str69;
                                                                            str49 = str70;
                                                                            OKLog.e(TAG, exc);
                                                                            queryParameter = str50;
                                                                            str28 = str51;
                                                                            str11 = str71;
                                                                            str17 = str53;
                                                                            str18 = str54;
                                                                            str19 = str55;
                                                                            str14 = str73;
                                                                            str21 = str56;
                                                                            str22 = str57;
                                                                            str23 = str58;
                                                                            str24 = str67;
                                                                            str25 = str60;
                                                                            str4 = str41;
                                                                            str27 = str62;
                                                                            str29 = str64;
                                                                            str20 = str2;
                                                                            str30 = str65;
                                                                            str31 = str66;
                                                                            str9 = str44;
                                                                            uri2 = uri;
                                                                            str32 = str42;
                                                                            str12 = str48;
                                                                            str33 = "content";
                                                                            str34 = str59;
                                                                            str35 = str61;
                                                                            str15 = str49;
                                                                            str26 = str52;
                                                                            str8 = str47;
                                                                            str5 = str46;
                                                                            str7 = str45;
                                                                            str10 = str43;
                                                                            str16 = str63;
                                                                            if (OKLog.D) {
                                                                            }
                                                                            Bundle bundle32222 = bundle;
                                                                            if (str16 != null) {
                                                                            }
                                                                            if (str34 != null) {
                                                                            }
                                                                            if (!TextUtils.isEmpty(str31)) {
                                                                            }
                                                                            if (!TextUtils.isEmpty(str40)) {
                                                                            }
                                                                            return new Command(str40, bundle32222);
                                                                        }
                                                                    } catch (Exception e12) {
                                                                        str6 = "token";
                                                                        str43 = "landpageUrl";
                                                                        str44 = "num";
                                                                        str45 = "action";
                                                                        exc = e12;
                                                                        str51 = null;
                                                                        str52 = null;
                                                                        str53 = null;
                                                                        str56 = null;
                                                                        str65 = null;
                                                                    }
                                                                } catch (Exception e13) {
                                                                    str2 = str75;
                                                                    str43 = "landpageUrl";
                                                                    str44 = "num";
                                                                    str45 = "action";
                                                                    str6 = "token";
                                                                    exc = e13;
                                                                    str51 = null;
                                                                    str52 = null;
                                                                    str53 = null;
                                                                    str56 = null;
                                                                    str64 = str56;
                                                                    str65 = str64;
                                                                    str66 = str65;
                                                                    str73 = str69;
                                                                    str49 = str70;
                                                                    OKLog.e(TAG, exc);
                                                                    queryParameter = str50;
                                                                    str28 = str51;
                                                                    str11 = str71;
                                                                    str17 = str53;
                                                                    str18 = str54;
                                                                    str19 = str55;
                                                                    str14 = str73;
                                                                    str21 = str56;
                                                                    str22 = str57;
                                                                    str23 = str58;
                                                                    str24 = str67;
                                                                    str25 = str60;
                                                                    str4 = str41;
                                                                    str27 = str62;
                                                                    str29 = str64;
                                                                    str20 = str2;
                                                                    str30 = str65;
                                                                    str31 = str66;
                                                                    str9 = str44;
                                                                    uri2 = uri;
                                                                    str32 = str42;
                                                                    str12 = str48;
                                                                    str33 = "content";
                                                                    str34 = str59;
                                                                    str35 = str61;
                                                                    str15 = str49;
                                                                    str26 = str52;
                                                                    str8 = str47;
                                                                    str5 = str46;
                                                                    str7 = str45;
                                                                    str10 = str43;
                                                                    str16 = str63;
                                                                    if (OKLog.D) {
                                                                    }
                                                                    Bundle bundle322222 = bundle;
                                                                    if (str16 != null) {
                                                                    }
                                                                    if (str34 != null) {
                                                                    }
                                                                    if (!TextUtils.isEmpty(str31)) {
                                                                    }
                                                                    if (!TextUtils.isEmpty(str40)) {
                                                                    }
                                                                    return new Command(str40, bundle322222);
                                                                }
                                                            } catch (Exception e14) {
                                                                str2 = str75;
                                                                str43 = "landpageUrl";
                                                                str44 = "num";
                                                                str45 = "action";
                                                                str6 = "token";
                                                                exc = e14;
                                                                str51 = null;
                                                                str52 = null;
                                                                str53 = null;
                                                                str56 = null;
                                                                str64 = null;
                                                                str65 = null;
                                                                str66 = null;
                                                                str67 = str2;
                                                            }
                                                        } catch (Exception e15) {
                                                            bundle = bundle2;
                                                            str44 = "num";
                                                            str45 = "action";
                                                            str46 = "shareActionType";
                                                            str6 = "token";
                                                            str2 = str67;
                                                            str47 = "channel";
                                                            str43 = "landpageUrl";
                                                            exc = e15;
                                                            str51 = null;
                                                            str52 = null;
                                                            str53 = null;
                                                            str54 = null;
                                                            str56 = null;
                                                        }
                                                    } catch (Exception e16) {
                                                        bundle = bundle2;
                                                        str3 = "1";
                                                        str43 = "landpageUrl";
                                                        str44 = "num";
                                                        str45 = "action";
                                                        str46 = "shareActionType";
                                                        str47 = "channel";
                                                        str6 = "token";
                                                        str2 = str67;
                                                        exc = e16;
                                                        str51 = null;
                                                        str52 = null;
                                                        str53 = null;
                                                        str54 = null;
                                                        str55 = null;
                                                        str56 = null;
                                                    }
                                                } catch (Exception e17) {
                                                    bundle = bundle2;
                                                    str63 = optString5;
                                                    str3 = "1";
                                                    str43 = "landpageUrl";
                                                    str44 = "num";
                                                    str45 = "action";
                                                    str46 = "shareActionType";
                                                    str47 = "channel";
                                                    str48 = "from";
                                                    str6 = "token";
                                                    str2 = str67;
                                                    exc = e17;
                                                    str51 = null;
                                                    str52 = null;
                                                    str53 = null;
                                                    str54 = null;
                                                    str55 = null;
                                                    str56 = null;
                                                    str57 = null;
                                                    str64 = null;
                                                }
                                            } catch (Exception e18) {
                                                bundle = bundle2;
                                                str70 = optString4;
                                                str63 = optString5;
                                                str3 = "1";
                                                str43 = "landpageUrl";
                                                str44 = "num";
                                                str45 = "action";
                                                str46 = "shareActionType";
                                                str47 = "channel";
                                                str48 = "from";
                                                str6 = "token";
                                                str71 = "iconUrl";
                                                str2 = str67;
                                                exc = e18;
                                                str51 = null;
                                                str52 = null;
                                                str53 = null;
                                                str54 = null;
                                                str55 = null;
                                                str56 = null;
                                                str57 = null;
                                                str58 = null;
                                                str64 = null;
                                            }
                                        } catch (Exception e19) {
                                            bundle = bundle2;
                                            str69 = optString3;
                                            str70 = optString4;
                                            str63 = optString5;
                                            str3 = "1";
                                            str43 = "landpageUrl";
                                            str44 = "num";
                                            str45 = "action";
                                            str46 = "shareActionType";
                                            str47 = "channel";
                                            str48 = "from";
                                            str6 = "token";
                                            str71 = "iconUrl";
                                            str42 = "shareUrl";
                                            str2 = str67;
                                            exc = e19;
                                            str51 = null;
                                            str52 = null;
                                            str53 = null;
                                            str54 = null;
                                            str55 = null;
                                            str56 = null;
                                            str57 = null;
                                            str58 = null;
                                            str62 = null;
                                            str64 = str62;
                                            str65 = str64;
                                            str66 = str65;
                                            str73 = str69;
                                            str49 = str70;
                                            OKLog.e(TAG, exc);
                                            queryParameter = str50;
                                            str28 = str51;
                                            str11 = str71;
                                            str17 = str53;
                                            str18 = str54;
                                            str19 = str55;
                                            str14 = str73;
                                            str21 = str56;
                                            str22 = str57;
                                            str23 = str58;
                                            str24 = str67;
                                            str25 = str60;
                                            str4 = str41;
                                            str27 = str62;
                                            str29 = str64;
                                            str20 = str2;
                                            str30 = str65;
                                            str31 = str66;
                                            str9 = str44;
                                            uri2 = uri;
                                            str32 = str42;
                                            str12 = str48;
                                            str33 = "content";
                                            str34 = str59;
                                            str35 = str61;
                                            str15 = str49;
                                            str26 = str52;
                                            str8 = str47;
                                            str5 = str46;
                                            str7 = str45;
                                            str10 = str43;
                                            str16 = str63;
                                            if (OKLog.D) {
                                            }
                                            Bundle bundle3222222 = bundle;
                                            if (str16 != null) {
                                            }
                                            if (str34 != null) {
                                            }
                                            if (!TextUtils.isEmpty(str31)) {
                                            }
                                            if (!TextUtils.isEmpty(str40)) {
                                            }
                                            return new Command(str40, bundle3222222);
                                        }
                                    } catch (Exception e20) {
                                        bundle = bundle2;
                                        str69 = optString3;
                                        str70 = optString4;
                                        str63 = optString5;
                                        str3 = "1";
                                        str43 = "landpageUrl";
                                        str44 = "num";
                                        str45 = "action";
                                        str46 = "shareActionType";
                                        str47 = "channel";
                                        str48 = "from";
                                        str6 = "token";
                                        str71 = "iconUrl";
                                        str42 = "shareUrl";
                                        str2 = str67;
                                        exc = e20;
                                        str50 = null;
                                        str51 = null;
                                    }
                                } catch (Exception e21) {
                                    str41 = Configuration.UNION_ID;
                                    bundle = bundle2;
                                    str69 = optString3;
                                    str70 = optString4;
                                    str63 = optString5;
                                    str3 = "1";
                                    str43 = "landpageUrl";
                                    str44 = "num";
                                    str45 = "action";
                                    str46 = "shareActionType";
                                    str47 = "channel";
                                    str48 = "from";
                                    str6 = "token";
                                    str71 = "iconUrl";
                                    str42 = "shareUrl";
                                    str2 = str67;
                                    exc = e21;
                                    str50 = null;
                                    str51 = null;
                                    str52 = null;
                                    str53 = null;
                                    str54 = null;
                                    str55 = null;
                                    str56 = null;
                                    str57 = null;
                                    str58 = null;
                                    str60 = null;
                                    str61 = str60;
                                    str62 = str61;
                                    str64 = str62;
                                    str65 = str64;
                                    str66 = str65;
                                    str73 = str69;
                                    str49 = str70;
                                    OKLog.e(TAG, exc);
                                    queryParameter = str50;
                                    str28 = str51;
                                    str11 = str71;
                                    str17 = str53;
                                    str18 = str54;
                                    str19 = str55;
                                    str14 = str73;
                                    str21 = str56;
                                    str22 = str57;
                                    str23 = str58;
                                    str24 = str67;
                                    str25 = str60;
                                    str4 = str41;
                                    str27 = str62;
                                    str29 = str64;
                                    str20 = str2;
                                    str30 = str65;
                                    str31 = str66;
                                    str9 = str44;
                                    uri2 = uri;
                                    str32 = str42;
                                    str12 = str48;
                                    str33 = "content";
                                    str34 = str59;
                                    str35 = str61;
                                    str15 = str49;
                                    str26 = str52;
                                    str8 = str47;
                                    str5 = str46;
                                    str7 = str45;
                                    str10 = str43;
                                    str16 = str63;
                                    if (OKLog.D) {
                                    }
                                    Bundle bundle32222222 = bundle;
                                    if (str16 != null) {
                                    }
                                    if (str34 != null) {
                                    }
                                    if (!TextUtils.isEmpty(str31)) {
                                    }
                                    if (!TextUtils.isEmpty(str40)) {
                                    }
                                    return new Command(str40, bundle32222222);
                                }
                            } catch (Exception e22) {
                                str41 = Configuration.UNION_ID;
                                bundle = bundle2;
                                str69 = optString3;
                                str70 = optString4;
                                str63 = optString5;
                                str3 = "1";
                                str43 = "landpageUrl";
                                str44 = "num";
                                str45 = "action";
                                str46 = "shareActionType";
                                str47 = "channel";
                                str48 = "from";
                                str6 = "token";
                                str71 = "iconUrl";
                                str42 = "shareUrl";
                                str2 = str67;
                                exc = e22;
                                str50 = null;
                                str51 = null;
                                str52 = null;
                                str53 = null;
                                str54 = null;
                                str55 = null;
                                str56 = null;
                                str57 = null;
                                str58 = null;
                                str59 = null;
                                str60 = null;
                            }
                        } catch (Exception e23) {
                            str41 = Configuration.UNION_ID;
                            bundle = bundle2;
                            str69 = optString3;
                            str70 = optString4;
                            str3 = "1";
                            str43 = "landpageUrl";
                            str44 = "num";
                            str45 = "action";
                            str46 = "shareActionType";
                            str47 = "channel";
                            str48 = "from";
                            str6 = "token";
                            str71 = "iconUrl";
                            str42 = "shareUrl";
                            str2 = str67;
                            exc = e23;
                            str50 = null;
                            str51 = null;
                            str52 = null;
                            str53 = null;
                            str54 = null;
                            str55 = null;
                            str56 = null;
                            str57 = null;
                            str58 = null;
                            str59 = null;
                            str60 = null;
                            str61 = null;
                            str62 = null;
                            str63 = null;
                            str64 = null;
                        }
                    } catch (Exception e24) {
                        str41 = Configuration.UNION_ID;
                        bundle = bundle2;
                        str3 = "1";
                        str43 = "landpageUrl";
                        str44 = "num";
                        str45 = "action";
                        str46 = "shareActionType";
                        str47 = "channel";
                        str48 = "from";
                        str6 = "token";
                        str71 = "iconUrl";
                        str42 = "shareUrl";
                        str2 = str67;
                        exc = e24;
                        str50 = null;
                        str51 = null;
                        str52 = null;
                        str53 = null;
                        str54 = null;
                        str55 = null;
                        str56 = null;
                        str57 = null;
                        str58 = null;
                        str59 = null;
                        str60 = null;
                        str61 = null;
                        str62 = null;
                        str63 = null;
                        str64 = null;
                        str65 = null;
                        str66 = null;
                        str73 = optString3;
                        str49 = null;
                    }
                } catch (Exception e25) {
                    e = e25;
                    str41 = Configuration.UNION_ID;
                    bundle = bundle2;
                    str3 = "1";
                    str43 = "landpageUrl";
                    str44 = "num";
                    str45 = "action";
                    str46 = "shareActionType";
                    str47 = "channel";
                    str48 = "from";
                    str6 = "token";
                    str71 = "iconUrl";
                    str42 = "shareUrl";
                    str2 = str67;
                    exc = e;
                    str49 = null;
                    str50 = null;
                    str51 = null;
                    str52 = null;
                    str53 = null;
                    str54 = null;
                    str55 = null;
                    str56 = null;
                    str57 = null;
                    str58 = null;
                    str59 = null;
                    str60 = null;
                    str61 = null;
                    str62 = null;
                    str63 = null;
                    str64 = null;
                    str65 = null;
                    str66 = null;
                    OKLog.e(TAG, exc);
                    queryParameter = str50;
                    str28 = str51;
                    str11 = str71;
                    str17 = str53;
                    str18 = str54;
                    str19 = str55;
                    str14 = str73;
                    str21 = str56;
                    str22 = str57;
                    str23 = str58;
                    str24 = str67;
                    str25 = str60;
                    str4 = str41;
                    str27 = str62;
                    str29 = str64;
                    str20 = str2;
                    str30 = str65;
                    str31 = str66;
                    str9 = str44;
                    uri2 = uri;
                    str32 = str42;
                    str12 = str48;
                    str33 = "content";
                    str34 = str59;
                    str35 = str61;
                    str15 = str49;
                    str26 = str52;
                    str8 = str47;
                    str5 = str46;
                    str7 = str45;
                    str10 = str43;
                    str16 = str63;
                    if (OKLog.D) {
                    }
                    Bundle bundle322222222 = bundle;
                    if (str16 != null) {
                    }
                    if (str34 != null) {
                    }
                    if (!TextUtils.isEmpty(str31)) {
                    }
                    if (!TextUtils.isEmpty(str40)) {
                    }
                    return new Command(str40, bundle322222222);
                }
            } catch (Exception e26) {
                e = e26;
                str41 = Configuration.UNION_ID;
                bundle = bundle2;
                str2 = "";
                str42 = "shareUrl";
                str3 = "1";
                str43 = "landpageUrl";
                str44 = "num";
                str45 = "action";
                str46 = "shareActionType";
                str47 = "channel";
                str48 = "from";
                str6 = "token";
            }
            try {
                str73 = jSONObjectProxy.optString(str43);
                if (OKLog.D) {
                    OKLog.d(str68, "BROADCAST HAVEN'T  PRAMES tokenKey" + str50);
                }
                queryParameter = str50;
                str11 = str71;
                str18 = str54;
                str19 = str55;
                str28 = str73;
                str22 = str57;
                str23 = str58;
                str24 = str67;
                str25 = str60;
                str4 = str41;
                str27 = str62;
                str29 = str64;
                str20 = str2;
                str30 = str65;
                str31 = str66;
                str9 = str44;
                uri2 = uri;
                str14 = str69;
                str32 = str42;
                str12 = str48;
                str26 = str52;
                str8 = str47;
                str34 = str59;
                str15 = str70;
                str5 = str46;
                str17 = str53;
                str21 = str56;
                str7 = str45;
                str33 = "content";
                str10 = str43;
                str35 = str61;
            } catch (Exception e27) {
                exc = e27;
                str51 = str73;
                str73 = str69;
                str49 = str70;
                OKLog.e(TAG, exc);
                queryParameter = str50;
                str28 = str51;
                str11 = str71;
                str17 = str53;
                str18 = str54;
                str19 = str55;
                str14 = str73;
                str21 = str56;
                str22 = str57;
                str23 = str58;
                str24 = str67;
                str25 = str60;
                str4 = str41;
                str27 = str62;
                str29 = str64;
                str20 = str2;
                str30 = str65;
                str31 = str66;
                str9 = str44;
                uri2 = uri;
                str32 = str42;
                str12 = str48;
                str33 = "content";
                str34 = str59;
                str35 = str61;
                str15 = str49;
                str26 = str52;
                str8 = str47;
                str5 = str46;
                str7 = str45;
                str10 = str43;
                str16 = str63;
                if (OKLog.D) {
                }
                Bundle bundle3222222222 = bundle;
                if (str16 != null) {
                }
                if (str34 != null) {
                }
                if (!TextUtils.isEmpty(str31)) {
                }
                if (!TextUtils.isEmpty(str40)) {
                }
                return new Command(str40, bundle3222222222);
            }
            str16 = str63;
        } else {
            bundle = bundle2;
            str2 = "";
            str3 = "1";
            str4 = Configuration.UNION_ID;
            uri2 = uri;
            try {
                String queryParameter2 = uri2.getQueryParameter("type");
                String queryParameter3 = uri2.getQueryParameter("keyword");
                String queryParameter4 = uri2.getQueryParameter(str4);
                String queryParameter5 = uri2.getQueryParameter(Configuration.SUB_UNION_ID);
                String queryParameter6 = uri2.getQueryParameter("payId");
                queryParameter = uri2.getQueryParameter("tokenKey");
                String queryParameter7 = uri2.getQueryParameter("title");
                String queryParameter8 = uri2.getQueryParameter("content");
                String queryParameter9 = uri2.getQueryParameter("shareUrl");
                String queryParameter10 = uri2.getQueryParameter("iconUrl");
                String queryParameter11 = uri2.getQueryParameter("from");
                String queryParameter12 = uri2.getQueryParameter("channel");
                String queryParameter13 = uri2.getQueryParameter("shareActionType");
                str5 = "shareActionType";
                String queryParameter14 = uri2.getQueryParameter("status");
                String queryParameter15 = uri2.getQueryParameter("token");
                str6 = "token";
                String queryParameter16 = uri2.getQueryParameter("safe_token");
                String queryParameter17 = uri2.getQueryParameter("error_msg");
                str7 = "action";
                String queryParameter18 = uri2.getQueryParameter(str7);
                str8 = "channel";
                str9 = "num";
                String queryParameter19 = uri2.getQueryParameter(str9);
                String queryParameter20 = uri2.getQueryParameter("landpageUrl");
                str10 = "landpageUrl";
                String queryParameter21 = uri2.getQueryParameter("key");
                if (OKLog.D) {
                    str13 = queryParameter21;
                    String str76 = TAG;
                    str12 = "from";
                    StringBuilder sb = new StringBuilder();
                    str11 = "iconUrl";
                    sb.append("BROADCAST  HAS PRAMES tokenKey");
                    sb.append(queryParameter);
                    OKLog.d(str76, sb.toString());
                } else {
                    str11 = "iconUrl";
                    str12 = "from";
                    str13 = queryParameter21;
                }
                str14 = queryParameter2;
                str15 = queryParameter3;
                str16 = queryParameter4;
                str17 = queryParameter18;
                str18 = queryParameter12;
                str19 = queryParameter11;
                str20 = str13;
                str21 = queryParameter15;
                str22 = queryParameter10;
                str23 = queryParameter9;
                str24 = queryParameter13;
                str25 = queryParameter6;
                str26 = queryParameter19;
                str27 = queryParameter8;
                str28 = queryParameter20;
                str29 = queryParameter14;
                str30 = queryParameter16;
                str31 = queryParameter17;
                str32 = "shareUrl";
                str33 = "content";
                str34 = queryParameter5;
                str35 = queryParameter7;
            } catch (Exception e28) {
                ExceptionReporter.reportGetQueryParameterException(uri2, e28.toString());
                e28.printStackTrace();
                return null;
            }
        }
        if (OKLog.D) {
            str37 = str35;
            String str77 = TAG;
            str38 = "title";
            StringBuilder sb2 = new StringBuilder();
            str36 = "keyword";
            sb2.append("data: ");
            sb2.append(uri2);
            OKLog.d(str77, sb2.toString());
            OKLog.d(str77, "inputParams: " + str72);
            OKLog.d(str77, "inputKeyword -->> " + str15);
            OKLog.d(str77, "inputType -->> " + str14);
        } else {
            str36 = "keyword";
            str37 = str35;
            str38 = "title";
        }
        Bundle bundle32222222222 = bundle;
        if (str16 != null) {
            bundle32222222222.putString(str4, str16);
        }
        if (str34 != null) {
            bundle32222222222.putString(Configuration.SUB_UNION_ID, str34);
        }
        if (!TextUtils.isEmpty(str31)) {
            ToastUtils.showToast(JdSdk.getInstance().getApplicationContext(), str31);
            str40 = str2;
        } else {
            if (!TextUtils.isEmpty(str30)) {
                bundle32222222222.putString("toMSM", "toMSM");
                bundle32222222222.putString("tokenKey", str30);
            } else {
                if (TextUtils.isEmpty(str21) || TextUtils.isEmpty(str29)) {
                    str39 = str29;
                } else {
                    str39 = str29;
                    if (str39.equals(DYConstants.DY_TRUE)) {
                        bundle32222222222.putString("isBind", IExceptionHandler.DynamicExceptionData.TYPE_BIND);
                        bundle32222222222.putString("tokenKey", str21);
                    }
                }
                if (!TextUtils.isEmpty(str17) && !TextUtils.isEmpty(str39) && str39.equals(DYConstants.DY_TRUE)) {
                    String str78 = str17;
                    if (str78.equals(LoginConstans.FREGMENT_LOGIN_FLAG)) {
                        bundle32222222222.putString(str7, LoginConstans.FREGMENT_LOGIN_FLAG);
                        if (OKLog.D) {
                            System.out.println("receive click to login +++++action:" + str78);
                        }
                    } else if (!TextUtils.isEmpty(str26) && str78.equals(NotificationCompat.CATEGORY_CALL)) {
                        bundle32222222222.putString(str7, NotificationCompat.CATEGORY_CALL);
                        bundle32222222222.putString(str9, str26);
                        if (OKLog.D) {
                            System.out.println("receive bind page contact customer +++++action:" + str78);
                        }
                    }
                } else if (!TextUtils.isEmpty(queryParameter)) {
                    bundle32222222222.putString("tokenKey", queryParameter);
                } else if (!TextUtils.isEmpty(str25)) {
                    bundle32222222222.putString("payId", str25);
                    bundle32222222222.putString("type", str14);
                } else if (str3.equals(str14)) {
                    str40 = JumpUtil.VALUE_DES_APPHOME;
                } else if ("2".equals(str14)) {
                    try {
                        bundle32222222222.putLong("id", Long.parseLong(str15));
                        bundle32222222222.putString("type", str14);
                        str40 = "productDetail";
                    } catch (NumberFormatException unused2) {
                        return new Command("productDetail", bundle32222222222);
                    }
                } else if ("3".equals(str14)) {
                    bundle32222222222.putString(str36, str15);
                    str40 = "search";
                } else if ("7".equals(str14)) {
                    str40 = JumpUtil.VALUE_DES_BARCODE_PURCHASE;
                } else if ("8".equals(str14)) {
                    str40 = "seckill";
                } else if ("9".equals(str14)) {
                    str40 = JumpUtil.VALUE_DES_JDLOGIN;
                } else if ("111".equals(str14)) {
                    bundle32222222222.putString(str38, str37);
                    bundle32222222222.putString(str33, str27);
                    bundle32222222222.putString(str32, str23);
                    bundle32222222222.putString(str11, str22);
                    bundle32222222222.putString(str12, str19);
                    bundle32222222222.putString(str8, str18);
                    bundle32222222222.putString(str5, str24);
                    str40 = "share";
                } else if ("126".equals(str14)) {
                    str40 = JumpUtil.VALUE_DES_PHONE_SALE;
                } else if ("1001".equals(str14)) {
                    bundle32222222222.putString("key", str20);
                    str40 = JumpUtil.VALUE_DES_SCAN_LOGIN;
                } else if (TextUtils.isEmpty(str28) || TextUtils.isEmpty(str28.trim())) {
                    str40 = JumpUtil.VALUE_DES_APPHOME;
                } else {
                    bundle32222222222.putString(str10, str28);
                    str40 = JumpUtil.VALUE_DES_WEB_LANDPAGE;
                }
            }
            str40 = str6;
        }
        if (!TextUtils.isEmpty(str40)) {
            type = str14;
            keyword = str15;
            unionId = str16;
            subunionId = str34;
            function = uri.getHost();
            timestamp = new Date();
            if (OKLog.D) {
                OKLog.d(TAG, "BROADCAST   getHost" + function);
            }
        }
        return new Command(str40, bundle32222222222);
    }

    private static boolean dealJdPayAction(Context context, Intent intent) {
        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            Uri data = intent.getData();
            if (data == null || !JumpUtil.isJdPayScheme(data.getScheme())) {
                return false;
            }
            try {
                JSONObject string2JsonObject = JumpUtil.string2JsonObject(data.getQueryParameter("params"), data);
                if (OKLog.D) {
                    OKLog.d(TAG, "dealJdPayAction ,jsonObject:" + string2JsonObject);
                }
                if (string2JsonObject != null) {
                    Bundle bundleFromJson = JumpUtil.getBundleFromJson(new JSONObjectProxy(string2JsonObject));
                    bundleFromJson.putString(KEY_SOURCE_WEBBZ, "jdPay");
                    bundleFromJson.putBoolean(MBaseKeyNames.KEY_FROM_THIRD_PAY, true);
                    if (OKLog.D) {
                        OKLog.d(TAG, "thirdPay bundle:" + bundleFromJson);
                    }
                    JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_WEB_BZ, baseActivity, bundleFromJson);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportGetQueryParameterException(data, e2.toString());
                e2.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public static Command dealJdPayOpenScheme(Uri uri) {
        try {
            JSONObject string2JsonObject = JumpUtil.string2JsonObject(uri.getQueryParameter("params"), uri);
            if (OKLog.D) {
                OKLog.d(TAG, "dealJdOpenPayScheme,jsonobject:" + string2JsonObject);
            }
            if (string2JsonObject != null) {
                Bundle bundleFromJson = JumpUtil.getBundleFromJson(new JSONObjectProxy(string2JsonObject));
                if (OKLog.D) {
                    OKLog.d(TAG, "dealJdOpenPayScheme,bundle:" + bundleFromJson);
                }
                String string = bundleFromJson.getString("openType");
                String string2 = bundleFromJson.getString("webUrl");
                if ("web".equals(string) && !TextUtils.isEmpty(string2)) {
                    bundleFromJson.putString("url", string2);
                    bundleFromJson.putBoolean(MBaseKeyNames.KEY_FROM_METRO_PAY, true);
                    return new Command("m", bundleFromJson);
                } else if (JumpUtil.VALUE_DES_QUICK_PASS.equals(string)) {
                    return new Command(JumpUtil.VALUE_DES_QUICK_PASS, bundleFromJson);
                }
            }
            return null;
        } catch (Exception e2) {
            ExceptionReporter.reportGetQueryParameterException(uri, e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    public static Command dealJdPayScheme(Uri uri) {
        try {
            JSONObject string2JsonObject = JumpUtil.string2JsonObject(uri.getQueryParameter("params"), uri);
            if (OKLog.D) {
                OKLog.d(TAG, "dealJdPayScheme,jsonobject:" + string2JsonObject);
            }
            if (string2JsonObject != null) {
                Bundle bundleFromJson = JumpUtil.getBundleFromJson(new JSONObjectProxy(string2JsonObject));
                if (OKLog.D) {
                    OKLog.d(TAG, "dealJdPayScheme,bundle:" + bundleFromJson);
                }
                if (TextUtils.isEmpty(bundleFromJson.getString("url"))) {
                    bundleFromJson.putString("url", String.format("https://jdpaycert.jd.com/jdpay/thirdAccess.action?orderId=%s&merchant=%s&signData=%s", bundleFromJson.getString("orderId"), bundleFromJson.getString(IRequestPayment.OUT_merchant), bundleFromJson.getString("signData")));
                }
                bundleFromJson.putBoolean(MBaseKeyNames.KEY_FROM_THIRD_PAY, true);
                if (OKLog.D) {
                    OKLog.d(TAG, "dealJdPayScheme,bundle:" + bundleFromJson);
                }
                return new Command("m", bundleFromJson);
            }
            return null;
        } catch (Exception e2) {
            ExceptionReporter.reportGetQueryParameterException(uri, e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    private static boolean dealSendAction(Intent intent) {
        final Uri uri;
        final String str;
        ClipData clipData;
        final Activity activity = (Activity) BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (activity != null && "android.intent.action.SEND".equals(intent.getAction())) {
            Uri uri2 = null;
            if (Build.VERSION.SDK_INT < 16 || (clipData = intent.getClipData()) == null || clipData.getItemCount() <= 0) {
                uri = null;
                str = null;
            } else {
                uri = clipData.getItemAt(0).getUri();
                str = JdWebViewFunctionUtil.getPathFromUri(activity, uri);
            }
            if (TextUtils.isEmpty(str)) {
                if (intent.getExtras() != null) {
                    try {
                        Uri uri3 = (Uri) intent.getExtras().get("android.intent.extra.STREAM");
                        OKLog.d(TAG, "get send PicPathUri:" + uri3);
                        uri2 = uri3;
                    } catch (Exception e2) {
                        OKLog.e(TAG, e2);
                    }
                } else {
                    uri2 = uri;
                }
                if (uri2 != null) {
                    str = JdWebViewFunctionUtil.getPathFromUri(activity, uri2);
                }
                uri = uri2;
            }
            if (!TextUtils.isEmpty(str)) {
                if (PermissionHelper.hasGrantedExternalStorage(activity, PermissionHelper.generateBundle("openapp", OpenAppJumpController.class.getSimpleName(), "dealSendAction"), new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.jump.OpenAppJumpController.2
                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onCanceled() {
                        activity.finish();
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onDenied() {
                        activity.finish();
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onGranted() {
                        OpenAppJumpController.sendImageDataToPhotoBuy(activity, str, uri);
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onIgnored() {
                    }

                    @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                    public void onOpenSetting() {
                    }
                })) {
                    sendImageDataToPhotoBuy(activity, str, uri);
                    return true;
                }
                return true;
            }
            ToastUtils.shortToast(activity, R.string.send_photobuy_fail);
        }
        return false;
    }

    public static void dispatchJumpRequest(Context context, Intent intent) {
        String str;
        Boolean bool;
        String str2;
        String str3;
        JDMtaUtils.onClick(context, TAG, context.getClass().getName());
        str = "";
        JDMtaUtils.sendCommonData(context, "Startup_OpenAppParam_Raw", intent != null ? intent.getDataString() : "", "", context.getClass().getName(), "", "", "");
        FireEyeUtils.reportFireEyeS(context, true);
        String str4 = null;
        if (intent != null) {
            bool = Boolean.valueOf(TextUtils.isEmpty(intent.getAction()) || intent.getBooleanExtra(OpenAppConstant.FLAG_INNERAPP, false));
        } else {
            bool = null;
        }
        if (intent != null && TextUtils.equals(intent.getStringExtra(OpenAppConstant.FLAG_UserActivity), OpenAppConstant.KEY_OuterApp)) {
            bool = Boolean.FALSE;
        }
        String str5 = "context: " + context.getClass().getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append(str5);
        if (bool != null) {
            str2 = ", isFromInside: " + bool.toString();
        } else {
            str2 = "";
        }
        sb.append(str2);
        String sb2 = sb.toString();
        if (OKLog.D) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("openapp url = ");
            sb3.append(intent != null ? intent.getData() : "(intent is null)");
            OKLog.d(TAG_OPENAPP, sb3.toString());
        }
        if (SwitchQueryFetcher.getSwitchBooleanValue("openAppActivityReferer", false) && (context instanceof Activity)) {
            OpenAppUtil.addRefererToIntent((Activity) context, intent);
        }
        if (intent != null && intent.getScheme() != null) {
            str4 = intent.getScheme();
        }
        if (str4 != null && str4.startsWith("http")) {
            ExceptionReporter.reportOpenAppJumpException("Openapp_HttpScheme", intent.getData() != null ? intent.getData().toString() : "", sb2);
            return;
        }
        if (!JumpUtil.isJdScheme(str4) && !JumpUtil.isJdPayScheme(str4) && !JumpUtil.isJdPayOpenScheme(str4)) {
            ExceptionReporter.reportOpenAppJumpException("Openapp_NotOpenappScheme", intent.getData() != null ? intent.getData().toString() : "", sb2);
        }
        JSONObject jSONObject = new JSONObject();
        boolean z = (bool != null && bool.booleanValue()) || OpenAppUtil.isJDReferer(context);
        if (z) {
            str3 = "3";
        } else {
            try {
                str3 = OpenLinkTimeManager.getInstance().isCold() ? "1" : "2";
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        jSONObject.put("coldOrHot", str3);
        jSONObject.put("openAppUrl", (intent == null || intent.getData() == null) ? "" : intent.getData().toString());
        jSONObject.put("sourceApp", OpenAppUtil.getReferer(context));
        Command createCommand = createCommand(context, intent, jSONObject);
        if (createCommand != null) {
            AdUtils.adReport(createCommand.getOutBundle().getString("params"));
            if (!z) {
                if (SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.LINK_TRACK_ENABLE, false)) {
                    createCommand.getOutBundle().putBoolean(KEY_OPEN_LINK, true);
                    OpenLinkTimeManager.getInstance().isOpenLink(createCommand.getOutBundle().getString("url"));
                }
                if (SwitchQueryFetcher.getSwitchBooleanValue("linkParamEnable", true)) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("coldOrHot", (Object) (OpenLinkTimeManager.getInstance().isCold() ? "1" : "2"));
                    jDJSONObject.put("sourceApp", (Object) OpenAppUtil.getReferer(context));
                    if (intent != null && intent.getData() != null) {
                        str = intent.getData().toString();
                    }
                    jDJSONObject.put("openAppUrl", (Object) str);
                    createCommand.getOutBundle().putInt(KEY_OPEN_LINK_PARAMS, OpenLinkTimeManager.getInstance().setOpenJsonParam(jDJSONObject));
                }
            }
        } else {
            ExceptionReporter.reportOpenAppJumpException("Openapp_NullCommand", intent.getData() != null ? intent.getData().toString() : "", sb2);
        }
        realHandle(context, intent, createCommand);
    }

    public static void dispatchJumpRequestInApp(Context context, Uri uri) {
        if (context == null) {
            return;
        }
        dispatchJumpRequest(context, new Intent().setData(uri));
    }

    @Nullable
    private static String getQueryParameterWithoutDecode(Uri uri, String str) {
        if (uri == null) {
            return null;
        }
        if (uri.isOpaque()) {
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        }
        if (str != null) {
            String encodedQuery = uri.getEncodedQuery();
            if (encodedQuery == null) {
                return null;
            }
            String encode = Uri.encode(str, null);
            int length = encodedQuery.length();
            int i2 = 0;
            while (true) {
                int indexOf = encodedQuery.indexOf(38, i2);
                int i3 = indexOf != -1 ? indexOf : length;
                int indexOf2 = encodedQuery.indexOf(61, i2);
                if (indexOf2 > i3 || indexOf2 == -1) {
                    indexOf2 = i3;
                }
                if (indexOf2 - i2 == encode.length() && encodedQuery.regionMatches(i2, encode, 0, encode.length())) {
                    return indexOf2 == i3 ? "" : encodedQuery.substring(indexOf2 + 1, i3);
                } else if (indexOf == -1) {
                    return null;
                } else {
                    i2 = indexOf + 1;
                }
            }
        } else {
            throw new NullPointerException("key");
        }
    }

    public static String getUrlWithoutParamQuery(Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            String queryParameterWithoutDecode = getQueryParameterWithoutDecode(uri, "params");
            String uri2 = uri.toString();
            if (!TextUtils.isEmpty(queryParameterWithoutDecode)) {
                uri2 = uri2.replace(queryParameterWithoutDecode, "");
            }
            if (OKLog.D) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("encodedParams --> ");
                if (queryParameterWithoutDecode == null) {
                    queryParameterWithoutDecode = DYConstants.DY_NULL_STR;
                }
                sb.append(queryParameterWithoutDecode);
                OKLog.d(str, sb.toString());
                OKLog.d(str, "urlWithoutParam --> " + uri2);
            }
            return uri2;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            ExceptionReporter.reportOpenAppJumpException("Openapp_ErrExcludeParams", uri.toString(), ExceptionReporter.getStackStringFromException(e2));
            return uri.toString();
        }
    }

    private static Command handlerVirtualData(@Nullable Context context, Uri uri, boolean z, JSONObject jSONObject) {
        Command command = new Command(context, uri, Boolean.valueOf(z));
        if (TextUtils.isEmpty(command.getDes())) {
            return null;
        }
        Bundle outBundle = command.getOutBundle();
        outBundle.putBoolean(WebWhiteScreenHolder.IS_FROM_M_INSIDE, z);
        if (!TextUtils.isEmpty(outBundle.getString(JshopConst.JSHOP_M_SOURCE_FROM))) {
            mMonitor(outBundle);
        }
        String string = outBundle.getString(JshopConst.JSHOP_M_PARAM);
        if (!TextUtils.isEmpty(string)) {
            JDMtaUtils.setMtaContent4OpenApp(JdSdk.getInstance().getApplication(), string, z);
            AdvertUtils.initFlt(string);
        }
        String string2 = outBundle.getString("ext");
        HashMap hashMap = new HashMap();
        hashMap.put("extension_id", string2);
        try {
            JSONObject jSONObject2 = new JSONObject(outBundle.getString("params"));
            String optString = jSONObject2.optString("ext");
            if (!TextUtils.isEmpty(optString)) {
                JSONObject jSONObject3 = new JSONObject(optString);
                jSONObject2.remove("ext");
                jSONObject2.put("ext", jSONObject3);
            }
            JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), "Startup_OpenAppParam_Status", jSONObject2.toString(), LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, TAG, z ? "1" : "0", LangUtils.SINGLE_SPACE, jSONObject != null ? jSONObject.toString() : "", hashMap);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (!z) {
            JDMtaUtils.setSourceData(outBundle.getString("sourceType"), outBundle.getString("sourceValue"));
            outBundle.putString("isFromOpenApp", "1");
        }
        return command;
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void mMonitor(Bundle bundle) {
        String str;
        String str2;
        String str3;
        JSONObjectProxy jSONObjectProxy;
        String str4 = "";
        String string = bundle.getString(JshopConst.JSHOP_M_SOURCE_FROM, "");
        String string2 = bundle.getString("kepler_param", "");
        try {
            jSONObjectProxy = new JSONObjectProxy(new JSONObject(bundle.getString(JshopConst.JSHOP_M_PARAM, "")));
            str = jSONObjectProxy.optString("jda");
        } catch (JSONException e2) {
            e = e2;
            str = "";
            str2 = str;
        }
        try {
            str2 = jSONObjectProxy.optString("jdv");
            try {
                str3 = jSONObjectProxy.optString("mba_muid");
            } catch (JSONException e3) {
                e = e3;
                str3 = "";
            }
        } catch (JSONException e4) {
            e = e4;
            str2 = "";
            str3 = str2;
            if (OKLog.E) {
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId("monitor");
            httpSetting.setHost(Configuration.getNgwHost());
            httpSetting.putJsonParam(JshopConst.JSHOP_M_SOURCE_FROM, string);
            httpSetting.putJsonParam("kepler_param", string2);
            httpSetting.putJsonParam("jda", str);
            httpSetting.putJsonParam("jdv", str2);
            httpSetting.putJsonParam("mba_muid", str3);
            httpSetting.putJsonParam("ref", str4);
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jump.OpenAppJumpController.4
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    if (OKLog.D) {
                        try {
                            String string3 = httpResponse.getJSONObject().getString("message");
                            OKLog.d(OpenAppJumpController.TAG, "message:" + string3);
                        } catch (JSONException e5) {
                            OKLog.e(OpenAppJumpController.TAG, e5);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
        try {
            str4 = jSONObjectProxy.optString("ref");
        } catch (JSONException e5) {
            e = e5;
            if (OKLog.E) {
                OKLog.e(TAG, e);
            }
            HttpSetting httpSetting2 = new HttpSetting();
            httpSetting2.setFunctionId("monitor");
            httpSetting2.setHost(Configuration.getNgwHost());
            httpSetting2.putJsonParam(JshopConst.JSHOP_M_SOURCE_FROM, string);
            httpSetting2.putJsonParam("kepler_param", string2);
            httpSetting2.putJsonParam("jda", str);
            httpSetting2.putJsonParam("jdv", str2);
            httpSetting2.putJsonParam("mba_muid", str3);
            httpSetting2.putJsonParam("ref", str4);
            httpSetting2.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jump.OpenAppJumpController.4
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    if (OKLog.D) {
                        try {
                            String string3 = httpResponse.getJSONObject().getString("message");
                            OKLog.d(OpenAppJumpController.TAG, "message:" + string3);
                        } catch (JSONException e52) {
                            OKLog.e(OpenAppJumpController.TAG, e52);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting2);
        }
        HttpSetting httpSetting22 = new HttpSetting();
        httpSetting22.setFunctionId("monitor");
        httpSetting22.setHost(Configuration.getNgwHost());
        httpSetting22.putJsonParam(JshopConst.JSHOP_M_SOURCE_FROM, string);
        httpSetting22.putJsonParam("kepler_param", string2);
        httpSetting22.putJsonParam("jda", str);
        httpSetting22.putJsonParam("jdv", str2);
        httpSetting22.putJsonParam("mba_muid", str3);
        httpSetting22.putJsonParam("ref", str4);
        httpSetting22.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jump.OpenAppJumpController.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (OKLog.D) {
                    try {
                        String string3 = httpResponse.getJSONObject().getString("message");
                        OKLog.d(OpenAppJumpController.TAG, "message:" + string3);
                    } catch (JSONException e52) {
                        OKLog.e(OpenAppJumpController.TAG, e52);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting22);
    }

    private static boolean openInappForwardProduct(Context context, Command command) {
        String str;
        String str2;
        Bundle transformBundle = command.getTransformBundle();
        String str3 = "";
        if (transformBundle != null) {
            str3 = transformBundle.getString("param_des");
            str = transformBundle.getString("param_landPageId");
            str2 = transformBundle.getString("param_type");
        } else {
            str = "";
            str2 = str;
        }
        if (!TextUtils.isEmpty(str3) && str3.equals("productDetail") && !TextUtils.isEmpty(str) && str.equals(Constants.JLOG_PRODUCT_QIHOO_PV_VALUE)) {
            if (OKLog.D) {
                OKLog.d(TAG, " openInappForwardProduct -->> productDetail ");
            }
            transformBundle.putString("id", transformBundle.getString("param_skuId"));
            if (TextUtils.isEmpty(str)) {
                str = "unknown";
            }
            transformBundle.putSerializable("source", new SourceEntity(str, null));
            DeeplinkProductDetailHelper.startProductDetailWithFlag(context, transformBundle, 268435456);
            return true;
        } else if (TextUtils.isEmpty(str2) || !str2.equals("2")) {
            return false;
        } else {
            cps();
            String string = transformBundle.getString("param_unionId");
            SourceEntity sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_OPEN_INTERFACE_CPS, string);
            if (OKLog.D) {
                OKLog.i(TAG, "InterfaceBroadcastReceiver onReceive() -->> unionId : " + string);
            }
            transformBundle.putLong("id", transformBundle.getLong("param_id"));
            transformBundle.putSerializable("source", sourceEntity);
            DeeplinkProductDetailHelper.startProductDetailWithFlag(context, transformBundle, 268435456);
            return true;
        }
    }

    private static void realHandle(Context context, Intent intent, Command command) {
        if (dealSendAction(intent) || dealJdPayAction(context, intent)) {
            return;
        }
        JDTHIRDLOGIN_THRIDPACKAGENAME = intent.getStringExtra("thirdAppPackage");
        if (OKLog.D) {
            OKLog.d("OpenAppJumpController thirdAppLogin  thirdAppPackage=", JDTHIRDLOGIN_THRIDPACKAGENAME);
        }
        if (command == null) {
            if ((context instanceof Activity) && context.getClass().getSimpleName().equals("InterfaceActivity")) {
                ((Activity) context).finish();
            }
        } else if (openInappForwardProduct(context, command)) {
            if (OKLog.D) {
                OKLog.d(TAG, " d-->> come  in  : ");
            }
        } else if (TextUtils.isEmpty(command.getDes())) {
            if ((context instanceof Activity) && context.getClass().getSimpleName().equals("InterfaceActivity")) {
                ((Activity) context).finish();
            }
        } else {
            toTargetPage(context, command);
        }
    }

    public static void requestBMode() {
        if (SwitchQueryFetcher.getSwitchBooleanValue("requestModeWithOffSite", true)) {
            JDBModeUtils.requestModeWithOffSite();
        }
    }

    public static void sendImageDataToPhotoBuy(final Activity activity, String str, Uri uri) {
        new AsyncTask<Pair<String, Uri>, Integer, ImageCompressUtils.TargetImageInfo>() { // from class: com.jingdong.common.jump.OpenAppJumpController.3
            long end;
            long start;

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                this.start = System.currentTimeMillis();
                OKLog.d(OpenAppJumpController.TAG, "start compress image:" + this.start);
            }

            @Override // android.os.AsyncTask
            public ImageCompressUtils.TargetImageInfo doInBackground(Pair<String, Uri>... pairArr) {
                Exception e2;
                ImageCompressUtils.TargetImageInfo targetImageInfo;
                ParcelFileDescriptor parcelFileDescriptor = null;
                String str2 = pairArr[0] != null ? (String) pairArr[0].first : null;
                Uri uri2 = pairArr[0] != null ? (Uri) pairArr[0].second : null;
                try {
                    targetImageInfo = ImageCompressUtils.compressImage(str2);
                    e2 = null;
                } catch (Exception e3) {
                    OKLog.e(OpenAppJumpController.TAG, e3);
                    e2 = e3;
                    targetImageInfo = null;
                }
                if (targetImageInfo == null && uri2 != null && activity != null && Build.VERSION.SDK_INT >= 24) {
                    ExceptionReporter.reportOpenAppJumpException("Openapp_SendImgToPhotoBuy_New", uri2.toString(), null);
                    try {
                        try {
                            try {
                                parcelFileDescriptor = activity.getContentResolver().openFileDescriptor(uri2, "r");
                                if (parcelFileDescriptor != null) {
                                    targetImageInfo = ImageCompressUtils.compressImage(parcelFileDescriptor.getFileDescriptor());
                                }
                            } catch (Exception e4) {
                                e2 = e4;
                                OKLog.e(OpenAppJumpController.TAG, e2);
                                if (parcelFileDescriptor != null) {
                                    parcelFileDescriptor.close();
                                }
                            }
                            if (parcelFileDescriptor != null) {
                                parcelFileDescriptor.close();
                            }
                        } catch (Throwable th) {
                            if (parcelFileDescriptor != null) {
                                try {
                                    parcelFileDescriptor.close();
                                } catch (IOException e5) {
                                    OKLog.e(OpenAppJumpController.TAG, e5);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e6) {
                        OKLog.e(OpenAppJumpController.TAG, e6);
                    }
                }
                if (targetImageInfo == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Uri = ");
                    sb.append(uri2 != null ? uri2.toString() : "");
                    sb.append(",  Path = ");
                    sb.append(str2);
                    ExceptionReporter.reportOpenAppJumpException("Openapp_SendImgToPhotoBuy_Err", sb.toString(), ExceptionReporter.getStackStringFromException(e2));
                }
                return targetImageInfo;
            }

            @Override // android.os.AsyncTask
            public void onPostExecute(ImageCompressUtils.TargetImageInfo targetImageInfo) {
                this.end = System.currentTimeMillis();
                OKLog.d(OpenAppJumpController.TAG, "compress image costs :" + (this.end - this.start));
                if (targetImageInfo != null && targetImageInfo.data != null) {
                    OKLog.d(OpenAppJumpController.TAG, "image size:" + targetImageInfo.data.length + "  byte");
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("bmpByte", targetImageInfo.data);
                    bundle.putBoolean("isAlbum", true);
                    bundle.putInt("srcType", 99);
                    bundle.putString("path", "3");
                    bundle.putString("mainBodyRectangle", "0,0|" + targetImageInfo.targetWidth + DYConstants.DY_REGEX_COMMA + targetImageInfo.targetHeight);
                    DeepLinkScanHelper.startPhotoBuyResultActivity(activity, bundle);
                    Activity activity2 = activity;
                    JDMtaUtils.onClick(activity2, "App_PhotoShareAppStartup", activity2.getClass().getSimpleName(), "", "");
                } else {
                    ToastUtils.shortToast(activity, R.string.send_photobuy_fail);
                }
                activity.finish();
            }
        }.execute(new Pair<>(str, uri));
    }

    public static void toTargetPage(Context context, Command command) {
        if (OKLog.D) {
            OKLog.d(TAG, "toTargetActivity -->> ");
        }
        cps();
        requestBMode();
        String des = command.getDes();
        Bundle outBundle = command.getOutBundle();
        if (OKLog.D) {
            String str = TAG;
            OKLog.d(str, "toTargetActivity des -->> " + des);
            if (outBundle != null) {
                OKLog.d(str, "bundle -->> " + outBundle);
                for (String str2 : outBundle.keySet()) {
                    Object obj = outBundle.get(str2);
                    OKLog.d(TAG, "bundle key value -->> " + str2 + "\uff1a" + obj);
                }
            }
        }
        JumpUtil.execJumpByDes(des, context, outBundle);
    }

    public static void cps(final Runnable runnable, boolean z) {
        if (unionId == null && subunionId == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d("Temp", "cps myActivity -->> getMainFrameActivity : " + BaseFrameUtil.getInstance().getMainFrameActivity());
        }
        HttpGroup.OnAllListener onAllListener = new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jump.OpenAppJumpController.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (OKLog.D) {
                    OKLog.d("Temp", "cps httpResponse.getString() -->> " + httpResponse.getString());
                }
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject != null) {
                    OpenAppJumpController.usid = jSONObject.optString("usid");
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(SourceEntity.SOURCE_TYPE_OPEN_INTERFACE_CPS);
        httpSetting.setHost(Configuration.getNgwHost());
        String str = type;
        if (str == null) {
            str = "";
        }
        httpSetting.putJsonParam("type", str);
        String str2 = keyword;
        if (str2 == null) {
            str2 = "";
        }
        httpSetting.putJsonParam("keyword", str2);
        httpSetting.putJsonParam(Configuration.UNION_ID, unionId);
        String str3 = subunionId;
        if (str3 == null) {
            str3 = "";
        }
        httpSetting.putJsonParam(Configuration.SUB_UNION_ID, str3);
        String str4 = function;
        httpSetting.putJsonParam("HandleOpenURL_FunctionID", str4 != null ? str4 : "");
        httpSetting.setListener(onAllListener);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static Command createCommand(@Nullable Context context, Intent intent) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("coldOrHot", "3");
            jSONObject.put("openAppUrl", (intent == null || intent.getData() == null) ? "" : intent.getData().toString());
            jSONObject.put("sourceApp", OpenAppUtil.getReferer(context));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return createCommand(null, intent, jSONObject);
    }

    public static Command createCommand(@Nullable Context context, Intent intent, JSONObject jSONObject) {
        String string;
        Uri data = intent != null ? intent.getData() : null;
        if (OKLog.D) {
            OKLog.d(TAG, "createCommand data -->> " + data);
        }
        if (data != null) {
            boolean z = TextUtils.equals(intent.getStringExtra(OpenAppConstant.FLAG_UserActivity), OpenAppConstant.KEY_OuterApp) ? false : TextUtils.isEmpty(intent.getAction()) || intent.getBooleanExtra(OpenAppConstant.FLAG_INNERAPP, false);
            SPUtil.handleTrackOrder(data);
            if (OKLog.D) {
                OKLog.d(TAG, "createCommand data.getHost() -->> " + data.getHost());
            }
            if ("virtual".equals(data.getHost())) {
                Command handlerVirtualData = handlerVirtualData(context, data, z, jSONObject);
                buildVirtualDataParams(intent, handlerVirtualData);
                return handlerVirtualData;
            } else if ("kepler".equals(data.getHost())) {
                Command handlerVirtualData2 = handlerVirtualData(context, data, z, jSONObject);
                if (OKLog.D) {
                    OKLog.d(TAG, "createCommand kepler command -->> " + handlerVirtualData2);
                }
                if (handlerVirtualData2 != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "createCommand kepler command outBundle -->> " + handlerVirtualData2.getOutBundle());
                    }
                    KeplerJumpUtils.initData(handlerVirtualData2.getOutBundle());
                    handlerVirtualData2.getOutBundle().putBoolean(KeplerJumpUtils.KEY_IS_JUMP_FROM, true);
                }
                return handlerVirtualData2;
            } else if (JumpUtil.isJdPayScheme(data.getScheme())) {
                return dealJdPayScheme(data);
            } else {
                if (JumpUtil.isJdPayOpenScheme(data.getScheme())) {
                    return dealJdPayOpenScheme(data);
                }
                return createOtherCommand(data, z, jSONObject);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(context != null ? "context: " + context.getClass().getSimpleName() + ",  " : "");
        sb.append((intent == null || intent.getExtras() == null) ? null : "intent: " + intent.getExtras().toString());
        ExceptionReporter.reportOpenAppJumpException("Openapp_NullData", null, sb.toString());
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                int i2 = extras.getInt("moduleId", 0);
                if (i2 != 0) {
                    string = JumpUtil.getDesFromModuleId(i2);
                } else {
                    string = extras.getString(LoginConstans.JUMP_DES, "");
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "createCommand des -->> " + string);
                }
                Bundle bundle = new Bundle();
                for (String str : extras.keySet()) {
                    if (str.startsWith("param_")) {
                        Object obj = extras.get(str);
                        String substring = str.substring(str.indexOf(CartConstant.KEY_YB_INFO_LINK) + 1);
                        if (obj instanceof String) {
                            bundle.putString(substring, (String) obj);
                        } else if (obj instanceof Integer) {
                            bundle.putInt(substring, ((Integer) obj).intValue());
                        } else if (obj instanceof Long) {
                            bundle.putLong(substring, ((Long) obj).longValue());
                        }
                    }
                }
                bundle.putAll(extras);
                if (!TextUtils.isEmpty(string)) {
                    return new Command(string, bundle);
                }
                Command command = (Command) extras.getSerializable("command");
                if (command != null) {
                    return command;
                }
            }
            return null;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }
}
