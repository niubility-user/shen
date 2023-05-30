package com.jingdong.app.mall.home.p.b.d;

import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;

/* loaded from: classes4.dex */
public class e {
    private String a;
    private String b;

    /* renamed from: c */
    private String f10529c;
    private String d;

    /* renamed from: e */
    private JDJSONArray f10530e;

    /* renamed from: f */
    private BaseModel f10531f;

    /* renamed from: g */
    private MutableLiveData<d> f10532g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements f.d {
        a() {
            e.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onEnd(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                e.this.e();
            } else {
                e.this.d(jDJSONObject);
            }
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onError(HttpError httpError) {
            e.this.e();
        }

        @Override // com.jingdong.app.mall.home.o.a.f.d
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ JDJSONObject f10533g;

        b(JDJSONObject jDJSONObject) {
            e.this = r1;
            this.f10533g = jDJSONObject;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            e.this.f10532g.setValue(new d(e.this.f10531f, this.f10533g));
        }
    }

    public e(Pair<View, BaseModel> pair, MutableLiveData<d> mutableLiveData) {
        try {
            this.f10531f = (BaseModel) pair.second;
            JDJSONObject h2 = s.h();
            this.f10530e = h2 == null ? null : h2.optJSONArray("coreFloorSkuList");
            this.f10532g = mutableLiveData;
            this.a = this.f10531f.g().A;
            this.b = this.f10531f.f("id");
            this.d = l.f();
            this.f10529c = this.f10531f.f("channelId");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String c() {
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put("floorId", (Object) this.a);
            jDJSONObject.put("moduleId", (Object) this.b);
            jDJSONObject.put("channelId", (Object) this.f10529c);
            jDJSONObject.put("userCategory", (Object) this.d);
            JDJSONArray jDJSONArray = this.f10530e;
            if (jDJSONArray != null) {
                jDJSONObject.put("skuList", (Object) jDJSONArray);
            }
            jDJSONObject.put("fQueryStamp", (Object) (com.jingdong.app.mall.home.b.f8602m + ""));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jDJSONObject.toString();
    }

    public void d(JDJSONObject jDJSONObject) {
        if (TextUtils.equals("2", jDJSONObject.optString("homeCode"))) {
            c.f10522h.set(true);
        } else {
            f.E0(new b(jDJSONObject));
        }
    }

    public void e() {
    }

    public void f() {
        if (!TextUtils.isEmpty(this.f10529c) && !TextUtils.isEmpty(this.d)) {
            f.C0("coreFloorBSide", c(), new a());
        } else {
            e();
        }
    }
}
