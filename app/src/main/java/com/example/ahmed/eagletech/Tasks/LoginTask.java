package com.example.ahmed.eagletech.Tasks;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.ahmed.eagletech.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by ahmed on 8/4/2016.
 */

public class LoginTask extends AsyncTask<String, Void, String> {
    private final String LOG_TAG =LoginTask.class.getSimpleName();
    private Context mContext;
    SharedPreferences.Editor editor;
    public LoginTask(Context context){
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String returnedToken= null;

        try {


//            String param = "username=miessam&password=123456&client_id=1&client_secret=123456&grant_type=password";
            String param = "username="+params[0]+"&password="+params[1]+"&client_id=1&client_secret=123456&grant_type=password";
            byte[] postDataLength = param.getBytes( StandardCharsets.UTF_8 );

            final String BASE_URL =mContext.getString(R.string.server)+"login";
            Uri builtUri= Uri.parse(BASE_URL).buildUpon().build();
            URL url =new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty( "Content-Job", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty( "charset", "utf-8");
            urlConnection.setRequestProperty( "Content-Length", Integer.toString(postDataLength.length ));
            urlConnection.setDoInput(true);


            JSONObject requestData = new JSONObject();


            DataOutputStream printout;


            printout = new DataOutputStream(urlConnection.getOutputStream());

            printout.write(postDataLength);

            printout.flush();

            printout.close();


            urlConnection.connect();

           Map map = urlConnection.getHeaderFields();


                Log.e(LOG_TAG,map.values().toString());

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

//
//            Log.e("HEEEEEEEEEEY","Connected");

            fetchJSONFromToken(returnedToken);
            return "successful";


        }catch (FileNotFoundException e){
            return "undefinedUser";
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void fetchJSONFromToken(String returnedToken) throws JSONException {
        JSONObject loginJSON =new JSONObject(returnedToken);
        String accessToken=loginJSON.getString("access_token");
        String tokenType =loginJSON.getString("token_type");
        String expiresIn =loginJSON.getString("expires_in");
        String refreshToken=loginJSON.getString("refresh_token");
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("nameSharedPref",0);
        editor=sharedPreferences.edit();
        editor.putString("accessToken",accessToken);
        editor.putString("tokenType",tokenType);
        editor.putString("expiresIn",expiresIn);
        editor.putString("refreshToken",refreshToken);
        editor.commit();
    }
}
