package com.example.android.todolist.work;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * The worker to send notifications after the task is ready
 */
public class TaskWorker extends Worker {

    public static final String TASK_WORK_NAME = "TaskWorker";

    /**
     * @param context      is context
     * @param workerParams are the params for the worker
     */
    public TaskWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            WorkerUtils.makeStatusNotification(getApplicationContext());
            return Result.success();
        } catch (Exception exception) {
            return Result.retry();
        }
    }
}
