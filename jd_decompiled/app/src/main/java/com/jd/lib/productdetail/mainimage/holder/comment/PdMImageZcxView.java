package com.jd.lib.productdetail.mainimage.holder.comment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdMImageZcxView extends RelativeLayout {

    /* renamed from: n */
    public static final /* synthetic */ int f4756n = 0;

    /* renamed from: g */
    public int f4757g;

    /* renamed from: h */
    public PdMainImagePresenter f4758h;

    /* renamed from: i */
    public SimpleDraweeView f4759i;

    /* renamed from: j */
    public int f4760j;

    /* renamed from: k */
    public ArrayList<SimpleDraweeView> f4761k;

    /* renamed from: l */
    public LinearLayout f4762l;

    /* renamed from: m */
    public boolean f4763m;

    public PdMImageZcxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4760j = PDUtils.dip2px(6.0f);
        this.f4761k = new ArrayList<>();
        this.f4763m = false;
    }

    public void b(boolean z) {
        LinearLayout linearLayout = this.f4762l;
        if (linearLayout != null) {
            int childCount = linearLayout.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.f4762l.getChildAt(i2);
                if (childAt != null) {
                    childAt.setClickable(z);
                }
            }
        }
    }

    public void c(PdMainImagePresenter pdMainImagePresenter) {
        this.f4758h = pdMainImagePresenter;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4759i = (SimpleDraweeView) findViewById(R.id.pd_zcx_title_img);
        TextView textView = (TextView) findViewById(R.id.pd_zcx_title_text);
        this.f4762l = (LinearLayout) findViewById(R.id.pd_zcx_img_layout);
    }
}
