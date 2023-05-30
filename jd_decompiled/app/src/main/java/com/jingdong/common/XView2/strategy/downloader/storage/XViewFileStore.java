package com.jingdong.common.XView2.strategy.downloader.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.util.SharedPreferenceUtils;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.strategy.downloader.entity.XViewFileEntity;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XViewMtaUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.FileService;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XViewFileStore {
    private static final String DATA_FILE_PREFIX = "XView_";
    private String dbName;
    private List<XViewFileEntity> mPreDownLoadResourceEntityList;
    private SharedPreferences mXViewJsonCacheSP;
    private SharedPreferences mXViewPreference;
    protected Map<String, XViewFileEntity> mXViewDataInMem = null;
    protected Map<String, String> mXViewJsonCacheData = null;
    protected String TAG = "XViewFileStore";

    public XViewFileStore() {
        getAllSharedPreferenceData();
        getAllSharedPreferenceJsonCacheData();
    }

    private void deleteFile(XViewFileEntity xViewFileEntity) {
        String[] list;
        if (xViewFileEntity.filePath != null) {
            File file = new File(xViewFileEntity.filePath);
            if (file.exists()) {
                File parentFile = file.getParentFile();
                file.delete();
                if (parentFile != null && (list = parentFile.list()) != null && list.length == 0) {
                    parentFile.delete();
                }
            }
            xViewFileEntity.filePath = null;
        }
    }

    public boolean checkIsAllDownLoaded(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.mXViewDataInMem == null) {
            return false;
        }
        synchronized (this) {
            for (String str3 : this.mXViewDataInMem.keySet()) {
                if (this.mXViewDataInMem.get(str3) != null && !TextUtils.isEmpty(this.mXViewDataInMem.get(str3).layerId) && str.equals(this.mXViewDataInMem.get(str3).layerId) && (this.mXViewDataInMem.get(str3).status != 1 || this.mXViewDataInMem.get(str3).filePath == null || !new File(this.mXViewDataInMem.get(str3).filePath).exists())) {
                    return false;
                }
            }
            String str4 = str.trim() + str2.trim();
            Map<String, String> map = this.mXViewJsonCacheData;
            if (map != null) {
                map.put(str4, str2);
            }
            SharedPreferences sharedPreferences = this.mXViewJsonCacheSP;
            if (sharedPreferences != null) {
                SharedPreferenceUtils.commitString(sharedPreferences, str4, str2);
            }
            return true;
        }
    }

    public void checkIsLayerDownLoadSuccessMta(XViewFileEntity xViewFileEntity) {
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PRELOAD_SUCCESS, false) && xViewFileEntity != null) {
            String str = xViewFileEntity.layerId;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String currentLayerJson = getCurrentLayerJson(str);
            if (!isJsonAlreadyCached(currentLayerJson, str) && checkIsAllDownLoaded(str, currentLayerJson)) {
                XViewMtaUtil.setSuccessDownLoadMta(JdSdk.getInstance().getApplicationContext(), str, xViewFileEntity.targetId, currentLayerJson);
            }
        }
    }

    public void delete(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        synchronized (this) {
            for (String str : list) {
                this.mXViewDataInMem.remove(str);
                SharedPreferenceUtils.putString(this.mXViewPreference, str, null);
            }
            SharedPreferenceUtils.commit(this.mXViewPreference);
        }
    }

    public void deleteCacheJson(List<String> list) {
        if (!SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PRELOAD_SUCCESS, false) || list == null || list.isEmpty()) {
            return;
        }
        synchronized (this) {
            for (String str : list) {
                Map<String, String> map = this.mXViewJsonCacheData;
                if (map != null && map.containsKey(str)) {
                    this.mXViewJsonCacheData.remove(str);
                    SharedPreferenceUtils.putString(this.mXViewJsonCacheSP, str, null);
                }
            }
            SharedPreferenceUtils.commit(this.mXViewJsonCacheSP);
        }
    }

    @Nullable
    public XViewFileEntity get(String str) {
        XViewFileEntity xViewFileEntity = this.mXViewDataInMem.get(str);
        if (xViewFileEntity != null) {
            try {
                return xViewFileEntity.publicClone();
            } catch (CloneNotSupportedException unused) {
                return null;
            }
        }
        return null;
    }

    public void getAllSharedPreferenceData() {
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        this.dbName = "XView_Sp";
        SharedPreferences createPreference = SharedPreferenceUtils.createPreference(applicationContext, "XView_Sp");
        this.mXViewPreference = createPreference;
        if (createPreference == null) {
            return;
        }
        Map<String, ?> all = createPreference.getAll();
        if (all != null && !all.isEmpty()) {
            this.mXViewDataInMem = new ConcurrentHashMap(all.size());
            for (String str : all.keySet()) {
                try {
                    XViewFileEntity newFromJson = newFromJson(new JSONObject(this.mXViewPreference.getString(str, null)));
                    if (newFromJson != null) {
                        this.mXViewDataInMem.put(str, newFromJson);
                    }
                } catch (Exception unused) {
                }
            }
            return;
        }
        this.mXViewDataInMem = new ConcurrentHashMap();
    }

    public void getAllSharedPreferenceJsonCacheData() {
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PRELOAD_SUCCESS, false)) {
            SharedPreferences createPreference = SharedPreferenceUtils.createPreference(JdSdk.getInstance().getApplicationContext(), "XView2_Json_Cache_Sp");
            this.mXViewJsonCacheSP = createPreference;
            if (createPreference == null) {
                return;
            }
            Map<String, ?> all = createPreference.getAll();
            if (all != null && !all.isEmpty()) {
                this.mXViewJsonCacheData = new ConcurrentHashMap(all.size());
                for (String str : all.keySet()) {
                    try {
                        String string = this.mXViewJsonCacheSP.getString(str, null);
                        if (!TextUtils.isEmpty(string)) {
                            this.mXViewJsonCacheData.put(str, string);
                        }
                    } catch (Exception unused) {
                    }
                }
                return;
            }
            this.mXViewJsonCacheData = new ConcurrentHashMap();
        }
    }

    public String getCurrentLayerJson(String str) {
        if (XView2Utils.isListEmpty(this.mPreDownLoadResourceEntityList) || TextUtils.isEmpty(str)) {
            return "";
        }
        for (XViewFileEntity xViewFileEntity : this.mPreDownLoadResourceEntityList) {
            if (xViewFileEntity != null && str.equals(xViewFileEntity.layerId) && FileService.CACHE_EXT_NAME_JSON.equals(XView2Utils.getFileSuffixFromPath(xViewFileEntity.url)) && xViewFileEntity.isDsl) {
                return xViewFileEntity.url;
            }
        }
        return "";
    }

    public XViewFileEntity getFileById(String str) {
        return get(str);
    }

    public XViewFileEntity getOriginalFile(String str) {
        return this.mXViewDataInMem.get(str);
    }

    public boolean isFileExpired(XViewFileEntity xViewFileEntity, long j2) {
        return xViewFileEntity.getExpiredTime() + 86400000 < j2;
    }

    public boolean isJsonAlreadyCached(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String str3 = str2.trim() + str.trim();
            Map<String, String> map = this.mXViewJsonCacheData;
            if (map != null && map.containsKey(str3)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTodayDeleted(long j2) {
        if (XView2Utils.isToday(new Date(XView2Utils.getLongFromSp("lastRealTime", 0L)))) {
            return true;
        }
        XViewLogPrint.JDXLog.e(this.TAG, "is today");
        XView2Utils.saveLongToSp("lastRealTime", j2);
        return false;
    }

    protected XViewFileEntity newFromJson(JSONObject jSONObject) throws JSONException {
        XViewFileEntity fromJson = new XViewFileEntity().fromJson(jSONObject);
        if (fromJson.filePath != null && new File(fromJson.filePath).exists()) {
            fromJson.status = 1;
        } else {
            fromJson.filePath = null;
        }
        return fromJson;
    }

    public void parseXViewFileEntityList(List<XViewFileEntity> list) {
        if (XView2Utils.isListEmpty(list)) {
            return;
        }
        this.mPreDownLoadResourceEntityList = list;
        HashMap hashMap = new HashMap();
        for (XViewFileEntity xViewFileEntity : list) {
            hashMap.put(xViewFileEntity.id, xViewFileEntity);
        }
        LinkedList linkedList = new LinkedList();
        long currentTimeMillis = System.currentTimeMillis();
        boolean isTodayDeleted = isTodayDeleted(currentTimeMillis);
        Map<String, XViewFileEntity> map = this.mXViewDataInMem;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (XViewFileEntity xViewFileEntity2 : this.mXViewDataInMem.values()) {
            XViewFileEntity xViewFileEntity3 = (XViewFileEntity) hashMap.get(xViewFileEntity2.id);
            if (xViewFileEntity3 == null) {
                if (!isTodayDeleted && isFileExpired(xViewFileEntity2, currentTimeMillis)) {
                    linkedList.add(xViewFileEntity2.id);
                    deleteFile(xViewFileEntity2);
                }
            } else {
                xViewFileEntity3.filePath = xViewFileEntity2.filePath;
                xViewFileEntity3.status = xViewFileEntity2.status;
                xViewFileEntity3.suffix = xViewFileEntity2.suffix;
                xViewFileEntity3.isDsl = xViewFileEntity2.isDsl;
                xViewFileEntity3.expiredTime = xViewFileEntity2.expiredTime;
                XViewLogPrint.JDXLog.e(this.TAG, "Existed download request, priority = " + xViewFileEntity2.project_priority + ", url: " + xViewFileEntity2.expiredTime);
            }
        }
        delete(linkedList);
        deleteCacheJson(linkedList);
        save(list);
    }

    public boolean save(String str, XViewFileEntity xViewFileEntity) {
        XViewFileEntity publicClone;
        if (xViewFileEntity == null) {
            return false;
        }
        try {
            publicClone = xViewFileEntity.publicClone();
        } catch (Exception unused) {
        }
        if (publicClone == null) {
            return false;
        }
        JSONObject json = publicClone.toJson();
        String jSONObject = json != null ? json.toString() : null;
        if (!TextUtils.isEmpty(jSONObject)) {
            synchronized (this) {
                this.mXViewDataInMem.put(str, publicClone);
                SharedPreferenceUtils.commitString(this.mXViewPreference, str, jSONObject);
            }
            return true;
        }
        return false;
    }

    public synchronized void update(XViewFileEntity xViewFileEntity) {
        save(xViewFileEntity.id, xViewFileEntity);
        checkIsLayerDownLoadSuccessMta(xViewFileEntity);
    }

    public boolean save(List<XViewFileEntity> list) {
        XViewFileEntity publicClone;
        if (XView2Utils.isListEmpty(list)) {
            return false;
        }
        try {
            synchronized (this) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    XViewFileEntity xViewFileEntity = list.get(i2);
                    if (xViewFileEntity != null && (publicClone = xViewFileEntity.publicClone()) != null) {
                        JSONObject json = publicClone.toJson();
                        String jSONObject = json != null ? json.toString() : null;
                        if (!TextUtils.isEmpty(jSONObject)) {
                            this.mXViewDataInMem.put(xViewFileEntity.id, publicClone);
                            SharedPreferenceUtils.putString(this.mXViewPreference, xViewFileEntity.id, jSONObject);
                        }
                    }
                }
                SharedPreferenceUtils.commit(this.mXViewPreference);
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
