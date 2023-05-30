package com.jingdong.manto.pkg;

import android.content.Context;
import android.text.TextUtils;
import com.jd.aips.verify.VerifyParams;
import com.jd.libs.hybrid.HybridSDK;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.n;
import com.jingdong.manto.network.mantorequests.p;
import com.jingdong.manto.network.mantorequests.q;
import com.jingdong.manto.network.mantorequests.t;
import com.jingdong.manto.network.mantorequests.z;
import com.jingdong.manto.pkg.db.entity.DomainBlackListEntity;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;
import com.jingdong.manto.pkg.db.entity.PkgRecommend;
import com.jingdong.manto.sdk.api.ILogin;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.s;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class PkgManager {

    /* loaded from: classes16.dex */
    public interface DomainBlackListCallBack {
        void onError(Throwable th, JSONObject jSONObject);

        void onSuccess(DomainBlackListEntity domainBlackListEntity);
    }

    /* loaded from: classes16.dex */
    public interface PkgCollectionListCallBack {
        void onError(Throwable th);

        void onSuccess(List<PkgCollectEntity> list, int i2);
    }

    /* loaded from: classes16.dex */
    public interface PkgFavoCallBack {
        void onError(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes16.dex */
    public interface PkgHistoryListCallBack {
        void onError(Throwable th);

        void onSuccess(List<PkgHistoryEntity> list);
    }

    /* loaded from: classes16.dex */
    public interface RecommendListCallback {
        void onError(Throwable th, JSONObject jSONObject);

        void onSuccess(List<PkgRecommend> list);
    }

    /* loaded from: classes16.dex */
    public class a implements FilenameFilter {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f13967c;

        a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.f13967c = str3;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            int lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
            if (lastIndexOf > 0 && "jdapkg".equals(str.substring(lastIndexOf + 1))) {
                String[] split = str.split("\\_");
                if (split.length == 3) {
                    String substring = split[2].substring(0, split[2].lastIndexOf(OrderISVUtil.MONEY_DECIMAL));
                    if (this.a.equals(split[0]) && this.b.equals(substring) && !this.f13967c.equals(split[1])) {
                        return true;
                    }
                } else if (split.length == 4 && this.a.equals(split[0]) && this.b.equals(split[2]) && !this.f13967c.equals(split[1])) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes16.dex */
    public class b extends IMantoHttpListener {
        final /* synthetic */ PkgCollectionListCallBack a;

        b(PkgCollectionListCallBack pkgCollectionListCallBack) {
            this.a = pkgCollectionListCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            PkgCollectionListCallBack pkgCollectionListCallBack = this.a;
            if (pkgCollectionListCallBack != null) {
                pkgCollectionListCallBack.onError(th);
            }
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            ArrayList arrayList = null;
            if (optJSONObject == null) {
                PkgCollectionListCallBack pkgCollectionListCallBack = this.a;
                if (pkgCollectionListCallBack != null) {
                    pkgCollectionListCallBack.onError(null);
                    return;
                }
                return;
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                com.jingdong.manto.b.k().a();
            } else {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                    PkgCollectEntity pkgCollectEntity = new PkgCollectEntity();
                    pkgCollectEntity.appId = optJSONObject2.optString("app_id");
                    pkgCollectEntity.logo = optJSONObject2.optString("logo");
                    pkgCollectEntity.name = optJSONObject2.optString("name");
                    pkgCollectEntity.type = optJSONObject2.optString("type");
                    pkgCollectEntity.favorite = "1".equals(optJSONObject2.optString("is_collection"));
                    arrayList.add(pkgCollectEntity);
                }
                com.jingdong.manto.b.k().a(arrayList);
            }
            int optInt = optJSONObject.optInt("sum_page");
            PkgCollectionListCallBack pkgCollectionListCallBack2 = this.a;
            if (pkgCollectionListCallBack2 != null) {
                pkgCollectionListCallBack2.onSuccess(arrayList, optInt);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class c extends IMantoHttpListener {
        final /* synthetic */ PkgHistoryListCallBack a;

        c(PkgHistoryListCallBack pkgHistoryListCallBack) {
            this.a = pkgHistoryListCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            PkgHistoryListCallBack pkgHistoryListCallBack = this.a;
            if (pkgHistoryListCallBack != null) {
                pkgHistoryListCallBack.onError(th);
            }
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            ArrayList arrayList = null;
            if (optJSONObject == null) {
                PkgHistoryListCallBack pkgHistoryListCallBack = this.a;
                if (pkgHistoryListCallBack != null) {
                    pkgHistoryListCallBack.onError(null);
                    return;
                }
                return;
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                com.jingdong.manto.b.k().b();
            } else {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                    PkgHistoryEntity pkgHistoryEntity = new PkgHistoryEntity();
                    pkgHistoryEntity.appId = optJSONObject2.optString("app_id");
                    pkgHistoryEntity.logo = optJSONObject2.optString("logo");
                    pkgHistoryEntity.name = optJSONObject2.optString("name");
                    pkgHistoryEntity.type = optJSONObject2.optString("type");
                    pkgHistoryEntity.favorite = "1".equals(optJSONObject2.optString("is_collection"));
                    arrayList.add(pkgHistoryEntity);
                }
                com.jingdong.manto.b.k().b(arrayList);
            }
            PkgHistoryListCallBack pkgHistoryListCallBack2 = this.a;
            if (pkgHistoryListCallBack2 != null) {
                pkgHistoryListCallBack2.onSuccess(arrayList);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class d extends IMantoHttpListener {
        final /* synthetic */ RecommendListCallback a;

        d(RecommendListCallback recommendListCallback) {
            this.a = recommendListCallback;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            ArrayList arrayList = null;
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                RecommendListCallback recommendListCallback = this.a;
                if (recommendListCallback != null) {
                    recommendListCallback.onError(null, jSONObject);
                    return;
                }
                return;
            }
            if (optJSONArray != null && optJSONArray.length() > 0) {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    PkgRecommend pkgRecommend = new PkgRecommend();
                    pkgRecommend.name = optJSONObject.optString("name");
                    pkgRecommend.logo = optJSONObject.optString("logo");
                    pkgRecommend.description = optJSONObject.optString("description");
                    pkgRecommend.appId = optJSONObject.optString("appId");
                    arrayList.add(pkgRecommend);
                }
            }
            RecommendListCallback recommendListCallback2 = this.a;
            if (recommendListCallback2 != null) {
                recommendListCallback2.onSuccess(arrayList);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class e implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ PkgFavoCallBack f13968c;

        e(String str, String str2, PkgFavoCallBack pkgFavoCallBack) {
            this.a = str;
            this.b = str2;
            this.f13968c = pkgFavoCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                s.c(PkgManager.getPkgPath(com.jingdong.manto.b.k().c(this.a, this.b)));
                s.c(MantoUtils.generateAppUniqueId(this.a, this.b));
                com.jingdong.manto.t.b.a(this.a, this.b);
                com.jingdong.manto.b.k().b(this.a, this.b);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            PkgFavoCallBack pkgFavoCallBack = this.f13968c;
            if (pkgFavoCallBack != null) {
                pkgFavoCallBack.onSuccess();
            }
            com.jingdong.manto.n.c.a(this.a, this.b);
        }
    }

    /* loaded from: classes16.dex */
    public class f extends IMantoHttpListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ PkgFavoCallBack f13969c;

        f(String str, String str2, PkgFavoCallBack pkgFavoCallBack) {
            this.a = str;
            this.b = str2;
            this.f13969c = pkgFavoCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            PkgFavoCallBack pkgFavoCallBack = this.f13969c;
            if (pkgFavoCallBack != null) {
                pkgFavoCallBack.onError(th);
            }
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (!TextUtils.equals("0", jSONObject.optString("code"))) {
                PkgFavoCallBack pkgFavoCallBack = this.f13969c;
                if (pkgFavoCallBack != null) {
                    pkgFavoCallBack.onError(null);
                    return;
                }
                return;
            }
            try {
                com.jingdong.manto.b.k().c(this.a, this.b);
                com.jingdong.manto.b.k().b(this.a, this.b);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            PkgFavoCallBack pkgFavoCallBack2 = this.f13969c;
            if (pkgFavoCallBack2 != null) {
                pkgFavoCallBack2.onSuccess();
            }
            com.jingdong.manto.n.c.a(this.a, this.b);
        }
    }

    /* loaded from: classes16.dex */
    public class g extends IMantoHttpListener {
        final /* synthetic */ PkgCollectEntity a;
        final /* synthetic */ PkgFavoCallBack b;

        g(PkgCollectEntity pkgCollectEntity, PkgFavoCallBack pkgFavoCallBack) {
            this.a = pkgCollectEntity;
            this.b = pkgFavoCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            PkgFavoCallBack pkgFavoCallBack = this.b;
            if (pkgFavoCallBack != null) {
                pkgFavoCallBack.onError(th);
            }
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (!TextUtils.equals("0", jSONObject.optString("code"))) {
                PkgFavoCallBack pkgFavoCallBack = this.b;
                if (pkgFavoCallBack != null) {
                    pkgFavoCallBack.onError(null);
                    return;
                }
                return;
            }
            this.a.favorite = true;
            com.jingdong.manto.b.k().a(this.a);
            PkgFavoCallBack pkgFavoCallBack2 = this.b;
            if (pkgFavoCallBack2 != null) {
                pkgFavoCallBack2.onSuccess();
            }
        }
    }

    /* loaded from: classes16.dex */
    public class h extends IMantoHttpListener {
        final /* synthetic */ PkgCollectEntity a;
        final /* synthetic */ PkgFavoCallBack b;

        h(PkgCollectEntity pkgCollectEntity, PkgFavoCallBack pkgFavoCallBack) {
            this.a = pkgCollectEntity;
            this.b = pkgFavoCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            PkgFavoCallBack pkgFavoCallBack = this.b;
            if (pkgFavoCallBack != null) {
                pkgFavoCallBack.onError(th);
            }
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (!TextUtils.equals("0", jSONObject.optString("code"))) {
                PkgFavoCallBack pkgFavoCallBack = this.b;
                if (pkgFavoCallBack != null) {
                    pkgFavoCallBack.onError(null);
                    return;
                }
                return;
            }
            this.a.favorite = false;
            com.jingdong.manto.b.k().a(this.a);
            PkgFavoCallBack pkgFavoCallBack2 = this.b;
            if (pkgFavoCallBack2 != null) {
                pkgFavoCallBack2.onSuccess();
            }
        }
    }

    /* loaded from: classes16.dex */
    class i extends IMantoHttpListener {
        final /* synthetic */ DomainBlackListCallBack a;

        i(DomainBlackListCallBack domainBlackListCallBack) {
            this.a = domainBlackListCallBack;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            DomainBlackListCallBack domainBlackListCallBack = this.a;
            if (domainBlackListCallBack != null) {
                domainBlackListCallBack.onError(th, jSONObject);
            }
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            if (!"0".equals(jSONObject.optString("code"))) {
                DomainBlackListCallBack domainBlackListCallBack = this.a;
                if (domainBlackListCallBack != null) {
                    domainBlackListCallBack.onError(new IllegalArgumentException("code error"), jSONObject);
                    return;
                }
                return;
            }
            DomainBlackListEntity domainBlackListEntity = new DomainBlackListEntity();
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            StringBuilder sb = new StringBuilder();
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    if (i2 > 0) {
                        sb.append("@,@");
                    }
                    sb.append(optJSONArray.optString(i2));
                }
            }
            domainBlackListEntity.blackDomainList = sb.toString();
            com.jingdong.manto.b.k().a(domainBlackListEntity);
            DomainBlackListCallBack domainBlackListCallBack2 = this.a;
            if (domainBlackListCallBack2 != null) {
                domainBlackListCallBack2.onSuccess(domainBlackListEntity);
            }
        }
    }

    /* loaded from: classes16.dex */
    public class j extends IMantoHttpListener {
        final /* synthetic */ k a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f13970c;

        j(k kVar, String str, String str2) {
            this.a = kVar;
            this.b = str;
            this.f13970c = str2;
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
            super.onError(jSONObject, th);
            k kVar = this.a;
            if (kVar != null) {
                kVar.onError(th, jSONObject);
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("vapp_type", this.b);
                jSONObject2.put("code", "12");
                jSONObject2.put("description", "" + th.getMessage());
            } catch (Throwable unused) {
            }
            MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "\u6253\u5f00\u5f02\u5e38\u4e0a\u62a5", "applets_getappinfo_fail", this.f13970c, "", "", jSONObject2.toString(), "", null);
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            String optString;
            JSONArray optJSONArray;
            if (!"0".equals(jSONObject.optString("code"))) {
                k kVar = this.a;
                if (kVar != null) {
                    kVar.onError(null, null);
                    return;
                }
                return;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject == null) {
                k kVar2 = this.a;
                if (kVar2 != null) {
                    kVar2.onError(null, jSONObject);
                    return;
                }
                return;
            }
            PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("info");
            if (optJSONObject2 != null) {
                pkgDetailEntity.appId = optJSONObject2.optString("app_id");
                pkgDetailEntity.name = optJSONObject2.optString("name");
                pkgDetailEntity.description = optJSONObject2.optString("description");
                pkgDetailEntity.logo = optJSONObject2.optString("logo");
                pkgDetailEntity.serviceEmail = optJSONObject2.optString("service_email");
                pkgDetailEntity.servicePhone = optJSONObject2.optString("service_phone");
                pkgDetailEntity.ownerName = optJSONObject2.optString("owner_name");
                pkgDetailEntity.favorite = "1".equals(optJSONObject2.optString("is_collection"));
                pkgDetailEntity.charteredUrl = optJSONObject2.optString("chartered_url");
                pkgDetailEntity.venderId = optJSONObject2.optString("vender_id");
            }
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("version");
            if (optJSONObject3 != null) {
                pkgDetailEntity.build = optJSONObject3.optString(HybridSDK.APP_VERSION_CODE);
                pkgDetailEntity.versionName = optJSONObject3.optString("version_name");
                pkgDetailEntity.pkgUrl = optJSONObject3.optString("package_url");
                pkgDetailEntity.type = optJSONObject3.optString("type");
                pkgDetailEntity.zipUrl = optJSONObject3.optString("zip_url");
            }
            JSONObject optJSONObject4 = optJSONObject.optJSONObject(ThemeTitleConstant.TITLE_SETTING_DRAWABLE_ID);
            if (optJSONObject4 != null) {
                JSONObject optJSONObject5 = optJSONObject4.optJSONObject("domain");
                if (optJSONObject5 != null && (optJSONArray = optJSONObject5.optJSONArray("network")) != null && optJSONArray.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        if (i2 > 0) {
                            sb.append("@,@");
                        }
                        sb.append(optJSONArray.optString(i2));
                    }
                    pkgDetailEntity.domains = sb.toString();
                }
                pkgDetailEntity.categories = optJSONObject4.optString("category");
            }
            JSONObject optJSONObject6 = optJSONObject.optJSONObject("vendor");
            if (optJSONObject6 != null) {
                pkgDetailEntity.venderName = optJSONObject6.optString(CartConstant.KEY_VENDOR_NAME);
            }
            JSONObject optJSONObject7 = optJSONObject.optJSONObject("app_permission");
            if (optJSONObject7 != null) {
                pkgDetailEntity.permissions = optJSONObject7.optInt("native_permission", 0);
                JSONArray optJSONArray2 = optJSONObject7.optJSONArray("api_white_list");
                JSONArray optJSONArray3 = optJSONObject7.optJSONArray("api_black_list");
                if (optJSONArray2 != null) {
                    pkgDetailEntity.apiWhiteList = optJSONArray2.toString();
                }
                if (optJSONArray3 != null) {
                    pkgDetailEntity.apiBlackList = optJSONArray3.toString();
                }
            }
            String optString2 = optJSONObject.optString(VerifyParams.CONFIG_JSON);
            if (!MantoStringUtils.isEmpty(optString2)) {
                pkgDetailEntity.configJson = optString2;
            }
            JSONObject optJSONObject8 = optJSONObject.optJSONObject(MobileCertConstants.TEMPLATE);
            if (optJSONObject8 != null) {
                pkgDetailEntity.templateId = optJSONObject8.optString("templateId");
                pkgDetailEntity.templateVersion = optJSONObject8.optString("templateVersion");
            }
            JSONObject optJSONObject9 = optJSONObject.optJSONObject("packInfo");
            if (optJSONObject9 != null && (optString = optJSONObject9.optString("subPkgInfo")) != null) {
                pkgDetailEntity.subPkgInfos = optString;
            }
            com.jingdong.manto.b.k().a(pkgDetailEntity);
            k kVar3 = this.a;
            if (kVar3 != null) {
                kVar3.a(pkgDetailEntity);
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface k {
        void a(PkgDetailEntity pkgDetailEntity);

        void onError(Throwable th, JSONObject jSONObject);
    }

    /* loaded from: classes16.dex */
    public static class l {
        public String a;
        public String b;

        /* renamed from: c */
        public String f13971c;
    }

    public static void delOldPkg(File file) {
        if (file == null) {
            return;
        }
        try {
            String[] split = file.getName().split("\\_");
            if (split.length != 3) {
                return;
            }
            File[] listFiles = file.getParentFile().listFiles(new a(split[0], split[2].substring(0, split[2].lastIndexOf(OrderISVUtil.MONEY_DECIMAL)), split[1]));
            if (listFiles == null || listFiles.length <= 0) {
                return;
            }
            for (File file2 : listFiles) {
                s.b(file2);
            }
        } catch (Exception unused) {
        }
    }

    public static void deletePkg(String str, String str2, PkgFavoCallBack pkgFavoCallBack) {
        ILogin iLogin = (ILogin) com.jingdong.a.n(ILogin.class);
        if (iLogin != null ? iLogin.hasLogin() : false) {
            MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.l(str, str2), new f(str, str2, pkgFavoCallBack));
        } else {
            com.jingdong.manto.b.d().diskIO().execute(new e(str, str2, pkgFavoCallBack));
        }
    }

    public static void favoPkg(PkgCollectEntity pkgCollectEntity, PkgFavoCallBack pkgFavoCallBack) {
        MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.f(pkgCollectEntity.appId, pkgCollectEntity.type), new g(pkgCollectEntity, pkgFavoCallBack));
    }

    public static void getCollectionList(int i2, PkgCollectionListCallBack pkgCollectionListCallBack) {
        MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.i(i2), new b(pkgCollectionListCallBack));
    }

    public static void getHistoryList(PkgHistoryListCallBack pkgHistoryListCallBack) {
        MantoJDHttpHandler.commit(new p(), new c(pkgHistoryListCallBack));
    }

    public static String getPkgPath(PkgDetailEntity pkgDetailEntity) {
        StringBuilder sb;
        String str;
        if (pkgDetailEntity == null) {
            return null;
        }
        File file = new File(com.jingdong.manto.c.a().getFilesDir(), "manto");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(pkgDetailEntity.templateId) || TextUtils.isEmpty(pkgDetailEntity.templateVersion)) {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.appId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str = pkgDetailEntity.build;
        } else {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.templateId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str = pkgDetailEntity.templateVersion;
        }
        sb.append(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(pkgDetailEntity.type);
        sb.append(OrderISVUtil.MONEY_DECIMAL);
        sb.append("jdapkg");
        return sb.toString();
    }

    public static String getPkgPath(PkgDetailEntity pkgDetailEntity, String str) {
        StringBuilder sb;
        String str2;
        if (pkgDetailEntity == null) {
            return null;
        }
        File file = new File(com.jingdong.manto.c.a().getFilesDir(), "manto");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(pkgDetailEntity.templateId) || TextUtils.isEmpty(pkgDetailEntity.templateVersion)) {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.appId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str2 = pkgDetailEntity.build;
        } else {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.templateId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str2 = pkgDetailEntity.templateVersion;
        }
        sb.append(str2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(pkgDetailEntity.type);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str);
        sb.append(OrderISVUtil.MONEY_DECIMAL);
        sb.append("jdapkg");
        return sb.toString();
    }

    public static String getPkgZipPath(PkgDetailEntity pkgDetailEntity) {
        StringBuilder sb;
        String str;
        if (pkgDetailEntity == null) {
            return null;
        }
        File file = new File(com.jingdong.manto.c.a().getFilesDir(), "manto");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(pkgDetailEntity.templateId) || TextUtils.isEmpty(pkgDetailEntity.templateVersion)) {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.appId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str = pkgDetailEntity.build;
        } else {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.templateId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str = pkgDetailEntity.templateVersion;
        }
        sb.append(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(pkgDetailEntity.type);
        sb.append(".zip");
        return sb.toString();
    }

    public static String getPkgZipPath(PkgDetailEntity pkgDetailEntity, String str) {
        StringBuilder sb;
        String str2;
        if (pkgDetailEntity == null) {
            return null;
        }
        File file = new File(com.jingdong.manto.c.a().getFilesDir(), "manto");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(pkgDetailEntity.templateId) || TextUtils.isEmpty(pkgDetailEntity.templateVersion)) {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.appId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str2 = pkgDetailEntity.build;
        } else {
            sb = new StringBuilder();
            sb.append(file.getAbsolutePath());
            sb.append(File.separator);
            sb.append(pkgDetailEntity.templateId);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            str2 = pkgDetailEntity.templateVersion;
        }
        sb.append(str2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(pkgDetailEntity.type);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str);
        sb.append(".zip");
        return sb.toString();
    }

    public static void getRecommedList(RecommendListCallback recommendListCallback) {
        MantoJDHttpHandler.commit(new z(), new d(recommendListCallback));
    }

    public static l getSubPkg(PkgDetailEntity pkgDetailEntity, String str) {
        try {
            JSONObject optJSONObject = new JSONObject(pkgDetailEntity.subPkgInfos).optJSONObject(str);
            if (optJSONObject != null) {
                l lVar = new l();
                try {
                    lVar.a = optJSONObject.optString("packIndex");
                    lVar.b = optJSONObject.optString("packUrl");
                    lVar.f13971c = optJSONObject.optString("compressPackUrl");
                    return lVar;
                } catch (Exception unused) {
                    return lVar;
                }
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    public static void requestDomainBlackList(DomainBlackListCallBack domainBlackListCallBack) {
        MantoJDHttpHandler.commit(new n(), new i(domainBlackListCallBack));
    }

    public static void requestPkgDetail(String str, String str2, k kVar) {
        MantoJDHttpHandler.commit(TextUtils.equals(str2, "5") ? new q(str, str2) : new t(str, str2), new j(kVar, str2, str));
    }

    public static void startMantoApp(String str, String str2) {
        startMantoApp(str, str2, MantoProcessUtil.getContext());
    }

    public static void startMantoApp(String str, String str2, Context context) {
        LaunchParam launchParam = new LaunchParam();
        launchParam.appId = str;
        launchParam.debugType = str2;
        com.jingdong.a.p(launchParam, context);
    }

    public static void unFavoPkg(PkgCollectEntity pkgCollectEntity, PkgFavoCallBack pkgFavoCallBack) {
        MantoJDHttpHandler.commit(new com.jingdong.manto.network.mantorequests.g(pkgCollectEntity.appId, pkgCollectEntity.type), new h(pkgCollectEntity, pkgFavoCallBack));
    }
}
