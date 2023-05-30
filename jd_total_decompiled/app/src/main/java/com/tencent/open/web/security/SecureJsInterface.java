package com.tencent.open.web.security;

import com.tencent.open.b;
import com.tencent.open.log.SLog;

/* loaded from: classes9.dex */
public class SecureJsInterface extends b.C0808b {
    public static boolean isPWDEdit;
    private String a;

    public void clearAllEdit() {
        SLog.i("openSDK_LOG.SecureJsInterface", "-->clear all edit.");
        try {
            JniInterface.clearAllPWD();
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->clear all edit exception: " + e2.getMessage());
            throw new RuntimeException(e2);
        }
    }

    public void curPosFromJS(String str) {
        int i2;
        SLog.d("openSDK_LOG.SecureJsInterface", "-->curPosFromJS: " + str);
        try {
            i2 = Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->curPosFromJS number format exception.", e2);
            i2 = -1;
        }
        if (i2 >= 0) {
            boolean z = a.f17714c;
            boolean z2 = a.b;
            if (z2) {
                if (Boolean.valueOf(JniInterface.BackSpaceChar(z2, i2)).booleanValue()) {
                    a.b = false;
                    return;
                }
                return;
            }
            String str2 = a.a;
            this.a = str2;
            JniInterface.insetTextToArray(i2, str2, str2.length());
            SLog.v("openSDK_LOG.SecureJsInterface", "curPosFromJS mKey: " + this.a);
            return;
        }
        throw new RuntimeException("position is illegal.");
    }

    @Override // com.tencent.open.b.C0808b
    public boolean customCallback() {
        return true;
    }

    public String getMD5FromNative() {
        SLog.i("openSDK_LOG.SecureJsInterface", "-->get md5 form native");
        try {
            String pWDKeyToMD5 = JniInterface.getPWDKeyToMD5(null);
            SLog.v("openSDK_LOG.SecureJsInterface", "-->getMD5FromNative, MD5= " + pWDKeyToMD5);
            return pWDKeyToMD5;
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->get md5 form native exception: " + e2.getMessage());
            throw new RuntimeException(e2);
        }
    }

    public void isPasswordEdit(String str) {
        int i2;
        SLog.i("openSDK_LOG.SecureJsInterface", "-->is pswd edit, flag: " + str);
        try {
            i2 = Integer.parseInt(str);
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.SecureJsInterface", "-->is pswd edit exception: " + e2.getMessage());
            i2 = -1;
        }
        if (i2 != 0 && i2 != 1) {
            throw new RuntimeException("is pswd edit flag is illegal.");
        }
        if (i2 == 0) {
            isPWDEdit = false;
        } else if (i2 == 1) {
            isPWDEdit = true;
        }
    }
}
