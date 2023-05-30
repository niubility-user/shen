package com.jingdong.app.mall.bundle.jdbrotli;

/* loaded from: classes2.dex */
public class BitReader {
    static final int BITNESS;
    private static final int BUFFER_SIZE = 4160;
    private static final int BYTENESS;
    private static final int CAPACITY = 4096;
    private static final int DEBUG;
    private static final int HALF_BITNESS;
    private static final int HALF_BUFFER_SIZE;
    private static final int HALF_SIZE;
    private static final int HALF_WATERLINE;
    private static final int HALVES_CAPACITY;
    private static final int LOG_BITNESS;
    private static final int LOG_HALF_SIZE;
    private static final int SAFEGUARD = 36;
    private static final int SLACK = 64;
    private static final int WATERLINE = 4060;

    static {
        int logBintness = Utils.getLogBintness();
        LOG_BITNESS = logBintness;
        DEBUG = Utils.isDebugMode();
        int i2 = 1 << logBintness;
        BITNESS = i2;
        int i3 = i2 / 8;
        BYTENESS = i3;
        HALF_BITNESS = i2 / 2;
        int i4 = i3 / 2;
        HALF_SIZE = i4;
        HALVES_CAPACITY = 4096 / i4;
        HALF_BUFFER_SIZE = 4160 / i4;
        HALF_WATERLINE = 4060 / i4;
        LOG_HALF_SIZE = logBintness - 4;
    }

    static void assertAccumulatorHealthy(State state) {
        if (state.bitOffset <= BITNESS) {
            return;
        }
        throw new IllegalStateException("Accumulator underloaded: " + state.bitOffset);
    }

