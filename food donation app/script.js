/* Scroll to the donation section */

const API_URL = "http://localhost:5001/api";

async function apiRequest(path, options = {}) {
  const token = localStorage.getItem("annaraToken");
  const headers = { "Content-Type": "application/json", ...(options.headers || {}) };
  if (token) headers.Authorization = `Bearer ${token}`;
  const response = await fetch(`${API_URL}${path}`, { ...options, headers });
  const data = await response.json().catch(() => ({}));
  if (!response.ok) throw new Error(data.message || "The server could not complete this request.");
  return data;
}

function scrollToDonations() {
  document.getElementById("donations").scrollIntoView({
    behavior: "smooth"
  });
}

/* Donation popup */

function showDonationForm() {
  const popup = document.getElementById("popup");

  if (popup) {
    popup.style.display = "flex";
  }
}

function closePopup() {
  const popup = document.getElementById("popup");

  if (popup) {
    popup.style.display = "none";
  }
}

/* Claim a donation */

function claimFood(button) {
  button.innerText = "Donation Claimed ✓";
  button.style.backgroundColor = "#167055";
  button.style.color = "white";
  button.disabled = true;

  alert(
    "Donation claimed successfully! The hotel and volunteer have been notified."
  );
}

/* Partner button */

function showPartnerMessage() {
  alert(
    "Thank you for your interest! Partner registration will be available soon."
  );
}

/* Add food donation to the Node.js and MySQL backend */

async function addDonation() {
  const foodName = document.getElementById("foodName").value.trim();
  const foodType = document.getElementById("foodType").value;
  const foodDescription = document.getElementById("foodDescription").value.trim();
  const mealCount = document.getElementById("mealCount").value;
  const hotelName = document.getElementById("hotelName").value.trim();
  const city = document.getElementById("donationCity").value.trim();
  const pickupAddress = document.getElementById("pickupAddress").value.trim();
  const pickupBefore = document.getElementById("pickupBefore").value;

  if (!foodName || !foodType || !mealCount || !hotelName || !city || !pickupAddress || !pickupBefore) {
    alert("Please fill in all fields.");
    return;
  }

  const savedUser = JSON.parse(localStorage.getItem("annaraUser") || "null");
  if (!localStorage.getItem("annaraToken") || !savedUser) {
    alert("Please sign in before publishing a donation.");
    window.location.href = "login.html";
    return;
  }
  if (savedUser.role !== "restaurant") {
    alert("Only a Restaurant / Hotel account can publish a food donation.");
    return;
  }

  try {
    await apiRequest("/donations", {
      method: "POST",
      body: JSON.stringify({
      foodName: foodName,
      description: foodDescription,
      foodType: foodType,
      mealCount: Number(mealCount),
      pickupAddress: pickupAddress,
      pickupBefore: pickupBefore
      })
    });

    document.querySelector('form[onsubmit*="addDonation"]').reset();

    closePopup();

    alert(
      "Donation saved online! Nearby NGOs, shelters, and volunteers can now see it."
    );
  } catch (error) {
    console.error("Error adding donation:", error);
    alert(error.message);
  }
}

/* Read donations from the Node.js and MySQL backend */

async function loadDonationsFromBackend() {
  const donationList = document.getElementById("donationList");
  if (!donationList) return;
  try {
    const donations = await apiRequest("/donations");
    donationList.innerHTML = "";
    donations.forEach(function (donation) {
      const newCard = document.createElement("div");
      newCard.className = "donation-card";
      newCard.innerHTML = `
          <img
            class="food-image"
            src="images/veg-thali.jpg"
            alt="Food donation"
          >
          <p class="status available">${escapeHtml((donation.status || "available").toUpperCase())}</p>
          <h3>${escapeHtml(donation.foodName || "Food donation")}</h3>
          <p>${escapeHtml(donation.restaurantName || "Partner")}, ${escapeHtml(donation.city || "")}</p>
          <p><strong>${Number(donation.mealCount) || 0} meals</strong> · ${escapeHtml(formatPickupTime(donation.pickupBefore))}</p>
          <button onclick="claimFood(this)">Claim Donation</button>
        `;
      donationList.appendChild(newCard);
    });
  } catch (error) {
    console.error("Error loading donations:", error);
    donationList.innerHTML = "<p>Could not load donations. Check that the backend is running.</p>";
  }
}

/* Login */

