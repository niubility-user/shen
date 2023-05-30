package com.jingdong.common.search.event;

import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes6.dex */
public class SearchIdPlusEvent extends BaseEvent {
    public static final String ADD_MY_IDPLUS_FROM_M = "ADD_MY_IDPLUS_FROM_M";
    public static final String GET_MY_IDPLUS_FAILED = "GET_MY_IDPLUS_FAILED";
    public static final String GET_MY_IDPLUS_SUCCESS = "GET_MY_IDPLUS_SUCCESS";

    public SearchIdPlusEvent(String str) {
        super(str);
    }

    public SearchIdPlusEvent(String str, String str2) {
        super(str, str2);
    }
}
