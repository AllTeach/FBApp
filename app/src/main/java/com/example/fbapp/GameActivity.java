package com.example.fbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class GameActivity extends AppCompatActivity implements FirebaseComm.FireStoreResult{
    private FirebaseComm firebaseComm;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initViews();
        // create FBComm object and set interface for results
        firebaseComm = new FirebaseComm();
        firebaseComm.setFireStoreResult(this);
    }

    private void initViews() {
        textView = findViewById(R.id.tvGameMove);
    }


    // Can move logic to controller
    public void startOrJoinGame(View view)
    {
        // try to join gameroom - if non exist - open a new gameroom
        firebaseComm.getDocumentWhereEqualWithLimit("gameRoom","status","created",1);

    }


    // overriding interface methods
    @Override
    public void elementsReturned(ArrayList<Map<String, Object>> arr) {

        // return here from join game
        // arr size should be one if there is a room available
        // join room
        if(arr.size() >0 )
        {
            GameRoom gr = new GameRoom(arr.get(0));
            // room status should be created
            // add user email as joined
            gr.setStatus("joined");
            gr.setNameOther(FirebaseComm.authUserEmail());

            // update game room in firestore and start listening
            // for changes
            firebaseComm.addToFireStoreDocument("gameRoom",gr.getNameOwner(),gr.gameToHashMap());
            textView.setText(gr.toString());
            Toast.makeText(this,"joined game wait for other player",Toast.LENGTH_SHORT).show();

            // listen for changes in this room
            firebaseComm.listenToDocumentChanges(this,"gameRoom",gr.getNameOwner());

        }
        else
        {
            // public GameRoom(String nameOwner, String nameOther, String status, int row, int col, String currPlayer) {
            // this means we created a new game

            GameRoom gr = new GameRoom();
            String mail = FirebaseComm.authUserEmail();
            gr.setNameOwner(mail);
            gr.setStatus(Utils.GAME_CREATED);
            firebaseComm.addToFireStoreDocument("gameRoom",mail,gr.gameToHashMap());

            // listen for changes
            firebaseComm.listenToDocumentChanges(this,"gameRoom",gr.getNameOwner());

        }

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

        String mail = FirebaseComm.authUserEmail();
        // here we get changes in the document
        GameRoom gr = new GameRoom(map);
        if(gr.getStatus().equals(Utils.GAME_STARTED))
        {
            // check if this is my turn
            Toast.makeText(this,"game started",Toast.LENGTH_SHORT).show();
            textView.setText(gr.toString());
        }
        else if(gr.getStatus().equals(Utils.GAME_JOINED))
        {


            // we can start - check what is the status
            // if JOINED -> check whether user is the owner
            // play and change status to start
            if(gr.getNameOwner().equals(mail))
            {
                Toast.makeText(this,"game started!!",Toast.LENGTH_SHORT).show();

                textView.setText(gr.toString());
                gr.setStatus(Utils.GAME_STARTED);
                firebaseComm.addToFireStoreDocument("gameRoom",gr.getNameOwner(),gr.gameToHashMap());

            }


        }
    }
}