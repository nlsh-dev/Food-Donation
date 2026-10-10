const firebaseConfig = {
  apiKey: "AIzaSyD9Huypn-CntVOyYj1Pq8pYkqa-7JJFhz8",
  authDomain: "aanara-food-donation.firebaseapp.com",
  projectId: "aanara-food-donation",
  storageBucket: "aanara-food-donation.firebasestorage.app",
  messagingSenderId: "325834087942",
  appId: "1:325834087942:web:952c8fb106166130f4ed87",
  measurementId: "G-0FRENYW28D"
};

firebase.initializeApp(firebaseConfig);

const db = firebase.firestore();
const auth = firebase.auth();