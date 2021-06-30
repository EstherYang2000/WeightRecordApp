package com.example.weightrecord;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightrecord.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity  implements
        NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener authListener = null;
    TextView mainLabel = null;
    private Button signOut;
    private View view;

    public Toolbar toolbar;

    public DrawerLayout drawerLayout;

    public NavController navController;

    public NavigationView navigationView;


    private FirebaseUser currentUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation();
        CreateToolbar();



                // [START initialize_auth]
                // Initialize Firebase Auth
                mAuth = FirebaseAuth.getInstance();
//        mainLabel = findViewById(R.id.tvHeaderName);
                // [END initialize_auth]

                authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            // user auth state is changed – user is null
                            // launch login activity
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                };

//        Intent notifyIntent = new Intent(this,MyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast
//                (context, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
//                1000 * 60 * 60 * 24, pendingIntent);



    }

    private void setupNavigation() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("HOME");

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigationView);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

        drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.nav_home:
                navController.navigate(R.id.mainFragment);
                toolbar.setTitle("HOME");
                break;

            case R.id.nav_converter:
                navController.navigate(R.id.BMIFragment);
                toolbar.setTitle("BMI Calculator");
                break;

            case R.id.nav_history:
                navController.navigate(R.id.histroyFragment);
                toolbar.setTitle("History");
                break;
            case R.id.nav_logout:
                toolbar.setTitle("LOG OUT");
                mAuth.signOut();
                finish();
                break;
        }
        return true;

    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
//            reload(currentUser);
            mAuth.addAuthStateListener(authListener);
        } else {
            Toast.makeText(MainActivity.this, "User not login",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            mAuth.removeAuthStateListener(authListener);
        }
    }
    public void CreateToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

            inflater.inflate(R.menu.action_bar_no_share, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                navController.navigate(R.id.settingFragment);

                return true;

            case R.id.action_graph:
                navController.navigate(R.id.graphFragment);

                return true;

            case R.id.action_home:
                navController.navigate(R.id.mainFragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
//    public void testNotification(View view) {
//        NotificationUtils.remindUserBecauseCharging(this);
//    }


}
