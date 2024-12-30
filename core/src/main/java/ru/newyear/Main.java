package ru.newyear;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture imgBackGround1;
    private Texture imgBackGround2;
    private Texture imgBackGround3;
    private Texture imgBackGround4;


    private Texture imgButtonStart;
    private Texture imgButtonNext;
    private Texture imgButtonStartGame;


    private Texture imgSanta;
    private Texture imgSnow;
    private Texture imgRedToy;
    private Texture imgRed1Toy;
    private Texture imgBlueToy;
    private Texture imgGift;


    private long timeStartGame;

    private Sound sndStart;
    private Sound sndClick;
    private Sound sndGame;
    private Sound sndNewYear;

    private Santa santa;
    private Snow[] snow = new Snow[120];
    private Toy[] redToys = new Toy[5];
    private Toy[] blueToys = new Toy[5];
    private Toy[] red1Toys = new Toy[5];


    private OrthographicCamera camera;
    private Vector3 touch;

    private BitmapFont font;
    private BitmapFont font3;
    private BitmapFont smallfont;


    public static final float SCR_WIDTH = 1280;
    public static final float SCR_HEIGHT = 720;
    public static final String NAME = "Новогоднее Дело";
    public static final String QUESTIOM = "Хочешь получить подарок?";

    private boolean santaZero = false;
    private final int STARTGAME = 0;
    private final int GAME = 1;
    private final int GAME1 = 2;
    private final int GAME1_1 = 3;
    private final int GAME2 = 4;
    private final int GAME2_1 = 5;
    private final int GAME3 = 6;
    private final int GAME3_1 = 7;
    private final int GAME4 = 8;






    private int gameState = 99;

    private int times = 0;
    private int times1 = 0;
    private int times2 = 0;
    private int times3 = 0;
    private int times4 = 0;
    private int times5 = 0;



    private float mouse_x, mouse_y;
    private long timeCurrent;

    private int count = 0;

    private boolean[][] trueLst = new boolean[10][3];


    @Override
    public void create() {
        batch = new SpriteBatch();

        imgBackGround1 = new Texture("winter.png");
        imgBackGround2 = new Texture("sadwinter.png");
        imgBackGround3 = new Texture("sadwintertree.png");
        imgBackGround4 = new Texture("room.png");


        imgButtonStart = new Texture("buttons/start.png");
        imgButtonStartGame = new Texture("buttons/startGame.png");
        imgButtonNext = new Texture("buttons/next.png");

        imgSanta = new Texture("characters/santa.png");
        imgSnow = new Texture("snow.png");
        imgGift = new Texture("gift.png");

        imgRedToy = new Texture("toys/red.png");
        imgRed1Toy = new Texture("toys/red1.png");
        imgBlueToy = new Texture("toys/blue.png");

        timeStartGame = TimeUtils.millis();


        sndStart = Gdx.audio.newSound(Gdx.files.classpath("sounds/start.mp3"));
        sndClick = Gdx.audio.newSound(Gdx.files.classpath("sounds/click.mp3"));
        sndGame = Gdx.audio.newSound(Gdx.files.classpath("sounds/play.mp3"));
        sndNewYear = Gdx.audio.newSound(Gdx.files.classpath("sounds/newyear.mp3"));

        santa = new Santa(10, 500);
        for (int i = 0; i < snow.length; i++) {
            snow[i] = new Snow(MathUtils.random(0f, 1300f), MathUtils.random(720, 1200));
        }


        touch = new Vector3();
        font = new BitmapFont(Gdx.files.internal("fonts/2.fnt"));
        smallfont = new BitmapFont(Gdx.files.internal("fonts/small.fnt"));
        font3 = new BitmapFont(Gdx.files.internal("fonts/3.fnt"));


        //font2 = new BitmapFont(Gdx.files.internal("fonts/2.fnt"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
    }

    @Override
    public void render() {
        //sndStart.play();
        batch.setProjectionMatrix(camera.combined);


        if (Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            System.out.println(touch.x + " " + touch.y);
            if (gameState == 99 &&  touch.x >= 527 && touch.x <= 801 && touch.y >= 285 && touch.y <= 482 ) {

                if (times == 0) {
                    sndStart.play();
                    times++;
                    gameState = STARTGAME;
                }

            }
            if (touch.x >= 624 && touch.x <= 754 && touch.y >= 191 && touch.y <= 235 && gameState == STARTGAME) {
                if (times1 == 0) {
                    gameState = GAME;
                    times1++;
                    sndStart.pause();
                }
            }
            if (touch.x >= 587 && touch.x <= 784 && touch.y >= 23 && touch.y <= 171 && gameState == GAME) {
                if (times2 == 0) {
                    gameState = GAME1;
                    times2++;

                }
            }
            if (touch.x >= 537 && touch.x <= 732 && touch.y >= 337 && touch.y <= 479) {
                System.out.println();
                if (gameState == GAME1) gameState = GAME1_1;
                if (gameState == GAME2) gameState = GAME2_1;
                if (gameState == GAME3) gameState = GAME3_1;
            }
                if (gameState == GAME4 && touch.x >=551 && touch.x <= 756 && touch.y >= 144 && touch.y <=243){
                    if (times5 == 0) {
                        sndNewYear.play();
                        times5++;
                    }
                    else{
                        sndNewYear.stop();
                        times5 = 0;
                    }

                }
        }


        batch.begin();


        if (gameState == 99) {
            batch.draw(imgBackGround1, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            batch.draw(imgButtonStart, 522, 281);
        }

        if (gameState == STARTGAME) {
            batch.draw(imgBackGround1, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            santa.fly();
            if (santa.x >= SCR_WIDTH / 1.5) {
                santa.fell();
            }
            if (santa.y <= -6) {
                santaZero = true;
            }

            for (Snow s : snow) {
                if (santaZero) {
                    s.fell(santaZero);
                } else {
                    s.fell();
                }

            }
            batch.draw(imgSanta, santa.x, santa.y);
            for (Snow s : snow) {
                batch.draw(imgSnow, s.x, s.y, s.width, s.height);
            }
            if (santaZero) {
                font.draw(batch, QUESTIOM, 278, SCR_HEIGHT / 2);
                font.draw(batch, "play", 622, SCR_HEIGHT / 3);
            } else {
                font.draw(batch, NAME, 360, SCR_HEIGHT / 2);
            }
        }
        if (gameState == GAME) {
            batch.draw(imgBackGround2, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            smallfont.draw(batch, "Цель: получить подарок", 370, 360);
            batch.draw(imgButtonNext, 587, 20, 200, 150);
        }
        if (gameState == GAME1 || gameState == GAME2 || gameState == GAME3) {
            if(times4 == 0) {
                sndGame.play(0.2f);
                times3 = 0;
                times4++;
            }
            for (int i = 0; i < redToys.length; i++) {
                redToys[i] = new Toy(100, 100, MathUtils.random(-3, 3), MathUtils.random(-3, 3));
                red1Toys[i] = new Toy(SCR_WIDTH / 2, SCR_HEIGHT / 2, MathUtils.random(-3, 3), MathUtils.random(-3, 3));
                blueToys[i] = new Toy(300, 500, MathUtils.random(-5, 5), MathUtils.random(-3, 3));
            }
            batch.draw(imgBackGround2, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            if(gameState == GAME1) {
                smallfont.draw(batch, "Игра 1", 20, 700);
                smallfont.draw(batch, "Нужно нарядить елочку за 60 секунд", 20, 650);
                timeStartGame = TimeUtils.millis();
            }
            if(gameState == GAME2){
                smallfont.draw(batch, "Игра 2", 20, 700);
                smallfont.draw(batch, "Нужно нарядить елочку за 40 секунд", 20, 650);
                timeStartGame = TimeUtils.millis();
            }
            if(gameState == GAME3){
                smallfont.draw(batch, "Игра 3", 20, 700);
                smallfont.draw(batch, "Нужно нарядить елочку за 30 секунд", 20, 650);
                timeStartGame = TimeUtils.millis();
            }

            batch.draw(imgButtonStartGame, 537, 331, 200, 150);


        }
        if (gameState == GAME1_1 || gameState == GAME2_1 ||  gameState == GAME3_1) {

            count = 0;
            if (times3 == 0) {
                times4 = 0;
                sndGame.stop();
                sndGame.play(1);
                System.out.println("YYYYYYYYYYYYYY");
                times3++;
            }

            if (Gdx.input.isTouched()) {
                mouse_x = Gdx.input.getX();
                mouse_y = Gdx.input.getY();


                //  redToys[0].x = mouse_x;
                // redToys[0].y = mouse_y ;


                touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);

                camera.unproject(touch);
                //    System.out.println(touch.x + " " + touch.y);
                for (int i = 0; i < redToys.length; i++) {
                    if (touch.x >= redToys[i].x && touch.x <= redToys[i].x + redToys[i].width && Math.abs(touch.y - SCR_HEIGHT) >= redToys[i].y && Math.abs(touch.y - SCR_HEIGHT) <= redToys[i].y + redToys[i].height) {
                        ///  redToys[i].setPosition(Gdx.input.getX(), Gdx.input.getY());
                        redToys[i].touch = true;
                        redToys[i].playSnd();
                        if (redToys[i].play) {
                            sndClick.play();
                        }


                        for (int j = 0; j < redToys.length; j++) {
                            if (redToys[j].touch != redToys[i].touch) redToys[j].touch = false;
                            red1Toys[j].touch = false;
                            blueToys[j].touch = false;
                        }


                    }
                    if (touch.x >= red1Toys[i].x && touch.x <= red1Toys[i].x + red1Toys[i].width && Math.abs(touch.y - SCR_HEIGHT) >= red1Toys[i].y && Math.abs(touch.y - SCR_HEIGHT) <= red1Toys[i].y + red1Toys[i].height) {
                        red1Toys[i].touch = true;
                        red1Toys[i].playSnd();
                        if (red1Toys[i].play) {
                            sndClick.play();
                        }

                        for (int j = 0; j < redToys.length; j++) {
                            if (red1Toys[j].touch != red1Toys[i].touch) red1Toys[j].touch = false;
                            redToys[j].touch = false;
                            blueToys[j].touch = false;
                        }


                    }
                    if (touch.x >= blueToys[i].x && touch.x <= blueToys[i].x + blueToys[i].width && Math.abs(touch.y - SCR_HEIGHT) >= blueToys[i].y && Math.abs(touch.y - SCR_HEIGHT) <= blueToys[i].y + blueToys[i].height) {
                        blueToys[i].touch = true;
                        blueToys[i].playSnd();
                        if (blueToys[i].play) {
                            sndClick.play();
                        }

                        for (int j = 0; j < redToys.length; j++) {
                            if (blueToys[j].touch != blueToys[i].touch) blueToys[j].touch = false;
                            redToys[j].touch = false;
                            red1Toys[j].touch = false;
                        }


                    }


                }

            }

            batch.draw(imgBackGround3, 0, 0, SCR_WIDTH, SCR_HEIGHT);

            for (int i = 0; i < redToys.length; i++) {
                if (!redToys[i].touch) redToys[i].move();
                else {
                    if (redToys[i].x >= 592 && redToys[i].x <= 759 && Math.abs(redToys[i].y - SCR_HEIGHT) >= 133 && Math.abs(redToys[i].y - SCR_HEIGHT) <= 805) {
                        redToys[i].stopFollow = true;
                    }
                    redToys[i].ftouch(mouse_x, Math.abs(mouse_y - SCR_HEIGHT));

                }
                if (!red1Toys[i].touch) red1Toys[i].move();
                else {

                    if (red1Toys[i].x >= 592 && red1Toys[i].x <= 759 && Math.abs(red1Toys[i].y - SCR_HEIGHT) >= 133 && Math.abs(red1Toys[i].y - SCR_HEIGHT) <= 805) {
                        red1Toys[i].stopFollow = true;
                    }
                    red1Toys[i].ftouch(mouse_x, Math.abs(mouse_y - SCR_HEIGHT));
                }

                if (!blueToys[i].touch) blueToys[i].move();
                else {
                    if (blueToys[i].x >= 592 && blueToys[i].x <= 759 && Math.abs(blueToys[i].y - SCR_HEIGHT) >= 133 && Math.abs(blueToys[i].y - SCR_HEIGHT) <= 805) {
                        blueToys[i].stopFollow = true;
                    }
                    blueToys[i].ftouch(mouse_x, Math.abs(mouse_y - SCR_HEIGHT));
                }


            }

            //collide
            for (int i = 0; i < redToys.length; i++) {
                for (int j = 0; j < redToys.length; j++) {
                    redToys[i].collide(redToys[i], redToys[j]);
                    redToys[i].collide(redToys[i], red1Toys[j]);
                    redToys[i].collide(redToys[i], blueToys[j]);


                    red1Toys[i].collide(red1Toys[i], red1Toys[j]);
                    red1Toys[i].collide(red1Toys[i], blueToys[j]);

                    blueToys[i].collide(blueToys[i], blueToys[j]);
                }

            }


            for (int i = 0; i < redToys.length; i++) {
                batch.draw(imgRed1Toy, red1Toys[i].x, red1Toys[i].y, red1Toys[i].width, red1Toys[i].height);
                batch.draw(imgRedToy, redToys[i].x, redToys[i].y, redToys[i].width, redToys[i].height);
                batch.draw(imgBlueToy, blueToys[i].x, blueToys[i].y, blueToys[i].width, blueToys[i].height);


            }
            timeCurrent = TimeUtils.millis() - timeStartGame;
            System.out.println(showTime(timeCurrent));
            System.out.println();
            if (gameState == GAME1_1) {
                smallfont.draw(batch, "Игра 1", 20, 700);
                smallfont.draw(batch, "Нужно нарядить елочку за 60 секунд", 20, 650);
                if (!showTime(timeCurrent).equals("1:00:0")) {
                    smallfont.draw(batch, showTime(timeCurrent), 982, 52);
                } else {
                    gameState = GAME1;
                }
            }
            if (gameState == GAME2_1){

                smallfont.draw(batch, "Игра 2", 20, 700);
                smallfont.draw(batch, "Нужно нарядить елочку за 40 секунд", 20, 650);
                if (!showTime(timeCurrent).equals("0:40:0")) {
                    smallfont.draw(batch, showTime(timeCurrent), 982, 52);
                } else {
                    gameState = GAME2;
                }
            }

            if (gameState == GAME3_1){

                smallfont.draw(batch, "Игра 3", 20, 700);
                smallfont.draw(batch, "Нужно нарядить елочку за 30 секунд", 20, 650);
                if (!showTime(timeCurrent).equals("0:30:0")) {
                    smallfont.draw(batch, showTime(timeCurrent), 982, 52);
                } else {
                    gameState = GAME3;
                }
            }




            for (int i = 0; i < redToys.length; i++) {
              //  System.out.println(redToys[i].stopFollow + " " + red1Toys[i].stopFollow + " " + blueToys[i].stopFollow);

                trueLst[i] = new boolean[]{redToys[i].stopFollow, red1Toys[i].stopFollow, blueToys[i].stopFollow};

            }

           for (int i=0; i < trueLst.length; i++){
                for (int j = 0; j < trueLst[i].length; j++)
                {
                    if(trueLst[i][j]){
                        count++;
                    }
                }
            }

           if (count == redToys.length + red1Toys.length + blueToys.length){
               if (gameState == GAME1_1) gameState = GAME2;
               if (gameState == GAME2_1) gameState = GAME3;
               if (gameState == GAME3_1) gameState = GAME4;
           }





        }
        if (gameState == GAME4) {
            sndStart.stop();
            batch.draw(imgBackGround4, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            font.draw(batch, "Поздравляю! С Новым Годом!!!", 139, 352);
            batch.draw(imgGift, 504, 70, 300, 300);
            smallfont.draw(batch, "нажми на подарок", 416, 89);
        }





        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgBackGround1.dispose();
        imgBackGround2.dispose();
        imgBackGround4.dispose();
        imgButtonStart.dispose();
        imgButtonNext.dispose();
        imgSanta.dispose();
        imgSnow.dispose();
        imgGift.dispose();
        imgRedToy.dispose();
        imgRed1Toy.dispose();
        imgBlueToy.dispose();

        font.dispose();
        smallfont.dispose();
        font3.dispose();

        sndStart.dispose();
        sndClick.dispose();
        sndGame.dispose();
        sndNewYear.dispose();
        // font2.dispose();
    }

    private String showTime(long time) {

        long msec = time % 1000;
        long sec = time / 1000 % 60;
        long min = time / 1000 / 60 % 60;
        // long hour = time/1000/60/60%24;
        if (gameState == GAME1_1) {
            return min / 10 + min % 10 + ":" + sec / 10 + sec % 10 + ":" + msec / 100;
        }
        if (gameState == GAME2_1) {
            return min / 10 + min % 10 + ":" + sec / 10 + sec % 10 + ":" + msec / 100;

        }

        if (gameState == GAME3_1) {
        return min / 10 + min % 10 + ":" + sec / 10 + sec % 10 + ":" + msec / 100;

    }

        return "0";


    }
}

