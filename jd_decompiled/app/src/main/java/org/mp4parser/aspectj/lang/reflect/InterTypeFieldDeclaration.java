package org.mp4parser.aspectj.lang.reflect;

import java.lang.reflect.Type;

/* loaded from: classes11.dex */
public interface InterTypeFieldDeclaration extends InterTypeDeclaration {
    Type getGenericType();

    String getName();

    AjType<?> getType();
}
