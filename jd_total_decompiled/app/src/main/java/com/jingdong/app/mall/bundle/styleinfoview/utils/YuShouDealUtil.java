package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import androidx.annotation.DrawableRes;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnListener;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomLeftButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomRightButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessDreamPurchase;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuShouInfo;

/* loaded from: classes3.dex */
public class YuShouDealUtil {
    private boolean isUseInStyleView;
    private LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
    private LibPdStyleInfoViewBottomBtnListener mBottomListener;
    private PdBottomButtonInfo mButtonInfo;
    private Context mContext;
    private boolean mHaveCouponId;
    private ProductDetailEntity product;
    private long startTime;
    @DrawableRes
    private int mRedDrawable = R.drawable.lib_pd_add2car_bg;
    @DrawableRes
    private int mJxYellowDrawable = R.drawable.libpdstyleinfoview_bottom_btn_jx_selector;
    @DrawableRes
    private int mYellowDrawable = R.drawable.libpdstyleinfoview_same_bg;

    public YuShouDealUtil(ProductDetailEntity productDetailEntity, Context context, PdBottomButtonInfo pdBottomButtonInfo, boolean z, long j2, LibPdStyleInfoViewUtils libPdStyleInfoViewUtils, LibPdStyleInfoViewBottomBtnListener libPdStyleInfoViewBottomBtnListener, boolean z2) {
        this.mBottomListener = libPdStyleInfoViewBottomBtnListener;
        this.product = productDetailEntity;
        this.mContext = context;
        this.mButtonInfo = pdBottomButtonInfo;
        this.isUseInStyleView = z;
        this.startTime = j2;
        this.libPdStyleInfoViewUtils = libPdStyleInfoViewUtils;
        this.mHaveCouponId = z2;
    }

