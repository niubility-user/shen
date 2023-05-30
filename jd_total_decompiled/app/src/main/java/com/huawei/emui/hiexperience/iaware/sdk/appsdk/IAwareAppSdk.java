package com.huawei.emui.hiexperience.iaware.sdk.appsdk;

import com.jd.dynamic.DYConstants;

/* loaded from: classes12.dex */
public class IAwareAppSdk {
    private static final int INTERFACE_CANCEL_VIP_THREAD = 1002;
    private static final int INTERFACE_NOTIFY_SCENE = 1000;
    private static final String INTERFACE_STR_ADD_VIPTHREAD = "\"IFID\":1001,\"vipThreads\":[";
    private static final String INTERFACE_STR_CANCEL_VIPTHREAD = "\"IFID\":1002,\"vipThreads\":[";
    private static final String INTERFACE_STR_NOTIFYSCENE = "\"IFID\":1000";
    private static final int INTERFACE_VIP_THREAD = 1001;
    private static final int MAX_STR_SIZE = 256;
    private static final String TAG = "IAwareAppSdk";
    private boolean registerStatus = false;
    private String mPhoneInfo = "";
    private IAwareAppSdkAdapter mIAwareAppSdkAdapter = null;
    private AppCallBack AppCbk = new AppCallBack() { // from class: com.huawei.emui.hiexperience.iaware.sdk.appsdk.IAwareAppSdk.1
        {
            IAwareAppSdk.this = this;
        }

        @Override // com.huawei.emui.hiexperience.iaware.sdk.appsdk.IAwareAppSdk.AppCallBack
        public void getPhoneInfo(String str) {
            String str2 = "info=" + str + " and mPhoneInfo is " + IAwareAppSdk.this.mPhoneInfo;
            IAwareAppSdk.this.mPhoneInfo = str;
        }
    };

    /* loaded from: classes12.dex */
    public interface AppCallBack {
        void getPhoneInfo(String str);
    }

    private boolean registerApp(String str, AppCallBack appCallBack) {
        if (str == null || str.length() <= 0 || appCallBack == null) {
            return false;
        }
        if (this.mIAwareAppSdkAdapter == null) {
            String str2 = "registerApp, packageName:" + str;
            IAwareAppSdkAdapter iAwareAppSdkAdapter = new IAwareAppSdkAdapter();
            this.mIAwareAppSdkAdapter = iAwareAppSdkAdapter;
            this.registerStatus = iAwareAppSdkAdapter.registerAppCallback(str, appCallBack);
        }
        return this.registerStatus;
    }

    public void addVipThreads(long[] jArr) {
        IAwareAppSdkAdapter iAwareAppSdkAdapter;
        if (jArr == null || jArr.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(INTERFACE_STR_ADD_VIPTHREAD);
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            String l2 = Long.toString(jArr[i2]);
            if (i2 == length - 1) {
                sb.append(l2);
            } else {
                sb.append(l2 + DYConstants.DY_REGEX_COMMA);
            }
        }
        sb.append("]");
        String str = "add vipThreads, json: " + sb.toString();
        if (sb.length() <= 256 && (iAwareAppSdkAdapter = this.mIAwareAppSdkAdapter) != null) {
            iAwareAppSdkAdapter.reportVip(sb.toString());
        }
    }

    public void cancelVipThreads(long[] jArr) {
        IAwareAppSdkAdapter iAwareAppSdkAdapter;
        if (jArr == null || jArr.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(INTERFACE_STR_CANCEL_VIPTHREAD);
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            String l2 = Long.toString(jArr[i2]);
            if (i2 == length - 1) {
                sb.append(l2);
            } else {
                sb.append(l2 + DYConstants.DY_REGEX_COMMA);
            }
        }
        sb.append("]");
        String str = "cancel vipThreads, json: " + sb.toString();
        if (sb.length() <= 256 && (iAwareAppSdkAdapter = this.mIAwareAppSdkAdapter) != null) {
            iAwareAppSdkAdapter.reportVip(sb.toString());
        }
    }

    public void notifyAppScene(int i2, int i3) {
        IAwareAppSdkAdapter iAwareAppSdkAdapter;
        StringBuilder sb = new StringBuilder();
        sb.append(INTERFACE_STR_NOTIFYSCENE);
        String num = Integer.toString(i2);
        String num2 = Integer.toString(i3);
        sb.append(",\"scene\":");
        sb.append(num);
        sb.append(",\"status\":");
        sb.append(num2);
        String str = "notifyAppScene, json: " + ((Object) sb);
        if (sb.length() <= 256 && (iAwareAppSdkAdapter = this.mIAwareAppSdkAdapter) != null) {
            iAwareAppSdkAdapter.reportScene(sb.toString());
        }
    }

    public boolean registerApp(String str) {
        return registerApp(str, this.AppCbk);
    }
}
