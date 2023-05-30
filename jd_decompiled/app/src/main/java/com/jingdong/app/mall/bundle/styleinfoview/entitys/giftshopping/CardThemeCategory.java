package com.jingdong.app.mall.bundle.styleinfoview.entitys.giftshopping;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class CardThemeCategory {
    public static final String CUSTOM_THEME = "\u81ea\u5b9a\u4e49";
    public ArrayList<CardThemeCategoryItem> aiSubject;

    public void dealAiSubject() {
        String str;
        ArrayList<CardThemeCategoryItem> arrayList = this.aiSubject;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                CardThemeCategoryItem cardThemeCategoryItem = this.aiSubject.get(size);
                if (cardThemeCategoryItem == null || (str = cardThemeCategoryItem.subject) == null || str.equals("")) {
                    this.aiSubject.remove(size);
                }
            }
            if (this.aiSubject.size() > 0) {
                CardThemeCategoryItem cardThemeCategoryItem2 = new CardThemeCategoryItem();
                cardThemeCategoryItem2.param = "\u81ea\u5b9a\u4e49";
                cardThemeCategoryItem2.subject = "\u81ea\u5b9a\u4e49";
                this.aiSubject.add(cardThemeCategoryItem2);
            }
        }
    }
}
