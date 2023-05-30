package com.jingdong.app.mall.bundle.styleinfoview.holder;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnClickListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCarListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCouponReceiveListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomLeftButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomRightButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDIsAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDLVEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDOperAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDVerificationEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PdToHandPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.buttoninfo.PDBottomBtn;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponCellEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponReceiveEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.LocShopInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.TreayNewInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFeeInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJdServerProduct;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusForBuyMt;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuShouInfo;
import com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder;
import com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDCouponReceiveProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.utils.OpenAppUtils;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDBaseDeepLinkHelper;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.mall.bundle.styleinfoview.utils.YuShouDealUtil;
import com.jingdong.app.mall.bundle.styleinfoview.views.CustomTypefaceSpan;
import com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog;
import com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueDealUtil;
import com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.entity.cart.NewGiftItem;
import com.jingdong.common.entity.cart.SubmitOrderProductInfo;
import com.jingdong.common.entity.settlement.FillOrder;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class LibPdBottomBtnHolder {
    private static final String BASIC_TYPE_1 = "1";
    private static final String BASIC_TYPE_2 = "2";
    public static final int LENGTH_2 = 2;
    private static final String PRIMARYCHECKOUT = "primaryCheckout";
    private static final String REQUEST_TAG = "BottomHolder";
    private static final int TYPE_BTN_MAIN = 0;
    private static final int TYPE_BTN_SECOND = 1;
    public static final String TYPE_HYJ_NEW = "contractPhone";
    private static final String VIRTUAL_CARD = "virtualCard";
    private static final String YUYUE_SERVICE_WISHPLAT = "wishPlat";
    private int bottomBtnType;
    private LibPdStyleInfoViewCouponReceiveListener couponReceiveListener;
    private YuShouDealUtil dealUtil;
    private boolean isJx;
    public boolean isStyleLayerShow;
    public boolean isYushouStyleOpen;
    private LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener;
    private Activity mActivity;
    private LibPdStyleInfoViewBottomBtnClickListener mBottomBtnClickListener;
    private LibPdStyleInfoViewBottomBtnListener mBottomListener;
    private PdBottomButtonInfo mButtonInfo;
    private LibPdStyleInfoViewCarListener mCarListener;
    private WareBusinessFeeInfo mCurFeeInfo;
    private TreayNewInfo mCurTreayNewInfo;
    private boolean mHaveCouponId;
    private IMyActivity mIMyActivity;
    private WareBusinessJdServerProduct mJdServerProduct;
    @DrawableRes
    private int mJxYellowDrawable;
    private TextView mLeftBtn;
    private LibPdStyleInfoViewUtils mLibPdStyleInfoViewUtils;
    OnCarClickListener mOnCarClickListener;
    private LibPdBottomBtnPresenter mPresenter;
    @DrawableRes
    private int mRedDrawable;
    private TextView mRightBtn;
    private long mStartTime;
    public PDYuYueVerifyDialog mVerifyDialog;
    @DrawableRes
    private int mYellowDrawable;
    private ProductDetailEntity product;
    public boolean showLayerFromAddCart;
    public boolean showLayerFromSpecial;
    private SpannableString yuShouAdvanceSpan;
    private SpannableString yuShouNumspan;

    /* renamed from: com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder$1 */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 implements LibPdStyleInfoViewYuYueListener {
        AnonymousClass1() {
            LibPdBottomBtnHolder.this = r1;
        }

        /* renamed from: a */
        public /* synthetic */ void b(PDOperAppointEntity pDOperAppointEntity) {
            if (pDOperAppointEntity == null || LibPdBottomBtnHolder.this.product == null) {
                return;
            }
            String str = "onAppointted mVerifyDialog = " + LibPdBottomBtnHolder.this.mVerifyDialog;
            if (LibPdBottomBtnHolder.this.mActivity == null || LibPdBottomBtnHolder.this.mActivity.isFinishing()) {
                return;
            }
            boolean z = pDOperAppointEntity.appSuccess;
            boolean z2 = false;
            if (z && pDOperAppointEntity.flag) {
                if (LibPdBottomBtnHolder.this.product.isYuyueAutoAddCart()) {
                    LibPdBottomBtnHolder.this.mPresenter.onCarClick(true, pDOperAppointEntity, LibPdBottomBtnHolder.this.mVerifyDialog);
                    return;
                }
                if (((LibPdBottomBtnHolder.this.product == null || LibPdBottomBtnHolder.this.product.mWareBusinessData == null || LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo == null || !LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.mad) ? false : true) && LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null) {
                    LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.dismiss();
                }
                PDYuYueVerifyDialog pDYuYueVerifyDialog = LibPdBottomBtnHolder.this.mVerifyDialog;
                if (pDYuYueVerifyDialog != null) {
                    pDOperAppointEntity.isAddCartSuccess = false;
                    pDYuYueVerifyDialog.processOrderProduct(pDOperAppointEntity);
                }
                if (LibPdBottomBtnHolder.this.product != null && LibPdBottomBtnHolder.this.product.isPlusMakeTime() && LibPdBottomBtnHolder.this.product.mWareBusinessData.makeMoreTime.makeMoreTimeFlag && ((LibPdBottomBtnHolder.this.product.mWareBusinessData.makeMoreTime.drawMoreTimeFlag || LibPdBottomBtnHolder.this.product.mWareBusinessData.makeMoreTime.appointMoreTimeFlag) && LibPdBottomBtnHolder.this.product.mWareBusinessData.makeMoreTime.userFlag)) {
                    z2 = true;
                }
                if (!z2 || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null) {
                    return;
                }
                LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.refreshData(LibPdBottomBtnHolder.this.mHaveCouponId);
            } else if (!z && !TextUtils.isEmpty(pDOperAppointEntity.appFailToast)) {
                PDUtils.showToastCenterNormal(LibPdBottomBtnHolder.this.mActivity, pDOperAppointEntity.appFailToast);
            } else {
                PDYuYueVerifyDialog pDYuYueVerifyDialog2 = LibPdBottomBtnHolder.this.mVerifyDialog;
                if (pDYuYueVerifyDialog2 != null) {
                    pDOperAppointEntity.isAddCartSuccess = false;
                    pDYuYueVerifyDialog2.processOrderProduct(pDOperAppointEntity);
                }
            }
        }

        /* renamed from: c */
        public /* synthetic */ void d(PDIsAppointEntity pDIsAppointEntity, boolean z) {
            if (LibPdBottomBtnHolder.this.product == null || pDIsAppointEntity == null || LibPdBottomBtnHolder.this.mActivity == null || LibPdBottomBtnHolder.this.mActivity.isFinishing()) {
                return;
            }
            if (z) {
                int i2 = pDIsAppointEntity.code;
                PDVerificationEntity pDVerificationEntity = pDIsAppointEntity.sec_comp;
                LibPdBottomBtnHolder.this.mVerifyDialog = new PDYuYueVerifyDialog(LibPdBottomBtnHolder.this.mActivity, this);
                LibPdBottomBtnHolder libPdBottomBtnHolder = LibPdBottomBtnHolder.this;
                libPdBottomBtnHolder.mVerifyDialog.setData(pDVerificationEntity, libPdBottomBtnHolder.product);
                if (pDVerificationEntity == null) {
                    LibPdBottomBtnHolder.this.mVerifyDialog.handleOrderProduct("", "", "", "0", Boolean.FALSE);
                } else if (i2 != 0 || LibPdBottomBtnHolder.this.mVerifyDialog.isShowing()) {
                } else {
                    LibPdBottomBtnHolder.this.mVerifyDialog.show();
                }
            } else if (pDIsAppointEntity.code == 0) {
                if (pDIsAppointEntity.isAppoint) {
                    if (LibPdBottomBtnHolder.this.product.isYuyueMask()) {
                        if (!TextUtils.equals(pDIsAppointEntity.drawEnum, "4")) {
                            LibPdBottomBtnHolder.this.buyYuyue(pDIsAppointEntity.isNowbuy);
                            return;
                        } else if (!TextUtils.isEmpty(pDIsAppointEntity.text)) {
                            PDUtils.showToastCenterNormal(LibPdBottomBtnHolder.this.mActivity, pDIsAppointEntity.text);
                            return;
                        } else {
                            PDUtils.showToastCenterNormal(LibPdBottomBtnHolder.this.mActivity, LibPdBottomBtnHolder.this.mActivity.getResources().getString(R.string.libpdstyleinfoview_pd_yuyue_mask_appoint_error));
                            return;
                        }
                    }
                    LibPdBottomBtnHolder.this.buyYuyue(pDIsAppointEntity.isNowbuy);
                    return;
                }
                LibPdBottomBtnHolder.this.popHintInfo(pDIsAppointEntity.text);
            }
        }

        @Override // com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener
        public void dealLvData(PDLVEntity pDLVEntity) {
            if (pDLVEntity == null || LibPdBottomBtnHolder.this.mActivity == null || LibPdBottomBtnHolder.this.mActivity.isFinishing()) {
                return;
            }
            if (pDLVEntity.code != 0) {
                PDUtils.showToastCenterIcon(LibPdBottomBtnHolder.this.mActivity, (byte) 1, LibPdBottomBtnHolder.this.mActivity.getResources().getString(R.string.libpdstyleinfoview_pd_error_panic));
            } else if (!TextUtils.isEmpty(pDLVEntity.url) && pDLVEntity.type == 2) {
                OpenAppUtils.openAppForInner(LibPdBottomBtnHolder.this.mActivity, pDLVEntity.url);
            } else if (!TextUtils.isEmpty(pDLVEntity.url)) {
                PDBaseDeepLinkHelper.gotoMWithUrl(LibPdBottomBtnHolder.this.mActivity, pDLVEntity.url);
            } else {
                PDUtils.showToastCenterIcon(LibPdBottomBtnHolder.this.mActivity, (byte) 1, pDLVEntity.text);
            }
        }

        @Override // com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener
        public void onAppointted(final PDOperAppointEntity pDOperAppointEntity, String str) {
            if (LibPdBottomBtnHolder.this.isActivityFinish() || pDOperAppointEntity == null) {
                return;
            }
            LibPdBottomBtnHolder.this.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.b
                {
                    LibPdBottomBtnHolder.AnonymousClass1.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    LibPdBottomBtnHolder.AnonymousClass1.this.b(pDOperAppointEntity);
                }
            });
        }

        @Override // com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener
        public void onIsAppointGetted(final boolean z, final PDIsAppointEntity pDIsAppointEntity) {
            if (LibPdBottomBtnHolder.this.isActivityFinish()) {
                return;
            }
            LibPdBottomBtnHolder.this.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.a
                {
                    LibPdBottomBtnHolder.AnonymousClass1.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    LibPdBottomBtnHolder.AnonymousClass1.this.d(pDIsAppointEntity, z);
                }
            });
        }

        @Override // com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener
        public void onLogin() {
            if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null) {
                LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.refreshData(LibPdBottomBtnHolder.this.mHaveCouponId);
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder$4 */
    /* loaded from: classes3.dex */
    public class AnonymousClass4 implements LibPdStyleInfoViewCouponReceiveListener {
        AnonymousClass4() {
            LibPdBottomBtnHolder.this = r1;
        }

        /* renamed from: a */
        public /* synthetic */ void b(PDCouponReceiveEntity pDCouponReceiveEntity) {
            try {
                PDUtils.showToastCenterIcon(LibPdBottomBtnHolder.this.mActivity, (byte) 1, pDCouponReceiveEntity.msg);
                if (LibPdBottomBtnHolder.this.mPresenter != null) {
                    LibPdBottomBtnHolder.this.mPresenter.onNowBuyClick();
                }
                if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null || !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                    return;
                }
                LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.dismiss();
            } catch (Exception unused) {
            }
        }

        /* renamed from: c */
        public /* synthetic */ void d(PDCouponReceiveEntity pDCouponReceiveEntity) {
            try {
                LibPdBottomBtnHolder.this.handleCouponResp(pDCouponReceiveEntity);
                if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null || !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                    return;
                }
                LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.dismiss();
            } catch (Exception unused) {
            }
        }

        @Override // com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCouponReceiveListener
        public void onParseError(final PDCouponReceiveEntity pDCouponReceiveEntity, String str) {
            if (LibPdBottomBtnHolder.this.isActivityFinish() || pDCouponReceiveEntity == null || !TextUtils.equals(LibPdBottomBtnHolder.REQUEST_TAG, pDCouponReceiveEntity.mRequestTag)) {
                return;
            }
            LibPdBottomBtnHolder.this.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.c
                {
                    LibPdBottomBtnHolder.AnonymousClass4.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    LibPdBottomBtnHolder.AnonymousClass4.this.b(pDCouponReceiveEntity);
                }
            });
        }

        @Override // com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCouponReceiveListener
        public void onParseResponse(final PDCouponReceiveEntity pDCouponReceiveEntity, String str) {
            String str2 = "===========onParseResponse111=============" + LibPdBottomBtnHolder.this.isActivityFinish() + "===entity==" + pDCouponReceiveEntity;
            if (LibPdBottomBtnHolder.this.isActivityFinish() || pDCouponReceiveEntity == null) {
                return;
            }
            LibPdBottomBtnHolder.this.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.d
                {
                    LibPdBottomBtnHolder.AnonymousClass4.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    LibPdBottomBtnHolder.AnonymousClass4.this.d(pDCouponReceiveEntity);
                }
            });
        }
    }

    /* loaded from: classes3.dex */
    public class OnCarClickListener implements View.OnClickListener {
        public static final int ADD_2_CAR = 1;
        public static final int ADD_2_CAR_STYLE = 70;
        public static final int CLICK_NONE = -1;
        public static final int NOW_BUY = 2;
        public static final int SKIP_PD = 60;
        public static final int YUSHOU = 3;
        public static final int YUSHOU_ADVANCE_BUY = 52;
        public static final int YUSHOU_DREAM_BUY = 26;
        public static final int YUYUE_BUY = 12;
        public static final int YUYUE_ORDER = 11;

        public OnCarClickListener() {
            LibPdBottomBtnHolder.this = r1;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:641:0x0162, code lost:
            if (r0.equals("3") == false) goto L635;
         */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onClick(View view) {
            if (!PDUtils.repeatClick() || LibPdBottomBtnHolder.this.isActivityFinish() || LibPdBottomBtnHolder.this.product == null || LibPdBottomBtnHolder.this.mPresenter == null) {
                return;
            }
            Object tag = view.getTag();
            if (tag instanceof Integer) {
                int intValue = ((Integer) tag).intValue();
                boolean z = true;
                boolean z2 = false;
                boolean z3 = LibPdBottomBtnHolder.this.product.isHaveDeliveryInstall() && TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.deliveryId);
                if (intValue != 1) {
                    char c2 = 2;
                    if (intValue != 2) {
                        if (intValue != 3) {
                            if (intValue != 11) {
                                if (intValue != 12) {
                                    if (intValue != 26) {
                                        if (intValue != 52) {
                                            if (intValue == 60) {
                                                LibPdBottomBtnHolder.this.clickJumpStartProductDetailActivity();
                                            } else if (intValue == 70) {
                                                if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                                                    return;
                                                }
                                                if (z3) {
                                                    PDUtils.showToastCenterNormal(LibPdBottomBtnHolder.this.mActivity, LibPdBottomBtnHolder.this.mActivity.getString(R.string.libpdstyleinfoview_style_deliveryinstall_tip2));
                                                } else if (LibPdBottomBtnHolder.this.product.isYuShou()) {
                                                    int skuSource = LibPdBottomBtnHolder.this.product.getSkuSource();
                                                    if (LibPdBottomBtnHolder.this.product.mWareBusinessData == null || LibPdBottomBtnHolder.this.product.mWareBusinessData.YuShouInfo == null || !LibPdBottomBtnHolder.this.product.mWareBusinessData.YuShouInfo.presaleAndOversea) {
                                                        LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.PRESALE, skuSource);
                                                    } else {
                                                        LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.JDWORLDWIDE_PRESALE, 10);
                                                    }
                                                } else if (LibPdBottomBtnHolder.this.product.isYuyue()) {
                                                    boolean z4 = (LibPdBottomBtnHolder.this.product.mWareBusinessData == null || LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo == null || !LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.isAdvanceBuy() || TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.appointPrice)) ? false : true;
                                                    String yuyueType = LibPdBottomBtnHolder.this.product.getYuyueType();
                                                    yuyueType.hashCode();
                                                    yuyueType.hashCode();
                                                    switch (yuyueType.hashCode()) {
                                                        case 49:
                                                            if (yuyueType.equals("1")) {
                                                                c2 = 0;
                                                                break;
                                                            }
                                                            c2 = '\uffff';
                                                            break;
                                                        case 50:
                                                            if (yuyueType.equals("2")) {
                                                                c2 = 1;
                                                                break;
                                                            }
                                                            c2 = '\uffff';
                                                            break;
                                                        case 51:
                                                            break;
                                                        case 52:
                                                            if (yuyueType.equals("4")) {
                                                                c2 = 3;
                                                                break;
                                                            }
                                                            c2 = '\uffff';
                                                            break;
                                                        default:
                                                            c2 = '\uffff';
                                                            break;
                                                    }
                                                    switch (c2) {
                                                        case 0:
                                                        case 1:
                                                        case 2:
                                                            if (LibPdBottomBtnHolder.this.product.mWareBusinessData != null && LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo != null && LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.mad && !TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.madBtnText)) {
                                                                z2 = true;
                                                            }
                                                            if (z2) {
                                                                LibPdBottomBtnHolder.this.mPresenter.doYuyue(LibPdBottomBtnHolder.this.libPdStyleInfoViewYuYueListener);
                                                                break;
                                                            } else if (!z4 || (!LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.showLayerFromBuyNow && LibPdBottomBtnHolder.this.mRightBtn.getVisibility() != 0)) {
                                                                LibPdBottomBtnHolder.this.mPresenter.doYuyue(LibPdBottomBtnHolder.this.libPdStyleInfoViewYuYueListener);
                                                                break;
                                                            } else {
                                                                LibPdBottomBtnHolder.this.mPresenter.onNowBuyClick();
                                                                break;
                                                            }
                                                            break;
                                                        case 3:
                                                            if (LibPdBottomBtnHolder.this.product.mWareBusinessData != null && LibPdBottomBtnHolder.this.product.mWareBusinessData.buttonInfo != null && LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.showLayerFromBuyNow) {
                                                                z2 = true;
                                                            }
                                                            LibPdBottomBtnHolder.this.handleValidOrderProduct(z2);
                                                            break;
                                                    }
                                                } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.showLayerFromBuyNow) {
                                                    if (LibPdBottomBtnHolder.this.product.mWareBusinessData == null || TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.mWareBusinessData.rxNewPrescriptInfoResult)) {
                                                        LibPdBottomBtnHolder.this.receiveCouponAndBuy();
                                                    } else {
                                                        LibPdBottomBtnHolder.this.clickJumpStartProductDetailActivity();
                                                        return;
                                                    }
                                                } else {
                                                    LibPdBottomBtnHolder.this.mPresenter.onCarClick();
                                                }
                                            }
                                        } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                                            return;
                                        } else {
                                            if (LibPdBottomBtnHolder.this.product != null) {
                                                String advanceSkuSource = LibPdBottomBtnHolder.this.product.getAdvanceSkuSource();
                                                if (LibPdBottomBtnHolder.this.product.mWareBusinessData == null || LibPdBottomBtnHolder.this.product.mWareBusinessData.YuShouInfo == null || !LibPdBottomBtnHolder.this.product.mWareBusinessData.YuShouInfo.presaleAndOversea) {
                                                    LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.PRESALE, PDUtils.stringToInt(advanceSkuSource));
                                                } else {
                                                    LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.JDWORLDWIDE_PRESALE, PDUtils.stringToInt(advanceSkuSource));
                                                }
                                            }
                                        }
                                    } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                                        return;
                                    } else {
                                        if (!z3) {
                                            LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.PRESALE, LibPdBottomBtnHolder.this.product.getSkuSource());
                                        } else {
                                            LibPdBottomBtnHolder libPdBottomBtnHolder = LibPdBottomBtnHolder.this;
                                            if (libPdBottomBtnHolder.isYushouStyleOpen) {
                                                PDUtils.showToastCenterNormal(libPdBottomBtnHolder.mActivity, LibPdBottomBtnHolder.this.mActivity.getString(R.string.libpdstyleinfoview_style_deliveryinstall_tip2));
                                            } else if (libPdBottomBtnHolder.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                                                LibPdBottomBtnHolder.this.openStyle();
                                            }
                                        }
                                    }
                                } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                                    return;
                                } else {
                                    if (!LibPdBottomBtnHolder.this.product.showBuyLayer() || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                                        LibPdBottomBtnHolder.this.handleValidOrderProduct(false);
                                    } else {
                                        LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.showLayerFromBuyNow = true;
                                        LibPdBottomBtnHolder.this.openStyle();
                                    }
                                }
                            } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                                return;
                            } else {
                                if (!LibPdBottomBtnHolder.this.product.showBuyLayer() || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                                    LibPdBottomBtnHolder.this.mPresenter.doYuyue(LibPdBottomBtnHolder.this.libPdStyleInfoViewYuYueListener);
                                } else {
                                    LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.showLayerFromYuYue = true;
                                    LibPdBottomBtnHolder.this.openStyle();
                                }
                            }
                        } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                            return;
                        } else {
                            if (LibPdBottomBtnHolder.this.product.showBuyLayer() && !LibPdBottomBtnHolder.this.product.isFullPriceYuShou()) {
                                LibPdBottomBtnHolder libPdBottomBtnHolder2 = LibPdBottomBtnHolder.this;
                                libPdBottomBtnHolder2.showLayerFromSpecial = true;
                                if (libPdBottomBtnHolder2.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                                    LibPdBottomBtnHolder.this.openStyle();
                                }
                            } else if (!z3) {
                                int skuSource2 = LibPdBottomBtnHolder.this.product.getSkuSource();
                                if (LibPdBottomBtnHolder.this.product.mWareBusinessData == null || LibPdBottomBtnHolder.this.product.mWareBusinessData.YuShouInfo == null || !LibPdBottomBtnHolder.this.product.mWareBusinessData.YuShouInfo.presaleAndOversea) {
                                    LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.PRESALE, skuSource2);
                                } else {
                                    LibPdBottomBtnHolder.this.jumpFillOrder(FillOrder.JDWORLDWIDE_PRESALE, 10);
                                }
                            } else {
                                LibPdBottomBtnHolder libPdBottomBtnHolder3 = LibPdBottomBtnHolder.this;
                                if (libPdBottomBtnHolder3.isYushouStyleOpen) {
                                    PDUtils.showToastCenterNormal(libPdBottomBtnHolder3.mActivity, LibPdBottomBtnHolder.this.mActivity.getString(R.string.libpdstyleinfoview_style_deliveryinstall_tip2));
                                } else if (libPdBottomBtnHolder3.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                                    LibPdBottomBtnHolder.this.openStyle();
                                }
                            }
                        }
                    } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                        return;
                    } else {
                        if (!LibPdBottomBtnHolder.this.product.showBuyLayer() || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                            if (LibPdBottomBtnHolder.this.product.isYuyue()) {
                                if (LibPdBottomBtnHolder.this.product.mWareBusinessData != null && LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo != null && LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.isAdvanceBuy() && !TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.mWareBusinessData.yuyueInfo.appointPrice)) {
                                    z2 = true;
                                }
                                if (!z2 || (!TextUtils.equals(LibPdBottomBtnHolder.this.product.getYuyueType(), "2") && !TextUtils.equals(LibPdBottomBtnHolder.this.product.getYuyueType(), "3") && !TextUtils.equals(LibPdBottomBtnHolder.this.product.getYuyueType(), "1"))) {
                                    LibPdBottomBtnHolder.this.handleValidOrderProduct(true);
                                } else {
                                    LibPdBottomBtnHolder.this.mPresenter.onNowBuyClick();
                                }
                            } else if (LibPdBottomBtnHolder.this.product.mWareBusinessData == null || TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.mWareBusinessData.rxNewPrescriptInfoResult)) {
                                LibPdBottomBtnHolder.this.receiveCouponAndBuy();
                            } else {
                                LibPdBottomBtnHolder.this.clickJumpStartProductDetailActivity();
                                return;
                            }
                        } else {
                            LibPdBottomBtnHolder.this.openStyle();
                            z = false;
                        }
                        PDUtils.onClickForTc("Component_BuyNow", LibPdBottomBtnHolder.this.product.skuId, LibPdBottomBtnHolder.this.product.source);
                    }
                    if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null && z && LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                        LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.dismiss();
                        return;
                    }
                    return;
                } else if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils != null && !LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.checkStyleButtonClick()) {
                    return;
                } else {
                    if (!LibPdBottomBtnHolder.this.product.isToABTest() || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null || LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils.isShowDialog()) {
                        if (LibPdBottomBtnHolder.this.product.isYuyue()) {
                            LibPdBottomBtnHolder.this.handleValidOrderProduct(false);
                        } else {
                            LibPdBottomBtnHolder.this.mPresenter.onCarClick();
                        }
                    } else {
                        LibPdBottomBtnHolder.this.openStyle();
                    }
                    PDUtils.onClickForTc("Component_Addtocart", LibPdBottomBtnHolder.this.product.skuId, LibPdBottomBtnHolder.this.product.source);
                }
                z = false;
                if (LibPdBottomBtnHolder.this.mLibPdStyleInfoViewUtils == null) {
                }
            }
        }
    }

    public LibPdBottomBtnHolder(Activity activity, IMyActivity iMyActivity, ProductDetailEntity productDetailEntity, TextView textView, TextView textView2, LibPdStyleInfoViewCarListener libPdStyleInfoViewCarListener, LibPdStyleInfoViewUtils libPdStyleInfoViewUtils, LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener) {
        this.mOnCarClickListener = new OnCarClickListener();
        this.mStartTime = -1L;
        this.mYellowDrawable = R.drawable.libpdstyleinfoview_same_corner_bg_v8;
        this.mRedDrawable = R.drawable.libpdstyleinfoview_add2car_corner_bg_v8;
        this.mJxYellowDrawable = R.drawable.libpdstyleinfoview_bottom_btn_jx_selector;
        this.showLayerFromSpecial = false;
        this.isYushouStyleOpen = false;
        this.isStyleLayerShow = false;
        this.isJx = false;
        this.showLayerFromAddCart = false;
        this.libPdStyleInfoViewYuYueListener = new AnonymousClass1();
        this.couponReceiveListener = new AnonymousClass4();
        this.mActivity = activity;
        this.mIMyActivity = iMyActivity;
        this.product = productDetailEntity;
        this.mLeftBtn = textView;
        this.mRightBtn = textView2;
        this.mCarListener = libPdStyleInfoViewCarListener;
        this.mLibPdStyleInfoViewUtils = libPdStyleInfoViewUtils;
        this.mBottomBtnClickListener = libPdStyleInfoViewBottomBtnClickListener;
        this.mPresenter = new LibPdBottomBtnPresenter(productDetailEntity, activity, iMyActivity, libPdStyleInfoViewCarListener, libPdStyleInfoViewUtils);
        PdBottomButtonInfo pdBottomButtonInfo = new PdBottomButtonInfo();
        this.mButtonInfo = pdBottomButtonInfo;
        pdBottomButtonInfo.leftButtonInfo = new PdBottomLeftButtonInfo();
        this.mButtonInfo.rightButtonInfo = new PdBottomRightButtonInfo();
        this.dealUtil = new YuShouDealUtil(this.product, this.mActivity, this.mButtonInfo, true, -1L, libPdStyleInfoViewUtils, this.mBottomListener, false);
        this.mLeftBtn.setOnClickListener(this.mOnCarClickListener);
        this.mRightBtn.setOnClickListener(this.mOnCarClickListener);
        showBottomBtnView();
    }

    private boolean checkNeedRefresh() {
        long parseLong;
        String str = "checkNeedRefresh yuyueType = " + this.product.mWareBusinessData.yuyueInfo.yuyueType;
        try {
            String str2 = this.product.mWareBusinessData.yuyueInfo.yuyueType;
            char c2 = '\uffff';
            switch (str2.hashCode()) {
                case 49:
                    if (str2.equals("1")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str2.equals("3")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 52:
                    if (str2.equals("4")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 53:
                    if (str2.equals("5")) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            if (c2 == 0) {
                parseLong = Long.parseLong(this.product.mWareBusinessData.yuyueInfo.yuYueStartTime);
            } else if (c2 == 1) {
                parseLong = Long.parseLong(this.product.mWareBusinessData.yuyueInfo.yuYueEndTime);
            } else if (c2 != 2) {
                parseLong = c2 != 3 ? Long.MAX_VALUE : Long.parseLong(this.product.mWareBusinessData.yuyueInfo.buyEndTime);
            } else {
                parseLong = Long.parseLong(this.product.mWareBusinessData.yuyueInfo.buyStartTime);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("checkNeedRefresh mStartTime = ");
            sb.append(this.mStartTime);
            sb.append(" countDownTime = ");
            sb.append(parseLong);
            sb.append(" need = ");
            sb.append(this.mStartTime >= parseLong);
            sb.toString();
            return this.mStartTime >= parseLong;
        } catch (Exception unused) {
            return false;
        }
    }

    public void clickJumpStartProductDetailActivity() {
        ProductDetailEntity productDetailEntity;
        Activity activity = this.mActivity;
        if (activity == null || (productDetailEntity = this.product) == null) {
            return;
        }
        this.mPresenter.onRecommendStartProductDetailActivity(activity, productDetailEntity);
        ProductDetailEntity productDetailEntity2 = this.product;
        PDUtils.onClickForTc("Component_ProductDetail", productDetailEntity2.skuId, productDetailEntity2.source);
    }

    private boolean dealAhStore() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.product.mWareBusinessData;
        if ((wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.daojiaFlag || wareBusinessData.daojiaStoreInfo == null) ? false : true) {
            goToPd();
            return true;
        }
        return false;
    }

    /* renamed from: dealBottomInfo */
    public boolean b() {
        setCarStatus();
        if (!this.product.isCanBuy() || this.product.getMain() == null) {
            return false;
        }
        setDoubleBtn();
        return true;
    }

    private boolean dealBuyByM() {
        if (this.product.check()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealCarBundlingSale() {
        if (this.product.isSelectCarShop()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealCarFinance() {
        WareBusinessData wareBusinessData = this.product.mWareBusinessData;
        if (wareBusinessData == null || wareBusinessData.carFinance == null) {
            return false;
        }
        goToPd();
        return true;
    }

    private boolean dealCarShop() {
        if (this.product.getCarShoInfo() != null) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealDaJiaDian() {
        ProductDetailEntity productDetailEntity = this.product;
        if (productDetailEntity == null || TextUtils.isEmpty(productDetailEntity.getAreaSkuId())) {
            return false;
        }
        goToPd();
        return true;
    }

    private boolean dealECardBuy() {
        if (this.product.isECardBuy()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealJX() {
        if (this.product.isJx() && isCanBuy()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealLoc() {
        if (this.product.isloc()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealNewTreayPhone() {
        TreayNewInfo treayNewInfo = getmCurTreayNewInfo();
        if (treayNewInfo == null || !TextUtils.equals(treayNewInfo.type, "1")) {
            return false;
        }
        goToPd();
        return true;
    }

    private boolean dealOPBuy() {
        if (this.product.isCartShield()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealPhoneBuyStyle() {
        WareBusinessFeeInfo curFeeInfo = getCurFeeInfo();
        if (curFeeInfo == null || !TextUtils.equals(curFeeInfo.type, "0")) {
            return false;
        }
        goToPd();
        return true;
    }

    private boolean dealPinGT() {
        WareBusinessData wareBusinessData = this.product.mWareBusinessData;
        if (((wareBusinessData == null || wareBusinessData.joinBuyInfo == null || (!wareBusinessData.isJoinBuyInfo() && !this.product.mWareBusinessData.isPinTuan())) ? false : true) && isCanBuy()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealPrescription() {
        if (this.product.isDrug()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealProNoticeInfo() {
        setCarStatus();
        if (this.product.isCanBuy() && this.product.isProSalesInfoStage()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealQQAndGame() {
        if (this.product.getRechargeJumpType() != -1) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealRegularBuy() {
        if (this.product.isRegularBuy4TimeOrder3()) {
            goToPd();
            return true;
        } else if (this.product.isRegularBuy()) {
            goToPd();
            return true;
        } else {
            return false;
        }
    }

    private boolean dealType() {
        String propertyType = this.product.getPropertyType();
        if ("1".equals(propertyType)) {
            WareBusinessData wareBusinessData = this.product.mWareBusinessData;
            if (wareBusinessData == null || wareBusinessData.koInfo == null) {
                return false;
            }
            goToPd();
            return true;
        } else if ("2".equals(propertyType)) {
            goToPd();
            return true;
        } else {
            return false;
        }
    }

    private boolean dealYuShouAdvanceBuy() {
        if (this.product.isYuShou() && this.product.isYuShouAdvanceBuy()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealYuYue() {
        if (this.product.isYuyue()) {
            goToPd();
        }
        return this.product.isYuyue();
    }

    private boolean dealYushou() {
        if (this.product.isYuShou()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean dealYushouDreamBuy() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.product.mWareBusinessData;
        if (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || !wareYuShouInfo.isDreamBuy()) {
            return false;
        }
        goToPd();
        return true;
    }

    private void forExposure() {
        ProductDetailEntity productDetailEntity = this.product;
        if (productDetailEntity == null) {
            return;
        }
        int i2 = this.bottomBtnType;
        if (i2 == 1) {
            PDUtils.exposureForTc("Component_AddtocartExpo", productDetailEntity.skuId, productDetailEntity.source);
        } else if (i2 == 2) {
            PDUtils.exposureForTc("Component_BuyNowExpo", productDetailEntity.skuId, productDetailEntity.source);
        } else if (i2 != 60) {
        } else {
            PDUtils.exposureForTc("Component_ProductDetailExpo", productDetailEntity.skuId, productDetailEntity.source);
        }
    }

    private int getBtnTag(int i2) {
        if (i2 != 0) {
            return i2 != 1 ? 0 : 2;
        }
        return 1;
    }

    private SpannableString getHandsPrice(String str, boolean z) {
        PdBottomRightButtonInfo pdBottomRightButtonInfo;
        PdBottomRightButtonInfo pdBottomRightButtonInfo2;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        if (this.product.isShowBtnHandPrice() && !this.product.isYuyue() && z) {
            PdToHandPriceEntity pdToHandPriceEntity = this.product.mWareBusinessData.toHandssSrengthen;
            String str2 = pdToHandPriceEntity.toHandsText;
            String str3 = pdToHandPriceEntity.toHandsPrice;
            StringBuilder sb = new StringBuilder();
            if (this.mHaveCouponId && (libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils) != null && libPdStyleInfoViewUtils.isNew930Style) {
                sb.append(str);
            } else if (this.product.isUseBatchReceiveCoupon() && !this.product.getBestCoupon().isEmpty()) {
                sb.append(this.mActivity.getResources().getString(R.string.libpdstyleinfoview_receive_coupon_and_buy));
            } else {
                sb.append(str);
            }
            PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
            if (pdBottomButtonInfo != null && (pdBottomRightButtonInfo2 = pdBottomButtonInfo.rightButtonInfo) != null) {
                pdBottomRightButtonInfo2.buttonText = sb.toString();
                this.mButtonInfo.rightButtonInfo.buttonSubText = str2 + str3;
                String str4 = "buttoninfo-------" + ((Object) this.mButtonInfo.rightButtonInfo.buttonText) + "-----sub-----" + this.mButtonInfo.rightButtonInfo.buttonSubText + "-----skuid-----" + this.product.skuId;
            }
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append(str2);
            sb.append(str3);
            SpannableString spannableString = new SpannableString(sb);
            int lastIndexOf = sb.lastIndexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            int length = sb.length();
            spannableString.setSpan(new CustomTypefaceSpan("", Typeface.DEFAULT), lastIndexOf, length, 18);
            spannableString.setSpan(new RelativeSizeSpan(0.8f), lastIndexOf, length, 33);
            return spannableString;
        }
        SpannableString spannableString2 = new SpannableString(str);
        PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
        if (pdBottomButtonInfo2 != null && (pdBottomRightButtonInfo = pdBottomButtonInfo2.rightButtonInfo) != null && z) {
            pdBottomRightButtonInfo.buttonText = str;
            String str5 = "buttoninfo1111-------" + ((Object) this.mButtonInfo.rightButtonInfo.buttonText) + "-----sub11111-----" + this.mButtonInfo.rightButtonInfo.buttonSubText + "-----skuid-----" + this.product.skuId;
        }
        return spannableString2;
    }

    private void goToPd() {
        TextView textView;
        if (this.mLeftBtn == null || (textView = this.mRightBtn) == null || this.mActivity == null) {
            return;
        }
        textView.setVisibility(8);
        this.mLeftBtn.setVisibility(0);
        this.mLeftBtn.setBackgroundResource(R.drawable.libpdstyleinfoview_add2car_corner_bg_v8);
        this.mLeftBtn.setText(this.mActivity.getResources().getString(R.string.libpdstyleinfoview_goto_pd));
        this.mLeftBtn.setTag(60);
        this.mLeftBtn.setEnabled(true);
        this.bottomBtnType = 60;
        forExposure();
    }

    public void handleCouponResp(PDCouponReceiveEntity pDCouponReceiveEntity) {
        Activity activity;
        int i2;
        String string;
        PDCouponReceiveEntity.Result result;
        if (pDCouponReceiveEntity == null) {
            return;
        }
        boolean z = false;
        if (TextUtils.equals(pDCouponReceiveEntity.code, "0") && (result = pDCouponReceiveEntity.result) != null) {
            if (result.isBatchReceive == 0) {
                if (TextUtils.equals(result.optCode, "9000")) {
                    markCouponReceived(pDCouponReceiveEntity.result.couponResult);
                    z = true;
                }
            } else {
                HashMap<String, String> hashMap = result.batchCouponResult;
                if (hashMap != null && hashMap.size() > 0) {
                    for (String str : pDCouponReceiveEntity.result.batchCouponResult.keySet()) {
                        if (TextUtils.equals(pDCouponReceiveEntity.result.batchCouponResult.get(str), "999")) {
                            markCouponReceived(str);
                            z = true;
                        }
                    }
                }
            }
        }
        if (TextUtils.equals(REQUEST_TAG, pDCouponReceiveEntity.mRequestTag)) {
            PDCouponReceiveEntity.Result result2 = pDCouponReceiveEntity.result;
            if (result2 == null || TextUtils.isEmpty(result2.desc)) {
                if (z) {
                    activity = this.mActivity;
                    i2 = R.string.libpdstyleinfoview_coupon_submit_suc;
                } else {
                    activity = this.mActivity;
                    i2 = R.string.libpdstyleinfoview_coupon_submit_faild;
                }
                string = activity.getString(i2);
            } else {
                string = pDCouponReceiveEntity.result.desc;
            }
            byte b = z ? (byte) 2 : (byte) 1;
            if (!TextUtils.isEmpty(string)) {
                PDUtils.showToastCenterIcon(this.mActivity, b, string);
            }
            LibPdBottomBtnPresenter libPdBottomBtnPresenter = this.mPresenter;
            if (libPdBottomBtnPresenter != null) {
                libPdBottomBtnPresenter.onNowBuyClick();
            }
        }
    }

    public void handleValidOrderProduct(boolean z) {
        this.mPresenter.qureyIsAppoint(z, this.libPdStyleInfoViewYuYueListener);
    }

    private void handleYuShouNum(String str, Spannable spannable) {
        try {
            int indexOf = str.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (indexOf > 0) {
                spannable.setSpan(new AbsoluteSizeSpan(10, true), indexOf, str.length(), 18);
                spannable.setSpan(new CustomTypefaceSpan("", Typeface.DEFAULT), indexOf, str.length(), 18);
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("Exception", e2.getMessage());
            }
        }
    }

    private void hideAddCartBtn() {
        ProductDetailEntity productDetailEntity;
        PdBottomLeftButtonInfo pdBottomLeftButtonInfo;
        if (this.mActivity == null || (productDetailEntity = this.product) == null || !productDetailEntity.isTermiteCartShield()) {
            return;
        }
        PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
        boolean z = (pdBottomButtonInfo == null || (pdBottomLeftButtonInfo = pdBottomButtonInfo.leftButtonInfo) == null || TextUtils.isEmpty(pdBottomLeftButtonInfo.buttonText) || !this.mButtonInfo.leftButtonInfo.buttonText.toString().contains(this.mActivity.getString(R.string.libpdstyleinfoview_add2car))) ? false : true;
        if (z) {
            PdBottomRightButtonInfo pdBottomRightButtonInfo = this.mButtonInfo.rightButtonInfo;
            if (pdBottomRightButtonInfo != null && !TextUtils.isEmpty(pdBottomRightButtonInfo.buttonText)) {
                PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
                PdBottomLeftButtonInfo pdBottomLeftButtonInfo2 = pdBottomButtonInfo2.leftButtonInfo;
                PdBottomRightButtonInfo pdBottomRightButtonInfo2 = pdBottomButtonInfo2.rightButtonInfo;
                pdBottomLeftButtonInfo2.buttonEvent = pdBottomRightButtonInfo2.buttonEvent;
                pdBottomLeftButtonInfo2.buttonText = pdBottomRightButtonInfo2.buttonText;
                pdBottomLeftButtonInfo2.buttonEnable = pdBottomRightButtonInfo2.buttonEnable;
                pdBottomLeftButtonInfo2.buttonSubText = pdBottomRightButtonInfo2.buttonSubText;
                pdBottomRightButtonInfo2.buttonText = null;
                pdBottomRightButtonInfo2.buttonEnable = true;
                pdBottomRightButtonInfo2.buttonSubText = "";
                pdBottomRightButtonInfo2.buttonEvent = 60;
            } else {
                PdBottomLeftButtonInfo pdBottomLeftButtonInfo3 = this.mButtonInfo.leftButtonInfo;
                pdBottomLeftButtonInfo3.buttonText = "\u67e5\u770b\u8be6\u60c5";
                pdBottomLeftButtonInfo3.buttonEnable = true;
                pdBottomLeftButtonInfo3.buttonSubText = "";
                pdBottomLeftButtonInfo3.buttonEvent = 60;
            }
        }
        boolean isViewContentAddCar = isViewContentAddCar(this.mLeftBtn);
        TextView textView = this.mRightBtn;
        if (textView != null && textView.getVisibility() == 0) {
            TextView textView2 = this.mLeftBtn;
            if (textView2 != null && textView2.getVisibility() == 0 && isViewContentAddCar) {
                this.mLeftBtn.setVisibility(8);
            }
        } else if (isViewContentAddCar || (z && this.showLayerFromAddCart)) {
            goToPd();
        }
    }

    public boolean isActivityFinish() {
        Activity activity = this.mActivity;
        return activity != null && activity.isFinishing();
    }

    private boolean isButtonGift() {
        if (this.product.isCanBuy() && this.product.isButtonEntrance()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean isCanBuy() {
        return this.product.isCanBuy();
    }

    private boolean isContainsCurFeeinfo() {
        boolean z = false;
        if (this.mCurFeeInfo == null || this.product.getWareBusinessFeeInfo() == null) {
            return false;
        }
        Iterator<WareBusinessFeeInfo> it = this.product.getWareBusinessFeeInfo().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WareBusinessFeeInfo next = it.next();
            if (next != null && TextUtils.equals(next.sku, this.mCurFeeInfo.sku) && TextUtils.equals(next.ft, this.mCurFeeInfo.ft)) {
                z = true;
                break;
            }
        }
        if (!z) {
            for (WareBusinessFeeInfo wareBusinessFeeInfo : this.product.getWareBusinessFeeInfo()) {
                if (wareBusinessFeeInfo != null && TextUtils.equals(wareBusinessFeeInfo.sku, this.mCurFeeInfo.sku)) {
                    this.mCurFeeInfo = wareBusinessFeeInfo;
                    return true;
                }
            }
        }
        return z;
    }

    private boolean isJxAndCanBuy() {
        return this.product.isJx() && isCanBuy();
    }

    private boolean isViewContentAddCar(TextView textView) {
        if (textView == null) {
            return false;
        }
        CharSequence text = textView.getText();
        return (TextUtils.isEmpty(text) || TextUtils.isEmpty(text) || !text.toString().contains(this.mActivity.getString(R.string.libpdstyleinfoview_add2car))) ? false : true;
    }

    public void jumpFillOrder(final FillOrder fillOrder, final int i2) {
        LoginUserHelper.getInstance().executeLoginRunnable(this.mIMyActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder.2
            private Bundle bundle;

            {
                LibPdBottomBtnHolder.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.bundle == null) {
                    this.bundle = new Bundle();
                }
                this.bundle.putString(DeepLinkFillOrderHelper.LIVE_SOURCE_PT_KEY, LibPdBottomBtnHolder.this.product.pt);
                ArrayList<NewGiftItem> selectedGiftItems = LibPdBottomBtnHolder.this.product.getSelectedGiftItems();
                if (selectedGiftItems != null && !selectedGiftItems.isEmpty()) {
                    this.bundle.putParcelableArrayList("newGiftItems", selectedGiftItems);
                }
                if (LibPdBottomBtnHolder.this.product.mWareBusinessData != null && LibPdBottomBtnHolder.this.product.mWareBusinessData.property != null && !TextUtils.isEmpty(LibPdBottomBtnHolder.this.product.mWareBusinessData.property.isOTC)) {
                    this.bundle.putString(SubmitOrderProductInfo.KEY_ADDITIONALDATA_HASSELECTEDOTC, LibPdBottomBtnHolder.this.product.mWareBusinessData.property.isOTC);
                    this.bundle.putString("otcMergeSwitch", "1");
                }
                this.bundle.putString(CartConstant.KEY_EXTFLAG, LibPdBottomBtnHolder.this.product.getToOrderParam().toJSONString());
                PDBaseDeepLinkHelper.startFillOrder(LibPdBottomBtnHolder.this.mActivity, LibPdBottomBtnHolder.this.product.skuId, LibPdBottomBtnHolder.this.product.number, LibPdBottomBtnHolder.this.product.deliveryId, LibPdBottomBtnHolder.this.product.giftPoolIdsSelect, LibPdBottomBtnHolder.this.product.yanbaoIdsSelect, fillOrder, i2, this.bundle);
            }
        });
    }

    private void markCouponReceived(String str) {
        if (this.product != null) {
            this.product.setCouponApplicability(str, false);
            showBottomBtnView();
            LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener = this.mBottomBtnClickListener;
            if (libPdStyleInfoViewBottomBtnClickListener != null) {
                libPdStyleInfoViewBottomBtnClickListener.libPdBottomRefreshPDView(null, null, false, this.mButtonInfo, true);
            }
        }
    }

    public void openStyle() {
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        this.isStyleLayerShow = true;
        ProductDetailEntity productDetailEntity = this.product;
        if (productDetailEntity == null || (libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils) == null) {
            return;
        }
        libPdStyleInfoViewUtils.openStyleDailog(productDetailEntity.skuId, productDetailEntity.source, productDetailEntity.sourceType);
    }

    private boolean paipaiBuy() {
        if (this.product.isPaiPaiSecond()) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean plusForBuyMt() {
        WareBusinessPlusForBuyMt wareBusinessPlusForBuyMt;
        WareBusinessData wareBusinessData = this.product.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessPlusForBuyMt = wareBusinessData.makeMoreTime) == null) {
            return false;
        }
        if (wareBusinessPlusForBuyMt.makeMoreTimeFlag || wareBusinessPlusForBuyMt.plusShopFlag) {
            goToPd();
            return true;
        }
        return false;
    }

    private boolean plusMtYuyue() {
        boolean z = this.product.isPlusMakeTime() && this.product.isYuyue();
        if (z) {
            goToPd();
        }
        return z;
    }

    public void receiveCouponAndBuy() {
        WareBusinessData wareBusinessData;
        boolean z = this.product.isShowBtnHandPrice() && !this.product.isYuyue();
        if (this.product.isUseBatchReceiveCoupon() && !this.product.getBestCoupon().isEmpty() && z && !this.mHaveCouponId) {
            PDCouponReceiveProtocol pDCouponReceiveProtocol = new PDCouponReceiveProtocol(0, this.product.mManageKey, this.couponReceiveListener);
            pDCouponReceiveProtocol.mRequestId = UUID.randomUUID().toString();
            pDCouponReceiveProtocol.mRequestTag = REQUEST_TAG;
            List<PDCouponCellEntity> bestCoupon = this.product.getBestCoupon();
            if (bestCoupon == null || bestCoupon.size() <= 0) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                String finger = DeviceFinger.getFinger(this.mActivity);
                if (TextUtils.isEmpty(finger)) {
                    finger = "-1";
                }
                jSONObject.put("eid", finger);
                ProductDetailEntity productDetailEntity = this.product;
                if (productDetailEntity != null && (wareBusinessData = productDetailEntity.mWareBusinessData) != null && wareBusinessData.isUseReceiveNCoupon()) {
                    jSONObject.put("isBatchReceive", 0);
                    jSONObject.put("isN", true);
                    jSONObject.put("isBottom", 1);
                }
                if (bestCoupon.size() > 1) {
                    jSONObject.put("isBatchReceive", 1);
                    JSONArray jSONArray = new JSONArray();
                    for (PDCouponCellEntity pDCouponCellEntity : bestCoupon) {
                        JSONObject jSONObject2 = new JSONObject();
                        if (!pDCouponCellEntity.personalCoupon) {
                            if (pDCouponCellEntity.isFinanceCoupon()) {
                                jSONObject2.put("receiveType", 2);
                                if (!TextUtils.isEmpty(pDCouponCellEntity.roleIdCBC)) {
                                    jSONObject2.put("couponKey", PDUtils.decrypt(pDCouponCellEntity.encryptedKey));
                                } else {
                                    jSONObject2.put("couponKey", pDCouponCellEntity.encryptedKey);
                                }
                                jSONObject2.put(JshopConst.JSKEY_BATCH_ID, pDCouponCellEntity.jrBatchId);
                            } else {
                                jSONObject2.put("receiveType", 1);
                                if (!TextUtils.isEmpty(pDCouponCellEntity.roleIdCBC)) {
                                    jSONObject2.put("couponKey", PDUtils.decrypt(pDCouponCellEntity.encryptedKey));
                                    jSONObject2.put("ruleId", PDUtils.decrypt(pDCouponCellEntity.roleIdCBC));
                                } else {
                                    jSONObject2.put("couponKey", pDCouponCellEntity.encryptedKey);
                                    jSONObject2.put("ruleId", pDCouponCellEntity.roleId);
                                }
                            }
                        }
                        jSONArray.put(jSONObject2);
                    }
                    jSONObject.put("batchCoupon", jSONArray);
                } else {
                    PDCouponCellEntity pDCouponCellEntity2 = bestCoupon.get(0);
                    if (!pDCouponCellEntity2.personalCoupon) {
                        if (pDCouponCellEntity2.isFinanceCoupon()) {
                            jSONObject.put("receiveType", 2);
                            if (!TextUtils.isEmpty(pDCouponCellEntity2.roleIdCBC)) {
                                jSONObject.put("couponKey", PDUtils.decrypt(pDCouponCellEntity2.encryptedKey));
                            } else {
                                jSONObject.put("couponKey", pDCouponCellEntity2.encryptedKey);
                            }
                            jSONObject.put(JshopConst.JSKEY_BATCH_ID, pDCouponCellEntity2.jrBatchId);
                        } else {
                            jSONObject.put("receiveType", 1);
                            if (!TextUtils.isEmpty(pDCouponCellEntity2.roleIdCBC)) {
                                jSONObject.put("couponKey", PDUtils.decrypt(pDCouponCellEntity2.encryptedKey));
                                jSONObject.put("ruleId", PDUtils.decrypt(pDCouponCellEntity2.roleIdCBC));
                            } else {
                                jSONObject.put("couponKey", pDCouponCellEntity2.encryptedKey);
                                jSONObject.put("ruleId", pDCouponCellEntity2.roleId);
                            }
                        }
                    }
                }
                String format = String.format("openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"productDetail\",\"skuId\":\"%s\"}", this.product.skuId);
                if (!TextUtils.isEmpty(format)) {
                    jSONObject.put("childActivityUrl", format);
                }
                pDCouponReceiveProtocol.setJSONObject(jSONObject);
                Activity activity = this.mActivity;
                if (activity instanceof BaseActivity) {
                    ((BaseActivity) activity).addHttpGroupWithNPSSetting(pDCouponReceiveProtocol.request());
                    return;
                }
                return;
            } catch (JSONException e2) {
                Log.d("yueliang", "", e2);
                return;
            }
        }
        LibPdBottomBtnPresenter libPdBottomBtnPresenter = this.mPresenter;
        if (libPdBottomBtnPresenter != null) {
            libPdBottomBtnPresenter.onNowBuyClick();
        }
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils;
        if (libPdStyleInfoViewUtils == null || !libPdStyleInfoViewUtils.isShowDialog()) {
            return;
        }
        this.mLibPdStyleInfoViewUtils.dismiss();
    }

    private void setBottomBtn(int i2, PDBottomBtn pDBottomBtn) {
        if (pDBottomBtn == null || this.product == null || this.mActivity == null) {
            return;
        }
        if (this.mButtonInfo == null) {
            this.mButtonInfo = new PdBottomButtonInfo();
        }
        PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
        if (pdBottomButtonInfo.rightButtonInfo == null) {
            pdBottomButtonInfo.rightButtonInfo = new PdBottomRightButtonInfo();
        }
        PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
        if (pdBottomButtonInfo2.leftButtonInfo == null) {
            pdBottomButtonInfo2.leftButtonInfo = new PdBottomLeftButtonInfo();
        }
        int parseColor = PDUtils.parseColor(pDBottomBtn.textColor, JDDarkUtil.COLOR_FFFFFFF);
        Drawable btnBgSelector = PDUtils.getBtnBgSelector(pDBottomBtn.bgColor, true);
        if (btnBgSelector == null) {
            if (i2 == 0) {
                btnBgSelector = this.mActivity.getResources().getDrawable(R.drawable.libpdstyleinfoview_add2car_corner_bg_v8);
            } else {
                btnBgSelector = this.mActivity.getResources().getDrawable(R.drawable.libpdstyleinfoview_same_corner_bg_v8);
            }
        }
        String str = pDBottomBtn.name;
        if (TextUtils.isEmpty(str)) {
            if (i2 == 0) {
                str = this.mActivity.getResources().getString(R.string.libpdstyleinfoview_now_buy);
            } else {
                str = this.mActivity.getResources().getString(R.string.libpdstyleinfoview_add2car);
            }
        }
        SpannableString handsPrice = getHandsPrice(str, pDBottomBtn.type == 1);
        if (i2 == 0) {
            TextView textView = this.mRightBtn;
            if (textView != null) {
                textView.setVisibility(0);
                this.mRightBtn.setText(handsPrice);
                if (this.product.isDarkTheme()) {
                    this.mRightBtn.setTextColor(ContextCompat.getColor(this.mActivity, R.color.libpdstyleinfoview_color_ececec));
                } else {
                    this.mRightBtn.setTextColor(parseColor);
                }
                this.mRightBtn.setBackgroundDrawable(btnBgSelector);
                this.mRightBtn.setTag(Integer.valueOf(getBtnTag(pDBottomBtn.type)));
                this.bottomBtnType = 2;
            }
            this.mButtonInfo.rightButtonInfo.buttonEvent = 2;
            forExposure();
        } else if (i2 != 1) {
        } else {
            TextView textView2 = this.mLeftBtn;
            if (textView2 != null) {
                textView2.setText(handsPrice);
                if (this.product.isDarkTheme()) {
                    this.mLeftBtn.setTextColor(ContextCompat.getColor(this.mActivity, R.color.libpdstyleinfoview_color_ececec));
                } else {
                    this.mLeftBtn.setTextColor(parseColor);
                }
                this.mLeftBtn.setBackgroundDrawable(btnBgSelector);
                this.mLeftBtn.setTag(Integer.valueOf(getBtnTag(pDBottomBtn.type)));
                this.bottomBtnType = 1;
            }
            forExposure();
            PdBottomLeftButtonInfo pdBottomLeftButtonInfo = this.mButtonInfo.leftButtonInfo;
            pdBottomLeftButtonInfo.buttonText = handsPrice;
            pdBottomLeftButtonInfo.buttonEvent = 1;
        }
    }

    private void setBottomButtonInfo() {
        TextView textView = this.mLeftBtn;
        if (textView != null) {
            if (this.mButtonInfo.leftButtonInfo != null) {
                textView.setVisibility(0);
                this.mLeftBtn.setEnabled(this.mButtonInfo.leftButtonInfo.buttonEnable);
                StringBuffer stringBuffer = new StringBuffer(this.mButtonInfo.leftButtonInfo.buttonText);
                if (!TextUtils.isEmpty(this.mButtonInfo.leftButtonInfo.buttonSubText)) {
                    stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    stringBuffer.append(this.mButtonInfo.leftButtonInfo.buttonSubText);
                }
                SpannableString spannableString = new SpannableString(stringBuffer);
                handleYuShouNum(stringBuffer.toString(), spannableString);
                this.mLeftBtn.setText(spannableString);
                this.mLeftBtn.setTag(Integer.valueOf(this.mButtonInfo.leftButtonInfo.buttonEvent));
                int i2 = this.mButtonInfo.leftButtonInfo.backgroundResource;
                if (i2 != -1) {
                    this.mLeftBtn.setBackgroundResource(i2);
                }
            } else {
                textView.setVisibility(8);
            }
        }
        TextView textView2 = this.mRightBtn;
        if (textView2 != null) {
            if (this.mButtonInfo.rightButtonInfo != null) {
                textView2.setVisibility(0);
                this.mRightBtn.setEnabled(this.mButtonInfo.rightButtonInfo.buttonEnable);
                StringBuffer stringBuffer2 = new StringBuffer(this.mButtonInfo.rightButtonInfo.buttonText);
                if (!TextUtils.isEmpty(this.mButtonInfo.rightButtonInfo.buttonSubText)) {
                    stringBuffer2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    stringBuffer2.append(this.mButtonInfo.rightButtonInfo.buttonSubText);
                }
                SpannableString spannableString2 = new SpannableString(stringBuffer2);
                handleYuShouNum(stringBuffer2.toString(), spannableString2);
                this.mRightBtn.setText(spannableString2);
                this.mRightBtn.setTag(Integer.valueOf(this.mButtonInfo.rightButtonInfo.buttonEvent));
                int i3 = this.mButtonInfo.rightButtonInfo.backgroundResource;
                if (i3 != -1) {
                    this.mRightBtn.setBackgroundResource(i3);
                    return;
                }
                return;
            }
            textView2.setVisibility(8);
        }
    }

    private void setDoubleBtn() {
        TextView textView = this.mRightBtn;
        if (textView != null) {
            textView.setVisibility(0);
        }
        setBottomBtn(0, this.product.getMain());
        if (this.product.getSecond() != null) {
            TextView textView2 = this.mLeftBtn;
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
            setBottomBtn(1, this.product.getSecond());
        } else {
            TextView textView3 = this.mLeftBtn;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        }
        if (this.product.isToABTest() && this.mLibPdStyleInfoViewUtils.mButtonEvent == 1) {
            this.showLayerFromAddCart = true;
            TextView textView4 = this.mRightBtn;
            if (textView4 != null) {
                textView4.setVisibility(8);
            }
            TextView textView5 = this.mLeftBtn;
            if (textView5 != null) {
                textView5.setText(this.mActivity.getString(R.string.libpdstyleinfoview_ok));
                this.mLeftBtn.setTag(70);
            }
        }
        if (this.mLibPdStyleInfoViewUtils.showLayerFromZb || (this.product.getPriceInfo() != null && !TextUtils.isEmpty(this.product.getPriceInfo().channelPrice))) {
            TextView textView6 = this.mRightBtn;
            if (textView6 != null) {
                textView6.setVisibility(8);
            }
            TextView textView7 = this.mLeftBtn;
            if (textView7 != null) {
                textView7.setText(this.mActivity.getString(R.string.libpdstyleinfoview_ok));
                this.mLeftBtn.setBackgroundResource(R.drawable.libpdstyleinfoview_add2car_corner_bg_v8);
                this.mLeftBtn.setTag(2);
            }
        }
        if (this.product.showBuyLayer()) {
            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils;
            if (libPdStyleInfoViewUtils.mButtonEvent == 2) {
                libPdStyleInfoViewUtils.showLayerFromBuyNow = true;
                TextView textView8 = this.mRightBtn;
                if (textView8 != null) {
                    textView8.setVisibility(8);
                }
                TextView textView9 = this.mLeftBtn;
                if (textView9 != null) {
                    textView9.setText(this.mActivity.getString(R.string.libpdstyleinfoview_ok));
                    this.mLeftBtn.setTag(70);
                }
            }
        }
        if (this.product.isYuyue()) {
            hideAddCartBtn();
        }
    }

    private void shouldRefreshData(boolean z) {
        WareBusinessData wareBusinessData;
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        ProductDetailEntity productDetailEntity = this.product;
        if (productDetailEntity == null || (wareBusinessData = productDetailEntity.mWareBusinessData) == null || wareBusinessData.yuyueInfo == null || !checkNeedRefresh() || (libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils) == null) {
            return;
        }
        PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
        if (pdBottomButtonInfo != null) {
            pdBottomButtonInfo.isRereshCountTime = true;
            pdBottomButtonInfo.mNeedRefresh = true;
        }
        libPdStyleInfoViewUtils.refreshData(this.mStartTime, this.mBottomListener, z);
    }

    private void showBottomBtn() {
        ProductDetailEntity productDetailEntity = this.product;
        if (productDetailEntity == null) {
            return;
        }
        if (productDetailEntity.isHaveDeliveryInstall() && TextUtils.isEmpty(this.product.deliveryId)) {
            goToPd();
        } else if (dealAhStore() || paipaiBuy() || plusMtYuyue() || plusForBuyMt() || dealCarFinance() || dealRegularBuy() || dealPrescription() || dealDaJiaDian() || dealECardBuy() || dealBuyByM() || dealNewTreayPhone() || dealPhoneBuyStyle() || dealCarShop()) {
        } else {
            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils;
            if (libPdStyleInfoViewUtils != null && libPdStyleInfoViewUtils.isNew930Style) {
                if (this.dealUtil.dealYuShouAdvanceBuy()) {
                    this.mButtonInfo.businessType = PdBottomButtonInfo.BUSINESSTYPE_YUSHOU;
                    setBottomButtonInfo();
                    return;
                } else if (this.dealUtil.dealYushouDreamBuy()) {
                    this.mButtonInfo.businessType = PdBottomButtonInfo.BUSINESSTYPE_YUSHOU;
                    setBottomButtonInfo();
                    return;
                } else if (this.dealUtil.dealYushou()) {
                    this.mButtonInfo.businessType = PdBottomButtonInfo.BUSINESSTYPE_YUSHOU;
                    setBottomButtonInfo();
                    return;
                }
            } else if (dealYuShouAdvanceBuy() || dealYushouDreamBuy() || dealYushou()) {
                return;
            }
            if (dealPinGT()) {
                return;
            }
            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2 = this.mLibPdStyleInfoViewUtils;
            if (libPdStyleInfoViewUtils2 != null && libPdStyleInfoViewUtils2.isNew930Style) {
                if (this.product.isYuyue()) {
                    dealYuYue(this.mButtonInfo, new PdYuYueHelper.DefalutButtonInfo() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.e
                        {
                            LibPdBottomBtnHolder.this = this;
                        }

                        @Override // com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper.DefalutButtonInfo
                        public final boolean apply() {
                            return LibPdBottomBtnHolder.this.b();
                        }
                    });
                    LibPdStyleInfoViewUtils libPdStyleInfoViewUtils3 = this.mLibPdStyleInfoViewUtils;
                    if (libPdStyleInfoViewUtils3 != null && libPdStyleInfoViewUtils3.mBottomBtnListener != null) {
                        this.mButtonInfo.businessType = "yuyue";
                    }
                    shouldRefreshData(this.mHaveCouponId);
                    return;
                }
            } else if (dealYuYue()) {
                return;
            }
            if (dealType() || dealLoc() || dealQQAndGame() || dealOPBuy() || dealJX() || isButtonGift()) {
                return;
            }
            if (a()) {
                LibPdStyleInfoViewUtils libPdStyleInfoViewUtils4 = this.mLibPdStyleInfoViewUtils;
                if (libPdStyleInfoViewUtils4 == null || libPdStyleInfoViewUtils4.mBottomBtnListener == null) {
                    return;
                }
                this.mButtonInfo.businessType = PdBottomButtonInfo.BUSINESSTYPE_DOUBLE_BTN;
            } else if (dealCarBundlingSale() || dealProNoticeInfo()) {
            } else {
                goToPd();
            }
        }
    }

    public void buyYuyue(boolean z) {
        WareBusinessData wareBusinessData;
        LocShopInfo locShopInfo;
        if ("1".equals(this.product.getPropertyType())) {
            this.mPresenter.queryLv(null, null, null, this.libPdStyleInfoViewYuYueListener);
        } else if (z) {
            ProductDetailEntity productDetailEntity = this.product;
            if (productDetailEntity != null && (wareBusinessData = productDetailEntity.mWareBusinessData) != null && wareBusinessData.getPdjServiceSpecialFlag()) {
                WareBusinessData wareBusinessData2 = this.product.mWareBusinessData;
                String str = (wareBusinessData2 == null || (locShopInfo = wareBusinessData2.locShopInfo) == null || TextUtils.isEmpty(locShopInfo.locShopId)) ? "" : this.product.mWareBusinessData.locShopInfo.locShopId;
                WareBusinessData wareBusinessData3 = this.product.mWareBusinessData;
                if (wareBusinessData3 != null) {
                    this.mPresenter.queryLvForYuyueCar(null, wareBusinessData3.jServiceSpecialBtnInfo.buttonBizName, str, this.libPdStyleInfoViewYuYueListener);
                    return;
                }
                return;
            }
            this.mPresenter.onNowBuyClick();
        } else {
            this.mPresenter.onCarClick(true);
        }
    }

    public PdBottomButtonInfo getButtonInfo() {
        return this.mButtonInfo;
    }

    public WareBusinessFeeInfo getCurFeeInfo() {
        if (this.mCurFeeInfo != null && !isContainsCurFeeinfo()) {
            this.mCurFeeInfo = null;
            return null;
        }
        if (this.product.haveWareBusinessFeeInfo()) {
            Iterator<WareBusinessFeeInfo> it = this.product.getWareBusinessFeeInfo().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WareBusinessFeeInfo next = it.next();
                if (next != null && TextUtils.equals(next.sku, this.product.skuId)) {
                    WareBusinessFeeInfo wareBusinessFeeInfo = this.mCurFeeInfo;
                    if (wareBusinessFeeInfo == null) {
                        this.mCurFeeInfo = next;
                        break;
                    } else if (TextUtils.equals(next.ft, wareBusinessFeeInfo.ft)) {
                        this.mCurFeeInfo = next;
                        break;
                    }
                }
            }
        } else {
            this.mCurFeeInfo = null;
        }
        return this.mCurFeeInfo;
    }

    public TreayNewInfo getmCurTreayNewInfo() {
        ArrayList<TreayNewInfo> treayNewInfo = this.product.getTreayNewInfo();
        TreayNewInfo treayNewInfo2 = null;
        boolean z = false;
        if (treayNewInfo != null) {
            Iterator<TreayNewInfo> it = treayNewInfo.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TreayNewInfo next = it.next();
                if (next != null) {
                    TreayNewInfo treayNewInfo3 = this.mCurTreayNewInfo;
                    if (treayNewInfo3 != null && TextUtils.equals(next.folderId, treayNewInfo3.folderId)) {
                        this.mCurTreayNewInfo = next;
                        z = true;
                        break;
                    } else if (TextUtils.equals(next.type, "0")) {
                        treayNewInfo2 = next;
                    }
                }
            }
        }
        if (!z) {
            this.mCurTreayNewInfo = treayNewInfo2;
        }
        return this.mCurTreayNewInfo;
    }

    public void popHintInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Activity activity = this.mActivity;
        final JDDialog createJdDialogWithStyle1 = jDDialogFactory.createJdDialogWithStyle1(activity, str, activity.getResources().getString(R.string.libpdstyleinfoview_pd_product_iknow));
        createJdDialogWithStyle1.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder.3
            {
                LibPdBottomBtnHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                createJdDialogWithStyle1.dismiss();
            }
        });
        createJdDialogWithStyle1.show();
    }

    public void post(Runnable runnable) {
        Activity activity = this.mActivity;
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).post(runnable);
        }
    }

    public void setBottomBtnClickListener(LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener) {
        LibPdBottomBtnPresenter libPdBottomBtnPresenter = this.mPresenter;
        if (libPdBottomBtnPresenter != null) {
            libPdBottomBtnPresenter.setBottomBtnClickListener(libPdStyleInfoViewBottomBtnClickListener);
        }
        this.mBottomBtnClickListener = libPdStyleInfoViewBottomBtnClickListener;
    }

    void setCarStatus() {
        if (isCanBuy()) {
            PdBottomButtonInfo pdBottomButtonInfo = this.mButtonInfo;
            pdBottomButtonInfo.leftButtonInfo.buttonEnable = true;
            pdBottomButtonInfo.rightButtonInfo.buttonEnable = true;
            TextView textView = this.mLeftBtn;
            if (textView != null) {
                textView.setEnabled(true);
            }
            TextView textView2 = this.mRightBtn;
            if (textView2 != null) {
                textView2.setEnabled(true);
                return;
            }
            return;
        }
        PdBottomButtonInfo pdBottomButtonInfo2 = this.mButtonInfo;
        pdBottomButtonInfo2.leftButtonInfo.buttonEnable = false;
        pdBottomButtonInfo2.rightButtonInfo.buttonEnable = false;
        TextView textView3 = this.mLeftBtn;
        if (textView3 != null) {
            textView3.setEnabled(false);
        }
        TextView textView4 = this.mRightBtn;
        if (textView4 != null) {
            textView4.setEnabled(false);
        }
    }

    public void setProducHolderData(ProductDetailEntity productDetailEntity) {
        this.product = productDetailEntity;
        LibPdBottomBtnPresenter libPdBottomBtnPresenter = this.mPresenter;
        if (libPdBottomBtnPresenter != null) {
            libPdBottomBtnPresenter.setProducHolderData(productDetailEntity);
        }
    }

    public void showBottomBtnView() {
        showBottomBtn();
        hideAddCartBtn();
    }

    private boolean dealYuYue(PdBottomButtonInfo pdBottomButtonInfo, PdYuYueHelper.DefalutButtonInfo defalutButtonInfo) {
        String str = "leftButton is " + this.mLeftBtn;
        if (this.product.isYuyue()) {
            if (this.mLeftBtn != null && this.mRightBtn != null) {
                new PdYuYueDealUtil().dealYuYue(this.mActivity, this.mLeftBtn, this.mRightBtn, this.product, defalutButtonInfo, this.mLibPdStyleInfoViewUtils);
            } else {
                new PdYuYueHelper().generatorYuYueBtn(this.mActivity, pdBottomButtonInfo, this.product, defalutButtonInfo);
            }
        }
        return this.product.isYuyue();
    }

    public LibPdBottomBtnHolder(Activity activity, IMyActivity iMyActivity, ProductDetailEntity productDetailEntity, long j2, LibPdStyleInfoViewCarListener libPdStyleInfoViewCarListener, LibPdStyleInfoViewUtils libPdStyleInfoViewUtils, LibPdStyleInfoViewBottomBtnListener libPdStyleInfoViewBottomBtnListener, boolean z) {
        this.mOnCarClickListener = new OnCarClickListener();
        this.mStartTime = -1L;
        this.mYellowDrawable = R.drawable.libpdstyleinfoview_same_corner_bg_v8;
        this.mRedDrawable = R.drawable.libpdstyleinfoview_add2car_corner_bg_v8;
        this.mJxYellowDrawable = R.drawable.libpdstyleinfoview_bottom_btn_jx_selector;
        this.showLayerFromSpecial = false;
        this.isYushouStyleOpen = false;
        this.isStyleLayerShow = false;
        this.isJx = false;
        this.showLayerFromAddCart = false;
        this.libPdStyleInfoViewYuYueListener = new AnonymousClass1();
        this.couponReceiveListener = new AnonymousClass4();
        this.mActivity = activity;
        this.mIMyActivity = iMyActivity;
        this.product = productDetailEntity;
        this.mCarListener = libPdStyleInfoViewCarListener;
        this.mBottomListener = libPdStyleInfoViewBottomBtnListener;
        this.mLibPdStyleInfoViewUtils = libPdStyleInfoViewUtils;
        this.mHaveCouponId = z;
        this.mPresenter = new LibPdBottomBtnPresenter(productDetailEntity, activity, iMyActivity, libPdStyleInfoViewCarListener, libPdStyleInfoViewUtils);
        PdBottomButtonInfo pdBottomButtonInfo = new PdBottomButtonInfo();
        this.mButtonInfo = pdBottomButtonInfo;
        pdBottomButtonInfo.leftButtonInfo = new PdBottomLeftButtonInfo();
        this.mButtonInfo.rightButtonInfo = new PdBottomRightButtonInfo();
        this.mStartTime = j2;
        this.dealUtil = new YuShouDealUtil(this.product, this.mActivity, this.mButtonInfo, false, j2, libPdStyleInfoViewUtils, this.mBottomListener, this.mHaveCouponId);
        showBottomBtnView();
    }
}
