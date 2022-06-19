import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import DashboardPage from './pages/admin/DashboardPage';
import HomePage from './pages/HomePage';
import SignInPage from './pages/admin/SignInPage';

const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/admin" element={<DashboardPage />} />
                <Route path="/admin/sign-in" element={<SignInPage />} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRouter;
