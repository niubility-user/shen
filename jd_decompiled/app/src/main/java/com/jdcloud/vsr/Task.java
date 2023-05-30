package com.jdcloud.vsr;

import com.jdcloud.vsr.exceptions.CoreException;

/* loaded from: classes18.dex */
public class Task extends JDTObject {
    protected JDTContext context;

    /* JADX INFO: Access modifiers changed from: protected */
    public Task(JDTContext jDTContext, long j2) {
        super(j2);
        this.context = jDTContext;
    }

    public float execute() throws CoreException {
        return this.context.performTask(this);
    }

    public JDTContext getContext() {
        return this.context;
    }
}
