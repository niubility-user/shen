package com.jingdong.app.mall.bundle.jdbrotli;

import java.io.InputStream;

/* loaded from: classes2.dex */
public class State {
    int accumulator32;
    long accumulator64;
    int bitOffset;
    int[] blockTrees;
    byte[] byteBuffer;
    int commandBlockLength;
    int[] commandTreeGroup;
    int commandTreeIdx;
    int contextLookupOffset1;
    int contextLookupOffset2;
    byte[] contextMap;
    int contextMapSlice;
    byte[] contextModes;
    int copyLength;
    byte[] distContextMap;
    int distContextMapSlice;
    byte[] distExtraBits;
    int[] distOffset;
    int distRbIdx;
    int distance;
    int distanceBlockLength;
    int distanceCode;
    int distancePostfixBits;
    int distancePostfixMask;
    int[] distanceTreeGroup;
    int endOfStreamReached;
    int expectedTotalSize;
    int halfOffset;
    InputStream input;
    int inputEnd;
    int insertLength;
    int[] intBuffer;
    int isEager;
    int isLargeWindow;
    int isMetadata;
    int isUncompressed;

    /* renamed from: j  reason: collision with root package name */
    int f8128j;
    int literalBlockLength;
    int[] literalTreeGroup;
    int literalTreeIdx;
    int maxBackwardDistance;
    int maxDistance;
    int maxRingBufferSize;
    int metaBlockLength;
    int nextRunningState;
    int numCommandBlockTypes;
    int numDirectDistanceCodes;
    int numDistanceBlockTypes;
    int numLiteralBlockTypes;
    byte[] output;
    int outputLength;
    int outputOffset;
    int outputUsed;
    int pos;
    byte[] ringBuffer = new byte[0];
    int ringBufferBytesReady;
    int ringBufferBytesWritten;
    int ringBufferSize;
    int[] rings;
    int runningState;
    short[] shortBuffer;
    int tailBytes;
    int trivialLiteralContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public State() {
        int[] iArr = new int[10];
        this.rings = iArr;
        iArr[0] = 16;
        iArr[1] = 15;
        iArr[2] = 11;
        iArr[3] = 4;
    }
}
