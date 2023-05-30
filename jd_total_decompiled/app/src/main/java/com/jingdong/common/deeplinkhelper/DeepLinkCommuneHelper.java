package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkCommuneHelper {
    public static final String ANSWER_ID = "answerId";
    public static final String CALL_BACK = "callBack";
    public static final String COMMENT = "comment";
    public static final String COMMENT_ID = "commentId";
    public static final String COMMUNE_SOURCE_FROM = "commune_source_from";
    public static final String DETAILALT = "detailalt";
    public static final String DETAILLEV = "detaillev";
    public static final String DINGDANXQ = "dingdanxq";
    public static final String HOST_COMMUNE_LIST = "questionlist";
    private static final String ID = "id";
    public static final String MESSAGE = "message";
    public static final String MYJD = "myjd";
    public static final String MYQA = "myQA";
    public static final String PRODUCTDETAIL = "detail";
    public static final String PRODUCT_DES = "productDes";
    public static final String PRODUCT_ID = "productSku";
    public static final String PRODUCT_IMAGE = "productImage";
    public static final String QAPUSH = "QApush";
    public static final String QUESTION_ID = "questionID";
    public static final String SEARCH = "search";
    public static final String SOURCE_TYPE = "sourceType";
    public static final String SUB_DES = "subDes";
    public static final String TAB_TYPE = "tabType";
    private static final String TYPE = "type";
    public static final String hintText = "hintText";
    public static final String productId = "productId";
    public static final String HOST_COMMUNE_DETAIL = "questiondetail";
    public static final String HOST_COMMUNE_PUBLISH = "communeproductpublish";
    public static final String HOST_COMMUNE_USER_CENTER = "usercenter";
    public static final String HOST_COMMUNE_COMMENT_LIST = "communecommentlist";
    public static final String HOST_COMMUNE_REPLY_LIST = "communereplylist";
    public static final String HOST_COMMUNE_LABEL_DETAIL = "labeldetail";
    public static final String HOST_COMMUNE_PUBLISH_ANSWER = "publishanswer";
    public static final String HOST_COMMUNE_MEDICINE_LIST = "medicineList";
    public static final String[] HOSTs = {HOST_COMMUNE_DETAIL, HOST_COMMUNE_PUBLISH, HOST_COMMUNE_USER_CENTER, HOST_COMMUNE_COMMENT_LIST, HOST_COMMUNE_REPLY_LIST, HOST_COMMUNE_LABEL_DETAIL, HOST_COMMUNE_PUBLISH_ANSWER, HOST_COMMUNE_MEDICINE_LIST};

    private static Bundle convertBundleKey(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!TextUtils.isEmpty(bundle.getString("id"))) {
            bundle.putString(PRODUCT_ID, bundle.getString("id"));
        }
        if (!TextUtils.isEmpty(bundle.getString("productId"))) {
            bundle.putString(PRODUCT_ID, bundle.getString("productId"));
        }
        if (!TextUtils.isEmpty(bundle.getString(hintText))) {
            bundle.putString("message", bundle.getString(hintText));
        }
        return bundle;
    }

    private static boolean isSwitchOpen() {
        return DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(20));
    }

    public static void startCommuneCommentList(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_COMMENT_LIST).toString(), bundle);
        }
    }

    public static void startCommuneCommentListForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_COMMENT_LIST).toString(), bundle, i2);
        }
    }

    public static void startCommuneDetail(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_DETAIL).toString(), bundle);
        }
    }

    public static void startCommuneDetailForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_DETAIL).toString(), bundle, i2);
        }
    }

    public static void startCommuneLabelDetail(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_LABEL_DETAIL).toString(), bundle);
        }
    }

    public static void startCommuneLabelDetailForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_LABEL_DETAIL).toString(), bundle, i2);
        }
    }

    public static void startCommuneList(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            if (bundle != null) {
                String string = bundle.getString("id");
                if (!TextUtils.isEmpty(string)) {
                    bundle.putString(PRODUCT_ID, string);
                }
            }
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_LIST).toString(), bundle);
        }
    }

    public static void startCommuneListForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            if (bundle != null) {
                String string = bundle.getString("id");
                if (!TextUtils.isEmpty(string)) {
                    bundle.putString(PRODUCT_ID, string);
                }
            }
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_LIST).toString(), bundle, i2);
        }
    }

    public static boolean startCommunePage(Context context, Bundle bundle, String str) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), convertBundleKey(bundle));
            return true;
        }
        return false;
    }

    public static boolean startCommunePageForResult(Activity activity, Bundle bundle, String str, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(str).toString(), convertBundleKey(bundle), i2);
            return true;
        }
        return false;
    }

    public static void startCommunePublish(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_PUBLISH).toString(), bundle);
        }
    }

    public static void startCommunePublishForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_PUBLISH).toString(), bundle, i2);
        }
    }

    public static void startCommuneReplyList(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_REPLY_LIST).toString(), bundle);
        }
    }

    public static void startCommuneReplyListForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_REPLY_LIST).toString(), bundle, i2);
        }
    }

    public static void startCommuneUserCenter(Context context, Bundle bundle) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityDirect(context, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_USER_CENTER).toString(), bundle);
        }
    }

    public static void startCommuneUserCenterForResult(Activity activity, Bundle bundle, int i2) {
        if (isSwitchOpen()) {
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(HOST_COMMUNE_USER_CENTER).toString(), bundle, i2);
        }
    }
}
