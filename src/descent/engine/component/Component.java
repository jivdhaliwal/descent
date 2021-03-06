package descent.engine.component;

import descent.engine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author Jiv Dhaliwal <jivdhaliwal@gmail.com>
 */
public abstract class Component {

    protected String id;
    protected Entity entity;

    public String getId()
    {
        return id;
    }

    public void setOwnerEntity(Entity entity)
    {
        this.entity = entity;
    }

    public abstract void update(GameContainer gc, StateBasedGame sb, int delta);

}
