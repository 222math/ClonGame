package CloneGame.Engine.Screens;

import static CloneGame.Engine.Main.GameResources.PERSON_IMG_PATH;
import static CloneGame.Engine.Main.GameSettings.PERSON_HEIGHT;
import static CloneGame.Engine.Main.GameSettings.PERSON_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;



import CloneGame.Engine.Components.TextButton;
import CloneGame.Engine.Main.Main;
import CloneGame.Engine.Objects.Person;
import CloneGame.Engine.Objects.Platform;
import CloneGame.Engine.Objects.Portal;
import CloneGame.Engine.Objects.PressurePlate;
import CloneGame.Engine.Recording.Record;
import CloneGame.Engine.Recording.Replay;

public class ScreenGame extends ScreenAdapter {
    Main main;
    ScreenMenu screenMenu;
    Record record;
    Replay replay;
    Person person;
    Platform platform1;
    Platform platform2;
    Portal portal;
    PressurePlate pressurePlate;
    TextButton rightMoveButton;
    TextButton leftMoveButton;
    TextButton jumpButton;
    TextButton recordStartButton;
    TextButton recordEndButton;
    TextButton replayButton;

    public ScreenGame(Main main){
        this.main = main;
        person = new Person(PERSON_IMG_PATH , 600 , 150 , PERSON_WIDTH , PERSON_HEIGHT , main.world);
        leftMoveButton = new TextButton(50 , 50 , 75 , 75 , "l" , "img.png");
        rightMoveButton = new TextButton(135 , 50 , 75 , 75 , "r" , "img.png");
        jumpButton = new TextButton(1200 , 50 , 75 , 75 , "j" , "img.png");
        recordStartButton = new TextButton(400 , 600 , 100 , 100 , "recS" , "img.png");
        recordEndButton = new TextButton(600 , 600 , 100 , 100 , "recE" , "img.png");
        replayButton = new TextButton(720 ,  600, 100 , 100 , "rep" , "img.png");
        platform1 = new Platform(PERSON_IMG_PATH , 800 , 150 , 400 , 20 , main.world);
        platform2 = new Platform(PERSON_IMG_PATH , 0 , 0 , 200000 , 25 , main.world);
        portal = new Portal("img_1.png" , 0 ,25 , 30 , 150 , main.world);
        pressurePlate = new PressurePlate("img_1.png" , 900 , 157 , 100 , 15 , main.world);
        record = new Record();
        portal.setInPortal(false);
    }


    @Override
    public void render(float delta) {
        main.stepWorld();
        person.update();
        if (portal.getInPortal()) {
            main.setScreen(new ScreenMenu(main));
            return;  // Выходим, чтобы не продолжать рендеринг
        }
        handleInput();
        draw();
    }

    boolean rec = false;
    boolean rep = false;
    public void handleInput(){
        float delta = Gdx.graphics.getDeltaTime();
        boolean anyButtonPressed = false;
        Vector3 touchPos = new Vector3();

        if (Gdx.input.isTouched()) {
            for (int i = 0; i < 10; i++) {
                if (!Gdx.input.isTouched(i)) continue;


                float screenX = Gdx.input.getX(i);
                float screenY = Gdx.input.getY(i);


                touchPos.set(screenX, screenY, 0);
                main.camera.unproject(touchPos);
                float worldX = touchPos.x;
                float worldY = touchPos.y;
                if (rightMoveButton.IsHit(worldX, worldY)) {
                    person.moveRight();
                    anyButtonPressed = true;
                }
                if (leftMoveButton.IsHit(worldX, worldY)) {
                    person.moveLeft();
                    anyButtonPressed = true;
                }
                if (jumpButton.IsHit(worldX, worldY)) {
                    person.jump();
                }
                if (recordStartButton.IsHit(worldX , worldY)){
                    if (!rec){
                        rec = true;   // Начинаем запись
                        record.deleteRecord();  // Очищаем старые данные
                        System.out.println("Recording started");
                    }
                }
                if (recordEndButton.IsHit(worldX , worldY)){
                    if (rec) {
                        rec = false;  // Останавливаем запись
                        System.out.println("Recording stopped. Total positions: " + record.getPositions().size());
                    }

                }
                if (replayButton.IsHit(worldX, worldY) && !rec && record.getPositions().size() > 0) {
                    rep = true;
                    replay = new Replay(record.getPositions(), record.getPlate() , PERSON_IMG_PATH); // ИСПОЛЬЗУЙТЕ PERSON_IMG_PATH вместо "img.png"
                    System.out.println("Replay created with " + record.getPositions().size() + " positions");
                }



            }

        }
        if (!anyButtonPressed) {
            person.stop();
        }
        if (rec){
            // getX() и getY() уже возвращают пиксельные координаты
            record.recordingPos(person.getX(), person.getY(), delta);

            System.out.println("Recording: " + person.getX() + ", " + person.getY() + " Total: " + record.getPositions().size());
        }
        if(portal.getInPortal()){
            System.out.println("В портале!");
            screenMenu = new ScreenMenu(this.main);
            main.setScreen(screenMenu);

        }
        pressurePlate.update(delta);

    }
    public void draw(){
        float delta = Gdx.graphics.getDeltaTime();
        main.camera.update();
        main.batch.setProjectionMatrix(main.camera.combined);
        ScreenUtils.clear(Color.CLEAR);



        main.batch.begin();

        person.draw(main.batch);
        leftMoveButton.draw(main.batch);
        rightMoveButton.draw(main.batch);
        jumpButton.draw(main.batch);
        recordStartButton.draw(main.batch);
        recordEndButton.draw(main.batch);
        replayButton.draw(main.batch);
        pressurePlate.draw(main.batch);
        platform1.draw(main.batch);
        platform2.draw(main.batch);
        // В ScreenGame.draw():
        if (rep && replay != null && replay.isPlaying()) {
            float delta1 = Gdx.graphics.getDeltaTime();
            replay.update(delta1);

            Vector2 pos = replay.getCurrentPosition();
            if (pos != null) {
                main.batch.draw(replay.getTexture(),
                    pos.x - PERSON_WIDTH/2,
                    pos.y - PERSON_HEIGHT/2,
                    PERSON_WIDTH, PERSON_HEIGHT);
            }
        } else if (replay != null && !replay.isPlaying()) {
            rep = false;
            replay.setTimer(0);
        }
        if (rep){
            replay.setTimer(replay.getTimer() + delta);
        }
        portal.draw(main.batch);


        main.batch.end();
    }

}
