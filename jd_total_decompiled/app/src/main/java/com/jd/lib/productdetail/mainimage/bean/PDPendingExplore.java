package com.jd.lib.productdetail.mainimage.bean;

import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class PDPendingExplore {
    public String eventId;
    public HashMap<String, String> extParam;
    public String jsonParam;

    public PDPendingExplore(String str, String str2, HashMap<String, String> hashMap) {
        this.eventId = str;
        this.jsonParam = str2;
        this.extParam = hashMap;
    }

    public void explore(PdMainImagePresenter pdMainImagePresenter) {
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mtaExposure(this.eventId, this.jsonParam);
        }
    }
}
