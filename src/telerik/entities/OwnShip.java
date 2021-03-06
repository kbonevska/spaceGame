package telerik.entities;


import telerik.Constants;
import telerik.system.Position;
import telerik.system.Size;
import telerik.game_states.PlayState;
import telerik.interfaces.Collidable;
import telerik.interfaces.HurtingShip;
import telerik.abstract_classes.Ship;

public class OwnShip extends Ship implements Collidable {

    private int velX = 0;
    private int velY = 0;

    private int bullets;
    private int width;
    private int height;

    private boolean isHurt = false;

    public OwnShip(PlayState game) {
        super(game);

        this.bullets = Constants.INITIAL_BULLETS;
        setHealth(Constants.INITIAL_HEALTH);
        this.width = Constants.OWN_SHIP_WIDTH;
        this.height = Constants.OWN_SHIP_HEIGHT;

        this.setSize(new Size(width, height));
        this.setPosition(new Position((Constants.WIDTH - width) / 2, Constants.HEIGHT - height));
        this.setSprites();
        this.setBounds();
    }

    private void setSprites() {
        int y;
        if (getLevel() == 1) {
            y = 0;
        }
        else {
            y = 326;
        }
        for (int i = 0; i < 8; i++) {
            this.setImage(getGame().getSpriteSheet().getImage(width * i, y, width, height));
        }
    }

    public void update() {
        updateFrame();

        if (getHealth() <= 0) {
            int currentLives = getGame().getPlayer().getLives();
            getGame().getPlayer().setLives(currentLives - 1);
            setHealth(Constants.INITIAL_HEALTH);
            new Explosion(getGame(), this);
            resetPosition();
        }

        if (getGame().getPlayer().getLives() < 0) {
            //todo go to game over state
        }

        getPosition().setX(getPosition().getX() + velX);
        getPosition().setY(getPosition().getY() + velY);

        if (getPosition().getX() <= 0) {
            getPosition().setX(0);
        }
        if (getPosition().getX() >= Constants.WIDTH - width) {
            getPosition().setX(Constants.WIDTH - width);
        }
        if (getPosition().getY() <= 105) {
            getPosition().setY(105);
        }
        if (getPosition().getY() >= Constants.HEIGHT - height) {
            getPosition().setY(Constants.HEIGHT - height);
        }
        getBounds().moveBounds(this);
    }

    private void updateFrame() {
        frame++;
        if (isHurt) {
            if (frame == getImageList().size()) {
                frame = 0;
                isHurt = false;
            }
        }
        else {
            if (frame == getImageList().size() / 2) {
                frame = 0;
            }
        }
    }

    public void upgradeShip() {
        getImageList().clear();
        width = Constants.OWN_SHIP_UPGRADED_WIDTH;
        height = Constants.OWN_SHIP_UPGRADED_HEIGHT;
        setLevel(2);
        setSprites();
        this.setSize(new Size(width, height));
    }


    @Override
    public void onCollide() {

    }

    public void onCollide(Collidable gameObj) {
        if(gameObj instanceof HurtingShip) {
            isHurt = true;
            frame = getImageList().size() / 2 - 1;
        }
    }

    public void resetPosition() {
        this.setPosition(new Position((Constants.WIDTH - width) / 2, Constants.HEIGHT - height));
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }

    @Override
    public String toString(){
        return "Own ship";
    }

}
