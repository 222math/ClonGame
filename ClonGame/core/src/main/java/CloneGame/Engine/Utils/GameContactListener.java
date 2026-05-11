package CloneGame.Engine.Utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import CloneGame.Engine.Objects.Person;
import CloneGame.Engine.Objects.Portal;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Person){
            ((Person) userDataA).setOnGround(true);
            if (userDataB instanceof Portal){
                Portal.setInPortal(true);
            }
        }
        if (userDataB instanceof Person){
            ((Person) userDataB).setOnGround(true);

            if (userDataA instanceof Portal){
                Portal.setInPortal(true);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getBody().getUserData();
        Object userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Person){
            ((Person) userDataA).setOnGround(false);
        }
        if (userDataB instanceof Person){
            ((Person) userDataB).setOnGround(false);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
