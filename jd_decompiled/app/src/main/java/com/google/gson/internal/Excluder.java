package com.google.gson.internal;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public final class Excluder implements TypeAdapterFactory, Cloneable {
    public static final Excluder DEFAULT = new Excluder();
    private static final double IGNORE_VERSIONS = -1.0d;
    private boolean requireExpose;
    private double version = IGNORE_VERSIONS;
    private int modifiers = R2.anim.lib_cashier_sdk_fragment_right_out;
    private boolean serializeInnerClasses = true;
    private List<ExclusionStrategy> serializationStrategies = Collections.emptyList();
    private List<ExclusionStrategy> deserializationStrategies = Collections.emptyList();

    private boolean excludeClassChecks(Class<?> cls) {
        if (this.version == IGNORE_VERSIONS || isValidVersion((Since) cls.getAnnotation(Since.class), (Until) cls.getAnnotation(Until.class))) {
            return (!this.serializeInnerClasses && isInnerClass(cls)) || isAnonymousOrLocal(cls);
        }
        return true;
    }

    private boolean excludeClassInStrategy(Class<?> cls, boolean z) {
        Iterator<ExclusionStrategy> it = (z ? this.serializationStrategies : this.deserializationStrategies).iterator();
        while (it.hasNext()) {
            if (it.next().shouldSkipClass(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnonymousOrLocal(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean isInnerClass(Class<?> cls) {
        return cls.isMemberClass() && !isStatic(cls);
    }

    private boolean isStatic(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean isValidSince(Since since) {
        return since == null || since.value() <= this.version;
    }

    private boolean isValidUntil(Until until) {
        return until == null || until.value() > this.version;
    }

    private boolean isValidVersion(Since since, Until until) {
        return isValidSince(since) && isValidUntil(until);
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        boolean excludeClassChecks = excludeClassChecks(rawType);
        final boolean z = excludeClassChecks || excludeClassInStrategy(rawType, true);
        final boolean z2 = excludeClassChecks || excludeClassInStrategy(rawType, false);
        if (z || z2) {
            return new TypeAdapter<T>() { // from class: com.google.gson.internal.Excluder.1
                private TypeAdapter<T> delegate;

                private TypeAdapter<T> delegate() {
                    TypeAdapter<T> typeAdapter = this.delegate;
                    if (typeAdapter != null) {
                        return typeAdapter;
                    }
                    TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(Excluder.this, typeToken);
                    this.delegate = delegateAdapter;
                    return delegateAdapter;
                }

                @Override // com.google.gson.TypeAdapter
                /* renamed from: read */
                public T read2(JsonReader jsonReader) throws IOException {
                    if (z2) {
                        jsonReader.skipValue();
                        return null;
                    }
                    return delegate().read2(jsonReader);
                }

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    if (z) {
                        jsonWriter.nullValue();
                    } else {
                        delegate().write(jsonWriter, t);
                    }
                }
            };
        }
        return null;
    }

    public Excluder disableInnerClassSerialization() {
        Excluder m13clone = m13clone();
        m13clone.serializeInnerClasses = false;
        return m13clone;
    }

    public boolean excludeClass(Class<?> cls, boolean z) {
        return excludeClassChecks(cls) || excludeClassInStrategy(cls, z);
    }

    public boolean excludeField(Field field, boolean z) {
        Expose expose;
        if ((this.modifiers & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.version == IGNORE_VERSIONS || isValidVersion((Since) field.getAnnotation(Since.class), (Until) field.getAnnotation(Until.class))) && !field.isSynthetic()) {
            if (!this.requireExpose || ((expose = (Expose) field.getAnnotation(Expose.class)) != null && (!z ? !expose.deserialize() : !expose.serialize()))) {
                if ((this.serializeInnerClasses || !isInnerClass(field.getType())) && !isAnonymousOrLocal(field.getType())) {
                    List<ExclusionStrategy> list = z ? this.serializationStrategies : this.deserializationStrategies;
                    if (list.isEmpty()) {
                        return false;
                    }
                    FieldAttributes fieldAttributes = new FieldAttributes(field);
                    Iterator<ExclusionStrategy> it = list.iterator();
                    while (it.hasNext()) {
                        if (it.next().shouldSkipField(fieldAttributes)) {
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            return true;
        }
        return true;
    }

    public Excluder excludeFieldsWithoutExposeAnnotation() {
        Excluder m13clone = m13clone();
        m13clone.requireExpose = true;
        return m13clone;
    }

    public Excluder withExclusionStrategy(ExclusionStrategy exclusionStrategy, boolean z, boolean z2) {
        Excluder m13clone = m13clone();
        if (z) {
            ArrayList arrayList = new ArrayList(this.serializationStrategies);
            m13clone.serializationStrategies = arrayList;
            arrayList.add(exclusionStrategy);
        }
        if (z2) {
            ArrayList arrayList2 = new ArrayList(this.deserializationStrategies);
            m13clone.deserializationStrategies = arrayList2;
            arrayList2.add(exclusionStrategy);
        }
        return m13clone;
    }

    public Excluder withModifiers(int... iArr) {
        Excluder m13clone = m13clone();
        m13clone.modifiers = 0;
        for (int i2 : iArr) {
            m13clone.modifiers = i2 | m13clone.modifiers;
        }
        return m13clone;
    }

    public Excluder withVersion(double d) {
        Excluder m13clone = m13clone();
        m13clone.version = d;
        return m13clone;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone  reason: merged with bridge method [inline-methods] */
    public Excluder m13clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }
}
