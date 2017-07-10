package com.bratek.board;

/**
 * Created by Mateusz on 05.07.2017.
 */
public class BoardField {
    private String symbol;
    private int fieldNumber;


    BoardField(int fieldNumber) {
        this.fieldNumber = fieldNumber;
        symbol = String.valueOf(fieldNumber);
    }

    public void setFieldNumber(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        if (symbol.equals(String.valueOf(fieldNumber))) {
            if (fieldNumber < 10) {
                return "0" + symbol + " ";
            }
            else return symbol + " ";
        }
        return " " + symbol + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardField that = (BoardField) o;

        if (fieldNumber != that.fieldNumber) return false;
        return symbol != null ? symbol.equals(that.symbol) : that.symbol == null;
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + fieldNumber;
        return result;
    }

    public boolean isTaken() {
        return !symbol.equals(String.valueOf(fieldNumber));
    }
}
