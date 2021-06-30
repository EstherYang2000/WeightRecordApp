package com.example.weightrecord;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.weightrecord.data.AppDatabase;
import com.example.weightrecord.data.DataConverter;
import com.example.weightrecord.data.Weight;
import com.example.weightrecord.data.WeightDao;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class AddWeightFragment extends Fragment implements View.OnClickListener {


    public AddWeightFragment() {
        // Required empty public constructor
    }
    private static final String TAG = "MainActivity";

    private View view;
    private Button takePicture;
    private Button saveWeight;
    private Button showDailyWeight;
    final int CAMERA_INTENT = 51;
    ImageView imageView;
    Bitmap bmpImage;
    EditText heightEdit,weightEdit, dateEdit;
    WeightDao weightDao;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_add_weight, container, false);

        takePicture=view.findViewById(R.id.addpicture_button);
        takePicture.setOnClickListener(this);

        saveWeight=view.findViewById(R.id.saveWeight);
        saveWeight.setOnClickListener(this);
        showDailyWeight=view.findViewById(R.id.showWeight);
        showDailyWeight.setOnClickListener(this);
        imageView=view.findViewById(R.id.weightimage);
        bmpImage=null;
        heightEdit=(EditText) view.findViewById(R.id.todayHeight);
        weightEdit=(EditText)view.findViewById(R.id.todayWeight);
        dateEdit=(EditText)view.findViewById(R.id.todayDate);
        weightDao= AppDatabase.getDbInstance(getActivity()).weightDao();
        getPermission();



        // Inflate the layout for this fragment
        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.addpicture_button:
                takePicture();
                break;
            case R.id.saveWeight:
                try {
                    saveWeight();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.showWeight:
                showDailyWeight();
                break;

        }

    }

    private void takePicture() {
        dispatchTakePictureIntent();

    }

    protected void dispatchTakePictureIntent() {

//        // Check if there is a camera.
//        Context context = getActivity();
//        PackageManager packageManager = context.getPackageManager();
//        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false){
//            Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT)
//                    .show();
//            return;
//        }


        // Camera exists? Then proceed...
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        MainActivity activity = (MainActivity)getActivity();
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {

            try {


                startActivityForResult(takePictureIntent, CAMERA_INTENT);


            } catch (ActivityNotFoundException e) {
                // display error state to the user
            }
//            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_INTENT) {
            if (resultCode == RESULT_OK) {
                bmpImage = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bmpImage);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }





    public void getPermission(){
        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
        }
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},1);
        new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setTitle("需要相機權限")
                .setMessage("你是不是傻？不給我權限我要怎麼拍照？")
                .setPositiveButton("知道了拉", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},1);
                    }
                })
                .show();

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {

        }else{
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},1);
        }
    }




        private void saveWeight () throws ParseException {
//            if(heightEdit.getText().toString().isEmpty()|| weightEdit.getText().toString().isEmpty()
//            || dateEdit.getText().toString().isEmpty()||bmpImage==null){
//                Toast.makeText(getActivity(),"Weight Data is missing",Toast.LENGTH_SHORT).show();
//
//            }else {

            Log.d("SHOW",heightEdit.getText().toString());
                Weight weight=new Weight();
                weight.setHeight(heightEdit.getText().toString());
                weight.setWeight(weightEdit.getText().toString());
                weight.setImage(DataConverter.convertImage2ByteArray(bmpImage));
                try{

                    weight.setDate(new SimpleDateFormat("dd/mm/yy").parse(dateEdit.getText().toString()));

//                    weight.setDate(new SimpleDateFormat("dd/mm/yy").parse(dateEdit.getText().toString()));
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                weightDao.insertWeight(weight);
                Toast.makeText(getActivity(),"Insertion successful",Toast.LENGTH_SHORT).show();


//            }

        }

        private void showDailyWeight () {
            NavController navController= Navigation.findNavController(view);
            navController.navigate(R.id.action_addWeightFragment_to_histroyFragment);
        }
    }