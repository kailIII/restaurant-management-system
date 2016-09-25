import React from "react";
import {render} from "react-dom";
import {browserHistory, IndexRoute, Link, Router, Route} from "react-router";
import Home from "./pages/Home";
import Manager from "./pages/Manager";
import Waiter from "./pages/Waiter";
import ManageRestaurant from "./pages/Manager/ManageRestaurant";
import ManageTable from "./pages/Manager/ManageTable";
import Waiters from "./pages/Waiters";

render(
    <Router history={browserHistory}>
        <Route path="/" component={Home} />
        <Route path="manager">
            <IndexRoute component={Manager} />
            <Route path="manage-restaurant/:restaurantId" component={ManageRestaurant} />
            <Route path="manage-table/:tableId" component={ManageTable} />
        </Route>
        <Route path="waiter" component={Waiters} />
        <Route path="waiter/:waiterId" component={Waiter} />
    </Router>,
    document.getElementById('app')
);
