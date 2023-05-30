package com.jingdong.sdk.jdshare.cell;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdshare.cell.b;
import com.jingdong.sdk.jdshare.utils.g;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes7.dex */
public class c extends Dialog {

    /* loaded from: classes7.dex */
    public static class b {
        private ViewOutlineProvider a;
        private ViewGroup b;

        /* renamed from: c  reason: collision with root package name */
        private SimpleDraweeView f15007c;
        private RelativeLayout d;

        /* renamed from: e  reason: collision with root package name */
        private RelativeLayout f15008e;

        /* renamed from: f  reason: collision with root package name */
        private JDCircleImageView f15009f;

        /* renamed from: g  reason: collision with root package name */
        private TextView f15010g;

        /* renamed from: h  reason: collision with root package name */
        private TextView f15011h;

        /* renamed from: i  reason: collision with root package name */
        private TextView f15012i;

        /* renamed from: j  reason: collision with root package name */
        private TextView f15013j;

        /* renamed from: k  reason: collision with root package name */
        private TextView f15014k;

        /* renamed from: l  reason: collision with root package name */
        private SimpleDraweeView f15015l;

        /* renamed from: m  reason: collision with root package name */
        private SimpleDraweeView f15016m;

        /* renamed from: n  reason: collision with root package name */
        private FrameLayout f15017n;
        private JDSharedCommandUtils.JDCommandInfo o;
        private c p;
        private Activity q;
        private boolean r;
        private ShareInfo s;
        private com.jingdong.sdk.jdshare.cell.b t;
        private JDDisplayImageOptions u = new JDDisplayImageOptions().setPlaceholder(R.drawable.share_sdv_user_header).displayer(new JDRoundedBitmapDisplayer(200));
        private JDDisplayImageOptions v = new JDDisplayImageOptions().setPlaceholder(R.drawable.share_sdv_img);

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class a extends ViewOutlineProvider {
            a() {
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, b.this.p(R2.attr.chipIconEnabled), b.this.p(R2.attr.contentPaddingEnd), b.this.p(23));
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.sdk.jdshare.cell.c$b$b  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public class ViewOnLayoutChangeListenerC0722b implements View.OnLayoutChangeListener {
            ViewOnLayoutChangeListenerC0722b() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                if (i8 <= 0 || i4 == i8 || !b.this.p.isShowing()) {
                    return;
                }
                b.this.p.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.sdk.jdshare.cell.c$b$c  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public class ViewOnClickListenerC0723c implements View.OnClickListener {
            ViewOnClickListenerC0723c() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                b bVar = b.this;
                if (bVar.q(bVar.o.jumpUrl)) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(b.this.o.jumpUrl));
                    try {
                        OpenAppJumpController.dispatchJumpRequest(b.this.q, intent);
                    } catch (Exception unused) {
                    }
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", RemoteMessageConst.TO);
                    bundle.putString("url", b.this.o.jumpUrl);
                    bundle.putString("from", "kouling");
                    JumpUtil.execJumpByDes("m", b.this.q, bundle);
                }
                g.l("ShareJingwords_OpenShare", b.this.o.srv, "", g.h(b.this.s, b.this.o.eventParamJson));
                b.this.p.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class d implements View.OnClickListener {
            d() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                g.l("ShareJingwords_CloseShare", b.this.o.srv, "", g.h(b.this.s, b.this.o.eventParamJson));
                b.this.p.dismiss();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public class e implements View.OnClickListener {

            /* loaded from: classes7.dex */
            class a implements b.d {
                a() {
                }

                @Override // com.jingdong.sdk.jdshare.cell.b.d
                public void a() {
                    if (b.this.p != null) {
                        b.this.p.dismiss();
                    }
                }
            }

            e() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String config;
                if (ShareUtil.isUseSwitchQuery()) {
                    config = SwitchQueryFetcher.getSwitchStringValue("isNewErrorFeedback", "");
                } else {
                    config = JDMobileConfig.getInstance().getConfig("JDShare", "isNewErrorFeedback", "switchType");
                }
                if ("1".equals(config)) {
                    g.l("ShareJingwords_Feedback", b.this.o.srv, "", g.h(b.this.s, b.this.o.eventParamJson));
                    b.this.t = new com.jingdong.sdk.jdshare.cell.b(b.this.q);
                    b.this.t.setCancelable(false);
                    b.this.t.setCanceledOnTouchOutside(false);
                    b.this.t.h(b.this.s, b.this.o);
                    b.this.t.show();
                    b.this.t.i(new a());
                    return;
                }
                ToastUtils.showToastInCenter((Context) b.this.q, (byte) 2, b.this.q.getString(R.string.jd_share_command_feedback), 1);
                if (!b.this.r) {
                    ExceptionReporter.reportKeyShareException("negativeFeedback", "", b.this.o.requestText + b.this.o.response, "");
                }
                b.this.r = true;
            }
        }

        public b(Activity activity) {
            this.q = activity;
            this.p = new c(activity);
            l(activity);
        }

        private void l(Context context) {
            this.b = new RelativeLayout(context);
            RelativeLayout relativeLayout = new RelativeLayout(context);
            this.d = relativeLayout;
            relativeLayout.setId(R.id.jd_share_command_content);
            this.d.setBackgroundColor(-1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(p(R2.attr.chipIconEnabled), p(R2.attr.contentPaddingEnd));
            layoutParams.addRule(14);
            this.b.addView(this.d, layoutParams);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            this.f15007c = simpleDraweeView;
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.f15007c.setId(R.id.jd_share_command_bg);
            this.d.addView(this.f15007c, new RelativeLayout.LayoutParams(p(R2.attr.chipIconEnabled), p(320)));
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(R.drawable.jd_share_command_up_smile);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(p(R2.attr.chipIconEnabled), p(22));
            layoutParams2.addRule(8, this.f15007c.getId());
            this.d.addView(imageView, layoutParams2);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setColor(0);
            gradientDrawable.setStroke(p(12), -1);
            this.f15017n = new FrameLayout(context);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(p(92), p(92));
            layoutParams3.topMargin = p(261);
            layoutParams3.leftMargin = p(30);
            this.f15017n.setBackgroundDrawable(gradientDrawable);
            this.d.addView(this.f15017n, layoutParams3);
            JDCircleImageView jDCircleImageView = new JDCircleImageView(context);
            this.f15009f = jDCircleImageView;
            jDCircleImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(p(80), p(80));
            layoutParams4.gravity = 17;
            this.f15017n.addView(this.f15009f, layoutParams4);
            this.f15008e = new RelativeLayout(context);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, p(64));
            layoutParams5.addRule(3, this.f15007c.getId());
            layoutParams5.topMargin = p(25);
            this.d.addView(this.f15008e, layoutParams5);
            TextView textView = new TextView(context);
            this.f15010g = textView;
            textView.setMaxLines(1);
            this.f15010g.setId(R.id.jd_share_command_user_name);
            this.f15010g.setTextColor(-14277082);
            this.f15010g.setMaxWidth(p(250));
            this.f15010g.setGravity(16);
            this.f15010g.setEllipsize(TextUtils.TruncateAt.END);
            this.f15010g.setTextSize(0, p(28));
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams6.leftMargin = p(36);
            this.f15008e.addView(this.f15010g, layoutParams6);
            SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(context);
            this.f15016m = simpleDraweeView2;
            simpleDraweeView2.setId(R.id.jd_share_command_user_icon);
            this.f15016m.setImageResource(R.drawable.jd_share_command_smile);
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(p(16), p(7));
            layoutParams7.addRule(1, this.f15010g.getId());
            layoutParams7.addRule(15);
            layoutParams7.leftMargin = p(5);
            this.f15008e.addView(this.f15016m, layoutParams7);
            TextView textView2 = new TextView(context);
            this.f15011h = textView2;
            textView2.setGravity(16);
            this.f15011h.setTextColor(-7566196);
            this.f15011h.setTextSize(0, p(28));
            this.f15011h.setText(context.getString(R.string.jd_share_command_dialog_default));
            RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams8.leftMargin = p(28);
            layoutParams8.addRule(1, this.f15010g.getId());
            this.f15008e.addView(this.f15011h, layoutParams8);
            TextView textView3 = new TextView(context);
            this.f15012i = textView3;
            textView3.setTextColor(-14277082);
            this.f15012i.setId(R.id.jd_share_command_text_content);
            this.f15012i.setMaxLines(2);
            this.f15012i.setEllipsize(TextUtils.TruncateAt.END);
            this.f15012i.setGravity(16);
            this.f15012i.setTextSize(0, p(26));
            this.f15012i.setPadding(p(36), 0, p(36), 0);
            RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-1, p(104));
            layoutParams9.addRule(3, this.f15007c.getId());
            layoutParams9.topMargin = p(89);
            this.d.addView(this.f15012i, layoutParams9);
            GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{-912372, -907508, -897780});
            gradientDrawable2.setShape(0);
            gradientDrawable2.setCornerRadius(p(35));
            TextView textView4 = new TextView(context);
            this.f15013j = textView4;
            textView4.setId(R.id.jd_share_command_open);
            this.f15013j.setTextColor(-1);
            this.f15013j.setGravity(17);
            this.f15013j.setText(context.getString(R.string.jd_share_command_dialog_goto));
            this.f15013j.setTextSize(0, p(32));
            this.f15013j.setBackgroundDrawable(gradientDrawable2);
            this.f15013j.getPaint().setFakeBoldText(true);
            RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(p(510), p(70));
            layoutParams10.addRule(14);
            layoutParams10.addRule(3, this.f15012i.getId());
            layoutParams10.topMargin = p(30);
            this.d.addView(this.f15013j, layoutParams10);
            TextView textView5 = new TextView(context);
            this.f15014k = textView5;
            textView5.setGravity(17);
            this.f15014k.setTextColor(-7566196);
            this.f15014k.setText(context.getString(R.string.jd_share_command_dialog_feedback));
            this.f15014k.setTextSize(0, p(24));
            RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(-2, p(36));
            layoutParams11.addRule(3, this.f15013j.getId());
            layoutParams11.topMargin = p(23);
            layoutParams11.addRule(14);
            this.d.addView(this.f15014k, layoutParams11);
            SimpleDraweeView simpleDraweeView3 = new SimpleDraweeView(context);
            this.f15015l = simpleDraweeView3;
            simpleDraweeView3.setImageResource(R.drawable.common_close_white_normal);
            RelativeLayout.LayoutParams layoutParams12 = new RelativeLayout.LayoutParams(p(50), p(50));
            layoutParams12.topMargin = p(48);
            layoutParams12.addRule(14);
            layoutParams12.addRule(3, this.d.getId());
            this.b.addView(this.f15015l, layoutParams12);
        }

