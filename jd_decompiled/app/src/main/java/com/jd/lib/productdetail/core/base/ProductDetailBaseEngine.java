package com.jd.lib.productdetail.core.base;

import android.view.ViewGroup;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupWithNPS;

/* loaded from: classes15.dex */
public abstract class ProductDetailBaseEngine {
    public static final String TAG = "ProductDetailBaseEngine";
    protected HttpGroupUtil httpGroupUtil;

    public ProductDetailBaseEngine(HttpGroupUtil httpGroupUtil) {
        this.httpGroupUtil = httpGroupUtil;
    }

    public void destroyHttpGroup() {
        HttpGroupUtil httpGroupUtil = this.httpGroupUtil;
        if (httpGroupUtil != null) {
            httpGroupUtil.destroyHttpGroup();
        }
    }

    public HttpGroupUtil getHttpGroupUtil() {
        return this.httpGroupUtil;
    }

    public HttpGroupWithNPS getHttpGroupWithNps() {
        HttpGroupUtil httpGroupUtil = this.httpGroupUtil;
        if (httpGroupUtil != null) {
            return httpGroupUtil.getHttpGroupNPS();
        }
        return null;
    }

    public HttpGroup getHttpGroupWithNpsGroup(IMyActivity iMyActivity) {
        HttpGroupUtil httpGroupUtil = this.httpGroupUtil;
        if (httpGroupUtil != null) {
            return httpGroupUtil.getHttpGroupWithNPSGroup(iMyActivity, null);
        }
        return null;
    }

    public void pauseHttpGroupWithNps() {
        HttpGroupUtil httpGroupUtil = this.httpGroupUtil;
        if (httpGroupUtil != null) {
            httpGroupUtil.pauseHttpGroupWithNps();
        }
    }

    public void setHttpGroupNPS(IMyActivity iMyActivity, ViewGroup viewGroup, String str, String str2, boolean z) {
        this.httpGroupUtil.setHttpGroupNPS(iMyActivity, viewGroup, str, str2, z);
    }
}
