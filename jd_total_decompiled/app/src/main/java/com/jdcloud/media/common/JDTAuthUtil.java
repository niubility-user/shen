package com.jdcloud.media.common;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jdcloud.media.common.auth.AuthCallback;
import com.jdcloud.media.common.auth.AuthListener;
import com.jdcloud.media.common.auth.AuthResult;
import com.jdcloud.media.common.auth.AuthUtil;
import com.jdcloud.media.common.log.ILogCallback;
import com.jdcloud.media.common.log.JDCLogUtil;
import java.util.List;

/* loaded from: classes18.dex */
public class JDTAuthUtil implements AuthCallback {
    private static final String TAG = "JDTAuthUtil";
    private static volatile JDTAuthUtil mInstance;
    private AuthListener mAuthListener;
    private Context mContext;
    private List<String> mFeatures;
    private boolean mInitResult = false;
    private int mErrorCode = -1;

    private JDTAuthUtil() {
    }

    public static JDTAuthUtil getInstance() {
        if (mInstance == null) {
            synchronized (JDTAuthUtil.class) {
                if (mInstance == null) {
                    mInstance = new JDTAuthUtil();
                }
            }
        }
        return mInstance;
    }

    public AuthListener getAuthListener() {
        return this.mAuthListener;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public List<String> getFeature() {
        return this.mFeatures;
    }

    public boolean getInitResult() {
        return this.mInitResult;
    }

    @Override // com.jdcloud.media.common.auth.AuthCallback
    public void hasAuth(int i2, boolean z) {
        this.mErrorCode = i2;
        this.mInitResult = true;
    }

    public void init(@NonNull Context context, @NonNull final String str, ILogCallback iLogCallback) {
        this.mContext = context;
        if (TextUtils.isEmpty(str)) {
            this.mErrorCode = AuthResult.INVILID_PARAM.getErrorCode();
            return;
        }
        this.mInitResult = true;
        JDCLogUtil.INSTANCE.setLogCallback(iLogCallback);
        new Thread(new Runnable() { // from class: com.jdcloud.media.common.JDTAuthUtil.1
            @Override // java.lang.Runnable
            public void run() {
                JDTAuthUtil jDTAuthUtil = JDTAuthUtil.this;
                jDTAuthUtil.mFeatures = AuthUtil.doAuth(jDTAuthUtil.mContext, str, JDTAuthUtil.this.mContext.getPackageName(), JDTAuthUtil.mInstance);
            }
        }).start();
    }

    @Override // com.jdcloud.media.common.auth.AuthCallback
    public void noAuth(int i2) {
        this.mErrorCode = i2;
        this.mInitResult = false;
    }

    public void setAuthListener(AuthListener authListener) {
        this.mAuthListener = authListener;
    }
}
