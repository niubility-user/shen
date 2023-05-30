package org.mp4parser.aspectj.runtime.reflect;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Field;
import org.mp4parser.aspectj.lang.reflect.FieldSignature;

/* loaded from: classes11.dex */
public class FieldSignatureImpl extends MemberSignatureImpl implements FieldSignature {
    private Field field;
    Class fieldType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldSignatureImpl(int i2, String str, Class cls, Class cls2) {
        super(i2, str, cls);
        this.fieldType = cls2;
    }

    @Override // org.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        if (stringMaker.includeArgs) {
            stringBuffer.append(stringMaker.makeTypeName(getFieldType()));
        }
        if (stringMaker.includeArgs) {
            stringBuffer.append(LangUtils.SINGLE_SPACE);
        }
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(OrderISVUtil.MONEY_DECIMAL);
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    @Override // org.mp4parser.aspectj.lang.reflect.FieldSignature
    public Field getField() {
        if (this.field == null) {
            try {
                this.field = getDeclaringType().getDeclaredField(getName());
            } catch (Exception unused) {
            }
        }
        return this.field;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.FieldSignature
    public Class getFieldType() {
        if (this.fieldType == null) {
            this.fieldType = extractType(3);
        }
        return this.fieldType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldSignatureImpl(String str) {
        super(str);
    }
}
