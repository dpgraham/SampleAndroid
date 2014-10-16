package com.foresee.www.sampleapplication.models;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ModelTest extends ApplicationTestCase<Application> {

    public ModelTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_() throws Exception{
        GoalModel mod = new GoalModel(this.getContext(), "jog (minutes)", GoalModel.MoreOrLess.MORE_THAN, 300);
        mod.Save();
        ArrayList<Field> mod2 = mod.Get(mod.id);
    }
}