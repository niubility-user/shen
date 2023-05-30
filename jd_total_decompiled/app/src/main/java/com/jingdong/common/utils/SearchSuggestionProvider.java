package com.jingdong.common.utils;

import android.content.SearchRecentSuggestionsProvider;

/* loaded from: classes6.dex */
public class SearchSuggestionProvider extends SearchRecentSuggestionsProvider {
    static final String AUTHORITY = "com.jd.app.trade.utils.SearchSuggestionProvider";
    static final int MODE = 1;

    public SearchSuggestionProvider() {
        setupSuggestions(AUTHORITY, 1);
    }
}
