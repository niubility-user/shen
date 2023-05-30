package com.jingdong.common.web.uilistener.impl;

import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.uilistener.IWebViewUrlInterceptor;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.urlcheck.IInterceptRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class WebViewUrlInterceptorImpl extends BaseWebComponent implements IWebViewUrlInterceptor {
    protected IInterceptRequest mCheckShouldInterceptRequest;
    protected LinkedHashMap<String, ICheckUrl> mCheckUrlOnCommitVisibleMap;
    protected LinkedHashMap<String, ICheckUrl> mCheckUrlOnPageFinishMap;
    protected LinkedHashMap<String, ICheckUrl> mCheckUrlOnPageStartAfterDefaultNaviMap;
    protected LinkedHashMap<String, ICheckUrl> mCheckUrlOnPageStartMap;
    protected LinkedHashMap<String, ICheckUrl> mCheckUrlShouldOverrideLoadingMap;

    public WebViewUrlInterceptorImpl() {
        super(null);
        this.mCheckUrlOnPageStartMap = new LinkedHashMap<>();
        this.mCheckUrlOnPageStartAfterDefaultNaviMap = new LinkedHashMap<>();
        this.mCheckUrlOnPageFinishMap = new LinkedHashMap<>();
        this.mCheckUrlShouldOverrideLoadingMap = new LinkedHashMap<>();
        this.mCheckUrlOnCommitVisibleMap = new LinkedHashMap<>();
        this.mCheckShouldInterceptRequest = null;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnCommitVisible(ICheckUrl iCheckUrl) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap;
        if (iCheckUrl == null || (linkedHashMap = this.mCheckUrlOnCommitVisibleMap) == null) {
            return;
        }
        linkedHashMap.put(iCheckUrl.getKey(), iCheckUrl);
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnPageFinish(ICheckUrl iCheckUrl) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap;
        if (iCheckUrl == null || (linkedHashMap = this.mCheckUrlOnPageFinishMap) == null) {
            return;
        }
        linkedHashMap.put(iCheckUrl.getKey(), iCheckUrl);
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnPageStart(ICheckUrl iCheckUrl) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap;
        if (iCheckUrl == null || (linkedHashMap = this.mCheckUrlOnPageStartMap) == null) {
            return;
        }
        linkedHashMap.put(iCheckUrl.getKey(), iCheckUrl);
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlCheckOnPageStartAfterDefaultNavi(ICheckUrl iCheckUrl) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap;
        if (iCheckUrl == null || (linkedHashMap = this.mCheckUrlOnPageStartAfterDefaultNaviMap) == null) {
            return;
        }
        linkedHashMap.put(iCheckUrl.getKey(), iCheckUrl);
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void addUrlShouldOverrideLoading(ICheckUrl iCheckUrl) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap;
        if (iCheckUrl == null || (linkedHashMap = this.mCheckUrlShouldOverrideLoadingMap) == null) {
            return;
        }
        linkedHashMap.put(iCheckUrl.getKey(), iCheckUrl);
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public <T extends ICheckUrl> T getUrlCheck(String str) {
        try {
            LinkedHashMap<String, ICheckUrl> linkedHashMap = this.mCheckUrlShouldOverrideLoadingMap;
            if (linkedHashMap != null && linkedHashMap.containsKey(str)) {
                return (T) this.mCheckUrlShouldOverrideLoadingMap.get(str);
            }
            LinkedHashMap<String, ICheckUrl> linkedHashMap2 = this.mCheckUrlOnPageStartMap;
            if (linkedHashMap2 != null && linkedHashMap2.containsKey(str)) {
                return (T) this.mCheckUrlOnPageStartMap.get(str);
            }
            LinkedHashMap<String, ICheckUrl> linkedHashMap3 = this.mCheckUrlOnPageStartAfterDefaultNaviMap;
            if (linkedHashMap3 != null && linkedHashMap3.containsKey(str)) {
                return (T) this.mCheckUrlOnPageStartAfterDefaultNaviMap.get(str);
            }
            LinkedHashMap<String, ICheckUrl> linkedHashMap4 = this.mCheckUrlOnCommitVisibleMap;
            if (linkedHashMap4 != null && linkedHashMap4.containsKey(str)) {
                return (T) this.mCheckUrlOnCommitVisibleMap.get(str);
            }
            LinkedHashMap<String, ICheckUrl> linkedHashMap5 = this.mCheckUrlOnPageFinishMap;
            if (linkedHashMap5 == null || !linkedHashMap5.containsKey(str)) {
                return null;
            }
            return (T) this.mCheckUrlOnPageFinishMap.get(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public boolean interceptOnPageStart(WebView webView, String str) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap = this.mCheckUrlOnPageStartMap;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return false;
        }
        Iterator<Map.Entry<String, ICheckUrl>> it = this.mCheckUrlOnPageStartMap.entrySet().iterator();
        while (it.hasNext()) {
            ICheckUrl value = it.next().getValue();
            if (value != null && value.checkUrl(webView, str) && value.checkReturn()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void interceptOnPageStartAfterDefaultNavi(WebView webView, String str) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap = this.mCheckUrlOnPageStartAfterDefaultNaviMap;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        Iterator<Map.Entry<String, ICheckUrl>> it = this.mCheckUrlOnPageStartAfterDefaultNaviMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().checkUrl(webView, str);
        }
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public WebResourceResponse interceptRequest(WebView webView, String str) {
        IInterceptRequest iInterceptRequest = this.mCheckShouldInterceptRequest;
        if (iInterceptRequest != null) {
            return iInterceptRequest.interceptRequest(webView, str);
        }
        return null;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public boolean interceptShouldOverrideLoading(WebView webView, String str) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap = this.mCheckUrlShouldOverrideLoadingMap;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return false;
        }
        Iterator<Map.Entry<String, ICheckUrl>> it = this.mCheckUrlShouldOverrideLoadingMap.entrySet().iterator();
        while (it.hasNext()) {
            ICheckUrl value = it.next().getValue();
            if (value != null && value.checkUrl(webView, str) && value.checkReturn()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void onPageCommitVisible(WebView webView, String str) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap = this.mCheckUrlOnCommitVisibleMap;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        Iterator<Map.Entry<String, ICheckUrl>> it = this.mCheckUrlOnCommitVisibleMap.entrySet().iterator();
        while (it.hasNext()) {
            ICheckUrl value = it.next().getValue();
            if (value != null && value.checkUrl(webView, str) && value.checkReturn()) {
                return;
            }
        }
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void onPageFinished(WebView webView, String str) {
        LinkedHashMap<String, ICheckUrl> linkedHashMap = this.mCheckUrlOnPageFinishMap;
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        Iterator<Map.Entry<String, ICheckUrl>> it = this.mCheckUrlOnPageFinishMap.entrySet().iterator();
        while (it.hasNext()) {
            ICheckUrl value = it.next().getValue();
            if (value != null && value.checkUrl(webView, str) && value.checkReturn()) {
                return;
            }
        }
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public void setShouldInterceptRequest(IInterceptRequest iInterceptRequest) {
        this.mCheckShouldInterceptRequest = iInterceptRequest;
    }

    @Override // com.jingdong.common.web.uilistener.IWebViewUrlInterceptor
    public WebResourceResponse interceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        IInterceptRequest iInterceptRequest = this.mCheckShouldInterceptRequest;
        if (iInterceptRequest != null) {
            return iInterceptRequest.interceptRequest(webView, webResourceRequest);
        }
        return null;
    }

    public WebViewUrlInterceptorImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.mCheckUrlOnPageStartMap = new LinkedHashMap<>();
        this.mCheckUrlOnPageStartAfterDefaultNaviMap = new LinkedHashMap<>();
        this.mCheckUrlOnPageFinishMap = new LinkedHashMap<>();
        this.mCheckUrlShouldOverrideLoadingMap = new LinkedHashMap<>();
        this.mCheckUrlOnCommitVisibleMap = new LinkedHashMap<>();
        this.mCheckShouldInterceptRequest = null;
    }
}
