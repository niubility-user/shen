package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCategoryHelper;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.corelib.utils.Log;

@Des(des = "category")
/* loaded from: classes19.dex */
public class JumpToCategory extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(this.a, "JumpToCategory  -->> VALUE_DES_CATEGORY\tnavigationType = " + NavigationBase.getInstance().getNavigationType());
        }
        if (NavigationBase.getInstance().getNavigationType() == 1) {
            DeepLinkCategoryHelper.homeToCategoryActivity(context, bundle);
            c(context);
            return;
        }
        bundle.putInt(RemoteMessageConst.TO, 1);
        g(context, bundle);
    }
}
