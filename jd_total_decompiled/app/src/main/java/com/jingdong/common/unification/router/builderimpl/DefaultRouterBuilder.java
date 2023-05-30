package com.jingdong.common.unification.router.builderimpl;

import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;

/* loaded from: classes6.dex */
public class DefaultRouterBuilder extends RouterBuilder<DefaultRouterBuilder, RouterEntry> {
    public DefaultRouterBuilder(String str, String str2) {
        super(str, str2);
    }

    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    protected RouterEntry initRouterEntry() {
        return new RouterEntry();
    }
}
