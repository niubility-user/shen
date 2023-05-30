package com.jingdong.common.utils;

import android.os.Binder;
import com.jingdong.common.utils.ShareUtil;

/* loaded from: classes6.dex */
public class ShareCallbackListenerBinder extends Binder {
    public ShareUtil.CallbackListener callbackListener;
    public ShareUtil.ClickCallbackListener clickCallbackListener;

    public ShareCallbackListenerBinder(ShareUtil.CallbackListener callbackListener, ShareUtil.ClickCallbackListener clickCallbackListener) {
        this.callbackListener = callbackListener;
        this.clickCallbackListener = clickCallbackListener;
    }

    public ShareUtil.CallbackListener getCallbackListener() {
        return this.callbackListener;
    }

    public ShareUtil.ClickCallbackListener getClickCallbackListener() {
        return this.clickCallbackListener;
    }
}
