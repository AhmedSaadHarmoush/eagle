package com.example.ahmed.eagletech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ahmed.eagletech.Tasks.UserDataTask;
import com.example.ahmed.eagletech.Instances.User;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView navName;
    TextView navType;
    SharedPreferences.Editor editor;
    String type="";
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        UserDataTask userDataTask = new UserDataTask(getApplicationContext());
        try {
            User user=userDataTask.execute().get();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View header=navigationView.getHeaderView(0);
            navName = (TextView) header.findViewById(R.id.navName);
            navType = (TextView) header.findViewById(R.id.navType);
            navName.setText(user.getName());
            type=user.getType();
            if(user.getType().equals("1")){
                navType.setText("Admin");
            }else if(user.getType().equals("2")){
                navType.setText("Branch Manager");
            }else{
                navType.setText("User");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(type.equals("2")){
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.nav_admin).setVisible(false);
        }

//        ViewPager viewPager = (ViewPager) fragment.getView().findViewById(R.id.pager);
//        // Assign created adapter to viewPager
//        viewPager.setAdapter(new MainActivity.TabsExamplePagerAdapter(getSupportFragmentManager()));
//
//        TabLayout tabLayout = (TabLayout) fragment.getView().findViewById(R.id.tab_layout);
//        // This method setup all required method for TabLayout with Viewpager
//        tabLayout.setupWithViewPager(viewPager);

        displayView(R.id. nav_profile);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void displayView(int viewId) {


        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_profile:
                fragment = new ProfileFragment();

                break;
            case R.id.nav_tasks:
                fragment = new MainFragment();
                break;
            case R.id.nav_admin:
                fragment = new Admin();
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            //getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_admin) {

        }  else if (id == R.id.nav_tasks) {

        } else if(id == R.id.nav_logout){
            SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("nameSharedPref",0);
            editor=sharedPreferences.edit();
            editor.putString("accessToken","");
            editor.putString("tokenType","");
            editor.putString("expiresIn","");
            editor.putString("refreshToken","");
            editor.commit();
            Intent intent =new Intent(Navigation.this,Login.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        displayView(item.getItemId());
        return true;
    }


}
