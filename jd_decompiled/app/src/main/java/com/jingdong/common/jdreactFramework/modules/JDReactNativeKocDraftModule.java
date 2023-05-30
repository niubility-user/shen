package com.jingdong.common.jdreactFramework.modules;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.database.table.koc.KocDraftEntity;
import com.jingdong.common.database.table.koc.KocDraftHelper;
import com.jingdong.common.database.table.koc.KocDraftUpdateEntity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactNativeKocDraftModule extends ReactContextBaseJavaModule {
    private static final String ERROR = "KOC_DRAFT_ERROR";

    public JDReactNativeKocDraftModule(@NonNull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private boolean BaseCheck(Promise promise) {
        if (promise == null) {
            return false;
        }
        if (LoginUserBase.hasLogin()) {
            return true;
        }
        promise.reject(ERROR, "error: Not logged in");
        return false;
    }

    public static /* synthetic */ void b(Promise promise) {
        int draftCount = KocDraftHelper.getDraftCount();
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("count", draftCount);
        promise.resolve(createMap);
    }

    public static /* synthetic */ void c(KocDraftEntity kocDraftEntity, Promise promise) {
        long insert = KocDraftHelper.insert(kocDraftEntity);
        if (insert > 0) {
            promise.resolve(String.valueOf(insert));
        } else {
            promise.reject(ERROR, "\u63d2\u5165\u5931\u8d25!");
        }
    }

    /* renamed from: d */
    public /* synthetic */ void e(Promise promise) {
        promise.resolve(query(-1L));
    }

    private KocDraftEntity deleteEntity(ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("id")) {
            KocDraftEntity kocDraftEntity = new KocDraftEntity();
            kocDraftEntity.id = parseLong(readableMap.getString("id"));
            return kocDraftEntity;
        }
        return null;
    }

    /* renamed from: f */
    public /* synthetic */ void g(Promise promise, ReadableMap readableMap) {
        promise.resolve(query(readableMap.getString("bType"), readableMap.getString("bId")));
    }

    private String get(ReadableMap readableMap, String str, String str2) {
        return (readableMap == null || str == null || !readableMap.hasKey(str)) ? str2 : readableMap.getString(str);
    }

    private WritableMap getMap(KocDraftEntity kocDraftEntity) {
        if (kocDraftEntity == null) {
            return null;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("id", String.valueOf(kocDraftEntity.id));
        createMap.putString("createTime", String.valueOf(kocDraftEntity.createTime));
        createMap.putString("modifyTime", String.valueOf(kocDraftEntity.lastModifyTime));
        createMap.putString("data", kocDraftEntity.data);
        createMap.putString("bType", kocDraftEntity.bType);
        createMap.putString("bId", kocDraftEntity.bId);
        return createMap;
    }

    /* renamed from: h */
    public /* synthetic */ void i(Promise promise, ReadableMap readableMap) {
        promise.resolve(query(parseLong(readableMap.getString("id"))));
    }

    private KocDraftEntity insertEntity(ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("data")) {
            String str = get(readableMap, "bId", "");
            return new KocDraftEntity(readableMap.getString("data"), get(readableMap, "bType", ""), str);
        }
        return null;
    }

    /* renamed from: j */
    public /* synthetic */ void k(Promise promise) {
        promise.resolve(query(-1L));
    }

    private long parseLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1L;
        }
    }

    private WritableMap queryAll() {
        WritableMap map;
        WritableMap createMap = Arguments.createMap();
        List<KocDraftEntity> query = KocDraftHelper.query();
        if (query != null && query.size() > 0) {
            WritableArray createArray = Arguments.createArray();
            for (int i2 = 0; i2 < query.size(); i2++) {
                if (query.get(i2) != null && (map = getMap(query.get(i2))) != null) {
                    createArray.pushMap(map);
                }
            }
            createMap.putArray("draft", createArray);
        }
        return createMap;
    }

    private KocDraftUpdateEntity updateEntity(ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("id") && readableMap.hasKey("data")) {
            KocDraftUpdateEntity kocDraftUpdateEntity = new KocDraftUpdateEntity();
            kocDraftUpdateEntity.id = parseLong(readableMap.getString("id"));
            kocDraftUpdateEntity.data = readableMap.getString("data");
            return kocDraftUpdateEntity;
        }
        return null;
    }

    @ReactMethod
    public void delete(ReadableMap readableMap, final Promise promise) {
        if (BaseCheck(promise)) {
            final KocDraftEntity deleteEntity = deleteEntity(readableMap);
            if (deleteEntity != null) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        Promise promise2 = promise;
                        KocDraftEntity kocDraftEntity = deleteEntity;
                        promise2.resolve(Boolean.valueOf(KocDraftHelper.delete(r1) > 0));
                    }
                });
            } else {
                promise.reject(ERROR, new IllegalArgumentException());
            }
        }
    }

    @ReactMethod
    public void getDraftCount(final Promise promise) {
        if (BaseCheck(promise)) {
            ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.b
                @Override // java.lang.Runnable
                public final void run() {
                    JDReactNativeKocDraftModule.b(promise);
                }
            });
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return "JDReactNativeKocDraftModule";
    }

    @ReactMethod
    public void insert(ReadableMap readableMap, final Promise promise) {
        if (BaseCheck(promise)) {
            final KocDraftEntity insertEntity = insertEntity(readableMap);
            if (insertEntity != null) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        JDReactNativeKocDraftModule.c(insertEntity, promise);
                    }
                });
            } else {
                promise.reject(ERROR, new IllegalArgumentException());
            }
        }
    }

    @ReactMethod
    public void query(final ReadableMap readableMap, final Promise promise) {
        if (BaseCheck(promise)) {
            if (readableMap == null) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.c
                    {
                        JDReactNativeKocDraftModule.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        JDReactNativeKocDraftModule.this.e(promise);
                    }
                });
            } else if (readableMap.hasKey("bId") && readableMap.hasKey("bType")) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.f
                    {
                        JDReactNativeKocDraftModule.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        JDReactNativeKocDraftModule.this.g(promise, readableMap);
                    }
                });
            } else if (readableMap.hasKey("id")) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.g
                    {
                        JDReactNativeKocDraftModule.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        JDReactNativeKocDraftModule.this.i(promise, readableMap);
                    }
                });
            } else {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.e
                    {
                        JDReactNativeKocDraftModule.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        JDReactNativeKocDraftModule.this.k(promise);
                    }
                });
            }
        }
    }

    @ReactMethod
    public void update(ReadableMap readableMap, final Promise promise) {
        if (BaseCheck(promise)) {
            final KocDraftUpdateEntity updateEntity = updateEntity(readableMap);
            if (updateEntity != null) {
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        Promise promise2 = promise;
                        KocDraftUpdateEntity kocDraftUpdateEntity = updateEntity;
                        promise2.resolve(Boolean.valueOf(KocDraftHelper.update(r1) > 0));
                    }
                });
            } else {
                promise.reject(ERROR, new IllegalArgumentException());
            }
        }
    }

    private WritableMap query(long j2) {
        WritableMap map;
        if (j2 == -1) {
            return queryAll();
        }
        WritableMap createMap = Arguments.createMap();
        KocDraftEntity query = KocDraftHelper.query(j2);
        if (query != null && (map = getMap(query)) != null) {
            createMap.putMap("draft", map);
        }
        return createMap;
    }

    private WritableMap query(String str, String str2) {
        KocDraftEntity query;
        WritableMap map;
        WritableMap createMap = Arguments.createMap();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && (query = KocDraftHelper.query(str, str2)) != null && (map = getMap(query)) != null) {
            createMap.putMap("draft", map);
        }
        return createMap;
    }
}
