package com.jingdong.jdsdk.auraSetting;

import android.app.Application;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import java.util.Set;

/* loaded from: classes14.dex */
public class AuraBundleInfos {
    public static final long AURA_MASK_SWITCH_TYPE_1 = 0;
    public static final long AURA_MASK_SWITCH_TYPE_1_EXTEND = 1152921504606846976L;
    public static final long AURA_MASK_SWITCH_TYPE_2 = 4611686018427387904L;
    public static final long AURA_MASK_SWITCH_TYPE_3 = 5764607523034234880L;
    public static final int BUNDLE_ID_ADDRESS = 29;
    public static final int BUNDLE_ID_ANDROIDPAY = 36;
    public static final int BUNDLE_ID_ARBOOK = 88;
    public static final int BUNDLE_ID_ARCLOTHES = 104;
    public static final int BUNDLE_ID_ARMAKEUP = 60;
    public static final int BUNDLE_ID_ARMART = 92;
    public static final int BUNDLE_ID_AVSDK = 85;
    public static final int BUNDLE_ID_BABEL = 90;
    public static final int BUNDLE_ID_BING = 14;
    public static final int BUNDLE_ID_BRANDSHANGOU = 93;
    public static final int BUNDLE_ID_BROWSERHISTORY = 24;
    public static final int BUNDLE_ID_CARD = 84;
    public static final int BUNDLE_ID_CART = 45;
    public static final int BUNDLE_ID_CASHIER = 96;
    public static final int BUNDLE_ID_CATEGORY = 26;
    public static final int BUNDLE_ID_CHARGE = 25;
    public static final int BUNDLE_ID_COMMUNE = 20;
    public static final int BUNDLE_ID_COMMUNITY = 65;
    public static final int BUNDLE_ID_COUPONCENTER = 11;
    public static final int BUNDLE_ID_CRONET = 107;
    public static final int BUNDLE_ID_DCEP = 113;
    public static final int BUNDLE_ID_DEVELOPERMODE = 61;
    public static final int BUNDLE_ID_DEVICE_MODEL_COMPUTE = 121;
    public static final int BUNDLE_ID_DISCOVERATTENTION = 95;
    public static final int BUNDLE_ID_DISCOVERY = 94;
    public static final int BUNDLE_ID_DOLPHIN = 97;
    public static final int BUNDLE_ID_EDGE = 126;
    public static final int BUNDLE_ID_ENJOYBUY = 74;
    public static final int BUNDLE_ID_EVALUATECENTER = 21;
    public static final int BUNDLE_ID_FACERECOGNITION = 38;
    public static final int BUNDLE_ID_FAIRY = 2;
    public static final int BUNDLE_ID_FAVOURITIES = 8;
    public static final int BUNDLE_ID_FOLLOW = 47;
    public static final int BUNDLE_ID_FROSTFIRE = 50;
    public static final int BUNDLE_ID_GOODSTUFF = 46;
    public static final int BUNDLE_ID_HEADSHOULDERS = 66;
    public static final int BUNDLE_ID_HOME = 91;
    public static final int BUNDLE_ID_HOURLYGO = 114;
    public static final int BUNDLE_ID_ICSSDK = 53;
    public static final int BUNDLE_ID_IDCARD_NFC_VERIFY = 122;
    public static final int BUNDLE_ID_IDCARD_OCR_VERIFY = 124;
    public static final int BUNDLE_ID_INCOMINGCALL = 63;
    public static final int BUNDLE_ID_INSIGHTAR = 87;
    public static final int BUNDLE_ID_INTELLIGENTASSISTANCE = 56;
    public static final int BUNDLE_ID_INTELLIGENTSDKEXTEND = 57;
    public static final int BUNDLE_ID_ISVHOURRECOMMEND = 117;
    public static final int BUNDLE_ID_ISVPDHOURSHOP = 116;
    public static final int BUNDLE_ID_ISVSTTLEMENT = 120;
    public static final int BUNDLE_ID_JDBUSINESSADDRESS = 102;
    public static final int BUNDLE_ID_JDCASHREWARD = 98;
    public static final int BUNDLE_ID_JDCUSTOMCHANNEL = 82;
    public static final int BUNDLE_ID_JDFAN = 80;
    public static final int BUNDLE_ID_JDFLUTTER = 86;
    public static final int BUNDLE_ID_JDFRIEND = 100;
    public static final int BUNDLE_ID_JDLIVELIST = 44;
    public static final int BUNDLE_ID_JDLIVELISTLIB = 106;
    public static final int BUNDLE_ID_JDMADE = 76;
    public static final int BUNDLE_ID_JDMIAOSHA = 35;
    public static final int BUNDLE_ID_JDPAYCODE = 51;
    public static final int BUNDLE_ID_JDPAYCOMMON = 52;
    public static final int BUNDLE_ID_JDPAYGENERAL = 67;
    public static final int BUNDLE_ID_JDPAYSDK = 10;
    public static final int BUNDLE_ID_JDPAYVERIFY = 75;
    public static final int BUNDLE_ID_JDPAYWITHHOLD = 78;
    public static final int BUNDLE_ID_JDSDK_EXTERNAL = 1;
    public static final int BUNDLE_ID_JSHOP = 16;
    public static final int BUNDLE_ID_JSHOPCUSTOM = 27;
    public static final int BUNDLE_ID_LINK = 43;
    public static final int BUNDLE_ID_LIVEVERIFICATION = 30;
    public static final int BUNDLE_ID_LOGIN = 41;
    public static final int BUNDLE_ID_MATRIXAR = 72;
    public static final int BUNDLE_ID_MEME = 64;
    public static final int BUNDLE_ID_MESSAGE = 79;
    public static final int BUNDLE_ID_MMONITOR = 123;
    public static final int BUNDLE_ID_MOCKLOCATION = 101;
    public static final int BUNDLE_ID_MYBANKCARD = 22;
    public static final int BUNDLE_ID_MYCALENDAR = 28;
    public static final int BUNDLE_ID_MYCARD = 73;
    public static final int BUNDLE_ID_MYCOUPON = 17;
    public static final int BUNDLE_ID_MYLIVE = 33;
    public static final int BUNDLE_ID_MYSTREET = 7;
    public static final int BUNDLE_ID_MYWALLET = 31;
    public static final int BUNDLE_ID_NETVIRTA = 112;
    public static final int BUNDLE_ID_NEWARRIVALS = 108;
    public static final int BUNDLE_ID_NEWCATEGORY = 125;
    public static final int BUNDLE_ID_NEWPRODUCT = 99;
    public static final int BUNDLE_ID_OMNICHANNELSEARCH = 110;
    public static final int BUNDLE_ID_OPENCV = 40;
    public static final int BUNDLE_ID_ORDERCENTER = 18;
    public static final int BUNDLE_ID_ORDERINFOCARD = 111;
    public static final int BUNDLE_ID_ORDER_UNIT = 115;
    public static final int BUNDLE_ID_PERSONAL = 58;
    public static final int BUNDLE_ID_PRODUCTDETAIL = 9;
    public static final int BUNDLE_ID_PRODUCTDETAILFEEDS = 109;
    public static final int BUNDLE_ID_PRODUCTDETAILMINI = 119;
    public static final int BUNDLE_ID_PUSH = 54;
    public static final int BUNDLE_ID_QUICKPASS = 49;
    public static final int BUNDLE_ID_RANK = 15;
    public static final int BUNDLE_ID_RANKINGLIST = 103;
    public static final int BUNDLE_ID_RISK_BANKCARD_OCR = 118;
    public static final int BUNDLE_ID_SCAN = 13;
    public static final int BUNDLE_ID_SEARCH = 12;
    public static final int BUNDLE_ID_SETTING = 39;
    public static final int BUNDLE_ID_SETTLEMENT = 55;
    public static final int BUNDLE_ID_SHAREORDER = 83;
    public static final int BUNDLE_ID_SHOPATTENTION = 81;
    public static final int BUNDLE_ID_SONICREDPACKET = 71;
    public static final int BUNDLE_ID_STORY = 6;
    public static final int BUNDLE_ID_THREEDPRODUCT = 48;
    public static final int BUNDLE_ID_THREEDTRYCLOTHES = 69;
    public static final int BUNDLE_ID_TOTAL = 0;
    public static final int BUNDLE_ID_TRYCLOTHES = 62;
    public static final int BUNDLE_ID_UNIFICATION = 68;
    public static final int BUNDLE_ID_UPHONE = 89;
    public static final int BUNDLE_ID_USERMANAGER = 34;
    public static final int BUNDLE_ID_VANGOPH = 70;
    public static final int BUNDLE_ID_VIRTUALHUMAN = 105;
    public static final int BUNDLE_ID_VOICE = 32;
    public static final int BUNDLE_ID_WORTHBUY = 19;
    private static d auraBundleInfos;

