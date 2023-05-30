package com.jd.lib.productdetail.mainimage.holder.suit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.generic.RoundingParams;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDpgSmallInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdMainSku;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.j0;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes15.dex */
public class PdTopImageSuitViewFloorSkuItem extends ConstraintLayout {

    /* renamed from: g */
    public ImageView f4954g;

    /* renamed from: h */
    public TextView f4955h;

    /* renamed from: i */
    public float f4956i;

    /* renamed from: j */
    public boolean f4957j;

    /* renamed from: k */
    public PdMainImagePresenter f4958k;

    public PdTopImageSuitViewFloorSkuItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4957j = true;
    }

    public /* synthetic */ void b(String str, String str2, View view) {
        if (!this.f4957j || getContext() == null || this.f4958k == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) str);
        if (TextUtils.isEmpty(str2)) {
            str2 = "-100";
        }
        jDJSONObject.put("matchid", (Object) str2);
        this.f4958k.mtaClick("Productdetail_MainPhotoProduct", jDJSONObject.toJSONString());
        PDUtils.showToastCenterNormal(getContext(), getContext().getString(R.string.lib_pd_mainimage_current_shop));
    }

    public /* synthetic */ void c(String str, String str2, PdDpgSmallInfo.PdDpgItemInfo pdDpgItemInfo, View view) {
        if (this.f4957j) {
            try {
                if (this.f4958k != null) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("type", (Object) str);
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "-100";
                    }
                    jDJSONObject.put("matchid", (Object) str2);
                    jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) (pdDpgItemInfo != null ? pdDpgItemInfo.skuId : "-100"));
                    this.f4958k.mtaClick("Productdetail_MainPhotoMatchProuct", jDJSONObject.toJSONString());
                }
                j0.b(getContext(), Long.valueOf(Long.parseLong(pdDpgItemInfo.skuId)), null, null);
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }

    public void a(PdMainSku pdMainSku, final PdDpgSmallInfo.PdDpgItemInfo pdDpgItemInfo, final String str, final String str2) {
        String str3 = null;
        this.f4954g.setImageBitmap(null);
        this.f4955h.setText("");
        this.f4955h.setVisibility(8);
        setClickable(false);
        if (pdMainSku != null) {
            str3 = pdMainSku.mainSkuPicUrl;
            this.f4955h.setText(R.string.lib_pd_mainimage_current_product_text);
            this.f4955h.setVisibility(0);
            this.f4955h.setTextSize(0, PDUtils.dip2px(10.0f) * this.f4956i);
            this.f4955h.setBackgroundResource(R.drawable.lib_pd_mainimage_suit_item_label_bg_current);
            this.f4955h.setTypeface(Typeface.defaultFromStyle(0));
            this.f4955h.setTextColor(getResources().getColor(R.color.pd_white));
            if (this.f4957j) {
                setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.suit.a
                    {
                        PdTopImageSuitViewFloorSkuItem.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PdTopImageSuitViewFloorSkuItem.this.b(str, str2, view);
                    }
                });
            }
        } else if (pdDpgItemInfo != null) {
            str3 = pdDpgItemInfo.itemUrl;
            if (!TextUtils.isEmpty(pdDpgItemInfo.price)) {
                this.f4955h.setVisibility(0);
                this.f4955h.setText(pdDpgItemInfo.price);
                this.f4955h.setTextSize(0, PDUtils.dip2px(12.0f) * this.f4956i);
                this.f4955h.setBackgroundResource(R.drawable.lib_pd_mainimage_suit_item_label_bg_suit);
                this.f4955h.setTextColor(getResources().getColor(R.color.lib_pd_mainimage_jdred_text_light));
                FontsUtil.changeTextFont(this.f4955h);
            }
            if (this.f4957j) {
                setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.suit.b
                    {
                        PdTopImageSuitViewFloorSkuItem.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PdTopImageSuitViewFloorSkuItem.this.c(str, str2, pdDpgItemInfo, view);
                    }
                });
            }
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        JDDisplayImageOptions placeholder = new JDDisplayImageOptions().setPlaceholder(17);
        placeholder.setRoundingParams(new RoundingParams().setCornersRadius(PDUtils.dip2px(8.0f)));
        JDImageUtils.displayImage(str3, this.f4954g, placeholder, true);
    }

    public void f(boolean z) {
        this.f4957j = z;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4954g = (ImageView) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_item_pic);
        this.f4955h = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_item_name);
        if (getContext() instanceof Activity) {
            this.f4956i = (PDUtils.getAppWidth((Activity) getContext()) / PDUtils.getDensity()) / 375.0f;
        }
    }
}
