package com.jd.framework.network.file;

import com.jd.framework.network.JDResponseListener;

/* loaded from: classes13.dex */
public interface JDFileResponseListener<T> extends JDResponseListener<T> {
    void onPause();

    void onProgress(int i2, int i3);
}
