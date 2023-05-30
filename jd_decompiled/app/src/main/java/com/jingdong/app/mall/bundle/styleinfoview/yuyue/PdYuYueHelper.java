package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomLeftButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomRightButtonInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean showYuyueDrawButton(android.content.Context r6, com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo r7, int r8, com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r9) {
        /*
            r5 = this;
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r0 = r9.mWareBusinessData
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r0 = r0.yuyueInfo
            boolean r1 = r0.showDraw
            r2 = 2
            r3 = 0
            r4 = -1
            if (r1 == 0) goto L4d
            java.lang.String r0 = r0.drawEnum
            java.lang.String r1 = "1"
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            if (r0 == 0) goto L1a
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r9 = r9.mWareBusinessData
            boolean r3 = r9.supportSale
            goto L56
        L1a:
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r0 = r9.mWareBusinessData
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r0 = r0.yuyueInfo
            java.lang.String r0 = r0.drawEnum
            java.lang.String r1 = "3"
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            if (r0 == 0) goto L2b
            int r8 = com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_pd_yuyue_mask_no_hit
            goto L55
        L2b:
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r0 = r9.mWareBusinessData
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r0 = r0.yuyueInfo
            java.lang.String r0 = r0.drawEnum
            java.lang.String r1 = "2"
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            if (r0 == 0) goto L3c
            int r8 = com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_pd_yuyue_mask_no_yuyue
            goto L55
        L3c:
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r9 = r9.mWareBusinessData
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r9 = r9.yuyueInfo
            java.lang.String r9 = r9.drawEnum
            java.lang.String r0 = "4"
            boolean r9 = android.text.TextUtils.equals(r9, r0)
            if (r9 == 0) goto L55
            int r8 = com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_pd_yuyue_swaying_buy
            goto L56
        L4d:
            boolean r9 = r9.isYuyueMask()
            if (r9 == 0) goto L55
            int r8 = com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_pd_yuyue_mask_draw
        L55:
            r2 = -1
        L56:
            com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomLeftButtonInfo r9 = r7.leftButtonInfo
            java.lang.String r6 = r6.getString(r8)
            r9.buttonText = r6
            com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomLeftButtonInfo r6 = r7.leftButtonInfo
            r6.buttonEnable = r3
            if (r2 == r4) goto L66
            r6.buttonEvent = r2
        L66:
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper.showYuyueDrawButton(android.content.Context, com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo, int, com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x01b9, code lost:
        if (r0.appointMoreTimeFlag != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01c3, code lost:
        if (r15.yuyueInfo.pinSkuYuYue != false) goto L85;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean generatorYuYueBtn(android.content.Context r12, com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo r13, com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r14, com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper.DefalutButtonInfo r15) {
        /*
            Method dump skipped, instructions count: 652
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper.generatorYuYueBtn(android.content.Context, com.jingdong.app.mall.bundle.styleinfoview.entity.PdBottomButtonInfo, com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity, com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuYueHelper$DefalutButtonInfo):boolean");
    }
}
