package com.jingdong.jdsdk.depend;

import com.jingdong.common.depend.DependImpl;

@Deprecated
/* loaded from: classes14.dex */
public class DependUtil {
    private static DependUtil dependUtil;
    private Idepend depend = new DependImpl();

    private DependUtil() {
    }

    @Deprecated
    public static DependUtil getInstance() {
        if (dependUtil == null) {
            synchronized (DependUtil.class) {
                if (dependUtil == null) {
                    dependUtil = new DependUtil();
                }
            }
        }
        return dependUtil;
    }

    @Deprecated
    public Idepend getDepend() {
        return this.depend;
    }

    @Deprecated
    public void setDepend(Idepend idepend) {
    }
}
