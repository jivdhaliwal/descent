package descent;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Descent extends StateBasedGame
{
     static int height = 48*10;
     static int width = 64*10;

     static boolean fullscreen = false;

     static boolean showFPS = false;

     static String title = "Descent";

     static int fpslimit = 60;

     public static final int GAMEPLAYSTATE = 1;
     public static final int MAINMENUSTATE = 0;

     

     public Descent() throws SlickException
     {
          super(title);

          this.addState(new MainMenuState());
          this.enterState(MAINMENUSTATE);
//          this.addState(new GameplayState());
//          this.enterState(GAMEPLAYSTATE);
          
          
     }

     public static void main(String[] args) throws SlickException
     {
          AppGameContainer app = new AppGameContainer(new ScalableGame(new Descent(),width,height));

          app.setDisplayMode((int)(width), (int)(height), fullscreen);
          app.setSmoothDeltas(true);
          app.setTargetFrameRate(fpslimit);
          app.setShowFPS(showFPS);
          app.start();
     }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
    }

    
    
     
}