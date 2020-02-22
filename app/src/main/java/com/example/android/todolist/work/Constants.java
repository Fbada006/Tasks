package com.example.android.todolist.work;

class Constants {
    // Name of Notification Channel for verbose notifications of background work
    static final CharSequence VERBOSE_NOTIFICATION_CHANNEL_NAME =
            "Task notification";
    static String VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
            "Shows notifications when the task is ready";
    static final CharSequence NOTIFICATION_TITLE = "Task ready!";
    static final CharSequence NOTIFICATION_MESSAGE = "Go ahead and check out your task!";
    static final String CHANNEL_ID = "VERBOSE_NOTIFICATION";
    static final int NOTIFICATION_ID = 1;
}
