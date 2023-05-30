package com.jd.aips.verify.api;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jd.aips.common.bean.DeviceInfo;
import com.jd.aips.verify.BaseVerifyParams;
import com.jd.aips.verify.VerifySession;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes12.dex */
public class VerifyRequest extends BaseRequest {
    static final long serialVersionUID = -5405555115431242767L;
    public final DeviceInfo deviceInfo;
    public Map<String, Object> extra = Collections.EMPTY_MAP;
    public final String pin;
    public final String sessionId;
    public final String token;
    public String verifyBusinessType;

    public VerifyRequest(@NonNull Context context, @NonNull VerifySession verifySession) {
        this.verifyBusinessType = BaseVerifyParams.VERIFY_BUSINESS_TYPE_SIMPLE;
        P p = verifySession.verifyParams;
        this.businessId = p.businessId;
        this.appAuthorityKey = p.appAuthorityKey;
        this.appName = p.appName;
        this.pin = p.userId;
        this.token = verifySession.verifyToken;
        this.sessionId = verifySession.id;
        this.deviceInfo = p.getDeviceInfo();
        this.verifyBusinessType = p.getVerifyBusinessType();
    }
}
