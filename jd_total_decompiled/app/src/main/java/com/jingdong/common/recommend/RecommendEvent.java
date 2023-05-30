package com.jingdong.common.recommend;

import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class RecommendEvent extends BaseEvent {
    public static final String onInterActiveEnd = "onInterActiveEnd";
    public HashMap<String, Object> param;

    public RecommendEvent() {
        this.param = new HashMap<>();
    }

    public RecommendEvent(String str) {
        super(str);
        this.param = new HashMap<>();
    }
}
