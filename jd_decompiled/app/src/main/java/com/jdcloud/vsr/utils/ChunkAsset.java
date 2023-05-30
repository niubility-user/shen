package com.jdcloud.vsr.utils;

import android.content.res.AssetManager;
import java.io.IOError;

/* loaded from: classes18.dex */
public class ChunkAsset extends ChunkCollection {
    public ChunkAsset(AssetManager assetManager, String str) throws IOError {
        super(newChunkAsset(assetManager, str));
    }

    private static native long newChunkAsset(AssetManager assetManager, String str) throws IOError;
}
