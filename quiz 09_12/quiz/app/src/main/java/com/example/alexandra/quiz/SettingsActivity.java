package com.example.alexandra.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiUsers;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKUsersArray;

import org.json.JSONObject;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sPref;
    final String SAVED_ISLOGGD = "false";
    final String SAVED_VKbutton = "false";
    final String SAVED_VKOutButton = "false";

    private String[] scope = new String[]{VKScope.WALL, VKScope.FRIENDS};
    private static  VKAccessToken Token=null;
    Boolean isLogged;
    Button VKbutton;
    Button VKOutButton;

    Button offButton;

    Spinner spinner;
    SharedPreferences settings;

    private String Load(String Patch, String Where)
    {
        sPref = this.getSharedPreferences(Patch, MODE_PRIVATE);
        String savedText = sPref.getString(Where, "");

        return savedText;
    }

    private void Save(String Patch, String Where, String In)
    {
        sPref = this.getSharedPreferences(Patch, MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(Where, In);
        ed.commit();
    }

    public static VKAccessToken getToken()

    {
        return Token;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        VKbutton = (Button) findViewById(R.id.vkButton);
        VKbutton.setOnClickListener(this);



        VKOutButton = (Button) findViewById(R.id.vkButtonOut);
        VKOutButton.setOnClickListener(this);

        if (Load("Settings", SAVED_ISLOGGD)=="true") {
            VKbutton.setVisibility(View.INVISIBLE);
            VKOutButton.setVisibility(View.VISIBLE);
            isLogged = true;
        }
        else {
            VKbutton.setVisibility(View.VISIBLE);
            VKOutButton.setVisibility(View.INVISIBLE);
            isLogged = false;
        }



        offButton = (Button) findViewById(R.id.OffButton);
        offButton.setOnClickListener(this);


        settings = getSharedPreferences("Color", MODE_PRIVATE);
        spinner = (Spinner) findViewById(R.id.spinner1);


        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        int id = adapter.getPosition(settings.getString("Color","не определено"));
        spinner.setSelection(id);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.ColorList);

                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putString("Color", choose[selectedItemPosition]);
                prefEditor.apply();
                switch (choose[selectedItemPosition]){
                    case "Dark blue":
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                        break;
                    case "Black":
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.vk_black)));
                        break;

                    case "Pink":
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
                        break;
                    case "Grey":
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.vk_share_link_color)));
                        break;

                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.vkButton: {

                VKSdk.login(this, scope);
                isLogged=true;
                VKbutton.setVisibility(View.INVISIBLE);
                VKOutButton.setVisibility(View.VISIBLE);//Ты белоусовв сдала ? да, на 4. ббля с этой хуйней моей не работает как надо а что не так ?


                Save("Settings", SAVED_ISLOGGD, "true");
                Toast.makeText(SettingsActivity.this, "you are logged in VK!", Toast.LENGTH_SHORT).show();

                break;
            }
            case R.id.vkButtonOut:
                Toast.makeText(SettingsActivity.this, "you are logout in VK!", Toast.LENGTH_SHORT).show();
                VKSdk.logout();
                isLogged=false;
                VKbutton.setVisibility(View.VISIBLE);
                VKOutButton.setVisibility(View.INVISIBLE);
                Save("Settings", SAVED_ISLOGGD, "false");

                break;



            //    spref = getSharedPreferences("Color", MODE_PRIVATE);
             //   SharedPreferences.Editor ed = spref.edit();
             //   getSupportActionBar().hide();


            case R.id.OffButton:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;



        }
        }




        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
                @Override
                public void onResult(VKAccessToken res) {
                    //Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();
// Пользователь успешно авторизовался
                    Token=res;

                   // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(VKError error) {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
                }
            })) {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

}
