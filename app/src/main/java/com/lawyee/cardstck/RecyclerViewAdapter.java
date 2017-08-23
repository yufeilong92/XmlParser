package com.lawyee.cardstck;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: CardStck
 * @Package com.lawyee.cardstck
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/8/22 17:30
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String> mDatas;
    private Context mContext;
    private final LayoutInflater mInflater;

    private int FirstView = R.layout.firesview;
    private int LastView = R.layout.lastview;
    private int mModeTyep;

    public RecyclerViewAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new firstViewHolder(mInflater.inflate(FirstView, parent, false));
        } else if (viewType == 1) {
            return new lastViewHodler(mInflater.inflate(LastView, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((firstViewHolder) holder).tvFristView.setText(mDatas.get(position));
        } else if (position == 1) {
            ((lastViewHodler) holder).tvLastView.setText(mDatas.get(position));
        }
    }
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                mModeTyep = FirstView;
                break;
            case 1:
                mModeTyep = LastView;
                break;
            default:
                break;
        }
        return mModeTyep;
    }


    private class firstViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFristView;

        public firstViewHolder(View inflate) {
            super(inflate);
            tvFristView = inflate.findViewById(R.id.tv_firstView);
        }

    }

    private class lastViewHodler extends RecyclerView.ViewHolder {

        private final TextView tvLastView;

        public lastViewHodler(View inflate) {
            super(inflate);
            tvLastView = inflate.findViewById(R.id.tv_lastView);
        }
    }
}
