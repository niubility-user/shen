package com.jingdong.common.jdreactFramework.preload;

/* loaded from: classes5.dex */
public enum EngineState {
    NOT_INIT(0),
    INITIALIZED(1),
    COMMON_READY(2),
    CONSUMED(3),
    DESTROYED(4);
    
    private int value;

    EngineState(int i2) {
        this.value = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAtLeast(EngineState engineState) {
        return this.value >= engineState.value;
    }

    boolean isAtMost(EngineState engineState) {
        return this.value <= engineState.value;
    }
}
