package com.jingdong.sdk.platform.floor.isv;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.base.ICloneableData;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.adapterView.StoreMananger;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public class FloorCooperateManager {
    public static final String ISV_NAME = "isvoptions";
    private static List<String> mOptions;
    @Deprecated
    private IBuildFloorListener mBuildFloorListeners;
    private final String mModuleName;
    private List<BaseLoadFloorOption> mbaseLoadFloorOptions;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public interface IOptionIterator {
        void onIter(String str, Class<? extends BaseFloor> cls);
    }

    public FloorCooperateManager(String str) {
        this.mModuleName = str;
    }

    private void dealCooperateFloor(Context context, ICloneableData iCloneableData) {
        dealCooperateFloor(context, iCloneableData, null);
    }

    private IBaseView getDynBaseView(Context context, String str, String str2) {
        try {
            return DefaultDynBaseView.createInstance(context, ISVConst.getSystemCode(this.mModuleName), str, new JSONObject(str2));
        } catch (JSONException unused) {
            reportOptionError(null, "getDynBaseView");
            return null;
        }
    }

    private String getExtInfo(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("step", str);
            jSONObject.put("class", str2);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0008, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jingdong.sdk.platform.floor.isv.IBaseView getLocalBaseView(android.view.ViewGroup r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            java.util.List<com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption> r0 = r5.mbaseLoadFloorOptions
            if (r0 == 0) goto L88
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L88
            java.lang.Object r1 = r0.next()
            com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption r1 = (com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption) r1
            if (r1 == 0) goto L8
            java.util.List r2 = r1.getRegistViews()     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L41
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L7e
        L20:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L7e
            if (r3 == 0) goto L41
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L7e
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L7e
            boolean r3 = android.text.TextUtils.equals(r3, r7)     // Catch: java.lang.Throwable -> L7e
            if (r3 == 0) goto L20
            com.jingdong.sdk.platform.floor.isv.IBaseView r2 = r1.createView(r6, r7)     // Catch: java.lang.Throwable -> L7e
            r2.onCreateView(r6)     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L40
            if (r8 == 0) goto L40
            r2.onBindData(r8)     // Catch: java.lang.Throwable -> L7e
        L40:
            return r2
        L41:
            java.util.HashMap r2 = r1.getViewList()     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L8
            java.util.Set r2 = r2.entrySet()     // Catch: java.lang.Throwable -> L7e
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L7e
        L4f:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L7e
            if (r3 == 0) goto L8
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L7e
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Throwable -> L7e
            java.lang.Object r4 = r3.getKey()     // Catch: java.lang.Throwable -> L7e
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch: java.lang.Throwable -> L7e
            boolean r4 = android.text.TextUtils.equals(r4, r7)     // Catch: java.lang.Throwable -> L7e
            if (r4 == 0) goto L4f
            java.lang.Object r2 = r3.getValue()     // Catch: java.lang.Throwable -> L7e
            java.lang.Class r2 = (java.lang.Class) r2     // Catch: java.lang.Throwable -> L7e
            if (r2 == 0) goto L8
            java.lang.Object r2 = r2.newInstance()     // Catch: java.lang.Throwable -> L7e
            com.jingdong.sdk.platform.floor.isv.IBaseView r2 = (com.jingdong.sdk.platform.floor.isv.IBaseView) r2     // Catch: java.lang.Throwable -> L7e
            r2.onCreateView(r6)     // Catch: java.lang.Throwable -> L7e
            if (r8 == 0) goto L7d
            r2.onBindData(r8)     // Catch: java.lang.Throwable -> L7e
        L7d:
            return r2
        L7e:
            r2 = move-exception
            java.lang.String r3 = "getLocalBaseView"
            r5.reportOptionError(r1, r3)
            com.jingdong.sdk.platform.utils.PlatformTools.catchExceptionAndReportToBugly(r2)
            goto L8
        L88:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.getLocalBaseView(android.view.ViewGroup, java.lang.String, java.lang.String):com.jingdong.sdk.platform.floor.isv.IBaseView");
    }

    private void iteratorOption(BaseLoadFloorOption baseLoadFloorOption, IOptionIterator iOptionIterator) {
        if (baseLoadFloorOption == null) {
            return;
        }
        try {
            HashMap<String, Class<? extends BaseFloor>> floorClass = baseLoadFloorOption.getFloorClass();
            if (floorClass == null || floorClass.size() <= 0) {
                return;
            }
            for (Map.Entry<String, Class<? extends BaseFloor>> entry : floorClass.entrySet()) {
                if (iOptionIterator != null && entry != null) {
                    iOptionIterator.onIter(entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e2) {
            PlatformTools.catchExceptionAndReportToBugly(e2);
            reportOptionError(baseLoadFloorOption, "iteratorOption");
        }
    }

    private void loadOption(Context context, ICloneableData iCloneableData, IBaseCooperateExt iBaseCooperateExt, boolean z, String str) throws IllegalAccessException, InstantiationException {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Object newInstance = context.getApplicationContext().getClassLoader().loadClass(str).newInstance();
                if (newInstance instanceof BaseLoadFloorOption) {
                    BaseLoadFloorOption baseLoadFloorOption = (BaseLoadFloorOption) newInstance;
                    if (iCloneableData != null) {
                        baseLoadFloorOption.mData = iCloneableData.cloneData();
                    }
                    if (iBaseCooperateExt != null) {
                        baseLoadFloorOption.cooperateExt = iBaseCooperateExt;
                    }
                    registerCooperateFloor((BaseLoadFloorOption) newInstance, z);
                }
            } catch (ClassNotFoundException e2) {
                PlatformTools.catchExceptionAndReportToBugly(e2);
                reportCrash(e2);
            }
        }
    }

    public static void preLoadClass(Context context) {
        String readLine;
        try {
            ArrayList arrayList = new ArrayList();
            String[] list = context.getAssets().list(ISV_NAME);
            if (list != null) {
                for (String str : list) {
                    String[] list2 = context.getAssets().list(ISV_NAME + "/" + str);
                    if (list2 != null) {
                        for (String str2 : list2) {
                            InputStream open = context.getAssets().open(ISV_NAME + "/" + str + "/" + str2);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
                            do {
                                readLine = bufferedReader.readLine();
                                if (!TextUtils.isEmpty(readLine)) {
                                    arrayList.add(readLine);
                                }
                            } while (readLine != null);
                            open.close();
                        }
                    }
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                try {
                    context.getApplicationContext().getClassLoader().loadClass((String) it.next());
                } catch (ClassNotFoundException e2) {
                    PlatformTools.catchExceptionAndReportToBugly(e2);
                }
            }
        } catch (Exception e3) {
            PlatformTools.catchExceptionAndReportToBugly(e3);
        }
    }

    private void preLoadOptionInfo(Context context, ArrayList<String> arrayList) throws IOException {
        String readLine;
        if (context == null) {
            return;
        }
        String str = "isvoptions/" + this.mModuleName;
        String[] list = context.getAssets().list(str);
        if (list != null) {
            for (String str2 : list) {
                InputStream open = context.getAssets().open(str + "/" + str2);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
                do {
                    readLine = bufferedReader.readLine();
                    if (!TextUtils.isEmpty(readLine)) {
                        arrayList.add(readLine);
                    }
                } while (readLine != null);
                open.close();
            }
        }
    }

    private void reportCrash(Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("app", "app");
        hashMap.put("m", ISVConst.getModelType(this.mModuleName));
        hashMap.put("mid", "");
        hashMap.put("bid", "");
        hashMap.put("t", 1);
        hashMap.put("st", 11);
        com.jingdong.sdk.lib.isvmonitor.a.a.b(exc, getClass().getName(), hashMap);
    }

    private void reportOptionError(BaseLoadFloorOption baseLoadFloorOption, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("app", "app");
        hashMap.put("m", ISVConst.getModelType(this.mModuleName));
        hashMap.put("mid", "");
        hashMap.put("bid", "");
        hashMap.put("t", 1);
        hashMap.put("st", 11);
        if (baseLoadFloorOption != null) {
            hashMap.put("ext", getExtInfo(str, baseLoadFloorOption.getClass().getName()));
        }
        com.jingdong.sdk.lib.isvmonitor.a.a.a(hashMap);
    }

    public void buildTemplate(ArrayList<BaseTemplateEntity> arrayList) {
        List<BaseLoadFloorOption> list;
        if (arrayList == null || arrayList.isEmpty() || (list = this.mbaseLoadFloorOptions) == null || list.isEmpty()) {
            return;
        }
        for (BaseLoadFloorOption baseLoadFloorOption : this.mbaseLoadFloorOptions) {
            if (baseLoadFloorOption != null && baseLoadFloorOption.getFloorClass() != null) {
                try {
                    baseLoadFloorOption.mHasFloor = false;
                    baseLoadFloorOption.onStartBuildTemplate();
                } catch (Throwable unused) {
                    reportOptionError(baseLoadFloorOption, "onStartBuildTemplate");
                }
            }
        }
        Iterator<BaseTemplateEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            BaseTemplateEntity next = it.next();
            if (next != null && !TextUtils.isEmpty(next.mId)) {
                Iterator<BaseLoadFloorOption> it2 = this.mbaseLoadFloorOptions.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    BaseLoadFloorOption next2 = it2.next();
                    if (next2 != null && next2.getFloorClass() != null && next2.getFloorClass().containsKey(next.mId)) {
                        if (TextUtils.isEmpty(next.bId)) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("app", "app");
                            hashMap.put("m", ISVConst.getModelType(this.mModuleName));
                            hashMap.put("mid", next.mId);
                            hashMap.put("bid", "");
                            hashMap.put("t", 1);
                            hashMap.put("st", 13);
                            com.jingdong.sdk.lib.isvmonitor.a.a.a(hashMap);
                        }
                        next.mExtData = next2.mData;
                        next2.mHasFloor = true;
                        try {
                            next.addToFloor(next2.buildTemplate(next));
                        } catch (Throwable unused2) {
                            reportOptionError(next2, "buildTemplate");
                        }
                    }
                }
            }
        }
    }

    public IBaseView createView(ViewGroup viewGroup, String str, boolean z, String str2) {
        if (viewGroup == null) {
            return null;
        }
        if (z) {
            return getDynBaseView(viewGroup.getContext(), str, str2);
        }
        return getLocalBaseView(viewGroup, str, str2);
    }

    public String dumpFloors() {
        final StringBuilder sb = new StringBuilder();
        for (BaseLoadFloorOption baseLoadFloorOption : this.mbaseLoadFloorOptions) {
            if (baseLoadFloorOption != null) {
                iteratorOption(baseLoadFloorOption, new IOptionIterator() { // from class: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.2
                    @Override // com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.IOptionIterator
                    public void onIter(String str, Class<? extends BaseFloor> cls) {
                        StringBuilder sb2 = sb;
                        sb2.append("mId:");
                        sb2.append(str);
                        sb2.append(" -> ");
                        sb2.append(cls.toString());
                        sb2.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    }
                });
            }
        }
        return sb.toString();
    }

    @Deprecated
    public void init(Context context, String str, IBuildFloorListener iBuildFloorListener) {
        dealCooperateFloor(context, null);
    }

    public void onScrollStateChanged(int i2) {
        List<BaseLoadFloorOption> list = this.mbaseLoadFloorOptions;
        if (list != null) {
            for (BaseLoadFloorOption baseLoadFloorOption : list) {
                if (baseLoadFloorOption != null) {
                    try {
                        baseLoadFloorOption.onScrollStateChanged(i2);
                    } catch (Throwable unused) {
                        reportOptionError(baseLoadFloorOption, "onMainViewScrolled");
                    }
                }
            }
        }
    }

    @Deprecated
    public void onViewScrolled(Activity activity, int i2, Object obj) {
        List<BaseLoadFloorOption> list = this.mbaseLoadFloorOptions;
        if (list != null) {
            try {
                for (BaseLoadFloorOption baseLoadFloorOption : list) {
                    if (baseLoadFloorOption != null && baseLoadFloorOption.mHasFloor) {
                        baseLoadFloorOption.onMainViewScrolled(activity, i2);
                    }
                }
            } catch (Throwable th) {
                PlatformTools.catchExceptionAndReportToBugly(th);
            }
        }
    }

    @Deprecated
    public void reBuildFloor(String str, String str2, Object obj) {
        IBuildFloorListener iBuildFloorListener = this.mBuildFloorListeners;
        if (iBuildFloorListener != null) {
            iBuildFloorListener.onRebuildFloor(str2, obj);
        }
    }

    public void registOption(Context context, String str) {
        registOption(context, str, null, null);
    }

    public void registerCooperateFloor(BaseLoadFloorOption baseLoadFloorOption, boolean z) {
        if (baseLoadFloorOption == null) {
            return;
        }
        if (this.mbaseLoadFloorOptions == null) {
            this.mbaseLoadFloorOptions = new ArrayList();
        }
        if (this.mbaseLoadFloorOptions.contains(baseLoadFloorOption)) {
            return;
        }
        this.mbaseLoadFloorOptions.add(baseLoadFloorOption);
        if (z) {
            return;
        }
        PlatformHelper.getFloorManager(this.mModuleName);
        iteratorOption(baseLoadFloorOption, new IOptionIterator
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0028: INVOKE 
              (r1v0 'this' com.jingdong.sdk.platform.floor.isv.FloorCooperateManager A[IMMUTABLE_TYPE, THIS])
              (r2v0 'baseLoadFloorOption' com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption)
              (wrap: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager$IOptionIterator : 0x0025: CONSTRUCTOR 
              (r1v0 'this' com.jingdong.sdk.platform.floor.isv.FloorCooperateManager A[IMMUTABLE_TYPE, THIS])
              (r3 I:com.jingdong.sdk.platform.floor.FloorManager A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.sdk.platform.floor.isv.FloorCooperateManager, com.jingdong.sdk.platform.floor.FloorManager):void (m), WRAPPED] (LINE:6) call: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.1.<init>(com.jingdong.sdk.platform.floor.isv.FloorCooperateManager, com.jingdong.sdk.platform.floor.FloorManager):void type: CONSTRUCTOR)
             type: DIRECT call: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.iteratorOption(com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption, com.jingdong.sdk.platform.floor.isv.FloorCooperateManager$IOptionIterator):void A[MD:(com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption, com.jingdong.sdk.platform.floor.isv.FloorCooperateManager$IOptionIterator):void (m)] (LINE:6) in method: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.registerCooperateFloor(com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption, boolean):void, file: classes10.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            if (r2 != 0) goto L3
            return
        L3:
            java.util.List<com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption> r0 = r1.mbaseLoadFloorOptions
            if (r0 != 0) goto Le
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.mbaseLoadFloorOptions = r0
        Le:
            java.util.List<com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption> r0 = r1.mbaseLoadFloorOptions
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L2b
            java.util.List<com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption> r0 = r1.mbaseLoadFloorOptions
            r0.add(r2)
            if (r3 != 0) goto L2b
            java.lang.String r3 = r1.mModuleName
            com.jingdong.sdk.platform.floor.FloorManager r3 = com.jingdong.sdk.platform.PlatformHelper.getFloorManager(r3)
            com.jingdong.sdk.platform.floor.isv.FloorCooperateManager$1 r0 = new com.jingdong.sdk.platform.floor.isv.FloorCooperateManager$1
            r0.<init>()
            r1.iteratorOption(r2, r0)
        L2b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.platform.floor.isv.FloorCooperateManager.registerCooperateFloor(com.jingdong.sdk.platform.floor.isv.BaseLoadFloorOption, boolean):void");
    }

    public void removeBuilder(String str) {
        if (this.mBuildFloorListeners != null) {
            TextUtils.isEmpty(str);
        }
    }

    public void updateCooperData(ICloneableData iCloneableData) {
        List<BaseLoadFloorOption> list = this.mbaseLoadFloorOptions;
        if (list == null || iCloneableData == null) {
            return;
        }
        for (BaseLoadFloorOption baseLoadFloorOption : list) {
            if (baseLoadFloorOption != null) {
                try {
                    baseLoadFloorOption.mData = iCloneableData.cloneData();
                } catch (Throwable unused) {
                    reportOptionError(baseLoadFloorOption, "onMainViewScrolled");
                }
            }
        }
    }

    private void dealCooperateFloor(Context context, ICloneableData iCloneableData, IBaseCooperateExt iBaseCooperateExt) {
        boolean z;
        if (context == null) {
            return;
        }
        try {
            new ArrayList();
            if (mOptions == null) {
                mOptions = new ArrayList();
            }
            ArrayList<String> options = StoreMananger.getOptionStore(this.mModuleName).getOptions();
            if (options.isEmpty()) {
                preLoadOptionInfo(context, options);
            }
            Iterator<String> it = options.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (mOptions.contains(next)) {
                    z = true;
                } else {
                    mOptions.add(next);
                    z = false;
                }
                loadOption(context, iCloneableData, iBaseCooperateExt, z, next);
            }
        } catch (Exception e2) {
            PlatformTools.catchExceptionAndReportToBugly(e2);
            reportCrash(e2);
        }
    }

    public void init(Context context, ICloneableData iCloneableData) {
        dealCooperateFloor(context, iCloneableData);
    }

    public void registOption(Context context, String str, ICloneableData iCloneableData, IBaseCooperateExt iBaseCooperateExt) {
        boolean z;
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (mOptions.contains(str)) {
            z = true;
        } else {
            mOptions.add(str);
            z = false;
        }
        try {
            loadOption(context, iCloneableData, iBaseCooperateExt, z, str);
        } catch (Exception e2) {
            PlatformTools.catchExceptionAndReportToBugly(e2);
            reportCrash(e2);
        }
    }

    public void init(Context context, ICloneableData iCloneableData, IBaseCooperateExt iBaseCooperateExt) {
        dealCooperateFloor(context, iCloneableData, iBaseCooperateExt);
    }

    public void onViewScrolled(Activity activity, int i2) {
        List<BaseLoadFloorOption> list = this.mbaseLoadFloorOptions;
        if (list != null) {
            for (BaseLoadFloorOption baseLoadFloorOption : list) {
                if (baseLoadFloorOption != null && baseLoadFloorOption.mHasFloor) {
                    try {
                        baseLoadFloorOption.onMainViewScrolled(activity, i2);
                    } catch (Throwable unused) {
                        reportOptionError(baseLoadFloorOption, "onMainViewScrolled");
                    }
                }
            }
        }
    }
}
