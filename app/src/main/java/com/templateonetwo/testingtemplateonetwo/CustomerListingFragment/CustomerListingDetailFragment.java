package com.templateonetwo.testingtemplateonetwo.CustomerListingFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.templateonetwo.testingtemplateonetwo.Models.ConsumerPostModel;
import com.templateonetwo.testingtemplateonetwo.R;

import org.w3c.dom.Text;

public class CustomerListingDetailFragment extends Fragment {
    EditText projectTitle,description;
    TextView date,time;
    Spinner category;
    ImageView mImageView;
    VideoView mVideoView;
    Button update;
    ConsumerPostModel mConsumerPostModel;
    ImageLoader imageLoader = ImageLoader.getInstance();
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mView==null)
        {
            mView=inflater.inflate(R.layout.fragment_current_listings_detail,container,false);
        }

        return mView;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        time = (TextView) view.findViewById(R.id.TimePostText);
        date = (TextView) view.findViewById(R.id.DatePostText);
        projectTitle = (EditText) view.findViewById(R.id.titleProjectName3);
        description = (EditText) view.findViewById(R.id.textViewDescription3);
      //  category = (Spinner) view.findViewById(R.id.CategoryPostText);
        mImageView = (ImageView)  view.findViewById(R.id.imageView3);
        mVideoView = (VideoView)  view.findViewById(R.id.videoView3);
        mConsumerPostModel=(ConsumerPostModel) getArguments().getSerializable("post");
        if(mConsumerPostModel!=null)
        {
            initalSetUp();
        }

    }

    void initalSetUp()
    {
        time.setText(mConsumerPostModel.getTime());
        date.setText(mConsumerPostModel.getDate());
        projectTitle.setText(mConsumerPostModel.getProjectTitle());
        description.setText(mConsumerPostModel.getDescription());
        String imageUri=mConsumerPostModel.getPhotoUrl();
        if(imageUri!=null) {
            imageLoader.displayImage(imageUri, mImageView);
        }
        else
        {
            imageUri=mConsumerPostModel.getVideoUrl();
            if(imageUri!=null)
            {
                Uri uri=Uri.parse(imageUri);
                mVideoView.setVideoURI(uri);
                mVideoView.start();

            }
        }

    }
}
