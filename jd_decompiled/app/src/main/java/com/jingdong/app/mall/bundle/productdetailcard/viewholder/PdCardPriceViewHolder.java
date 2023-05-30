package com.jingdong.app.mall.bundle.productdetailcard.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.bundle.productdetailcard.PdCardView;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardPriceData;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes3.dex */
public class PdCardPriceViewHolder extends RecyclerView.ViewHolder implements a {

    /* renamed from: g  reason: collision with root package name */
    private TextView f8269g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f8270h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f8271i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f8272j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f8273k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f8274l;

    /* renamed from: m  reason: collision with root package name */
    private Context f8275m;

    public PdCardPriceViewHolder(Context context, @NonNull View view) {
        super(view);
        this.f8275m = context;
        this.f8269g = (TextView) view.findViewById(R.id.productdetailcard_price_title_tv);
        TextView textView = (TextView) view.findViewById(R.id.productdetailcard_main_price_tv);
        this.f8270h = textView;
        FontsUtil.changeTextFont(textView);
        TextView textView2 = (TextView) view.findViewById(R.id.productdetailcard_hx_price_tv1);
        this.f8272j = textView2;
        FontsUtil.changeTextFont(textView2);
        TextView textView3 = (TextView) view.findViewById(R.id.productdetailcard_hx_price_tv2);
        this.f8273k = textView3;
        FontsUtil.changeTextFont(textView3);
        TextView textView4 = (TextView) view.findViewById(R.id.productdetailcard_plus_price);
        this.f8274l = textView4;
        FontsUtil.changeTextFont(textView4);
        this.f8271i = (SimpleDraweeView) view.findViewById(R.id.productdetailcard_main_price_icon);
    }

