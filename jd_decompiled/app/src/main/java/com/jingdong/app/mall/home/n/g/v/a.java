package com.jingdong.app.mall.home.n.g.v;

import com.jingdong.cleanmvp.common.BaseEvent;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class a extends BaseEvent {
    a(String str) {
        super(str);
    }

    public static void a(String str) {
        EventBus.getDefault().post(new a(str));
    }
}
