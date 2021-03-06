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

package com.ivianuu.rxfastadapter.click;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ivianuu.rxfastadapter.eventhookcallback.EventHookCallback;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Fast adapter click event hook observable
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class ClickEventHookObservable<T extends IItem>
        implements ObservableOnSubscribe<ClickEvent<T>> {

    private final FastAdapter<T> adapter;
    private final EventHookCallback callback;

    private ClickEventHookObservable(FastAdapter<T> adapter, EventHookCallback callback) {
        this.adapter = adapter;
        this.callback = callback;
    }

    /**
     * Emits on click events
     */
    @CheckResult @NonNull
    public static <T extends IItem> Observable<ClickEvent<T>> create(@NonNull FastAdapter<T> adapter,
                                                                     @NonNull EventHookCallback callback) {
        return Observable.create(new ClickEventHookObservable<T>(adapter, callback));
    }

    @Override
    public void subscribe(final ObservableEmitter<ClickEvent<T>> e) throws Exception {
        adapter.withEventHook(new ClickEventHook<T>() {
            @Override
            public void onClick(View v, int position, FastAdapter<T> fastAdapter, T item) {
                ClickEvent<T> clickEventHookEvent = new ClickEvent<>(v, adapter.getAdapter(position), item, position);
                e.onNext(clickEventHookEvent);
            }

            @Nullable
            @Override
            public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
                return callback.onBind(viewHolder);
            }

            @Nullable
            @Override
            public List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder) {
                return callback.onBindMany(viewHolder);
            }
        });

        e.setCancellable(() -> {});
    }
}
