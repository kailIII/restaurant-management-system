import React, {Component} from 'react';
import {Link} from 'react-router';

class SelectAWaiter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            waiters: []
        }
    }

    componentDidMount() {
        fetch('/api/v1/waiters/')
            .then(response => response.json())
            .then(json => {
                this.setState({
                    waiters: json
                })
            })
    }

    render() {
        return (
            <div>{this.state.waiters.map(waiter => (
                <p key={waiter.id}><Link to={`/waiter/${waiter.id}`}>{waiter.name}</Link></p>
            ))}</div>
        );
    }
}

export default SelectAWaiter;