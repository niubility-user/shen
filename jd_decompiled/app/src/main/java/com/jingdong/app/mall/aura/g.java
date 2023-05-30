package com.jingdong.app.mall.aura;

import com.jingdong.aura.wrapper.listener.IMobileLogCallback;

/* loaded from: classes19.dex */
public class g implements IMobileLogCallback {
    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void debug(String str, String str2) {
        try {
            com.jingdong.app.mall.log.g.a("AuraMobileLog_" + str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void error(String str, String str2) {
        try {
            com.jingdong.app.mall.log.g.b("AuraMobileLog_" + str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void fatal(String str, String str2) {
        try {
            com.jingdong.app.mall.log.g.b("AuraMobileLog_" + str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void info(String str, String str2) {
        try {
            com.jingdong.app.mall.log.g.d("AuraMobileLog_" + str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void verbose(String str, String str2) {
        try {
            com.jingdong.app.mall.log.g.f("AuraMobileLog_" + str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void warn(String str, String str2) {
        try {
            com.jingdong.app.mall.log.g.g("AuraMobileLog_" + str, str2);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void error(String str, String str2, Throwable th) {
        try {
            com.jingdong.app.mall.log.g.c("AuraMobileLog_" + str, str2, th);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void fatal(String str, String str2, Throwable th) {
        try {
            com.jingdong.app.mall.log.g.c("AuraMobileLog_" + str, str2, th);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void warn(String str, String str2, Throwable th) {
        try {
            com.jingdong.app.mall.log.g.h("AuraMobileLog_" + str, str2, th);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void error(String str, StringBuffer stringBuffer, Throwable th) {
        try {
            com.jingdong.app.mall.log.g.c("AuraMobileLog_" + str, stringBuffer.toString(), th);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void fatal(String str, StringBuffer stringBuffer, Throwable th) {
        try {
            com.jingdong.app.mall.log.g.c("AuraMobileLog_" + str, stringBuffer.toString(), th);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.aura.wrapper.listener.IMobileLogCallback
    public void warn(String str, StringBuffer stringBuffer, Throwable th) {
        try {
            com.jingdong.app.mall.log.g.h("AuraMobileLog_" + str, stringBuffer.toString(), th);
        } catch (Throwable unused) {
        }
    }
}
