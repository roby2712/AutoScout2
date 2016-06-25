package autoscout.it.autoscout2.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
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

import autoscout.it.autoscout2.R;
import autoscout.it.autoscout2.model.ImageModel;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roberto on 12/06/2016.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<ImageModel> data = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm, ArrayList<ImageModel> data) {

        super(fm);

        this.data = data;

    }

    @Override
    public Fragment getItem(int position) {

        return PlaceholderFragment.newInstance(position, data.get(position).getName(), data.get(position).getUrl());

    }

    @Override
    public int getCount() {

        return data.size();

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return data.get(position).getName();

    }

    public static class PlaceholderFragment extends Fragment {

        @Bind(R.id.imageViewFullscreenCar)
        ImageView imageViewFullscreenCar;

        ProgressBar progressBar;

        String name;

        String url;

        int pos;

        private static final String ARG_SECTION_NUMBER = "section_number";

        private static final String ARG_IMG_TITLE = "image_title";

        private static final String ARG_IMG_URL = "image_url";

        @Override
        public void setArguments(Bundle args) {

            super.setArguments(args);

            this.pos = args.getInt(ARG_SECTION_NUMBER);

            this.name = args.getString(ARG_IMG_TITLE);

            this.url = args.getString(ARG_IMG_URL);

        }

        public static PlaceholderFragment newInstance(int sectionNumber, String name, String url) {

            PlaceholderFragment fragment = new PlaceholderFragment();

            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);

            args.putString(ARG_IMG_TITLE, name);

            args.putString(ARG_IMG_URL, url);

            fragment.setArguments(args);

            return fragment;

        }

        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_fullscreen_image, container, false);

            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

            ButterKnife.bind(this, view);

            Glide.with(this).load(url).thumbnail(0.1f).into(imageViewFullscreenCar);

            progressBar.setVisibility(View.VISIBLE);

            Glide.with(this)
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
                    }).fitCenter().into(imageViewFullscreenCar);

            return view;

        }

    }

}