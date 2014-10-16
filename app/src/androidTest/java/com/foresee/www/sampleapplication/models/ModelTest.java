package com.foresee.www.sampleapplication.models;

import android.app.Application;
import android.test.ApplicationTestCase;

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

        // Destroy what's currently there
        GoalModel mod = new GoalModel(this.getContext(), "jog (minutes)", GoalModel.MoreOrLess.MORE_THAN, 300);
        mod.Destroy();

        // Create a new model and save it
        mod = new GoalModel(this.getContext(), "jog (minutes)", GoalModel.MoreOrLess.MORE_THAN, 400);
        mod.Save();

        // Get the recently saved value and check that it's correct
        GoalModel getter = new GoalModel(this.getContext(), mod.Get(mod.id));
        assertEquals(getter.getValue("name"), "jog (minutes)");
        assertEquals(getter.getValue("more_or_less"), GoalModel.MoreOrLess.MORE_THAN.toString());
        assertEquals(getter.getValue("more_or_less_amount"), "400.0");
    }
}