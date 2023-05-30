package com.jd.libs.hybrid.offlineload.db;

import android.text.TextUtils;
import com.jd.libs.hybrid.base.util.HybridDataStore;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.TestModule;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class TestOfflineDataStore extends HybridDataStore<TestModule> {

    /* renamed from: e  reason: collision with root package name */
    private static volatile TestOfflineDataStore f5966e;

    public TestOfflineDataStore() {
        super("testData");
        this.d = "TestDataStore";
    }

    public static TestOfflineDataStore getInstance() {
        if (f5966e == null) {
            synchronized (TestOfflineDataStore.class) {
                if (f5966e == null) {
                    f5966e = new TestOfflineDataStore();
                }
            }
        }
        return f5966e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.libs.hybrid.base.util.HybridDataStore
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public TestModule a(JSONObject jSONObject) throws JSONException {
        return new TestModule().fromJson(jSONObject);
    }

    public void delete(TestModule testModule) {
        if (testModule != null) {
            super.delete(testModule.getAppid());
        }
    }

    public TestModule getByRegexpUrl(String str, int i2) {
        Map<String, V> map;
        if (!TextUtils.isEmpty(str) && (map = this.f5899c) != 0 && !map.isEmpty()) {
            try {
                for (TestModule testModule : this.f5899c.values()) {
                    if (!testModule.isShared() && testModule.isRegexpMatch() && HybridUrlUtils.isRegexpMatched(testModule.getOriginalUrl(), str) && (i2 < 0 || i2 == testModule.getFileInfo().getVersionCode())) {
                        return testModule.publicClone();
                    }
                }
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
            }
        }
        return null;
    }

    public TestModule getByUrl(String str, int i2) {
        Map<String, V> map;
        if (!TextUtils.isEmpty(str) && (map = this.f5899c) != 0 && !map.isEmpty()) {
            try {
                for (TestModule testModule : this.f5899c.values()) {
                    if (!testModule.isShared() && str.equalsIgnoreCase(testModule.getOriginalUrl()) && (i2 < 0 || i2 == testModule.getFileInfo().getVersionCode())) {
                        return testModule.publicClone();
                    }
                }
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
            }
        }
        return null;
    }

    public TestModule getSharedByRegexpUrl(String str) {
        Map<String, V> map;
        if (!TextUtils.isEmpty(str) && (map = this.f5899c) != 0 && !map.isEmpty()) {
            try {
                for (TestModule testModule : this.f5899c.values()) {
                    if (testModule.isShared() && testModule.isRegexpMatch() && HybridUrlUtils.isRegexpMatched(testModule.getOriginalUrl(), str)) {
                        return testModule.publicClone();
                    }
                }
            } catch (CloneNotSupportedException e2) {
                Log.e(this.d, e2);
            }
        }
        return null;
    }

    public boolean save(TestModule testModule) {
        if (testModule != null) {
            return super.save(testModule.getAppid(), testModule);
        }
        return false;
    }
}
