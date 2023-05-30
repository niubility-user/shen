package org.mp4parser.aspectj.internal.lang.reflect;

import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration;

/* loaded from: classes11.dex */
public class InterTypeConstructorDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeConstructorDeclaration {
    private Method baseMethod;

    public InterTypeConstructorDeclarationImpl(AjType<?> ajType, String str, int i2, Method method) {
        super(ajType, str, i2);
        this.baseMethod = method;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration
    public AjType<?>[] getExceptionTypes() {
        Class<?>[] exceptionTypes = this.baseMethod.getExceptionTypes();
        AjType<?>[] ajTypeArr = new AjType[exceptionTypes.length];
        for (int i2 = 0; i2 < exceptionTypes.length; i2++) {
            ajTypeArr[i2] = AjTypeSystem.getAjType(exceptionTypes[i2]);
        }
        return ajTypeArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration
    public Type[] getGenericParameterTypes() {
        Type[] genericParameterTypes = this.baseMethod.getGenericParameterTypes();
        AjType[] ajTypeArr = new AjType[genericParameterTypes.length - 1];
        for (int i2 = 1; i2 < genericParameterTypes.length; i2++) {
            if (genericParameterTypes[i2] instanceof Class) {
                ajTypeArr[i2 - 1] = AjTypeSystem.getAjType((Class) genericParameterTypes[i2]);
            } else {
                ajTypeArr[i2 - 1] = genericParameterTypes[i2];
            }
        }
        return ajTypeArr;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration
    public AjType<?>[] getParameterTypes() {
        Class<?>[] parameterTypes = this.baseMethod.getParameterTypes();
        AjType<?>[] ajTypeArr = new AjType[parameterTypes.length - 1];
        for (int i2 = 1; i2 < parameterTypes.length; i2++) {
            ajTypeArr[i2 - 1] = AjTypeSystem.getAjType(parameterTypes[i2]);
        }
        return ajTypeArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(getModifiers()));
        stringBuffer.append(LangUtils.SINGLE_SPACE);
        stringBuffer.append(this.targetTypeName);
        stringBuffer.append(".new");
        stringBuffer.append("(");
        AjType<?>[] parameterTypes = getParameterTypes();
        for (int i2 = 0; i2 < parameterTypes.length - 1; i2++) {
            stringBuffer.append(parameterTypes[i2].toString());
            stringBuffer.append(", ");
        }
        if (parameterTypes.length > 0) {
            stringBuffer.append(parameterTypes[parameterTypes.length - 1].toString());
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
