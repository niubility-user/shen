package com.jingdong.common.lbs.http;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public interface JDLbsHttpListListener<T> {
    void onFail(JDLbsHttpError jDLbsHttpError);

    void onSuccess(ArrayList<T> arrayList);
}
