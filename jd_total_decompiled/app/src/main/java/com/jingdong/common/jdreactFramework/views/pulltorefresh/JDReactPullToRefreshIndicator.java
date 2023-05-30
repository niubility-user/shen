package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.graphics.PointF;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshIndicator {
    public static final int POS_START = 0;
    private int mHeaderHeight;
    private float mOffsetX;
    private float mOffsetY;
    protected int mOffsetToRefresh = 0;
    private PointF mPtLastMove = new PointF();
    private int mCurrentPos = 0;
    private int mLastPos = 0;
    private int mPressedPos = 0;
    private float mRatioOfHeaderHeightToRefresh = 1.2f;
    private float mResistance = 1.7f;
    private boolean mIsUnderTouch = false;
    private int mOffsetToKeepHeaderWhileLoading = -1;
    private int mRefreshCompleteY = 0;

    public void convertFrom(JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator) {
        this.mCurrentPos = jDReactPullToRefreshIndicator.mCurrentPos;
        this.mLastPos = jDReactPullToRefreshIndicator.mLastPos;
        this.mHeaderHeight = jDReactPullToRefreshIndicator.mHeaderHeight;
    }

    public boolean crossRefreshLineFromTopToBottom() {
        return this.mLastPos < getOffsetToRefresh() && this.mCurrentPos >= getOffsetToRefresh();
    }

    public float getCurrentPercent() {
        int i2 = this.mHeaderHeight;
        if (i2 == 0) {
            return 0.0f;
        }
        return (this.mCurrentPos * 1.0f) / i2;
    }

    public int getCurrentPosY() {
        return this.mCurrentPos;
    }

    public int getHeaderHeight() {
        return this.mHeaderHeight;
    }

    public float getLastPercent() {
        int i2 = this.mHeaderHeight;
        if (i2 == 0) {
            return 0.0f;
        }
        return (this.mLastPos * 1.0f) / i2;
    }

    public int getLastPosY() {
        return this.mLastPos;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        int i2 = this.mOffsetToKeepHeaderWhileLoading;
        return i2 >= 0 ? i2 : this.mHeaderHeight;
    }

    public int getOffsetToRefresh() {
        return this.mOffsetToRefresh;
    }

    public float getOffsetX() {
        return this.mOffsetX;
    }

    public float getOffsetY() {
        return this.mOffsetY;
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.mRatioOfHeaderHeightToRefresh;
    }

    public float getResistance() {
        return this.mResistance;
    }

    public boolean goDownCrossFinishPosition() {
        return this.mCurrentPos >= this.mRefreshCompleteY;
    }

    public boolean hasJustBackToStartPosition() {
        return this.mLastPos != 0 && isInStartPosition();
    }

    public boolean hasJustLeftStartPosition() {
        return this.mLastPos == 0 && hasLeftStartPosition();
    }

    public boolean hasJustReachedHeaderHeightFromTopToBottom() {
        int i2 = this.mLastPos;
        int i3 = this.mHeaderHeight;
        return i2 < i3 && this.mCurrentPos >= i3;
    }

    public boolean hasLeftStartPosition() {
        return this.mCurrentPos > 0;
    }

    public boolean hasMovedAfterPressedDown() {
        return this.mCurrentPos != this.mPressedPos;
    }

    public boolean isAlreadyHere(int i2) {
        return this.mCurrentPos == i2;
    }

    public boolean isInStartPosition() {
        return this.mCurrentPos == 0;
    }

    public boolean isOverOffsetToKeepHeaderWhileLoading() {
        return this.mCurrentPos > getOffsetToKeepHeaderWhileLoading();
    }

    public boolean isOverOffsetToRefresh() {
        return this.mCurrentPos >= getOffsetToRefresh();
    }

    public boolean isUnderTouch() {
        return this.mIsUnderTouch;
    }

    public final void onMove(float f2, float f3) {
        PointF pointF = this.mPtLastMove;
        processOnMove(f2, f3, f2 - pointF.x, f3 - pointF.y);
        this.mPtLastMove.set(f2, f3);
    }

    public void onPressDown(float f2, float f3) {
        this.mIsUnderTouch = true;
        this.mPressedPos = this.mCurrentPos;
        this.mPtLastMove.set(f2, f3);
    }

    public void onRelease() {
        this.mIsUnderTouch = false;
    }

    public void onUIRefreshComplete() {
        this.mRefreshCompleteY = this.mCurrentPos;
    }

    protected void onUpdatePos(int i2, int i3) {
    }

    protected void processOnMove(float f2, float f3, float f4, float f5) {
        setOffset(f4, f5 / this.mResistance);
    }

    public final void setCurrentPos(int i2) {
        int i3 = this.mCurrentPos;
        this.mLastPos = i3;
        this.mCurrentPos = i2;
        onUpdatePos(i2, i3);
    }

    public void setHeaderHeight(int i2) {
        this.mHeaderHeight = i2;
        updateHeight();
    }

    protected void setOffset(float f2, float f3) {
        this.mOffsetX = f2;
        this.mOffsetY = f3;
    }

    public void setOffsetToKeepHeaderWhileLoading(int i2) {
        this.mOffsetToKeepHeaderWhileLoading = i2;
    }

    public void setOffsetToRefresh(int i2) {
        this.mRatioOfHeaderHeightToRefresh = (this.mHeaderHeight * 1.0f) / i2;
        this.mOffsetToRefresh = i2;
    }

    public void setRatioOfHeaderHeightToRefresh(float f2) {
        this.mRatioOfHeaderHeightToRefresh = f2;
        this.mOffsetToRefresh = (int) (this.mHeaderHeight * f2);
    }

    public void setResistance(float f2) {
        this.mResistance = f2;
    }

    protected void updateHeight() {
        this.mOffsetToRefresh = (int) (this.mRatioOfHeaderHeightToRefresh * this.mHeaderHeight);
    }

    public boolean willOverTop(int i2) {
        return i2 < 0;
    }
}
