package com.foresee.www.sampleapplication.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created by Dan.Graham on 10/14/2014.
 */
public class Model extends DBHelper {

    protected String model_name; // Required. Uniquely identifies the data.
    protected ArrayList<Field> fields;
    protected Context context;
    public long id; // ID of the specific instance

    public Model(Context context){
        super(context);
        this.context = context;
    }

    public Model(Context context, ArrayList<Field> fields){
        super(context);
        this.fields = fields;
        this.context = context;
    }

    protected void CreateTable(){
        String query = "CREATE TABLE IF NOT EXISTS " + this.model_name + "(";
        for(Field field : this.fields){
            query += field.name + " " + field.type + ",";
        }
        query += " _ID Integer PRIMARY KEY);";
        this.getWritableDatabase().execSQL(query);
    }

    /**
     * Commit changes to database
     * @return
     */
    public final long Save() throws Exception{
        ContentValues values = new ContentValues();
        for(Field field : this.fields){
            if(field.value == null){
                throw new Exception("Value not set on: " + field.name);
            }
            values.put(field.name, field.value);
        }

        this.id = this.getWritableDatabase().insert(this.model_name, null, values);
        return this.id;
    }

    /**
     * Delete this model from the database
     * @return
     */
    public boolean Delete(){
        Model.Delete(this.id);
        return false;
    }




    /**
     * Get an instance of this model from the database by ID
     * @param id
     * @return
     */
    public ArrayList<Field> Get(long id){

        // Parse the columns as a string array
        String[] columns = new String[this.fields.size()];
        int i = 0;
        for(Field field: this.fields){
            columns[i++] = field.name;
        }

        // Select everything that matches the criteria
        Cursor entry = this.getWritableDatabase().query(
            this.model_name,
            null,
            "_ID=" + id,
            new String[]{},
            "",
            "",
            "",
            "1"
        );

        // Parse out the data as 'Field' objects
        entry.moveToFirst();
        ArrayList<Field> res = new ArrayList<Field>();
        int column_count = entry.getColumnCount() - 1; // All columns, minus the ID
        for(i=0; i<column_count; i++){
            Field field = new Field(this.fields.get(i).name, this.fields.get(i).type, entry.getString(i));
            res.add(field);
        }

        return res;
    }

    public String getValue(String name) throws Exception{
        for(Field field : this.fields){
            if(field.name == name){
                return field.value;
            }
        }

        throw new Exception("Field not found: " + name);
    }

    /**
     * Delete model from the database by ID
     * @param id
     * @return
     */
    public final static boolean Delete(long id){
        return false;
    }

    /**
     * Drop the table
     */
    public final void Destroy(){
        String query = "DROP TABLE " + this.model_name + ";";
        this.getWritableDatabase().execSQL(query);
    }

    /**
     * Are the provided
     * @return
     */
    public boolean IsValid(){
        return true;
    };

    /**
     * Once the database is ready
     * @param db
     */
    public void onCreate(SQLiteDatabase db){

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
