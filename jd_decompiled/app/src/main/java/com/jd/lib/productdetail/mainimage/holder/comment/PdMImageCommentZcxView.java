package com.jd.lib.productdetail.mainimage.holder.comment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentItemInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class PdMImageCommentZcxView extends RelativeLayout {
    public static final /* synthetic */ int y = 0;

    /* renamed from: g */
    public LinearLayout f4746g;

    /* renamed from: h */
    public LinearLayout f4747h;

    /* renamed from: i */
    public PdCommentInfo f4748i;

    /* renamed from: j */
    public TextView f4749j;

    /* renamed from: k */
    public View f4750k;

    /* renamed from: l */
    public PdMImageCommentItemNewView f4751l;

    /* renamed from: m */
    public PdMImageCommentItemNewView f4752m;

    /* renamed from: n */
    public ConstraintLayout f4753n;
    public View o;
    public View p;
    public View q;
    public boolean r;
    public PdMImageCommentUGCNewView s;
    public PdMImageZcxView t;
    public LinearLayout u;
    public PdMImageCommentLabelNewView v;
    public PdMainImagePresenter w;
    public Context x;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
            PdMImageCommentZcxView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PdMImageCommentZcxView.this.r && PDUtils.repeatClick()) {
                PdMImageCommentZcxView.this.e();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
            PdMImageCommentZcxView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMImageCommentZcxView pdMImageCommentZcxView = PdMImageCommentZcxView.this;
            int i2 = PdMImageCommentZcxView.y;
            pdMImageCommentZcxView.g();
        }
    }

    public PdMImageCommentZcxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = false;
        this.x = context;
    }

    public /* synthetic */ void b(View view) {
        if (this.r && PDUtils.repeatClick()) {
            e();
        }
    }

    public /* synthetic */ void f(View view) {
        g();
    }

    public /* synthetic */ void h(View view) {
        if (this.r && PDUtils.repeatClick()) {
            e();
        }
    }

    public /* synthetic */ void j(View view) {
        if (this.r && PDUtils.repeatClick()) {
            e();
        }
    }

    public /* synthetic */ void k(View view) {
        if (this.r && PDUtils.repeatClick()) {
            e();
        }
    }

    public /* synthetic */ void l(View view) {
        g();
    }

    public final void a() {
        this.o.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.e
            {
                PdMImageCommentZcxView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentZcxView.this.b(view);
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.d
            {
                PdMImageCommentZcxView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentZcxView.this.f(view);
            }
        });
        this.f4751l.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.f
            {
                PdMImageCommentZcxView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentZcxView.this.h(view);
            }
        });
        this.f4752m.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.i
            {
                PdMImageCommentZcxView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentZcxView.this.j(view);
            }
        });
        this.f4753n.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.h
            {
                PdMImageCommentZcxView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentZcxView.this.k(view);
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.g
            {
                PdMImageCommentZcxView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentZcxView.this.l(view);
            }
        });
        this.f4746g.setOnClickListener(new a());
        this.f4747h.setOnClickListener(new b());
    }

    public final void c(boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f4753n.getLayoutParams();
        if (z) {
            if (this.r) {
                this.q.setVisibility(0);
            } else {
                this.q.setVisibility(4);
            }
            layoutParams.addRule(2, R.id.pd_bottom_layout_new);
            this.p.setVisibility(8);
        } else {
            if (this.r) {
                this.p.setVisibility(0);
            } else {
                this.p.setVisibility(4);
            }
            layoutParams.addRule(2, R.id.pd_bottom_layout);
            this.q.setVisibility(8);
        }
        t(this.r);
        this.f4753n.setLayoutParams(layoutParams);
    }

    public final boolean d(PdCommentItemInfo pdCommentItemInfo) {
        return (pdCommentItemInfo == null || TextUtils.isEmpty(pdCommentItemInfo.commentScore) || PDUtils.stringToFloat(pdCommentItemInfo.commentScore) <= 3.0f || TextUtils.isEmpty(pdCommentItemInfo.commentData)) ? false : true;
    }

    public final void e() {
        i();
        Bundle bundle = new Bundle();
        bundle.putString("key", "1");
        bundle.putBoolean("key1", true);
        this.w.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.OPEN_COMMENTPAGE, bundle));
    }

    public final void g() {
        if (this.f4748i != null && this.r && PDUtils.repeatClick()) {
            i();
            OpenAppUtils.openAppForInner(this.x, this.f4748i.buyersShowInfo);
            PdMainImagePresenter pdMainImagePresenter = this.w;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
            }
        }
    }

    public final void i() {
        if (this.w == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ZHONGCAOXIU);
        jDJSONObject.put("frame_info", (Object) "-100");
        jDJSONObject.put("QuesNum", (Object) "");
        jDJSONObject.put("LableNum", (Object) "");
        jDJSONObject.put("isPhoto", (Object) "0");
        jDJSONObject.put("rankid", (Object) "");
        jDJSONObject.put("touchstone_expids", (Object) "");
        jDJSONObject.put("click_pos", (Object) "-100");
        this.w.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        findViewById(R.id.lib_pd_holder_topimage_item_comment_content_title_new);
        this.f4753n = (ConstraintLayout) findViewById(R.id.pd_content_new);
        this.f4749j = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_count_new);
        this.f4750k = findViewById(R.id.lib_pd_holder_topimage_item_comment_subtitle_layout_new);
        this.f4751l = (PdMImageCommentItemNewView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_1_new);
        this.f4752m = (PdMImageCommentItemNewView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_2_new);
        this.o = findViewById(R.id.lib_pd_holder_topimage_item_comment_content_btn_ok_new);
        this.p = findViewById(R.id.pd_bottom_layout);
        this.q = findViewById(R.id.pd_bottom_layout_new);
        this.s = (PdMImageCommentUGCNewView) findViewById(R.id.lib_pd_holder_topimage_item_comment_ugc_layout_new);
        this.v = (PdMImageCommentLabelNewView) findViewById(R.id.lib_pd_holder_topimage_item_comment_label_layout_new);
        this.t = (PdMImageZcxView) findViewById(R.id.pd_zcx_root_layout_new);
        this.u = (LinearLayout) findViewById(R.id.pd_zcx_btn_ok_new);
        this.f4746g = (LinearLayout) findViewById(R.id.lib_pd_holder_topimage_item_comment_look);
        this.f4747h = (LinearLayout) findViewById(R.id.pd_zcx_btn_ok_look);
        a();
    }

    public void s(PdMainImagePresenter pdMainImagePresenter) {
        this.w = pdMainImagePresenter;
        PdMImageZcxView pdMImageZcxView = this.t;
        if (pdMImageZcxView != null) {
            pdMImageZcxView.c(pdMainImagePresenter);
        }
    }

    public void t(boolean z) {
        this.u.setClickable(z);
        this.o.setClickable(z);
        this.f4751l.setClickable(z);
        this.f4752m.setClickable(z);
        this.f4747h.setClickable(z);
        this.f4746g.setClickable(z);
        this.t.b(z);
        this.t.setClickable(z);
        this.f4753n.setClickable(z);
    }
}
