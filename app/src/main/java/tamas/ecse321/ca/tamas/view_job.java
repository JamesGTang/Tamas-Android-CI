package tamas.ecse321.ca.tamas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class view_job extends AppCompatActivity {

    Button refresh_data_button;
    Button select_job_button;
    ListView listview;
    TextView feedback_textview;
    String selectedItem;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    private String user_id;
    private String pass_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);

        Bundle infoFromSinginView=getIntent().getExtras();

        if(infoFromSinginView!=null){
            user_id=infoFromSinginView.getString("id");
            pass_word=infoFromSinginView.getString("password");
        }else{
            user_id=null;
            pass_word=null;
        }

        listview=(ListView)findViewById(R.id.list_of_job);
        String preRefreshItem[]=new String[]{"Hello","Please Refresh One or two times to See list of Jobs Available"};
        listItems=new ArrayList<String>();
        feedback_textview=(TextView) findViewById(R.id.feedback_textview);
        for(int i=0;i<preRefreshItem.length;i++){
            listItems.add(preRefreshItem[i]);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        listview.setAdapter(adapter);

        refresh_data_button=(Button)findViewById(R.id.refresh_data_button);
        refresh_data_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                adapter.clear();
                getAllJobPosting();
                adapter.notifyDataSetChanged();

            }
        });

        select_job_button=(Button)findViewById(R.id.select_job_button);
        select_job_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Todo handle null string non selected exception
                setContentView(R.layout.activity_apply_for_job);
                Intent intent=new Intent(view_job.this,apply_for_job.class);
                intent.putExtra("selectedItem",selectedItem);
                intent.putExtra("id",user_id);
                intent.putExtra("password",pass_word);
                startActivity(intent);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem=listview.getItemAtPosition(position).toString();
                feedback_textview.setText("You selected: "+selectedItem);
            }
        });
    }

    public int getAllJobPosting(){
        // clear all exsiting entries
        adapter.clear();

        RequestQueue queue= Volley.newRequestQueue(this);
        String urlForID="http://www.jamesgtang.com/tamas/jobPostingService.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET,urlForID,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    // use JSON Parser to tokenize data
                    JSONArray listing=new JSONArray(response);
                    for(int i=0;i<listing.length();i++){
                        JSONObject c=listing.getJSONObject(i);
                        String instructor_name=c.getString("INSTRUCTOR_NAME");
                        String course_name=c.getString("COURSE");
                        String post_id=c.getString("POST_ID");
                        String hour=c.getString("HOUR");
                        adapter.add("Post_ID: "+post_id+" Course: "+course_name+ " Hour: "+hour
                                +" Instructor_Name: "+instructor_name);

                        HashMap<String, String> map=new HashMap<String,String>();
                        map.put("INSTRUCTOR_NAME",instructor_name);
                        map.put("COURSE_NAME",course_name);
                        map.put("POST_ID",post_id);
                        map.put("HOUR",hour);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                // handle response here
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
        return 0;

    }
}
