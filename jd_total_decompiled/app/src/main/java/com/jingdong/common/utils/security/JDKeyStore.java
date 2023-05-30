package com.jingdong.common.utils.security;

import android.text.TextUtils;
import com.jingdong.common.R;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public final class JDKeyStore {
    public static final String KEY_TYPE_3DES = "TrippleDES";
    public static final String KEY_TYPE_AES = "AES";
    public static final String PwdDesP3 = "YWFhYWFhYTI=";
    public static final String TAG = "JDKeyStore";
    private static volatile JDKeyStore instance;
    private ConcurrentHashMap<String, String> keyMap = new ConcurrentHashMap<>();

    private JDKeyStore() {
    }

    public static synchronized JDKeyStore getInstance() {
        JDKeyStore jDKeyStore;
        synchronized (JDKeyStore.class) {
            if (instance == null) {
                synchronized (JDKeyStore.class) {
                    if (instance == null) {
                        instance = new JDKeyStore();
                    }
                }
            }
            jDKeyStore = instance;
        }
        return jDKeyStore;
    }

    public String generateKey(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (TextUtils.equals(str, KEY_TYPE_3DES)) {
            if (this.keyMap.containsKey(KEY_TYPE_3DES)) {
                return this.keyMap.get(KEY_TYPE_3DES);
            }
            String string = JdSdk.getInstance().getApplicationContext().getResources().getString(R.string.privateKeyDesP2);
            String str2 = null;
            try {
                str2 = new String(Base64.decode(PwdDesP3));
            } catch (IOException unused) {
            }
            if (OKLog.D) {
                OKLog.d(TAG, "key type : " + str + ", key1 = " + string + ", key2 = n@adm!n1, key3 = " + str2);
            }
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty("n@adm!n1") || TextUtils.isEmpty(str2)) {
                return "";
            }
            stringBuffer.append(string);
            stringBuffer.append("n@adm!n1");
            stringBuffer.append(str2);
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.length() != 24) {
                return "";
            }
            if (OKLog.D) {
                OKLog.d(TAG, "final key : " + stringBuffer2);
            }
            this.keyMap.put(KEY_TYPE_3DES, stringBuffer2);
            return stringBuffer2;
        }
        TextUtils.equals(str, KEY_TYPE_AES);
        return stringBuffer.toString();
    }
}
