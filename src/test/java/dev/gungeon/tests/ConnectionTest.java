package dev.gungeon.tests;

import dev.gungeon.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class ConnectionTest {
    @Test
    @Order(1)
    void can_connect() {
        Connection conn = ConnectionUtil.createConnection();
        Assertions.assertNotNull(conn);
    }
}
