package com.kishlaly.interviews.leverton.urlshortener.controllers;

import com.kishlaly.interviews.leverton.urlshortener.service.Shortener;
import com.kishlaly.interviews.leverton.urlshortener.service.ShortenerException;
import com.kishlaly.interviews.leverton.urlshortener.to.LinkTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
@RestController
public class MainController {

    @Autowired
    private Shortener service;

    @RequestMapping("/shorten")
    public LinkTO shorten(@RequestParam(defaultValue = "") String url) {
        LinkTO link = new LinkTO();
        try {
            link.setResult(service.shorten(url));
            link.setStatus("ok");
        } catch (ShortenerException s) {
            link.setStatus("Unable to convert specified url");
        } catch (RuntimeException r) {
            link.setStatus("Internal error");
        }

        return link;
    }

    @RequestMapping("/expand")
    public void redirect(@RequestParam(defaultValue = "") String url, HttpServletResponse response) {
        try {
            String longUrl = service.expand(url);
            response.sendRedirect(longUrl);
        } catch (ShortenerException e) {
            // does nothing as for now
        } catch (IOException e) {
            // does nothing as for now
        }
    }

}
