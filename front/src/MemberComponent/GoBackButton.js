import React, {useState, useEffect} from "react";
import axios from "axios";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';


import Button from 'react-bootstrap/Button';
import { useNavigate } from "react-router-dom";


function GoBackButton(){

    const navigate = useNavigate();

    return(
        <div style={{display: 'inline'}}>
            <Button variant="primary" onClick={() => navigate(-1)}>취소</Button>{' '}
        </div>
    );

}

export default GoBackButton;