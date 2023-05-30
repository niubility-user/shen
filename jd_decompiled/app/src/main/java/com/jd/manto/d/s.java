package com.jd.manto.d;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.manto.sdk.api.IMPDownGrade;

/* loaded from: classes17.dex */
public class s implements IMPDownGrade {
    @Override // com.jingdong.manto.sdk.api.IMPDownGrade
    public void onDownGrade(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String string = bundle.getString("url");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des("m");
        builder.category("jump");
        builder.map("url", string);
        builder.map("fromManto", Boolean.TRUE);
        builder.build().jump(context);
    }
}
