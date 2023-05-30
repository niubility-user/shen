package com.jd.hybrid.downloader;

/* loaded from: classes13.dex */
public interface e<T> {
    void onEnd(FileResponse<T> fileResponse);

    void onError(FileError fileError);

    void onProgress(int i2, int i3);

    void onStart();
}
