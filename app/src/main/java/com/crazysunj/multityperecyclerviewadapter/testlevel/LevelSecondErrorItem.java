package com.crazysunj.multityperecyclerviewadapter.testlevel;

import androidx.annotation.Nullable;

import static com.crazysunj.multityperecyclerviewadapter.testlevel.TestLevelAdapter.TYPE_LEVEL_SECOND_ERROR;

/**
 * @author: sunjian
 * created on: 2019/1/7 上午10:21
 * description:
 */
public class LevelSecondErrorItem implements MultiTypeTitleEntity {

    private String msg;

    public LevelSecondErrorItem(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public int getItemType() {
        return TYPE_LEVEL_SECOND_ERROR;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof LevelSecondErrorItem)) {
            return false;
        }
        if (msg == null) {
            return false;
        }
        return msg.equals(((LevelSecondErrorItem) obj).msg);
    }
}
