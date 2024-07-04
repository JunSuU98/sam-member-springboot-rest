import React from "react";
import * as ReactDOM from "react-dom/client";
import {createBrowserRouter, RouterProvider} from "react-router-dom";

import Root from "../MemberPages/RootPage";
import MyPage from "../MemberPages/MyPage";
import MemberUpdatePage from "../MemberPages/MemberUpdatePage";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />
    },
    {
        path: "/members/:member_id",
        element: <MyPage />
    },
    {
        path: "/members/update/:member_id",
        element: <MemberUpdatePage />
    },
    {
        path: "",
        element: <div></div>
    },
    {
        path: "",
        element: <div></div>
    },
]);

export default router;
