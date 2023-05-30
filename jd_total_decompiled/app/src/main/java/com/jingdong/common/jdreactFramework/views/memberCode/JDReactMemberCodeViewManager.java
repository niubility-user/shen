package com.jingdong.common.jdreactFramework.views.memberCode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.Window;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.widget.CodeView;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.CashierDeskConstantBean;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"LongLogTag"})
/* loaded from: classes5.dex */
public class JDReactMemberCodeViewManager extends SimpleViewManager<RTCCodeView> implements ActivityEventListener {
    private static final int DESTORY = 9;
    private static final int FORBIDDEN_SCREEN_CAPTRUE = 4;
    private static final int INIT_CODE_VIEW = 5;
    private static final int REGISTER_RECEIVER = 10;
    private static final int RESUME_SCREEN_CAPTRUE = 3;
    private static final int SET_COUPONS = 6;
    private static final int START_TIMER = 8;
    private static final int STOP_CODEVIEW = 2;
    private static final int STOP_TIMER = 7;
    private static final String TAG = "JDReactMemberCodeViewManager";
    private static final int UNREGISTER_RECEIVER = 11;
    private static final int UPDATE_CODEVIEW = 1;
    private ArrayList<CodeView> mHashMap = new ArrayList<>();
    private ReactApplicationContext mReactApplicationContext;

    /* loaded from: classes5.dex */
    public static class MsgReceiver extends BroadcastReceiver {
        CodeView mCodeView;

