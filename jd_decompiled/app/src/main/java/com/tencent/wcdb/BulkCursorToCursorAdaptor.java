package com.tencent.wcdb;

import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.RemoteException;
import com.tencent.wcdb.AbstractCursor;
import com.tencent.wcdb.support.Log;

/* loaded from: classes9.dex */
public final class BulkCursorToCursorAdaptor extends AbstractWindowedCursor {
    private static final String TAG = "BulkCursor";
    private IBulkCursor mBulkCursor;
    private String[] mColumns;
    private int mCount;
    private AbstractCursor.SelfContentObserver mObserverBridge = new AbstractCursor.SelfContentObserver(this);
    private boolean mWantsAllOnMoveCalls;

    private void throwIfCursorIsClosed() {
        if (this.mBulkCursor == null) {
            throw new StaleDataException("Attempted to access a cursor after it has been closed.");
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        IBulkCursor iBulkCursor = this.mBulkCursor;
        if (iBulkCursor != null) {
            try {
                try {
                    iBulkCursor.close();
                } catch (RemoteException unused) {
                    Log.w(TAG, "Remote process exception when closing");
                }
            } finally {
                this.mBulkCursor = null;
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractWindowedCursor, com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void copyStringToBuffer(int i2, CharArrayBuffer charArrayBuffer) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void deactivate() {
        super.deactivate();
        IBulkCursor iBulkCursor = this.mBulkCursor;
        if (iBulkCursor != null) {
            try {
                iBulkCursor.deactivate();
            } catch (RemoteException unused) {
                Log.w(TAG, "Remote process exception when deactivating");
            }
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public String[] getColumnNames() {
        throwIfCursorIsClosed();
        return this.mColumns;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public int getCount() {
        throwIfCursorIsClosed();
        return this.mCount;
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public Bundle getExtras() {
        throwIfCursorIsClosed();
        try {
            return this.mBulkCursor.getExtras();
        } catch (RemoteException e2) {
            throw new RuntimeException(e2);
        }
    }

    public IContentObserver getObserver() {
        try {
            return (IContentObserver) this.mObserverBridge.getClass().getMethod("getContentObserver", new Class[0]).invoke(this.mObserverBridge, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public void initialize(BulkCursorDescriptor bulkCursorDescriptor) {
        this.mBulkCursor = bulkCursorDescriptor.cursor;
        String[] strArr = bulkCursorDescriptor.columnNames;
        this.mColumns = strArr;
        this.mRowIdColumnIndex = DatabaseUtils.findRowIdColumnIndex(strArr);
        this.mWantsAllOnMoveCalls = bulkCursorDescriptor.wantsAllOnMoveCalls;
        this.mCount = bulkCursorDescriptor.count;
        CursorWindow cursorWindow = bulkCursorDescriptor.window;
        if (cursorWindow != null) {
            setWindow(cursorWindow);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0035 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036 A[RETURN] */
    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.CrossProcessCursor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onMove(int r3, int r4) {
        /*
            r2 = this;
            r2.throwIfCursorIsClosed()
            r3 = 0
            com.tencent.wcdb.CursorWindow r0 = r2.mWindow     // Catch: android.os.RemoteException -> L38
            if (r0 == 0) goto L28
            int r0 = r0.getStartPosition()     // Catch: android.os.RemoteException -> L38
            if (r4 < r0) goto L28
            com.tencent.wcdb.CursorWindow r0 = r2.mWindow     // Catch: android.os.RemoteException -> L38
            int r0 = r0.getStartPosition()     // Catch: android.os.RemoteException -> L38
            com.tencent.wcdb.CursorWindow r1 = r2.mWindow     // Catch: android.os.RemoteException -> L38
            int r1 = r1.getNumRows()     // Catch: android.os.RemoteException -> L38
            int r0 = r0 + r1
            if (r4 < r0) goto L1e
            goto L28
        L1e:
            boolean r0 = r2.mWantsAllOnMoveCalls     // Catch: android.os.RemoteException -> L38
            if (r0 == 0) goto L31
            com.tencent.wcdb.IBulkCursor r0 = r2.mBulkCursor     // Catch: android.os.RemoteException -> L38
            r0.onMove(r4)     // Catch: android.os.RemoteException -> L38
            goto L31
        L28:
            com.tencent.wcdb.IBulkCursor r0 = r2.mBulkCursor     // Catch: android.os.RemoteException -> L38
            com.tencent.wcdb.CursorWindow r4 = r0.getWindow(r4)     // Catch: android.os.RemoteException -> L38
            r2.setWindow(r4)     // Catch: android.os.RemoteException -> L38
        L31:
            com.tencent.wcdb.CursorWindow r4 = r2.mWindow
            if (r4 != 0) goto L36
            return r3
        L36:
            r3 = 1
            return r3
        L38:
            java.lang.String r4 = "BulkCursor"
            java.lang.String r0 = "Unable to get window because the remote process is dead"
            com.tencent.wcdb.support.Log.e(r4, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.BulkCursorToCursorAdaptor.onMove(int, int):boolean");
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerContentObserver(ContentObserver contentObserver) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public boolean requery() {
        throwIfCursorIsClosed();
        try {
            int requery = this.mBulkCursor.requery(getObserver());
            this.mCount = requery;
            if (requery != -1) {
                this.mPos = -1;
                closeWindow();
                super.requery();
                return true;
            }
            deactivate();
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "Unable to requery because the remote process exception " + e2.getMessage());
            deactivate();
            return false;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public Bundle respond(Bundle bundle) {
        throwIfCursorIsClosed();
        try {
            return this.mBulkCursor.respond(bundle);
        } catch (RemoteException e2) {
            Log.w(TAG, "respond() threw RemoteException, returning an empty bundle.", e2);
            return Bundle.EMPTY;
        }
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterContentObserver(ContentObserver contentObserver) {
    }

    @Override // com.tencent.wcdb.AbstractCursor, com.tencent.wcdb.Cursor, android.database.Cursor
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
    }
}
