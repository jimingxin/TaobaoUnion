package com.example.taobaounion.ui.apapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.taobaounion.model.domain.Categories;
import com.example.taobaounion.ui.fragment.HomePagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: TaobaoUnion
 * @Package: com.example.taobaounion.ui.apapter
 * @ClassName: HomePageAdapter
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2020-02-26 13:03
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020-02-26 13:03
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class HomePageAdapter extends FragmentPagerAdapter {

    private List<Categories.DataBean> data = new ArrayList<>();
    public HomePageAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTitle();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Categories.DataBean dataBean = data.get(position);
        HomePagerFragment homePagerFragment = HomePagerFragment.newInstance(dataBean);
        return homePagerFragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }


    public void setCategories(Categories categories) {
        this.data.clear();
        this.data.addAll(categories.getData());
        notifyDataSetChanged();
    }
}
