package com.jd.aips.verify.face;

import android.os.Bundle;
import com.jd.aips.verify.SdkVerifyParams;
import com.jd.aips.verify.VerifyParams;
import java.security.InvalidParameterException;

/* loaded from: classes12.dex */
public class FaceVerifyParams extends SdkVerifyParams<FaceVerifyConfig> {
    static final long serialVersionUID = -8140039478717686653L;
    private String idCardToken;
    private String token;

    public FaceVerifyParams() {
    }

    public String getIdCardToken() {
        return this.idCardToken;
    }

    public String getToken() {
        return this.token;
    }

    public void setIdCardToken(String str) {
        this.idCardToken = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public FaceVerifyParams(Bundle bundle) throws InvalidParameterException {
        super(bundle);
        this.idCardToken = bundle.getString(VerifyParams.ID_CARD_TOKEN, "");
    }
}
