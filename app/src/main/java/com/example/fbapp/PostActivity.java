package com.example.fbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        firebaseComm.addToFireStoreDocument("posts",p.getOwnerMail(),p.postToHashMap());

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
        // convert the hashmap to post
        // using the Hashmap as a generic collection to pass data
        // each item - Post, User, Room, Etc.. will implement
        // two methods ->
        // 1) Convert Object to Map :   postToMap / userToMap etc
        // 2) Convert Map to Specific Object mapToPost / mapToUser etc

        Post p = new Post(map);
        Toast.makeText(this,"changed " + p.getTitle() + "," + p.getBody() + "," + p.getOwnerMail(),Toast.LENGTH_LONG).show();
        //update the UI HERE
    }
}