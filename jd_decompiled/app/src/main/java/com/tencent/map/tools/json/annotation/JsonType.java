package com.tencent.map.tools.json.annotation;

import androidx.annotation.Keep;
import com.tencent.map.tools.json.FieldNameStyle;
import com.tencent.map.tools.json.JsonParser;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Keep
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonType {
    boolean allowEmpty() default true;

    Class<? extends JsonParser.Deserializer> deserializer() default JsonParser.Deserializer.class;

    String fieldNamePrefix() default "";

    FieldNameStyle fieldNameStyle() default FieldNameStyle.HUMP;
}
