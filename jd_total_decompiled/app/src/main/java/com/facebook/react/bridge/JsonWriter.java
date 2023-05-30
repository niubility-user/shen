package com.facebook.react.bridge;

import com.jd.dynamic.DYConstants;
import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Deque;

/* loaded from: classes.dex */
public class JsonWriter implements Closeable {
    private final Deque<Scope> mScopes = new ArrayDeque();
    private final Writer mWriter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.bridge.JsonWriter$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope;

        static {
            int[] iArr = new int[Scope.values().length];
            $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope = iArr;
            try {
                iArr[Scope.EMPTY_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope[Scope.EMPTY_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope[Scope.ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$JsonWriter$Scope[Scope.OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum Scope {
        EMPTY_OBJECT,
        OBJECT,
        EMPTY_ARRAY,
        ARRAY
    }

    public JsonWriter(Writer writer) {
        this.mWriter = writer;
    }

    private void beforeName() throws IOException {
        Scope peek = this.mScopes.peek();
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$JsonWriter$Scope[peek.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                replace(Scope.OBJECT);
                return;
            } else if (i2 != 3) {
                if (i2 == 4) {
                    this.mWriter.write(44);
                    return;
                }
                throw new IllegalStateException("Unknown scope: " + peek);
            }
        }
        throw new IllegalStateException("name not allowed in array");
    }

    private void beforeValue() throws IOException {
        Scope peek = this.mScopes.peek();
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$react$bridge$JsonWriter$Scope[peek.ordinal()];
        if (i2 == 1) {
            replace(Scope.ARRAY);
        } else if (i2 == 2) {
            throw new IllegalArgumentException(Scope.EMPTY_OBJECT.name());
        } else {
            if (i2 == 3) {
                this.mWriter.write(44);
            } else if (i2 == 4) {
            } else {
                throw new IllegalStateException("Unknown scope: " + peek);
            }
        }
    }

    private void open(Scope scope, char c2) throws IOException {
        this.mScopes.push(scope);
        this.mWriter.write(c2);
    }

    private void replace(Scope scope) {
        this.mScopes.pop();
        this.mScopes.push(scope);
    }

    private void string(String str) throws IOException {
        this.mWriter.write(34);
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\f') {
                this.mWriter.write("\\f");
            } else if (charAt == '\r') {
                this.mWriter.write("\\r");
            } else if (charAt == '\"' || charAt == '\\') {
                this.mWriter.write(92);
                this.mWriter.write(charAt);
            } else if (charAt != '\u2028' && charAt != '\u2029') {
                switch (charAt) {
                    case '\b':
                        this.mWriter.write("\\b");
                        continue;
                    case '\t':
                        this.mWriter.write("\\t");
                        continue;
                    case '\n':
                        this.mWriter.write("\\n");
                        continue;
                    default:
                        if (charAt <= 31) {
                            this.mWriter.write(String.format("\\u%04x", Integer.valueOf(charAt)));
                            continue;
                        } else {
                            this.mWriter.write(charAt);
                            break;
                        }
                }
            } else {
                this.mWriter.write(String.format("\\u%04x", Integer.valueOf(charAt)));
            }
        }
        this.mWriter.write(34);
    }

    public JsonWriter beginArray() throws IOException {
        open(Scope.EMPTY_ARRAY, '[');
        return this;
    }

    public JsonWriter beginObject() throws IOException {
        open(Scope.EMPTY_OBJECT, '{');
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mWriter.close();
    }

    public JsonWriter endArray() throws IOException {
        close(']');
        return this;
    }

    public JsonWriter endObject() throws IOException {
        close('}');
        return this;
    }

    public JsonWriter name(String str) throws IOException {
        if (str != null) {
            beforeName();
            string(str);
            this.mWriter.write(58);
            return this;
        }
        throw new NullPointerException("name can not be null");
    }

    public JsonWriter nullValue() throws IOException {
        beforeValue();
        this.mWriter.write(DYConstants.DY_NULL_STR);
        return this;
    }

    public JsonWriter rawValue(String str) throws IOException {
        beforeValue();
        this.mWriter.write(str);
        return this;
    }

    public JsonWriter value(String str) throws IOException {
        if (str == null) {
            return nullValue();
        }
        beforeValue();
        string(str);
        return this;
    }

    private void close(char c2) throws IOException {
        this.mScopes.pop();
        this.mWriter.write(c2);
    }

    public JsonWriter value(boolean z) throws IOException {
        beforeValue();
        this.mWriter.write(z ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
        return this;
    }

    public JsonWriter value(double d) throws IOException {
        beforeValue();
        this.mWriter.append((CharSequence) Double.toString(d));
        return this;
    }

    public JsonWriter value(long j2) throws IOException {
        beforeValue();
        this.mWriter.write(Long.toString(j2));
        return this;
    }

    public JsonWriter value(Number number) throws IOException {
        if (number == null) {
            return nullValue();
        }
        beforeValue();
        this.mWriter.append((CharSequence) number.toString());
        return this;
    }
}
