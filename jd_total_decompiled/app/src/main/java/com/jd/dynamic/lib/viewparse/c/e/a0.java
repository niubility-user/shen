package com.jd.dynamic.lib.viewparse.c.e;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.views.CollectionView;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class a0 extends p0<CollectionView> {

    /* renamed from: c  reason: collision with root package name */
    private DynamicTemplateEngine f2366c;

    public static int f(String str, int i2) {
        try {
            return (int) Float.parseFloat(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public void g(DynamicTemplateEngine dynamicTemplateEngine) {
        this.f2366c = dynamicTemplateEngine;
    }

    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, CollectionView collectionView) {
        collectionView.attachEngine(this.f2366c);
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String str = hashMap.get(entry.getKey());
            String key = entry.getKey();
            key.hashCode();
            char c2 = '\uffff';
            int i2 = 2;
            int i3 = 0;
            switch (key.hashCode()) {
                case -1209246707:
                    if (key.equals("columnSpacing")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -669554715:
                    if (key.equals("spanCount")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 3076010:
                    if (key.equals("data")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 99996865:
                    if (key.equals("minimumLineSpacing")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 320386138:
                    if (key.equals("onLoadMore")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 341662084:
                    if (key.equals("layoutType")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 386979759:
                    if (key.equals("onScrollEnd")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 638068610:
                    if (key.equals("minimumInteritemSpacing")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 704521400:
                    if (key.equals(DYConstants.DY_MAX_COUNT)) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case 1051770806:
                    if (key.equals("onLoadRefresh")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case 1196808457:
                    if (key.equals("rowSpacing")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case 1372310438:
                    if (key.equals("clipToPadding")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 1490730380:
                    if (key.equals("onScroll")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 1614714674:
                    if (key.equals("scrollDirection")) {
                        c2 = '\r';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    try {
                        i3 = Integer.parseInt(str);
                    } catch (Exception e2) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidCollectionParse parseAttribute columnSpacing error", b(), d(), e2);
                    }
                    collectionView.setColumnSpacing(i3);
                    break;
                case 1:
                    try {
                        i2 = Integer.parseInt(str);
                    } catch (Exception e3) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidCollectionParse parseAttribute spanCount error", b(), d(), e3);
                    }
                    collectionView.setSpanCount(i2);
                    break;
                case 2:
                    collectionView.setData(str);
                    break;
                case 3:
                    try {
                        i3 = Integer.parseInt(str);
                    } catch (Exception e4) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidCollectionParse parseAttribute minimumLineSpacing error", b(), d(), e4);
                    }
                    collectionView.setMinimumLineSpacing(i3);
                    break;
                case 4:
                    collectionView.setOnLoadMoreEvent(str);
                    break;
                case 5:
                    collectionView.setLayoutType(str);
                    break;
                case 6:
                    collectionView.setRecyclerViewScrollEndListener(str);
                    break;
                case 7:
                    try {
                        i3 = Integer.parseInt(str);
                    } catch (Exception e5) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidCollectionParse parseAttribute minimumInteritemSpacing error", b(), d(), e5);
                    }
                    collectionView.setMinimumInteritemSpacing(i3);
                    break;
                case '\b':
                    collectionView.setMaxCount(f(str, 0));
                    break;
                case '\t':
                    collectionView.setOnLoadRefreshEvent(str);
                    break;
                case '\n':
                    try {
                        i3 = Integer.parseInt(str);
                    } catch (Exception e6) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidCollectionParse parseAttribute rowSpacing error", b(), d(), e6);
                    }
                    collectionView.setRowSpacing(i3);
                    break;
                case 11:
                    collectionView.serClipToPadding("1".equals(str));
                    break;
                case '\f':
                    collectionView.setRecyclerViewScrollListener(str);
                    break;
                case '\r':
                    try {
                        i3 = Integer.parseInt(str);
                    } catch (Exception e7) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidCollectionParse parseAttribute scrollDirection error", b(), d(), e7);
                    }
                    collectionView.setScrollDirection(i3);
                    break;
            }
        }
        collectionView.finishSetting();
    }
}