    static void bytesToNibbles(State state, int i2) {
        byte[] bArr = state.byteBuffer;
        int i3 = i2 >> LOG_HALF_SIZE;
        int i4 = 0;
        if (BITNESS == 64) {
            int[] iArr = state.intBuffer;
            while (i4 < i3) {
                int i5 = i4 * 4;
                iArr[i4] = ((bArr[i5 + 3] & 255) << 24) | (bArr[i5] & 255) | ((bArr[i5 + 1] & 255) << 8) | ((bArr[i5 + 2] & 255) << 16);
                i4++;
            }
            return;
        }
        short[] sArr = state.shortBuffer;
        while (i4 < i3) {
            int i6 = i4 * 2;
            sArr[i4] = (short) (((bArr[i6 + 1] & 255) << 8) | (bArr[i6] & 255));
            i4++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkHealth(State state, int i2) {
        if (state.endOfStreamReached == 0) {
            return;
        }
        int i3 = ((state.halfOffset << LOG_HALF_SIZE) + ((state.bitOffset + 7) >> 3)) - BYTENESS;
        int i4 = state.tailBytes;
        if (i3 > i4) {
            throw new BrotliRuntimeException("Read after end");
        }
        if (i2 != 0 && i3 != i4) {
            throw new BrotliRuntimeException("Unused bytes after end");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyBytes(State state, byte[] bArr, int i2, int i3) {
        if ((state.bitOffset & 7) == 0) {
            while (state.bitOffset != BITNESS && i3 != 0) {
                bArr[i2] = (byte) peekBits(state);
                state.bitOffset += 8;
                i3--;
                i2++;
            }
            if (i3 == 0) {
                return;
            }
            int halfAvailable = halfAvailable(state);
            int i4 = LOG_HALF_SIZE;
            int min = Math.min(halfAvailable, i3 >> i4);
            if (min > 0) {
                int i5 = state.halfOffset << i4;
                int i6 = min << i4;
                System.arraycopy(state.byteBuffer, i5, bArr, i2, i6);
                i2 += i6;
                i3 -= i6;
                state.halfOffset += min;
            }
            if (i3 == 0) {
                return;
            }
            if (halfAvailable(state) <= 0) {
                while (i3 > 0) {
                    int readInput = Utils.readInput(state.input, bArr, i2, i3);
                    if (readInput == -1) {
                        throw new BrotliRuntimeException("Unexpected end of input");
                    }
                    i2 += readInput;
                    i3 -= readInput;
                }
                return;
            }
            fillBitWindow(state);
            while (i3 != 0) {
                bArr[i2] = (byte) peekBits(state);
                state.bitOffset += 8;
                i3--;
                i2++;
            }
            checkHealth(state, 0);
            return;
        }
        throw new BrotliRuntimeException("Unaligned copyBytes");
    }

    static void doFillBitWindow(State state) {
        if (DEBUG != 0) {
            assertAccumulatorHealthy(state);
        }
        if (BITNESS == 64) {
            int[] iArr = state.intBuffer;
            state.halfOffset = state.halfOffset + 1;
            int i2 = HALF_BITNESS;
            state.accumulator64 = (iArr[r1] << i2) | (state.accumulator64 >>> i2);
        } else {
            short[] sArr = state.shortBuffer;
            int i3 = state.halfOffset;
            state.halfOffset = i3 + 1;
            short s = sArr[i3];
            int i4 = HALF_BITNESS;
            state.accumulator32 = (s << i4) | (state.accumulator32 >>> i4);
        }
        state.bitOffset -= HALF_BITNESS;
    }

    static void doReadMoreInput(State state) {
        if (state.endOfStreamReached != 0) {
            if (halfAvailable(state) < -2) {
                throw new BrotliRuntimeException("No more input");
            }
            return;
        }
        int i2 = state.halfOffset << LOG_HALF_SIZE;
        int i3 = 4096 - i2;
        Utils.copyBytesWithin(state.byteBuffer, 0, i2, 4096);
        state.halfOffset = 0;
        while (true) {
            if (i3 >= 4096) {
                break;
            }
            int readInput = Utils.readInput(state.input, state.byteBuffer, i3, 4096 - i3);
            if (readInput <= 0) {
                state.endOfStreamReached = 1;
                state.tailBytes = i3;
                i3 += HALF_SIZE - 1;
                break;
            }
            i3 += readInput;
        }
        bytesToNibbles(state, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fillBitWindow(State state) {
        if (DEBUG != 0) {
            assertAccumulatorHealthy(state);
        }
        int i2 = state.bitOffset;
        int i3 = HALF_BITNESS;
        if (i2 >= i3) {
            if (BITNESS == 64) {
                int[] iArr = state.intBuffer;
                state.halfOffset = state.halfOffset + 1;
                state.accumulator64 = (iArr[r3] << i3) | (state.accumulator64 >>> i3);
            } else {
                short[] sArr = state.shortBuffer;
                int i4 = state.halfOffset;
                state.halfOffset = i4 + 1;
                state.accumulator32 = (sArr[i4] << i3) | (state.accumulator32 >>> i3);
            }
            state.bitOffset = i2 - i3;
        }
    }

    static int halfAvailable(State state) {
        int i2 = HALVES_CAPACITY;
        if (state.endOfStreamReached != 0) {
            i2 = (state.tailBytes + (HALF_SIZE - 1)) >> LOG_HALF_SIZE;
        }
        return i2 - state.halfOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initBitReader(State state) {
        state.byteBuffer = new byte[4160];
        int i2 = BITNESS;
        if (i2 == 64) {
            state.accumulator64 = 0L;
            state.intBuffer = new int[HALF_BUFFER_SIZE];
        } else {
            state.accumulator32 = 0;
            state.shortBuffer = new short[HALF_BUFFER_SIZE];
        }
        state.bitOffset = i2;
        state.halfOffset = HALVES_CAPACITY;
        state.endOfStreamReached = 0;
        prepare(state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void jumpToByteBoundary(State state) {
        int i2 = (BITNESS - state.bitOffset) & 7;
        if (i2 != 0 && readFewBits(state, i2) != 0) {
            throw new BrotliRuntimeException("Corrupted padding bits");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int peekBits(State state) {
        if (BITNESS == 64) {
            return (int) (state.accumulator64 >>> state.bitOffset);
        }
        return state.accumulator32 >>> state.bitOffset;
    }

    private static void prepare(State state) {
        readMoreInput(state);
        checkHealth(state, 0);
        doFillBitWindow(state);
        doFillBitWindow(state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readBits(State state, int i2) {
        if (HALF_BITNESS >= 24) {
            return readFewBits(state, i2);
        }
        return i2 <= 16 ? readFewBits(state, i2) : readManyBits(state, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readFewBits(State state, int i2) {
        int peekBits = peekBits(state) & ((1 << i2) - 1);
        state.bitOffset += i2;
        return peekBits;
    }

    private static int readManyBits(State state, int i2) {
        int readFewBits = readFewBits(state, 16);
        doFillBitWindow(state);
        return (readFewBits(state, i2 - 16) << 16) | readFewBits;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void readMoreInput(State state) {
        if (state.halfOffset > HALF_WATERLINE) {
            doReadMoreInput(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reload(State state) {
        if (state.bitOffset == BITNESS) {
            prepare(state);
        }
    }
}
