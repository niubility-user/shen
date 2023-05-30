package com.facebook.react.modules.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.module.annotations.ReactModule;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import javax.annotation.Nullable;

@ReactModule(name = IntentModule.NAME)
/* loaded from: classes12.dex */
public class IntentModule extends ReactContextBaseJavaModule {
    public static final String NAME = "IntentAndroid";

    /* renamed from: com.facebook.react.modules.intent.IntentModule$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public IntentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void canOpenURL(String str, Promise promise) {
        if (str != null && !str.isEmpty()) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addFlags(268435456);
                promise.resolve(Boolean.valueOf(intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null));
                return;
            } catch (Exception e2) {
                promise.reject(new JSApplicationIllegalArgumentException("Could not check if URL '" + str + "' can be opened: " + e2.getMessage()));
                return;
            }
        }
        promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + str));
    }

    @ReactMethod
    public void getInitialURL(Promise promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            String str = null;
            if (currentActivity != null) {
                Intent intent = currentActivity.getIntent();
                String action = intent.getAction();
                Uri data = intent.getData();
                if ("android.intent.action.VIEW".equals(action) && data != null) {
                    str = data.toString();
                }
            }
            promise.resolve(str);
        } catch (Exception e2) {
            promise.reject(new JSApplicationIllegalArgumentException("Could not get the initial URL : " + e2.getMessage()));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void openURL(String str, Promise promise) {
        if (str != null && !str.isEmpty()) {
            try {
                Activity currentActivity = getCurrentActivity();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str).normalizeScheme());
                String packageName = getReactApplicationContext().getPackageName();
                ComponentName resolveActivity = intent.resolveActivity(getReactApplicationContext().getPackageManager());
                String packageName2 = resolveActivity != null ? resolveActivity.getPackageName() : "";
                if (currentActivity == null || !packageName.equals(packageName2)) {
                    intent.addFlags(268435456);
                }
                if (currentActivity != null) {
                    currentActivity.startActivity(intent);
                } else {
                    getReactApplicationContext().startActivity(intent);
                }
                promise.resolve(Boolean.TRUE);
                return;
            } catch (Exception e2) {
                promise.reject(new JSApplicationIllegalArgumentException("Could not open URL '" + str + "': " + e2.getMessage()));
                return;
            }
        }
        promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + str));
    }

    @ReactMethod
    public void sendIntent(String str, @Nullable ReadableArray readableArray, Promise promise) {
        if (str != null && !str.isEmpty()) {
            Intent intent = new Intent(str);
            if (intent.resolveActivity(getReactApplicationContext().getPackageManager()) == null) {
                promise.reject(new JSApplicationIllegalArgumentException("Could not launch Intent with action " + str + OrderISVUtil.MONEY_DECIMAL));
                return;
            }
            if (readableArray != null) {
                for (int i2 = 0; i2 < readableArray.size(); i2++) {
                    ReadableMap map = readableArray.getMap(i2);
                    String nextKey = map.keySetIterator().nextKey();
                    int i3 = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[map.getType(nextKey).ordinal()];
                    if (i3 == 1) {
                        intent.putExtra(nextKey, map.getString(nextKey));
                    } else if (i3 == 2) {
                        intent.putExtra(nextKey, Double.valueOf(map.getDouble(nextKey)));
                    } else if (i3 != 3) {
                        promise.reject(new JSApplicationIllegalArgumentException("Extra type for " + nextKey + " not supported."));
                        return;
                    } else {
                        intent.putExtra(nextKey, map.getBoolean(nextKey));
                    }
                }
            }
            getReactApplicationContext().startActivity(intent);
            return;
        }
        promise.reject(new JSApplicationIllegalArgumentException("Invalid Action: " + str + OrderISVUtil.MONEY_DECIMAL));
    }
}
