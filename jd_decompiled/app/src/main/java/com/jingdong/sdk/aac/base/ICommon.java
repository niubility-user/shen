package com.jingdong.sdk.aac.base;

import androidx.fragment.app.FragmentActivity;

/* loaded from: classes7.dex */
public interface ICommon {
    String getManagerKey();

    <T extends FragmentActivity> T getThisActivity();
}
