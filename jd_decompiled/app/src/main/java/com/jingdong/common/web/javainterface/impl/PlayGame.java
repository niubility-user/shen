package com.jingdong.common.web.javainterface.impl;

import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;

/* loaded from: classes12.dex */
public final class PlayGame extends BaseWebComponent implements IJavaInterface {
    private final String TAG;

    public PlayGame(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = PlayGame.class.getSimpleName();
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.PLAY_GAMES;
    }
}
