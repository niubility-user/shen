package com.jingdong.common.controller;

import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.web.IRouterParams;

/* loaded from: classes5.dex */
public class CartH5RouterController {
    public static String getCartUUIDForH5(IRouterParams iRouterParams) {
        return StatisticsReportUtil.readDeviceUUID() + StatisticsReportUtil.readCartUUID();
    }
}
