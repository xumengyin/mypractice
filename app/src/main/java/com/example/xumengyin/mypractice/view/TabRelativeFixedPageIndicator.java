/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.xumengyin.mypractice.view; 

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xumengyin.mypractice.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
public class TabRelativeFixedPageIndicator extends HorizontalScrollView implements PageIndicator {
    /**
     * Title text used when no title is provided by the adapter.
     */
    private static final CharSequence EMPTY_TITLE = "";

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    private Runnable mTabSelector;

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabRelativeView tabView = (TabRelativeView) view;
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();
            mViewPager.setCurrentItem(newSelected);
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

    public final IcsLinearLayout mTabLayout;

    private ViewPager mViewPager;
    private OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    public TabRelativeFixedPageIndicator(Context context) {
        this(context, null);
    }

    public TabRelativeFixedPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        mTabLayout = new IcsLinearLayout(context, R.attr.vpiTabFixedPageIndicatorStyle);
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, LayoutParams.FILL_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int mwidth=getMeasuredWidth();
        int mheight=getMeasuredHeight();
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(scrollPos, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }
    ColorStateList colorStateList;
    public void setColorList(ColorStateList colorStateList)
    {
        this.colorStateList=colorStateList;
    }
    private void addTab(int index, CharSequence text, int iconResId) {
        final TabRelativeView tabView = new TabRelativeView(getContext());
        tabView.mIndex = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);
        if(colorStateList!=null)
        tabView.setTabColor(colorStateList);
        if (iconResId != 0) {
            tabView.setResIdt(iconResId);
        }

        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, LayoutParams.FILL_PARENT, 1));
    }

    public void setMessage(int position) {
        final TabRelativeView tabView = (TabRelativeView) mTabLayout.getChildAt(position);
        tabView.setNoMessage();
    }

    public void setMessage(int position, int visible, int count) {
        final TabRelativeView tabView = (TabRelativeView) mTabLayout.getChildAt(position);
        tabView.setMessage(visible, count);
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
        if (mListener != null) {
            mListener.onPageSelected(arg0);
        }
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        IconPagerAdapter iconAdapter = null;
        if (adapter instanceof IconPagerAdapter) {
            iconAdapter = (IconPagerAdapter) adapter;
        }
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            int iconResId = 0;
            if (iconAdapter != null) {
                iconResId = iconAdapter.getIconResId(i);
            }
            addTab(i, title, iconResId);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
        mViewPager.setCurrentItem(item);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }

    public IcsLinearLayout getIcs() {
        return mTabLayout;
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

//    public class TabView extends TextView {
//        private int mIndex;
//
//        public TabView(Context context) {
//            super(context, null, R.attr.vpiTabFixedPageIndicatorStyle);
//        }
//
//        @Override
//        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//            // Re-measure if we went beyond our maximum size.
//            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
//                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
//                        heightMeasureSpec);
//            }
//        }
//
//        public int getIndex() {
//            return mIndex;
//        }
//    }

    public class TabRelativeView extends RelativeLayout
    {

        private TextView tvTab;
        private TextView tvMessage;
        private int mIndex;
        private ImageView img;

        public TabRelativeView(Context context) {
            this(context, null);
        }

        public TabRelativeView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public TabRelativeView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            initView(context, attrs);
        }

        private void initView(Context context, AttributeSet attrs) {
            View view = LayoutInflater.from(context).inflate(R.layout.tab_view_layout, this);
            tvTab = (TextView) view.findViewById(R.id.tv_tab);
            tvMessage = (TextView) view.findViewById(R.id.tv_message);
            img = (ImageView) view.findViewById(R.id.img);
        }

        public void setText(CharSequence text) {
            tvTab.setText(text);
        }

        public void setResIdt(int iconResId) {
            img.setImageResource(iconResId);
        }

        public void setNoMessage() {
            setMessage(0, 0);
        }

        public void setMessage(int visible, int count) {
            if (visible == 0) {
                tvMessage.setVisibility(GONE);
            } else if (visible == 1) {
                tvMessage.setVisibility(VISIBLE);
                if (count == 0) {
                    tvMessage.setText("");
                } else {
                    tvMessage.setText("" + count);
                }
            }

        }

        public void setTabColor(ColorStateList color)
        {
            tvTab.setTextColor(color);
        }
        public int getIndex() {
            return mIndex;
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int width=MeasureSpec.getSize(widthMeasureSpec);
            int height=MeasureSpec.getSize(heightMeasureSpec);
            int mwidth=getMeasuredWidth();
            int mheight=getMeasuredHeight();
            getIndex();
            // Re-measure if we went beyond our maximum size.
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            }
        }
    }
}
