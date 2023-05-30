package com.jingdong.common.protocol.imageloader;

/* loaded from: classes5.dex */
public abstract class AbstractImageOptionsHolder<Options> {
    private Options mOptions;

    public abstract Options createDefaultOptions();

    public Options getOptions() {
        if (this.mOptions == null) {
            this.mOptions = createDefaultOptions();
        }
        return this.mOptions;
    }

    public void setOptions(Options options) {
        this.mOptions = options;
    }
}
