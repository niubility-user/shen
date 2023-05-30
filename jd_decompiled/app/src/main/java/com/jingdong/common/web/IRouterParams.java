package com.jingdong.common.web;

import android.content.Context;

/* loaded from: classes.dex */
public interface IRouterParams {
    Context getContext();

    String getRouterParam();

    void onCallBack(Object obj);
}
