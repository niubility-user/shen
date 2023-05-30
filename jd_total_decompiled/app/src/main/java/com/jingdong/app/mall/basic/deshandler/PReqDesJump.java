package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jump.JumpNetDataProvider;
import java.util.Map;

/* loaded from: classes19.dex */
public abstract class PReqDesJump extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (!TextUtils.isEmpty(t(context, bundle)) && JumpNetDataProvider.getInstance().request(t(context, bundle), u(context, bundle), new boolean[0])) {
            bundle.putBoolean("hasHttpRequestByJump", true);
        }
        s(context, bundle);
    }

    abstract void s(Context context, Bundle bundle);

    abstract String t(Context context, Bundle bundle);

    abstract Map<String, Object> u(Context context, Bundle bundle);
}
