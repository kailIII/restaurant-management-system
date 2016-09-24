import React from 'react';
import { browserHistory, Link } from 'react-router';
import 'whatwg-fetch';

class ManageTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            waiters: []
        }
    }

    componentDidMount() {
        fetch(`/api/v1/waiters`)
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
            .then(response => response.json())
            .then(json => {
                browserHistory.goBack()
            })
    }

    render() {
        return (
            <div>
                <h1>Assign A Waiter To This Table</h1>
                {this.state.waiters.map(waiter => (
                    <div key={waiter.id} onClick={event => this.assignWaiterToTable(waiter.id, this.props.params.tableId)}>
                        {waiter.name}
                    </div>
                ))}
            </div>
        )
    }
}

export default ManageTable;
