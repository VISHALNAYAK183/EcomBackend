const carousel = document.getElementById("carousel");

fetch("http://localhost:8080/api/products")
  .then((res) => res.json())
  .then((data) => {
    const hotDeals = data.filter(p => p.hotdeal === "Y");

    hotDeals.forEach(product => {
      const slide = document.createElement("div");
      slide.className = "slide";
      slide.innerHTML = `
        <img src="${product.imgurl}" alt="${product.heading}" />
        <div class="heading">${product.heading}</div>
      `;
      carousel.appendChild(slide);
    });

    startCarousel(hotDeals.length);
  });

function startCarousel(length) {
  let index = 0;
  setInterval(() => {
    index = (index + 1) % length;
    document.getElementById("carousel").style.transform = `translateX(-${index * 100}vw)`;
  }, 3000);
}
