package com.jingdong.common.login;

import android.util.Pair;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class RegistObserverManager {
    private static final String TAG = "WJRegistObserverManager";
    private static RegistObserverManager instance;
    private ArrayList<Pair<IRegist, String>> iRegists = new ArrayList<>();
    private Object lock = new Object();

    public static synchronized RegistObserverManager getInstance() {
        RegistObserverManager registObserverManager;
        synchronized (RegistObserverManager.class) {
            if (instance == null) {
                instance = new RegistObserverManager();
            }
            if (OKLog.D) {
                OKLog.d(TAG, " RegistObserverManager getInstance()");
            }
            registObserverManager = instance;
        }
        return registObserverManager;
    }

    public void notifyRegistSuccess() {
        ArrayList<Pair<IRegist, String>> arrayList = this.iRegists;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        synchronized (this.lock) {
            for (int i2 = 0; i2 < this.iRegists.size(); i2++) {
                Pair<IRegist, String> pair = this.iRegists.get(i2);
                IRegist iRegist = (IRegist) pair.first;
                String str = (String) pair.second;
                if (str == null) {
                    str = "";
                }
                if (iRegist != null && (iRegist instanceof IRegist)) {
                    iRegist.onSuccess(str);
                    if (OKLog.D) {
                        OKLog.d(TAG, "regist success!!");
                    }
                }
            }
            this.iRegists.clear();
        }
    }

    public void registerRegistListener(IRegist iRegist, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, " registerRegistListener");
        }
        if (str == null) {
            str = "";
        }
        if (this.iRegists == null || iRegist == null) {
            return;
        }
        synchronized (this.lock) {
            this.iRegists.add(new Pair<>(iRegist, str));
        }
    }

    public void unRegisterRegistListener() {
        if (this.iRegists != null) {
            synchronized (this.lock) {
                for (int i2 = 0; i2 < this.iRegists.size(); i2++) {
                    Pair<IRegist, String> pair = this.iRegists.get(i2);
                    IRegist iRegist = (IRegist) pair.first;
                    String str = (String) pair.second;
                    if (str == null) {
                        str = "";
                    }
                    if (iRegist != null && (iRegist instanceof IRegist)) {
                        iRegist.onCancel(str);
                        if (OKLog.D) {
                            OKLog.d(TAG, "regist cancel!!");
                        }
                    }
                }
                this.iRegists.clear();
            }
        }
    }
}
