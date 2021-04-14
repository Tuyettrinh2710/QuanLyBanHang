module com.mycompany.qlbanhang {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.qlbanhang to javafx.fxml;
    exports com.mycompany.qlbanhang;
    exports com.mycompany.pojo;
}
