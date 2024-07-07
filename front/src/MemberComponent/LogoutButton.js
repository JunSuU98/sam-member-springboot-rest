import React, {useState, useEffect} from "react";
import axios from "axios";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Button from 'react-bootstrap/Button';


function LogoutButton({handleStorageChange}){

    // sessionStorage 에 저장했던 모든 정보들을 삭제한다 
    const handleLogout = () => {
        sessionStorage.clear();
        handleStorageChange();
    }

    return (
        <div style={{display: 'inline'}}>
            <Button variant="outline-danger" onClick={handleLogout}>로그아웃</Button>{' '}
        </div>
    )
}

export default LogoutButton;