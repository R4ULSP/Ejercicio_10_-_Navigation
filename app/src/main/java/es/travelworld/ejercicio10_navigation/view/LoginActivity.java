package es.travelworld.ejercicio10_navigation.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.travelworld.ejercicio10_navigation.databinding.ActivityLoginBinding;

import es.travelworld.ejercicio10_navigation.domain.User;
import es.travelworld.ejercicio10_navigation.view.fragments.LoginErrorFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.LoginFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.RegisterFragment;
import es.travelworld.ejercicio10_navigation.domain.References;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnClickItemLoginFragment, RegisterFragment.OnClickItemRegisterFragment {

    private ActivityLoginBinding binding;
    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startLoginFragment();
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
}