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
package com.crazysunj.multitypeadapter.entity;

/**
 * @author: sunjian
 * created on: 2017/3/28
 * description: 多类型实体类接口
 */
public interface MultiTypeEntity {

    /**
     * itemType标志数据的类型
     * 对应{@link androidx.recyclerview.widget.RecyclerView.Adapter#getItemViewType(int)}
     *
     * @return int
     */
    int getItemType();

    /**
     * 比较两个数据内容是否相同，完全由用户去自定义，这里不采用系统的equals是因为Java的许多集合操作都会关联这个方法
     * @param entity
     * @return
     */
//    boolean areContentsTheSame(MultiTypeEntity entity);
}
