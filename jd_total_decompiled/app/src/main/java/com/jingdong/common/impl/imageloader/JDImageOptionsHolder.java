package com.jingdong.common.impl.imageloader;

import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.protocol.imageloader.AbstractImageOptionsHolder;

/* loaded from: classes5.dex */
public class JDImageOptionsHolder extends AbstractImageOptionsHolder<JDDisplayImageOptions> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jingdong.common.protocol.imageloader.AbstractImageOptionsHolder
    public JDDisplayImageOptions createDefaultOptions() {
        return new JDDisplayImageOptions();
    }
}
