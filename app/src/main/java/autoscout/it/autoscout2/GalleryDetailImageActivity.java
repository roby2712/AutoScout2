package autoscout.it.autoscout2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

import autoscout.it.autoscout2.adapter.SectionsPagerAdapter;
import autoscout.it.autoscout2.model.ImageModel;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryDetailImageActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    public ArrayList<ImageModel> data = new ArrayList<>();

    int pos;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_gallery_image);

        ButterKnife.bind(this);

        data = (ArrayList<ImageModel>) getIntent().getSerializableExtra("data");

        pos = getIntent().getIntExtra("pos", 0);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), data);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //viewPager.setPageTransformer(true, new DepthPageTransformer());

        viewPager.setAdapter(mSectionsPagerAdapter);

        viewPager.setCurrentItem(pos);

    }

}