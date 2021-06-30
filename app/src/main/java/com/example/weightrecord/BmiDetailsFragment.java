package com.example.weightrecord;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;


public class BmiDetailsFragment extends Fragment {

    TextView todaytitle,todaybody;
    private View view;
    Button backbmi;
    private WebView webview;
    private ProgressDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_bmi_details, container, false);
//        Toolbar toolbar=view.findViewById(R.id.toolbar);
//        toolbar.setTitle("BMI Detail");

        todaytitle=view.findViewById(R.id.bmititle);
        todaybody=view.findViewById(R.id.bmibody);
        backbmi=view.findViewById(R.id.back);
        backbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_bmiDetailsFragment_to_BMIFragment);

            }
        });
        return view;

        }

    @Override
    public void onStart() {
        super.onStart();
        BmiDetailsFragmentArgs args = BmiDetailsFragmentArgs.fromBundle(getArguments());
        todaytitle.setText(args.getBmititle());
        todaybody.setText(args.getBmibody());
    }

//    public void SetUpPage(){
//        webview = view.findViewById(R.id.wvBMI);
//        dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("Loading..");
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                dialog.show();
//                super.onPageStarted(view, url, favicon);
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                dialog.cancel();
//                dialog.dismiss();
//            }
//        });
//        webview.loadUrl("file:///android_asset/bmi_details.html");
//    }



}