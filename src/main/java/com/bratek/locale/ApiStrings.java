//package com.bratek.locale;
//
//import java.util.HashMap;
//
///**
// * Created by Mateusz on 06.07.2017.
// */
//public enum ApiStrings {
//    INCORRECT_NUMBER("Incorrect Number", "Zła Liczba"),
//    GAME_TYPE("Choose game type", "Wybierz rodzaj gry"),
//    CONNECTED_TO_HOTSEAT_SERVER("Connected to TicTacToe game server! Type of game is hot seat", "Jesteś podłączony do serwera TicTacToe. Tryb rozgrywki to HotSeat");
//
//
////    private static Locale locale;
//
//    private HashMap<Locale, String> values;
//
//    ApiStrings(String en, String pl) {
//        values = createValues(en, pl);
//    }
//
//    private static HashMap<Locale, String> createValues(String en, String pl) {
//        HashMap<Locale, String> values = new HashMap<>();
//        values.put(Locale.EN, en);
//        values.put(Locale.PL, pl);
//        return values;
//    }
//
////    public void setLocale(Locale locale) {
////        this.locale = locale;
////    }
//
//    public String getValue(Locale locale) {
//        if (values.containsKey(locale)) {
//            return values.get(locale);
//        } else if (values.containsKey(Locale.EN)) {
//            return values.get(Locale.EN);
//        } else {
//            return "";
//        }
//    }
//}
