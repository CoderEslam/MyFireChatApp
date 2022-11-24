/*
 * Copyright 2022 Randy Webster. All rights reserved.
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

package com.doubleclick.fastscroller.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal val RecyclerView.LayoutManager?.firstVisibleItemPosition
    get() = when (this) {
        is LinearLayoutManager -> findFirstVisibleItemPosition()
        is StaggeredGridLayoutManager -> findFirstVisibleItemPositions(null)[0]
        else -> 0
    }

internal val RecyclerView.LayoutManager?.isLayoutReversed
    get() = when (this) {
        is LinearLayoutManager -> reverseLayout
        is StaggeredGridLayoutManager -> reverseLayout
        else -> false
    }
