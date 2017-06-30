package com.bratek;

/**
 * Created by bratek on 29.06.17.
 */
public class Player {
    final private String name;
    final private String sign;
    private int score = 0;

    public Player(String name, String sign) {
        this.name = name;
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (score != player.score) return false;
        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + score;
        return result;
    }

    public String getName() {
        return name;
    }
}
