import React from 'react';
import { Link } from 'react-router';

export default () => (
    <div>
        <p><Link to="/manager">Manager</Link></p>
        <p><Link to="/waiter">Waiter</Link></p>
    </div>
);