package com.jingdong.common.XView;

import android.view.ViewGroup;

/* loaded from: classes5.dex */
public interface IXView {
    @Deprecated
    void autoShowXView();

    void closeXView();

    void configCloseButton(String str, float f2, float f3);

    void configXView(ViewGroup viewGroup, XViewEntity xViewEntity, XViewCallBack xViewCallBack);

    void destroyXView();

    boolean displayXView();

    void execJs(String str);

    boolean isXViewShow();

    void onResume();

    void onStop();

    @Deprecated
    void preloadXView();

    void startXView();
}
