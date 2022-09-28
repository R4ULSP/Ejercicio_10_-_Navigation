package es.travelworld.ejercicio10_navigation.view;

import static es.travelworld.ejercicio10_navigation.domain.References.PRUEBAS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.travelworld.ejercicio10_navigation.R;
import com.travelworld.ejercicio10_navigation.databinding.ActivityLoginBinding;

import java.util.Objects;

import es.travelworld.ejercicio10_navigation.domain.User;
import es.travelworld.ejercicio10_navigation.view.fragments.DestinationFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.LoginErrorFragment;
import es.travelworld.ejercicio10_navigation.domain.References;
import es.travelworld.ejercicio10_navigation.view.fragments.LoginFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.MatchFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.RoommateFragment;

public class LoginActivity extends AppCompatActivity implements MatchFragment.OnClickItemMatchFragment, RoommateFragment.OnClickItemRoommateFragment, LoginFragment.OnClickItemLoginFragment {

    private ActivityLoginBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpNavigation();
    }

    private void setUpNavigation() {
        setSupportActionBar(binding.materialToolbar); //Establecer la action bar
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.loginFragmentFrame.getId());
        navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build(); //Ceder la parte de la actionBar a la appBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public void loginButton(User user, String code) {
        //TODO Posible safeArgs al HomeActivity: comunicacion entre ellos
        if (code.equals(References.LOGIN_SUCCESSFUL)) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(References.KEY_USER, user);
            startActivity(intent);
            finish();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            LoginErrorFragment loginErrorFragment = LoginErrorFragment.newInstance();
            loginErrorFragment.show(fragmentManager, null);
        }
    }

    @Override
    public void matchSkipButton() {
        navigateToLogin();
    }

    private void navigateToLogin() {
        navController.navigate(R.id.to_loginFragment_from_destinationFragment);
    }

    @Override
    public void roommateLoginButton() {
        navigateToLogin();
    }

    @Override
    public void onBackPressed() {
        int currentFragment = Objects.requireNonNull(navController.getCurrentDestination()).getId();

        if (currentFragment == R.id.loginFragment) {
            navController.navigate(R.id.to_destinationFragment_from_loginFragment);
        } else if (currentFragment == R.id.registerFragment) {
            navController.navigate(R.id.to_loginFragment_from_registerFragment);
        } else if (currentFragment == R.id.destinationFragment) {
            finish();
        }
    }


}