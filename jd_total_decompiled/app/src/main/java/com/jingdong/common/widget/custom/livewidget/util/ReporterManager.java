package com.jingdong.common.widget.custom.livewidget.util;

import android.content.Context;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class ReporterManager {
    private boolean isDisplayCutout;
    private String mDns;
    private List<String> mListLives;
    private String roomNumber;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class InstanceHolder {
        private static ReporterManager INSTANCE = new ReporterManager();

        private InstanceHolder() {
        }
    }

    public static ReporterManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void addLiveItem(String str) {
        if (this.mListLives.contains(str)) {
            return;
        }
        this.mListLives.add(str);
    }

    public void clearLiveList() {
        this.mListLives.clear();
    }

    public List<String> filterInvalidLiveList(List<String> list) {
        if (list != null && list.size() != 0) {
            List<String> list2 = this.mListLives;
            if (list2 == null) {
                return null;
            }
            try {
                Iterator<String> it = list2.iterator();
                while (it.hasNext()) {
                    if (list.contains(it.next())) {
                        it.remove();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.e("SmallWindow", "remove failed");
            }
            return this.mListLives;
        }
        return this.mListLives;
    }

    public boolean getDisplayCutout() {
        return this.isDisplayCutout;
    }

    public String getFirstLiveId() {
        if (this.mListLives.size() == 0) {
            return null;
        }
        return this.mListLives.get(0);
    }

    public String getJdPullDns() {
        return this.mDns;
    }

    public String getLastLiveId() {
        int size;
        List<String> list = this.mListLives;
        if (list == null || (size = list.size()) == 0) {
            return null;
        }
        return this.mListLives.get(size - 1);
    }

    public List<String> getLiveList() {
        return this.mListLives;
    }

    public int getLiveListSize() {
        return this.mListLives.size();
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public int screenMarginTop(Context context, int i2) {
        return getInstance().getDisplayCutout() ? i2 + DPIUtil.dip2px(context, 28.0f) : i2;
    }

    public void setDisplayCutout(boolean z) {
        this.isDisplayCutout = z;
    }

    public void setJdPullDns(String str) {
        this.mDns = str;
    }

    public void setRoomNumber(String str) {
        this.roomNumber = str;
    }

    private ReporterManager() {
        this.mListLives = new ArrayList();
        this.isDisplayCutout = false;
    }
}
