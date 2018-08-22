package classes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Table {
	protected static Database db = new Database("nsbm");
	protected static Connection con = db.getConnection();
	
	public static Connection getCon() {
		return con;
	}

	public static void setCon(Connection con) {
		Table.con = con;
	}

	public static ResultSet executeQuery(String query)
	{
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Object getPropertyValue(Field f)
	{
		try {
			return f.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Field getProperty(String property)
	{
		Class<?> callingClass = this.getClass();
		Field f = null;
		try {
			f = callingClass.getField(property);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	private String[] getColumns()
	{

		Class<?> callingClass = this.getClass();
		String dbTableFields[] = null;
		Field field;
		try {
			field = callingClass.getField("dbTableFields");
			dbTableFields = (String[])field.get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbTableFields;
	}
	
	public String createInsertStatement()
	{
		Class<?> callingClass= this.getClass();
		StringBuilder fields = new StringBuilder();
		StringBuilder vars = new StringBuilder();
		
		for(Field field : callingClass.getDeclaredFields())
		{
			String name = field.getName();
			if(fields.length()>1)
			{
				fields.append(",");
				vars.append(",");
			}
			if(name!="id")
			{
				vars.append("?");
				fields.append(name);
			}
		}
		
		String sql = "INSERT INTO " + callingClass.getSimpleName() +
				"(" + fields.toString() + ") " + 
				"VALUES(" + vars.toString() + ")";
		System.out.println(sql);
		return sql;
	}
	
	private PreparedStatement createInsertPreparedStatement()
	{
		Class<?> callingClass = this.getClass(); 
		String sql = createInsertStatement();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			Field fields[] = callingClass.getDeclaredFields();
			int index = 1;
			for(int i=0; i<fields.length; i++)				//Assumed the first field always is a auto incrementing primary key
			{
				Field field = fields[i];
				field.setAccessible(true);
				if((field.getName())=="id")
				{
					continue;
				}
				Object value = field.get(this);
				st.setObject((index), value);
				System.out.println(field.getName());
				index++;
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(st);
		return st;
	}
	
	public int insertRecord()
	{
		PreparedStatement st = createInsertPreparedStatement();
		try {
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			System.out.println(rs.getInt(1));
			return rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public static <T> List <T> query(String table, Class<T> type, String where)
	{
		List <T> list = new ArrayList<T>();
		String sql = "SELECT * FROM " + table;
		if(!(where==null || where.isEmpty()))
			sql+= " WHERE " + where;
		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next())
			{
//				@SuppressWarnings("deprecation")
				T t = type.getDeclaredConstructor().newInstance();
				loadResultSetIntoObject(rs, t);
				list.add(t);
			}
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		System.out.println(sql);
		return list;
	}

	private static void loadResultSetIntoObject(ResultSet rs, Object object) {
		// TODO Auto-generated method stub
		Class<?> cls = object.getClass();
		for(Field field : cls.getDeclaredFields())
		{
			String name = field.getName();
			field.setAccessible(true);
			Object value;
			try {
				value = rs.getObject(name);
				Class<?> type = field.getType();
//				if(isPremitive(type))
//				{
//					Class<?> boxed = boxPremitiveClass(type);
//					value = boxed.cast(value);
//					field.set(object, value);
//				}
				field.set(object, value);
			} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static Class<?> boxPremitiveClass(Class<?> type) {
		Class<?> cls = null;
		if(type==int.class)
			cls = Integer.class;
	    else if(type==long.class)
	    	cls = Long.class;
	    else if (type==double.class)
	    	cls = Double.class;
	    else if(type==float.class)
	    	cls = Float.class;
	    else if(type==boolean.class)
	    	cls = Boolean.class;
	    else if(type==byte.class)
	    	cls = Byte.class;
	    else if(type==char.class)
	    	cls = Character.class;
	    else if(type==short.class)
	    	cls = Short.class;
	    else
	    {
	        String string="class '" + type.getName() + "' is not a primitive";
	        System.out.println(string);
	    }
		return cls;
	}

	private static boolean isPremitive(Class<?> type) {
		return(
				type==int.class || type==long.class
				 || type==double.class  || type==float.class
	             || type==boolean.class || type==byte.class
	             || type==char.class || type==short.class
				);
	}
	
	public static String createUpdateStatementSql(Class<?> cls, String table, String pk)
	{
		StringBuilder sets = new StringBuilder();
		String where = null;
		
		for(Field field : cls.getDeclaredFields())
		{
			String name = field.getName();
			String pair = name + " = ?";
			if(name.equals(pk))
			{
				where = " WHERE " + pair;
			}
			else
			{
				if(sets.length()>1)
					sets.append(", ");
				sets.append(pair);
			}
		}
		if(where==null)
		{
			System.out.println("No pk found");
		}
		String sql = "UPDATE " + table + " SET " + sets.toString() + where;
//		System.out.println(sql);
		return sql;
	}
	
	public static PreparedStatement createPreparedUpdateStatement(Object obj, String table, String pk)
	{
		PreparedStatement st = null;
		Class<?> cls = obj.getClass();
		
		String sql = createUpdateStatementSql(cls, table, pk);
		try {
			st = con.prepareStatement(sql);
			
			Field fields[] = cls.getDeclaredFields();
			int pkSequence = fields.length;
			
			for(int i=0; i<fields.length; i++)
			{
				Field field = fields[i];
				field.setAccessible(true);
				Object value = field.get(obj);
				
				String name = field.getName();
				if(name.equals(pk))
					st.setObject(pkSequence, value);
				else
					st.setObject(i, value);
			}
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	
	public void updateRecord()
	{
		Object obj = this;
		String table = this.getClass().getSimpleName();
		String pk = "id";
		
		PreparedStatement ps = createPreparedUpdateStatement(obj, table, pk);
		try {

			System.out.println(ps);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement createDeleteStatement(Object obj, String table, String pk)
	{
		String sql = "DELETE FROM " + table + " WHERE " + pk + "=?";
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			
			Object value = obj.getClass().getField(pk).get(obj);
			st.setObject(1, value);
		} catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	
	public void deleteRecord()
	{
		PreparedStatement ps = createDeleteStatement(this, this.getClass().getSimpleName(), "id");
		try {
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ps);
	}
}
