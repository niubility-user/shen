package com.jingdong.common.entity.personal;

import com.jingdong.common.network.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class HomeMoreResponse extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5303173461812687998L;
    public long clientQueryTime;
    public ArrayList<HomeConfig> jdHomeMore;
}
