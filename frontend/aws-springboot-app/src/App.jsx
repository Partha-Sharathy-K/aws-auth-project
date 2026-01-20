import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Dashboard from "./pages/Dashboard";
import ProtectedRoute from "./utils/ProtectedRoute.jsx";
import { AuthProvider } from "./hooks/useAuth.jsx";
import NewPassword from "./pages/NewPassword";
import ForgotPassword from "./pages/ForgotPassword";

function App() {
    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="/signup" element={<Signup />} />
                    <Route
                        path="/dashboard"
                        element={
                            <ProtectedRoute>
                                <Dashboard />
                            </ProtectedRoute>
                        }
                    />
                    <Route path="/new-password" element={<NewPassword />} />
                    <Route path="/forgot-password" element={<ForgotPassword />} />
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;
