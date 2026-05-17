function toggleTheme() {
    const html = document.documentElement;
    html.classList.toggle("dark");

    localStorage.setItem(
        "theme",
        html.classList.contains("dark") ? "dark" : "light"
    );
}

document.addEventListener("DOMContentLoaded", () => {
    if (localStorage.getItem("theme") === "dark") {
        document.documentElement.classList.add("dark");
    }
});

let index = 0;

window.addEventListener("load", () => {
  const slider = document.getElementById("slider");
  const total = document.querySelectorAll(".slide-img").length;

  setInterval(() => {
    index++;

    if (index >= total) {
      index = 0;
    }

    slider.style.transform = `translateX(-${index * 100}%)`;

  }, 3000);
});
window.addEventListener("load", () => {
  const audio = document.getElementById("welcomeAudio");

  audio.play().catch(() => {
    // अगर autoplay block हुआ
    document.addEventListener("click", () => {
      audio.play();
    }, { once: true });
  });

  // 👉 सिर्फ 10-15 sec चलाना है
  setTimeout(() => {
    audio.pause();
    audio.currentTime = 0;
  }, 12000);
});

const facilityData = {

    computer: {
        title: "💻 Computer Lab",
        text: "Our computer lab contains advanced systems with internet and practical learning support.",
        images: [
            "/images/computer1.jpg",
            "/images/computer2.jpg",
            "/images/computer3.jpg"
        ]
    },

    library: {
        title: "📚 Library",
        text: "Large collection of books and academic resources.",
        images: [
            "/images/library1.jpg",
            "/images/library2.jpg",
            "/images/library3.jpg"
        ]
    },

    science: {
        title: "🧪 Science Labs",
        text: "Modern laboratories for practical learning.",
        images: [
            "/images/lab1.jpg",
            "/images/lab2.jpg",
            "/images/lab3.jpg",
            "/images/lab4.jpg"
        ]
    },

    stage: {
        title: "🎤 Stage Facility",
        text: "Professional stage for seminars and events.",
        images: [
            "/images/stage1.jpg",
            "/images/stage2.jpg",
             "/images/stage3.jpg"
        ]
    },

    canteen: {
        title: "🍔 Canteen",
        text: "Clean and hygienic food facility for students.",
        images: [
            "/images/canteen1.jpg"
        ]
    },

    bus: {
        title: "🚌 Bus Facility",
        text: "Safe and comfortable transportation.",
        images: [
            "/images/bus1.jpg",
            "/images/bus2.jpg"
        ]
    }

};

let currentSlide = 0;
let currentImages = [];

function openFacilityPopup(type) {

    const data = facilityData[type];

    currentImages = data.images;

    currentSlide = 0;

    document.getElementById("popupTitle").innerText = data.title;

    document.getElementById("popupText").innerText = data.text;

    const slider = document.getElementById("popupSlider");

    slider.innerHTML = "";

    currentImages.forEach(img => {

        slider.innerHTML +=
        '<img src="' + img + '" class="w-full min-w-full h-[260px] md:h-[500px] object-cover">';

    });

    updatePopupSlider();

    document.getElementById("facilityPopup").classList.remove("hidden");

    document.getElementById("facilityPopup").classList.add("flex");
}

function closeFacilityPopup() {

    document.getElementById("facilityPopup").classList.add("hidden");

    document.getElementById("facilityPopup").classList.remove("flex");
}

function slidePopup(direction) {

    currentSlide += direction;

    if(currentSlide < 0) {
        currentSlide = currentImages.length - 1;
    }

    if(currentSlide >= currentImages.length) {
        currentSlide = 0;
    }

    updatePopupSlider();
}

function updatePopupSlider() {

    document.getElementById("popupSlider").style.transform =
    `translateX(-${currentSlide * 100}%)`;
}
const menuBtn = document.getElementById("menuBtn");
 const closeBtn = document.getElementById("closeBtn");
 const mobileMenu = document.getElementById("mobileMenu");
 const menuOverLay = document.getElementById("menuOverLay");

 function openMenu() {

     if (mobileMenu) {
         mobileMenu.classList.remove("-left-full");
         mobileMenu.classList.add("left-0");
     }

     if (menuOverLay) {
         menuOverLay.classList.remove("hidden");
     }
 }

 function closeMenu() {

     if (mobileMenu) {
         mobileMenu.classList.remove("left-0");
         mobileMenu.classList.add("-left-full");
     }

     if (menuOverLay) {
         menuOverLay.classList.add("hidden");
     }
 }

 if (menuBtn) {
     menuBtn.addEventListener("click", openMenu);
 }

 if (closeBtn) {
     closeBtn.addEventListener("click", closeMenu);
 }

 if (menuOverLay) {
     menuOverLay.addEventListener("click", closeMenu);
 }

 window.addEventListener("load", () => {

     if (mobileMenu) {
         mobileMenu.classList.remove("left-0");
         mobileMenu.classList.add("-left-full");
     }

     if (menuOverLay) {
         menuOverLay.classList.add("hidden");
     }

 });