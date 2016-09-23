import React from 'react';
import { Link } from 'react-router';
import 'whatwg-fetch';

class ManagerRestaurant extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: null,
            name: null,
            tables: []
        }
    }

    componentDidMount() {
        let restaurantId = this.props.params.restaurantId;

        fetch(`/api/v1/restaurants/${restaurantId}`)
            .then(response => response.json())
            .then(json => {
                this.setState({
                    id: json.id,
                    name: json.name,
                    tables: json.tables
                })
            })
    }

    render() {
        return (
            <div>
                <h1>{this.state.name}</h1>
                <h2>Tables</h2>
                {this.state.tables.map(table => <Link key={table.id} to={`/manager/add-waiter-to-table/${table.id}`}>{table.name}</Link>)}
            </div>
        )
    }
}

export default ManagerRestaurant;
