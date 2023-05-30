package com.jingdong.common.web.urlcheck.impl;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.search.event.SearchIdPlusEvent;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.corelib.utils.Log;
import com.tencent.smtt.sdk.WebView;
import de.greenrobot.event.EventBus;

/* loaded from: classes12.dex */
public class SearchMyIdPlusImpl extends BaseWebComponent implements ICheckUrl {
    private final String TAG;

    public SearchMyIdPlusImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = SearchMyIdPlusImpl.class.getSimpleName();
    }

    private boolean checkMyIdPlusUrl(Uri uri) {
        String scheme;
        if (uri != null && (scheme = uri.getScheme()) != null && !scheme.equals("http") && !scheme.equals("https") && JumpUtil.isJdScheme(scheme)) {
            final String queryParameter = uri.getQueryParameter("params");
            if (!TextUtils.isEmpty(queryParameter) && queryParameter.indexOf("\"des\":\"searchmyidplus\"") >= 0) {
                this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.urlcheck.impl.SearchMyIdPlusImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EventBus.getDefault().post(new SearchIdPlusEvent(SearchIdPlusEvent.ADD_MY_IDPLUS_FROM_M, queryParameter));
                    }
                }, 1000);
                return true;
            }
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d(this.TAG, "start checkMyIdPlusUrl :" + parse);
        }
        if (checkMyIdPlusUrl(parse)) {
            this.webUiBinder.finishUi();
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_SEARCH_MYIDPLUS;
    }
}
