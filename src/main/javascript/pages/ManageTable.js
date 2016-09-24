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
                        return Promise.reject('Something went wrong!');
                    default:
                        return response.json()
                }
            })
            .then(json => {
                browserHistory.goBack()
            })
            .catch(message => {
                alert(message);
            })
    }

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
                        browserHistory.goBack();
                        break;
                    default:
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
                    <p><a key={waiter.id} href="javascript:void(0)" onClick={event => this.assignWaiterToTable(waiter.id, this.props.params.tableId)}>
                        {waiter.name}
                    </a></p>
                ))}
            </div>
        )
    }
}

export default ManageTable;
