package org.cdfsunrise.open;

public class OpenServiceImpl implements OpenService {
    @Override
    public String Test(String words) {
        System.out.println(words);
        return words;
    }
}
