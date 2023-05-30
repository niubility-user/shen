package com.jingdong.common.jdreactFramework.modules;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes5.dex */
public class JDReactTextToSpeechModule extends ReactContextBaseJavaModule {
    private static final String TAG = "JDReactTextToSpeechModule";
    private AudioManager.OnAudioFocusChangeListener afChangeListener;
    private AudioManager audioManager;
    private boolean ducking;
    private ArrayList<Promise> initStatusPromises;
    private Map<String, Locale> localeCountryMap;
    private Map<String, Locale> localeLanguageMap;
    private Boolean ready;
    private TextToSpeech tts;

    public JDReactTextToSpeechModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.ready = null;
        this.ducking = false;
        this.audioManager = (AudioManager) reactApplicationContext.getApplicationContext().getSystemService("audio");
        this.initStatusPromises = new ArrayList<>();
    }

    private void initCountryLanguageCodeMapping() {
        String[] iSOCountries = Locale.getISOCountries();
        this.localeCountryMap = new HashMap(iSOCountries.length);
        for (String str : iSOCountries) {
            Locale locale = new Locale("", str);
            this.localeCountryMap.put(locale.getISO3Country().toUpperCase(), locale);
        }
        String[] iSOLanguages = Locale.getISOLanguages();
        this.localeLanguageMap = new HashMap(iSOLanguages.length);
        for (String str2 : iSOLanguages) {
            Locale locale2 = new Locale(str2);
            this.localeLanguageMap.put(locale2.getISO3Language(), locale2);
        }
    }

    private boolean isPackageInstalled(String str) {
        try {
            getReactApplicationContext().getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private String iso3CountryCodeToIso2CountryCode(String str) {
        return this.localeCountryMap.get(str).getCountry();
    }

    private String iso3LanguageCodeToIso2LanguageCode(String str) {
        return this.localeLanguageMap.get(str).getLanguage();
    }

    private boolean notReady(Promise promise) {
        Boolean bool = this.ready;
        if (bool == null) {
            promise.reject("not_ready", "TTS is not ready");
            return true;
        } else if (bool != Boolean.TRUE) {
            resolveReadyPromise(promise);
            return true;
        } else {
            return false;
        }
    }

    @ReactMethod
    private void requestInstallData(Promise promise) {
        Intent intent = new Intent();
        intent.setAction("android.speech.tts.engine.INSTALL_TTS_DATA");
        try {
            getCurrentActivity().startActivity(intent);
            promise.resolve("success");
        } catch (ActivityNotFoundException unused) {
            promise.reject("no_engine", "No TTS engine installed");
        }
    }

    @ReactMethod
    private void requestInstallEngine(Promise promise) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=com.google.android.tts"));
        try {
            getCurrentActivity().startActivity(intent);
            promise.resolve("success");
        } catch (Exception unused) {
            promise.reject("error", "Could not open Google Text to Speech App in the Play Store");
        }
    }

    private static void resolvePromiseWithStatusCode(int i2, Promise promise) {
        switch (i2) {
            case -9:
                promise.reject("not_installed_yet", "Unfinished download of voice data");
                return;
            case -8:
                promise.reject("invalid_request", "Failure caused by an invalid request");
                return;
            case -7:
                promise.reject("network_timeout", "Failure caused by network timeout.");
                return;
            case -6:
                promise.reject("network_error", "Failure caused by a network connectivity problems");
                return;
            case -5:
                promise.reject("output_error", "Failure related to the output (audio device or a file)");
                return;
            case -4:
                promise.reject("service_error", "Failure of a TTS service");
                return;
            case -3:
                promise.reject("synthesis_error", "Failure of a TTS engine to synthesize the given input");
                return;
            case -2:
                promise.reject("lang_not_supported", "Language is not supported");
                return;
            case -1:
                promise.reject("lang_missing_data", "Language data is missing");
                return;
            case 0:
                promise.resolve("success");
                return;
            case 1:
                promise.resolve("lang_country_available");
                return;
            case 2:
                promise.resolve("lang_country_var_available");
                return;
            default:
                promise.reject("error", "Unknown error code: " + i2);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveReadyPromise(Promise promise) {
        if (this.ready == Boolean.TRUE) {
            promise.resolve("success");
        } else {
            promise.reject("no_engine", "No TTS engine installed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("utteranceId", str2);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, createMap);
    }

    private void setUtteranceProgress() {
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech == null || Build.VERSION.SDK_INT < 15) {
            return;
        }
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactTextToSpeechModule.2
            @Override // android.speech.tts.UtteranceProgressListener
            public void onDone(String str) {
                if (JDReactTextToSpeechModule.this.ducking) {
                    JDReactTextToSpeechModule.this.audioManager.abandonAudioFocus(JDReactTextToSpeechModule.this.afChangeListener);
                }
                JDReactTextToSpeechModule.this.sendEvent("tts-finish", str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onError(String str) {
                if (JDReactTextToSpeechModule.this.ducking) {
                    JDReactTextToSpeechModule.this.audioManager.abandonAudioFocus(JDReactTextToSpeechModule.this.afChangeListener);
                }
                JDReactTextToSpeechModule.this.sendEvent("tts-error", str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStart(String str) {
                JDReactTextToSpeechModule.this.sendEvent("tts-start", str);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public void onStop(String str, boolean z) {
                if (JDReactTextToSpeechModule.this.ducking) {
                    JDReactTextToSpeechModule.this.audioManager.abandonAudioFocus(JDReactTextToSpeechModule.this.afChangeListener);
                }
                JDReactTextToSpeechModule.this.sendEvent("tts-cancel", str);
            }
        });
    }

    @ReactMethod
    public void engines(Promise promise) {
        if (notReady(promise)) {
            return;
        }
        WritableArray createArray = Arguments.createArray();
        if (Build.VERSION.SDK_INT >= 14) {
            try {
                TextToSpeech textToSpeech = this.tts;
                if (textToSpeech != null) {
                    String defaultEngine = textToSpeech.getDefaultEngine();
                    for (TextToSpeech.EngineInfo engineInfo : this.tts.getEngines()) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putString("name", engineInfo.name);
                        createMap.putString(Constant.KEY_PROMOTION_LABEL, engineInfo.label);
                        createMap.putBoolean("default", engineInfo.name.equals(defaultEngine));
                        createMap.putInt("icon", engineInfo.icon);
                        createArray.pushMap(createMap);
                    }
                } else {
                    JLog.d(TAG, "tts is null, please use initTts() to init tts");
                }
            } catch (Exception unused) {
                promise.reject("error", "Unknown error code");
            }
        }
        promise.resolve(createArray);
    }

    @ReactMethod
    public void getInitStatus(Promise promise) {
        synchronized (this.initStatusPromises) {
            if (this.ready == null) {
                this.initStatusPromises.add(promise);
            } else {
                resolveReadyPromise(promise);
            }
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactTextToSpeech";
    }

    @ReactMethod
    public void initTts(final Callback callback, final Callback callback2) {
        initCountryLanguageCodeMapping();
        if (this.tts == null) {
            this.tts = new TextToSpeech(getReactApplicationContext(), new TextToSpeech.OnInitListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactTextToSpeechModule.1
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public void onInit(int i2) {
                    synchronized (JDReactTextToSpeechModule.this.initStatusPromises) {
                        JDReactTextToSpeechModule.this.ready = Boolean.valueOf(i2 == 0);
                        if (JDReactTextToSpeechModule.this.ready.booleanValue()) {
                            Callback callback3 = callback;
                            if (callback3 != null) {
                                callback3.invoke(Boolean.TRUE);
                            }
                        } else {
                            Callback callback4 = callback2;
                            if (callback4 != null) {
                                callback4.invoke(Boolean.FALSE);
                            }
                        }
                        Iterator it = JDReactTextToSpeechModule.this.initStatusPromises.iterator();
                        while (it.hasNext()) {
                            JDReactTextToSpeechModule.this.resolveReadyPromise((Promise) it.next());
                        }
                        JDReactTextToSpeechModule.this.initStatusPromises.clear();
                    }
                }
            });
        }
        setUtteranceProgress();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.stop();
            this.tts.shutdown();
        }
    }

    @ReactMethod
    public void setDefaultEngine(String str, final Promise promise) {
        if (notReady(promise)) {
            return;
        }
        if (isPackageInstalled(str)) {
            this.ready = null;
            onCatalystInstanceDestroy();
            this.tts = new TextToSpeech(getReactApplicationContext(), new TextToSpeech.OnInitListener() { // from class: com.jingdong.common.jdreactFramework.modules.JDReactTextToSpeechModule.3
                @Override // android.speech.tts.TextToSpeech.OnInitListener
                public void onInit(int i2) {
                    synchronized (JDReactTextToSpeechModule.this.initStatusPromises) {
                        JDReactTextToSpeechModule.this.ready = i2 == 0 ? Boolean.TRUE : Boolean.FALSE;
                        Iterator it = JDReactTextToSpeechModule.this.initStatusPromises.iterator();
                        while (it.hasNext()) {
                            JDReactTextToSpeechModule.this.resolveReadyPromise((Promise) it.next());
                        }
                        JDReactTextToSpeechModule.this.initStatusPromises.clear();
                        promise.resolve(JDReactTextToSpeechModule.this.ready);
                    }
                }
            }, str);
            setUtteranceProgress();
            return;
        }
        promise.reject("not_found", "The selected engine was not found");
    }

    @ReactMethod
    public void setDefaultLanguage(String str, Promise promise) {
        Locale locale;
        if (notReady(promise)) {
            return;
        }
        if (str.indexOf("-") != -1) {
            String[] split = str.split("-");
            locale = new Locale(split[0], split[1]);
        } else {
            locale = new Locale(str);
        }
        try {
            TextToSpeech textToSpeech = this.tts;
            if (textToSpeech != null) {
                resolvePromiseWithStatusCode(textToSpeech.setLanguage(locale), promise);
            } else {
                JLog.d(TAG, "tts is null,please use initTts() to init tts");
            }
        } catch (Exception unused) {
            promise.reject("error", "Unknown error code");
        }
    }

    @ReactMethod
    public void setDefaultPitch(Float f2, Promise promise) {
        if (notReady(promise)) {
            return;
        }
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            resolvePromiseWithStatusCode(textToSpeech.setPitch(f2.floatValue()), promise);
        } else {
            JLog.d(TAG, "tts is null,please use initTts() to init tts");
        }
    }

    @ReactMethod
    public void setDefaultRate(Float f2, Boolean bool, Promise promise) {
        float floatValue;
        if (notReady(promise)) {
            return;
        }
        if (this.tts != null) {
            if (bool.booleanValue()) {
                resolvePromiseWithStatusCode(this.tts.setSpeechRate(f2.floatValue()), promise);
                return;
            }
            if (f2.floatValue() < 0.5f) {
                floatValue = f2.floatValue() * 2.0f;
            } else {
                floatValue = (f2.floatValue() * 4.0f) - 1.0f;
            }
            resolvePromiseWithStatusCode(this.tts.setSpeechRate(floatValue), promise);
            return;
        }
        JLog.d(TAG, "tts is null,please use initTts() to init tts");
    }

    @ReactMethod
    public void setDefaultVoice(String str, Promise promise) {
        if (notReady(promise)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                TextToSpeech textToSpeech = this.tts;
                if (textToSpeech != null) {
                    for (Voice voice : textToSpeech.getVoices()) {
                        if (voice.getName().equals(str)) {
                            resolvePromiseWithStatusCode(this.tts.setVoice(voice), promise);
                            return;
                        }
                    }
                } else {
                    JLog.d(TAG, "tts is null,please use initTts() to init tts");
                }
            } catch (Exception unused) {
            }
            promise.reject("not_found", "The selected voice was not found");
            return;
        }
        promise.reject("not_available", "Android API 21 level or higher is required");
    }

    @ReactMethod
    public void setDucking(Boolean bool, Promise promise) {
        if (notReady(promise)) {
            return;
        }
        this.ducking = bool.booleanValue();
        promise.resolve("success");
    }

    @ReactMethod
    public void speak(String str, ReadableMap readableMap, Promise promise) {
        if (notReady(promise)) {
            return;
        }
        if (this.ducking && this.audioManager.requestAudioFocus(this.afChangeListener, 3, 3) != 1) {
            promise.reject("Android AudioManager error, failed to request audio focus");
            return;
        }
        String num = Integer.toString(str.hashCode());
        if (this.tts != null) {
            int speak = speak(str, num, readableMap);
            if (speak == 0) {
                promise.resolve(num);
                return;
            } else {
                resolvePromiseWithStatusCode(speak, promise);
                return;
            }
        }
        JLog.d(TAG, "tts is null,please use initTts() to init tts");
    }

    @ReactMethod
    public void stop(Promise promise) {
        if (notReady(promise)) {
            return;
        }
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            int stop = textToSpeech.stop();
            (stop == 0 ? Boolean.TRUE : Boolean.FALSE).booleanValue();
            promise.resolve(Integer.valueOf(stop));
            return;
        }
        JLog.d(TAG, "tts is null, please use initTts() to init tts");
    }

    @ReactMethod
    public void voices(Promise promise) {
        if (notReady(promise)) {
            return;
        }
        WritableArray createArray = Arguments.createArray();
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                TextToSpeech textToSpeech = this.tts;
                if (textToSpeech != null) {
                    for (Voice voice : textToSpeech.getVoices()) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putString("id", voice.getName());
                        createMap.putString("name", voice.getName());
                        String iso3LanguageCodeToIso2LanguageCode = iso3LanguageCodeToIso2LanguageCode(voice.getLocale().getISO3Language());
                        String iSO3Country = voice.getLocale().getISO3Country();
                        if (iSO3Country != "") {
                            iso3LanguageCodeToIso2LanguageCode = iso3LanguageCodeToIso2LanguageCode + "-" + iso3CountryCodeToIso2CountryCode(iSO3Country);
                        }
                        createMap.putString(IjkMediaMeta.IJKM_KEY_LANGUAGE, iso3LanguageCodeToIso2LanguageCode);
                        createMap.putInt("quality", voice.getQuality());
                        createMap.putInt("latency", voice.getLatency());
                        createMap.putBoolean("networkConnectionRequired", voice.isNetworkConnectionRequired());
                        createMap.putBoolean("notInstalled", voice.getFeatures().contains("notInstalled"));
                        createArray.pushMap(createMap);
                    }
                } else {
                    JLog.d(TAG, "tts is null,please use initTts() to init tts");
                }
            } catch (Exception unused) {
            }
        }
        promise.resolve(createArray);
    }

    private int speak(String str, String str2, ReadableMap readableMap) {
        String string = readableMap.hasKey("KEY_PARAM_STREAM") ? readableMap.getString("KEY_PARAM_STREAM") : "";
        float f2 = readableMap.hasKey("KEY_PARAM_VOLUME") ? (float) readableMap.getDouble("KEY_PARAM_VOLUME") : 1.0f;
        float f3 = readableMap.hasKey("KEY_PARAM_PAN") ? (float) readableMap.getDouble("KEY_PARAM_PAN") : 0.0f;
        string.hashCode();
        char c2 = '\uffff';
        int i2 = 5;
        switch (string.hashCode()) {
            case -1192558710:
                if (string.equals("STREAM_VOICE_CALL")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1048950966:
                if (string.equals("STREAM_NOTIFICATION")) {
                    c2 = 1;
                    break;
                }
                break;
            case -485436536:
                if (string.equals("STREAM_DTMF")) {
                    c2 = 2;
                    break;
                }
                break;
            case -485030001:
                if (string.equals("STREAM_RING")) {
                    c2 = 3;
                    break;
                }
                break;
            case 2081173454:
                if (string.equals("STREAM_SYSTEM")) {
                    c2 = 4;
                    break;
                }
                break;
            case 2128316594:
                if (string.equals("STREAM_ALARM")) {
                    c2 = 5;
                    break;
                }
                break;
            case 2139683974:
                if (string.equals("STREAM_MUSIC")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                i2 = 0;
                break;
            case 1:
                break;
            case 2:
                i2 = 8;
                break;
            case 3:
                i2 = 2;
                break;
            case 4:
                i2 = 1;
                break;
            case 5:
                i2 = 4;
                break;
            case 6:
                i2 = 3;
                break;
            default:
                i2 = Integer.MIN_VALUE;
                break;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Bundle bundle = new Bundle();
            bundle.putInt("streamType", i2);
            bundle.putFloat("volume", f2);
            bundle.putFloat(com.unionpay.tsmservice.data.Constant.KEY_PAN, f3);
            return this.tts.speak(str, 1, bundle, str2);
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("utteranceId", str2);
        hashMap.put("streamType", String.valueOf(i2));
        hashMap.put("volume", String.valueOf(f2));
        hashMap.put(com.unionpay.tsmservice.data.Constant.KEY_PAN, String.valueOf(f3));
        return this.tts.speak(str, 1, hashMap);
    }
}
