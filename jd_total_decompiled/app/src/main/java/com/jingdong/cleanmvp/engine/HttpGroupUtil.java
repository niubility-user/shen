package com.jingdong.cleanmvp.engine;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;

/* loaded from: classes4.dex */
public class HttpGroupUtil {
    private HttpGroupWithNPS mHttpGroupWithNPS = null;
    private HttpGroup httpGroup = null;

    public void destroyHttpGroup() {
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup != null) {
            httpGroup.onDestroy();
            this.httpGroup = null;
        }
        destroyHttpGroupNPS();
    }

    public void destroyHttpGroupNPS() {
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            httpGroupWithNPS.destory();
            this.mHttpGroupWithNPS = null;
        }
    }

    public HttpGroupWithNPS getHttpGroupNPS() {
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            return httpGroupWithNPS;
        }
        return null;
    }

    public HttpGroup getHttpGroupWithNPSGroup(IMyActivity iMyActivity, ViewGroup viewGroup) {
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            HttpGroup httpGroup = httpGroupWithNPS.getHttpGroup();
            this.httpGroup = httpGroup;
            if (httpGroup != null) {
                if (httpGroup.getHttpGroupSetting() != null) {
                    this.httpGroup.getHttpGroupSetting().setMyActivity((Activity) iMyActivity);
                }
                return this.httpGroup;
            }
        }
        return getHttpGroupaAsynPool(iMyActivity, viewGroup);
    }

    public HttpGroup getHttpGroupaAsynPool() {
        return getHttpGroupaAsynPool(1000);
    }

    public void pauseHttpGroupWithNps() {
        HttpGroupWithNPS httpGroupWithNPS = this.mHttpGroupWithNPS;
        if (httpGroupWithNPS != null) {
            httpGroupWithNPS.onPause();
            this.mHttpGroupWithNPS = null;
        }
    }

    public void setHttpGroupNPS(IMyActivity iMyActivity, ViewGroup viewGroup, String str, String str2, boolean z) {
        destroyHttpGroup();
        getHttpGroupaAsynPool(iMyActivity, viewGroup);
        this.mHttpGroupWithNPS = new HttpGroupWithNPS((Context) iMyActivity, this.httpGroup, str, str2, z);
    }

    public HttpGroup getHttpGroupaAsynPool(int i2) {
        return HttpGroupUtils.getHttpGroupaAsynPool(i2);
    }

    public HttpGroup getHttpGroupaAsynPool(HttpGroupSetting httpGroupSetting) {
        HttpGroup httpGroup = HttpGroup.getHttpGroup(httpGroupSetting);
        this.httpGroup = httpGroup;
        return httpGroup;
    }

    public HttpGroup getHttpGroupaAsynPool(IMyActivity iMyActivity, ViewGroup viewGroup) {
        HttpGroup httpGroup = this.httpGroup;
        return httpGroup != null ? httpGroup : getHttpGroupaAsynPool(iMyActivity, 1000, viewGroup);
    }

    public HttpGroup getHttpGroupaAsynPool(IMyActivity iMyActivity, int i2, ViewGroup viewGroup) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setMyActivity((Activity) iMyActivity);
        createNewSettings.setType(i2);
        createNewSettings.setProgressBarRootLayout(viewGroup);
        return getHttpGroupaAsynPool(createNewSettings);
    }
}
