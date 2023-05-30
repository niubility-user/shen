package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Detail;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes9.dex */
public class eh implements TrafficEvent {
    private static final String b = "yyyy/MM/dd HH\u65f6";

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f16477c = {"\u4ea4\u901a\u4e8b\u6545", "\u4ea4\u901a\u7ba1\u5236", "\u9053\u8def\u65bd\u5de5", "\u8def\u4e0a\u969c\u788d\u7269", "\u6d3b\u52a8", "\u6076\u52a3\u5929\u6c14", "\u707e\u5bb3", "\u62e5\u5835", "\u68c0\u67e5", "\u4e00\u822c\u4e8b\u6545", "\u79ef\u6c34", "\u5176\u4ed6\u4e8b\u4ef6"};
    private static final String[] d = {"\u53d1\u751f", "\u51fa\u73b0", "\u6709", "\u6709", "\u6709", "\u51fa\u73b0", "\u6709", "\u51fa\u73b0", "\u6709", "\u53d1\u751f", "\u6709", "\u6709"};
    private Detail a;

    public eh(Detail detail) {
        this.a = detail;
    }

    public Detail a() {
        return this.a;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public String getDescription() {
        int i2 = this.a.basic.type;
        String str = i2 > f16477c.length ? d[r1.length - 1] : d[i2 - 1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b, Locale.US);
        return simpleDateFormat.format(new Date(this.a.basic.start_time * 1000)) + " - " + simpleDateFormat.format(new Date(this.a.basic.end_time * 1000)) + '\uff0c' + getRoadName() + str + getType();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public int getEndTime() {
        return this.a.basic.end_time;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public String getMessage() {
        return this.a.basic.message;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public String getRoadName() {
        return this.a.basic.roadname;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public String getSource() {
        return this.a.basic.source;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public int getStartTime() {
        return this.a.basic.start_time;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public String getType() {
        int i2 = this.a.basic.type;
        String[] strArr = f16477c;
        return i2 > strArr.length ? strArr[strArr.length - 1] : strArr[i2 - 1];
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent
    public int getUpdateTime() {
        return this.a.basic.update_time;
    }
}
