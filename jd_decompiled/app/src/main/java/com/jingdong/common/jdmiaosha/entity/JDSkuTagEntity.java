package com.jingdong.common.jdmiaosha.entity;

import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR$\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR$\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0004\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR$\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b\u00a8\u0006\u0017"}, d2 = {"Lcom/jingdong/common/jdmiaosha/entity/JDSkuTagEntity;", "Ljava/io/Serializable;", "", "tagLeftIcon", "Ljava/lang/String;", "getTagLeftIcon", "()Ljava/lang/String;", "setTagLeftIcon", "(Ljava/lang/String;)V", "tagColorEnd", "getTagColorEnd", "setTagColorEnd", "tagText", "getTagText", "setTagText", "tagImg", "getTagImg", "setTagImg", "tagColorBegin", "getTagColorBegin", "setTagColorBegin", "<init>", "()V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class JDSkuTagEntity implements Serializable {
    @Nullable
    private String tagColorBegin;
    @Nullable
    private String tagColorEnd;
    @Nullable
    private String tagImg;
    @Nullable
    private String tagLeftIcon;
    @Nullable
    private String tagText;

    @Nullable
    public final String getTagColorBegin() {
        return this.tagColorBegin;
    }

    @Nullable
    public final String getTagColorEnd() {
        return this.tagColorEnd;
    }

    @Nullable
    public final String getTagImg() {
        return this.tagImg;
    }

    @Nullable
    public final String getTagLeftIcon() {
        return this.tagLeftIcon;
    }

    @Nullable
    public final String getTagText() {
        return this.tagText;
    }

    public final void setTagColorBegin(@Nullable String str) {
        this.tagColorBegin = str;
    }

    public final void setTagColorEnd(@Nullable String str) {
        this.tagColorEnd = str;
    }

    public final void setTagImg(@Nullable String str) {
        this.tagImg = str;
    }

    public final void setTagLeftIcon(@Nullable String str) {
        this.tagLeftIcon = str;
    }

    public final void setTagText(@Nullable String str) {
        this.tagText = str;
    }
}
