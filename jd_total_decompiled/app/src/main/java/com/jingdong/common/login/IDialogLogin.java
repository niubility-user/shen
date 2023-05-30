package com.jingdong.common.login;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes5.dex */
public interface IDialogLogin {
    boolean showDialog(Activity activity, Bundle bundle);

    boolean showDialog(Activity activity, Bundle bundle, ILogin iLogin, String str);
}
