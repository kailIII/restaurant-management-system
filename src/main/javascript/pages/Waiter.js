import React from "react";

class Waiter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            restaurants: []
        }
    }

    componentDidMount() {
        fetch(`/api/v1/restaurants/`)
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
                <h2>Waiter</h2>
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
                            <h2>{restaurant.name}</h2>
                            {assignedTables}
                        </div>
                    );
                })}
            </div>
        )
    }
}

export default Waiter;
