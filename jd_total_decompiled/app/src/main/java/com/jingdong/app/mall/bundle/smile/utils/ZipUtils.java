package com.jingdong.app.mall.bundle.smile.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.bundle.smile.model.SmileBean;
import com.jingdong.app.mall.bundle.smile.model.SmileJsonEntity;
import com.jingdong.app.mall.bundle.updownload.utils.FileUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes3.dex */
public class ZipUtils {
    private static final String TAG = "ZipUtils";

    /* JADX WARN: Removed duplicated region for block: B:73:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean fromSmile(Context context, String str, String str2, String str3, boolean z) {
        BufferedReader bufferedReader;
        SmileJsonEntity.Data data;
        ArrayList<SmileBean> arrayList;
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str3);
            try {
                StringBuilder sb = new StringBuilder();
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream2, "utf-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        try {
                            OKLog.e(TAG, e.toString());
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e6) {
                                    e6.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        throw th;
                    }
                }
                SmileJsonEntity smileJsonEntity = (SmileJsonEntity) GsonFactory.getInstance().formJson(sb.toString(), SmileJsonEntity.class);
                if (smileJsonEntity != null && (data = smileJsonEntity.data) != null && (arrayList = data.list) != null && !arrayList.isEmpty()) {
                    RouterUtil.deleteSmileys(context, z);
                    Iterator<SmileBean> it = smileJsonEntity.data.list.iterator();
                    while (it.hasNext()) {
                        SmileBean next = it.next();
                        next.directoryPath = str;
                        String concat = next.filePrefixName.concat(OrderISVUtil.MONEY_DECIMAL).concat(next.fileExtName);
                        next.name = concat;
                        next.path = str2.concat(concat);
                        next.desclocal = next.desc.zh_CN;
                        OKLog.d(TAG, "smiley path: " + next.path);
                        RouterUtil.saveSmileys(context, GsonFactory.getInstance().toJson(next), z);
                    }
                }
                RouterUtil.putDownloadMark(context, true);
                try {
                    fileInputStream2.close();
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                try {
                    bufferedReader.close();
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
                return true;
            } catch (Exception e9) {
                e = e9;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Exception e10) {
            e = e10;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
        }
    }

    public static Pair<String, String> upZipSmile(String str, String str2) throws Exception {
        String str3 = TAG;
        OKLog.d(str3, "upZipSmiley>>>  zipFilePath: " + str + " fileString: " + str2);
        String str4 = null;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            String str5 = null;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (!TextUtils.equals("../", name)) {
                        String str6 = str2 + File.separator + name;
                        if (nextEntry.isDirectory()) {
                            OKLog.d(TAG, "isDirectory filePath:" + str6);
                            FileUtils.newFile(str6).mkdirs();
                            str5 = str6;
                        } else {
                            OKLog.d(TAG, "is not DirectoryfilePath:" + str6);
                            File newFile = FileUtils.newFile(str6);
                            if (!newFile.getParentFile().exists()) {
                                newFile.getParentFile().mkdirs();
                            }
                            newFile.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                                fileOutputStream.flush();
                            }
                            fileOutputStream.close();
                        }
                        if (!TextUtils.isEmpty(str6) && str6.endsWith(FileService.CACHE_EXT_NAME_JSON) && TextUtils.isEmpty(str4)) {
                            str4 = str6;
                        }
                    }
                } else {
                    zipInputStream.close();
                    return new Pair<>(str4, str5);
                }
            }
        } else {
            OKLog.d(str3, "upZipSmiley>>>  zipFilePath is null  or fileString is null");
            return null;
        }
    }
}
