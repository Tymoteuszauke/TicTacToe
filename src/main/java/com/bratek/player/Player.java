package com.bratek.player;

/**
 * Created by bratek on 29.06.17.
 */
public class Player implements Comparable<Player> {
    private String name;
    private String sign;
    private int positionOrder;

    private Player() {}

    public String getSign() {
        return sign;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return positionOrder;
    }

    public static class PlayerBuilder {
        Player player = new Player();

        public PlayerBuilder positionOrder(int positionOrder) {
            player.positionOrder = positionOrder;
            return this;
        }

        public PlayerBuilder name(String name) {
            player.name = name;
            return this;
        }

        public PlayerBuilder setSign(String sign) {
            player.sign = " " + sign.substring(0, 1).toUpperCase() + " ";
            return this;
        }

        public Player build() {
            return player;
        }
    }

    @Override
    public int compareTo(Player o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        return sign != null ? sign.equals(player.sign) : player.sign == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        return result;
    }
}
