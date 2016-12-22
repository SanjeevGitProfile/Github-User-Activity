package com.studios.sanjeev.githubprofileviewer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class Main2Activity extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    Bitmap bp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String name = getIntent().getExtras().getString("key");
        String data = getIntent().getExtras().getString("key2");
        String Img = getIntent().getExtras().getString("key3");
        String repo= getIntent().getExtras().getString("key4");
        Uri imguri= Uri.parse(Img);

/*        Button button = (Button)findViewById(R.id.feedbutton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("Repositories");
                builder.setMessage("Hi");
                builder.show();
            }
        });
*/
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(Main2Activity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(name,data,imguri,repo);
        recyclerView.setAdapter(adapter);

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        if(menuItem.getItemId()==R.id.about)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Github User Profiles 1.0");
            builder.setMessage("Developer: Sanjeev kumar N\n\nsanjeevitcit@gmail.com");
            builder.setPositiveButton("Close", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.cancel();
                }
            });
            builder.show();
        }
        return false;
    }
/*    public void onBackPressed()
    {
        Intent home_intent = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(home_intent);
        overridePendingTransition(R.transition.slide_from_left, R.transition.slide_out_right);
        finish();
    }
*/
}
