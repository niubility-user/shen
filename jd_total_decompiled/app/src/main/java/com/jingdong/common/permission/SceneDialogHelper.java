package com.jingdong.common.permission;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes5.dex */
public class SceneDialogHelper {
    private static final String LEFT_BUTTON_TITLE = "\u53d6\u6d88";
    private static final String RIGHT_BUTTON_TITLE = "\u5f00\u542f";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public interface SceneDialogClickListener {
        void onClick(boolean z, boolean z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JDDialog createDialog(Context context, String str, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, JdSdk.getInstance().getApplication().getString(R.string.permission_dialog_msg_title_muti), str, "\u53d6\u6d88", RIGHT_BUTTON_TITLE);
        if (onClickListener != null) {
            createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.SceneDialogHelper.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDDialog.this.dismiss();
                    onClickListener.onClick(view);
                }
            });
        }
        if (onClickListener2 != null) {
            createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.SceneDialogHelper.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDDialog.this.dismiss();
                    onClickListener2.onClick(view);
                }
            });
        }
        return createJdDialogWithStyle6;
    }

    public static boolean showSceneDialog(final Context context, String str, final String str2, final boolean z, final SceneDialogClickListener sceneDialogClickListener) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        createDialog(context, str, new View.OnClickListener() { // from class: com.jingdong.common.permission.SceneDialogHelper.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SceneDialogClickListener sceneDialogClickListener2 = SceneDialogClickListener.this;
                if (sceneDialogClickListener2 != null) {
                    sceneDialogClickListener2.onClick(false, false);
                }
            }
        }, new View.OnClickListener() { // from class: com.jingdong.common.permission.SceneDialogHelper.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SceneDialogClickListener sceneDialogClickListener2 = SceneDialogClickListener.this;
                if (sceneDialogClickListener2 != null) {
                    sceneDialogClickListener2.onClick(false, true);
                }
                if (!z || TextUtils.isEmpty(str2)) {
                    return;
                }
                SceneDialogHelper.createDialog(context, str2, new View.OnClickListener() { // from class: com.jingdong.common.permission.SceneDialogHelper.4.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        SceneDialogClickListener sceneDialogClickListener3 = SceneDialogClickListener.this;
                        if (sceneDialogClickListener3 != null) {
                            sceneDialogClickListener3.onClick(true, false);
                        }
                    }
                }, new View.OnClickListener() { // from class: com.jingdong.common.permission.SceneDialogHelper.4.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        SceneDialogClickListener sceneDialogClickListener3 = SceneDialogClickListener.this;
                        if (sceneDialogClickListener3 != null) {
                            sceneDialogClickListener3.onClick(true, true);
                        }
                    }
                }).show();
            }
        }).show();
        return true;
    }
}
