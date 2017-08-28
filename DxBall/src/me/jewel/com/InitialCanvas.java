package me.jewel.com;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class InitialCanvas extends View{
	
	Context context;

    public static int canvasHeight,canvasWidth;

    ArrayList<Bricks> bricks = new ArrayList<Bricks>();

    float brickX = 0,brickY = 140;
    Paint paint;

    float x = 0, y = 0;
    float batX = 0, batY = 0;
    int moveX = 5;
    int moveY = -5;

    boolean first = true;
    boolean reTrayandGameOver=false;

    int score = 0;
    int life=3;
	
    Bitmap bat;
	
	 public InitialCanvas(Context context) {
	        super(context);
	        paint = new Paint();
	        bat= BitmapFactory.decodeResource(context.getResources(),R.drawable.bat);
	        this.context=context;
	    }
	 
	 protected void calculateNextPos(Canvas canvas) {

	        if (x >= canvas.getWidth() - 40 || x <= 40) {
	            moveX = -moveX;
	        }
	        if (y >= canvas.getHeight() || y <= 40) {
	            moveY = -moveY;
	        }
	        x += moveX;
	        y += moveY;


	    }

	    
	    protected void onDraw(Canvas canvas) {
	        canvasHeight = canvas.getHeight();
	        canvasWidth = canvas.getWidth();

	        if (first == true) {

	            first = false;

	            x = canvas.getWidth() / 2;
	            Log.d("X Value :", "" + x);
	            y = canvas.getHeight() - 80-40;
	            Log.d("Y Value :", "" + y);

	            batX=canvas.getWidth() / 2-109;
	            batY=canvas.getHeight()-80;

	            for (int i = 0; i < 20; i++) {
	                int color;
	                if (brickX >= canvas.getWidth()) {
	                    brickX = 0;
	                    brickY += 140;
	                }
	                if (i % 2 == 0)
	                    color = Color.BLUE;
	                else
	                    color = Color.BLACK;
	                bricks.add(new Bricks(brickX, brickY, brickX + canvas.getWidth() / 5, brickY + 140, color));

	                brickX += canvas.getWidth() / 5;
	            }
	        }
	        //canvas.drawRGB(29, 182, 220);
	        canvas.drawRGB(255, 255, 255);
	        this.ballBrickCollision(bricks);
	        this.calculateNextPos(canvas);
	        this.BarBallCollision(canvas);
	        this.LifeReduceGameOver(canvas);

	        paint.setColor(Color.RED);
	        paint.setStyle(Paint.Style.FILL);
	        canvas.drawCircle(x, y, 40, paint);
	        canvas.drawBitmap(bat,batX,batY,null);

	        paint.setColor(Color.BLACK);
	        paint.setTextSize(50);
	        canvas.drawText("Score :"+score, 10, 50, paint);
	        paint.setColor(Color.BLACK);
	        paint.setTextSize(50);
	        canvas.drawText("Life :"+life, 700, 50, paint);

	        for (int i = 0; i < bricks.size(); i++) {
	            canvas.drawRect(bricks.get(i).getLeft()+3, bricks.get(i).getTop()+3, bricks.get(i).getRight()-3, bricks.get(i).getBottom()-3, bricks.get(i).getPaint());
	        }

	        invalidate();
	    }
	    
	    public void ballBrickCollision(ArrayList<Bricks> br) {
	        for (int i = 0; i < br.size(); i++) {
	            if (((y - 40) <= br.get(i).getBottom()) && ((y + 40) >= br.get(i).getTop()) && (x >= br.get(i).getLeft()) && ((x) <= br.get(i).getRight())) {
	                br.remove(i);
	                score += 5;

	                if(score>=100){
	                    MainActivity main=new MainActivity();
	                    main.setContentView(R.layout.main);

	                }
	                moveY = -moveY;
	            }
	        }
	    }
	    
	    public void GameRetry(){
	        
	        //i = new Intent(InitialCanvas.this,Retry.class);
	        //startActivity(i);
	        Intent i=new Intent(context ,Retry.class);
            context.startActivity(i);
	        //Retry rt=new Retry();
	        //rt.setContentView(R.layout.retry_activity);
	        life=3;
	    }
	    
	    public void LifeReduceGameOver(Canvas canvas){
	        float y1=canvas.getHeight()-26,y2=canvas.getHeight();
	        if(y>=y1 && y<=y2) {
	            moveY=-moveY;
	            if(life<=1){
	                GameRetry();
	               // Retry retry=new Retry();
	                //retry.setContentView(R.layout.retry_activity);
	                life=3;
	                //Intent i;
	                //i = new Intent(this,Retry.class);
	                //startActivity(i);
	                //reTrayandGameOver=true;
	                Log.d("","Game Over");
	            }else {
	                --life;
	            }
	        }

	    }
	    
	    protected void BarBallCollision(Canvas canvas) {
	        int h=canvas.getHeight();
	        int w=canvas.getWidth();
	        //Log.d("Collision","bat :X="+batPixelX+" bat :Y="+batPixelY);
	        Log.d("Collision","bat :W="+w+" bat :H="+h);
	        if(x+40>batX-26 && x<batX+218-26){
	            if(y>batY-40 && y<batY+26) {
	                moveY = -moveY;
	            }

	            }
	        }
	    @Override
	    public boolean onTouchEvent(MotionEvent event) {

	        switch (event.getAction()) {
	            case MotionEvent.ACTION_MOVE:
	                if(batX<=800) {
	                    batX = (int) event.getX();
	                }else{
	                    batX-=5;
	                }
	                break;
	        }
	        return true;
	    }
	    
	    

}
