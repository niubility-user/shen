package com.jingdong.manto.sdk.api;

import android.app.Activity;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IActionBar extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface IMenuBtnClickCallBack extends IMantoSdkBase {
        void clickDebugSwtich();

        boolean getDebugStatus();
    }

    boolean hideCapsule();

    boolean onMoreBtnClick(Activity activity, String str, String str2, IMenuBtnClickCallBack iMenuBtnClickCallBack);
}
