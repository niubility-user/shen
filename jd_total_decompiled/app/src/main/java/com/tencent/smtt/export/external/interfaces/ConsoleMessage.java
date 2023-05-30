package com.tencent.smtt.export.external.interfaces;

/* loaded from: classes9.dex */
public interface ConsoleMessage {

    /* loaded from: classes9.dex */
    public enum MessageLevel {
        TIP,
        LOG,
        WARNING,
        ERROR,
        DEBUG
    }

    int lineNumber();

    String message();

    MessageLevel messageLevel();

    String sourceId();
}
