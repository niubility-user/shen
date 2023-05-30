package com.jingdong.app.mall.open;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.utils.base.BaseArchUtil;
import com.jingdong.common.web.WebDebug;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes4.dex */
public class AppLinkActivity extends BaseEntryActivity {
    @Override // com.jingdong.app.mall.open.BaseEntryActivity
    protected void v(Intent intent) {
        String dataString = intent.getDataString();
        if (TextUtils.isEmpty(dataString)) {
            return;
        }
        JDMtaUtils.onClick(this, "Startup_AppLink_Jump", getClass().getSimpleName(), dataString);
        ExceptionReporter.reportOpenAppJumpException("AppLink_Jump", dataString, null);
        Bundle bundle = new Bundle();
        bundle.putString("action", RemoteMessageConst.TO);
        bundle.putString("url", dataString);
        bundle.putString(OpenAppConstant.FLAG_UserActivity, OpenAppConstant.KEY_OuterApp);
        bundle.putString("openAppActivityReferer", BaseArchUtil.getReferer(this));
        JumpUtil.execJumpByDes("m", this, bundle);
        if (WebDebug.report) {
            WebDebug.log("openapp", "realHandle applink:" + getIntent().toString(), this);
        }
    }
}
