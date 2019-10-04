package com.crazysunj.multityperecyclerviewadapter.expand;

import androidx.annotation.Nullable;

import com.crazysunj.multitypeadapter.helper.RecyclerViewAdapterHelper;
import com.crazysunj.multitypeadapter.sticky.StickyHeaderDecoration;

import java.util.Locale;

/**
 * description
 * <p>
 * Created by sunjian on 2017/7/5.
 */

public class FooterOCEntity implements OpenCloseItem {

    private long id;
    private String title;
    private int type;
    private final int FLAG = -2;

    public FooterOCEntity(int type, String title, long id) {
        this.type = type;
        this.title = title;
        this.id = id;
//        id = String.format(Locale.getDefault(), "%d_%d_%s", FLAG, type, title).hashCode();
    }

    public FooterOCEntity(int type, String title) {
        this.type = type;
        this.title = title;
        id = String.format(Locale.getDefault(), "%d_%d_%s", FLAG, type, title).hashCode();
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
        return type - RecyclerViewAdapterHelper.FOOTER_TYPE_DIFFER;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof FooterOCEntity)) {
            return false;
        }
        if (id != ((FooterOCEntity) obj).id) {
            return false;
        }
        if (title == null) {
            return false;
        }
        return title.equals(((FooterOCEntity) obj).title);
    }

    @Override
    public long getHeaderId() {
        return StickyHeaderDecoration.NO_HEADER_ID;
    }
}
