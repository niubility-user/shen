package com.jingdong.common.unification.uniconfig;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnLibFileUtils;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/* loaded from: classes6.dex */
public class UnIconConfigController {
    public static final String A_B_SWITCH_A = "base";
    public static String EXP_ENTITY = "un_icon_exp_entity";
    public static String EXP_ID = "un_icon_exp_id";
    private static UnIconConfigController controller;
    private boolean isElderMode = false;
    public Boolean abSwitch = null;
    private String expId = null;
    private String buriedStr = "";
    private int iconNotFoundTimes = 0;
    private boolean isInit = false;

    private Bitmap createBitmap(IconConfigModel iconConfigModel, BitmapFactory.Options options) {
        try {
            return BitmapFactory.decodeFile(iconConfigModel.path, options);
        } catch (Exception unused) {
            return null;
        }
    }

    public static synchronized UnIconConfigController getController() {
        UnIconConfigController unIconConfigController;
        synchronized (UnIconConfigController.class) {
            UnIconConfigController unIconConfigController2 = controller;
            if (unIconConfigController2 != null) {
                return unIconConfigController2;
            }
            synchronized (UnIconConfigController.class) {
                if (controller == null) {
                    controller = new UnIconConfigController();
                }
                unIconConfigController = controller;
            }
            return unIconConfigController;
        }
    }

    private String getNinePatchId(String str) {
        return str;
    }

