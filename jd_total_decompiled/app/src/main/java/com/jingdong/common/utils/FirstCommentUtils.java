package com.jingdong.common.utils;

import android.text.TextUtils;
import com.jingdong.common.entity.DesCommonUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class FirstCommentUtils {
    private static final String IS_FIRST_COMMENT_FLAG = "true";
    private static final String KEY_FIRST_COMMENT_USER_TAG = "personal_first_comment_user_";
    private static final String NOT_FIRST_COMMENT_FLAG = "false";
    private static final String TAG = "FirstCommentUtils";

    private static String getDesKey(String str) throws Exception {
        return KEY_FIRST_COMMENT_USER_TAG + DesCommonUtils.encryptThreeDESECB(str, DesCommonUtils.KEY_CART_SHARE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isFirstComment() {
        String userPin = LoginUserBase.getUserPin();
        if (TextUtils.isEmpty(userPin)) {
            return false;
        }
        try {
            String string = CommonBase.getJdSharedPreferences().getString(getDesKey(userPin), null);
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            char c2 = '\uffff';
            int hashCode = string.hashCode();
            if (hashCode != 3569038) {
                if (hashCode == 97196323 && string.equals("false")) {
                    c2 = 1;
                }
            } else if (string.equals("true")) {
                c2 = 0;
            }
            return true;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "isFirstComment: exception: " + e2.getLocalizedMessage());
            }
            return false;
        }
    }

    public static void putFirstCommentFlag(boolean z) {
        String userPin = LoginUserBase.getUserPin();
        if (TextUtils.isEmpty(userPin)) {
            return;
        }
        try {
            CommonBase.getJdSharedPreferences().edit().putString(getDesKey(userPin), z ? "true" : "false").apply();
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "putFirstCommentFlag exception: " + e2.getLocalizedMessage());
            }
        }
    }
}
