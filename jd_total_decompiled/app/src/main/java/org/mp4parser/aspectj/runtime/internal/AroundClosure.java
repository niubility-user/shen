package org.mp4parser.aspectj.runtime.internal;

import org.mp4parser.aspectj.lang.ProceedingJoinPoint;

/* loaded from: classes11.dex */
public abstract class AroundClosure {
    protected int bitflags = 1048576;
    protected Object[] preInitializationState;
    protected Object[] state;

    public AroundClosure() {
    }

    public int getFlags() {
        return this.bitflags;
    }

    public Object[] getPreInitializationState() {
        return this.preInitializationState;
    }

    public Object[] getState() {
        return this.state;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint() {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) this.state[r0.length - 1];
        proceedingJoinPoint.set$AroundClosure(this);
        return proceedingJoinPoint;
    }

    public abstract Object run(Object[] objArr) throws Throwable;

    public AroundClosure(Object[] objArr) {
        this.state = objArr;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint(int i2) {
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) this.state[r0.length - 1];
        proceedingJoinPoint.set$AroundClosure(this);
        this.bitflags = i2;
        return proceedingJoinPoint;
    }
}
