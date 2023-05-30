package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes9.dex */
public class ca {

    /* loaded from: classes9.dex */
    public static class a {
        private Toast a;
        private int b;

        /* renamed from: c */
        private int f16365c;
        private int d;

        /* renamed from: e */
        private String f16366e;

        /* renamed from: f */
        private String f16367f;

        /* renamed from: g */
        private TextView f16368g;

        /* renamed from: h */
        private TextView f16369h;

        /* renamed from: i */
        private TextView f16370i;

        /* renamed from: j */
        private Context f16371j;

        public a(Context context) {
            this.f16371j = context;
        }

        public ViewGroup a(Context context, String str, String str2) {
            this.f16366e = str;
            this.f16367f = str2;
            LinearLayout linearLayout = new LinearLayout(context);
            int a = y9.a(context, 5);
            int a2 = y9.a(context, 10);
            linearLayout.setPadding(a2, a, a2, a);
            linearLayout.setBackgroundColor(-12303292);
            linearLayout.setOrientation(1);
            if (!TextUtils.isEmpty(str)) {
                this.f16369h = new TextView(context);
                int a3 = y9.a(context, 5);
                this.f16369h.setPadding(a3, a3, a3, a3);
                this.f16369h.setText(str);
                this.f16369h.setTextColor(-1);
                this.f16369h.setTextSize(2, 18.0f);
                linearLayout.addView(this.f16369h, new ViewGroup.MarginLayoutParams(-1, -2));
            }
            if (!TextUtils.isEmpty(str2)) {
                TextView textView = new TextView(context);
                this.f16370i = textView;
                textView.setId(16908299);
                this.f16370i.setText(str2);
                this.f16370i.setTextColor(-1);
                this.f16370i.setTextSize(2, 16.0f);
                int a4 = y9.a(context, 5);
                this.f16370i.setPadding(a4, a4, a4, a4);
                linearLayout.addView(this.f16370i, new ViewGroup.MarginLayoutParams(-1, -2));
            }
            LinearLayout linearLayout2 = new LinearLayout(context);
            linearLayout2.setOrientation(0);
            linearLayout2.setGravity(21);
            TextView textView2 = new TextView(context);
            int a5 = y9.a(context, 5);
            int a6 = y9.a(context, 10);
            textView2.setTextColor(-1);
            textView2.setBackgroundColor(-16776961);
            textView2.setPadding(a6, a5, a6, a5);
            textView2.setVisibility(8);
            this.f16368g = textView2;
            linearLayout2.addView(textView2, new ViewGroup.MarginLayoutParams(-2, -2));
            linearLayout.addView(linearLayout2, new ViewGroup.MarginLayoutParams(-1, -2));
            return linearLayout;
        }

        public a a(Context context, View view, int i2) {
            Toast toast = new Toast(context);
            this.a = toast;
            toast.setView(view);
            this.a.setDuration(i2);
            this.b = this.a.getGravity();
            return this;
        }

        public a a(int i2) {
            this.b = i2;
            Toast toast = this.a;
            if (toast != null) {
                toast.setGravity(i2, this.f16365c, this.d);
            }
            return this;
        }

        public a a(int i2, int i3) {
            this.f16365c = i2;
            this.d = i3;
            Toast toast = this.a;
            if (toast != null) {
                toast.setGravity(this.b, i2, i3);
            }
            return this;
        }

        public a a(String str) {
            return a(str, GravityCompat.START);
        }

        public a a(String str, int i2) {
            TextView textView;
            this.f16367f = str;
            if (str != null && (textView = this.f16370i) != null) {
                textView.setText(str);
                this.f16370i.setGravity(i2);
            }
            return this;
        }

        public a a(String str, View.OnClickListener onClickListener) {
            if (this.f16368g != null && !TextUtils.isEmpty(str) && onClickListener != null) {
                this.f16368g.setText(str);
                this.f16368g.setOnClickListener(onClickListener);
                this.f16368g.setVisibility(0);
            }
            return this;
        }

        public a a(boolean z) {
            try {
                Object a = d7.a(this.a, "mTN");
                if (a != null) {
                    Object a2 = d7.a(a, "mParams");
                    if (a2 instanceof WindowManager.LayoutParams) {
                        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) a2;
                        if (z) {
                            layoutParams.flags = R2.anim.lib_cashier_sdk_fragment_right_out;
                        } else {
                            layoutParams.flags = 152;
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return this;
        }

        public void a() {
            Toast toast = this.a;
            if (toast != null) {
                toast.cancel();
            }
        }

        public a b(String str) {
            TextView textView;
            this.f16366e = str;
            if (str != null && (textView = this.f16369h) != null) {
                textView.setText(str);
            }
            return this;
        }

        public boolean b() {
            Toast toast = this.a;
            if (toast != null) {
                toast.show();
                return true;
            }
            return false;
        }
    }

    public static a a(Context context) {
        return a(context, LangUtils.SINGLE_SPACE, 0);
    }

    public static a a(Context context, String str) {
        return a(context, str, 0);
    }

    public static a a(Context context, String str, int i2) {
        return a(context, null, str, i2);
    }

    public static a a(Context context, String str, String str2, int i2) {
        if (context == null) {
            return null;
        }
        a aVar = new a(context);
        return aVar.a(context, aVar.a(context, str, str2), i2);
    }
}
