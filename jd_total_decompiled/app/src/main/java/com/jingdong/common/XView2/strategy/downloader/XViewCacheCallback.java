package com.jingdong.common.XView2.strategy.downloader;

import android.text.TextUtils;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.b;
import com.jd.hybrid.downloader.h;
import com.jingdong.common.XView2.strategy.downloader.entity.XViewFileEntity;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;

/* loaded from: classes5.dex */
public class XViewCacheCallback extends b {
    private XViewFileEntity entity;
    private final h observable;

    public XViewCacheCallback(h hVar) {
        this.observable = hVar;
    }

    private String getExtractFilePath(File file) {
        return file.getParent() + File.separator + this.entity.id;
    }

    @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
    public void onEnd(FileResponse<File> fileResponse) {
        File data = fileResponse.getData();
        if (data == null || this.entity == null) {
            return;
        }
        String fileNameFromPath = XView2Utils.getFileNameFromPath(data.getPath());
        String str = Md5Encrypt.md5(this.entity.id) + this.entity.suffix;
        if (!TextUtils.isEmpty(fileNameFromPath) && !TextUtils.isEmpty(str) && fileNameFromPath.equals(str)) {
            this.entity.onDownloaded(data.getPath());
        } else {
            this.entity.status = -1;
            data.delete();
        }
        this.observable.notifyChange(this.entity);
    }

    @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
    public void onError(FileError fileError) {
        super.onError(fileError);
        XViewFileEntity xViewFileEntity = this.entity;
        if (xViewFileEntity != null) {
            xViewFileEntity.status = -1;
        }
        this.observable.notifyChange(xViewFileEntity);
        XViewCache.getInstance().retryDownLoader(this.entity);
        XViewFileEntity xViewFileEntity2 = this.entity;
        if (xViewFileEntity2 != null) {
            String str = xViewFileEntity2.url;
            String message = fileError != null ? fileError.getMessage() : "";
            XViewFileEntity xViewFileEntity3 = this.entity;
            XView2Utils.reportXView2Error("dlFail", "NXViewException", str, message, xViewFileEntity3.layerId, xViewFileEntity3.name, "");
        }
    }

    public void setEntity(XViewFileEntity xViewFileEntity) {
        this.entity = xViewFileEntity;
    }
}
