package com.jingdong.aura;

import android.database.AbstractCursor;
import android.os.Bundle;
import com.jingdong.common.utils.SwitchQueryFetcher;

/* loaded from: classes4.dex */
public class XTimeCursor extends AbstractCursor {
    public static final String XTIME_KEY = "XTime";

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String[] getColumnNames() {
        return new String[0];
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getCount() {
        return 0;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public double getDouble(int i2) {
        return 0.0d;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public Bundle getExtras() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(XTIME_KEY, SwitchQueryFetcher.isXTime());
        return bundle;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public float getFloat(int i2) {
        return 0.0f;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public int getInt(int i2) {
        return 0;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public long getLong(int i2) {
        return 0L;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public short getShort(int i2) {
        return (short) 0;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public String getString(int i2) {
        return null;
    }

    @Override // android.database.AbstractCursor, android.database.Cursor
    public boolean isNull(int i2) {
        return false;
    }
}
