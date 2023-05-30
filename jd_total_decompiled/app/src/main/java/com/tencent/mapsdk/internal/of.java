package com.tencent.mapsdk.internal;

import android.util.Log;
import com.jd.dynamic.DYConstants;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.core.components.protocol.jce.conf.CSFileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateReq;
import com.tencent.mapsdk.core.components.protocol.jce.conf.FileUpdateRsp;
import com.tencent.mapsdk.core.components.protocol.jce.conf.SCFileUpdateRsp;
import com.tencent.mapsdk.internal.r6;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes9.dex */
public class of {

    /* renamed from: g  reason: collision with root package name */
    private static final String f16932g = "UTF-8";
    private WeakReference<qf> a;
    private List<FileUpdateReq> b;

    /* renamed from: c  reason: collision with root package name */
    private String f16933c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f16934e;

    /* renamed from: f  reason: collision with root package name */
    private int f16935f;

    private FileUpdateReq a(String str) {
        List<FileUpdateReq> list = this.b;
        if (list != null && !list.isEmpty()) {
            for (FileUpdateReq fileUpdateReq : this.b) {
                if (e7.c(fileUpdateReq.sName, str)) {
                    return fileUpdateReq;
                }
            }
        }
        return null;
    }

    private r6.c a(FileUpdateRsp fileUpdateRsp) {
        if (fileUpdateRsp == null || fileUpdateRsp.iRet != 0) {
            return null;
        }
        String str = fileUpdateRsp.sName;
        str.hashCode();
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1319508241:
                if (str.equals(j4.f16734n)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1091367180:
                if (str.equals(j4.f16733m)) {
                    c2 = 1;
                    break;
                }
                break;
            case -503063473:
                if (str.equals(j4.p)) {
                    c2 = 2;
                    break;
                }
                break;
            case 178735484:
                if (str.equals(j4.f16730j)) {
                    c2 = 3;
                    break;
                }
                break;
            case 204802075:
                if (str.equals(j4.f16732l)) {
                    c2 = 4;
                    break;
                }
                break;
            case 451944782:
                if (str.equals("poi_icon")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1366209438:
                if (str.equals(j4.f16729i)) {
                    c2 = 6;
                    break;
                }
                break;
            case 1864531656:
                if (str.equals(j4.o)) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 6:
            case 7:
                return a(fileUpdateRsp, this.f16933c, false);
            case 3:
                return a(fileUpdateRsp, this.d, true);
            case 5:
                return a(fileUpdateRsp, this.f16933c, true);
            default:
                return null;
        }
    }

    private r6.c a(FileUpdateRsp fileUpdateRsp, String str, boolean z) {
        if (fileUpdateRsp.iFileUpdated != 1) {
            return null;
        }
        String b = b(fileUpdateRsp.sName);
        File file = new File(str + b);
        r6.c a = a(b, fileUpdateRsp.sUpdateUrl, file);
        ma.c("net", "fileUpdateRsp.sName = " + fileUpdateRsp.sName);
        if (a != null) {
            a.b = fileUpdateRsp.sName;
            return a;
        }
        try {
            String a2 = va.a(file);
            ma.c("net", "fileMd5 = " + a2);
            if (!fileUpdateRsp.sMd5.equals(a2)) {
                r6.c cVar = new r6.c();
                cVar.b = fileUpdateRsp.sName;
                cVar.d = fileUpdateRsp.sMd5;
                cVar.f17186e = a2;
                ma.c("net", "error md5 1");
                return cVar;
            }
            if (z) {
                try {
                    ia.a(file, file.getParent());
                    file.delete();
                } catch (Throwable th) {
                    ma.b(th.getMessage());
                    r6.c cVar2 = new r6.c();
                    cVar2.b = fileUpdateRsp.sName;
                    ma.c("net", "error unzip");
                    return cVar2;
                }
            }
            WeakReference<qf> weakReference = this.a;
            if (weakReference != null && weakReference.get() != null) {
                this.a.get().a = true;
            }
            return null;
        } catch (Exception e2) {
            ma.b(e2.getMessage());
            r6.c cVar3 = new r6.c();
            cVar3.b = fileUpdateRsp.sName;
            ma.c("net", "error md5 2 " + e2.getMessage());
            return cVar3;
        }
    }

    private r6.c a(String str, String str2, File file) {
        NetResponse netResponse;
        InputStream inputStream;
        ma.c(la.f16818e, "\u5f00\u59cb\u4e0b\u8f7d[" + str + "]:" + str2);
        qa.c(la.f16818e, str2);
        FileOutputStream fileOutputStream = null;
        int i2 = 200;
        try {
            netResponse = NetManager.getInstance().builder().url(str2).doStream();
            try {
                i2 = netResponse.statusCode;
                inputStream = netResponse.dataStream;
                try {
                    try {
                        if (!file.exists()) {
                            File parentFile = file.getParentFile();
                            if (parentFile != null) {
                                parentFile.mkdirs();
                            }
                            file.createNewFile();
                        }
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                        try {
                            ga.a(inputStream, fileOutputStream2);
                            ga.a((Closeable) inputStream);
                            ga.a(fileOutputStream2);
                            ga.a((Closeable) netResponse.dataStream);
                            qa.a(la.f16818e, str2, "netError", Integer.valueOf(i2));
                            qa.f(la.f16818e, str2);
                            ma.c(la.f16818e, "\u4e0b\u8f7d[" + str + "]\u7ed3\u675f");
                            return null;
                        } catch (Exception e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            ma.b(Log.getStackTraceString(e));
                            r6.c cVar = new r6.c();
                            cVar.f17188g = i2;
                            qa.a(la.f16818e, str2, "error", Log.getStackTraceString(e));
                            ga.a((Closeable) inputStream);
                            ga.a(fileOutputStream);
                            if (netResponse != null) {
                                ga.a((Closeable) netResponse.dataStream);
                            }
                            qa.a(la.f16818e, str2, "netError", Integer.valueOf(i2));
                            qa.f(la.f16818e, str2);
                            ma.c(la.f16818e, "\u4e0b\u8f7d[" + str + "]\u7ed3\u675f");
                            return cVar;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            ga.a((Closeable) inputStream);
                            ga.a(fileOutputStream);
                            if (netResponse != null) {
                                ga.a((Closeable) netResponse.dataStream);
                            }
                            qa.a(la.f16818e, str2, "netError", Integer.valueOf(i2));
                            qa.f(la.f16818e, str2);
                            ma.c(la.f16818e, "\u4e0b\u8f7d[" + str + "]\u7ed3\u675f");
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
            }
        } catch (Exception e5) {
            e = e5;
            netResponse = null;
            inputStream = null;
        } catch (Throwable th4) {
            th = th4;
            netResponse = null;
            inputStream = null;
        }
    }

    private List<FileUpdateRsp> a(CSFileUpdateReq cSFileUpdateReq) {
        byte[] bArr;
        try {
            NetResponse configFileUpdate = ((x2) ((l3) l2.a(l3.class)).d()).configFileUpdate(b7.F(), b7.A(), b7.N(), b7.G(), this.f16934e, cSFileUpdateReq.toByteArray("UTF-8"));
            if (configFileUpdate != null && (bArr = configFileUpdate.data) != null) {
                m mVar = new m(bArr);
                mVar.a("UTF-8");
                SCFileUpdateRsp sCFileUpdateRsp = new SCFileUpdateRsp();
                sCFileUpdateRsp.readFrom(mVar);
                ma.c("net", "scrsp.iRet = " + sCFileUpdateRsp.iRet);
                if (sCFileUpdateRsp.iRet == 0) {
                    return sCFileUpdateRsp.vItems;
                }
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("rsp = ");
            sb.append(configFileUpdate != null ? Integer.valueOf(configFileUpdate.statusCode) : DYConstants.DY_NULL_STR);
            ma.c("net", sb.toString());
            return null;
        } catch (Exception e2) {
            ma.b(Log.getStackTraceString(e2));
            return null;
        }
    }

    private void a(r6.c cVar) {
        qf qfVar;
        WeakReference<h1>[] c2;
        h1 h1Var;
        WeakReference<qf> weakReference = this.a;
        if (weakReference == null || (qfVar = weakReference.get()) == null || (c2 = qfVar.c()) == null) {
            return;
        }
        for (int i2 = 0; i2 < c2.length; i2++) {
            if (c2[i2] != null && (h1Var = c2[i2].get()) != null && h1Var.l() != null && h1Var.l().A() != null) {
                v6 w = h1Var.l().A().w();
                if (w == null) {
                    return;
                }
                cVar.f17185c -= w.a();
                w.l().a(cVar);
            }
        }
    }

    private String b(String str) {
        return str.equals(j4.f16729i) ? j4.a : str.equals(j4.f16732l) ? j4.b : str.equals(j4.f16733m) ? j4.f16724c : str.equals(j4.f16734n) ? j4.f16725e : str.equals(j4.o) ? j4.f16726f : str.equals(j4.p) ? j4.f16728h : str;
    }

    public List<FileUpdateRsp> a(String str, String str2, String str3, CSFileUpdateReq cSFileUpdateReq, qf qfVar) {
        if (cSFileUpdateReq != null && qfVar != null) {
            this.b = cSFileUpdateReq.vItems;
            this.f16933c = str;
            this.d = str2;
            this.a = new WeakReference<>(qfVar);
            this.f16934e = str3;
            List<FileUpdateRsp> a = a(cSFileUpdateReq);
            ma.c("net", "rspList = " + a);
            if (a != null && !a.isEmpty()) {
                this.f16935f = a.size();
                for (FileUpdateRsp fileUpdateRsp : a) {
                    r6.c a2 = a(fileUpdateRsp);
                    if (a2 != null) {
                        FileUpdateReq a3 = a(a2.b);
                        a2.f17187f = a3 != null ? a3.iVersion : -1;
                        a2.f17185c = System.currentTimeMillis();
                        a(a2);
                        ma.c("net", "fileUpdateRsp = " + fileUpdateRsp);
                        ma.c("net", "failUpdate = " + a2);
                        return null;
                    }
                    this.f16935f--;
                }
                if (this.f16935f != 0) {
                    return null;
                }
                return a;
            }
        }
        return null;
    }
}
