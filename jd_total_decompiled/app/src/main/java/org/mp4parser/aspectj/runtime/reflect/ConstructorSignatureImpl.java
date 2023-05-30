package org.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import org.mp4parser.aspectj.lang.reflect.ConstructorSignature;

/* loaded from: classes11.dex */
class ConstructorSignatureImpl extends CodeSignatureImpl implements ConstructorSignature {
    private Constructor constructor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstructorSignatureImpl(int i2, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        super(i2, "<init>", cls, clsArr, strArr, clsArr2);
    }

    @Override // org.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringMaker.addSignature(stringBuffer, getParameterTypes());
        stringMaker.addThrows(stringBuffer, getExceptionTypes());
        return stringBuffer.toString();
    }

    @Override // org.mp4parser.aspectj.lang.reflect.ConstructorSignature
    public Constructor getConstructor() {
        if (this.constructor == null) {
            try {
                this.constructor = getDeclaringType().getDeclaredConstructor(getParameterTypes());
            } catch (Exception unused) {
            }
        }
        return this.constructor;
    }

    @Override // org.mp4parser.aspectj.runtime.reflect.SignatureImpl, org.mp4parser.aspectj.lang.Signature
    public String getName() {
        return "<init>";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstructorSignatureImpl(String str) {
        super(str);
    }
}
