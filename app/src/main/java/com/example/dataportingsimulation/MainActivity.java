/**
 *  Notes:
 *  >   This is a simple application to demonstrate handling of data
 *      between Frontend and Backend of an App. Database and(or) Room can be
 *      utilised for more functionality. It is only a TEST PROJECT and its
 *      source code SHOULD NOT BE USED for critical applications. In any case
 *      @author is NOT RESPONSIBLE for bugs, crashes, or data loss that might
 *      occour by re-purposing or re-using this project. Feel free to study/use
 *      the source code whatsoever.
 *
 *  >   ViewBinding object is used to avoid unnecessary NullPointerExceptions.
 *
 *  >   Bundle is implemented to prevent data loss while app is running.
 *
 *  >   The code is un-optimised for multiple layouts.
 *
 */

package com.example.dataportingsimulation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dataportingsimulation.databinding.ActivityMainBinding;
import java.util.Objects;


public class MainActivity extends BackendClass {

    // View Binding object of MainActivity
    private ActivityMainBinding binding;

    // Other variables
    String string = "";

    /**
     * This will save the current data into a bundle during interruptions
     * */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("currentdata", binding.myEditField.getText().toString());
        outState.putString("saveddata", super.getStoredText());
    }

    /**
     *  This will retrieve data from bundle and restore state of the app after interruptions
     * */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        binding.myEditField.setText(savedInstanceState.getString("currentdata"));
        super.setStoredText(savedInstanceState.getString("saveddata"));
    }

    /**
     *  This function is called when MainActivity is created.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onPushButtonPress();
        onPullButtonPress();
    }

    /**
     *  OnClickListener for Push Button
     */
    private void onPushButtonPress() {
        binding.pushButton.setOnClickListener(v -> storeData());
    }

    /**
     *  OnClickListener for Pull Button
     */
    private void onPullButtonPress() {
        binding.pullButton.setOnClickListener(v -> retrieveData());
    }

    /**
    *   Controller function to pass data to backend
    */
    private void storeData(){
        try{
            string = Objects.requireNonNull(binding.myEditField.getText()).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(string.equals("")){
            Toast.makeText(getBaseContext(), R.string.msg_no_input, Toast.LENGTH_SHORT).show();
            return;
        }

        super.setStoredText(string);
        Toast.makeText(getBaseContext(), R.string.msg_success, Toast.LENGTH_SHORT).show();
        binding.myEditField.setText(null);
    }

    /**
     *  Controller function to retrieve data from backend
     * */
    private void retrieveData(){
        string = super.getStoredText();

        if(string == null){
            Toast.makeText(getBaseContext(), R.string.msg_empty_db, Toast.LENGTH_SHORT).show();
        }else{
            binding.myTextView.setText(string);
            Toast.makeText(getBaseContext(), R.string.msg_success, Toast.LENGTH_SHORT).show();
            binding.myEditField.setText(null);
        }
    }


}