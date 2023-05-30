package com.jingdong.manto.jsapi.refact;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.R;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.g1.a;
import com.jingdong.manto.m.g1.c;
import com.jingdong.manto.m.g1.d;
import com.jingdong.manto.m.g1.e;
import com.jingdong.manto.m.g1.f;
import com.jingdong.manto.m.g1.g;
import com.jingdong.manto.sdk.api.IPickerInterface;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiShowPickerView extends AbstractMantoModule implements IPickerInterface {
    private static final String DATE_PICKER_NAME = "showDatePickerView";
    private static final String GROUP_NAME = "pickerView";
    private static final String MULTI_PICKER_NAME = "showMultiPickerView";
    private static final String PICKER_NAME = "showPickerView";
    private static final String UPDATE_MULTI_PICKER_NAME = "updateMultiPickerView";
    private a datePickerInvoker;
    private c multiPickerInvoker;
    private d multiPickerUpdateInvoker;
    private e optionPickerInvoker;
    private g timePickerInvoker;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final MantoLifecycleLisener addLifecycleLisener(String str, Bundle bundle) {
        d dVar;
        if (PICKER_NAME.equals(str)) {
            e eVar = new e();
            this.optionPickerInvoker = eVar;
            dVar = eVar;
        } else if (DATE_PICKER_NAME.equals(str)) {
            String string = bundle.getString("mode");
            if (JDDateTimePickerDialog.SELECT_DATE_MODE.equals(string)) {
                a aVar = new a();
                this.datePickerInvoker = aVar;
                dVar = aVar;
            } else if (!"time".equals(string)) {
                return null;
            } else {
                g gVar = new g();
                this.timePickerInvoker = gVar;
                dVar = gVar;
            }
        } else if (MULTI_PICKER_NAME.equals(str)) {
            c cVar = new c();
            this.multiPickerInvoker = cVar;
            dVar = cVar;
        } else if (!UPDATE_MULTI_PICKER_NAME.equals(str)) {
            return null;
        } else {
            d dVar2 = new d();
            this.multiPickerUpdateInvoker = dVar2;
            dVar = dVar2;
        }
        return dVar.d;
    }

    @Override // com.jingdong.manto.sdk.api.IPickerInterface
    public int getBackgroudColor(Context context, int i2) {
        return context.getResources().getColor(R.color.manto_half_transparent);
    }

    @Override // com.jingdong.manto.sdk.api.IPickerInterface
    public int getCancelColor(Context context, int i2) {
        return context.getResources().getColor(R.color.manto_picker_cancel);
    }

    @Override // com.jingdong.manto.sdk.api.IPickerInterface
    public int getConfirmColor(Context context, int i2) {
        return context.getResources().getColor(i2 == 1 ? R.color.manto_dark_open_main_color : R.color.manto_picker_confirm);
    }

    @Override // com.jingdong.manto.sdk.api.IPickerInterface
    public int getContentColor(Context context, int i2) {
        return context.getResources().getColor(i2 == 1 ? R.color.manto_dark_background_light : R.color.manto_day_background_light);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return GROUP_NAME;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        f fVar;
        f fVar2;
        if (PICKER_NAME.equals(str)) {
            fVar2 = this.optionPickerInvoker;
        } else if (DATE_PICKER_NAME.equals(str)) {
            String string = bundle.getString("mode");
            if (!JDDateTimePickerDialog.SELECT_DATE_MODE.equals(string)) {
                if ("time".equals(string)) {
                    fVar = this.timePickerInvoker;
                    fVar.a(this, this, mantoCore, bundle, mantoResultCallBack);
                    return;
                }
                mantoResultCallBack.onFailed(null);
                return;
            }
            fVar2 = this.datePickerInvoker;
        } else if (!MULTI_PICKER_NAME.equals(str)) {
            if (UPDATE_MULTI_PICKER_NAME.equals(str)) {
                fVar = this.multiPickerUpdateInvoker;
                fVar.a(this, this, mantoCore, bundle, mantoResultCallBack);
                return;
            }
            mantoResultCallBack.onFailed(null);
            return;
        } else {
            fVar2 = this.multiPickerInvoker;
        }
        fVar2.a(this, this, mantoCore, bundle, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("params", jSONObject.toString());
        if (DATE_PICKER_NAME.equals(str)) {
            bundle.putString("mode", jSONObject.optString("mode"));
        }
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(PICKER_NAME, 1));
        list.add(new JsApiMethod(DATE_PICKER_NAME, 1));
        list.add(new JsApiMethod(MULTI_PICKER_NAME, 1));
        list.add(new JsApiMethod(UPDATE_MULTI_PICKER_NAME, 1));
    }
}
