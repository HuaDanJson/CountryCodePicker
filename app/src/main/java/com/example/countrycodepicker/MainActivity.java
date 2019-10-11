package com.example.countrycodepicker;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageView mLocationFlogView;
    private TextView mLocationCountryNameView;
    private TextView mLocationCountryCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.rl_main_activity);
        mLocationFlogView = findViewById(R.id.iv_location_country_flag);
        mLocationCountryNameView = findViewById(R.id.iv_location_country_name);
        mLocationCountryCodeView = findViewById(R.id.iv_location_country_code);
        Country locationCountry = CountryHelper.getInstance().getLocationCountry();
        if (locationCountry != null) {
            mLocationFlogView.setImageDrawable(ContextCompat.getDrawable(this, getResources().getIdentifier(locationCountry.getIcon(), "drawable", "com.example.countrycodepicker")));
            mLocationCountryNameView.setText(locationCountry.getEn());
            mLocationCountryCodeView.setText("(+" + locationCountry.getCode() + ")");
        }
        getAllCountry();
    }

    public void getAllCountry() {
        List<Country> countries = CountryHelper.getInstance().getCountryList();
        initRecyclerView(countries);
    }

    public void initRecyclerView(List<Country> countryList) {
        if (mRecyclerView == null || countryList == null || countryList.isEmpty()) {
            return;
        }
        LinearLayoutManager customLinearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(customLinearLayoutManager);
        CountryAdapter mSelectCountryAdapter = new CountryAdapter(this, countryList);
        mRecyclerView.setAdapter(mSelectCountryAdapter);
        mSelectCountryAdapter.setOnItemClicked(new CountryAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(Country country, int position) {
                Toast.makeText(MainActivity.this, "Select:" + country, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
