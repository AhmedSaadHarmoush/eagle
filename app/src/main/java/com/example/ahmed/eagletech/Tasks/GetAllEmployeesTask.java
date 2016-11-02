package com.example.ahmed.eagletech.Tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ahmed.eagletech.Instances.Employee;
import com.example.ahmed.eagletech.Instances.User;
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

public class GetAllEmployeesTask extends AsyncTask<String, Void, ArrayList> {

    private final String LOG_TAG = UserDataTask.class.getSimpleName();
    private Context mContext;
    public GetAllEmployeesTask(Context context){
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
            final String BASE_URL =mContext.getString(R.string.server)+"users/all-employees?";
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
        ArrayList employees = new ArrayList();
        JSONArray employeesArray =new JSONArray(returnedToken);
        for(int i=0;i<employeesArray.length();i++){
            JSONObject employeeJSON =employeesArray.getJSONObject(i);
            Employee employee =new Employee();
            User user=new User();
            employee.setEmploee_id(employeeJSON.getString("employee_id"));
            employee.setBirth_date(employeeJSON.getString("birth_date"));
            employee.setJob_role_id(employeeJSON.getString("job_role_id"));
            employee.setManager_id(employeeJSON.getString("manager_id"));
            if(employeeJSON.isNull("user")){
                user.setName("");
                employee.setUser(user);
            }else {
                JSONObject userJSON =employeeJSON.getJSONObject("user");
                user.setName(userJSON.getString("name"));
                employee.setUser(user);
            }
            employees.add(employee);

        }
        return employees;

    }

}
