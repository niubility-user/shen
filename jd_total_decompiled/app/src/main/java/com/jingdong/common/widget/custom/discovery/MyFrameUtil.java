package com.jingdong.common.widget.custom.discovery;

import android.content.Context;
import android.content.Intent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.utils.DexAsyncUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes12.dex */
public class MyFrameUtil implements BaseFrameUtil.BaseFrameUtilImpl {
    public static final String TAG = "MyFrameUtil";
    private static MyFrameUtil instance;

    private MyFrameUtil() {
        BaseFrameUtil.getInstance().setBaseFrameUtilImpl(this);
    }

    public static synchronized MyFrameUtil getInstance() {
        MyFrameUtil myFrameUtil;
        synchronized (MyFrameUtil.class) {
            if (instance == null) {
                instance = new MyFrameUtil();
            }
            myFrameUtil = instance;
        }
        return myFrameUtil;
    }

    public BaseActivity getCurrentMyActivity() {
        return (BaseActivity) BaseFrameUtil.getInstance().getCurrentMyActivity();
    }

    @Override // com.jingdong.common.BaseFrameUtil.BaseFrameUtilImpl
    public void startMainFrameActivity(Context context) {
        IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
        Intent mainFrameActivityIntent = DexAsyncUtil.getMainFrameActivityIntent(context);
        if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
            if (Log.D) {
                Log.d(TAG, "Commutils goToMainFrameActivity() -->> not run");
            }
            mainFrameActivityIntent.addFlags(268435456);
            context.startActivity(mainFrameActivityIntent);
            return;
        }
        if (Log.D) {
            Log.d(TAG, "Commutils goToMainFrameActivity() -->> run");
        }
        if (mainFrameActivity.isMainStop()) {
            mainFrameActivityIntent.addFlags(268435456);
            context.startActivity(mainFrameActivityIntent);
        }
    }
}
