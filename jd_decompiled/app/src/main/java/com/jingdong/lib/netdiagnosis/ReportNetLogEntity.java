package com.jingdong.lib.netdiagnosis;

import android.os.Build;
import com.jingdong.jdsdk.utils.FormatUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.Date;
import java.util.List;

/* loaded from: classes14.dex */
public class ReportNetLogEntity {
    public List<PingResultEntity> ping;
    public String mode = "-1";
    public String reportTime = FormatUtils.formatDate(new Date());
    public String sdkVersion = Build.VERSION.SDK_INT + "";
    public String operator = NetUtils.getNetworkOperator();
    public String netType = NetUtils.getNetworkType();
}
