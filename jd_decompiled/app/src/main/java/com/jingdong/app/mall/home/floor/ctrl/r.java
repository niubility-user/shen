package com.jingdong.app.mall.home.floor.ctrl;

import android.os.Build;
import android.view.View;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class r {
    private HomeRecycleView a;

    /* renamed from: c  reason: collision with root package name */
    private int f9505c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f9506e;

    /* renamed from: g  reason: collision with root package name */
    private b f9508g;
    private SimpleDraweeView b = null;

    /* renamed from: f  reason: collision with root package name */
    private AtomicBoolean f9507f = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    public AtomicBoolean f9509h = new AtomicBoolean(false);

    /* renamed from: i  reason: collision with root package name */
    public AtomicBoolean f9510i = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (r.this.f9508g != null) {
                r.this.f9508g.a();
            }
            JDMtaUtils.onClickWithPageId(view.getContext(), "Home_ReturnTop", com.jingdong.app.mall.home.r.c.a.f10642k, String.valueOf(r.this.d), RecommendMtaUtils.Home_PageId);
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void a();
    }

    private void e() {
        SimpleDraweeView simpleDraweeView = this.b;
        if (simpleDraweeView == null) {
            return;
        }
        simpleDraweeView.setOnClickListener(new a());
    }

    public void c(HomeRecycleView homeRecycleView, SimpleDraweeView simpleDraweeView, JDJSONObject jDJSONObject, boolean z, b bVar) {
        this.f9507f.set(false);
        this.a = homeRecycleView;
        this.b = simpleDraweeView;
        simpleDraweeView.setImageResource(R.drawable.home_direct_to_top);
        this.f9506e = z;
        int jSONIntWithDefault = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "toTopBtnLine", 5);
        this.f9505c = jSONIntWithDefault;
        this.f9505c = Math.max(jSONIntWithDefault, 0);
        this.f9508g = bVar;
        e();
    }

    public boolean d(int i2) {
        HomeRecycleView homeRecycleView;
        SimpleDraweeView simpleDraweeView = this.b;
        if (simpleDraweeView == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            simpleDraweeView.setVisibility(8);
            return false;
        } else if (!this.f9506e) {
            simpleDraweeView.setVisibility(8);
            return false;
        } else if (i2 >= (com.jingdong.app.mall.home.floor.common.d.f9278f >> 1) && (homeRecycleView = this.a) != null) {
            int g2 = ((com.jingdong.app.mall.home.a.f8562k - homeRecycleView.g()) / com.jingdong.app.mall.home.floor.common.d.d(510)) + this.a.h();
            this.d = g2;
            if (g2 >= this.f9505c) {
                this.b.setVisibility(0);
                return true;
            }
            this.b.setVisibility(8);
            return false;
        } else {
            this.b.setVisibility(8);
            return false;
        }
    }
}
