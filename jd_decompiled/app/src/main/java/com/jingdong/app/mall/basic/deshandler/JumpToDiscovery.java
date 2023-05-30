package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkDiscoveryHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.unification.navigationbar.NavigationBase;

@Des(des = JumpUtil.VAULE_DES_DISCOVERY)
/* loaded from: classes19.dex */
public class JumpToDiscovery extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (NavigationBase.getInstance().isNavigationType(2)) {
            DeepLinkDiscoveryHelper.startDiscovery4EventActivity(context, new Bundle());
            c(context);
            return;
        }
        bundle.putInt(RemoteMessageConst.TO, 2);
        String string = bundle.getString("projectId");
        String string2 = bundle.getString("assignmentId");
        String string3 = bundle.getString("type");
        String string4 = bundle.getString("android");
        if (bundle.containsKey("projectId")) {
            bundle.putString("discovery_projectId", string);
            bundle.remove("projectId");
        }
        if (bundle.containsKey("assignmentId")) {
            bundle.putString("discovery_assignmentId", string2);
            bundle.remove("assignmentId");
        }
        if (bundle.containsKey("type")) {
            bundle.putString("discovery_type", string3);
            bundle.remove("type");
        }
        if (bundle.containsKey("android")) {
            bundle.putString("discovery_tab_android", string4);
            bundle.remove("android");
        }
        g(context, bundle);
    }
}
