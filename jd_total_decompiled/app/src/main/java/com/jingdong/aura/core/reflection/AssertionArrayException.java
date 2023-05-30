package com.jingdong.aura.core.reflection;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.core.reflection.Hack;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class AssertionArrayException extends Exception {
    private static final long serialVersionUID = 1;
    private List<Hack.HackDeclaration.HackAssertionException> mAssertionErr;

    public AssertionArrayException(String str) {
        super(str);
        this.mAssertionErr = new ArrayList();
    }

    public static AssertionArrayException mergeException(AssertionArrayException assertionArrayException, AssertionArrayException assertionArrayException2) {
        if (assertionArrayException == null) {
            return assertionArrayException2;
        }
        if (assertionArrayException2 == null) {
            return assertionArrayException;
        }
        AssertionArrayException assertionArrayException3 = new AssertionArrayException(assertionArrayException.getMessage() + ";" + assertionArrayException2.getMessage());
        assertionArrayException3.addException(assertionArrayException.getExceptions());
        assertionArrayException3.addException(assertionArrayException2.getExceptions());
        return assertionArrayException3;
    }

    public void addException(Hack.HackDeclaration.HackAssertionException hackAssertionException) {
        this.mAssertionErr.add(hackAssertionException);
    }

    public List<Hack.HackDeclaration.HackAssertionException> getExceptions() {
        return this.mAssertionErr;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Hack.HackDeclaration.HackAssertionException hackAssertionException : this.mAssertionErr) {
            sb.append(hackAssertionException.toString());
            sb.append(";");
            try {
                int i2 = 0;
                if (hackAssertionException.getCause() instanceof NoSuchFieldException) {
                    Field[] declaredFields = hackAssertionException.getHackedClass().getDeclaredFields();
                    sb.append(hackAssertionException.getHackedClass().getName());
                    sb.append(OrderISVUtil.MONEY_DECIMAL);
                    sb.append(hackAssertionException.getHackedFieldName());
                    sb.append(";");
                    int length = declaredFields.length;
                    while (i2 < length) {
                        sb.append(declaredFields[i2].getName());
                        sb.append("/");
                        i2++;
                    }
                } else if (hackAssertionException.getCause() instanceof NoSuchMethodException) {
                    Method[] declaredMethods = hackAssertionException.getHackedClass().getDeclaredMethods();
                    sb.append(hackAssertionException.getHackedClass().getName());
                    sb.append("->");
                    sb.append(hackAssertionException.getHackedMethodName());
                    sb.append(";");
                    while (i2 < declaredMethods.length) {
                        if (hackAssertionException.getHackedMethodName().equals(declaredMethods[i2].getName())) {
                            sb.append(declaredMethods[i2].toGenericString());
                            sb.append("/");
                        }
                        i2++;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            sb.append("@@@@");
        }
        return sb.toString();
    }

    public void addException(List<Hack.HackDeclaration.HackAssertionException> list) {
        this.mAssertionErr.addAll(list);
    }
}
