package com.example.fbapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements FirebaseComm.FireStoreResult {


    private EditText etTitle,etBody;
    private FirebaseComm firebaseComm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();
        firebaseComm = new FirebaseComm();
        firebaseComm.setFireStoreResult(this);

    }
    private void initViews() {
        etTitle = findViewById(R.id.etPostName);
        etBody = findViewById(R.id.etPostData);

    }


    public void postToFirebase(View view)
    {
        String title = etTitle.getText().toString();
        String body = etBody.getText().toString();

        String mail = FirebaseComm.authUserEmail();
        // create post
        Post p = new Post(title,body,mail);
        // a few examples
        // add a post as a document to the collection
     //   firebaseComm.addToFireStoreCollection("posts",p.postToHasMap());

        // here we map a specific post as single document for a user
        // can be used to open a Game Room in games
        firebaseComm.addToFireStoreDocument("posts",p.getOwnerMail(),p.postToHasMap());

        // listen for updates in the collection
        //firebaseComm.listenToCollectionChanges(this,"posts");

        // listen for updates on a specific document
        // we pass the acitivity as reference for unregistering when exits
        firebaseComm.listenToDocumentChanges(this,"posts",p.getOwnerMail());

        firebaseComm.getAllDocumentsInCollection("posts");
        firebaseComm.getDocumentsOrderedByFieldWithLimit("posts","body",2);
    }

    public void gotoAllPostsActivityUsingFirebaseUI(View view) {
    }

    // methods notifying on firebase actions completed
    @Override
    public void elementsReturned(ArrayList<Map<String, Object>> arr) {

    }

    @Override
    public void elementsChanged(Map<String, Object> map, int oldIndex, int newIndex) {

    }

    @Override
    public void elementRemoved(int index) {

    }

    @Override
    public void elementAdded(Map<String, Object> map, int index) {

    }

    @Override
    public void changedElement(Map<String, Object> map) {
        Post p = new Post(map);
        Toast.makeText(this,"changed " + p.getTitle() + "," + p.getBody() + "," + p.getOwnerMail(),Toast.LENGTH_LONG).show();
    }
}