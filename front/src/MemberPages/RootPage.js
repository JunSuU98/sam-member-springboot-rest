import React, {useState, useEffect} from "react";
import axios from "axios";

import LoginModal from "../modal/LoginModal";
import LogoutButton from "../MemberComponent/LogoutButton";
import NaverLoginButton from "../MemberComponent/NaverLoginButton";
import KakaoLoginButton from "../MemberComponent/KakaoLoginButton";

import ToJoinPageButton from "../MemberComponent/ToJoinPageButton";

import NaverCallbackPage from "../MemberPages/NaverCallbackPage";
import KakaoCallbackPage from "../MemberPages/KakaoCallbackPage";

import MyPageButton from "../MemberComponent/MyPageButton";

import { Link } from "react-router-dom";

function Root({handleStorageChange, memberId}){

    return(

        <div>
            {memberId === null ? (
                <>
                    <LoginModal handleStorageChange={handleStorageChange} />
                    <ToJoinPageButton />
                </>
                    ) : (
                <>
                <LogoutButton handleStorageChange={handleStorageChange} />
                <MyPageButton member_id={memberId} />
                </>
            )}

            {memberId === "admin" && <Link to={"/admin"}>admin page</Link>}

        </div>

    )
}

export default Root;