package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusForBuyMt;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.mall.bundle.styleinfoview.views.CustomTypefaceSpan;
import com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes3.dex */
public class PdYuYueDealUtil {
    private LibPdStyleInfoViewUtils mUtils;
    @DrawableRes
    private int mRedDrawable = R.drawable.libpdstyleinfoview_add2car_corner_bg_v8;
    @DrawableRes
    private int mYellowDrawable = R.drawable.libpdstyleinfoview_same_corner_bg_v8;

    private void handleJoinBuyPriceStyle(Context context, String str, Spannable spannable, String str2) {
        try {
            int indexOf = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (indexOf > 0) {
                int indexOf2 = str.indexOf("(");
                if (indexOf2 > 0) {
                    spannable.setSpan(new AbsoluteSizeSpan(12, true), indexOf, indexOf2, 18);
                    spannable.setSpan(new AbsoluteSizeSpan(10, true), indexOf2, str.length(), 18);
                } else if (!TextUtils.isEmpty(str2)) {
                    spannable.setSpan(new AbsoluteSizeSpan(12, true), indexOf, str.length() - str2.length(), 18);
                    spannable.setSpan(new AbsoluteSizeSpan(10, true), str.length() - str2.length(), str.length(), 18);
                } else {
                    spannable.setSpan(new AbsoluteSizeSpan(12, true), indexOf, str.length(), 18);
                }
            }
            if (str.startsWith("\u00a5")) {
                spannable.setSpan(new AbsoluteSizeSpan(10, true), 0, 1, 18);
                int indexOf3 = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf3 > 0) {
                    spannable.setSpan(new AbsoluteSizeSpan(13, true), 1, indexOf3, 18);
                    spannable.setSpan(new AbsoluteSizeSpan(10, true), indexOf3, indexOf, 18);
                }
                spannable.setSpan(new CustomTypefaceSpan("", FontsUtil.getTypeFace(context, 4098)), 0, str.length(), 18);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    private void handleMadYuyueStyle(String str, Spannable spannable) {
        try {
            int lastIndexOf = str.lastIndexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (lastIndexOf != -1) {
                spannable.setSpan(new CustomTypefaceSpan("", Typeface.DEFAULT), lastIndexOf, str.length(), 18);
                spannable.setSpan(new RelativeSizeSpan(0.77f), lastIndexOf, str.length(), 33);
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    private boolean isYuyueMask(Context context, TextView textView, TextView textView2, ProductDetailEntity productDetailEntity, int i2) {
        if (textView == null || textView2 == null || !TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.type, "5")) {
            return false;
        }
        WareYuYueInfo wareYuYueInfo = productDetailEntity.mWareBusinessData.yuyueInfo;
        if (wareYuYueInfo.showDraw) {
            if (TextUtils.equals(wareYuYueInfo.drawEnum, "1")) {
                setYuyueMaskBtn(context, textView, textView2, productDetailEntity, i2, productDetailEntity.mWareBusinessData.supportSale, 2, this.mRedDrawable);
                textView.setTag(2);
            } else if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.drawEnum, "3")) {
                setYuyueMaskBtn(context, textView, textView2, productDetailEntity, R.string.libpdstyleinfoview_pd_yuyue_mask_no_hit, false, -1, this.mRedDrawable);
            } else if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.drawEnum, "2")) {
                setYuyueMaskBtn(context, textView, textView2, productDetailEntity, R.string.libpdstyleinfoview_pd_yuyue_mask_no_yuyue, false, -1, this.mRedDrawable);
            } else if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.drawEnum, "4")) {
                setYuyueMaskBtn(context, textView, textView2, productDetailEntity, R.string.libpdstyleinfoview_pd_yuyue_swaying_buy, false, 2, this.mRedDrawable);
                textView.setTag(2);
            }
        } else {
            setYuyueMaskBtn(context, textView, textView2, productDetailEntity, productDetailEntity.isYuyueMask() ? R.string.libpdstyleinfoview_pd_yuyue_mask_draw : i2, false, -1, this.mRedDrawable);
        }
        return true;
    }

    private void setCarBtnStatus(Context context, TextView textView, TextView textView2, ProductDetailEntity productDetailEntity, String str, boolean z, int i2, int i3) {
        WareYuYueInfo wareYuYueInfo;
        if (textView == null) {
            return;
        }
        textView.setEnabled(z);
        if (productDetailEntity.getAddCartButtonMultiRow()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (!TextUtils.isEmpty(productDetailEntity.getAddCartButtonContent())) {
                sb.append(productDetailEntity.getAddCartButtonContent());
            }
            SpannableString spannableString = new SpannableString(sb);
            handleJoinBuyPriceStyle(context, sb.toString(), spannableString, productDetailEntity.getAddCartButtonContent());
            textView.setText(spannableString);
        } else {
            textView.setText(str);
        }
        if (i2 != -1) {
            textView.setTag(Integer.valueOf(i2));
        }
        textView.setTextColor(ContextCompat.getColor(context, R.color.pd_white));
        WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
        if ((wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.mad || TextUtils.isEmpty(wareYuYueInfo.madBtnText)) ? false : true) {
            textView.setBackgroundResource(R.drawable.libpdstyleinfoview_pd_mad_yuyue_btn_bg);
        } else {
            textView.setBackgroundResource(i3);
        }
    }

    private void setMadYuyueBtn(Context context, TextView textView, TextView textView2, ProductDetailEntity productDetailEntity, boolean z) {
        if (textView == null || textView2 == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(context.getString(R.string.libpdstyleinfoview_lib_pd_product_begin_to_order));
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append(productDetailEntity.mWareBusinessData.yuyueInfo.madBtnText);
        SpannableString spannableString = new SpannableString(sb);
        handleMadYuyueStyle(sb.toString(), spannableString);
        textView.setBackgroundResource(R.drawable.libpdstyleinfoview_lib_pd_mad_yuyue_btn_bg);
        textView.setVisibility(0);
        textView.setText(spannableString);
        textView.setEnabled(z);
        textView.setTag(70);
        textView2.setVisibility(8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.widget.TextView] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v8, types: [android.text.SpannableString, android.text.Spannable] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueDealUtil] */
    private void setYuYueBtn(Context context, TextView textView, TextView textView2, String str, boolean z, ProductDetailEntity productDetailEntity) {
        if (textView2 == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        String formatPrice = PDUtils.formatPrice(productDetailEntity.mWareBusinessData.yuyueInfo.appointPrice, StringUtil.no_price);
        sb.append(formatPrice);
        if (!TextUtils.equals(formatPrice, StringUtil.no_price)) {
            sb.insert(0, "\u00a5");
        }
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        SpannableString spannableString = new SpannableString("");
        if (TextUtils.equals(str, "2")) {
            sb.append(context.getString(R.string.libpdstyleinfoview_lib_pd_product_begin_to_order));
            spannableString = new SpannableString(sb);
            handleJoinBuyPriceStyle(context, sb.toString(), spannableString, "");
        } else if (TextUtils.equals(str, "1")) {
            sb.append(context.getString(R.string.libpdstyleinfoview_lib_pd_product_waiting_to_order));
            spannableString = new SpannableString(sb);
            handleJoinBuyPriceStyle(context, sb.toString(), spannableString, "");
            textView2.setEnabled(false);
        } else if (TextUtils.equals(str, "3")) {
            sb.append(context.getString(R.string.libpdstyleinfoview_lib_pd_product_waiting_to_buy));
            spannableString = new SpannableString(sb);
            handleJoinBuyPriceStyle(context, sb.toString(), spannableString, "");
            textView2.setEnabled(false);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\u00a5");
        sb2.append(productDetailEntity.getJdPrice());
        sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb2.append(context.getString(R.string.libpdstyleinfoview_now_buy));
        ?? spannableString2 = new SpannableString(sb2);
        handleJoinBuyPriceStyle(context, sb2.toString(), spannableString2, "");
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils = this.mUtils;
        boolean z2 = true;
        boolean z3 = (libPdStyleInfoViewUtils != null && libPdStyleInfoViewUtils.showLayerFromYuYue) || libPdStyleInfoViewUtils.showLayerFromBuyNow;
        if (z3) {
            spannableString2 = context.getString(R.string.libpdstyleinfoview_ok);
        }
        textView.setText(spannableString2);
        textView.setBackgroundResource(z3 ? this.mRedDrawable : this.mYellowDrawable);
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2 = this.mUtils;
        textView.setTag(Integer.valueOf((libPdStyleInfoViewUtils2 == null || !libPdStyleInfoViewUtils2.showLayerFromBuyNow) ? 70 : 2));
        textView.setVisibility(0);
        if (!z3 && !productDetailEntity.mWareBusinessData.supportSale) {
            z2 = false;
        }
        textView.setEnabled(z2);
        textView2.setText(spannableString);
        textView2.setBackgroundResource(this.mRedDrawable);
        textView2.setTag(11);
        textView2.setVisibility(z3 ? 8 : 0);
        if (TextUtils.equals(str, "2")) {
            textView2.setEnabled(z);
        }
    }

    private void setYuyueMaskBtn(Context context, TextView textView, TextView textView2, ProductDetailEntity productDetailEntity, int i2, boolean z, int i3, int i4) {
        if (textView == null || textView2 == null) {
            return;
        }
        textView.setText(context.getString(i2));
        textView.setEnabled(z);
        if (i3 != -1) {
            textView.setTag(Integer.valueOf(i3));
        }
        textView.setTextColor(ContextCompat.getColor(context, R.color.pd_white));
        textView.setBackgroundResource(i4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x023f, code lost:
        if (r0.yuyueInfo.pinSkuYuYue != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0235, code lost:
        if (r3.appointMoreTimeFlag != false) goto L99;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dealYuYue(Context context, TextView textView, TextView textView2, ProductDetailEntity productDetailEntity, PdYuYueHelper.DefalutButtonInfo defalutButtonInfo, LibPdStyleInfoViewUtils libPdStyleInfoViewUtils) {
        int i2;
        WareYuYueInfo wareYuYueInfo;
        this.mUtils = libPdStyleInfoViewUtils;
        if (productDetailEntity.isYuyue()) {
            WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
            boolean z = true;
            boolean z2 = (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isAdvanceBuy() || TextUtils.isEmpty(productDetailEntity.mWareBusinessData.yuyueInfo.appointPrice)) ? false : true;
            WareBusinessData wareBusinessData2 = productDetailEntity.mWareBusinessData;
            boolean z3 = wareBusinessData2 != null && wareBusinessData2.supportSale;
            String str = "isSupportAdvanceBuy = " + z2 + " tRightBtnEnable = " + z3 + " YuyueType = " + productDetailEntity.getYuyueType();
            String yuyueType = productDetailEntity.getYuyueType();
            yuyueType.hashCode();
            yuyueType.hashCode();
            char c2 = '\uffff';
            switch (yuyueType.hashCode()) {
                case 49:
                    if (yuyueType.equals("1")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 50:
                    if (yuyueType.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 51:
                    if (yuyueType.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 52:
                    if (yuyueType.equals("4")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 53:
                    if (yuyueType.equals("5")) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    if (z2) {
                        setYuYueBtn(context, textView, textView2, "1", z3, productDetailEntity);
                        break;
                    } else {
                        int i3 = R.string.libpdstyleinfoview_lib_pd_product_waiting_to_order;
                        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2 = this.mUtils;
                        if (libPdStyleInfoViewUtils2 != null && TextUtils.equals(libPdStyleInfoViewUtils2.mDialogInitSkuId, productDetailEntity.skuId)) {
                            i3 = R.string.libpdstyleinfoview_ok;
                        }
                        setCarBtnStatus(context, textView, textView2, productDetailEntity, context.getString(i3), false, -1, this.mRedDrawable);
                        textView2.setVisibility(8);
                        break;
                    }
                    break;
                case 1:
                    WareYuYueInfo wareYuYueInfo2 = productDetailEntity.mWareBusinessData.yuyueInfo;
                    if (wareYuYueInfo2.mad && !TextUtils.isEmpty(wareYuYueInfo2.madBtnText)) {
                        setMadYuyueBtn(context, textView, textView2, productDetailEntity, z3);
                        break;
                    } else if (z2) {
                        setYuYueBtn(context, textView, textView2, "2", z3, productDetailEntity);
                        break;
                    } else {
                        if (productDetailEntity.isPlusMakeTime()) {
                            WareBusinessData wareBusinessData3 = productDetailEntity.mWareBusinessData;
                            WareBusinessPlusForBuyMt wareBusinessPlusForBuyMt = wareBusinessData3.makeMoreTime;
                            if (wareBusinessPlusForBuyMt.makeMoreTimeFlag) {
                                if (!wareBusinessPlusForBuyMt.drawMoreTimeFlag) {
                                    break;
                                }
                                if (wareBusinessPlusForBuyMt.userFlag) {
                                    break;
                                }
                            }
                        }
                        z = false;
                        String str2 = "isAppoint is " + z;
                        if (z) {
                            i2 = R.string.libpdstyleinfoview_lib_pd_product_reserved;
                        } else {
                            i2 = R.string.libpdstyleinfoview_lib_pd_product_begin_to_order;
                        }
                        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils3 = this.mUtils;
                        if (libPdStyleInfoViewUtils3 != null && TextUtils.equals(libPdStyleInfoViewUtils3.mDialogInitSkuId, productDetailEntity.skuId)) {
                            i2 = R.string.libpdstyleinfoview_ok;
                        }
                        setCarBtnStatus(context, textView, textView2, productDetailEntity, context.getString(i2), z3, 11, this.mRedDrawable);
                        textView.setTag(70);
                        textView2.setVisibility(8);
                        break;
                    }
                    break;
                case 2:
                    if (z2) {
                        setYuYueBtn(context, textView, textView2, "3", z3, productDetailEntity);
                        break;
                    } else {
                        int i4 = R.string.libpdstyleinfoview_lib_pd_product_waiting_to_buy;
                        if (productDetailEntity.isYuyueMask()) {
                            i4 = R.string.libpdstyleinfoview_pd_yuyue_mask_draw;
                        }
                        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils4 = this.mUtils;
                        if (libPdStyleInfoViewUtils4 != null && TextUtils.equals(libPdStyleInfoViewUtils4.mDialogInitSkuId, productDetailEntity.skuId)) {
                            i4 = R.string.libpdstyleinfoview_ok;
                        }
                        setCarBtnStatus(context, textView, textView2, productDetailEntity, context.getString(i4), false, -1, this.mRedDrawable);
                        textView2.setVisibility(8);
                        break;
                    }
                    break;
                case 3:
                    String str3 = "yuyueInfo.type is " + productDetailEntity.mWareBusinessData.yuyueInfo.type;
                    String str4 = "product.getPropertyType() is " + productDetailEntity.getPropertyType();
                    int i5 = R.string.libpdstyleinfoview_lib_pd_product_buy_now;
                    if (!isYuyueMask(context, textView, textView2, productDetailEntity, i5)) {
                        if ("1".equals(productDetailEntity.getPropertyType())) {
                            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils5 = this.mUtils;
                            if (libPdStyleInfoViewUtils5 != null && TextUtils.equals(libPdStyleInfoViewUtils5.mDialogInitSkuId, productDetailEntity.skuId)) {
                                i5 = R.string.libpdstyleinfoview_ok;
                            }
                            setCarBtnStatus(context, textView, textView2, productDetailEntity, context.getString(i5), productDetailEntity.mWareBusinessData.supportSale, 12, this.mRedDrawable);
                            textView2.setVisibility(8);
                            textView.setTag(70);
                            break;
                        } else if (defalutButtonInfo == null || !defalutButtonInfo.apply()) {
                            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils6 = this.mUtils;
                            if (libPdStyleInfoViewUtils6 != null && TextUtils.equals(libPdStyleInfoViewUtils6.mDialogInitSkuId, productDetailEntity.skuId)) {
                                i5 = R.string.libpdstyleinfoview_ok;
                            }
                            setCarBtnStatus(context, textView, textView2, productDetailEntity, context.getString(i5), productDetailEntity.mWareBusinessData.supportSale, 12, this.mRedDrawable);
                            textView2.setVisibility(8);
                            textView.setTag(70);
                            break;
                        }
                    }
                    break;
                case 4:
                    int i6 = R.string.libpdstyleinfoview_lib_pd_product_end_to_buy;
                    LibPdStyleInfoViewUtils libPdStyleInfoViewUtils7 = this.mUtils;
                    if (libPdStyleInfoViewUtils7 != null && TextUtils.equals(libPdStyleInfoViewUtils7.mDialogInitSkuId, productDetailEntity.skuId)) {
                        i6 = R.string.libpdstyleinfoview_ok;
                    }
                    setCarBtnStatus(context, textView, textView2, productDetailEntity, context.getString(i6), false, -1, this.mRedDrawable);
                    textView2.setVisibility(8);
                    break;
            }
        }
        return productDetailEntity.isYuyue();
    }
}
