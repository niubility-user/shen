package com.airbnb.lottie.parser.moshi;

import androidx.annotation.Nullable;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.jd.dynamic.DYConstants;
import java.io.EOFException;
import java.io.IOException;
import jd.wjlogin_sdk.util.ReplyCode;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* loaded from: classes.dex */
public final class JsonUtf8Reader extends JsonReader {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_BUFFERED_NAME = 15;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 18;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 16;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 17;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final Buffer buffer;
    private int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    @Nullable
    private String peekedString;
    private final BufferedSource source;
    private static final ByteString SINGLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("'\\");
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("\"\\");
    private static final ByteString UNQUOTED_STRING_TERMINALS = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = ByteString.encodeUtf8("\n\r");
    private static final ByteString CLOSING_BLOCK_COMMENT = ByteString.encodeUtf8("*/");

    public JsonUtf8Reader(BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            this.source = bufferedSource;
            this.buffer = bufferedSource.buffer();
            pushScope(6);
            return;
        }
        throw new NullPointerException("source == null");
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int doPeek() throws IOException {
        int[] iArr = this.scopes;
        int i2 = this.stackSize;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int nextNonWhitespace = nextNonWhitespace(true);
            this.buffer.readByte();
            if (nextNonWhitespace != 44) {
                if (nextNonWhitespace != 59) {
                    if (nextNonWhitespace == 93) {
                        this.peeked = 4;
                        return 4;
                    }
                    throw syntaxError("Unterminated array");
                }
                checkLenient();
            }
        } else if (i3 == 3 || i3 == 5) {
            iArr[i2 - 1] = 4;
            if (i3 == 5) {
                int nextNonWhitespace2 = nextNonWhitespace(true);
                this.buffer.readByte();
                if (nextNonWhitespace2 != 44) {
                    if (nextNonWhitespace2 != 59) {
                        if (nextNonWhitespace2 == 125) {
                            this.peeked = 2;
                            return 2;
                        }
                        throw syntaxError("Unterminated object");
                    }
                    checkLenient();
                }
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.buffer.readByte();
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                this.buffer.readByte();
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 != 125) {
                checkLenient();
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            } else if (i3 != 5) {
                this.buffer.readByte();
                this.peeked = 2;
                return 2;
            } else {
                throw syntaxError("Expected name");
            }
        } else if (i3 == 4) {
            iArr[i2 - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            this.buffer.readByte();
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.source.request(1L) && this.buffer.getByte(0L) == 62) {
                        this.buffer.readByte();
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (i3 == 6) {
            iArr[i2 - 1] = 7;
        } else if (i3 == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 18;
                return 18;
            }
            checkLenient();
        } else if (i3 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            this.buffer.readByte();
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 == 39) {
            checkLenient();
            this.buffer.readByte();
            this.peeked = 8;
            return 8;
        } else {
            if (nextNonWhitespace5 != 44 && nextNonWhitespace5 != 59) {
                if (nextNonWhitespace5 == 91) {
                    this.buffer.readByte();
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 != 123) {
                        int peekKeyword = peekKeyword();
                        if (peekKeyword != 0) {
                            return peekKeyword;
                        }
                        int peekNumber = peekNumber();
                        if (peekNumber != 0) {
                            return peekNumber;
                        }
                        if (isLiteral(this.buffer.getByte(0L))) {
                            checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        throw syntaxError("Expected value");
                    }
                    this.buffer.readByte();
                    this.peeked = 1;
                    return 1;
                } else if (i3 == 1) {
                    this.buffer.readByte();
                    this.peeked = 4;
                    return 4;
                }
            }
            if (i3 != 1 && i3 != 2) {
                throw syntaxError("Unexpected value");
            }
            checkLenient();
            this.peeked = 7;
            return 7;
        }
    }

