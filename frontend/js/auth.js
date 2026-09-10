const BASE_URL = "http://localhost:8080";

async function signup() {
    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        mobile: document.getElementById("mobile").value,
        password: document.getElementById("password").value,
        role: document.getElementById("role").value
    };

    const res = await fetch(BASE_URL + "/auth/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    const msg = await res.text();
    alert(msg);
}

async function login() {
    const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    const res = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    const user = await res.json();

    if (user && user.role === "USER") {
        localStorage.setItem("user", JSON.stringify(user));
        window.location.href = "user-dashboard.html";
    }
    else if (user && user.role === "MANAGER") {
        localStorage.setItem("user", JSON.stringify(user));
        window.location.href = "manager-dashboard.html";
    }
    else {
        alert("Invalid Login");
    }
}