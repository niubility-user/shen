package com.jingdong.common.ui;

/* loaded from: classes6.dex */
public class JdDialogParam {
    public int countDownTime;
    public String functionId;
    public ButtonParam left;
    public String message;
    public String pageId;
    public String posType;
    public ButtonParam right;
    public String title;

    /* loaded from: classes6.dex */
    public static class ButtonParam {
        public String jumpUrl;
        public String title;

        public ButtonParam() {
        }

        public ButtonParam(String str, String str2) {
            this.title = str;
            this.jumpUrl = str2;
        }
    }

    public String getLeftJumpUrl() {
        ButtonParam buttonParam = this.left;
        return buttonParam != null ? buttonParam.jumpUrl : "";
    }

    public String getLeftTitle() {
        ButtonParam buttonParam = this.left;
        return buttonParam != null ? buttonParam.title : "";
    }

    public String getRightJumpUrl() {
        ButtonParam buttonParam = this.right;
        return buttonParam != null ? buttonParam.jumpUrl : "";
    }

    public String getRightTitle() {
        ButtonParam buttonParam = this.right;
        return buttonParam != null ? buttonParam.title : "";
    }
}
