package cn.com.union.fido.common;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import cn.com.union.fido.bean.asm.ASMRequest;
import cn.com.union.fido.bean.asm.AuthenticatorInfo;
import cn.com.union.fido.bean.asm.GetInfoOut;
import cn.com.union.fido.bean.asm.Request;
import cn.com.union.fido.service.AsmService;
import cn.com.union.fido.util.CryptoTools;
import cn.com.union.fido.util.StringTools;
import com.jdcn.fido.utils.FingerDeviceIdManger;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.List;

/* loaded from: classes.dex */
public class GlobalConfiguration {
    public static String SERI_NUM;
    public static List<AuthenticatorInfo> authenticators;

    public static void getAuthenticatorInfo(Context context) {
        AsmService asmService = new AsmService(context);
        ASMRequest aSMRequest = new ASMRequest();
        aSMRequest.requestType = Request.GetInfo;
        Object responseData = asmService.process(aSMRequest).getResponseData();
        if (responseData != null) {
            authenticators = ((GetInfoOut) responseData).Authenticators;
        }
    }

    public static void getSeriNumEID(Context context) {
        byte[] hash;
        String str = null;
        try {
            String orGenerateDeviceId = FingerDeviceIdManger.getOrGenerateDeviceId(context);
            if (!TextUtils.isEmpty(orGenerateDeviceId) && (hash = CryptoTools.hash(orGenerateDeviceId, "SHA256")) != null && hash.length > 0) {
                StringBuilder sb = new StringBuilder(hash.length * 2);
                for (byte b : hash) {
                    int i2 = b & 255;
                    if (i2 < 16) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(i2));
                }
                str = sb.toString();
            }
        } catch (Exception unused) {
        }
        SERI_NUM = str;
    }

    public static String getSeriNumFingerprint(Context context) {
        try {
            String androidId = BaseInfo.getAndroidId();
            String oSFingerprint = BaseInfo.getOSFingerprint();
            if (TextUtils.isEmpty(androidId) || TextUtils.isEmpty(oSFingerprint)) {
                return null;
            }
            byte[] hash = CryptoTools.hash(androidId + oSFingerprint, "SHA256");
            if (hash == null || hash.length <= 0) {
                return null;
            }
            StringBuilder sb = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                int i2 = b & 255;
                if (i2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getSeriNumSerial(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 26 || context.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
                String androidId = BaseInfo.getAndroidId();
                String hardwareSerialNo = BaseInfo.getHardwareSerialNo();
                if (TextUtils.isEmpty(androidId) || TextUtils.isEmpty(hardwareSerialNo)) {
                    return null;
                }
                byte[] hash = CryptoTools.hash(androidId + hardwareSerialNo, "SHA256");
                if (hash == null || hash.length <= 0) {
                    return null;
                }
                StringBuilder sb = new StringBuilder(hash.length * 2);
                for (byte b : hash) {
                    int i2 = b & 255;
                    if (i2 < 16) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(i2));
                }
                return sb.toString();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static AuthenticatorInfo locateAuthenticator(String str) {
        List<AuthenticatorInfo> list = authenticators;
        AuthenticatorInfo authenticatorInfo = null;
        if (list != null) {
            for (AuthenticatorInfo authenticatorInfo2 : list) {
                if (StringTools.stringEqual(str.toLowerCase(), authenticatorInfo2.aaid) || StringTools.stringEqual(str.toUpperCase(), authenticatorInfo2.aaid)) {
                    authenticatorInfo = authenticatorInfo2;
                }
            }
        }
        return authenticatorInfo;
    }

    public static AuthenticatorInfo locateAuthenticator(short s) {
        List<AuthenticatorInfo> list = authenticators;
        AuthenticatorInfo authenticatorInfo = null;
        if (list != null) {
            for (AuthenticatorInfo authenticatorInfo2 : list) {
                if (authenticatorInfo2.authenticatorIndex == s) {
                    authenticatorInfo = authenticatorInfo2;
                }
            }
        }
        return authenticatorInfo;
    }
}
