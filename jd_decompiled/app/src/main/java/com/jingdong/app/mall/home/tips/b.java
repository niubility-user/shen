package com.jingdong.app.mall.home.tips;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class b {
    private c a;
    private final Context b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f10924g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ SimpleDraweeView f10925h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ JDDisplayImageOptions f10926i;

        a(b bVar, String str, SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions) {
            this.f10924g = str;
            this.f10925h = simpleDraweeView;
            this.f10926i = jDDisplayImageOptions;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            e.f(this.f10924g, this.f10925h, this.f10926i);
        }
    }

    public b(Context context, c cVar) {
        this.b = context;
        this.a = cVar;
    }

    private void a(String str, SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions) {
        f.E0(new a(this, str, simpleDraweeView, jDDisplayImageOptions));
    }

    public View b() {
        View inflate;
        String jsonString = this.a.getJsonString("img", "");
        String jsonString2 = this.a.getJsonString("words1", "");
        String jsonString3 = this.a.getJsonString("words2", "");
        String jsonString4 = this.a.getJsonString("words3", "");
        if (TextUtils.isEmpty(jsonString) || TextUtils.isEmpty(jsonString2) || TextUtils.isEmpty(jsonString3) || TextUtils.isEmpty(jsonString4) || this.a.getJump() == null || (inflate = LayoutInflater.from(this.b).inflate(R.layout.home_tips_repeat_buy, (ViewGroup) null)) == null) {
            return null;
        }
        inflate.setLayoutParams(new RelativeLayout.LayoutParams(d.d(R2.attr.customNavigationLayout), d.d(116)));
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        f.n(layoutParams);
        ((RelativeLayout.LayoutParams) layoutParams).bottomMargin = d.d(10);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.home_tips_image);
        ViewGroup.LayoutParams layoutParams2 = simpleDraweeView.getLayoutParams();
        f.n(layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
        layoutParams3.width = d.d(76);
        layoutParams3.height = d.d(76);
        layoutParams3.leftMargin = d.d(40);
        TextView textView = (TextView) inflate.findViewById(R.id.home_tips_text_1);
        textView.setWidth(d.d(R2.attr.appTheme));
        textView.setTextSize(0, d.d(28));
        textView.setCompoundDrawablePadding(d.d(10));
        ViewGroup.LayoutParams layoutParams4 = textView.getLayoutParams();
        f.n(layoutParams4);
        ((RelativeLayout.LayoutParams) layoutParams4).leftMargin = d.d(20);
        TextView textView2 = (TextView) inflate.findViewById(R.id.home_tips_text_2);
        textView2.setTextSize(0, d.d(24));
        textView2.setWidth(d.d(R2.attr.actionModeWebSearchDrawable));
        ViewGroup.LayoutParams layoutParams5 = textView2.getLayoutParams();
        f.n(layoutParams5);
        ((RelativeLayout.LayoutParams) layoutParams5).leftMargin = d.d(20);
        TextView textView3 = (TextView) inflate.findViewById(R.id.home_tips_text_4);
        textView3.setTextSize(0, d.d(28));
        ViewGroup.LayoutParams layoutParams6 = ((SimpleDraweeView) inflate.findViewById(R.id.home_tips_close)).getLayoutParams();
        f.n(layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) layoutParams6;
        layoutParams7.width = d.d(60);
        layoutParams7.height = d.d(60);
        a(jsonString, simpleDraweeView, null);
        textView.setText(jsonString2);
        textView2.setText(jsonString3);
        textView3.setText(jsonString4);
        return inflate;
    }
}
