package es.travelworld.ejercicio10_navigation.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import com.travelworld.ejercicio10_navigation.R;
import com.travelworld.ejercicio10_navigation.databinding.ActivityHomeBinding;

import es.travelworld.ejercicio10_navigation.domain.User;
import es.travelworld.ejercicio10_navigation.view.fragments.CarFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.HomeFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.PositionFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.WipFragment;
import es.travelworld.ejercicio10_navigation.domain.References;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private User user;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(References.KEY_USER);


        setupNavigation();


    }

    private void setupNavigation() {
        setSupportActionBar(binding.toolbar);
        navController = Navigation.findNavController(this,R.id.main_fragment_frame);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
    }

    //TODO mover a un fragment con el viewPager2



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.eurodisney) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.disneylandparis.com/es-es/"));
            startActivity(intent);
        }

        if (item.getItemId() == R.id.rentacar) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            WipFragment wipFragment = WipFragment.newInstance();
            wipFragment.show(fragmentManager, null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(References.KEY_USER, user);
        startActivity(intent);
        finish();
    }


}