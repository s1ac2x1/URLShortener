package com.kishlaly.interviews.leverton.urlshortener.service.impl;

import com.kishlaly.interviews.leverton.urlshortener.model.Link;
import com.kishlaly.interviews.leverton.urlshortener.repository.LinksRepository;
import com.kishlaly.interviews.leverton.urlshortener.service.Shortener;
import com.kishlaly.interviews.leverton.urlshortener.service.ShortenerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The implementation of {@link Shortener} based on a simple algorithm.
 *
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
@Service
public class SimpleShortener implements Shortener {

    private HashMap<String, String> keyMap = new HashMap<>();
    private HashMap<String, String> valueMap = new HashMap<>();
    private String domain = "http://localhost:8080/expand?url=";
    private char myChars[] = new char[62];
    private Random myRand = new Random();
    private int keyLength = 8;
    private boolean initialized;
    private ConcurrentHashMap<String, String> longToShortCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> shortToLongCache = new ConcurrentHashMap<>();

    @Autowired
    private LinksRepository repository;

    @Override
    public String shorten(String url) throws ShortenerException {
        if (!initialized) {
            init();
        }
        String inCache = longToShortCache.get(url);
        if (inCache != null) {
            return inCache;
        }
        String newValue = longToShort(url);
        if (!repository.exists(newValue)) {
            repository.save(new Link(newValue, url));
        }
        longToShortCache.put(url, newValue);
        return newValue;
    }

    @Override
    public String expand(String shortUrl) throws ShortenerException {
        String inCache = shortToLongCache.get(shortUrl);
        if (inCache != null) {
            return inCache;
        }
        Link existing = repository.findOne(shortUrl);
        if (existing != null) {
            String longUrl = existing.getUrl();
            shortToLongCache.put(shortUrl, longUrl);
            return longUrl;
        }
        throw new ShortenerException("Given short link does not exist");
    }

    private void init() {
        for (int i = 0; i < 62; i++) {
            int j = 0;
            if (i < 10) {
                j = i + 48;
            } else if (i > 9 && i <= 35) {
                j = i + 55;
            } else {
                j = i + 61;
            }
            myChars[i] = (char) j;
            initialized = true;
        }
    }


    public String longToShort(String longURL) {
        String shortURL = "";
        longURL = sanitizeURL(longURL);
        if (valueMap.containsKey(longURL)) {
            shortURL = domain + "/" + valueMap.get(longURL);
        } else {
            shortURL = domain + "/" + getKey(longURL);
        }
        return shortURL;
    }

    public String shortToLong(String shortURL) {
        String longURL = "";
        String key = shortURL.substring(domain.length() + 1);
        longURL = keyMap.get(key);
        return longURL;
    }

    private String sanitizeURL(String url) {
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

    private String getKey(String longURL) {
        String key;
        key = generateKey();
        keyMap.put(key, longURL);
        valueMap.put(longURL, key);
        return key;
    }

    private String generateKey() {
        String key = "";
        boolean flag = true;
        while (flag) {
            key = "";
            for (int i = 0; i <= keyLength; i++) {
                key += myChars[myRand.nextInt(62)];
            }
            if (!keyMap.containsKey(key)) {
                flag = false;
            }
        }
        return key;
    }


}
