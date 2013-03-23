/*
 * Copyright (C) 2012 Artur Termenji
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.atermenji.android.iconictextview.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.atermenji.android.iconictextview.IconicFontDrawable;
import com.atermenji.android.iconictextview.IconicTextView;
import com.atermenji.android.iconictextview.icon.EntypoIcon;
import com.atermenji.android.iconictextview.icon.EntypoSocialIcon;
import com.atermenji.android.iconictextview.icon.FontAwesomeIcon;
import com.atermenji.android.iconictextview.icon.Icon;
import com.atermenji.android.iconictextview.icon.IconicIcon;

public class TypefaceSampleActivity extends ListActivity {
    
    private static final String EXTRA_ICON_TYPE = "extra_icon_type";
    
    public static final int ICON_TYPE_ENTYPO = 1001;
    public static final int ICON_TYPE_ENTYPO_SOCIAL = 1002;
    public static final int ICON_TYPE_FONT_AWESOME = 1003;
    public static final int ICON_TYPE_ICONIC = 1004;
    
    public static Intent createIntent(final Context context, final int iconType) {
        Intent intent = new Intent(context, TypefaceSampleActivity.class);
        intent.putExtra(EXTRA_ICON_TYPE, iconType);
        return intent;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int iconType = getIntent().getExtras().getInt(EXTRA_ICON_TYPE);
        initIconsList(iconType);
    }

    private void initIconsList(int iconType) {
        List<Icon> values = new ArrayList<Icon>();
        Icon[] valuesArray = null;
        
        switch (iconType) {
            case ICON_TYPE_ENTYPO: {
                valuesArray = EntypoIcon.values();
                setTitle("Entypo");
                break;
            }
            case ICON_TYPE_ENTYPO_SOCIAL: {
                valuesArray = EntypoSocialIcon.values();
                setTitle("Entypo-Social");
                break;
            }
            case ICON_TYPE_FONT_AWESOME: {
                valuesArray = FontAwesomeIcon.values();
                setTitle("Font Awesome");
                break;
            }
            case ICON_TYPE_ICONIC: {
                valuesArray = IconicIcon.values();
                setTitle("Iconic");
                break;
            }
        }
        
        values.addAll(Arrays.asList(valuesArray));
        
        SampleIconsAdapter adapter = new SampleIconsAdapter(this, values);
        getListView().setAdapter(adapter);
    }
    
    private class SampleIconsAdapter extends ArrayAdapter<Icon> {

        private LayoutInflater mInflater;
        private List<Icon> mIcons;
        
        public SampleIconsAdapter(Context context, List<Icon> icons) {
            super(context, R.layout.list_item_iconic, icons);
            mIcons = icons;
            mInflater = LayoutInflater.from(context);
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Icon icon = mIcons.get(position);
            ViewHolder holder = null;
            
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_iconic, null);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.icon = convertView.findViewById(R.id.view_icon);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            
            holder.title.setText(icon.toString());

            IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(getContext());
            iconicFontDrawable.setIcon(icon);
            iconicFontDrawable.setIconColor(Utils.randomColor());
            holder.icon.setBackground(iconicFontDrawable);

            return convertView;
        }
        
        private class ViewHolder {
            
            public TextView title;
            public View icon;
            
        }
    }
}
