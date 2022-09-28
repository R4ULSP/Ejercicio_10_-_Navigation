package es.travelworld.ejercicio10_navigation.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import com.travelworld.ejercicio10_navigation.R;
import com.travelworld.ejercicio10_navigation.databinding.ActivityHomeBinding;

import java.util.Objects;

import es.travelworld.ejercicio10_navigation.domain.User;
import es.travelworld.ejercicio10_navigation.view.fragments.WipFragment;
import es.travelworld.ejercicio10_navigation.domain.References;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(References.KEY_USER);

        Snackbar.make(binding.getRoot(), "Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup(), BaseTransientBottomBar.LENGTH_LONG).show();


        //TODO Gestionar la recepcion del usuario con safeArgs

        setupNavigation();


    }

    private void setupNavigation() {
        setSupportActionBar(binding.toolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.mainFragmentFrame.getId());
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
    }


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

    //TODO: Gestionar BackStack
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(References.KEY_USER, user);
        startActivity(intent);
        finish();
    }


}