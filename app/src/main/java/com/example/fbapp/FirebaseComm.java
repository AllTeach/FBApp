package com.example.fbapp;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import androidx.annotation.NonNull;

public class FirebaseComm
{
      private static final String TAG = " Firebase Util";
    private static FirebaseFirestore FIRESTORE;
    private static FirebaseAuth AUTH;




    public static FirebaseAuth getAuth() {
        if (AUTH == null)
            AUTH = FirebaseAuth.getInstance();
        return AUTH;
    }

    public static FirebaseFirestore getFisrestore() {
        if (FIRESTORE == null)
            FIRESTORE = FirebaseFirestore.getInstance();

        return FIRESTORE;
    }

    public static CollectionReference getCollectionReference(String collection)
    {
        return getFisrestore().collection(collection);
    }


    public static boolean isUserSignedIn() {

        return getAuth().getCurrentUser() != null;

    }

    public static String authUserEmail() {
        return getAuth().getCurrentUser().getEmail();


    }

    public void loginUser(String email, String password) {

        getAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: login success ");
                                } else {
                                    Log.d(TAG, "onComplete: login failed ");
                                }
                            }
                        });
    }
    public void createUser(String mail,String password)
    {
        getAuth().createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG, "onComplete:  register success");
                        }
                        else
                            Log.d(TAG, "onComplete: " + task.getException());
                    }
                });
    }



    // the following methods perform firestore transactions

    public void insertToFireStore(HashMap<String,Object>)








}
