package com.jingdong.common.network;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SignatureAlertController {
    public static void alertSignatureFailedDialog(Activity activity) {
        if (activity == null) {
            return;
        }
        final DialogController dialogController = new DialogController() { // from class: com.jingdong.common.network.SignatureAlertController.1
            @Override // com.jingdong.common.network.DialogController, android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 != -1) {
                    return;
                }
                JDHttpTookit.getEngine().getAppProxy().exitApp();
            }

            @Override // com.jingdong.common.network.DialogController, android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                return true;
            }
        };
        dialogController.setTitle("\u63d0\u793a");
        dialogController.setMessage("\u5f53\u524d\u8f6f\u4ef6\u4e0d\u53ef\u7528\uff0c\u8bf7\u81f3\u4eac\u4e1c\u5b98\u7f51\u4e0b\u8f7d\u5b89\u88c5\u6700\u65b0\u7248\u672c\u3002");
        dialogController.setCanceledOnTouchOutside(false);
        dialogController.setPositiveButton("\u786e\u5b9a");
        dialogController.init(activity);
        activity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.network.SignatureAlertController.2
            @Override // java.lang.Runnable
            public void run() {
                dialogController.show();
            }
        });
    }

    public static JSONObjectProxy handlerEncrypt(JSONObjectProxy jSONObjectProxy) throws UnsupportedEncodingException, JSONException {
        if (jSONObjectProxy == null) {
            return null;
        }
        String stringOrNull = jSONObjectProxy.getStringOrNull("encryptContent");
        if (TextUtils.isEmpty(stringOrNull)) {
            return jSONObjectProxy;
        }
        byte[] decodeFromJni = JDHttpTookit.getEngine().getSignatureHandlerImpl().decodeFromJni(toByteArray(stringOrNull));
        if (decodeFromJni == null) {
            Activity currentMyActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity();
            if (currentMyActivity != null) {
                alertSignatureFailedDialog(currentMyActivity);
            }
            return null;
        }
        return new JSONObjectProxy(new JSONObject(new String(decodeFromJni, "UTF-8")));
    }

    public static byte[] toByteArray(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            bArr[i2] = (byte) ((Character.digit(str.charAt(i3), 16) << 4) | Character.digit(str.charAt(i4), 16));
            i2++;
            i3 = i4 + 1;
        }
        return bArr;
    }

    public static JDJSONObject handlerEncrypt(JDJSONObject jDJSONObject) throws UnsupportedEncodingException, com.alibaba.fastjson.JSONException {
        if (jDJSONObject == null) {
            return null;
        }
        String optString = jDJSONObject.optString("encryptContent");
        if (TextUtils.isEmpty(optString)) {
            return jDJSONObject;
        }
        byte[] decodeFromJni = JDHttpTookit.getEngine().getSignatureHandlerImpl().decodeFromJni(toByteArray(optString));
        if (decodeFromJni == null) {
            Activity currentMyActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity();
            if (currentMyActivity != null) {
                alertSignatureFailedDialog(currentMyActivity);
            }
            return null;
        }
        return JDJSON.parseObject(new String(decodeFromJni, "UTF-8"));
    }
}
