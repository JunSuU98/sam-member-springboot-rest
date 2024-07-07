import React, {useState, useEffect} from "react";
import axios from "axios";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { useNavigate, useSearchParams } from "react-router-dom";


function NaverCallbackPage({handleStorageChange}) {

    const [searchParams, setSearchParams] = useSearchParams();

    const code = searchParams.get("code");
    const state = searchParams.get("state");

    const navigate = useNavigate();

    useEffect(() => {
        getResponse();
    }, [handleStorageChange]);

    const getResponse = async () => {
        try{
            const response = await axios.get(`/api/naver-login-callback?code=${code}&state=${state}`);
            console.log(response.data)

            // 기존에 sessionStorage 에 남아있던 값 삭제
            sessionStorage.clear();

            // sessionStorage에 사용자 정보 저장 
            sessionStorage.setItem("member_sns_session",response.data.id);
            sessionStorage.setItem("member_id",response.data.nickname);
            sessionStorage.setItem("member_name",response.data.name);
            sessionStorage.setItem("member_email", response.data.email);
            sessionStorage.setItem("member_birth", response.data.birthyear + "-" +response.data.birthday);
            sessionStorage.setItem("member_phone",response.data.mobile);
            sessionStorage.setItem("member_status", "active");

            handleStorageChange();

        } catch (error) {
            console.log("api error= ", error)
        }

        navigate("/");
    }


}

export default NaverCallbackPage;