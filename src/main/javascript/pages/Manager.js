import React from "react";
import {browserHistory, Link} from "react-router";
import "whatwg-fetch";

class Manager extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            restaurants: [],
            selectedRestaurant: false
        }
    }

    componentDidMount() {
        fetch('/api/v1/restaurants')
            .then(response => response.json())
            .then(json => {
                // When React loads all the restaurants, we need to set a
                // sensible default value for the "restaurant chooser" dropdown.
                // This lets us render the correct list of tables.
                let selectedRestaurant = json[0].id;

                // If we have a selected restaurant stored in session storage,
                // e.g. we recently refreshed the page, then use that value if
                // it is still valid.
                if (sessionStorage.getItem('selected_restaurant')) {

                    // Check whether the selected restaurant stored in session
                    // storage is still valid.
                    let isRestaurantIdValid = json
                        .find(restaurant => restaurant.id === sessionStorage.getItem('selected_restaurant'));

                    if (isRestaurantIdValid) {
                        selectedRestaurant = sessionStorage.getItem('selected_restaurant');
                    }
                }

                this.setState({
                    restaurants: json,
                    selectedRestaurant: selectedRestaurant
                }, ()=> {
                    // Add the selected restaurant to session storage to prevent page
                    // refreshes from clearing the manager's session
                    sessionStorage.setItem('selected_restaurant', selectedRestaurant);
                })
            })
    }

    /**
     * Event handler to indicate which restaurant the manager is currently
     * editing
     *
     * @param {Event} event
     */
    updateSelectedRestaurant(event) {
        event.preventDefault();

        // The selected restaurant comes from the value of the dropdown box in
        // the header
        let selectedRestaurant = event.target.value;

        this.setState({
            selectedRestaurant: selectedRestaurant
        }, () => {
            // Add the selected restaurant to session storage to prevent page
            // refreshes from clearing the manager's session
            sessionStorage.setItem('selected_restaurant', selectedRestaurant);
        });
    }

    /**
     * Navigate from the manager page to the waiters page
     */
    switchToWaiter() {
        browserHistory.push('/waiter');
    }

    render() {
        // Render the selected restaurant's tables.
        // TODO: Move this logic to its own component
        let selectedRestaurant = this.state.restaurants
            .find(restaurant => restaurant.id === this.state.selectedRestaurant);

        let tables = <p>No tables available for this restaurant</p>;
        if (selectedRestaurant !== undefined) {
            tables = selectedRestaurant.tables
                .map(table => (
                    <div key={table.id}>
                        <Link to={`/manager/manage-table/${table.id}`}>{table.name} - {table.waiter === null ? 'unassigned' : table.waiter.name}</Link>
                    </div>
                ))
        }

        return (
            <div>
                <div className="header">
                    <h1 className="title">Manager</h1>
                    <span className="scope-switcher" onClick={this.switchToWaiter}>(Switch to Waiter)</span>
                    {this.state.restaurants.length > 0 ?
                        <div className="actions">
                            <span className="label">Select a Restaurant: </span>
                                <select onChange={this.updateSelectedRestaurant.bind(this)} value={this.state.selectedRestaurant}>
                                    {this.state.restaurants.map(restaurant => (
                                        <option key={restaurant.id} value={restaurant.id}>{restaurant.name}</option>)
                                    )}
                                </select>
                        </div>
                        :
                        null
                    }
                </div>
                <div className="content">
                    <p>Select a table to update which waiter is assigned to it.</p>
                    {tables}
                </div>
            </div>
        )
    }
}

export default Manager;
