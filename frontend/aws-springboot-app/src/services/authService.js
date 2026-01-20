const BASE_URL = "http://localhost:8080/auth";

export async function loginApi(email, password) {
    const res = await fetch(`${BASE_URL}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    const data = await res.json();
    if (!res.ok) throw new Error(data.message || "Login failed");
    return data;
}

export async function newPasswordApi(email, newPassword, sessionToken) {
    const res = await fetch(`${BASE_URL}/new-password`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, newPassword, sessionToken })
    });

    const data = await res.json();
    if (!res.ok) throw new Error("Password update failed");
    return data;
}

export async function forgotPasswordApi(email) {
    const res = await fetch(`${BASE_URL}/forgot-password`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email })
    });

    if (!res.ok) throw new Error("OTP send failed");
}

export async function confirmForgotPasswordApi(email, otp, newPassword) {
    const res = await fetch(`${BASE_URL}/confirm-forgot-password`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, otp, newPassword })
    });

    if (!res.ok) throw new Error("Password reset failed");
}

export async function logoutApi(accessToken) {
    await fetch(`${BASE_URL}/logout`, {
        method: "POST",
        headers: {
            Authorization: `Bearer ${accessToken}`
        }
    });
}

export async function addUserApi(payload) {
    const res = await fetch(`${BASE_URL}/add-user`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });

    if (!res.ok) throw new Error("User creation failed");
}