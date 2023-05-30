package org.mp4parser.aspectj.runtime.internal;

import org.mp4parser.aspectj.runtime.CFlow;

/* loaded from: classes11.dex */
public class CFlowPlusState extends CFlow {
    private Object[] state;

    public CFlowPlusState(Object[] objArr) {
        this.state = objArr;
    }

    @Override // org.mp4parser.aspectj.runtime.CFlow
    public Object get(int i2) {
        return this.state[i2];
    }

    public CFlowPlusState(Object[] objArr, Object obj) {
        super(obj);
        this.state = objArr;
    }
}
