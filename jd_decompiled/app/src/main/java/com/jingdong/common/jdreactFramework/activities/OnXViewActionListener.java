package com.jingdong.common.jdreactFramework.activities;

import com.jingdong.common.jdreactFramework.JDCallback;

/* loaded from: classes5.dex */
public interface OnXViewActionListener {
    void closeXView();

    void destroyXView();

    void showXView(int i2, String str, boolean z, long j2, JDCallback jDCallback, JDCallback jDCallback2);

    void showXView(int i2, String str, boolean z, long j2, boolean z2, JDCallback jDCallback, JDCallback jDCallback2);
}
