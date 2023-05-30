package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class TradeInResultOldDeviceCard extends ConstraintLayout {

    /* renamed from: g */
    public TextView f5425g;

    /* renamed from: h */
    public LinearLayout f5426h;

    /* renamed from: i */
    public TextView f5427i;

    /* renamed from: j */
    public View f5428j;

    /* renamed from: k */
    public View f5429k;

    /* renamed from: l */
    public View f5430l;

    /* renamed from: m */
    public View f5431m;

    /* renamed from: n */
    public View f5432n;
    public View o;
    public TextView p;
    public TextView q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public TextView w;
    public f x;
    public TradeInResultData.TradeInWareCardInfo y;
    public Drawable z;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        public a() {
            TradeInResultOldDeviceCard.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LiveData liveData;
            TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem;
            if (TradeInResultOldDeviceCard.this.x == null || !(view.getTag() instanceof TradeInResultData.TradeInWareCardInfo.TradeInWareInfo)) {
                return;
            }
            f fVar = TradeInResultOldDeviceCard.this.x;
            TradeInResultData.TradeInWareCardInfo.TradeInWareInfo tradeInWareInfo = (TradeInResultData.TradeInWareCardInfo.TradeInWareInfo) view.getTag();
            TradeInResultFragment.j jVar = (TradeInResultFragment.j) fVar;
            TradeInResultFragment.this.K.setVisibility(0);
            if (!TradeInResultFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) || TradeInResultFragment.this.f5399h == null) {
                return;
            }
            JsonObject jsonObject = new JsonObject();
            if (tradeInWareInfo != null && (tagItem = tradeInWareInfo.tagInfo) != null) {
                if (tagItem.tagType == 1) {
                    TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.o2nProductDetailedForHouseElectricVO o2nproductdetailedforhouseelectricvo = tradeInWareInfo.o2nProductDetailedForHouseElectricVO;
                    if (o2nproductdetailedforhouseelectricvo != null) {
                        jsonObject.addProperty("product_id", o2nproductdetailedforhouseelectricvo.oldProductId);
                    }
                } else {
                    TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = tradeInWareInfo.oldProductInfoForClap;
                    if (oldProductInfoForClap != null) {
                        jsonObject.addProperty("product_id", oldProductInfoForClap.oldProductId);
                    }
                }
            }
            TradeInResultFragment.this.f5399h.e("Productdetail_yjhxDeleteClick", jsonObject);
            TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
            tradeInViewModel.getClass();
            if (tradeInWareInfo != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
                hashMap.put("skuId", tradeInViewModel.f5253e);
                hashMap.put("inquiryId", tradeInWareInfo.inquiryId);
                hashMap.put("extend", tradeInViewModel.t);
                hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
                hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
                if (!TextUtils.isEmpty(tradeInViewModel.o)) {
                    hashMap.put("qualificationId", tradeInViewModel.o);
                }
                hashMap.put("queryDetailedType", Integer.valueOf(tradeInViewModel.p));
                if (tradeInWareInfo.tagInfo != null) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("tagId", tradeInWareInfo.tagInfo.tagId);
                    hashMap2.put("tagType", tradeInWareInfo.tagInfo.tagType + "");
                    hashMap.put("tagInfo", hashMap2);
                }
                com.jd.lib.productdetail.tradein.d.a aVar = new com.jd.lib.productdetail.tradein.d.a(hashMap);
                aVar.request(tradeInViewModel.b);
                liveData = aVar.mResult;
            } else {
                liveData = null;
            }
            liveData.observe(TradeInResultFragment.this.getViewLifecycleOwner(), new com.jd.lib.productdetail.tradein.f.c(jVar));
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
            TradeInResultOldDeviceCard.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TradeInResultOldDeviceCard.this.x == null || !(view.getTag() instanceof TradeInResultData.TradeInWareCardInfo.TradeInWareInfo)) {
                return;
            }
            f fVar = TradeInResultOldDeviceCard.this.x;
            TradeInResultData.TradeInWareCardInfo.TradeInWareInfo tradeInWareInfo = (TradeInResultData.TradeInWareCardInfo.TradeInWareInfo) view.getTag();
            TradeInResultFragment.j jVar = (TradeInResultFragment.j) fVar;
            jVar.getClass();
            if (tradeInWareInfo != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("extra.key.estimate.type", 2);
                bundle.putBoolean("extra.key.from.tradein.page", true);
                bundle.putSerializable("extra.key.old.device.tag", tradeInWareInfo.tagInfo);
                TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem = tradeInWareInfo.tagInfo;
                if (tagItem != null) {
                    int i2 = tagItem.tagType;
                    if (i2 == 1) {
                        bundle.putInt("extra.key.tag.type", i2);
                        TradeInResultFragment.this.f5398g.moveToStep(TradeInStep.SELECT_OLD_DEVICE, bundle);
                    } else if (i2 == 2) {
                        TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries = new TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries();
                        TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem = new TradeInSelectDeviceData.Data.CategoriesInfo.CateItem();
                        oldProductInquiries.inquiryId = tradeInWareInfo.inquiryId;
                        TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = tradeInWareInfo.oldProductInfoForClap;
                        if (oldProductInfoForClap != null) {
                            oldProductInquiries.oldProductId = oldProductInfoForClap.oldProductId;
                            oldProductInquiries.venderId = oldProductInfoForClap.venderId;
                        }
                        bundle.putSerializable("extra.key.old.device", oldProductInquiries);
                        bundle.putSerializable("extra.key.old.device.cate", cateItem);
                        TradeInResultFragment.this.f5398g.moveToStep(TradeInStep.ESTIMATE, bundle);
                    }
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class c implements View.OnClickListener {
        public c() {
            TradeInResultOldDeviceCard.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultData.TradeInFloorData tradeInFloorData;
            TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
            TradeInResultOldDeviceCard tradeInResultOldDeviceCard = TradeInResultOldDeviceCard.this;
            f fVar = tradeInResultOldDeviceCard.x;
            if (fVar != null) {
                TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInResultOldDeviceCard.y;
                TradeInResultFragment.j jVar = (TradeInResultFragment.j) fVar;
                jVar.getClass();
                Bundle bundle = new Bundle();
                bundle.putSerializable("key.oldWare", tradeInWareCardInfo);
                TradeInResultFragment.this.f5398g.moveToStep(TradeInStep.TRADEIN_MODE, bundle);
                if (TradeInResultFragment.this.f5399h != null) {
                    JsonObject jsonObject = new JsonObject();
                    if (tradeInWareCardInfo != null && (tradeInFloorData = tradeInWareCardInfo.exchangeWareWay) != null && (barterFloorRight = tradeInFloorData.rightInfo) != null) {
                        jsonObject.addProperty("change_ways", barterFloorRight.getText1());
                    }
                    TradeInResultFragment.this.f5399h.e("Productdetail_yjhxChangeWaysClick", jsonObject);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class d implements View.OnClickListener {
        public d() {
            TradeInResultOldDeviceCard.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInResultData.TradeInFloorData tradeInFloorData;
            TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
            TradeInResultOldDeviceCard tradeInResultOldDeviceCard = TradeInResultOldDeviceCard.this;
            f fVar = tradeInResultOldDeviceCard.x;
            if (fVar != null) {
                TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInResultOldDeviceCard.y;
                TradeInResultFragment.j jVar = (TradeInResultFragment.j) fVar;
                jVar.getClass();
                Bundle bundle = new Bundle();
                bundle.putSerializable("key.oldWare", tradeInWareCardInfo);
                TradeInResultFragment.this.f5398g.moveToStep(TradeInStep.WAY, bundle);
                if (TradeInResultFragment.this.f5399h != null) {
                    JsonObject jsonObject = new JsonObject();
                    if (tradeInWareCardInfo != null && (tradeInFloorData = tradeInWareCardInfo.tradMode) != null && (barterFloorRight = tradeInFloorData.rightInfo) != null) {
                        jsonObject.addProperty("Ttransaction_Ways", barterFloorRight.getText1());
                    }
                    TradeInResultFragment.this.f5399h.e("Productdetail_yjhxTransactionWays", jsonObject);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class e implements View.OnClickListener {
        public e() {
            TradeInResultOldDeviceCard.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f fVar = TradeInResultOldDeviceCard.this.x;
            if (fVar != null) {
                TradeInResultFragment.j jVar = (TradeInResultFragment.j) fVar;
                jVar.getClass();
                TradeInResultFragment.this.f5398g.moveToStep(TradeInStep.BANK, new Bundle());
                TradeInViewModel tradeInViewModel = TradeInResultFragment.this.f5399h;
                if (tradeInViewModel != null) {
                    tradeInViewModel.e("Productdetail_yjhxPaymentWays", null);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface f {
    }

    public TradeInResultOldDeviceCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable = context.getResources().getDrawable(R.drawable.tradein_icon_arrow_right_black);
        this.z = drawable;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.z.getIntrinsicHeight());
    }

    public void a(TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo) {
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight;
        TradeInResultData.BarterText barterText;
        TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight2;
        TradeInResultData.BarterText barterText2;
        this.y = tradeInWareCardInfo;
        if (tradeInWareCardInfo != null && tradeInWareCardInfo.isValid()) {
            ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList = tradeInWareCardInfo.wareList;
            if (arrayList != null && arrayList.size() > 0) {
                setVisibility(0);
                if (!TextUtils.isEmpty(tradeInWareCardInfo.title)) {
                    this.f5425g.setVisibility(0);
                    this.f5425g.setText(tradeInWareCardInfo.title);
                } else {
                    this.f5425g.setVisibility(8);
                }
                this.f5426h.removeAllViews();
                Iterator<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> it = tradeInWareCardInfo.wareList.iterator();
                while (it.hasNext()) {
                    TradeInResultData.TradeInWareCardInfo.TradeInWareInfo next = it.next();
                    if (next != null && next.isValid()) {
                        TradeInResultOldDeviceItem tradeInResultOldDeviceItem = (TradeInResultOldDeviceItem) LayoutInflater.from(getContext()).inflate(R.layout.tradein_result_old_device_item, (ViewGroup) this.f5426h, false);
                        tradeInResultOldDeviceItem.q = new a();
                        tradeInResultOldDeviceItem.r = new b();
                        tradeInResultOldDeviceItem.f(next);
                        this.f5426h.addView(tradeInResultOldDeviceItem);
                    }
                }
                if (!TextUtils.isEmpty(tradeInWareCardInfo.subsidy)) {
                    this.f5427i.setVisibility(0);
                    this.f5427i.setText(tradeInWareCardInfo.subsidy);
                } else {
                    this.f5427i.setVisibility(8);
                }
                TradeInResultData.TradeInFloorData tradeInFloorData = tradeInWareCardInfo.exchangeWareWay;
                if (tradeInFloorData != null && tradeInFloorData.rightInfo != null && tradeInFloorData.show) {
                    this.f5431m.setVisibility(0);
                    this.f5428j.setVisibility(0);
                    this.p.setVisibility(0);
                    this.q.setVisibility(0);
                    this.r.setVisibility(0);
                    this.p.setText(tradeInWareCardInfo.exchangeWareWay.text1);
                    if (!TextUtils.isEmpty(tradeInWareCardInfo.exchangeWareWay.rightInfo.getText1())) {
                        this.q.setText(tradeInWareCardInfo.exchangeWareWay.rightInfo.getText1());
                    }
                    if (!TextUtils.isEmpty(tradeInWareCardInfo.exchangeWareWay.rightInfo.getText2())) {
                        this.r.setText(tradeInWareCardInfo.exchangeWareWay.rightInfo.getText2());
                    }
                    if (tradeInWareCardInfo.getCurrentTradeInMode() != null && tradeInWareCardInfo.getCurrentTradeInMode().replacementModeList != null && tradeInWareCardInfo.getCurrentTradeInMode().replacementModeList.size() > 0) {
                        this.q.setCompoundDrawables(null, null, this.z, null);
                        this.r.setPadding(0, 0, PDUtils.dip2px(13.0f), 0);
                        this.f5431m.setClickable(true);
                    } else {
                        this.q.setCompoundDrawables(null, null, null, null);
                        this.r.setPadding(0, 0, 0, 0);
                        this.f5431m.setClickable(false);
                    }
                } else {
                    this.f5431m.setVisibility(8);
                }
                TradeInResultData.TradeInFloorData tradeInFloorData2 = tradeInWareCardInfo.tradMode;
                if (tradeInFloorData2 != null && tradeInFloorData2.show) {
                    this.f5432n.setVisibility(0);
                    this.f5429k.setVisibility(0);
                    this.s.setVisibility(0);
                    this.t.setVisibility(0);
                    this.u.setVisibility(0);
                    this.s.setText(tradeInWareCardInfo.tradMode.text1);
                    TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight3 = tradeInWareCardInfo.tradMode.rightInfo;
                    if (barterFloorRight3 != null) {
                        if (!TextUtils.isEmpty(barterFloorRight3.getText1())) {
                            this.t.setText(tradeInWareCardInfo.tradMode.rightInfo.getText1());
                        }
                        TradeInResultData.TradeInFloorData tradeInFloorData3 = tradeInWareCardInfo.tradMode;
                        if (tradeInFloorData3.showText2 && !TextUtils.isEmpty(tradeInFloorData3.rightInfo.getText2())) {
                            this.u.setText(tradeInWareCardInfo.tradMode.rightInfo.getText2());
                        } else {
                            this.u.setVisibility(8);
                        }
                    }
                    TradeInResultData.TradeInFloorData tradeInFloorData4 = tradeInWareCardInfo.tradMode;
                    if (tradeInFloorData4 != null && (barterFloorRight2 = tradeInFloorData4.rightInfo) != null && (barterText2 = barterFloorRight2.text1) != null && barterText2.event != null && tradeInFloorData4.showText2) {
                        this.t.setCompoundDrawables(null, null, this.z, null);
                        this.u.setPadding(0, 0, PDUtils.dip2px(13.0f), 0);
                        this.f5432n.setClickable(true);
                    } else {
                        this.t.setCompoundDrawables(null, null, null, null);
                        this.u.setPadding(0, 0, 0, 0);
                        this.f5432n.setClickable(false);
                    }
                } else {
                    this.f5432n.setVisibility(8);
                }
                TradeInResultData.TradeInFloorData tradeInFloorData5 = tradeInWareCardInfo.bankCard;
                if (tradeInFloorData5 != null && tradeInFloorData5.show) {
                    this.o.setVisibility(0);
                    this.f5430l.setVisibility(0);
                    this.v.setVisibility(0);
                    this.w.setVisibility(0);
                    this.v.setText(tradeInWareCardInfo.bankCard.text1);
                    TradeInResultData.TradeInFloorData.BarterFloorRight barterFloorRight4 = tradeInWareCardInfo.bankCard.rightInfo;
                    if (barterFloorRight4 != null && !TextUtils.isEmpty(barterFloorRight4.getText1())) {
                        this.w.setText(tradeInWareCardInfo.bankCard.rightInfo.getText1());
                    }
                    TradeInResultData.TradeInFloorData tradeInFloorData6 = tradeInWareCardInfo.bankCard;
                    if (tradeInFloorData6 != null && (barterFloorRight = tradeInFloorData6.rightInfo) != null && (barterText = barterFloorRight.text1) != null && barterText.event != null) {
                        this.w.setCompoundDrawables(null, null, this.z, null);
                        this.o.setClickable(true);
                        return;
                    }
                    this.w.setCompoundDrawables(null, null, null, null);
                    this.o.setClickable(false);
                    return;
                }
                this.o.setVisibility(8);
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
        this.f5425g = (TextView) findViewById(R.id.tradein_result_old_device_tag);
        this.f5426h = (LinearLayout) findViewById(R.id.tradein_result_old_device_container);
        this.f5427i = (TextView) findViewById(R.id.tradein_result_subsidy_hint);
        this.f5431m = findViewById(R.id.tradein_result_tradein_mode);
        this.f5428j = findViewById(R.id.tradein_result_tradein_mode_divider);
        this.p = (TextView) findViewById(R.id.tradein_result_tradein_mode_title);
        this.q = (TextView) findViewById(R.id.tradein_result_tradein_mode_value);
        this.r = (TextView) findViewById(R.id.tradein_result_tradein_mode_value_2);
        this.f5432n = findViewById(R.id.tradein_result_transaction_mode);
        this.f5429k = findViewById(R.id.tradein_result_transaction_mode_divider);
        this.s = (TextView) findViewById(R.id.tradein_result_transaction_mode_title);
        this.t = (TextView) findViewById(R.id.tradein_result_transaction_mode_value);
        this.u = (TextView) findViewById(R.id.tradein_result_transaction_time);
        this.o = findViewById(R.id.tradein_result_payment_mode);
        this.f5430l = findViewById(R.id.tradein_result_payment_mode_divider);
        this.v = (TextView) findViewById(R.id.tradein_result_payment_mode_title);
        this.w = (TextView) findViewById(R.id.tradein_result_payment_mode_value);
        this.f5431m.setOnClickListener(new c());
        this.f5432n.setOnClickListener(new d());
        this.o.setOnClickListener(new e());
    }
}
