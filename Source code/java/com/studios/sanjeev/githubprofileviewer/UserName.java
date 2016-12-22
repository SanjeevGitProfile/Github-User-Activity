package com.studios.sanjeev.githubprofileviewer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserName extends AppCompatActivity
{

    String jsontext;
    String jsondata;
    String ImgUrl;

    String reposurl;
    String str="";
    String GITHUB_URL="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);

        Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        final TextView TV = (TextView) findViewById(R.id.textView3);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Please wait,it may take a while...",Toast.LENGTH_SHORT).show();
                GITHUB_URL="https://api.github.com/users/";
                String name = editText.getText().toString();
                GITHUB_URL = GITHUB_URL + name;
//                TV.setText(GITHUB_URL);
                try
                {

                    JsonObjectRequest jsObjRequest = new JsonObjectRequest
                            (Request.Method.GET, GITHUB_URL,(String) null, new Response.Listener<JSONObject>()
                            {
                                @Override
                                public void onResponse(JSONObject response)
                                {
//                                    TV.setText("Response: " + response.toString());
                                    try
                                    {
                                        JSONObject json = new JSONObject(response.toString());
                                        profile(json);
                                        repo(GITHUB_URL);
                                    }
                                    catch (JSONException e)
                                    {
                                        Log.i("response","error");
                                    }
                                }
                            }, new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {
                                    // TODO Auto-generated method stub
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(UserName.this);
                                    builder.setTitle("Hi!");
                                    builder.setMessage("User name doesn't exist\nEnter a valid one");
                                    builder.show();
                                }
                            });
                    VolleySingleton.getInstance(UserName.this).addToRequestQueue(jsObjRequest);
                }
                catch (Exception e)
                {
                    Log.i("Some","error");
                }
            }
        });
    }
    private void profile(JSONObject json)
    {
        try
        {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("JSON raw");
            jsontext = json.getString("name");
            jsontext= jsontext.concat("\nID: " + json.getString("id"));

            jsondata = "Details";
            if(!json.getString("email").equalsIgnoreCase("null"))
            {
                jsondata = jsondata.concat("\nMAIL ID: " + json.getString("email"));
            }
            if(!json.getString("bio").equalsIgnoreCase("null"))
            {
                jsondata = jsondata.concat("\nBIO: " + json.getString("bio"));
            }
            if(!json.getString("location").equalsIgnoreCase("null"))
            {
                jsondata = jsondata.concat("\nLOCATION: " + json.getString("location"));
            }
            if(!json.getString("company").equalsIgnoreCase("null"))
            {
                jsondata = jsondata.concat("\nCOMPANY: " + json.getString("company"));
            }
            jsondata = jsondata.concat("\nPublic repositories: " + json.getString("public_repos"));
            jsondata = jsondata.concat("\nCREATED AT: " + json.getString("created_at"));
            ImgUrl = json.getString("avatar_url");
//            reposurl = json.getString("repos_url");
//            str=reposurl;
//            builder.setMessage(jsontext);
//            builder.show();

        }
        catch (JSONException exc)
        {
            Log.i("exc","error");
        }

    }
    public void repo(String gt)
    {
        reposurl = gt + "/repos";
        try
        {
            JsonArrayRequest jsonArrayRequest= new JsonArrayRequest
                    (Request.Method.GET, reposurl,(String) null, new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            breakup(response);

                            Intent MyIntent = new Intent(UserName.this,Main2Activity.class);
                            MyIntent.putExtra("key",jsontext);
                            MyIntent.putExtra("key2",jsondata);
                            MyIntent.putExtra("key3",ImgUrl);
                            MyIntent.putExtra("key4",str);
                            startActivity(MyIntent);
                            overridePendingTransition(R.transition.slide_from_right, R.transition.slide_out_left);
                            finish();
                        }
                    }, new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            // TODO Auto-generated method stub
                        }
                    });

            VolleySingleton.getInstance(UserName.this).addToRequestQueue(jsonArrayRequest);
        }
        catch (Exception e)
        {
            Log.i("Some","error");
        }
    }
    public void breakup(JSONArray json)
    {
        try
        {
            str = "Sorry! Nothing found";
            JSONObject j = json.getJSONObject(0);
            if(!j.getString("name").equalsIgnoreCase("null"))
                str = "Project name:\n\t " + j.getString("name");
            if(!j.getString("description").equalsIgnoreCase("null"))
                str = str.concat("\nDescription:\n\t " + j.getString("description"));

            j = json.getJSONObject(1);
            if(!j.getString("name").equalsIgnoreCase("null"))
                str = str + "\n\nProject name:\n\t " + j.getString("name");
            if(!j.getString("description").equalsIgnoreCase("null"))
                str = str.concat("\nDescription:\n\t " + j.getString("description"));
        }
        catch(JSONException e)
        {
            Log.i("re","as");
        }
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
        Intent home_intent = new Intent(UserName.this,MainActivity.class);
        startActivity(home_intent);
        overridePendingTransition(R.transition.slide_from_left, R.transition.slide_out_right);
        finish();
    }*/
}
