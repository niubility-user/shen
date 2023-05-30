package tv.danmaku.ijk.media.alpha.download;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes11.dex */
public class AlphaDownloadConfig {
    private String cacheDirPath;
    private Context mContext;

    /* loaded from: classes11.dex */
    public static class Builder {
        public Context appContext;

        public Builder appContext(Context context) {
            this.appContext = context;
            return this;
        }

        public AlphaDownloadConfig builder() {
            return new AlphaDownloadConfig(this);
        }
    }

    public AlphaDownloadConfig(Builder builder) {
        try {
            Context context = builder.appContext;
            if (context != null && context.getExternalCacheDir() != null) {
                this.cacheDirPath = builder.appContext.getExternalCacheDir().getAbsolutePath() + "/JDAlphaVideo/";
            } else {
                this.cacheDirPath = Environment.getExternalStorageState() + "/JDAlphaVideo/";
            }
            if (!TextUtils.isEmpty(this.cacheDirPath)) {
                new File(this.cacheDirPath).mkdirs();
            }
            this.mContext = builder.appContext;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getCacheDirPath() {
        return this.cacheDirPath;
    }

    public Context getContext() {
        return this.mContext;
    }
}
