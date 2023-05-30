package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkMyStreetHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_GENE_RECOM)
/* loaded from: classes19.dex */
public class JumpToGene_recom extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            DeepLinkMyStreetHelper.startMyStreetGeneRecom((IMyActivity) context, bundle);
        }
        c(context);
    }
}
