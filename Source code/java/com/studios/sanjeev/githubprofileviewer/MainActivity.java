package com.studios.sanjeev.githubprofileviewer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView TV= (TextView)findViewById(R.id.textView2);
        String note="Note:\n\n 1. Provide a valid Github User name \n\n 2. You can view all of the public information\n\n";
        TV.setText(note);
        Button button=(Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, UserName.class));
//                overridePendingTransition(R.transition.slide_from_right, R.transition.slide_out_left);
//                finish();
            }
        });
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
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quit");
        builder.setNegativeButton("Ok",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog,int choice)
            {
                finish();
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
