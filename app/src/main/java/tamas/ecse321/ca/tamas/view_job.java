package tamas.ecse321.ca.tamas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import tamas.ecse321.ca.tamas.controller.JobController;
import tamas.ecse321.ca.tamas.util.PersistenceChangeObserver;

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

    JobController jobController;
    LinkedList<String> jobList;

    PersistenceChangeObserver persistenceChangeObserver;

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
        String preRefreshItem[]=new String[]{"Hello Applicant!","Please Refresh One or two times to See list of Jobs Available"};
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
                jobController=new JobController();
                jobList=jobController.getAllJobPosting();
                if(jobList!=null){
                    Iterator<String> jobIterator=jobList.iterator();
                    while (jobIterator.hasNext()){
                        adapter.add(jobIterator.next());
                    }
                }else{
                    feedback_textview.setText("No jobs are avialble at the moment, or unable to fetch job from database at the moment");
                }
                adapter.notifyDataSetChanged();
            }
        });



        select_job_button=(Button)findViewById(R.id.select_job_button);
        select_job_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

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


        Thread observerAcitivty =new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    if (!persistenceChangeObserver.observe(adapter)) {
                        jobList.clear();
                        adapter.clear();
                        adapter.add("New Job has been posted! They are updated automatically!");
                        jobList = jobController.getAllJobPosting();
                        if (jobList != null) {
                            Iterator<String> jobIterator = jobList.iterator();
                            while (jobIterator.hasNext()) {
                                adapter.add(jobIterator.next());
                            }
                        } else {
                            feedback_textview.setText("No jobs are avialble at the moment, or unable to fetch job from database at the moment");
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("Observer thread failed!");
                    e.printStackTrace();
                }
            }
        });

        observerAcitivty.start();
    }


}
