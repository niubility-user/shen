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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double getLastLat() {
        /*
            r9 = this;
            java.lang.String r0 = "SchoolLbs"
            r1 = -4582131145872769024(0xc069000000000000, double:-200.0)
            android.content.SharedPreferences r3 = com.jingdong.common.utils.CommonBase.getJdSharedPreferences()     // Catch: java.lang.Exception -> L2f
            java.lang.String r4 = "school_lbs_lat_key"
            java.lang.String r5 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> L2f
            java.lang.String r3 = r3.getString(r4, r5)     // Catch: java.lang.Exception -> L2f
            double r3 = java.lang.Double.parseDouble(r3)     // Catch: java.lang.Exception -> L2f
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 != 0) goto L3f
            android.content.SharedPreferences r5 = com.jingdong.common.utils.CommonBase.getJdSharedPreferences()     // Catch: java.lang.Exception -> L2d
            java.lang.String r6 = "last_school_lbs_lat_key"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> L2d
            java.lang.String r1 = r5.getString(r6, r1)     // Catch: java.lang.Exception -> L2d
            double r3 = java.lang.Double.parseDouble(r1)     // Catch: java.lang.Exception -> L2d
            goto L3f
        L2d:
            r1 = move-exception
            goto L33
        L2f:
            r3 = move-exception
            r7 = r1
            r1 = r3
            r3 = r7
        L33:
            boolean r2 = com.jingdong.corelib.utils.Log.D
            if (r2 == 0) goto L3f
            java.lang.String r2 = "pare string to double in exception at method getLastLat\n"
            com.jingdong.corelib.utils.Log.d(r0, r2)
            r1.printStackTrace()
        L3f:
            boolean r1 = com.jingdong.corelib.utils.Log.D
            if (r1 == 0) goto L5c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "last lat is valid and value ="
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = "\n"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.jingdong.corelib.utils.Log.d(r0, r1)
        L5c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.lbs.SchoolLbs.getLastLat():double");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0043  */
    @Override // com.jingdong.common.lbs.ISchoolLbs.ISchoolLbsSupporter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double getLastLng() {
        /*
            r9 = this;
            java.lang.String r0 = "SchoolLbs"
            r1 = -4582131145872769024(0xc069000000000000, double:-200.0)
            android.content.SharedPreferences r3 = com.jingdong.common.utils.CommonBase.getJdSharedPreferences()     // Catch: java.lang.Exception -> L2f
            java.lang.String r4 = "school_lbs_lng_key"
            java.lang.String r5 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> L2f
            java.lang.String r3 = r3.getString(r4, r5)     // Catch: java.lang.Exception -> L2f
            double r3 = java.lang.Double.parseDouble(r3)     // Catch: java.lang.Exception -> L2f
            int r5 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r5 != 0) goto L3f
            android.content.SharedPreferences r5 = com.jingdong.common.utils.CommonBase.getJdSharedPreferences()     // Catch: java.lang.Exception -> L2d
            java.lang.String r6 = "last_school_lbs_lng_key"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Exception -> L2d
            java.lang.String r1 = r5.getString(r6, r1)     // Catch: java.lang.Exception -> L2d
            double r3 = java.lang.Double.parseDouble(r1)     // Catch: java.lang.Exception -> L2d
            goto L3f
        L2d:
            r1 = move-exception
            goto L33
        L2f:
            r3 = move-exception
            r7 = r1
            r1 = r3
            r3 = r7
        L33:
            boolean r2 = com.jingdong.corelib.utils.Log.D
            if (r2 == 0) goto L3f
            java.lang.String r2 = "pare string to double in exception at method getLastLng\n"
            com.jingdong.corelib.utils.Log.d(r0, r2)
            r1.printStackTrace()
        L3f:
            boolean r1 = com.jingdong.corelib.utils.Log.D
            if (r1 == 0) goto L5c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "last lng is valid and value ="
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = "\n"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.jingdong.corelib.utils.Log.d(r0, r1)
        L5c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.lbs.SchoolLbs.getLastLng():double");
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
