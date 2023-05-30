package com.jingdong.common.lbs;

/* loaded from: classes5.dex */
public interface ISchoolLbs {

    /* loaded from: classes5.dex */
    public interface ISchoolLbsSetting {
        void setGeo(double d, double d2);
    }

    /* loaded from: classes5.dex */
    public interface ISchoolLbsSupporter {
        public static final double INVALIDLAT = -200.0d;
        public static final double INVALIDLNG = -200.0d;

        double getCurrentLat();

        double getCurrentLng();

        double getLastLat();

        double getLastLng();

        long getLastLocateTime();

        long getLocateTime();
    }
}
