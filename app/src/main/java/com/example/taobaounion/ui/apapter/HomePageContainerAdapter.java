package com.example.taobaounion.ui.apapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taobaounion.R;
import com.example.taobaounion.model.domain.HomePageContent;
import com.example.taobaounion.utils.LogUtils;
import com.example.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.ui.apapter
 * @ClassName: HomePageContainerAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-27 19:23
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-27 19:23
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomePageContainerAdapter extends RecyclerView.Adapter<HomePageContainerAdapter.InnerHolder> {


    List<HomePageContent.DataBean> data = new ArrayList<>();
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_page_container,parent,false);

        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        HomePageContent.DataBean dataBean = data.get(position);
        // 设置数据
        holder.setData(dataBean);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<HomePageContent.DataBean> contents) {
        data.clear();
        data.addAll(contents);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.goods_cover)
        public ImageView cover;


        @BindView(R.id.goods_title)
        public TextView title;


        @BindView(R.id.goods_off_price)
        public TextView off_price;

        @BindView(R.id.goods_after_off_price)
        public TextView tv_final_price;

        @BindView(R.id.goods_origin_price)
        public TextView tv_origin_price;

        @BindView(R.id.goods_sell_count)
        public TextView tv_sell_count;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(HomePageContent.DataBean dataBean) {

            Context context = itemView.getContext();

            title.setText(dataBean.getTitle());
            LogUtils.d(this,"url--->"+dataBean.getPict_url());

            long coupon_amount = dataBean.getCoupon_amount();
            //券后价
            String zk_final_price = dataBean.getZk_final_price();

            // 设置图片显示
            Glide.with(itemView.getContext()).load(UrlUtils.getCoverPath(dataBean.getPict_url())).into(cover);
            // 设置省多少钱
            off_price.setText(String.format(context.getString(R.string.text_goos_off_price),coupon_amount));

            // 最后的间隔
            float result = Float.parseFloat(zk_final_price) - coupon_amount;
            tv_final_price.setText(String.format("%.2f",result));

            // 原始的价格
            tv_origin_price.setText(String.format("￥%.2f",Float.parseFloat(zk_final_price) ));

            // 已经购买的数目
            tv_sell_count.setText(String.format("%d人已购买",dataBean.getVolume()));

        }
    }
}
