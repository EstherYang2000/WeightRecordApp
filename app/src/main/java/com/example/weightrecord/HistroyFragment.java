package com.example.weightrecord;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weightrecord.data.AppDatabase;
import com.example.weightrecord.data.Weight;
import com.example.weightrecord.data.WeightDao;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import static androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL;
import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;
import static com.example.weightrecord.HistroyFragmentDirections.*;


public class HistroyFragment extends Fragment implements HistoryAdapter.ItemClickListener{

    private View view;
    private ImageView ToAddWeightFragment;
    private static final String TAG = "MainActivity";
//    List<Weight>dailyweight;
    private HistoryAdapter mHistoryAdapter;
    private RecyclerView mRecyclerView;

    private AppDatabase mDb;
    public HistroyFragment(){

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_histroy, container, false);
        ToAddWeightFragment=(ImageView)view.findViewById(R.id.toaddweight);
        mRecyclerView=view.findViewById(R.id.recyclerViewWeights);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHistoryAdapter=new HistoryAdapter(getActivity(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.setAdapter(mHistoryAdapter);
        mDb=AppDatabase.getDbInstance(getActivity().getApplicationContext());


//        DividerItemDecoration decoration = new DividerItemDecoration(getActivity().getApplicationContext(), HORIZONTAL);
//        mRecyclerView.addItemDecoration(decoration);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipedir) {

                int position=viewHolder.getAdapterPosition();
//                int id=mHistoryAdapter.getweightList().get(position).getUid();

                List<Weight>dailyweight=mDb.weightDao().getAllWeights();
                Toast.makeText(getActivity(),"deletion successful",Toast.LENGTH_SHORT).show();
//                mDb.weightDao().deleteWeight(id);
                mDb.weightDao().deleteWeight(dailyweight.get(position));

            }
        }).attachToRecyclerView(mRecyclerView);;
        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToAddWeightFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_histroyFragment_to_addWeightFragment);
            }
        });


    }

    private void loadWeightList(){
        mDb=AppDatabase.getDbInstance(getActivity().getApplicationContext());
        List<Weight> WeightList=mDb.weightDao().getAllWeights();
        mHistoryAdapter.setdailyWeightList(WeightList);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }


    @Override
    public void onItemClickListener(int itemId) {
//        String elementid2=String.valueOf(itemId);
//        NavController navController = Navigation.findNavController(view);
//
//        ActionHistroyFragmentToHistoryListFragment3 action  =
//                new ActionHistroyFragmentToHistoryListFragment3 ("id: " + elementid2);
//        navController.navigate(action);
//        HistroyFragmentDirections
//                .ActionHistroyFragmentToHistoryListFragment3 action = HistroyFragmentDirections.setUid();
//        action.setCurrentDate(currentDate);
//        Navigation.findNavController(v).navigate(action);


    }

    @Override
    public void onResume() {
        super.onResume();
        loadWeightList();
    }


}