package com.crazysunj.multityperecyclerviewadapter.data;

import androidx.annotation.Nullable;

import com.crazysunj.multityperecyclerviewadapter.R;
import com.crazysunj.multityperecyclerviewadapter.helper.SimpleHelper;
import com.crazysunj.multityperecyclerviewadapter.sticky.FirstStickyItem;

import java.util.UUID;

/**
 * Created by sunjian on 2017/3/28.
 */

public class FirstItem implements FirstStickyItem {

    private String name;

    private String stickyName = "第一条粘性";

    private int img = R.mipmap.ic_launcher;

    private long id;

    public FirstItem(String name) {
        this.name = name;
         id = UUID.nameUUIDFromBytes((name + stickyName).getBytes()).hashCode();
    }

    public FirstItem(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FirstItem)) {
            return false;
        }
        return id == ((FirstItem) obj).id;
    }

    @Override
    public long getHeaderId() {
        return 1;
    }

    @Override
    public int getItemType() {
        return SimpleHelper.TYPE_ONE;
    }

    @Override
    public void setHeaderId(long headerId) {

    }

    @Override
    public void setStickyName(String stickyName) {

    }

    @Override
    public String getStickyName() {
        return stickyName;
    }
}
