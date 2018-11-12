package com.example.jongha.meetingorganizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleListViewAdapter extends BaseAdapter {


    private ArrayList<ScheduleDTO> listViewItemList = new ArrayList<ScheduleDTO>();

    //ScheduleListViewAdapter 생성
    public ScheduleListViewAdapter() {
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "layout_schedule_listview.xml" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_schedule_listview, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTextView = (TextView) convertView.findViewById(R.id.scheduleListNameView) ;
        TextView timeTextView = (TextView) convertView.findViewById(R.id.scheduleListTimeView) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ScheduleDTO listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getActivityName());
        timeTextView.setText(listViewItem.getListViewTime());

        return convertView;
    }

    public void addItem(ScheduleDTO gotItem) {
        listViewItemList.add(gotItem);
    }

    public void removeItem(ScheduleDTO gotItem){
        listViewItemList.remove(gotItem);
    }
}
