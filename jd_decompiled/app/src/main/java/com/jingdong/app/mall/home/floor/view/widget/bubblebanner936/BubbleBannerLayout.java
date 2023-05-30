package com.jingdong.app.mall.home.floor.view.widget.bubblebanner936;

import android.content.Context;
import android.graphics.Rect;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.r.f.a.h;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class BubbleBannerLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f10210g;

    /* renamed from: h  reason: collision with root package name */
    private h f10211h;

    /* renamed from: i  reason: collision with root package name */
    private ArrayList<BubbleBannerItemView> f10212i;

    public BubbleBannerLayout(Context context, h hVar) {
        super(context);
        this.f10210g = context;
        this.f10211h = hVar;
        b();
    }

    private void b() {
        this.f10212i = new ArrayList<>(this.f10211h.Z() * 4);
        int i2 = 0;
        while (i2 < this.f10211h.Z()) {
            int i3 = (i2 * 180) + 86;
            int i4 = 0;
            while (i4 < 4) {
                int i5 = (i4 * R2.anim.pop_left_top_out) + 44;
                boolean z = i2 == 0 && (i4 == 0 || i4 == 3);
                f fVar = new f(R2.anim.pickerview_dialog_scale_in, z ? R2.anim.slide_in_from_top : 170);
                fVar.F(new Rect(i5, z ? i3 - 20 : i3, 0, 0));
                BubbleBannerItemView bubbleBannerItemView = new BubbleBannerItemView(this.f10210g, fVar, z);
                addView(bubbleBannerItemView, fVar.u(bubbleBannerItemView));
                this.f10212i.add(bubbleBannerItemView);
                i4++;
            }
            i2++;
        }
    }

    public void a(int i2) {
        int Y = i2 % this.f10211h.Y();
        boolean z = Y < 0 || Y >= this.f10211h.Y();
        for (int i3 = 0; i3 < this.f10212i.size(); i3++) {
            this.f10212i.get(i3).g(new a(this.f10211h, z ? Integer.MAX_VALUE : (this.f10211h.Z() * Y * 4) + i3));
        }
    }
}
