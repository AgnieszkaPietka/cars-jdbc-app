package agnieszka.homework;

import java.util.HashMap;
import java.util.Map;

public interface DaoProvider {

    Map<String, String> whatDao = new HashMap<>();

    static Dao getDao(Sources source) {
        switch (source) {
            case DB:
                whatDao.clear();
                whatDao.put("currentDao", "Baza produkcyjna");
                return new DBDaoImplementation();
            case TEST:
                whatDao.clear();
                whatDao.put("currentDao", "Kolekcja testowa");
                return new Test();
            default:
                return null;
        }
    }
}