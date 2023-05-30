package com.jingdong.common.XView2.page;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.XView2.utils.BaseRunnable;
import com.jingdong.common.XView2.utils.XView2SubThreadCtrl;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes5.dex */
public class PageFile implements Parcelable {
    public static final Parcelable.Creator<PageFile> CREATOR = new Parcelable.Creator<PageFile>() { // from class: com.jingdong.common.XView2.page.PageFile.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PageFile createFromParcel(Parcel parcel) {
            return new PageFile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PageFile[] newArray(int i2) {
            return new PageFile[i2];
        }
    };
    private static final String JSON_FILE = ".json";
    private static PageFile pageFile;
    private String oldDir;
    public Report report;
    private String rootDir;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class BaseFile implements Parcelable {
        public static final Parcelable.Creator<BaseFile> CREATOR = new Parcelable.Creator<BaseFile>() { // from class: com.jingdong.common.XView2.page.PageFile.BaseFile.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseFile createFromParcel(Parcel parcel) {
                return new BaseFile(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BaseFile[] newArray(int i2) {
                return new BaseFile[i2];
            }
        };
        private File file;
        public String path;

        public void delete() {
            File file = file();
            this.file = file;
            if (file != null) {
                file.delete();
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public File file() {
            File file = this.file;
            if (file == null) {
                File file2 = new File(this.path);
                this.file = file2;
                return file2;
            }
            return file;
        }

        public String path() {
            return this.path;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.path);
        }

        private BaseFile(String str) {
            this.path = str;
        }

        protected BaseFile(Parcel parcel) {
            this.path = parcel.readString();
        }
    }

    /* loaded from: classes5.dex */
    public static class Hprof extends BaseFile implements Parcelable {
        public static final Parcelable.Creator<Hprof> CREATOR = new Parcelable.Creator<Hprof>() { // from class: com.jingdong.common.XView2.page.PageFile.Hprof.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Hprof createFromParcel(Parcel parcel) {
                return new Hprof(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Hprof[] newArray(int i2) {
                return new Hprof[i2];
            }
        };
        public boolean stripped;

        public Hprof(String str) {
            super(str);
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile
        public /* bridge */ /* synthetic */ void delete() {
            super.delete();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile
        public /* bridge */ /* synthetic */ File file() {
            return super.file();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile
        public /* bridge */ /* synthetic */ String path() {
            return super.path();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeByte(this.stripped ? (byte) 1 : (byte) 0);
        }

        protected Hprof(Parcel parcel) {
            super(parcel);
            this.stripped = parcel.readByte() != 0;
        }

        public static Hprof file(String str) {
            return new Hprof(str);
        }
    }

    /* loaded from: classes5.dex */
    public static class Report extends BaseFile {
        public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() { // from class: com.jingdong.common.XView2.page.PageFile.Report.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Report createFromParcel(Parcel parcel) {
                return new Report(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Report[] newArray(int i2) {
                return new Report[i2];
            }
        };

        public Report(String str) {
            super(str);
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile
        public /* bridge */ /* synthetic */ void delete() {
            super.delete();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile, android.os.Parcelable
        public /* bridge */ /* synthetic */ int describeContents() {
            return super.describeContents();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile
        public /* bridge */ /* synthetic */ File file() {
            return super.file();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile
        public /* bridge */ /* synthetic */ String path() {
            return super.path();
        }

        @Override // com.jingdong.common.XView2.page.PageFile.BaseFile, android.os.Parcelable
        public /* bridge */ /* synthetic */ void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
        }

        protected Report(Parcel parcel) {
            super(parcel);
        }

        public static Report file(String str) {
            return new Report(str);
        }
    }

    public PageFile() {
        String str;
        File cacheDir = JdSdk.getInstance().getApplication().getCacheDir();
        if (cacheDir != null) {
            str = cacheDir.getAbsolutePath() + File.separator + "pageinfo";
        } else {
            str = "/data/data/" + JdSdk.getInstance().getApplication().getPackageName() + "/cache/pageinfo";
        }
        this.oldDir = str;
        this.rootDir = "/data/data/" + JdSdk.getInstance().getApplication().getPackageName() + "/files/pageinfo";
        File file = new File(this.rootDir);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    public static boolean copyFile(File file, File file2) {
        if (file == null) {
            return false;
        }
        try {
            if (!file.exists()) {
                return false;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return true;
                }
            }
        } catch (Exception unused) {
            XView2Utils.reportXView2Error("copyFile", "NXViewException", "", "\u62f7\u8d1d\u6587\u4ef6\u5931\u8d25");
            return false;
        }
    }

    public static void delete() {
        PageFile pageFile2 = pageFile;
        if (pageFile2 == null) {
            return;
        }
        pageFile2.report.file().delete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteFileWithParent(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        File parentFile = file.getParentFile();
        file.delete();
        if (parentFile != null) {
            parentFile.delete();
        }
    }

    private void generateDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Report generateReportFile(String str) {
        generateDir(str);
        Report report = new Report(str + File.separator + "pageInfo.json");
        if (!report.file().exists()) {
            try {
                report.file().createNewFile();
                report.file().setWritable(true);
                report.file().setReadable(true);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return report;
    }

    public static PageFile getPageFile() {
        PageFile pageFile2 = pageFile;
        if (pageFile2 == null) {
            PageFile pageFile3 = new PageFile();
            pageFile = pageFile3;
            return pageFile3;
        }
        return pageFile2;
    }

    public void buildFiles() {
        this.report = generateReportFile(this.rootDir);
        copyOldPageInfo();
    }

    public void copyOldPageInfo() {
        XView2SubThreadCtrl.addLongTimeTask(new BaseRunnable() { // from class: com.jingdong.common.XView2.page.PageFile.1
            @Override // com.jingdong.common.XView2.utils.BaseRunnable
            protected void safeRun() {
                Report report;
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(PageFile.this.oldDir);
                    String str = File.separator;
                    sb.append(str);
                    sb.append("pageInfo");
                    sb.append(".json");
                    File file = new File(sb.toString());
                    if (file.exists() && (report = PageFile.this.report) != null) {
                        if (PageFile.copyFile(file, report.file())) {
                            PageFile.this.deleteFileWithParent(file);
                        } else {
                            PageFile pageFile2 = PageFile.this;
                            pageFile2.report = pageFile2.generateReportFile(pageFile2.oldDir);
                            PageFile.this.deleteFileWithParent(new File(PageFile.this.rootDir + str + "pageInfo.json"));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
    }

    protected PageFile(Parcel parcel) {
        this.report = (Report) parcel.readParcelable(Report.class.getClassLoader());
    }
}
