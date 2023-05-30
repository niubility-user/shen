package com.jingdong.common.web.javainterface.impl;

import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;

@Deprecated
/* loaded from: classes12.dex */
public class JSCalendarHelper extends BaseWebComponent implements IJavaInterface {
    public static final String KEY_PICK_DATE_1 = "pickedDate1";
    public static final String KEY_PICK_DATE_2 = "pickedDate2";
    public static final int RESULT_OK = -1;
    private static final String TAG = "JSControlHelper";

    public JSCalendarHelper(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JS_CALENDAR;
    }
}
