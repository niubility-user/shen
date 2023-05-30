package com.jingdong.service.service;

import android.app.Activity;
import android.view.View;
import com.jingdong.service.entity.RnViewBean;

/* loaded from: classes10.dex */
public interface RNService {
    void createEngineHelper();

    void destroy();

    Object getEngineHelper();

    View getRNView(Activity activity, RnViewBean rnViewBean);

    boolean msgRNTemplateEanble();

    void resume(Activity activity);
}
