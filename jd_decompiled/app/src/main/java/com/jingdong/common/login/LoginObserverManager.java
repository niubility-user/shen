package com.jingdong.common.login;

import android.util.Pair;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class LoginObserverManager {
    private static final String TAG = "WJLoginLoginObserverManager";
    private static LoginObserverManager instance;
    private ArrayList<Pair<ILogin, String>> iLogins = new ArrayList<>();
    private Object lock = new Object();

    private LoginObserverManager() {
    }

    public static synchronized LoginObserverManager getInstance() {
        LoginObserverManager loginObserverManager;
        synchronized (LoginObserverManager.class) {
            if (instance == null) {
                instance = new LoginObserverManager();
            }
            if (OKLog.D) {
                OKLog.d(TAG, " LoginObserverManager getInstance()");
            }
            loginObserverManager = instance;
        }
        return loginObserverManager;
    }

    public void notifyLoginSuccess() {
        ArrayList<Pair<ILogin, String>> arrayList = this.iLogins;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        synchronized (this.lock) {
            for (int i2 = 0; i2 < this.iLogins.size(); i2++) {
                Pair<ILogin, String> pair = this.iLogins.get(i2);
                ILogin iLogin = (ILogin) pair.first;
                String str = (String) pair.second;
                if (str == null) {
                    str = "";
                }
                if (iLogin != null) {
                    iLogin.onSuccess(str);
                    if (OKLog.D) {
                        OKLog.d(TAG, "login success!!");
                    }
                }
            }
            this.iLogins.clear();
        }
    }

    public void registerLoginListener(ILogin iLogin, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, " registerLoginListener");
        }
        if (str == null) {
            str = "";
        }
        if (this.iLogins == null || iLogin == null) {
            return;
        }
        synchronized (this.lock) {
            this.iLogins.add(new Pair<>(iLogin, str));
        }
    }

    public void unRegisterLoginListener() {
        if (this.iLogins != null) {
            synchronized (this.lock) {
                for (int i2 = 0; i2 < this.iLogins.size(); i2++) {
                    Pair<ILogin, String> pair = this.iLogins.get(i2);
                    ILogin iLogin = (ILogin) pair.first;
                    String str = (String) pair.second;
                    if (str == null) {
                        str = "";
                    }
                    if (iLogin != null && (iLogin instanceof ICancelLogin) && !LoginUserBase.hasLogin()) {
                        ((ICancelLogin) iLogin).onCancel(str);
                        if (OKLog.D) {
                            OKLog.d(TAG, "login cancel!!");
                        }
                    }
                }
                this.iLogins.clear();
            }
        }
    }
}
