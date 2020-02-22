package com.example.android.todolist;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<PagedList<TaskEntry>> tasks;

    public MainViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        buildPagedList(database);
    }

    private void buildPagedList(AppDatabase database) {
        DataSource.Factory<Integer, TaskEntry> factory = database.taskDao().loadAllTasks();
        LivePagedListBuilder builder = new LivePagedListBuilder(factory,20);
        tasks = builder.build();
    }

    public LiveData<PagedList<TaskEntry>> getTasks() {
        return tasks;
    }
}
