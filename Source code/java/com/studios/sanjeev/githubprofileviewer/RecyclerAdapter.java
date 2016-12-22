package com.studios.sanjeev.githubprofileviewer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private String[] titles = {"Chapter One"};

    private String[] details = {"Item one details"};

    public String[] repo={"empty"};

    public Uri imageUri;

    public RecyclerAdapter()
    {}
    public RecyclerAdapter(String a,String b,Uri c,String rep)
    {
        titles[0]= a;
        details[0]= b;
        imageUri = c;
        repo[0]=rep;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public Button button;

        public ViewHolder(View itemView)
        {
            super(itemView);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            button = (Button)itemView.findViewById(R.id.feedbutton);
            button.setOnClickListener(this);
        }
        public void onClick(View view)
        {
//            Toast.makeText(view.getContext(),"hai",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Repositories");
            builder.setMessage(repo[0]);
            builder.setNegativeButton("OK",new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog,int id)
                {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);

        Context context = viewHolder.itemImage.getContext();
        Picasso.with(context).load(imageUri).into(viewHolder.itemImage);
    }

    @Override
    public int getItemCount()
    {
        return titles.length;
    }
}
