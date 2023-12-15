const mainDrop = document.querySelectorAll(".main-drop-area li");

const subDrop = document.querySelectorAll(".sub-drop-area");

mainDrop.forEach((item, index) => {
  item.addEventListener("click", () => {
    changeSubMenu(index);
  });
});

function changeSubMenu(index) {
  subDrop.forEach((item) => (item.style.display = "none"));
  subDrop[index].style.display = "block";
}

document.addEventListener("mouseup", function (e) {
  var container = document.getElementById("banner-move-area");
  if (!container.contains(e.target)) {
    subDrop.forEach((item) => (item.style.display = "none"));
  }
});
