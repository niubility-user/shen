package com.jd.lib.productdetail.core.repository;

import androidx.lifecycle.LiveData;
import com.jd.lib.productdetail.core.entitys.comment.PdCommentRepositoryEntity;

/* loaded from: classes15.dex */
public interface IPdCommentDataRepository<T> {
    LiveData<T> loadCommentData(PdCommentRepositoryEntity pdCommentRepositoryEntity);
}
