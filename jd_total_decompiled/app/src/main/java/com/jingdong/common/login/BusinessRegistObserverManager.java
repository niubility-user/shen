package com.jingdong.common.login;

import android.util.Pair;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class BusinessRegistObserverManager extends RegistObserverManager {
    private static final String TAG = "BusinessRegistObserverManager";
    private static BusinessRegistObserverManager instance;
    private ArrayList<Pair<IBusinessRegist, String>> iRegists = new ArrayList<>();
    private Object lock = new Object();

    public static synchronized BusinessRegistObserverManager getInstance() {
        BusinessRegistObserverManager businessRegistObserverManager;
        synchronized (BusinessRegistObserverManager.class) {
            if (instance == null) {
                instance = new BusinessRegistObserverManager();
            }
            if (OKLog.D) {
                OKLog.d(TAG, " RegistObserverManager getInstance()");
            }
            businessRegistObserverManager = instance;
        }
        return businessRegistObserverManager;
    }

    public void failRegistListener() {
        if (this.iRegists != null) {
            synchronized (this.lock) {
                for (int i2 = 0; i2 < this.iRegists.size(); i2++) {
                    Pair<IBusinessRegist, String> pair = this.iRegists.get(i2);
                    IBusinessRegist iBusinessRegist = (IBusinessRegist) pair.first;
                    String str = (String) pair.second;
                    if (str == null) {
                        str = "";
                    }
                    if (iBusinessRegist != null && (iBusinessRegist instanceof IBusinessRegist)) {
                        iBusinessRegist.onFail(str);
                        if (OKLog.D) {
                            OKLog.d(TAG, "regist fail!!");
                        }
                    }
                }
                this.iRegists.clear();
            }
        }
    }

    @Override // com.jingdong.common.login.RegistObserverManager
    public void notifyRegistSuccess() {
        ArrayList<Pair<IBusinessRegist, String>> arrayList = this.iRegists;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        synchronized (this.lock) {
            for (int i2 = 0; i2 < this.iRegists.size(); i2++) {
                Pair<IBusinessRegist, String> pair = this.iRegists.get(i2);
                IBusinessRegist iBusinessRegist = (IBusinessRegist) pair.first;
                String str = (String) pair.second;
                if (str == null) {
                    str = "";
                }
                if (iBusinessRegist != null && (iBusinessRegist instanceof IBusinessRegist)) {
                    iBusinessRegist.onSuccess(str);
                    if (OKLog.D) {
                        OKLog.d(TAG, "regist success!!");
                    }
                }
            }
            this.iRegists.clear();
        }
    }

    public void registerRegistListener(IBusinessRegist iBusinessRegist, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, " registerRegistListener");
        }
        if (str == null) {
            str = "";
        }
        if (this.iRegists == null || iBusinessRegist == null) {
            return;
        }
        synchronized (this.lock) {
            this.iRegists.add(new Pair<>(iBusinessRegist, str));
        }
    }

    @Override // com.jingdong.common.login.RegistObserverManager
    public void unRegisterRegistListener() {
        if (this.iRegists != null) {
            synchronized (this.lock) {
                for (int i2 = 0; i2 < this.iRegists.size(); i2++) {
                    Pair<IBusinessRegist, String> pair = this.iRegists.get(i2);
                    IBusinessRegist iBusinessRegist = (IBusinessRegist) pair.first;
                    String str = (String) pair.second;
                    if (str == null) {
                        str = "";
                    }
                    if (iBusinessRegist != null && (iBusinessRegist instanceof IBusinessRegist)) {
                        iBusinessRegist.onCancel(str);
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
