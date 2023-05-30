package com.jingdong.app.mall.basic.deshandler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_NOTIFICATION_HANDLER)
/* loaded from: classes19.dex */
public class JumpToNotificationHandler extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, "com.jingdong.app.mall.open.MessageNotificationActivity"));
        intent.putExtra("summary", bundle.getString("summary"));
        intent.putExtra("messageFlag", bundle.getInt("from"));
        intent.setFlags(337641472);
        context.startActivity(intent);
        c(context);
    }
}
