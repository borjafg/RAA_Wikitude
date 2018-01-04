package com.example.mm.raa;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private ArchitectView architectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_architect_view);
        this.architectView  =  (ArchitectView) this.findViewById(R.id.architectView);
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("Ma8l9AU+QZ2MMeL6c5INK+lqoAm7xVxhwKOm5lxHf38ReJs4/8ArgTx5pOKU9ma7Efab3Wnqo/ivGP0/RUulzgo/beXcEtebvx2UN5F0seRIDwoirnEhSzq/ILpcqst/xoPvtkZRFhWb/XlOs3Q5wJNWwu4AqPfKumRnMqYwn5xTYWx0ZWRfXyMZICYv2qSJsp+FjhBvZESVE64JO8eEgn+Ei0HQFB7+avI6B523rsnNjbE/vVkeatmWxhhPCGkYJ4HDwTwgIRtUTiaQmIef4FKsAGYLJgqDsbwdSZK9cwJGq/KzKWULkHFnQIr4m3BMLAyLNT/kKNzW5oOKsDX6/jx+PuOHDwfc9CY/DelLa9jMYtNvSuE0PkCLreXPDTWMUajTHmpc/HZ8rts2uRdGW6kCVzf4q7lJqw8aXkl+PdZeV8kB42FqtR00F2x8mwUQ5+6SvhYQC1CG8e/e/lU/vtPAX+b4A90eBfmLFCXQKAbfbmdTSkf1FGaBV6wtc92e1X/InhBJRofWTj/HLblK6Xwyt1HOGZ6WPdhPOHFV/RZRC4slKIN5JcUn1Ex9pfBgcgIjjP56ikI2IYKIUrSV/32gdAaOthSm8qEOzwVWCeX0PIIp7mZLeXAHJr+fQ2Nmegpqg9qr6NRnV/nkUJ0m5LAoOa7RX/Q45PAAvspVNu0=");
        checkPermission();
        this.architectView.onCreate(config);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle  savedInstanceState){
        super.onPostCreate(savedInstanceState);
        if(this.architectView !=null){
            architectView.onPostCreate();
        }
        try{
            this.architectView.load("ImageOnTarget/index.html");
        } catch(IOException e){
            e.printStackTrace();
        }
        super.onPostCreate(savedInstanceState);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (this.architectView != null)
            this.architectView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this.architectView!=null)
            this.architectView.onResume();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        if(this.architectView!=null)
            this.architectView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        if(this.architectView!=null)
            this.architectView.onDestroy();
        super.onDestroy();
    }

    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void checkPermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();

        } else {

            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.CAMERA);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[] {Manifest.permission.CAMERA},
                        REQUEST_CODE_ASK_PERMISSIONS);

                Toast.makeText(this, "Requesting permissions", Toast.LENGTH_LONG).show();

            }else if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
            }

        }

        return;
    }

}
