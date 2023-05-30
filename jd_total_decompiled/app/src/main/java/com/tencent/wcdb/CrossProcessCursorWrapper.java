package com.tencent.wcdb;

/* loaded from: classes9.dex */
public class CrossProcessCursorWrapper extends CursorWrapper implements CrossProcessCursor {
    public CrossProcessCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @Override // com.tencent.wcdb.CrossProcessCursor
    public void fillWindow(int i2, CursorWindow cursorWindow) {
        Cursor cursor = this.mCursor;
        if (cursor instanceof CrossProcessCursor) {
            ((CrossProcessCursor) cursor).fillWindow(i2, cursorWindow);
        } else {
            DatabaseUtils.cursorFillWindow(cursor, i2, cursorWindow);
        }
    }

    @Override // com.tencent.wcdb.CrossProcessCursor
    public CursorWindow getWindow() {
        Cursor cursor = this.mCursor;
        if (cursor instanceof CrossProcessCursor) {
            return ((CrossProcessCursor) cursor).getWindow();
        }
        return null;
    }

    @Override // com.tencent.wcdb.CrossProcessCursor
    public boolean onMove(int i2, int i3) {
        Cursor cursor = this.mCursor;
        if (cursor instanceof CrossProcessCursor) {
            return ((CrossProcessCursor) cursor).onMove(i2, i3);
        }
        return true;
    }
}