    public static String[] getAutoBundles() {
        return auraBundleInfos.a();
    }

    public static List<String> getBundleDownloadOrder() {
        return auraBundleInfos.getBundleDownloadOrder();
    }

    public static String getBundleNameFromBundleId(int i2) {
        return auraBundleInfos.getBundleNameFromBundleId(i2);
    }

    public static String getBundleNameFromSwitchMask(long j2) {
        return auraBundleInfos.g(j2);
    }

    public static String getBundleNameFromUpdateID(String str) {
        return auraBundleInfos.getBundleNameFromUpdateID(str);
    }

    public static long getSwitchDefaultValue(long j2) {
        return auraBundleInfos.h(j2);
    }

    public static long getSwitchMaskFromBundleId(int i2) {
        return auraBundleInfos.d(i2);
    }

    public static long getSwitchMaskFromBundleName(String str) {
        return auraBundleInfos.e(str);
    }

    public static long getSwitchMaxValue() {
        return auraBundleInfos.f();
    }

    public static long getSwitchMinValue() {
        return auraBundleInfos.b();
    }

    public static long getSwitchType(long j2) {
        return auraBundleInfos.c(j2);
    }

    public static Set<String> getUpdateIDKeySet() {
        return auraBundleInfos.i();
    }

    public static String getUpdateIdFromBundleName(String str) {
        return auraBundleInfos.getUpdateIdFromBundleName(str);
    }

    public static void init(String str, Application application) {
        if (application != null) {
            if (auraBundleInfos != null) {
                return;
            }
            if ("jingdong".equals(str)) {
                auraBundleInfos = c.j();
                return;
            } else if (JshopConst.JSKEY_IS_GLOBAL.equals(str)) {
                auraBundleInfos = a.j();
                return;
            } else {
                throw new IllegalArgumentException("not a valid clientId:" + str);
            }
        }
        throw new IllegalArgumentException("context is null !!");
    }
}
