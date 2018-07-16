package com.templateonetwo.testingtemplateonetwo.CustomerListingFragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.templateonetwo.testingtemplateonetwo.Models.ConsumerPostModel;
import com.templateonetwo.testingtemplateonetwo.R;
import com.templateonetwo.testingtemplateonetwo.Utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CustomerCurrentListingsAdapter extends
        RecyclerView.Adapter<CustomerCurrentListingsAdapter.ViewHolder> {
    ArrayList<ConsumerPostModel> mConsumerPostModelArrayList;
    Context context;
    OnItemClickListener mOnItemClickListener;

    public CustomerCurrentListingsAdapter(ArrayList<ConsumerPostModel> consumerPostModelArrayList, Context context, OnItemClickListener onItemClickListener) {
        mConsumerPostModelArrayList = consumerPostModelArrayList;
        context = context;
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CustomerCurrentListingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        Activity activity = (Activity) this.context;


        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.adapter_current_listings, parent, false);

        CustomerCurrentListingsAdapter.ViewHolder viewHolder = new CustomerCurrentListingsAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerCurrentListingsAdapter.ViewHolder holder, int position) {
      holder.uniqueId.setText(mConsumerPostModelArrayList.get(position).getUserId());
      holder.messages.setText("messages");
      holder.date.setText(mConsumerPostModelArrayList.get(position).getDate());
      holder.projectTitle.setText(mConsumerPostModelArrayList.get(position).getProjectTitle());
      holder.uniqueId.setText(mConsumerPostModelArrayList.get(position).getUniqueId());
        ImageLoader imageLoader = ImageLoader.getInstance();
        String imageUri=mConsumerPostModelArrayList.get(position).getPhotoUrl();
        if(imageUri!=null) {
            imageLoader.displayImage(imageUri, holder.leftImage);
        }
        else
        {
            imageUri=mConsumerPostModelArrayList.get(position).getVideoUrl();
            if(imageUri!=null)
            imageLoader.displayImage(imageUri, holder.leftImage);
        }
     // holder.bids
      //holder.uniqueId.setText(mConsumerPostModelArrayList.get(position).get);

    }

    @Override
    public int getItemCount() {
        return mConsumerPostModelArrayList.size();
    }

    public void update(ArrayList<ConsumerPostModel> mConsumerPostModelArrayList)
    {
        this.mConsumerPostModelArrayList=mConsumerPostModelArrayList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView projectTitle,date,bids,messages,uniqueId;
        ImageView leftImage,edit;

       public ViewHolder(View itemView)
        {
            super(itemView);
            projectTitle=itemView.findViewById(R.id.project_title);
            date=itemView.findViewById(R.id.date);
            bids=itemView.findViewById(R.id.bids);
            messages=itemView.findViewById(R.id.messages);
            uniqueId=itemView.findViewById(R.id.unique_id);

            leftImage=itemView.findViewById(R.id.image);
            edit=itemView.findViewById(R.id.edit);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
