package jd.wjlogin_sdk.model;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class SuccessResult {
    private CompanyLoginResult companyLoginResult;
    private FaceLoginSwitch faceLoginSwitch;
    private int intVal;
    private String strVal;
    private ArrayList<WJUserInfo> userList;

    public CompanyLoginResult getCompanyLoginResult() {
        return this.companyLoginResult;
    }

    public FaceLoginSwitch getFaceLoginSwitch() {
        return this.faceLoginSwitch;
    }

    public int getIntVal() {
        return this.intVal;
    }

    public String getStrVal() {
        return this.strVal;
    }

    public ArrayList<WJUserInfo> getUserList() {
        return this.userList;
    }

    public void setCompanyLoginResult(CompanyLoginResult companyLoginResult) {
        this.companyLoginResult = companyLoginResult;
    }

    public void setFaceLoginSwitch(FaceLoginSwitch faceLoginSwitch) {
        this.faceLoginSwitch = faceLoginSwitch;
    }

    public void setIntVal(int i2) {
        this.intVal = i2;
    }

    public void setStrVal(String str) {
        this.strVal = str;
    }

    public void setUserList(ArrayList<WJUserInfo> arrayList) {
        this.userList = arrayList;
    }
}
