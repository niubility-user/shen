package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.pavilion.PavilionListActivity;

@Des(des = "newthemestreet")
/* loaded from: classes19.dex */
public class JumpToNew_themestreet extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PavilionListActivity.class);
        if (bundle.getInt("jumpFrom") == 1) {
            intent.putExtra("isSHHome", 1);
        }
        context.startActivity(intent);
        c(context);
    }
}
