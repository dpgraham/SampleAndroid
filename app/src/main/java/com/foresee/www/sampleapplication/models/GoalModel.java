package com.foresee.www.sampleapplication.models;

import android.content.ContentValues;
import android.content.Context;
import android.provider.BaseColumns;

import java.util.ArrayList;

public final class GoalModel extends Model {

    public enum MoreOrLess {
        MORE_THAN,
        LESS_THAN
    }

    /**
     * Constructor.
     */
    public GoalModel(Context application_context, String name, MoreOrLess more_or_less, float more_or_less_amount){

        // Call the super constructor
        super(application_context);

        // Get the name of the model
        this.model_name = "GOALS";

        // Add fields to the model
        this.fields = new ArrayList<Field>();
        this.fields.add(new Field("name", "text", name));
        this.fields.add(new Field("more_or_less", "text", more_or_less.toString()));
        this.fields.add(new Field("more_or_less_amount", "text", Float.toString(more_or_less_amount)));

        this.CreateTable();
    }

    public GoalModel(Context application_context, ArrayList<Field> fields){
        super(application_context, fields);
        this.model_name = "GOALS";
        this.CreateTable();
    }

    /**
     *
     * @return
     */
    public static boolean New(){
        return false;
    }


    public boolean Delete(){
        return false;
    }


    /**
     *
     * @return
     */
    public boolean IsValid(){
        return false;
    }
}
