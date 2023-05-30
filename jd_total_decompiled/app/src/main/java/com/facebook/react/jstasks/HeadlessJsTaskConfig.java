package com.facebook.react.jstasks;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes12.dex */
public class HeadlessJsTaskConfig {
    private final boolean mAllowedInForeground;
    private final WritableMap mData;
    private final String mTaskKey;
    private final long mTimeout;

    public HeadlessJsTaskConfig(String str, WritableMap writableMap) {
        this(str, writableMap, 0L, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WritableMap getData() {
        return this.mData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getTaskKey() {
        return this.mTaskKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getTimeout() {
        return this.mTimeout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAllowedInForeground() {
        return this.mAllowedInForeground;
    }

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j2) {
        this(str, writableMap, j2, false);
    }

    public HeadlessJsTaskConfig(String str, WritableMap writableMap, long j2, boolean z) {
        this.mTaskKey = str;
        this.mData = writableMap;
        this.mTimeout = j2;
        this.mAllowedInForeground = z;
    }
}
