package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.m;
import com.jingdong.common.cps.JDUnionUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.mta.JDMtaUtils;

@Des(des = JumpUtil.VALUE_DES_CPS_UNION)
/* loaded from: classes19.dex */
public class JumpToCpsUnion extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        JDMtaUtils.sendClickDataWithExt(context, "ExplainDesUnionBeforeTraceSdk", JDUnionUtils.bundleToJson(bundle2), "", "", this.a, "", "", JDUnionUtils.getJsonParamFromBundle(bundle2), null);
        m.g(context, bundle);
        d(context, bundle);
    }
}
