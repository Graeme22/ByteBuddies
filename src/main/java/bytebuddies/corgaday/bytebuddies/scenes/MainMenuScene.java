package bytebuddies.corgaday.bytebuddies.scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import bytebuddies.corgaday.bytebuddies.R;
import bytebuddies.corgaday.bytebuddies.engine.Dimension;
import bytebuddies.corgaday.bytebuddies.engine.MainActivity;
import bytebuddies.corgaday.bytebuddies.util.Constants;
import bytebuddies.corgaday.bytebuddies.engine.ButtonManager;

import static bytebuddies.corgaday.bytebuddies.util.Constants.FRAME;

class MainMenuScene implements Scene {
	private MainActivity ma;

	// create button manager
	private ButtonManager buttonManager;

	//create bitmaps
	private Bitmap title;
	private Bitmap bg1;
	private Bitmap bg2;
	private Bitmap bg3;

	// create other vars
	private int currentBG;

    MainMenuScene(MainActivity ma) {
	    this.ma = ma;
	    //LTRB
	    Bitmap buttonNewGame;
	    Bitmap buttonStore;
	    Bitmap buttonOptions;

	    // init button manager
        buttonManager = new ButtonManager();

	    // init bitmaps
	    buttonNewGame = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.button_start), 128, 128, true);
	    buttonStore = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.button_store), 128, 128, true);
	    buttonOptions = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.button_options), 128, 128, true);
	    title = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.title), 288, 80, false);
	    bg1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.bg1), 512, 256, false);
	    bg2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.bg2), 512, 256, false);
	    bg3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Constants.RESOURCES, R.drawable.bg3), 512, 256, false);

	    // add buttons
	    buttonManager.addButton("newGame", new Rect(FRAME.left + 2 * FRAME.width / 32, 5 * FRAME.height / 16, FRAME.left + 10 * FRAME.width / 32, 13 * FRAME.height / 16), buttonNewGame, false, new Dimension(128, 128));
	    buttonManager.addButton("options", new Rect(FRAME.left + 12 * FRAME.width / 32, 5 * FRAME.height / 16, FRAME.left + 20 * FRAME.width / 32, 13 * FRAME.height / 16), buttonStore, false, new Dimension(128, 128));
	    buttonManager.addButton("store", new Rect(FRAME.left + 22 * FRAME.width / 32, 5 * FRAME.height / 16, FRAME.left + 30 * FRAME.width / 32, 13 * FRAME.height / 16), buttonOptions, false, new Dimension(128, 128));
    }

    @Override
    public void update() {
        buttonManager.update();
    }

    @Override
    public void draw(Canvas canvas) {

	    // use switch so that based on what background we have we draw that one respectively
	    switch (currentBG) {
		    case 0:
				canvas.drawBitmap(bg1, new Rect(0, 0, 512, 256), new Rect(0, 0, Constants.FRAME.width, Constants.FRAME.height), null);
		    	break;
		    case 1:
			    canvas.drawBitmap(bg2, new Rect(0, 0, 512, 256), new Rect(0, 0, Constants.FRAME.width, Constants.FRAME.height), null);
			    break;
		    case 2:
			    canvas.drawBitmap(bg3, new Rect(0, 0, 512, 256), new Rect(0, 0, Constants.FRAME.width, Constants.FRAME.height), null);
			    break;
	    }

	    // draw title
	    canvas.drawBitmap(title, new Rect(1, 1, 288, 80), new Rect(Constants.FRAME.left + 7 * Constants.FRAME.width / 32, Constants.FRAME.top, Constants.FRAME.left + 24 * Constants.FRAME.width / 32, Constants.FRAME.top + 5 * Constants.FRAME.height / 16), null);

	    // last, draw buttons
        buttonManager.draw(canvas);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
	    // find out what buttons might have been pressed
        String pressedButton = buttonManager.receiveTouch(event);
	    // if a button was pressed...
        if (pressedButton != null) {
	        // find out what kind of action was made to the button
            switch (event.getAction()) {
	            // in the case it was pressed...
                case MotionEvent.ACTION_DOWN:
                	// find out what button was pressed with switch
                    switch (pressedButton) {
	                    // if the button was "newGame"
                        case "newGame":
                        	// open bt list
							ma.activateBT();

                        	// "start" a new game
//                            SceneManager.setScene(SceneManager.GAME_PLAY_SCENE);
                            break;
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
    }

    @Override
    public void activate() {
	    // generate new bg number between 0 and 2
		currentBG = (int)Math.floor(3 * Math.random());
    }

    @Override
    public void deactivate() {

    }
}
