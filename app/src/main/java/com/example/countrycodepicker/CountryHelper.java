package com.example.countrycodepicker;

import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CountryHelper {

    private static CountryHelper countryHelper;
    private List<Country> mCountryArrayList;
    private Country mLocationCountry;

    private CountryHelper() {

    }

    public static CountryHelper getInstance() {
        if (countryHelper == null) {
            synchronized (CountryHelper.class) {
                if (countryHelper == null) {
                    countryHelper = new CountryHelper();
                }
            }
        }
        return countryHelper;
    }

    public List<Country> getCountryList() {
        if (mCountryArrayList == null || mCountryArrayList.isEmpty()) {
            mCountryArrayList = getAllCountry();
        }
        return mCountryArrayList;
    }

    public Country getLocationCountry() {
        if (mLocationCountry == null) {
            mLocationCountry = getCurrentCountry();
        }
        return mLocationCountry;
    }

    private List<Country> getAllCountry() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BaseApplication.getInstance().getResources().getAssets().open("country_code.json")));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            String json = stringBuilder.toString();
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            List<Country> countries = GsonConverter.fromJson(json, new TypeToken<List<Country>>() {
            }.getType());
            return countries;
        } catch (Exception e) {
        }
        return null;
    }

    private Country getCurrentCountry() {
        String countryLocale = getCountryCode();
        if (TextUtils.isEmpty(countryLocale)) {
            return null;
        }
        List<Country> countryList = getCountryList();
        if (countryList == null || countryList.isEmpty()) {
            return null;
        }
        for (Country country : countryList) {
            if (country != null && countryLocale.equals(country.getLocale())) {
                return country;
            }
        }
        return null;
    }

    private  String getCountryCode() {
        String countryCode = "";
        TelephonyManager manager = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
        String simCountryID = manager.getSimCountryIso().toUpperCase();
        String networkCountryID = manager.getNetworkCountryIso().toUpperCase();
        if (!TextUtils.isEmpty(simCountryID)) {
            countryCode = simCountryID;
        } else if (!TextUtils.isEmpty(networkCountryID)) {
            countryCode = networkCountryID;
        }
        return countryCode;
    }
}
