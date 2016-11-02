package com.example.ahmed.eagletech.Tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ahmed.eagletech.Instances.Branch;
import com.example.ahmed.eagletech.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ahmed on 8/18/2016.
 */

public class GetBranchesTask extends AsyncTask<String, Void, ArrayList> {

    private final String LOG_TAG = UserDataTask.class.getSimpleName();
    private Context mContext;
    public GetBranchesTask(Context context){
        mContext = context;
    }


    @Override
    protected ArrayList doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String returnedToken= null;

        try {
            SharedPreferences sharedPreferences=mContext.getSharedPreferences("nameSharedPref",0);
            String token=sharedPreferences.getString("accessToken","");
            final String BASE_URL =mContext.getString(R.string.server)+"users/branches?";
            Uri builtUri= Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter("access_token",token).build();

            URL url =new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.v(LOG_TAG,"Built URL :"+builtUri.toString());


            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            returnedToken =buffer.toString();
            Log.v(LOG_TAG,"string : "+returnedToken);


            return fetchJSONFromToken(returnedToken);


        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList fetchJSONFromToken(String returnedToken) throws JSONException {
        ArrayList branches = new ArrayList();
        JSONArray customersArray =new JSONArray(returnedToken);
        for(int i=0;i<customersArray.length();i++){
            JSONObject customerJSON =customersArray.getJSONObject(i);
            Branch branch =new Branch();
            branch.setId(customerJSON.getString("branch_id"));
            branch.setName(customerJSON.getString("branch_name"));
            branch.setAddress(customerJSON.getString("branch_address"));
            branch.setCity(customerJSON.getString("branch_city_id"));
            branches.add(branch);

        }
        return branches;

    }

}
