import React from 'react';
import { render } from 'react-dom';
import { browserHistory, IndexRoute, Link, Router, Route } from 'react-router';

import Home from './pages/Home';
import Manager from './pages/Manager';
import Waiter from './pages/Waiter';
import ManageRestaurant from './pages/ManageRestaurant';

render(
    <Router history={browserHistory}>
        <Route path="/" component={Home} />
        <Route path="manager">
            <IndexRoute component={Manager} />
            <Route path="manage-restaurant/:restaurantId" component={ManageRestaurant} />
        </Route>
        <Route path="waiter" component={Waiter} />
    </Router>,
    document.getElementById('app')
);