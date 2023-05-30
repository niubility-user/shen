package com.google.common.io;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

@GwtIncompatible
/* loaded from: classes12.dex */
abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    @CanIgnoreReturnValue
    private boolean finishLine(boolean z) throws IOException {
        handleLine(this.line.toString(), this.sawReturn ? z ? "\r\n" : "\r" : z ? ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void add(char[] cArr, int i2, int i3) throws IOException {
        int i4;
        int i5;
        if (this.sawReturn && i3 > 0) {
            if (finishLine(cArr[i2] == '\n')) {
                i4 = i2 + 1;
                i5 = i2 + i3;
                int i6 = i4;
                while (i4 < i5) {
                    char c2 = cArr[i4];
                    if (c2 == '\n') {
                        this.line.append(cArr, i6, i4 - i6);
                        finishLine(true);
                    } else if (c2 != '\r') {
                        i4++;
                    } else {
                        this.line.append(cArr, i6, i4 - i6);
                        this.sawReturn = true;
                        int i7 = i4 + 1;
                        if (i7 < i5) {
                            if (finishLine(cArr[i7] == '\n')) {
                                i4 = i7;
                            }
                        }
                    }
                    i6 = i4 + 1;
                    i4++;
                }
                this.line.append(cArr, i6, i5 - i6);
            }
        }
        i4 = i2;
        i5 = i2 + i3;
        int i62 = i4;
        while (i4 < i5) {
        }
        this.line.append(cArr, i62, i5 - i62);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void finish() throws IOException {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }

    protected abstract void handleLine(String str, String str2) throws IOException;
}
