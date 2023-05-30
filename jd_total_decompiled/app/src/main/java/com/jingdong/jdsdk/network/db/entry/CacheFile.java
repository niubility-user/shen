package com.jingdong.jdsdk.network.db.entry;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.Date;

/* loaded from: classes14.dex */
public class CacheFile {
    private Integer bussinessId;
    private Date cleanTime;
    private FileService.Directory directory;
    private File file;
    public String firstName;
    public String lastName;
    private String name;

    public CacheFile() {
    }

    public int getBussinessId() {
        Integer num = this.bussinessId;
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public Date getCleanTime() {
        return this.cleanTime;
    }

    public FileService.Directory getDirectory() {
        return this.directory;
    }

    public File getFile() {
        if (this.file == null && getDirectory() != null) {
            this.file = new File(getDirectory().getDir(), getName());
        }
        return this.file;
    }

    public String getName() {
        if (this.name == null) {
            this.name = this.firstName + OrderISVUtil.MONEY_DECIMAL + this.lastName;
        }
        return this.name;
    }

    public void setBussinessId(Integer num) {
        this.bussinessId = num;
    }

    public void setCleanTime(Date date) {
        this.cleanTime = date;
    }

    public void setDirectory(FileService.Directory directory) {
        this.directory = directory;
    }

    public void setFile(File file) {
        setName(file.getName());
        this.file = file;
    }

    public void setName(String str) {
        this.name = str;
        if (TextUtils.isEmpty(str)) {
            this.firstName = "";
            this.lastName = "";
            return;
        }
        int lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
        this.firstName = str.substring(0, lastIndexOf);
        this.lastName = str.substring(lastIndexOf + 1);
    }

    public CacheFile(File file) {
        setFile(file);
    }

    public CacheFile(String str, long j2) {
        setName(str);
        this.cleanTime = new Date(new Date().getTime() + j2);
        if (OKLog.D) {
            OKLog.d("CacheFileTable", " -->> cacheTime : " + j2);
            OKLog.d("CacheFileTable", " -->> cleanTime : " + this.cleanTime.getTime());
        }
    }
}
