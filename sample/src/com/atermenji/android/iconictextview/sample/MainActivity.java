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
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSamplesList();
    }

    private void initSamplesList() {
        List<SampleItem> sampleItems = new ArrayList<SampleItem>();

        sampleItems.add(new SampleItem("Simple Sample", new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleSampleActivity.class));
            }
        }));
        sampleItems.add(new SampleItem("Entypo icons", new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TypefaceSampleActivity.createIntent(MainActivity.this,
                        TypefaceSampleActivity.ICON_TYPE_ENTYPO));
            }
        }));
        sampleItems.add(new SampleItem("Entypo Social icons", new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TypefaceSampleActivity.createIntent(MainActivity.this,
                        TypefaceSampleActivity.ICON_TYPE_ENTYPO_SOCIAL));
            }
        }));
        sampleItems.add(new SampleItem("Font Awesome icons", new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TypefaceSampleActivity.createIntent(MainActivity.this,
                        TypefaceSampleActivity.ICON_TYPE_FONT_AWESOME));
            }
        }));

        SamplesAdapter adapter = new SamplesAdapter(this, sampleItems);
        getListView().setAdapter(adapter);
    }

    private class SampleItem {

        public String title;
        public OnClickListener onClickListener;

        public SampleItem(String title, OnClickListener onClickListener) {
            this.title = title;
            this.onClickListener = onClickListener;
        }
    }

    private class SamplesAdapter extends ArrayAdapter<SampleItem> {

        private LayoutInflater mInflater;
        private List<SampleItem> mSamples;

        public SamplesAdapter(Context context, List<SampleItem> samples) {
            super(context, R.id.tv_title, samples);
            mSamples = samples;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final SampleItem sampleItem = mSamples.get(position);

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_simple, null);
            }

            ((TextView) convertView.findViewById(R.id.tv_title)).setText(sampleItem.title);
            convertView.setOnClickListener(sampleItem.onClickListener);

            return convertView;
        }
    }
}
