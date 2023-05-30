package kotlin.io;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u001a\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0005H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0006\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\b\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\tH\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\n\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000bH\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\f\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u000e\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000fH\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0010\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0011H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0012\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0013H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0014\u001a\u0018\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0015H\u0087\b\u00a2\u0006\u0004\b\u0003\u0010\u0016\u001a\u001a\u0010\u0017\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0004\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0005H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0006\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0007H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\b\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\tH\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\n\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000bH\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\f\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u000e\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u000fH\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0010\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0011H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0012\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0013H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0014\u001a\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0015H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0016\u001a\u0010\u0010\u0017\u001a\u00020\u0002H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a\u000f\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a!\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001eH\u0000\u00a2\u0006\u0004\b\u001a\u0010 \u001a+\u0010&\u001a\u00020\u000f*\u00020\u001e2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b&\u0010'\u001a\u0013\u0010(\u001a\u00020\u000f*\u00020#H\u0002\u00a2\u0006\u0004\b(\u0010)\u001a\u0013\u0010+\u001a\u00020\u0002*\u00020*H\u0002\u00a2\u0006\u0004\b+\u0010,\u001a\u001f\u00100\u001a\u00020\u0002*\u00020#2\n\u0010/\u001a\u00060-j\u0002`.H\u0002\u00a2\u0006\u0004\b0\u00101\"\u0016\u00102\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b2\u00103\"\u0016\u00104\u001a\u00020\u00058\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b4\u00103\"\u001d\u0010\u001f\u001a\u00020\u001e8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108\u00a8\u00069"}, d2 = {"", "message", "", "print", "(Ljava/lang/Object;)V", "", "(I)V", "", "(J)V", "", "(B)V", "", "(S)V", "", "(C)V", "", "(Z)V", "", "(F)V", "", "(D)V", "", "([C)V", "println", "()V", "", "readLine", "()Ljava/lang/String;", "Ljava/io/InputStream;", "inputStream", "Ljava/nio/charset/CharsetDecoder;", "decoder", "(Ljava/io/InputStream;Ljava/nio/charset/CharsetDecoder;)Ljava/lang/String;", "Ljava/nio/ByteBuffer;", "byteBuffer", "Ljava/nio/CharBuffer;", "charBuffer", "isEndOfStream", "tryDecode", "(Ljava/nio/charset/CharsetDecoder;Ljava/nio/ByteBuffer;Ljava/nio/CharBuffer;Z)Z", "endsWithLineSeparator", "(Ljava/nio/CharBuffer;)Z", "Ljava/nio/Buffer;", "flipBack", "(Ljava/nio/Buffer;)V", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "offloadPrefixTo", "(Ljava/nio/CharBuffer;Ljava/lang/StringBuilder;)V", "LINE_SEPARATOR_MAX_LENGTH", "I", "BUFFER_SIZE", "decoder$delegate", "Lkotlin/Lazy;", "getDecoder", "()Ljava/nio/charset/CharsetDecoder;", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "ConsoleKt")
/* loaded from: classes11.dex */
public final class ConsoleKt {
    private static final int BUFFER_SIZE = 32;
    private static final int LINE_SEPARATOR_MAX_LENGTH = 2;
    private static final Lazy decoder$delegate;

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<CharsetDecoder>() { // from class: kotlin.io.ConsoleKt$decoder$2
            @Override // kotlin.jvm.functions.Function0
            public final CharsetDecoder invoke() {
                return Charset.defaultCharset().newDecoder();
            }
        });
        decoder$delegate = lazy;
    }

    private static final boolean endsWithLineSeparator(@NotNull CharBuffer charBuffer) {
        int position = charBuffer.position();
        return position > 0 && charBuffer.get(position - 1) == '\n';
    }

    private static final void flipBack(@NotNull Buffer buffer) {
        buffer.position(buffer.limit());
        buffer.limit(buffer.capacity());
    }

    private static final CharsetDecoder getDecoder() {
        return (CharsetDecoder) decoder$delegate.getValue();
    }

    private static final void offloadPrefixTo(@NotNull CharBuffer charBuffer, StringBuilder sb) {
        charBuffer.flip();
        int limit = charBuffer.limit() - 1;
        for (int i2 = 0; i2 < limit; i2++) {
            sb.append(charBuffer.get());
        }
        charBuffer.compact();
    }

    @InlineOnly
    private static final void print(Object obj) {
        System.out.print(obj);
    }

    @InlineOnly
    private static final void println(Object obj) {
        System.out.println(obj);
    }

    @Nullable
    public static final String readLine() {
        InputStream inputStream = System.in;
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "System.`in`");
        return readLine(inputStream, getDecoder());
    }

    private static final boolean tryDecode(@NotNull CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer, boolean z) {
        int position = charBuffer.position();
        byteBuffer.flip();
        CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, z);
        if (decode.isError()) {
            decode.throwException();
        }
        boolean z2 = charBuffer.position() > position;
        if (z2) {
            byteBuffer.clear();
        } else {
            flipBack(byteBuffer);
        }
        return z2;
    }

    @InlineOnly
    private static final void print(int i2) {
        System.out.print(i2);
    }

    @InlineOnly
    private static final void println(int i2) {
        System.out.println(i2);
    }

    @Nullable
    public static final String readLine(@NotNull InputStream inputStream, @NotNull CharsetDecoder charsetDecoder) {
        if (charsetDecoder.maxCharsPerByte() <= ((float) 1)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(32);
            CharBuffer charBuffer = CharBuffer.allocate(4);
            StringBuilder sb = new StringBuilder();
            int read = inputStream.read();
            if (read == -1) {
                return null;
            }
            do {
                byteBuffer.put((byte) read);
                Intrinsics.checkExpressionValueIsNotNull(byteBuffer, "byteBuffer");
                Intrinsics.checkExpressionValueIsNotNull(charBuffer, "charBuffer");
                if (tryDecode(charsetDecoder, byteBuffer, charBuffer, false)) {
                    if (endsWithLineSeparator(charBuffer)) {
                        break;
                    } else if (charBuffer.remaining() < 2) {
                        offloadPrefixTo(charBuffer, sb);
                    }
                }
                read = inputStream.read();
            } while (read != -1);
            tryDecode(charsetDecoder, byteBuffer, charBuffer, true);
            charsetDecoder.reset();
            int position = charBuffer.position();
            if (position > 0 && charBuffer.get(position - 1) == '\n' && position - 1 > 0 && charBuffer.get(position - 1) == '\r') {
                position--;
            }
            charBuffer.flip();
            for (int i2 = 0; i2 < position; i2++) {
                sb.append(charBuffer.get());
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Encodings with multiple chars per byte are not supported".toString());
    }

    @InlineOnly
    private static final void print(long j2) {
        System.out.print(j2);
    }

    @InlineOnly
    private static final void println(long j2) {
        System.out.println(j2);
    }

    @InlineOnly
    private static final void print(byte b) {
        System.out.print(Byte.valueOf(b));
    }

    @InlineOnly
    private static final void println(byte b) {
        System.out.println(Byte.valueOf(b));
    }

    @InlineOnly
    private static final void print(short s) {
        System.out.print(Short.valueOf(s));
    }

    @InlineOnly
    private static final void println(short s) {
        System.out.println(Short.valueOf(s));
    }

    @InlineOnly
    private static final void print(char c2) {
        System.out.print(c2);
    }

    @InlineOnly
    private static final void println(char c2) {
        System.out.println(c2);
    }

    @InlineOnly
    private static final void print(boolean z) {
        System.out.print(z);
    }

    @InlineOnly
    private static final void println(boolean z) {
        System.out.println(z);
    }

    @InlineOnly
    private static final void print(float f2) {
        System.out.print(f2);
    }

    @InlineOnly
    private static final void println(float f2) {
        System.out.println(f2);
    }

    @InlineOnly
    private static final void print(double d) {
        System.out.print(d);
    }

    @InlineOnly
    private static final void println(double d) {
        System.out.println(d);
    }

    @InlineOnly
    private static final void print(char[] cArr) {
        System.out.print(cArr);
    }

    @InlineOnly
    private static final void println(char[] cArr) {
        System.out.println(cArr);
    }

    @InlineOnly
    private static final void println() {
        System.out.println();
    }
}
