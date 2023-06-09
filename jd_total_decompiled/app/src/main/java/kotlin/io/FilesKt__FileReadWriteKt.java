package kotlin.io;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001e\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a(\u0010\t\u001a\u00020\b*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\b\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\f\u001a\u00020\u000b*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\f\u0010\r\u001a(\u0010\u000f\u001a\u00020\u000e*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0087\b\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a\u001e\u0010\u0012\u001a\u00020\u0011*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u0011\u0010\u0015\u001a\u00020\u0014*\u00020\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0019\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a\u0019\u0010\u001b\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\u0004\b\u001b\u0010\u001a\u001a\u001b\u0010\u001d\u001a\u00020\u001c*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a#\u0010 \u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b \u0010!\u001a#\u0010\"\u001a\u00020\u0018*\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001c2\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b\"\u0010!\u001aI\u0010)\u001a\u00020\u0018*\u00020\u000026\u0010(\u001a2\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00180#\u00a2\u0006\u0004\b)\u0010*\u001aQ\u0010)\u001a\u00020\u0018*\u00020\u00002\u0006\u0010+\u001a\u00020\u000626\u0010(\u001a2\u0012\u0013\u0012\u00110\u0014\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(&\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00180#\u00a2\u0006\u0004\b)\u0010,\u001a>\u0010/\u001a\u00020\u0018*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012!\u0010(\u001a\u001d\u0012\u0013\u0012\u00110\u001c\u00a2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00180-\u00a2\u0006\u0004\b/\u00100\u001a\u0014\u00102\u001a\u000201*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b2\u00103\u001a\u0014\u00105\u001a\u000204*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b5\u00106\u001a!\u00108\u001a\b\u0012\u0004\u0012\u00020\u001c07*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b8\u00109\u001aA\u0010>\u001a\u00028\u0000\"\u0004\b\u0000\u0010:*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0018\u0010<\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0;\u0012\u0004\u0012\u00028\u00000-H\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b>\u0010?\u0082\u0002\b\n\u0006\b\u0011(=0\u0001\u00a8\u0006@"}, d2 = {"Ljava/io/File;", "Ljava/nio/charset/Charset;", "charset", "Ljava/io/InputStreamReader;", "reader", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/io/InputStreamReader;", "", "bufferSize", "Ljava/io/BufferedReader;", "bufferedReader", "(Ljava/io/File;Ljava/nio/charset/Charset;I)Ljava/io/BufferedReader;", "Ljava/io/OutputStreamWriter;", "writer", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/io/OutputStreamWriter;", "Ljava/io/BufferedWriter;", "bufferedWriter", "(Ljava/io/File;Ljava/nio/charset/Charset;I)Ljava/io/BufferedWriter;", "Ljava/io/PrintWriter;", "printWriter", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/io/PrintWriter;", "", "readBytes", "(Ljava/io/File;)[B", "array", "", "writeBytes", "(Ljava/io/File;[B)V", "appendBytes", "", "readText", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/lang/String;", "text", "writeText", "(Ljava/io/File;Ljava/lang/String;Ljava/nio/charset/Charset;)V", "appendText", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "action", "forEachBlock", "(Ljava/io/File;Lkotlin/jvm/functions/Function2;)V", "blockSize", "(Ljava/io/File;ILkotlin/jvm/functions/Function2;)V", "Lkotlin/Function1;", "line", "forEachLine", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)V", "Ljava/io/FileInputStream;", "inputStream", "(Ljava/io/File;)Ljava/io/FileInputStream;", "Ljava/io/FileOutputStream;", "outputStream", "(Ljava/io/File;)Ljava/io/FileOutputStream;", "", "readLines", "(Ljava/io/File;Ljava/nio/charset/Charset;)Ljava/util/List;", "T", "Lkotlin/sequences/Sequence;", JDReactConstant.BLOCK, "Requires newer compiler version to be inlined correctly.", "useLines", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/io/FilesKt")
/* loaded from: classes11.dex */
public class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    public static final void appendBytes(@NotNull File file, @NotNull byte[] bArr) {
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        try {
            fileOutputStream.write(bArr);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
        } finally {
        }
    }

    public static final void appendText(@NotNull File file, @NotNull String str, @NotNull Charset charset) {
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        appendBytes(file, bytes);
    }

    public static /* synthetic */ void appendText$default(File file, String str, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        appendText(file, str, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull File file, Charset charset, int i2) {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, i2);
    }

    static /* synthetic */ BufferedReader bufferedReader$default(File file, Charset charset, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i3 & 2) != 0) {
            i2 = 8192;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, i2);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull File file, Charset charset, int i2) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, i2);
    }

    static /* synthetic */ BufferedWriter bufferedWriter$default(File file, Charset charset, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i3 & 2) != 0) {
            i2 = 8192;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, i2);
    }

    public static final void forEachBlock(@NotNull File file, @NotNull Function2<? super byte[], ? super Integer, Unit> function2) {
        forEachBlock(file, 4096, function2);
    }

    public static final void forEachLine(@NotNull File file, @NotNull Charset charset, @NotNull Function1<? super String, Unit> function1) {
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream(file), charset)), function1);
    }

    public static /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        forEachLine(file, charset, function1);
    }

    @InlineOnly
    private static final FileInputStream inputStream(@NotNull File file) {
        return new FileInputStream(file);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(@NotNull File file) {
        return new FileOutputStream(file);
    }

    @InlineOnly
    private static final PrintWriter printWriter(@NotNull File file, Charset charset) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    static /* synthetic */ PrintWriter printWriter$default(File file, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @NotNull
    public static final byte[] readBytes(@NotNull File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            long length = file.length();
            if (length <= Integer.MAX_VALUE) {
                int i2 = (int) length;
                byte[] bArr = new byte[i2];
                int i3 = i2;
                int i4 = 0;
                while (i3 > 0) {
                    int read = fileInputStream.read(bArr, i4, i3);
                    if (read < 0) {
                        break;
                    }
                    i3 -= read;
                    i4 += read;
                }
                if (i3 > 0) {
                    bArr = Arrays.copyOf(bArr, i4);
                    Intrinsics.checkExpressionValueIsNotNull(bArr, "java.util.Arrays.copyOf(this, newSize)");
                } else {
                    int read2 = fileInputStream.read();
                    if (read2 != -1) {
                        ExposingBufferByteArrayOutputStream exposingBufferByteArrayOutputStream = new ExposingBufferByteArrayOutputStream(R2.drawable.button_p_02);
                        exposingBufferByteArrayOutputStream.write(read2);
                        ByteStreamsKt.copyTo$default(fileInputStream, exposingBufferByteArrayOutputStream, 0, 2, null);
                        int size = exposingBufferByteArrayOutputStream.size() + i2;
                        if (size >= 0) {
                            byte[] buffer = exposingBufferByteArrayOutputStream.getBuffer();
                            byte[] copyOf = Arrays.copyOf(bArr, size);
                            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                            bArr = ArraysKt___ArraysJvmKt.copyInto(buffer, copyOf, i2, 0, exposingBufferByteArrayOutputStream.size());
                        } else {
                            throw new OutOfMemoryError("File " + file + " is too big to fit in memory.");
                        }
                    }
                }
                CloseableKt.closeFinally(fileInputStream, null);
                return bArr;
            }
            throw new OutOfMemoryError("File " + file + " is too big (" + length + " bytes) to fit in memory.");
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileInputStream, th);
                throw th2;
            }
        }
    }

    @NotNull
    public static final List<String> readLines(@NotNull File file, @NotNull Charset charset) {
        final ArrayList arrayList = new ArrayList();
        forEachLine(file, charset, new Function1<String, Unit>() { // from class: kotlin.io.FilesKt__FileReadWriteKt$readLines$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(@NotNull String str) {
                arrayList.add(str);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ List readLines$default(File file, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readLines(file, charset);
    }

    @NotNull
    public static final String readText(@NotNull File file, @NotNull Charset charset) {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        try {
            String readText = TextStreamsKt.readText(inputStreamReader);
            CloseableKt.closeFinally(inputStreamReader, null);
            return readText;
        } finally {
        }
    }

    public static /* synthetic */ String readText$default(File file, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readText(file, charset);
    }

    @InlineOnly
    private static final InputStreamReader reader(@NotNull File file, Charset charset) {
        return new InputStreamReader(new FileInputStream(file), charset);
    }

    static /* synthetic */ InputStreamReader reader$default(File file, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(new FileInputStream(file), charset);
    }

    public static final <T> T useLines(@NotNull File file, @NotNull Charset charset, @NotNull Function1<? super Sequence<String>, ? extends T> function1) {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            T invoke = function1.invoke(TextStreamsKt.lineSequence(bufferedReader));
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReader, null);
            } else {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                    CloseableKt.closeFinally(bufferedReader, th);
                } else {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused) {
                    }
                }
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public static /* synthetic */ Object useLines$default(File file, Charset charset, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), charset);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            Object invoke = function1.invoke(TextStreamsKt.lineSequence(bufferedReader));
            InlineMarker.finallyStart(1);
            if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
                CloseableKt.closeFinally(bufferedReader, null);
            } else {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
        }
    }

    public static final void writeBytes(@NotNull File file, @NotNull byte[] bArr) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(fileOutputStream, null);
        } finally {
        }
    }

    public static final void writeText(@NotNull File file, @NotNull String str, @NotNull Charset charset) {
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        writeBytes(file, bytes);
    }

    public static /* synthetic */ void writeText$default(File file, String str, Charset charset, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(file, str, charset);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull File file, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream(file), charset);
    }

    static /* synthetic */ OutputStreamWriter writer$default(File file, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(new FileOutputStream(file), charset);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, byte[]] */
    public static final void forEachBlock(@NotNull File file, int i2, @NotNull Function2<? super byte[], ? super Integer, Unit> function2) {
        int coerceAtLeast;
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i2, 512);
        ?? r2 = new byte[coerceAtLeast];
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int read = fileInputStream.read(r2);
                if (read <= 0) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    return;
                }
                function2.invoke(r2, Integer.valueOf(read));
            } finally {
            }
        }
    }
}
