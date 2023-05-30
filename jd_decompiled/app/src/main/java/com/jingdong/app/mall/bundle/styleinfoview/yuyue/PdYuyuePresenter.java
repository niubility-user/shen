package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

/* loaded from: classes3.dex */
class PdYuyuePresenter {
    PdYuyuePresenter() {
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doYuyue(final com.jingdong.common.BaseActivity r20, final com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r3 = r2.mWareBusinessData
            if (r3 == 0) goto Le0
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r3 = r3.yuyueInfo
            if (r3 != 0) goto L10
            goto Le0
        L10:
            boolean r3 = r21.isPlusMakeTime()
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L34
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r3 = r2.mWareBusinessData
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusForBuyMt r6 = r3.makeMoreTime
            boolean r7 = r6.makeMoreTimeFlag
            if (r7 == 0) goto L34
            boolean r7 = r6.drawMoreTimeFlag
            if (r7 != 0) goto L28
            boolean r7 = r6.appointMoreTimeFlag
            if (r7 == 0) goto L34
        L28:
            boolean r6 = r6.userFlag
            if (r6 == 0) goto L34
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r3 = r3.yuyueInfo
            boolean r3 = r3.pinSkuYuYue
            if (r3 == 0) goto L34
            r3 = 1
            goto L35
        L34:
            r3 = 0
        L35:
            if (r3 == 0) goto L41
            int r2 = com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_lib_pd_yuyue_success
            java.lang.String r2 = r1.getString(r2)
            com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils.showToastCenterNormal(r1, r2)
            return
        L41:
            long r6 = r21.getStartTime()
            r8 = 0
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r3 <= 0) goto L95
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "{\"des\":\"productDetail\",\"params\":{\"skuId\":\""
            r3.append(r6)
            java.lang.String r6 = r2.skuId
            r3.append(r6)
            java.lang.String r6 = "\"}}"
            r3.append(r6)
            java.lang.String r16 = r3.toString()
            int r3 = com.jingdong.app.mall.bundle.styleinfoview.R.string.libpdstyleinfoview_lib_pd_yuyue
            java.lang.String r8 = r1.getString(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "detailReserve_"
            r3.append(r6)
            java.lang.String r6 = r2.skuId
            r3.append(r6)
            java.lang.String r9 = r3.toString()
            java.lang.String r10 = r21.getProductDetailName()
            long r6 = r21.getStartTime()
            r11 = 1000(0x3e8, double:4.94E-321)
            long r12 = r6 * r11
            r14 = 0
            java.lang.String r7 = "RESERVE"
            java.lang.String r11 = ""
            java.lang.String r17 = ""
            java.lang.String r18 = ""
            com.jingdong.common.utils.JDReminderNewUtils.setReminder(r7, r8, r9, r10, r11, r12, r14, r16, r17, r18)
        L95:
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData r3 = r2.mWareBusinessData
            com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo r3 = r3.yuyueInfo
            boolean r3 = r3.mad
            if (r3 == 0) goto Ld4
            boolean r3 = com.jingdong.common.login.LoginUserBase.hasLogin()
            if (r3 == 0) goto Lc7
            com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDIsAppointProtocol r3 = new com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDIsAppointProtocol
            java.lang.String r6 = r2.mManageKey
            r3.<init>(r6)
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r7 = r2.skuId
            r6[r5] = r7
            java.lang.String r5 = "1"
            r6[r4] = r5
            java.lang.String r2 = r21.getYuyueShowCode()
            r4 = 2
            r6[r4] = r2
            r3.setInputParams(r6)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r2 = r3.request()
            r1.addHttpGroupWithNPSSetting(r2)
            goto Le0
        Lc7:
            com.jingdong.common.login.LoginUserHelper r3 = com.jingdong.common.login.LoginUserHelper.getInstance()
            com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter$1 r4 = new com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter$1
            r4.<init>()
            r3.executeLoginRunnable(r1, r4)
            goto Le0
        Ld4:
            com.jingdong.common.login.LoginUserHelper r3 = com.jingdong.common.login.LoginUserHelper.getInstance()
            com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter$2 r4 = new com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter$2
            r4.<init>()
            r3.executeLoginRunnable(r1, r4)
        Le0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.yuyue.PdYuyuePresenter.doYuyue(com.jingdong.common.BaseActivity, com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity):void");
    }
}
