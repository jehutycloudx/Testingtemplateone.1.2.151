package com.templateonetwo.testingtemplateonetwo.CustomerListingFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.templateonetwo.testingtemplateonetwo.Models.ConsumerPostModel;
import com.templateonetwo.testingtemplateonetwo.Models.UserModel;
import com.templateonetwo.testingtemplateonetwo.R;
import com.templateonetwo.testingtemplateonetwo.Utils.OnItemClickListener;
import com.templateonetwo.testingtemplateonetwo.Utils.SessionManager;

import java.util.ArrayList;

public class CustomerCurrentListingsFragment extends Fragment {
   View mView;
   RecyclerView mRecyclerView;
   TextView mTextView;
   CustomerCurrentListingsAdapter mCustomerCurrentListingsAdapter;
    ArrayList<ConsumerPostModel> mConsumerPostModelArrayList=new ArrayList<ConsumerPostModel>();
    SessionManager mSessionManager;
    UserModel mUserModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSessionManager=SessionManager.NewInstance(getActivity());
        mUserModel=mSessionManager.getProfileData(getActivity());
        if(mView==null)
        {
            mView=inflater.inflate(R.layout.fragment_customer_listings,container,false);
        }
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView=view.findViewById(R.id.recycler_view_customer_listings);
        mTextView=view.findViewById(R.id.error);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mCustomerCurrentListingsAdapter=new CustomerCurrentListingsAdapter(mConsumerPostModelArrayList, getActivity(),
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
      mRecyclerView.setAdapter(mCustomerCurrentListingsAdapter);
      getPosts();
    }


    void getPosts()
    {
        mConsumerPostModelArrayList.clear();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query query=databaseReference.child("posts").orderByChild(mUserModel.getUserKey());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot  childSnap : dataSnapshot.getChildren())
                {

                    for(DataSnapshot children:childSnap.getChildren())
                    {
                        ConsumerPostModel consumerPostModel=children.getValue(ConsumerPostModel.class);
                        mConsumerPostModelArrayList.add(consumerPostModel);
                    }

                }
                if(mConsumerPostModelArrayList.size()>0)
                {
                    mCustomerCurrentListingsAdapter.update(mConsumerPostModelArrayList);
                    mTextView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
                else
                {
                    mTextView.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
