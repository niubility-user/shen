package com.jd.cashier.app.jdlibcutter.impl.router;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.router.IInnerRouter;
import com.jingdong.common.unification.router.JDRouter;

/* loaded from: classes13.dex */
public class InnerRouterImpl implements IInnerRouter {
    private static final String ROUTE_SCHEME = "router";

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IRouterAnalyzer
    public boolean isTargetRouter(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        return TextUtils.equals(parse.getScheme(), "router") || "router".equalsIgnoreCase(parse.getScheme());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IInnerRouter
    public void router(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        JDRouter.build(context, str).open();
    }
}