    private boolean getTestAb() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("unification", "unIcon", ABTestUtils.KEY_BASE_ARCH_CONFIG_NAMESPACE), "1");
    }

    public void optExpLabel(Map<String, ExpLabelEntity> map) {
        ExpLabelEntity expLabelEntity;
        if (map == null || map.isEmpty()) {
            return;
        }
        String testIds = getTestIds();
        if (TextUtils.isEmpty(testIds) || (expLabelEntity = map.get(testIds)) == null) {
            return;
        }
        this.expId = testIds;
        this.buriedStr = expLabelEntity.buriedStr;
        SharedPreferencesUtil.putString(EXP_ID, testIds);
        SharedPreferencesUtil.putString(EXP_ENTITY, JDJSON.toJSONString(expLabelEntity));
        this.abSwitch = Boolean.valueOf(TextUtils.equals(A_B_SWITCH_A, expLabelEntity.label));
    }

    private Bitmap scaleBitmap(Bitmap bitmap, String str, boolean z) {
        if (bitmap == null) {
            return null;
        }
        if (NinePatch.isNinePatchChunk(bitmap.getNinePatchChunk())) {
            return bitmap;
        }
        float density = DpiUtil.getDensity(a.g().d());
        if (UnLog.D) {
            UnLog.d("Uniocn", "dpi:" + density);
        }
        int width = DpiUtil.getWidth(a.g().d());
        float f2 = (density * (z ? 1.3f : 1.0f)) / 3.0f;
        if (UnLog.D) {
            UnLog.d("Uniocn", "scale:" + f2);
        }
        if (width == 480 && "tab_100".equals(str)) {
            f2 = 0.46f;
        }
        Matrix matrix = new Matrix();
        if (f2 < 1.0f) {
            try {
                f2 = Float.valueOf(new DecimalFormat("0.00").format(f2)).floatValue();
            } catch (Exception e2) {
                if (UnLog.D) {
                    e2.printStackTrace();
                }
            }
        }
        matrix.postScale(f2, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public boolean abSwitch() {
        if (getTestAb()) {
            return false;
        }
        if (this.abSwitch == null) {
            String expEntityFromSp = getExpEntityFromSp();
            if (TextUtils.isEmpty(expEntityFromSp)) {
                this.abSwitch = Boolean.TRUE;
            } else {
                ExpLabelEntity expLabelEntity = (ExpLabelEntity) JDJSON.optParseObject(expEntityFromSp, ExpLabelEntity.class);
                if (expLabelEntity == null) {
                    this.abSwitch = Boolean.TRUE;
                } else {
                    this.abSwitch = Boolean.valueOf(TextUtils.equals(A_B_SWITCH_A, expLabelEntity.label));
                }
            }
        }
        return this.abSwitch.booleanValue();
    }

    public void clearCache() {
        UnIconCache.getInstance().clear();
    }

    public void download4NotFinish() {
        if (UnSharedPreferencesUtils.getLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DOWNLOAD_FINISH_DATA_VERSION, 0L) == UnIconConfigHelper.getUniConfigDataVersion()) {
            return;
        }
        NetDataController.getController().multiDownload(DataBaseController.getController().queryListNotDown());
    }

    public boolean fileIsExists(final IconConfigModel iconConfigModel) {
        if (UnLibFileUtils.fileIsExists(iconConfigModel.path)) {
            return true;
        }
        Observable.create(new Observable.OnSubscribe<String>() { // from class: com.jingdong.common.unification.uniconfig.UnIconConfigController.2
            {
                UnIconConfigController.this = this;
            }

            @Override // rx.functions.Action1
            public void call(Subscriber<? super String> subscriber) {
                iconConfigModel.path = "";
                DataBaseController.getController().update(iconConfigModel);
            }
        }).observeOn(Schedulers.io());
        return false;
    }

    public synchronized Bitmap getBitmap(String str, BitmapFactory.Options options, boolean z) {
        return getBitmap(str, options, z, 0);
    }

    public String getBuriedStr() {
        ExpLabelEntity expLabelEntity;
        if (isBVersion()) {
            return "";
        }
        if (!TextUtils.isEmpty(this.buriedStr)) {
            return this.buriedStr;
        }
        String expEntityFromSp = getExpEntityFromSp();
        if (TextUtils.isEmpty(expEntityFromSp) || (expLabelEntity = (ExpLabelEntity) JDJSON.parseObject(expEntityFromSp, ExpLabelEntity.class)) == null) {
            return "";
        }
        String str = expLabelEntity.buriedStr;
        this.buriedStr = str;
        return str;
    }

    public String getExpEntityFromSp() {
        return SharedPreferencesUtil.getString(EXP_ENTITY, "");
    }

    public String getExpId() {
        if (isBVersion()) {
            return "";
        }
        if (TextUtils.isEmpty(this.expId)) {
            this.expId = SharedPreferencesUtil.getString(EXP_ID, "");
        }
        return this.expId;
    }

    public IconConfigModel getIconConfigModel(String str) {
        return getIconConfigModel(str, false, 0);
    }

    public String getIconInfoJson(String str) {
        return getIconInfoJson(str, this.isElderMode, 0);
    }

    public String getIconPath4DraweeView(String str) {
        return getIconPath4DraweeView(str, false, 0);
    }

    public String getIconUrl(String str) {
        return getIconUrl(str, false, 0);
    }

    public String getIdWithSwitch(String str, boolean z, boolean z2) {
        if (z2) {
            return str;
        }
        if (!isBVersion()) {
            if (abSwitch()) {
                return str;
            }
            return str + UnIconConfigConstants.UN_ICON_ID_B_S;
        } else if (z) {
            return str + UnIconConfigConstants.UN_ICON_ID_B_S;
        } else {
            return str + UnIconConfigConstants.UN_ICON_ID_B;
        }
    }

    public String getTestIds() {
        return JDMobileConfig.getInstance().getConfig("unification", "unIcon", "tsExpIds");
    }

    public String getTextColor(String str) {
        return getTextColor(str, this.isElderMode, 0);
    }

    public IconExtraConfigModel getTextConfig(String str) {
        return getTextConfig(str, false, 0);
    }

    public void init() {
        if (this.isInit) {
            return;
        }
        DefaultDataController.getController().initCache();
        this.isInit = true;
    }

    public boolean isBVersion() {
        String currentMode = JDBModeUtils.getCurrentMode();
        if (OKLog.D) {
            OKLog.d("UnIcon", "isBVersion:" + currentMode);
        }
        return TextUtils.equals(currentMode, "2");
    }

    public boolean isSmall(int i2) {
        return i2 == 2;
    }

    public void requestData() {
        NetDataController.getController().requestToJson(new OnUnIconResponseListener() { // from class: com.jingdong.common.unification.uniconfig.UnIconConfigController.1
            {
                UnIconConfigController.this = this;
            }

            @Override // com.jingdong.common.unification.uniconfig.OnUnIconResponseListener
            public void onEnd(IconConfigJsonModel iconConfigJsonModel, long j2) {
                List<IconConfigModel> list;
                List<IconConfigModel> list2;
                UnIconConfigController.this.optExpLabel(iconConfigJsonModel.expLabelData);
                if (UnIconConfigHelper.getUniConfigDataVersion() == j2) {
                    UnIconConfigController.this.download4NotFinish();
                    return;
                }
                IconConfigWidgetJsonModel iconConfigWidgetJsonModel = iconConfigJsonModel.data;
                List<IconConfigModel> queryListNotDown = DefaultDataController.getController().isFirstInit() ? DataBaseController.getController().queryListNotDown() : null;
                if (iconConfigWidgetJsonModel != null && (((list = iconConfigWidgetJsonModel.widget) != null && list.size() > 0) || ((list2 = iconConfigWidgetJsonModel.delete) != null && list2.size() > 0))) {
                    UnIconConfigController.this.clearCache();
                    DataBaseController.getController().deleteModels(iconConfigWidgetJsonModel.delete);
                    if (DataBaseController.getController().insertOrUpdate(iconConfigWidgetJsonModel.widget)) {
                        if (UnLog.D) {
                            UnLog.d("UnIcon", "UN_ICON_VERSION_CODE " + UnIconConfigHelper.getCurVersionCode());
                        }
                        UnSharedPreferencesUtils.putLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DATA_VERSION, j2);
                        if (queryListNotDown != null) {
                            queryListNotDown.addAll(iconConfigWidgetJsonModel.widget);
                        }
                        NetDataController.getController().multiDownload(queryListNotDown);
                    }
                } else if (queryListNotDown == null || queryListNotDown.size() <= 0) {
                } else {
                    NetDataController.getController().multiDownload(queryListNotDown);
                }
            }

            @Override // com.jingdong.common.unification.uniconfig.OnUnIconResponseListener
            public void onError() {
            }
        });
    }

    public synchronized void setIconNotFoundTimes(int i2) {
        this.iconNotFoundTimes = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x00a3 A[Catch: all -> 0x00b5, TryCatch #0 {, blocks: (B:48:0x0001, B:53:0x000a, B:55:0x000e, B:56:0x0017, B:58:0x002d, B:63:0x0035, B:67:0x004b, B:69:0x0053, B:72:0x005a, B:79:0x009d, B:81:0x00a3, B:82:0x00aa, B:73:0x005f, B:75:0x007a, B:77:0x0082, B:65:0x003d), top: B:88:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.graphics.Bitmap getBitmap(java.lang.String r6, android.graphics.BitmapFactory.Options r7, boolean r8, int r9) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Lb5
            r1 = 0
            if (r0 == 0) goto La
            monitor-exit(r5)
            return r1
        La:
            boolean r0 = r5.isElderMode     // Catch: java.lang.Throwable -> Lb5
            if (r0 == r8) goto L17
            r5.isElderMode = r8     // Catch: java.lang.Throwable -> Lb5
            com.jingdong.common.unification.uniconfig.UnIconCache r0 = com.jingdong.common.unification.uniconfig.UnIconCache.getInstance()     // Catch: java.lang.Throwable -> Lb5
            r0.clear()     // Catch: java.lang.Throwable -> Lb5
        L17:
            boolean r0 = r5.isSmall(r9)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r0 = r5.getIdWithSwitch(r6, r0, r8)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r6 = r5.getNinePatchId(r6)     // Catch: java.lang.Throwable -> Lb5
            com.jingdong.common.unification.uniconfig.UnIconCache r2 = com.jingdong.common.unification.uniconfig.UnIconCache.getInstance()     // Catch: java.lang.Throwable -> Lb5
            com.jingdong.common.unification.uniconfig.IconConfigModel r2 = r2.get(r6, r0)     // Catch: java.lang.Throwable -> Lb5
            if (r2 == 0) goto L33
            android.graphics.Bitmap r3 = r2.bitmap     // Catch: java.lang.Throwable -> Lb5
            if (r3 == 0) goto L33
            monitor-exit(r5)
            return r3
        L33:
            if (r2 == 0) goto L3d
            java.lang.String r3 = r2.path     // Catch: java.lang.Throwable -> Lb5
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> Lb5
            if (r3 == 0) goto L49
        L3d:
            com.jingdong.common.unification.uniconfig.DataBaseController r2 = com.jingdong.common.unification.uniconfig.DataBaseController.getController()     // Catch: java.lang.Throwable -> Lb5
            boolean r9 = r5.isSmall(r9)     // Catch: java.lang.Throwable -> Lb5
            com.jingdong.common.unification.uniconfig.IconConfigModel r2 = r2.getIconConfigModel(r6, r8, r9)     // Catch: java.lang.Throwable -> Lb5
        L49:
            if (r2 == 0) goto L5f
            java.lang.String r9 = r2.path     // Catch: java.lang.Throwable -> Lb5
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> Lb5
            if (r9 != 0) goto L5f
            boolean r9 = r5.fileIsExists(r2)     // Catch: java.lang.Throwable -> Lb5
            if (r9 != 0) goto L5a
            goto L5f
        L5a:
            android.graphics.Bitmap r7 = r5.createBitmap(r2, r7)     // Catch: java.lang.Throwable -> Lb5
            goto L9d
        L5f:
            com.jingdong.common.unification.uniconfig.DefaultDataController r9 = com.jingdong.common.unification.uniconfig.DefaultDataController.getController()     // Catch: java.lang.Throwable -> Lb5
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r3.<init>()     // Catch: java.lang.Throwable -> Lb5
            r3.append(r0)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r4 = ".png"
            r3.append(r4)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lb5
            android.graphics.Bitmap r9 = r9.getAssetsBitmap(r3, r1, r7)     // Catch: java.lang.Throwable -> Lb5
            if (r9 != 0) goto L9c
            java.lang.String r3 = "_var_"
            boolean r3 = r6.contains(r3)     // Catch: java.lang.Throwable -> Lb5
            if (r3 != 0) goto L9c
            com.jingdong.common.unification.uniconfig.DefaultDataController r9 = com.jingdong.common.unification.uniconfig.DefaultDataController.getController()     // Catch: java.lang.Throwable -> Lb5
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb5
            r3.<init>()     // Catch: java.lang.Throwable -> Lb5
            r3.append(r6)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r4 = ".png"
            r3.append(r4)     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lb5
            android.graphics.Bitmap r7 = r9.getAssetsBitmap(r3, r1, r7)     // Catch: java.lang.Throwable -> Lb5
            goto L9d
        L9c:
            r7 = r9
        L9d:
            android.graphics.Bitmap r7 = r5.scaleBitmap(r7, r6, r8)     // Catch: java.lang.Throwable -> Lb5
            if (r2 != 0) goto Laa
            com.jingdong.common.unification.uniconfig.IconConfigModel r2 = new com.jingdong.common.unification.uniconfig.IconConfigModel     // Catch: java.lang.Throwable -> Lb5
            r2.<init>()     // Catch: java.lang.Throwable -> Lb5
            r2.id = r0     // Catch: java.lang.Throwable -> Lb5
        Laa:
            r2.bitmap = r7     // Catch: java.lang.Throwable -> Lb5
            com.jingdong.common.unification.uniconfig.UnIconCache r8 = com.jingdong.common.unification.uniconfig.UnIconCache.getInstance()     // Catch: java.lang.Throwable -> Lb5
            r8.update(r6, r0, r2)     // Catch: java.lang.Throwable -> Lb5
            monitor-exit(r5)
            return r7
        Lb5:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.UnIconConfigController.getBitmap(java.lang.String, android.graphics.BitmapFactory$Options, boolean, int):android.graphics.Bitmap");
    }

    public IconConfigModel getIconConfigModel(String str, boolean z, int i2) {
        IconExtraConfigModel iconExtraConfigModel;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        IconConfigModel iconConfigModel = UnIconCache.getInstance().get(str, getIdWithSwitch(str, isSmall(i2), z));
        return (iconConfigModel == null || (iconExtraConfigModel = iconConfigModel.config) == null || (TextUtils.isEmpty(iconExtraConfigModel.textColor) && TextUtils.isEmpty(iconConfigModel.config.edge))) ? DataBaseController.getController().getIconConfigModel(str, z, isSmall(i2)) : iconConfigModel;
    }

    public String getIconInfoJson(String str, boolean z, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String idWithSwitch = getIdWithSwitch(str, isSmall(i2), z);
        IconConfigModel iconConfigModel = UnIconCache.getInstance().get(str, idWithSwitch);
        if (iconConfigModel != null) {
            return JDJSON.toJSONString(iconConfigModel);
        }
        IconConfigModel iconConfigModel2 = DataBaseController.getController().getIconConfigModel(str, z, isSmall(i2));
        if (iconConfigModel2 == null) {
            iconConfigModel2 = new IconConfigModel();
            iconConfigModel2.id = idWithSwitch;
            UnIconCache.getInstance().update(str, idWithSwitch, iconConfigModel2);
        }
        return JDJSON.toJSONString(iconConfigModel2);
    }

    public String getIconPath4DraweeView(String str, boolean z, int i2) {
        String iconPath;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String idWithSwitch = getIdWithSwitch(str, isSmall(i2), z);
        IconConfigModel iconConfigModel = UnIconCache.getInstance().get(str, idWithSwitch);
        if (iconConfigModel != null) {
            if (!TextUtils.isEmpty(iconConfigModel.cachePath)) {
                return iconConfigModel.cachePath;
            }
            if (!TextUtils.isEmpty(iconConfigModel.path)) {
                String str2 = "file://" + iconConfigModel.path;
                iconConfigModel.cachePath = str2;
                return str2;
            }
        }
        IconConfigModel iconConfigModel2 = DataBaseController.getController().getIconConfigModel(str, z, isSmall(i2));
        if (iconConfigModel2 != null && !TextUtils.isEmpty(iconConfigModel2.path) && fileIsExists(iconConfigModel2)) {
            if (TextUtils.isEmpty(iconConfigModel2.path)) {
                iconPath = iconConfigModel2.url;
            } else {
                iconPath = "file://" + iconConfigModel2.path;
            }
        } else {
            iconPath = DefaultDataController.getController().getIconPath(str);
            if (TextUtils.isEmpty(iconPath) && iconConfigModel2 != null) {
                iconPath = iconConfigModel2.url;
            }
        }
        if (iconConfigModel2 == null) {
            iconConfigModel2 = new IconConfigModel();
            iconConfigModel2.id = idWithSwitch;
            UnIconCache.getInstance().update(str, idWithSwitch, iconConfigModel2);
        }
        iconConfigModel2.cachePath = iconPath;
        return iconPath;
    }

    public String getIconUrl(String str, boolean z, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        IconConfigModel iconConfigModel = UnIconCache.getInstance().get(str, getIdWithSwitch(str, isSmall(i2), z));
        if (iconConfigModel != null) {
            return iconConfigModel.url;
        }
        return DataBaseController.getController().getIconConfigModel(str, z, isSmall(i2)).url;
    }

    public String getTextColor(String str, boolean z, int i2) {
        IconExtraConfigModel iconExtraConfigModel;
        IconExtraConfigModel iconExtraConfigModel2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        IconConfigModel iconConfigModel = UnIconCache.getInstance().get(str, getIdWithSwitch(str, isSmall(i2), z));
        if (iconConfigModel == null || (iconExtraConfigModel2 = iconConfigModel.config) == null || TextUtils.isEmpty(iconExtraConfigModel2.textColor)) {
            iconConfigModel = DataBaseController.getController().getIconConfigModel(str, z, isSmall(i2));
        }
        if (iconConfigModel == null || (iconExtraConfigModel = iconConfigModel.config) == null) {
            return null;
        }
        return iconExtraConfigModel.textColor;
    }

    public IconExtraConfigModel getTextConfig(String str, boolean z, int i2) {
        IconExtraConfigModel iconExtraConfigModel;
        IconExtraConfigModel iconExtraConfigModel2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        IconConfigModel iconConfigModel = UnIconCache.getInstance().get(str, getIdWithSwitch(str, isSmall(i2), z));
        if (iconConfigModel == null || (iconExtraConfigModel2 = iconConfigModel.config) == null || (TextUtils.isEmpty(iconExtraConfigModel2.textColor) && TextUtils.isEmpty(iconConfigModel.config.edge))) {
            iconConfigModel = DataBaseController.getController().getIconConfigModel(str, z, isSmall(i2));
        }
        if (iconConfigModel == null || (iconExtraConfigModel = iconConfigModel.config) == null) {
            return null;
        }
        return iconExtraConfigModel;
    }
}
