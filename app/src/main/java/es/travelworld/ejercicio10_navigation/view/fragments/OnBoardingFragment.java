package es.travelworld.ejercicio10_navigation.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelworld.ejercicio10_navigation.databinding.FragmentOnboardingBinding;

public class OnBoardingFragment extends Fragment {

    private FragmentOnboardingBinding binding;

    private OnClickItemOnBoardingFragment listener;

    public interface OnClickItemOnBoardingFragment {
        void onBoardingNextButton();
    }

    public OnBoardingFragment() {
        // Required empty public constructor
    }


    public static OnBoardingFragment newInstance() {
        return new OnBoardingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setListeners();

        return view;
    }

    private void setListeners() {
        binding.buttonNext.setOnClickListener(view -> listener.onBoardingNextButton());
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnClickItemOnBoardingFragment) {
            listener = (OnClickItemOnBoardingFragment) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}