package me.jewel.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {
    /** Called when the activity is first created. */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        //imageView=(ImageView)findViewById(R.id.imageWelcome);
        //startButton=(Button)findViewById(R.id.startButton);

    }
	
	 public void onClick(View v) {
	        switch (v.getId()){
	            case R.id.button:

	                Intent i=new Intent(this,StartActivity.class);
	                this.startActivity(i);
	                break;
	        }
	    }
    
	
	
	
	
	
	
}