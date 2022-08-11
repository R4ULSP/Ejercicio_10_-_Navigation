package es.travelworld.ejercicio10_navigation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;

import com.travelworld.ejercicio10_navigation.databinding.ActivityMainBinding;

import es.travelworld.ejercicio10_navigation.view.fragments.MatchFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.OnBoardingFragment;
import es.travelworld.ejercicio10_navigation.view.fragments.RoommateFragment;
import es.travelworld.ejercicio10_navigation.domain.References;

public class MainActivity extends AppCompatActivity implements OnBoardingFragment.OnClickItemOnBoardingFragment, MatchFragment.OnClickItemMatchFragment, RoommateFragment.OnClickItemRoommateFragment {

    private ActivityMainBinding binding;

    //A

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentStateAdapter fragmentStateAdapter = new MainActivityFragmentStateAdapter(this);
        binding.viewPager.setAdapter(fragmentStateAdapter);
    }

    @Override
    public void onBackPressed() {
        if (binding.viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
            finish();
        } else {
            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onBoardingNextButton() {
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
    }

    @Override
    public void matchNextButton() {
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
    }

    @Override
    public void matchSkipButton() {
        launchLoginActivity();
    }

    @Override
    public void roommateLoginButton() {
        launchLoginActivity();
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private class MainActivityFragmentStateAdapter extends FragmentStateAdapter {

        public MainActivityFragmentStateAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new Fragment();

            switch (position) {
                case 0:
                    fragment = startOnBoardingFragment();
                    break;
                case 1:
                    fragment = startMatchFragment();
                    break;
                case 2:
                    fragment = startRoommateFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }

        @Override
        public int getItemCount() {
            return References.NUM_PAGES_MAIN;
        }

        private Fragment startOnBoardingFragment() {
            OnBoardingFragment fragment = (OnBoardingFragment) getSupportFragmentManager().findFragmentByTag(References.ON_BOARDING_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new OnBoardingFragment();
            }
        }

        private Fragment startMatchFragment() {
            MatchFragment fragment = (MatchFragment) getSupportFragmentManager().findFragmentByTag(References.MATCH_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new MatchFragment();
            }
        }

        private Fragment startRoommateFragment() {
            RoommateFragment fragment = (RoommateFragment) getSupportFragmentManager().findFragmentByTag(References.ROOMMATE_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new RoommateFragment();
            }
        }
    }
}