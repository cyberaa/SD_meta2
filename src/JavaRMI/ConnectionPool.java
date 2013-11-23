
package JavaRMI;
import java.util.*;
import java.sql.*;
/**
 * @author Bruno Caceiro - caceiro@student.dei.uc.pt
 * @author David Cardoso - davidfpc@student.dei.uc.pt
 * @version 0.1
 * @project Sistemas Distribuidos
 */


class ConnectionPool {
    private final int initialConnections = 5;
    private final ArrayList connectionsAvailable;
    private final String connectionUrl ;
    private final String userName;
    private final String userPassword ;

    public ConnectionPool() throws SQLException {
        connectionsAvailable = new ArrayList();
        this.connectionUrl = "jdbc:oracle:thin:@"+ServerRMI.ipAddr+":"+ServerRMI.port+":XE";
        this.userName = "sd";
        this.userPassword = "sd2013";
        for (int count = 0; count < initialConnections; count++) {
            connectionsAvailable.add(getnewConnection());
        }
    }


    private Connection getnewConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, userName, userPassword);
    }


    public synchronized Connection getConnection() throws SQLException {
        Connection newConnection;
        while(connectionsAvailable.size()>initialConnections){
            newConnection =(Connection) connectionsAvailable.get(connectionsAvailable.size()-1);
            connectionsAvailable.remove(newConnection);
            newConnection.close();
        }
        while(connectionsAvailable.size()<initialConnections){
            newConnection = getnewConnection();
            connectionsAvailable.add(newConnection);
        }
        newConnection = (Connection) connectionsAvailable.get(connectionsAvailable.size() - 1);
        connectionsAvailable.remove(newConnection);
        return newConnection;
    }

    public synchronized void  releaseConnection(Connection conn) throws SQLException {
        conn.rollback();
        connectionsAvailable.add(conn);
    }


}
