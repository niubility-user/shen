package com.jd.libs.hybrid.adapter;

import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class RequestAdapter<S, E> implements IAdapter {
    public static final String NAME = "adapter_request";

    /* loaded from: classes16.dex */
    public interface RequestCallback<S, E> {
        void onCancel();

        void onError(String str, E e2);

        void onStart();

        void onSusses(String str, S s);
    }

    public abstract void cancel();

    @Override // com.jd.libs.hybrid.adapter.IAdapter
    public String getName() {
        return NAME;
    }

    public abstract void request(String str, String str2, String str3, JSONObject jSONObject, boolean z, int i2, String str4, String str5, RequestCallback<S, E> requestCallback);
}
