package com.crazysunj.multityperecyclerviewadapter.testlevel;

import androidx.annotation.Nullable;

/**
 * author: sunjian
 * created on: 2018/4/3 上午11:11
 * description:
 */

public class TypeThreeItem implements MultiTypeTitleEntity {

    public static final int TYPE_THREE = 3;
    private long id;
    private String msg;

    public TypeThreeItem(long id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getItemType() {
        return TYPE_THREE;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TypeThreeItem)) {
            return false;
        }
        if (id != ((TypeThreeItem) obj).id) {
            return false;
        }
        if (msg == null) {
            return false;
        }
        return msg.equals(((TypeThreeItem) obj).msg);
    }
}
