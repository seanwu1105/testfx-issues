import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.ParentMatchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class MyTest {

    private Pane pane;

    @Start
    private void start(final Stage stage) {
        pane = new Pane();
        setPaneOnClick();
        final var scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void setPaneOnClick() {
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> pane.getChildren().add(new Rectangle(event.getX(), event.getY(), 20, 20)));
    }

    @Test
    void testOne(final FxRobot robot) {
        robot.clickOn(pane);
        assertAll(
                () -> verifyThat(pane, ParentMatchers.hasChildren(1)),
                () -> assertThat(pane.getChildren().get(0), instanceOf(Rectangle.class))
        );
    }

    @Test
    void testTwo(final FxRobot robot) {
        robot.clickOn(pane);
        assertAll(
                () -> verifyThat(pane, ParentMatchers.hasChildren(1)),
                () -> assertThat(pane.getChildren().get(0), instanceOf(Rectangle.class))
        );
    }
}
