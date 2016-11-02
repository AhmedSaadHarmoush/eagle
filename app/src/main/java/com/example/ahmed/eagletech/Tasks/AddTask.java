package com.example.ahmed.eagletech.Tasks;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.ahmed.eagletech.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by ahmed on 8/15/2016.
 */

public class AddTask extends AsyncTask<String, Void, String> {
    private final String LOG_TAG =AddTask.class.getSimpleName();
    private Context mContext;

    public AddTask(Context mContext) {
        this.mContext = mContext;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override

    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String returnedToken= null;
        try {
            if(params[0].equals("task")){

                SharedPreferences sharedPreferences=mContext.getSharedPreferences("nameSharedPref",0);
                String token=sharedPreferences.getString("accessToken","");
                String param = "access_token="+token+"&task_num="+params[1]
                        +"&task_name="+params[2]
                        +"&task_description="+params[3]
                        +"&task_type_id="+params[4]
                        +"&priority_id="+params[5]
                        +"&severity_id="+params[6]
                        +"&status_id="+params[7]
                        +"&assigned_to="+params[8]
                        +"&customer_id="+params[9]
                        +"&task_map_location=null"
                        ;



                byte[] postDataLength = param.getBytes( StandardCharsets.UTF_8 );
                final String BASE_URL =mContext.getString(R.string.server)+"tasks/manage/create";
                Uri builtUri= Uri.parse(BASE_URL).buildUpon().build();
                URL url =new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");

                urlConnection.setRequestProperty( "Content-Job", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty( "charset", "utf-8");
                urlConnection.setRequestProperty( "Content-Length", Integer.toString(postDataLength.length ));
                urlConnection.setDoInput(true);

                DataOutputStream printout;


                printout = new DataOutputStream(urlConnection.getOutputStream());

                printout.write(postDataLength);

                printout.flush();

                printout.close();


                urlConnection.connect();

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
                Log.v(LOG_TAG,"Task : "+returnedToken);
                return "successful";

            }else if(params[0].equals("task_type")){

                SharedPreferences sharedPreferences=mContext.getSharedPreferences("nameSharedPref",0);
                String token=sharedPreferences.getString("accessToken","");
                String param = "type_name="+params[1]+
                        "&type_description="+params[2]+
                        "&access_token="+token;
                byte[] postDataLength = param.getBytes( StandardCharsets.UTF_8 );


                final String BASE_URL =mContext.getString(R.string.server)+"tasks/manage/new-type";
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

                return "successful";
            }else if(params[0].equals("user")){

                SharedPreferences sharedPreferences=mContext.getSharedPreferences("nameSharedPref",0);
                String token=sharedPreferences.getString("accessToken","");
                String param = "access_token="+token+
                        "&username="+params[1]+
                        "&password="+params[2]+
                        "&name="+params[3]+
                        "&mobile_number="+params[4]+
                        "&address="+params[5]+
                        "&email_address="+params[6]+
                        "&branch_id="+params[7]+
                        "&type_id="+params[8];
                if(Integer.parseInt(params[8])==1){
                    param=param +"&birth_date="+ params[9]+
                        "&job_role_id="+params[10]+
                        "&manager_id="+params[11];

                }else{
                    param=param +"&customer_city="+ params[9]+
                            "&responsible_name="+params[10];
                }

                byte[] postDataLength = param.getBytes( StandardCharsets.UTF_8 );

                Log.e("eror","Here");
                final String BASE_URL =mContext.getString(R.string.server)+"users/manage/create";
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

                return "successful";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "corrupted";
        }


        return null;
    }
}
