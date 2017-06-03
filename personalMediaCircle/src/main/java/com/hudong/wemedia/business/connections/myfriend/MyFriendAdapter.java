package com.hudong.wemedia.business.connections.myfriend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudong.wemedia.R;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.utils.StringUtils;
import com.hudong.wemedia.basiccomponent.widgets.CircleImageView;
import com.hudong.wemedia.basiccomponent.widgets.SwipeItemLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dyj on 2017/3/13.
 * 人脉中我的好友列表的适配器
 */

public class MyFriendAdapter extends RecyclerView.Adapter<MyFriendAdapter.ContactMyFriendViewHolder> {

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
    /**
     * 当前处于打开状态的item
     */
    private List<SwipeItemLayout> mOpenedSil = new ArrayList<>();

    private Context mContext;

    /**
     * 记录选中好友要发起群聊
     */
    private List<Integer> isCheck = new ArrayList<>();

    public MyFriendAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 条目点击接口
     */
    private OnContactItemClickListener onContactItemClickListener;

    /**
     * 更新好友集合
     *
     * @param connection 好友集合
     */
    public void updateContacts(List<Connections> connection) {
        this.connections.clear();
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
        Pattern pattern = Pattern.compile("[0-9]*");
        //先遍历将星标好友取出来
        for (int i = 0; i < connection.size(); i++) {
            Connections connect = connection.get(i);
            if ("1".equals(connect.getSpecial())) {
                //如果是星标好友的话,先添加进集合
                this.connections.add(connect);
                connection.remove(connect);
                i--;
            }
        }
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
            if (!"1".equals(conect.getSpecial())) {
                //如果不是星标好友的话
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
            } else {
                //星标好友
                if (!sections.contains("★")) {
                    sections.add("★");
                    //记录首字母的位置
                    sparseIntArray.put(sections.size() - 1, i);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public ContactMyFriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_my_friend, parent, false);
        ContactMyFriendViewHolder mHolder = new ContactMyFriendViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ContactMyFriendViewHolder holder, final int position) {
        final Connections connection = this.connections.get(position);
        //好友名字
        String realname = connection.getRealname();
        //首字母
        String serionName = StringUtils.getFirstC(realname);
        //设置头部
        Pattern pattern = Pattern.compile("[0-9]*");
        if (!"1".equals(connection.getSpecial())) {
            //如果不是星标好友
            if (pattern.matcher(serionName).matches()) {
                //如果是数字
                holder.mItemContactMyFriendSerionName.setText("#");
            } else {
                holder.mItemContactMyFriendSerionName.setText(serionName);
            }
            holder.mItemContactMyFriendStar.setText("设置为星标朋友");
        } else {
            //星标好友
            holder.mItemContactMyFriendSerionName.setText("星标朋友");
            holder.mItemContactMyFriendStar.setText("取消特别关注");
        }
        //控制显示和隐藏头部
        if (!"1".equals(connection.getSpecial())) {
            //如果不是星标朋友
            if (position == 0 || !serionName.equalsIgnoreCase(StringUtils.getFirstC(connections.get(position - 1).getRealname()))) {
                //跟上一个好友的首字母不一样
                //这里还要判断数字
                if (position != 0 && pattern.matcher(serionName).matches() && pattern.matcher(StringUtils.getFirstC(connections.get(position - 1).getRealname())).matches()) {
                    holder.mItemContactMyFriendSerionName.setVisibility(View.GONE);
                } else {
                    holder.mItemContactMyFriendSerionName.setVisibility(View.VISIBLE);
                }
            } else {
                //跟上一个好友首字母一样,这里注意的是星标朋友
                if (serionName.equalsIgnoreCase(StringUtils.getFirstC(connections.get(position - 1).getRealname())) && "1".equals(connections.get(position - 1).getSpecial())) {
                    holder.mItemContactMyFriendSerionName.setVisibility(View.VISIBLE);
                } else {
                    holder.mItemContactMyFriendSerionName.setVisibility(View.GONE);
                }
            }
        } else {
            //星标朋友
            holder.mItemContactMyFriendSerionName.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        }
        //Name
        holder.mItemContactMyFriendName.setText(realname);
        //头像 TODO 头像加载
//        ImageLoaderUtil.setImageUrl(connection.getHeadimg(), holder.mItemContactMyFriendIcon);
        //公司
        if (!TextUtils.isEmpty(connection.getCompany())) {
            holder.mItemContactMyFriendCompany.setText(connection.getCompany());
        } else {
            holder.mItemContactMyFriendCompany.setText("暂未设置公司信息");
        }
        //行业
        if (!TextUtils.isEmpty(connection.getIndustry())) {
            holder.mItemContactMyFriendIndustry.setText(connection.getNowcityc() + " | " + connection.getIndustry());
        } else {
            holder.mItemContactMyFriendIndustry.setText(connection.getNowcityc());
        }
        //职位
        String job = connection.getJob();
        if (job != null && job.length() > 0) {
            holder.mItemContactMyFriendJob.setText(connection.getJob());
            holder.mItemContactMyFriendJob.setVisibility(View.VISIBLE);
        } else {
            holder.mItemContactMyFriendJob.setVisibility(View.GONE);
        }
        //条目之后点击事件
        holder.mItemContactMyFriendStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                if (onContactItemClickListener != null) {
                    if ("1".equals(connection.getSpecial())) {
                        Log.d("TAG", "onClick: " + "取消星标朋友");
                        onContactItemClickListener.onStarClick(connection.getId(), false);
                    } else {
                        Log.d("TAG", "onClick: " + "设置为星标朋友");
                        onContactItemClickListener.onStarClick(connection.getId(), true);
                    }
                }
                //关闭侧滑条目
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        //条目SwipeItem侧滑监听,添加进集合
        holder.mItemContactMyFriendSwipe.setDelegate(new SwipeItemLayout.SwipeItemLayoutDelegate() {
            @Override
            public void onSwipeItemLayoutOpened(SwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onSwipeItemLayoutClosed(SwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onSwipeItemLayoutStartOpen(SwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        //设置条目点击事件
        holder.mItemContactMyFriendItemRelativeeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: " + "条目被点击了");
                if (mOpenedSil.size() == 0) {
                    if (!isShowCheckBox) {
                        //如果CheckBox没有打开
                        if (onContactItemClickListener != null) {

                            onContactItemClickListener.onItemClick(connection.getId(), connection);
                        }
                    }
                } else {
                    closeOpenedSwipeItemLayoutWithAnim();
                }
            }
        });
        holder.mItemContactMyFriendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onContactItemClickListener != null) {
                    onContactItemClickListener.onItemClick(connection.getId(), connection);
                }
            }
        });
        //判断CheckBox是否打开
        if (isShowCheckBox) {
            holder.mItemContactMyFriendCb.setVisibility(View.VISIBLE);
        } else {
            holder.mItemContactMyFriendCb.setVisibility(View.GONE);
        }
        //CheckBox的点击事件
        holder.mItemContactMyFriendCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //选中状态
                    isCheck.add(position);
                } else {
                    //取消选中
                    if (isCheck.contains(position)) {
                        isCheck.remove(new Integer(position));
                    }
                }
            }
        });
        //防止复用出现的问题
        if (isCheck.contains(position)) {
            holder.mItemContactMyFriendCb.setChecked(true);
        } else {
            holder.mItemContactMyFriendCb.setChecked(false);
        }
    }

    /**
     * 获取要创建群聊的好友集合
     *
     * @return
     */
    public List<Connections> getDiscussGroup() {
        List<Connections> connectionList = new ArrayList<>();
//        connections
        for (int i = 0; i < isCheck.size(); i++) {
            connectionList.add(connections.get(isCheck.get(i)));
        }
        return connectionList;
    }

    private boolean isShowCheckBox = false;

    /**
     * 判断是否显示CheckBox
     *
     * @param b
     */
    public void isShowCheckBox(boolean b) {
        isShowCheckBox = b;
        if (!isShowCheckBox) {
            //隐藏checkBox的时候清空集合
            isCheck.clear();
        }
    }

    /**
     * 条目点击接口
     */
    public interface OnContactItemClickListener {
        void onItemClick(String conectionID, Connections connections);

        void onStarClick(String connectionsID, boolean isStar);
    }

    public void setOnContactItemClickListener(OnContactItemClickListener onContactItemClickListener) {
        this.onContactItemClickListener = onContactItemClickListener;
    }

    /**
     * 关闭正在打开的条目
     */
    public void closeOpenedSwipeItemLayoutWithAnim() {
        //遍历所有的条目
        for (SwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    //返回当前所有联系人的字母
    public ArrayList<String> getSections() {

        return sections;
    }

    //根据字母在容器中的位置获取对应的第一条的position
    public int getPositionForSection(int sectionIndex) {
        return sparseIntArray.get(sectionIndex);
    }

    @Override
    public int getItemCount() {
        return connections.size();
    }

    class ContactMyFriendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_contact_my_friend_icon)
        CircleImageView mItemContactMyFriendIcon;
        @BindView(R.id.item_contact_my_friend_name)
        TextView mItemContactMyFriendName;
        @BindView(R.id.item_contact_my_friend_job)
        TextView mItemContactMyFriendJob;
        @BindView(R.id.item_contact_my_friend_company)
        TextView mItemContactMyFriendCompany;
        @BindView(R.id.item_contact_my_friend_industry)
        TextView mItemContactMyFriendIndustry;
        @BindView(R.id.item_contact_my_friend_serion_name)
        TextView mItemContactMyFriendSerionName;
        @BindView(R.id.item_contact_my_friend_star)
        TextView mItemContactMyFriendStar;
        @BindView(R.id.swipe_item)
        SwipeItemLayout mItemContactMyFriendSwipe;
        @BindView(R.id.activity_contact_my_friend_cb)
        CheckBox mItemContactMyFriendCb;
        @BindView(R.id.act_contact_my_friend_relativrlayout)
        RelativeLayout mItemContactMyFriendItemRelativeeLayout;

        public ContactMyFriendViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
