package com.bratek.board;

/**
 * Created by Mateusz on 05.07.2017.
 */
public class BoardField {
    private String sign;
    private int fieldNumber;


    BoardField(int fieldNumber) {
        this.fieldNumber = fieldNumber;
        sign = String.valueOf(fieldNumber);
    }

    public void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        if (sign.equals(String.valueOf(fieldNumber))) {
            if (fieldNumber < 10) {
                return "0" + sign + " ";
            }
            else return sign + " ";
        }
        return " " + sign + " ";
    }

    public boolean isTaken() {
        return !sign.equals(String.valueOf(fieldNumber));
    }
}
