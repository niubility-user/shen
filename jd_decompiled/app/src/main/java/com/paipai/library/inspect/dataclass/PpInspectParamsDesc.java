package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectParamsDesc implements PpInspectParamsAware {
    private List<PpReportOutlines> data;

    public PpInspectParamsDesc(List<PpReportOutlines> list) {
        this.data = list;
    }

    public List<PpReportOutlines> getData() {
        return this.data;
    }
}
