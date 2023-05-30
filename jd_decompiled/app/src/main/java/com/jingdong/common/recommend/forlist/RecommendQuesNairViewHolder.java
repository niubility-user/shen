package com.jingdong.common.recommend.forlist;

import android.view.View;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendDna;

/* loaded from: classes6.dex */
public class RecommendQuesNairViewHolder extends BaseRecommendViewHolder {
    private View.OnClickListener onClickListener;
    private RecommendDna qustion;

    public RecommendQuesNairViewHolder(View view) {
        super(view);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendQuesNairViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendQuesNairViewHolder recommendQuesNairViewHolder = RecommendQuesNairViewHolder.this;
                if (recommendQuesNairViewHolder.clickedListener == null || recommendQuesNairViewHolder.qustion == null) {
                    return;
                }
                RecommendMtaUtils.recommendQuestionnairClickMta(RecommendQuesNairViewHolder.this.itemView.getContext(), RecommendQuesNairViewHolder.this.qustion);
                RecommendQuesNairViewHolder recommendQuesNairViewHolder2 = RecommendQuesNairViewHolder.this;
                recommendQuesNairViewHolder2.clickedListener.onRecommendChannelJump(recommendQuesNairViewHolder2.qustion);
            }
        };
        this.onClickListener = onClickListener;
        view.setOnClickListener(onClickListener);
    }

    public void setData(RecommendDna recommendDna, ExpoDataStore expoDataStore) {
        this.qustion = recommendDna;
    }
}
