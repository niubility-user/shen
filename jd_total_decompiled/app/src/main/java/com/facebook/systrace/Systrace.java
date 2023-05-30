package com.facebook.systrace;

import android.os.Build;
import android.os.Trace;

/* loaded from: classes12.dex */
public class Systrace {
    public static final long TRACE_TAG_REACT_APPS = 0;
    public static final long TRACE_TAG_REACT_FRESCO = 0;
    public static final long TRACE_TAG_REACT_JAVA_BRIDGE = 0;
    public static final long TRACE_TAG_REACT_JS_VM_CALLS = 0;
    public static final long TRACE_TAG_REACT_VIEW = 0;

    /* loaded from: classes12.dex */
    public enum EventScope {
        THREAD('t'),
        PROCESS('p'),
        GLOBAL('g');
        
        private final char mCode;

        EventScope(char c2) {
            this.mCode = c2;
        }

        public char getCode() {
            return this.mCode;
        }
    }

    public static void beginAsyncSection(long j2, String str, int i2) {
    }

    public static void beginAsyncSection(long j2, String str, int i2, long j3) {
    }

    public static void beginSection(long j2, String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.beginSection(str);
        }
    }

    public static void endAsyncFlow(long j2, String str, int i2) {
    }

    public static void endAsyncSection(long j2, String str, int i2) {
    }

    public static void endAsyncSection(long j2, String str, int i2, long j3) {
    }

    public static void endSection(long j2) {
        if (Build.VERSION.SDK_INT >= 18) {
            Trace.endSection();
        }
    }

    public static boolean isTracing(long j2) {
        return false;
    }

    public static void registerListener(TraceListener traceListener) {
    }

    public static void startAsyncFlow(long j2, String str, int i2) {
    }

    public static void stepAsyncFlow(long j2, String str, int i2) {
    }

    public static void traceCounter(long j2, String str, int i2) {
    }

    public static void traceInstant(long j2, String str, EventScope eventScope) {
    }

    public static void unregisterListener(TraceListener traceListener) {
    }
}
