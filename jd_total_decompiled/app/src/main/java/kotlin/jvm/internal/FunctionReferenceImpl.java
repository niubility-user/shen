package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes11.dex */
public class FunctionReferenceImpl extends FunctionReference {
    private final String name;
    private final KDeclarationContainer owner;
    private final String signature;

    public FunctionReferenceImpl(int i2, KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(i2);
        this.owner = kDeclarationContainer;
        this.name = str;
        this.signature = str2;
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return this.owner;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return this.signature;
    }
}