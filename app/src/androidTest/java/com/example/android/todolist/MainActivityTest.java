package com.example.android.todolist;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android.todolist.addTask.AddTaskActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void beforeActivityLaunched() {
        Intents.init();
    }

    @After
    public void afterActivityFinished() {
        Intents.release();
    }

    @Test
    public void clickSendButtonOpensSecondActivity() {
        onView(withId(R.id.add_new_task_fab)).perform(click());
        intended(hasComponent(AddTaskActivity.class.getName()));
    }
}