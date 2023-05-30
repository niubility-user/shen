package jd.wjlogin_sdk.common;

import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.config.ConfigHostMode;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.JumpResult;
import jd.wjlogin_sdk.tlvtype.l;
import jd.wjlogin_sdk.tlvtype.x;
import jd.wjlogin_sdk.util.LanguageToast;
import jd.wjlogin_sdk.util.p;

/* loaded from: classes.dex */
public class g {
    public int seq = 1;
    protected long a = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public JumpResult a(jd.wjlogin_sdk.tlvtype.d dVar, l lVar) {
        JumpResult jumpResult = new JumpResult();
        if (dVar != null) {
            jumpResult.setToken(new String(dVar.a()));
        }
        if (lVar != null) {
            jumpResult.setUrl(lVar.a());
        }
        return jumpResult;
    }

    public void enableLog(boolean z) {
        p.a(z);
    }

    public void setConfigMode(int i2) {
        ConfigHostMode.setConfigMode(i2);
    }

    public void setDevelop(int i2) {
        DevelopType.setDebugModel(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FailResult a() {
        FailResult failResult = new FailResult();
        failResult.setReplyCode((byte) -2);
        failResult.setMessage(LanguageToast.getToastMsg(-102));
        return failResult;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(FailResult failResult, byte b, x xVar) {
        if (failResult == null) {
            return;
        }
        failResult.setReplyCode(b);
        if (xVar != null) {
            if (xVar.b() != null) {
                failResult.setMessage(xVar.b());
                return;
            } else {
                failResult.setMessage(xVar.a());
                return;
            }
        }
        failResult.setMessage(LanguageToast.getToastMsg(-102));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(FailResult failResult, JumpResult jumpResult) {
        if (failResult == null) {
            return;
        }
        failResult.setJumpResult(jumpResult);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(OnCommonCallback onCommonCallback, FailResult failResult, jd.wjlogin_sdk.tlvtype.d dVar, l lVar) {
        a(failResult, a(dVar, lVar));
        if (onCommonCallback != null) {
            onCommonCallback.onFailHandleInner(failResult);
        }
    }
}
