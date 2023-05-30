package com.jdjr.degrade;

import android.content.Context;
import com.jdjr.securehttp.JDJRResultMessage;
import com.jdjr.securehttp.SecureHttpHandler;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.DeviceInfo;
import com.jdjr.tools.JDJRLog;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class SDKRequestDegradeManager {
    private static final String TAG = "SDKRequestDegradeManager";
    public static boolean mUpdatedFunclist;
    private Context mContext;
    private String mDeviceId;
    private DeviceInfo mDeviceInfo;
    private String mFuncList;
    private SecureHttpHandler mHttpHandler;

    public SDKRequestDegradeManager(Context context, String str, String str2) {
        this.mDeviceId = str;
        this.mDeviceInfo = DeviceInfo.newInstance(context);
        this.mHttpHandler = new SecureHttpHandler(context);
        this.mFuncList = str2;
        this.mContext = context;
    }

    public void requestSDKDegradeResult() {
        String degradeAddress = CommonTools.getDegradeAddress();
        JSONObject jSONObject = new JSONObject();
        DeviceInfo.composeJson(jSONObject, "sdk_version", this.mDeviceInfo.getSDKVersion(), "app_info", this.mDeviceInfo.getAppPackageName(), ApplicationUpgradeHelper.APP_VERSION, this.mDeviceInfo.getAppVersionName(), PushConstants.DEVICE_ID, this.mDeviceInfo.getCurrentDID(), "device_type", this.mDeviceInfo.getDeviceType(), "os_type", this.mDeviceInfo.getOS(), "os_info", this.mDeviceInfo.getOSVersion(), CommonTools.KEY_FUNCLIST, this.mFuncList, "app_device_id", this.mDeviceId);
        this.mHttpHandler.secureSendDataToServer(CommonTools.generateHttpFixedData("0001", "0001", jSONObject.toString()), degradeAddress, new SecureHttpHandler.secureHttpRetCallback() { // from class: com.jdjr.degrade.SDKRequestDegradeManager.1
            @Override // com.jdjr.securehttp.SecureHttpHandler.secureHttpRetCallback
            public void getResultMessage(JDJRResultMessage jDJRResultMessage) {
                if (jDJRResultMessage.getErrorCode().equals("00000") && jDJRResultMessage.getResultString() != null && jDJRResultMessage.getResultString().length() != 0) {
                    if (jDJRResultMessage.getResultString().length() <= 17 || CommonTools.isOvertime20min(jDJRResultMessage.getResultString().substring(0, 17))) {
                        return;
                    }
                    JDJRLog.i(SDKRequestDegradeManager.TAG, "TIME CHECK SUCCESS");
                    SDKRequestDegradeManager.mUpdatedFunclist = true;
                    if (jDJRResultMessage.getResultString() == null || jDJRResultMessage.getResultString().length() < 4) {
                        return;
                    }
                    CommonTools.putStringSharePreference(SDKRequestDegradeManager.this.mContext, CommonTools.KEY_FUNCLIST, jDJRResultMessage.getResultString().substring(jDJRResultMessage.getResultString().length() - 4));
                    return;
                }
                JDJRLog.i(SDKRequestDegradeManager.TAG, "TIME FAILED");
            }
        });
    }
}
