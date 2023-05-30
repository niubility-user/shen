package com.jingdong.app.mall.navigationbar.entity;

import java.util.List;

/* loaded from: classes4.dex */
public class NavigationBubbleResult {
    public String code;
    public List<NavigationBubbleEntity> result;
    public boolean success;

    public String toString() {
        return "NavigationBubbleResult{code='" + this.code + "', success=" + this.success + ", result=" + this.result + '}';
    }
}
