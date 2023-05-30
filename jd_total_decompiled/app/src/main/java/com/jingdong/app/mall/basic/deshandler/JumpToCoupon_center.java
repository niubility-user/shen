package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.sec.LogoManager;
import com.jd.stat.security.jma.JMA;
import com.jdpay.lib.crypto.AES;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.h;
import com.jingdong.common.deeplinkhelper.DeepLinkCouponCenterHelper;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.util.ReplyCode;

@Des(des = "couponCenter,couponcenter")
/* loaded from: classes19.dex */
public class JumpToCoupon_center extends PReqDesJump {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a {
        final byte[] a = {ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31, ReplyCode.reply0x31};

        a(JumpToCoupon_center jumpToCoupon_center) {
        }

        private String c(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString.toLowerCase());
            }
            return sb.toString();
        }

        public String a(byte[] bArr, byte[] bArr2) {
            try {
                IvParameterSpec ivParameterSpec = new IvParameterSpec(this.a);
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, AES.ALGORITHM);
                Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
                cipher.init(1, secretKeySpec, ivParameterSpec);
                return c(cipher.doFinal(bArr));
            } catch (Exception unused) {
                return null;
            }
        }

        public String b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return a(str.getBytes(), "3d84378ebdb4a951".getBytes());
        }
    }

    private String v(String str) {
        return (TextUtils.isEmpty(str) || !str.equals("HomeMain")) ? str : "1";
    }

    @Override // com.jingdong.app.mall.basic.deshandler.PReqDesJump
    void s(Context context, Bundle bundle) {
        if (bundle == null) {
            c(context);
            return;
        }
        DeepLinkCouponCenterHelper.startCouponCenter(context, bundle);
        c(context);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.PReqDesJump
    String t(Context context, Bundle bundle) {
        return "getCouponConfig";
    }

    @Override // com.jingdong.app.mall.basic.deshandler.PReqDesJump
    Map<String, Object> u(Context context, Bundle bundle) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("monitorSource", "ccresource_android_index_config");
        hashMap.put("monitorRefer", "");
        String string = bundle.getString("sourceFrom");
        if (!TextUtils.isEmpty(string)) {
            hashMap.put("sourceFrom", v(string));
        } else {
            hashMap.put("sourceFrom", "-100");
        }
        if (JDElderModeUtils.isElderMode()) {
            hashMap.put("ynOld", "1");
        }
        hashMap.put("incentiveShowTimes", Integer.valueOf(h.b()));
        hashMap.put("rewardShowTimes", Integer.valueOf(h.a()));
        w(hashMap);
        String logo = LogoManager.getInstance(context).getLogo();
        if (TextUtils.isEmpty(logo)) {
            hashMap.put("eid", "-1");
        } else {
            hashMap.put("eid", logo);
        }
        hashMap.put("childActivityUrl", "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"couponCenter\"}");
        hashMap.put("pageClickKey", "Coupons_GetCenter");
        hashMap.put("shshshfpb", JMA.getSoftFingerprint(context));
        return hashMap;
    }

    void w(Map<String, Object> map) {
        String str;
        String str2;
        JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
        jDLocationCacheOption.setBusinessId("3d84378ebdb4a95104725af11007b3e8");
        JDLocation location = JDLocationCache.getInstance().getLocation(jDLocationCacheOption);
        if (location != null) {
            str2 = location.getLat() == 0.0d ? "" : String.valueOf(location.getLat());
            str = location.getLng() == 0.0d ? "" : String.valueOf(location.getLng());
        } else {
            str = "";
            str2 = str;
        }
        map.put("lat", TextUtils.isEmpty(str2) ? "" : new a(this).b(str2));
        map.put(HybridSDK.LNG, TextUtils.isEmpty(str) ? "" : new a(this).b(str));
    }
}
