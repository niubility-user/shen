package com.jd.lib.productdetail.core.utils;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.jd.lib.productdetail.core.entitys.PDVedioShareEntity;
import com.jd.lib.productdetail.core.floor.FloorThemeHelper;
import com.jingdong.common.entity.ProductDetailEntityBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.aac.util.SyncEventBus;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class PDManager {
    public static final String EXTRA_KEY = "key";
    public static final String EXTRA_KEY_1 = "key1";
    public static final String EXTRA_KEY_2 = "key2";
    public static final String EXTRA_KEY_FOR_PAIPAI = "inspectSkuId";
    private static final String TAG = "PDManager";
    public static final String WARE_SOURCE_FROM_ORDER_STYLE = "formOrderStyle";
    static volatile EventBus defaultEventBus;
    private static Map<String, PDManager> managerMap = new HashMap(3);
    private MutableLiveData<PDVedioShareEntity> mVedioShareData;
    private String mkey;
    private ProductDetailEntityBase product;
    private SyncEventBus syncEventBus;
    private FloorThemeHelper themeHelper;
    private boolean isDestroy = false;
    private Map<String, Boolean> shareStatusCacheMap = new HashMap();

    /* loaded from: classes15.dex */
    public interface PDListener extends SyncEventBus.EventBusListener {
    }

    private PDManager(String str) {
        this.mkey = str;
        this.syncEventBus = SyncEventBus.getInstances(str);
    }

    public static void destroy(String str) {
        PDManager remove;
        Map<String, PDManager> map = managerMap;
        if (map == null || (remove = map.remove(str)) == null) {
            return;
        }
        remove.onDestroy();
    }

    public static synchronized EventBus getEventBus() {
        EventBus eventBus;
        synchronized (PDManager.class) {
            if (defaultEventBus == null) {
                defaultEventBus = new EventBus();
            }
            eventBus = defaultEventBus;
        }
        return eventBus;
    }

    public static PDManager getExistManagerOrNull(String str) {
        if (managerMap.isEmpty()) {
            return null;
        }
        return managerMap.get(str);
    }

    public static synchronized PDManager getInstances(String str) {
        PDManager pDManager;
        synchronized (PDManager.class) {
            pDManager = managerMap.isEmpty() ? null : managerMap.get(str);
            if (pDManager == null) {
                pDManager = new PDManager(str);
                managerMap.put(str, pDManager);
            }
        }
        return pDManager;
    }

    private void onDestroy() {
        this.isDestroy = true;
        SyncEventBus.destroy(this.mkey);
        this.syncEventBus = null;
        this.shareStatusCacheMap.clear();
        this.shareStatusCacheMap = null;
        this.mVedioShareData = null;
    }

    public static void showProduct(String str, String str2, String str3) {
        getInstances(str).post("pd_ProductDetailActivity", "pd_PDTopViewrefreshProduct", str2, str3);
    }

    public Object getData(String str, String str2) {
        SyncEventBus syncEventBus;
        if (this.isDestroy) {
            return null;
        }
        if (Log.D) {
            Log.d(TAG, "action = " + str + " eventAction= " + str2);
        }
        if (TextUtils.isEmpty(str) || (syncEventBus = this.syncEventBus) == null) {
            return null;
        }
        return syncEventBus.getData(str, str2);
    }

    public String getKey() {
        return this.mkey;
    }

    public boolean getShareStatus(String str, boolean z) {
        Map<String, Boolean> map;
        return (TextUtils.isEmpty(str) || (map = this.shareStatusCacheMap) == null || map.isEmpty() || !this.shareStatusCacheMap.containsKey(str)) ? z : this.shareStatusCacheMap.get(str).booleanValue();
    }

    public String getSkuId() {
        ProductDetailEntityBase productDetailEntityBase = this.product;
        return productDetailEntityBase != null ? productDetailEntityBase.skuId : "";
    }

    public synchronized FloorThemeHelper getThemeHelper() {
        if (this.themeHelper == null) {
            this.themeHelper = new FloorThemeHelper();
        }
        return this.themeHelper;
    }

    public MutableLiveData<PDVedioShareEntity> getVedioShareData() {
        if (this.mVedioShareData == null) {
            this.mVedioShareData = new MutableLiveData<>();
            this.mVedioShareData.setValue(new PDVedioShareEntity());
        }
        return this.mVedioShareData;
    }

    public void initData(ProductDetailEntityBase productDetailEntityBase) {
        this.product = productDetailEntityBase;
    }

    public void post(String str, String str2) {
        post(str, str2, (Bundle) null);
    }

    public synchronized void registerListener(PDListener pDListener) {
        if (pDListener == null) {
            return;
        }
        SyncEventBus syncEventBus = this.syncEventBus;
        if (syncEventBus != null) {
            syncEventBus.register(pDListener);
        }
    }

    public void shareAnyStatus(String str, boolean z) {
        Map<String, Boolean> map;
        if (TextUtils.isEmpty(str) || (map = this.shareStatusCacheMap) == null) {
            return;
        }
        map.put(str, Boolean.valueOf(z));
    }

    public void post(String str, String str2, Bundle bundle) {
        SyncEventBus syncEventBus;
        if (this.isDestroy) {
            return;
        }
        if (Log.D) {
            Log.d(TAG, "action = " + str + " eventAction= " + str2);
        }
        if (TextUtils.isEmpty(str) || (syncEventBus = this.syncEventBus) == null) {
            return;
        }
        syncEventBus.post(str, str2, bundle);
    }

    public void post(String str, String str2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("key", z);
        post(str, str2, bundle);
    }

    public void post(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("key", str3);
        post(str, str2, bundle);
    }

    public void post(String str, String str2, String str3, String str4) {
        Bundle bundle = new Bundle();
        bundle.putString("key", str3);
        bundle.putString("key1", str4);
        post(str, str2, bundle);
    }

    public void post(String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", i2);
        post(str, str2, bundle);
    }

    public void post(String str, String str2, long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("key", j2);
        post(str, str2, bundle);
    }
}
