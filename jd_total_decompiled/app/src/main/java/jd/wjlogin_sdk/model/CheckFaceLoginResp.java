package jd.wjlogin_sdk.model;

/* loaded from: classes.dex */
public class CheckFaceLoginResp {
    private FaceLoginSwitch faceLoginSwitch;
    private int status;
    private String statusJson;
    private String url;

    public FaceLoginSwitch getFaceLoginSwitch() {
        return this.faceLoginSwitch;
    }

    public int getStatus() {
        return this.status;
    }

    public String getStatusJson() {
        return this.statusJson;
    }

    public String getUrl() {
        return this.url;
    }

    public void setFaceLoginSwitch(FaceLoginSwitch faceLoginSwitch) {
        this.faceLoginSwitch = faceLoginSwitch;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public void setStatusJson(String str) {
        this.statusJson = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