    private int findName(String str, JsonReader.Options options) {
        int length = options.strings.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.strings[i2])) {
                this.peeked = 0;
                this.pathNames[this.stackSize - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private int findString(String str, JsonReader.Options options) {
        int length = options.strings.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.strings[i2])) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
        }
        return -1;
    }

    private boolean isLiteral(int i2) throws IOException {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (i2 != 47 && i2 != 61) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:0x0025, code lost:
        r6.buffer.skip((long) (r3 - 1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x002f, code lost:
        if (r1 != 47) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0039, code lost:
        if (r6.source.request(2) != false) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x003b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x003c, code lost:
        checkLenient();
        r3 = r6.buffer.getByte(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0049, code lost:
        if (r3 == 42) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x004b, code lost:
        if (r3 == 47) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x004d, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x004e, code lost:
        r6.buffer.readByte();
        r6.buffer.readByte();
        skipToEndOfLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x005c, code lost:
        r6.buffer.readByte();
        r6.buffer.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x006a, code lost:
        if (skipToEndOfBlockComment() == false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0073, code lost:
        throw syntaxError("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0076, code lost:
        if (r1 != 35) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0078, code lost:
        checkLenient();
        skipToEndOfLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x007f, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int nextNonWhitespace(boolean z) throws IOException {
        while (true) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (!this.source.request(i3)) {
                    if (z) {
                        throw new EOFException("End of input");
                    }
                    return -1;
                }
                byte b = this.buffer.getByte(i2);
                if (b != 10 && b != 32 && b != 13 && b != 9) {
                    break;
                }
                i2 = i3;
            }
        }
    }

    private String nextQuotedValue(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement != -1) {
                if (this.buffer.getByte(indexOfElement) != 92) {
                    if (sb == null) {
                        String readUtf8 = this.buffer.readUtf8(indexOfElement);
                        this.buffer.readByte();
                        return readUtf8;
                    }
                    sb.append(this.buffer.readUtf8(indexOfElement));
                    this.buffer.readByte();
                    return sb.toString();
                }
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer.readUtf8(indexOfElement));
                this.buffer.readByte();
                sb.append(readEscapeCharacter());
            } else {
                throw syntaxError("Unterminated string");
            }
        }
    }

    private String nextUnquotedValue() throws IOException {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        return indexOfElement != -1 ? this.buffer.readUtf8(indexOfElement) : this.buffer.readUtf8();
    }

    private int peekKeyword() throws IOException {
        int i2;
        String str;
        String str2;
        byte b = this.buffer.getByte(0L);
        if (b == 116 || b == 84) {
            i2 = 5;
            str = DYConstants.DY_TRUE;
            str2 = "TRUE";
        } else if (b == 102 || b == 70) {
            i2 = 6;
            str = DYConstants.DY_FALSE;
            str2 = "FALSE";
        } else if (b != 110 && b != 78) {
            return 0;
        } else {
            i2 = 7;
            str = DYConstants.DY_NULL_STR;
            str2 = "NULL";
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.source.request(i4)) {
                return 0;
            }
            byte b2 = this.buffer.getByte(i3);
            if (b2 != str.charAt(i3) && b2 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.source.request(length + 1) && isLiteral(this.buffer.getByte(length))) {
            return 0;
        }
        this.buffer.skip(length);
        this.peeked = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:273:0x0089, code lost:
        if (isLiteral(r11) != false) goto L297;
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x008b, code lost:
        if (r6 != 2) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x008d, code lost:
        if (r7 == false) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0093, code lost:
        if (r8 != Long.MIN_VALUE) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0095, code lost:
        if (r10 == false) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x0099, code lost:
        if (r8 != 0) goto L282;
     */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x009b, code lost:
        if (r10 != false) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x009d, code lost:
        if (r10 == false) goto L284;
     */
    /* JADX WARN: Code restructure failed: missing block: B:284:0x00a0, code lost:
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x00a1, code lost:
        r16.peekedLong = r8;
        r16.buffer.skip(r5);
        r16.peeked = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:286:0x00ad, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x00ae, code lost:
        if (r6 == 2) goto L295;
     */
    /* JADX WARN: Code restructure failed: missing block: B:289:0x00b1, code lost:
        if (r6 == 4) goto L295;
     */
    /* JADX WARN: Code restructure failed: missing block: B:291:0x00b4, code lost:
        if (r6 != 7) goto L293;
     */
    /* JADX WARN: Code restructure failed: missing block: B:293:0x00b7, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:295:0x00b9, code lost:
        r16.peekedNumberLength = r5;
        r16.peeked = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x00bf, code lost:
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x00c0, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int peekNumber() throws IOException {
        char c2 = 1;
        int i2 = 0;
        long j2 = 0;
        int i3 = 0;
        char c3 = 0;
        boolean z = true;
        boolean z2 = false;
        while (true) {
            int i4 = i3 + 1;
            if (!this.source.request(i4)) {
                break;
            }
            byte b = this.buffer.getByte(i3);
            if (b != 43) {
                if (b == 69 || b == 101) {
                    if (c3 != 2 && c3 != 4) {
                        return i2;
                    }
                    c3 = 5;
                } else if (b != 45) {
                    if (b != 46) {
                        if (b < 48 || b > 57) {
                            break;
                        } else if (c3 == c2 || c3 == 0) {
                            j2 = -(b + ReplyCode.reply0xd0);
                            i2 = 0;
                            c3 = 2;
                        } else {
                            if (c3 == 2) {
                                if (j2 == 0) {
                                    return i2;
                                }
                                long j3 = (10 * j2) - (b + ReplyCode.reply0xd0);
                                z &= j2 > MIN_INCOMPLETE_INTEGER || (j2 == MIN_INCOMPLETE_INTEGER && j3 < j2);
                                j2 = j3;
                            } else if (c3 == 3) {
                                i2 = 0;
                                c3 = 4;
                            } else if (c3 == 5 || c3 == 6) {
                                i2 = 0;
                                c3 = 7;
                            }
                            i2 = 0;
                        }
                    } else if (c3 != 2) {
                        return i2;
                    } else {
                        c3 = 3;
                    }
                } else if (c3 == 0) {
                    c3 = 1;
                    z2 = true;
                } else if (c3 != 5) {
                    return i2;
                }
                i3 = i4;
                c2 = 1;
            } else if (c3 != 5) {
                return i2;
            }
            c3 = 6;
            i3 = i4;
            c2 = 1;
        }
    }

    private char readEscapeCharacter() throws IOException {
        int i2;
        int i3;
        if (this.source.request(1L)) {
            byte readByte = this.buffer.readByte();
            if (readByte == 10 || readByte == 34 || readByte == 39 || readByte == 47 || readByte == 92) {
                return (char) readByte;
            }
            if (readByte != 98) {
                if (readByte != 102) {
                    if (readByte != 110) {
                        if (readByte != 114) {
                            if (readByte != 116) {
                                if (readByte != 117) {
                                    if (this.lenient) {
                                        return (char) readByte;
                                    }
                                    throw syntaxError("Invalid escape sequence: \\" + ((char) readByte));
                                } else if (this.source.request(4L)) {
                                    char c2 = 0;
                                    for (int i4 = 0; i4 < 4; i4++) {
                                        byte b = this.buffer.getByte(i4);
                                        char c3 = (char) (c2 << 4);
                                        if (b < 48 || b > 57) {
                                            if (b >= 97 && b <= 102) {
                                                i2 = b - 97;
                                            } else if (b < 65 || b > 70) {
                                                throw syntaxError("\\u" + this.buffer.readUtf8(4L));
                                            } else {
                                                i2 = b - 65;
                                            }
                                            i3 = i2 + 10;
                                        } else {
                                            i3 = b + ReplyCode.reply0xd0;
                                        }
                                        c2 = (char) (c3 + i3);
                                    }
                                    this.buffer.skip(4L);
                                    return c2;
                                } else {
                                    throw new EOFException("Unterminated escape sequence at path " + getPath());
                                }
                            }
                            return '\t';
                        }
                        return '\r';
                    }
                    return '\n';
                }
                return '\f';
            }
            return '\b';
        }
        throw syntaxError("Unterminated escape sequence");
    }

    private void skipQuotedValue(ByteString byteString) throws IOException {
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement != -1) {
                if (this.buffer.getByte(indexOfElement) == 92) {
                    this.buffer.skip(indexOfElement + 1);
                    readEscapeCharacter();
                } else {
                    this.buffer.skip(indexOfElement + 1);
                    return;
                }
            } else {
                throw syntaxError("Unterminated string");
            }
        }
    }

    private boolean skipToEndOfBlockComment() throws IOException {
        long indexOf = this.source.indexOf(CLOSING_BLOCK_COMMENT);
        boolean z = indexOf != -1;
        Buffer buffer = this.buffer;
        buffer.skip(z ? indexOf + r1.size() : buffer.size());
        return z;
    }

    private void skipToEndOfLine() throws IOException {
        long indexOfElement = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        Buffer buffer = this.buffer;
        buffer.skip(indexOfElement != -1 ? indexOfElement + 1 : buffer.size());
    }

    private void skipUnquotedValue() throws IOException {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer = this.buffer;
        if (indexOfElement == -1) {
            indexOfElement = buffer.size();
        }
        buffer.skip(indexOfElement);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginArray() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 3) {
            pushScope(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginObject() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 1) {
            pushScope(3);
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.scopes[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void endArray() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 4) {
            int i3 = this.stackSize - 1;
            this.stackSize = i3;
            int[] iArr = this.pathIndices;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void endObject() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 2) {
            int i3 = this.stackSize - 1;
            this.stackSize = i3;
            this.pathNames[i3] = null;
            int[] iArr = this.pathIndices;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean hasNext() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        return (i2 == 2 || i2 == 4 || i2 == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean nextBoolean() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double nextDouble() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 16) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.peekedLong;
        }
        if (i2 == 17) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (i2 == 9) {
            this.peekedString = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (i2 == 8) {
            this.peekedString = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (i2 == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i2 != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double parseDouble = Double.parseDouble(this.peekedString);
            if (!this.lenient && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + getPath());
            }
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i4 = this.stackSize - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int nextInt() throws IOException {
        String nextQuotedValue;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 16) {
            long j2 = this.peekedLong;
            int i3 = (int) j2;
            if (j2 == i3) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
        }
        if (i2 == 17) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (i2 == 9 || i2 == 8) {
            if (i2 == 9) {
                nextQuotedValue = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else {
                nextQuotedValue = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
            }
            this.peekedString = nextQuotedValue;
            try {
                int parseInt = Integer.parseInt(nextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i5 = this.stackSize - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i2 != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double parseDouble = Double.parseDouble(this.peekedString);
            int i6 = (int) parseDouble;
            if (i6 == parseDouble) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr3 = this.pathIndices;
                int i7 = this.stackSize - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextName() throws IOException {
        String str;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 14) {
            str = nextUnquotedValue();
        } else if (i2 == 13) {
            str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (i2 == 12) {
            str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (i2 == 15) {
            str = this.peekedString;
        } else {
            throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = str;
        return str;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextString() throws IOException {
        String readUtf8;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 10) {
            readUtf8 = nextUnquotedValue();
        } else if (i2 == 9) {
            readUtf8 = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (i2 == 8) {
            readUtf8 = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (i2 == 11) {
            readUtf8 = this.peekedString;
            this.peekedString = null;
        } else if (i2 == 16) {
            readUtf8 = Long.toString(this.peekedLong);
        } else if (i2 == 17) {
            readUtf8 = this.buffer.readUtf8(this.peekedNumberLength);
        } else {
            throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr[i3] = iArr[i3] + 1;
        return readUtf8;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token peek() throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        switch (i2) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int selectName(JsonReader.Options options) throws IOException {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 < 12 || i2 > 15) {
            return -1;
        }
        if (i2 == 15) {
            return findName(this.peekedString, options);
        }
        int select = this.source.select(options.doubleQuoteSuffix);
        if (select != -1) {
            this.peeked = 0;
            this.pathNames[this.stackSize - 1] = options.strings[select];
            return select;
        }
        String str = this.pathNames[this.stackSize - 1];
        String nextName = nextName();
        int findName = findName(nextName, options);
        if (findName == -1) {
            this.peeked = 15;
            this.peekedString = nextName;
            this.pathNames[this.stackSize - 1] = str;
        }
        return findName;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipName() throws IOException {
        if (!this.failOnUnknown) {
            int i2 = this.peeked;
            if (i2 == 0) {
                i2 = doPeek();
            }
            if (i2 == 14) {
                skipUnquotedValue();
            } else if (i2 == 13) {
                skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else if (i2 == 12) {
                skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
            } else if (i2 != 15) {
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
            }
            this.peeked = 0;
            this.pathNames[this.stackSize - 1] = DYConstants.DY_NULL_STR;
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipValue() throws IOException {
        if (!this.failOnUnknown) {
            int i2 = 0;
            do {
                int i3 = this.peeked;
                if (i3 == 0) {
                    i3 = doPeek();
                }
                if (i3 == 3) {
                    pushScope(1);
                } else if (i3 == 1) {
                    pushScope(3);
                } else {
                    if (i3 == 4) {
                        i2--;
                        if (i2 >= 0) {
                            this.stackSize--;
                        } else {
                            throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                        }
                    } else if (i3 == 2) {
                        i2--;
                        if (i2 >= 0) {
                            this.stackSize--;
                        } else {
                            throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                        }
                    } else if (i3 == 14 || i3 == 10) {
                        skipUnquotedValue();
                    } else if (i3 == 9 || i3 == 13) {
                        skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    } else if (i3 == 8 || i3 == 12) {
                        skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    } else if (i3 == 17) {
                        this.buffer.skip(this.peekedNumberLength);
                    } else if (i3 == 18) {
                        throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                    }
                    this.peeked = 0;
                }
                i2++;
                this.peeked = 0;
            } while (i2 != 0);
            int[] iArr = this.pathIndices;
            int i4 = this.stackSize;
            int i5 = i4 - 1;
            iArr[i5] = iArr[i5] + 1;
            this.pathNames[i4 - 1] = DYConstants.DY_NULL_STR;
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    public String toString() {
        return "JsonReader(" + this.source + ")";
    }
}
