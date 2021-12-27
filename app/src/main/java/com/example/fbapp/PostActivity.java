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

public class PostActivity extends AppCompatActivity {
    private EditText etTitle,etBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initViews();

    }
    private void initViews() {
        etTitle = findViewById(R.id.etPostName);
        etBody = findViewById(R.id.etPostData);

    }

    public void postToFirebase(View view)
    {
        String title = etTitle.getText().toString();
        String body = etBody.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String mail = auth.getCurrentUser().getEmail();

        // create post
        Post p = new Post(title,body,mail);


        // reference , collection : POSTS -> document
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("posts").document();

        docRef.set(p)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PostActivity.this,"post set in firestore",Toast.LENGTH_SHORT).show();
                    }
                });

        docRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                {
                    Toast.makeText(PostActivity.this,"error " ,Toast.LENGTH_SHORT).show();
                    return;

                }
                if(value!=null)
                {
                    Post p = value.toObject(Post.class);
                    etTitle.setText(p.getTitle());
                    etBody.setText(p.getBody());
                }

            }
        });












    }

    public void gotoAllPostsActivityUsingFirebaseUI(View view) {
    }
}