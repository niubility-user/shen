package com.jingdong.app.mall.home.widget.recommend;

import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;

/* loaded from: classes4.dex */
public class HomeRecommend extends HomeRecommendContentLayout {
    private static boolean sLoadRecommend = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDJSONObject f11071g;

        a(JDJSONObject jDJSONObject) {
            this.f11071g = jDJSONObject;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            HomeRecommend.this.setFistViewShowType(this.f11071g.optString("recommendAB", "A"));
        }
    }

    public HomeRecommend(RecyclerView recyclerView, BaseActivity baseActivity) {
        super(recyclerView, baseActivity);
        setNeedWaitSplash(i.i());
    }

    public static boolean isLoadRecommend() {
        return sLoadRecommend;
    }

    private void setRecommendUISafe(JDJSONObject jDJSONObject, boolean z, boolean z2) {
        f.E0(new a(jDJSONObject));
        setHomeJsonData(jDJSONObject.optJSONObject("recommend"), z, jDJSONObject.optInt("appType", 0));
        if (this.recommendConfig != null) {
            com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jDJSONObject.optString("recommendParam"));
            sLoadRecommend = !TextUtils.equals(c2.optString("requestType"), "1");
            c2.put("allFloorHeight", com.jingdong.app.mall.home.a.f8564m);
            c2.put("homeContentHeight", com.jingdong.app.mall.home.a.f8562k);
            this.recommendConfig.setNetExtentParam(c2.toString());
            this.recommendConfig.setDisplayStyle(z2 ? "B" : "A");
            this.recommendConfig.setBusinessElderValue(jDJSONObject.optString("appType", "0"));
        }
    }

    public void setRecommendUI(JDJSONObject jDJSONObject, boolean z, boolean z2) {
        try {
            setRecommendUISafe(jDJSONObject, z, z2);
        } catch (Exception e2) {
            e2.printStackTrace();
            f.o(e2.getMessage());
            l.j(e2);
        }
    }
}
