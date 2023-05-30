package com.jd.libs.hybrid.xbehavior.events;

import com.jd.libs.hybrid.xbehavior.base.BaseEvent;

/* loaded from: classes16.dex */
public class RequestEvent extends BaseEvent {
    public static final String TYPE_GATE_WAY = "gate_way";
    public static final String TYPE_H5_PAGE = "h5_page";
    public String type;
    public String url;

    public RequestEvent(String str, String str2) {
        this.url = str;
        this.type = str2;
    }

    @Override // com.jd.libs.hybrid.xbehavior.base.BaseEvent
    public String getName() {
        return "RequestEvent";
    }

    @Override // com.jd.libs.hybrid.xbehavior.base.BaseEvent
    public String toJson() {
        return null;
    }
}
