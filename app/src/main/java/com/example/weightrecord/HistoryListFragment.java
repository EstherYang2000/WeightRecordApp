package com.example.weightrecord;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weightrecord.data.AppDatabase;
import com.example.weightrecord.data.DataConverter;
import com.example.weightrecord.data.Weight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class HistoryListFragment extends Fragment {

ImageView imageView;
TextView todaydate,todayheight,todayweight;
View view;
Button back;

    private static final String DATE_FORMAT = "dd/MM/yyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    private AppDatabase mDb;


    public HistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_history_list, container, false);
//        imageView=view.findViewById(R.id.weightlistimage);
//        todaydate=view.findViewById(R.id.weightlistdate);
//        todayheight=view.findViewById(R.id.weightlistheight);
//        todayweight=view.findViewById(R.id.weightlistweight);
//        back=view.findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                NavController navController= Navigation.findNavController(view);
////                navController.navigate(R.id.action_historyListFragment_to_histroyFragment2);
//
//            }
//        });



        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
//        HistoryListFragmentArgs args = HistoryListFragmentArgs.fromBundle(getArguments());
//        int uid=Integer.parseInt(args.getUid());
//        mDb=AppDatabase.getDbInstance(getActivity().getApplicationContext());
//        List<Weight> WeightList=mDb.weightDao().getAllWeights();
//        byte[] bitmap=WeightList.get(uid).getImage();
//        Date date=WeightList.get(uid).getDate();
//        String height=WeightList.get(uid).getHeight();
//        String weight=WeightList.get(uid).getWeight();
//        imageView.setImageBitmap(DataConverter.convertByteArray2Image(bitmap));
//        todaydate.setText(dateFormat.format(date));
//        todayheight.setText(height);
//        todayweight.setText(weight);
    }
}