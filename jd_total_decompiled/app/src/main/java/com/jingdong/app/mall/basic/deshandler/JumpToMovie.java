package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.s;

@Des(des = "Movie,dianyingpiao")
/* loaded from: classes19.dex */
public class JumpToMovie extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context != null && (context instanceof Activity)) {
            s.e((Activity) context, bundle);
        }
        c(context);
    }
}
