import { createContext, useContext, useState } from "react";
import { clearTokens } from "../utils/auth";
import { Navigate } from "react-router-dom";

const AuthContext = createContext();

export function AuthProvider({ children }) {
    const [isAuthenticated, setIsAuthenticated] = useState(
        !!localStorage.getItem("accessToken")
    );
    const login = () => setIsAuthenticated(true);

    const logout = () => {
        clearTokens();
        setIsAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}