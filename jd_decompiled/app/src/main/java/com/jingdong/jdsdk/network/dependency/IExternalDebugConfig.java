package com.jingdong.jdsdk.network.dependency;

import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes.dex */
public interface IExternalDebugConfig {
    void addMockerIdName(HttpSetting httpSetting);

    boolean isForceHttpDownGrade();
}
