package com.example.controller;

import javafx.scene.Node;

public class CollisionCheckerThread extends Thread {

    private Node node1, node2;
    private boolean isCollision;

    @Override
    public void run()
    {
        isCollision = node1.getBoundsInParent().intersects(node2.getBoundsInParent());
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public boolean isCollision() {
        return isCollision;
    }
}
