import React, {useState, useEffect} from "react";
import axios from "axios";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import SearchForm from "../MemberComponent/SearchForm";

import GoBackButton from "../MemberComponent/GoBackButton";

function AdminPage(){

    return (
        <div style={{display: 'inline'}}>
            <SearchForm />
            <GoBackButton text={"이전"}/>
        </div>
    )

}

export default AdminPage;