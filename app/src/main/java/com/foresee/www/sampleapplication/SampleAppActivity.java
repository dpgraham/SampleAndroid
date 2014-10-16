package com.foresee.www.sampleapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;


public class SampleAppActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_app);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_create_goal:
                openCreateGoal();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Open the goal creation activity
     * @return
     */
    public boolean openCreateGoal(){
        Intent intent = new Intent(this, GoalForm.class);
        startActivity(intent);
        return true;
    }

    /**
     * Open the app settings
     * @return
     */
    public boolean openSettings(){
        return true;
    }


}
