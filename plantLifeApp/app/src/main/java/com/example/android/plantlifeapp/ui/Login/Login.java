package com.example.android.plantlifeapp.ui.Login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.plantlifeapp.R;
import com.example.android.plantlifeapp.ui.Register.registerFragment;
import com.example.android.plantlifeapp.databinding.FragmentLoginBinding;


public class Login extends Fragment implements View.OnClickListener {
    private FragmentLoginBinding binding;
    private EditText Emailedit;
    private EditText pwdedit;
    private  Button regBtn;
    Menu myMenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        regBtn.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        Button regBtn = (Button) root.findViewById(R.id.registerbutton);

        regBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

//                Intent intent = new Intent(getActivity().getApplicationContext(), registerFragment.class);
//                startActivity(intent);

        switch (v.getId()) {
            case R.id.registerbutton:

                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.container, registerFragment.class,null);
                transaction.addToBackStack(null);
                transaction.commit();


                String message="hi";
                Toast.makeText(getActivity().getApplicationContext(), message,
                        Toast.LENGTH_SHORT).show();
                break;

        }
            }
        });
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {

    }
}