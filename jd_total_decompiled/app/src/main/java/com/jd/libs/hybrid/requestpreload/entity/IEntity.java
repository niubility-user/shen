package com.jd.libs.hybrid.requestpreload.entity;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0003H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/entity/IEntity;", "T", "", "Lorg/json/JSONObject;", "json", "fromJson", "(Lorg/json/JSONObject;)Ljava/lang/Object;", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public interface IEntity<T> {
    T fromJson(@NotNull JSONObject json);
}
