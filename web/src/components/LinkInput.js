/**
 * Created by vladimirkishlaly on 20/06/2017.
 */

import * as React from "react";
import Shorten from "./Search";

export default class LinkInput extends React.Component {

    constructor() {
        super();
        this.state = {
            srcLink: "",
            result: "",
            error: ""
        }
        this.urlCharEntered = this.urlCharEntered.bind(this);
        this.gotResult = this.gotResult.bind(this);
    }

    urlCharEntered(event) {
        this.setState({
            srcLink: event.target.value,
            result: ""
        });
    }

    gotResult(res) {
        this.setState({
            result: res
        });
    }

    render() {
        return (
            <div className="inputWrapper">
                <input placeholder="type URL here" onKeyPress={this.urlCharEntered}/>
                <Shorten
                    url={this.state.srcLink}
                    resultHandler={() => this.gotResult()}
                />
                <div><a href={this.state.result}></a></div>
            </div>
        )
    }

}

