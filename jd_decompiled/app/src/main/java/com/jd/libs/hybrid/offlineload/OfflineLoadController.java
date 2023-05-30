package com.jd.libs.hybrid.offlineload;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.offlineload.db.BuildInDataStore;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.loader.CommonFileService;
import com.jd.libs.hybrid.offlineload.processor.ModuleConfigService;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes16.dex */
public class OfflineLoadController {
    private static OfflineLoadController b;
    private CommonFileService a;

    /* loaded from: classes16.dex */
    public static class ChangeEntityLists<T> {
        public List<T> addList;
        public ConfigMtaData configMtaData;
        public List<T> deleteList;
        public List<T> downloadList;
        public int newDownloadCount = 0;
        public int updateDownloadCount = 0;
        public List<T> updateList;
    }

    @Keep
    /* loaded from: classes16.dex */
    public interface ConfigCallback<T> {
        void onCacheCallback(T t, boolean z);

        void onFilesAvailable();
    }

    /* loaded from: classes16.dex */
    public static class ConfigMtaData {
        public int del;
        public int newDownload;
        public int newLocalAll;
        public int oldLocalAll;
        public int serverAll;
        public int updateDownload;
    }

    @Keep
    /* loaded from: classes16.dex */
    public interface NetConfigCallback<T> extends ConfigCallback<T> {
        void onNetworkCallback(T t, boolean z, boolean z2);
    }

    private OfflineLoadController(Context context) {
        this.a = new CommonFileService(context);
    }

    public static OfflineLoadController getInstance(Context context) {
        if (b == null) {
            synchronized (OfflineLoadController.class) {
                if (b == null) {
                    b = new OfflineLoadController(context);
                }
            }
        }
        return b;
    }

    public void deleteAllDownloaded() {
        deleteAllDownloadedCommon();
        deleteAllDownloadedOffline();
    }

    public void deleteAllDownloadedCommon() {
        this.a.deleteAllDownloaded();
    }

    public void deleteAllDownloadedOffline() {
        ModuleConfigService.deleteAllWithoutBuildIn();
    }

    public void getCommonOfflineFiles(ConfigCallback<List<CommonFile>> configCallback) {
        this.a.getAllEntities(configCallback);
    }

    public void getOfflineFiles(final String str, final NetConfigCallback<OfflineFiles> netConfigCallback) {
        if (GraySwitch.TYPE_4_GET_CONFIG_ASYNC) {
            DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.d
                @Override // java.lang.Runnable
                public final void run() {
                    new ModuleConfigService().getModuleByUrl(str, netConfigCallback);
                }
            });
        } else {
            new ModuleConfigService().getModuleByUrl(str, netConfigCallback);
        }
    }

    public void getSharedOfflineFiles(String str, ConfigCallback<OfflineFiles> configCallback) {
        new ModuleConfigService().getSharedModuleByUrl(str, configCallback);
    }

    public void refreshDownloadedCommonOffline(List<CommonEntity> list) {
        this.a.onAllChanged(list);
    }

    public void refreshModuleFromBuildIn() {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.c
            @Override // java.lang.Runnable
            public final void run() {
                ModuleConfigService.onFetchListFromBuildIn(BuildInDataStore.getInstance().getAll());
            }
        });
    }

    public void refreshModuleFromNet(final JSONArray jSONArray, final String str) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.b
            @Override // java.lang.Runnable
            public final void run() {
                ModuleConfigService.onFetchListFromNet(jSONArray, str);
            }
        });
    }

    public void startDownload(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ModuleConfigService.dispatchDownload(str);
    }
}
