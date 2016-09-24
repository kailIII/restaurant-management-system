import React from "react";
import {Link} from "react-router";
import "whatwg-fetch";

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
                <p>Select a Restaurant</p>
                {this.state.restaurants.map(restaurant => <p key={restaurant.id}><Link to={`/manager/manage-restaurant/${restaurant.id}`}>{restaurant.name}</Link></p>)}
            </div>
        )
    }
}

export default Manager;
