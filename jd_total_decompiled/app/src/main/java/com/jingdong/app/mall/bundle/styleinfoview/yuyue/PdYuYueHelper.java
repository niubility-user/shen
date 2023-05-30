package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomLeftButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomRightButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusForBuyMt;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.jdsdk.res.StringUtil;

/* loaded from: classes3.dex */
public class PdYuYueHelper {

    /* loaded from: classes3.dex */
    public interface DefalutButtonInfo {
        boolean apply();
    }

    private void showAdvanceBuyButton(Context context, PdBottomButtonInfo pdBottomButtonInfo, String str, boolean z, ProductDetailEntity productDetailEntity) {
        pdBottomButtonInfo.rightButtonInfo.buttonText = "\u00a5" + PDUtils.formatPrice(productDetailEntity.mWareBusinessData.yuyueInfo.appointPrice, StringUtil.no_price);
        if (TextUtils.equals(str, "2")) {
            pdBottomButtonInfo.rightButtonInfo.buttonSubText = context.getString(R.string.libpdstyleinfoview_lib_pd_product_begin_to_order);
            PdBottomRightButtonInfo pdBottomRightButtonInfo = pdBottomButtonInfo.rightButtonInfo;
            pdBottomRightButtonInfo.buttonEnable = z;
            pdBottomRightButtonInfo.buttonEvent = 11;
        } else if (TextUtils.equals(str, "1")) {
            pdBottomButtonInfo.rightButtonInfo.buttonSubText = context.getString(R.string.libpdstyleinfoview_lib_pd_product_waiting_to_order);
            PdBottomRightButtonInfo pdBottomRightButtonInfo2 = pdBottomButtonInfo.rightButtonInfo;
            pdBottomRightButtonInfo2.buttonEnable = false;
            pdBottomRightButtonInfo2.buttonEvent = -1;
        } else if (TextUtils.equals(str, "3")) {
            pdBottomButtonInfo.rightButtonInfo.buttonSubText = context.getString(R.string.libpdstyleinfoview_lib_pd_product_waiting_to_buy);
            PdBottomRightButtonInfo pdBottomRightButtonInfo3 = pdBottomButtonInfo.rightButtonInfo;
            pdBottomRightButtonInfo3.buttonEnable = false;
            pdBottomRightButtonInfo3.buttonEvent = -1;
        }
        pdBottomButtonInfo.leftButtonInfo.buttonText = "\u00a5" + productDetailEntity.getJdPrice();
        pdBottomButtonInfo.leftButtonInfo.buttonSubText = context.getString(R.string.libpdstyleinfoview_pd_now_buy);
        PdBottomLeftButtonInfo pdBottomLeftButtonInfo = pdBottomButtonInfo.leftButtonInfo;
        pdBottomLeftButtonInfo.buttonEvent = 2;
        pdBottomLeftButtonInfo.buttonEnable = productDetailEntity.isCanBuy();
    }

