package com.jingdong.sdk.bmode.util;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/jingdong/sdk/bmode/util/RequestModeCallback;", "", "", "success", "()V", "fail", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface RequestModeCallback {
    void fail();

    void success();
}
