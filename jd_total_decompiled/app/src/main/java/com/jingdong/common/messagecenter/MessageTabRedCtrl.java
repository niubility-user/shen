package com.jingdong.common.messagecenter;

import com.jingdong.common.BaseApplication;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.view.NewMessagRedManager;
import com.jingdong.common.messagecenter.view.NewMessageRedInfo;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.navigationbar.NavigationBase;

/* loaded from: classes5.dex */
public class MessageTabRedCtrl {
    public static final int MODE_NONE = 0;
    public static final int MODE_RED_NUM = 1;
    public static final int MODE_RED_POINT = 2;
    private static MessageTabRedCtrl messageTabRedCtrl;

    private MessageTabRedCtrl() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMsgRedPoint() {
        if (isMessageTabShow()) {
            NavigationBase.getInstance().refreshMessageNum(null);
            NavigationBase.getInstance().refreshMessageRedpoint(false);
        }
        saveMessageInfo(0, false);
    }

    public static MessageTabRedCtrl getInstance() {
        if (messageTabRedCtrl == null) {
            synchronized (MessageTabRedCtrl.class) {
                if (messageTabRedCtrl == null) {
                    messageTabRedCtrl = new MessageTabRedCtrl();
                }
            }
        }
        return messageTabRedCtrl;
    }

    private void initMessageRed() {
        int preRedDotParams = NewMessageRedInfo.getPreRedDotParams();
        boolean redPointStatus = NewMessageRedInfo.getRedPointStatus();
        if (preRedDotParams > 0) {
            showMsgRedPoint(1, preRedDotParams);
            return;
        }
        if ((preRedDotParams <= 0) & redPointStatus) {
            showMsgRedPoint(2, 0);
        } else {
            showMsgRedPoint(0, 0);
        }
    }

    private boolean isMessageTabShow() {
        return NavigationBase.getInstance().isMsgDisplayType();
    }

    private void saveMessageInfo(int i2, boolean z) {
        NewMessageRedInfo.putPreRedDotParams(i2);
        NewMessageRedInfo.putRedPointStatus(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMsgRedNum(int i2) {
        if (!LoginUserBase.hasLogin()) {
            clearMsgRedPoint();
            return;
        }
        saveMessageInfo(i2, false);
        if (isMessageTabShow()) {
            if (i2 <= 0) {
                NavigationBase.getInstance().refreshMessageNum(null);
            } else {
                NavigationBase.getInstance().refreshMessageNum(Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRedPoint() {
        if (!LoginUserBase.hasLogin()) {
            clearMsgRedPoint();
            return;
        }
        if (isMessageTabShow()) {
            NavigationBase.getInstance().refreshMessageRedpoint(true);
        }
        saveMessageInfo(0, true);
    }

    public void requestMessage() {
        if (!LoginUserBase.hasLogin()) {
            BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.messagecenter.MessageTabRedCtrl.2
                @Override // java.lang.Runnable
                public void run() {
                    MessageTabRedCtrl.this.clearMsgRedPoint();
                }
            });
            return;
        }
        initMessageRed();
        NewMessagRedManager.getInstance(LoginUserBase.getUserPin());
        NewMessagRedManager.requestMessage(HttpGroupUtils.getHttpGroupaAsynPool());
    }

    public void showMsgRedPoint(final int i2, final int i3) {
        BaseApplication.getHandler().post(new Runnable() { // from class: com.jingdong.common.messagecenter.MessageTabRedCtrl.1
            @Override // java.lang.Runnable
            public void run() {
                int i4 = i2;
                if (i4 == 0) {
                    MessageTabRedCtrl.this.clearMsgRedPoint();
                } else if (i4 == 1) {
                    MessageTabRedCtrl.this.showMsgRedNum(i3);
                } else if (i4 != 2) {
                } else {
                    MessageTabRedCtrl.this.showRedPoint();
                }
            }
        });
    }
}
