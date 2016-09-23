import React from 'react';
import { Link } from 'react-router';
import 'whatwg-fetch';

class Manager extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            restaurants: []
        }
    }

    componentDidMount() {
        fetch('/api/v1/restaurants')
            .then(response => response.json())
            .then(json => {
                this.setState({
                    restaurants: json
                })
            })
    }

    render() {
        return (
            <div>
                <h1>Manager</h1>
                {this.state.restaurants.map(restaurant => <Link key={restaurant.id} to={`/manager/manage-restaurant/${restaurant.id}`}>{restaurant.name}</Link>)}
            </div>
        )
    }
}

export default Manager;
