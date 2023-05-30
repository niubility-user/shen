package com.jd.cashier.app.jdlibcutter.impl.router;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOuterRouter;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpBuilder;

/* loaded from: classes13.dex */
public class OpenAppRouterImpl implements IOuterRouter {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IRouterAnalyzer
    public boolean isTargetRouter(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return JumpUtil.isJdScheme(Uri.parse(str).getScheme());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.router.IOuterRouter
    public void router(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
    }
}
