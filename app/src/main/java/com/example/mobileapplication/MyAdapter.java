package com.example.mobileapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;


import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //list that contain some string values
    private List<CompanyData> mDataset;

    //parameter for my onclicklistener for the recycleview.. so when i hit it it will respond
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<CompanyData> myDataset, Context context)
    {
        mDataset = myDataset;

        //context.. to initialise the context from here
        this.context = context;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    //This class is responsible for each item on the list
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case

        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
            v.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }



        @Override
      public void onClick(View v) //I create this so when I hit the recycle view it will show the content in it
        {
            //get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            //use that to acccess the affeced item in mWordList.
            String element = mDataset.get(mPosition).getCompany_name();
            String companyNumm = mDataset.get(mPosition).getCompany_number();

            Log.d("position", mPosition+"");
            Log.d("name", element);
            Log.d("company_number", companyNumm);
            //the onclick Log shows the company number.

            Toast.makeText(context,element+"Clicked",Toast.LENGTH_SHORT ).show();

            //new Nodepage(companyNumm).jsonParse2();

            //Transfer the company number to the next activity
            Intent intent = new Intent(context, Nodepage.class);
            intent.putExtra("companyNumm",companyNumm);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).getCompany_name());

//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int mPosition = getLayoutPosition();
////                Log.d("position", mPosition+"");
////                use that to acccess the affeced item in mWordList.
////                String element = mDataset.get(mPosition);
////                Log.d("name", element);
//
////                String query = element;
//
//                Intent intent = new Intent(context, Nodepage.class);
////
//                context.startActivity(intent);
//            }
//        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
