package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class WXTokenInfo implements UnionLoginTokenInfo {
    private String code;

    @Override // jd.wjlogin_sdk.model.UnionLoginTokenInfo
    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }
}
