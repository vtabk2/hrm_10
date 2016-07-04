package com.example.framgia.hrm_10.model.listenner;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by framgia on 27/06/2016.
 */
public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public static final int STAFF_PER_PAGE = 30;
    private static final int INIT_DEFAULT = 0;
    private int mVisibleThreshold = STAFF_PER_PAGE;
    private int mPreviousTotalItemCount = INIT_DEFAULT;
    private boolean mLoading;

    private RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerViewScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int totalItemCount = mLayoutManager.getItemCount();
        int lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        if (totalItemCount < mPreviousTotalItemCount) {
            mPreviousTotalItemCount = totalItemCount;
            if (totalItemCount == INIT_DEFAULT) {
                mLoading = true;
            }
        }
        if (mLoading && (totalItemCount > mPreviousTotalItemCount)) {
            mLoading = false;
            mPreviousTotalItemCount = totalItemCount;
        }
        lastVisibleItemPosition += mVisibleThreshold;
        if (!mLoading && lastVisibleItemPosition > totalItemCount) {
            mLoading = true;
            onLoadMore(totalItemCount);
        }
    }

    public abstract void onLoadMore(int startIndex);

    public void reset() {
        mPreviousTotalItemCount = INIT_DEFAULT;
    }
}
