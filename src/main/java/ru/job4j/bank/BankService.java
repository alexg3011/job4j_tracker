package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает функции банковской системы:
 * добавление нового пользователя,
 * создание для него аккаунта,
 * поиск пользователя по паспорту,
 * поиск пользователя по реквизитам,
 * перевод денег с одного счета на другой.
 */
public class BankService {
    /**
     * Создаем хранилище для информации о пользователях:
     * ключ - данные о пользователе, значение - набор сведений об аккаунте.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового user в хранилище.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод ищет пользователя по паспорту
     * и если находит, проверяет есть ли у него данные аккаунта и если нет,
     * добавляет сведения в хранилище.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAc = users.get(user);
            if (!userAc.contains(account)) {
                userAc.add(account);
            }
        }
    }

    /**
     *
     * @param passport поиск по введенному паспорту.
     * @return возвращает паспортные данные в случае нахождения
     * пользователя в базе, и null в случае отсутствия.
     */
    public User findByPassport(String passport) {
        User rsl = null;

        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод ищет пользователя в хранилище по введенным данным паспорта и реквизитам счета:
     * сначала находит пользователя по паспорту, в случае успеха
     * ищет в хранилище данные об аккаунте пользователя.
     * @return возвращает данные о состоянии счета найденного пользователя.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        Account result = null;
        if (user != null) {
            List<Account> requis = users.get(user);
            for (Account account : requis) {
                if (account.getRequisite().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;

    }

    /**
     * Метод перечисляет деньги с одного счета на другой.
     * @param amount деньги на текущем счету.
     * В методе ищем аккаунт, откуда надо перевести деньги
     * по данным паспорта (srcPassport)
     * и реквизитам (srcRequisite),
     * затем ищем аккаунт, куда надо перевести по паспорту и реквизитам (destPassport, destRequisite).
     * Проверяем хватит ли денег на первом аккаунте и в случае успеха
     * делаем перевод.
     * @return возвращает true в случае успешного перевода и false, если один из счетов не найдены
     * или не хватает денег для перевода.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAcc = findByRequisite(srcPassport, srcRequisite);
        Account destAcc = findByRequisite(destPassport, destRequisite);
        if (srcAcc.getRequisite() != null
                && destAcc.getRequisite() != null
                && srcAcc.getBalance() >= amount) {
            rsl = true;
            srcAcc.setBalance(srcAcc.getBalance() - amount);
            destAcc.setBalance(destAcc.getBalance() + amount);
        }

        return rsl;
    }
}
