package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@Beta
@GwtIncompatible
/* loaded from: classes12.dex */
public final class CharStreams {
    private static final int DEFAULT_BUF_SIZE = 2048;

    /* loaded from: classes12.dex */
    private static final class NullWriter extends Writer {
        private static final NullWriter INSTANCE = new NullWriter();

        private NullWriter() {
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(char c2) {
            return this;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }

        @Override // java.io.Writer
        public void write(int i2) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
            Preconditions.checkNotNull(cArr);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3 + i2, cArr.length);
        }

        @Override // java.io.Writer
        public void write(String str) {
            Preconditions.checkNotNull(str);
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return this;
        }

        @Override // java.io.Writer
        public void write(String str, int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3 + i2, str.length());
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence, int i2, int i3) {
            Preconditions.checkPositionIndexes(i2, i3, charSequence.length());
            return this;
        }
    }

    private CharStreams() {
    }

    public static Writer asWriter(Appendable appendable) {
        if (appendable instanceof Writer) {
            return (Writer) appendable;
        }
        return new AppendableWriter(appendable);
    }

    @CanIgnoreReturnValue
    public static long copy(Readable readable, Appendable appendable) throws IOException {
        if (readable instanceof Reader) {
            if (appendable instanceof StringBuilder) {
                return copyReaderToBuilder((Reader) readable, (StringBuilder) appendable);
            }
            return copyReaderToWriter((Reader) readable, asWriter(appendable));
        }
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(appendable);
        long j2 = 0;
        CharBuffer createBuffer = createBuffer();
        while (readable.read(createBuffer) != -1) {
            createBuffer.flip();
            appendable.append(createBuffer);
            j2 += createBuffer.remaining();
            createBuffer.clear();
        }
        return j2;
    }

    @CanIgnoreReturnValue
    static long copyReaderToBuilder(Reader reader, StringBuilder sb) throws IOException {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(sb);
        char[] cArr = new char[2048];
        long j2 = 0;
        while (true) {
            int read = reader.read(cArr);
            if (read == -1) {
                return j2;
            }
            sb.append(cArr, 0, read);
            j2 += read;
        }
    }

    @CanIgnoreReturnValue
    static long copyReaderToWriter(Reader reader, Writer writer) throws IOException {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(writer);
        char[] cArr = new char[2048];
        long j2 = 0;
        while (true) {
            int read = reader.read(cArr);
            if (read == -1) {
                return j2;
            }
            writer.write(cArr, 0, read);
            j2 += read;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    @CanIgnoreReturnValue
    public static long exhaust(Readable readable) throws IOException {
        CharBuffer createBuffer = createBuffer();
        long j2 = 0;
        while (true) {
            long read = readable.read(createBuffer);
            if (read == -1) {
                return j2;
            }
            j2 += read;
            createBuffer.clear();
        }
    }

    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    public static List<String> readLines(Readable readable) throws IOException {
        ArrayList arrayList = new ArrayList();
        LineReader lineReader = new LineReader(readable);
        while (true) {
            String readLine = lineReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine);
        }
    }

    public static void skipFully(Reader reader, long j2) throws IOException {
        Preconditions.checkNotNull(reader);
        while (j2 > 0) {
            long skip = reader.skip(j2);
            if (skip == 0) {
                throw new EOFException();
            }
            j2 -= skip;
        }
    }

    public static String toString(Readable readable) throws IOException {
        return toStringBuilder(readable).toString();
    }

    private static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (readable instanceof Reader) {
            copyReaderToBuilder((Reader) readable, sb);
        } else {
            copy(readable, sb);
        }
        return sb;
    }

    @CanIgnoreReturnValue
    public static <T> T readLines(Readable readable, LineProcessor<T> lineProcessor) throws IOException {
        String readLine;
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(lineProcessor);
        LineReader lineReader = new LineReader(readable);
        do {
            readLine = lineReader.readLine();
            if (readLine == null) {
                break;
            }
        } while (lineProcessor.processLine(readLine));
        return lineProcessor.getResult();
    }
}
