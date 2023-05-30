package com.jingdong.sdk.jdshare.cell;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.sdk.jdshare.cell.ChannelAdapter;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class CommonPanelView extends RelativeLayout {

    /* renamed from: g */
    private Context f14981g;

    /* renamed from: h */
    private com.jingdong.c.a.c.f f14982h;

    /* renamed from: i */
    private LinearLayout f14983i;

    /* renamed from: j */
    private i f14984j;

    /* renamed from: k */
    private boolean f14985k;

    /* renamed from: l */
    private String f14986l;

    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.c.a.c.f f14987g;

        a(com.jingdong.c.a.c.f fVar) {
            CommonPanelView.this = r1;
            this.f14987g = fVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            CommonPanelView.this.l();
            if (!TextUtils.isEmpty(this.f14987g.f12279g) || !TextUtils.isEmpty(this.f14987g.f12280h)) {
                CommonPanelView.this.k(this.f14987g);
            } else {
                CommonPanelView.this.g(this.f14987g);
            }
            com.jingdong.sdk.jdshare.utils.g.n("Share_JDPictorial_PicExpo", "", "", this.f14987g.b.getShareSource());
        }
    }

    /* loaded from: classes7.dex */
    public class b implements JDImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.c.a.c.f f14989g;

        b(CommonPanelView commonPanelView, com.jingdong.c.a.c.f fVar) {
            this.f14989g = fVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            com.jingdong.c.a.c.f fVar = this.f14989g;
            if (fVar != null) {
                fVar.t = bitmap;
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    }

    /* loaded from: classes7.dex */
    public class c implements JDImageLoadingListener {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.c.a.c.f f14990g;

        c(CommonPanelView commonPanelView, com.jingdong.c.a.c.f fVar) {
            this.f14990g = fVar;
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            com.jingdong.c.a.c.f fVar = this.f14990g;
            if (fVar != null) {
                fVar.t = bitmap;
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    }

    /* loaded from: classes7.dex */
    public class d implements View.OnClickListener {
        d(CommonPanelView commonPanelView) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* loaded from: classes7.dex */
    public class e implements ChannelAdapter.c {
        e() {
            CommonPanelView.this = r1;
        }

        @Override // com.jingdong.sdk.jdshare.cell.ChannelAdapter.c
        public void a(com.jingdong.c.a.c.b bVar) {
            if (CommonPanelView.this.f14984j != null) {
                CommonPanelView.this.f14984j.a(bVar);
            }
        }
    }

    /* loaded from: classes7.dex */
    public class f implements ChannelAdapter.c {
        f() {
            CommonPanelView.this = r1;
        }

        @Override // com.jingdong.sdk.jdshare.cell.ChannelAdapter.c
        public void a(com.jingdong.c.a.c.b bVar) {
            if (CommonPanelView.this.f14984j != null) {
                CommonPanelView.this.f14984j.a(bVar);
            }
        }
    }

    /* loaded from: classes7.dex */
    public class g implements View.OnClickListener {
        g() {
            CommonPanelView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CommonPanelView.this.f14984j != null) {
                CommonPanelView.this.f14984j.b();
            }
        }
    }

    /* loaded from: classes7.dex */
    public class h implements ChannelAdapter.c {
        h() {
            CommonPanelView.this = r1;
        }

        @Override // com.jingdong.sdk.jdshare.cell.ChannelAdapter.c
        public void a(com.jingdong.c.a.c.b bVar) {
            if (CommonPanelView.this.f14984j != null) {
                CommonPanelView.this.f14984j.a(bVar);
            }
        }
    }

    /* loaded from: classes7.dex */
    public interface i {
        void a(com.jingdong.c.a.c.b bVar);

        void b();
    }

    public CommonPanelView(Context context) {
        super(context);
        this.f14986l = "";
        this.f14981g = context;
    }

    private void e(com.jingdong.c.a.c.f fVar) {
        LinearLayout.LayoutParams layoutParams;
        ShareInfo shareInfo;
        ShareInfo shareInfo2;
        LinearLayout linearLayout = new LinearLayout(this.f14981g);
        this.f14983i = linearLayout;
        linearLayout.setId(R.id.bottomPanel);
        boolean z = true;
        this.f14983i.setOrientation(1);
        this.f14983i.setOnClickListener(new d(this));
        p();
        i(fVar);
        h(fVar);
        com.jingdong.c.a.c.c cVar = fVar.f12276c;
        List<com.jingdong.c.a.c.b> list = cVar.a;
        List<com.jingdong.c.a.c.b> list2 = cVar.b;
        List<com.jingdong.c.a.c.b> list3 = cVar.f12271c;
        boolean z2 = list2 != null && list2.size() >= 2 && list3 != null && list3.size() >= 2 && list.size() > 5;
        if (z2) {
            list = list2;
        }
        RecyclerView recyclerView = new RecyclerView(this.f14981g);
        if ((list.size() <= 4 && !fVar.b()) || ShareValues.isLandscapeMode()) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 1;
        } else {
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            recyclerView.setClipToPadding(false);
            recyclerView.setPadding(m(43), 0, m(43), 0);
        }
        layoutParams.topMargin = m(28);
        this.f14983i.addView(recyclerView, layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f14981g, 0, false));
        com.jingdong.c.a.c.f fVar2 = this.f14982h;
        if (fVar2 != null && (shareInfo2 = fVar2.b) != null) {
            this.f14986l = shareInfo2.getUrl();
        }
        ChannelAdapter channelAdapter = new ChannelAdapter(this.f14981g, list, this.f14986l);
        if (!fVar.f12285m && !fVar.f12283k) {
            z = false;
        }
        channelAdapter.p(z);
        channelAdapter.o(new e());
        recyclerView.setAdapter(channelAdapter);
        recyclerView.addItemDecoration(new ChannelItemSpaceDecoration(m(14)));
        if (z2) {
            RecyclerView recyclerView2 = new RecyclerView(this.f14981g);
            recyclerView2.setClipToPadding(false);
            recyclerView2.setPadding(m(43), 0, m(43), 0);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.topMargin = m(28);
            this.f14983i.addView(recyclerView2, layoutParams2);
            recyclerView2.setLayoutManager(new LinearLayoutManager(this.f14981g, 0, false));
            com.jingdong.c.a.c.f fVar3 = this.f14982h;
            if (fVar3 != null && (shareInfo = fVar3.b) != null) {
                this.f14986l = shareInfo.getUrl();
            }
            ChannelAdapter channelAdapter2 = new ChannelAdapter(this.f14981g, list3, this.f14986l);
            channelAdapter2.o(new f());
            recyclerView2.setAdapter(channelAdapter2);
            recyclerView2.addItemDecoration(new ChannelItemSpaceDecoration(m(14)));
        }
        if (!ShareValues.isLandscapeMode()) {
            TextView textView = new TextView(this.f14981g);
            textView.setTextSize(0, m(32));
            textView.setGravity(17);
            textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            textView.setText(R.string.share_btn_cancel);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, m(102));
            layoutParams3.topMargin = m(35);
            this.f14983i.addView(textView, layoutParams3);
            textView.setOnClickListener(new g());
        }
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(12);
        addView(this.f14983i, layoutParams4);
    }

    public void g(com.jingdong.c.a.c.f fVar) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.f14981g);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m(R2.attr.blendSrc), m(690));
        layoutParams.addRule(14);
        if (this.f14985k) {
            layoutParams.topMargin = m(60);
            layoutParams.addRule(10);
        } else {
            layoutParams.bottomMargin = m(100);
            layoutParams.addRule(2, this.f14983i.getId());
        }
        addView(simpleDraweeView, 0, layoutParams);
        com.jingdong.sdk.jdshare.utils.h.a(simpleDraweeView, m(23));
        JDImageUtils.displayImage(fVar.f12278f, simpleDraweeView, (JDDisplayImageOptions) null, new b(this, fVar));
    }

    private void h(com.jingdong.c.a.c.f fVar) {
        ShareInfo shareInfo;
        if (fVar.b()) {
            List<com.jingdong.c.a.c.b> list = fVar.f12276c.d;
            RecyclerView recyclerView = new RecyclerView(this.f14981g);
            recyclerView.setClipToPadding(false);
            recyclerView.setPadding(m(50), 0, m(50), 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = m(36);
            this.f14983i.addView(recyclerView, layoutParams);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.f14981g, 0, false));
            com.jingdong.c.a.c.f fVar2 = this.f14982h;
            if (fVar2 != null && (shareInfo = fVar2.b) != null) {
                this.f14986l = shareInfo.getUrl();
            }
            ChannelAdapter channelAdapter = new ChannelAdapter(this.f14981g, list, this.f14986l);
            channelAdapter.n(true);
            channelAdapter.o(new h());
            recyclerView.setAdapter(channelAdapter);
            recyclerView.addItemDecoration(new ChannelItemSpaceDecoration(m(28)));
            View view = new View(this.f14981g);
            view.setBackgroundColor(Color.parseColor("#F2F2F2"));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, m(1));
            layoutParams2.topMargin = m(36);
            this.f14983i.addView(view, layoutParams2);
        }
    }

    private void i(com.jingdong.c.a.c.f fVar) {
        if (fVar.d) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.f14981g);
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f14983i.addView(simpleDraweeView, new LinearLayout.LayoutParams(-1, m(90)));
            JDImageUtils.displayImage(fVar.f12277e, simpleDraweeView);
        } else if (ShareValues.isLandscapeMode()) {
        } else {
            TextView textView = new TextView(this.f14981g);
            textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            textView.setTextSize(0, m(28));
            textView.setGravity(17);
            textView.setText(fVar.b() ? R.string.share_friends_title : R.string.share_title);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = m(36);
            this.f14983i.addView(textView, layoutParams);
        }
    }

    private void j(com.jingdong.c.a.c.f fVar) {
        LinearLayout linearLayout;
        if (!fVar.u || TextUtils.isEmpty(fVar.f12278f) || (linearLayout = this.f14983i) == null) {
            return;
        }
        linearLayout.post(new a(fVar));
    }

    public void k(com.jingdong.c.a.c.f fVar) {
        RelativeLayout relativeLayout = new RelativeLayout(this.f14981g);
        relativeLayout.setBackgroundColor(-1);
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.f14981g);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m(R2.anim.mtrl_bottom_sheet_slide_in), m(95));
        layoutParams.topMargin = m(36);
        layoutParams.rightMargin = m(48);
        layoutParams.addRule(11);
        relativeLayout.addView(simpleDraweeView, layoutParams);
        simpleDraweeView.setImageResource(R.drawable.pictorial_smile);
        if (!TextUtils.isEmpty(fVar.f12279g)) {
            TextView textView = new TextView(this.f14981g);
            textView.setGravity(16);
            textView.setTextSize(0, m(32));
            textView.getPaint().setFakeBoldText(true);
            textView.setTextColor(Color.parseColor("#F2270C"));
            textView.setMaxLines(1);
            textView.setText(com.jingdong.sdk.jdshare.utils.h.b(10, fVar.f12279g));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, m(R2.anim.lib_cashier_sdk_pop_out_animation_bottom));
            layoutParams2.leftMargin = m(42);
            relativeLayout.addView(textView, layoutParams2);
        }
        if (!TextUtils.isEmpty(fVar.f12280h)) {
            TextView textView2 = new TextView(this.f14981g);
            textView2.setGravity(16);
            textView2.setTextSize(0, m(24));
            textView2.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            textView2.setMaxLines(1);
            textView2.setText(com.jingdong.sdk.jdshare.utils.h.b(16, fVar.f12280h));
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, m(216));
            layoutParams3.leftMargin = m(42);
            relativeLayout.addView(textView2, layoutParams3);
        }
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(this.f14981g);
        simpleDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, m(R2.attr.cameraTargetLng));
        layoutParams4.addRule(12);
        relativeLayout.addView(simpleDraweeView2, layoutParams4);
        com.jingdong.sdk.jdshare.utils.h.a(simpleDraweeView2, m(23));
        JDImageUtils.displayImage(fVar.f12278f, simpleDraweeView2, (JDDisplayImageOptions) null, new c(this, fVar));
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(m(R2.attr.blendSrc), m(690));
        layoutParams5.addRule(14);
        if (this.f14985k) {
            layoutParams5.topMargin = m(60);
            layoutParams5.addRule(10);
        } else {
            layoutParams5.bottomMargin = m(100);
            layoutParams5.addRule(2, this.f14983i.getId());
        }
        addView(relativeLayout, 0, layoutParams5);
        com.jingdong.sdk.jdshare.utils.h.a(relativeLayout, m(23));
    }

    public void l() {
        LinearLayout linearLayout = this.f14983i;
        if (linearLayout == null) {
            return;
        }
        double appHeight = DPIUtil.getAppHeight((Activity) this.f14981g);
        Double.isNaN(appHeight);
        this.f14985k = ((double) ((linearLayout.getHeight() + m(790)) + UnStatusBarTintUtil.getNavigationBarHeight((Activity) this.f14981g))) > appHeight * 0.95d;
    }

    private int m(int i2) {
        return com.jingdong.sdk.jdshare.utils.g.d(this.f14981g, i2);
    }

    private void p() {
        if (ShareValues.isLandscapeMode()) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setShape(0);
        float d2 = com.jingdong.sdk.jdshare.utils.g.d(this.f14981g, 24);
        gradientDrawable.setCornerRadii(new float[]{d2, d2, d2, d2, 0.0f, 0.0f, 0.0f, 0.0f});
        this.f14983i.setBackgroundDrawable(gradientDrawable);
    }

    public void f(com.jingdong.c.a.c.f fVar) {
        this.f14982h = fVar;
        e(fVar);
        j(fVar);
    }

    public void n(@NonNull RelativeLayout relativeLayout) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200L);
        relativeLayout.addView(this, new RelativeLayout.LayoutParams(-1, -1));
        startAnimation(translateAnimation);
        com.jingdong.sdk.jdshare.utils.g.k("Share_SharePanelPop", "1_0", this.f14982h);
    }

    public void o(i iVar) {
        this.f14984j = iVar;
    }
}
