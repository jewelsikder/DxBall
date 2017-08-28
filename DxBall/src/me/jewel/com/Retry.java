package me.jewel.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Retry extends Activity implements View.OnClickListener{
	
	  Button retry;

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //retry=(Button)findViewById(R.id.retry);
	        setContentView(R.layout.retry_activity);
	    }

	    
	    public void onClick(View v) {
	        switch (v.getId()){
	            case R.id.retry:
	                Intent i=new Intent(this,StartActivity.class);
	                this.startActivity(i);
	                break;
	        }
	    }

}
