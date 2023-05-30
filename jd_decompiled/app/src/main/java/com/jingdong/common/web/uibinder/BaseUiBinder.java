package com.jingdong.common.web.uibinder;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.web.IWebBusinessParams;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.ui.IJdWebViewUi;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public abstract class BaseUiBinder implements IWebUiBinder, IWebBusinessParams {
    protected IJdWebViewUi jdWebViewUi;
    protected IWebViewUrlInterceptor urlInterceptor;
    protected Map<String, IJavaInterface> mJsObjectMap = new HashMap();
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected boolean bindFlag = false;

    /* JADX INFO: Access modifiers changed from: protected */
    public void addJavascriptInterface(IJavaInterface iJavaInterface) {
        IJdWebViewUi iJdWebViewUi = this.jdWebViewUi;
        if (iJdWebViewUi == null || iJdWebViewUi.getJdWebView() == null) {
            return;
        }
        this.mJsObjectMap.put(iJavaInterface.getName(), iJavaInterface);
        this.jdWebViewUi.getJdWebView().addJavascriptInterface(iJavaInterface, iJavaInterface.getName());
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public void bindUi(IJdWebViewUi iJdWebViewUi) {
        this.jdWebViewUi = iJdWebViewUi;
        if (this.bindFlag) {
            return;
        }
        this.urlInterceptor = newUrlInterceptor();
        onBindUi();
        this.bindFlag = true;
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public void finishUi() {
        getBaseActivity().finish();
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder, com.jingdong.common.web.IWebBusinessParams
    public BaseActivity getBaseActivity() {
        IJdWebViewUi iJdWebViewUi = this.jdWebViewUi;
        if (iJdWebViewUi != null) {
            return (BaseActivity) iJdWebViewUi.getContext();
        }
        return null;
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public <T extends IJavaInterface> T getJavaInterfaceObj(String str) {
        try {
            return (T) this.mJsObjectMap.get(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public JDWebView getJdWebView() {
        IJdWebViewUi iJdWebViewUi = this.jdWebViewUi;
        if (iJdWebViewUi != null) {
            return iJdWebViewUi.getJdWebView();
        }
        return null;
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public IJdWebViewUi getUi() {
        return this.jdWebViewUi;
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public WebEntity getWebEntity() {
        IJdWebViewUi iJdWebViewUi = this.jdWebViewUi;
        if (iJdWebViewUi != null) {
            return iJdWebViewUi.getWebEntity();
        }
        return null;
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public IWebViewUrlInterceptor getWebViewUrlInterceptor() {
        return this.urlInterceptor;
    }

    @Override // com.jingdong.common.web.IWebBusinessParams
    public void injectJs(String str) {
        if (getJdWebView() != null) {
            getJdWebView().injectJs(str);
        }
    }

    protected abstract IWebViewUrlInterceptor newUrlInterceptor();

    protected abstract void onBindUi();

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public void startActivity(Intent intent) {
        getBaseActivity().startActivity(intent);
    }

    @Override // com.jingdong.common.web.uibinder.IWebUiBinder
    public void unbindUi() {
    }
}
