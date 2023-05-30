package com.jingdong.common.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class PersonalUtsManager {
    private static final int MAX_TIME_WAIT_LOCK = 2;
    private static final String SP_FILE_NAME = "jd_personal_sp";
    private static final String SP_KEY_PREFIX_PERSONAL_UTS = "personal_uts_";
    private static final String TAG = "PersonalUtsManager";
    private static volatile PersonalUtsManager instance;
    private volatile boolean hasReadFromSp;
    private final ReentrantReadWriteLock lock;
    private final Lock readLock;
    private volatile JDSharedPreferences sharedPreferences;
    private volatile String uts;
    private final Lock writeLock;

    private PersonalUtsManager() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.lock = reentrantReadWriteLock;
        this.readLock = reentrantReadWriteLock.readLock();
        this.writeLock = reentrantReadWriteLock.writeLock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PersonalUtsManager getInstance() {
        if (instance == null) {
            synchronized (PersonalUtsManager.class) {
                if (instance == null) {
                    instance = new PersonalUtsManager();
                }
            }
        }
        return instance;
    }

    private JDSharedPreferences getSharedPreferences() {
        if (this.sharedPreferences == null) {
            synchronized (this) {
                if (this.sharedPreferences == null) {
                    this.sharedPreferences = new JDSharedPreferences(JdSdk.getInstance().getApplicationContext(), SP_FILE_NAME, 0);
                }
            }
        }
        return this.sharedPreferences;
    }

    private String getUtsKey(String str) {
        return SP_KEY_PREFIX_PERSONAL_UTS + Md5Encrypt.md5(str);
    }

    @NonNull
    private String readFromSp() {
        String str;
        str = "";
        if (this.hasReadFromSp) {
            return this.uts == null ? "" : this.uts;
        } else if (LoginUserBase.hasLogin()) {
            String userPin = LoginUserBase.getUserPin();
            if (userPin != null && userPin.length() != 0) {
                String str2 = null;
                try {
                    str2 = getSharedPreferences().getString(getUtsKey(userPin), "");
                } catch (Exception e2) {
                    if (OKLog.D) {
                        OKLog.d(TAG, e2);
                    }
                }
                str = str2 != null ? str2 : "";
                this.hasReadFromSp = true;
            }
            return str;
        } else {
            return "";
        }
    }

    private void writeToSp(@NonNull String str) {
        String userPin;
        if (!LoginUserBase.hasLogin() || (userPin = LoginUserBase.getUserPin()) == null || userPin.length() == 0) {
            return;
        }
        try {
            getSharedPreferences().edit().putString(getUtsKey(userPin), str).apply();
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getUts() {
        try {
            if (this.readLock.tryLock(2L, TimeUnit.SECONDS)) {
                if (this.uts == null || this.uts.length() == 0) {
                    this.uts = readFromSp();
                }
                String encode = URLEncoder.encode(this.uts, "UTF-8");
                this.readLock.unlock();
                return encode;
            }
            return "";
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, th);
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeUts() {
        try {
            if (this.writeLock.tryLock(2L, TimeUnit.SECONDS)) {
                this.uts = "";
                this.hasReadFromSp = false;
                this.writeLock.unlock();
            }
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUts(@Nullable String str) {
        try {
            if (this.writeLock.tryLock(2L, TimeUnit.SECONDS)) {
                if (str == null) {
                    str = "";
                }
                this.uts = str;
                writeToSp(str);
                this.writeLock.unlock();
            }
        } catch (Throwable th) {
            if (OKLog.D) {
                OKLog.d(TAG, th);
            }
        }
    }
}
