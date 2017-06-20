package com.kishlaly.interviews.leverton.urlshortener.repository;

import com.kishlaly.interviews.leverton.urlshortener.model.Link;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
public interface LinksRepository extends CrudRepository<Link, String> {
}