        private void n() {
            if ("cps_shop".equals(this.o.source)) {
                this.o.img = "https://img13.360buyimg.com/imagetools/jfs/t1/215549/26/17640/31363/6266470eEad00d83e/daf058da0ed6c0c1.png";
            } else if ("cps_product".equals(this.o.source)) {
                this.o.img = "https://img13.360buyimg.com/imagetools/jfs/t1/167311/37/23461/36740/6266470eEdfda2995/f527648efd878ba1.png";
            }
            JDImageUtils.displayImage(this.o.img, this.f15007c, this.v);
        }

        private void o() {
            JDImageUtils.displayImage(this.o.headImg, this.f15009f, this.u);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int p(int i2) {
            return g.d(this.q, i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean q(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return JumpUtil.isJdScheme(Uri.parse(str).getScheme());
        }

        private void r() {
            if (Build.VERSION.SDK_INT >= 21) {
                a aVar = new a();
                this.a = aVar;
                this.d.setOutlineProvider(aVar);
                this.d.setClipToOutline(true);
            }
        }

        private void u() {
            this.p.setContentView(this.b);
            this.p.setCancelable(false);
            this.p.setCanceledOnTouchOutside(false);
            this.f15017n.setVisibility(0);
            this.f15008e.setVisibility(0);
            o();
            this.d.getLayoutParams().height = p(R2.attr.contentPaddingEnd);
            ((RelativeLayout.LayoutParams) this.f15012i.getLayoutParams()).topMargin = p(89);
            r();
        }

        public c k() {
            this.b.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC0722b());
            this.f15012i.setText(this.o.title);
            this.f15010g.setText(this.o.userName);
            this.f15013j.setOnClickListener(new ViewOnClickListenerC0723c());
            this.f15015l.setOnClickListener(new d());
            this.f15014k.setOnClickListener(new e());
            n();
            u();
            return this.p;
        }

        public void m() {
            c cVar = this.p;
            if (cVar != null && cVar.isShowing()) {
                Context context = this.p.getContext();
                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
                    return;
                }
                this.p.dismiss();
            }
            com.jingdong.sdk.jdshare.cell.b bVar = this.t;
            if (bVar == null || !bVar.isShowing()) {
                return;
            }
            this.t.dismiss();
        }

        public b s(JDSharedCommandUtils.JDCommandInfo jDCommandInfo) {
            this.o = jDCommandInfo;
            if (TextUtils.isEmpty(jDCommandInfo.userName)) {
                this.o.userName = "\u795e\u79d8\u7528\u6237";
            }
            return this;
        }

        public b t(ShareInfo shareInfo) {
            this.s = shareInfo;
            return this;
        }
    }

    private c(Context context) {
        super(context, R.style.jd_share_key_dialog_style);
    }
}
