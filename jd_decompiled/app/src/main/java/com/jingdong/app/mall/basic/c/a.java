package com.jingdong.app.mall.basic.c;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.appshare.R;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.sdk.jdshare.utils.g;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes19.dex */
public class a extends Dialog {

    /* loaded from: classes19.dex */
    public static class b {

        /* renamed from: i  reason: collision with root package name */
        private static long f8007i;
        private Context a;
        private View b;

        /* renamed from: c  reason: collision with root package name */
        private a f8008c;
        private RelativeLayout d;

        /* renamed from: e  reason: collision with root package name */
        private String f8009e;

        /* renamed from: f  reason: collision with root package name */
        private String f8010f;

        /* renamed from: g  reason: collision with root package name */
        private String f8011g;

        /* renamed from: h  reason: collision with root package name */
        private c f8012h;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.basic.c.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes19.dex */
        public class ViewOnClickListenerC0240a implements View.OnClickListener {
            ViewOnClickListenerC0240a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!b.this.j() || b.this.f8012h == null) {
                    return;
                }
                b.this.f8012h.a(b.this.f8011g);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.app.mall.basic.c.a$b$b  reason: collision with other inner class name */
        /* loaded from: classes19.dex */
        public class ViewOnClickListenerC0241b implements View.OnClickListener {
            ViewOnClickListenerC0241b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.this.j() && b.this.f8012h != null) {
                    b.this.f8012h.b();
                }
                b.this.f8008c.dismiss();
            }
        }

        public b(Context context) {
            this.a = context;
            this.f8008c = new a(context);
        }

        private View f(Context context) {
            ScrollView scrollView;
            RelativeLayout relativeLayout = new RelativeLayout(context);
            this.d = new RelativeLayout(context);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(g.d(context, R2.attr.click_remove_id), g.d(context, R2.attr.bottomSheetStyle));
            layoutParams.addRule(14);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-1);
            gradientDrawable.setSize(g.d(this.a, R2.attr.click_remove_id), g.d(this.a, R2.attr.bottomSheetStyle));
            gradientDrawable.setCornerRadius(g.d(this.a, 23));
            relativeLayout.addView(this.d, layoutParams);
            this.d.setBackgroundDrawable(gradientDrawable);
            TextView textView = new TextView(context);
            textView.setText(this.f8010f);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            textView.setGravity(17);
            textView.setTextSize(0, g.d(this.a, 32));
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, g.d(this.a, 120));
            layoutParams2.addRule(14);
            this.d.addView(textView, layoutParams2);
            try {
                scrollView = (ScrollView) ImageUtil.inflate(R.layout.share_dialog_scrollview, null);
            } catch (Exception unused) {
                scrollView = new ScrollView(context);
            }
            scrollView.setClipToPadding(false);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(g.d(this.a, R2.attr.carousel_previousState), g.d(this.a, 206));
            scrollView.setPadding(g.d(this.a, 8), g.d(this.a, 16), g.d(this.a, 8), g.d(this.a, 16));
            layoutParams3.addRule(14);
            layoutParams3.topMargin = g.d(this.a, 121);
            View view = new View(context);
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setColor(Color.parseColor("#F2F2F2"));
            gradientDrawable2.setCornerRadius(g.d(this.a, 12));
            view.setBackgroundDrawable(gradientDrawable2);
            this.d.addView(view, layoutParams3);
            this.d.addView(scrollView, layoutParams3);
            TextView textView2 = new TextView(context);
            textView2.setText(TextUtils.isEmpty(this.f8009e) ? "" : this.f8009e);
            textView2.setTextColor(Color.parseColor("#8C8C8C"));
            textView2.setTextSize(0, g.d(this.a, 26));
            textView2.setLineSpacing(0.0f, 1.3f);
            textView2.setPadding(g.d(this.a, 16), g.d(this.a, 8), g.d(this.a, 16), g.d(this.a, 6));
            scrollView.addView(textView2, new RelativeLayout.LayoutParams(-1, -2));
            RelativeLayout relativeLayout2 = new RelativeLayout(context);
            relativeLayout2.setBackgroundDrawable(g(this.a));
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(g.d(this.a, R2.attr.carousel_previousState), g.d(this.a, 70));
            layoutParams4.addRule(14);
            layoutParams4.topMargin = g.d(this.a, R2.attr.ambientEnabled);
            this.d.addView(relativeLayout2, layoutParams4);
            relativeLayout2.setOnClickListener(new ViewOnClickListenerC0240a());
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(0);
            linearLayout.setGravity(16);
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(13);
            relativeLayout2.addView(linearLayout, layoutParams5);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            simpleDraweeView.setImageResource(i());
            LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(g.d(this.a, 48), g.d(this.a, 48));
            layoutParams6.rightMargin = g.d(this.a, 12);
            linearLayout.addView(simpleDraweeView, layoutParams6);
            TextView textView3 = new TextView(context);
            textView3.setText(h());
            textView3.setTextSize(0, g.d(this.a, 28));
            textView3.setGravity(17);
            textView3.setTextColor(-1);
            textView3.getPaint().setFakeBoldText(true);
            linearLayout.addView(textView3, new LinearLayout.LayoutParams(-2, -2));
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setBackgroundResource(R.drawable.common_close_white_normal);
            RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(g.d(this.a, 50), g.d(this.a, 50));
            layoutParams7.topMargin = g.d(this.a, R2.attr.cameraBearing);
            layoutParams7.leftMargin = g.d(this.a, 10);
            layoutParams7.rightMargin = g.d(this.a, 10);
            layoutParams7.addRule(14);
            relativeLayout.addView(linearLayout2, layoutParams7);
            linearLayout2.setOnClickListener(new ViewOnClickListenerC0241b());
            return relativeLayout;
        }

        private GradientDrawable g(Context context) {
            int[] iArr = {Color.parseColor(JDDarkUtil.COLOR_999999), Color.parseColor(JDDarkUtil.COLOR_999999)};
            String str = this.f8011g;
            if (str != null) {
                str.hashCode();
                char c2 = '\uffff';
                switch (str.hashCode()) {
                    case -1897456180:
                        if (str.equals("QQzone")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1541301387:
                        if (str.equals("QQfriends")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -250999948:
                        if (str.equals("Wxfriends")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 347823071:
                        if (str.equals("Sinaweibo")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 1584365650:
                        if (str.equals("Wxmoments")) {
                            c2 = 4;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        iArr = new int[]{Color.parseColor("#FDAE30"), Color.parseColor("#FDAE30"), Color.parseColor("#FDAE30")};
                        break;
                    case 1:
                        iArr = new int[]{Color.parseColor("#69A2FC"), Color.parseColor("#69A2FC"), Color.parseColor("#53C2F3")};
                        break;
                    case 2:
                        iArr = new int[]{Color.parseColor("#2FD17C"), Color.parseColor("#2FD17C"), Color.parseColor("#89F65B")};
                        break;
                    case 3:
                        iArr = new int[]{Color.parseColor("#FA5B48"), Color.parseColor("#FA5B48"), Color.parseColor("#FE7550")};
                        break;
                    case 4:
                        iArr = new int[]{Color.parseColor("#9CDC0F"), Color.parseColor("#9CDC0F"), Color.parseColor("#BDEA2D")};
                        break;
                }
            }
            return a.b(context, iArr);
        }

        private String h() {
            String str = this.f8011g;
            if (str != null) {
                str.hashCode();
                char c2 = '\uffff';
                switch (str.hashCode()) {
                    case -1897456180:
                        if (str.equals("QQzone")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1541301387:
                        if (str.equals("QQfriends")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -250999948:
                        if (str.equals("Wxfriends")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 347823071:
                        if (str.equals("Sinaweibo")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 1584365650:
                        if (str.equals("Wxmoments")) {
                            c2 = 4;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        return "\u53bbQQ\u7a7a\u95f4\u7c98\u8d34\u5206\u4eab";
                    case 1:
                        return "\u53bbQQ\u7c98\u8d34\u7ed9\u597d\u53cb";
                    case 2:
                        return "\u53bb\u5fae\u4fe1\u7c98\u8d34\u7ed9\u597d\u53cb";
                    case 3:
                        return "\u53bb\u65b0\u6d6a\u5fae\u535a\u7c98\u8d34\u5206\u4eab";
                    case 4:
                        return "\u53bb\u670b\u53cb\u5708\u7c98\u8d34\u5206\u4eab";
                }
            }
            return "";
        }

        private int i() {
            int i2 = R.drawable.share_key_wx_friend;
            String str = this.f8011g;
            if (str != null) {
                str.hashCode();
                char c2 = '\uffff';
                switch (str.hashCode()) {
                    case -1897456180:
                        if (str.equals("QQzone")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1541301387:
                        if (str.equals("QQfriends")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -250999948:
                        if (str.equals("Wxfriends")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 347823071:
                        if (str.equals("Sinaweibo")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 1584365650:
                        if (str.equals("Wxmoments")) {
                            c2 = 4;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        return R.drawable.share_key_qq_zone;
                    case 1:
                        return R.drawable.share_key_qq_friend;
                    case 2:
                    default:
                        return i2;
                    case 3:
                        return R.drawable.share_key_weibo;
                    case 4:
                        return R.drawable.share_key_wx_circle;
                }
            }
            return i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean j() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - f8007i >= 500) {
                f8007i = currentTimeMillis;
                return true;
            }
            return false;
        }

        public a e() {
            View f2 = f(this.a);
            this.b = f2;
            this.f8008c.setContentView(f2);
            this.f8008c.setCancelable(false);
            this.f8008c.setCanceledOnTouchOutside(false);
            return this.f8008c;
        }

        public b k(c cVar) {
            this.f8012h = cVar;
            return this;
        }

        public b l(String str) {
            this.f8009e = str;
            return this;
        }

        public b m(String str) {
            this.f8010f = str;
            return this;
        }

        public b n(String str) {
            this.f8011g = str;
            return this;
        }
    }

    /* loaded from: classes19.dex */
    public interface c {
        void a(String str);

        void b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GradientDrawable b(Context context, int[] iArr) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
        gradientDrawable.setCornerRadius(g.d(context, 45));
        gradientDrawable.setShape(0);
        return gradientDrawable;
    }

    private a(Context context) {
        super(context, R.style.jd_share_key_dialog_style);
    }
}
