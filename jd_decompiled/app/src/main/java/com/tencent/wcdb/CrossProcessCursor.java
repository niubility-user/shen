package com.tencent.wcdb;

/* loaded from: classes9.dex */
public interface CrossProcessCursor extends Cursor {
    void fillWindow(int i2, CursorWindow cursorWindow);

    CursorWindow getWindow();

    boolean onMove(int i2, int i3);
}
