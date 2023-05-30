package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.ReactConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@DoNotStrip
/* loaded from: classes.dex */
public class Inspector {
    private final HybridData mHybridData;

    @DoNotStrip
    /* loaded from: classes.dex */
    public static class LocalConnection {
        private final HybridData mHybridData;

        private LocalConnection(HybridData hybridData) {
            this.mHybridData = hybridData;
        }

        public native void disconnect();

        public native void sendMessage(String str);
    }

    @DoNotStrip
    /* loaded from: classes.dex */
    public static class Page {
        private final int mId;
        private final String mTitle;
        private final String mVM;

        @DoNotStrip
        private Page(int i2, String str, String str2) {
            this.mId = i2;
            this.mTitle = str;
            this.mVM = str2;
        }

        public int getId() {
            return this.mId;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getVM() {
            return this.mVM;
        }

        public String toString() {
            return "Page{mId=" + this.mId + ", mTitle='" + this.mTitle + "'}";
        }
    }

    @DoNotStrip
    /* loaded from: classes.dex */
    public interface RemoteConnection {
        @DoNotStrip
        void onDisconnect();

        @DoNotStrip
        void onMessage(String str);
    }

    static {
        ReactBridge.staticInit();
    }

    private Inspector(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public static LocalConnection connect(int i2, RemoteConnection remoteConnection) {
        try {
            return instance().connectNative(i2, remoteConnection);
        } catch (UnsatisfiedLinkError e2) {
            FLog.e(ReactConstants.TAG, "Inspector doesn't work in open source yet", e2);
            throw new RuntimeException(e2);
        }
    }

    private native LocalConnection connectNative(int i2, RemoteConnection remoteConnection);

    public static List<Page> getPages() {
        try {
            return Arrays.asList(instance().getPagesNative());
        } catch (UnsatisfiedLinkError e2) {
            FLog.e(ReactConstants.TAG, "Inspector doesn't work in open source yet", e2);
            return Collections.emptyList();
        }
    }

    private native Page[] getPagesNative();

    private static native Inspector instance();
}
