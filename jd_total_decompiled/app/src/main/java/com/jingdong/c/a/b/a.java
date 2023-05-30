package com.jingdong.c.a.b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.common.entity.ShareInfo;
import com.tencent.tauth.Tencent;

/* loaded from: classes7.dex */
public interface a {
    boolean check(Context context);

    Tencent getTencentInstance();

    void injectIUiListener(ShareActivity shareActivity);

    boolean onActivityResultData(int i2, int i3, Intent intent);

    void shareToQQ(Activity activity, ShareInfo shareInfo);

    void shareToQQ(Activity activity, ShareInfo shareInfo, String str);

    void shareToQZone(Activity activity, ShareInfo shareInfo);
}
