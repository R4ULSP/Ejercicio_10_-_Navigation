package es.travelworld.ejercicio10_navigation.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelworld.ejercicio10_navigation.databinding.FragmentDestinationBinding;

import es.travelworld.ejercicio10_navigation.domain.References;

public class DestinationFragment extends Fragment {

    private FragmentDestinationBinding binding;
    private StartDestinationFragmentStateAdapter fragmentStateAdapter;
    private DestinationViewPagerManager listener;

    public interface DestinationViewPagerManager{
        void nextPage();
    }

    public DestinationFragment() {
        // Required empty public constructor
    }

    public static DestinationFragment newInstance() {
        return new DestinationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDestinationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        fragmentStateAdapter = new StartDestinationFragmentStateAdapter(this);
        binding.viewPager.setAdapter(fragmentStateAdapter);

        return view;
    }

    public void nextPage(){
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DestinationFragment.DestinationViewPagerManager) {
            listener = (DestinationFragment.DestinationViewPagerManager) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private class StartDestinationFragmentStateAdapter extends FragmentStateAdapter {
        public StartDestinationFragmentStateAdapter(DestinationFragment destinationFragment) {
            super(destinationFragment);
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
            OnBoardingFragment fragment = (OnBoardingFragment) getParentFragmentManager().findFragmentByTag(References.ON_BOARDING_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new OnBoardingFragment();
            }
        }

        private Fragment startMatchFragment() {
            MatchFragment fragment = (MatchFragment) getParentFragmentManager().findFragmentByTag(References.MATCH_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new MatchFragment();
            }
        }

        private Fragment startRoommateFragment() {
            RoommateFragment fragment = (RoommateFragment) getParentFragmentManager().findFragmentByTag(References.ROOMMATE_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new RoommateFragment();
            }
        }
    }
}