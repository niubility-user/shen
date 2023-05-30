package com.jingdong.app.mall.home.n.h;

import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes4.dex */
public class h {
    public static void a(String str, HttpGroup.HttpTaskListener httpTaskListener, boolean z) {
        if (com.jingdong.app.mall.home.o.a.f.j0() || com.jingdong.app.mall.home.floor.common.i.g.n()) {
            return;
        }
        ExceptionReporter exceptionReporter = new ExceptionReporter();
        HttpSetting httpSetting = new HttpSetting();
        if (!TextUtils.isEmpty(str)) {
            httpSetting.setFunctionId(str);
        } else {
            httpSetting.setFunctionId(SourceEntity.SOURCE_TYPE_HOME_MIAOSHA);
        }
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.putJsonParam("isAdvance", Integer.valueOf(z ? 1 : 0));
        httpSetting.putJsonParam("blackHomeFlag", s.i("blackHomeFlag"));
        httpSetting.putJsonParam("privacyType", com.jingdong.app.mall.home.o.a.f.k0() ? "1" : "0");
        httpSetting.setEffect(0);
        CommonBase.handleHomeConnectReadTimeByNetType(httpSetting);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setListener(httpTaskListener);
        com.jingdong.app.mall.home.o.a.f.e(httpSetting);
        exceptionReporter.attachHttpSetting(httpSetting);
    }
}
