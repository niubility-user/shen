package org.mp4parser.aspectj.internal.lang.reflect;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.Pointcut;
import org.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* loaded from: classes11.dex */
public class PointcutImpl implements Pointcut {
    private final Method baseMethod;
    private final AjType declaringType;
    private final String name;
    private String[] parameterNames;
    private final PointcutExpression pc;

    /* JADX INFO: Access modifiers changed from: protected */
    public PointcutImpl(String str, String str2, Method method, AjType ajType, String str3) {
        this.parameterNames = new String[0];
        this.name = str;
        this.pc = new PointcutExpressionImpl(str2);
        this.baseMethod = method;
        this.declaringType = ajType;
        this.parameterNames = splitOnComma(str3);
    }

    private String[] splitOnComma(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, DYConstants.DY_REGEX_COMMA);
        int countTokens = stringTokenizer.countTokens();
        String[] strArr = new String[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            strArr[i2] = stringTokenizer.nextToken().trim();
        }
        return strArr;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Pointcut
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Pointcut
    public int getModifiers() {
        return this.baseMethod.getModifiers();
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Pointcut
    public String getName() {
        return this.name;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Pointcut
    public String[] getParameterNames() {
        return this.parameterNames;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Pointcut
    public AjType<?>[] getParameterTypes() {
        Class<?>[] parameterTypes = this.baseMethod.getParameterTypes();
        int length = parameterTypes.length;
        AjType<?>[] ajTypeArr = new AjType[length];
        for (int i2 = 0; i2 < length; i2++) {
            ajTypeArr[i2] = AjTypeSystem.getAjType(parameterTypes[i2]);
        }
        return ajTypeArr;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Pointcut
    public PointcutExpression getPointcutExpression() {
        return this.pc;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getName());
        stringBuffer.append("(");
        AjType<?>[] parameterTypes = getParameterTypes();
        int i2 = 0;
        while (i2 < parameterTypes.length) {
            stringBuffer.append(parameterTypes[i2].getName());
            String[] strArr = this.parameterNames;
            if (strArr != null && strArr[i2] != null) {
                stringBuffer.append(LangUtils.SINGLE_SPACE);
                stringBuffer.append(this.parameterNames[i2]);
            }
            i2++;
            if (i2 < parameterTypes.length) {
                stringBuffer.append(DYConstants.DY_REGEX_COMMA);
            }
        }
        stringBuffer.append(") : ");
        stringBuffer.append(getPointcutExpression().asString());
        return stringBuffer.toString();
    }
}
