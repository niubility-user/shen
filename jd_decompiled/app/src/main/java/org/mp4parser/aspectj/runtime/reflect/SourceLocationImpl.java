package org.mp4parser.aspectj.runtime.reflect;

import org.mp4parser.aspectj.lang.reflect.SourceLocation;

/* loaded from: classes11.dex */
class SourceLocationImpl implements SourceLocation {
    String fileName;
    int line;
    Class withinType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceLocationImpl(Class cls, String str, int i2) {
        this.withinType = cls;
        this.fileName = str;
        this.line = i2;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.SourceLocation
    public int getColumn() {
        return -1;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.SourceLocation
    public String getFileName() {
        return this.fileName;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.SourceLocation
    public int getLine() {
        return this.line;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.SourceLocation
    public Class getWithinType() {
        return this.withinType;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getFileName());
        stringBuffer.append(":");
        stringBuffer.append(getLine());
        return stringBuffer.toString();
    }
}
