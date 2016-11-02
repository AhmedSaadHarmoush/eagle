package com.example.ahmed.eagletech.Tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ahmed.eagletech.R;
import com.example.ahmed.eagletech.Instances.Severity;

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

public class GetSeveritiesTask extends AsyncTask<String, Void, ArrayList> {

    private final String LOG_TAG = UserDataTask.class.getSimpleName();
    private Context mContext;
    public GetSeveritiesTask(Context context){
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
            final String BASE_URL =mContext.getString(R.string.server)+"tasks/manage/get-severity?";
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
        ArrayList severities = new ArrayList();
        JSONArray severityArray =new JSONArray(returnedToken);
        for(int i=0;i<severityArray.length();i++){
            JSONObject sevJSON =severityArray.getJSONObject(i);
            Severity severity =new Severity();
            severity.setId(sevJSON.getString("severity_id"));
            severity.setName(sevJSON.getString("severity_name"));
            severity.setDescription(sevJSON.getString("severity_description"));
            severities.add(severity);

        }
        return severities;

    }

}
