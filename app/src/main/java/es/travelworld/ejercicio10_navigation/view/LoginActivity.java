package es.travelworld.ejercicio10_navigation.view;


import static es.travelworld.ejercicio10_navigation.domain.References.PRUEBAS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.travelworld.ejercicio10_navigation.databinding.ActivityLoginBinding;

import es.travelworld.ejercicio10_navigation.domain.User;
import es.travelworld.ejercicio10_navigation.view.fragments.DestinationFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.LoginErrorFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.LoginFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.MatchFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.OnBoardingFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.RegisterFragment;
import es.travelworld.ejercicio10_navigation.domain.References;
import es.travelworld.ejercicio10_navigation.view.fragments.RoommateFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnClickItemLoginFragment, RegisterFragment.OnClickItemRegisterFragment, OnBoardingFragment.OnClickItemOnBoardingFragment, MatchFragment.OnClickItemMatchFragment, RoommateFragment.OnClickItemRoommateFragment {

    private ActivityLoginBinding binding;
    private String currentFragment;
    private DestinationFragment destinationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //startLoginFragment();
        startDestinationFragment();
    }

    private void startDestinationFragment() {
        destinationFragment = (DestinationFragment) getSupportFragmentManager().findFragmentByTag(References.DESTINATION_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        destinationFragment != null ? destinationFragment : createInstance(),
                        References.DESTINATION_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        currentFragment = References.DESTINATION_FRAGMENT;
    }

    private DestinationFragment createInstance() {
        destinationFragment = DestinationFragment.newInstance();
        return destinationFragment;
    }


    private void startLoginFragment() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(References.LOGIN_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment != null ? fragment : LoginFragment.newInstance(),
                        References.LOGIN_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        currentFragment = References.LOGIN_FRAGMENT;
    }

    private void startRegisterFragment() {
        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag(References.REGISTER_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment != null ? fragment : RegisterFragment.newInstance(),
                        References.REGISTER_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        currentFragment = References.REGISTER_FRAGMENT;
    }

    @Override
    public void onBackPressed() {
        if (currentFragment.equals(References.LOGIN_FRAGMENT)) {
            super.onBackPressed();
            finish();
        } else {
            startLoginFragment();
        }

        //TODO - Añadir consideraciones para la gestion del DestinationFragment
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
    public void loginNewAccountButton() {
        startRegisterFragment();
    }

    @Override
    public void registerJoinButton() {
        startLoginFragment();
    }

    @Override
    public void onBoardingNextButton() {
        destinationFragment.nextPage();
    }

    @Override
    public void matchNextButton() {
        destinationFragment.nextPage();
    }

    @Override
    public void matchSkipButton() {
        startLoginFragment();
    }

    @Override
    public void roommateLoginButton() {
        startLoginFragment();
    }
}