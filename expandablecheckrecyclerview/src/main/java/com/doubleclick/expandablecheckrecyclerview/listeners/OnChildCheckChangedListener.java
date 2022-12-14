package com.doubleclick.expandablecheckrecyclerview.listeners;


import android.view.View;

/**
 * Interface definition for a callback to be invoked when a CheckableChildViewHolder#checkable
 * has been clicked.
 */
public interface OnChildCheckChangedListener {

  /**
   * @param checked The current checked state of the view
   * @param flatPos The flat position (raw index) of the the child within the RecyclerView
   */
  void onChildCheckChanged(View view, boolean checked, int flatPos);

}