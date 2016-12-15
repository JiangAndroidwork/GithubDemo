package com.laojiang.githubdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Jiang on 2016/12/13.
 */

public class GridViewAdapter  extends BaseAdapter{
    private Context context;
    private String[] str;

    public GridViewAdapter(Context context, String[] str) {
        this.context = context;
        this.str = str;
    }

    @Override
    public int getCount() {
        if (str==null)
        return 0;
        return str.length;
    }

    @Override
    public Object getItem(int i) {
        return str[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View views, ViewGroup viewGroup) {
        View view=null;
        view = View.inflate(context,R.layout.item_buttons,null);
        TextView tvBts = (TextView) view.findViewById(R.id.tv_bts);
        tvBts.setText(str[i]);

        return view;
    }
}
