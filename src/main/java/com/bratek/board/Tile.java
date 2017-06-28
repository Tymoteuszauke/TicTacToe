package com.bratek.board;

/**
 * Created by Mateusz on 2017-06-28.
 */
public class Tile {
    final private Position position;
    private String sign;

    public Tile(Position position, String sign) {
        this.position = position;
        this.sign = sign;
    }

    public Position getPosition() {
        return position;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
