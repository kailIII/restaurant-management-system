import React, {Component} from 'react';
import {browserHistory, Link} from 'react-router';

class Waiters extends Component {
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

    switchToManager() {
        browserHistory.push('/manager');
    }

    render() {
        return (
            <div>
                <div className="header">
                    <h1 className="title">Waiter</h1>
                    <span className="scope-switcher" onClick={this.switchToManager}>(Switch To Manager)</span>
                </div>
                <div className="content">
                    <p>Select your account to view your assigned tables.</p>
                    {this.state.waiters.map(waiter => (
                        <p key={waiter.id}><Link to={`/waiter/${waiter.id}`}>{waiter.name}</Link></p>
                    ))}
                </div>
            </div>
        );
    }
}

export default Waiters;