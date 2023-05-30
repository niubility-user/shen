package org.mp4parser.aspectj.runtime.reflect;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import org.mp4parser.aspectj.lang.reflect.InitializerSignature;

/* loaded from: classes11.dex */
class InitializerSignatureImpl extends CodeSignatureImpl implements InitializerSignature {
    private Constructor constructor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InitializerSignatureImpl(int i2, Class cls) {
        super(i2, Modifier.isStatic(i2) ? "<clinit>" : "<init>", cls, SignatureImpl.EMPTY_CLASS_ARRAY, SignatureImpl.EMPTY_STRING_ARRAY, SignatureImpl.EMPTY_CLASS_ARRAY);
    }

    @Override // org.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(OrderISVUtil.MONEY_DECIMAL);
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InitializerSignature
    public Constructor getInitializer() {
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
        return Modifier.isStatic(getModifiers()) ? "<clinit>" : "<init>";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InitializerSignatureImpl(String str) {
        super(str);
    }
}
