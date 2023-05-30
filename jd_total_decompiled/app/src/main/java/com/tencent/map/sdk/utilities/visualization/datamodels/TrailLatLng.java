package com.tencent.map.sdk.utilities.visualization.datamodels;

/* loaded from: classes9.dex */
public class TrailLatLng {
    private TimeLatLng[] trailData;

    public TrailLatLng(TimeLatLng[] timeLatLngArr) {
        boolean z;
        if (timeLatLngArr == null || timeLatLngArr.length < 2) {
            this.trailData = new TimeLatLng[0];
            return;
        }
        int i2 = 0;
        while (true) {
            z = true;
            if (i2 >= timeLatLngArr.length - 1) {
                break;
            }
            int time = timeLatLngArr[i2].getTime();
            i2++;
            if (time >= timeLatLngArr[i2].getTime()) {
                z = false;
                break;
            }
        }
        if (z) {
            this.trailData = timeLatLngArr;
        } else {
            this.trailData = new TimeLatLng[0];
        }
    }

    public TimeLatLng[] getTrailData() {
        return this.trailData;
    }
}
