package com.jingdong.c.a.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.common.entity.ShareInfo;
import com.sina.weibo.sdk.openapi.IWBAPI;

/* loaded from: classes7.dex */
public interface b {
    boolean check(Context context);

    void doActivityResultIntent(Intent intent, ShareActivity shareActivity);

    void doWBShare(Activity activity, ShareInfo shareInfo, byte[] bArr);

    IWBAPI getWBShareApi(Context context);
}