    private CharSequence b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) LangUtils.SINGLE_SPACE).append((CharSequence) str).append((CharSequence) LangUtils.SINGLE_SPACE);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(13, true), 0, spannableStringBuilder.length(), 17);
        return spannableStringBuilder;
    }

    private CharSequence c(PdCardPriceData.PdCardPriceItem pdCardPriceItem) {
        if (TextUtils.isEmpty(pdCardPriceItem.val)) {
            return "";
        }
        String str = pdCardPriceItem.val;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        Bitmap bitmap = UnIconConfigHelper.getBitmap(pdCardPriceItem.icon);
        if (bitmap != null && !bitmap.isRecycled()) {
            spannableStringBuilder.insert(str.length(), (CharSequence) "\b\b");
            spannableStringBuilder.setSpan(new com.jingdong.app.mall.bundle.productdetailcard.a(com.jingdong.app.mall.bundle.productdetailcard.a.a(this.f8275m, bitmap)), str.length() + 1, spannableStringBuilder.length(), 17);
        }
        return spannableStringBuilder;
    }

    private CharSequence d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (str.startsWith("\u00a5")) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.65f), 0, 1, 33);
        }
        int lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
        if (lastIndexOf != -1) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.65f), lastIndexOf, str.length(), 33);
        }
        return spannableStringBuilder;
    }

    private CharSequence e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StrikethroughSpan(), 0, str.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(JDDarkUtil.COLOR_BFBFBF)), 0, str.length(), 33);
        return spannableString;
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        boolean z;
        if (pdCardFloorInfo != null) {
            if (!TextUtils.isEmpty(pdCardFloorInfo.title)) {
                this.f8269g.setText(pdCardFloorInfo.title);
            }
            Object obj = pdCardFloorInfo.data;
            if (obj instanceof JDJSONObject) {
                PdCardPriceData pdCardPriceData = (PdCardPriceData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardPriceData.class);
                if (Log.D) {
                    Log.d(PdCardView.TAG, "PdCardPriceViewHolder:bindView pdCardPriceData" + pdCardPriceData);
                }
                if (pdCardPriceData != null) {
                    PdCardPriceData.PdCardPriceItem pdCardPriceItem = pdCardPriceData.priceType;
                    String str = (pdCardPriceItem == null || TextUtils.isEmpty(pdCardPriceItem.val)) ? "" : pdCardPriceData.priceType.val;
                    Log.d(PdCardView.TAG, "PdCardPriceViewHolder:priceType = " + str);
                    str.hashCode();
                    if (!str.equals("2")) {
                        PdCardPriceData.PdCardSecondPriceInfo pdCardSecondPriceInfo = pdCardPriceData.secondPriceInfo;
                        if (pdCardSecondPriceInfo != null) {
                            if (!TextUtils.isEmpty(pdCardSecondPriceInfo.val)) {
                                this.f8270h.setText(b(pdCardPriceData.secondPriceInfo.val));
                                this.f8270h.setPadding(DPIUtil.dip2px(32.0f), 0, 0, 0);
                                this.f8270h.setBackgroundResource(R.drawable.productdetailcard_praise_item_price_bg);
                            } else {
                                this.f8270h.setText("");
                            }
                            if (!TextUtils.isEmpty(pdCardPriceData.secondPriceInfo.icon)) {
                                this.f8271i.setVisibility(0);
                                JDImageUtils.displayImage(pdCardPriceData.secondPriceInfo.icon, this.f8271i, new JDDisplayImageOptions());
                            } else {
                                this.f8271i.setVisibility(8);
                            }
                            PdCardPriceData.PdCardPriceItem pdCardPriceItem2 = pdCardPriceData.jPrice;
                            if (pdCardPriceItem2 != null) {
                                CharSequence e2 = e(pdCardPriceItem2.val);
                                this.f8272j.setText(e2);
                                this.f8273k.setText(e2);
                                this.f8272j.setVisibility(0);
                                this.f8273k.setVisibility(8);
                            } else {
                                this.f8272j.setVisibility(8);
                                this.f8273k.setVisibility(8);
                            }
                        } else {
                            this.f8270h.setPadding(0, 0, 0, 0);
                            this.f8271i.setVisibility(8);
                            PdCardPriceData.PdCardPriceItem pdCardPriceItem3 = pdCardPriceData.jPrice;
                            if (pdCardPriceItem3 != null) {
                                this.f8270h.setText(d(pdCardPriceItem3.val));
                                this.f8270h.setBackgroundColor(0);
                            } else {
                                this.f8270h.setText("");
                            }
                            PdCardPriceData.PdCardPriceItem pdCardPriceItem4 = pdCardPriceData.hxPrice;
                            if (pdCardPriceItem4 != null) {
                                CharSequence e3 = e(pdCardPriceItem4.val);
                                this.f8272j.setText(e3);
                                this.f8273k.setText(e3);
                                this.f8272j.setVisibility(0);
                                this.f8273k.setVisibility(8);
                            } else {
                                this.f8272j.setVisibility(8);
                                this.f8273k.setVisibility(8);
                            }
                        }
                        this.f8274l.setVisibility(8);
                        return;
                    }
                    this.f8270h.setPadding(0, 0, 0, 0);
                    this.f8271i.setVisibility(8);
                    PdCardPriceData.PdCardPriceItem pdCardPriceItem5 = pdCardPriceData.jPrice;
                    if (pdCardPriceItem5 != null) {
                        this.f8270h.setText(d(pdCardPriceItem5.val));
                        this.f8270h.setBackgroundColor(0);
                    } else {
                        this.f8270h.setText("");
                    }
                    PdCardPriceData.PdCardPriceItem pdCardPriceItem6 = pdCardPriceData.hxPrice;
                    if (pdCardPriceItem6 != null) {
                        CharSequence e4 = e(pdCardPriceItem6.val);
                        this.f8272j.setText(e4);
                        this.f8273k.setText(e4);
                        z = true;
                    } else {
                        z = false;
                    }
                    if (pdCardPriceData.plusPrice != null) {
                        this.f8274l.setVisibility(0);
                        this.f8274l.setText(c(pdCardPriceData.plusPrice));
                        if (z) {
                            this.f8272j.setVisibility(8);
                            this.f8273k.setVisibility(0);
                            return;
                        }
                        this.f8272j.setVisibility(8);
                        this.f8273k.setVisibility(8);
                    }
                }
            }
        }
    }
}
