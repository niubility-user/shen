package com.jingdong.common.recommend.entity;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class RecommendType {
    public static final int TYPE_AFFORDABLE_PRODUCT = 37;
    public static final int TYPE_AFFORDABLE_PRODUCT_LIST = 88;
    public static final int TYPE_AUTO_PLAY = 26;
    public static final int TYPE_AdsShop = 20;
    public static final int TYPE_BANNER = 1000;
    public static final int TYPE_CHANNEL = 34;
    public static final int TYPE_CONTENT_MATERIAL = 24;
    public static final int TYPE_DYNAMIC_PRODUCT = 20000;
    public static final int TYPE_FESTIVAL_SHORT = 2002;
    public static final int TYPE_FESTIVAL_TALL = 2001;
    public static final int TYPE_HOME_CARD_ONE_CHANNEL_FLOOR = 2004;
    public static final int TYPE_HOME_CARD_TWO_CHANNEL_FLOOR = 2003;
    public static final int TYPE_HOME_DYNAMIC_VIDEO_WITH_ONE_CHANNEL = 2006;
    public static final int TYPE_HOME_DYNAMIC_VIDEO_WITH_TWO_CHANNEL = 2005;
    public static final int TYPE_HOME_LIVE_VIDEO = 2007;
    public static final int TYPE_HOME_TAB_ACTIVITY = 15;
    public static final int TYPE_HOME_TAB_PRODUCT = 16;
    public static final int TYPE_HOME_TAB_RANK = 19;
    public static final int TYPE_HOME_TAB_YARD = 23;
    public static final int TYPE_INTERACTION = 33;
    public static final int TYPE_INTERACTION_930 = 9302;
    public static final int TYPE_LIVE = 18;
    public static final int TYPE_MONETIZATION = 32;
    public static final int TYPE_NUM = 12;
    public static final int TYPE_PD_RECOMMEND_2_PRODUCT = 10002;
    public static final int TYPE_PD_RECOMMEND_COUPON = 1003;
    public static final int TYPE_PD_RECOMMEND_TITLE = 10001;
    public static final int TYPE_PRODUCT = 0;
    public static final int TYPE_QUESTIONNAIR = 999;
    public static final int TYPE_RECOMMEND_PRODUCT_B = 11440;
    public static final int TYPE_SCENE_LABEL = 31;
    public static final int TYPE_TEMPLATE_EIGHT = 1008;
    public static final int TYPE_TEMPLATE_EIGHTEEN = 1018;
    public static final int TYPE_TEMPLATE_ELEVEN = 1011;
    public static final int TYPE_TEMPLATE_FIFTEEN = 1015;
    public static final int TYPE_TEMPLATE_FIVE = 1005;
    public static final int TYPE_TEMPLATE_FOUR = 1004;
    public static final int TYPE_TEMPLATE_FOURTEEN = 1014;
    public static final int TYPE_TEMPLATE_LIVE = 1007;
    public static final int TYPE_TEMPLATE_NINE = 1009;
    public static final int TYPE_TEMPLATE_NINETEEN = 1019;
    public static final int TYPE_TEMPLATE_ONE = 1001;
    public static final int TYPE_TEMPLATE_SIX = 1006;
    public static final int TYPE_TEMPLATE_TEN = 1010;
    public static final int TYPE_TEMPLATE_THIRTEEN = 1013;
    public static final int TYPE_TEMPLATE_THREE = 10003;
    public static final int TYPE_TEMPLATE_TWELVE = 1012;
    public static final int TYPE_TEMPLATE_TWENTYTWO = 1022;
    public static final int TYPE_TEMPLATE_TWO = 1002;
    public static final int TYPE_UGC = 36;
    public static int dynamic_baseline = 20001;
    public static HashMap<String, Integer> dynamicTypeMap = new HashMap<>();
    public static HashMap<Integer, String> typeDynamicMap = new HashMap<>();
    public static ArrayList<Integer> recom_dynamic_types = new ArrayList<>();

    public static boolean isAggreationType(int i2) {
        if (i2 != 15 && i2 != 18 && i2 != 20 && i2 != 24 && i2 != 26 && i2 != 36 && i2 != 1022 && i2 != 9302 && i2 != 10003 && i2 != 1001 && i2 != 1002 && i2 != 1018 && i2 != 1019) {
            switch (i2) {
                case 31:
                case 32:
                case 33:
                case 34:
                    break;
                default:
                    switch (i2) {
                        case 1004:
                        case 1005:
                        case 1006:
                        case 1007:
                        case 1008:
                        case 1009:
                        case 1010:
                        case 1011:
                            break;
                        default:
                            switch (i2) {
                                case 1013:
                                case 1014:
                                case 1015:
                                    break;
                                default:
                                    return recom_dynamic_types.contains(Integer.valueOf(i2));
                            }
                    }
            }
        }
        return true;
    }

    public static boolean isProduct(int i2) {
        return i2 == 0 || i2 == 10002 || i2 == 20000;
    }
}
