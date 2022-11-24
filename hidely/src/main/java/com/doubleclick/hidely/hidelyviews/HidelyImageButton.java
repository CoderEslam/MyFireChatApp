package com.doubleclick.hidely.hidelyviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageButton;

import com.doubleclick.hidely.HidelyAnimationCallbacks;
import com.doubleclick.hidely.HidelyCore;
import com.doubleclick.hidely.HidelyInterface;


public class HidelyImageButton extends AppCompatImageButton implements HidelyInterface {
    private HidelyCore hidelyCore;

    public HidelyImageButton(Context context) {
        super(context);
        initCore();
    }

    public HidelyImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCore();

    }

    public HidelyImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCore();
    }

    private void initCore() {
        hidelyCore = new HidelyCore(this);
    }


    @Override
    public void show() {
        if (hidelyCore != null)
            hidelyCore.show();
    }

    @Override
    public void hide() {
        if (hidelyCore != null)
            hidelyCore.hide();
    }

    @Override
    public boolean isShowing() {
        return hidelyCore == null ? false : hidelyCore.isShowing();
    }

    @Override
    public void setAnimationCallbacks(HidelyAnimationCallbacks animationCallbacks) {
        hidelyCore.setAnimationListener(animationCallbacks);
    }
}
