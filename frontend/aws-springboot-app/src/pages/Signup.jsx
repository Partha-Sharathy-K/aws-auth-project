import { useState } from "react";
import { addUserApi } from "../services/authService";
import { useNavigate } from "react-router-dom";
import "../assets/login.css";

export default function Signup() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [msg, setMsg] = useState("");
    const navigate = useNavigate();

    const ROLE = "SUPERADMIN";
    const TEMP_PASSWORD = "Temp@123";

    const signup = async () => {
        try {
            await addUserApi({
                name,
                email,
                group: ROLE,
                temporaryPassword: TEMP_PASSWORD
            });

            setMsg("Superadmin created. Please login.");
            setTimeout(() => navigate("/"), 2000);

        } catch {
            setMsg("Signup failed");
        }
    };

    return (
    <div className="forgot-wrapper">
        <div className="forgot-card">
            <h2>Superadmin Signup</h2>
            <p className="subtitle">
                Create the primary administrator account
            </p>

            <input
                className="forgot-input"
                placeholder="Full Name"
                onChange={e => setName(e.target.value)}
            />

            <input
                className="forgot-input"
                placeholder="Email Address"
                onChange={e => setEmail(e.target.value)}
            />

            <div className="role-badge">
                Role: <strong>SUPERADMIN</strong>
            </div>

            <button className="primary-btn" onClick={signup}>
                Sign Up
            </button>

            {msg && <p className="success-msg">{msg}</p>}
        </div>
    </div>
);

}
