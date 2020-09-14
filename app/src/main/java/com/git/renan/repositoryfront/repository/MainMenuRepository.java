package com.git.renan.repositoryfront.repository;

import android.content.Context;
import android.widget.Toast;

import com.git.renan.repositoryfront.R;
import com.git.renan.repositoryfront.adapter.MainMenuAdapter;
import com.git.renan.repositoryfront.model.MenuType;

import java.util.ArrayList;
import java.util.List;

public class MainMenuRepository {

    public static List<MainMenuAdapter.ListItem> generateMenuItemsRepository(){
        List<MainMenuAdapter.ListItem> items = new ArrayList<>();

        items.add(new MainMenuAdapter.ListItem(-1, null, -1, MenuType.DIVIDER));
        items.add(new MainMenuAdapter.ListItem(100, "Bottom Navigation", R.drawable.ic_view_column, MenuType.HEADER));
        items.add(new MainMenuAdapter.ListItem(101, "Basic", -1, MenuType.SUB_HEADER));

        items.add(new MainMenuAdapter.ListItem(200, "Bottom Sheet", R.drawable.ic_call_to_actio, MenuType.HEADER));
        items.add(new MainMenuAdapter.ListItem(201, "Basic", -1, MenuType.SUB_HEADER));

        return items;
    }

    public static void onMenuItemSelectedRecycler(int itemId, Context context){
        switch (itemId){
            case 101:
                Toast.makeText(context, "Bottom Navigation", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(context, "Item "+itemId, Toast.LENGTH_SHORT).show();
        }
    }
}
