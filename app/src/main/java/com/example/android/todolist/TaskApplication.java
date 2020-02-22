package com.example.android.todolist;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.android.todolist.utils.ThemeHelper;
import com.example.android.todolist.work.TaskWorker;

import java.util.concurrent.TimeUnit;

/**
 * The application class
 */
public class TaskApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpTaskWork();
        setAppTheme();
    }

    private void setAppTheme() {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        String themePref = sharedPreferences.getString(
                getString(R.string.pref_night_mode_key), ThemeHelper.DEFAULT_MODE);
        ThemeHelper.applyTheme(themePref);
    }

    private void setUpTaskWork() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        PeriodicWorkRequest repeatingMovieRefreshRequest =
                new PeriodicWorkRequest.Builder(TaskWorker.class, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork(
                TaskWorker.TASK_WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingMovieRefreshRequest
        );
    }
}

