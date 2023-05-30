package com.jingdong.app.mall.bundle.styleinfoview.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessAddSubToast;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PdKeyBoardListener;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PdKeyboardChangeListener;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.FontsUtil;
import jpbury.t;

/* loaded from: classes3.dex */
public class PDStyleCountView extends PDBaseRelativeView implements View.OnClickListener, TextWatcher, PdKeyBoardListener {
    private boolean isDark;
    private ImageView mAddBtn;
    private PdAutoChangeTextSize mCountTip;
    private EditText mCountView;
    private PdAutoChangeTextSize mFloorTitle;
    private PdKeyboardChangeListener mKeyboardChangeListener;
    private int mProductCount;
    private ImageView mReduceBtn;

    public PDStyleCountView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProductCount = 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
        if (r0 <= r3.lowestBuyNum) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void addOnReleaseOne(int r7) {
        /*
            r6 = this;
            android.widget.EditText r0 = r6.mCountView
            if (r0 != 0) goto L5
            return
        L5:
            android.text.Editable r0 = r0.getText()
            r1 = 0
            if (r0 == 0) goto L10
            java.lang.String r1 = r0.toString()
        L10:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            r2 = 0
            if (r0 != 0) goto L75
            int r0 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L1c
            goto L2b
        L1c:
            r0 = move-exception
            boolean r1 = com.jingdong.corelib.utils.Log.D
            if (r1 == 0) goto L2a
            java.lang.String r0 = r0.getMessage()
            java.lang.String r1 = "exception"
            com.jingdong.corelib.utils.Log.d(r1, r0)
        L2a:
            r0 = 0
        L2b:
            r1 = 1
            if (r7 >= 0) goto L4c
            com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r3 = r6.mProduct
            java.lang.String r4 = r3.skuId
            java.lang.String r3 = r3.source
            java.lang.String r5 = "Component_MinusNumber"
            com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils.onClickForTc(r5, r4, r3)
            if (r0 <= r1) goto L45
            com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r3 = r6.mProduct
            boolean r4 = r3.lowestBuy
            if (r4 == 0) goto L46
            int r3 = r3.lowestBuyNum
            if (r0 > r3) goto L46
        L45:
            r2 = 1
        L46:
            if (r2 == 0) goto L63
            r6.showLowestToast()
            return
        L4c:
            if (r7 <= 0) goto L63
            com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r2 = r6.mProduct
            java.lang.String r3 = r2.skuId
            java.lang.String r2 = r2.source
            java.lang.String r4 = "Component_AddNumber"
            com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils.onClickForTc(r4, r3, r2)
            com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r2 = r6.mProduct
            int r2 = r2.buyMaxNum
            if (r0 < r2) goto L63
            r6.showMaxToast()
            return
        L63:
            int r7 = r7 + r0
            if (r7 > 0) goto L67
            goto L68
        L67:
            r1 = r7
        L68:
            com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r7 = r6.mProduct
            int r7 = r7.buyMaxNum
            if (r1 <= r7) goto L70
            r2 = r7
            goto L71
        L70:
            r2 = r1
        L71:
            r6.setBtnLeftStatus(r2)
            goto L76
        L75:
            r0 = 0
        L76:
            if (r2 == r0) goto L8a
            java.lang.String r7 = java.lang.String.valueOf(r2)
            android.widget.EditText r0 = r6.mCountView
            r0.setText(r7)
            android.widget.EditText r0 = r6.mCountView
            int r7 = r7.length()
            r0.setSelection(r7)
        L8a:
            r6.setNum(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleCountView.addOnReleaseOne(int):void");
    }

    private void setBtnLeftStatus(int i2) {
        ImageView imageView = this.mReduceBtn;
        if (imageView != null) {
            ProductDetailEntity productDetailEntity = this.mProduct;
            if (productDetailEntity.lowestBuy && i2 <= productDetailEntity.lowestBuyNum) {
                imageView.setBackgroundResource(this.isDark ? R.drawable.libpdstyleinfoview_style_num_reduce_disable_dark : R.drawable.libpdstyleinfoview_style_num_reduce_disable);
            } else if (i2 <= 1) {
                imageView.setBackgroundResource(this.isDark ? R.drawable.libpdstyleinfoview_style_num_reduce_disable_dark : R.drawable.libpdstyleinfoview_style_num_reduce_disable);
            } else {
                imageView.setBackgroundResource(this.isDark ? R.drawable.libpdstyleinfoview_style_num_reduce_pressed_dark : R.drawable.libpdstyleinfoview_style_num_reduce_pressed);
            }
        }
        ImageView imageView2 = this.mAddBtn;
        if (imageView2 != null) {
            if (i2 < this.mProduct.buyMaxNum) {
                imageView2.setBackgroundResource(this.isDark ? R.drawable.libpdstyleinfoview_style_num_add_pressed_dark : R.drawable.libpdstyleinfoview_style_num_add_pressed);
            } else {
                imageView2.setBackgroundResource(this.isDark ? R.drawable.libpdstyleinfoview_style_num_add_disable_dark : R.drawable.libpdstyleinfoview_style_num_add_disable);
            }
        }
    }

    private void setDarkTheme() {
        ProductDetailEntity productDetailEntity = this.mProduct;
        boolean z = productDetailEntity != null && productDetailEntity.isDarkTheme();
        this.isDark = z;
        this.mFloorTitle.setTextColor(ContextCompat.getColor(this.mContext, z ? R.color.libpdstyleinfoview_color_ececec : R.color.libpdstyleinfoview_color_2e2d2d));
        this.mCountView.setTextColor(ContextCompat.getColor(this.mContext, this.isDark ? R.color.libpdstyleinfoview_color_ececec : R.color.libpdstyleinfoview_color_232326));
        this.mCountTip.setTextColor(ContextCompat.getColor(this.mContext, this.isDark ? R.color.libpdstyleinfoview_color_ff3826 : R.color.libpdstyleinfoview_color_F0250F));
        this.mCountView.setBackgroundColor(ContextCompat.getColor(this.mContext, this.isDark ? R.color.libpdstyleinfoview_color_302E2E : R.color.libpdstyleinfoview_color_f2f2f2));
    }

    private void setNum(int i2) {
        this.mProductCount = i2;
        this.mProduct.setNumber(i2);
    }

    private void showLowestToast() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessAddSubToast wareBusinessAddSubToast;
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if ((wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || (wareBusinessAddSubToast = warePropertyInfo.addAndSubToast) == null || TextUtils.isEmpty(wareBusinessAddSubToast.lowestToastText)) ? false : true) {
            PDUtils.showToastCenterNormal(this.mContext, this.mProduct.mWareBusinessData.property.addAndSubToast.lowestToastText);
        }
    }

    private void showMaxToast() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessAddSubToast wareBusinessAddSubToast;
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if ((wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || (wareBusinessAddSubToast = warePropertyInfo.addAndSubToast) == null || TextUtils.isEmpty(wareBusinessAddSubToast.limitToastText)) ? false : true) {
            PDUtils.showToastCenterNormal(this.mContext, this.mProduct.mWareBusinessData.property.addAndSubToast.limitToastText);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    public void bindData2View(boolean z, String str) {
        WarePropertyInfo warePropertyInfo;
        setDarkTheme();
        PdKeyboardChangeListener pdKeyboardChangeListener = new PdKeyboardChangeListener(this.mCountView);
        this.mKeyboardChangeListener = pdKeyboardChangeListener;
        pdKeyboardChangeListener.setKeyBoardListener(this);
        setEditFocusable(false);
        this.mProductCount = this.mProduct.number;
        this.mCountView.setText("" + this.mProductCount);
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        boolean z2 = (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || TextUtils.isEmpty(warePropertyInfo.lowAndLimitText)) ? false : true;
        if (z) {
            StringBuilder sb = new StringBuilder();
            if (z2) {
                sb.append(this.mProduct.mWareBusinessData.property.lowAndLimitText);
                sb.append(LangUtils.SINGLE_SPACE);
            }
            sb.append(str);
            if (!TextUtils.isEmpty(sb.toString())) {
                this.mCountTip.setVisibility(0);
                this.mCountTip.setText(sb.toString());
            } else {
                this.mCountTip.setVisibility(8);
            }
        } else if (z2) {
            this.mCountTip.setVisibility(0);
            this.mCountTip.setText(this.mProduct.mWareBusinessData.property.lowAndLimitText);
        } else {
            this.mCountTip.setVisibility(8);
        }
        boolean hasYanBao = this.mProduct.hasYanBao();
        if (this.mProduct.getWareFurnitureInfo() == null && !hasYanBao && this.mProduct.getWareJdServerPlusEntity() == null && !this.mProduct.isShowHealthOnLine()) {
            setPadding(PDUtils.dip2px(18.0f), PDUtils.dip2px(15.0f), 0, PDUtils.dip2px(28.0f));
        } else {
            setPadding(PDUtils.dip2px(18.0f), PDUtils.dip2px(15.0f), 0, PDUtils.dip2px(4.0f));
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseRelativeView
    protected void initView() {
        this.mCountTip = (PdAutoChangeTextSize) findViewById(R.id.detail_style_count_tip);
        this.mFloorTitle = (PdAutoChangeTextSize) findViewById(R.id.detail_style_count_title);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.pd_style_count_add);
        this.mAddBtn = simpleDraweeView;
        simpleDraweeView.setOnClickListener(this);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) findViewById(R.id.pd_style_count_reduce);
        this.mReduceBtn = simpleDraweeView2;
        simpleDraweeView2.setOnClickListener(this);
        EditText editText = (EditText) findViewById(R.id.pd_style_count_edit);
        this.mCountView = editText;
        editText.addTextChangedListener(this);
        FontsUtil.changeTextFont(this.mCountView);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.pd_style_count_reduce == id) {
            addOnReleaseOne(-1);
        } else if (R.id.pd_style_count_add == id) {
            addOnReleaseOne(1);
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseRelativeView
    public void onDestoryView() {
        super.onDestoryView();
        this.mCountTip = null;
        this.mAddBtn = null;
        this.mReduceBtn = null;
        this.mCountView = null;
        PdKeyboardChangeListener pdKeyboardChangeListener = this.mKeyboardChangeListener;
        if (pdKeyboardChangeListener != null) {
            pdKeyboardChangeListener.destroy();
            this.mKeyboardChangeListener = null;
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.utils.PdKeyBoardListener
    public void onKeyboardChange(int i2) {
        if (this.mCountView == null || i2 < PDUtils.getHeight()) {
            return;
        }
        Editable text = this.mCountView.getText();
        String obj = text != null ? text.toString() : null;
        int i3 = 0;
        try {
            i3 = Integer.parseInt(obj);
        } catch (NumberFormatException e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
        int i4 = this.mProduct.lowestBuyNum;
        if (i3 < i4) {
            setBtnLeftStatus(i4);
            this.mCountView.setText(String.valueOf(i4));
            this.mCountView.setSelection(this.mCountView.getText().toString().length());
            setNum(i4);
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        addOnReleaseOne(0);
    }

    public void resetLowestCount() {
        this.mCountView.setText(String.valueOf(this.mProduct.number));
    }

    public void setEditFocusable(boolean z) {
        EditText editText = this.mCountView;
        if (editText != null) {
            editText.setFocusable(z);
            this.mCountView.setFocusableInTouchMode(z);
        }
    }
}
