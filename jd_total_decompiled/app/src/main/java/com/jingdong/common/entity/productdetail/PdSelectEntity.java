package com.jingdong.common.entity.productdetail;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class PdSelectEntity {
    public int selectLength;
    public String selectText;
    public int status = 0;
    public Map<Integer, PDStylePropertyEntity> selectItems = new LinkedHashMap();
}