const loginForm = document.getElementById("loginForm");

if (loginForm) {
  loginForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const role = document.getElementById("role").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const loginMessage = document.getElementById("loginMessage");

    if (role === "" || email === "" || password === "") {
      loginMessage.innerText = "Please fill in all fields.";
      loginMessage.style.color = "red";
      return;
    }

    try {
      const result = await apiRequest("/auth/login", {
        method: "POST",
        body: JSON.stringify({ email, password })
      });
      const savedRole = result.user.role;
      if (savedRole !== role) {
        loginMessage.innerText = "Please choose the role used when you registered.";
        loginMessage.style.color = "red";
        return;
      }

      localStorage.setItem(
        "annaraUser",
        JSON.stringify({
          role: savedRole,
          email: email,
          fullName: result.user.fullName || ""
        })
      );
      localStorage.setItem("annaraToken", result.token);

      loginMessage.innerText = "Login successful!";
      loginMessage.style.color = "green";

      setTimeout(function () {
        window.location.href = "index.html";
      }, 1000);

    } catch (error) {
      loginMessage.innerText = "Login failed: " + error.message;
      loginMessage.style.color = "red";
    }
  });
}

/* Registration message */

function showRegisterMessage() {
  alert(
    "Registration will be available soon. Please contact the Annara admin team."
  );
}

/* Show logged-in user */

function showLoggedInUser() {
  const savedUser = localStorage.getItem("annaraUser");
  const welcomeUser = document.getElementById("welcomeUser");

  if (savedUser && welcomeUser) {
    const user = JSON.parse(savedUser);
    welcomeUser.innerText = user.role;
  }
}

/* Logout */

function logoutUser() {
  localStorage.removeItem("annaraUser");
  localStorage.removeItem("annaraToken");
  alert("You have logged out successfully.");
  window.location.href = "login.html";
}

/* Notification */

function markNotificationRead() {
  const notificationBox = document.querySelector(".notification-box");
  const notificationTitle = document.getElementById("notificationTitle");
  const notificationText = document.getElementById("notificationText");
  const notificationButton = document.querySelector(".notification-btn");

  if (!notificationBox) {
    return;
  }

  notificationBox.style.background = "#f3f3f3";
  notificationBox.style.borderColor = "#dddddd";

  notificationTitle.innerText = "Notification marked as read";
  notificationText.innerText =
    "You will receive a new alert when food is available near you.";

  notificationButton.innerText = "Read ✓";
  notificationButton.disabled = true;
  notificationButton.style.background = "#dddddd";
  notificationButton.style.color = "#64736d";
  notificationButton.style.cursor = "not-allowed";
}

/* Start website features */

showLoggedInUser();
loadDonationsFromBackend();
const registerForm = document.getElementById("registerForm");

if (registerForm) {
  registerForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const role = document.getElementById("registerRole").value;
    const fullName = document.getElementById("registerName").value.trim();
    const organisationName = document.getElementById("registerOrganisation").value.trim();
    const city = document.getElementById("registerCity").value.trim();
    const phone = document.getElementById("registerPhone").value.trim();
    const email = document.getElementById("registerEmail").value.trim();
    const password = document.getElementById("registerPassword").value;
    const registerMessage = document.getElementById("registerMessage");

    if (!role || !fullName || !organisationName || !city || !phone || !email || !password) {
      registerMessage.innerText = "Please fill in all fields.";
      registerMessage.style.color = "red";
      return;
    }

    if (password.length < 6) {
      registerMessage.innerText =
        "Password must contain at least 6 characters.";
      registerMessage.style.color = "red";
      return;
    }

    try {
      await apiRequest("/auth/register", {
        method: "POST",
        body: JSON.stringify({
          fullName,
          email,
          phone,
          password,
          role,
          city,
          organisationName
        })
      });

      registerMessage.innerText =
        "Account created successfully! Please sign in.";
      registerMessage.style.color = "green";

      setTimeout(function () {
        window.location.href = "login.html";
      }, 1500);

    } catch (error) {
      console.error(error);

      if (error.message.includes("already has an account")) {
        registerMessage.innerText =
          "This email already has an account. Please sign in.";
      } else if (error.message === "Failed to fetch") {
        registerMessage.innerText =
          "Cannot reach the backend. In VS Code, open the backend folder and run: npm run dev";
      } else {
        registerMessage.innerText = "Registration failed: " + error.message;
      }

      registerMessage.style.color = "red";
    }
  });
}
let trackingProgress = 35;
let trackingInterval;

