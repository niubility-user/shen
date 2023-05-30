package tv.danmaku.ijk.media.alpha.download;

import java.io.File;
import java.util.concurrent.Callable;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimBean;
import tv.danmaku.ijk.media.utils.PlayerToolsUtil;

/* loaded from: classes11.dex */
public class AlphaResDownloadCallable implements Callable<String> {
    private static final String TAG = "AlphaResDownloadCallable";
    private final AlphaAnimBean alphaAnimBean;
    private final AlphaDownloadConfig downloadConfig;
    private final IAlphaListener.OnEventListener eventListener;

    public AlphaResDownloadCallable(AlphaAnimBean alphaAnimBean) {
        this(alphaAnimBean, null);
    }

    private boolean checkDownloadFile(File file) {
        if (file != null) {
            String fileMD5 = PlayerToolsUtil.getFileMD5(file);
            if (fileMD5 == null || !fileMD5.equals(this.alphaAnimBean.getMd5())) {
                file.deleteOnExit();
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0173 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x017d A[Catch: IOException -> 0x0144, TRY_ENTER, TRY_LEAVE, TryCatch #10 {IOException -> 0x0144, blocks: (B:59:0x0140, B:90:0x017d), top: B:116:0x0071 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x019f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0145 -> B:115:0x0180). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String downloadFromNet() {
        /*
            Method dump skipped, instructions count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.alpha.download.AlphaResDownloadCallable.downloadFromNet():java.lang.String");
    }

    private boolean generateAndCheckFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        String fileMD5 = PlayerToolsUtil.getFileMD5(file);
        if (fileMD5 == null || !fileMD5.equals(this.alphaAnimBean.getMd5())) {
            file.deleteOnExit();
            return false;
        }
        return true;
    }

    public AlphaResDownloadCallable(AlphaAnimBean alphaAnimBean, AlphaDownloadConfig alphaDownloadConfig) {
        this(alphaAnimBean, alphaDownloadConfig, null);
    }

    @Override // java.util.concurrent.Callable
    public String call() {
        return downloadFromNet();
    }

    public AlphaResDownloadCallable(AlphaAnimBean alphaAnimBean, AlphaDownloadConfig alphaDownloadConfig, IAlphaListener.OnEventListener onEventListener) {
        this.alphaAnimBean = alphaAnimBean;
        this.downloadConfig = alphaDownloadConfig;
        this.eventListener = onEventListener;
    }
}
