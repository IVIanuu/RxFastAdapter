/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.rxfastadapter.eventhookcallback;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Used to bind events
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public interface EventHookCallback {
    /**
     * Returns the view of this event hook
     */
    @Nullable
    View onBind(@NonNull RecyclerView.ViewHolder viewHolder);

    /**
     * Returns multiple views of this event hook
     */
    @Nullable
    List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder);
}
