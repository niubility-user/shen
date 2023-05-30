package com.googlecode.mp4parser.util;

/* loaded from: classes12.dex */
public class AndroidLogger extends Logger {
    private static final String TAG = "isoparser";
    String name;

    public AndroidLogger(String str) {
        this.name = str;
    }

    @Override // com.googlecode.mp4parser.util.Logger
    public void logDebug(String str) {
        String str2 = String.valueOf(this.name) + ":" + str;
    }

    @Override // com.googlecode.mp4parser.util.Logger
    public void logError(String str) {
        String str2 = String.valueOf(this.name) + ":" + str;
    }

    @Override // com.googlecode.mp4parser.util.Logger
    public void logWarn(String str) {
        String str2 = String.valueOf(this.name) + ":" + str;
    }
}
