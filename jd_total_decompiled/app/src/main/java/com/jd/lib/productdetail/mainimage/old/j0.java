package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class j0 {
    @NonNull
    public static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            if (!TextUtils.isEmpty(str)) {
                for (Map.Entry<String, Object> entry : JDJSON.parseObject(str).entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue().toString());
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return hashMap;
    }

    public static void b(Context context, Long l2, String str, SourceEntity sourceEntity) {
        Bundle build;
        if (l2 == null || (build = DeeplinkProductDetailHelper.BundleBuilder.from(l2.longValue()).imageTitlePrice(null, null, null).sourceEntity(null).personas().build()) == null) {
            return;
        }
        DeeplinkProductDetailHelper.startProductDetail(context, build);
    }
}
