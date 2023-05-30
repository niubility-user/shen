package com.jingdong.app.mall.im.business;

import android.content.Context;
import android.text.TextUtils;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.security.JDKeyStore;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.impl.IMDevice;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public class k extends IMDevice {
    private static final String a = "k";

    private String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
        }
        return str.toUpperCase();
    }

    private String b(String str, String str2, String str3) throws Exception {
        if (str2 != null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(c(str2), JDKeyStore.KEY_TYPE_AES);
            Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
            cipher.init(1, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            return a(cipher.doFinal(str.getBytes(str3))).toUpperCase();
        }
        throw new Exception("Key is null\uff01");
    }

    private byte[] c(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 == 1) {
            return null;
        }
        int i2 = length / 2;
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 != i2; i3++) {
            int i4 = i3 * 2;
            bArr[i3] = (byte) Integer.parseInt(str.substring(i4, i4 + 2), 16);
        }
        return bArr;
    }

    @Override // com.jingdong.service.impl.IMDevice, com.jingdong.service.service.DeviceService
    public String getDeviceId(Context context) {
        String str = "";
        if (!TextUtils.isEmpty("")) {
            OKLog.d("bundleicssdkservice", a + "---getDeviceId:");
            return "";
        }
        try {
            str = "dd_dvc_aes_" + b(StatisticsReportUtil.readDeviceUUID(), "AF6087F27D9CA16FB1A4936D42EABA1B", "utf-8");
        } catch (Exception unused) {
        }
        OKLog.d("bundleicssdkservice", a + "---getDeviceId:" + str);
        return str;
    }

    @Override // com.jingdong.service.impl.IMDevice, com.jingdong.service.service.DeviceService
    public String getOriDeviceId() {
        return StatisticsReportUtil.readDeviceUUID();
    }
}
