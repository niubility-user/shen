package com.jingdong.app.mall;

import android.content.Context;
import android.content.Intent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.utils.DexAsyncUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes19.dex */
public class e implements BaseFrameUtil.BaseFrameUtilImpl {
    public static final String a = "e";
    private static e b;

    private e() {
        BaseFrameUtil.getInstance().setBaseFrameUtilImpl(this);
    }

    public static synchronized e b() {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e();
            }
            eVar = b;
        }
        return eVar;
    }

    public BaseActivity a() {
        return (BaseActivity) BaseFrameUtil.getInstance().getCurrentMyActivity();
    }

    @Override // com.jingdong.common.BaseFrameUtil.BaseFrameUtilImpl
    public void startMainFrameActivity(Context context) {
        IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
        Intent mainFrameActivityIntent = DexAsyncUtil.getMainFrameActivityIntent(context);
        if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
            if (Log.D) {
                Log.d(a, "Commutils goToMainFrameActivity() -->> not run");
            }
            mainFrameActivityIntent.addFlags(268435456);
            context.startActivity(mainFrameActivityIntent);
            return;
        }
        if (Log.D) {
            Log.d(a, "Commutils goToMainFrameActivity() -->> run");
        }
        if (mainFrameActivity.isMainStop()) {
            mainFrameActivityIntent.addFlags(268435456);
            context.startActivity(mainFrameActivityIntent);
        }
    }
}
