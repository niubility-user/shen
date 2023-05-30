package com.jd.dynamic.lib.j;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.PreParseConfig;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class e {
    private PreParseConfig a;

    /* loaded from: classes13.dex */
    public static class a {
        public final f a;
        public long b;

        /* renamed from: c  reason: collision with root package name */
        public PreParseConfig f2257c;
        public boolean d;

        /* renamed from: e  reason: collision with root package name */
        public String f2258e;

        public a(f fVar) {
            this.a = fVar;
        }

        public String a() {
            if (TextUtils.isEmpty(this.f2258e)) {
                this.f2258e = this.f2257c.mConfig.getModule() + CartConstant.KEY_YB_INFO_LINK + this.f2257c.mConfig.getTemplateId();
            }
            return this.f2258e;
        }
    }

    public e(PreParseConfig preParseConfig) {
        this.a = preParseConfig;
    }

    public static boolean b(a aVar) {
        DynamicTemplateEngine h2;
        PreParseConfig preParseConfig = aVar.f2257c;
        int i2 = preParseConfig.preCreateCount;
        if (i2 <= 0) {
            return false;
        }
        ArrayList<DynamicTemplateEngine> arrayList = DynamicSdk.getEngine().templateEngineCache.get(aVar.a());
        if (arrayList == null || arrayList.size() >= i2 || (h2 = aVar.a.h()) == null) {
            return false;
        }
        try {
            h2.bindView(preParseConfig.mData);
        } catch (Exception unused) {
        }
        arrayList.add(h2);
        return true;
    }

    @NonNull
    public a a() {
        PreParseConfig preParseConfig = this.a;
        DYContainerConfig dYContainerConfig = preParseConfig.mConfig;
        JSONObject jSONObject = preParseConfig.mData;
        f fVar = new f(dYContainerConfig);
        fVar.b(dYContainerConfig.getContext());
        fVar.d(jSONObject);
        a aVar = new a(fVar);
        aVar.f2257c = this.a;
        return aVar;
    }
}
