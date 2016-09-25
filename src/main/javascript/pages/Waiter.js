import React from "react";
import {browserHistory} from "react-router";

class Waiter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            waiter: {
                name: false
            },
            restaurants: []
        }
    }

    componentDidMount() {
        Promise.all([
            fetch(`/api/v1/waiters/${this.props.params.waiterId}`).then(response => response.json()),
            fetch(`/api/v1/restaurants/`).then(response => response.json())
        ])
        .then(json => {
            this.setState({
                waiter: json[0],
                restaurants: json[1]
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
                    {this.state.waiter.name !== false ? <h2>{this.state.waiter.name}'s table assignments</h2> : null}
                    {this.state.restaurants.map(restaurant => {
                        let tables = restaurant.tables
                            .filter(table => table.waiter !== null && table.waiter.id == this.props.params.waiterId);

                        let assignedTables = <div>Nothing assigned</div>
                        if (tables.length > 0) {
                            assignedTables = tables.map(table => (
                                <div key={table.id}>
                                    {table.name}
                                </div>
                            ))
                        }

                        return (
                            <div key={restaurant.id}>
                                <h3>{restaurant.name}</h3>
                                {assignedTables}
                            </div>
                        );
                    })}
                </div>
            </div>
        )
    }
}

export default Waiter;
