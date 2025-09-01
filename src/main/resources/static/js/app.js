// Custom JS за интерактивност

document.addEventListener("DOMContentLoaded", function() {
  console.log("MaxWebPro сайтът е зареден успешно!");

  // Пример: плавно скролиране към anchor линкове
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener("click", function(e) {
      e.preventDefault();
      document.querySelector(this.getAttribute("href")).scrollIntoView({
        behavior: "smooth"
      });
    });
  });
});
