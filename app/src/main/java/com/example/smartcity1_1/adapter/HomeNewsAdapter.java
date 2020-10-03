package com.example.smartcity1_1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcity1_1.R;
import com.example.smartcity1_1.bean.NewsList;
import com.example.smartcity1_1.net.VolleyImage;
import com.example.smartcity1_1.net.VolleyLo;
import com.example.smartcity1_1.net.VolleyLoImage;
import com.example.smartcity1_1.net.VolleyTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 8:39
 */
public class HomeNewsAdapter extends ArrayAdapter<NewsList> {


    public HomeNewsAdapter(@NonNull Context context, @NonNull List<NewsList> objects) {
        super(context, 0, objects);
    }

    static class ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemCommit;
        private TextView itemDz;
        private TextView itemGkrs;
        private TextView itemTime;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item2, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemContext = convertView.findViewById(R.id.item_context);
            holder.itemCommit = convertView.findViewById(R.id.item_commit);
            holder.itemDz = convertView.findViewById(R.id.item_dz);
            holder.itemGkrs = convertView.findViewById(R.id.item_gkrs);
            holder.itemTime = convertView.findViewById(R.id.item_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsList newsList = getItem(position);
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(newsList.getImg())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.itemImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        holder.itemTitle.setText(newsList.getTitle());
        holder.itemContext.setText(newsList.getContent());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getCommitById")
                .setJsonObject("id",newsList.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        int all = jsonArray.length();
                        holder.itemCommit.setText("总评：" + all );

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

        holder.itemDz.setText("点赞：" + newsList.getPraiseCount() );
        holder.itemGkrs.setText("观看人数：" + newsList.getAudienceCount() );
        holder.itemTime.setText("发布日期：" + newsList.getPublicTime());
        return convertView;
    }

    private void initView() {


    }
}
