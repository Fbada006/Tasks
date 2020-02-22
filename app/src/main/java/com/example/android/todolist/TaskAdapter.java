/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.todolist.database.TaskEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * This TaskAdapter creates and binds ViewHolders, that hold the description and priority of a task,
 * to a RecyclerView to efficiently display data.
 */
public class TaskAdapter extends PagedListAdapter<TaskEntry, TaskAdapter.TaskViewHolder> {

    private static final DiffUtil.ItemCallback<TaskEntry> diffUtil = new DiffUtil.ItemCallback<TaskEntry>() {
        @Override
        public boolean areItemsTheSame(@NonNull TaskEntry oldItem, @NonNull TaskEntry newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TaskEntry oldItem, @NonNull TaskEntry newItem) {
            return oldItem == newItem;
        }
    };

    private static final String DATE_FORMAT = "dd/MM/yyy";

    final private ItemClickListener mItemClickListener;
    private Context mContext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());


    TaskAdapter(Context context, ItemClickListener listener) {
        super(diffUtil);
        mContext = context;
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, parent, false);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        TaskEntry taskEntry = getItem(position);
        assert taskEntry != null;
        String description = taskEntry.getDescription();
        int priority = taskEntry.getPriority();
        String updatedAt = dateFormat.format(taskEntry.getUpdatedAt());

        holder.taskDescriptionView.setText(description);
        holder.updatedAtView.setText(updatedAt);

        String priorityString = "" + priority;
        holder.priorityView.setText(priorityString);

        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }

    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView taskDescriptionView;
        TextView updatedAtView;
        TextView priorityView;

        TaskViewHolder(View itemView) {
            super(itemView);

            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
            priorityView = itemView.findViewById(R.id.priorityTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final int adapterPosition = getAdapterPosition();
            int elementId = Objects.requireNonNull(getItem(adapterPosition)).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}