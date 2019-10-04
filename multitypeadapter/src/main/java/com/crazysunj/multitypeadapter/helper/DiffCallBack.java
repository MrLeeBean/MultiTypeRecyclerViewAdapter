/*
  Copyright 2017 Sun Jian
  <p>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p>
  http://www.apache.org/licenses/LICENSE-2.0
  <p>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.crazysunj.multitypeadapter.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.crazysunj.multitypeadapter.entity.MultiTypeEntity;

import java.util.List;

/**
 * @author: sunjian
 * created on: 2017/3/28
 * description: 关于新老数据比较的callback，暂时不提供出去
 * 如果你有自己的比较逻辑，实现接口{@link RecyclerViewAdapterHelper#getDiffCallBack(List, List)}
 */
class DiffCallBack<T extends MultiTypeEntity> extends DiffUtil.Callback {

    private List<T> mOldData;
    private List<T> mNewData;

    DiffCallBack(List<T> oldData, List<T> newData) {
        this.mOldData = oldData;
        this.mNewData = newData;
    }

    @Override
    public int getOldListSize() {
        return mOldData == null ? 0 : mOldData.size();
    }

    @Override
    public int getNewListSize() {
        return mNewData == null ? 0 : mNewData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = mOldData.get(oldItemPosition);
        T newItem = mNewData.get(newItemPosition);
        // 先把不同itemType过滤掉
        return !(oldItem == null || newItem == null) && oldItem.getItemType() == newItem.getItemType();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = mOldData.get(oldItemPosition);
        T newItem = mNewData.get(newItemPosition);
        return oldItem.equals(newItem);
    }
}
