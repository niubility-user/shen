package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDOperAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDVerificationEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PdAddRiskCheckInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDBaseDeepLinkHelper;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDDataUtil;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDManager;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.mall.bundle.styleinfoview.utils.VerificationUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.entity.UserAddress;
import com.jingdong.common.login.DeviceFinger;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import java.lang.reflect.Field;

/* loaded from: classes3.dex */
public class PDYuYueVerifyDialog extends Dialog implements View.OnClickListener, TextWatcher {
    private EditText editView;
    private ImageView imgView;
    private boolean isDestroy;
    private LibPdStyleInfoViewYuYueListener mCallbacks;
    private Context mContext;
    private PdYuYueMadDialogHolder mPdYuYueVerifyDialog;
    private ProductDetailEntity mProduct;
    private Resources mResources;
    private PDOperAppointEntity.FollowShopContent mShopContent;
    private UserAddress mUserAddress;
    private PDVerificationEntity mVerificationEntity;
    private PDOperAppointEntity.OfficeAccount officeAccount;
    private TextView textView;

    public PDYuYueVerifyDialog(Context context, LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        super(context, R.style.fill_order_dialog);
        this.isDestroy = false;
        this.mContext = context;
        this.mResources = context.getResources();
        this.mCallbacks = libPdStyleInfoViewYuYueListener;
    }

