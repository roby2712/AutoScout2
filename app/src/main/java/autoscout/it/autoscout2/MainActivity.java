package autoscout.it.autoscout2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import autoscout.it.autoscout2.adapter.Adapter;
import autoscout.it.autoscout2.adapter.ItemClickListener;
import autoscout.it.autoscout2.model.Car;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private LinearLayoutManager linearLayoutManager;

    private GridLayoutManager gridLayoutManager;

    private String nome;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;
    }

    private int type = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {

            case R.id.action_switch: {

                if (type == 1) {

                    recyclerView.setLayoutManager(gridLayoutManager);

                    type = 2;

                } else if (type == 2) {

                    recyclerView.setLayoutManager(linearLayoutManager);

                    type = 1;

                }

                break;
            }

        }

        return true;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putString("KEY", nome);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        /*if (savedInstanceState != null && savedInstanceState.containsKey("KEY")) {

            nome = savedInstanceState.getString("KEY");
*/
        if (nome != null) {

            Log.i("NOME", nome);

        } else {

            Log.i("NOME", "nome vuoto");

        }

  /*  }*/

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("125");

        getSupportActionBar().setSubtitle("Risultati");

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Car car = new Car();

        car.setName("Porsche Boxster 3.2");

        car.setPrice(12.000);

        car.setKm(79.197);

        car.setYear("12/2002");

        car.setCv(185);

        car.setLocation("I-10148 TORINO");

        List<Car> listCar = new ArrayList<Car>();

        for (int i = 0; i < 4; i++) {

            listCar.add(car);

        }

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        gridLayoutManager = new GridLayoutManager(this, 2);

        int orientation = getResources().getConfiguration().orientation;

        switch (orientation) {

            case 1: {

                recyclerView.setLayoutManager(linearLayoutManager);

                break;

            }

            case 2: {

                recyclerView.setLayoutManager(gridLayoutManager);

                break;

            }

        }

        Adapter adapter = new Adapter(this, getSupportFragmentManager(), new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                //Toast.makeText(MainActivity.this, "position: " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                startActivity(intent);

            }

        });

        recyclerView.setAdapter(adapter);

        adapter.setListCar(listCar);

    }

}