function openTracking(restaurant = "JW Marriott Hotel Bengaluru", city = "Bengaluru") {
  window.location.href = "live-tracking.html";
  return;
  const trackingModal = document.getElementById("trackingModal");

  if (!trackingModal) {
    return;
  }

  const location = restaurant + ", " + city;
  const locationLabel = document.getElementById("trackingLocation");
  const map = document.getElementById("restaurantMap");
  const mapLink = document.getElementById("openGoogleMap");
  if (locationLabel) locationLabel.innerHTML = "<b>Pickup location:</b> " + location;
  if (map) map.src = "https://www.google.com/maps?q=" + encodeURIComponent(location) + "&output=embed";
  if (mapLink) mapLink.href = "https://www.google.com/maps/search/?api=1&query=" + encodeURIComponent(location);

  trackingModal.style.display = "flex";

  trackingProgress = 35;

  updateTrackingScreen();

  clearInterval(trackingInterval);

  trackingInterval = setInterval(function () {
    if (trackingProgress < 92) {
      trackingProgress += 4;

      updateTrackingScreen();
    } else {
      clearInterval(trackingInterval);

      const trackingStatus =
        document.querySelector(".tracking-status");

      if (trackingStatus) {
        trackingStatus.innerHTML =
          "✅ Ravi has arrived at Asha Child Shelter.";
      }
    }
  }, 2000);
}

function closeTracking() {
  const trackingModal = document.getElementById("trackingModal");

  if (trackingModal) {
    trackingModal.style.display = "none";
  }

  clearInterval(trackingInterval);
}

document.addEventListener("click", function (event) {
  const trackingModal = document.getElementById("trackingModal");
  if (trackingModal && event.target === trackingModal) {
    closeTracking();
  }
});

document.addEventListener("keydown", function (event) {
  if (event.key === "Escape") {
    closeTracking();
  }
});

function updateTrackingScreen() {
  const routeProgress = document.getElementById("routeProgress");
  const liveRider = document.getElementById("liveRider");
  const trackingTime = document.getElementById("trackingTime");
  const minutesLeft = Math.max(
    1,
    Math.ceil((100 - trackingProgress) / 8)
  );

  if (routeProgress) {
    routeProgress.style.width = trackingProgress + "%";
  }

  if (liveRider) {
    const riderPosition = 17 + (trackingProgress * 0.65) - 5;
    liveRider.style.left = riderPosition + "%";
  }

  if (trackingTime) {
    trackingTime.innerText = minutesLeft;
  }
}

function escapeHtml(value) {
  const element = document.createElement("div");
  element.textContent = String(value);
  return element.innerHTML;
}

function normalizeRole(role) {
  const value = String(role || "").toLowerCase();
  if (value.includes("restaurant") || value.includes("hotel")) return "restaurant";
  if (value.includes("ngo") || value.includes("shelter")) return "ngo";
  if (value.includes("volunteer") || value.includes("driver")) return "driver";
  if (value.includes("admin")) return "admin";
  return value;
}

function formatPickupTime(timestamp) {
  if (!timestamp || typeof timestamp.toDate !== "function") return "Available now";
  return "Pickup before " + timestamp.toDate().toLocaleString([], {
    day: "numeric", month: "short", hour: "numeric", minute: "2-digit"
  });
}

function setupDonationSearch() {
  const searchBox = document.getElementById("donationSearch");
  const cityBox = document.getElementById("cityFilter");
  if (!searchBox || !cityBox) return;

  function filterCards() {
    const searchText = searchBox.value.trim().toLowerCase();
    const cityText = cityBox.value.trim().toLowerCase();
    const cards = document.querySelectorAll(
      "article.donation-card, #donationList .donation-card"
    );

    cards.forEach(function (card) {
      const content = card.textContent.toLowerCase();
      const matchesName = !searchText || content.includes(searchText);
      const matchesCity = !cityText || content.includes(cityText);
      card.style.display = matchesName && matchesCity ? "" : "none";
    });
  }

  searchBox.addEventListener("input", filterCards);
  cityBox.addEventListener("change", filterCards);
}

// The page has an older search handler in its HTML. Disable it so it cannot
// overwrite the reliable search handler above.
window.filterDonationCards = function () {};
setupDonationSearch();
