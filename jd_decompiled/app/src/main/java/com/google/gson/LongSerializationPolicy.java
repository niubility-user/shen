package com.google.gson;

/* loaded from: classes12.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l2) {
            return new JsonPrimitive((Number) l2);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l2) {
            return new JsonPrimitive(String.valueOf(l2));
        }
    };

    public abstract JsonElement serialize(Long l2);
}
