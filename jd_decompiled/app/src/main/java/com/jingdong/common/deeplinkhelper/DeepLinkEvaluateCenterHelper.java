package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class DeepLinkEvaluateCenterHelper {
    public static final int ALL_EXCLUDE_AFTER_COMMENT = 0;
    public static final String EXTRA_CHECK_STATUS = "checkSkuStatus";
    public static final String EXTRA_COMMENT_SHOW_TYPE = "EXTRA_COMMENT_SHOW_TYPE";
    public static final int ONLY_AFTER_COMMENT = 2;
    public static final int ONLY_CAR = 4;
    public static final int ONLY_SERVICE = 3;
    public static final int ONLY_SHARE_PIC = 1;
    public static final String SHARE_ORDER_NEW_CENTER = "shareOrderNewCenter";

    public static boolean isJumpCommentFloor() {
        return true;
    }

    public static boolean isJumpEvaluateCenterMain() {
        String stringFromPreference = ConfigUtil.getStringFromPreference(SHARE_ORDER_NEW_CENTER, "0");
        if (Log.D) {
            Log.e(SHARE_ORDER_NEW_CENTER, stringFromPreference);
        }
        return TextUtils.equals("1", stringFromPreference);
    }

    public static void startCommentInputActivity(Context context, Bundle bundle) {
    }

    public static void startCommentListActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SHARE_ORDER_COMMENT_LIST, bundle);
    }

    public static void startCommentPhotoActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SHARE_ORDER_COMMENT_PHOTO, bundle);
    }

    public static void startCommentReportDetail(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_SHARE_ORDER_REPORT_DETAIL, bundle);
    }

    public static void startCommentSecondReplyActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_EVALUATE_SECOND_REPLY, bundle);
    }

    public static void startEvaluateAgain(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(EXTRA_CHECK_STATUS, "1");
            bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 2);
            DeepLinkCommonHelper.startActivityDirectNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_COMMENT_FLOOR, bundle, "");
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluateCenter(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            DeepLinkCommonHelper.startActivityDirectNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_CENTER_MAIN, bundle, "");
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluateCenterFromOpenApp(Context context, Bundle bundle) {
        if (bundle == null) {
            startEvaluateCenter(context, null);
            return;
        }
        String string = bundle.getString("commentType");
        if (!"1".equals(string) && !"3".equals(string) && !"4".equals(string)) {
            if ("2".equals(string)) {
                startEvaluateAgain(context, bundle);
                return;
            }
            String string2 = bundle.getString("jumpType", "1");
            Bundle bundle2 = new Bundle();
            bundle2.putString("fromCommentOfficer", bundle.getString("fromCommentOfficer", "0"));
            if ("2".equals(string2)) {
                bundle2.putInt("evaluate_center_tab_position", 2);
                startEvaluateCenter(context, bundle2);
                return;
            } else if ("3".equals(string2)) {
                bundle2.putInt("evaluate_center_tab_position", 4);
                startEvaluateCenter(context, bundle2);
                return;
            } else {
                startEvaluateCenter(context, bundle2);
                return;
            }
        }
        startEvaluateEdit(context, bundle);
    }

    public static void startEvaluateDetail(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_EVALUATE_DETAIL_ACTIVITY, bundle);
    }

    public static void startEvaluateDetailForResult(Context context, Bundle bundle, int i2) {
        if (context instanceof Activity) {
            DeepLinkCommonHelper.startActivityForResult((Activity) context, DeepLinkCommonHelper.HOST_EVALUATE_DETAIL_ACTIVITY, bundle, i2);
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluateEdit(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(EXTRA_CHECK_STATUS, "1");
            String string = bundle.getString("commentType");
            if (TextUtils.equals(string, "3")) {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 1);
            } else if (TextUtils.equals(string, "2")) {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 2);
            } else if (TextUtils.equals(string, "4")) {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 3);
            } else {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 0);
            }
            if (bundle.containsKey("requestCode")) {
                int i2 = 404;
                try {
                    i2 = bundle.getInt("requestCode", 404);
                } catch (Exception unused) {
                    if (OKLog.D) {
                        OKLog.e("requestCode", "requestCode\u4e0d\u662f\u6574\u6570");
                    }
                }
                DeepLinkCommonHelper.startActivityForResultNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_COMMENT_FLOOR, bundle, i2, "");
                return;
            }
            DeepLinkCommonHelper.startActivityDirectNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_COMMENT_FLOOR, bundle, "");
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluateEditForResult(Context context, Bundle bundle, int i2) {
        if (context instanceof IMyActivity) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString(EXTRA_CHECK_STATUS, "1");
            String string = bundle.getString("commentType");
            if (TextUtils.equals(string, "3")) {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 1);
            } else if (TextUtils.equals(string, "2")) {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 2);
            } else if (TextUtils.equals(string, "4")) {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 3);
            } else {
                bundle.putInt(EXTRA_COMMENT_SHOW_TYPE, 0);
            }
            DeepLinkCommonHelper.startActivityForResultNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_COMMENT_FLOOR, bundle, i2, "");
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluateUnite(Context context, Bundle bundle) {
        if (context instanceof IMyActivity) {
            DeepLinkCommonHelper.startActivityDirectNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_ACTIVITY, bundle, "");
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluateUniteForResult(Context context, Bundle bundle, int i2) {
        if (context instanceof IMyActivity) {
            DeepLinkCommonHelper.startActivityForResultNeedLogin((IMyActivity) context, DeepLinkCommonHelper.HOST_EVALUATE_ACTIVITY, bundle, i2, "");
        } else if (OKLog.D) {
            OKLog.e("DeepLinkEvaluateCenterHelper", "\u8df3\u8f6c\u8bc4\u4ef7\u6652\u5355\u9875\u9762\u5931\u8d25\uff0c\u8be6\u7ec6\u4fe1\u606f\u8bf7\u67e5\u770bDeepLinkEvaluateCenterHelper\u7c7b");
        }
    }

    public static void startEvaluationRegister(Context context, Bundle bundle) {
        String string = bundle.getString("url");
        boolean z = bundle.getBoolean("isTitleBarGone", true);
        URLParamMap uRLParamMap = new URLParamMap();
        if (!TextUtils.isEmpty(string)) {
            uRLParamMap.put(RemoteMessageConst.TO, string);
        }
        Bundle bundle2 = new Bundle();
        SerializableContainer serializableContainer = new SerializableContainer();
        serializableContainer.setMap(uRLParamMap);
        bundle2.putSerializable("urlParamMap", serializableContainer);
        bundle2.putString("urlAction", RemoteMessageConst.TO);
        bundle2.putBoolean("isTopBarGone", z);
        DeepLinkCommonHelper.startWebActivity(context, bundle2, true);
    }

    public static void startUgcContentActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, "shareOrderUgcContent", bundle);
    }

    public static void startEvaluateUnite(Context context, Bundle bundle, int i2, String str) {
        DeepLinkCommonHelper.startActivity(context, DeepLinkCommonHelper.HOST_EVALUATE_ACTIVITY, bundle, true, i2, true, str);
    }
}
