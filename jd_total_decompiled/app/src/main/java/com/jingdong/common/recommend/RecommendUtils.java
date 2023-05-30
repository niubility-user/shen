package com.jingdong.common.recommend;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.entitys.PDRecommendEntity;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.recommend.entity.RecommendData;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendLabel;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.ui.homerecommend.HomeRecommendContentLayout;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendUtils {
    public static int APPTYPE_B = 0;
    public static String HOME_FIST_RECOMMEND_VIEW_HIDE = null;
    public static String HOME_FIST_RECOMMEND_VIEW_SHOW = null;
    public static String HOME_MINI_PLAY_END = null;
    public static String HOME_MINI_PLAY_START = null;
    public static String MATERIAL_FEEDBACK_ANI = null;
    private static final long MIN_CLICK_DELAY = 1000;
    public static boolean RECOMMEND_LIVE_GUIDE_SHOW;
    public static int RECYCLERVIEW_ITEM_MARGIN;
    public static int RECYCLERVIEW_LEFT_RIGHT_PADDING;
    public static int RECYCLER_VIEW_PADDING_NEW;
    private static long lastClickTime;
    private static JDLocationCacheOption locationOption;
    public static int normalColor_262626;
    public static int normalColor_2E2D2D;
    public static int normalColor_ECECEC;
    public static int normalColor_F2270C;
    public static boolean recommendTestSwitch;
    public static int statusBarHeight;
    public static ArrayMap<String, Float> textWidthArrayMap = new ArrayMap<>();
    public static String HTTP_REFER = RecommendConstant.HTTP_REFER;
    public static int windowWidthSize = 0;
    public static int bottomHeight = DPIUtil.dip2px(50.0f);
    public static int screenHeight = DPIUtil.getHeight();
    public static int homeScreenHeight = 0;
    public static int RECYCLER_VIEW_PADDING = DPIUtil.dip2px(4.0f);

    static {
        int dip2px = DPIUtil.dip2px(6.0f);
        RECYCLER_VIEW_PADDING_NEW = dip2px;
        RECYCLERVIEW_LEFT_RIGHT_PADDING = dip2px;
        RECYCLERVIEW_ITEM_MARGIN = DPIUtil.dip2px(4.0f);
        normalColor_262626 = Color.parseColor(JDDarkUtil.COLOR_262626);
        normalColor_F2270C = Color.parseColor("#F2270C");
        normalColor_2E2D2D = Color.parseColor(JDDarkUtil.COLOR_2E2D2D);
        normalColor_ECECEC = Color.parseColor(JDDarkUtil.COLOR_ECECEC);
        RECOMMEND_LIVE_GUIDE_SHOW = false;
        HOME_FIST_RECOMMEND_VIEW_SHOW = "HOME_FIST_RECOMMEND_VIEW_SHOW";
        HOME_FIST_RECOMMEND_VIEW_HIDE = "HOME_FIST_RECOMMEND_VIEW_HIDE";
        HOME_MINI_PLAY_START = "HOME_MINI_PLAY_START";
        HOME_MINI_PLAY_END = "HOME_MINI_PLAY_END";
        MATERIAL_FEEDBACK_ANI = "MATERIAL_FEEDBACK_ANI";
        recommendTestSwitch = false;
        APPTYPE_B = 4;
    }

    public static boolean downgradeExpoAdd() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "downgradeExpoAdd", "downgradeExpoAdd", "0"));
    }

    public static boolean downgradeSplitText() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "downSplitText", "downSplitText", "1"));
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (drawable instanceof NinePatchDrawable) {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return createBitmap;
        }
        return null;
    }

    public static String getCurrentAddress() {
        if (locationOption == null) {
            JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
            locationOption = jDLocationCacheOption;
            jDLocationCacheOption.setBusinessId("373b37f4abb4412d06bca58ed8ee7242");
        }
        JDLocation location = JDLocationCache.getInstance().getLocation(locationOption);
        String valueOf = String.valueOf(location.getLng());
        String valueOf2 = String.valueOf(location.getLat());
        if (TextUtils.isEmpty(valueOf) || TextUtils.isEmpty(valueOf2)) {
            return "";
        }
        return valueOf + DYConstants.DY_REGEX_COMMA + valueOf2;
    }

    public static String[] getExpoID_Num() {
        String[] strArr = new String[2];
        String string = SharedPreferencesUtil.getString("RECOMMEND_EXPOID_NUM", "");
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(CartConstant.KEY_YB_INFO_LINK);
            if (split.length >= 2) {
                strArr[0] = split[0];
                strArr[1] = split[1];
            }
        }
        return strArr;
    }

    public static String getFormatTime(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        return new SimpleDateFormat("mm:ss").format(Long.valueOf(i2 * 1000)).replace(":", "'") + "\"";
    }

    public static String getIllegalPrice() {
        return JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_price);
    }

    public static String getJdPrice(String str) {
        String string = JdSdk.getInstance().getApplication().getString(R.string.recommend_product_no_price);
        if (TextUtils.isEmpty(str)) {
            return string;
        }
        try {
            double doubleValue = Double.valueOf(str).doubleValue();
            return doubleValue > 0.0d ? String.format("%.2f", Double.valueOf(doubleValue)) : string;
        } catch (Exception unused) {
            return string;
        }
    }

    public static boolean getRecommendTestSwitch() {
        return recommendTestSwitch;
    }

    public static String getShippingAddress() {
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal("basicShoppingProcess");
        if (addressGlobal != null) {
            String longitude = addressGlobal.getLongitude();
            String latitude = addressGlobal.getLatitude();
            if (!TextUtils.isEmpty(longitude) && !TextUtils.isEmpty(latitude)) {
                return longitude + DYConstants.DY_REGEX_COMMA + latitude;
            }
        }
        return "";
    }

    public static String getShowPrice(String str) {
        String illegalPrice = getIllegalPrice();
        if (TextUtils.isEmpty(str)) {
            return illegalPrice;
        }
        try {
            double doubleValue = Double.valueOf(str).doubleValue();
            if (doubleValue <= 0.0d) {
                str = illegalPrice;
            } else if (!isUseServicePrice()) {
                str = String.format("%.2f", Double.valueOf(doubleValue));
            }
            return str;
        } catch (Exception unused) {
            return illegalPrice;
        }
    }

    public static JSONObject getVideoPlayExpoJsonParam(RecommendVideo recommendVideo, int i2, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("skutime", String.valueOf(System.currentTimeMillis() / 1000));
            jSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, recommendVideo.wareId);
            jSONObject.put("reqsig", recommendVideo.reqsig);
            jSONObject.put("videotime", String.valueOf(recommendVideo.videoDuration));
            jSONObject.put("playtime", String.valueOf(i2 / 1000));
            jSONObject.put("playstatus", z ? 1 : 0);
            jSONObject.put("broker_info", TextUtils.isEmpty(recommendVideo.brokerInfo) ? "-100" : recommendVideo.brokerInfo);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static int getWidthByDesignValue(int i2, int i3, int i4) {
        if (i2 == 0) {
            return DPIUtil.getWidthByDesignValue750(i3);
        }
        return (i2 * i3) / i4;
    }

    public static void handleLabelValueMtaJson(String str, RecommendProduct recommendProduct) {
        if (StringUtil.isEmpty(str)) {
            str = "-100";
        }
        if (recommendProduct.isMtaValueChanged || StringUtil.isEmpty(str)) {
            return;
        }
        if (str.endsWith("#")) {
            str = str.substring(0, str.length() - 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\"labellist\":");
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        sb.append(DYConstants.DY_REGEX_COMMA);
        if (!TextUtils.isEmpty(recommendProduct.sourceValue)) {
            StringBuilder sb2 = new StringBuilder(recommendProduct.sourceValue);
            sb2.insert(1, (CharSequence) sb);
            recommendProduct.sourceValue = sb2.toString();
        }
        if (TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue)) {
            return;
        }
        StringBuilder sb3 = new StringBuilder(recommendProduct.exposureJsonSourceValue);
        sb3.insert(1, (CharSequence) sb);
        recommendProduct.exposureJsonSourceValue = sb3.toString();
    }

    @Deprecated
    public static void handleLableMtaJson(PDRecommendEntity pDRecommendEntity, RecommendLabel recommendLabel, boolean z) {
    }

    @Deprecated
    public static void handleLableMtaJson(RecommendProduct recommendProduct, RecommendLabel recommendLabel, boolean z) {
    }

    public static boolean isAppB(int i2) {
        return APPTYPE_B == i2;
    }

    public static boolean isBAppType() {
        return "2".equals(JDBModeUtils.getCurrentMode());
    }

    public static boolean isRealExpo2() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "enableRightExpo", "enableRightExpo2", "0"));
    }

    public static boolean isSplitText() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "splitText", "splitText", "0"));
    }

    public static boolean isTooFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime > 1000) {
            lastClickTime = currentTimeMillis;
            return false;
        }
        return true;
    }

    public static boolean isUseServicePrice() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "downgrade0", "downgrade0", "0"));
    }

    public static boolean isViewInLeft(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return true;
        }
        ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
        return layoutParams instanceof GridLayoutManager.LayoutParams ? viewHolder.getAdapterPosition() % 2 == 0 : !(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) || ((StaggeredGridLayoutManager.LayoutParams) layoutParams).getSpanIndex() % 2 == 0;
    }

    public static boolean isWaterFull(int i2) {
        return i2 == 9 || i2 == 36 || i2 == 0 || i2 == 18 || i2 == 6 || i2 == 48 || i2 == 35 || i2 == 49 || i2 == 3 || i2 == 4 || i2 == 37;
    }

    public static void joinLabelValue(StringBuilder sb, RecommendProduct recommendProduct, RecommendLabel recommendLabel) {
        if (recommendProduct == null || recommendProduct.isMtaValueChanged || StringUtil.isEmpty(recommendLabel.labelValue)) {
            return;
        }
        sb.append(recommendLabel.labelValue);
        sb.append("#");
    }

    public static RecommendData parseJsonToList(JDJSONObject jDJSONObject, String[] strArr) {
        RecommendData recommendData = new RecommendData();
        if (jDJSONObject == null) {
            return recommendData;
        }
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("wareInfoList");
        JDJSONObject jDJSONObject2 = null;
        try {
            if (jDJSONObject.optJSONObject(RecommendMtaUtils.UET) != null && jDJSONObject.optJSONObject(RecommendMtaUtils.UET).optJSONObject(RecommendMtaUtils.TRACKING) != null) {
                JDJSONObject jDJSONObject3 = new JDJSONObject();
                try {
                    jDJSONObject3.put(RecommendMtaUtils.UET, (Object) jDJSONObject.optJSONObject(RecommendMtaUtils.UET));
                    jDJSONObject2 = jDJSONObject3;
                } catch (Exception e2) {
                    e = e2;
                    jDJSONObject2 = jDJSONObject3;
                    if (OKLog.D) {
                        e.printStackTrace();
                    }
                    if (optJSONArray != null) {
                        recommendData.setRecommendList(toRecomendList(optJSONArray, jDJSONObject2, strArr));
                    }
                    return recommendData;
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
        if (optJSONArray != null && !optJSONArray.isEmpty()) {
            recommendData.setRecommendList(toRecomendList(optJSONArray, jDJSONObject2, strArr));
        }
        return recommendData;
    }

    public static double parseStringToDouble(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0.0d;
            }
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return 0.0d;
        }
    }

    public static float parseStringToFloat(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0.0f;
            }
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return 0.0f;
        }
    }

    public static int parseStringToInt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static void printRecommendException(Exception exc) {
        if (OKLog.D) {
            exc.printStackTrace();
        }
    }

    public static void requestDynamic() {
        if (DynamicSdk.getEngine() != null) {
            if (!HomeRecommendContentLayout.isUseDynamicZip && !HomeRecommendContentLayout.isUseNewDynApi) {
                DynamicSdk.getEngine().fetchTemplates(null, true, RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE);
                return;
            }
            IDynamicDriver driver = DynamicSdk.getDriver();
            if (driver != null) {
                driver.prepare(RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, "");
            }
        }
    }

    public static void saveExpoID_Num(String str, int i2) {
        SharedPreferencesUtil.putString("RECOMMEND_EXPOID_NUM", str + CartConstant.KEY_YB_INFO_LINK + i2);
    }

    public static void setRecommendDebugMark(TextView textView, boolean z, boolean z2) {
        if (getRecommendTestSwitch()) {
            RecommendViewUtil.visible(textView);
            if (z2) {
                RecommendViewUtil.setViewBackgroundColor(textView, Color.parseColor("#5DAD5B"));
                RecommendViewUtil.setText(textView, "\u8d27\u5e01\u5316");
                return;
            } else if (z) {
                RecommendViewUtil.setViewBackgroundColor(textView, Color.parseColor("#0000FF"));
                RecommendViewUtil.setText(textView, "\u5e7f\u544a\u63a8\u8350");
                return;
            } else {
                RecommendViewUtil.setViewBackgroundColor(textView, Color.parseColor("#FF0000"));
                RecommendViewUtil.setText(textView, "\u81ea\u7136\u63a8\u8350");
                return;
            }
        }
        RecommendViewUtil.gone(textView);
    }

    public static void setRecommendTestSwitch(boolean z) {
        recommendTestSwitch = z;
        RecommendSPUtils.putBoolean(RecommendSPUtils.SP_USER_DEBUG, z);
    }

    public static boolean shieldRequest(int i2) {
        if ("2".equals(JDBModeUtils.getCurrentMode())) {
            return i2 == 1 || i2 == 10 || i2 == 15 || i2 == 13 || i2 == 17 || i2 == 34;
        }
        return false;
    }

    public static ArrayList<RecommendItem> toRecomendList(JDJSONArray jDJSONArray, JDJSONObject jDJSONObject, String[] strArr) {
        ArrayList<RecommendItem> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            RecommendItem recommendItem = new RecommendItem();
            recommendItem.setData(jDJSONArray.getJSONObject(i2));
            RecommendProduct recommendProduct = recommendItem.product;
            if (recommendProduct != null) {
                recommendProduct.rootUETJson = jDJSONObject;
                recommendProduct.relationSkus = strArr;
            }
            if (recommendItem.isShow) {
                arrayList.add(recommendItem);
            }
        }
        return arrayList;
    }

    public static void joinLabelValue(StringBuilder sb, PDRecommendEntity pDRecommendEntity, RecommendLabel recommendLabel) {
        if (pDRecommendEntity == null || pDRecommendEntity.isMtaValueChanged || StringUtil.isEmpty(recommendLabel.labelValue)) {
            return;
        }
        sb.append(recommendLabel.labelValue);
        sb.append("#");
    }

    public static void handleLabelValueMtaJson(String str, PDRecommendEntity pDRecommendEntity) {
        if (StringUtil.isEmpty(str)) {
            str = "-100";
        }
        if (pDRecommendEntity.isMtaValueChanged || StringUtil.isEmpty(str)) {
            return;
        }
        if (str.endsWith("#")) {
            str = str.substring(0, str.length() - 1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\"labellist\":");
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        sb.append(DYConstants.DY_REGEX_COMMA);
        if (!TextUtils.isEmpty(pDRecommendEntity.sourceValue)) {
            StringBuilder sb2 = new StringBuilder(pDRecommendEntity.sourceValue);
            sb2.insert(1, (CharSequence) sb);
            pDRecommendEntity.sourceValue = sb2.toString();
        }
        if (TextUtils.isEmpty(pDRecommendEntity.exposureJsonSourceValue)) {
            return;
        }
        StringBuilder sb3 = new StringBuilder(pDRecommendEntity.exposureJsonSourceValue);
        sb3.insert(1, (CharSequence) sb);
        pDRecommendEntity.exposureJsonSourceValue = sb3.toString();
    }
}