    private View getDialogContentView(final PdAddRiskCheckInfo pdAddRiskCheckInfo) {
        View inflate = ImageUtil.inflate(this.mContext, com.jingdong.app.mall.bundle.styleinfoview.R.layout.libpdstyleinfoview_yuyue_select_address_layout, null, false);
        inflate.setLayoutParams(new RelativeLayout.LayoutParams(-1, PDUtils.dip2px(40.0f)));
        TextView textView = (TextView) inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.detail_yuyue_prompt_text);
        TextView textView2 = (TextView) inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.detail_yuyue_operate_text);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.detail_select_address_layout);
        if (!TextUtils.isEmpty(pdAddRiskCheckInfo.addressContent)) {
            textView.setText(pdAddRiskCheckInfo.addressContent);
        }
        if (!TextUtils.isEmpty(pdAddRiskCheckInfo.guide)) {
            textView2.setText(pdAddRiskCheckInfo.guide);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PDBaseDeepLinkHelper.gotoMWithUrl(PDYuYueVerifyDialog.this.mContext, pdAddRiskCheckInfo.inputAddressUrl);
            }
        });
        return inflate;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private JDDialog getMyOrderDlg(final PDOperAppointEntity pDOperAppointEntity) {
        String str;
        String str2;
        PdAddRiskCheckInfo pdAddRiskCheckInfo;
        boolean z;
        String str3;
        final JDDialog jDDialog;
        int indexOf;
        PDOperAppointEntity.FollowShopContent followShopContent;
        SpannableString spannableString;
        PDOperAppointEntity.OfficeAccount officeAccount;
        int lastIndexOf;
        int indexOf2;
        String string;
        setDialogCanAutoDismiss(true);
        dismiss();
        String str4 = pDOperAppointEntity.flag ? pDOperAppointEntity.content : pDOperAppointEntity.msg;
        boolean z2 = pDOperAppointEntity.isAddCartSuccess;
        String str5 = pDOperAppointEntity.title;
        if (z2 && !TextUtils.isEmpty(pDOperAppointEntity.addCartTitle)) {
            str5 = pDOperAppointEntity.addCartTitle;
        }
        String str6 = str5;
        this.mShopContent = pDOperAppointEntity.followShopContent;
        this.officeAccount = pDOperAppointEntity.officeAccount;
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        String string2 = this.mResources.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_product_iknow);
        String string3 = this.mResources.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_product_yuyue_share);
        if (wareBusinessData != null) {
            WarePropertyInfo warePropertyInfo = wareBusinessData.property;
            boolean z3 = warePropertyInfo != null ? warePropertyInfo.isFxyl : false;
            WareYuYueInfo wareYuYueInfo = wareBusinessData.yuyueInfo;
            if (wareYuYueInfo != null) {
                if (!TextUtils.isEmpty(wareYuYueInfo.buttonLeftText)) {
                    string2 = wareBusinessData.yuyueInfo.buttonLeftText;
                }
                if (TextUtils.equals("2", pDOperAppointEntity.drawTaskType)) {
                    string = pDOperAppointEntity.drawMoreTitle;
                } else if (!TextUtils.isEmpty(wareBusinessData.yuyueInfo.buttonRightText)) {
                    string = wareBusinessData.yuyueInfo.buttonRightText;
                } else if (z3) {
                    string = this.mResources.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_share_gift);
                }
                str2 = string;
                str = string2;
                pdAddRiskCheckInfo = pDOperAppointEntity.addRiskCheckInfo;
                if (pdAddRiskCheckInfo == null) {
                    jDDialog = JDDialogFactory.getInstance().createJdDialogWithStyle9(this.mContext, str6, str4, getDialogContentView(pdAddRiskCheckInfo), str, str2);
                    setExposureMa("1");
                    z = z2;
                    str3 = str4;
                } else {
                    JDDialog newJDDialog = JDDialog.newJDDialog(this.mContext);
                    PdYuYueMadDialogHolder pdYuYueMadDialogHolder = new PdYuYueMadDialogHolder(this.mContext, newJDDialog);
                    this.mPdYuYueVerifyDialog = pdYuYueMadDialogHolder;
                    z = z2;
                    str3 = str4;
                    pdYuYueMadDialogHolder.bindData2View(str4, str6, str, str2, pDOperAppointEntity);
                    setExposureMa("0");
                    jDDialog = newJDDialog;
                }
                if (!z) {
                    if (!TextUtils.isEmpty(pDOperAppointEntity.addCartContent)) {
                        jDDialog.setMessage(getSpannableString(pDOperAppointEntity, pDOperAppointEntity.addCartContent));
                    } else if (!TextUtils.isEmpty(str3)) {
                        jDDialog.setMessage(getSpannableString(pDOperAppointEntity, str3));
                    }
                } else if (!TextUtils.isEmpty(pDOperAppointEntity.link) && !TextUtils.isEmpty(pDOperAppointEntity.guide)) {
                    String str7 = str3 + ("  " + pDOperAppointEntity.guide);
                    int length = str3.length();
                    int length2 = str7.length();
                    SpannableString spannableString2 = new SpannableString(str7);
                    ClickUrlSpan clickUrlSpan = new ClickUrlSpan(this.mContext, pDOperAppointEntity.link);
                    clickUrlSpan.setEvents("ReservePopup_MyReserve", this.mProduct.skuId);
                    spannableString2.setSpan(clickUrlSpan, length, length2, 33);
                    spannableString2.setSpan(new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK), length, length2, 33);
                    PDOperAppointEntity.OfficeAccount officeAccount2 = pDOperAppointEntity.officeAccount;
                    if (officeAccount2 != null && !TextUtils.isEmpty(officeAccount2.text) && str7.contains(pDOperAppointEntity.officeAccount.text) && (indexOf = str7.indexOf(pDOperAppointEntity.officeAccount.text)) != -1) {
                        spannableString2.setSpan(new ForegroundColorSpan(PDUtils.parseColor(pDOperAppointEntity.officeAccount.color, "#4D88FF")), indexOf, pDOperAppointEntity.officeAccount.text.length() + indexOf, 33);
                        Context context = this.mContext;
                        spannableString2.setSpan(new ClickUrlSpanCopy(context, this.mProduct.skuId, pDOperAppointEntity.officeAccount.text, context.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_success), this.mContext.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_failure)), indexOf, pDOperAppointEntity.officeAccount.text.length() + indexOf, 33);
                        spannableString2.setSpan(new StyleSpan(1), indexOf, pDOperAppointEntity.officeAccount.text.length() + indexOf, 33);
                    }
                    jDDialog.setMessage(spannableString2);
                } else if (!TextUtils.isEmpty(str3)) {
                    jDDialog.setMessage(getSpannableString(pDOperAppointEntity, str3));
                }
                followShopContent = pDOperAppointEntity.followShopContent;
                if (followShopContent != null && !TextUtils.isEmpty(followShopContent.text) && !TextUtils.isEmpty(pDOperAppointEntity.followShopContent.keyWord)) {
                    String str8 = str3 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + pDOperAppointEntity.followShopContent.text;
                    spannableString = new SpannableString(str8);
                    officeAccount = pDOperAppointEntity.officeAccount;
                    if (officeAccount != null && !TextUtils.isEmpty(officeAccount.text) && str8.contains(pDOperAppointEntity.officeAccount.text) && (indexOf2 = str8.indexOf(pDOperAppointEntity.officeAccount.text)) != -1) {
                        spannableString.setSpan(new ForegroundColorSpan(PDUtils.parseColor(pDOperAppointEntity.officeAccount.color, "#4D88FF")), indexOf2, pDOperAppointEntity.officeAccount.text.length() + indexOf2, 33);
                        Context context2 = this.mContext;
                        spannableString.setSpan(new ClickUrlSpanCopy(context2, this.mProduct.skuId, pDOperAppointEntity.officeAccount.text, context2.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_success), this.mContext.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_failure)), indexOf2, pDOperAppointEntity.officeAccount.text.length() + indexOf2, 33);
                        spannableString.setSpan(new StyleSpan(1), indexOf2, pDOperAppointEntity.officeAccount.text.length() + indexOf2, 33);
                    }
                    lastIndexOf = str8.lastIndexOf(pDOperAppointEntity.followShopContent.keyWord);
                    if (lastIndexOf != -1) {
                        int length3 = pDOperAppointEntity.followShopContent.keyWord.length() + lastIndexOf;
                        Context context3 = this.mContext;
                        ProductDetailEntity productDetailEntity = this.mProduct;
                        ClickUrlSpanForYuYue clickUrlSpanForYuYue = new ClickUrlSpanForYuYue(context3, productDetailEntity.skuId, productDetailEntity.getPlusMakeMoreTimeShopId(), this.mProduct.mManageKey, jDDialog);
                        ProductDetailEntity productDetailEntity2 = this.mProduct;
                        if (productDetailEntity2 != null) {
                            clickUrlSpanForYuYue.setEvents("ReservePopup_JDShop", productDetailEntity2.skuId);
                        }
                        spannableString.setSpan(clickUrlSpanForYuYue, lastIndexOf, length3, 33);
                        spannableString.setSpan(new ForegroundColorSpan(PDUtils.parseColor(pDOperAppointEntity.followShopContent.color, "")), lastIndexOf, length3, 33);
                        spannableString.setSpan(new StyleSpan(1), lastIndexOf, length3, 33);
                    }
                    jDDialog.setMessage(spannableString);
                }
                jDDialog.messageView.setMovementMethod(LinkMovementMethod.getInstance());
                if (pDOperAppointEntity.flag) {
                    jDDialog.messageView.setGravity(3);
                }
                jDDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        jDDialog.dismiss();
                    }
                });
                jDDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (TextUtils.equals("2", pDOperAppointEntity.drawTaskType)) {
                            PDBaseDeepLinkHelper.gotoMWithUrl(PDYuYueVerifyDialog.this.mContext, pDOperAppointEntity.drawMoreUrl);
                            return;
                        }
                        PDManager.getInstances(PDYuYueVerifyDialog.this.mProduct.mManageKey).post("pd_ProductDetailActivity", "pd_action_event_to_share");
                        jDDialog.dismiss();
                        if (PDYuYueVerifyDialog.this.mProduct != null) {
                            PDYuYueVerifyDialog.this.mProduct.isFxyl();
                        }
                    }
                });
                return jDDialog;
            }
        }
        str = string2;
        str2 = string3;
        pdAddRiskCheckInfo = pDOperAppointEntity.addRiskCheckInfo;
        if (pdAddRiskCheckInfo == null) {
        }
        if (!z) {
        }
        followShopContent = pDOperAppointEntity.followShopContent;
        if (followShopContent != null) {
            String str82 = str3 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + pDOperAppointEntity.followShopContent.text;
            spannableString = new SpannableString(str82);
            officeAccount = pDOperAppointEntity.officeAccount;
            if (officeAccount != null) {
                spannableString.setSpan(new ForegroundColorSpan(PDUtils.parseColor(pDOperAppointEntity.officeAccount.color, "#4D88FF")), indexOf2, pDOperAppointEntity.officeAccount.text.length() + indexOf2, 33);
                Context context22 = this.mContext;
                spannableString.setSpan(new ClickUrlSpanCopy(context22, this.mProduct.skuId, pDOperAppointEntity.officeAccount.text, context22.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_success), this.mContext.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_failure)), indexOf2, pDOperAppointEntity.officeAccount.text.length() + indexOf2, 33);
                spannableString.setSpan(new StyleSpan(1), indexOf2, pDOperAppointEntity.officeAccount.text.length() + indexOf2, 33);
            }
            lastIndexOf = str82.lastIndexOf(pDOperAppointEntity.followShopContent.keyWord);
            if (lastIndexOf != -1) {
            }
            jDDialog.setMessage(spannableString);
        }
        jDDialog.messageView.setMovementMethod(LinkMovementMethod.getInstance());
        if (pDOperAppointEntity.flag) {
        }
        jDDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                jDDialog.dismiss();
            }
        });
        jDDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.equals("2", pDOperAppointEntity.drawTaskType)) {
                    PDBaseDeepLinkHelper.gotoMWithUrl(PDYuYueVerifyDialog.this.mContext, pDOperAppointEntity.drawMoreUrl);
                    return;
                }
                PDManager.getInstances(PDYuYueVerifyDialog.this.mProduct.mManageKey).post("pd_ProductDetailActivity", "pd_action_event_to_share");
                jDDialog.dismiss();
                if (PDYuYueVerifyDialog.this.mProduct != null) {
                    PDYuYueVerifyDialog.this.mProduct.isFxyl();
                }
            }
        });
        return jDDialog;
    }

    @NonNull
    private SpannableString getSpannableString(PDOperAppointEntity pDOperAppointEntity, String str) {
        int indexOf;
        if (TextUtils.isEmpty(str)) {
            new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(str);
        PDOperAppointEntity.OfficeAccount officeAccount = pDOperAppointEntity.officeAccount;
        if (officeAccount != null && !TextUtils.isEmpty(officeAccount.text) && str.contains(pDOperAppointEntity.officeAccount.text) && (indexOf = str.indexOf(pDOperAppointEntity.officeAccount.text)) != -1) {
            spannableString.setSpan(new ForegroundColorSpan(PDUtils.parseColor(pDOperAppointEntity.officeAccount.color, "#4D88FF")), indexOf, pDOperAppointEntity.officeAccount.text.length() + indexOf, 33);
            Context context = this.mContext;
            spannableString.setSpan(new ClickUrlSpanCopy(context, this.mProduct.skuId, pDOperAppointEntity.officeAccount.text, context.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_success), this.mContext.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_copy_failure)), indexOf, pDOperAppointEntity.officeAccount.text.length() + indexOf, 33);
            spannableString.setSpan(new StyleSpan(1), indexOf, pDOperAppointEntity.officeAccount.text.length() + indexOf, 33);
        }
        return spannableString;
    }

    private void getVerificationImg(PDVerificationEntity pDVerificationEntity) {
        if (pDVerificationEntity == null) {
            return;
        }
        final ExceptionReporter exceptionReporter = new ExceptionReporter();
        String str = pDVerificationEntity.gwfuncId;
        exceptionReporter.attachHttpSetting(VerificationUtil.getVerificationImg((BaseActivity) this.mContext, str, pDVerificationEntity.type, str, pDVerificationEntity.bsid, pDVerificationEntity.interval, new VerificationUtil.VerificationListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.1
            @Override // com.jingdong.app.mall.bundle.styleinfoview.utils.VerificationUtil.VerificationListener
            public void onEnd(HttpResponse httpResponse) {
                if (PDYuYueVerifyDialog.this.isDestroy) {
                    return;
                }
                final Bitmap parseBitmapFromResponse = PDDataUtil.parseBitmapFromResponse(httpResponse);
                if (parseBitmapFromResponse != null) {
                    ((BaseActivity) PDYuYueVerifyDialog.this.mContext).post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PDYuYueVerifyDialog.this.isDestroy) {
                                return;
                            }
                            PDYuYueVerifyDialog.this.imgView.setImageBitmap(parseBitmapFromResponse);
                        }
                    });
                } else {
                    exceptionReporter.reportHttpBusinessException(httpResponse);
                }
            }

            @Override // com.jingdong.app.mall.bundle.styleinfoview.utils.VerificationUtil.VerificationListener
            public void onError(HttpError httpError) {
                if (PDYuYueVerifyDialog.this.isDestroy) {
                    return;
                }
                httpError.getValidDataErrorCode();
                ((BaseActivity) PDYuYueVerifyDialog.this.mContext).post(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: INVOKE 
                      (wrap: com.jingdong.common.BaseActivity : 0x0013: CHECK_CAST (com.jingdong.common.BaseActivity) (wrap: android.content.Context : 0x000f: IGET 
                      (wrap: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog : 0x000d: IGET (r2v0 'this' com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog$1 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:3) com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.1.this$0 com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog)
                     A[MD:(com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog):android.content.Context (m), WRAPPED] (LINE:1) com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.mContext android.content.Context))
                      (wrap: java.lang.Runnable : 0x0017: CONSTRUCTOR 
                      (r2v0 'this' com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog$1 A[IMMUTABLE_TYPE, THIS])
                      (r3 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog$1, java.lang.String):void (m), WRAPPED] call: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.1.1.<init>(com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog$1, java.lang.String):void type: CONSTRUCTOR)
                     type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:1) in method: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.1.onError(com.jingdong.jdsdk.network.toolbox.HttpError):void, file: classes3.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                    	... 19 more
                    */
                /*
                    this = this;
                    com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog r0 = com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.this
                    boolean r0 = com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.access$000(r0)
                    if (r0 == 0) goto L9
                    return
                L9:
                    java.lang.String r3 = r3.getValidDataErrorCode()
                    com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog r0 = com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.this
                    android.content.Context r0 = com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.access$100(r0)
                    com.jingdong.common.BaseActivity r0 = (com.jingdong.common.BaseActivity) r0
                    com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog$1$1 r1 = new com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog$1$1
                    r1.<init>()
                    r0.post(r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog.AnonymousClass1.onError(com.jingdong.jdsdk.network.toolbox.HttpError):void");
            }
        }));
    }

    private void initView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(com.jingdong.app.mall.bundle.styleinfoview.R.layout.libpdstyleinfoview_yuyue_verify_dlg, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_input);
        this.editView = editText;
        editText.addTextChangedListener(this);
        ImageView imageView = (ImageView) inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_image);
        this.imgView = imageView;
        imageView.setImageDrawable(new ExceptionDrawable(this.mContext, this.mResources.getString(com.jingdong.app.mall.bundle.styleinfoview.R.string.app_name)));
        this.imgView.setOnClickListener(this);
        this.textView = (TextView) inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_status_info);
        inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_dialog_cancel).setOnClickListener(this);
        inflate.findViewById(com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_dialog_ok).setOnClickListener(this);
        setContentView(inflate, new ViewGroup.LayoutParams(PDUtils.dip2px(300.0f), -2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDialogCanAutoDismiss(boolean z) {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mShowing");
            declaredField.setAccessible(true);
            declaredField.set(this, Boolean.valueOf(z));
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    private void setExposureMa(String str) {
        if (this.mProduct != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            PDOperAppointEntity.FollowShopContent followShopContent = this.mShopContent;
            if (followShopContent != null && !TextUtils.isEmpty(followShopContent.text) && !TextUtils.isEmpty(this.mShopContent.keyWord)) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            if (this.officeAccount != null) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        TextView textView;
        String trim = this.editView.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || trim.length() != 1 || (textView = this.textView) == null || textView.getVisibility() != 0) {
            return;
        }
        this.textView.setVisibility(8);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.isDestroy = true;
        this.mShopContent = null;
        this.officeAccount = null;
    }

    public void handleOrderProduct(String str, String str2, String str3, String str4, Boolean bool) {
        PDAppointProtocol pDAppointProtocol = new PDAppointProtocol(this.mProduct.mManageKey, this.mCallbacks);
        String str5 = this.mProduct.isYuyueAutoAddCart() ? "1" : "0";
        ProductDetailEntity productDetailEntity = this.mProduct;
        WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
        if (wareBusinessData == null || wareBusinessData.makeMoreTime == null) {
            pDAppointProtocol.setInputParams(productDetailEntity.skuId, str, str2, str3, str4, bool, productDetailEntity.getYuyueShowCode(), str5, this.mProduct.isMad(), this.mProduct.getYuyueInfoType());
        } else {
            ProductDetailEntity productDetailEntity2 = this.mProduct;
            pDAppointProtocol.setInputParams(productDetailEntity.skuId, str, str2, str3, str4, bool, productDetailEntity.getYuyueShowCode(), str5, this.mProduct.isMad(), this.mProduct.getYuyueInfoType(), Boolean.valueOf(this.mProduct.mWareBusinessData.makeMoreTime.appointMoreTimeFlag), productDetailEntity2.mWareBusinessData.makeMoreTime.shopId, this.mUserAddress, Boolean.valueOf(productDetailEntity2.isPlusSwaying()), DeviceFinger.getFinger(this.mContext));
        }
        ((BaseActivity) this.mContext).addHttpGroupWithNPSSetting(pDAppointProtocol.request());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (PDUtils.repeatClick()) {
            int id = view.getId();
            if (com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_image == id) {
                getVerificationImg(this.mVerificationEntity);
            } else if (com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_dialog_ok == id) {
                String obj = this.editView.getText().toString();
                if (!TextUtils.isEmpty(obj.trim())) {
                    PDVerificationEntity pDVerificationEntity = this.mVerificationEntity;
                    handleOrderProduct(pDVerificationEntity.bsid, obj, pDVerificationEntity.type, "1", Boolean.TRUE);
                } else {
                    this.textView.setVisibility(0);
                    this.textView.setText(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_verify_empty);
                }
                setDialogCanAutoDismiss(false);
            } else if (com.jingdong.app.mall.bundle.styleinfoview.R.id.verify_dialog_cancel == id) {
                setDialogCanAutoDismiss(true);
                dismiss();
            }
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
        getVerificationImg(this.mVerificationEntity);
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void processOrderProduct(PDOperAppointEntity pDOperAppointEntity) {
        boolean z = pDOperAppointEntity.flag;
        if (pDOperAppointEntity.needVerify && !z) {
            this.textView.setVisibility(0);
            if (TextUtils.isEmpty(pDOperAppointEntity.msg)) {
                this.textView.setText(com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_verify_error);
            } else {
                this.textView.setText(pDOperAppointEntity.msg);
            }
            this.editView.setText("");
            getVerificationImg(this.mVerificationEntity);
            return;
        }
        JDDialog myOrderDlg = getMyOrderDlg(pDOperAppointEntity);
        if (myOrderDlg == null || myOrderDlg.isShowing()) {
            return;
        }
        myOrderDlg.show();
    }

    public void setData(PDVerificationEntity pDVerificationEntity, ProductDetailEntity productDetailEntity) {
        this.mVerificationEntity = pDVerificationEntity;
        this.mProduct = productDetailEntity;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.mUserAddress = userAddress;
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.isDestroy = false;
    }
}
