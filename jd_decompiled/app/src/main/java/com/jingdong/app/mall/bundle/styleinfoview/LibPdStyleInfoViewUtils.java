package com.jingdong.app.mall.bundle.styleinfoview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnClickListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCarListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewDialogListener;
import com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.entity.productdetail.PDStylePropertyEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.common.utils.LangUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class LibPdStyleInfoViewUtils implements ProductDetailEngine.ProductEngineListner {
    public static String BBTF = "bbtf";
    public static final String EXTRAS_JSON_STR = "extras.json.str";
    public static final String EXTRA_BUSINESS_ORIGIN = "extra.business.origin";
    public static final String EXTRA_PT = "extra.pt";
    public static final String EXTRA_ROOMID = "extra.roomid";
    public static final String EXTRA_SOURCE_TYPE = "extra.source.type";
    public static final String EXTRA_SUPPER_ROOM_PROMO = "supperRoomPromo";
    public LibPdStyleInfoViewDialogListener dialogListener;
    public boolean isNew930Style;
    public LibPdStyleInfoViewBottomBtnClickListener mBottomBtnClickListener;
    public LibPdStyleInfoViewBottomBtnListener mBottomBtnListener;
    public int mButtonEvent;
    private LibPdStyleInfoViewCarListener mCarListener;
    private IMyActivity mContext;
    private ProductDetailEngine mEngine;
    public LibPdBottomBtnHolder mHolder;
    private UnBottomDialog mLayerDialog;
    private ProductDetailEntity mProduct;
    private LibPdStyleInfoView mStyleInfoView;
    public String mSourceType = "";
    public boolean showLayerFromBuyNow = false;
    public boolean showLayerFromYuYue = false;
    public boolean showLayerFromZb = false;
    public String mDialogInitSkuId = "";
    private Bundle mExtras = null;

    public LibPdStyleInfoViewUtils(IMyActivity iMyActivity) {
        this.mContext = iMyActivity;
        initStyleInfoView();
        initJdBottomDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(LibPdStyleInfoViewBottomBtnListener libPdStyleInfoViewBottomBtnListener, boolean z, int i2, ProductDetailEntity productDetailEntity, JDJSONObject jDJSONObject) {
        String str = "product engine callback type = " + i2 + " json = " + jDJSONObject;
        if (i2 == 1) {
            LibPdBottomBtnHolder libPdBottomBtnHolder = new LibPdBottomBtnHolder(this.mContext.getThisActivity(), this.mContext, productDetailEntity, 0L, this.mCarListener, this, libPdStyleInfoViewBottomBtnListener, z);
            this.mHolder = libPdBottomBtnHolder;
            if (libPdStyleInfoViewBottomBtnListener != null) {
                PdBottomButtonInfo buttonInfo = libPdBottomBtnHolder.getButtonInfo();
                if (buttonInfo != null) {
                    buttonInfo.dataJson = jDJSONObject != null ? jDJSONObject.toJSONString() : "";
                    buttonInfo.isRereshCountTime = true;
                    String str2 = "refreshData libPdBottomBtnInfo info = " + buttonInfo.dataJson;
                }
                libPdStyleInfoViewBottomBtnListener.libPdBottomBtnInfo(buttonInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(boolean z, int i2, ProductDetailEntity productDetailEntity, JDJSONObject jDJSONObject) {
        LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener;
        if (i2 != 1 || (libPdStyleInfoViewBottomBtnClickListener = this.mBottomBtnClickListener) == null) {
            return;
        }
        libPdStyleInfoViewBottomBtnClickListener.libPdBottomRefreshPDView(null, jDJSONObject != null ? jDJSONObject.toJSONString() : "", true, null, z);
    }

    private void dialogShow() {
        UnBottomDialog unBottomDialog = this.mLayerDialog;
        if (unBottomDialog == null || unBottomDialog.isShowing()) {
            return;
        }
        this.mLayerDialog.show();
    }

    private void initJdBottomDialog() {
        if (this.mLayerDialog == null && (this.mContext instanceof Context)) {
            UnBottomDialog unBottomDialog = new UnBottomDialog((Context) this.mContext);
            this.mLayerDialog = unBottomDialog;
            unBottomDialog.setButtonColor(0);
            this.mLayerDialog.mTitleContentLayout.setBackgroundResource(R.color.libpdstyleinfoview_white);
            this.mLayerDialog.setTitleTextColor(((Context) this.mContext).getResources().getColor(R.color.libpdstyleinfoview_common_black));
        }
        UnBottomDialog unBottomDialog2 = this.mLayerDialog;
        if (unBottomDialog2 != null) {
            unBottomDialog2.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils.1
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    LibPdStyleInfoViewDialogListener libPdStyleInfoViewDialogListener = LibPdStyleInfoViewUtils.this.dialogListener;
                    if (libPdStyleInfoViewDialogListener != null) {
                        libPdStyleInfoViewDialogListener.libPdStyleDialogShow();
                    }
                }
            });
            this.mLayerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    LibPdStyleInfoViewDialogListener libPdStyleInfoViewDialogListener = LibPdStyleInfoViewUtils.this.dialogListener;
                    if (libPdStyleInfoViewDialogListener != null) {
                        libPdStyleInfoViewDialogListener.libPdStyleDialogDis();
                    }
                    LibPdStyleInfoViewUtils libPdStyleInfoViewUtils = LibPdStyleInfoViewUtils.this;
                    libPdStyleInfoViewUtils.mDialogInitSkuId = "";
                    libPdStyleInfoViewUtils.showLayerFromYuYue = false;
                    libPdStyleInfoViewUtils.showLayerFromBuyNow = false;
                    libPdStyleInfoViewUtils.showLayerFromZb = false;
                    if (libPdStyleInfoViewUtils.mCarListener != null) {
                        LibPdStyleInfoViewUtils.this.mCarListener = null;
                    }
                    LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2 = LibPdStyleInfoViewUtils.this;
                    if (libPdStyleInfoViewUtils2.mBottomBtnListener != null) {
                        libPdStyleInfoViewUtils2.mBottomBtnListener = null;
                    }
                    if (libPdStyleInfoViewUtils2.mBottomBtnClickListener != null) {
                        libPdStyleInfoViewUtils2.mBottomBtnClickListener = null;
                    }
                    if (libPdStyleInfoViewUtils2.mEngine != null) {
                        LibPdStyleInfoViewUtils.this.mEngine.onDestroy();
                    }
                }
            });
            this.mLayerDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    return false;
                }
            });
        }
    }

    private void initStyleInfoView() {
        if (this.mStyleInfoView == null) {
            IMyActivity iMyActivity = this.mContext;
            if (iMyActivity instanceof Context) {
                LibPdStyleInfoView libPdStyleInfoView = (LibPdStyleInfoView) LayoutInflater.from((Context) iMyActivity).inflate(R.layout.libpdstyleinfoview_info_layout, (ViewGroup) null);
                this.mStyleInfoView = libPdStyleInfoView;
                libPdStyleInfoView.setListener(this);
            }
        }
    }

    public boolean checkStyleButtonClick() {
        UnBottomDialog unBottomDialog;
        WareBusinessData wareBusinessData;
        LibPdStyleInfoView libPdStyleInfoView = this.mStyleInfoView;
        if (libPdStyleInfoView != null && libPdStyleInfoView.mStyleSizeView != null && (unBottomDialog = this.mLayerDialog) != null && unBottomDialog.isShowing()) {
            StringBuilder sb = new StringBuilder();
            List<PDStylePropertyEntity> styleProperty = this.mStyleInfoView.mStyleSizeView.getStyleProperty();
            if (styleProperty != null) {
                int i2 = 0;
                while (true) {
                    if (i2 < styleProperty.size()) {
                        PDStylePropertyEntity pDStylePropertyEntity = styleProperty.get(i2);
                        if (pDStylePropertyEntity != null && pDStylePropertyEntity.status == 0) {
                            sb.append(pDStylePropertyEntity.title);
                            sb.append(LangUtils.SINGLE_SPACE);
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
            Context context = (Context) this.mContext;
            if (sb.length() != 0) {
                ProductDetailEntity productDetailEntity = this.mProduct;
                if (productDetailEntity != null && (wareBusinessData = productDetailEntity.mWareBusinessData) != null && wareBusinessData.newStyle) {
                    PDUtils.showToastCenterIcon(context, (byte) 1, context.getString(R.string.libpdstyleinfoview_please_select) + sb.toString());
                } else {
                    PDUtils.showToastCenterNormal(context, context.getString(R.string.libpdstyleinfoview_please_select) + sb.toString());
                }
                return false;
            }
        }
        return true;
    }

    public void clickBottomBtn(String str, String str2, String str3, int i2, LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener) {
        clickBottomBtn(null, str, str2, str3, i2, libPdStyleInfoViewBottomBtnClickListener);
    }

    public void dismiss() {
        UnBottomDialog unBottomDialog = this.mLayerDialog;
        if (unBottomDialog == null || !unBottomDialog.isShowing()) {
            return;
        }
        this.mLayerDialog.dismiss();
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.ProductEngineListner
    public void engineNetStatus(int i2, ProductDetailEntity productDetailEntity, JDJSONObject jDJSONObject) {
        LibPdStyleInfoView libPdStyleInfoView;
        if (i2 == 1) {
            LibPdStyleInfoView libPdStyleInfoView2 = this.mStyleInfoView;
            if (libPdStyleInfoView2 != null) {
                libPdStyleInfoView2.setProgressLoading(false, false);
                this.mStyleInfoView.setNetErrorLayout(false);
                this.mStyleInfoView.initParamData(productDetailEntity);
                this.mStyleInfoView.bindData2View(this.mContext, this.mCarListener, this.mBottomBtnClickListener);
            }
        } else if (i2 == 2 && (libPdStyleInfoView = this.mStyleInfoView) != null) {
            libPdStyleInfoView.setNetErrorLayout(true);
            if (!this.mStyleInfoView.mIsClickNetError || this.mContext.getThisActivity() == null) {
                return;
            }
            PDUtils.showToastCenterNormal(this.mContext.getThisActivity(), this.mContext.getThisActivity().getResources().getString(R.string.libpdstyleinfoview_network_error_text_toast));
        }
    }

    public boolean isShowDialog() {
        UnBottomDialog unBottomDialog = this.mLayerDialog;
        if (unBottomDialog != null) {
            return unBottomDialog.isShowing();
        }
        return false;
    }

    public void openStyleDailog(String str, String str2) {
        openStyleDailog(str, str2, "");
    }

    public void refreshData(long j2, final LibPdStyleInfoViewBottomBtnListener libPdStyleInfoViewBottomBtnListener, final boolean z) {
        String str = "refreshData startTime = " + j2;
        new RuntimeException();
        if (this.mProduct != null) {
            ProductDetailEngine productDetailEngine = new ProductDetailEngine(this.mContext, new HttpGroupUtil(), this.mProduct);
            this.mEngine = productDetailEngine;
            productDetailEngine.setProductEngineListner(new ProductDetailEngine.ProductEngineListner() { // from class: com.jingdong.app.mall.bundle.styleinfoview.a
                @Override // com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.ProductEngineListner
                public final void engineNetStatus(int i2, ProductDetailEntity productDetailEntity, JDJSONObject jDJSONObject) {
                    LibPdStyleInfoViewUtils.this.b(libPdStyleInfoViewBottomBtnListener, z, i2, productDetailEntity, jDJSONObject);
                }
            });
            ProductDetailEngine productDetailEngine2 = this.mEngine;
            ProductDetailEntity productDetailEntity = this.mProduct;
            productDetailEngine2.queryProduct(productDetailEntity.skuId, productDetailEntity.source);
        }
    }

    public void requestSku(String str, String str2) {
        this.mDialogInitSkuId = "";
        ProductDetailEntity productDetailEntity = new ProductDetailEntity(PDUtils.getPDManagerKey());
        this.mProduct = productDetailEntity;
        productDetailEntity.skuId = str;
        Bundle bundle = this.mExtras;
        productDetailEntity.roomId = bundle != null ? bundle.getString(EXTRA_ROOMID) : null;
        ProductDetailEntity productDetailEntity2 = this.mProduct;
        Bundle bundle2 = this.mExtras;
        productDetailEntity2.businessOrigin = bundle2 != null ? bundle2.getString(EXTRA_BUSINESS_ORIGIN) : null;
        ProductDetailEntity productDetailEntity3 = this.mProduct;
        Bundle bundle3 = this.mExtras;
        productDetailEntity3.supperRoomPromo = bundle3 != null ? bundle3.getString(EXTRA_SUPPER_ROOM_PROMO) : null;
        ProductDetailEntity productDetailEntity4 = this.mProduct;
        Bundle bundle4 = this.mExtras;
        productDetailEntity4.pt = bundle4 != null ? bundle4.getString(EXTRA_PT) : "";
        this.mProduct.bbtf = PDUtils.getBbtf(this.mExtras);
        if (!TextUtils.isEmpty(this.mSourceType)) {
            this.mProduct.sourceType = this.mSourceType;
        }
        ProductDetailEngine productDetailEngine = new ProductDetailEngine(this.mContext, new HttpGroupUtil(), this.mProduct);
        this.mEngine = productDetailEngine;
        productDetailEngine.setProductEngineListner(this);
        this.mEngine.queryProduct(str, str2, this.mExtras);
    }

    public void setLibPdStyleInfoViewDialogListener(LibPdStyleInfoViewDialogListener libPdStyleInfoViewDialogListener) {
        this.dialogListener = libPdStyleInfoViewDialogListener;
    }

    public void setProductData(String str, String str2, String str3, String str4, boolean z, LibPdStyleInfoViewBottomBtnListener libPdStyleInfoViewBottomBtnListener) {
        setProductData(null, str, str2, str3, str4, z, libPdStyleInfoViewBottomBtnListener);
    }

    public void showDailog(String str, String str2, LibPdStyleInfoViewCarListener libPdStyleInfoViewCarListener) {
        showDailog(str, str2, null, libPdStyleInfoViewCarListener);
    }

    public void clickBottomBtn(Bundle bundle, String str, String str2, String str3, int i2, LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener) {
        this.isNew930Style = true;
        String str4 = "clickBottomBtn skuId = " + str + " source = " + str2 + " buttonEvent = " + i2;
        String str5 = "clickBottomBtn json = " + str3;
        JDJSONObject parseObject = JDJSON.parseObject(str3);
        ProductDetailEntity productDetailEntity = new ProductDetailEntity(PDUtils.getPDManagerKey());
        this.mProduct = productDetailEntity;
        productDetailEntity.skuId = str;
        this.mProduct.updateWareBusinessData(new PDWareBusinessEntity(parseObject));
        ProductDetailEntity productDetailEntity2 = this.mProduct;
        productDetailEntity2.source = str2;
        if (bundle != null) {
            productDetailEntity2.sourceType = bundle.getString(EXTRA_SOURCE_TYPE, "");
            this.mSourceType = this.mProduct.sourceType;
        }
        this.mButtonEvent = i2;
        this.mBottomBtnClickListener = libPdStyleInfoViewBottomBtnClickListener;
        LibPdBottomBtnHolder libPdBottomBtnHolder = this.mHolder;
        if (libPdBottomBtnHolder != null) {
            libPdBottomBtnHolder.setBottomBtnClickListener(libPdStyleInfoViewBottomBtnClickListener);
            this.mHolder.setProducHolderData(this.mProduct);
            View view = new View(this.mContext.getThisActivity());
            view.setTag(Integer.valueOf(i2));
            LibPdBottomBtnHolder libPdBottomBtnHolder2 = this.mHolder;
            libPdBottomBtnHolder2.getClass();
            new LibPdBottomBtnHolder.OnCarClickListener().onClick(view);
        }
    }

    public void openStyleDailog(String str, String str2, String str3) {
        this.mDialogInitSkuId = str;
        initStyleInfoView();
        initJdBottomDialog();
        LibPdStyleInfoView libPdStyleInfoView = this.mStyleInfoView;
        if (libPdStyleInfoView != null) {
            libPdStyleInfoView.setProgressLoading(true, true);
        }
        ProductDetailEntity productDetailEntity = new ProductDetailEntity(PDUtils.getPDManagerKey());
        this.mProduct = productDetailEntity;
        productDetailEntity.skuId = str;
        productDetailEntity.sourceType = str3;
        ProductDetailEngine productDetailEngine = new ProductDetailEngine(this.mContext, new HttpGroupUtil(), this.mProduct);
        this.mEngine = productDetailEngine;
        productDetailEngine.setProductEngineListner(this);
        this.mEngine.queryProduct(str, str2);
        UnBottomDialog unBottomDialog = this.mLayerDialog;
        if (unBottomDialog != null) {
            unBottomDialog.addContentWithHeight((View) this.mStyleInfoView, (String) null, false);
            if (this.mLayerDialog.isShowing()) {
                return;
            }
            IMyActivity iMyActivity = this.mContext;
            if (!(iMyActivity instanceof Activity) || ((Activity) iMyActivity).isFinishing()) {
                return;
            }
            this.mLayerDialog.setDarkMode(this.mProduct.isDarkTheme());
            dialogShow();
            ProductDetailEntity productDetailEntity2 = this.mProduct;
            PDUtils.exposureForTc("Component_SpecExpo", productDetailEntity2.skuId, productDetailEntity2.source);
        }
    }

    public void setProductData(Bundle bundle, String str, String str2, String str3, String str4, boolean z, LibPdStyleInfoViewBottomBtnListener libPdStyleInfoViewBottomBtnListener) {
        PdBottomButtonInfo buttonInfo;
        this.isNew930Style = true;
        this.mBottomBtnListener = libPdStyleInfoViewBottomBtnListener;
        JDJSONObject parseObject = JDJSON.parseObject(str3);
        ProductDetailEntity productDetailEntity = new ProductDetailEntity(PDUtils.getPDManagerKey());
        this.mProduct = productDetailEntity;
        productDetailEntity.skuId = str;
        if (bundle != null) {
            productDetailEntity.sourceType = bundle.getString(EXTRA_SOURCE_TYPE, "");
            this.mSourceType = this.mProduct.sourceType;
        }
        this.mProduct.updateWareBusinessData(new PDWareBusinessEntity(parseObject));
        this.mProduct.source = str2;
        String str5 = "====skuid==" + this.mProduct.skuId;
        LibPdBottomBtnHolder libPdBottomBtnHolder = new LibPdBottomBtnHolder(this.mContext.getThisActivity(), this.mContext, this.mProduct, TextUtils.isEmpty(str4) ? 0L : Long.parseLong(str4), this.mCarListener, this, libPdStyleInfoViewBottomBtnListener, z);
        this.mHolder = libPdBottomBtnHolder;
        if (this.mBottomBtnListener == null || (buttonInfo = libPdBottomBtnHolder.getButtonInfo()) == null) {
            return;
        }
        buttonInfo.dataJson = str3;
        String str6 = "setProduct libPdBottomBtnInfo info = " + buttonInfo.dataJson + " needRefresh = " + buttonInfo.mNeedRefresh;
        if (buttonInfo.mNeedRefresh) {
            return;
        }
        this.mBottomBtnListener.libPdBottomBtnInfo(buttonInfo);
        if (buttonInfo.rightButtonInfo != null) {
            String str7 = "buttoninfo===" + ((Object) buttonInfo.rightButtonInfo.buttonText) + "====sub==" + buttonInfo.rightButtonInfo.buttonSubText + "==skuid==" + this.mProduct.skuId;
        }
    }

    public void showDailog(String str, String str2, Bundle bundle, LibPdStyleInfoViewCarListener libPdStyleInfoViewCarListener) {
        this.isNew930Style = false;
        this.mExtras = bundle;
        if (libPdStyleInfoViewCarListener != null) {
            this.mCarListener = libPdStyleInfoViewCarListener;
        }
        LibPdStyleInfoView libPdStyleInfoView = this.mStyleInfoView;
        if (libPdStyleInfoView != null) {
            libPdStyleInfoView.setProgressLoading(true, true);
        }
        try {
            ProductDetailEntity productDetailEntity = new ProductDetailEntity(PDUtils.getPDManagerKey());
            this.mProduct = productDetailEntity;
            productDetailEntity.skuId = str;
            Bundle bundle2 = this.mExtras;
            productDetailEntity.roomId = bundle2 != null ? bundle2.getString(EXTRA_ROOMID) : null;
            ProductDetailEntity productDetailEntity2 = this.mProduct;
            Bundle bundle3 = this.mExtras;
            productDetailEntity2.businessOrigin = bundle3 != null ? bundle3.getString(EXTRA_BUSINESS_ORIGIN) : null;
            ProductDetailEntity productDetailEntity3 = this.mProduct;
            Bundle bundle4 = this.mExtras;
            productDetailEntity3.supperRoomPromo = bundle4 != null ? bundle4.getString(EXTRA_SUPPER_ROOM_PROMO) : null;
            this.mProduct.bbtf = PDUtils.getBbtf(this.mExtras);
            ProductDetailEntity productDetailEntity4 = this.mProduct;
            Bundle bundle5 = this.mExtras;
            productDetailEntity4.pt = bundle5 != null ? bundle5.getString(EXTRA_PT) : "";
            if (!TextUtils.isEmpty(this.mProduct.roomId)) {
                this.showLayerFromZb = true;
            }
            ProductDetailEngine productDetailEngine = new ProductDetailEngine(this.mContext, new HttpGroupUtil(), this.mProduct);
            this.mEngine = productDetailEngine;
            productDetailEngine.setProductEngineListner(this);
            this.mEngine.queryProduct(str, str2, this.mExtras);
            UnBottomDialog unBottomDialog = this.mLayerDialog;
            if (unBottomDialog != null) {
                unBottomDialog.addContentWithHeight((View) this.mStyleInfoView, (String) null, false);
                if (this.mLayerDialog.isShowing()) {
                    return;
                }
                IMyActivity iMyActivity = this.mContext;
                if (!(iMyActivity instanceof Activity) || ((Activity) iMyActivity).isFinishing()) {
                    return;
                }
                this.mLayerDialog.setDarkMode(this.mProduct.isDarkTheme());
                dialogShow();
                ProductDetailEntity productDetailEntity5 = this.mProduct;
                PDUtils.exposureForTc("Component_SpecExpo", productDetailEntity5.skuId, productDetailEntity5.source);
            }
        } catch (Exception unused) {
        }
    }

    public void refreshData(final boolean z) {
        if (this.mProduct != null) {
            ProductDetailEngine productDetailEngine = new ProductDetailEngine(this.mContext, new HttpGroupUtil(), this.mProduct);
            this.mEngine = productDetailEngine;
            productDetailEngine.setProductEngineListner(new ProductDetailEngine.ProductEngineListner() { // from class: com.jingdong.app.mall.bundle.styleinfoview.b
                @Override // com.jingdong.app.mall.bundle.styleinfoview.engine.ProductDetailEngine.ProductEngineListner
                public final void engineNetStatus(int i2, ProductDetailEntity productDetailEntity, JDJSONObject jDJSONObject) {
                    LibPdStyleInfoViewUtils.this.d(z, i2, productDetailEntity, jDJSONObject);
                }
            });
            ProductDetailEngine productDetailEngine2 = this.mEngine;
            ProductDetailEntity productDetailEntity = this.mProduct;
            productDetailEngine2.queryProduct(productDetailEntity.skuId, productDetailEntity.source);
        }
    }
}
