<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm_but_im_legit_trying_to_do_it/MainActivity.java
package com.example.tradm_but_im_legit_trying_to_do_it;

=======
package com.example.tradm;
<<<<<<< HEAD:TRADM/app/src/main/java/com/example/tradm_but_im_legit_trying_to_do_it/MainActivity.java
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/MainActivity.java
=======
>>>>>>> parent of fbbf6db... Final commit:TRADM/app/src/main/java/com/example/tradm/MainActivity.java
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureCreateAccount();
        configureLogin();

    }

    private void configureCreateAccount() {
        Button create_acc = (Button) findViewById(R.id.create_acc);
        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
            }
        });

    }

    private void configureLogin() {
        TextView login = (TextView) findViewById(R.id.sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

    }
}
