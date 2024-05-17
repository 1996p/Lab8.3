module ru.vorotov.simulationslab83 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vorotov.simulationslab83 to javafx.fxml;
    exports ru.vorotov.simulationslab83;
}