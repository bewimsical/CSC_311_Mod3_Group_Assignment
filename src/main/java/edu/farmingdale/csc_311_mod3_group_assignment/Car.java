package edu.farmingdale.csc_311_mod3_group_assignment;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import javafx.scene.transform.Affine;

import javafx.scene.transform.Scale;
import javafx.util.Duration;
import javafx.animation.RotateTransition;

import java.util.List;

public class Car extends MovableSprite {

    private double heading;
    @FXML
    private Group sprite;
    private double scale;
    private Affine transformation;


    public Car() {
        this.x = 0;
        this.y = 0;
        this.image = null;
        this.sprite = null;
        this.bounds = null;
    }

    public Car(Group sprite, Maze maze) {
        this.sprite = sprite;
        this.heading = 0;
        this.scale = 0.05; //will not work on maze 1 if 0.05<
        this.transformation = new Affine();
        draw();
        render();
        this.x = sprite.getLayoutX();
        this.y = sprite.getLayoutY();
        this.bounds = sprite.getBoundsInParent();
        this.maze = maze;
        this.maze_im = maze.getImage();
        this.pixelRatio = maze_im.getHeight() / maze.getSprite().getFitHeight();
        this.pr = maze_im.getPixelReader();

    }



    @Override
    public void setX(double x) {
        this.x = x;
        this.sprite.setLayoutX(x);
    }

    @Override
    public void setY(double y) {
        this.y = y;
        this.sprite.setLayoutY(y);
    }

    @Override
    public void moveLeft() {
        double newX = x - 5;
        if (!isCollision(newX, y)) {
            x = newX;
            sprite.setLayoutX(x);
            heading = 180;    //
            render();
        }
    }

    @Override
    public void moveRight() {
        double newX = x + 5;
        double width = sprite.getLayoutBounds().getWidth() * this.scale;
        if (!isCollision(newX + width, y)) {
            x = newX;
            sprite.setLayoutX(x);

            heading =0;    //
            render(); //

        }
    }

    @Override
    public void moveUp() {
        double newY = y -5 ;
        if (!isCollision(x, newY)) {
            y = newY;
            sprite.setLayoutY(y);

            heading = -90;  //
            render();  //

        }
    }

    @Override
    public void moveDown() {
        double newY = y +5;
        double height = sprite.getLayoutBounds().getHeight() * this.scale;
        if (!isCollision(x, newY + height)) {
            y = newY;
            sprite.setLayoutY(y);
            heading = 90;

            render();

        }
    }

    @Override
    public void animateAlongPath(List<Point2D> path) {
        final int[] index = {0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (index[0] >= path.size()) {
                    stop();
                    System.out.println("All done!");
                    return;
                }
                Point2D target = path.get(index[0]);
                double targetX = target.getX();
                double targetY = target.getY();
                double currentX = getX();
                double currentY = getY();
                double deltaX = targetX - currentX;
                double deltaY = targetY - currentY;
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                double speed = 2;
                if (distance < speed) {
                    setX(targetX);
                    setY(targetY);
                    index[0]++;
                } else {
                    double moveX = (deltaX / distance) * speed;
                    double moveY = (deltaY / distance) * speed;
                    setX(currentX + moveX);
                    setY(currentY + moveY);
                }
                animateSprite(deltaX, deltaY);
                render();
            }
        };
        timer.start();
    }

    private void animateSprite(double deltaX, double deltaY) {
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            heading = (deltaX > 0) ? 0 : 180;
        } else {
            heading = (deltaY > 0) ? 90 : -90;
        }

        render();

    }

    @Override
    public boolean isAreaClear(double posX, double posY) {
        //Checks several key pointsaround the sprite
        double spriteWidth = sprite.getLayoutBounds().getWidth()*this.scale;
        double spriteHeight = sprite.getLayoutBounds().getHeight()*this.scale;
        //Used De Morgan's Law to invert the logic
        return !(isCollision(posX, posY) ||
                isCollision(posX + spriteWidth, posY) ||
                isCollision(posX, posY + spriteHeight) ||
                isCollision(posX + spriteWidth, posY + spriteHeight));
    }

    @Override
    protected void render() {
        sprite.getTransforms().clear();

        transformation.setToIdentity();
        transformation.appendRotation(
                this.heading,
                sprite.getLayoutBounds().getWidth()*this.scale/2,
                sprite.getLayoutBounds().getHeight()*this.scale/2
        );
        transformation.appendScale(
                this.scale,
                this.scale * (this.heading == 180 ? -1 : 1),
                (this.heading == 180 ? (sprite.getLayoutBounds().getWidth()*this.scale)/2 -10: 0),
                (this.heading == 180 ? (sprite.getLayoutBounds().getHeight()*this.scale)/2 +3 : 0)
        );
        sprite.getTransforms().add(transformation);

    }
    private void draw(){
        Polygon body = new Polygon();
        body.getPoints().addAll(
                50.0, 50.0,
                100.0, 0.0,
                230.0, 0.0,
                280.0, 50.0,
                350.0, 50.0,
                380.0, 100.0,
                0.0, 100.0
        );

        body.setFill(Color.PURPLE);

        // Windows (Rectangles)
        Rectangle frontWindow = new Rectangle(110, 10, 40, 40);
        frontWindow.setFill(Color.GREEN);
        Rectangle rearWindow = new Rectangle(180, 10, 50, 40);
        rearWindow.setFill(Color.GREEN);

        // Wheels
        Circle frontWheel = new Circle(100, 100, 30);
        frontWheel.setFill(Color.BLACK);
        Circle rearWheel = new Circle(310, 100, 30);
        rearWheel.setFill(Color.BLACK);

        // Grouping all elements
        sprite.getChildren().add(body);
        sprite.getChildren().add(frontWindow);
        sprite.getChildren().add(rearWindow);
        sprite.getChildren().add(frontWheel);
        sprite.getChildren().add(rearWheel);

    }
}
