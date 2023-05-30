package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class ZteAngelCareTokenInfo implements UnionLoginTokenInfo {
    private String code;

    public ZteAngelCareTokenInfo(String str) {
        this.code = "";
        this.code = str;
    }

    @Override // jd.wjlogin_sdk.model.UnionLoginTokenInfo
    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public ZteAngelCareTokenInfo() {
        this.code = "";
    }
}
