package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.home.video.SimpleVideoActivity;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_SH_ACTIVITY_VIDEO)
/* loaded from: classes19.dex */
public class JumpToSimpleVideoActivity extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SimpleVideoActivity.class);
        intent.putExtras(bundle);
        intent.putExtra(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
        context.startActivity(intent);
        c(context);
    }
}
