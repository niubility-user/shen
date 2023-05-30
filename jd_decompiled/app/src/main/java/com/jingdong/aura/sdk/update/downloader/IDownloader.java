package com.jingdong.aura.sdk.update.downloader;

import com.jingdong.aura.sdk.update.AuraBundleResult;
import java.util.List;

/* loaded from: classes4.dex */
public interface IDownloader {
    boolean cancelAll(List<AuraBundleResult> list);

    boolean cancelBySign(AuraBundleResult auraBundleResult);

    void download(AuraBundleResult auraBundleResult, IDownloadCallback iDownloadCallback);
}
