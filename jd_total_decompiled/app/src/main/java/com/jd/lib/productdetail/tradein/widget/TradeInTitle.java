package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.bean.TradeInTitleInfo;
import com.jd.lib.productdetail.tradein.l.g;
import com.jd.lib.productdetail.tradein.l.i;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;

/* loaded from: classes16.dex */
public class TradeInTitle extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public SimpleDraweeView f5647g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5648h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInTitleInfo f5649i;

    /* renamed from: j  reason: collision with root package name */
    public View.OnClickListener f5650j;

    /* loaded from: classes16.dex */
    public class a implements ImageRequestListener<Bitmap> {
        public a() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onSuccess(Bitmap bitmap) {
            TradeInTitle.this.post(new i(this, bitmap));
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TradeInTitle.this.f5649i != null) {
                g gVar = new g(TradeInTitle.this.getContext());
                gVar.a(TradeInTitle.this.f5649i);
                gVar.show();
            }
            View.OnClickListener onClickListener = TradeInTitle.this.f5650j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public TradeInTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static boolean a(TradeInTitle tradeInTitle) {
        SimpleDraweeView simpleDraweeView;
        LifecycleOwner lifecycleOwner;
        return (tradeInTitle.getContext() == null || (simpleDraweeView = tradeInTitle.f5647g) == null || (lifecycleOwner = ViewTreeLifecycleOwner.get(simpleDraweeView)) == null || lifecycleOwner.getLifecycle() == null || lifecycleOwner.getLifecycle().getCurrentState() == null || !lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) ? false : true;
    }

    public void b(TradeInTitleInfo tradeInTitleInfo) {
        this.f5649i = tradeInTitleInfo;
        if (tradeInTitleInfo != null && tradeInTitleInfo.isValid()) {
            setVisibility(0);
            JDImageLoader.getBitmap(tradeInTitleInfo.threeStepImage, JDDisplayImageOptions.createSimple(), new a());
            if (TextUtils.isEmpty(tradeInTitleInfo.ruleClickText)) {
                tradeInTitleInfo.ruleClickText = getResources().getString(R.string.tradein_widget_title_btn_rule_default);
            }
            this.f5648h.setText(tradeInTitleInfo.ruleClickText);
            return;
        }
        setVisibility(8);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5647g = (SimpleDraweeView) findViewById(R.id.tradein_widget_title_icon);
        TextView textView = (TextView) findViewById(R.id.tradein_widget_title_btn_rule);
        this.f5648h = textView;
        textView.setOnClickListener(new b());
    }
}
