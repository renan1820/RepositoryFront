package com.git.renan.repositoryfront.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.git.renan.repositoryfront.R;
import com.git.renan.repositoryfront.model.MenuType;

import java.util.List;

public class MainMenuAdapter extends ExpandableRecyclerAdapter<MainMenuAdapter.ListItem> {
    private Context context;
    private OnItemClickListener onItemClickListener;

    public MainMenuAdapter(Context context, List<ListItem> items, OnItemClickListener onItemClickListener){
        super(context);
        this.context = context;
        this.onItemClickListener = onItemClickListener;
        setItems(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View divider = new View(context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2));
        divider.setBackgroundColor(context.getResources().getColor(R.color.grey_60));
        if(viewType == NORMAL){
            return new PlainViewHolder(inflate(R.layout.item_menu_plain, parent));
        } else if (viewType == HEADER) {
            return new HeaderViewHolder(inflate(R.layout.item_menu_group, parent));
        } else if (viewType == SUB_HEADER) {
            return new SubHeaderViewHolder(inflate(R.layout.item_menu_sub_group, parent));
        } else if (viewType == DIVIDER) {
            return new DividerViewHolder(inflate(R.layout.item_menu_divider, parent));
        }else{
            return new PlainViewHolder(inflate(R.layout.item_menu_group, parent));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExpandableRecyclerAdapter.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == NORMAL) {
            ((PlainViewHolder) holder).bind(position);
        } else if (viewType == HEADER) {
            ((HeaderViewHolder) holder).bind(position);
        } else if (viewType == SUB_HEADER) {
            ((SubHeaderViewHolder) holder).bind(position);
        } else if (viewType == DIVIDER) {
            ((DividerViewHolder) holder).bind(position);
        } else {
            ((SubHeaderViewHolder) holder).bind(position);
        }
    }


    public static class ListItem extends ExpandableRecyclerAdapter.ListItem {
        public int id;
        public int icon;
        public String title;
        public boolean isNew = false;

        public ListItem(int id, String title, int icon, MenuType type) {
            super(type.getValue());
            this.id = id;
            this.title = title;
            this.icon = icon;
        }

        public ListItem(int id, String title, int icon, boolean isNew, MenuType type) {
            super(type.getValue());
            this.id = id;
            this.title = title;
            this.icon = icon;
            this.isNew = isNew;
        }
    }

    /**
     * ClassView for menu with icon and text
     */
    public class PlainViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        View view;
        TextView name;
        ImageView icon;

        public PlainViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.item_menu_group_name);
            icon = view.findViewById(R.id.item_menu_group_image);
        }

        public void bind(final int position) {
            name.setText(visibleItems.get(position).title);
            icon.setImageResource(visibleItems.get(position).icon);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, visibleItems.get(position).id);
                }
            });
        }
    }

    /**
     * ClassView for menu with icon, text and expandable feature
     */
    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView name;
        ImageView icon;
        ImageView badge;

        public HeaderViewHolder(View view) {
            super(view, (ImageView) view.findViewById(R.id.item_arrow));
            name = view.findViewById(R.id.item_menu_group_name);
            icon = view.findViewById(R.id.item_menu_group_image);
            badge = view.findViewById(R.id.item_menu_group_badge);
        }

        public void bind(final int position) {
            super.bind(position);
            name.setText(visibleItems.get(position).title);
            icon.setImageResource(visibleItems.get(position).icon);
            badge.setVisibility(visibleItems.get(position).isNew ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * ClassView for menu with text and sub header from HeaderViewHolder
     */
    public class SubHeaderViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        View view;
        TextView name;
        ImageView badge;

        public SubHeaderViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.item_menu_sub_group_name);
            badge = view.findViewById(R.id.item_menu_sub_group_badge);
        }

        public void bind(final int position) {
            name.setText(visibleItems.get(position).title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, visibleItems.get(position).id);
                }
            });
            badge.setVisibility(visibleItems.get(position).isNew ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * ClassView for menu divider line
     */
    public class DividerViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView name;

        public DividerViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.item_menu_divider_name);
        }

        public void bind(int position) {
            if (visibleItems.get(position).title == null) {
                name.setVisibility(View.GONE);
            } else {
                name.setVisibility(View.VISIBLE);
                name.setText(visibleItems.get(position).title);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int itemId);
    }
}
