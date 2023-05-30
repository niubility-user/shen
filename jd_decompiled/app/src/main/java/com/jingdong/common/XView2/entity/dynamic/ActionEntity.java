package com.jingdong.common.XView2.entity.dynamic;

import com.jd.framework.json.JDJSONObject;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ActionEntity {
    public String condition;
    public String name;
    public JDJSONObject params = new JDJSONObject();
    public ArrayList<ActionEntity> success = new ArrayList<>();
    public ArrayList<ActionEntity> fail = new ArrayList<>();
}
