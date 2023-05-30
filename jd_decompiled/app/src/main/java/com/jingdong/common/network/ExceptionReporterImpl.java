package com.jingdong.common.network;

import com.jingdong.jdexreport.einterface.InitCommonInfo;
import com.jingdong.jdexreport.einterface.OnPermissionCheckListener;
import com.jingdong.jdsdk.depend.DependUtil;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.PackageInfoUtil;

/* loaded from: classes5.dex */
public class ExceptionReporterImpl implements ExceptionReporter.ExtraComponentInitializer {
    @Override // com.jingdong.jdsdk.network.toolbox.ExceptionReporter.ExtraComponentInitializer
    public InitCommonInfo init() {
        InitCommonInfo initCommonInfo = new InitCommonInfo();
        initCommonInfo.appv = PackageInfoUtil.getVersionName();
        initCommonInfo.hmv = PackageInfoUtil.getHarmonyVersionName();
        initCommonInfo.build = String.valueOf(PackageInfoUtil.getVersionCode());
        initCommonInfo.appId = "4";
        initCommonInfo.guid = DependUtil.getInstance().getDepend().getUUID();
        initCommonInfo.setCheckListener(new OnPermissionCheckListener() { // from class: com.jingdong.common.network.ExceptionReporterImpl.1
            @Override // com.jingdong.jdexreport.einterface.OnPermissionCheckListener
            public String updateGuid() {
                return DependUtil.getInstance().getDepend().getUUID();
            }
        });
        initCommonInfo.zipFlag = 1;
        return initCommonInfo;
    }
}
