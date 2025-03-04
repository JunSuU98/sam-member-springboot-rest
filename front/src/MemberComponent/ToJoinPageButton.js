import React, {useState, useEffect} from "react";
import axios from "axios";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';
import { Link } from "react-router-dom";


function ToJoinPageButton(){

    return(

        <Link to={"/join"}>
            <Button variant="outline-primary">회원가입</Button>{' '}
        </Link>

    )

}

export default ToJoinPageButton;