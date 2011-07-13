package descent.engine.component;

/**
 * @author Jiv Dhaliwal <jivdhaliwal@gmail.com>
 */

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import descent.GameplayState;

public class PlayerMovement extends Component {

    GameplayState gameplayState;
    
    private int inputCounter;
    private int jumpCounter;
    private int gravityCounter;
    
    private boolean isJumping;
    
    private static final int gravity = 64;
    private static final int jumpspeed = 640;
    private static final int maxVelocity = 896;
    private static final int hori_velocity = 512;
    private static final int maxJumpTime = 300;
    private int velocity_y = 0;
    
    

    public PlayerMovement( String id )
    {
        this.id = id;
      
    }


    @Override
    public void update(GameContainer gc, StateBasedGame sb, int delta) {

        isJumping=!entity.onTopOfWall();

        Vector2f position = entity.getPosition();
        
        inputCounter-=delta;
        jumpCounter-=delta;
        gravityCounter-=delta;

        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_SPACE)) {
            jumpCounter -= delta;
        } else {
            jumpCounter = -1;
        }

        if(inputCounter<0) {

            if(input.isKeyPressed(Input.KEY_SPACE) && !isJumping) {
                jumpCounter=maxJumpTime;
                isJumping=true;
            }
            
            if(input.isKeyDown(Input.KEY_LEFT)) {
                position.x-= hori_velocity>>8;
                entity.getCollisionPoly().setX(position.x);
                if (entity.blocked()) {
                    position.x += hori_velocity >> 8;
                    entity.getCollisionPoly().setX(position.x);
                }
                inputCounter=10;
            }
            
            if(input.isKeyDown(Input.KEY_RIGHT)) {
                position.x+= hori_velocity>>8;
                entity.getCollisionPoly().setX(position.x);
                if (entity.blocked()) {
                    position.x -= hori_velocity >> 8;
                    entity.getCollisionPoly().setX(position.x);
                }
                inputCounter=10;
            }
            
            
        }
        
        if(gravityCounter<0) {
            
            if(jumpCounter>0) {
                velocity_y= (-jumpspeed);
            } else {
                velocity_y+=gravity;
                if(velocity_y>maxVelocity) {
                    velocity_y=maxVelocity;
                }
            }
            position.y+= velocity_y>>8;
            entity.getCollisionPoly().setY(position.y);
            if(entity.blocked()) {
                jumpCounter= -1;
                position.y-=velocity_y>>8;
                entity.getCollisionPoly().setY(position.y);
                velocity_y=0;
            }
            gravityCounter=10;
        }
        
        
        entity.setPosition(position);
        entity.getCollisionPoly().setLocation(position);
        

    }

}

