package com.jd.aips.verify;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.jd.aips.common.utils.FsGsonUtil;
import com.jd.aips.verify.config.VerificationSdk;
import com.jd.aips.verify.config.VerifyConfig;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;

/* loaded from: classes12.dex */
public class SdkVerifyParams<C extends VerifyConfig> extends BaseVerifyParams<C> {
    static final long serialVersionUID = 6168114511268330813L;
    public boolean securityChannelReady;

    public SdkVerifyParams() {
        this.securityChannelReady = false;
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [C, java.lang.Object] */
    public void buildConfig(String str) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            try {
                ?? fromJson = FsGsonUtil.fromJson(str, (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
                this.verifyConfig = fromJson;
                if (fromJson == 0 || ((VerifyConfig) fromJson).verificationSdk == null || ((VerifyConfig) fromJson).verificationSdk.config == null) {
                    throw new InvalidParameterException("verify config is invalid!");
                }
                return;
            } catch (JsonSyntaxException e2) {
                e2.getMessage();
                throw new InvalidParameterException("config json \u683c\u5f0f\u9519\u8bef\uff1a " + e2.getMessage());
            }
        }
        throw new RuntimeException("is not parameterized type!");
    }

    @Override // com.jd.aips.verify.BaseVerifyParams
    public boolean validateParams() {
        C c2;
        VerificationSdk verificationSdk;
        return (!super.validateParams() || (c2 = this.verifyConfig) == 0 || (verificationSdk = ((VerifyConfig) c2).verificationSdk) == null || verificationSdk.config == null) ? false : true;
    }

    public SdkVerifyParams(Bundle bundle) throws InvalidParameterException {
        super(bundle);
        this.securityChannelReady = false;
        this.securityChannelReady = bundle.getBoolean(VerifyParams.SECURITY_CHANNEL_READY, false);
        String string = bundle.getString(VerifyParams.CONFIG_JSON);
        if (!TextUtils.isEmpty(string)) {
            buildConfig(string);
            return;
        }
        throw new InvalidParameterException(String.format("%s is empty!", VerifyParams.CONFIG_JSON));
    }
}
