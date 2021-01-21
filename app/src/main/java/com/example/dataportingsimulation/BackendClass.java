/**
 *  Backend Script to manipulate user input
 *
 * */

package com.example.dataportingsimulation;

import androidx.appcompat.app.AppCompatActivity;

public class BackendClass extends AppCompatActivity {
    // Variable to store data
    private String storedText;

    // Default constructor for BackendClass
    public BackendClass(){
        this.storedText = null;
    }

    /**
     *  Getter & Setter functions to handle user data
     * */
    public String getStoredText() {
        return this.storedText;
    }

    public void setStoredText(String storedText) {
        this.storedText = storedText;
    }


}
