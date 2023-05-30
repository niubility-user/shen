package com.jingdong.common.jdmiaosha.view;

import android.app.Activity;
import android.view.ViewParent;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdmiaosha.listener.AccountWithCloseClickListener;
import com.jingdong.common.jdmiaosha.listener.NewAccountDisplayedListener;
import com.jingdong.common.jdmiaosha.listener.SupernatantClickListener;
import com.jingdong.common.jdmiaosha.listener.SupernatantDisplayedListener;

/* loaded from: classes5.dex */
public interface AbstractFloatingView {
    boolean bSupernatantShow();

    void cancelAllAnim();

    void closeAnim(int i2);

    void displayNewAccountByAmin();

    ViewParent getParentView();

    String getSuperEventSrv();

    void hideNewAccountByAmin();

    void initNewAccountData(JDJSONObject jDJSONObject, int i2);

    void initSupernatant();

    void initSupernatantData(JDJSONObject jDJSONObject);

    void resetCloseButton(boolean z);

    void resetNewAccountIcoView(Activity activity);

    void setAccountWithCloseClickListener(AccountWithCloseClickListener accountWithCloseClickListener);

    void setCanShow(boolean z);

    void setOnNewAccountDisplayedListener(NewAccountDisplayedListener newAccountDisplayedListener);

    void setOnSupernatantClickListener(SupernatantClickListener supernatantClickListener);

    void setOnSupernatantDisplayedListener(SupernatantDisplayedListener supernatantDisplayedListener);
}
