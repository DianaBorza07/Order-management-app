package bll;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Borza Diana-Cristina
 * Clasa specifica pentru crearea tabelului si a headerului de tabel folosind reflection
 */
public class TableBLL {
    /**
     * Metoda care returneaza tabelul sub forma unei liste de liste de stringuri
     * @param objects Obiectele pe care vrem sa le adaugam in tabel
     * @return tabelul sub forma String
     * @throws IllegalAccessException
     */
    public List<List<String>> generateTable(List<Object> objects) throws IllegalAccessException {
        List<String> columnFields = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();
        Object object = objects.get(0);
        for (Field f:object.getClass().getDeclaredFields()  // get table header
             ) { f.setAccessible(true);
                 columnFields.add(f.getName()); }
        rows.add(columnFields); // table header will be the first element in the list
        for (Object obj : objects
             ) {  List<String> list = new ArrayList<>();
            for (Field f: object.getClass().getDeclaredFields()
                 ) { f.setAccessible(true);
                 list.add(f.get(obj).toString());
            }
            rows.add(list);
        }
        return rows;
    }

}
