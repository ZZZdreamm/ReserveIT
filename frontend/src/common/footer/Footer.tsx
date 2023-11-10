import Portal from "../Portal";
import "./style.scss";

export function Footer() {
  return (
    <div className="h-40 w-full ">
      <Portal>
        <footer>
          <h6>MayIT</h6>
          <span className="text-sm">kmultan1234@gmail.com</span>
          <span className="text-sm font-bold">
            Ta strona nie służy do prawdziwych korepetycji, jest tylko moim
            projektem
          </span>
          <span className="text-sm mb-4">
            ©2023 wykonanie MayIT. Stworzone przez Kacpra Multana
          </span>
        </footer>
      </Portal>
    </div>
  );
}
