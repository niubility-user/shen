package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.os.SystemClock;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.category.view.CaLoadingLayout;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.n.g.j;
import com.jingdong.app.mall.home.n.g.l;
import com.jingdong.app.mall.home.n.g.o;
import com.jingdong.app.mall.home.n.g.u.c;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class b {
    private CaMoreLayout a;
    private HttpRequest b;

    /* renamed from: c  reason: collision with root package name */
    private CaLoadingLayout f8804c;
    private l d;

    /* renamed from: e  reason: collision with root package name */
    private AtomicBoolean f8805e = new AtomicBoolean(false);

    /* loaded from: classes4.dex */
    class a implements CaLoadingLayout.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.category.view.CaLoadingLayout.b
        public void onRetry() {
            b bVar = b.this;
            bVar.j(bVar.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.category.view.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class C0281b implements HttpGroup.OnCommonListener {

        /* renamed from: com.jingdong.app.mall.home.category.view.b$b$a */
        /* loaded from: classes4.dex */
        class a extends com.jingdong.app.mall.home.o.a.b {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ JDJSONObject f8807g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ List f8808h;

            a(JDJSONObject jDJSONObject, List list) {
                this.f8807g = jDJSONObject;
                this.f8808h = list;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                b.this.a.u(com.jingdong.app.mall.home.r.e.b.getJsonString(this.f8807g, "name", ""), this.f8808h);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.home.category.view.b$b$b  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class C0282b extends com.jingdong.app.mall.home.o.a.b {
            C0282b() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                b.this.f8804c.d(true);
            }
        }

        C0281b() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            b.this.f8805e.set(false);
            if (httpResponse == null) {
                onError(new HttpError());
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject != null) {
                List i2 = b.this.i(fastJsonObject);
                if (i2 != null && i2.size() > 0) {
                    f.E0(new a(fastJsonObject, i2));
                    return;
                } else {
                    onError(new HttpError());
                    return;
                }
            }
            onError(new HttpError());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            b.this.f8805e.set(false);
            f.E0(new C0282b());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public b(Context context, CaMoreLayout caMoreLayout, CaLoadingLayout caLoadingLayout) {
        this.a = caMoreLayout;
        this.f8804c = caLoadingLayout;
        caLoadingLayout.e(new a());
    }

    private void f(List<c> list, long j2) {
        list.add(com.jingdong.app.mall.home.n.a.C_DIVIDER.getTypeModel(null, null, j2, -1));
    }

    private void g(List<c> list, JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2, JDJSONArray jDJSONArray, long j2) {
        int size = list.size();
        int spanSize = com.jingdong.app.mall.home.n.b.S_MORE_ICON.getSpanSize();
        int size2 = ((jDJSONArray.size() + spanSize) - 1) / spanSize;
        for (int i2 = 0; i2 < size2; i2++) {
            c typeModel = com.jingdong.app.mall.home.n.a.C_MORE_ICON.getTypeModel(jDJSONObject2, null, j2, size);
            f.n(typeModel);
            o oVar = (o) typeModel;
            int i3 = i2 * spanSize;
            oVar.R(i3, i3 + spanSize, jDJSONObject);
            list.add(oVar);
            size++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<c> i(@NotNull JDJSONObject jDJSONObject) {
        JDJSONArray jsonArr;
        ArrayList arrayList = new ArrayList();
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("floorList");
        if (jSONArray != null && jSONArray.size() > 0) {
            int size = jSONArray.size();
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                for (int i2 = 0; i2 < size; i2++) {
                    JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (jSONObject != null && (jsonArr = com.jingdong.app.mall.home.r.e.b.getJsonArr(jSONObject, "iconList")) != null) {
                        if (i2 != 0) {
                            f(arrayList, elapsedRealtime);
                        }
                        g(arrayList, jDJSONObject, jSONObject, jsonArr, elapsedRealtime);
                    }
                }
                if (arrayList.size() > 0) {
                    arrayList.add(new j(null, com.jingdong.app.mall.home.n.a.C_MORE_FOOT));
                }
            } catch (Exception e2) {
                f.s0("parseMoreList", e2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        HttpRequest httpRequest = this.b;
        if (httpRequest != null) {
            httpRequest.stop();
        }
        this.f8805e.set(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(l lVar) {
        if (this.f8805e.get() && this.d == lVar) {
            return;
        }
        HttpRequest httpRequest = this.b;
        if (httpRequest != null) {
            httpRequest.stop();
        }
        this.d = lVar;
        this.f8804c.setVisibility(0);
        this.f8804c.bringToFront();
        this.f8804c.d(false);
        this.f8805e.set(true);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setListener(new C0281b());
        CategoryEntity.CaItem i2 = CaContentLayout.i();
        if (i2 != null) {
            httpSetting.putJsonParam("tabOrder", Integer.valueOf(i2.getPosition()));
        }
        httpSetting.putJsonParam("filteredCateIds", lVar.T());
        httpSetting.putJsonParam("catePoolId", lVar.R());
        httpSetting.putJsonParam("cid", lVar.S());
        httpSetting.putJsonParam("pcid", lVar.U());
        httpSetting.setFunctionId("moreCategory");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        this.b = HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
