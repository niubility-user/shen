package com.jingdong.common.lbs;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.lbs.ISchoolLbs;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class SchoolLbs implements ISchoolLbs.ISchoolLbsSetting, ISchoolLbs.ISchoolLbsSupporter {
    private static final String LAST_LAT_KEY = "last_school_lbs_lat_key";
    private static final String LAST_LNG_KEY = "last_school_lbs_lng_key";
    private static final String LAST_TIME_STAMP_KEY = "last_time_located_key";
    private static final String LAT_KEY = "school_lbs_lat_key";
    private static final String LNG_KEY = "school_lbs_lng_key";
    private static final long LOCATE_TIME_LIMIT = 600000;
    private static final String TAG = "SchoolLbs";
    private static final String TIME_STAMP_KEY = "time_located_key";
    private double mLat = -200.0d;
    private double mLng = -200.0d;
    private long mTimestamp;

    private synchronized void updateLat(double d) {
        String string = CommonBase.getJdSharedPreferences().getString(LAT_KEY, String.valueOf(-200.0d));
        CommonBase.getJdSharedPreferences().edit().putString(LAST_LAT_KEY, string).apply();
        CommonBase.getJdSharedPreferences().edit().putString(LAT_KEY, String.valueOf(d)).apply();
        if (Log.D) {
            Log.d(TAG, "Last lat is updated and lastLat = " + string + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
    }

    private synchronized void updateLng(double d) {
        String string = CommonBase.getJdSharedPreferences().getString(LNG_KEY, String.valueOf(-200.0d));
        CommonBase.getJdSharedPreferences().edit().putString(LAST_LNG_KEY, string).apply();
        CommonBase.getJdSharedPreferences().edit().putString(LNG_KEY, String.valueOf(d)).apply();
        if (Log.D) {
            Log.d(TAG, "Last lng is updated and lastLng = " + string + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
    }

    private synchronized void updateTimestamp() {
        this.mTimestamp = System.currentTimeMillis();
        long j2 = CommonBase.getJdSharedPreferences().getLong(TIME_STAMP_KEY, 0L);
        CommonBase.getJdSharedPreferences().edit().putLong(LAST_TIME_STAMP_KEY, j2).apply();
        CommonBase.getJdSharedPreferences().edit().putLong(TIME_STAMP_KEY, this.mTimestamp).apply();
        if (Log.D) {
            Log.d(TAG, "Last timeStamp is updated and lastTime = " + j2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
    }

    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    public double getCurrentLat() {
        if (Log.D) {
            Log.d(TAG, "current lat = " + this.mLat + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return this.mLat;
    }

    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    public double getCurrentLng() {
        if (Log.D) {
            Log.d(TAG, "current lng = " + this.mLng + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return this.mLng;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double getLastLat() {
        Exception e2;
        double d;
        try {
            d = Double.parseDouble(CommonBase.getJdSharedPreferences().getString(LAT_KEY, String.valueOf(-200.0d)));
            if (d == -200.0d) {
                try {
                    d = Double.parseDouble(CommonBase.getJdSharedPreferences().getString(LAST_LAT_KEY, String.valueOf(-200.0d)));
                } catch (Exception e3) {
                    e2 = e3;
                    if (Log.D) {
                        Log.d(TAG, "pare string to double in exception at method getLastLat\n");
                        e2.printStackTrace();
                    }
                    if (Log.D) {
                    }
                    return d;
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            d = -200.0d;
        }
        if (Log.D) {
            Log.d(TAG, "last lat is valid and value =" + d + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double getLastLng() {
        Exception e2;
        double d;
        try {
            d = Double.parseDouble(CommonBase.getJdSharedPreferences().getString(LNG_KEY, String.valueOf(-200.0d)));
            if (d == -200.0d) {
                try {
                    d = Double.parseDouble(CommonBase.getJdSharedPreferences().getString(LAST_LNG_KEY, String.valueOf(-200.0d)));
                } catch (Exception e3) {
                    e2 = e3;
                    if (Log.D) {
                        Log.d(TAG, "pare string to double in exception at method getLastLng\n");
                        e2.printStackTrace();
                    }
                    if (Log.D) {
                    }
                    return d;
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            d = -200.0d;
        }
        if (Log.D) {
            Log.d(TAG, "last lng is valid and value =" + d + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return d;
    }

    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    public long getLastLocateTime() {
        long j2 = CommonBase.getJdSharedPreferences().getLong(TIME_STAMP_KEY, 0L);
        return j2 == 0 ? CommonBase.getJdSharedPreferences().getLong(LAST_TIME_STAMP_KEY, 0L) : j2;
    }

    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    public long getLocateTime() {
        return this.mTimestamp;
    }

    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSetting
    public synchronized void setGeo(double d, double d2) {
        updateTimestamp();
        this.mLat = d;
        this.mLng = d2;
        updateLat(d);
        updateLng(d2);
        if (Log.D) {
            Log.d(TAG, "geo is updated and lat = " + this.mLng + ";lon = " + d + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
    }

    public boolean shouldLocate() {
        return Math.abs(System.currentTimeMillis() - getLastLocateTime()) > 600000;
    }
}
