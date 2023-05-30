package com.jd.lib.productdetail.core.entitys.thumbnail;

import android.text.TextUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class ThumbnailEntity {
    public static final int FLOOR_TYPE_ICON_TEXT = 2;
    public static final int FLOOR_TYPE_IMAGE = 0;
    public static final int FLOOR_TYPE_SPEC = 1;
    public String color;
    public List<ThumbnailItemEntity> items;
    public int selectPosition = -1;
    public int showStyleEnum = -1;
    public boolean showTotalButton;
    public String title;
    public int totalCount;

    public boolean isEffective() {
        List<ThumbnailItemEntity> list = this.items;
        return (list == null || list.size() <= 1 || TextUtils.isEmpty(this.title)) ? false : true;
    }
}
