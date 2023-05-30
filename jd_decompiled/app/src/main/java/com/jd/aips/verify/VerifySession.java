package com.jd.aips.verify;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.utils.RandomSessionIdUtil;
import com.jd.aips.verify.BaseVerifyParams;
import com.jd.aips.verify.tracker.BaseVerifyTracker;
import com.jd.aips.verify.tracker.TrackerCallback;

/* loaded from: classes12.dex */
public abstract class VerifySession<P extends BaseVerifyParams, C, T extends BaseVerifyTracker> {
    public final TrackerCallback trackerCallback;
    public final C verifyCallback;
    public volatile String verifyId;
    public final P verifyParams;
    public volatile String verifyToken;
    public final T verifyTracker;
    public int totalCount = 3;
    public volatile int count = 1;
    public final String id = RandomSessionIdUtil.buildSessionId();

    public VerifySession(@NonNull Context context, @NonNull P p, @NonNull C c2, @Nullable TrackerCallback trackerCallback) {
        this.verifyToken = "";
        this.verifyId = "";
        this.verifyParams = p;
        this.verifyCallback = c2;
        this.trackerCallback = trackerCallback;
        this.verifyTracker = buildVerifyTracker(context);
        this.verifyToken = p.verifyToken;
        this.verifyId = "";
    }

    protected abstract T buildVerifyTracker(@NonNull Context context);

    public boolean canRetry() {
        return this.count <= this.totalCount;
    }

    public void destroy() {
        this.count = 1;
        this.verifyId = "";
        this.verifyToken = "";
    }

    public int getCount() {
        return this.count;
    }

    String getId() {
        return this.id;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    C getVerifyCallback() {
        return this.verifyCallback;
    }

    public String getVerifyId() {
        return this.verifyId;
    }

    P getVerifyParams() {
        return this.verifyParams;
    }

    public String getVerifyToken() {
        return this.verifyToken;
    }

    public void setCount(int i2) {
        this.count = i2;
    }

    public void setTotalCount(int i2) {
        this.totalCount = i2;
    }

    public void setVerifyId(String str) {
        this.verifyId = str;
    }

    public void updateVerifyToken(String str) {
        this.verifyToken = str;
    }

    public synchronized boolean validateRetry() {
        if (this.count > this.totalCount) {
            return false;
        }
        this.count++;
        return true;
    }
}
