package com.tencent.wcdb.support;

import android.os.RemoteException;
import com.tencent.wcdb.support.ICancellationSignal;

/* loaded from: classes9.dex */
public final class CancellationSignal {
    private boolean mCancelInProgress;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;
    private ICancellationSignal mRemote;

    /* loaded from: classes9.dex */
    public interface OnCancelListener {
        void onCancel();
    }

    /* loaded from: classes9.dex */
    private static final class Transport extends ICancellationSignal.Stub {
        final CancellationSignal mCancellationSignal;

        private Transport() {
            this.mCancellationSignal = new CancellationSignal();
        }

        @Override // com.tencent.wcdb.support.ICancellationSignal
        public void cancel() throws RemoteException {
            this.mCancellationSignal.cancel();
        }
    }

    public static ICancellationSignal createTransport() {
        return new Transport();
    }

    public static CancellationSignal fromTransport(ICancellationSignal iCancellationSignal) {
        if (iCancellationSignal instanceof Transport) {
            return ((Transport) iCancellationSignal).mCancellationSignal;
        }
        return null;
    }

    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void cancel() {
        synchronized (this) {
            if (this.mIsCanceled) {
                return;
            }
            this.mIsCanceled = true;
            this.mCancelInProgress = true;
            OnCancelListener onCancelListener = this.mOnCancelListener;
            ICancellationSignal iCancellationSignal = this.mRemote;
            if (onCancelListener != null) {
                try {
                    onCancelListener.onCancel();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.mCancelInProgress = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (iCancellationSignal != null) {
                try {
                    iCancellationSignal.cancel();
                } catch (RemoteException unused) {
                }
            }
            synchronized (this) {
                this.mCancelInProgress = false;
                notifyAll();
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this) {
            z = this.mIsCanceled;
        }
        return z;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        synchronized (this) {
            waitForCancelFinishedLocked();
            if (this.mOnCancelListener == onCancelListener) {
                return;
            }
            this.mOnCancelListener = onCancelListener;
            if (this.mIsCanceled && onCancelListener != null) {
                onCancelListener.onCancel();
            }
        }
    }

    public void setRemote(ICancellationSignal iCancellationSignal) {
        synchronized (this) {
            waitForCancelFinishedLocked();
            if (this.mRemote == iCancellationSignal) {
                return;
            }
            this.mRemote = iCancellationSignal;
            if (this.mIsCanceled && iCancellationSignal != null) {
                try {
                    iCancellationSignal.cancel();
                } catch (RemoteException unused) {
                }
            }
        }
    }

    public void throwIfCanceled() {
        if (isCanceled()) {
            throw new OperationCanceledException();
        }
    }
}
