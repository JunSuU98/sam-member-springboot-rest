import React, {useState, useEffect} from "react";
import axios from "axios";
import MemberTable from "../MemberComponent/MemberTable";
import Paging from "../MemberComponent/Paging";
import SearchForm from "../MemberComponent/SearchForm";

function Root(){

    const [memberList, setMemberList] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [currentPage, setCurrentPage] = useState(1);

	// 컴포넌트가 처음 마운트될 때 회원 목록을 불러오는 useEffect 훅을 선언한다
	// useEffect(() => {
	// 	loadMembers();
	// }, []);

	const loadMembers = async () => {
		try{
			const response = await axios.get("/members");
			setMemberList(response.data.memberList);
            setTotalPage(response.data.totalPage);
            setCurrentPage(response.data.currentPage)

			console.log(response.data);
		} catch (error) {
			alert('회원이 없습니다');
			console.log(error.message);
		}
	}


    return(

        <div>
            {/* <MemberTable memberList={memberList}/> */}

			<SearchForm />

            {/* <Paging totalPage={totalPage} currentPage={currentPage}/> */}
        </div>

    )
}

export default Root;