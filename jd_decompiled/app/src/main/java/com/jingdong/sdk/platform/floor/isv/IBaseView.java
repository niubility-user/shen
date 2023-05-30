package com.jingdong.sdk.platform.floor.isv;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes10.dex */
public interface IBaseView {
    View getView();

    void onBindData(String str);

    void onCreateView(ViewGroup viewGroup);

    void onDestroy();
}
