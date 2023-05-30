package com.jingdong.jdsdk.network.dependency;

import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes.dex */
public interface IBusinessJsonCodeEventListener {
    void onJsonCodeReceive(String str, HttpSetting httpSetting, HttpResponse httpResponse);
}
