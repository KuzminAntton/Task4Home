package com.epam.task3.util;

public class RequestWorker {
    public static String removePunct(String str) {
        StringBuilder result = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c) || Character.isDigit(c) || Character.isSpaceChar(c)) {
                result.append(c);
            } else {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static  String [] getSearchParametersInRequest(String request) {
        request = RequestWorker.removePunct(request);

        request = request.toLowerCase();

        String[] parameterForTheSearch = request.split(" ");

        return parameterForTheSearch;
    }
}
