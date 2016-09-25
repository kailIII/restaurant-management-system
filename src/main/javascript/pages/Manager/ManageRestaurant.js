import React from "react";
import {Link} from "react-router";
import "whatwg-fetch";

class ManagerRestaurant extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            restaurant: {
                name: null,
                tables: []
            }
        }
    }

    componentDidMount() {
        let restaurantId = this.props.params.restaurantId;

        fetch(`/api/v1/restaurants/${restaurantId}`)
            .then(response => response.json())
            .then(json => {
                this.setState({
                    restaurant: json
                })
            })
    }

    render() {
        return (
            <div>
                <h1>{this.state.restaurant.name}</h1>
                <h2>Table Assignments</h2>
                <p>Select a table to update which waiter is assigned to it.</p>
                {this.state.restaurant.tables.map(table => (
                    <div key={table.id}>
                        <Link to={`/manager/manage-table/${table.id}`}>{table.name} - {table.waiter === null ? 'unassigned' : table.waiter.name}</Link>
                    </div>
                ))}
            </div>
        )
    }
}

export default ManagerRestaurant;
