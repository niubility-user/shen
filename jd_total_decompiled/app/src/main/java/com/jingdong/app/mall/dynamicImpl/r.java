package com.jingdong.app.mall.dynamicImpl;

import android.text.TextUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class r implements IExceptionHandler {
    private String a(String str) {
        return TextUtils.isEmpty(str) ? "unkhnow" : str;
    }

    @Override // com.jd.dynamic.base.interfaces.IExceptionHandler
    public void handException(IExceptionHandler.DynamicExceptionData dynamicExceptionData) {
        if (dynamicExceptionData != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
                String str = null;
                Exception exc = dynamicExceptionData.exception;
                if (exc != null) {
                    exc.printStackTrace(printWriter);
                    printWriter.flush();
                    str = byteArrayOutputStream.toString();
                    byteArrayOutputStream.close();
                    printWriter.close();
                }
                HashMap hashMap = new HashMap();
                String a = a(dynamicExceptionData.type);
                String a2 = a(dynamicExceptionData.errorMsg);
                String a3 = a(dynamicExceptionData.bizField);
                String a4 = a(str);
                hashMap.put("function", a);
                hashMap.put("errCode", "941");
                hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
                hashMap.put("errMsg", a(dynamicExceptionData.systemCode));
                hashMap.put("PostData", a2);
                hashMap.put(jpbury.t.f20145j, a4);
                hashMap.put("url", a3);
                hashMap.put("httpResp", String.valueOf(dynamicExceptionData.httpRespCode));
                ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
            } catch (Exception unused) {
            }
        }
    }
}
