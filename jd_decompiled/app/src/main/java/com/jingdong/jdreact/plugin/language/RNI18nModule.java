package com.jingdong.jdreact.plugin.language;

import android.os.Build;
import android.os.LocaleList;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes13.dex */
public class RNI18nModule extends ReactContextBaseJavaModule {
    public RNI18nModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private WritableArray getLocaleList() {
        WritableArray createArray = Arguments.createArray();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = getReactApplicationContext().getResources().getConfiguration().getLocales();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                createArray.pushString(toLanguageTag(locales.get(i2)));
            }
        } else {
            createArray.pushString(toLanguageTag(getReactApplicationContext().getResources().getConfiguration().locale));
        }
        return createArray;
    }

    private String toLanguageTag(Locale locale) {
        String sb;
        if (Build.VERSION.SDK_INT >= 21) {
            sb = locale.toLanguageTag();
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(locale.getLanguage());
            if (locale.getCountry() != null) {
                sb2.append("-");
                sb2.append(locale.getCountry());
            }
            sb = sb2.toString();
        }
        return sb.matches("^(iw|in|ji).*") ? sb.replace("iw", "he").replace("in", "id").replace("ji", "yi") : sb;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("languages", getLocaleList());
        return hashMap;
    }

    @ReactMethod
    public void getLanguages(Promise promise) {
        try {
            promise.resolve(getLocaleList());
        } catch (Exception e2) {
            promise.reject(e2);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNI18n";
    }
}
