package com.jdpay.membercode.network.a;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.json.JsonAdapter;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.membercode.Aks;
import com.jdpay.membercode.network.ResponseBean;
import com.jdpay.membercode.network.ResponseWrapperBean;
import com.jdpay.net.http.HttpResponse;
import com.jdpay.net.http.converter.HttpResponseConverter;
import com.jdpay.util.JPMCMonitor;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class a implements HttpResponseConverter<ResponseBean> {
    private Type a;

    /* JADX WARN: Type inference failed for: r5v1, types: [com.jdpay.net.Request] */
    @Override // com.jdpay.lib.converter.Converter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public ResponseBean convert(@Nullable HttpResponse httpResponse) {
        int parseInt;
        String str;
        if (httpResponse == null) {
            return null;
        }
        String string = httpResponse.getString();
        JDPayLog.i(string);
        if (TextUtils.isEmpty(string)) {
            JPMCMonitor.e("MC HttpResponse data is empty");
            return null;
        }
        String decode = Aks.decode(string);
        JDPayLog.i(decode);
        String header = httpResponse.getHeader("useAksDegrade");
        if (!TextUtils.isEmpty(header) && TextUtils.isDigitsOnly(header) && (parseInt = Integer.parseInt(header)) != Aks.algorithm) {
            if (parseInt == 1) {
                Aks.algorithm = 1;
                str = "MC Aks internation";
            } else {
                Aks.algorithm = 0;
                str = "MC Aks sm";
            }
            JPMCMonitor.i(str);
        }
        ResponseBean responseBean = (ResponseBean) JsonAdapter.objectSafety(((ResponseWrapperBean) JsonAdapter.objectSafety(decode, ResponseWrapperBean.class)).resData, this.a);
        if (responseBean != null) {
            responseBean.clientKey = (String) httpResponse.getRequest().getExtra(1);
        }
        return responseBean;
    }

    @Override // com.jdpay.net.http.converter.HttpResponseConverter
    public Type getType() {
        return this.a;
    }

    @Override // com.jdpay.net.http.converter.HttpResponseConverter
    public void setType(@NonNull Type type) {
        this.a = type;
    }
}
