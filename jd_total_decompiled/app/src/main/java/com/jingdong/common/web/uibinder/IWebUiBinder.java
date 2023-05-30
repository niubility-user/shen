package com.jingdong.common.web.uibinder;

import android.content.Intent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.ui.IJdWebViewUi;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;

/* loaded from: classes12.dex */
public interface IWebUiBinder {
    void bindUi(IJdWebViewUi iJdWebViewUi);

    void finishUi();

    BaseActivity getBaseActivity();

    <T extends IJavaInterface> T getJavaInterfaceObj(String str);

    JDWebView getJdWebView();

    IJdWebViewUi getUi();

    WebEntity getWebEntity();

    IWebViewUrlInterceptor getWebViewUrlInterceptor();

    void startActivity(Intent intent);

    void unbindUi();
}
