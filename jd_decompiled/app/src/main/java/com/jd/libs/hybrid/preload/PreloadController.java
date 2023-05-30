package com.jd.libs.hybrid.preload;

import android.content.Context;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import com.jd.libs.hybrid.preload.service.PreloadService;
import java.util.List;

/* loaded from: classes16.dex */
public class PreloadController {
    private PreloadService a;

    /* loaded from: classes16.dex */
    public interface Callback {
        void onCallback(PreloadInfoEntity preloadInfoEntity);
    }

    public PreloadController(Context context) {
        this.a = new PreloadService(context);
    }

    public void deleteAll() {
        this.a.deleteAll();
    }

    public void getEntityByUrl(String str, Callback callback) {
        this.a.getPreloadApi(str, callback);
    }

    public void refresh(List<PreloadInfoEntity> list) {
        this.a.refreshPreload(list);
    }
}
