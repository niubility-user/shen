package com.jd.cashier.app.jdlibcutter.impl.config;

import com.jd.cashier.app.jdlibcutter.protocol.config.IHostConfig;
import com.jingdong.jdsdk.config.HostConfig;

/* loaded from: classes13.dex */
public class JDSdkHostConfigImpl implements IHostConfig {
    private static final String CASHIER_HOST = "cashier_host";

    public JDSdkHostConfigImpl() {
        initHost();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IHostConfig
    public String getHost() {
        return HostConfig.getInstance().getHost(CASHIER_HOST);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.config.IHostConfig
    public void initHost() {
        HostConfig.getInstance().putHost(CASHIER_HOST, new HostConfig.HostModel("api.m.jd.care", "api.m.jd.com", "beta-api.m.jd.com", "beta-api2.m.jd.com", "beta-api3.m.jd.com", "beta-api4.m.jd.com", "mp.m.jd.care", "mp1.m.jd.care", "mp2.m.jd.care", "mp3.m.jd.care", "mp4.m.jd.care", "mp5.m.jd.care", "mp6.m.jd.care", "mp7.m.jd.care", "mp8.m.jd.care", "mp9.m.jd.care", "mp10.m.jd.care"));
        HostConfig.getInstance().restoreSvaedConfigFromSp();
    }
}
