package com.example.smartcity1_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.activity.TrafficActivity;
import com.example.smartcity1_1.bean.ServiceHome;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLoImage;
import com.example.smartcity1_1.util.OnUpDate;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/1 at 20:35
 */
public class HomeServiceAdapter extends ArrayAdapter<ServiceHome> {

    public HomeServiceAdapter(@NonNull Context context, @NonNull List<ServiceHome> objects) {
        super(context, 0, objects);
    }

    private OnUpDate onUpDate;

    public void setOnUpDate(OnUpDate onUpDate) {
        this.onUpDate = onUpDate;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
            holder = new ViewHolder();
            holder.itemPhoto = convertView.findViewById(R.id.item_photo);
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemLayout = convertView.findViewById(R.id.item_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 9) {
            holder.itemName.setText("更多服务");
            holder.itemPhoto.setImageResource(R.mipmap.more_service);
            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUpDate.upDate("更多服务");
                }
            });
        } else {
            ServiceHome serviceHome = getItem(position);
            holder.itemName.setText(serviceHome.getName());
            VolleyImage volleyImage = new VolleyImage();
            volleyImage.setUrl(serviceHome.getImage())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.itemPhoto.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            if (serviceHome.getName().equals("地铁查询")) {
                holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContext().startActivity(new Intent(getContext(), TrafficActivity.class));
                    }
                });
            } else if (serviceHome.getName().equals("新闻中心")) {
                holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onUpDate.upDate("新闻中心");
                    }
                });
            }
        }
        return convertView;
    }

    static class ViewHolder {

        private ImageView itemPhoto;
        private TextView itemName;
        private LinearLayout itemLayout;
    }

    private void initView() {
    }
}
