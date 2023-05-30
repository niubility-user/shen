package com.jingdong.manto.pkg.db.entity;

import androidx.annotation.NonNull;
import com.jingdong.manto.provider.db.anno.Table;

@Table(primaryKeys = {"key"}, value = "cardActivity")
/* loaded from: classes16.dex */
public class CardActivityEntity {
    @NonNull
    public String key;
    public String result;
}
