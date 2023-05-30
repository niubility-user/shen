package com.jingdong.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.common.PersonalDownloadImageEntity;
import com.jingdong.common.unification.navigationbar.theme.INavigationChangeState;
import com.jingdong.common.unification.navigationbar.theme.NavThemeEntity;
import com.jingdong.common.unification.navigationbar.theme.NavigationThemeController;
import com.jingdong.common.utils.DownloadImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes6.dex */
public class PersonalChangeSkinUtils {
    public static PersonalChangeSkinUtils instance;
    public static boolean isAlreadyChanged;
    public static boolean isBirthdayAlreadyExpo;
    Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jingdong.common.utils.PersonalChangeSkinUtils.3
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            return false;
        }
    });

    private PersonalChangeSkinUtils() {
    }

    public static PersonalChangeSkinUtils getInstance() {
        if (instance == null) {
            instance = new PersonalChangeSkinUtils();
        }
        return instance;
    }

    public void changeDefaultIcon() {
        if (isAlreadyChanged) {
            isAlreadyChanged = false;
            this.mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.PersonalChangeSkinUtils.2
                @Override // java.lang.Runnable
                public void run() {
                    NavigationThemeController.getInstance().changeToDefault();
                }
            });
        }
    }

    public void changeIcon(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (isAlreadyChanged) {
                return;
            }
            DownloadImageUtils.downloadImage(getRequestData(str, str2), new DownloadImageUtils.DownloadImageListener() { // from class: com.jingdong.common.utils.PersonalChangeSkinUtils.1
                @Override // com.jingdong.common.utils.DownloadImageUtils.DownloadImageListener
                public void downloadComplete(final List<PersonalDownloadImageEntity> list) {
                    PersonalChangeSkinUtils.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.PersonalChangeSkinUtils.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int size;
                            List list2 = list;
                            if (list2 != null && (size = list2.size()) == 2) {
                                NavThemeEntity navThemeEntity = new NavThemeEntity();
                                navThemeEntity.navigationId = 4;
                                for (int i2 = 0; i2 < size; i2++) {
                                    if (((PersonalDownloadImageEntity) list.get(i2)).isSelected) {
                                        navThemeEntity.onPath = ((PersonalDownloadImageEntity) list.get(i2)).getSavePath();
                                    } else {
                                        navThemeEntity.offPath = ((PersonalDownloadImageEntity) list.get(i2)).getSavePath();
                                    }
                                }
                                NavigationThemeController.getInstance().changeTheme(navThemeEntity, "", new INavigationChangeState() { // from class: com.jingdong.common.utils.PersonalChangeSkinUtils.1.1.1
                                    @Override // com.jingdong.common.unification.navigationbar.theme.INavigationChangeState
                                    public void result(boolean z) {
                                        if (z) {
                                            PersonalChangeSkinUtils.isAlreadyChanged = true;
                                            Context applicationContext = JdSdk.getInstance().getApplicationContext();
                                            if (PersonalChangeSkinUtils.isBirthdayAlreadyExpo) {
                                                return;
                                            }
                                            PersonalChangeSkinUtils.isBirthdayAlreadyExpo = true;
                                            JDMtaUtils.sendCommonData(applicationContext, "NavigationBar_MyJD_BirthdayExpo", "", "", applicationContext, "", "", "");
                                            if (Log.D) {
                                                Log.i("PersonalChangeSkinUtils", "\u751f\u65e5Icon\u66dd\u5149");
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    });
                }

                @Override // com.jingdong.common.utils.DownloadImageUtils.DownloadImageListener
                public void downloadError() {
                    PersonalChangeSkinUtils.this.changeDefaultIcon();
                }
            });
            return;
        }
        changeDefaultIcon();
    }

    public List<PersonalDownloadImageEntity> getRequestData(String str, String str2) {
        LinkedList linkedList = new LinkedList();
        PersonalDownloadImageEntity personalDownloadImageEntity = new PersonalDownloadImageEntity();
        personalDownloadImageEntity.setDownloadUrl(str);
        personalDownloadImageEntity.setSelected(true);
        linkedList.add(personalDownloadImageEntity);
        PersonalDownloadImageEntity personalDownloadImageEntity2 = new PersonalDownloadImageEntity();
        personalDownloadImageEntity2.setSelected(false);
        personalDownloadImageEntity2.setDownloadUrl(str2);
        linkedList.add(personalDownloadImageEntity2);
        return linkedList;
    }
}
