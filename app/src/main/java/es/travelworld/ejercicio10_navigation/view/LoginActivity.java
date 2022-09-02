package es.travelworld.ejercicio10_navigation.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.travelworld.ejercicio10_navigation.R;
import com.travelworld.ejercicio10_navigation.databinding.ActivityLoginBinding;

import es.travelworld.ejercicio10_navigation.domain.User;
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
        navController = Navigation.findNavController(this, R.id.login_fragment_frame);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build(); //Ceder la parte de la actionBar a la appBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public void loginButton(User user, String code) {
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
}