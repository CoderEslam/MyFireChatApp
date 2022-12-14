package com.doubleclick.hidely;

public interface HidelyInterface {
    void show();

    void hide();

    boolean isShowing();

    void setAnimationCallbacks(HidelyAnimationCallbacks animationCallbacks);
}