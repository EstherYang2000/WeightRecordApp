package com.example.weightrecord;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weightrecord.data.DataConverter;
import com.example.weightrecord.data.Weight;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    final private ItemClickListener mItemClickListener;
    private Context mContext;
    private List<Weight> dailyWeight;
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());



    public HistoryAdapter(Context mContext ,ItemClickListener mItemClickListener){
        this.mContext=mContext;
//        this.dailyWeight=dailyWeight;
        this.mItemClickListener=mItemClickListener;
    }

    public void setdailyWeightList(List<Weight>dailyWeightList){
        this.dailyWeight=dailyWeightList;
        notifyDataSetChanged();
    }

    public List<Weight> getweightList() {
        return dailyWeight;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.weight_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Weight weight=dailyWeight.get(position);

        holder.daily_date.setText(dateFormat.format(weight.getDate()));
        holder.daily_figure_picture.setImageBitmap(DataConverter.convertByteArray2Image(weight.getImage()));
        holder.daily_height.setText(weight.getHeight());
        holder.daily_weight.setText(weight.getWeight());




    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    @Override
    public int getItemCount() {
        if (dailyWeight == null) {
            return 0;
        }
        return dailyWeight.size();

    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView daily_date;
        TextView daily_height;
        TextView daily_weight;
        ImageView daily_figure_picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            daily_date=(TextView)itemView.findViewById(R.id.historydate);
            daily_height=(TextView)itemView.findViewById(R.id.historyheight);
            daily_weight=(TextView)itemView.findViewById(R.id.historyweight);
            daily_figure_picture=(ImageView)itemView.findViewById(R.id.historyimage);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int elementId = dailyWeight.get(getAdapterPosition()).getUid();
            mItemClickListener.onItemClickListener(elementId);
//            Bitmap weightlistimage=DataConverter.convertByteArray2Image(dailyWeight.get(getAdapterPosition()).getImage());
//            String weightlistdate=String.valueOf(dateFormat.format(dailyWeight.get(getAdapterPosition()).getDate()));
//            String weightlistheight=dailyWeight.get(getAdapterPosition()).getHeight();
//            String weightlistweight=dailyWeight.get(getAdapterPosition()).getWeight();
//            String elementid2=String.valueOf(elementId);
//            NavController navController = Navigation.findNavController(v);
//
//            HistroyFragmentDirections.ActionHistroyFragmentToHistoryListFragment3 action  =
//                    new HistroyFragmentDirections.actionHistroyFragmentToHistoryListFragment3 ("id: " + elementid2);
//            navController.navigate(action);

        }
    }
}
