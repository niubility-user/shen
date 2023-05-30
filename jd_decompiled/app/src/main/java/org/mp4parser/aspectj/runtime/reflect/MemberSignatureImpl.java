package org.mp4parser.aspectj.runtime.reflect;

import org.mp4parser.aspectj.lang.reflect.MemberSignature;

/* loaded from: classes11.dex */
abstract class MemberSignatureImpl extends SignatureImpl implements MemberSignature {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MemberSignatureImpl(int i2, String str, Class cls) {
        super(i2, str, cls);
    }

    public MemberSignatureImpl(String str) {
        super(str);
    }
}
