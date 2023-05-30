package org.mp4parser.aspectj.internal.lang.reflect;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.mp4parser.aspectj.lang.annotation.AdviceName;
import org.mp4parser.aspectj.lang.reflect.Advice;
import org.mp4parser.aspectj.lang.reflect.AdviceKind;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* loaded from: classes11.dex */
public class AdviceImpl implements Advice {
    private static final String AJC_INTERNAL = "org.mp4parser.aspectj.runtime.internal";
    private final Method adviceMethod;
    private AjType[] exceptionTypes;
    private Type[] genericParameterTypes;
    private boolean hasExtraParam;
    private final AdviceKind kind;
    private AjType[] parameterTypes;
    private PointcutExpression pointcutExpression;

    /* renamed from: org.mp4parser.aspectj.internal.lang.reflect.AdviceImpl$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$aspectj$lang$reflect$AdviceKind;

        static {
            int[] iArr = new int[AdviceKind.values().length];
            $SwitchMap$org$aspectj$lang$reflect$AdviceKind = iArr;
            try {
                iArr[AdviceKind.AFTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$AdviceKind[AdviceKind.AFTER_RETURNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$AdviceKind[AdviceKind.AFTER_THROWING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$AdviceKind[AdviceKind.AROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$aspectj$lang$reflect$AdviceKind[AdviceKind.BEFORE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AdviceImpl(Method method, String str, AdviceKind adviceKind) {
        this.hasExtraParam = false;
        this.kind = adviceKind;
        this.adviceMethod = method;
        this.pointcutExpression = new PointcutExpressionImpl(str);
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public AjType getDeclaringType() {
        return AjTypeSystem.getAjType(this.adviceMethod.getDeclaringClass());
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public AjType<?>[] getExceptionTypes() {
        if (this.exceptionTypes == null) {
            Class<?>[] exceptionTypes = this.adviceMethod.getExceptionTypes();
            this.exceptionTypes = new AjType[exceptionTypes.length];
            for (int i2 = 0; i2 < exceptionTypes.length; i2++) {
                this.exceptionTypes[i2] = AjTypeSystem.getAjType(exceptionTypes[i2]);
            }
        }
        return this.exceptionTypes;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public Type[] getGenericParameterTypes() {
        if (this.genericParameterTypes == null) {
            Type[] genericParameterTypes = this.adviceMethod.getGenericParameterTypes();
            int i2 = 0;
            int i3 = 0;
            for (Type type : genericParameterTypes) {
                if ((type instanceof Class) && ((Class) type).getPackage().getName().equals(AJC_INTERNAL)) {
                    i3++;
                }
            }
            this.genericParameterTypes = new Type[genericParameterTypes.length - i3];
            while (true) {
                Type[] typeArr = this.genericParameterTypes;
                if (i2 >= typeArr.length) {
                    break;
                }
                if (genericParameterTypes[i2] instanceof Class) {
                    typeArr[i2] = AjTypeSystem.getAjType((Class) genericParameterTypes[i2]);
                } else {
                    typeArr[i2] = genericParameterTypes[i2];
                }
                i2++;
            }
        }
        return this.genericParameterTypes;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public AdviceKind getKind() {
        return this.kind;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public String getName() {
        String name = this.adviceMethod.getName();
        if (name.startsWith("ajc$")) {
            AdviceName adviceName = (AdviceName) this.adviceMethod.getAnnotation(AdviceName.class);
            return adviceName != null ? adviceName.value() : "";
        }
        return name;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public AjType<?>[] getParameterTypes() {
        if (this.parameterTypes == null) {
            Class<?>[] parameterTypes = this.adviceMethod.getParameterTypes();
            int i2 = 0;
            int i3 = 0;
            for (Class<?> cls : parameterTypes) {
                if (cls.getPackage().getName().equals(AJC_INTERNAL)) {
                    i3++;
                }
            }
            this.parameterTypes = new AjType[parameterTypes.length - i3];
            while (true) {
                AjType[] ajTypeArr = this.parameterTypes;
                if (i2 >= ajTypeArr.length) {
                    break;
                }
                ajTypeArr[i2] = AjTypeSystem.getAjType(parameterTypes[i2]);
                i2++;
            }
        }
        return this.parameterTypes;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.Advice
    public PointcutExpression getPointcutExpression() {
        return this.pointcutExpression;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a6, code lost:
        if (r10 != 3) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getName().length() > 0) {
            stringBuffer.append("@AdviceName(\"");
            stringBuffer.append(getName());
            stringBuffer.append("\") ");
        }
        if (getKind() == AdviceKind.AROUND) {
            stringBuffer.append(this.adviceMethod.getGenericReturnType().toString());
            stringBuffer.append(LangUtils.SINGLE_SPACE);
        }
        int i2 = AnonymousClass1.$SwitchMap$org$aspectj$lang$reflect$AdviceKind[getKind().ordinal()];
        if (i2 == 1) {
            stringBuffer.append("after(");
        } else if (i2 == 2) {
            stringBuffer.append("after(");
        } else if (i2 == 3) {
            stringBuffer.append("after(");
        } else if (i2 == 4) {
            stringBuffer.append("around(");
        } else if (i2 == 5) {
            stringBuffer.append("before(");
        }
        AjType<?>[] parameterTypes = getParameterTypes();
        int length = parameterTypes.length;
        if (this.hasExtraParam) {
            length--;
        }
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            stringBuffer.append(parameterTypes[i4].getName());
            i4++;
            if (i4 < length) {
                stringBuffer.append(DYConstants.DY_REGEX_COMMA);
            }
        }
        stringBuffer.append(") ");
        int i5 = AnonymousClass1.$SwitchMap$org$aspectj$lang$reflect$AdviceKind[getKind().ordinal()];
        if (i5 == 2) {
            stringBuffer.append("returning");
            if (this.hasExtraParam) {
                stringBuffer.append("(");
                stringBuffer.append(parameterTypes[length - 1].getName());
                stringBuffer.append(") ");
            }
        }
        stringBuffer.append("throwing");
        if (this.hasExtraParam) {
            stringBuffer.append("(");
            stringBuffer.append(parameterTypes[length - 1].getName());
            stringBuffer.append(") ");
        }
        AjType<?>[] exceptionTypes = getExceptionTypes();
        if (exceptionTypes.length > 0) {
            stringBuffer.append("throws ");
            while (i3 < exceptionTypes.length) {
                stringBuffer.append(exceptionTypes[i3].getName());
                i3++;
                if (i3 < exceptionTypes.length) {
                    stringBuffer.append(DYConstants.DY_REGEX_COMMA);
                }
            }
            stringBuffer.append(LangUtils.SINGLE_SPACE);
        }
        stringBuffer.append(": ");
        stringBuffer.append(getPointcutExpression().asString());
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AdviceImpl(Method method, String str, AdviceKind adviceKind, String str2) {
        this(method, str, adviceKind);
        this.hasExtraParam = true;
    }
}
