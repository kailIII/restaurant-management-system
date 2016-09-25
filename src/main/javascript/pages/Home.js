import React from "react";
import {Link} from "react-router";

export default () => (
    <div>
        <div className="scope-selector"><Link className="scope" to="/manager">I Am A Manager</Link></div>
        <div className="scope-selector"><Link className="scope" to="/waiter">I Am A Waiter</Link></div>
    </div>
);
