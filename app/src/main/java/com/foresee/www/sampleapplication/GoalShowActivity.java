package com.foresee.www.sampleapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.foresee.www.sampleapplication.models.Field;
import com.foresee.www.sampleapplication.models.LogGoalModel;

import java.util.ArrayList;


public class GoalShowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        long id = extras.getLong("goal_id");
        setContentView(R.layout.activity_goal_show);
        ArrayList<Field> data = (new LogGoalModel(getApplicationContext())).Get(id);

        this.render(data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.goal_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void render(ArrayList<Field> fields){
        for(Field field : fields){
            String name = field.name;
            if(name=="name"){
                ((TextView) findViewById(R.id.goal_show_name)).setText(field.value);
            } else if(name=="more_or_less"){
                ((TextView) findViewById(R.id.goal_show_more_or_less)).setText(field.value);
            } else if(name=="more_or_less_amount"){
                ((TextView) findViewById(R.id.goal_show_more_or_less_amount)).setText(field.value);
            }
        }
    }
}