    private boolean checkNeedRefresh(String str) {
        try {
            long parseLong = Long.parseLong(this.product.mWareBusinessData.YuShouInfo.countdownNumMills);
            if (TextUtils.equals(str, "0") || TextUtils.equals(str, "1")) {
                return parseLong <= this.startTime;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isCanBuy() {
        return this.product.isCanBuy();
    }

    private void shouldRefreshData(String str, boolean z) {
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        if (!checkNeedRefresh(str) || (libPdStyleInfoViewUtils = this.libPdStyleInfoViewUtils) == null) {
            return;
        }
        PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
        if (pdBottomButtonInfo != null) {
            pdBottomButtonInfo.isRereshCountTime = true;
            pdBottomButtonInfo.mNeedRefresh = true;
        }
        libPdStyleInfoViewUtils.refreshData(this.startTime, this.mBottomListener, z);
    }

    public boolean dealYuShouAdvanceBuy() {
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        String str;
        String str2;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils3;
        String str3;
        if (this.product.isYuShou() && this.product.isYuShouAdvanceBuy()) {
            PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
            ProductDetailEntity productDetailEntity = this.product;
            pdBottomButtonInfo.countTime = productDetailEntity.mWareBusinessData.YuShouInfo.countdownNumMills;
            shouldRefreshData(productDetailEntity.getYuShouType(), this.mHaveCouponId);
            PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
            if (pdBottomButtonInfo2.leftButtonInfo == null) {
                pdBottomButtonInfo2.leftButtonInfo = new PdBottomLeftButtonInfo();
            }
            this.mButtonInfo.leftButtonInfo.buttonEvent = 3;
            setCarStatus();
            String yuShouType = this.product.getYuShouType();
            yuShouType.hashCode();
            if (yuShouType.equals("0")) {
                this.mButtonInfo.leftButtonInfo.buttonEnable = false;
                if (this.isUseInStyleView && (libPdStyleInfoViewUtils = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils.mDialogInitSkuId, this.product.skuId)) {
                    this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                } else {
                    this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_yushou_not_start_1);
                }
                this.mButtonInfo.rightButtonInfo = null;
                return true;
            } else if (yuShouType.equals("1")) {
                StringBuilder sb = new StringBuilder();
                Context context = this.mContext;
                int i2 = R.string.libpdstyleinfoview_yushou_start_buy;
                sb.append(context.getString(i2));
                String yuShouNumOfPeople = this.product.getYuShouNumOfPeople();
                if (TextUtils.isEmpty(yuShouNumOfPeople)) {
                    str = null;
                } else {
                    str = PDUtils.formatInt(PDUtils.stringToInt(yuShouNumOfPeople));
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    sb.append(str);
                    sb.append(this.mContext.getString(R.string.libpdstyleinfoview_yushou_num3));
                }
                PDUtils.handleYuShouNum(sb.toString(), new SpannableString(sb));
                PdBottomButtonInfo pdBottomButtonInfo3 = this.mButtonInfo;
                if (pdBottomButtonInfo3.rightButtonInfo == null) {
                    pdBottomButtonInfo3.rightButtonInfo = new PdBottomRightButtonInfo();
                }
                if (this.isUseInStyleView) {
                    LibPdStyleInfoViewUtils libPdStyleInfoViewUtils4 = this.libPdStyleInfoViewUtils;
                    if (libPdStyleInfoViewUtils4 != null && TextUtils.equals(libPdStyleInfoViewUtils4.mDialogInitSkuId, this.product.skuId)) {
                        this.mButtonInfo.rightButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                        this.mButtonInfo.rightButtonInfo.buttonEvent = 70;
                    } else {
                        PdBottomRightButtonInfo pdBottomRightButtonInfo = this.mButtonInfo.rightButtonInfo;
                        pdBottomRightButtonInfo.buttonEvent = 70;
                        pdBottomRightButtonInfo.buttonText = this.mContext.getString(i2);
                        PdBottomRightButtonInfo pdBottomRightButtonInfo2 = this.mButtonInfo.rightButtonInfo;
                        if (TextUtils.isEmpty(str)) {
                            str3 = null;
                        } else {
                            str3 = str + this.mContext.getString(R.string.libpdstyleinfoview_yushou_num3);
                        }
                        pdBottomRightButtonInfo2.buttonSubText = str3;
                    }
                } else {
                    PdBottomRightButtonInfo pdBottomRightButtonInfo3 = this.mButtonInfo.rightButtonInfo;
                    pdBottomRightButtonInfo3.buttonEvent = 3;
                    pdBottomRightButtonInfo3.buttonText = this.mContext.getString(i2);
                    PdBottomRightButtonInfo pdBottomRightButtonInfo4 = this.mButtonInfo.rightButtonInfo;
                    if (TextUtils.isEmpty(str)) {
                        str2 = null;
                    } else {
                        str2 = str + this.mContext.getString(R.string.libpdstyleinfoview_yushou_num3);
                    }
                    pdBottomRightButtonInfo4.buttonSubText = str2;
                }
                this.mButtonInfo.rightButtonInfo.backgroundResource = this.mYellowDrawable;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.product.getAdvanceBuyTitle());
                sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                sb2.append(this.product.getAdvanceSubTitle());
                PDUtils.handleYuShouNum(sb2.toString(), new SpannableString(sb2));
                if (this.isUseInStyleView && (libPdStyleInfoViewUtils3 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils3.mDialogInitSkuId, this.product.skuId)) {
                    this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                } else {
                    this.mButtonInfo.leftButtonInfo.buttonEnable = isCanBuy();
                    this.mButtonInfo.leftButtonInfo.buttonText = this.product.getAdvanceBuyTitle();
                    this.mButtonInfo.leftButtonInfo.buttonSubText = this.product.getAdvanceSubTitle();
                }
                PdBottomLeftButtonInfo pdBottomLeftButtonInfo = this.mButtonInfo.leftButtonInfo;
                pdBottomLeftButtonInfo.backgroundResource = this.mRedDrawable;
                pdBottomLeftButtonInfo.buttonEvent = 52;
                if (this.isUseInStyleView && (libPdStyleInfoViewUtils2 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils2.mDialogInitSkuId, this.product.skuId)) {
                    this.mButtonInfo.rightButtonInfo = null;
                    return true;
                }
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0062, code lost:
        if (r0.equals("2") == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01a0, code lost:
        if (r0.equals("2") == false) goto L61;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dealYushou() {
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        String str;
        String str2;
        String str3;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils3;
        String str4;
        String str5;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils4;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils5;
        boolean z = false;
        if (this.product.isYuShou()) {
            PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
            ProductDetailEntity productDetailEntity = this.product;
            pdBottomButtonInfo.countTime = productDetailEntity.mWareBusinessData.YuShouInfo.countdownNumMills;
            shouldRefreshData(productDetailEntity.getYuShouType(), this.mHaveCouponId);
            PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
            if (pdBottomButtonInfo2.leftButtonInfo == null) {
                pdBottomButtonInfo2.leftButtonInfo = new PdBottomLeftButtonInfo();
            }
            this.mButtonInfo.leftButtonInfo.buttonEvent = 3;
            setCarStatus();
            char c2 = 2;
            if (this.product.isFullPriceYuShou()) {
                String yuShouType = this.product.getYuShouType();
                yuShouType.hashCode();
                yuShouType.hashCode();
                switch (yuShouType.hashCode()) {
                    case 48:
                        if (yuShouType.equals("0")) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 49:
                        if (yuShouType.equals("1")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 50:
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        if (this.isUseInStyleView && (libPdStyleInfoViewUtils3 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils3.mDialogInitSkuId, this.product.skuId)) {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                        } else {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_yushou_not_start_1);
                        }
                        this.mButtonInfo.leftButtonInfo.buttonEnable = false;
                        break;
                    case 1:
                        StringBuilder sb = new StringBuilder();
                        Context context = this.mContext;
                        int i2 = R.string.libpdstyleinfoview_product_buy_now;
                        sb.append(context.getString(i2));
                        String yuShouNumOfPeople = this.product.getYuShouNumOfPeople();
                        if (TextUtils.isEmpty(yuShouNumOfPeople)) {
                            str4 = null;
                        } else {
                            str4 = PDUtils.formatInt(PDUtils.stringToInt(yuShouNumOfPeople));
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            sb.append(str4);
                            sb.append(this.mContext.getString(R.string.libpdstyleinfoview_yushou_num4));
                        }
                        PDUtils.handleYuShouNum(sb.toString(), new SpannableString(sb));
                        if (this.isUseInStyleView && (libPdStyleInfoViewUtils4 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils4.mDialogInitSkuId, this.product.skuId)) {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                            break;
                        } else {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(i2);
                            PdBottomLeftButtonInfo pdBottomLeftButtonInfo = this.mButtonInfo.leftButtonInfo;
                            if (TextUtils.isEmpty(str4)) {
                                str5 = null;
                            } else {
                                str5 = str4 + this.mContext.getString(R.string.libpdstyleinfoview_yushou_num4);
                            }
                            pdBottomLeftButtonInfo.buttonSubText = str5;
                            break;
                        }
                    case 2:
                        if (this.isUseInStyleView && (libPdStyleInfoViewUtils5 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils5.mDialogInitSkuId, this.product.skuId)) {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                        } else {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_add2car);
                        }
                        this.mButtonInfo.leftButtonInfo.buttonEnable = false;
                        break;
                }
            } else {
                String yuShouType2 = this.product.getYuShouType();
                yuShouType2.hashCode();
                yuShouType2.hashCode();
                switch (yuShouType2.hashCode()) {
                    case 48:
                        if (yuShouType2.equals("0")) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 49:
                        if (yuShouType2.equals("1")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 50:
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        if (this.isUseInStyleView && (libPdStyleInfoViewUtils = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils.mDialogInitSkuId, this.product.skuId)) {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                        } else {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_yushou_not_start_1);
                        }
                        this.mButtonInfo.leftButtonInfo.buttonEnable = false;
                        break;
                    case 1:
                        StringBuilder sb2 = new StringBuilder();
                        Context context2 = this.mContext;
                        int i3 = R.string.libpdstyleinfoview_yushou_start_buy;
                        sb2.append(context2.getString(i3));
                        String yuShouNumOfPeople2 = this.product.getYuShouNumOfPeople();
                        if (TextUtils.isEmpty(yuShouNumOfPeople2)) {
                            str = null;
                        } else {
                            str = PDUtils.formatInt(PDUtils.stringToInt(yuShouNumOfPeople2));
                            sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            sb2.append(str);
                            sb2.append(this.mContext.getString(R.string.libpdstyleinfoview_yushou_num3));
                        }
                        PDUtils.handleYuShouNum(sb2.toString(), new SpannableString(sb2));
                        if (this.isUseInStyleView) {
                            this.mButtonInfo.leftButtonInfo.buttonEvent = 70;
                            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils6 = this.libPdStyleInfoViewUtils;
                            if (libPdStyleInfoViewUtils6 != null && TextUtils.equals(libPdStyleInfoViewUtils6.mDialogInitSkuId, this.product.skuId)) {
                                this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                            } else {
                                this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(i3);
                                PdBottomLeftButtonInfo pdBottomLeftButtonInfo2 = this.mButtonInfo.leftButtonInfo;
                                if (TextUtils.isEmpty(str)) {
                                    str3 = null;
                                } else {
                                    str3 = str + this.mContext.getString(R.string.libpdstyleinfoview_yushou_num3);
                                }
                                pdBottomLeftButtonInfo2.buttonSubText = str3;
                            }
                        } else {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(i3);
                            PdBottomLeftButtonInfo pdBottomLeftButtonInfo3 = this.mButtonInfo.leftButtonInfo;
                            if (TextUtils.isEmpty(str)) {
                                str2 = null;
                            } else {
                                str2 = str + this.mContext.getString(R.string.libpdstyleinfoview_yushou_num3);
                            }
                            pdBottomLeftButtonInfo3.buttonSubText = str2;
                        }
                        if (TextUtils.equals(this.product.getYuShouladder(), "2")) {
                            z = true;
                            break;
                        }
                        break;
                    case 2:
                        this.mButtonInfo.leftButtonInfo.buttonEnable = false;
                        if (this.isUseInStyleView && (libPdStyleInfoViewUtils2 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils2.mDialogInitSkuId, this.product.skuId)) {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                            break;
                        } else {
                            this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_add2car);
                            break;
                        }
                        break;
                }
            }
            if (this.product.isMergedBuy() && z) {
                isCanBuy();
            }
            this.mButtonInfo.rightButtonInfo = null;
            return true;
        }
        return false;
    }

    public boolean dealYushouDreamBuy() {
        WareYuShouInfo wareYuShouInfo;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils3;
        WareBusinessData wareBusinessData = this.product.mWareBusinessData;
        if (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || !wareYuShouInfo.isDreamBuy()) {
            return false;
        }
        PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
        pdBottomButtonInfo.countTime = this.product.mWareBusinessData.YuShouInfo.countdownNumMills;
        if (pdBottomButtonInfo.leftButtonInfo == null) {
            pdBottomButtonInfo.leftButtonInfo = new PdBottomLeftButtonInfo();
        }
        WareYuShouInfo wareYuShouInfo2 = this.product.mWareBusinessData.YuShouInfo;
        this.mButtonInfo.leftButtonInfo.buttonEvent = 26;
        shouldRefreshData(wareYuShouInfo2.yuShouType, this.mHaveCouponId);
        String str = wareYuShouInfo2.yuShouType;
        str.hashCode();
        if (!str.equals("0")) {
            if (isCanBuy()) {
                this.mButtonInfo.leftButtonInfo.buttonEnable = true;
                if (this.isUseInStyleView && (libPdStyleInfoViewUtils3 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils3.mDialogInitSkuId, this.product.skuId)) {
                    this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                } else {
                    WareBusinessDreamPurchase wareBusinessDreamPurchase = wareYuShouInfo2.dreamPurchase;
                    if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.buyButton)) {
                        this.mButtonInfo.leftButtonInfo.buttonText = wareYuShouInfo2.dreamPurchase.buyButton;
                    } else {
                        this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_product_buy_now);
                    }
                }
            } else {
                this.mButtonInfo.leftButtonInfo.buttonEnable = false;
                if (this.isUseInStyleView && (libPdStyleInfoViewUtils2 = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils2.mDialogInitSkuId, this.product.skuId)) {
                    this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
                } else {
                    WareBusinessDreamPurchase wareBusinessDreamPurchase2 = wareYuShouInfo2.dreamPurchase;
                    if (wareBusinessDreamPurchase2 != null && !TextUtils.isEmpty(wareBusinessDreamPurchase2.buyButton)) {
                        this.mButtonInfo.leftButtonInfo.buttonText = wareYuShouInfo2.dreamPurchase.buyButton;
                    } else {
                        this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_product_buy_now);
                    }
                }
            }
        } else {
            this.mButtonInfo.leftButtonInfo.buttonEnable = false;
            if (this.isUseInStyleView && (libPdStyleInfoViewUtils = this.libPdStyleInfoViewUtils) != null && TextUtils.equals(libPdStyleInfoViewUtils.mDialogInitSkuId, this.product.skuId)) {
                this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_ok);
            } else {
                WareBusinessDreamPurchase wareBusinessDreamPurchase3 = wareYuShouInfo2.dreamPurchase;
                if (wareBusinessDreamPurchase3 != null && !TextUtils.isEmpty(wareBusinessDreamPurchase3.buyButton)) {
                    this.mButtonInfo.leftButtonInfo.buttonText = wareYuShouInfo2.dreamPurchase.buyButton;
                } else {
                    this.mButtonInfo.leftButtonInfo.buttonText = this.mContext.getString(R.string.libpdstyleinfoview_yushou_not_start_1);
                }
            }
        }
        this.mButtonInfo.rightButtonInfo = null;
        return true;
    }

    void setCarStatus() {
        if (isCanBuy()) {
            PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
            PdBottomLeftButtonInfo pdBottomLeftButtonInfo = pdBottomButtonInfo.leftButtonInfo;
            if (pdBottomLeftButtonInfo != null) {
                pdBottomLeftButtonInfo.buttonEnable = true;
            }
            PdBottomRightButtonInfo pdBottomRightButtonInfo = pdBottomButtonInfo.rightButtonInfo;
            if (pdBottomRightButtonInfo != null) {
                pdBottomRightButtonInfo.buttonEnable = true;
                return;
            }
            return;
        }
        PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
        PdBottomLeftButtonInfo pdBottomLeftButtonInfo2 = pdBottomButtonInfo2.leftButtonInfo;
        if (pdBottomLeftButtonInfo2 != null) {
            pdBottomLeftButtonInfo2.buttonEnable = false;
        }
        PdBottomRightButtonInfo pdBottomRightButtonInfo2 = pdBottomButtonInfo2.rightButtonInfo;
        if (pdBottomRightButtonInfo2 != null) {
            pdBottomRightButtonInfo2.buttonEnable = false;
        }
    }
}
