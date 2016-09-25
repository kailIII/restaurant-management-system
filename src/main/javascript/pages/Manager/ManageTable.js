import React from "react";
import {browserHistory, Link} from "react-router";
import "whatwg-fetch";

class ManageTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            waiters: []
        }
    }

    componentDidMount() {
        fetch(`/api/v1/waiters?table_id=${this.props.params.tableId}`)
            .then(response => response.json())
            .then(json => {
                this.setState({
                    waiters: json
                })
            })
    }

    /**
     * Assign a waiter to a table.
     *
     * @param {string} waiterId
     * @param {string} tableId
     */
    assignWaiterToTable(waiterId, tableId) {
        let formData = new FormData();
        formData.append('waiter_id', waiterId);

        fetch(`/api/v1/tables/${tableId}/waiter`, {
                method: 'POST',
                body: formData
            })
            .then(response => {
                switch (response.status) {
                    case 400:
                        // The HTTP status "400" is returned when a waiter has
                        // reached their maximum allowed number of table
                        // assignments for a specific restaurant. The
                        // requirements specified that this value is 4.
                        return Promise.reject('Something went wrong!');
                    default:
                        // If the HTTP status is anything other than "400" then
                        // assign that it was a successful request!
                        return response.json()
                }
            })
            .then(json => {
                // Redirect back to the "manage waiter" page
                browserHistory.goBack()
            })
            .catch(message => {
                // TODO: Implement more detailed error messages
                alert(message);
            })
    }

    /**
     * Unassign a waiter from the specified table.
     *
     * @param {string} tableId
     */
    removeWaiterFromTable(tableId) {
        let formData = new FormData();
        formData.append('_method', 'DELETE');

        fetch(`/api/v1/tables/${tableId}/waiter`, {
                method: 'POST',
                body: formData
            })
            .then(response => {
                switch (response.status) {
                    case 204:
                        // The HTTP status "204" is returned when we
                        // successfully remove a waiter from a table.
                        browserHistory.goBack();
                        break;
                    default:
                        // We only care about the "204" HTTP status code. We can
                        // consider everything else to be an error.
                        // TODO: Implement more detailed error messages
                        alert('Something went wrong!');
                }
            })
    }

    render() {
        return (
            <div>
                <h1>Assign A Waiter To This Table</h1>
                <p><a href="javascript:void(0)" onClick={event => this.removeWaiterFromTable(this.props.params.tableId)}>Unassigned</a></p>
                {this.state.waiters.map(waiter => (
                    <p key={waiter.id}><a href="javascript:void(0)" onClick={event => this.assignWaiterToTable(waiter.id, this.props.params.tableId)}>
                        {waiter.name}
                    </a></p>
                ))}
            </div>
        )
    }
}

export default ManageTable;
