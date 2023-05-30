package com.paipai.library.inspect.dataclass;

import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectParamsItems implements PpInspectParamsAware {
    private List<PpBaseOutlines> data;

    public PpInspectParamsItems(List<PpBaseOutlines> list) {
        this.data = list;
    }

    public List<PpBaseOutlines> getData() {
        return this.data;
    }
}
