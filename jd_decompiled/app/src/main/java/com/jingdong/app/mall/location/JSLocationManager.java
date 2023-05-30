package com.jingdong.app.mall.location;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.lbs.report.LBSReportManager;
import com.jingdong.common.permission.PermissionConstants;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.web.IRouterParams;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class JSLocationManager {
    public static String[] location = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};

    /* loaded from: classes4.dex */
    class a implements JDLocationListener {
        final /* synthetic */ IRouterParams a;

        a(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
        public void onFail(JDLocationError jDLocationError) {
            this.a.onCallBack(JSLocationManager.genFailObj(jDLocationError));
        }

        @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
        public void onSuccess(JDLocation jDLocation) {
            this.a.onCallBack(JSLocationManager.genSuccessObj(jDLocation));
        }
    }

    /* loaded from: classes4.dex */
    class b implements JDLocationListener {
        final /* synthetic */ IRouterParams a;

        b(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
        public void onFail(JDLocationError jDLocationError) {
            this.a.onCallBack(JSLocationManager.genFailObj(jDLocationError));
        }

        @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
        public void onSuccess(JDLocation jDLocation) {
            this.a.onCallBack(JSLocationManager.genSuccessObj(jDLocation));
        }
    }

    /* loaded from: classes4.dex */
    class c extends PermissionHelper.PermissionResultCallBack {
        final /* synthetic */ IRouterParams a;

        c(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionObj("onCanceled-\u6743\u9650\u7533\u8bf7\u672a\u5904\u7406\u6216\u7528\u6237\u53d6\u6d88\u7533\u8bf7", 3));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionObj("onDenied-\u6743\u9650\u7533\u8bf7\u88ab\u62d2\u7edd", 2));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionObj("onGranted-\u6743\u9650\u7533\u8bf7\u6210\u529f", 1));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionObj("onIgnored-\u6743\u9650\u7533\u8bf7\u88ab\u7cfb\u7edf\u6846\u67b6\u5ffd\u7565", 4));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            super.onOpenSetting();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionObj("onOpenSetting-\u8df3\u8f6c\u8bbe\u7f6e\uff0c\u65e0\u6cd5\u83b7\u77e5\u540e\u7eed\u72b6\u6001", 5));
        }
    }

    /* loaded from: classes4.dex */
    class d extends PermissionHelper.PermissionSceneCallback {
        final /* synthetic */ IRouterParams a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f11152c;

        d(IRouterParams iRouterParams, String str, String str2) {
            this.a = iRouterParams;
            this.b = str;
            this.f11152c = str2;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onAgree() {
            super.onAgree();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onAgree", 6));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onCanceled-\u6743\u9650\u7533\u8bf7\u672a\u5904\u7406\u6216\u7528\u6237\u53d6\u6d88\u7533\u8bf7", 3));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onDenied-\u6743\u9650\u7533\u8bf7\u88ab\u62d2\u7edd", 2));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onDisagree() {
            super.onDisagree();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onDisagree", 7));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onGranted-\u6743\u9650\u7533\u8bf7\u6210\u529f", 1));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onIgnored-\u6743\u9650\u7533\u8bf7\u88ab\u7cfb\u7edf\u6846\u67b6\u5ffd\u7565", 4));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            super.onOpenSetting();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onOpenSetting-\u8df3\u8f6c\u8bbe\u7f6e\uff0c\u65e0\u6cd5\u83b7\u77e5\u540e\u7eed\u72b6\u6001", 5));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onSceneIsEmpty() {
            super.onSceneIsEmpty();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onSceneIsEmpty", 9));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onTimeLimited() {
            super.onTimeLimited();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11152c, "onTimeLimited", 8));
        }
    }

    /* loaded from: classes4.dex */
    class e extends PermissionHelper.PermissionSceneCallback {
        final /* synthetic */ IRouterParams a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f11153c;

        e(IRouterParams iRouterParams, String str, String str2) {
            this.a = iRouterParams;
            this.b = str;
            this.f11153c = str2;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onAgree() {
            super.onAgree();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onAgree", 6));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            super.onCanceled();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onCanceled-\u6743\u9650\u7533\u8bf7\u672a\u5904\u7406\u6216\u7528\u6237\u53d6\u6d88\u7533\u8bf7", 3));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            super.onDenied();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onDenied-\u6743\u9650\u7533\u8bf7\u88ab\u62d2\u7edd", 2));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onDisagree() {
            super.onDisagree();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onDisagree", 7));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            super.onGranted();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onGranted-\u6743\u9650\u7533\u8bf7\u6210\u529f", 1));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
            super.onIgnored();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onIgnored-\u6743\u9650\u7533\u8bf7\u88ab\u7cfb\u7edf\u6846\u67b6\u5ffd\u7565", 4));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
            super.onOpenSetting();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onOpenSetting-\u8df3\u8f6c\u8bbe\u7f6e\uff0c\u65e0\u6cd5\u83b7\u77e5\u540e\u7eed\u72b6\u6001", 5));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onSceneIsEmpty() {
            super.onSceneIsEmpty();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onSceneIsEmpty", 9));
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionSceneCallback
        public void onTimeLimited() {
            super.onTimeLimited();
            this.a.onCallBack(JSLocationManager.genRequestLocationPermissionWithSceneObj(this.b, this.f11153c, "onTimeLimited", 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genFailObj(JDLocationError jDLocationError) {
        JSONObject jSONObject = new JSONObject();
        if (jDLocationError == null) {
            return jSONObject;
        }
        try {
            jSONObject.put("code", jDLocationError.getCode());
            jSONObject.put("message", jDLocationError.getMsg());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    @Deprecated
    private static JSONObject genHasLocationPermissionObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", "0");
            jSONObject.put("result", PermissionHelper.hasPermission(com.jingdong.app.mall.e.b().a(), Arrays.asList(location)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static JSONObject genHasLocationPermissionWithSceneObj(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", "0");
            jSONObject.put("result", PermissionHelper.hasLocationPermissionWithScene(str, str2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public static JSONObject genRequestLocationPermissionObj(String str, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", "0");
            jSONObject.put("message", "" + str);
            jSONObject.put("result", PermissionHelper.hasPermission(com.jingdong.app.mall.e.b().a(), Arrays.asList(location)));
            jSONObject.put("status", "" + i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genRequestLocationPermissionWithSceneObj(String str, String str2, String str3, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", "0");
            jSONObject.put("message", "" + str3);
            jSONObject.put("result", PermissionHelper.hasLocationPermissionWithScene(str, str2));
            jSONObject.put("status", "" + i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject genSuccessObj(JDLocation jDLocation) {
        if (jDLocation == null) {
            return new JSONObject();
        }
        JSONObject jSONObject = null;
        try {
            jSONObject = new JSONObject(jDLocation.getJsonForWeb());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public static void getAddress(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            JDLocationOption jDLocationOption = new JDLocationOption();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("appid");
                int optInt = jSONObject.optInt("ifdetail", 0);
                int optInt2 = jSONObject.optInt("ifip", 0);
                if (!TextUtils.isEmpty(optString)) {
                    jDLocationOption.setBusinessId(optString);
                }
                String optString2 = jSONObject.optString("sceneId");
                if (!TextUtils.isEmpty(optString2)) {
                    jDLocationOption.setSceneId(optString2);
                }
                jDLocationOption.setNeedDetail(optInt == 1);
                jDLocationOption.setNeedIP(optInt2 == 1);
            }
            JDLocationManager.getInstance().getAddress(jDLocationOption, new b(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static JSONObject getLBSConfig(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    new JSONObject(iRouterParams.getRouterParam());
                }
                JSONObject jSONObject = new JSONObject();
                JSONObject configObj = LBSReportManager.getInstance().getConfigObj();
                if (configObj != null) {
                    jSONObject.put("lbsnewreportswitch", configObj.optInt("lbsnewreportswitch", 0));
                }
                return jSONObject;
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static void getLatLng(IRouterParams iRouterParams) {
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            JDLocationOption jDLocationOption = new JDLocationOption();
            if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("appid");
                if (!TextUtils.isEmpty(optString)) {
                    jDLocationOption.setBusinessId(optString);
                }
                String optString2 = jSONObject.optString("sceneId");
                if (!TextUtils.isEmpty(optString2)) {
                    jDLocationOption.setSceneId(optString2);
                }
            }
            JDLocationManager.getInstance().getLatLng(jDLocationOption, new a(iRouterParams));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static JSONObject getLocation(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                    String optString = jSONObject.optString("appid");
                    if (!TextUtils.isEmpty(optString)) {
                        jDLocationCacheOption.setBusinessId(optString);
                    }
                    String optString2 = jSONObject.optString("sceneId");
                    if (!TextUtils.isEmpty(optString2)) {
                        jDLocationCacheOption.setSceneId(optString2);
                    }
                }
                return genSuccessObj(JDLocationCache.getInstance().getLocation(jDLocationCacheOption));
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    @Deprecated
    public static JSONObject hasLocationPermission(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (!TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    new JSONObject(iRouterParams.getRouterParam());
                }
                return genHasLocationPermissionObj();
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static JSONObject hasLocationPermissionWithScene(IRouterParams iRouterParams) {
        String str;
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                String str2 = "";
                if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    str = "";
                } else {
                    JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                    String optString = jSONObject.optString("businessId");
                    str2 = jSONObject.optString("sceneId");
                    str = optString;
                }
                return genHasLocationPermissionWithSceneObj(str2, str);
            } catch (Exception e2) {
                e2.printStackTrace();
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    public static void manualRequestLocationPermissionWithScene(IRouterParams iRouterParams) {
        String str;
        String str2;
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            String str3 = "";
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = "";
                str2 = str;
            } else {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("businessId");
                String optString2 = jSONObject.optString("sceneId");
                str2 = jSONObject.optString("sceneContent");
                str = optString;
                str3 = optString2;
            }
            if (PermissionHelper.hasLocationPermissionWithScene(str3, str)) {
                iRouterParams.onCallBack(genRequestLocationPermissionWithSceneObj(str3, str, "onGranted-\u6743\u9650\u5df2\u83b7\u53d6", 1));
            } else {
                PermissionHelper.manualRequestLocationPermissionWithScene(com.jingdong.app.mall.e.b().a(), new e(iRouterParams, str3, str), str3, str, str2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Deprecated
    public static void requestLocationPermission(IRouterParams iRouterParams) {
        String str;
        String str2;
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            if (PermissionHelper.hasPermission(com.jingdong.app.mall.e.b().a(), Arrays.asList(location))) {
                iRouterParams.onCallBack(genRequestLocationPermissionObj("onGranted-\u6743\u9650\u5df2\u83b7\u53d6", 1));
                return;
            }
            boolean z = false;
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = "";
                str2 = str;
            } else {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                str = jSONObject.optString("appid");
                str2 = jSONObject.optString("tipMsg");
                z = jSONObject.optBoolean("repeatPop");
                if (TextUtils.isEmpty(str2)) {
                    str2 = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u4f4d\u7f6e\u6743\u9650,\u4ee5\u4fbf\u6839\u636e\u4f4d\u7f6e\u5411\u60a8\u5c55\u793a\u9644\u8fd1\u7ebf\u4e0b\u95e8\u5e97\u3001\u5feb\u901f\u914d\u9001\u670d\u52a1\u3001\u5546\u54c1\u5e93\u5b58";
                }
            }
            Bundle generateBundle = PermissionHelper.generateBundle("LBS_H5", JSLocationManager.class.getSimpleName(), "" + str, z);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.add(PermissionConstants.LBS_DEFAULT_TITLE);
            arrayList2.add(str2);
            arrayList.add(PermissionConstants.LBS_DEFAULT_TITLE);
            arrayList2.add(str2);
            PermissionHelper.requestPermission(com.jingdong.app.mall.e.b().a(), generateBundle, Arrays.asList(location), new c(iRouterParams), arrayList, arrayList2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void requestLocationPermissionWithScene(IRouterParams iRouterParams) {
        String str;
        String str2;
        if (iRouterParams == null || iRouterParams.getContext() == null) {
            return;
        }
        try {
            String str3 = "";
            if (TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = "";
                str2 = str;
            } else {
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                String optString = jSONObject.optString("businessId");
                String optString2 = jSONObject.optString("sceneId");
                str2 = jSONObject.optString("sceneContent");
                str = optString;
                str3 = optString2;
            }
            if (PermissionHelper.hasLocationPermissionWithScene(str3, str)) {
                iRouterParams.onCallBack(genRequestLocationPermissionWithSceneObj(str3, str, "onGranted-\u6743\u9650\u5df2\u83b7\u53d6", 1));
            } else {
                PermissionHelper.requestLocationPermissionWithScene(com.jingdong.app.mall.e.b().a(), new d(iRouterParams, str3, str), str3, str, str2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
