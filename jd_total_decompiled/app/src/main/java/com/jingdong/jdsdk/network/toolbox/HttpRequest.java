package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.jd.framework.network.JDNetworkHelper;
import com.jd.framework.network.request.JDFileRequest;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;

/* loaded from: classes.dex */
public class HttpRequest implements HttpGroup.StopController {
    public HttpResponse httpResponse;
    protected HttpSetting httpSetting;
    private JDFileRequest jdFileRequest;
    private String jdRequestTag;
    private boolean stopFlag;

    public HttpRequest(HttpSetting httpSetting) {
        this.httpSetting = httpSetting;
    }

    public File findCachesFileByMd5() {
        FileService.Directory directory;
        if (OKLog.D) {
            OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- findCachesFileByMd5() -->> ");
        }
        String md5 = this.httpSetting.getMd5();
        int type = this.httpSetting.getType();
        if (type == 1000) {
            if (this.httpSetting.isForeverCache()) {
                directory = FileService.getDirectory(5);
            } else {
                directory = FileService.getDirectory(2);
            }
            md5 = md5 + FileService.CACHE_EXT_NAME_JSON;
        } else if (type != 5000) {
            directory = null;
        } else {
            directory = FileService.getDirectory(1);
            md5 = md5 + FileService.CACHE_EXT_NAME_IMAGE;
        }
        if (OKLog.D) {
            OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- findCachesFileByMd5() directory -->> " + directory);
        }
        if (directory == null) {
            return null;
        }
        File dir = directory.getDir();
        if (OKLog.D) {
            OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- findCachesFileByMd5() dir.exists() -->> " + dir.exists());
            OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- findCachesFileByMd5() dir.isDirectory() -->> " + dir.isDirectory());
            OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- findCachesFileByMd5() dir -->> " + dir);
        }
        if (directory.getPath() != null) {
            String str = directory.getPath() + File.separatorChar + md5;
            File file = new File(str);
            if (OKLog.D) {
                OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- findCachesFileByMd5() filePath -->> " + str);
            }
            if (file.exists()) {
                if (OKLog.D) {
                    OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- can find caches file by md5 -->> ");
                }
                return file;
            }
        }
        if (OKLog.D) {
            OKLog.d("HttpGroup", "id:" + this.httpSetting.getId() + "- canot find caches file by md5 -->> ");
        }
        return null;
    }

    public HttpSetting getHttpSetting() {
        return this.httpSetting;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.StopController
    public boolean isStop() {
        return this.stopFlag;
    }

    public void setJDFileRequest(JDFileRequest jDFileRequest) {
        this.jdFileRequest = jDFileRequest;
    }

    public void setJDRequestTag(String str) {
        this.jdRequestTag = str;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.StopController
    public void stop() {
        this.stopFlag = true;
        if (!TextUtils.isEmpty(this.jdRequestTag) && JDNetworkHelper.getGlobalJDRequestQueue() != null) {
            JDNetworkHelper.getGlobalJDRequestQueue().cancelByTag(this.jdRequestTag);
        }
        JDFileRequest jDFileRequest = this.jdFileRequest;
        if (jDFileRequest != null) {
            jDFileRequest.stop();
        }
    }
}
