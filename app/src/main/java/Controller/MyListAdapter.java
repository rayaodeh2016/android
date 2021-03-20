package Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import ptuk.coder.loginform.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> implements Filterable {

    private List<Data> listdata;
    private List<Data> dataFilter;
    // RecyclerView recyclerView;
    public MyListAdapter(List<Data> listdata ) {
        this.listdata = listdata;
        this.dataFilter=listdata;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_customer, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = listdata.get(position);

        holder.textView1.setText(data.getName().substring(0,1));
        holder.textView.setText(data.getName());
        holder.textView2.setText(data.getEmail());



    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView, textView1,textView2;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.usernameux);
            this.textView1 = (TextView) itemView.findViewById(R.id.prefixx);
            this.textView2 = (TextView) itemView.findViewById(R.id.roll_vx);


        }
    }

    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if(constraint== null |constraint.length()==0){
                    filterResults.count=dataFilter.size();
                    filterResults.values=dataFilter;
                }else {
                    String search=constraint.toString().toLowerCase();
                    List<Data> data=new ArrayList<>();
                    for (Data data1:dataFilter){
                        if(data1.getName().toLowerCase().contains(search)){
                            data.add(data1);

                        }

                    }
                    filterResults.count=data.size();
                    filterResults.values=data;

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listdata= (List<Data>) results.values;
                notifyDataSetChanged();
            }
        };
        return  filter;
    }



}