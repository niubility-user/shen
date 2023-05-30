package org.mp4parser.aspectj.internal.lang.reflect;

import org.mp4parser.aspectj.lang.reflect.PerClause;
import org.mp4parser.aspectj.lang.reflect.PerClauseKind;

/* loaded from: classes11.dex */
public class PerClauseImpl implements PerClause {
    private final PerClauseKind kind;

    /* JADX INFO: Access modifiers changed from: protected */
    public PerClauseImpl(PerClauseKind perClauseKind) {
        this.kind = perClauseKind;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.PerClause
    public PerClauseKind getKind() {
        return this.kind;
    }

    public String toString() {
        return "issingleton()";
    }
}
