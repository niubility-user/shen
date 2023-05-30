package com.jingdong.app.util.image.assist;

/* loaded from: classes.dex */
public class JDFailReason {
    private final Throwable cause;
    private final JDFailType type;

    public JDFailReason(JDFailType jDFailType, Throwable th) {
        this.type = jDFailType;
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public JDFailType getType() {
        return this.type;
    }

    public String toString() {
        return "JDFailReason{type=" + this.type + ", cause=" + this.cause + '}';
    }
}
