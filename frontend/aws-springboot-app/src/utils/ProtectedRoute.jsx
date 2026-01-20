import { Navigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth.jsx";

export default function ProtectedRoute({ children }) {
    const { isAuthenticated } = useAuth();
    return isAuthenticated ? children : <Navigate to="/" />;
}
