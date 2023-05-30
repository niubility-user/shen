package com.tencent.mapsdk.internal;

import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.tencent.mapsdk.shell.events.EngineWriteDataModel;
import com.tencent.mapsdk.shell.events.ReportEvent;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class mi extends ReportEvent {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public mi(EngineWriteDataModel engineWriteDataModel) {
        super("map_engine_writeData", null);
        engineWriteDataModel.getClass();
        HashMap hashMap = new HashMap();
        this.params = hashMap;
        hashMap.put("err", engineWriteDataModel.resultCode + "");
        this.params.put("writeCount", engineWriteDataModel.totalWriteCount + "");
        this.params.put(ApkDownloadTable.FIELD_SIZE, engineWriteDataModel.dataSize + "");
        this.params.put("ptr", engineWriteDataModel.ptr + "");
        this.params.put("url", engineWriteDataModel.url + "");
    }
}
