/**
 * Created by vladimirkishlaly on 20/06/2017.
 */

import * as React from "react";
import axios from "axios";
import PropTypes from "prop-types";

function shorten(url) {
    var encodedURI = window.encodeURI('http://127.0.0.1:8080/shorten?url=' + url);

    return axios.request({
        url: encodedURI,
        method: 'get',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'origin, content-type, accept'
        }
    })
}

export default class Shorten extends React.Component {

    serviceCall() {
        shorten(this.props.url).then(result => this.props.resultHandler(result));
    }

    render() {
        return (
            <button onClick={() => this.serviceCall(this.props.url)}>></button>
        )
    }

}

Shorten.propTypes = {
    url: PropTypes.string.isRequired
}