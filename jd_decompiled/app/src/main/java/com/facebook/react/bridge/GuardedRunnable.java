package com.facebook.react.bridge;

/* loaded from: classes.dex */
public abstract class GuardedRunnable implements Runnable {
    private final ReactContext mReactContext;

    public GuardedRunnable(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            runGuarded();
        } catch (RuntimeException e2) {
            try {
                this.mReactContext.handleException(e2);
            } catch (Exception unused) {
            }
        }
    }

    public abstract void runGuarded();
}
