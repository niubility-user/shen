package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.config.Configuration;

@Des(des = JumpUtil.VALUE_DES_PRODUCT)
/* loaded from: classes19.dex */
public class JumpToProduct extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        SourceEntity sourceEntity;
        String string = bundle.getString(Configuration.UNION_ID);
        if (bundle.getInt("jumpFrom") != 0) {
            sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_FROM_PHONE_ONLY, bundle.getString("sourceValue"));
        } else if (TextUtils.isEmpty(string)) {
            sourceEntity = new SourceEntity("widget", null);
        } else {
            sourceEntity = new SourceEntity(SourceEntity.SOURCE_TYPE_OPEN_INTERFACE_CPS, string);
        }
        bundle.putSerializable("source", sourceEntity);
        DeeplinkProductDetailHelper.startProductDetailFromOpenApp(context, bundle);
        c(context);
    }
}
