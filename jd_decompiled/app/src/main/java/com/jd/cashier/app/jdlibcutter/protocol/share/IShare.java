package com.jd.cashier.app.jdlibcutter.protocol.share;

import android.content.Context;
import java.util.Map;

/* loaded from: classes13.dex */
public interface IShare {
    void doShare(Context context, Map<String, String> map);

    int getShareRequestCode();

    int getShareResultSucCode();
}
