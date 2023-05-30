package com.jingdong.app.mall.navigationbar.entity;

import com.jingdong.common.unification.navigationbar.NavigationFrequencyRuleEntity;
import java.util.List;

/* loaded from: classes4.dex */
public class NavigationFrequencyRuleResult {
    public String code;
    public List<NavigationFrequencyRuleEntity> result;
    public boolean success;

    public String toString() {
        return "NavigationFrequencyRuleResult{code='" + this.code + "', success=" + this.success + ", result=" + this.result + '}';
    }
}