        public MsgReceiver(RTCCodeView rTCCodeView) {
            this.mCodeView = rTCCodeView;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String stringExtra = intent.getStringExtra("msg");
                if (this.mCodeView != null) {
                    String str = "onPayResult msg = " + stringExtra;
                    this.mCodeView.onPayResult(stringExtra);
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class RTCCodeView extends CodeView implements LifecycleEventListener {
        private ThemedReactContext mThemedReactContext;
        MsgReceiver msgReceiver;

        public RTCCodeView(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.mThemedReactContext = themedReactContext;
            themedReactContext.addLifecycleEventListener(this);
            this.msgReceiver = new MsgReceiver(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("code_view_push_msg_action");
            this.mThemedReactContext.registerReceiver(this.msgReceiver, intentFilter);
        }

        public void closeCodeView() {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_onCloseMemberCode", null);
        }

        public void hideSettingMenu() {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_onHideSettingBtn", null);
        }

        public void onActivated() {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_onActivated", null);
        }

        public void onError(int i2, @Nullable Throwable th) {
            WritableMap createMap = Arguments.createMap();
            if (th != null) {
                createMap.putString("message", th.getMessage());
            }
            createMap.putInt(CashierDeskConstantBean.JDCASHIER_SETTLEMENT_OPT_TYPE, i2);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_onError", createMap);
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostDestroy() {
            ThemedReactContext themedReactContext = this.mThemedReactContext;
            if (themedReactContext != null) {
                try {
                    themedReactContext.unregisterReceiver(this.msgReceiver);
                } catch (Exception unused) {
                }
            }
            destroy();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostPause() {
            stopScheduler();
        }

        @Override // com.facebook.react.bridge.LifecycleEventListener
        public void onHostResume() {
            startScheduler();
        }

        public void onInactivated() {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_onInactivated", null);
        }

        public void onInited() {
            if (isCodeActivated()) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_onShowSettingBtn", null);
            }
        }

        @Override // com.jdpay.membercode.widget.CodeView
        public boolean startBrowser(@NonNull String str, int i2) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mThemedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("jdreact_member_code_get_three_code", null);
            return true;
        }
    }

    public JDReactMemberCodeViewManager(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    private void setToken(CodeView codeView) {
        if (codeView == null) {
            return;
        }
        codeView.setAppSource("jdmall");
        String a2 = UserUtil.getWJLoginHelper().getA2();
        if (!TextUtils.isEmpty(a2)) {
            codeView.setUserToken(a2);
        }
        String deviceToken = MessageCommonUtils.getDeviceToken(this.mReactApplicationContext);
        if (TextUtils.isEmpty(deviceToken)) {
            return;
        }
        codeView.setDeviceToken(deviceToken);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        MapBuilder.Builder builder = MapBuilder.builder();
        builder.put("changeVolume", 1);
        builder.put("changePause", 2);
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMemberCodeView";
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        if (this.mHashMap.size() >= 1) {
            ArrayList<CodeView> arrayList = this.mHashMap;
            CodeView codeView = arrayList.get(arrayList.size() - 1);
            if (codeView != null) {
                String str = "onActivityResult()...requestCode = " + i2 + ", resultCode = " + i3;
                if (i2 == 110) {
                    setToken(codeView);
                    codeView.init();
                    return;
                }
                codeView.onActivityResult(i2, i3, intent);
            }
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactProp(name = "strCouponId")
    public void setStrCouponId(CodeView codeView, String str) {
        if (!TextUtils.isEmpty(str)) {
            String str2 = "setStrCouponId()..." + str;
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            codeView.setCouponIds(arrayList);
        }
        setToken(codeView);
        codeView.init();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public RTCCodeView createViewInstance(ThemedReactContext themedReactContext) {
        MessageCommonUtils.registeredBusiness(20001, PushMessageUtil.class);
        JDPayMemberCode.init((themedReactContext.getCurrentActivity() == null || themedReactContext.getCurrentActivity().getApplication() == null) ? JdSdk.getInstance().getApplication() : themedReactContext.getCurrentActivity().getApplication());
        RTCCodeView rTCCodeView = new RTCCodeView(themedReactContext);
        setToken(rTCCodeView);
        if (themedReactContext.getCurrentActivity() != null) {
            rTCCodeView.setInteractor(new MemberCodeImpl(themedReactContext.getCurrentActivity(), rTCCodeView));
        } else if (this.mReactApplicationContext.getCurrentActivity() != null) {
            rTCCodeView.setInteractor(new MemberCodeImpl(this.mReactApplicationContext.getCurrentActivity(), rTCCodeView));
        }
        this.mHashMap.add(rTCCodeView);
        this.mReactApplicationContext.addActivityEventListener(this);
        return rTCCodeView;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(RTCCodeView rTCCodeView) {
        super.onDropViewInstance((JDReactMemberCodeViewManager) rTCCodeView);
        if (rTCCodeView != null) {
            ((ThemedReactContext) rTCCodeView.getContext()).removeLifecycleEventListener(rTCCodeView);
            rTCCodeView.destroy();
        }
        this.mHashMap.remove(rTCCodeView);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(RTCCodeView rTCCodeView, int i2, @Nullable ReadableArray readableArray) {
        Window window;
        if (rTCCodeView != null) {
            setToken(rTCCodeView);
        }
        int i3 = 0;
        switch (i2) {
            case 1:
                if (rTCCodeView != null) {
                    rTCCodeView.updateCode();
                    return;
                }
                return;
            case 2:
                if (rTCCodeView != null) {
                    rTCCodeView.inactivateCode();
                    return;
                }
                return;
            case 3:
                if (rTCCodeView != null) {
                    Activity currentActivity = this.mReactApplicationContext.getCurrentActivity();
                    window = currentActivity != null ? currentActivity.getWindow() : null;
                    if (window != null) {
                        window.clearFlags(8192);
                        return;
                    }
                    return;
                }
                return;
            case 4:
                if (rTCCodeView != null) {
                    Activity currentActivity2 = this.mReactApplicationContext.getCurrentActivity();
                    window = currentActivity2 != null ? currentActivity2.getWindow() : null;
                    if (window != null) {
                        window.addFlags(8192);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (rTCCodeView != null) {
                    rTCCodeView.init();
                    if (readableArray == null || readableArray.size() <= 0) {
                        return;
                    }
                    try {
                        ArrayList arrayList = new ArrayList();
                        while (i3 < readableArray.size()) {
                            String str = "type = " + readableArray.getType(i3);
                            String string = readableArray.getString(i3);
                            String str2 = "couponId: " + string;
                            if (!TextUtils.isEmpty(string)) {
                                arrayList.add(string);
                            }
                            i3++;
                        }
                        rTCCodeView.setCouponIds(arrayList);
                        rTCCodeView.updateCode();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            case 6:
                if (rTCCodeView != null) {
                    ArrayList arrayList2 = new ArrayList();
                    if (readableArray != null && readableArray.size() > 0) {
                        while (i3 < readableArray.size()) {
                            String string2 = readableArray.getString(i3);
                            if (!TextUtils.isEmpty(string2)) {
                                arrayList2.add(string2);
                            }
                            i3++;
                        }
                    }
                    rTCCodeView.setCouponIds(arrayList2);
                    rTCCodeView.updateCode();
                    return;
                }
                return;
            case 7:
                if (rTCCodeView != null) {
                    rTCCodeView.stopScheduler();
                    return;
                }
                return;
            case 8:
                if (rTCCodeView != null) {
                    rTCCodeView.startScheduler();
                    return;
                }
                return;
            case 9:
                if (rTCCodeView != null) {
                    rTCCodeView.destroy();
                    return;
                }
                return;
            case 10:
                if (rTCCodeView == null || rTCCodeView.mThemedReactContext == null) {
                    return;
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("code_view_push_msg_action");
                rTCCodeView.mThemedReactContext.registerReceiver(rTCCodeView.msgReceiver, intentFilter);
                return;
            case 11:
                if (rTCCodeView == null || rTCCodeView.mThemedReactContext == null) {
                    return;
                }
                try {
                    rTCCodeView.mThemedReactContext.unregisterReceiver(rTCCodeView.msgReceiver);
                    return;
                } catch (Exception unused) {
                    return;
                }
            default:
                return;
        }
    }
}
