package com.jingdong.common.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.AttributionReporter;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.R;
import com.jingdong.common.permission.SceneDialogHelper;
import com.jingdong.common.permission.entity.ListPermissionEntity;
import com.jingdong.common.permission.entity.PermissionItem;
import com.jingdong.common.permission.entity.SceneStatus;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PermissionHelper {
    private static final boolean CHECK_ACTIVITY = true;
    private static final boolean CHECK_PERMISSION = false;
    private static final String CHOICE_CANCEL = "cancel";
    private static final String CHOICE_ERR = "error";
    private static final String CHOICE_YES = "yes";
    private static final String EMPTY_STR = "";
    private static final boolean INSTALL_RESULT = true;
    private static final String LOC_TAG = "LOCSceneTAG";
    public static final String NO_SHOW_DIALOG = "0";
    public static final String PARAM_CLASS_NAME = "className";
    public static final String PARAM_METHOD_NAME = "methodName";
    public static final String PARAM_MODULE_NAME = "moduleName";
    public static final String PARAM_PERMISSION_NAMES = "permissionNames";
    public static final String PARAM_PERMISSION_TIPS = "permissionTips";
    public static final String PARAM_REQUEST_TIP_CANCEL = "permissionSDKRequestTipCancel";
    public static final String PARAM_REQUEST_TIP_CONFIRM = "permissionSDKRequestTipConfirm";
    public static final String PARAM_REQUEST_TIP_MESSAGE = "permissionSDKRequestTipMessage";
    public static final String PARAM_SHOW_MSG_DIALOG = "permissionSDKShowJDDialog";
    public static final String PARAM_SHOW_OPEN_SETTING_DIALOG = "permissionSDKOpenSettingDialog";
    public static final String PARAM_USER_INITIATIVE = "isInitiative";
    private static final String PERMISSION_FIRST = "PERMISSION_FIRST";
    public static final String PERMISSION_GET_INSTALLED_APPS = "com.android.permission.GET_INSTALLED_APPS";
    private static final String REQ_CHOICE = "user_choice";
    private static final String REQ_FUNC = "req_function";
    private static final String REQ_SCENE = "scene_id";
    private static final String REQ_TYPE = "req_type";
    public static final String SHOW_DIALOG = "1";
    public static final String TAG = "PermissionHelper";
    private static final long TIME_48_HOUR = 172800000;
    private static final String TYPE_LEAD = "lead";
    private static final String TYPE_SCENE = "scene";
    private static final String TYPE_SYS = "sys";
    private static SparseArray<PermissionResultCallBack> callBacks;
    private static List<CarePermissionResultCallBack> carePermissionResultCallBacks;
    private static ConcurrentHashMap<String, Boolean> dialogsShowingStatus;
    private static HashMap<String, String> permissionNameDefaults;
    private static HashMap<String, String> permissionTipDefaults;
    private static HashMap<String, String> permissionTitles;
    private static AtomicInteger requestCodeAtomic;
    private static String sceneLocTitle;
    private static HashMap<Integer, List<PermissionItem>> permissionItemHashMap = new HashMap<>(6);
    private static Handler handler = new Handler(Looper.getMainLooper());

    /* loaded from: classes5.dex */
    public static abstract class AlertWindowRequestCallBack {
        public void onDenied() {
        }

        public void onGranted() {
        }
    }

    /* loaded from: classes5.dex */
    public static class CarePermissionResultCallBack {
        private String carePermission;

        public CarePermissionResultCallBack(String str) {
            this.carePermission = str;
        }

        public final boolean isAccept(List<String> list) {
            if (list == null || list.size() == 0 || TextUtils.isEmpty(this.carePermission)) {
                return false;
            }
            return list.contains(this.carePermission);
        }

        public final boolean isGranted(Activity activity) {
            return !TextUtils.isEmpty(this.carePermission) && ContextCompat.checkSelfPermission(activity, this.carePermission) == 0;
        }

        public void onDenied() {
        }

        public void onGranted() {
        }
    }

    /* loaded from: classes5.dex */
    public static class LBSReportBuilder {
        private String sceneId = "";
        private String requestType = "";
        private String dialogType = "";
        private String dialogChoice = "";
        private String error = "";

        private LBSReportBuilder() {
        }

        static LBSReportBuilder getReportBuilder() {
            return new LBSReportBuilder();
        }

        JSONObject build() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(PermissionHelper.REQ_FUNC, this.requestType);
                jSONObject.put(PermissionHelper.REQ_CHOICE, this.dialogChoice);
                jSONObject.put("req_type", this.dialogType);
                jSONObject.put(PermissionHelper.REQ_SCENE, this.sceneId);
                jSONObject.put("error", this.error);
                return jSONObject;
            } catch (JSONException unused) {
                return null;
            }
        }

        LBSReportBuilder diaChoice(String str) {
            this.dialogChoice = str;
            return this;
        }

        LBSReportBuilder diaType(String str) {
            this.dialogType = str;
            return this;
        }

        LBSReportBuilder error(String str) {
            this.error = str;
            return this;
        }

        LBSReportBuilder reqType(String str) {
            this.requestType = str;
            return this;
        }

        LBSReportBuilder scene(String str) {
            this.sceneId = str;
            return this;
        }
    }

    /* loaded from: classes5.dex */
    public static class Permission26 {
        public static String[] storage = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        public static String[] location = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        public static String[] phone = {"android.permission.READ_PHONE_STATE"};

        private Permission26() {
        }
    }

    @TargetApi(29)
    /* loaded from: classes5.dex */
    public static class Permission29 {
        public static String[] location = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

        private Permission29() {
        }
    }

    /* loaded from: classes5.dex */
    public static abstract class PermissionResultCallBack {
        private boolean isInitiative;
        Bundle params;

        public void onCanceled() {
        }

        public void onDenied() {
        }

        public void onGranted() {
        }

        public void onIgnored() {
        }

        public void onOpenSetting() {
        }
    }

    /* loaded from: classes5.dex */
    public static class PermissionResultCallbackWrapper extends PermissionResultCallBack {
        private final PermissionResultCallBack origin;
        private final WeakReference<JDDialog> weakDia;

        PermissionResultCallbackWrapper(PermissionResultCallBack permissionResultCallBack, JDDialog jDDialog) {
            this.origin = permissionResultCallBack;
            this.weakDia = new WeakReference<>(jDDialog);
        }

        private void tryCloseDialog() {
            JDDialog jDDialog = this.weakDia.get();
            if (jDDialog != null) {
                jDDialog.dismiss();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            this.origin.onCanceled();
            tryCloseDialog();
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            this.origin.onDenied();
            tryCloseDialog();
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            this.origin.onGranted();
            tryCloseDialog();
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            this.origin.onIgnored();
            tryCloseDialog();
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            this.origin.onOpenSetting();
            tryCloseDialog();
        }
    }

    /* loaded from: classes5.dex */
    public static abstract class PermissionSceneCallback extends PermissionResultCallBack {
        public void onAgree() {
        }

        public void onDisagree() {
        }

        public void onSceneIsEmpty() {
        }

        public void onTimeLimited() {
        }
    }

    /* loaded from: classes5.dex */
    public static class PermissionSceneCallbackWrapper extends PermissionSceneCallback {
        final LBSReportBuilder builder;
        private final PermissionSceneCallback callback;
        private final String scene;

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onAgree() {
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onAgree();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onCanceled();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            if (!TextUtils.isEmpty(this.scene)) {
                LBSSceneSwitchHelper.saveLbsSceneSwitch(this.scene, false);
            }
            this.builder.diaChoice("cancel");
            PermissionHelper.lbsPermissionReport(this.builder.build());
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onDenied();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onDisagree() {
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onDisagree();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            if (!TextUtils.isEmpty(this.scene)) {
                LBSSceneSwitchHelper.saveLbsSceneSwitch(this.scene, true);
            }
            this.builder.diaChoice(PermissionHelper.CHOICE_YES);
            PermissionHelper.lbsPermissionReport(this.builder.build());
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onGranted();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onIgnored();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            super.onOpenSetting();
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onOpenSetting();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onSceneIsEmpty() {
            super.onSceneIsEmpty();
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onSceneIsEmpty();
            }
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onTimeLimited() {
            super.onTimeLimited();
            PermissionSceneCallback permissionSceneCallback = this.callback;
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onTimeLimited();
            }
        }

        private PermissionSceneCallbackWrapper(String str, PermissionSceneCallback permissionSceneCallback, LBSReportBuilder lBSReportBuilder) {
            this.scene = str;
            this.callback = permissionSceneCallback;
            this.builder = lBSReportBuilder;
        }
    }

    /* loaded from: classes5.dex */
    public static class PhoneResultCallBack extends CarePermissionResultCallBack {
        public PhoneResultCallBack() {
            super("android.permission.READ_PHONE_STATE");
        }

        @Override // com.jingdong.common.permission.PermissionHelper.CarePermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            try {
                JDMtaUtils.setImeiTag();
                PermissionHelper.removeCarePermissionResultCallBack(this);
            } catch (Throwable unused) {
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 23) {
            HashMap<String, String> hashMap = new HashMap<>(6);
            permissionTitles = hashMap;
            Application application = JdSdk.getInstance().getApplication();
            int i2 = R.string.permission_location;
            hashMap.put("android.permission.ACCESS_COARSE_LOCATION", application.getString(i2));
            permissionTitles.put("android.permission.ACCESS_FINE_LOCATION", JdSdk.getInstance().getApplication().getString(i2));
            String string = JdSdk.getInstance().getApplication().getString(R.string.permission_phone_state);
            permissionTitles.put("android.permission.READ_CALL_LOG", string);
            permissionTitles.put("android.permission.WRITE_CALL_LOG", string);
            permissionTitles.put("com.android.voicemail.permission.ADD_VOICEMAIL", string);
            permissionTitles.put("android.permission.USE_SIP", string);
            permissionTitles.put("android.permission.PROCESS_OUTGOING_CALLS", string);
            permissionTitles.put("android.permission.READ_PHONE_STATE", string);
            permissionTitles.put("android.permission.RECORD_AUDIO", JdSdk.getInstance().getApplication().getString(R.string.permission_record_audio));
            HashMap<String, String> hashMap2 = permissionTitles;
            Application application2 = JdSdk.getInstance().getApplication();
            int i3 = R.string.permission_storage;
            hashMap2.put("android.permission.WRITE_EXTERNAL_STORAGE", application2.getString(i3));
            permissionTitles.put("android.permission.READ_EXTERNAL_STORAGE", JdSdk.getInstance().getApplication().getString(i3));
            permissionTitles.put("android.permission.CAMERA", JdSdk.getInstance().getApplication().getString(R.string.permission_camera));
            HashMap<String, String> hashMap3 = permissionTitles;
            Application application3 = JdSdk.getInstance().getApplication();
            int i4 = R.string.permission_contacts;
            hashMap3.put("android.permission.READ_CONTACTS", application3.getString(i4));
            permissionTitles.put("android.permission.WRITE_CONTACTS", JdSdk.getInstance().getApplication().getString(i4));
            permissionTitles.put("android.permission.GET_ACCOUNTS", JdSdk.getInstance().getApplication().getString(i4));
            HashMap<String, String> hashMap4 = permissionTitles;
            Application application4 = JdSdk.getInstance().getApplication();
            int i5 = R.string.permission_sms;
            hashMap4.put("android.permission.SEND_SMS", application4.getString(i5));
            permissionTitles.put("android.permission.RECEIVE_SMS", JdSdk.getInstance().getApplication().getString(i5));
            permissionTitles.put("android.permission.READ_SMS", JdSdk.getInstance().getApplication().getString(i5));
            permissionTitles.put("android.permission.RECEIVE_MMS", JdSdk.getInstance().getApplication().getString(i5));
            permissionTitles.put("android.permission.RECEIVE_WAP_PUSH", JdSdk.getInstance().getApplication().getString(i5));
            permissionTitles.put(PERMISSION_GET_INSTALLED_APPS, JdSdk.getInstance().getApplication().getString(R.string.permission_get_install_apps));
            sceneLocTitle = JdSdk.getInstance().getApplication().getString(R.string.permission_location_default_title);
            callBacks = new SparseArray<>();
            requestCodeAtomic = new AtomicInteger();
            dialogsShowingStatus = new ConcurrentHashMap<>();
            addCarePermissionResultCallBack(new PhoneResultCallBack());
            permissionNameDefaults = new HashMap<>(6);
            permissionTipDefaults = new HashMap<>(6);
            permissionNameDefaults.put(PERMISSION_GET_INSTALLED_APPS, JdSdk.getInstance().getApplication().getString(R.string.permission_installed_apps_title));
            permissionTipDefaults.put(PERMISSION_GET_INSTALLED_APPS, JdSdk.getInstance().getApplication().getString(R.string.permission_installed_apps_content));
            HashMap<String, String> hashMap5 = permissionNameDefaults;
            Application application5 = JdSdk.getInstance().getApplication();
            int i6 = R.string.permission_calendar_default;
            hashMap5.put("android.permission.READ_CALENDAR", application5.getString(i6));
            permissionNameDefaults.put("android.permission.WRITE_CALENDAR", JdSdk.getInstance().getApplication().getString(i6));
            HashMap<String, String> hashMap6 = permissionTipDefaults;
            Application application6 = JdSdk.getInstance().getApplication();
            int i7 = R.string.permission_calendar_default_msg;
            hashMap6.put("android.permission.READ_CALENDAR", application6.getString(i7));
            permissionTipDefaults.put("android.permission.WRITE_CALENDAR", JdSdk.getInstance().getApplication().getString(i7));
            permissionNameDefaults.put("android.permission.CAMERA", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_default));
            permissionTipDefaults.put("android.permission.CAMERA", JdSdk.getInstance().getApplication().getString(R.string.permission_camera_default_msg));
            HashMap<String, String> hashMap7 = permissionNameDefaults;
            Application application7 = JdSdk.getInstance().getApplication();
            int i8 = R.string.permission_contact_default;
            hashMap7.put("android.permission.READ_CONTACTS", application7.getString(i8));
            permissionNameDefaults.put("android.permission.WRITE_CONTACTS", JdSdk.getInstance().getApplication().getString(i8));
            permissionNameDefaults.put("android.permission.GET_ACCOUNTS", JdSdk.getInstance().getApplication().getString(i8));
            HashMap<String, String> hashMap8 = permissionTipDefaults;
            Application application8 = JdSdk.getInstance().getApplication();
            int i9 = R.string.permission_contact_default_msg;
            hashMap8.put("android.permission.READ_CONTACTS", application8.getString(i9));
            permissionTipDefaults.put("android.permission.WRITE_CONTACTS", JdSdk.getInstance().getApplication().getString(i9));
            permissionTipDefaults.put("android.permission.GET_ACCOUNTS", JdSdk.getInstance().getApplication().getString(i9));
            permissionNameDefaults.put("android.permission.RECORD_AUDIO", JdSdk.getInstance().getApplication().getString(R.string.permission_record_default));
            permissionTipDefaults.put("android.permission.RECORD_AUDIO", JdSdk.getInstance().getApplication().getString(R.string.permission_record_default_msg));
            HashMap<String, String> hashMap9 = permissionNameDefaults;
            Application application9 = JdSdk.getInstance().getApplication();
            int i10 = R.string.permission_calllog_default;
            hashMap9.put("com.android.voicemail.permission.ADD_VOICEMAIL", application9.getString(i10));
            permissionNameDefaults.put("android.permission.PROCESS_OUTGOING_CALLS", JdSdk.getInstance().getApplication().getString(i10));
            permissionNameDefaults.put("android.permission.USE_SIP", JdSdk.getInstance().getApplication().getString(i10));
            permissionNameDefaults.put("android.permission.WRITE_CALL_LOG", JdSdk.getInstance().getApplication().getString(i10));
            permissionNameDefaults.put("android.permission.CALL_PHONE", JdSdk.getInstance().getApplication().getString(i10));
            permissionNameDefaults.put("android.permission.READ_PHONE_STATE", JdSdk.getInstance().getApplication().getString(i10));
            permissionNameDefaults.put("android.permission.READ_CALL_LOG", JdSdk.getInstance().getApplication().getString(i10));
            HashMap<String, String> hashMap10 = permissionTipDefaults;
            Application application10 = JdSdk.getInstance().getApplication();
            int i11 = R.string.permission_calllog_default_msg;
            hashMap10.put("com.android.voicemail.permission.ADD_VOICEMAIL", application10.getString(i11));
            permissionTipDefaults.put("android.permission.PROCESS_OUTGOING_CALLS", JdSdk.getInstance().getApplication().getString(i11));
            permissionTipDefaults.put("android.permission.USE_SIP", JdSdk.getInstance().getApplication().getString(i11));
            permissionTipDefaults.put("android.permission.WRITE_CALL_LOG", JdSdk.getInstance().getApplication().getString(i11));
            permissionTipDefaults.put("android.permission.CALL_PHONE", JdSdk.getInstance().getApplication().getString(i11));
            permissionTipDefaults.put("android.permission.READ_PHONE_STATE", JdSdk.getInstance().getApplication().getString(i11));
            permissionTipDefaults.put("android.permission.READ_CALL_LOG", JdSdk.getInstance().getApplication().getString(i11));
            HashMap<String, String> hashMap11 = permissionNameDefaults;
            Application application11 = JdSdk.getInstance().getApplication();
            int i12 = R.string.permission_storage_default;
            hashMap11.put("android.permission.READ_EXTERNAL_STORAGE", application11.getString(i12));
            permissionNameDefaults.put("android.permission.WRITE_EXTERNAL_STORAGE", JdSdk.getInstance().getApplication().getString(i12));
            HashMap<String, String> hashMap12 = permissionTipDefaults;
            Application application12 = JdSdk.getInstance().getApplication();
            int i13 = R.string.permission_storage_default_msg;
            hashMap12.put("android.permission.READ_EXTERNAL_STORAGE", application12.getString(i13));
            permissionTipDefaults.put("android.permission.WRITE_EXTERNAL_STORAGE", JdSdk.getInstance().getApplication().getString(i13));
            HashMap<String, String> hashMap13 = permissionNameDefaults;
            Application application13 = JdSdk.getInstance().getApplication();
            int i14 = R.string.permission_location_default;
            hashMap13.put("android.permission.ACCESS_FINE_LOCATION", application13.getString(i14));
            permissionNameDefaults.put("android.permission.ACCESS_COARSE_LOCATION", JdSdk.getInstance().getApplication().getString(i14));
            permissionNameDefaults.put("android.permission.ACCESS_BACKGROUND_LOCATION", JdSdk.getInstance().getApplication().getString(i14));
            HashMap<String, String> hashMap14 = permissionTipDefaults;
            Application application14 = JdSdk.getInstance().getApplication();
            int i15 = R.string.permission_location_default_msg;
            hashMap14.put("android.permission.ACCESS_FINE_LOCATION", application14.getString(i15));
            permissionTipDefaults.put("android.permission.ACCESS_COARSE_LOCATION", JdSdk.getInstance().getApplication().getString(i15));
            permissionTipDefaults.put("android.permission.ACCESS_BACKGROUND_LOCATION", JdSdk.getInstance().getApplication().getString(i15));
        }
    }

    public static void addCarePermissionResultCallBack(CarePermissionResultCallBack carePermissionResultCallBack) {
        if (carePermissionResultCallBack == null) {
            return;
        }
        if (carePermissionResultCallBacks == null) {
            carePermissionResultCallBacks = new CopyOnWriteArrayList();
        }
        if (carePermissionResultCallBacks.contains(carePermissionResultCallBack)) {
            return;
        }
        carePermissionResultCallBacks.add(carePermissionResultCallBack);
    }

    private static boolean bIncludea(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length > strArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (!strArr[i2].equals(strArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkInstallPermission() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDPrivacy", "GetInstalledApps", "enable", "0"));
    }

    private static boolean checkInstalledAppsPermission() {
        Application application;
        if (isDeviceInInstalledAppsWhiteList() || (application = JdSdk.getInstance().getApplication()) == null) {
            return true;
        }
        try {
            if (isSecurePermission(application)) {
                return ContextCompat.checkSelfPermission(application, PERMISSION_GET_INSTALLED_APPS) == 0;
            }
            PermissionInfo permissionInfo = application.getPackageManager().getPermissionInfo(PERMISSION_GET_INSTALLED_APPS, 0);
            if (permissionInfo == null) {
                return true;
            }
            return (permissionInfo.protectionLevel & 15) != 1 || (application.getPackageManager().getApplicationInfo(permissionInfo.packageName, 0).flags & 1) == 0 || ContextCompat.checkSelfPermission(application, PERMISSION_GET_INSTALLED_APPS) == 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    private static boolean checkParam(Activity activity, Bundle bundle, boolean z) {
        if (bundle == null) {
            return false;
        }
        return (z && activity == null) ? false : true;
    }

    private static boolean checkPermission(String[] strArr) {
        return true;
    }

    public static String checkSceneContent(String str, String str2) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 126020275:
                if (str.equals("marketingActivities")) {
                    c2 = 0;
                    break;
                }
                break;
            case 179587985:
                if (str.equals("receiveAddress")) {
                    c2 = 1;
                    break;
                }
                break;
            case 215622105:
                if (str.equals("basicShoppingProcess")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1580766037:
                if (str.equals("locService")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_MARKET;
            case 1:
                return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_ADDRESS;
            case 2:
                return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_BASE;
            case 3:
                return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_LOC;
            default:
                return PermissionConstants.LBS_SCENE_BACKUP_CONTENT;
        }
    }

    private static JDDialog createJDDialog(Activity activity, String str, String str2) {
        return JDDialogFactory.getInstance().createJdDialogWithStyle9(activity, str, str2, null, "", "\u6211\u77e5\u9053\u4e86");
    }

    private static JDDialog createJDSettingDialog(Activity activity, List<String> list, List<String> list2, List<String> list3, String str, String str2) {
        String str3;
        if (list == null) {
            return new JDDialog(activity);
        }
        ArrayList arrayList = new ArrayList();
        boolean isLocSceneRequest = isLocSceneRequest(list);
        int i2 = 0;
        while (i2 < list.size()) {
            if (!hasPermission(activity, list.get(i2))) {
                ListPermissionEntity listPermissionEntity = new ListPermissionEntity();
                String str4 = "";
                listPermissionEntity.setTitle(getPermissionName(list.get(i2), (list2 == null || i2 >= list2.size()) ? "" : list2.get(i2)));
                String str5 = list.get(i2);
                if (list3 != null && i2 < list3.size()) {
                    str4 = list3.get(i2);
                }
                listPermissionEntity.setContent(getPermissionTipMsg(str5, str4));
                arrayList.add(listPermissionEntity);
                if (isLocSceneRequest) {
                    break;
                }
            }
            i2++;
        }
        List<ListPermissionEntity> removeDuplicate = removeDuplicate(arrayList);
        Application application = JdSdk.getInstance().getApplication();
        int i3 = R.string.permission_dialog_msg_title_muti;
        String string = application.getString(i3);
        if (removeDuplicate.size() == 1) {
            String string2 = JdSdk.getInstance().getApplication().getString(R.string.permission_dialog_msg_title_single, new Object[]{removeDuplicate.get(0).getTitle()});
            JDPermissionDialogFactory jDPermissionDialogFactory = JDPermissionDialogFactory.getInstance();
            if (isLocSceneRequest) {
                string2 = string;
            }
            if (isLocSceneRequest) {
                str3 = removeDuplicate.get(0).getContent();
            } else {
                str3 = removeDuplicate.get(0).getContent() + "\u3002\u62d2\u7edd\u6216\u53d6\u6d88\u6388\u6743\u4e0d\u5f71\u54cd\u4f7f\u7528\u5176\u4ed6\u670d\u52a1";
            }
            return jDPermissionDialogFactory.createJdSinglePermissionDialog(activity, string2, str3, str, str2);
        }
        String string3 = JdSdk.getInstance().getApplication().getString(i3);
        JDPermissionDialogFactory jDPermissionDialogFactory2 = JDPermissionDialogFactory.getInstance();
        if (isLocSceneRequest) {
            string3 = string;
        }
        return jDPermissionDialogFactory2.createJdMutiPermissionDialog(activity, string3, removeDuplicate, "\u62d2\u7edd\u6216\u53d6\u6d88\u6388\u6743\u4e0d\u5f71\u54cd\u4f7f\u7528\u5176\u4ed6\u670d\u52a1", str, str2);
    }

    private static JDDialog createJDTopListDialog(Activity activity, List<String> list, List<String> list2, List<String> list3) {
        String str;
        String str2;
        if (list == null) {
            return new JDDialog(activity);
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            str = "";
            if (i2 >= list.size()) {
                break;
            }
            if (!hasPermission(activity, list.get(i2))) {
                ListPermissionEntity listPermissionEntity = new ListPermissionEntity();
                listPermissionEntity.setTitle(getPermissionName(list.get(i2), (list2 == null || i2 >= list2.size()) ? "" : list2.get(i2)));
                String str3 = list.get(i2);
                if (list3 != null && i2 < list3.size()) {
                    str = list3.get(i2);
                }
                listPermissionEntity.setContent(getPermissionTipMsg(str3, str));
                arrayList.add(listPermissionEntity);
            }
            i2++;
        }
        List<ListPermissionEntity> removeDuplicate = removeDuplicate(arrayList);
        boolean isLocSceneRequest = isLocSceneRequest(list);
        if (removeDuplicate.size() == 1) {
            String string = JdSdk.getInstance().getApplication().getString(R.string.permission_dialog_msg_title_single, new Object[]{removeDuplicate.get(0).getTitle()});
            JDPermissionDialogFactory jDPermissionDialogFactory = JDPermissionDialogFactory.getInstance();
            if (isLocSceneRequest) {
                str2 = removeDuplicate.get(0).getContent();
            } else {
                str2 = removeDuplicate.get(0).getContent() + "\u3002\u62d2\u7edd\u6216\u53d6\u6d88\u6388\u6743\u4e0d\u5f71\u54cd\u4f7f\u7528\u5176\u4ed6\u670d\u52a1";
            }
            return jDPermissionDialogFactory.createJdTopPermissionDialog(activity, string, str2);
        }
        String string2 = JdSdk.getInstance().getApplication().getString(R.string.permission_dialog_msg_title_muti);
        String str4 = createMutiContent(removeDuplicate) + "\u3002\u62d2\u7edd\u6216\u53d6\u6d88\u6388\u6743\u4e0d\u5f71\u54cd\u4f7f\u7528\u5176\u4ed6\u670d\u52a1";
        if (isLocSceneRequest) {
            String str5 = list.get(0);
            if (list3 != null && list3.size() > 0) {
                str = list3.get(0);
            }
            str4 = getPermissionTipMsg(str5, str);
        }
        return JDPermissionDialogFactory.getInstance().createJdTopPermissionDialog(activity, string2, str4);
    }

    private static String createMutiContent(List<ListPermissionEntity> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(list.get(i2).getContent());
            if (i2 != size - 1) {
                sb.append("\u3001");
            }
        }
        return sb.toString();
    }

    public static boolean defaultUserInitiative(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return true;
        }
        for (String str : strArr) {
            if (TextUtils.equals(str, "android.permission.ACCESS_FINE_LOCATION") || TextUtils.equals(str, "android.permission.ACCESS_COARSE_LOCATION") || TextUtils.equals(str, "android.permission.READ_PHONE_STATE")) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public static Bundle generateBundle(String str, String str2, String str3, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("moduleName", str);
        bundle.putString("className", str2);
        bundle.putString("methodName", str3);
        return bundle;
    }

    public static String generatePermissionMsgs(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String permissionTitle = getPermissionTitle(list.get(i2));
                if (!TextUtils.isEmpty(permissionTitle) && !sb.toString().contains(permissionTitle)) {
                    sb.append(permissionTitle);
                    if (size - 1 > i2) {
                        sb.append("\u3001");
                    }
                }
            }
        }
        if (sb.toString().endsWith("\u3001")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static File getExternalCacheDir() {
        File externalCacheDir = JdSdk.getInstance().getApplication().getExternalCacheDir();
        if (externalCacheDir != null) {
            if (externalCacheDir.exists() && externalCacheDir.isDirectory()) {
                return externalCacheDir;
            }
            externalCacheDir.mkdirs();
            return externalCacheDir;
        }
        return JdSdk.getInstance().getApplication().getFilesDir();
    }

    public static File getExternalFilesDir(String str) {
        File externalFilesDir = JdSdk.getInstance().getApplication().getExternalFilesDir(str);
        if (externalFilesDir != null) {
            if (externalFilesDir.exists() && externalFilesDir.isDirectory()) {
                return externalFilesDir;
            }
            externalFilesDir.mkdirs();
            return externalFilesDir;
        } else if (TextUtils.isEmpty(str)) {
            return JdSdk.getInstance().getApplication().getFilesDir();
        } else {
            File file = new File(JdSdk.getInstance().getApplication().getFilesDir() + File.separator + str);
            if (file.exists() && file.isDirectory()) {
                return file;
            }
            file.mkdirs();
            return file;
        }
    }

    public static String getPermissionName(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            HashMap<String, String> hashMap = permissionNameDefaults;
            return (hashMap == null || !hashMap.containsKey(str)) ? "" : permissionNameDefaults.get(str);
        }
        return str2;
    }

    public static String getPermissionTipMsg(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            HashMap<String, String> hashMap = permissionTipDefaults;
            return (hashMap == null || !hashMap.containsKey(str)) ? "" : permissionTipDefaults.get(str);
        }
        return str2;
    }

    private static String getPermissionTitle(String str) {
        HashMap<String, String> hashMap = permissionTitles;
        return (hashMap == null || !hashMap.containsKey(str)) ? "" : permissionTitles.get(str);
    }

    private static int getRequestCode() {
        int incrementAndGet = requestCodeAtomic.incrementAndGet();
        if (Build.VERSION.SDK_INT >= 23) {
            if ((incrementAndGet & InputDeviceCompat.SOURCE_ANY) == 0) {
                return incrementAndGet;
            }
            requestCodeAtomic.set(1);
        } else if (((-65536) & incrementAndGet) == 0) {
            return incrementAndGet;
        } else {
            requestCodeAtomic.set(1);
        }
        return 1;
    }

    private static PermissionItem getthePermissionItem(String str, List<PermissionItem> list) {
        if (list != null && !list.isEmpty()) {
            for (PermissionItem permissionItem : list) {
                if (TextUtils.equals(str, permissionItem.getPermission())) {
                    return permissionItem;
                }
            }
        }
        return null;
    }

    public static void goToAppSetting(PermissionResultCallBack permissionResultCallBack) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", JdSdk.getInstance().getApplication().getPackageName(), null));
            intent.setFlags(268435456);
            JdSdk.getInstance().getApplication().startActivity(intent);
        } catch (Throwable unused) {
            Toast.makeText(JdSdk.getInstance().getApplication(), JdSdk.getInstance().getApplication().getString(R.string.permission_goto_setting), 0).show();
        }
        if (permissionResultCallBack != null) {
            permissionResultCallBack.onOpenSetting();
        }
    }

    private static boolean handleAsCommon() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDPrivacy", "GetInstalledApps", "handleAsCommon", "0"));
    }

    private static boolean handleAsDangerous() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDPrivacy", "GetInstalledApps", "handleAsDangerous", "0"));
    }

    public static void handleFunctionPermissionDialog(Activity activity, List<String> list, List<String> list2, PermissionResultCallBack permissionResultCallBack, boolean z, List<String> list3, String str, String str2) {
        String str3;
        String str4;
        String str5;
        boolean z2;
        boolean z3;
        boolean z4;
        String[] strArr;
        String str6;
        Bundle bundle;
        if (permissionResultCallBack != null) {
            try {
                permissionResultCallBack.isInitiative = z;
            } catch (Throwable th) {
                if (OKLog.D) {
                    OKLog.e(TAG, "new handleFunctionPermissionDialog - error " + th.toString());
                }
                ExceptionReporter.reportExceptionToBugly(th);
                return;
            }
        }
        String str7 = "";
        if (permissionResultCallBack == null || (bundle = permissionResultCallBack.params) == null) {
            str3 = "";
            str4 = str3;
            str5 = str4;
            z2 = true;
        } else {
            z2 = bundle.getBoolean(PARAM_SHOW_MSG_DIALOG, true);
            permissionResultCallBack.params.getBoolean(PARAM_SHOW_OPEN_SETTING_DIALOG, true);
            str3 = permissionResultCallBack.params.getString("moduleName", "");
            str4 = permissionResultCallBack.params.getString("className", "");
            str5 = permissionResultCallBack.params.getString("methodName", "");
        }
        String str8 = PERMISSION_FIRST + unionPermissions(list);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str3);
        sb2.append(str4);
        sb2.append(str5);
        sb.append((Object) sb2);
        sb.append(unionPermissions(list));
        String sb3 = sb.toString();
        boolean booleanvalue = PermissionFileUtils.getBooleanvalue(activity, str8, true);
        boolean shouldShowRationale = shouldShowRationale(activity, list);
        String[] strArr2 = (String[]) list.toArray(new String[list.size()]);
        if (OKLog.D) {
            OKLog.e(TAG, "new handleFunctionPermissionDialog -isInitiative: " + z);
            OKLog.e(TAG, "new handleFunctionPermissionDialog -showJDDialog: " + z2);
            OKLog.e(TAG, "new handleFunctionPermissionDialog -shouldShowRationale: " + shouldShowRationale);
            OKLog.e(TAG, "new handleFunctionPermissionDialog -key: " + str8);
            OKLog.e(TAG, "new handleFunctionPermissionDialog -isInitiativeKey: " + sb3);
            for (int i2 = 0; i2 < strArr2.length; i2++) {
                if (!PERMISSION_GET_INSTALLED_APPS.equals(strArr2[i2])) {
                    if (!(ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), strArr2[i2]) == 0)) {
                        OKLog.e(TAG, "new handleFunctionPermissionDialog -permissions " + i2 + LangUtils.SINGLE_SPACE + strArr2[i2]);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < list.size()) {
            PermissionItem permissionItem = new PermissionItem();
            String str9 = str7;
            permissionItem.setPermission(list.get(i3));
            String str10 = list.get(i3);
            if (list2 != null) {
                strArr = strArr2;
                if (i3 < list2.size()) {
                    str6 = list2.get(i3);
                    permissionItem.setPermissionName(getPermissionName(str10, str6));
                    permissionItem.setPermissionTip(getPermissionTipMsg(list.get(i3), (list3 != null || i3 >= list3.size()) ? str9 : list3.get(i3)));
                    arrayList.add(permissionItem);
                    i3++;
                    strArr2 = strArr;
                    str7 = str9;
                }
            } else {
                strArr = strArr2;
            }
            str6 = str9;
            permissionItem.setPermissionName(getPermissionName(str10, str6));
            permissionItem.setPermissionTip(getPermissionTipMsg(list.get(i3), (list3 != null || i3 >= list3.size()) ? str9 : list3.get(i3)));
            arrayList.add(permissionItem);
            i3++;
            strArr2 = strArr;
            str7 = str9;
        }
        String[] strArr3 = strArr2;
        if (Build.VERSION.SDK_INT >= 29) {
            if (!z && PermissionFileUtils.getBooleanvalue(activity, sb3, true)) {
                if (OKLog.D) {
                    OKLog.e(TAG, "new handleFunctionPermissionDialog >29-\u975e\u4e3b\u52a8\uff0c\u4e14\u6ca1\u88ab\u62d2\u7edd\u8fc7");
                }
                if (!booleanvalue && !shouldShowRationale) {
                    if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                        ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType(TYPE_LEAD);
                    }
                    showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                    z4 = false;
                    PermissionFileUtils.savePrivacy(activity, sb3, z4);
                }
                if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                    ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType("sys");
                }
                z4 = false;
                showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr3);
                PermissionFileUtils.savePrivacy(activity, sb3, z4);
            } else if (!z) {
                if (permissionResultCallBack != null) {
                    permissionResultCallBack.onIgnored();
                    if (OKLog.D) {
                        OKLog.e(TAG, "new handleFunctionPermissionDialog >29-\u975e\u4e3b\u52a8\uff0c\u88ab\u51b3\u7edd\u8fc7\uff0c\u5c31\u76f4\u63a5\u5ffd\u7565");
                    }
                }
            } else {
                int intValue = PermissionFileUtils.getIntValue(activity, str8 + "_RejectTimes", 0);
                if (intValue < 2 && (intValue != 1 || shouldShowRationale)) {
                    if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                        ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType("sys");
                    }
                    showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr3);
                    return;
                }
                Boolean bool = dialogsShowingStatus.get(str8);
                if (bool == null || !bool.booleanValue()) {
                    if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                        ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType(TYPE_LEAD);
                    }
                    showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                } else if (permissionResultCallBack != null) {
                    permissionResultCallBack.onIgnored();
                    if (OKLog.D) {
                        OKLog.e(TAG, "new handleFunctionPermissionDialog >29-key:" + str8 + "-onIgnored");
                    }
                }
            }
        } else if (!z && PermissionFileUtils.getBooleanvalue(activity, sb3, true)) {
            if (OKLog.D) {
                OKLog.e(TAG, "new handleFunctionPermissionDialog -\u975e\u4e3b\u52a8\uff0c\u4e14\u6ca1\u88ab\u62d2\u7edd\u8fc7");
            }
            if (!booleanvalue && !shouldShowRationale) {
                if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                    ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType(TYPE_LEAD);
                }
                showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                z3 = false;
                PermissionFileUtils.savePrivacy(activity, sb3, z3);
            }
            if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType("sys");
            }
            z3 = false;
            showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr3);
            PermissionFileUtils.savePrivacy(activity, sb3, z3);
        } else if (!z) {
            if (permissionResultCallBack != null) {
                permissionResultCallBack.onIgnored();
                if (OKLog.D) {
                    OKLog.e(TAG, "new handleFunctionPermissionDialog -\u975e\u4e3b\u52a8\uff0c\u88ab\u51b3\u7edd\u8fc7\uff0c\u5c31\u76f4\u63a5\u5ffd\u7565");
                }
            }
        } else {
            if (!booleanvalue && !shouldShowRationale) {
                if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                    ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType(TYPE_LEAD);
                }
                showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                return;
            }
            Boolean bool2 = dialogsShowingStatus.get(str8);
            if (bool2 == null || !bool2.booleanValue()) {
                if (permissionResultCallBack instanceof PermissionSceneCallbackWrapper) {
                    ((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.diaType("sys");
                }
                showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr3);
            } else if (permissionResultCallBack != null) {
                permissionResultCallBack.onIgnored();
                if (OKLog.D) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("new handleFunctionPermissionDialog --isFirst-");
                    String str11 = DYConstants.DY_TRUE;
                    sb4.append(booleanvalue ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
                    sb4.append("- shouldShowRationale-");
                    if (!shouldShowRationale) {
                        str11 = DYConstants.DY_FALSE;
                    }
                    sb4.append(str11);
                    OKLog.e(TAG, sb4.toString());
                }
            }
        }
    }

    @Deprecated
    public static boolean hasGrantedBackgroundLocation() {
        Bundle generateBundle = generateBundle(AttributionReporter.SYSTEM_PERMISSION, AttributionReporter.SYSTEM_PERMISSION, "hasGrantedBackgroundLocation");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            return hasGrantedPermissions(null, generateBundle, Permission29.location, false, null);
        }
        if (i2 >= 26) {
            return hasGrantedPermissions(null, generateBundle, Permission26.location, false, null);
        }
        return hasPermission(null, generateBundle, "android.permission.ACCESS_COARSE_LOCATION", false, null);
    }

    public static boolean hasGrantedCamera(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        return hasPermission(activity, bundle, "android.permission.CAMERA", permissionResultCallBack);
    }

    public static boolean hasGrantedContacts(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        return hasPermission(activity, bundle, "android.permission.READ_CONTACTS", permissionResultCallBack);
    }

    public static boolean hasGrantedExternalStorage(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        if (Build.VERSION.SDK_INT >= 26) {
            return hasGrantedPermissions(activity, bundle, Permission26.storage, true, permissionResultCallBack);
        }
        return hasPermission(activity, bundle, "android.permission.WRITE_EXTERNAL_STORAGE", permissionResultCallBack);
    }

    public static boolean hasGrantedGetInstalledApps(Bundle bundle) {
        return true;
    }

    public static boolean hasGrantedLocation(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        if (Build.VERSION.SDK_INT >= 26) {
            return hasGrantedPermissions(activity, bundle, Permission26.location, true, permissionResultCallBack);
        }
        return hasPermission(activity, bundle, "android.permission.ACCESS_COARSE_LOCATION", permissionResultCallBack);
    }

    public static boolean hasGrantedPermissions(Activity activity, Bundle bundle, String[] strArr, boolean z, PermissionResultCallBack permissionResultCallBack) {
        boolean z2;
        String str;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (checkParam(activity, bundle, z) && checkPermission(strArr)) {
            if (permissionResultCallBack != null) {
                permissionResultCallBack.params = bundle;
            }
            for (String str2 : strArr) {
                if (TextUtils.equals(str2, "android.permission.CAMERA")) {
                    bundle.putString(PARAM_REQUEST_TIP_MESSAGE, JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
                    bundle.putBoolean(PARAM_SHOW_MSG_DIALOG, true);
                }
            }
            String string = bundle.getString(PARAM_REQUEST_TIP_MESSAGE);
            String string2 = bundle.getString(PARAM_REQUEST_TIP_CANCEL);
            String string3 = bundle.getString(PARAM_REQUEST_TIP_CONFIRM);
            boolean z3 = bundle.getBoolean(PARAM_USER_INITIATIVE, defaultUserInitiative(strArr));
            String[] stringArray = bundle.getStringArray(PARAM_PERMISSION_NAMES);
            String[] stringArray2 = bundle.getStringArray(PARAM_PERMISSION_TIPS);
            if (strArr != null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                boolean z4 = bIncludea(strArr, Permission26.storage) || bIncludea(strArr, Permission26.phone) || bIncludea(strArr, Permission26.location);
                int i2 = 0;
                boolean z5 = true;
                while (i2 < strArr.length) {
                    String str3 = strArr[i2];
                    if (PERMISSION_GET_INSTALLED_APPS.equals(str3)) {
                        z2 = checkInstalledAppsPermission();
                    } else {
                        z2 = ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), strArr[i2]) == 0;
                    }
                    if (!z2) {
                        arrayList.add(str3);
                        String str4 = "";
                        if (z4) {
                            String str5 = strArr[i2];
                            if (stringArray != null) {
                                str = "";
                                str4 = stringArray[0];
                            } else {
                                str = "";
                            }
                            arrayList2.add(getPermissionName(str5, str4));
                            arrayList3.add(getPermissionTipMsg(strArr[i2], stringArray2 != null ? stringArray2[0] : str));
                        } else {
                            arrayList2.add(getPermissionName(strArr[i2], (stringArray == null || i2 >= stringArray.length) ? "" : stringArray[i2]));
                            arrayList3.add(getPermissionTipMsg(strArr[i2], (stringArray2 == null || i2 >= stringArray2.length) ? "" : stringArray2[i2]));
                        }
                    }
                    z5 &= z2;
                    i2++;
                }
                if (z && !arrayList.isEmpty()) {
                    requestPermission(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), (String[]) arrayList3.toArray(new String[arrayList3.size()]), permissionResultCallBack, z3, string, string2, string3);
                }
                return z5;
            }
            return true;
        }
        return false;
    }

    @Deprecated
    public static boolean hasGrantedPhoneCall(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        return false;
    }

    @Deprecated
    public static boolean hasGrantedPhoneCall(Bundle bundle) {
        return false;
    }

    public static boolean hasGrantedPhoneState(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        if (Build.VERSION.SDK_INT >= 26) {
            return hasGrantedPermissions(activity, bundle, Permission26.phone, true, permissionResultCallBack);
        }
        return hasPermission(activity, bundle, "android.permission.READ_PHONE_STATE", permissionResultCallBack);
    }

    public static boolean hasGrantedRecordAudio(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        return hasPermission(activity, bundle, "android.permission.RECORD_AUDIO", permissionResultCallBack);
    }

    public static boolean hasGrantedSms(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        return hasPermission(activity, bundle, "android.permission.READ_SMS", permissionResultCallBack);
    }

    private static boolean hasLocationPermissionInner() {
        Bundle generateBundle = generateBundle(AttributionReporter.SYSTEM_PERMISSION, AttributionReporter.SYSTEM_PERMISSION, "hasLocationPermissionInner", false);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            return hasGrantedPermissions(null, generateBundle, Permission29.location, false, null);
        }
        if (i2 >= 26) {
            return hasGrantedPermissions(null, generateBundle, Permission26.location, false, null);
        }
        return hasPermission(null, generateBundle, "android.permission.ACCESS_COARSE_LOCATION", false, null);
    }

    public static boolean hasLocationPermissionWithScene(String str, String str2) {
        if (!hasLocationPermissionInner()) {
            String str3 = " with scene no location. " + str;
            return false;
        }
        boolean lbsSceneSwitch = LBSSceneSwitchHelper.getLbsSceneSwitch(str);
        String str4 = " with scene is : " + lbsSceneSwitch;
        return lbsSceneSwitch;
    }

    public static SceneStatus hasLocationPermissionWithSceneV2(String str, String str2) {
        if (!hasLocationPermissionInner()) {
            return SceneStatus.NO_SYSTEM_PERMISSION;
        }
        if (!LBSSceneSwitchHelper.getLbsSceneSwitch(str)) {
            return SceneStatus.NO_SCENE_PERMISSION;
        }
        return SceneStatus.HAS_ALL_PERMISSION;
    }

    @Deprecated
    public static boolean hasNecessaryPermissions() {
        return false;
    }

    public static boolean hasPermission(Activity activity, Bundle bundle, String str, PermissionResultCallBack permissionResultCallBack) {
        return hasPermission(activity, bundle, str, true, permissionResultCallBack);
    }

    private static boolean isAllGranted(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (-1 == i2) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDangerous() {
        return false;
    }

    private static boolean isDeviceInInstalledAppsWhiteList() {
        Map<String, String> configs = JDMobileConfig.getInstance().getConfigs(ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE, "InstalledApps");
        if (configs != null && "1".equals(configs.get("isOpen"))) {
            String str = configs.get("granted");
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    int i2 = Build.VERSION.SDK_INT;
                    String deviceBrand = BaseInfo.getDeviceBrand();
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        Object obj = jSONArray.get(i3);
                        if (obj instanceof JSONObject) {
                            if (i2 == ((JSONObject) obj).getInt(TouchesHelper.TARGET_KEY)) {
                                Object obj2 = ((JSONObject) obj).get(CartConstant.KEY_YANBAO_BRANDS);
                                if (obj2 instanceof JSONArray) {
                                    for (int i4 = 0; i4 < ((JSONArray) obj2).length(); i4++) {
                                        if (deviceBrand.equalsIgnoreCase(((JSONArray) obj2).get(i4).toString())) {
                                            return true;
                                        }
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private static boolean isHuaWei() {
        return false;
    }

    private static boolean isLocSceneRequest(List<String> list) {
        if (list == null) {
            return false;
        }
        List asList = Arrays.asList(Permission29.location);
        if (asList.containsAll(list)) {
            return list.containsAll(asList) || list.size() == 1;
        }
        return false;
    }

    private static boolean isSecurePermission(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "oem_installed_apps_runtime_permission_enable", 0) == 1;
    }

    public static void lbsPermissionReport(JSONObject jSONObject) {
        if (jSONObject != null) {
            JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplicationContext(), "", "", "", "lbs_scene_request_box", jSONObject.toString(), "", "", "");
            String str = "event id is: lbs_scene_request_box param is : " + jSONObject.toString();
        }
    }

    public static String manualRequestLocationPermissionWithScene(final Activity activity, PermissionSceneCallback permissionSceneCallback, final String str, String str2, final String str3) {
        String str4 = "manual activity is : " + activity + " scene : " + str + " buss: " + str2 + " content: " + str3;
        final LBSReportBuilder reportBuilder = LBSReportBuilder.getReportBuilder();
        reportBuilder.reqType("manual").scene(str);
        if (!LimitedPermissionContract.isSceneLegal(str)) {
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onSceneIsEmpty();
            }
            reportBuilder.diaChoice("error");
            lbsPermissionReport(reportBuilder.build());
            return "0";
        } else if (hasLocationPermissionWithScene(str, str2)) {
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onGranted();
            }
            reportBuilder.diaChoice("error");
            lbsPermissionReport(reportBuilder.build());
            return "0";
        } else {
            final PermissionSceneCallbackWrapper permissionSceneCallbackWrapper = new PermissionSceneCallbackWrapper(str, permissionSceneCallback, reportBuilder);
            if (!hasLocationPermissionInner()) {
                Bundle generateBundle = generateBundle(AttributionReporter.SYSTEM_PERMISSION, AttributionReporter.SYSTEM_PERMISSION, "requestLocationPermissionWithScene", true);
                ArrayList arrayList = new ArrayList();
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 29) {
                    arrayList.addAll(Arrays.asList(Permission29.location));
                } else if (i2 >= 26) {
                    arrayList.addAll(Arrays.asList(Permission26.location));
                } else {
                    arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
                }
                String checkSceneContent = checkSceneContent(str, str3);
                String str5 = "scene content is : " + checkSceneContent;
                requestPermission(activity, generateBundle, arrayList, permissionSceneCallbackWrapper, Collections.singletonList(sceneLocTitle), Collections.singletonList(checkSceneContent));
                if (i2 < 23) {
                    if (permissionSceneCallback != null) {
                        permissionSceneCallback.onAgree();
                    }
                    return "0";
                }
                return "1";
            }
            handler.post(new Runnable() { // from class: com.jingdong.common.permission.PermissionHelper.6
                @Override // java.lang.Runnable
                public void run() {
                    reportBuilder.diaType("scene");
                    String checkSceneContent2 = PermissionHelper.checkSceneContent(str, str3);
                    String str6 = "in scene dialog scene content is : " + checkSceneContent2;
                    SceneDialogHelper.showSceneDialog(activity, checkSceneContent2, PermissionConstants.LBS_ALL_SCENE_CONTENT, false, new SceneDialogHelper.SceneDialogClickListener() { // from class: com.jingdong.common.permission.PermissionHelper.6.1
                        {
                            AnonymousClass6.this = this;
                        }

                        @Override // com.jingdong.common.permission.SceneDialogHelper.SceneDialogClickListener
                        public void onClick(boolean z, boolean z2) {
                            if (!z) {
                                LBSSceneSwitchHelper.saveLbsSceneSwitch(str, z2);
                                if (z2) {
                                    reportBuilder.diaChoice(PermissionHelper.CHOICE_YES);
                                    permissionSceneCallbackWrapper.onAgree();
                                } else {
                                    reportBuilder.diaChoice("cancel");
                                    permissionSceneCallbackWrapper.onDisagree();
                                }
                                PermissionHelper.lbsPermissionReport(reportBuilder.build());
                                return;
                            }
                            LBSSceneSwitchHelper.saveAllLbsSceneSwtich(z2);
                        }
                    });
                }
            });
            return "1";
        }
    }

    public static void onActivityCreate() {
        onActivityDestroy();
    }

    public static void onActivityDestroy() {
        if (Build.VERSION.SDK_INT < 23) {
        }
    }

    public static void onRequestPermissionsResult(Activity activity, int i2, String[] strArr, int[] iArr) {
        int i3;
        String str;
        boolean z;
        Bundle bundle;
        Bundle bundle2;
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (strArr != null) {
            for (String str2 : strArr) {
                arrayList.add(str2);
            }
        }
        boolean isAllGranted = isAllGranted(iArr);
        try {
            List<CarePermissionResultCallBack> list = carePermissionResultCallBacks;
            if (list != null && list.size() > 0) {
                for (CarePermissionResultCallBack carePermissionResultCallBack : carePermissionResultCallBacks) {
                    if (carePermissionResultCallBack != null && carePermissionResultCallBack.isAccept(arrayList)) {
                        if (carePermissionResultCallBack.isGranted(activity)) {
                            carePermissionResultCallBack.onGranted();
                        } else {
                            carePermissionResultCallBack.onDenied();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        SparseArray<PermissionResultCallBack> sparseArray = callBacks;
        String str3 = null;
        final PermissionResultCallBack permissionResultCallBack = sparseArray != null ? sparseArray.get(i2) : null;
        if (permissionResultCallBack != null) {
            if (isAllGranted) {
                permissionResultCallBack.onGranted();
            } else {
                permissionResultCallBack.onDenied();
            }
        }
        List<PermissionItem> list2 = permissionItemHashMap.get(Integer.valueOf(i2));
        permissionItemHashMap.remove(Integer.valueOf(i2));
        SparseArray<PermissionResultCallBack> sparseArray2 = callBacks;
        if (sparseArray2 != null) {
            sparseArray2.remove(i2);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            final String str4 = PERMISSION_FIRST + unionPermissions(arrayList);
            try {
                i3 = PermissionFileUtils.getIntValue(activity, str4 + "_RejectTimes", 0);
            } catch (Exception unused2) {
                i3 = 0;
            }
            if (!isAllGranted) {
                i3++;
                try {
                    PermissionFileUtils.savePrivacy(activity, str4 + "_RejectTimes", i3);
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            } else if (i3 > 0) {
                try {
                    PermissionFileUtils.savePrivacy(activity, str4 + "_RejectTimes", 0);
                } catch (Exception e3) {
                    ExceptionReporter.reportExceptionToBugly(e3);
                }
            }
            boolean z2 = (permissionResultCallBack == null || (bundle2 = permissionResultCallBack.params) == null) ? true : bundle2.getBoolean(PARAM_SHOW_OPEN_SETTING_DIALOG, true);
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("onRequestPermissionsResult");
                sb.append(i3);
                sb.append("-showOpenSettingDialog-");
                String str5 = DYConstants.DY_TRUE;
                sb.append(z2 ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
                sb.append("-isAllGranted:");
                if (!isAllGranted) {
                    str5 = DYConstants.DY_FALSE;
                }
                sb.append(str5);
                OKLog.e(TAG, sb.toString());
            }
            if (i3 < 3 || isAllGranted || !z2) {
                return;
            }
            Boolean bool = dialogsShowingStatus.get(str4);
            if (bool == null || !bool.booleanValue()) {
                if (permissionResultCallBack == null || (bundle = permissionResultCallBack.params) == null) {
                    str = null;
                } else {
                    str3 = bundle.getString(PARAM_REQUEST_TIP_CANCEL);
                    str = permissionResultCallBack.params.getString(PARAM_REQUEST_TIP_CONFIRM);
                }
                String string = !TextUtils.isEmpty(str3) ? str3 : JdSdk.getInstance().getApplication().getString(R.string.permission_dialog_btn_cancel);
                String string2 = !TextUtils.isEmpty(str) ? str : JdSdk.getInstance().getApplication().getString(R.string.permission_dialog_btn_open);
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                if (strArr != null) {
                    for (int i4 = 0; i4 < strArr.length; i4++) {
                        String str6 = strArr[i4];
                        if (PERMISSION_GET_INSTALLED_APPS.equals(str6)) {
                            z = checkInstalledAppsPermission();
                        } else {
                            z = ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), str6) == 0;
                        }
                        if (!z) {
                            arrayList2.add(str6);
                            PermissionItem permissionItem = getthePermissionItem(str6, list2);
                            arrayList3.add(permissionItem != null ? permissionItem.getPermissionName() : getPermissionName(strArr[i4], ""));
                            arrayList4.add(permissionItem != null ? permissionItem.getPermissionTip() : getPermissionTipMsg(strArr[i4], ""));
                        }
                    }
                }
                if (OKLog.D) {
                    OKLog.e(TAG, "onRequestPermissionsResult -dialogCancel:" + string + "-dialogConfirm:" + string2);
                }
                final JDDialog createJDSettingDialog = createJDSettingDialog(activity, arrayList2, arrayList3, arrayList4, string, string2);
                createJDSettingDialog.setCancelable(false);
                createJDSettingDialog.setCanceledOnTouchOutside(false);
                createJDSettingDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.PermissionHelper.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        PermissionHelper.dialogsShowingStatus.remove(str4);
                        createJDSettingDialog.dismiss();
                        PermissionResultCallBack permissionResultCallBack2 = permissionResultCallBack;
                        if (permissionResultCallBack2 != null) {
                            permissionResultCallBack2.onCanceled();
                        }
                    }
                });
                createJDSettingDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.PermissionHelper.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        PermissionHelper.dialogsShowingStatus.remove(str4);
                        createJDSettingDialog.dismiss();
                        PermissionHelper.goToAppSetting(permissionResultCallBack);
                    }
                });
                dialogsShowingStatus.put(str4, Boolean.TRUE);
                createJDSettingDialog.show();
            }
        }
    }

    private static void registerCallBack(int i2, PermissionResultCallBack permissionResultCallBack) {
        SparseArray<PermissionResultCallBack> sparseArray;
        if (permissionResultCallBack == null || (sparseArray = callBacks) == null) {
            return;
        }
        sparseArray.put(i2, permissionResultCallBack);
    }

    public static void removeCarePermissionResultCallBack(CarePermissionResultCallBack carePermissionResultCallBack) {
        List<CarePermissionResultCallBack> list = carePermissionResultCallBacks;
        if (list == null || !list.contains(carePermissionResultCallBack)) {
            return;
        }
        carePermissionResultCallBacks.remove(carePermissionResultCallBack);
    }

    private static List<ListPermissionEntity> removeDuplicate(List<ListPermissionEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!arrayList.contains(list.get(i2))) {
                arrayList.add(list.get(i2));
            }
        }
        return arrayList;
    }

    @Deprecated
    public static void requestBackgroundLocation(Activity activity, PermissionResultCallBack permissionResultCallBack) {
        Bundle generateBundle = generateBundle(AttributionReporter.SYSTEM_PERMISSION, AttributionReporter.SYSTEM_PERMISSION, "requestBackgroundLocation");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            hasGrantedPermissions(activity, generateBundle, Permission29.location, true, permissionResultCallBack);
        } else if (i2 >= 29) {
            hasGrantedPermissions(activity, generateBundle, Permission29.location, true, permissionResultCallBack);
        } else if (i2 >= 26) {
            hasGrantedPermissions(activity, generateBundle, Permission26.location, true, permissionResultCallBack);
        } else {
            hasPermission(activity, generateBundle, "android.permission.ACCESS_COARSE_LOCATION", permissionResultCallBack);
        }
    }

    public static void requestGetInstalledAppsPermission(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
        if (permissionResultCallBack == null) {
            return;
        }
        permissionResultCallBack.onGranted();
    }

    public static String requestLocationPermissionWithScene(final Activity activity, PermissionSceneCallback permissionSceneCallback, final String str, String str2, final String str3) {
        String str4 = " activity is : " + activity + " scene : " + str + " buss: " + str2 + " content: " + str3;
        final LBSReportBuilder reportBuilder = LBSReportBuilder.getReportBuilder();
        reportBuilder.scene(str).reqType("auto");
        if (!LimitedPermissionContract.isSceneLegal(str)) {
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onSceneIsEmpty();
            }
            reportBuilder.diaChoice("error");
            lbsPermissionReport(reportBuilder.build());
            return "0";
        } else if (hasLocationPermissionWithScene(str, str2)) {
            if (permissionSceneCallback != null) {
                permissionSceneCallback.onGranted();
            }
            reportBuilder.diaChoice("error");
            lbsPermissionReport(reportBuilder.build());
            return "0";
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - PermissionFileUtils.getScenePreShowTime(str) < TIME_48_HOUR) {
                if (permissionSceneCallback != null) {
                    permissionSceneCallback.onTimeLimited();
                }
                reportBuilder.diaChoice("error");
                lbsPermissionReport(reportBuilder.build());
                return "0";
            }
            PermissionFileUtils.updateSceneShowTime(str, currentTimeMillis);
            final PermissionSceneCallbackWrapper permissionSceneCallbackWrapper = new PermissionSceneCallbackWrapper(str, permissionSceneCallback, reportBuilder);
            if (!hasLocationPermissionInner()) {
                Bundle generateBundle = generateBundle(AttributionReporter.SYSTEM_PERMISSION, AttributionReporter.SYSTEM_PERMISSION, "requestLocationPermissionWithScene", true);
                ArrayList arrayList = new ArrayList();
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 29) {
                    arrayList.addAll(Arrays.asList(Permission29.location));
                } else if (i2 >= 26) {
                    arrayList.addAll(Arrays.asList(Permission26.location));
                } else {
                    arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
                }
                String checkSceneContent = checkSceneContent(str, str3);
                String str5 = "scene content is : " + checkSceneContent;
                requestPermission(activity, generateBundle, arrayList, permissionSceneCallbackWrapper, Collections.singletonList(sceneLocTitle), Collections.singletonList(checkSceneContent));
                if (i2 < 23) {
                    if (permissionSceneCallback != null) {
                        permissionSceneCallback.onAgree();
                    }
                    return "0";
                }
                return "1";
            }
            handler.post(new Runnable() { // from class: com.jingdong.common.permission.PermissionHelper.5
                @Override // java.lang.Runnable
                public void run() {
                    String checkSceneContent2 = PermissionHelper.checkSceneContent(str, str3);
                    String str6 = "in scene dialog scene content is : " + checkSceneContent2;
                    reportBuilder.diaType("scene");
                    SceneDialogHelper.showSceneDialog(activity, checkSceneContent2, PermissionConstants.LBS_ALL_SCENE_CONTENT, false, new SceneDialogHelper.SceneDialogClickListener() { // from class: com.jingdong.common.permission.PermissionHelper.5.1
                        {
                            AnonymousClass5.this = this;
                        }

                        @Override // com.jingdong.common.permission.SceneDialogHelper.SceneDialogClickListener
                        public void onClick(boolean z, boolean z2) {
                            if (!z) {
                                LBSSceneSwitchHelper.saveLbsSceneSwitch(str, z2);
                                if (z2) {
                                    reportBuilder.diaChoice(PermissionHelper.CHOICE_YES);
                                    permissionSceneCallbackWrapper.onAgree();
                                } else {
                                    reportBuilder.diaChoice("cancel");
                                    permissionSceneCallbackWrapper.onDisagree();
                                }
                                PermissionHelper.lbsPermissionReport(reportBuilder.build());
                                return;
                            }
                            LBSSceneSwitchHelper.saveAllLbsSceneSwtich(z2);
                        }
                    });
                }
            });
            return "1";
        }
    }

    @Deprecated
    public static void requestNecessaryPermissions(Activity activity, Bundle bundle, PermissionResultCallBack permissionResultCallBack) {
    }

    private static void requestPermission(final Activity activity, final String[] strArr, final String[] strArr2, final String[] strArr3, final PermissionResultCallBack permissionResultCallBack, final boolean z, final String str, final String str2, final String str3) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (!LimitedPermissionContract.shouldInterceptRequest(strArr, permissionResultCallBack != null ? permissionResultCallBack.params : null)) {
            handler.post(new Runnable() { // from class: com.jingdong.common.permission.PermissionHelper.3
                @Override // java.lang.Runnable
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    if (strArr != null) {
                        int i2 = 0;
                        while (true) {
                            String[] strArr4 = strArr;
                            if (i2 >= strArr4.length) {
                                break;
                            }
                            arrayList.add(strArr4[i2]);
                            String str4 = strArr[i2];
                            String[] strArr5 = strArr2;
                            String str5 = "";
                            arrayList2.add(PermissionHelper.getPermissionName(str4, strArr5 != null ? strArr5[i2] : ""));
                            String str6 = strArr[i2];
                            String[] strArr6 = strArr3;
                            if (strArr6 != null) {
                                str5 = strArr6[i2];
                            }
                            arrayList3.add(PermissionHelper.getPermissionTipMsg(str6, str5));
                            i2++;
                        }
                    }
                    if (arrayList.size() > 0) {
                        PermissionHelper.handleFunctionPermissionDialog(activity, PermissionHelper.generatePermissionMsgs(arrayList), arrayList, arrayList2, arrayList3, permissionResultCallBack, z, str, str2, str3);
                        return;
                    }
                    PermissionResultCallBack permissionResultCallBack2 = permissionResultCallBack;
                    if (permissionResultCallBack2 != null) {
                        permissionResultCallBack2.onGranted();
                    }
                }
            });
        } else if (permissionResultCallBack != null) {
            permissionResultCallBack.onCanceled();
        }
    }

    @Deprecated
    public static void setNecessaryPermissions(String[] strArr) {
    }

    private static boolean shouldShowRationale(Activity activity, List<String> list) {
        Iterator<String> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= ActivityCompat.shouldShowRequestPermissionRationale(activity, it.next());
        }
        return z;
    }

    private static void showDialogIKnow(Activity activity, List<String> list, List<String> list2, List<String> list3, PermissionResultCallBack permissionResultCallBack, String str, List<PermissionItem> list4, String[] strArr) {
        if (activity == null || list == null) {
            return;
        }
        JDDialog createJDTopListDialog = createJDTopListDialog(activity, list, list2, list3);
        createJDTopListDialog.setCancelable(true);
        createJDTopListDialog.setCanceledOnTouchOutside(true);
        tryGetPermission(activity, permissionResultCallBack, str, list4, strArr, createJDTopListDialog);
        createJDTopListDialog.show();
        if (OKLog.D) {
            OKLog.e(TAG, "showDialogIKnow -\u5b58\u5165 key\uff1a " + str);
        }
        if (OKLog.D) {
            OKLog.e(TAG, "showDialogIKnow -permissionsNeeded:" + list.toString());
        }
    }

    private static void showDialogToSetting(Activity activity, List<String> list, List<String> list2, List<String> list3, final PermissionResultCallBack permissionResultCallBack, final String str) {
        Bundle bundle;
        if (activity == null || list == null) {
            return;
        }
        final JDDialog createJDSettingDialog = createJDSettingDialog(activity, list, list2, list3, "\u53d6\u6d88", "\u53bb\u6253\u5f00");
        createJDSettingDialog.setCancelable(false);
        createJDSettingDialog.setCanceledOnTouchOutside(false);
        createJDSettingDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.PermissionHelper.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionHelper.dialogsShowingStatus.remove(str);
                if (OKLog.D) {
                    OKLog.e(PermissionHelper.TAG, "showDialogToSetting -\u79fb\u9664 key\uff1a " + str);
                }
                PermissionResultCallBack permissionResultCallBack2 = permissionResultCallBack;
                if (permissionResultCallBack2 instanceof PermissionSceneCallbackWrapper) {
                    ((PermissionSceneCallbackWrapper) permissionResultCallBack2).builder.diaChoice("cancel");
                    PermissionHelper.lbsPermissionReport(((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.build());
                }
                createJDSettingDialog.dismiss();
                PermissionResultCallBack permissionResultCallBack3 = permissionResultCallBack;
                if (permissionResultCallBack3 != null) {
                    permissionResultCallBack3.onCanceled();
                }
            }
        });
        createJDSettingDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.PermissionHelper.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionHelper.dialogsShowingStatus.remove(str);
                if (OKLog.D) {
                    OKLog.e(PermissionHelper.TAG, "showDialogToSetting -\u79fb\u9664 key\uff1a " + str);
                }
                createJDSettingDialog.dismiss();
                PermissionResultCallBack permissionResultCallBack2 = permissionResultCallBack;
                if (permissionResultCallBack2 instanceof PermissionSceneCallbackWrapper) {
                    ((PermissionSceneCallbackWrapper) permissionResultCallBack2).builder.diaChoice(JshopConst.JSKEY_CATE_OPEN);
                    PermissionHelper.lbsPermissionReport(((PermissionSceneCallbackWrapper) permissionResultCallBack).builder.build());
                }
                PermissionHelper.goToAppSetting(permissionResultCallBack);
            }
        });
        dialogsShowingStatus.put(str, Boolean.TRUE);
        createJDSettingDialog.show();
        if (permissionResultCallBack != null && (bundle = permissionResultCallBack.params) != null) {
            bundle.putBoolean(PARAM_SHOW_OPEN_SETTING_DIALOG, false);
        }
        if (OKLog.D) {
            OKLog.e(TAG, "showDialogToSetting -\u5b58\u5165 key\uff1a " + str);
        }
        if (OKLog.D) {
            OKLog.e(TAG, "showDialogToSetting -permissionsNeeded:" + list.toString());
        }
    }

    private static void tryGetPermission(Activity activity, PermissionResultCallBack permissionResultCallBack, String str, List<PermissionItem> list, String[] strArr, JDDialog jDDialog) {
        dialogsShowingStatus.remove(str);
        if (OKLog.D) {
            OKLog.e(TAG, "showDialogIKnow -\u79fb\u9664 key\uff1a " + str);
        }
        jDDialog.dismiss();
        int requestCode = getRequestCode();
        permissionItemHashMap.put(Integer.valueOf(requestCode), list);
        registerCallBack(requestCode, new PermissionResultCallbackWrapper(permissionResultCallBack, jDDialog));
        ActivityCompat.requestPermissions(activity, strArr, requestCode);
        try {
            PermissionFileUtils.savePrivacy((Context) activity, str, false);
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    private static String unionPermissions(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str = list.get(i2);
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                    if (size - 1 > i2) {
                        sb.append(ContainerUtils.FIELD_DELIMITER);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static boolean hasGrantedCamera(Bundle bundle) {
        return hasPermission(null, bundle, "android.permission.CAMERA", false, null);
    }

    public static boolean hasGrantedContacts(Bundle bundle) {
        return hasPermission(null, bundle, "android.permission.READ_CONTACTS", false, null);
    }

    public static boolean hasGrantedRecordAudio(Bundle bundle) {
        return hasPermission(null, bundle, "android.permission.RECORD_AUDIO", false, null);
    }

    public static boolean hasGrantedSms(Bundle bundle) {
        return hasPermission(null, bundle, "android.permission.READ_SMS", false, null);
    }

    public static boolean hasPermission(Bundle bundle, String str) {
        return hasPermission(null, bundle, str, false, null);
    }

    public static boolean hasPermission(Activity activity, Bundle bundle, String str, boolean z, PermissionResultCallBack permissionResultCallBack) {
        boolean z2;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (checkParam(activity, bundle, z)) {
            if (permissionResultCallBack != null) {
                permissionResultCallBack.params = bundle;
            }
            if (TextUtils.equals(str, "android.permission.CAMERA")) {
                bundle.putString(PARAM_REQUEST_TIP_MESSAGE, JdSdk.getInstance().getApplication().getString(R.string.permission_camera_full_msg));
                bundle.putBoolean(PARAM_SHOW_MSG_DIALOG, true);
            }
            String string = bundle.getString(PARAM_REQUEST_TIP_MESSAGE);
            String string2 = bundle.getString(PARAM_REQUEST_TIP_CANCEL);
            String string3 = bundle.getString(PARAM_REQUEST_TIP_CONFIRM);
            boolean z3 = bundle.getBoolean(PARAM_USER_INITIATIVE, defaultUserInitiative(new String[]{str}));
            if (PERMISSION_GET_INSTALLED_APPS.equals(str)) {
                z2 = checkInstalledAppsPermission();
            } else {
                z2 = ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), str) == 0;
            }
            String[] stringArray = bundle.getStringArray(PARAM_PERMISSION_NAMES);
            String[] stringArray2 = bundle.getStringArray(PARAM_PERMISSION_TIPS);
            if (!z2 && z) {
                requestPermission(activity, new String[]{str}, stringArray, stringArray2, permissionResultCallBack, z3, string, string2, string3);
            }
            return z2;
        }
        return false;
    }

    public static boolean hasGrantedExternalStorage(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            return hasGrantedPermissions(null, bundle, Permission26.storage, false, null);
        }
        return hasPermission(null, bundle, "android.permission.WRITE_EXTERNAL_STORAGE", false, null);
    }

    public static boolean hasGrantedLocation(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            return hasGrantedPermissions(null, bundle, Permission26.location, false, null);
        }
        return hasPermission(null, bundle, "android.permission.ACCESS_COARSE_LOCATION", false, null);
    }

    public static boolean hasGrantedPhoneState(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 26) {
            return hasGrantedPermissions(null, bundle, Permission26.phone, false, null);
        }
        return hasPermission(null, bundle, "android.permission.READ_PHONE_STATE", false, null);
    }

    @Deprecated
    public static Bundle generateBundle(String str, String str2, String str3) {
        return generateBundle(str, str2, str3, (Bundle) null);
    }

    public static Bundle generateBundle(String str, String str2, String str3, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("moduleName", str);
        bundle.putString("className", str2);
        bundle.putString("methodName", str3);
        bundle.putBoolean(PARAM_USER_INITIATIVE, z);
        return bundle;
    }

    public static void requestPermission(Activity activity, Bundle bundle, String str, PermissionResultCallBack permissionResultCallBack, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            if (permissionResultCallBack != null) {
                permissionResultCallBack.onCanceled();
                return;
            }
            return;
        }
        if (permissionResultCallBack != null) {
            permissionResultCallBack.params = bundle;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(str2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(str3);
        requestPermission(activity, bundle, arrayList, permissionResultCallBack, arrayList2, arrayList3);
    }

    public static Bundle generateBundle(Bundle bundle, String str, String str2, String str3, String[] strArr, String[] strArr2, boolean z) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("moduleName", str);
        bundle.putString("className", str2);
        bundle.putString("methodName", str3);
        bundle.putStringArray(PARAM_PERMISSION_NAMES, strArr);
        bundle.putStringArray(PARAM_PERMISSION_TIPS, strArr2);
        bundle.putBoolean(PARAM_USER_INITIATIVE, z);
        return bundle;
    }

    public static void requestPermission(final Activity activity, final Bundle bundle, final List<String> list, final PermissionResultCallBack permissionResultCallBack, final List<String> list2, final List<String> list3) {
        if (list == null || list.isEmpty()) {
            if (permissionResultCallBack != null) {
                permissionResultCallBack.onCanceled();
            }
        } else if (Build.VERSION.SDK_INT < 23) {
        } else {
            if (!LimitedPermissionContract.shouldInterceptRequest(list, bundle)) {
                handler.post(new Runnable() { // from class: com.jingdong.common.permission.PermissionHelper.4
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean z;
                        PermissionResultCallBack permissionResultCallBack2 = permissionResultCallBack;
                        if (permissionResultCallBack2 != null) {
                            permissionResultCallBack2.params = bundle;
                        }
                        Bundle bundle2 = bundle;
                        if (bundle2 == null) {
                            List list4 = list;
                            z = PermissionHelper.defaultUserInitiative((String[]) list4.toArray(new String[list4.size()]));
                        } else {
                            List list5 = list;
                            z = bundle2.getBoolean(PermissionHelper.PARAM_USER_INITIATIVE, PermissionHelper.defaultUserInitiative((String[]) list5.toArray(new String[list5.size()])));
                        }
                        PermissionHelper.handleFunctionPermissionDialog(activity, list, list2, permissionResultCallBack, z, list3, "", "\u6211\u77e5\u9053\u4e86");
                    }
                });
            } else if (permissionResultCallBack != null) {
                permissionResultCallBack.onCanceled();
            }
        }
    }

    @Deprecated
    public static Bundle generateBundle(Bundle bundle, String str, String str2, String str3, String[] strArr, String[] strArr2) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("moduleName", str);
        bundle.putString("className", str2);
        bundle.putString("methodName", str3);
        bundle.putStringArray(PARAM_PERMISSION_NAMES, strArr);
        bundle.putStringArray(PARAM_PERMISSION_TIPS, strArr2);
        return bundle;
    }

    public static boolean hasPermission(Activity activity, String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (PERMISSION_GET_INSTALLED_APPS.equals(str)) {
            return checkInstalledAppsPermission();
        }
        return ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), str) == 0;
    }

    public static Bundle generateBundle(Bundle bundle, String str, String str2, String str3, List<PermissionItem> list) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("moduleName", str);
        bundle.putString("className", str2);
        bundle.putString("methodName", str3);
        return bundle;
    }

    public static boolean hasPermission(Activity activity, List<String> list) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 23 && list != null && !list.isEmpty()) {
            for (String str : list) {
                if (PERMISSION_GET_INSTALLED_APPS.equals(str)) {
                    z = checkInstalledAppsPermission();
                } else {
                    z = ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), str) == 0;
                }
                if (!z) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void handleFunctionPermissionDialog(Activity activity, String str, List<String> list, List<String> list2, List<String> list3, PermissionResultCallBack permissionResultCallBack, boolean z, String str2, String str3, String str4) {
        String str5;
        String str6;
        String str7;
        boolean z2;
        Bundle bundle;
        if (permissionResultCallBack != null) {
            try {
                permissionResultCallBack.isInitiative = z;
            } catch (Throwable th) {
                if (OKLog.D) {
                    OKLog.e(TAG, "old handleFunctionPermissionDialog - error " + th.toString());
                }
                ExceptionReporter.reportExceptionToBugly(th);
                return;
            }
        }
        if (permissionResultCallBack == null || (bundle = permissionResultCallBack.params) == null) {
            str5 = "";
            str6 = str5;
            str7 = str6;
            z2 = true;
        } else {
            z2 = bundle.getBoolean(PARAM_SHOW_MSG_DIALOG, true);
            permissionResultCallBack.params.getBoolean(PARAM_SHOW_OPEN_SETTING_DIALOG, true);
            str5 = permissionResultCallBack.params.getString("moduleName", "");
            str6 = permissionResultCallBack.params.getString("className", "");
            str7 = permissionResultCallBack.params.getString("methodName", "");
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str5);
        sb2.append(str6);
        sb2.append(str7);
        sb.append((Object) sb2);
        sb.append(unionPermissions(list));
        String sb3 = sb.toString();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            PermissionItem permissionItem = new PermissionItem();
            permissionItem.setPermission(list.get(i2));
            permissionItem.setPermissionName(getPermissionName(list.get(i2), (list2 == null || i2 >= list2.size()) ? "" : list2.get(i2)));
            permissionItem.setPermissionTip(getPermissionTipMsg(list.get(i2), (list3 == null || i2 >= list3.size()) ? "" : list3.get(i2)));
            arrayList.add(permissionItem);
            i2++;
        }
        String str8 = PERMISSION_FIRST + unionPermissions(list);
        boolean booleanvalue = PermissionFileUtils.getBooleanvalue(activity, str8, true);
        boolean shouldShowRationale = shouldShowRationale(activity, list);
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        if (OKLog.D) {
            OKLog.e(TAG, "old handleFunctionPermissionDialog -isInitiative:" + z);
            OKLog.e(TAG, "old handleFunctionPermissionDialog -shouldShowRationale: " + shouldShowRationale);
            OKLog.e(TAG, "old handleFunctionPermissionDialog -showJDDialog: " + z2);
            OKLog.e(TAG, "old handleFunctionPermissionDialog -key: " + str8);
            OKLog.e(TAG, "old handleFunctionPermissionDialog -isInitiativeKey: " + sb3);
            for (int i3 = 0; i3 < strArr.length; i3++) {
                if (!PERMISSION_GET_INSTALLED_APPS.equals(strArr[i3])) {
                    if (!(ContextCompat.checkSelfPermission(JdSdk.getInstance().getApplication(), strArr[i3]) == 0)) {
                        OKLog.e(TAG, "old handleFunctionPermissionDialog -permissions " + i3 + LangUtils.SINGLE_SPACE + strArr[i3]);
                    }
                }
            }
        }
        int i4 = Build.VERSION.SDK_INT;
        String str9 = DYConstants.DY_TRUE;
        if (i4 < 29) {
            if (!z && PermissionFileUtils.getBooleanvalue(activity, sb3, true) && z2) {
                if (OKLog.D) {
                    OKLog.e(TAG, "old handleFunctionPermissionDialog -\u975e\u4e3b\u52a8\uff0c\u4e14\u6ca1\u88ab\u62d2\u7edd\u8fc7");
                }
                if (!booleanvalue && !shouldShowRationale) {
                    showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                    PermissionFileUtils.savePrivacy((Context) activity, sb3, false);
                }
                showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr);
                PermissionFileUtils.savePrivacy((Context) activity, sb3, false);
            } else if (!z && z2) {
                if (permissionResultCallBack != null) {
                    permissionResultCallBack.onIgnored();
                    if (OKLog.D) {
                        OKLog.e(TAG, "old handleFunctionPermissionDialog -\u975e\u4e3b\u52a8\uff0c\u88ab\u51b3\u7edd\u8fc7\uff0c\u5c31\u76f4\u63a5\u5ffd\u7565");
                    }
                }
            } else {
                if (!booleanvalue && !shouldShowRationale) {
                    showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                    return;
                }
                Boolean bool = dialogsShowingStatus.get(str8);
                if (bool == null || !bool.booleanValue()) {
                    if (z2) {
                        showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr);
                        return;
                    }
                    int requestCode = getRequestCode();
                    permissionItemHashMap.put(Integer.valueOf(requestCode), arrayList);
                    registerCallBack(requestCode, permissionResultCallBack);
                    ActivityCompat.requestPermissions(activity, strArr, requestCode);
                    PermissionFileUtils.savePrivacy((Context) activity, str8, false);
                } else if (permissionResultCallBack != null) {
                    permissionResultCallBack.onIgnored();
                    if (OKLog.D) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("old handleFunctionPermissionDialog --isFirst");
                        sb4.append(booleanvalue ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
                        sb4.append("- shouldShowRationale");
                        if (!shouldShowRationale) {
                            str9 = DYConstants.DY_FALSE;
                        }
                        sb4.append(str9);
                        OKLog.e(TAG, sb4.toString());
                    }
                }
            }
        } else if (!z2) {
            Boolean bool2 = dialogsShowingStatus.get(str8);
            if (bool2 == null || !bool2.booleanValue()) {
                int requestCode2 = getRequestCode();
                permissionItemHashMap.put(Integer.valueOf(requestCode2), arrayList);
                registerCallBack(requestCode2, permissionResultCallBack);
                ActivityCompat.requestPermissions(activity, strArr, requestCode2);
                PermissionFileUtils.savePrivacy((Context) activity, str8, false);
            } else if (permissionResultCallBack != null) {
                permissionResultCallBack.onIgnored();
                if (OKLog.D) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("old handleFunctionPermissionDialog >29-showJDDialog:");
                    if (!z2) {
                        str9 = DYConstants.DY_FALSE;
                    }
                    sb5.append(str9);
                    OKLog.e(TAG, sb5.toString());
                }
            }
        } else if (!z && PermissionFileUtils.getBooleanvalue(activity, sb3, true)) {
            if (OKLog.D) {
                OKLog.e(TAG, "old handleFunctionPermissionDialog >29-\u975e\u4e3b\u52a8\uff0c\u4e14\u6ca1\u88ab\u62d2\u7edd\u8fc7");
            }
            if (!booleanvalue && !shouldShowRationale) {
                showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
                PermissionFileUtils.savePrivacy((Context) activity, sb3, false);
            }
            showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr);
            PermissionFileUtils.savePrivacy((Context) activity, sb3, false);
        } else if (!z) {
            if (permissionResultCallBack != null) {
                permissionResultCallBack.onIgnored();
                if (OKLog.D) {
                    OKLog.e(TAG, "old handleFunctionPermissionDialog >29-\u975e\u4e3b\u52a8\uff0c\u88ab\u51b3\u7edd\u8fc7\uff0c\u5c31\u76f4\u63a5\u5ffd\u7565");
                }
            }
        } else {
            int intValue = PermissionFileUtils.getIntValue(activity, str8 + "_RejectTimes", 0);
            if (intValue < 2 && (intValue != 1 || shouldShowRationale)) {
                showDialogIKnow(activity, list, list2, list3, permissionResultCallBack, str8, arrayList, strArr);
                return;
            }
            Boolean bool3 = dialogsShowingStatus.get(str8);
            if (bool3 == null || !bool3.booleanValue()) {
                showDialogToSetting(activity, list, list2, list3, permissionResultCallBack, str8);
            } else if (permissionResultCallBack != null) {
                permissionResultCallBack.onIgnored();
                if (OKLog.D) {
                    OKLog.e(TAG, "old handleFunctionPermissionDialog>29 - rejectTimes>2-key:" + str8 + "-onIgnored");
                }
            }
        }
    }
}
