package com.example.emergengy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class CategoryActivity extends AppCompatActivity {

    private long backPressedTime;

    Fragment selectedFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if (Build.VERSION.SDK_INT>=21)
        {
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }

        BottomNavigationView bottomnavigation = findViewById(R.id.bottom_navigation);
        bottomnavigation.setOnNavigationItemSelectedListener(navlistener);

        Fragment selectedFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new HospitalFragment();
                            break;

                        case R.id.nav_about:
                            selectedFragment = new AboutFragment();
                            break;

                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
                };

                @Override
                public boolean onCreateOptionsMenu(Menu menu) {

                    getMenuInflater().inflate(R.menu.menu, menu);
                    return true;

                }

                @Override
                public boolean onOptionsItemSelected(MenuItem item) {

                    int id = item.getItemId();

                    switch (id) {
                        case R.id.logout:
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(CategoryActivity.this, login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                    }
                    return true;
                }

                @Override
                public void onBackPressed() {

                    if (backPressedTime + 2000 > System.currentTimeMillis()) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    } else {
                        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
                    }
                    backPressedTime = System.currentTimeMillis();
                }
}
