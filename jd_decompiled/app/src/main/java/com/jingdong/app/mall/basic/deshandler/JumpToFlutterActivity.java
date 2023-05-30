package com.jingdong.app.mall.basic.deshandler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import jd.wjlogin_sdk.util.f;

@Des(des = "JDFlutterView")
/* loaded from: classes19.dex */
public class JumpToFlutterActivity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(f.f19954c, "com.jd.lib.jdflutter.JDFlutterMainActivity"));
        intent.putExtras(bundle);
        try {
            context.startActivity(intent);
            c(context);
        } catch (Exception unused) {
        }
    }
}
