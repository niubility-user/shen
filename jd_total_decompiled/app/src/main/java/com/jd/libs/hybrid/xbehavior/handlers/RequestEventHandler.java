package com.jd.libs.hybrid.xbehavior.handlers;

import android.text.TextUtils;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.xbehavior.base.IEventHandler;
import com.jd.libs.hybrid.xbehavior.events.RequestEvent;
import com.jd.libs.hybrid.xbehavior.lifecycle.LifeCycleDispatcher;

/* loaded from: classes16.dex */
public class RequestEventHandler implements IEventHandler<RequestEvent> {
    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LifeCycleDispatcher.dispatchOnCreate(CommonUtils.getCleanUrl(str), "onUrlLoad");
    }

    @Override // com.jd.libs.hybrid.xbehavior.base.IEventHandler
    public void handleEvent(RequestEvent requestEvent) {
        if (RequestEvent.TYPE_H5_PAGE.equals(requestEvent.type)) {
            a(requestEvent.url);
        }
    }
}
