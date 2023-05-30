package com.jingdong.common.impl.log;

import com.jingdong.common.protocol.log.ILog;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class JDLog implements ILog {
    @Override // com.jingdong.common.protocol.log.ILog
    public void d(String str, String str2) {
        if (Log.D) {
            Log.d(str, str2);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void e(String str, String str2) {
        if (Log.E) {
            Log.e(str, str2);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void i(String str, String str2) {
        if (Log.I) {
            Log.i(str, str2);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void s(int i2) {
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void s(String str) {
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void s(String str, String str2) {
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void s(String str, String str2, Throwable th) {
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void v(String str, String str2) {
        if (Log.V) {
            Log.v(str, str2);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void w(String str, String str2) {
        if (Log.W) {
            Log.w(str, str2);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void d(String str, String str2, Throwable th) {
        if (Log.D) {
            Log.d(str, str2, th);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void e(String str, String str2, Throwable th) {
        if (Log.E) {
            Log.e(str, str2, th);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void i(String str, String str2, Throwable th) {
        if (Log.I) {
            Log.i(str, str2, th);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void v(String str, String str2, Throwable th) {
        if (Log.V) {
            Log.v(str, str2, th);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void w(String str, Throwable th) {
        if (Log.W) {
            Log.w(str, th);
        }
    }

    @Override // com.jingdong.common.protocol.log.ILog
    public void w(String str, String str2, Throwable th) {
        if (Log.W) {
            Log.w(str, str2, th);
        }
    }
}
