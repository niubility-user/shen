package com.jingdong.app.mall.bundle.jdbrotli;

import cn.com.union.fido.bean.uafclient.ErrorCode;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.InputStream;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes2.dex */
public class Decode {
    private static final int BLOCK_START = 2;
    private static final int CLOSED = 11;
    static final short[] CMD_LOOKUP;
    private static final int CODE_LENGTH_CODES = 18;
    private static final int CODE_LENGTH_REPEAT_CODE = 16;
    private static final int COMPRESSED_BLOCK_START = 3;
    private static final int COPY_LOOP = 8;
    private static final int COPY_UNCOMPRESSED = 6;
    private static final int DEFAULT_CODE_LENGTH = 8;
    private static final int DISTANCE_CONTEXT_BITS = 2;
    private static final int FINISHED = 10;
    private static final int HUFFMAN_TABLE_BITS = 8;
    private static final int HUFFMAN_TABLE_MASK = 255;
    private static final int HUFFMAN_TABLE_SIZE_258 = 632;
    private static final int HUFFMAN_TABLE_SIZE_26 = 396;
    private static final int INITIALIZED = 1;
    private static final int INIT_WRITE = 12;
    private static final int INSERT_LOOP = 7;
    private static final int LITERAL_CONTEXT_BITS = 6;
    private static final int MAIN_LOOP = 4;
    private static final int MAX_ALLOWED_DISTANCE = 2147483644;
    private static final int MAX_DISTANCE_BITS = 24;
    static final int MAX_LARGE_WINDOW_BITS = 30;
    private static final int MAX_LARGE_WINDOW_DISTANCE_BITS = 62;
    static final int MAX_TRANSFORMED_WORD_LENGTH = 37;
    static final int MAX_WORD_LENGTH = 24;
    static final int MIN_LARGE_WINDOW_BITS = 10;
    static final int MIN_WORD_LENGTH = 4;
    private static final int NUM_BLOCK_LENGTH_CODES = 26;
    private static final int NUM_COMMAND_CODES = 704;
    private static final int NUM_DISTANCE_SHORT_CODES = 16;
    private static final int NUM_LITERAL_CODES = 256;
    private static final int READ_METADATA = 5;
    private static final int TRANSFORM = 9;
    private static final int UNINITIALIZED = 0;
    private static final int WRITE = 13;
    static final int[] MAX_HUFFMAN_TABLE_SIZE = {256, 402, R2.attr.banner_slide_direction, R2.attr.borderWidth, 500, R2.attr.can_rota, R2.attr.checked, R2.attr.circleInterval, 630, R2.attr.colorSecondaryVariant, R2.attr.contentPaddingBottom, R2.attr.currentState, R2.attr.deltaPolarRadius, 790, R2.attr.enforceTextAppearance, R2.attr.exTabTextAppearance, R2.attr.fastScrollVerticalThumbDrawable, R2.attr.flow_firstHorizontalBias, R2.attr.forceApplySystemWindowInsetTop, R2.attr.height, 1016, R2.attr.inRoundWidth, R2.attr.internalMinHeight};
    private static final int[] CODE_LENGTH_CODE_ORDER = {1, 2, 3, 4, 0, 5, 17, 6, 16, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final int[] DISTANCE_SHORT_CODE_INDEX_OFFSET = {0, 3, 2, 1, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3};
    private static final int[] DISTANCE_SHORT_CODE_VALUE_OFFSET = {0, 0, 0, 0, -1, 1, -2, 2, -3, 3, -1, 1, -2, 2, -3, 3};
    private static final int[] FIXED_TABLE = {131072, 131076, IMediaPlayer.OnNativeInvokeListener.CTRL_WILL_HTTP_OPEN, 196610, 131072, 131076, IMediaPlayer.OnNativeInvokeListener.CTRL_WILL_HTTP_OPEN, 262145, 131072, 131076, IMediaPlayer.OnNativeInvokeListener.CTRL_WILL_HTTP_OPEN, 196610, 131072, 131076, IMediaPlayer.OnNativeInvokeListener.CTRL_WILL_HTTP_OPEN, 262149};
    static final int[] DICTIONARY_OFFSETS_BY_LENGTH = {0, 0, 0, 0, 0, 4096, R2.drawable.jd_top_popup_bg, R2.style.TextAppearance_AppCompat_Notification_Info_Media, 35840, 44032, 53248, 63488, 74752, 87040, 93696, 100864, 104704, 106752, 108928, 113536, 115968, 118528, 119872, 121280, 122016};
    static final int[] DICTIONARY_SIZE_BITS_BY_LENGTH = {0, 0, 0, 0, 10, 10, 11, 11, 10, 10, 10, 10, 10, 9, 9, 8, 7, 7, 8, 7, 7, 6, 6, 5, 5};
    static final int[] BLOCK_LENGTH_OFFSET = {1, 5, 9, 13, 17, 25, 33, 41, 49, 65, 81, 97, 113, R2.anim.message_center_dialog_out, 177, 209, 241, 305, R2.attr.alignItems, R2.attr.btn_title, R2.attr.defaultState, R2.attr.layout_order, R2.attr.yg2_borderHorizontal, R2.color.libpaipaireplacemodel_paipai_daojiao_text, R2.drawable.common_forward_normal, R2.id.tradein_estimate_old_device_tag};
    static final int[] BLOCK_LENGTH_N_BITS = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13, 24};
    static final short[] INSERT_LENGTH_N_BITS = {0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 12, 14, 24};
    static final short[] COPY_LENGTH_N_BITS = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8, 9, 10, 24};

    static {
        short[] sArr = new short[R2.color.C_Wathet_02_dark];
        CMD_LOOKUP = sArr;
        unpackCommandLookupTable(sArr);
    }

    private static int calculateDistanceAlphabetLimit(int i2, int i3, int i4) {
        if (i2 >= (2 << i3) + i4) {
            int i5 = ((i2 - i4) >> i3) + 4;
            int log2floor = log2floor(i5) - 1;
            return (((((i5 >> log2floor) & 1) | ((log2floor - 1) << 1)) - 1) << i3) + (1 << i3) + i4 + 16;
        }
        throw new IllegalArgumentException("maxDistance is too small");
    }

    private static int calculateDistanceAlphabetSize(int i2, int i3, int i4) {
        return i3 + 16 + ((i4 << i2) * 2);
    }

    private static void calculateDistanceLut(State state, int i2) {
        byte[] bArr = state.distExtraBits;
        int[] iArr = state.distOffset;
        int i3 = state.distancePostfixBits;
        int i4 = state.numDirectDistanceCodes;
        int i5 = 1 << i3;
        int i6 = 16;
        int i7 = 0;
        while (i7 < i4) {
            bArr[i6] = 0;
            i7++;
            iArr[i6] = i7;
            i6++;
        }
        int i8 = 0;
        int i9 = 1;
        while (i6 < i2) {
            int i10 = ((((i8 + 2) << i9) - 4) << i3) + i4 + 1;
            for (int i11 = 0; i11 < i5; i11++) {
                bArr[i6] = (byte) i9;
                iArr[i6] = i10 + i11;
                i6++;
            }
            i9 += i8;
            i8 ^= 1;
        }
    }

    private static int calculateFence(State state) {
        int i2 = state.ringBufferSize;
        return state.isEager != 0 ? Math.min(i2, (state.ringBufferBytesWritten + state.outputLength) - state.outputUsed) : i2;
    }

    private static void checkDupes(int[] iArr, int i2) {
        int i3 = 0;
        while (i3 < i2 - 1) {
            int i4 = i3 + 1;
            for (int i5 = i4; i5 < i2; i5++) {
                if (iArr[i3] == iArr[i5]) {
                    throw new BrotliRuntimeException("Duplicate simple Huffman code symbol");
                }
            }
            i3 = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void close(State state) throws IOException {
        int i2 = state.runningState;
        if (i2 == 0) {
            throw new IllegalStateException("State MUST be initialized");
        }
        if (i2 == 11) {
            return;
        }
        state.runningState = 11;
        InputStream inputStream = state.input;
        if (inputStream != null) {
            Utils.closeInput(inputStream);
            state.input = null;
        }
    }

    private static void copyUncompressedData(State state) {
        byte[] bArr = state.ringBuffer;
        int i2 = state.metaBlockLength;
        if (i2 <= 0) {
            BitReader.reload(state);
            state.runningState = 2;
            return;
        }
        int min = Math.min(state.ringBufferSize - state.pos, i2);
        BitReader.copyBytes(state, bArr, state.pos, min);
        state.metaBlockLength -= min;
        int i3 = state.pos + min;
        state.pos = i3;
        if (i3 == state.ringBufferSize) {
            state.nextRunningState = 6;
            state.runningState = 12;
            return;
        }
        BitReader.reload(state);
        state.runningState = 2;
    }

    private static int decodeBlockTypeAndLength(State state, int i2, int i3) {
        int i4;
        int[] iArr = state.rings;
        int i5 = i2 * 2;
        int i6 = i5 + 4;
        BitReader.fillBitWindow(state);
        int readSymbol = readSymbol(state.blockTrees, i5, state);
        int readBlockLength = readBlockLength(state.blockTrees, i5 + 1, state);
        if (readSymbol == 1) {
            i4 = iArr[i6 + 1] + 1;
        } else {
            i4 = readSymbol == 0 ? iArr[i6] : readSymbol - 2;
        }
        if (i4 >= i3) {
            i4 -= i3;
        }
        int i7 = i6 + 1;
        iArr[i6] = iArr[i7];
        iArr[i7] = i4;
        return readBlockLength;
    }

    private static void decodeCommandBlockSwitch(State state) {
        state.commandBlockLength = decodeBlockTypeAndLength(state, 1, state.numCommandBlockTypes);
        state.commandTreeIdx = state.rings[7];
    }

    private static int decodeContextMap(int i2, byte[] bArr, State state) {
        BitReader.readMoreInput(state);
        int decodeVarLenUnsignedByte = decodeVarLenUnsignedByte(state) + 1;
        if (decodeVarLenUnsignedByte == 1) {
            Utils.fillBytesWithZeroes(bArr, 0, i2);
            return decodeVarLenUnsignedByte;
        }
        BitReader.fillBitWindow(state);
        int readFewBits = BitReader.readFewBits(state, 1) != 0 ? BitReader.readFewBits(state, 4) + 1 : 0;
        int i3 = decodeVarLenUnsignedByte + readFewBits;
        int i4 = MAX_HUFFMAN_TABLE_SIZE[(i3 + 31) >> 5] + 1;
        int[] iArr = new int[i4];
        int i5 = i4 - 1;
        readHuffmanCode(i3, i3, iArr, i5, state);
        int i6 = 0;
        while (i6 < i2) {
            BitReader.readMoreInput(state);
            BitReader.fillBitWindow(state);
            int readSymbol = readSymbol(iArr, i5, state);
            if (readSymbol == 0) {
                bArr[i6] = 0;
            } else if (readSymbol <= readFewBits) {
                BitReader.fillBitWindow(state);
                for (int readFewBits2 = (1 << readSymbol) + BitReader.readFewBits(state, readSymbol); readFewBits2 != 0; readFewBits2--) {
                    if (i6 < i2) {
                        bArr[i6] = 0;
                        i6++;
                    } else {
                        throw new BrotliRuntimeException("Corrupted context map");
                    }
                }
            } else {
                bArr[i6] = (byte) (readSymbol - readFewBits);
            }
            i6++;
        }
        BitReader.fillBitWindow(state);
        if (BitReader.readFewBits(state, 1) == 1) {
            inverseMoveToFrontTransform(bArr, i2);
        }
        return decodeVarLenUnsignedByte;
    }

    private static void decodeDistanceBlockSwitch(State state) {
        state.distanceBlockLength = decodeBlockTypeAndLength(state, 2, state.numDistanceBlockTypes);
        state.distContextMapSlice = state.rings[9] << 2;
    }

    private static int[] decodeHuffmanTreeGroup(int i2, int i3, int i4, State state) {
        int[] iArr = new int[(MAX_HUFFMAN_TABLE_SIZE[(i3 + 31) >> 5] * i4) + i4];
        int i5 = i4;
        for (int i6 = 0; i6 < i4; i6++) {
            iArr[i6] = i5;
            i5 += readHuffmanCode(i2, i3, iArr, i6, state);
        }
        return iArr;
    }

    private static void decodeLiteralBlockSwitch(State state) {
        state.literalBlockLength = decodeBlockTypeAndLength(state, 0, state.numLiteralBlockTypes);
        int i2 = state.rings[5];
        int i3 = i2 << 6;
        state.contextMapSlice = i3;
        state.literalTreeIdx = state.contextMap[i3] & 255;
        int i4 = state.contextModes[i2] << 9;
        state.contextLookupOffset1 = i4;
        state.contextLookupOffset2 = i4 + 256;
    }

    private static void decodeMetaBlockLength(State state) {
        BitReader.fillBitWindow(state);
        int readFewBits = BitReader.readFewBits(state, 1);
        state.inputEnd = readFewBits;
        int i2 = 0;
        state.metaBlockLength = 0;
        state.isUncompressed = 0;
        state.isMetadata = 0;
        if (readFewBits == 0 || BitReader.readFewBits(state, 1) == 0) {
            int readFewBits2 = BitReader.readFewBits(state, 2) + 4;
            if (readFewBits2 == 7) {
                state.isMetadata = 1;
                if (BitReader.readFewBits(state, 1) == 0) {
                    int readFewBits3 = BitReader.readFewBits(state, 2);
                    if (readFewBits3 == 0) {
                        return;
                    }
                    while (i2 < readFewBits3) {
                        BitReader.fillBitWindow(state);
                        int readFewBits4 = BitReader.readFewBits(state, 8);
                        if (readFewBits4 == 0 && i2 + 1 == readFewBits3 && readFewBits3 > 1) {
                            throw new BrotliRuntimeException("Exuberant nibble");
                        }
                        state.metaBlockLength = (readFewBits4 << (i2 * 8)) | state.metaBlockLength;
                        i2++;
                    }
                } else {
                    throw new BrotliRuntimeException("Corrupted reserved bit");
                }
            } else {
                while (i2 < readFewBits2) {
                    BitReader.fillBitWindow(state);
                    int readFewBits5 = BitReader.readFewBits(state, 4);
                    if (readFewBits5 == 0 && i2 + 1 == readFewBits2 && readFewBits2 > 4) {
                        throw new BrotliRuntimeException("Exuberant nibble");
                    }
                    state.metaBlockLength = (readFewBits5 << (i2 * 4)) | state.metaBlockLength;
                    i2++;
                }
            }
            state.metaBlockLength++;
            if (state.inputEnd == 0) {
                state.isUncompressed = BitReader.readFewBits(state, 1);
            }
        }
    }

    private static int decodeVarLenUnsignedByte(State state) {
        BitReader.fillBitWindow(state);
        if (BitReader.readFewBits(state, 1) != 0) {
            int readFewBits = BitReader.readFewBits(state, 3);
            if (readFewBits == 0) {
                return 1;
            }
            return BitReader.readFewBits(state, readFewBits) + (1 << readFewBits);
        }
        return 0;
    }

    private static int decodeWindowBits(State state) {
        int readFewBits;
        int i2 = state.isLargeWindow;
        state.isLargeWindow = 0;
        BitReader.fillBitWindow(state);
        if (BitReader.readFewBits(state, 1) == 0) {
            return 16;
        }
        int readFewBits2 = BitReader.readFewBits(state, 3);
        if (readFewBits2 != 0) {
            return readFewBits2 + 17;
        }
        int readFewBits3 = BitReader.readFewBits(state, 3);
        if (readFewBits3 != 0) {
            if (readFewBits3 == 1) {
                if (i2 == 0) {
                    return -1;
                }
                state.isLargeWindow = 1;
                if (BitReader.readFewBits(state, 1) != 1 && (readFewBits = BitReader.readFewBits(state, 6)) >= 10 && readFewBits <= 30) {
                    return readFewBits;
                }
                return -1;
            }
            return readFewBits3 + 8;
        }
        return 17;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00f0, code lost:
        throw new com.jingdong.app.mall.bundle.jdbrotli.BrotliRuntimeException("Invalid backward reference");
     */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x033b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x033b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0202 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0333 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0208  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void decompress(State state) {
        int i2;
        int i3;
        int i4;
        int i5;
        int readBits;
        int i6;
        int i7 = state.runningState;
        if (i7 == 0) {
            throw new IllegalStateException("Can't decompress until initialized");
        }
        if (i7 != 11) {
            int i8 = 2;
            if (i7 == 1) {
                int decodeWindowBits = decodeWindowBits(state);
                if (decodeWindowBits != -1) {
                    int i9 = 1 << decodeWindowBits;
                    state.maxRingBufferSize = i9;
                    state.maxBackwardDistance = i9 - 16;
                    state.runningState = 2;
                } else {
                    throw new BrotliRuntimeException("Invalid 'windowBits' code");
                }
            }
            int calculateFence = calculateFence(state);
            int i10 = state.ringBufferSize - 1;
            byte[] bArr = state.ringBuffer;
            while (true) {
                int i11 = state.runningState;
                if (i11 == 10) {
                    if (i11 == 10) {
                        if (state.metaBlockLength >= 0) {
                            BitReader.jumpToByteBoundary(state);
                            BitReader.checkHealth(state, 1);
                            return;
                        }
                        throw new BrotliRuntimeException("Invalid metablock length");
                    }
                    return;
                }
                switch (i11) {
                    case 2:
                        if (state.metaBlockLength >= 0) {
                            readNextMetablockHeader(state);
                            calculateFence = calculateFence(state);
                            i10 = state.ringBufferSize - 1;
                            bArr = state.ringBuffer;
                            break;
                        } else {
                            throw new BrotliRuntimeException("Invalid metablock length");
                        }
                    case 3:
                        readMetablockHuffmanCodesAndContextMaps(state);
                        state.runningState = 4;
                        if (state.metaBlockLength > 0) {
                            state.runningState = i8;
                            break;
                        } else {
                            BitReader.readMoreInput(state);
                            if (state.commandBlockLength == 0) {
                                decodeCommandBlockSwitch(state);
                            }
                            state.commandBlockLength--;
                            BitReader.fillBitWindow(state);
                            int readSymbol = readSymbol(state.commandTreeGroup, state.commandTreeIdx, state) << i8;
                            short[] sArr = CMD_LOOKUP;
                            short s = sArr[readSymbol];
                            short s2 = sArr[readSymbol + 1];
                            short s3 = sArr[readSymbol + 2];
                            state.distanceCode = sArr[readSymbol + 3];
                            BitReader.fillBitWindow(state);
                            state.insertLength = s2 + BitReader.readBits(state, s & ErrorCode.UNKNOWN);
                            BitReader.fillBitWindow(state);
                            state.copyLength = s3 + BitReader.readBits(state, s >> 8);
                            state.f8128j = 0;
                            state.runningState = 7;
                            if (state.trivialLiteralContext == 0) {
                                int i12 = state.pos;
                                int i13 = bArr[(i12 - 1) & i10] & 255;
                                int i14 = bArr[(i12 - i8) & i10] & 255;
                                while (true) {
                                    if (state.f8128j < state.insertLength) {
                                        BitReader.readMoreInput(state);
                                        if (state.literalBlockLength == 0) {
                                            decodeLiteralBlockSwitch(state);
                                        }
                                        int[] iArr = Context.LOOKUP;
                                        int i15 = iArr[state.contextLookupOffset1 + i13];
                                        byte[] bArr2 = state.contextMap;
                                        int i16 = state.contextMapSlice;
                                        state.literalBlockLength--;
                                        BitReader.fillBitWindow(state);
                                        int readSymbol2 = readSymbol(state.literalTreeGroup, bArr2[i16 + (iArr[state.contextLookupOffset2 + i14] | i15)] & 255, state);
                                        int i17 = state.pos;
                                        bArr[i17] = (byte) readSymbol2;
                                        int i18 = i17 + 1;
                                        state.pos = i18;
                                        state.f8128j++;
                                        if (i18 >= calculateFence) {
                                            state.nextRunningState = 7;
                                            state.runningState = 12;
                                        } else {
                                            i14 = i13;
                                            i13 = readSymbol2;
                                        }
                                    }
                                }
                                if (state.runningState != 7) {
                                    continue;
                                } else {
                                    int i19 = state.metaBlockLength - state.insertLength;
                                    state.metaBlockLength = i19;
                                    if (i19 <= 0) {
                                        state.runningState = 4;
                                        break;
                                    } else {
                                        int i20 = state.distanceCode;
                                        if (i20 < 0) {
                                            state.distance = state.rings[state.distRbIdx];
                                        } else {
                                            BitReader.readMoreInput(state);
                                            if (state.distanceBlockLength == 0) {
                                                decodeDistanceBlockSwitch(state);
                                            }
                                            state.distanceBlockLength--;
                                            BitReader.fillBitWindow(state);
                                            i20 = readSymbol(state.distanceTreeGroup, state.distContextMap[state.distContextMapSlice + i20] & 255, state);
                                            if (i20 < 16) {
                                                int i21 = state.rings[(state.distRbIdx + DISTANCE_SHORT_CODE_INDEX_OFFSET[i20]) & 3] + DISTANCE_SHORT_CODE_VALUE_OFFSET[i20];
                                                state.distance = i21;
                                                if (i21 < 0) {
                                                    throw new BrotliRuntimeException("Negative distance");
                                                }
                                            } else {
                                                byte b = state.distExtraBits[i20];
                                                if (state.bitOffset + b <= BitReader.BITNESS) {
                                                    readBits = BitReader.readFewBits(state, b);
                                                } else {
                                                    BitReader.fillBitWindow(state);
                                                    readBits = BitReader.readBits(state, b);
                                                }
                                                state.distance = state.distOffset[i20] + (readBits << state.distancePostfixBits);
                                            }
                                        }
                                        int i22 = state.maxDistance;
                                        int i23 = state.maxBackwardDistance;
                                        if (i22 != i23 && (i6 = state.pos) < i23) {
                                            state.maxDistance = i6;
                                        } else {
                                            state.maxDistance = i23;
                                        }
                                        int i24 = state.distance;
                                        if (i24 > state.maxDistance) {
                                            state.runningState = 9;
                                            break;
                                        } else {
                                            if (i20 > 0) {
                                                int i25 = (state.distRbIdx + 1) & 3;
                                                state.distRbIdx = i25;
                                                state.rings[i25] = i24;
                                            }
                                            if (state.copyLength <= state.metaBlockLength) {
                                                state.f8128j = 0;
                                                state.runningState = 8;
                                                int i26 = state.pos;
                                                int i27 = (i26 - state.distance) & i10;
                                                int i28 = state.copyLength - state.f8128j;
                                                i2 = i27 + i28;
                                                int i29 = i26 + i28;
                                                if (i2 < i10 || i29 >= i10) {
                                                    do {
                                                        i3 = state.f8128j;
                                                        if (i3 >= state.copyLength) {
                                                            int i30 = state.pos;
                                                            bArr[i30] = bArr[(i30 - state.distance) & i10];
                                                            state.metaBlockLength--;
                                                            i4 = i30 + 1;
                                                            state.pos = i4;
                                                            state.f8128j = i3 + 1;
                                                        }
                                                    } while (i4 < calculateFence);
                                                    i5 = 8;
                                                    state.nextRunningState = 8;
                                                    state.runningState = 12;
                                                    if (state.runningState == i5) {
                                                        state.runningState = 4;
                                                        break;
                                                    } else {
                                                        break;
                                                    }
                                                } else {
                                                    if (i28 < 12 || (i2 > i26 && i29 > i27)) {
                                                        for (int i31 = 0; i31 < i28; i31 += 4) {
                                                            int i32 = i26 + 1;
                                                            int i33 = i27 + 1;
                                                            bArr[i26] = bArr[i27];
                                                            int i34 = i32 + 1;
                                                            int i35 = i33 + 1;
                                                            bArr[i32] = bArr[i33];
                                                            int i36 = i34 + 1;
                                                            int i37 = i35 + 1;
                                                            bArr[i34] = bArr[i35];
                                                            i26 = i36 + 1;
                                                            i27 = i37 + 1;
                                                            bArr[i36] = bArr[i37];
                                                        }
                                                    } else {
                                                        Utils.copyBytesWithin(bArr, i26, i27, i2);
                                                    }
                                                    state.f8128j += i28;
                                                    state.metaBlockLength -= i28;
                                                    state.pos += i28;
                                                }
                                                i5 = 8;
                                                if (state.runningState == i5) {
                                                }
                                            } else {
                                                throw new BrotliRuntimeException("Invalid backward reference");
                                            }
                                        }
                                    }
                                }
                            }
                            while (true) {
                                if (state.f8128j >= state.insertLength) {
                                    BitReader.readMoreInput(state);
                                    if (state.literalBlockLength == 0) {
                                        decodeLiteralBlockSwitch(state);
                                    }
                                    state.literalBlockLength--;
                                    BitReader.fillBitWindow(state);
                                    bArr[state.pos] = (byte) readSymbol(state.literalTreeGroup, state.literalTreeIdx, state);
                                    int i38 = state.pos + 1;
                                    state.pos = i38;
                                    state.f8128j++;
                                    if (i38 >= calculateFence) {
                                        state.nextRunningState = 7;
                                        state.runningState = 12;
                                    }
                                }
                            }
                            if (state.runningState != 7) {
                            }
                        }
                        break;
                    case 4:
                        if (state.metaBlockLength > 0) {
                        }
                        break;
                    case 5:
                        while (state.metaBlockLength > 0) {
                            BitReader.readMoreInput(state);
                            BitReader.fillBitWindow(state);
                            BitReader.readFewBits(state, 8);
                            state.metaBlockLength--;
                        }
                        state.runningState = i8;
                        break;
                    case 6:
                        copyUncompressedData(state);
                        break;
                    case 7:
                        if (state.trivialLiteralContext == 0) {
                        }
                        while (true) {
                            if (state.f8128j >= state.insertLength) {
                            }
                        }
                        if (state.runningState != 7) {
                        }
                        break;
                    case 8:
                        int i262 = state.pos;
                        int i272 = (i262 - state.distance) & i10;
                        int i282 = state.copyLength - state.f8128j;
                        i2 = i272 + i282;
                        int i292 = i262 + i282;
                        if (i2 < i10) {
                        }
                        do {
                            i3 = state.f8128j;
                            if (i3 >= state.copyLength) {
                            }
                        } while (i4 < calculateFence);
                        i5 = 8;
                        state.nextRunningState = 8;
                        state.runningState = 12;
                        if (state.runningState == i5) {
                        }
                        break;
                    case 9:
                        int i39 = state.distance;
                        if (i39 <= MAX_ALLOWED_DISTANCE) {
                            int i40 = state.copyLength;
                            if (i40 >= 4 && i40 <= 24) {
                                int i41 = DICTIONARY_OFFSETS_BY_LENGTH[i40];
                                int i42 = (i39 - state.maxDistance) - 1;
                                int i43 = DICTIONARY_SIZE_BITS_BY_LENGTH[i40];
                                int i44 = i42 >>> i43;
                                int i45 = i41 + ((((1 << i43) - 1) & i42) * i40);
                                if (i44 < 121) {
                                    int transformDictionaryWord = Transform.transformDictionaryWord(bArr, state.pos, Dictionary.getData(), i45, state.copyLength, Transform.RFC_TRANSFORMS, i44);
                                    int i46 = state.pos + transformDictionaryWord;
                                    state.pos = i46;
                                    state.metaBlockLength -= transformDictionaryWord;
                                    if (i46 >= calculateFence) {
                                        state.nextRunningState = 4;
                                        state.runningState = 12;
                                        break;
                                    } else {
                                        state.runningState = 4;
                                        break;
                                    }
                                } else {
                                    throw new BrotliRuntimeException("Invalid backward reference");
                                }
                            }
                        } else {
                            throw new BrotliRuntimeException("Invalid backward reference");
                        }
                        break;
                    case 10:
                    case 11:
                    default:
                        throw new BrotliRuntimeException("Unexpected state " + state.runningState);
                    case 12:
                        state.ringBufferBytesReady = Math.min(state.pos, state.ringBufferSize);
                        state.runningState = 13;
                        if (writeRingBuffer(state) == 0) {
                            int i47 = state.pos;
                            int i48 = state.maxBackwardDistance;
                            if (i47 >= i48) {
                                state.maxDistance = i48;
                            }
                            int i49 = state.ringBufferSize;
                            if (i47 >= i49) {
                                if (i47 > i49) {
                                    Utils.copyBytesWithin(bArr, 0, i49, i47);
                                }
                                state.pos &= i10;
                                state.ringBufferBytesWritten = 0;
                            }
                            state.runningState = state.nextRunningState;
                            break;
                        } else {
                            return;
                        }
                    case 13:
                        if (writeRingBuffer(state) == 0) {
                        }
                        break;
                }
                i8 = 2;
            }
        } else {
            throw new IllegalStateException("Can't decompress after close");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableEagerOutput(State state) {
        if (state.runningState == 1) {
            state.isEager = 1;
            return;
        }
        throw new IllegalStateException("State MUST be freshly initialized");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableLargeWindow(State state) {
        if (state.runningState == 1) {
            state.isLargeWindow = 1;
            return;
        }
        throw new IllegalStateException("State MUST be freshly initialized");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initState(State state, InputStream inputStream) {
        if (state.runningState == 0) {
            int[] iArr = new int[R2.color.btn_grey_outline_color_pressed];
            state.blockTrees = iArr;
            iArr[0] = 7;
            state.distRbIdx = 3;
            int calculateDistanceAlphabetLimit = calculateDistanceAlphabetLimit(MAX_ALLOWED_DISTANCE, 3, 120);
            state.distExtraBits = new byte[calculateDistanceAlphabetLimit];
            state.distOffset = new int[calculateDistanceAlphabetLimit];
            state.input = inputStream;
            BitReader.initBitReader(state);
            state.runningState = 1;
            return;
        }
        throw new IllegalStateException("State MUST be uninitialized");
    }

    private static void inverseMoveToFrontTransform(byte[] bArr, int i2) {
        int[] iArr = new int[256];
        for (int i3 = 0; i3 < 256; i3++) {
            iArr[i3] = i3;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = bArr[i4] & 255;
            bArr[i4] = (byte) iArr[i5];
            if (i5 != 0) {
                moveToFront(iArr, i5);
            }
        }
    }

    private static int log2floor(int i2) {
        int i3 = -1;
        for (int i4 = 16; i4 > 0; i4 >>= 1) {
            int i5 = i2 >>> i4;
            if (i5 != 0) {
                i3 += i4;
                i2 = i5;
            }
        }
        return i3 + i2;
    }

    private static void maybeReallocateRingBuffer(State state) {
        int i2 = state.maxRingBufferSize;
        int i3 = state.expectedTotalSize;
        if (i2 > i3) {
            while (true) {
                int i4 = i2 >> 1;
                if (i4 <= i3) {
                    break;
                }
                i2 = i4;
            }
            if (state.inputEnd == 0 && i2 < 16384 && state.maxRingBufferSize >= 16384) {
                i2 = 16384;
            }
        }
        int i5 = state.ringBufferSize;
        if (i2 <= i5) {
            return;
        }
        byte[] bArr = new byte[i2 + 37];
        byte[] bArr2 = state.ringBuffer;
        if (bArr2.length != 0) {
            System.arraycopy(bArr2, 0, bArr, 0, i5);
        }
        state.ringBuffer = bArr;
        state.ringBufferSize = i2;
    }

    private static void moveToFront(int[] iArr, int i2) {
        int i3 = iArr[i2];
        while (i2 > 0) {
            iArr[i2] = iArr[i2 - 1];
            i2--;
        }
        iArr[0] = i3;
    }

    private static int readBlockLength(int[] iArr, int i2, State state) {
        BitReader.fillBitWindow(state);
        int readSymbol = readSymbol(iArr, i2, state);
        int i3 = BLOCK_LENGTH_N_BITS[readSymbol];
        BitReader.fillBitWindow(state);
        return BLOCK_LENGTH_OFFSET[readSymbol] + BitReader.readBits(state, i3);
    }

    private static int readComplexHuffmanCode(int i2, int i3, int[] iArr, int i4, State state) {
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[18];
        int i5 = 0;
        int i6 = 32;
        while (i3 < 18 && i6 > 0) {
            int i7 = CODE_LENGTH_CODE_ORDER[i3];
            BitReader.fillBitWindow(state);
            int peekBits = BitReader.peekBits(state) & 15;
            int i8 = state.bitOffset;
            int[] iArr4 = FIXED_TABLE;
            state.bitOffset = i8 + (iArr4[peekBits] >> 16);
            int i9 = iArr4[peekBits] & 65535;
            iArr3[i7] = i9;
            if (i9 != 0) {
                i6 -= 32 >> i9;
                i5++;
            }
            i3++;
        }
        if (i6 != 0 && i5 != 1) {
            throw new BrotliRuntimeException("Corrupted Huffman code histogram");
        }
        readHuffmanCodeLengths(iArr3, i2, iArr2, state);
        return Huffman.buildHuffmanTable(iArr, i4, 8, iArr2, i2);
    }

    private static int readHuffmanCode(int i2, int i3, int[] iArr, int i4, State state) {
        BitReader.readMoreInput(state);
        BitReader.fillBitWindow(state);
        int readFewBits = BitReader.readFewBits(state, 2);
        if (readFewBits == 1) {
            return readSimpleHuffmanCode(i2, i3, iArr, i4, state);
        }
        return readComplexHuffmanCode(i3, readFewBits, iArr, i4, state);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0085, code lost:
        if (r4 != 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0087, code lost:
        com.jingdong.app.mall.bundle.jdbrotli.Utils.fillIntsWithZeroes(r13, r3, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0093, code lost:
        throw new com.jingdong.app.mall.bundle.jdbrotli.BrotliRuntimeException("Unused space");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void readHuffmanCodeLengths(int[] iArr, int i2, int[] iArr2, State state) {
        int[] iArr3 = new int[33];
        Huffman.buildHuffmanTable(iArr3, 32, 5, iArr, 18);
        int i3 = 8;
        int i4 = 0;
        int i5 = 32768;
        int i6 = 0;
        loop0: while (true) {
            int i7 = 0;
            while (i4 < i2 && i5 > 0) {
                BitReader.readMoreInput(state);
                BitReader.fillBitWindow(state);
                int peekBits = BitReader.peekBits(state) & 31;
                state.bitOffset += iArr3[peekBits] >> 16;
                int i8 = iArr3[peekBits] & 65535;
                if (i8 < 16) {
                    int i9 = i4 + 1;
                    iArr2[i4] = i8;
                    if (i8 != 0) {
                        i5 -= 32768 >> i8;
                        i4 = i9;
                        i3 = i8;
                    } else {
                        i4 = i9;
                    }
                } else {
                    int i10 = i8 - 14;
                    int i11 = i8 == 16 ? i3 : 0;
                    if (i6 != i11) {
                        i6 = i11;
                        i7 = 0;
                    }
                    int i12 = i7 > 0 ? (i7 - 2) << i10 : i7;
                    BitReader.fillBitWindow(state);
                    int readFewBits = i12 + BitReader.readFewBits(state, i10) + 3;
                    int i13 = readFewBits - i7;
                    if (i4 + i13 > i2) {
                        throw new BrotliRuntimeException("symbol + repeatDelta > numSymbols");
                    }
                    int i14 = 0;
                    while (i14 < i13) {
                        iArr2[i4] = i6;
                        i14++;
                        i4++;
                    }
                    if (i6 != 0) {
                        i5 -= i13 << (15 - i6);
                    }
                    i7 = readFewBits;
                }
            }
        }
    }

    private static void readMetablockHuffmanCodesAndContextMaps(State state) {
        int i2;
        int i3;
        int decodeVarLenUnsignedByte = decodeVarLenUnsignedByte(state) + 1;
        state.numLiteralBlockTypes = decodeVarLenUnsignedByte;
        state.literalBlockLength = readMetablockPartition(state, 0, decodeVarLenUnsignedByte);
        int decodeVarLenUnsignedByte2 = decodeVarLenUnsignedByte(state) + 1;
        state.numCommandBlockTypes = decodeVarLenUnsignedByte2;
        state.commandBlockLength = readMetablockPartition(state, 1, decodeVarLenUnsignedByte2);
        int decodeVarLenUnsignedByte3 = decodeVarLenUnsignedByte(state) + 1;
        state.numDistanceBlockTypes = decodeVarLenUnsignedByte3;
        state.distanceBlockLength = readMetablockPartition(state, 2, decodeVarLenUnsignedByte3);
        BitReader.readMoreInput(state);
        BitReader.fillBitWindow(state);
        state.distancePostfixBits = BitReader.readFewBits(state, 2);
        int readFewBits = BitReader.readFewBits(state, 4);
        int i4 = state.distancePostfixBits;
        state.numDirectDistanceCodes = readFewBits << i4;
        state.distancePostfixMask = (1 << i4) - 1;
        state.contextModes = new byte[state.numLiteralBlockTypes];
        int i5 = 0;
        while (true) {
            i2 = state.numLiteralBlockTypes;
            if (i5 >= i2) {
                break;
            }
            int min = Math.min(i5 + 96, i2);
            while (i5 < min) {
                BitReader.fillBitWindow(state);
                state.contextModes[i5] = (byte) BitReader.readFewBits(state, 2);
                i5++;
            }
            BitReader.readMoreInput(state);
        }
        byte[] bArr = new byte[i2 << 6];
        state.contextMap = bArr;
        int decodeContextMap = decodeContextMap(i2 << 6, bArr, state);
        state.trivialLiteralContext = 1;
        int i6 = 0;
        while (true) {
            if (i6 >= (state.numLiteralBlockTypes << 6)) {
                break;
            } else if (state.contextMap[i6] != (i6 >> 6)) {
                state.trivialLiteralContext = 0;
                break;
            } else {
                i6++;
            }
        }
        int i7 = state.numDistanceBlockTypes;
        byte[] bArr2 = new byte[i7 << 2];
        state.distContextMap = bArr2;
        int decodeContextMap2 = decodeContextMap(i7 << 2, bArr2, state);
        state.literalTreeGroup = decodeHuffmanTreeGroup(256, 256, decodeContextMap, state);
        state.commandTreeGroup = decodeHuffmanTreeGroup(704, 704, state.numCommandBlockTypes, state);
        int calculateDistanceAlphabetSize = calculateDistanceAlphabetSize(state.distancePostfixBits, state.numDirectDistanceCodes, 24);
        if (state.isLargeWindow == 1) {
            calculateDistanceAlphabetSize = calculateDistanceAlphabetSize(state.distancePostfixBits, state.numDirectDistanceCodes, 62);
            i3 = calculateDistanceAlphabetLimit(MAX_ALLOWED_DISTANCE, state.distancePostfixBits, state.numDirectDistanceCodes);
        } else {
            i3 = calculateDistanceAlphabetSize;
        }
        state.distanceTreeGroup = decodeHuffmanTreeGroup(calculateDistanceAlphabetSize, i3, decodeContextMap2, state);
        calculateDistanceLut(state, i3);
        state.contextMapSlice = 0;
        state.distContextMapSlice = 0;
        int i8 = state.contextModes[0] * 512;
        state.contextLookupOffset1 = i8;
        state.contextLookupOffset2 = i8 + 256;
        state.literalTreeIdx = 0;
        state.commandTreeIdx = 0;
        int[] iArr = state.rings;
        iArr[4] = 1;
        iArr[5] = 0;
        iArr[6] = 1;
        iArr[7] = 0;
        iArr[8] = 1;
        iArr[9] = 0;
    }

    private static int readMetablockPartition(State state, int i2, int i3) {
        int[] iArr = state.blockTrees;
        int i4 = i2 * 2;
        int i5 = iArr[i4];
        if (i3 <= 1) {
            iArr[i4 + 1] = i5;
            iArr[i4 + 2] = i5;
            return 268435456;
        }
        int i6 = i3 + 2;
        int readHuffmanCode = i5 + readHuffmanCode(i6, i6, iArr, i4, state);
        int[] iArr2 = state.blockTrees;
        int i7 = i4 + 1;
        iArr2[i7] = readHuffmanCode;
        int readHuffmanCode2 = readHuffmanCode + readHuffmanCode(26, 26, iArr2, i7, state);
        int[] iArr3 = state.blockTrees;
        iArr3[i4 + 2] = readHuffmanCode2;
        return readBlockLength(iArr3, i7, state);
    }

    private static void readNextMetablockHeader(State state) {
        if (state.inputEnd != 0) {
            state.nextRunningState = 10;
            state.runningState = 12;
            return;
        }
        state.literalTreeGroup = new int[0];
        state.commandTreeGroup = new int[0];
        state.distanceTreeGroup = new int[0];
        BitReader.readMoreInput(state);
        decodeMetaBlockLength(state);
        if (state.metaBlockLength == 0 && state.isMetadata == 0) {
            return;
        }
        if (state.isUncompressed == 0 && state.isMetadata == 0) {
            state.runningState = 3;
        } else {
            BitReader.jumpToByteBoundary(state);
            state.runningState = state.isMetadata != 0 ? 5 : 6;
        }
        if (state.isMetadata != 0) {
            return;
        }
        int i2 = state.expectedTotalSize + state.metaBlockLength;
        state.expectedTotalSize = i2;
        if (i2 > 1073741824) {
            state.expectedTotalSize = 1073741824;
        }
        if (state.ringBufferSize < state.maxRingBufferSize) {
            maybeReallocateRingBuffer(state);
        }
    }

    private static int readSimpleHuffmanCode(int i2, int i3, int[] iArr, int i4, State state) {
        int[] iArr2 = new int[i3];
        int[] iArr3 = new int[4];
        int log2floor = log2floor(i2 - 1) + 1;
        int readFewBits = BitReader.readFewBits(state, 2) + 1;
        for (int i5 = 0; i5 < readFewBits; i5++) {
            BitReader.fillBitWindow(state);
            int readFewBits2 = BitReader.readFewBits(state, log2floor);
            if (readFewBits2 < i3) {
                iArr3[i5] = readFewBits2;
            } else {
                throw new BrotliRuntimeException("Can't readHuffmanCode");
            }
        }
        checkDupes(iArr3, readFewBits);
        if (readFewBits == 4) {
            readFewBits += BitReader.readFewBits(state, 1);
        }
        if (readFewBits == 1) {
            iArr2[iArr3[0]] = 1;
        } else if (readFewBits == 2) {
            iArr2[iArr3[0]] = 1;
            iArr2[iArr3[1]] = 1;
        } else if (readFewBits == 3) {
            iArr2[iArr3[0]] = 1;
            iArr2[iArr3[1]] = 2;
            iArr2[iArr3[2]] = 2;
        } else if (readFewBits == 4) {
            iArr2[iArr3[0]] = 2;
            iArr2[iArr3[1]] = 2;
            iArr2[iArr3[2]] = 2;
            iArr2[iArr3[3]] = 2;
        } else if (readFewBits == 5) {
            iArr2[iArr3[0]] = 1;
            iArr2[iArr3[1]] = 2;
            iArr2[iArr3[2]] = 3;
            iArr2[iArr3[3]] = 3;
        }
        return Huffman.buildHuffmanTable(iArr, i4, 8, iArr2, i3);
    }

    private static int readSymbol(int[] iArr, int i2, State state) {
        int i3 = iArr[i2];
        int peekBits = BitReader.peekBits(state);
        int i4 = i3 + (peekBits & 255);
        int i5 = iArr[i4] >> 16;
        int i6 = iArr[i4] & 65535;
        if (i5 <= 8) {
            state.bitOffset += i5;
            return i6;
        }
        int i7 = i4 + i6 + ((peekBits & ((1 << i5) - 1)) >>> 8);
        state.bitOffset += (iArr[i7] >> 16) + 8;
        return iArr[i7] & 65535;
    }

    private static void unpackCommandLookupTable(short[] sArr) {
        short[] sArr2 = new short[24];
        short[] sArr3 = new short[24];
        sArr3[0] = 2;
        int i2 = 0;
        while (i2 < 23) {
            int i3 = i2 + 1;
            sArr2[i3] = (short) (sArr2[i2] + (1 << INSERT_LENGTH_N_BITS[i2]));
            sArr3[i3] = (short) (sArr3[i2] + (1 << COPY_LENGTH_N_BITS[i2]));
            i2 = i3;
        }
        for (int i4 = 0; i4 < 704; i4++) {
            int i5 = i4 >>> 6;
            int i6 = -4;
            if (i5 >= 2) {
                i5 -= 2;
                i6 = 0;
            }
            int i7 = i5 * 2;
            int i8 = (((170064 >>> i7) & 3) << 3) | ((i4 >>> 3) & 7);
            int i9 = (((156228 >>> i7) & 3) << 3) | (i4 & 7);
            short s = sArr3[i9];
            int i10 = i6 + (s > 4 ? 3 : s - 2);
            int i11 = i4 * 4;
            sArr[i11 + 0] = (short) (INSERT_LENGTH_N_BITS[i8] | (COPY_LENGTH_N_BITS[i9] << 8));
            sArr[i11 + 1] = sArr2[i8];
            sArr[i11 + 2] = sArr3[i9];
            sArr[i11 + 3] = (short) i10;
        }
    }

    private static int writeRingBuffer(State state) {
        int min = Math.min(state.outputLength - state.outputUsed, state.ringBufferBytesReady - state.ringBufferBytesWritten);
        if (min != 0) {
            System.arraycopy(state.ringBuffer, state.ringBufferBytesWritten, state.output, state.outputOffset + state.outputUsed, min);
            state.outputUsed += min;
            state.ringBufferBytesWritten += min;
        }
        return state.outputUsed < state.outputLength ? 1 : 0;
    }
}
