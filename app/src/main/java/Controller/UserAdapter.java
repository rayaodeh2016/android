package Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Data;
import Views.AdminPages.AllCoustemer;
import ptuk.coder.loginform.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterVh> implements Filterable {
private List<Data> userList,mydata1;
private Context  context;
private  SelectedUser selectedUser;
private AllCoustemer allCoustemer;

private List<Data> dataFilter;

    public UserAdapter(List<Data> userList,SelectedUser selectedUser) {
        this.userList = userList;
        this.selectedUser=selectedUser;
        this.dataFilter=userList;

    }


    @NonNull
    @Override
    public UserAdapter.UserAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context=parent.getContext();
       return new UserAdapterVh(
               LayoutInflater.from(context).inflate(
                       R.layout.raw_user,null));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserAdapterVh holder, int position) {
        Data data=userList.get(position);
        String username=data.getName();
        String prefix=data.getName().substring(0,1);
        String roll=data.getRoll();
        holder.textView.setText(username);

        holder.textPrefix.setText(prefix);
        holder.rollV.setText(roll);



    }

    @Override
    public int getItemCount() {
        return userList.size();
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
userList= (List<Data>) results.values;
notifyDataSetChanged();
            }
        };
        return  filter;
    }

    public  interface  SelectedUser{
        void SelectedUser(Data data);
}

    public class UserAdapterVh extends RecyclerView.ViewHolder{
TextView textPrefix;
TextView textView,rollV;
ImageView icon;

        public UserAdapterVh(@NonNull View itemView) {
            super(itemView);
            textPrefix=itemView.findViewById(R.id.prefix);
            textView=itemView.findViewById(R.id.usernameu);
            rollV=itemView.findViewById(R.id.roll_va);
            icon=itemView.findViewById(R.id.imagev);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 selectedUser.SelectedUser(userList.get(getAdapterPosition()));
                }
            });

        }
    }

}
