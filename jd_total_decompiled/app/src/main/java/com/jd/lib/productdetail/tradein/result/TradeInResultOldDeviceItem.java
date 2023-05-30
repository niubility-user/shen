package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes16.dex */
public class TradeInResultOldDeviceItem extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public View f5438g;

    /* renamed from: h  reason: collision with root package name */
    public SimpleDraweeView f5439h;

    /* renamed from: i  reason: collision with root package name */
    public SimpleDraweeView f5440i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f5441j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f5442k;

    /* renamed from: l  reason: collision with root package name */
    public TextView f5443l;

    /* renamed from: m  reason: collision with root package name */
    public TextView f5444m;

    /* renamed from: n  reason: collision with root package name */
    public TextView f5445n;
    public TextView o;
    public FrameLayout p;
    public View.OnClickListener q;
    public View.OnClickListener r;
    public TradeInResultData.TradeInWareCardInfo.TradeInWareInfo s;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultOldDeviceItem tradeInResultOldDeviceItem = TradeInResultOldDeviceItem.this;
            if (tradeInResultOldDeviceItem.r != null) {
                view.setTag(tradeInResultOldDeviceItem.s);
                TradeInResultOldDeviceItem.this.r.onClick(view);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultOldDeviceItem tradeInResultOldDeviceItem = TradeInResultOldDeviceItem.this;
            if (tradeInResultOldDeviceItem.r != null) {
                view.setTag(tradeInResultOldDeviceItem.s);
                TradeInResultOldDeviceItem.this.r.onClick(view);
            }
        }
    }

    public TradeInResultOldDeviceItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final View view) {
        if (this.q != null) {
            final JDDialog jDDialog = new JDDialog(getContext());
            jDDialog.setContentView(R.layout.tradein_widget_dialog_delete);
            jDDialog.titleView = (TextView) jDDialog.findViewById(R.id.tradein_widget_dialog_delete_title);
            jDDialog.negButton = (Button) jDDialog.findViewById(R.id.tradein_widget_dialog_delete_btn_neg);
            jDDialog.posButton = (Button) jDDialog.findViewById(R.id.tradein_widget_dialog_delete_btn_pos);
            jDDialog.setCanceledOnTouchOutside(true);
            if (getResources() != null) {
                jDDialog.titleView.setText(getResources().getString(R.string.tradein_widget_dialog_delete_title, this.s.name));
                jDDialog.negButton.setText(getResources().getString(R.string.tradein_common_ok));
                jDDialog.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.l
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        TradeInResultOldDeviceItem.this.c(jDDialog, view, view2);
                    }
                });
                jDDialog.posButton.setText(getResources().getString(R.string.tradein_common_cancel));
                jDDialog.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.q
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        JDDialog.this.dismiss();
                    }
                });
                jDDialog.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(JDDialog jDDialog, View view, View view2) {
        jDDialog.dismiss();
        view.setTag(this.s);
        this.q.onClick(view);
    }

    public void f(TradeInResultData.TradeInWareCardInfo.TradeInWareInfo tradeInWareInfo) {
        this.s = tradeInWareInfo;
        if (tradeInWareInfo == null || !tradeInWareInfo.isValid()) {
            return;
        }
        this.f5441j.setText(tradeInWareInfo.name);
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(6.0f)));
        createSimple.resetViewBeforeLoading(false);
        createSimple.setPlaceholder(17);
        JDImageLoader.display(tradeInWareInfo.image, this.f5439h, createSimple);
        this.f5442k.setVisibility(8);
        this.f5440i.setVisibility(8);
        this.f5443l.setVisibility(8);
        this.f5444m.setVisibility(8);
        int i2 = tradeInWareInfo.clickStatus;
        if (i2 == 1) {
            this.f5442k.setVisibility(0);
            if (tradeInWareInfo.revisable) {
                this.f5440i.setVisibility(0);
            }
            this.f5442k.setText(tradeInWareInfo.price);
        } else if (i2 == 2) {
            this.f5444m.setVisibility(0);
            this.f5444m.setText(R.string.tradein_result_old_ware_btn_estimate);
        } else if (i2 == 3) {
            this.f5444m.setVisibility(0);
            this.f5444m.setText(R.string.tradein_result_old_ware_btn_update);
        } else if (i2 == 4) {
            this.f5442k.setVisibility(0);
            if (tradeInWareInfo.revisable) {
                this.f5440i.setVisibility(0);
            }
            this.f5442k.setText(tradeInWareInfo.price);
            this.f5443l.setVisibility(0);
            this.f5443l.setText(tradeInWareInfo.underPriceText);
        }
        this.p.setVisibility(8);
        this.o.setVisibility(8);
        this.f5445n.setVisibility(8);
        int i3 = tradeInWareInfo.subNameType;
        if (i3 == 2) {
            this.p.setVisibility(0);
            this.f5445n.setVisibility(0);
            this.f5445n.setText(tradeInWareInfo.subName);
            this.f5445n.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
        } else if (i3 == 3) {
            this.p.setVisibility(0);
            this.o.setVisibility(0);
            this.o.setText(tradeInWareInfo.subName);
        } else if (i3 != 4) {
        } else {
            this.p.setVisibility(0);
            this.f5445n.setVisibility(0);
            this.f5445n.setText(tradeInWareInfo.subName);
            this.f5445n.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5438g = findViewById(R.id.tradein_result_old_device_btn_delete);
        this.f5439h = (SimpleDraweeView) findViewById(R.id.tradein_result_old_device_preview);
        this.f5440i = (SimpleDraweeView) findViewById(R.id.tradein_result_old_device_preview_btn_estimate);
        this.f5441j = (TextView) findViewById(R.id.tradein_result_old_device_name);
        TextView textView = (TextView) findViewById(R.id.tradein_result_old_device_price);
        this.f5442k = textView;
        FontsUtil.changeTextFont(textView);
        this.f5443l = (TextView) findViewById(R.id.tradein_result_old_device_sub_price);
        this.f5444m = (TextView) findViewById(R.id.tradein_result_old_device_btn_refresh);
        this.f5445n = (TextView) findViewById(R.id.tradein_result_old_device_no_subsidy);
        this.o = (TextView) findViewById(R.id.tradein_result_old_device_subsidy);
        this.p = (FrameLayout) findViewById(R.id.tradein_result_old_device_subsidy_layout);
        this.f5440i.setOnClickListener(new a());
        this.f5438g.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInResultOldDeviceItem.this.a(view);
            }
        });
        this.f5444m.setOnClickListener(new b());
    }
}
