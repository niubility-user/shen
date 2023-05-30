package com.jdcloud.vsr.utils;

import java.io.IOError;

/* loaded from: classes18.dex */
public class ChunkFile extends ChunkCollection {
    public ChunkFile(String str, boolean z) throws IOError {
        super(newChunkfile(str, z));
    }

    private static native long newChunkfile(String str, boolean z) throws IOError;
}
