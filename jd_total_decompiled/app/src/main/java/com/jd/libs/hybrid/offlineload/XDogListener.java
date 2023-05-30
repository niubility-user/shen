package com.jd.libs.hybrid.offlineload;

import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.offlineload.db.OfflineDataStore;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.xdog.e;
import com.jd.libs.xdog.f;
import java.io.File;
import java.util.Map;

/* loaded from: classes16.dex */
public class XDogListener implements e {

    /* renamed from: com.jd.libs.hybrid.offlineload.XDogListener$2 */
    /* loaded from: classes16.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[LocalFileType.values().length];
            a = iArr;
            try {
                iArr[LocalFileType.FILE_TYPE_PKG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[LocalFileType.FILE_TYPE_PKG_SHARED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[LocalFileType.FILE_TYPE_GLOBAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[LocalFileType.FILE_TYPE_GLOBAL_BUILD_IN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static int getResourceType(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1679012641:
                if (str.equals("\u9879\u76ee\u5185\u6587\u4ef6")) {
                    c2 = 0;
                    break;
                }
                break;
            case -413958271:
                if (str.equals("\u5185\u7f6e\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6")) {
                    c2 = 1;
                    break;
                }
                break;
            case 294090443:
                if (str.equals("\u516c\u5171\u79bb\u7ebf\u5305\u6587\u4ef6")) {
                    c2 = 2;
                    break;
                }
                break;
            case 401062392:
                if (str.equals("\u5168\u5c40\u516c\u5171\u8d44\u6e90\u6587\u4ef6")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 1;
            case 1:
                return 4;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 0;
        }
    }

    public static void xLocalFileListStr(final OfflineFiles offlineFiles) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.XDogListener.1
            @Override // java.lang.Runnable
            public void run() {
                String[] list;
                if (f.u) {
                    File file = new File(offlineFiles.getFileRootPath());
                    if (file.exists() && file.isDirectory() && (list = file.list()) != null && list.length > 0) {
                        com.jd.libs.xdog.b.f(offlineFiles.getAppId(), FileUtils.getStringFromFile(HybridSettings.getAppContext(), new File(file, "resource.json")));
                    }
                }
            }
        });
    }

    @Override // com.jd.libs.xdog.e
    public void notify(com.jd.libs.xdog.d dVar) {
        Map<String, OfflineModule> all = OfflineDataStore.getInstance().getAll();
        if (all != null && !all.isEmpty()) {
            dVar.onSuccess(all.values().toString());
        } else {
            dVar.onError("data is empty");
        }
    }

    public static int getResourceType(LocalFileType localFileType) {
        int i2 = AnonymousClass2.a[localFileType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return i2 != 4 ? 0 : 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }
}
