package com.jd.lib.un.basewidget.widget.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.flow.TagAdapter;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes16.dex */
public class TagFlowLayout extends FlowLayout implements TagAdapter.OnDataChangedListener {
    private static final String KEY_CHOOSE_POS = "key_choose_pos";
    private static final String KEY_DEFAULT = "key_default";
    private static final String TAG = "TagFlowLayout";
    private OnSelectListener mOnSelectListener;
    private OnTagClickListener mOnTagClickListener;
    private int mSelectedMax;
    private Set<Integer> mSelectedView;
    private TagAdapter mTagAdapter;

    /* loaded from: classes16.dex */
    public interface OnSelectListener {
        void onSelected(Set<Integer> set);
    }

    /* loaded from: classes16.dex */
    public interface OnTagClickListener {
        boolean onTagClick(View view, int i2, FlowLayout flowLayout);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSelectedMax = -1;
        this.mSelectedView = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.mSelectedMax = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_select, -1);
        obtainStyledAttributes.recycle();
    }

    private void changeAdapter() {
        removeAllViews();
        TagAdapter tagAdapter = this.mTagAdapter;
        HashSet<Integer> preCheckedList = tagAdapter.getPreCheckedList();
        for (final int i2 = 0; i2 < tagAdapter.getCount(); i2++) {
            View view = tagAdapter.getView(this, i2, tagAdapter.getItem(i2));
            final TagItemView tagItemView = new TagItemView(getContext());
            view.setDuplicateParentStateEnabled(true);
            if (view.getLayoutParams() != null) {
                tagItemView.setLayoutParams(view.getLayoutParams());
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f), dip2px(getContext(), 5.0f));
                tagItemView.setLayoutParams(marginLayoutParams);
            }
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            tagItemView.addView(view);
            addView(tagItemView);
            if (preCheckedList.contains(Integer.valueOf(i2))) {
                setChildChecked(i2, tagItemView);
            }
            if (this.mTagAdapter.setSelected(i2, tagAdapter.getItem(i2))) {
                setChildChecked(i2, tagItemView);
            }
            view.setClickable(false);
            tagItemView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.un.basewidget.widget.flow.TagFlowLayout.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    TagFlowLayout.this.doSelect(tagItemView, i2);
                    if (TagFlowLayout.this.mOnTagClickListener != null) {
                        TagFlowLayout.this.mOnTagClickListener.onTagClick(tagItemView, i2, TagFlowLayout.this);
                    }
                }
            });
        }
        this.mSelectedView.addAll(preCheckedList);
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * UnDeviceInfo.getDensity()) + 0.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSelect(TagItemView tagItemView, int i2) {
        if (!tagItemView.isChecked()) {
            if (this.mSelectedMax == 1 && this.mSelectedView.size() == 1) {
                Integer next = this.mSelectedView.iterator().next();
                setChildUnChecked(next.intValue(), (TagItemView) getChildAt(next.intValue()));
                setChildChecked(i2, tagItemView);
                this.mSelectedView.remove(next);
                this.mSelectedView.add(Integer.valueOf(i2));
            } else if (this.mSelectedMax > 0 && this.mSelectedView.size() >= this.mSelectedMax) {
                return;
            } else {
                setChildChecked(i2, tagItemView);
                this.mSelectedView.add(Integer.valueOf(i2));
            }
        } else {
            setChildUnChecked(i2, tagItemView);
            this.mSelectedView.remove(Integer.valueOf(i2));
        }
        OnSelectListener onSelectListener = this.mOnSelectListener;
        if (onSelectListener != null) {
            onSelectListener.onSelected(new HashSet(this.mSelectedView));
        }
    }

    private void setChildChecked(int i2, TagItemView tagItemView) {
        tagItemView.setChecked(true);
        this.mTagAdapter.onSelected(i2, tagItemView.getTagView());
    }

    private void setChildUnChecked(int i2, TagItemView tagItemView) {
        tagItemView.setChecked(false);
        this.mTagAdapter.unSelected(i2, tagItemView.getTagView());
    }

    public TagAdapter getAdapter() {
        return this.mTagAdapter;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.mSelectedView);
    }

    @Override // com.jd.lib.un.basewidget.widget.flow.TagAdapter.OnDataChangedListener
    public void onChanged() {
        this.mSelectedView.clear();
        changeAdapter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.un.basewidget.widget.flow.FlowLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            TagItemView tagItemView = (TagItemView) getChildAt(i4);
            if (tagItemView.getVisibility() != 8 && tagItemView.getTagView().getVisibility() == 8) {
                tagItemView.setVisibility(8);
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            String string = bundle.getString(KEY_CHOOSE_POS);
            if (!TextUtils.isEmpty(string)) {
                for (String str : string.split(DYConstants.DY_REGEX_VERTICAL_LINE)) {
                    int parseInt = Integer.parseInt(str);
                    this.mSelectedView.add(Integer.valueOf(parseInt));
                    TagItemView tagItemView = (TagItemView) getChildAt(parseInt);
                    if (tagItemView != null) {
                        setChildChecked(parseInt, tagItemView);
                    }
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());
        String str = "";
        if (this.mSelectedView.size() > 0) {
            Iterator<Integer> it = this.mSelectedView.iterator();
            while (it.hasNext()) {
                str = str + it.next().intValue() + "|";
            }
            str = str.substring(0, str.length() - 1);
        }
        bundle.putString(KEY_CHOOSE_POS, str);
        return bundle;
    }

    public void setAdapter(TagAdapter tagAdapter) {
        this.mTagAdapter = tagAdapter;
        tagAdapter.setOnDataChangedListener(this);
        this.mSelectedView.clear();
        changeAdapter();
    }

    public void setMaxSelectCount(int i2) {
        if (this.mSelectedView.size() > i2) {
            String str = "you has already select more than " + i2 + " views , so it will be clear .";
            this.mSelectedView.clear();
        }
        this.mSelectedMax = i2;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }
}
