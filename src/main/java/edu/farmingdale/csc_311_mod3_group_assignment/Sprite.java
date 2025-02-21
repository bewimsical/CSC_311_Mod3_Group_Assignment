package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//this class is currently used by the mazes. Could make abstrct and have a maze class inherit from sprite.
public class Sprite {
    protected double x;
    protected double y;
    protected Image image;
    @FXML
    protected ImageView sprite;
    protected Bounds bounds;

    public Sprite(){
        this.x = 0;
        this.y = 0;
        this.image = null;
        this.sprite = null;
        this.bounds = null;
    }

    public Sprite(Image image, ImageView sprite){
        this.x = sprite.getLayoutX();
        this.y = sprite.getLayoutY();
        this.image = image;
        this.sprite = sprite;
        this.bounds = sprite.getBoundsInParent();
        render();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.sprite.setLayoutX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.sprite.setLayoutY(y);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.render();
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    //TODO fix this
    public void render(){
        this.sprite.setImage(this.image);
    }


}
