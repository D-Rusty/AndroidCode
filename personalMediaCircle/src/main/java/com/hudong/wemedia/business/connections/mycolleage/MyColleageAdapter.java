package com.hudong.wemedia.business.connections.mycolleage;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudong.wemedia.R;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.utils.StringUtils;
import com.hudong.wemedia.basiccomponent.widgets.CircleImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dyj on 2017/3/16.
 * 适配器
 */

public class MyColleageAdapter extends RecyclerView.Adapter<MyColleageAdapter.ContactMyColleageViewHolder> {


    /**
     * 存放好友Name首字母的集合
     */
    private ArrayList<String> sections = new ArrayList<>();
    /**
     * 记录字母在sections的Position
     */
    private SparseIntArray sparseIntArray = new SparseIntArray();
    /**
     * 好友的集合
     */
    private ArrayList<Connections> connections = new ArrayList<>();

    public void updateContacts(List<Connections> connection) {
        connections.clear();
        //按照首字母进行排序,对集合进行排序
        Collections.sort(connection, new Comparator<Connections>() {
            @Override
            public int compare(Connections lhs, Connections rhs) {
                //根据名字进行排序
                String o1C = StringUtils.getFirstC(lhs.getRealname());
                String o2C = StringUtils.getFirstC(rhs.getRealname());
                return o1C.compareTo(o2C);
            }
        });
        Pattern pattern = Pattern.compile("[0-9]+");
        //将不是数字ID的好友取出来
        for (int i = 0; i < connection.size(); i++) {
            Connections connect = connection.get(i);
            if (!pattern.matcher(connect.getRealname()).matches()) {
                //不是数字开头ID
                this.connections.add(connect);
                connection.remove(connect);
                i--;
            }
        }
        this.connections.addAll(connection);
        sections.clear();
        sparseIntArray.clear();
        //遍历联系人 添加首字母
        for (int i = 0; i < this.connections.size(); i++) {
            Connections conect = this.connections.get(i);
            String c = StringUtils.getFirstC(conect.getRealname());
            if (!pattern.matcher(c).matches()) {
                //不是数字的话
                if (!sections.contains(c)) {
                    sections.add(c);
                    //记录首字母的位置
                    sparseIntArray.put(sections.size() - 1, i);
                }
            } else {
                //是数字
                if (!sections.contains("#")) {
                    sections.add("#");
                    sparseIntArray.put(sections.size() - 1, i);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ContactMyColleageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_my_colleage, parent, false);
        ContactMyColleageViewHolder mHolder = new ContactMyColleageViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ContactMyColleageViewHolder holder, int position) {
        final Connections connection = this.connections.get(position);
        //好友名字
        String realname = connection.getRealname();
        //首字母
        String serionName = StringUtils.getFirstC(realname);
        //设置头部
        Pattern pattern = Pattern.compile("[0-9]*");
        //如果不是星标好友
        if (pattern.matcher(serionName).matches()) {
            //如果是数字
            holder.mItemContactMyColleageSerionName.setText("#");
        } else {
            holder.mItemContactMyColleageSerionName.setText(serionName);
        }
        //如果不是星标朋友
        if (position == 0 || !serionName.equalsIgnoreCase(StringUtils.getFirstC(connections.get(position - 1).getRealname()))) {
            //这里还要判断数字
            if (position != 0 && pattern.matcher(serionName).matches() && pattern.matcher(StringUtils.getFirstC(connections.get(position - 1).getRealname())).matches()) {
                holder.mItemContactMyColleageSerionName.setVisibility(View.GONE);
            } else {
                holder.mItemContactMyColleageSerionName.setVisibility(View.VISIBLE);
            }
        } else {
            holder.mItemContactMyColleageSerionName.setVisibility(View.GONE);

        }
        //Name
        holder.mItemContactMyColleageName.setText(realname);
        //头像 TODO
//        ImageLoaderUtil.setImageUrl(connection.getHeadimg(), holder.mItemContactMyColleageIcon);
        //公司
        if (!TextUtils.isEmpty(connection.getCompany())) {
            holder.mItemContactMyColleageCompany.setText(connection.getCompany());
        } else {
            holder.mItemContactMyColleageCompany.setText("暂未设置公司信息");
        }
        //行业
        if (!TextUtils.isEmpty(connection.getIndustry())) {
            holder.mItemContactMyColleageIndustry.setText(connection.getNowcityc() + " | " + connection.getIndustry());
        } else {
            holder.mItemContactMyColleageIndustry.setText(connection.getNowcityc());
        }
        //职位
        String job = connection.getJob();
        if (job != null && job.length() > 0) {
            holder.mItemContactMyColleageJob.setText(connection.getJob());
            holder.mItemContactMyColleageJob.setVisibility(View.VISIBLE);
        } else {
            holder.mItemContactMyColleageJob.setVisibility(View.GONE);
        }
        //设置条目点击事件
        holder.mItemContactSwipeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " + "条目被点击了");
                if (mContactColleageListener != null) {
                    mContactColleageListener.onItemClick(connection.getId());
                }
            }
        });
        holder.mItemContactMyColleageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContactColleageListener != null) {
                    mContactColleageListener.onItemClick(connection.getId());
                }
            }
        });
    }

    private ContactColleageListener mContactColleageListener;


    public void setOnClickListener(ContactColleageListener contactColleageListener) {
        this.mContactColleageListener = contactColleageListener;
    }

    public ArrayList<String> getSections() {
        return sections;
    }

    //根据字母在容器中的位置获取对应的第一条的position
    public int getPositionForSection(int sectionIndex) {
        return sparseIntArray.get(sectionIndex);
    }

    public interface ContactColleageListener {
        void onItemClick(String contactID);
    }

    @Override
    public int getItemCount() {
        return connections.size();
    }

    public class ContactMyColleageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_contact_my_colleage_serion_name)
        TextView mItemContactMyColleageSerionName;
        @BindView(R.id.item_contact_my_colleage_icon)
        CircleImageView mItemContactMyColleageIcon;
        @BindView(R.id.item_contact_my_colleage_name)
        TextView mItemContactMyColleageName;
        @BindView(R.id.item_contact_my_colleage_job)
        TextView mItemContactMyColleageJob;
        @BindView(R.id.item_contact_my_colleage_company)
        TextView mItemContactMyColleageCompany;
        @BindView(R.id.item_contact_my_colleage_industry)
        TextView mItemContactMyColleageIndustry;
        @BindView(R.id.act_contact_my_colleage_relativrlayout)
        RelativeLayout mActContactMyColleageRelativrlayout;
        @BindView(R.id.item_contact_swipe_item)
        RelativeLayout mItemContactSwipeItem;

        public ContactMyColleageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
