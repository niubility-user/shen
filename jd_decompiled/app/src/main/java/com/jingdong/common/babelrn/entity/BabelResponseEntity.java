package com.jingdong.common.babelrn.entity;

import com.jingdong.jdsdk.network.toolbox.HttpResponse;

/* loaded from: classes5.dex */
public class BabelResponseEntity {
    public int height;
    public HttpResponse response;
    public String rndata;
    public long time;
    public int width;

    public BabelResponseEntity() {
    }

    public BabelResponseEntity(long j2, HttpResponse httpResponse) {
        this.time = j2;
        this.response = httpResponse;
    }
}
