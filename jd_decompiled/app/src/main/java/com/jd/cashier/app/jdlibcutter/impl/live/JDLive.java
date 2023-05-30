package com.jd.cashier.app.jdlibcutter.impl.live;

import com.jd.cashier.app.jdlibcutter.protocol.live.ILive;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.LiveSmallWindow;

/* loaded from: classes13.dex */
public class JDLive implements ILive {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.live.ILive
    public void collapseLiveWindow() {
        try {
            LiveSmallWindow.hideSmallWindow();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.live.ILive
    public void expandLiveWindow() {
        try {
            LiveSmallWindow.exposeSmallWindow();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
