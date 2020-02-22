package com.example.android.todolist.settings;

import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.android.todolist.R;
import com.example.android.todolist.utils.ThemeHelper;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);

        ListPreference themePreference = findPreference(getString(R.string.pref_night_mode_key));
        if (themePreference != null) {
            themePreference.setOnPreferenceChangeListener(
                    (preference, newValue) -> {
                        String themeOption = (String) newValue;
                        ThemeHelper.applyTheme(themeOption);
                        return true;
                    });
        }
    }
}
