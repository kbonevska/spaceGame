package telerik;

import telerik.game_states.PlayState;
import telerik.interfaces.Entity;
import telerik.interfaces.Movable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Handler {
    private PlayState game;
    private ArrayList<Entity> gameObjects;
    private ArrayList<Movable> movables;

    public Handler(PlayState game) {
        this.game = game;
        this.gameObjects = new ArrayList<>();
        this.movables = new ArrayList<>();
    }

    public void render(Graphics2D g) {
        gameObjects.forEach(obj -> obj.render(g));
    }

    public void update() {
        movables.forEach(obj -> obj.move());

    }

    public void addGameObject(Entity gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void addMovable(Movable gameObject) {
        this.movables.add(gameObject);
    }

    public PlayState getGame() {
        return game;
    }

}
