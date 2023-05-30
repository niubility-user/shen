package com.jd.manto.d;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jingdong.common.utils.DeviceInfoHelper;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.manto.sdk.api.IGlobalParam;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class d implements IGlobalParam {
    @Override // com.jingdong.manto.sdk.api.IGlobalParam
    public String getCartUUID(Context context) {
        return "";
    }

    @Override // com.jingdong.manto.sdk.api.IGlobalParam
    @NonNull
    public Map getEncryptUUID(Context context) {
        JDUUIDEncHelper.EncryptResult readEncryptDeviceUUID = StatisticsReportUtil.readEncryptDeviceUUID();
        if (readEncryptDeviceUUID == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("eu", readEncryptDeviceUUID.eu);
        hashMap.put("fv", readEncryptDeviceUUID.fv);
        return hashMap;
    }

    @Override // com.jingdong.manto.sdk.api.IGlobalParam
    @NonNull
    public String getIp(Context context) {
        return null;
    }

    @Override // com.jingdong.manto.sdk.api.IGlobalParam
    @NonNull
    public String getRandomCartUUID(Context context) {
        return "" + StatisticsReportUtil.readCartUUID();
    }

    @Override // com.jingdong.manto.sdk.api.IGlobalParam
    @NonNull
    public String getUUID(Context context) {
        return DeviceInfoHelper.getAid();
    }
}
