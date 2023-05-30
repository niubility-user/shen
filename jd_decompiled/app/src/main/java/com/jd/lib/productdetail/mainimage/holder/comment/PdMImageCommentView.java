package com.jd.lib.productdetail.mainimage.holder.comment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentItemInfo;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageCommentView extends ConstraintLayout {

    /* renamed from: g */
    public String f4736g;

    /* renamed from: h */
    public View f4737h;

    /* renamed from: i */
    public TextView f4738i;

    /* renamed from: j */
    public PdMImageCommentItemView f4739j;

    /* renamed from: k */
    public PdMImageCommentItemView f4740k;

    /* renamed from: l */
    public PdMImageCommentItemView f4741l;

    /* renamed from: m */
    public SimpleDraweeView f4742m;

    /* renamed from: n */
    public View f4743n;
    public View o;
    public View p;
    public ConstraintLayout q;
    public View r;
    public boolean s;
    public PdMImageCommentUGCView t;
    public PdMImageCommentLabelView u;
    public PdMainImagePresenter v;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
            PdMImageCommentView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PdMImageCommentView.this.s && PDUtils.repeatClick()) {
                PdMImageCommentView.this.a();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
            PdMImageCommentView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PdMImageCommentView.this.s && PDUtils.repeatClick()) {
                PdMImageCommentView.this.a();
            }
        }
    }

    public PdMImageCommentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = false;
    }

    public /* synthetic */ void b(View view) {
        if (this.s && PDUtils.repeatClick()) {
            a();
        }
    }

    public /* synthetic */ void f(View view) {
        if (this.s && PDUtils.repeatClick()) {
            a();
        }
    }

    public /* synthetic */ void g(View view) {
        if (this.s && PDUtils.repeatClick()) {
            a();
        }
    }

    public final void a() {
        if (this.v != null) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("type", (Object) "comment");
            jDJSONObject.put("frame_info", (Object) "-100");
            jDJSONObject.put("QuesNum", (Object) "");
            jDJSONObject.put("LableNum", (Object) "");
            jDJSONObject.put("isPhoto", (Object) "0");
            jDJSONObject.put("rankid", (Object) "");
            jDJSONObject.put("touchstone_expids", (Object) "");
            jDJSONObject.put("click_pos", (Object) "-100");
            this.v.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
            Bundle bundle = new Bundle();
            bundle.putString("key", "1");
            bundle.putBoolean("key1", true);
            this.v.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.OPEN_COMMENTPAGE, bundle));
        }
    }

    public final void c(boolean z) {
        if (z) {
            this.f4737h.setVisibility(8);
            this.f4738i.setBackgroundResource(R.drawable.lib_pd_mainimage_holder_item_comment_default_bg);
            this.t.setVisibility(8);
            this.u.setVisibility(8);
            this.f4738i.setVisibility(8);
            this.f4740k.setVisibility(8);
            this.f4739j.setVisibility(8);
            this.f4741l.setVisibility(8);
            this.f4742m.setVisibility(0);
            JDImageUtils.displayImage(this.f4736g, (ImageView) this.f4742m, JDDisplayImageOptions.createSimple(), false);
            return;
        }
        this.f4737h.setVisibility(0);
        this.f4740k.setVisibility(0);
        this.f4739j.setVisibility(0);
        this.f4742m.setVisibility(8);
        this.f4738i.setVisibility(0);
        this.f4738i.setBackgroundResource(0);
        this.f4739j.b(false);
        this.f4740k.b(false);
        this.f4741l.b(false);
    }

    public final boolean d(PdCommentInfo pdCommentInfo, int i2) {
        List<PdCommentItemInfo> list;
        if (pdCommentInfo != null && (list = pdCommentInfo.commentInfoList) != null && list.size() >= 2) {
            PdCommentItemInfo pdCommentItemInfo = pdCommentInfo.commentInfoList.get(1);
            if (e(pdCommentInfo.commentInfoList.get(0))) {
                boolean e2 = e(pdCommentItemInfo);
                if (i2 == 2) {
                    if (!e2 || pdCommentInfo.commentInfoList.size() < 3) {
                        return false;
                    }
                    return e(pdCommentInfo.commentInfoList.get(2));
                }
                return e2;
            }
        }
        return false;
    }

    public final boolean e(PdCommentItemInfo pdCommentItemInfo) {
        return (pdCommentItemInfo == null || TextUtils.isEmpty(pdCommentItemInfo.commentScore) || PDUtils.stringToFloat(pdCommentItemInfo.commentScore) <= 3.0f || TextUtils.isEmpty(pdCommentItemInfo.commentData)) ? false : true;
    }

    public void k(PdMainImagePresenter pdMainImagePresenter) {
        this.v = pdMainImagePresenter;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4737h = findViewById(R.id.lib_pd_holder_topimage_item_comment_content_title);
        this.f4738i = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_count);
        this.f4739j = (PdMImageCommentItemView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_1);
        this.f4740k = (PdMImageCommentItemView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_2);
        this.f4741l = (PdMImageCommentItemView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_3);
        this.q = (ConstraintLayout) findViewById(R.id.lib_pd_comment_content_new);
        this.f4742m = (SimpleDraweeView) findViewById(R.id.lib_pd_holder_topimage_item_comment_item_default_2);
        this.r = findViewById(R.id.lib_pd_holder_topimage_item_comment_content_divider);
        this.f4743n = findViewById(R.id.lib_pd_holder_topimage_item_comment_content_btn_ok);
        this.p = findViewById(R.id.lib_pd_holder_topimage_item_comment_bottom_btn_out);
        this.o = findViewById(R.id.lib_pd_holder_topimage_item_comment_bottom_btn);
        this.t = (PdMImageCommentUGCView) findViewById(R.id.lib_pd_holder_topimage_item_comment_ugc_layout);
        this.u = (PdMImageCommentLabelView) findViewById(R.id.lib_pd_holder_topimage_item_comment_label_layout);
        this.f4743n.setOnClickListener(new a());
        this.o.setOnClickListener(new b());
        this.f4739j.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.c
            {
                PdMImageCommentView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentView.this.b(view);
            }
        });
        this.f4740k.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.a
            {
                PdMImageCommentView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentView.this.f(view);
            }
        });
        this.f4741l.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.comment.b
            {
                PdMImageCommentView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PdMImageCommentView.this.g(view);
            }
        });
    }
}
