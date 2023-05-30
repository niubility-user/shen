package com.jingdong.app.mall.home.category.floor.base;

import android.content.Context;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.n.g.u.c;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFeeds;", "Lcom/jingdong/app/mall/home/n/g/u/c;", "M", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public abstract class BaseCaFeeds<M extends com.jingdong.app.mall.home.n.g.u.c> extends BaseCaEventFloor<M> {
    public BaseCaFeeds(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
    }
}
