package com.example.ahmed.eagletech.Tasks;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.example.ahmed.eagletech.R;
import com.example.ahmed.eagletech.Instances.Branch;
import com.example.ahmed.eagletech.Instances.Job;
import com.example.ahmed.eagletech.Instances.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ahmed on 8/14/2016.
 */

public class UserDataTask extends AsyncTask<String, Void, User> {

        private final String LOG_TAG = UserDataTask.class.getSimpleName();
        private Context mContext;
        public UserDataTask(Context context){
            mContext = context;
        }




        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected User doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String returnedToken= null;

            try {
                SharedPreferences sharedPreferences=mContext.getSharedPreferences("nameSharedPref",0);
                String token=sharedPreferences.getString("accessToken","");
                final String BASE_URL =mContext.getString(R.string.server)+"users/user-data?";
                Uri builtUri= Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter("access_token",token).build();
//                String param = "access_token="+token;
//                byte[] postDataLength = param.getBytes( StandardCharsets.UTF_8 );
//
//                final String BASE_URL ="http://192.168.1.7:8000/users/user-data";
//                Uri builtUri= Uri.parse(BASE_URL).buildUpon().build();
//                URL url =new URL(builtUri.toString());
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("GET");
//
//                urlConnection.setRequestProperty( "Content-Job", "application/x-www-form-urlencoded");
//                urlConnection.setRequestProperty( "charset", "utf-8");
//                urlConnection.setRequestProperty( "Content-Length", Integer.toString(postDataLength.length ));
//                urlConnection.setDoInput(true);

//
//                JSONObject requestData = new JSONObject();


//                DataOutputStream printout;
//
//
//                printout = new DataOutputStream(urlConnection.getOutputStream());
//
//                printout.write(postDataLength);
//
//                printout.flush();
//
//                printout.close();
//
//
//                urlConnection.connect();
//
//                Map map = urlConnection.getHeaderFields();

                URL url =new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                Log.v(LOG_TAG,"Built URL :"+builtUri.toString());

//                Log.e(LOG_TAG,map.values().toString());

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

               return fetchJSONFromToken(returnedToken);


            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    @Override
    protected void onPostExecute(User s) {
        super.onPostExecute(s);
    }

    private User fetchJSONFromToken(String returnedToken) throws JSONException {
            User user=new User();
            JSONObject userJSON =new JSONObject(returnedToken);
            user.setId(userJSON.getString("id"));
            user.setName(userJSON.getString("name"));
            user.setUsername(userJSON.getString("username"));
            user.setType(userJSON.getString("type_id"));

            Job job =new Job();
            JSONObject typeJSON=userJSON.getJSONObject("type");
            job.setId(typeJSON.getString("id"));
            job.setName(typeJSON.getString("name"));
            user.setJob(job);

            Branch branch =new Branch();
            JSONObject branchJSON = userJSON.getJSONObject("branch");
            branch.setId(branchJSON.getString("branch_id"));
            branch.setName(branchJSON.getString("branch_name"));
            branch.setAddress(branchJSON.getString("branch_address"));
            branch.setCity(branchJSON.getString("branch_city_id"));
            user.setBranch(branch);
            return user;

        }
    }

