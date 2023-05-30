package com.jingdong.common.messagecenter;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class MessageLottieCtrl {
    private static final int MSG_ANT_BLK1 = 1;
    private static final int MSG_ANT_BLK2 = 2;
    private static final int MSG_ANT_BLK3 = 3;
    private static final int MSG_ANT_WHT1 = 4;
    private static final int MSG_ANT_WHT2 = 5;
    private static final int MSG_ANT_WHT3 = 6;
    public static MessageLottieCtrl messageLottieCtrl;
    private HashMap<Integer, String> mLottieList;

    private MessageLottieCtrl() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        this.mLottieList = hashMap;
        hashMap.put(1, "ic_msg_black1.json");
        this.mLottieList.put(2, "ic_msg_black2.json");
        this.mLottieList.put(3, "ic_msg_black3.json");
        this.mLottieList.put(4, "ic_msg_white1.json");
        this.mLottieList.put(5, "ic_msg_white2.json");
        this.mLottieList.put(6, "ic_msg_white3.json");
    }

    public static synchronized MessageLottieCtrl getInstance() {
        MessageLottieCtrl messageLottieCtrl2;
        synchronized (MessageLottieCtrl.class) {
            if (messageLottieCtrl == null) {
                messageLottieCtrl = new MessageLottieCtrl();
            }
            messageLottieCtrl2 = messageLottieCtrl;
        }
        return messageLottieCtrl2;
    }

    public String getAntiPath(int i2, boolean z) {
        if (i2 < 30) {
            return this.mLottieList.get(Integer.valueOf(z ? 1 : 4));
        } else if (i2 <= 60) {
            return this.mLottieList.get(Integer.valueOf(z ? 2 : 5));
        } else {
            return this.mLottieList.get(Integer.valueOf(z ? 3 : 6));
        }
    }
}
