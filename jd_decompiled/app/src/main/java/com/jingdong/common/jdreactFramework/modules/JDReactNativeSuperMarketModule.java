package com.jingdong.common.jdreactFramework.modules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jdreactFramework.utils.ReactMessageUtils;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.common.utils.pay.RiskControlUtils;
import com.jingdong.common.xSupermarket.ImageCompressUtils;
import com.jingdong.common.xSupermarket.TakePhotoActivity;
import com.tencent.mm.opensdk.modelbiz.OpenWebview;

/* loaded from: classes5.dex */
public class JDReactNativeSuperMarketModule extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final int REQUEST_PICTURE = 54321;
    private ReactContext reactContext;

    public JDReactNativeSuperMarketModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this);
    }

    public void startCamera(ReadableMap readableMap) {
        BaseActivity baseActivity = (BaseActivity) getCurrentActivity();
        if (baseActivity != null) {
            try {
                Intent intent = new Intent();
                if (readableMap != null) {
                    intent.putExtra("limitSize", readableMap.getInt("limitSize"));
                }
                intent.setClass(baseActivity, TakePhotoActivity.class);
                baseActivity.startActivityForResult(intent, REQUEST_PICTURE);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @ReactMethod
    public void getDeviceInfo(Callback callback, Callback callback2) {
        String localMacAddress = RiskControlUtils.getLocalMacAddress();
        String str = "localMacAddress " + localMacAddress;
        if (TextUtils.isEmpty(localMacAddress)) {
            return;
        }
        callback.invoke(localMacAddress);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactNativeSuperMarketModule";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        if (i2 == 54321 && -1 == i3) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("imageData", ImageCompressUtils.getInstance().getBase64Bitmap());
            ReactMessageUtils.send(this.reactContext, "didFinishedPhoto", createMap);
            ImageCompressUtils.getInstance().clearBytes();
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void openWeixinNoPwdPay(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (WeixinUtil.isWXInstalled()) {
            if (WeixinUtil.isWXAppSupportAPI()) {
                OpenWebview.Req req = new OpenWebview.Req();
                if (readableMap != null) {
                    String string = readableMap.getString("openUrl");
                    String string2 = readableMap.getString("payInfo");
                    if (StringUtil.isEmpty(string) || StringUtil.isEmpty(string2)) {
                        return;
                    }
                    req.url = string + "?" + string2;
                    WeixinUtil.getWXApi().sendReq(req);
                    return;
                }
                return;
            }
            ToastUtil.showToast(getCurrentActivity(), "\u5fae\u4fe1\u7248\u672c\u4f4e\uff0c\u8bf7\u5347\u7ea7");
            return;
        }
        ToastUtil.showToast(getCurrentActivity(), "\u62b1\u6b49\uff0c\u60a8\u5c1a\u672a\u5b89\u88c5\u5fae\u4fe1");
    }

    @ReactMethod
    public void startPhoto(final ReadableMap readableMap, Callback callback, Callback callback2) {
        Bundle bundle = new Bundle();
        bundle.putString("moduleName", "XSuperMarket");
        bundle.putString("className", "JDReactNativeSuperMarketModule");
        bundle.putString("methodName", "startPhoto");
        if (PermissionHelper.hasGrantedCamera(getCurrentActivity(), bundle, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactNativeSuperMarketModule.1
            {
                JDReactNativeSuperMarketModule.this = this;
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                JDReactNativeSuperMarketModule.this.startCamera(readableMap);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
            }
        })) {
            startCamera(readableMap);
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void startWebActivityForResult(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap != null) {
            String string = readableMap.getString("url");
            Bundle bundle = new Bundle();
            bundle.putString("url", string);
            DeepLinkCommonHelper.startActivityForResult(getCurrentActivity(), DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle, 110);
        }
    }
}
