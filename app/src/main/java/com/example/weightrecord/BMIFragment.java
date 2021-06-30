package com.example.weightrecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;
import java.util.Locale;

import static com.example.weightrecord.BMIFragmentDirections.actionBMIFragmentToBmiDetailsFragment;


public class BMIFragment extends Fragment {

    private View view;
    private EditText etBmiWeight;
    private EditText etBmiHeight;
    private double bmi;
    private static final String TAG = "MainActivity";
    private static SharedPreferences _prefs;
    private FirebaseAuth firebaseAuth;
    private Button CalculateBmiButton, BmiDetailsbutton;
    String titleText;
    String bodyText;


    public BMIFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_b_m_i, container, false);
        bmi = 0;

        TextView tvBmiWeight = view.findViewById(R.id.tvBmiWeight);
        TextView tvBmiHeight = view.findViewById(R.id.tvBmiHeight);

        etBmiWeight = view.findViewById(R.id.etBmiWeight);
        etBmiHeight = view.findViewById(R.id.etBmiHeight);
        TextView tvBmiTitle = view.findViewById(R.id.tvBmiTitle);
        TextView tvBmiBody = view.findViewById(R.id.tvBmiBody);
        tvBmiHeight.setText(String.valueOf(etBmiWeight.getText().toString()));
        tvBmiWeight.setText(String.valueOf(etBmiHeight.getText().toString()));
        tvBmiTitle.setText("");
        tvBmiBody.setText("");
        CalculateBmiButton=view.findViewById(R.id.btCalculateBmi);
        BmiDetailsbutton=view.findViewById(R.id.btBmiDetails);
        CalculateBmiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BMI(view);
            }
        });

        BmiDetailsbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(view);

                BMIFragmentDirections.ActionBMIFragmentToBmiDetailsFragment action =
                        actionBMIFragmentToBmiDetailsFragment("Title: " + titleText,"Body"+bodyText);
                navController.navigate(action);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void CalculateBMI(){
        String strWeight = etBmiWeight.getText().toString();
        String strHeight = etBmiHeight.getText().toString();
        double weight = Double.valueOf(strWeight);
        double height = Double.valueOf(strHeight);

        double result = 0;
        result = Calc.ImperialBMI(weight,height);
        result = Calc.MetricBMI(weight,height);
        bmi = result;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void DisplayBmi(){
        TextView tvBmiTitle = view.findViewById(R.id.tvBmiTitle);
        TextView tvBmiBody = view.findViewById(R.id.tvBmiBody);
        String name ="You";
        titleText = String.format(Locale.ENGLISH, getText(R.string.bmi_main).toString(),bmi);
        tvBmiTitle.setText(titleText);

        String range = GenerateBmiRangeText();
         bodyText = String.format(Locale.ENGLISH, getText(R.string.bmi_body).toString(),name,range);
        tvBmiBody.setText(bodyText);
    }

    public String GenerateBmiRangeText(){

        if(bmi < 18.5){return getText(R.string.underweight).toString();}
        if(bmi < 24.9){return getText(R.string.normal).toString();}
        if(bmi < 29.9){return getText(R.string.overweight).toString();}
        if(bmi < 34.9){return getText(R.string.obese).toString();}
        if(bmi >=34.9){return getText(R.string.extremely_obese).toString();}
        return "";
    }

    public void BMI(View v){
        CalculateBMI();
        DisplayBmi();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

}