package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgCollectEntity;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class MantoPreLaunchProcess implements Runnable {
    private String actionId;
    private volatile String appId;
    private String debugType;
    private String enterPath;
    private String extras;
    private final b launchInterface;
    private LaunchParam launchParam;
    private String logo;
    private String mpMode;
    private String pageAlias;
    public com.jingdong.manto.i.d referrer;
    private String scene;
    public String sourcePath;
    public String sourceSubPkgJson;

    /* loaded from: classes15.dex */
    public static class LaunchError implements Parcelable {
        public static final Parcelable.Creator<LaunchError> CREATOR = new a();
        public String appId;
        public int errorCode;
        public LaunchParam launchParam;
        public String msg;
        public String title;
        public String word;

        /* loaded from: classes15.dex */
        class a implements Parcelable.Creator<LaunchError> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public LaunchError createFromParcel(Parcel parcel) {
                return new LaunchError(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public LaunchError[] newArray(int i2) {
                return new LaunchError[i2];
            }
        }

        public LaunchError() {
            this.errorCode = -1;
        }

        protected LaunchError(Parcel parcel) {
            this.errorCode = -1;
            this.errorCode = parcel.readInt();
            this.title = parcel.readString();
            this.msg = parcel.readString();
            this.word = parcel.readString();
            this.appId = parcel.readString();
            this.launchParam = (LaunchParam) parcel.readParcelable(LaunchParam.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "{errorCode=" + this.errorCode + ", title='" + this.title + "', msg='" + this.msg + "', word='" + this.word + "'}";
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.errorCode);
            parcel.writeString(this.title);
            parcel.writeString(this.msg);
            parcel.writeString(this.word);
            parcel.writeString(this.appId);
            parcel.writeParcelable(this.launchParam, i2);
        }
    }

    /* loaded from: classes15.dex */
    class a implements PkgManager.k {
        final /* synthetic */ PkgDetailEntity a;

        /* renamed from: com.jingdong.manto.launch.MantoPreLaunchProcess$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0538a implements Runnable {
            final /* synthetic */ PkgCollectEntity a;

            RunnableC0538a(a aVar, PkgCollectEntity pkgCollectEntity) {
                this.a = pkgCollectEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jingdong.manto.b.k().a(this.a);
            }
        }

        /* loaded from: classes15.dex */
        class b implements Runnable {
            final /* synthetic */ PkgCollectEntity a;

            b(a aVar, PkgCollectEntity pkgCollectEntity) {
                this.a = pkgCollectEntity;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.jingdong.manto.b.k().a(this.a);
            }
        }

        a(PkgDetailEntity pkgDetailEntity) {
            this.a = pkgDetailEntity;
        }

        @Override // com.jingdong.manto.pkg.PkgManager.k
        public void a(PkgDetailEntity pkgDetailEntity) {
            com.jingdong.manto.i.c cVar = new com.jingdong.manto.i.c();
            cVar.a(this.a);
            cVar.b(pkgDetailEntity);
            cVar.f13083f = MantoPreLaunchProcess.this.enterPath;
            MantoPreLaunchProcess mantoPreLaunchProcess = MantoPreLaunchProcess.this;
            cVar.f13084g = mantoPreLaunchProcess.referrer;
            cVar.f13082e = mantoPreLaunchProcess.debugType;
            cVar.f13090m = MantoPreLaunchProcess.this.extras;
            cVar.f13091n = MantoPreLaunchProcess.this.scene;
            cVar.o = MantoPreLaunchProcess.this.pageAlias;
            cVar.p = MantoPreLaunchProcess.this.actionId;
            cVar.q = MantoPreLaunchProcess.this.mpMode;
            cVar.r = MantoPreLaunchProcess.this.logo;
            if (MantoPreLaunchProcess.this.launchInterface != null) {
                MantoPreLaunchProcess.this.launchInterface.a(cVar);
                com.jingdong.manto.b.d().diskIO().execute(new RunnableC0538a(this, new PkgCollectEntity(pkgDetailEntity.appId, pkgDetailEntity.type, pkgDetailEntity.name, pkgDetailEntity.logo, pkgDetailEntity.favorite, System.currentTimeMillis())));
            }
        }

        @Override // com.jingdong.manto.pkg.PkgManager.k
        public void onError(Throwable th, JSONObject jSONObject) {
            JSONObject optJSONObject;
            if (jSONObject != null && (optJSONObject = jSONObject.optJSONObject("error")) != null) {
                JSONObject optJSONObject2 = jSONObject.optJSONObject("info");
                int optInt = optJSONObject.optInt("code");
                if (optInt != 0) {
                    com.jingdong.manto.b.k().b(MantoPreLaunchProcess.this.appId, String.valueOf(MantoPreLaunchProcess.this.debugType));
                    LaunchError launchError = new LaunchError();
                    launchError.appId = MantoPreLaunchProcess.this.appId;
                    launchError.errorCode = optInt;
                    String optString = optJSONObject2 != null ? optJSONObject2.optString("name") : "";
                    launchError.title = optString;
                    if (TextUtils.isEmpty(optString)) {
                        launchError.title = optJSONObject.optString("title");
                    }
                    launchError.msg = optJSONObject.optString("msg");
                    launchError.word = optJSONObject.optString("word");
                    launchError.launchParam = MantoPreLaunchProcess.this.launchParam;
                    if (MantoPreLaunchProcess.this.launchInterface != null) {
                        MantoPreLaunchProcess.this.launchInterface.onLaunchError(launchError);
                        return;
                    } else {
                        c.a(launchError);
                        return;
                    }
                }
            }
            PkgDetailEntity c2 = com.jingdong.manto.b.k().c(MantoPreLaunchProcess.this.appId, String.valueOf(MantoPreLaunchProcess.this.debugType));
            if (c2 == null) {
                LaunchError launchError2 = new LaunchError();
                launchError2.errorCode = PkgDetailEntity.NO_INFO;
                launchError2.appId = MantoPreLaunchProcess.this.appId;
                launchError2.msg = "\u6682\u65f6\u65e0\u6cd5\u83b7\u53d6\u5c0f\u7a0b\u5e8f\u4fe1\u606f";
                launchError2.word = "\u8bf7\u67e5\u770b\u7f51\u7edc\u94fe\u63a5\u60c5\u51b5\uff0c\u5e76\u4f7f\u7528\u7cfb\u7edf\u9ed8\u8ba4\u65f6\u95f4\u540e\u518d\u6b21\u5c1d\u8bd5";
                launchError2.title = "\u8fd4\u56de";
                launchError2.launchParam = MantoPreLaunchProcess.this.launchParam;
                if (MantoPreLaunchProcess.this.launchInterface != null) {
                    MantoPreLaunchProcess.this.launchInterface.onLaunchError(launchError2);
                    return;
                } else {
                    c.a(launchError2);
                    return;
                }
            }
            com.jingdong.manto.i.c cVar = new com.jingdong.manto.i.c();
            cVar.a(this.a);
            cVar.b(c2);
            cVar.f13083f = MantoPreLaunchProcess.this.enterPath;
            MantoPreLaunchProcess mantoPreLaunchProcess = MantoPreLaunchProcess.this;
            cVar.f13084g = mantoPreLaunchProcess.referrer;
            cVar.f13082e = mantoPreLaunchProcess.debugType;
            cVar.f13090m = MantoPreLaunchProcess.this.extras;
            cVar.f13091n = MantoPreLaunchProcess.this.scene;
            cVar.o = MantoPreLaunchProcess.this.pageAlias;
            cVar.p = MantoPreLaunchProcess.this.actionId;
            cVar.q = MantoPreLaunchProcess.this.mpMode;
            cVar.r = MantoPreLaunchProcess.this.logo;
            if (MantoPreLaunchProcess.this.launchInterface != null) {
                MantoPreLaunchProcess.this.launchInterface.a(cVar);
                com.jingdong.manto.b.d().diskIO().execute(new b(this, new PkgCollectEntity(c2.appId, c2.type, c2.name, c2.logo, c2.favorite, System.currentTimeMillis())));
            }
        }
    }

    /* loaded from: classes15.dex */
    public interface b {
        void a(com.jingdong.manto.i.c cVar);

        void onLaunchError(LaunchError launchError);
    }

    public MantoPreLaunchProcess(LaunchParam launchParam, b bVar) {
        this.appId = launchParam.appId;
        this.launchInterface = bVar;
        this.debugType = launchParam.debugType;
        this.enterPath = launchParam.launchPath;
        this.referrer = launchParam.launchReferrer;
        this.sourcePath = launchParam.sourcePath;
        this.sourceSubPkgJson = launchParam.sourceSubPkgJson;
        this.extras = launchParam.extrasJson;
        this.scene = launchParam.scene;
        this.pageAlias = launchParam.pageAlias;
        this.actionId = launchParam.actionId;
        this.mpMode = launchParam.mpMode;
        this.logo = launchParam.logo;
        this.launchParam = launchParam;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!TextUtils.isEmpty(this.sourcePath)) {
            com.jingdong.manto.i.c cVar = new com.jingdong.manto.i.c();
            cVar.a = this.appId;
            cVar.f13084g = this.referrer;
            cVar.f13083f = this.enterPath;
            cVar.f13088k = this.sourcePath;
            cVar.f13089l = this.sourceSubPkgJson;
            cVar.f13082e = this.debugType;
            cVar.f13090m = this.extras;
            cVar.f13091n = this.scene;
            cVar.o = this.pageAlias;
            cVar.p = this.actionId;
            cVar.q = this.mpMode;
            cVar.r = this.logo;
            b bVar = this.launchInterface;
            if (bVar != null) {
                bVar.a(cVar);
                return;
            }
            return;
        }
        if (TextUtils.equals(this.debugType, "13")) {
            try {
                JSONObject jSONObject = new JSONObject(com.jingdong.manto.b.f().getSharedPreferences("mini-dev-mode", 0).getString("key", ""));
                com.jingdong.manto.i.c cVar2 = new com.jingdong.manto.i.c();
                cVar2.a = jSONObject.optString("appId");
                cVar2.b = jSONObject.optString("name");
                cVar2.f13081c = jSONObject.optString("logoUrl");
                cVar2.f13082e = "13";
                cVar2.f13090m = this.extras;
                cVar2.f13091n = this.scene;
                cVar2.o = this.pageAlias;
                cVar2.p = this.actionId;
                cVar2.q = this.mpMode;
                cVar2.r = this.logo;
                PkgDetailEntity pkgDetailEntity = new PkgDetailEntity();
                pkgDetailEntity.appId = cVar2.a;
                pkgDetailEntity.name = cVar2.b;
                pkgDetailEntity.logo = cVar2.f13081c;
                pkgDetailEntity.type = "13";
                pkgDetailEntity.build = jSONObject.optString(HybridSDK.APP_VERSION_CODE, "1");
                pkgDetailEntity.description = jSONObject.optString("description", "");
                pkgDetailEntity.pkgUrl = jSONObject.getString("pkgUrl");
                pkgDetailEntity.versionName = jSONObject.optString("version", "V1.0");
                cVar2.b(pkgDetailEntity);
                b bVar2 = this.launchInterface;
                if (bVar2 != null) {
                    bVar2.a(cVar2);
                    return;
                }
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        PkgManager.requestPkgDetail(this.appId, String.valueOf(this.debugType), new a(com.jingdong.manto.b.k().c(this.appId, String.valueOf(this.debugType))));
    }
}
