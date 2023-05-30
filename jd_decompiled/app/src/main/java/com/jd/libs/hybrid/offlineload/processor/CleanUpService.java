package com.jd.libs.hybrid.offlineload.processor;

import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;

/* loaded from: classes16.dex */
public class CleanUpService {
    @Keep
    public static void deleteTempFiles() {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.CleanUpService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    OfflineFileUtils.deleteAllOldFiles();
                    OfflineFileUtils.deleteTempFiles(HybridSettings.getAppContext());
                    OfflineFileHelper.deleteAllOldFiles();
                    OfflineFileHelper.deleteTempFiles();
                } catch (Exception e2) {
                    Log.e("CleanUpService", e2);
                    OfflineExceptionUtils.reportError("hybrid_del_temp", "", "CleanUpService", ExceptionUtils.getStackStringFromException(e2));
                }
            }
        });
    }
}
