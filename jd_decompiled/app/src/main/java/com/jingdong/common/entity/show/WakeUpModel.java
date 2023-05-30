package com.jingdong.common.entity.show;

import com.jingdong.common.entity.WakeUpEntity;
import java.util.List;

/* loaded from: classes5.dex */
public class WakeUpModel {
    public List<WakeUpEntity> allowed_app_list;
    public boolean enable_gps;
    public boolean explicit_wakeup;
    public List<String> gps_brand_blacklist;
    public List<String> gps_device_blacklist;
    public boolean passive_wakeup;
    public int timerly_wakeup_interval;
    public int wakeup_interval;
}
