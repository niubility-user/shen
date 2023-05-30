package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.alibaba.fastjson.JSONReader;
import com.jd.framework.json.TypeToken;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.oklog.OKLog;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class DeepLinkArMakeupHelper {
    public static final String EXTRA_AREA = "area";
    public static final String EXTRA_ARMAKEUP_DATA = "arMakeupData";
    public static final String EXTRA_SKU_ID = "skuId";
    private static final String TAG = "DeepLinkArMakeupHelper";

    /* loaded from: classes5.dex */
    public static class ArMakeupColor implements Parcelable {
        public static final Parcelable.Creator<ArMakeupColor> CREATOR = new Parcelable.Creator<ArMakeupColor>() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkArMakeupHelper.ArMakeupColor.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ArMakeupColor createFromParcel(Parcel parcel) {
                return new ArMakeupColor(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ArMakeupColor[] newArray(int i2) {
                return new ArMakeupColor[i2];
            }
        };
        public int alpha;
        public String colorNum;
        public String colorValue;
        public int glossiness;
        public String imgUrl;
        public String name;
        public long sku;

        public ArMakeupColor() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeLong(this.sku);
            parcel.writeString(this.name);
            parcel.writeString(this.colorNum);
            parcel.writeString(this.colorValue);
            parcel.writeInt(this.alpha);
            parcel.writeInt(this.glossiness);
            parcel.writeString(this.imgUrl);
        }

        public ArMakeupColor(long j2, String str, String str2, String str3, int i2, int i3, String str4) {
            this.sku = j2;
            this.name = str;
            this.colorNum = str2;
            this.colorValue = str3;
            this.alpha = i2;
            this.glossiness = i3;
            this.imgUrl = str4;
        }

        protected ArMakeupColor(Parcel parcel) {
            this.sku = parcel.readLong();
            this.name = parcel.readString();
            this.colorNum = parcel.readString();
            this.colorValue = parcel.readString();
            this.alpha = parcel.readInt();
            this.glossiness = parcel.readInt();
            this.imgUrl = parcel.readString();
        }
    }

    /* loaded from: classes5.dex */
    public static class ArMakeupData implements Parcelable {
        public static final Parcelable.Creator<ArMakeupData> CREATOR = new Parcelable.Creator<ArMakeupData>() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkArMakeupHelper.ArMakeupData.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ArMakeupData createFromParcel(Parcel parcel) {
                return new ArMakeupData(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ArMakeupData[] newArray(int i2) {
                return new ArMakeupData[i2];
            }
        };
        public String color;
        public ArrayList<ArMakeupColor> colors;

        public ArMakeupData() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.color);
            parcel.writeTypedList(this.colors);
        }

        public ArMakeupData(String str, ArrayList<ArMakeupColor> arrayList) {
            this.color = str;
            this.colors = arrayList;
        }

        protected ArMakeupData(Parcel parcel) {
            this.color = parcel.readString();
            this.colors = parcel.createTypedArrayList(ArMakeupColor.CREATOR);
        }
    }

    public static void callBundleMethod(String str, Class[] clsArr, Object[] objArr) {
        Method declaredMethod;
        if (!isBundleSwitchOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "bundle-armakeup switch is close ");
                return;
            }
            return;
        }
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.armakeup.PublicInterface");
            if (OKLog.D) {
                OKLog.d(TAG, "clz=====" + loadClass);
            }
            if (loadClass == null) {
                return;
            }
            try {
                if (clsArr != null) {
                    declaredMethod = loadClass.getDeclaredMethod(str, clsArr);
                } else {
                    declaredMethod = loadClass.getDeclaredMethod(str, new Class[0]);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "method=====" + declaredMethod);
                }
                if (declaredMethod == null) {
                    return;
                }
                declaredMethod.invoke(null, objArr);
            } catch (NoSuchMethodException e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
            } catch (InvocationTargetException e3) {
                if (OKLog.D) {
                    OKLog.e(TAG, e3);
                }
            }
        } catch (ClassNotFoundException e4) {
            if (OKLog.D) {
                OKLog.e(TAG, e4);
            }
        } catch (IllegalAccessException e5) {
            if (OKLog.D) {
                OKLog.e(TAG, e5);
            }
        }
    }

    public static void cleanCache(Context context) {
        callBundleMethod("cleanCache", new Class[]{Context.class}, new Object[]{context});
    }

    private static boolean isBundleSwitchOpen() {
        return true;
    }

    public static void preload(Context context) {
        callBundleMethod(HttpDnsConfig.PREDOWNLOAD_PARAMS, new Class[]{Context.class}, new Object[]{context});
    }

    public static void startArMakeup(Context context, long j2, String str) {
        startArMakeup(context, j2, str, "");
    }

    public static void startArMakeup(Context context, long j2, String str, String str2) {
        try {
            ArMakeupData arMakeupData = (ArMakeupData) new JSONReader(new StringReader(str)).readObject(new TypeToken<ArMakeupData>() { // from class: com.jingdong.common.deeplinkhelper.DeepLinkArMakeupHelper.1
            }.getType());
            startArMakeup(context, j2, arMakeupData.color, arMakeupData.colors, str2);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "start bundle-armakeup error : " + e2.getMessage());
            }
            OKLog.e(TAG, e2);
        }
    }

    public static void startArMakeup(Context context, long j2, String str, ArrayList<ArMakeupColor> arrayList, String str2) {
        if (context != null && arrayList != null && arrayList.size() != 0) {
            Bundle bundle = new Bundle();
            bundle.putLong("skuId", j2);
            bundle.putString("area", str2);
            bundle.putParcelable(EXTRA_ARMAKEUP_DATA, new ArMakeupData(str, arrayList));
            startArMakeup(context, bundle);
        } else if (OKLog.D) {
            OKLog.d(TAG, "start bundle-armakeup error : param is null");
        }
    }

    public static void startArMakeup(Context context, Bundle bundle) {
        if (OKLog.D) {
            OKLog.d(TAG, "start bundle-armakeup");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "armakeupactivity", bundle);
    }
}
