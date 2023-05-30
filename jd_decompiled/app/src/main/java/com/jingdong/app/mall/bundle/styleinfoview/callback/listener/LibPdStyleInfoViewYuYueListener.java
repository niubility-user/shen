package com.jingdong.app.mall.bundle.styleinfoview.callback.listener;

import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDIsAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDLVEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDOperAppointEntity;

/* loaded from: classes3.dex */
public interface LibPdStyleInfoViewYuYueListener {
    void dealLvData(PDLVEntity pDLVEntity);

    void onAppointted(PDOperAppointEntity pDOperAppointEntity, String str);

    void onIsAppointGetted(boolean z, PDIsAppointEntity pDIsAppointEntity);

    void onLogin();
}
