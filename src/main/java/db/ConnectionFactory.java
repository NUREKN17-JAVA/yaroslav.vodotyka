package db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection CreateConnection() throws DatabaseException;
}
