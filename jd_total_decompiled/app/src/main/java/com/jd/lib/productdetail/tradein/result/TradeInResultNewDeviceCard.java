package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.events.PDLayerEvent;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.bean.TradeInServiceInfo;
import com.jd.lib.productdetail.tradein.l.h;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.jdsdk.utils.FontsUtil;
import de.greenrobot.event.EventBus;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInResultNewDeviceCard extends ConstraintLayout {

    /* renamed from: g */
    public List<String> f5417g;

    /* renamed from: h */
    public TradeInViewModel f5418h;

    /* renamed from: i */
    public TradeInDialogFragment f5419i;

    /* renamed from: j */
    public TextView f5420j;

    /* renamed from: k */
    public SimpleDraweeView f5421k;

    /* renamed from: l */
    public TextView f5422l;

    /* renamed from: m */
    public TextView f5423m;

    /* renamed from: n */
    public TextView f5424n;
    public TextView o;
    public TextView p;
    public TextView q;

    public TradeInResultNewDeviceCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
    }

    public /* synthetic */ void b(View view) {
        ProductDetailEntity productDetailEntity;
        TradeInViewModel tradeInViewModel = this.f5418h;
        if (tradeInViewModel == null || (productDetailEntity = tradeInViewModel.f5252c) == null) {
            return;
        }
        productDetailEntity.mIsInTradeInStep = true;
        TradeInDialogFragment tradeInDialogFragment = this.f5419i;
        if (tradeInDialogFragment != null) {
            tradeInDialogFragment.dismiss();
        }
        try {
            JsonObject jsonObject = new JsonObject();
            List<String> list = this.f5417g;
            if (list == null) {
                jsonObject.add("touchstone_expids", null);
            } else if (list != null) {
                JsonArray jsonArray = new JsonArray();
                for (int i2 = 0; i2 < this.f5417g.size(); i2++) {
                    jsonArray.add(this.f5417g.get(i2));
                }
                jsonObject.add("touchstone_expids", jsonArray);
            }
            this.f5418h.e("Productdetail_yjhxChangeAgain", jsonObject);
        } catch (Exception unused) {
        }
        EventBus eventBus = PDManager.getEventBus();
        TradeInViewModel tradeInViewModel2 = this.f5418h;
        eventBus.post(new PDLayerEvent(PDLayerEvent.ACTION_EVENT_LAYER_TRADEIN_OPEN_STYLE, new Object[]{"layer.tradein", tradeInViewModel2.f5254f}, tradeInViewModel2.f5252c.mManageKey));
    }

    public void c(JsonObject jsonObject, TradeInResultData.TradeInWareCardInfo.TradeInWareInfo tradeInWareInfo, View view) {
        TradeInViewModel tradeInViewModel = this.f5418h;
        if (tradeInViewModel != null) {
            tradeInViewModel.e("Productdetail_yjhxSeviceYXSmallI", jsonObject);
            this.f5418h.h("Productdetail_yjhxSeviceYXToastExpo", jsonObject);
        }
        h.a aVar = new h.a(getContext());
        aVar.b = getContext().getString(R.string.tradein_widget_dialog_service);
        aVar.d = tradeInWareInfo.serviceInfo.serviceList;
        aVar.f5394c = getContext().getString(R.string.tradein_widget_dialog_rule_btn_know);
        aVar.f5395e = new DialogInterface.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.o
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                TradeInResultNewDeviceCard.a(dialogInterface, i2);
            }
        };
        aVar.a().show();
    }

    public void f(List<String> list) {
        this.f5417g = list;
        TradeInViewModel tradeInViewModel = this.f5418h;
        if (tradeInViewModel != null) {
            tradeInViewModel.a = list;
        }
    }

    public void g(TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo) {
        if (tradeInWareCardInfo != null && tradeInWareCardInfo.mNewDevice && tradeInWareCardInfo.isValid()) {
            final TradeInResultData.TradeInWareCardInfo.TradeInWareInfo tradeInWareInfo = tradeInWareCardInfo.wareList.get(0);
            if (tradeInWareInfo != null && tradeInWareInfo.isValid()) {
                setVisibility(0);
                if (!TextUtils.isEmpty(tradeInWareCardInfo.title)) {
                    this.f5420j.setVisibility(0);
                    this.f5420j.setText(tradeInWareCardInfo.title);
                } else {
                    this.f5420j.setVisibility(8);
                }
                this.f5422l.setText(tradeInWareInfo.name);
                if (TextUtils.isEmpty(tradeInWareInfo.price)) {
                    tradeInWareInfo.price = getResources().getString(R.string.tradein_result_new_ware_no_price);
                }
                this.f5423m.setText(tradeInWareInfo.price);
                if (!TextUtils.isEmpty(tradeInWareInfo.landedPriceText)) {
                    this.f5424n.setText(tradeInWareInfo.landedPriceText);
                    this.f5424n.setVisibility(0);
                } else {
                    this.f5424n.setVisibility(8);
                }
                this.o.setText(tradeInWareInfo.subName);
                TradeInServiceInfo tradeInServiceInfo = tradeInWareInfo.serviceInfo;
                if (tradeInServiceInfo != null && !TextUtils.isEmpty(tradeInServiceInfo.floorContent)) {
                    this.q.setText(tradeInWareInfo.serviceInfo.floorContent);
                    this.q.setVisibility(0);
                    if (tradeInWareInfo.serviceInfo.hasI) {
                        final JsonObject jsonObject = new JsonObject();
                        try {
                            TradeInViewModel tradeInViewModel = this.f5418h;
                            if (tradeInViewModel != null && tradeInViewModel.a != null) {
                                JsonArray jsonArray = new JsonArray();
                                for (int i2 = 0; i2 < this.f5418h.a.size(); i2++) {
                                    jsonArray.add(this.f5418h.a.get(i2));
                                }
                                jsonObject.add("touchstone_expids", jsonArray);
                            } else {
                                jsonObject.add("touchstone_expids", null);
                            }
                        } catch (Exception unused) {
                        }
                        this.f5418h.h("Productdetail_yjhxSeviceYXSmallIExpo", jsonObject);
                        Drawable drawable = getResources().getDrawable(R.drawable.tradein_icon_i);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        this.q.setCompoundDrawables(null, null, drawable, null);
                        this.q.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.i
                            {
                                TradeInResultNewDeviceCard.this = this;
                            }

                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TradeInResultNewDeviceCard.this.c(jsonObject, tradeInWareInfo, view);
                            }
                        });
                    } else {
                        this.q.setCompoundDrawables(null, null, null, null);
                        this.q.setOnClickListener(null);
                    }
                } else {
                    this.q.setVisibility(8);
                }
                if (this.p.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.p.getLayoutParams();
                    if (!TextUtils.isEmpty(tradeInWareInfo.landedPriceText)) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = PDUtils.dip2px(10.0f);
                    } else {
                        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = PDUtils.dip2px(22.0f);
                    }
                    this.p.setLayoutParams(layoutParams);
                }
                TextView textView = this.p;
                TradeInViewModel tradeInViewModel2 = this.f5418h;
                textView.setVisibility((tradeInViewModel2 == null || !tradeInViewModel2.f5260l || tradeInViewModel2.f5252c == null) ? 4 : 0);
                TextView textView2 = this.p;
                TradeInViewModel tradeInViewModel3 = this.f5418h;
                textView2.setClickable((tradeInViewModel3 == null || !tradeInViewModel3.f5260l || tradeInViewModel3.f5252c == null) ? false : true);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(6.0f)));
                createSimple.resetViewBeforeLoading(false);
                createSimple.setPlaceholder(17);
                JDImageLoader.display(tradeInWareInfo.image, this.f5421k, createSimple);
                return;
            }
            setVisibility(8);
            return;
        }
        setVisibility(8);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5420j = (TextView) findViewById(R.id.tradein_result_new_device_tag);
        this.f5421k = (SimpleDraweeView) findViewById(R.id.tradein_result_new_device_preview);
        this.f5422l = (TextView) findViewById(R.id.tradein_result_new_device_name);
        TextView textView = (TextView) findViewById(R.id.tradein_result_new_device_price);
        this.f5423m = textView;
        FontsUtil.changeTextFont(textView);
        this.f5424n = (TextView) findViewById(R.id.tradein_result_new_device_landed);
        this.o = (TextView) findViewById(R.id.tradein_result_new_device_style);
        this.p = (TextView) findViewById(R.id.tradein_result_new_device_btn_style);
        this.q = (TextView) findViewById(R.id.tradein_result_new_device_service);
        this.p.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.result.j
            {
                TradeInResultNewDeviceCard.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInResultNewDeviceCard.this.b(view);
            }
        });
    }
}
