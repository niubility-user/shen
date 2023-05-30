package com.jingdong.common.web.ui;

import android.content.Context;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.widget.NavigatorHolder;

/* loaded from: classes12.dex */
public interface IJdWebViewUi {
    Context getContext();

    JDWebView getJdWebView();

    NavigatorHolder getNaviHolder();

    WebEntity getWebEntity();

    IWebUiBinder getWebUiBinder();
}
