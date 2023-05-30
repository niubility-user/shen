package com.eclipsesource.v8;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;

/* loaded from: classes.dex */
public abstract class V8ScriptException extends V8RuntimeException {
    private final int endColumn;
    private final String fileName;
    private final String jsMessage;
    private final String jsStackTrace;
    private final int lineNumber;
    private final String sourceLine;
    private final int startColumn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V8ScriptException(String str, int i2, String str2, String str3, int i3, int i4, String str4, Throwable th) {
        this.fileName = str;
        this.lineNumber = i2;
        this.jsMessage = str2;
        this.sourceLine = str3;
        this.startColumn = i3;
        this.endColumn = i4;
        this.jsStackTrace = str4;
        if (th != null) {
            initCause(th);
        }
    }

    private char[] createCharSequence(int i2, char c2) {
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = c2;
        }
        return cArr;
    }

    private String createJSStackDetails() {
        if (this.jsStackTrace != null) {
            return ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + this.jsStackTrace;
        }
        return "";
    }

    private String createMessageDetails() {
        StringBuilder sb = new StringBuilder();
        String str = this.sourceLine;
        if (str != null && !str.isEmpty()) {
            sb.append('\n');
            sb.append(this.sourceLine);
            sb.append('\n');
            int i2 = this.startColumn;
            if (i2 >= 0) {
                sb.append(createCharSequence(i2, ' '));
                sb.append(createCharSequence(this.endColumn - this.startColumn, '^'));
            }
        }
        return sb.toString();
    }

    private String createMessageLine() {
        return this.fileName + ":" + this.lineNumber + ": " + this.jsMessage;
    }

    public int getEndColumn() {
        return this.endColumn;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getJSMessage() {
        return this.jsMessage;
    }

    public String getJSStackTrace() {
        return this.jsStackTrace;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return createMessageLine();
    }

    public String getSourceLine() {
        return this.sourceLine;
    }

    public int getStartColumn() {
        return this.startColumn;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return createMessageLine() + createMessageDetails() + createJSStackDetails() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + getClass().getName();
    }
}
