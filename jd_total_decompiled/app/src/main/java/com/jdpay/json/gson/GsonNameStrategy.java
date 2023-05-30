package com.jdpay.json.gson;

import com.google.gson.FieldNamingStrategy;
import com.jdpay.json.NameStrategy;
import com.jdpay.lib.annotation.Name;
import java.lang.reflect.Field;

/* loaded from: classes18.dex */
public class GsonNameStrategy implements NameStrategy, FieldNamingStrategy {
    @Override // com.google.gson.FieldNamingStrategy
    public String translateName(Field field) {
        Name name = (Name) field.getAnnotation(Name.class);
        return name != null ? name.value() : field.getName();
    }
}
