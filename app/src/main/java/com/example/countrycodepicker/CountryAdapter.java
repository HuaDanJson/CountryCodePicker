package com.example.countrycodepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryAdapterViewHolder> {

    private Context mContext;
    private List<Country> mCountryList;
    private OnItemClickedListener mListener;

    public CountryAdapter(Context context, List<Country> countryList) {
        this.mContext = context;
        this.mCountryList = countryList;
    }

    public void setOnItemClicked(OnItemClickedListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickedListener {
        void onItemClicked(Country country, int position);
    }

    @NonNull
    @Override
    public CountryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview, parent, false);
        return new CountryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapterViewHolder holder, int position) {
        Country country = mCountryList.get(position);
        if (country == null) {
            return;
        }
        if (country.isFirst()) {
            holder.mGroupView.setText(country.getInitial());
            holder.mGroupView.setVisibility(View.VISIBLE);
        } else {
            holder.mGroupView.setVisibility(View.GONE);
        }
        holder.mFlagView.setImageDrawable(ContextCompat.getDrawable(mContext, mContext.getResources().getIdentifier(country.getIcon(), "drawable", "com.example.countrycodepicker")));
        holder.mCountryNameView.setText(country.getEn());
        holder.mCountryCodeView.setText("+" + country.getCode());
        holder.mCountryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.mCountryItem.setSelected(true);
//                Toast.makeText(mContext, "Select:" + country, Toast.LENGTH_SHORT).show();

                if (mListener != null) {
                    mListener.onItemClicked(country, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public class CountryAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView mGroupView;
        ImageView mFlagView;
        TextView mCountryNameView;
        TextView mCountryCodeView;
        LinearLayout mCountryItem;

        public CountryAdapterViewHolder(View itemView) {
            super(itemView);
            mGroupView = itemView.findViewById(R.id.tv_group);
            mFlagView = itemView.findViewById(R.id.iv_flag);
            mCountryNameView = itemView.findViewById(R.id.tv_country_name);
            mCountryCodeView = itemView.findViewById(R.id.tv_country_code);
            mCountryItem = itemView.findViewById(R.id.ll_country_item);
        }
    }
}
