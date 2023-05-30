package org.mp4parser.aspectj.internal.lang.reflect;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import org.mp4parser.aspectj.lang.reflect.AjType;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import org.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration;

/* loaded from: classes11.dex */
public class InterTypeFieldDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeFieldDeclaration {
    private Type genericType;
    private String name;
    private AjType<?> type;

    public InterTypeFieldDeclarationImpl(AjType<?> ajType, String str, int i2, String str2, AjType<?> ajType2, Type type) {
        super(ajType, str, i2);
        this.name = str2;
        this.type = ajType2;
        this.genericType = type;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration
    public Type getGenericType() {
        return this.genericType;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration
    public String getName() {
        return this.name;
    }

    @Override // org.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration
    public AjType<?> getType() {
        return this.type;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(getModifiers()));
        stringBuffer.append(LangUtils.SINGLE_SPACE);
        stringBuffer.append(getType().toString());
        stringBuffer.append(LangUtils.SINGLE_SPACE);
        stringBuffer.append(this.targetTypeName);
        stringBuffer.append(OrderISVUtil.MONEY_DECIMAL);
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    public InterTypeFieldDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, Field field) {
        super(ajType, ajType2, field.getModifiers());
        this.name = field.getName();
        this.type = AjTypeSystem.getAjType(field.getType());
        Type genericType = field.getGenericType();
        if (genericType instanceof Class) {
            this.genericType = AjTypeSystem.getAjType((Class) genericType);
        } else {
            this.genericType = genericType;
        }
    }
}
