package com.jd.lib.productdetail.mainimage.holder.comment;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentUGCInfo;
import com.jd.lib.productdetail.mainimage.R;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdMImageCommentUGCView extends ConstraintLayout {

    /* renamed from: l  reason: collision with root package name */
    public static final /* synthetic */ int f4729l = 0;

    /* renamed from: g  reason: collision with root package name */
    public TextView f4730g;

    /* renamed from: h  reason: collision with root package name */
    public ArrayList<PdCommentUGCInfo> f4731h;

    /* renamed from: i  reason: collision with root package name */
    public int f4732i;

    /* renamed from: j  reason: collision with root package name */
    public Handler f4733j;

    /* renamed from: k  reason: collision with root package name */
    public Runnable f4734k;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ViewTreeLifecycleOwner.get(PdMImageCommentUGCView.this) == null || ViewTreeLifecycleOwner.get(PdMImageCommentUGCView.this).getLifecycle() == null || ViewTreeLifecycleOwner.get(PdMImageCommentUGCView.this).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                return;
            }
            if (ViewTreeLifecycleOwner.get(PdMImageCommentUGCView.this).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                PdMImageCommentUGCView pdMImageCommentUGCView = PdMImageCommentUGCView.this;
                int i2 = PdMImageCommentUGCView.f4729l;
                pdMImageCommentUGCView.getClass();
                String str = "startAnim state = " + ViewTreeLifecycleOwner.get(pdMImageCommentUGCView).getLifecycle().getCurrentState();
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 800);
                ofFloat.setDuration(800);
                ofFloat.addUpdateListener(new k(pdMImageCommentUGCView, 800));
                ofFloat.start();
            }
            PdMImageCommentUGCView pdMImageCommentUGCView2 = PdMImageCommentUGCView.this;
            pdMImageCommentUGCView2.f4733j.removeCallbacks(pdMImageCommentUGCView2.f4734k);
            PdMImageCommentUGCView pdMImageCommentUGCView3 = PdMImageCommentUGCView.this;
            pdMImageCommentUGCView3.f4733j.postDelayed(pdMImageCommentUGCView3.f4734k, 1800L);
        }
    }

    public PdMImageCommentUGCView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4732i = 0;
        this.f4733j = new Handler();
        this.f4734k = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(@NonNull PdCommentUGCInfo pdCommentUGCInfo) {
        if (pdCommentUGCInfo != null) {
            this.f4730g.setText(pdCommentUGCInfo.text);
        } else {
            this.f4730g.setText("");
        }
    }

    public void b(ArrayList<PdCommentUGCInfo> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.f4731h = arrayList;
        ArrayList<PdCommentUGCInfo> arrayList2 = this.f4731h;
        int i2 = this.f4732i + 1;
        this.f4732i = i2;
        c(arrayList2.get(i2 % arrayList2.size()));
        this.f4733j.removeCallbacks(this.f4734k);
        this.f4733j.postDelayed(this.f4734k, 1800L);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4730g = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_ugc_text);
    }
}
