package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeFileUploadListener {
    void fileToBase64(String str, JDCallback jDCallback, JDCallback jDCallback2);

    void getFileName(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void getFileSize(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void upLoadingFile(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
