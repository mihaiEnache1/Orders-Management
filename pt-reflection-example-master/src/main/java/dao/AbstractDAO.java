package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * @param field the field's name used in the WHERE clause
	 * @return String which represents the query that will be executed when needed to select those objects that have a specific id
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * @param field the field's name used in the WHERE clause
	 * @return String which represents the query that will be executed when needed to select those objects that have a specific name
	 */
	private String createSelectNameQuery(String field) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ").append(type.getSimpleName());
		query.append(" WHERE ").append(field).append( " =?");
		return query.toString();
	}

	/**
	 * @return String which represents the query to select all the objects from database
	 */
	private String createFindAllQuery(){
		return "SELECT " + " * " + " FROM " + type.getSimpleName();
	}

	/**
	 * @param t generic object used for reflexion technique
	 * @return String which represents the query used to insert objects into database
	 */
	private String createInsertQuery(T t) {
		StringBuilder query = new StringBuilder("INSERT INTO " + type.getSimpleName() + " (");
		Field[] fields = t.getClass().getDeclaredFields();
		for(int i=1; i<fields.length; i++) {
			if(i != t.getClass().getDeclaredFields().length - 1) {
				query.append(fields[i].getName()).append(",");
			} else {
				query.append(fields[i].getName()).append(")");
			}
		}
		query.append("VALUES (");
		for(int i=1; i< fields.length; i++) {
			if(i != fields.length - 1) {
				query.append("?,");
			} else {
				query.append("?)");
			}
		}
		return query.toString();
	}

	/**
	 * @param fields an array of the fields of a class used to get the properties of an object and update those columns
	 * @param field the field's name used in the WHERE clause
	 * @return String which represents the query used to update data from database
	 */
	private String createUpdateQuery(Field[] fields, String field) {
		StringBuilder query = new StringBuilder("UPDATE " + type.getSimpleName() + "\nSET ");
		for(int i=1; i< fields.length; i++) {
			if(i != fields.length - 1) {
				query.append(fields[i].getName()).append(" =?, ");
			} else {
				query.append(fields[i].getName()).append(" =?");
			}
		}
		query.append("\nWHERE " + field + " =?");
		return query.toString();
	}

	/**
	 * @param field the field's name used in the WHERE clause
	 * @return String which represents the query used to delete data from database
	 */
	private String createDeleteQuery(String field){
		return "DELETE FROM " + type.getSimpleName() + " WHERE " + field + " =?";
	}

	/**
	 * @return List which represents a list of objects
	 * This is a generic method that connects to the database and retrieve all the data from a table
	 */
	public List<T> findAll() {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * @param id which represents the id of the object that we want to create from database
	 * @return an object returned by the query
	 * This is a generic method that connects to the database and retrieves the object with the given id (which is unique)
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * @param name which represents the name of the object that we want to create from database
	 * @return an object returned by the query
	 * This is a generic method that connects to the database and retrieves the object with the given name
	 */
	public T findByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectNameQuery("name");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * @param resultSet - result of the query
	 * @return List<T> - list of objects with the data retrieved by the query
	 * This is a generic method that creates a list of objects using reflexion technique from the result provided by the query
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param t an object that we want to insert into database
	 * @return 1 if the insert operation executed successfully, 0 if not
	 * This is a generic method that creates the query calling the method "createInsertQuery" and executes the insert operation into database
	 * The columns are given using reflexion technique
	 */
	public int insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			Field[] fields = t.getClass().getDeclaredFields();
			for(int i=1; i< fields.length; i++) {
				if(fields[i].getType().getName().equals("int")) {
					fields[i].setAccessible(true);
					statement.setInt(i, fields[i].getInt(t));
				} else {
					fields[i].setAccessible(true);
					statement.setString(i, (String) fields[i].get(t));
				}
			}
			return statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}

	/**
	 * @param t an object whose properties we want to change into database
	 * @param id the object's id used in the WHERE clause to find the object (the id of the object is unique)
	 * @return 1 if the update operation executed successfully, 0 if not
	 * This is a generic method that connects to the database and execute the update operation to an object, updating the data into database of that object
	 * The column's name are given using reflexion technique
	 */
	public int update(T t, int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		Field[] fields = t.getClass().getDeclaredFields();
		String query = createUpdateQuery(fields,"id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i;
			for(i=1; i< fields.length; i++) {
				if(fields[i].getType().getName().equals("int")) {
					fields[i].setAccessible(true);
					statement.setInt(i, fields[i].getInt(t));
				} else {
					fields[i].setAccessible(true);
					statement.setString(i, (String) fields[i].get(t));
				}
			}
			statement.setInt(i, id);

			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param id the object's id that we want to delete
	 * @return 1 if the delete operation executed successfully, 0 if not
	 * This is a generic method that connects to the database and executes the delete operation of an object from database
	 */
	public int delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return 0;
	}
}
