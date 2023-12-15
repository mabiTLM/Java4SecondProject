const yearMove = document.querySelectorAll(".year-area-inner");

const subYearMove = document.querySelectorAll(".sub-year-menu-area");

yearMove.forEach((item, index) => {
  item.addEventListener("click", () => {
    changeYearMenu(index);
  });
});

function changeYearMenu(index) {
  subYearMove.forEach((item) => (item.style.display = "none"));
  subYearMove[index].style.display = "block";
}
