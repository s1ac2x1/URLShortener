package com.kishlaly.interviews.leverton.urlshortener.service;

/**
 * The main interface which provides the API to shorted given URL in a string presentation.
 *
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
public interface Shortener {

    String shorten(String srcUrl) throws ShortenerException;

    String expand(String shortUrl) throws ShortenerException;

}
