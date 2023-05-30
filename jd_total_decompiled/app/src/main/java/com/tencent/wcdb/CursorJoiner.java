package com.tencent.wcdb;

import java.util.Iterator;

/* loaded from: classes9.dex */
public final class CursorJoiner implements Iterator<Result>, Iterable<Result> {
    private int[] mColumnsLeft;
    private int[] mColumnsRight;
    private Result mCompareResult;
    private boolean mCompareResultIsValid;
    private Cursor mCursorLeft;
    private Cursor mCursorRight;
    private String[] mValues;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.tencent.wcdb.CursorJoiner$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$tencent$wcdb$CursorJoiner$Result;

        static {
            int[] iArr = new int[Result.values().length];
            $SwitchMap$com$tencent$wcdb$CursorJoiner$Result = iArr;
            try {
                iArr[Result.BOTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tencent$wcdb$CursorJoiner$Result[Result.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tencent$wcdb$CursorJoiner$Result[Result.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum Result {
        RIGHT,
        LEFT,
        BOTH
    }

    public CursorJoiner(Cursor cursor, String[] strArr, Cursor cursor2, String[] strArr2) {
        if (strArr.length == strArr2.length) {
            this.mCursorLeft = cursor;
            this.mCursorRight = cursor2;
            cursor.moveToFirst();
            this.mCursorRight.moveToFirst();
            this.mCompareResultIsValid = false;
            this.mColumnsLeft = buildColumnIndiciesArray(cursor, strArr);
            this.mColumnsRight = buildColumnIndiciesArray(cursor2, strArr2);
            this.mValues = new String[this.mColumnsLeft.length * 2];
            return;
        }
        throw new IllegalArgumentException("you must have the same number of columns on the left and right, " + strArr.length + " != " + strArr2.length);
    }

    private int[] buildColumnIndiciesArray(Cursor cursor, String[] strArr) {
        int[] iArr = new int[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            iArr[i2] = cursor.getColumnIndexOrThrow(strArr[i2]);
        }
        return iArr;
    }

    private static int compareStrings(String... strArr) {
        if (strArr.length % 2 == 0) {
            for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                if (strArr[i2] == null) {
                    if (strArr[i2 + 1] != null) {
                        return -1;
                    }
                } else {
                    int i3 = i2 + 1;
                    if (strArr[i3] == null) {
                        return 1;
                    }
                    int compareTo = strArr[i2].compareTo(strArr[i3]);
                    if (compareTo != 0) {
                        return compareTo < 0 ? -1 : 1;
                    }
                }
            }
            return 0;
        }
        throw new IllegalArgumentException("you must specify an even number of values");
    }

    private void incrementCursors() {
        if (this.mCompareResultIsValid) {
            int i2 = AnonymousClass1.$SwitchMap$com$tencent$wcdb$CursorJoiner$Result[this.mCompareResult.ordinal()];
            if (i2 == 1) {
                this.mCursorLeft.moveToNext();
                this.mCursorRight.moveToNext();
            } else if (i2 == 2) {
                this.mCursorLeft.moveToNext();
            } else if (i2 == 3) {
                this.mCursorRight.moveToNext();
            }
            this.mCompareResultIsValid = false;
        }
    }

    private static void populateValues(String[] strArr, Cursor cursor, int[] iArr, int i2) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            strArr[(i3 * 2) + i2] = cursor.getString(iArr[i3]);
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.mCompareResultIsValid) {
            return (this.mCursorLeft.isAfterLast() && this.mCursorRight.isAfterLast()) ? false : true;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$tencent$wcdb$CursorJoiner$Result[this.mCompareResult.ordinal()];
        if (i2 == 1) {
            return (this.mCursorLeft.isLast() && this.mCursorRight.isLast()) ? false : true;
        } else if (i2 == 2) {
            return (this.mCursorLeft.isLast() && this.mCursorRight.isAfterLast()) ? false : true;
        } else if (i2 == 3) {
            return (this.mCursorLeft.isAfterLast() && this.mCursorRight.isLast()) ? false : true;
        } else {
            throw new IllegalStateException("bad value for mCompareResult, " + this.mCompareResult);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Result> iterator() {
        return this;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("not implemented");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Iterator
    public Result next() {
        if (hasNext()) {
            incrementCursors();
            boolean z = !this.mCursorLeft.isAfterLast();
            boolean z2 = !this.mCursorRight.isAfterLast();
            if (z && z2) {
                populateValues(this.mValues, this.mCursorLeft, this.mColumnsLeft, 0);
                populateValues(this.mValues, this.mCursorRight, this.mColumnsRight, 1);
                int compareStrings = compareStrings(this.mValues);
                if (compareStrings == -1) {
                    this.mCompareResult = Result.LEFT;
                } else if (compareStrings == 0) {
                    this.mCompareResult = Result.BOTH;
                } else if (compareStrings == 1) {
                    this.mCompareResult = Result.RIGHT;
                }
            } else if (z) {
                this.mCompareResult = Result.LEFT;
            } else {
                this.mCompareResult = Result.RIGHT;
            }
            this.mCompareResultIsValid = true;
            return this.mCompareResult;
        }
        throw new IllegalStateException("you must only call next() when hasNext() is true");
    }
}
