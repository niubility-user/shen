package org.mp4parser.aspectj.internal.lang.reflect;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration;

/* loaded from: classes11.dex */
public class InterTypeMethodDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeMethodDeclaration {
    private Method baseMethod;
    private AjType<?>[] exceptionTypes;
    private Type[] genericParameterTypes;
    private Type genericReturnType;
    private String name;
    private int parameterAdjustmentFactor;
    private AjType<?>[] parameterTypes;
    private AjType<?> returnType;

    public InterTypeMethodDeclarationImpl(AjType<?> ajType, String str, int i2, String str2, Method method) {
        super(ajType, str, i2);
        this.parameterAdjustmentFactor = 1;
        this.name = str2;
        this.baseMethod = method;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public AjType<?>[] getExceptionTypes() {
        Class<?>[] exceptionTypes = this.baseMethod.getExceptionTypes();
        AjType<?>[] ajTypeArr = new AjType[exceptionTypes.length];
        for (int i2 = 0; i2 < exceptionTypes.length; i2++) {
            ajTypeArr[i2] = AjTypeSystem.getAjType(exceptionTypes[i2]);
        }
        return ajTypeArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public Type[] getGenericParameterTypes() {
        Type[] genericParameterTypes = this.baseMethod.getGenericParameterTypes();
        int length = genericParameterTypes.length;
        int i2 = this.parameterAdjustmentFactor;
        AjType[] ajTypeArr = new AjType[length - i2];
        while (i2 < genericParameterTypes.length) {
            if (genericParameterTypes[i2] instanceof Class) {
                ajTypeArr[i2 - this.parameterAdjustmentFactor] = AjTypeSystem.getAjType((Class) genericParameterTypes[i2]);
            } else {
                ajTypeArr[i2 - this.parameterAdjustmentFactor] = genericParameterTypes[i2];
            }
            i2++;
        }
        return ajTypeArr;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public Type getGenericReturnType() {
        Type genericReturnType = this.baseMethod.getGenericReturnType();
        return genericReturnType instanceof Class ? AjTypeSystem.getAjType((Class) genericReturnType) : genericReturnType;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public String getName() {
        return this.name;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public AjType<?>[] getParameterTypes() {
        Class<?>[] parameterTypes = this.baseMethod.getParameterTypes();
        int length = parameterTypes.length;
        int i2 = this.parameterAdjustmentFactor;
        AjType<?>[] ajTypeArr = new AjType[length - i2];
        while (i2 < parameterTypes.length) {
            ajTypeArr[i2 - this.parameterAdjustmentFactor] = AjTypeSystem.getAjType(parameterTypes[i2]);
            i2++;
        }
        return ajTypeArr;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public AjType<?> getReturnType() {
        return AjTypeSystem.getAjType(this.baseMethod.getReturnType());
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration
    public TypeVariable<Method>[] getTypeParameters() {
        return this.baseMethod.getTypeParameters();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(getModifiers()));
        stringBuffer.append(LangUtils.SINGLE_SPACE);
        stringBuffer.append(getReturnType().toString());
        stringBuffer.append(LangUtils.SINGLE_SPACE);
        stringBuffer.append(this.targetTypeName);
        stringBuffer.append(OrderISVUtil.MONEY_DECIMAL);
        stringBuffer.append(getName());
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

    public InterTypeMethodDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, Method method, int i2) {
        super(ajType, ajType2, i2);
        this.parameterAdjustmentFactor = 1;
        this.parameterAdjustmentFactor = 0;
        this.name = method.getName();
        this.baseMethod = method;
    }
}
