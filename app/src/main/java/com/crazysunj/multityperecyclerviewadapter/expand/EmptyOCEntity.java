package com.crazysunj.multityperecyclerviewadapter.expand;

import androidx.annotation.Nullable;

import com.crazysunj.multitypeadapter.sticky.StickyHeaderDecoration;

import java.util.UUID;

/**
 * description
 * <p>
 * Created by sunjian on 2017/7/5.
 */

public class EmptyOCEntity implements OpenCloseItem {

    private long id;

    private String title;
    private int type;
    private final int FLAG = -3;

    public EmptyOCEntity(int type, String title) {
        this.type = type;
        this.title = title;
        id = UUID.nameUUIDFromBytes((FLAG + title).getBytes()).hashCode();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getFlag() {
        return FLAG;
    }

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof EmptyOCEntity)) {
            return false;
        }
        if (id != ((EmptyOCEntity) obj).id) {
            return false;
        }
        if (title == null) {
            return false;
        }
        return title.equals(((EmptyOCEntity) obj).title);
    }

    @Override
    public long getHeaderId() {
        return StickyHeaderDecoration.NO_HEADER_ID;
    }
}
