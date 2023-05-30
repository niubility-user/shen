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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isFirstComment() {
        /*
            java.lang.String r0 = com.jingdong.common.login.LoginUserBase.getUserPin()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto Lc
            return r2
        Lc:
            android.content.SharedPreferences r1 = com.jingdong.common.utils.CommonBase.getJdSharedPreferences()     // Catch: java.lang.Exception -> L48
            java.lang.String r0 = getDesKey(r0)     // Catch: java.lang.Exception -> L48
            r3 = 0
            java.lang.String r0 = r1.getString(r0, r3)     // Catch: java.lang.Exception -> L48
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L48
            if (r1 == 0) goto L20
            return r2
        L20:
            r1 = -1
            int r3 = r0.hashCode()     // Catch: java.lang.Exception -> L48
            r4 = 3569038(0x36758e, float:5.001287E-39)
            r5 = 1
            if (r3 == r4) goto L3b
            r4 = 97196323(0x5cb1923, float:1.9099262E-35)
            if (r3 == r4) goto L31
            goto L44
        L31:
            java.lang.String r3 = "false"
            boolean r0 = r0.equals(r3)     // Catch: java.lang.Exception -> L48
            if (r0 == 0) goto L44
            r1 = 1
            goto L44
        L3b:
            java.lang.String r3 = "true"
            boolean r0 = r0.equals(r3)     // Catch: java.lang.Exception -> L48
            if (r0 == 0) goto L44
            r1 = 0
        L44:
            if (r1 == 0) goto L47
            return r2
        L47:
            return r5
        L48:
            r0 = move-exception
            boolean r1 = com.jingdong.corelib.utils.Log.D
            if (r1 == 0) goto L67
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "isFirstComment: exception: "
            r1.append(r3)
            java.lang.String r0 = r0.getLocalizedMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "FirstCommentUtils"
            com.jingdong.corelib.utils.Log.d(r1, r0)
        L67:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.FirstCommentUtils.isFirstComment():boolean");
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
