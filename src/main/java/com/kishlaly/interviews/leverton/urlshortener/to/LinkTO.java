package com.kishlaly.interviews.leverton.urlshortener.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LinkTO {

    private String status;
    private String result;

}
