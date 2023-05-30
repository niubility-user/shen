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
public class PdMImageCommentUGCNewView extends ConstraintLayout {

    /* renamed from: l  reason: collision with root package name */
    public static final /* synthetic */ int f4722l = 0;

    /* renamed from: g  reason: collision with root package name */
    public TextView f4723g;

    /* renamed from: h  reason: collision with root package name */
    public ArrayList<PdCommentUGCInfo> f4724h;

    /* renamed from: i  reason: collision with root package name */
    public int f4725i;

    /* renamed from: j  reason: collision with root package name */
    public Handler f4726j;

    /* renamed from: k  reason: collision with root package name */
    public Runnable f4727k;

    /* loaded from: classes15.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ViewTreeLifecycleOwner.get(PdMImageCommentUGCNewView.this) == null || ViewTreeLifecycleOwner.get(PdMImageCommentUGCNewView.this).getLifecycle() == null || ViewTreeLifecycleOwner.get(PdMImageCommentUGCNewView.this).getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                return;
            }
            if (ViewTreeLifecycleOwner.get(PdMImageCommentUGCNewView.this).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                PdMImageCommentUGCNewView pdMImageCommentUGCNewView = PdMImageCommentUGCNewView.this;
                int i2 = PdMImageCommentUGCNewView.f4722l;
                pdMImageCommentUGCNewView.getClass();
                String str = "startAnim state = " + ViewTreeLifecycleOwner.get(pdMImageCommentUGCNewView).getLifecycle().getCurrentState();
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 800);
                ofFloat.setDuration(800);
                ofFloat.addUpdateListener(new j(pdMImageCommentUGCNewView, 800));
                ofFloat.start();
            }
            PdMImageCommentUGCNewView pdMImageCommentUGCNewView2 = PdMImageCommentUGCNewView.this;
            pdMImageCommentUGCNewView2.f4726j.removeCallbacks(pdMImageCommentUGCNewView2.f4727k);
            PdMImageCommentUGCNewView pdMImageCommentUGCNewView3 = PdMImageCommentUGCNewView.this;
            pdMImageCommentUGCNewView3.f4726j.postDelayed(pdMImageCommentUGCNewView3.f4727k, 1800L);
        }
    }

    public PdMImageCommentUGCNewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4725i = 0;
        this.f4726j = new Handler();
        this.f4727k = new a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(@NonNull PdCommentUGCInfo pdCommentUGCInfo) {
        if (pdCommentUGCInfo != null) {
            this.f4723g.setText(pdCommentUGCInfo.text);
        } else {
            this.f4723g.setText("");
        }
    }

    public void b(ArrayList<PdCommentUGCInfo> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.f4724h = arrayList;
        ArrayList<PdCommentUGCInfo> arrayList2 = this.f4724h;
        int i2 = this.f4725i + 1;
        this.f4725i = i2;
        c(arrayList2.get(i2 % arrayList2.size()));
        this.f4726j.removeCallbacks(this.f4727k);
        this.f4726j.postDelayed(this.f4727k, 1800L);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4723g = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_comment_ugc_text_new);
    }
}