    private void showYuyueButton(PdBottomButtonInfo pdBottomButtonInfo, String str, boolean z, int i2, ProductDetailEntity productDetailEntity) {
        PdBottomLeftButtonInfo pdBottomLeftButtonInfo = pdBottomButtonInfo.leftButtonInfo;
        pdBottomLeftButtonInfo.buttonEnable = z;
        pdBottomLeftButtonInfo.buttonText = str;
        if (productDetailEntity.getAddCartButtonMultiRow() && !TextUtils.isEmpty(productDetailEntity.getAddCartButtonContent())) {
            pdBottomButtonInfo.leftButtonInfo.buttonSubText = productDetailEntity.getAddCartButtonContent();
        }
        if (i2 != -1) {
            pdBottomButtonInfo.leftButtonInfo.buttonEvent = i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean showYuyueDrawButton(Context context, PdBottomButtonInfo pdBottomButtonInfo, int i2, ProductDetailEntity productDetailEntity) {
        WareYuYueInfo wareYuYueInfo = productDetailEntity.mWareBusinessData.yuyueInfo;
        int i3 = 2;
        boolean z = false;
        if (wareYuYueInfo.showDraw) {
            if (TextUtils.equals(wareYuYueInfo.drawEnum, "1")) {
                z = productDetailEntity.mWareBusinessData.supportSale;
            } else if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.drawEnum, "3")) {
                i2 = R.string.libpdstyleinfoview_pd_yuyue_mask_no_hit;
            } else if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.drawEnum, "2")) {
                i2 = R.string.libpdstyleinfoview_pd_yuyue_mask_no_yuyue;
            } else if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.drawEnum, "4")) {
                i2 = R.string.libpdstyleinfoview_pd_yuyue_swaying_buy;
            }
            pdBottomButtonInfo.leftButtonInfo.buttonText = context.getString(i2);
            PdBottomLeftButtonInfo pdBottomLeftButtonInfo = pdBottomButtonInfo.leftButtonInfo;
            pdBottomLeftButtonInfo.buttonEnable = z;
            if (i3 == -1) {
                pdBottomLeftButtonInfo.buttonEvent = i3;
                return true;
            }
            return true;
        } else if (productDetailEntity.isYuyueMask()) {
            i2 = R.string.libpdstyleinfoview_pd_yuyue_mask_draw;
        }
        i3 = -1;
        pdBottomButtonInfo.leftButtonInfo.buttonText = context.getString(i2);
        PdBottomLeftButtonInfo pdBottomLeftButtonInfo2 = pdBottomButtonInfo.leftButtonInfo;
        pdBottomLeftButtonInfo2.buttonEnable = z;
        if (i3 == -1) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x01b9, code lost:
        if (r0.appointMoreTimeFlag != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01c3, code lost:
        if (r15.yuyueInfo.pinSkuYuYue != false) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean generatorYuYueBtn(Context context, PdBottomButtonInfo pdBottomButtonInfo, ProductDetailEntity productDetailEntity, DefalutButtonInfo defalutButtonInfo) {
        int parseInt;
        int i2;
        if (productDetailEntity.isYuyue()) {
            boolean z = true;
            boolean z2 = productDetailEntity.mWareBusinessData.yuyueInfo.isAdvanceBuy() && !TextUtils.isEmpty(productDetailEntity.mWareBusinessData.yuyueInfo.appointPrice);
            WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
            boolean z3 = wareBusinessData != null && wareBusinessData.supportSale;
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
            int i3 = Integer.MAX_VALUE;
            try {
                switch (c2) {
                    case 0:
                        if (z2) {
                            showAdvanceBuyButton(context, pdBottomButtonInfo, "1", z3, productDetailEntity);
                        } else {
                            showYuyueButton(pdBottomButtonInfo, context.getString(R.string.libpdstyleinfoview_lib_pd_product_waiting_to_order), false, -1, productDetailEntity);
                            pdBottomButtonInfo.rightButtonInfo = null;
                        }
                        parseInt = Integer.parseInt(productDetailEntity.mWareBusinessData.yuyueInfo.yuYueStartTime);
                        i3 = parseInt;
                        break;
                    case 1:
                        WareYuYueInfo wareYuYueInfo = productDetailEntity.mWareBusinessData.yuyueInfo;
                        if (wareYuYueInfo.mad && !TextUtils.isEmpty(wareYuYueInfo.madBtnText)) {
                            pdBottomButtonInfo.leftButtonInfo.buttonText = context.getString(R.string.libpdstyleinfoview_lib_pd_product_begin_to_order);
                            PdBottomLeftButtonInfo pdBottomLeftButtonInfo = pdBottomButtonInfo.leftButtonInfo;
                            WareBusinessData wareBusinessData2 = productDetailEntity.mWareBusinessData;
                            pdBottomLeftButtonInfo.buttonSubText = wareBusinessData2.yuyueInfo.madBtnText;
                            pdBottomLeftButtonInfo.buttonEnable = wareBusinessData2.supportSale;
                            pdBottomLeftButtonInfo.buttonEvent = 11;
                            pdBottomButtonInfo.rightButtonInfo = null;
                        } else if (z2) {
                            showAdvanceBuyButton(context, pdBottomButtonInfo, "2", z3, productDetailEntity);
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
                            showYuyueButton(pdBottomButtonInfo, context.getString(i2), z3, 11, productDetailEntity);
                            pdBottomButtonInfo.rightButtonInfo = null;
                        }
                        parseInt = Integer.parseInt(productDetailEntity.mWareBusinessData.yuyueInfo.yuYueEndTime);
                        i3 = parseInt;
                        break;
                    case 2:
                        if (z2) {
                            showAdvanceBuyButton(context, pdBottomButtonInfo, "3", z3, productDetailEntity);
                        } else {
                            int i4 = R.string.libpdstyleinfoview_lib_pd_product_waiting_to_buy;
                            if (productDetailEntity.isYuyueMask()) {
                                i4 = R.string.libpdstyleinfoview_pd_yuyue_mask_draw;
                            }
                            showYuyueButton(pdBottomButtonInfo, context.getString(i4), false, -1, productDetailEntity);
                            pdBottomButtonInfo.rightButtonInfo = null;
                        }
                        parseInt = Integer.parseInt(productDetailEntity.mWareBusinessData.yuyueInfo.buyStartTime);
                        i3 = parseInt;
                        break;
                    case 3:
                        String str3 = "yuyueInfo.type is " + productDetailEntity.mWareBusinessData.yuyueInfo.type;
                        String str4 = "product.getPropertyType() is " + productDetailEntity.getPropertyType();
                        if (TextUtils.equals(productDetailEntity.mWareBusinessData.yuyueInfo.type, "5")) {
                            showYuyueDrawButton(context, pdBottomButtonInfo, R.string.libpdstyleinfoview_lib_pd_product_buy_now, productDetailEntity);
                            pdBottomButtonInfo.rightButtonInfo = null;
                        } else if ("1".equals(productDetailEntity.getPropertyType())) {
                            showYuyueButton(pdBottomButtonInfo, context.getString(R.string.libpdstyleinfoview_lib_pd_product_buy_now), productDetailEntity.mWareBusinessData.supportSale, 12, productDetailEntity);
                            pdBottomButtonInfo.rightButtonInfo = null;
                        } else if (defalutButtonInfo == null || !defalutButtonInfo.apply()) {
                            showYuyueButton(pdBottomButtonInfo, context.getString(R.string.libpdstyleinfoview_lib_pd_product_buy_now), productDetailEntity.mWareBusinessData.supportSale, 12, productDetailEntity);
                            pdBottomButtonInfo.rightButtonInfo = null;
                        }
                        parseInt = Integer.parseInt(productDetailEntity.mWareBusinessData.yuyueInfo.buyEndTime);
                        i3 = parseInt;
                        break;
                    case 4:
                        showYuyueButton(pdBottomButtonInfo, context.getString(R.string.libpdstyleinfoview_lib_pd_product_end_to_buy), false, -1, productDetailEntity);
                        pdBottomButtonInfo.rightButtonInfo = null;
                        break;
                }
            } catch (Exception unused) {
            }
            pdBottomButtonInfo.countTime = i3 + "";
            String str5 = "countTime is " + pdBottomButtonInfo.countTime;
            String str6 = "LeftButton is " + pdBottomButtonInfo.leftButtonInfo;
            String str7 = "RightButton is " + pdBottomButtonInfo.rightButtonInfo;
        }
        return productDetailEntity.isYuyue();
    }
}
