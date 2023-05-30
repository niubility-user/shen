package com.jd.libs.hybrid.preload.service;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.VersionUtils;
import com.jd.libs.hybrid.preload.PreloadController;
import com.jd.libs.hybrid.preload.db.PreloadDatabase;
import com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class PreloadService {
    private final Context a;
    private PreloadInfoDao b;

    public PreloadService(Context context) {
        this.a = context.getApplicationContext();
        this.b = PreloadDatabase.getInstance(context.getApplicationContext()).getDao();
    }

    public void deleteAll() {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.preload.service.PreloadService.1
            @Override // java.lang.Runnable
            public void run() {
                PreloadService.this.b.deleteAll();
            }
        });
    }

    public void getPreloadApi(String str, final PreloadController.Callback callback) {
        final String trim = str != null ? str.trim() : null;
        if (Log.isDebug()) {
            Log.xLogD("PreloadService", "\u63a5\u53e3\u9884\u52a0\u8f7d\uff1a\u6b63\u5728\u67e5\u627e\u662f\u5426\u5b58\u5728\u63a5\u53e3\u9884\u52a0\u8f7d\u914d\u7f6e\uff0cURL\uff1a" + trim);
        }
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.preload.service.PreloadService.2
            @Override // java.lang.Runnable
            public void run() {
                List<PreloadInfoEntity> allByUrlRegexp;
                try {
                    if (!TextUtils.isEmpty(trim) && !HybridSettings.isPreloadDisable()) {
                        PreloadInfoEntity oneByUrl = PreloadService.this.b.getOneByUrl(HybridUrlUtils.excludeQuery(trim));
                        if (oneByUrl != null && Log.isDebug()) {
                            Log.xLogDForDev("PreloadService", "(\u63a5\u53e3\u9884\u52a0\u8f7d)\u627e\u5230\u9884\u52a0\u8f7d\u914d\u7f6e\uff0curl: " + trim);
                        }
                        if (oneByUrl == null && (allByUrlRegexp = PreloadService.this.b.getAllByUrlRegexp()) != null && allByUrlRegexp.size() > 0) {
                            Iterator<PreloadInfoEntity> it = allByUrlRegexp.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PreloadInfoEntity next = it.next();
                                if (next != null && next.isRegexpMatch() && HybridUrlUtils.isRegexpMatched(next.getOriginalUrl(), trim)) {
                                    if (Log.isDebug()) {
                                        Log.xLogDForDev("PreloadService", "(\u63a5\u53e3\u9884\u52a0\u8f7d)\u627e\u5230\u9884\u52a0\u8f7d\u914d\u7f6e\uff0curl\u6b63\u5219: " + next.getOriginalUrl());
                                    }
                                    oneByUrl = next;
                                }
                            }
                        }
                        if (oneByUrl != null && VersionUtils.isAppVersionBetween(PreloadService.this.a, oneByUrl.getAppMin(), oneByUrl.getAppMax())) {
                            if (Log.isDebug()) {
                                Log.xLogD("PreloadService", "\u63a5\u53e3\u9884\u52a0\u8f7d\uff1a\u6210\u529f\u627e\u5230\u63a5\u53e3\u9884\u52a0\u8f7d\u914d\u7f6e(id:" + oneByUrl.getAppid() + ")\uff0cURL\uff1a" + trim + "\uff0c\u672c\u5730\u914d\u7f6e\uff1a", JDJSON.toJSONString(oneByUrl));
                            }
                            Log.d("Query preload found: " + trim);
                            callback.onCallback(oneByUrl);
                            return;
                        }
                        if (Log.isDebug()) {
                            Log.xLogD("PreloadService", "\u63a5\u53e3\u9884\u52a0\u8f7d\uff1a\u672a\u627e\u5230\u63a5\u53e3\u9884\u52a0\u8f7d\u914d\u7f6e\uff0cURL\uff1a" + trim);
                        }
                        Log.d("Query preload NOT found: " + trim);
                        callback.onCallback(null);
                        return;
                    }
                    Log.d("Query preload NOT enable, or url is null: " + trim);
                    callback.onCallback(null);
                } catch (Exception e2) {
                    Log.e("PreloadService", e2);
                }
            }
        });
    }

    public void refreshPreload(final List<PreloadInfoEntity> list) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.preload.service.PreloadService.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    List<PreloadInfoEntity> removeUseless = IInterfaceCheck.Companion.removeUseless(list);
                    HashSet hashSet = new HashSet(removeUseless.size());
                    for (PreloadInfoEntity preloadInfoEntity : removeUseless) {
                        if (!TextUtils.isEmpty(preloadInfoEntity.getAppid())) {
                            hashSet.add(preloadInfoEntity.getAppid());
                        }
                    }
                    for (PreloadInfoEntity preloadInfoEntity2 : list) {
                        preloadInfoEntity2.setUrl(TextUtils.isEmpty(preloadInfoEntity2.getUrl()) ? "" : HybridUrlUtils.excludeQuery(preloadInfoEntity2.getUrl()));
                        String originalUrl = preloadInfoEntity2.getOriginalUrl();
                        if (!TextUtils.isEmpty(originalUrl)) {
                            if (!"2".equals(preloadInfoEntity2.getOriginalUrlType())) {
                                originalUrl = HybridUrlUtils.excludeQuery(originalUrl);
                            }
                            preloadInfoEntity2.setOriginalUrl(originalUrl);
                        }
                    }
                    List<PreloadInfoEntity> all = PreloadService.this.b.getAll();
                    ArrayList arrayList = new ArrayList(all.size());
                    for (PreloadInfoEntity preloadInfoEntity3 : all) {
                        if (!hashSet.contains(preloadInfoEntity3.getAppid())) {
                            arrayList.add(preloadInfoEntity3);
                        }
                    }
                    PreloadService.this.b.delete(arrayList);
                    PreloadService.this.b.save(list);
                    Log.d("Save preload success");
                } catch (Exception e2) {
                    Log.e("PreloadService", e2);
                }
            }
        });
    }
}
