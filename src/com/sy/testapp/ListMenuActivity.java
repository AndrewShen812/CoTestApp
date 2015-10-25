package com.sy.testapp;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sy.cartracker.SettingItem;
import com.sy.cartracker.SettingItemFactory;

public class ListMenuActivity extends Activity implements OnItemClickListener{
    
    private ListView mLvMenu;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        
        initView();
    }
    
    
    private void initView() {
        mLvMenu = (ListView) findViewById(R.id.lv_menu);
        mLvMenu.setAdapter(new MenuListAdapter(this));
        mLvMenu.setOnItemClickListener(this);
    }
    
    private class MenuListAdapter extends BaseAdapter {
        private List<SettingItem> Items;
        private Context context;
        public MenuListAdapter(Context context) {
            this.context = context;
            SettingItemFactory fac = new SettingItemFactory(context);
            fac.add(SettingItemFactory.ItemId.SPEED);
            fac.add(SettingItemFactory.ItemId.DISTANCE);
            fac.add(SettingItemFactory.ItemId.TIME);
            fac.add(SettingItemFactory.ItemId.VOICE);
//            fac.add(SettingItemFactory.ItemId.OTHER);
            Items = fac.getItems();
        }
        
        @Override
        public int getCount() {
            return Items == null ? 0 : Items.size();
        }

        @Override
        public Object getItem(int arg0) {
            return Items == null ? null : Items.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder item = null;
            if (convertView == null) {
                item = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_setting_item, null);
                item.mIvIcon = (ImageView) convertView.findViewById(R.id.iv_setting_item_icon);
                item.mTvSwTitle = (TextView) convertView.findViewById(R.id.tv_setting_item_title);
                item.mIvMore = (ImageView) convertView.findViewById(R.id.iv_setting_item_more);
                
                convertView.setTag(item);
            } else {
                item = (ViewHolder) convertView.getTag();
            }
            SettingItem settingItem = Items.get(position);
            item.mIvIcon.setBackgroundResource(settingItem.iconResId);
            item.mTvSwTitle.setText(settingItem.title);
            item.mIvIcon.setColorFilter(context.getResources().getColor(R.color.blue));
            item.mIvMore.setColorFilter(context.getResources().getColor(R.color.blue));
            return convertView;
        }
        
        private class ViewHolder {
            ImageView mIvIcon;
            TextView mTvTitle;
            ImageView mIvMore;
            TextView mTvSwTitle;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }

}
