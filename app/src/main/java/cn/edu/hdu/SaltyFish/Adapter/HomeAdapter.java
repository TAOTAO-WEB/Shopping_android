package cn.edu.hdu.SaltyFish.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

import cn.edu.hdu.SaltyFish.HomeFragment;
import cn.edu.hdu.SaltyFish.R;
import cn.edu.hdu.SaltyFish.ShoppingActivity;
import cn.edu.hdu.SaltyFish.base.HomeData;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //所有要展示的数据源object集合
    private List<Object> objects;
    private Context context;

    //加入标记作为区分
    private static final int BANNER = 1;
    private static final int FOURFOUNCTION = 2;
    private static final int BOTTOM_FAVORITE = 3;

    public HomeAdapter(Context context, List<Object> objects) {
        this.objects = objects;
        this.context = context;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 基于项不同的类型来获得不同的viewholder,关联对应的布局
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case BANNER:
                viewHolder = new BannerHolder(inflate(parent, R.layout.test_item_banner));
                break;
            case FOURFOUNCTION:
                viewHolder = new FocucsHolder(inflate(parent, R.layout.test_item_focus));
                break;
            case BOTTOM_FAVORITE:
                viewHolder = new BottomHolder(inflate(parent, R.layout.test_item_bottom_item));
                break;
        }
        //viewHolder.setIsRecyclable(false);
        return viewHolder;
    }

    /**
     * 关联布局
     */
    private View inflate(ViewGroup parent, int resId) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Object object = objects.get(position);
        switch (holder.getItemViewType()) {
            case BANNER://轮播图
                setTypeItemBanner((BannerHolder) holder, (HomeData.TypeBanner) object);
                break;
            case FOURFOUNCTION://四格
                setTypeItemFocucs((FocucsHolder) holder, (HomeData.TypeFocus) object);
                break;
            case BOTTOM_FAVORITE://底部商品列表
                setTypeItemBottom((BottomHolder) holder, (HomeData.TypeBottom) object);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object object = objects.get(position);
        //判断position是否属于该实例，加载不同的布局
        if (object instanceof HomeData.TypeBanner) {
            return BANNER;
        }
        else if (object instanceof HomeData.TypeFocus) {
            return FOURFOUNCTION;
        }
        else if (object instanceof HomeData.TypeBottom) {
            return BOTTOM_FAVORITE;
        }
        return position;
    }



    @Override
    public int getItemCount() {
        if (objects == null){
            return 0;
        }
        else {
            return objects.size();
        }
    }

    /**
     * 设置轮播
     *
     * @param holder BannerHolder
     * @param banner 实体
     */
    private void setTypeItemBanner(BannerHolder holder, HomeData.TypeBanner banner) {

        //设置banner样式
        holder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        holder.banner.setImageLoader(new HomeFragment.GlideImageLoader());
        //设置图片集合
        holder.banner.setImages(banner.getRsid());
        //设置指示器位置
        holder.banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置轮播时间
        holder.banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        holder.banner.start();
    }

    class BannerHolder extends RecyclerView.ViewHolder {

        private Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }


    /**
     * 设置四格
     */
    private void setTypeItemFocucs(FocucsHolder holder, HomeData.TypeFocus focus) {


        Integer picFourth = focus.getPicFirst();
        Integer picFifth = focus.getPicSecond();
        Integer picSixth = focus.getPicThird();
        Integer picSeventh = focus.getPicForth();


        holder.foucesPicFourth.setImageResource(picFourth);
        holder.foucesPicFifth.setImageResource(picFifth);
        holder.foucesPicSixth.setImageResource(picSixth);
        holder.foucesPicSeventh.setImageResource(picSeventh);

        String first = focus.getTitleFirst();
        holder.foucesTitleFirst.setText(first);
        String second = focus.getTitleSecond();
        holder.foucesTitleSecond.setText(second);
        String third = focus.getTitleThird();
        holder.foucesTitleThird.setText(third);
        String fourth = focus.getTitleFourth();
        holder.foucesTitleFourth.setText(fourth);
        String fifith = focus.getTitleFifth();
        holder.foucesTitleFifth.setText(fifith);
        String sixth = focus.getTitleSixth();
        holder.foucesTitleSixth.setText(sixth);
        String seventh = focus.getTitleSeventh();
        holder.foucesTitleSeventh.setText(seventh);
        String eighth = focus.getTitleEighth();
        holder.foucesTitleEighth.setText(eighth);
    }

    class FocucsHolder extends RecyclerView.ViewHolder {


        ImageView foucesPicFourth = itemView.findViewById(R.id.fouces_pic_fourth);
        ImageView foucesPicFifth = itemView.findViewById(R.id.fouces_pic_fifth);
        ImageView foucesPicSixth = itemView.findViewById(R.id.fouces_pic_sixth);
        ImageView foucesPicSeventh = itemView.findViewById(R.id.fouces_pic_seventh);

        TextView foucesTitleFirst = itemView.findViewById(R.id.fouces_title_first);
        TextView foucesTitleSecond = itemView.findViewById(R.id.fouces_title_second);
        TextView foucesTitleThird = itemView.findViewById(R.id.fouces_title_third);
        TextView foucesTitleFourth = itemView.findViewById(R.id.fouces_title_fourth);
        TextView foucesTitleFifth = itemView.findViewById(R.id.fouces_title_fifth);
        TextView foucesTitleSixth = itemView.findViewById(R.id.fouces_title_sixth);
        TextView foucesTitleSeventh = itemView.findViewById(R.id.fouces_title_seventh);
        TextView foucesTitleEighth = itemView.findViewById(R.id.fouces_title_eighth);

        public FocucsHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * 底部商品；列表
     */
    private void setTypeItemBottom(BottomHolder holder, HomeData.TypeBottom bottom) {
        //图片
        Integer url = bottom.getUrl();
        holder.itemUrl.setImageResource(url);
        //品牌
        String title = bottom.getTitle();
        holder.itemTitle.setText(title);
        //内容
        String content = bottom.getContent();
        holder.itemContent.setText(content);
        //价格
        double price = bottom.getPrice();
        holder.itemPrice.setText("¥"+price);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShoppingActivity.class);
                intent.putExtra("image",url);
                intent.putExtra("firstshop",title);
                intent.putExtra("secshop",content);
                intent.putExtra("price",price);
                context.startActivity(intent);
            }
        });

    }

    class BottomHolder extends RecyclerView.ViewHolder {

        ImageView itemUrl = itemView.findViewById(R.id.item_url);
        TextView itemTitle = itemView.findViewById(R.id.item_title);
        TextView itemContent = itemView.findViewById(R.id.item_content);
        TextView itemPrice = itemView.findViewById(R.id.item_price);

        public BottomHolder(View itemView) {
            super(itemView);
        }
    }
}
