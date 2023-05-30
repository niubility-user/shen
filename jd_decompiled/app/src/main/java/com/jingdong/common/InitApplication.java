package com.jingdong.common;

import android.content.Context;
import com.jingdong.common.network.ExceptionReporterImpl;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes5.dex */
public class InitApplication {
    private static InitApplication initApp;

    private InitApplication(Context context) {
        if (!Configuration.getBooleanProperty(Configuration.BEFORE_INIT_TIP).booleanValue() || CommonBase.getJdSharedPreferences().getBoolean(Configuration.HAS_INIT_TIP, false)) {
            JDMtaUtils.init(context.getApplicationContext());
            ExceptionReporter.acceptProtocol(true);
            ExceptionReporter.init(context, new ExceptionReporterImpl());
        }
    }

    public static synchronized InitApplication instance(Context context) {
        InitApplication initApplication;
        synchronized (InitApplication.class) {
            if (initApp == null) {
                initApp = new InitApplication(context);
            }
            initApplication = initApp;
        }
        return initApplication;
    }
}
