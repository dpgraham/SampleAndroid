package com.foresee.www.sampleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.foresee.www.sampleapplication.models.LogGoalModel;


public class GoalNewActivity extends FormActivity {

    private EditText goal_name;
    private Spinner spinner;
    private EditText more_than_or_less_than_amount;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);

        this.activateInputs();
        this.bindInputs();
    }

    @Override
    public void activateInputs(){
        // Get references to the input components
        this.submit = (Button) findViewById(R.id.submit);
        this.spinner = (Spinner) findViewById(R.id.more_than_or_less_than);
        this.goal_name = (EditText) findViewById(R.id.goal_name);
        this.more_than_or_less_than_amount = (EditText) findViewById(R.id.more_than_or_less_than_amount);

        // Activate the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.more_than_or_less_than, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Bind input events to their appropriate listeners
     */
    @Override
    public void bindInputs() {

        this.submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                submitData();
            }
        });
    };

    /**
     * Submit the form data
     */
    @Override
    public void submitData() {

        // Extract the values from the form
        String name = this.goal_name.getText().toString();
        LogGoalModel.MoreOrLess more_or_less = this.spinner.getSelectedItemId()==0 ? LogGoalModel.MoreOrLess.MORE_THAN : LogGoalModel.MoreOrLess.LESS_THAN;
        String amount = this.more_than_or_less_than_amount.getText().toString();

        // Create the model
        LogGoalModel model = new LogGoalModel(getApplicationContext(), name, more_or_less, name.isEmpty() ? 0 : Float.valueOf(amount));

        // Save the data
        try {
            model.Save();
            this.openNewActivity(model);
        } catch(Exception e){
            e.getMessage();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_goal, menu);
        return true;
    }

    /**
     * Open the activity after the form is submitted
     * @param model
     */
    private void openNewActivity(LogGoalModel model){
        Intent intent = new Intent(this, GoalShowActivity.class);
        intent.putExtra("goal_id", model.id);
        startActivity(intent);
    }
}
