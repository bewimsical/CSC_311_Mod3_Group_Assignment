package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Maze extends Sprite {
    private double startX;
    private double startY;
    private double goalX;
    private double goalY;

    public Maze(Image image, ImageView sprite, double startX, double startY, double goalX, double goalY){
        super(image, sprite);
        this.startX = startX;
        this.startY = startY;
        this.goalX=goalX;
        this.goalY=goalY;

    }
    public double getStartX(){
        return this.startX;
  }
    public double getStartY(){
        return this.startY;
    }
    public double getGoalX(){
        return this.goalX;
    }
    public double getGoalY(){
        return this.goalY;
    }

    protected void render(){
        this.sprite.setImage(this.image);
    }

}
