package com.facebook.react.bridge;

import android.os.AsyncTask;

/* loaded from: classes.dex */
public abstract class GuardedAsyncTask<Params, Progress> extends AsyncTask<Params, Progress, Void> {
    private final ReactContext mReactContext;

    /* JADX INFO: Access modifiers changed from: protected */
    public GuardedAsyncTask(ReactContext reactContext) {
        this.mReactContext = reactContext;
    }

    protected abstract void doInBackgroundGuarded(Params... paramsArr);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public final Void doInBackground(Params... paramsArr) {
        try {
            doInBackgroundGuarded(paramsArr);
            return null;
        } catch (RuntimeException e2) {
            try {
                this.mReactContext.handleException(e2);
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
