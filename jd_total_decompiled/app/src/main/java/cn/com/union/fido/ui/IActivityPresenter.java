package cn.com.union.fido.ui;

import android.app.Activity;

/* loaded from: classes.dex */
public interface IActivityPresenter {
    void handlerActivityResult(int i2);

    void setFidoView(IFidoView iFidoView);

    void startAuthenticator(Activity activity);
}
