package com.jingdong.c.a.b;

import android.graphics.Bitmap;
import com.jingdong.common.entity.ShareInfo;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/* loaded from: classes7.dex */
public interface c {
    boolean check();

    boolean checkSupportFileProvider();

    void doWXShare(ShareInfo shareInfo, boolean z, boolean z2, byte[] bArr);

    void doWXShare(ShareInfo shareInfo, boolean z, byte[] bArr, Bitmap bitmap);

    void doWXShare(ShareInfo shareInfo, boolean z, byte[] bArr, String str);

    IWXAPI getWXApi();
}
