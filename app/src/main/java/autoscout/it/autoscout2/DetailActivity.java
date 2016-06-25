package autoscout.it.autoscout2;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.transition.Slide;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;

import autoscout.it.autoscout2.adapter.CustomPagerAdapter;
import autoscout.it.autoscout2.model.ImageModel;
import autoscout.it.autoscout2.model.Post;
import autoscout.it.autoscout2.rest.RestAPI;
import autoscout.it.autoscout2.rest.RestAPIBuilder;
import autoscout.it.autoscout2.util.Utils;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE = "com.antonioleiva.materializeyourapp.extraImage";
    private static final String EXTRA_TITLE = "com.antonioleiva.materializeyourapp.extraTitle";

    @Bind(R.id.collapsingToolbarLayout)
    @Nullable
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.buttonEmail)
    Button buttonEmail;

    @Bind(R.id.buttonMappa)
    Button buttonMappa;

    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;

    @Bind(R.id.expandableLayout1)
    ExpandableRelativeLayout expandableLayout1;

    @Bind(R.id.expandableLayout2)
    ExpandableRelativeLayout expandableLayout2;

    @Bind(R.id.expandableLayout3)
    ExpandableRelativeLayout expandableLayout3;

    @Bind(R.id.textViewExpandableRelativeLayoutDettagli)
    TextView textViewExpandableRelativeLayoutDettagli;

    @Bind(R.id.textViewExpandableRelativeLayoutDescrizione)
    TextView textViewExpandableRelativeLayoutDescrizione;

    @Bind(R.id.textViewExpandableRelativeLayoutEquipaggiamento)
    TextView textViewExpandableRelativeLayoutEquipaggiamento;

    @Bind(R.id.expandableButton1)
    Button expandableButton1;

    @Bind(R.id.expandableButton2)
    Button expandableButton2;

    @Bind(R.id.expandableButton3)
    Button expandableButton3;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    /*
        @Bind(R.id.viewPager)
        InfiniteViewPager viewPager;
    */
    @Bind(R.id.underlinePageIndicator)
    @Nullable
    UnderlinePageIndicator underlinePageIndicator;

    @Bind(R.id.linearLayoutViewPager)
    @Nullable
    LinearLayout linearLayoutViewPager;

    private Subscription subscription;

    public static String IMGS[] = {
            "https://images.autouncle.com/it/car_images/84835de4-766b-4a29-924a-4d6ab0afb2d6.jpg",
            "https://images.autouncle.com/it/car_images/c2dadc48-ecba-4b76-8009-46b15b182268.jpg",
            "https://images.autouncle.com/it/car_images/7a6c84cd-7cc2-44c9-89cd-8319ebbf6025.jpg"
    };

    public static void navigate(AppCompatActivity activity, View transitionImage/*, ViewModel viewModel*/) {
        Intent intent = new Intent(activity, DetailActivity.class);
        //intent.putExtra(EXTRA_IMAGE, viewModel.getImage());
        //intent.putExtra(EXTRA_TITLE, viewModel.getText());

        //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        //ActivityCompat.startActivity(activity, intent, options.toBundle());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initActivityTransitions();
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        //ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        //supportPostponeEnterTransition();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("1 da 125");

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);

        if (collapsingToolbarLayout != null) {

            //collapsingToolbarLayout.setTitle("1 da 125");

            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        }

        RestAPI api = RestAPIBuilder.buildRetrofitService();

        /*
        NetworkRequest.performAsyncRequest(api.getPost(1), new Action1<Post>() {
            @Override
            public void call(Post post) {

                Log.i("CALL", post.body + " " + post.title + " " + post.id + " " + post.userId);

            }

        })
        */

        subscription = api.getPost(1).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                        Log.i("CALL", "dentro onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("CALL", e.getMessage() + " " + e.getCause() + " " + e.getStackTrace());

                    }

                    @Override
                    public void onNext(Post post) {

                        Button button = new Button(DetailActivity.this);

                        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        layoutParams.topMargin = 10;

                        button.setPadding(0, 0, Utils.dpAsPx(getApplicationContext(), 10), 0);

                        button.setTextColor(getResources().getColor(R.color.colorBlue));

                        button.setText(getString(R.string.string_dettagli));

                        button.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));

                        button.setTextSize(18);

                        button.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);

                        button.setAllCaps(false);

                        button.setBackground(getResources().getDrawable(android.R.color.transparent));

                        Drawable img = getResources().getDrawable(R.drawable.ic_arrow_down);

                        button.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);

                        button.setLayoutParams(layoutParams);

                        final ExpandableRelativeLayout
                                expandableRelativeLayout = new ExpandableRelativeLayout(DetailActivity.this);

                        layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        expandableRelativeLayout.setLayoutParams(layoutParams);

                        expandableRelativeLayout.setDuration(100);

                        expandableRelativeLayout.setInterpolator(new AccelerateInterpolator());

                        expandableRelativeLayout.setExpanded(false);

                        expandableRelativeLayout.setOrientation(ExpandableLayout.VERTICAL);

                        TextView textView = new TextView(DetailActivity.this);

                        textView.setText("Expandable Layout provides an easy way to create a view called header with an expandable view. Views are external layout to allow a maximum of customization.");

                        expandableRelativeLayout.addView(textView);

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                expandableRelativeLayout.toggle();

                            }

                        });

                        linearLayout.addView(button);

                        linearLayout.addView(expandableRelativeLayout);

                    }

                });

        if (linearLayoutViewPager != null) {

            Display display = getWindowManager().getDefaultDisplay();

            Point size = new Point();

            display.getSize(size);

            int width = size.x;

            int height = size.y;

            ViewGroup.LayoutParams layoutParams = linearLayoutViewPager.getLayoutParams();

            layoutParams.width = width / 2;

            linearLayoutViewPager.setLayoutParams(layoutParams);

        }

        ArrayList<ImageModel> data = new ArrayList<>();

        for (int i = 0; i < IMGS.length; i++) {

            ImageModel imageModel = new ImageModel();

            imageModel.setName("Image " + i);

            imageModel.setUrl(IMGS[i]);

            data.add(imageModel);

        }


        //PagerAdapter wrappedAdapter = new InfinitePagerAdapter(new CustomPagerAdapter(this, data));

        viewPager.setAdapter(new CustomPagerAdapter(this, data));

        underlinePageIndicator.setFades(false);

        underlinePageIndicator.setViewPager(viewPager);

        TextView title = (TextView) findViewById(R.id.title);
        //title.setText(itemTitle);

        Spannable spannable = new SpannableString("  " + getString(R.string.string_button_email));

        spannable.setSpan(new ImageSpan(getApplicationContext(), R.drawable.ic_mail,
                ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        buttonEmail.setText(spannable);

        spannable = new SpannableString("  " + getString(R.string.string_button_mappa));

        spannable.setSpan(new ImageSpan(getApplicationContext(), R.drawable.ic_pin,
                ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        buttonMappa.setText(spannable);

        expandableButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                expandableLayout2.toggle();

            }

        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailActivity.this, MailActivity.class);

                startActivity(intent);

            }

        });

    }

    public void expandableButton1(View view) {
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    /*
    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton) findViewById(R.id.fab), palette);
        supportStartPostponedEnterTransition();
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));

        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_activity_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

        //return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscription != null && !subscription.isUnsubscribed()) {

            subscription.unsubscribe();

        }

    }
}