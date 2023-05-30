package com.jingdong.manto;

import android.view.MotionEvent;
import com.jingdong.manto.card.CardRequestParameter;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.q.s;

/* loaded from: classes15.dex */
public interface e extends MantoCore {
    boolean dispatchTouchEvent(MotionEvent motionEvent);

    CardRequestParameter getCardRequestParameter();

    f getLatestRuntime();

    com.jingdong.manto.jsapi.webview.g getMantoWebViewContainer();

    void hideLoading();

    void hideSplash(s.c cVar);

    boolean isSameToHostTask();

    int registMantoWebviewInterface(IMantoWebViewJS iMantoWebViewJS, String str);

    void removeSplashView();

    void setGestureMode(String str);

    void setTaskDescription();

    void setTitle(String str, int i2);

    void showLoading();

    void showSplashView(String str, String str2, int i2);
}
