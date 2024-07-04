import React, {useState, useEffect} from "react";
import axios from "axios";
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css';

import { useParams } from "react-router-dom";

import ListGroup from 'react-bootstrap/ListGroup';
import ToMainPageButton from "../component/ToMainPageButton";
import MemberDeleteButton from "../component/MemberDeleteButton";
import ToUpdatePageButton from "../component/ToUpdatePageButton";


// 회원 상세 보기 
function MyPage(){

    const {member_id} = useParams();

    const [member, setMember] = useState({});

    useEffect(() => {
        loadMember();
    }, []);

    const loadMember = async () => {

        try{
            const response = await axios.get(`/members/${member_id}`);
            console.log(response.data);
            setMember(response.data);

        } catch (error) {
            console.log('Select Detail Error: ', error);
        }
    }

    return(
        <div>
            <p>마이 페이지</p>
            <ListGroup>
                <ListGroup.Item>이름: {member.memberName}</ListGroup.Item>
                <ListGroup.Item>상태: {member.memberStatus}</ListGroup.Item>
                <ListGroup.Item>아이디: {member.memberId}</ListGroup.Item>
                <ListGroup.Item>이메일: {member.memberEmail}</ListGroup.Item>
                <ListGroup.Item>핸드폰: {member.memberPhone}</ListGroup.Item>
                <ListGroup.Item>생년월일: {member.memberBirth}</ListGroup.Item>
                <ListGroup.Item>주소: {member.memberAddress}</ListGroup.Item>
                <ListGroup.Item>매너온도: {member.memberRate}</ListGroup.Item>
                <ListGroup.Item>가입일자: {member.memberCreate}</ListGroup.Item>
            </ListGroup>

            <ToMainPageButton/>
            <ToUpdatePageButton />
            <MemberDeleteButton />

        </div>


    );
}

export default MyPage;