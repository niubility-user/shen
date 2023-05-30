package com.airbnb.lottie.network;

import com.airbnb.lottie.utils.Logger;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.network.toolbox.FileService;

/* loaded from: classes.dex */
public enum FileExtension {
    JSON(FileService.CACHE_EXT_NAME_JSON),
    ZIP(".zip");
    
    public final String extension;

    FileExtension(String str) {
        this.extension = str;
    }

    public static FileExtension forFile(String str) {
        for (FileExtension fileExtension : values()) {
            if (str.endsWith(fileExtension.extension)) {
                return fileExtension;
            }
        }
        Logger.warning("Unable to find correct extension for " + str);
        return JSON;
    }

    public String tempExtension() {
        return DYConstants.TEMP_NAME_PREFIX + this.extension;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.extension;
    }
}
