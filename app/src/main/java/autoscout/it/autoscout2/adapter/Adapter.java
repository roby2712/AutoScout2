package autoscout.it.autoscout2.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import autoscout.it.autoscout2.R;
import autoscout.it.autoscout2.model.Car;
import autoscout.it.autoscout2.model.ImageModel;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roberto on 12/06/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.CarViewHolder> {

    private Context context;

    private FragmentManager fragmentManager;

    private ItemClickListener itemClickListener;

    private List<Car> listCar;

    private ArrayList<ImageModel> data;

    public List<Car> getListCar() {
        return listCar;
    }

    public void setListCar(List<Car> listCar) {
        this.listCar = listCar;
        notifyDataSetChanged();
    }

    public Adapter() {

    }

    public Adapter(Context context, FragmentManager fragmentManager, ItemClickListener itemClickListener) {

        this.context = context;

        this.fragmentManager = fragmentManager;

        this.itemClickListener = itemClickListener;

        this.data = new ArrayList<ImageModel>();

        for (int i = 0; i < 10; i++) {

            ImageModel imageModel = new ImageModel();

            imageModel.setName("1");

            imageModel.setUrl("http://images.cdn.autocar.co.uk/sites/autocar.co.uk/files/styles/gallery_slide/public/Box11.jpg?itok=jyPEEttB");

            data.add(imageModel);

        }

    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_car,
                parent, false);

        return new CarViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {

        //SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(fragmentManager, data);

        holder.viewPager.setAdapter(new CustomPagerAdapter(context));

        //holder.viewPager.setAdapter(mSectionsPagerAdapter);

        holder.underlinePageIndicator.setFades(false);

        holder.underlinePageIndicator.setViewPager(holder.viewPager);

        Car car = listCar.get(position);

        holder.textViewCarName.setText(car.getName());

        holder.textViewCarPrice.setText("€ " + car.getPrice());

        holder.textViewCarKm.setText(car.getKm() + " Km");

        holder.textViewCarYear.setText(car.getYear());

        holder.textViewCarCV.setText(car.getCv() + " CV");

        holder.textViewCarLocation.setText(car.getLocation());

        holder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                itemClickListener.onClick(view, position, isLongClick);

            }

        });

    }

    @Override
    public int getItemCount() {

        if (listCar != null) {

            return listCar.size();

        } else {

            return 0;

        }

    }

    class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.viewPager)
        ViewPager viewPager;

        @Bind(R.id.underlinePageIndicator)
        UnderlinePageIndicator underlinePageIndicator;

        @Bind(R.id.textViewCarName)
        TextView textViewCarName;

        @Bind(R.id.textViewCarPrice)
        TextView textViewCarPrice;

        @Bind(R.id.textViewCarKm)
        TextView textViewCarKm;

        @Bind(R.id.textViewCarYear)
        TextView textViewCarYear;

        @Bind(R.id.textViewCarCV)
        TextView textViewCarCV;

        @Bind(R.id.textViewCarLocation)
        TextView textViewCarLocation;

        private ItemClickListener clickListener;

        public ItemClickListener getClickListener() {
            return clickListener;
        }

        public void setClickListener(ItemClickListener clickListener) {
            this.clickListener = clickListener;
        }

        public CarViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            clickListener.onClick(view, getLayoutPosition(), false);

        }

    }

}
