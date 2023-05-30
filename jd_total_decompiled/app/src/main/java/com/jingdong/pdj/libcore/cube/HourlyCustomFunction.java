package com.jingdong.pdj.libcore.cube;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.pdj.libcore.point.HourlyFloorMaiDianJson;
import com.jingdong.pdj.libcore.point.HourlyGoHomeMaiDianUpload;
import com.jingdong.pdj.libcore.point.PointData;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\f\u0018\u0000 =2\u00020\u0001:\u0002=>B\u0017\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010:\u001a\u00020/\u00a2\u0006\u0004\b;\u0010<J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J%\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ!\u0010\u0010\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0004J\u0015\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0019\u0010\u0013J\u0015\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001a\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u00a2\u0006\u0004\b\u001f\u0010 J\u0015\u0010#\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020!\u00a2\u0006\u0004\b#\u0010$R\u0018\u0010%\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010(R$\u0010)\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010&\u001a\u0004\b*\u0010+\"\u0004\b,\u0010\u0013R\u0018\u0010-\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010(R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\"\u0010.R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020/8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u00101R\u001f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001e038\u0006@\u0006\u00a2\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0016\u00108\u001a\u00020\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u00109\u00a8\u0006?"}, d2 = {"Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction;", "Lcom/jd/dynamic/base/CommFunction;", "", "mtaExplore", "()V", "Lcom/jd/dynamic/base/DynamicTemplateEngine;", "engine", "Lorg/json/JSONObject;", "jsonObject", "", "exec", "(Lcom/jd/dynamic/base/DynamicTemplateEngine;Lorg/json/JSONObject;)Ljava/lang/Object;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", CartConstant.KEY_JUMPURL, "jump", "(Landroid/content/Context;Ljava/lang/String;)V", "clickUpload", "(Lorg/json/JSONObject;)V", "name", "optParams", "(Ljava/lang/String;Lorg/json/JSONObject;)Ljava/lang/String;", "getPointData", "data", "setData", "", "isDetailPage", "getPageName", "(Z)Ljava/lang/String;", "Lcom/jingdong/pdj/libcore/point/PointData;", "getCubePointData", "()Lcom/jingdong/pdj/libcore/point/PointData;", "Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction$MethodInterface;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "setMethodInterface", "(Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction$MethodInterface;)V", "mData", "Lorg/json/JSONObject;", "mPointData", "Lcom/jingdong/pdj/libcore/point/PointData;", "mJsonObject", "getMJsonObject", "()Lorg/json/JSONObject;", "setMJsonObject", "mOnePointData", "Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction$MethodInterface;", "", "mType", "I", "mBusinessType", "", "mPointList", "Ljava/util/List;", "getMPointList", "()Ljava/util/List;", "mContext", "Landroid/content/Context;", "businessType", "<init>", "(Landroid/content/Context;I)V", "Companion", "MethodInterface", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class HourlyCustomFunction extends CommFunction {
    private static final String CUBE_DATA = "data";
    private static final String CUBE_DATANODE = "dataNode";
    private static final String CUBE_EVENTID = "eventId";
    private static final String CUBE_EXPOEVENTID = "expoEventId";
    private static final String CUBE_FUN = "fun";
    private static final String CUBE_LA_P = "la_p";
    private static final String CUBE_MESSAGE = "message";
    private static final String CUBE_MTATYPE = "mtaType";
    private static final String CUBE_MTA_CLICK = "mtaClick";
    private static final String CUBE_MTA_EXPLORE = "mtaExplore";
    private static final String CUBE_OPEN_APP = "openApp";
    private static final String CUBE_POSITION = "position";
    private static final String CUBE_SRVJSON = "srvJson";
    private static final String CUBE_STM = "stm";
    private static final String CUBE_TOAST = "toast";
    private static final String CUBE_URL = "url";
    @NotNull
    public static final String HOURLY_DETAIL_PAGE_NAME = "HourlyGoDetailFragment";
    @NotNull
    public static final String HOURLY_HOME_PAGE_ID = "Home_Nearby_Main";
    @NotNull
    public static final String HOURLY_HOME_PAGE_NAME = "HourlyGoHomeFragment";
    private MethodInterface listener;
    private final int mBusinessType;
    private final Context mContext;
    private JSONObject mData;
    @Nullable
    private JSONObject mJsonObject;
    private PointData mOnePointData;
    private PointData mPointData;
    @NotNull
    private final List<PointData> mPointList = new ArrayList();
    private int mType;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction$MethodInterface;", "", "", "functionName", "", "functionClick", "(Ljava/lang/String;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public interface MethodInterface {
        void functionClick(@NotNull String functionName);
    }

    public HourlyCustomFunction(@NotNull Context context, int i2) {
        this.mContext = context;
        this.mBusinessType = i2;
    }

    private final void mtaExplore() {
        OKLog.i("DynamicContainer", "mtaExplore");
        getPointData();
    }

    public final void clickUpload(@NotNull JSONObject jsonObject) {
        getPointData();
        if (this.mType == 0) {
            PointData pointData = this.mOnePointData;
            if (pointData != null) {
                HourlyGoHomeMaiDianUpload.onViewClickPoint(pointData);
                return;
            }
            return;
        }
        int optInt = jsonObject.optJSONObject("data").optInt("position");
        if (optInt < this.mPointList.size()) {
            HourlyGoHomeMaiDianUpload.onViewClickPoint(this.mPointList.get(optInt));
        }
    }

    @Override // com.jd.dynamic.base.CommFunction
    @Nullable
    public final Object exec(@Nullable DynamicTemplateEngine engine, @Nullable JSONObject jsonObject) {
        OKLog.i("DynamicContainer", String.valueOf(jsonObject));
        if (jsonObject != null) {
            try {
                this.mJsonObject = jsonObject;
                String functionName = jsonObject.optString(CUBE_FUN);
                this.mType = jsonObject.optInt(CUBE_MTATYPE);
                MethodInterface methodInterface = this.listener;
                if (methodInterface == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(CartCleanConstants.CART_CLEAN_DIALOG_LISTENER);
                }
                Intrinsics.checkExpressionValueIsNotNull(functionName, "functionName");
                methodInterface.functionClick(functionName);
                switch (functionName.hashCode()) {
                    case -1547040359:
                        if (functionName.equals("mtaExplore")) {
                            mtaExplore();
                            break;
                        }
                        break;
                    case -1263222921:
                        if (functionName.equals("openApp")) {
                            jump(this.mContext, optParams("url", jsonObject));
                            break;
                        }
                        break;
                    case -593764850:
                        if (functionName.equals("mtaClick")) {
                            clickUpload(jsonObject);
                            break;
                        }
                        break;
                    case 110532135:
                        if (functionName.equals("toast")) {
                            ToastUtil.showToast(optParams("message", jsonObject));
                            break;
                        }
                        break;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Nullable
    public final PointData getCubePointData() {
        return this.mType == 0 ? this.mOnePointData : this.mPointData;
    }

    @Nullable
    public final JSONObject getMJsonObject() {
        return this.mJsonObject;
    }

    @NotNull
    public final List<PointData> getMPointList() {
        return this.mPointList;
    }

    @NotNull
    public final String getPageName(boolean isDetailPage) {
        return isDetailPage ? "HourlyGoDetailFragment" : "HourlyGoHomeFragment";
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void getPointData() {
        JSONObject jSONObject;
        int i2;
        int i3;
        JDJSONArray jDJSONArray;
        String str;
        String str2;
        String str3;
        ArrayList arrayList;
        boolean z;
        String str4;
        try {
            if (this.mJsonObject == null || (jSONObject = this.mData) == null) {
                return;
            }
            if (jSONObject == null) {
                Intrinsics.throwNpe();
            }
            boolean optBoolean = jSONObject.optBoolean("isDetailPage");
            JSONObject jSONObject2 = this.mData;
            if (jSONObject2 == null) {
                Intrinsics.throwNpe();
            }
            boolean optBoolean2 = jSONObject2.optBoolean("isCache");
            JSONObject jSONObject3 = this.mData;
            if (jSONObject3 == null) {
                Intrinsics.throwNpe();
            }
            String optString = jSONObject3.optString("realIndex");
            JSONObject jSONObject4 = this.mJsonObject;
            if (jSONObject4 == null) {
                Intrinsics.throwNpe();
            }
            String optString2 = jSONObject4.optString("eventId");
            JSONObject jSONObject5 = this.mJsonObject;
            if (jSONObject5 == null) {
                Intrinsics.throwNpe();
            }
            String optString3 = jSONObject5.optString(CUBE_EXPOEVENTID);
            JSONObject jSONObject6 = this.mJsonObject;
            if (jSONObject6 == null) {
                Intrinsics.throwNpe();
            }
            JSONObject optJSONObject = jSONObject6.optJSONObject("data");
            int i4 = this.mType;
            String str5 = "#1#";
            String str6 = CUBE_SRVJSON;
            String str7 = "position";
            if (i4 == 0) {
                String optString4 = optJSONObject.optString(CUBE_SRVJSON);
                int optInt = optJSONObject.optInt("position");
                HourlyFloorMaiDianJson build = HourlyFloorMaiDianJson.build(optString4);
                StringBuilder sb = new StringBuilder();
                if (optString == null || optString.length() == 0) {
                    optString = "1";
                }
                sb.append(optString);
                sb.append("#1#");
                sb.append(optInt + 1);
                build.addInfo("position", sb.toString());
                this.mOnePointData = new PointData("Home_Nearby_Main", getPageName(optBoolean), optString2, optString3, build.toString(), true, optBoolean2, optBoolean);
                return;
            }
            this.mPointList.clear();
            String optString5 = optJSONObject != null ? optJSONObject.optString(CUBE_DATANODE) : null;
            JSONObject jSONObject7 = this.mData;
            if (jSONObject7 == null) {
                Intrinsics.throwNpe();
            }
            JDJSONArray parseArray = JDJSON.parseArray(jSONObject7.optString(optString5));
            OKLog.i("DynamicContainer", parseArray.toJSONString());
            ArrayList arrayList2 = new ArrayList();
            int size = parseArray.size();
            int i5 = 0;
            while (i5 < size) {
                Object obj = parseArray.get(i5);
                if (obj instanceof JDJSONObject) {
                    HourlyFloorMaiDianJson build2 = HourlyFloorMaiDianJson.build(((JDJSONObject) obj).optString(str6));
                    StringBuilder sb2 = new StringBuilder();
                    if (optString != null && optString.length() != 0) {
                        z = false;
                        if (z) {
                            i3 = size;
                            str4 = optString;
                        } else {
                            i3 = size;
                            str4 = "1";
                        }
                        sb2.append(str4);
                        sb2.append(str5);
                        sb2.append(i5 + 1);
                        build2.addInfo(str7, sb2.toString());
                        i2 = i5;
                        jDJSONArray = parseArray;
                        str = str7;
                        str2 = str6;
                        str3 = str5;
                        PointData pointData = new PointData("Home_Nearby_Main", getPageName(optBoolean), optString2, optString3, build2.toString(), true, optBoolean2, optBoolean);
                        arrayList = arrayList2;
                        arrayList.add(build2.toString());
                        OKLog.i("DynamicContainer", pointData.toString());
                        this.mPointList.add(pointData);
                    }
                    z = true;
                    if (z) {
                    }
                    sb2.append(str4);
                    sb2.append(str5);
                    sb2.append(i5 + 1);
                    build2.addInfo(str7, sb2.toString());
                    i2 = i5;
                    jDJSONArray = parseArray;
                    str = str7;
                    str2 = str6;
                    str3 = str5;
                    PointData pointData2 = new PointData("Home_Nearby_Main", getPageName(optBoolean), optString2, optString3, build2.toString(), true, optBoolean2, optBoolean);
                    arrayList = arrayList2;
                    arrayList.add(build2.toString());
                    OKLog.i("DynamicContainer", pointData2.toString());
                    this.mPointList.add(pointData2);
                } else {
                    i2 = i5;
                    i3 = size;
                    jDJSONArray = parseArray;
                    str = str7;
                    str2 = str6;
                    str3 = str5;
                    arrayList = arrayList2;
                }
                i5 = i2 + 1;
                arrayList2 = arrayList;
                size = i3;
                str7 = str;
                str6 = str2;
                parseArray = jDJSONArray;
                str5 = str3;
            }
            PointData pointData3 = new PointData("Home_Nearby_Main", getPageName(optBoolean), optString2, optString3, (List<String>) arrayList2, true, optBoolean2, optBoolean);
            this.mPointData = pointData3;
            OKLog.i("DynamicContainer", String.valueOf(pointData3));
        } catch (Exception e2) {
            OKLog.i("DynamicContainer", e2.toString());
        }
    }

    public final void jump(@Nullable Context context, @Nullable String jumpUrl) {
        if (context == null || TextUtils.isEmpty(jumpUrl)) {
            return;
        }
        try {
            new OpenAppJumpBuilder.Builder(Uri.parse(jumpUrl)).build().jump(context);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @NotNull
    public final String optParams(@NotNull String name, @NotNull JSONObject jsonObject) {
        String optString = jsonObject.optString(name);
        Intrinsics.checkExpressionValueIsNotNull(optString, "jsonObject.optString(name)");
        return optString;
    }

    public final void setData(@NotNull JSONObject data) {
        OKLog.i("DynamicContainer", "setData");
        this.mData = data;
        this.mPointList.clear();
        this.mPointData = null;
        this.mOnePointData = null;
    }

    public final void setMJsonObject(@Nullable JSONObject jSONObject) {
        this.mJsonObject = jSONObject;
    }

    public final void setMethodInterface(@NotNull MethodInterface listener) {
        this.listener = listener;
    }
}
