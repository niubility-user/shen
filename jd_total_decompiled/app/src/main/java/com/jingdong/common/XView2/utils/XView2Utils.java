package com.jingdong.common.XView2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.flexcube.pool.a;
import com.jd.lib.un.utils.UnTimeUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.PopSceneEntity;
import com.jingdong.common.XView2.entity.PreDownLoadResourceEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.LayerTypeManager;
import com.jingdong.common.XView2.layer.flexcube.aide.XViewCountDownViewAide;
import com.jingdong.common.XView2.layer.flexcube.aide.XViewLottieAide;
import com.jingdong.common.XView2.layer.flexcube.aide.XViewVideoViewAide;
import com.jingdong.common.XView2.layer.flexcube.aide.XViewWebviewAide;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.gray.JDGrayModelUtils;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.core.c;
import com.jingdong.sdk.platform.business.personal.R2;
import de.greenrobot.event.EventBus;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import jpbury.t;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XView2Utils {
    public static volatile int H_HEIGHT = 0;
    public static volatile int H_WIDTH = 0;
    private static final int INTERVAL = 500;
    private static final int MAX_SIZE = 128;
    public static JDDisplayImageOptions imageNullOptions;
    public static Context mContext;
    public static double renderRatio;
    private static long sPreClickTime;
    public static Drawable sTransparentDrawable;
    private static Pattern colorPattern = Pattern.compile("^#([a-fA-F0-9]{2})?[a-fA-F0-9]{6}$");
    private static Pattern floatPattern = Pattern.compile("^[-\\+]?[.\\d]*$");
    private static boolean DEBUG = true;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static String integerRegex = "^[-\\+]?[\\d]*$";
    private static String doubleRegex = "^[-\\+]?[.\\d]*$";
    private static long currentTime = 0;
    private static SparseIntArray mCacheSize = new SparseIntArray();
    private static volatile int[] mCacheArr = new int[128];
    private static ReadWriteLock mSizeLock = new ReentrantReadWriteLock();

    /* loaded from: classes5.dex */
    public interface OnClickEventListener {
        void onClick(View view);
    }

    /* loaded from: classes5.dex */
    public interface OnDataFilterCondition<T> {
        boolean filter(T t);
    }

    static {
        screenWidthChanged(getScreen().x, getScreen().y);
        sTransparentDrawable = new ColorDrawable(0);
        imageNullOptions = new JDDisplayImageOptions().resetViewBeforeLoading(false).showImageOnFail(sTransparentDrawable).showImageOnLoading(sTransparentDrawable).showImageForEmptyUri(sTransparentDrawable);
        renderRatio = 0.0d;
        sPreClickTime = 0L;
    }

    public static double add(double d, double d2) {
        return new BigDecimal(Double.toString(d)).add(new BigDecimal(Double.toString(d2))).setScale(2, 4).doubleValue();
    }

    public static boolean canWebViewLayerPreLoaded(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        if (layersEntity == null) {
            return false;
        }
        if (layersEntity.renderType == 4 && (renderDataEntity = layersEntity.renderData) != null && isConvertBool(renderDataEntity.bufEnable)) {
            return true;
        }
        if (layersEntity.renderType == 6 && !isListEmpty(layersEntity.preDownLoadList)) {
            Iterator<PreDownLoadResourceEntity> it = layersEntity.preDownLoadList.iterator();
            while (it.hasNext()) {
                if (it.next().type == 3) {
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "webview preLoad");
                    return true;
                }
            }
        }
        return false;
    }

    public static void clickJump(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (isHttpOrHttps(Uri.parse(str).getScheme())) {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            JumpUtil.execJumpByDes("m", context, bundle);
            return;
        }
        JumpUtils.clickJump(context, str);
    }

    private static void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (Exception unused) {
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int compareVersion(String str, String str2) {
        if (str == null || str2 == null || !str.matches("^[1-9][0-9]*(\\.[0-9]+){2,}$") || !str2.matches("^[1-9][0-9]*(\\.[0-9]+){2,}$")) {
            return -2;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        int i2 = 0;
        for (int i3 = 0; i3 < min; i3++) {
            i2 = split[i3].length() - split2[i3].length();
            if (i2 != 0 || (i2 = split[i3].compareTo(split2[i3])) != 0) {
                break;
            }
        }
        if (i2 == 0) {
            i2 = split.length - split2.length;
        }
        return i2 == 0 ? i2 : i2 > 0 ? 1 : -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T convert(Object obj) {
        return obj;
    }

    public static void convertArgs(JSONObject jSONObject, String str, JSONObject jSONObject2) {
        if (jSONObject == null || TextUtils.isEmpty(str) || jSONObject2 == null) {
            return;
        }
        try {
            if (jSONObject2.length() > 0) {
                JSONObject jSONObject3 = new JSONObject();
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject3.put(next, jSONObject2.opt(next));
                }
                jSONObject.put(str, jSONObject3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static double div(double d, double d2, int i2) {
        if (i2 >= 0 && d2 > 0.0d) {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(Double.toString(d2)), i2, 4).doubleValue();
        }
        throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }

    public static String encodeUrl(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("");
        if (!TextUtils.isEmpty(str) && str.indexOf("params=") >= 0) {
            spannableStringBuilder.append((CharSequence) str.substring(0, str.indexOf("params=") + 7));
            String substring = str.substring(str.indexOf("params=") + 7, str.length());
            if (substring.startsWith("{%22") || substring.startsWith("%7B%22") || substring.startsWith("%7b%22")) {
                return str;
            }
            try {
                substring = URLEncoder.encode(substring, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            return spannableStringBuilder.append((CharSequence) substring).toString();
        }
        return str;
    }

    public static boolean findHitH5Page(String str, FragmentManager fragmentManager) {
        if (fragmentManager != null) {
            Iterator<Fragment> it = fragmentManager.getFragments().iterator();
            while (it.hasNext()) {
                if (isHitH5Fragment(str, it.next())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static int findMax(int[] iArr) {
        int i2 = Integer.MIN_VALUE;
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    public static int findMin(int[] iArr) {
        int i2 = Integer.MAX_VALUE;
        for (int i3 : iArr) {
            if (i3 < i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    static int get1335SizeValue(int i2, int i3) {
        return (int) (((i3 * i2) / 1335.0f) + 0.5f);
    }

    static int get750SizeValue(int i2, int i3) {
        return (int) (((i3 * i2) / 750.0f) + 0.5f);
    }

    static int get750SizeValue(int i2, int i3, int i4) {
        int min = Math.min(i3, (int) R2.attr.miaoShaPriceTextColor);
        float f2 = i4;
        if (f2 > 0.0f && f2 / min < 1.4f) {
            min = (int) (f2 / 1.6f);
        }
        return (int) (((min * i2) / 750.0f) + 0.5f);
    }

    public static int getAlign(String str, String str2) {
        int i2;
        int i3 = 0;
        if ("1".equals(str)) {
            i2 = 3;
        } else if ("2".equals(str)) {
            i2 = 1;
        } else {
            i2 = "3".equals(str) ? 5 : 0;
        }
        if ("1".equals(str2)) {
            i3 = 48;
        } else if ("2".equals(str2)) {
            i3 = 16;
        } else if ("3".equals(str2)) {
            i3 = 80;
        }
        return (i2 <= 0 || i3 <= 0) ? i2 > 0 ? i2 : i3 : i2 | i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<XViewConfigEntity.LayersEntity> getAllLayersByTargetName(Context context, XViewConfigEntity xViewConfigEntity, Fragment fragment) {
        String str;
        String str2;
        if (fragment != null && context != null && xViewConfigEntity != null && !TextUtils.isEmpty(fragment.getClass().getName())) {
            Iterator<XViewConfigEntity.TargetsEntity> it = xViewConfigEntity.targets.iterator();
            boolean z = false;
            while (it.hasNext()) {
                XViewConfigEntity.TargetsEntity next = it.next();
                if (!TextUtils.isEmpty(next.targetName)) {
                    String str3 = next.targetName;
                    if (TextUtils.isEmpty(str3)) {
                        continue;
                    } else {
                        try {
                            JSONObject jSONObject = new JSONObject(str3);
                            if (jSONObject.length() > 0) {
                                z = true;
                                str = jSONObject.optString("activity");
                                try {
                                    str2 = jSONObject.has(XView2Constants.FRAGMENT) ? jSONObject.optString(XView2Constants.FRAGMENT) : null;
                                } catch (JSONException unused) {
                                    str2 = null;
                                    z = false;
                                    if (z) {
                                    }
                                    if (TextUtils.isEmpty(str3)) {
                                    }
                                }
                            } else {
                                str2 = null;
                                str = null;
                            }
                        } catch (JSONException unused2) {
                            str = null;
                        }
                        if (z) {
                            str3 = str;
                        }
                        if ((TextUtils.isEmpty(str3) && str3.equals(context.getClass().getName()) && !TextUtils.isEmpty(str2) && str2.equals(fragment.getClass().getName())) || isHitH5Fragment(str3, fragment)) {
                            return next.layers;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static RecyclerView getAllRecyclerViews(SoftReference<Activity> softReference) {
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return getOutRecyclerView(softReference.get().getWindow().getDecorView().findViewById(16908290));
    }

    public static int getAlphaColor(String str, float f2) {
        if (isColorString(str) && str.length() == 7) {
            String[] split = str.split("#");
            return Color.parseColor("#" + Integer.toHexString((int) (f2 * 255.0f)) + split[1]);
        }
        return -16777216;
    }

    public static byte[] getAssertsFile(SoftReference<Activity> softReference, String str) {
        BufferedInputStream bufferedInputStream;
        try {
            InputStream open = softReference.get().getAssets().open(str);
            if (open == null) {
                return null;
            }
            try {
                bufferedInputStream = new BufferedInputStream(open);
            } catch (IOException unused) {
                bufferedInputStream = null;
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = null;
            }
            try {
                byte[] bArr = new byte[bufferedInputStream.available()];
                bufferedInputStream.read(bArr);
                try {
                    bufferedInputStream.close();
                } catch (Exception unused2) {
                }
                return bArr;
            } catch (IOException unused3) {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception unused4) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (Exception unused5) {
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAssetsString(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = JdSdk.getInstance().getApplicationContext().getResources().getAssets().open(str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } catch (Exception unused) {
                bufferedReader = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Exception unused2) {
            bufferedReader = null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            inputStream = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine.trim());
                } else {
                    close(inputStream);
                    close(bufferedReader);
                    String sb2 = sb.toString();
                    close(inputStream);
                    close(bufferedReader);
                    return sb2;
                }
            }
        } catch (Exception unused3) {
            inputStream2 = inputStream;
            close(inputStream2);
            close(bufferedReader);
            return "";
        } catch (Throwable th4) {
            th = th4;
            close(inputStream);
            close(bufferedReader);
            throw th;
        }
    }

    public static boolean getBooleanFromSp(String str, boolean z) {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences != null) {
            return jdSharedPreferences.getBoolean(str, z);
        }
        return false;
    }

    public static String getContainerBgColor(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() == 8) {
            return "#" + str.substring(6, 8) + str.substring(0, 6);
        } else if (str.length() == 6) {
            return "#" + str;
        } else {
            return "";
        }
    }

    static int getDpi750Width(Activity activity, int i2) {
        int min = Math.min(DpiUtil.getAppWidth(activity), (int) R2.attr.miaoShaPriceTextColor);
        float height = DpiUtil.getHeight(activity);
        if (height > 0.0f && height / min < 1.4f) {
            min = (int) (height / 1.6f);
        }
        return (int) (((min * i2) / 750.0f) + 0.5f);
    }

    public static String getFileNameFromPath(String str) {
        int lastIndexOf;
        return (str.endsWith("/") || -1 == (lastIndexOf = str.lastIndexOf("/"))) ? "" : str.substring(lastIndexOf + 1);
    }

    public static String getFileSuffixFromPath(String str) {
        int lastIndexOf;
        return (str.endsWith(OrderISVUtil.MONEY_DECIMAL) || -1 == (lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL))) ? "" : str.substring(lastIndexOf);
    }

    public static <T> void getFilterList(List<T> list, OnDataFilterCondition<T> onDataFilterCondition) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (next != null && onDataFilterCondition.filter(next)) {
                it.remove();
            }
        }
    }

    public static String getFinalLayerId(String str) {
        int length;
        return (TextUtils.isEmpty(str) || str.indexOf("_Preview") == -1 || (length = str.length()) <= 8) ? str : str.substring(0, length - 8);
    }

    public static int getIntFromSp(String str, int i2) {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences != null) {
            return jdSharedPreferences.getInt(str, i2);
        }
        return 0;
    }

    public static long getLongFromSp(String str, long j2) {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences != null) {
            return jdSharedPreferences.getLong(str, j2);
        }
        return 0L;
    }

    public static View getMaxDepthLeafView(View view) {
        if (view instanceof ViewGroup) {
            LinkedList linkedList = new LinkedList();
            linkedList.offer(view);
            View view2 = null;
            while (linkedList.size() > 0) {
                view2 = (View) linkedList.poll();
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        linkedList.offer(viewGroup.getChildAt(i2));
                    }
                }
            }
            return view2;
        }
        return view;
    }

    public static String getModuleName() {
        Intent intent;
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (!(currentMyActivity instanceof Activity) || (intent = ((Activity) currentMyActivity).getIntent()) == null || intent.getExtras() == null) {
            return null;
        }
        return intent.getExtras().getString(JDReactConstant.IntentConstant.MODULE_NAME);
    }

    private static String getModuleVersion(String str) {
        Intent intent;
        IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
        if (!(currentMyActivity instanceof Activity) || (intent = ((Activity) currentMyActivity).getIntent()) == null || intent.getExtras() == null) {
            return null;
        }
        String string = intent.getExtras().getString(JDReactConstant.IntentConstant.MODULE_NAME);
        if (TextUtils.isEmpty(str) || !str.equals(string)) {
            return null;
        }
        String str2 = "Module Name: " + string + "  -  Version: " + intent.getExtras().getString("version");
        return intent.getExtras().getString("version");
    }

    public static RecyclerView getOutRecyclerView(View view) {
        if (view != null && (view instanceof ViewGroup)) {
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            ArrayList arrayList = new ArrayList();
            linkedBlockingQueue.offer((ViewGroup) view);
            while (!linkedBlockingQueue.isEmpty()) {
                while (!linkedBlockingQueue.isEmpty()) {
                    ViewGroup viewGroup = (ViewGroup) linkedBlockingQueue.poll();
                    if (viewGroup instanceof RecyclerView) {
                        return (RecyclerView) viewGroup;
                    }
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt instanceof ViewGroup) {
                            arrayList.add((ViewGroup) childAt);
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    linkedBlockingQueue.addAll(arrayList);
                    arrayList.clear();
                }
            }
            return null;
        }
        return null;
    }

    public static XViewConfigEntity.LayersEntity getPopLayerById(ArrayList<XViewConfigEntity.LayersEntity> arrayList, String str) {
        Iterator<XViewConfigEntity.LayersEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            if (!TextUtils.isEmpty(next.layerId) && next.layerId.equals(str)) {
                return next;
            }
        }
        return null;
    }

    public static ArrayList<RecyclerView> getRecyclerViews(SoftReference<Activity> softReference) {
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return getRecyclerViews(softReference.get().getWindow().getDecorView().findViewById(16908290));
    }

    public static Point getScreen() {
        try {
            if (mContext == null) {
                mContext = JdSdk.getInstance().getApplication();
            }
            WindowManager windowManager = (WindowManager) convert(mContext.getSystemService("window"));
            if (windowManager != null) {
                Point point2 = new Point();
                windowManager.getDefaultDisplay().getSize(point2);
                return point2;
            }
        } catch (Throwable unused) {
        }
        return new Point(DPIUtil.getWidth(), DPIUtil.getHeight());
    }

    public static int getSizeBy750(int i2) {
        int i3;
        try {
            mSizeLock.readLock().lock();
            int i4 = (i2 >= 128 || i2 <= 0) ? mCacheSize.get(i2) : mCacheArr[i2];
            if (i4 > 0) {
                mSizeLock.readLock().unlock();
                return i4;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            mSizeLock.readLock().unlock();
            throw th;
        }
        mSizeLock.readLock().unlock();
        if (SwitchQueryFetcher.getSwitchBooleanValue("sizeAdapter", false)) {
            i3 = get750SizeValue(i2, H_WIDTH, H_HEIGHT);
        } else {
            i3 = get750SizeValue(i2, H_WIDTH);
        }
        try {
            mSizeLock.writeLock().lock();
            if (i2 < 128 && i2 > 0) {
                mCacheArr[i2] = i3;
            } else {
                mCacheSize.put(i2, i3);
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            mSizeLock.writeLock().unlock();
            throw th2;
        }
        mSizeLock.writeLock().unlock();
        return i3;
    }

    public static int getStatusBarHeight(BaseActivity baseActivity) {
        if (baseActivity.isStatusBarTintEnable()) {
            return baseActivity.getStatusHeight();
        }
        return 0;
    }

    public static String getStr(Context context, int i2) {
        return (context == null || i2 == 0) ? "" : context.getResources().getString(i2);
    }

    public static String getStrFromSp(String str, String str2) {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences != null) {
            return jdSharedPreferences.getString(str, str2);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getTargetNameByActivity(SoftReference<Activity> softReference, XViewConfigEntity xViewConfigEntity) {
        String str;
        if (softReference != null && softReference.get() != null && xViewConfigEntity != null && xViewConfigEntity != null && !isListEmpty(xViewConfigEntity.targets)) {
            Iterator<XViewConfigEntity.TargetsEntity> it = xViewConfigEntity.targets.iterator();
            boolean z = false;
            while (it.hasNext()) {
                XViewConfigEntity.TargetsEntity next = it.next();
                String str2 = next.targetName;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.length() > 0) {
                            z = true;
                            str = jSONObject.optString("activity");
                            try {
                                if (jSONObject.has(XView2Constants.FRAGMENT)) {
                                    jSONObject.optString(XView2Constants.FRAGMENT);
                                }
                            } catch (JSONException unused) {
                                z = false;
                                if (z) {
                                }
                                if (isListEmpty(next.layers)) {
                                    continue;
                                } else {
                                    return str2;
                                }
                            }
                        } else {
                            str = null;
                        }
                    } catch (JSONException unused2) {
                        str = null;
                    }
                    if (z) {
                        str2 = str;
                    }
                    if (isListEmpty(next.layers) && ((!TextUtils.isEmpty(str2) && str2.equals(softReference.get().getClass().getName())) || isHitRNPage(str2, softReference) || isH5Page(str2, softReference, null))) {
                        return str2;
                    }
                }
            }
        }
        return "";
    }

    public static int getTextMeasureWidth(Context context, String str, int i2) {
        if (TextUtils.isEmpty(str) || !(context instanceof Activity)) {
            return 0;
        }
        Paint paint = new Paint();
        paint.setTextSize(com.jingdong.sdk.utils.DPIUtil.getWidthByDesignValue750((Activity) context, i2));
        return (int) paint.measureText(str);
    }

    @Deprecated
    public static int getTextViewWidth(TextView textView) {
        Rect rect = new Rect();
        String charSequence = textView.getText().toString();
        textView.getPaint().getTextBounds(charSequence, 0, charSequence.length(), rect);
        return rect.width() + textView.getPaddingLeft() + textView.getPaddingRight();
    }

    public static String getTimeStamp(Date date) {
        if (date != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        return null;
    }

    public static String getUrl(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString("url");
        if (string == null) {
            try {
                URLParamMap map = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
                if (map != null) {
                    string = URLDecoder.decode(map.get((Object) RemoteMessageConst.TO), "utf-8");
                }
            } catch (Exception unused) {
                string = bundle.getString("webUrl");
            }
        }
        if (TextUtils.isEmpty(string)) {
            return string;
        }
        Uri parse = Uri.parse(string);
        return parse.getScheme() + "://" + parse.getHost() + parse.getPath();
    }

    public static int getViewMaxDeep(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return 0;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                int viewMaxDeep = getViewMaxDeep(viewGroup.getChildAt(i3)) + 1;
                if (viewMaxDeep > i2) {
                    i2 = viewMaxDeep;
                }
            }
            return i2;
        }
        return 0;
    }

    public static boolean greaterOrEqualKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static boolean greaterOrEqualN() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isCanDrag(String str) {
        return (TextUtils.isEmpty(str) || "1".equals(str) || "0".equals(str)) ? false : true;
    }

    public static boolean isColorString(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return colorPattern.matcher(str).matches();
    }

    public static boolean isConvertBool(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "1".equals(str);
    }

    public static boolean isDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }

    public static boolean isDouble(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return Pattern.compile(doubleRegex).matcher(str).matches();
    }

    public static boolean isDoubleOrFloat(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return floatPattern.matcher(str).matches();
    }

    public static boolean isFastClick() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - sPreClickTime > 500) {
            sPreClickTime = elapsedRealtime;
            return false;
        }
        return true;
    }

    public static boolean isGlobalLayer(XViewConfigEntity.LayersEntity layersEntity) {
        return SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && layersEntity != null && layersEntity.isGlobalWindow;
    }

    public static boolean isH5Page(String str, SoftReference<Activity> softReference, Fragment fragment) {
        if (isHitH5Fragment(str, fragment)) {
            return true;
        }
        if (softReference == null || softReference.get() == null || !(softReference.get() instanceof FragmentActivity)) {
            return false;
        }
        return findHitH5Page(str, ((FragmentActivity) softReference.get()).getSupportFragmentManager());
    }

    public static boolean isHasAd(Object obj, SoftReference<Activity> softReference) {
        return (softReference == null || softReference.get() == null || obj == null || !XView2Constants.MAIN_FRAME_ACTIVITY_TAG.equalsIgnoreCase(softReference.get().getClass().getSimpleName())) ? false : true;
    }

    public static boolean isHitH5Fragment(String str, Fragment fragment) {
        if ((fragment instanceof CommonMFragment) && !TextUtils.isEmpty(str)) {
            CommonMFragment commonMFragment = (CommonMFragment) fragment;
            if (commonMFragment.getWebEntity() != null && !TextUtils.isEmpty(commonMFragment.getWebEntity().url)) {
                Uri parse = Uri.parse(commonMFragment.getWebEntity().url);
                Uri parse2 = Uri.parse(str.trim());
                if (parse != null && parse2 != null) {
                    if (!TextUtils.isEmpty(parse.getPath())) {
                        if (parse.getPath().equals(parse2.getPath()) && !TextUtils.isEmpty(parse.getHost()) && parse.getHost().equals(parse2.getHost())) {
                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, " h5 hit");
                            return true;
                        }
                    } else if (!TextUtils.isEmpty(parse.getHost()) && parse.getHost().equals(parse2.getHost())) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, " h5 hit1");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isHitRNPage(String str, SoftReference<Activity> softReference) {
        if (softReference == null || softReference.get() == null || TextUtils.isEmpty(str)) {
            return false;
        }
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, " currentModuleName" + getModuleName());
        return (softReference.get() instanceof JDReactNativeBaseCommonActivity) && str.equalsIgnoreCase(getModuleName());
    }

    public static boolean isHitTarget(String str, XViewConfigEntity xViewConfigEntity) {
        if (!TextUtils.isEmpty(str) && xViewConfigEntity != null) {
            Iterator<XViewConfigEntity.TargetsEntity> it = xViewConfigEntity.targets.iterator();
            while (it.hasNext()) {
                XViewConfigEntity.TargetsEntity next = it.next();
                String str2 = next.targetName;
                int i2 = next.targetType;
                if ((i2 == 3 || i2 == 1 || i2 == 2) && !TextUtils.isEmpty(str2) && str2.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isHitTargetCheck(XView2 xView2) {
        if (xView2 == null || xView2.getCurrentFragment() == null) {
            return false;
        }
        Fragment currentFragment = xView2.getCurrentFragment();
        if (isListEmpty(xView2.getCurrentFragmentArray()) || !xView2.getCurrentFragmentArray().contains(currentFragment.getClass().getName())) {
            return xView2 != null && isHitH5Fragment(xView2.getCurrentTargetName(), currentFragment);
        }
        return true;
    }

    public static boolean isHttpOrHttps(String str) {
        return "http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str);
    }

    public static boolean isInRect(float f2, float f3, Rect rect, Rect rect2) {
        boolean z;
        if (rect2 != null) {
            z = f2 >= ((float) rect2.left) && f2 <= ((float) rect2.right) && f3 >= ((float) rect2.top) && f3 <= ((float) rect2.bottom);
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "is close " + z);
        } else {
            z = false;
        }
        return (f2 >= ((float) rect.left) && f2 <= ((float) rect.right) && f3 >= ((float) rect.top) && f3 <= ((float) rect.bottom)) || z;
    }

    public static boolean isInteger(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile(integerRegex).matcher(str).matches();
    }

    public static boolean isJsonArray(Object obj) {
        return obj != null && (obj instanceof JSONArray) && ((JSONArray) obj).length() > 0;
    }

    public static boolean isJsonObject(Object obj) {
        return obj != null && (obj instanceof JSONObject) && ((JSONObject) obj).length() > 0;
    }

    public static boolean isLayerEnterImmediatelyPop(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RuleEntity ruleEntity;
        PopSceneEntity popSceneEntity;
        return (layersEntity == null || (ruleEntity = layersEntity.rule) == null || (popSceneEntity = ruleEntity.popScene) == null || !"1".equals(popSceneEntity.onEnterTargetImmediately)) ? false : true;
    }

    public static boolean isLayerEnterPop(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RuleEntity ruleEntity;
        PopSceneEntity popSceneEntity;
        return (layersEntity == null || (ruleEntity = layersEntity.rule) == null || (popSceneEntity = ruleEntity.popScene) == null || TextUtils.isEmpty(popSceneEntity.onEnterTarget) || !"1".equals(layersEntity.rule.popScene.onEnterTarget)) ? false : true;
    }

    public static boolean isLayerInVisible(BaseLayer baseLayer) {
        return (baseLayer == null || baseLayer.getContainer() == null || baseLayer.getContainer().getVisibility() == 0) ? false : true;
    }

    public static boolean isListEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isMoreThanZero(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isInteger(str) ? Integer.parseInt(str) > 0 : isDoubleOrFloat(str) && Double.parseDouble(str) > 0.0d;
    }

    private static boolean isThisTime(Date date, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(new Date()));
    }

    public static boolean isToday(Date date) {
        return isThisTime(date, "yyyy-MM-dd");
    }

    public static boolean isValid(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                jsonReader.skipValue();
            }
            jsonReader.endObject();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isWidthChanged(int i2) {
        return i2 > 0 && i2 != H_WIDTH;
    }

    public static boolean isWithInTime(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        if (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null || layersEntity.rule == null) {
            return false;
        }
        int i2 = renderDataEntity.bufBeforeDays;
        int i3 = i2 > 0 ? i2 * 60 * 24 * 60 * 1000 : UnTimeUtils.DAY;
        long currentTimeMillis = System.currentTimeMillis();
        XViewConfigEntity.RuleEntity ruleEntity = layersEntity.rule;
        long j2 = ruleEntity.beginTime;
        if (currentTimeMillis > ruleEntity.endTime || currentTimeMillis < j2 - i3) {
            return false;
        }
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "isWithInTime");
        return true;
    }

    public static void logD(Object obj, String str) {
        if (DEBUG) {
            String str2 = "XView2==>" + (obj instanceof String ? obj.toString() : obj.getClass().getSimpleName());
        }
    }

    public static int numCompareTo(String str, String str2) {
        if (isDouble(str) && isDouble(str2)) {
            return Double.compare(Double.parseDouble(str), Double.parseDouble(str2));
        }
        return -2;
    }

    public static String numStrFormat(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (isInteger(str) || isDoubleOrFloat(str)) {
            return new DecimalFormat("0.00").format(Double.parseDouble(str));
        }
        return null;
    }

    public static int percentHeight(float f2) {
        return (int) (H_HEIGHT * f2);
    }

    public static void postOnUiThread(@NotNull BaseRunnable baseRunnable, long j2) {
        sHandler.postDelayed(baseRunnable, j2);
    }

    public static int[] randomNums(int i2, int i3, int i4) {
        boolean z;
        int i5 = (i3 - i2) + 1;
        if (i4 > i5 || i3 < i2) {
            return null;
        }
        int[] iArr = {-1, -1, -1};
        int i6 = 0;
        while (i6 < i4) {
            int nextInt = new Random().nextInt(i5);
            int i7 = 0;
            while (true) {
                if (i7 >= i4) {
                    z = true;
                    break;
                } else if (nextInt == iArr[i7]) {
                    z = false;
                    break;
                } else {
                    i7++;
                }
            }
            if (z) {
                iArr[i6] = nextInt;
                i6++;
            }
        }
        return iArr;
    }

    public static void registerXViewWidget() {
        try {
            a.b().d("xview_webview", new XViewWebviewAide());
            a.b().d("xview_video", new XViewVideoViewAide());
            a.b().d("xview_lottie", new XViewLottieAide());
            a.b().d("xview_counttime", new XViewCountDownViewAide());
        } catch (Exception unused) {
        }
    }

    public static void renderRatio(SoftReference<Activity> softReference, final XView2 xView2) {
        final View findViewById;
        if (softReference == null || softReference.get() == null || (findViewById = softReference.get().getWindow().getDecorView().findViewById(16908290)) == null) {
            return;
        }
        softReference.get().runOnUiThread(new Runnable() { // from class: com.jingdong.common.XView2.utils.XView2Utils.2
            /* JADX WARN: Code restructure failed: missing block: B:224:0x015d, code lost:
                if (r2 == null) goto L241;
             */
            /* JADX WARN: Code restructure failed: missing block: B:239:0x0180, code lost:
                if (r2 == null) goto L241;
             */
            /* JADX WARN: Code restructure failed: missing block: B:240:0x0182, code lost:
                r2.recycle();
             */
            /* JADX WARN: Code restructure failed: missing block: B:241:0x0185, code lost:
                r1.destroyDrawingCache();
             */
            /* JADX WARN: Code restructure failed: missing block: B:242:0x018a, code lost:
                return;
             */
            /* JADX WARN: Removed duplicated region for block: B:221:0x0155  */
            /* JADX WARN: Removed duplicated region for block: B:223:0x015a  */
            /* JADX WARN: Removed duplicated region for block: B:228:0x0163  */
            /* JADX WARN: Removed duplicated region for block: B:230:0x0168  */
            /* JADX WARN: Removed duplicated region for block: B:232:0x016d  */
            /* JADX WARN: Removed duplicated region for block: B:236:0x0178  */
            /* JADX WARN: Removed duplicated region for block: B:238:0x017d  */
            /* JADX WARN: Removed duplicated region for block: B:245:0x0029 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                Bitmap bitmap;
                Bitmap createBitmap;
                XView2 xView22;
                LayerTypeManager layerTypeManager;
                findViewById.buildDrawingCache();
                Bitmap drawingCache = findViewById.getDrawingCache();
                Bitmap bitmap2 = null;
                if (drawingCache != null) {
                    try {
                        if (!drawingCache.isRecycled()) {
                            createBitmap = Bitmap.createBitmap(drawingCache);
                            if (createBitmap != null) {
                                try {
                                    if (!createBitmap.isRecycled()) {
                                        Matrix matrix = new Matrix();
                                        matrix.setScale(0.1f, 0.1f);
                                        bitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true);
                                        if (bitmap2 != null && !bitmap2.isRecycled()) {
                                            int width = bitmap2.getWidth();
                                            int height = bitmap2.getHeight();
                                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "bimap width " + width);
                                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "bimap height " + height);
                                            float f2 = 0.0f;
                                            float f3 = 0.0f;
                                            for (int i2 = 0; i2 < width; i2++) {
                                                for (int i3 = 0; i3 < height; i3++) {
                                                    int pixel = bitmap2.getPixel(i2, i3);
                                                    Color.alpha(pixel);
                                                    int red = Color.red(pixel);
                                                    int green = Color.green(pixel);
                                                    int blue = Color.blue(pixel);
                                                    if ((red > 238 && green > 238 && blue > 238) || (green < 45 && red < 45 && blue < 45)) {
                                                        f2 += 1.0f;
                                                    }
                                                    f3 += 1.0f;
                                                }
                                            }
                                            c cVar = XViewLogPrint.JDXLog;
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("countfinal ");
                                            float f4 = f2 / f3;
                                            sb.append(f4);
                                            cVar.e(XView2Constants.TAG, sb.toString());
                                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "hitCount " + f2 + " totalCount " + f3);
                                            if (f3 != 0.0f) {
                                                XView2Utils.renderRatio = f4;
                                            }
                                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "renderRatio " + XView2Utils.renderRatio);
                                        }
                                    }
                                } catch (OutOfMemoryError e2) {
                                    e = e2;
                                    Bitmap bitmap3 = createBitmap;
                                    bitmap = bitmap2;
                                    bitmap2 = bitmap3;
                                    try {
                                        XView2Utils.renderRatio = 0.0d;
                                        e.printStackTrace();
                                        xView22 = xView2;
                                        if (xView22 != null && (layerTypeManager = xView22.mLayerTypeManager) != null) {
                                            layerTypeManager.reportException(1, "renderRatioException", e.getMessage());
                                        }
                                        if (bitmap2 != null) {
                                            bitmap2.recycle();
                                        }
                                        if (bitmap != null) {
                                            bitmap.recycle();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        if (bitmap2 != null) {
                                            bitmap2.recycle();
                                        }
                                        if (bitmap != null) {
                                            bitmap.recycle();
                                        }
                                        if (drawingCache != null) {
                                            drawingCache.recycle();
                                        }
                                        findViewById.destroyDrawingCache();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    Bitmap bitmap4 = createBitmap;
                                    bitmap = bitmap2;
                                    bitmap2 = bitmap4;
                                    if (bitmap2 != null) {
                                    }
                                    if (bitmap != null) {
                                    }
                                    if (drawingCache != null) {
                                    }
                                    findViewById.destroyDrawingCache();
                                    throw th;
                                }
                            }
                            if (createBitmap != null) {
                                createBitmap.recycle();
                            }
                            if (bitmap2 != null) {
                                bitmap2.recycle();
                            }
                        }
                    } catch (OutOfMemoryError e3) {
                        e = e3;
                        bitmap = null;
                        XView2Utils.renderRatio = 0.0d;
                        e.printStackTrace();
                        xView22 = xView2;
                        if (xView22 != null) {
                            layerTypeManager.reportException(1, "renderRatioException", e.getMessage());
                        }
                        if (bitmap2 != null) {
                        }
                        if (bitmap != null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bitmap = null;
                        if (bitmap2 != null) {
                        }
                        if (bitmap != null) {
                        }
                        if (drawingCache != null) {
                        }
                        findViewById.destroyDrawingCache();
                        throw th;
                    }
                }
                createBitmap = null;
                if (createBitmap != null) {
                }
                if (createBitmap != null) {
                }
                if (bitmap2 != null) {
                }
            }
        });
    }

    public static void reportXView2Error(String str, String str2, String str3, String str4) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "943");
            hashMap.put("url", str3);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("errMsg", str4);
            hashMap.put("function", str);
            hashMap.put(t.f20145j, str2);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void reportXView2ErrorWithInvokeSwitch(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(XView2Constants.XVIEW_INVOKE_EXCEPTION, "");
        if (TextUtils.isEmpty(switchStringValue) || TextUtils.isEmpty(str5)) {
            return;
        }
        for (String str8 : switchStringValue.split(CartConstant.KEY_YB_INFO_LINK)) {
            if (str8.equals(str5)) {
                reportXView2Error(str, str2, str3, str4, str5, str6, str7);
                return;
            }
        }
    }

    public static void reportXView2ErrorWithSwitch(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(XView2Constants.XVIEW_EXCEPTION_REPORT, "");
        if (TextUtils.isEmpty(switchStringValue) || TextUtils.isEmpty(str5)) {
            return;
        }
        for (String str8 : switchStringValue.split(CartConstant.KEY_YB_INFO_LINK)) {
            if (str8.equals(str5)) {
                reportXView2Error(str, str2, str3, str4, str5, str6, str7);
                return;
            }
        }
    }

    public static void runOnUiThread(@NotNull BaseRunnable baseRunnable, long j2) {
        sHandler.postDelayed(baseRunnable, j2);
    }

    public static void safeRegisterEventBus(Object obj) {
        try {
            if (EventBus.getDefault().isRegistered(obj)) {
                return;
            }
            EventBus.getDefault().register(obj);
        } catch (Exception unused) {
        }
    }

    public static void safeUnRegisterEventBus(Object obj) {
        try {
            if (EventBus.getDefault().isRegistered(obj)) {
                EventBus.getDefault().unregister(obj);
            }
        } catch (Exception unused) {
        }
    }

    public static void saveBooleanToSp(String str, boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean(str, z).apply();
    }

    public static void saveIntToSp(String str, int i2) {
        CommonBase.getJdSharedPreferences().edit().putInt(str, i2).apply();
    }

    public static void saveLongToSp(String str, long j2) {
        CommonBase.getJdSharedPreferences().edit().putLong(str, j2).apply();
    }

    public static void saveStrToSp(String str, String str2) {
        CommonBase.getJdSharedPreferences().edit().putString(str, str2).apply();
    }

    public static boolean screenWidthChanged(int i2, int i3) {
        if (i2 == H_WIDTH && i3 == H_HEIGHT) {
            return false;
        }
        try {
            mSizeLock.writeLock().lock();
            boolean z = i2 != H_WIDTH;
            H_WIDTH = i2;
            if (i2 == DPIUtil.getWidth()) {
                i3 = DPIUtil.getHeight();
            }
            H_HEIGHT = i3;
            if (z) {
                mCacheArr = new int[128];
                mCacheSize.clear();
            }
            return z;
        } finally {
            mSizeLock.writeLock().unlock();
        }
    }

    public static void setOnClick(View view, final OnClickEventListener onClickEventListener) {
        if (view != null && onClickEventListener != null && System.currentTimeMillis() - currentTime >= 500) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.XView2Utils.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    onClickEventListener.onClick(view2);
                }
            });
        } else {
            currentTime = System.currentTimeMillis();
        }
    }

    public static void setOptionReferer(JDDisplayImageOptions jDDisplayImageOptions, String str) {
        if (jDDisplayImageOptions == null || TextUtils.isEmpty(str)) {
            return;
        }
        jDDisplayImageOptions.setReferer("image_xview_" + str);
    }

    public static void setSubText(TextView textView, String str, int i2, int i3, boolean z) {
        if (textView != null && !TextUtils.isEmpty(str) && i3 > i2) {
            if (str.length() >= i3 - i2) {
                textView.setText(str.substring(i2, i3));
            } else {
                textView.setText(str);
            }
        }
        if (textView == null || !TextUtils.isEmpty(str)) {
            return;
        }
        textView.setVisibility(z ? 0 : 8);
    }

    public static void setTextColor(TextView textView, String str, String str2) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (!isColorString(str2)) {
                str2 = JDDarkUtil.COLOR_0000000;
            }
            textView.setTextColor(Color.parseColor(str2));
            return;
        }
        if (!isColorString(str)) {
            str = JDDarkUtil.COLOR_0000000;
        }
        textView.setTextColor(Color.parseColor(str));
    }

    public static void setTextToJDBoldFont(TextView... textViewArr) {
        if (textViewArr == null || textViewArr.length == 0) {
            return;
        }
        for (TextView textView : textViewArr) {
            FontsUtil.changeTextFont(textView, 4097);
        }
    }

    public static void setTextToJDFont(TextView... textViewArr) {
        if (textViewArr == null || textViewArr.length == 0) {
            return;
        }
        for (TextView textView : textViewArr) {
            FontsUtil.changeTextFont(textView);
        }
    }

    public static void setTextToJDZhengHTRegularFont(TextView... textViewArr) {
        if (textViewArr == null || textViewArr.length == 0) {
            return;
        }
        for (TextView textView : textViewArr) {
            FontsUtil.changeTextFont(textView, 4099);
        }
    }

    public static void setXViewGrayMode(View view) {
        JDGrayModelUtils.getInstance().setViewGray(view, JDGrayModelUtils.getInstance().isGrayModel());
    }

    public static double sub(double d, double d2) {
        return new BigDecimal(Double.toString(d)).subtract(new BigDecimal(Double.toString(d2))).setScale(2, 4).doubleValue();
    }

    public boolean getCanPreloadStatus(XViewConfigEntity.LayersEntity layersEntity, String str) {
        if (isListEmpty(layersEntity.preDownLoadList)) {
            return false;
        }
        Iterator<PreDownLoadResourceEntity> it = layersEntity.preDownLoadList.iterator();
        while (it.hasNext()) {
            PreDownLoadResourceEntity next = it.next();
            if (isConvertBool(next.canPreload) && !TextUtils.isEmpty(str) && str.equals(next.url) && !TextUtils.isEmpty(layersEntity.layerId) && layersEntity.layerId.equals(next.layerId)) {
                break;
            }
        }
        return true;
    }

    private static void close(Reader reader) {
        try {
            reader.close();
        } catch (Exception unused) {
        }
    }

    public static void runOnUiThread(@NotNull BaseRunnable baseRunnable) {
        if (isMainThread()) {
            baseRunnable.run();
        } else {
            sHandler.post(baseRunnable);
        }
    }

    public static String getIntFromSp(String str, String str2) {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        return jdSharedPreferences != null ? jdSharedPreferences.getString(str, str2) : "";
    }

    public static void saveIntToSp(String str, String str2) {
        CommonBase.getJdSharedPreferences().edit().putString(str, str2).apply();
    }

    public static ArrayList<RecyclerView> getRecyclerViews(View view) {
        if (view != null && (view instanceof ViewGroup)) {
            LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
            ArrayList arrayList = new ArrayList();
            ArrayList<RecyclerView> arrayList2 = new ArrayList<>();
            linkedBlockingQueue.offer((ViewGroup) view);
            while (!linkedBlockingQueue.isEmpty()) {
                while (!linkedBlockingQueue.isEmpty()) {
                    ViewGroup viewGroup = (ViewGroup) linkedBlockingQueue.poll();
                    if (viewGroup instanceof RecyclerView) {
                        arrayList2.add((RecyclerView) viewGroup);
                    } else {
                        int childCount = viewGroup.getChildCount();
                        for (int i2 = 0; i2 < childCount; i2++) {
                            View childAt = viewGroup.getChildAt(i2);
                            if (childAt instanceof ViewGroup) {
                                arrayList.add((ViewGroup) childAt);
                            }
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    linkedBlockingQueue.addAll(arrayList);
                    arrayList.clear();
                }
            }
            return arrayList2;
        }
        return null;
    }

    public static void reportXView2Error(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "943");
            hashMap.put("url", str3);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("errMsg", "layerId_" + str5 + CartConstant.KEY_YB_INFO_LINK + str6 + CartConstant.KEY_YB_INFO_LINK + str4);
            hashMap.put("function", str);
            hashMap.put("PostData", str7);
            hashMap.put(t.f20145j, str2);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
