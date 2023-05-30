package com.jd.libs.hybrid.preload;

import androidx.annotation.Keep;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;
import java.util.Map;

@Keep
/* loaded from: classes16.dex */
public interface IPreloadParamGetter {

    @Keep
    /* loaded from: classes16.dex */
    public static class PreloadParamGetter implements IPreloadParamGetter {
        @Override // com.jd.libs.hybrid.preload.IPreloadParamGetter
        public boolean allowPreloadData(PreloadInfoEntity preloadInfoEntity) {
            return true;
        }

        @Override // com.jd.libs.hybrid.preload.IPreloadParamGetter
        public Map<String, String> getFetchParams() {
            return null;
        }
    }

    boolean allowPreloadData(PreloadInfoEntity preloadInfoEntity);

    Map<String, String> getFetchParams();
}
