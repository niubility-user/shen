package com.tencent.mapsdk.shell.events;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes9.dex */
public class EngineWriteDataModel {
    public float dataSize;
    public final String eventCode = "map_engine_writeData";
    public long ptr;
    public int resultCode;
    public int totalWriteCount;
    public String url;
}
