package autoscout.it.autoscout2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import autoscout.it.autoscout2.GalleryDetailImageActivity;
import autoscout.it.autoscout2.R;
import autoscout.it.autoscout2.model.ImageModel;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    private ArrayList<ImageModel> data;

    public CustomPagerAdapter(Context context) {

        mContext = context;

    }

    public CustomPagerAdapter(Context context, ArrayList<ImageModel> data) {

        this.mContext = context;

        this.data = data;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.fullscreen_image_pageadapter, collection, false);

        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, GalleryDetailImageActivity.class);

                intent.putExtra("data", data);

                intent.putExtra("pos", position);

                mContext.startActivity(intent);

            }

        });

        ImageView imageViewFullscreenFotoLocale = (ImageView) layout.findViewById(R.id.imageViewFullscreenCar);

        final ProgressBar progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);

        //Glide.with(mContext).load("http://images.cdn.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/Box11.jpg?itok=jyPEEttB").thumbnail(0.1f).into(imageViewFullscreenFotoLocale);

        progressBar.setVisibility(View.VISIBLE);

        String url = "";

        if (position == 0) {

            url = "https://images.autouncle.com/it/car_images/84835de4-766b-4a29-924a-4d6ab0afb2d6.jpg";

            //url = "https://img.olx.pt/images_olxpt/840960937_6_644x461_troco-vendo-porsche-boxster-s-32-01aceito-retoma-troca-_rev001.jpg";

            Glide.with(mContext)
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).fitCenter().into(imageViewFullscreenFotoLocale);

        } else if (position == 1) {

            url = "https://images.autouncle.com/it/car_images/c2dadc48-ecba-4b76-8009-46b15b182268.jpg";

            //url = "https://img.olx.pt/images_olxpt/840960937_8_644x461_troco-vendo-porsche-boxster-s-32-01-aceito-retoma-troca-_rev001.jpg";

            Glide.with(mContext)
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).fitCenter().into(imageViewFullscreenFotoLocale);


        } else {

            //url = "https://img.olx.pt/images_olxpt/840960937_5_644x461_troco-vendo-porsche-boxster-s-32-01aceito-retoma-troca-lisboa_rev001.jpg";

            url = "https://images.autouncle.com/it/car_images/7a6c84cd-7cc2-44c9-89cd-8319ebbf6025.jpg";

            Glide.with(mContext)
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).fitCenter().into(imageViewFullscreenFotoLocale);

        }

        collection.addView(layout);

        return layout;

    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}