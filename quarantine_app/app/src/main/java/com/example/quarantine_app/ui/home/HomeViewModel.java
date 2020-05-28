package com.example.quarantine_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Quarantine Application - Showing Global and countries data regarding Covid19");
    }

    public LiveData<String> getText() {
        return mText;
    }
}