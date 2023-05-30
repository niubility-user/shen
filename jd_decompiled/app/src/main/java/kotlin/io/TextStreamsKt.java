package kotlin.io;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u001e\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001e\u0010\u0004\u001a\u00020\u0007*\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\b\u001a%\u0010\r\u001a\u00020\u000b*\u00020\u00002\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u000f*\u00020\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a7\u0010\u0016\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0012*\u00020\u00002\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0013\u0012\u0004\u0012\u00028\u00000\tH\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0019\u001a\u00020\u0018*\u00020\nH\u0087\b\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\u0013*\u00020\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u0011\u0010\u001d\u001a\u00020\n*\u00020\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a#\u0010!\u001a\u00020 *\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u00a2\u0006\u0004\b!\u0010\"\u001a\u001e\u0010\u001d\u001a\u00020\n*\u00020#2\b\b\u0002\u0010%\u001a\u00020$H\u0087\b\u00a2\u0006\u0004\b\u001d\u0010&\u001a\u0011\u0010(\u001a\u00020'*\u00020#\u00a2\u0006\u0004\b(\u0010)\u0082\u0002\b\n\u0006\b\u0011(\u00150\u0001\u00a8\u0006*"}, d2 = {"Ljava/io/Reader;", "", "bufferSize", "Ljava/io/BufferedReader;", "buffered", "(Ljava/io/Reader;I)Ljava/io/BufferedReader;", "Ljava/io/Writer;", "Ljava/io/BufferedWriter;", "(Ljava/io/Writer;I)Ljava/io/BufferedWriter;", "Lkotlin/Function1;", "", "", "action", "forEachLine", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)V", "", "readLines", "(Ljava/io/Reader;)Ljava/util/List;", "T", "Lkotlin/sequences/Sequence;", JDReactConstant.BLOCK, "Requires newer compiler version to be inlined correctly.", "useLines", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Ljava/io/StringReader;", "reader", "(Ljava/lang/String;)Ljava/io/StringReader;", "lineSequence", "(Ljava/io/BufferedReader;)Lkotlin/sequences/Sequence;", "readText", "(Ljava/io/Reader;)Ljava/lang/String;", "out", "", "copyTo", "(Ljava/io/Reader;Ljava/io/Writer;I)J", "Ljava/net/URL;", "Ljava/nio/charset/Charset;", "charset", "(Ljava/net/URL;Ljava/nio/charset/Charset;)Ljava/lang/String;", "", "readBytes", "(Ljava/net/URL;)[B", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "TextStreamsKt")
/* loaded from: classes11.dex */
public final class TextStreamsKt {
    @InlineOnly
    private static final BufferedReader buffered(@NotNull Reader reader, int i2) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i2);
    }

    static /* synthetic */ BufferedReader buffered$default(Reader reader, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 8192;
        }
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i2);
    }

    public static final long copyTo(@NotNull Reader reader, @NotNull Writer writer, int i2) {
        char[] cArr = new char[i2];
        int read = reader.read(cArr);
        long j2 = 0;
        while (read >= 0) {
            writer.write(cArr, 0, read);
            j2 += read;
            read = reader.read(cArr);
        }
        return j2;
    }

    public static /* synthetic */ long copyTo$default(Reader reader, Writer writer, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 8192;
        }
        return copyTo(reader, writer, i2);
    }

    public static final void forEachLine(@NotNull Reader reader, @NotNull Function1<? super String, Unit> function1) {
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        try {
            Iterator<String> it = lineSequence(bufferedReader).iterator();
            while (it.hasNext()) {
                function1.invoke(it.next());
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(bufferedReader, null);
        } finally {
        }
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader bufferedReader) {
        Sequence<String> constrainOnce;
        constrainOnce = SequencesKt__SequencesKt.constrainOnce(new LinesSequence(bufferedReader));
        return constrainOnce;
    }

    @NotNull
    public static final byte[] readBytes(@NotNull URL url) {
        InputStream it = url.openStream();
        try {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            byte[] readBytes = ByteStreamsKt.readBytes(it);
            CloseableKt.closeFinally(it, null);
            return readBytes;
        } finally {
        }
    }

    @NotNull
    public static final List<String> readLines(@NotNull Reader reader) {
        final ArrayList arrayList = new ArrayList();
        forEachLine(reader, new Function1<String, Unit>() { // from class: kotlin.io.TextStreamsKt$readLines$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull String str) {
                arrayList.add(str);
            }
        });
        return arrayList;
    }

    @NotNull
    public static final String readText(@NotNull Reader reader) {
        StringWriter stringWriter = new StringWriter();
        copyTo$default(reader, stringWriter, 0, 2, null);
        String stringWriter2 = stringWriter.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringWriter2, "buffer.toString()");
        return stringWriter2;
    }

    static /* synthetic */ String readText$default(URL url, Charset charset, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new String(readBytes(url), charset);
    }

    @InlineOnly
    private static final StringReader reader(@NotNull String str) {
        return new StringReader(str);
    }

    public static final <T> T useLines(@NotNull Reader reader, @NotNull Function1<? super Sequence<String>, ? extends T> function1) {
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, 8192);
        try {
            T invoke = function1.invoke(lineSequence(bufferedReader));
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

    @InlineOnly
    private static final BufferedWriter buffered(@NotNull Writer writer, int i2) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i2);
    }

    static /* synthetic */ BufferedWriter buffered$default(Writer writer, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = 8192;
        }
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i2);
    }

    @InlineOnly
    private static final String readText(@NotNull URL url, Charset charset) {
        return new String(readBytes(url), charset);
    }
}
