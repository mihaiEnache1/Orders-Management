package presentation;

import java.lang.reflect.Field;

public class AbstractView<T> {
    /**
     * @param t which will represent a generic class
     * @return String[] which will represent the column's name of the table
     * This method will be used in the constructor of the client view and product view to set the column's name
     */
    public String[] columns(T t) {
        int i=0;
        String[] columnsName = new String[t.getClass().getDeclaredFields().length - 1];
        Field[] fields = t.getClass().getDeclaredFields();
        for(int j=1; j< fields.length; j++){
            fields[j].setAccessible(true);
            columnsName[i++] = fields[i].getName();
        }
        return columnsName;
    }
}
