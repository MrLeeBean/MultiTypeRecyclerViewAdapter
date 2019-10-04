package com.crazysunj.multityperecyclerviewadapter.expand;

import androidx.annotation.Nullable;

import com.crazysunj.multitypeadapter.helper.RecyclerViewAdapterHelper;
import com.crazysunj.multitypeadapter.sticky.StickyHeaderDecoration;

import java.util.UUID;

/**
 * description
 * <p>
 * Created by sunjian on 2017/7/5.
 */

public class TitleOCEntity implements OpenCloseItem {

    private long id;

    private String title;
    private int type;
    private final int FLAG = -1;

    public TitleOCEntity(int type, String title) {
        this.type = type;
        this.title = title;
        id = UUID.nameUUIDFromBytes((FLAG + title).getBytes()).hashCode();
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
        return type - RecyclerViewAdapterHelper.HEADER_TYPE_DIFFER;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof TitleOCEntity)) {
            return false;
        }
        if (id != ((TitleOCEntity) obj).id) {
            return false;
        }
        if (title == null) {
            return false;
        }
        return title.equals(((TitleOCEntity) obj).title);
    }

    @Override
    public long getHeaderId() {
        return StickyHeaderDecoration.NO_HEADER_ID;
    }
}
