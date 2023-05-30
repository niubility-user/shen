package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.libs.Des;

@Des(des = "jdnow")
/* loaded from: classes19.dex */
public class JumpToDash_main extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("url", "https://m-now.jd.com/");
        context.startActivity(intent);
        c(context);
    }
}
