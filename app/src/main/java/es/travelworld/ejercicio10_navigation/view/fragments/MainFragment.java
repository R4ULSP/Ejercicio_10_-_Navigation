package es.travelworld.ejercicio10_navigation.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.travelworld.ejercicio10_navigation.R;
import com.travelworld.ejercicio10_navigation.databinding.FragmentMainBinding;

import java.util.Objects;

import es.travelworld.ejercicio10_navigation.domain.References;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater,container,false);

        setUpTabs();

        return binding.getRoot();
    }

    private void setUpTabs() {
        FragmentStateAdapter fragmentStateAdapter = new MainFragmentFragmentStateAdapter(this);
        binding.homeViewPager.setAdapter(fragmentStateAdapter);

        setCustomTabs();

        setTabsListeners();

        initializeIconColors();

        Objects.requireNonNull(binding.tabLayout.getTabAt(0)).select();
    }

    private void setCustomTabs() {
        new TabLayoutMediator(binding.tabLayout, binding.homeViewPager, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setCustomView(R.layout.tab_camera);
                    break;
                case 1:
                    tab.setCustomView(R.layout.tab_car);
                    break;
                case 2:
                    tab.setCustomView(R.layout.tab_terrain);
                    break;
                case 3:
                    tab.setCustomView(R.layout.tab_face);
                    break;
                default:
                    break;
            }
        })).attach();
    }

    private void setTabsListeners() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                AppCompatImageView imageView;
                switch (position) {
                    case 0:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCamera); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    case 1:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCar); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    case 2:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconTerrain); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    case 3:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconFace); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                AppCompatImageView imageView;
                switch (position) {
                    case 0:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCamera); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    case 1:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCar); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    case 2:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconTerrain); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    case 3:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconFace); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });
    }

    private void initializeIconColors() {
        setIconColorBlack(Objects.requireNonNull(Objects.requireNonNull(binding.tabLayout.getTabAt(0)).getCustomView()).findViewById(R.id.iconCamera));
        setIconColorBlack(Objects.requireNonNull(Objects.requireNonNull(binding.tabLayout.getTabAt(1)).getCustomView()).findViewById(R.id.iconCar));
        setIconColorBlack(Objects.requireNonNull(Objects.requireNonNull(binding.tabLayout.getTabAt(2)).getCustomView()).findViewById(R.id.iconTerrain));
        setIconColorBlack(Objects.requireNonNull(Objects.requireNonNull(binding.tabLayout.getTabAt(3)).getCustomView()).findViewById(R.id.iconFace));
    }

    private void setIconColorWhite(AppCompatImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.white));
    }

    private void setIconColorBlack(AppCompatImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
    }

    private class MainFragmentFragmentStateAdapter extends FragmentStateAdapter {
        public MainFragmentFragmentStateAdapter(MainFragment mainFragment) {
            super(mainFragment);

        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment;

            if (position == 0) {
                fragment = startHomeFragment();
            } else if (position == 1) {
                fragment = startCarFragment();
            } else {
                fragment = startPositionFragment(position);
            }

            return fragment;
        }

        @Override
        public int getItemCount() {
            return References.NUM_PAGES_HOME;
        }

        private Fragment startHomeFragment() {
            HomeFragment fragment = (HomeFragment) getParentFragmentManager().findFragmentByTag(References.HOME_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return HomeFragment.newInstance();
            }
        }

        private Fragment startPositionFragment(int position) {
            PositionFragment positionFragment = (PositionFragment) getParentFragmentManager().findFragmentByTag(References.POSITION_FRAGMENT);

            if (positionFragment != null) {
                return positionFragment;
            } else {
                return PositionFragment.newInstance(position);
            }
        }

        private Fragment startCarFragment() {
            CarFragment carFragment = (CarFragment) getParentFragmentManager().findFragmentByTag(References.CAR_FRAGMENT);

            if (carFragment != null) {
                return carFragment;
            } else {
                return CarFragment.newInstance();
            }
        }
    }
}