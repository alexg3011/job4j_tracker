package ru.job4j.bank;

import java.util.*;

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
     * @param user на вход подаются паспорт и имя пользователя.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод ищет пользователя по паспорту
     * и если находит, проверяет есть ли у него данные аккаунта и если нет,
     * добавляет сведения в хранилище.
     * @param passport паспорт, по которому ищем пользователя.
     * @param account содержит данные об вносимом в базу аккаунте,
     * такие как номер счета и состояние баланса.
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> userAc = users.get(user.get());
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
     *
     */
    public Optional<User> findByPassport(String passport) {
        Optional<User> rsl = users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst();

        return rsl;
    }

    /**
     * Метод ищет пользователя в хранилище по введенным данным паспорта и реквизитам счета:
     * сначала находит пользователя по паспорту, в случае успеха
     * ищет в хранилище данные об аккаунте пользователя.
     * @param passport паспорт, по которому ищем пользователя.
     * @param requisite реквизиты, которые ищем у найденного пользователя.
     * @return возвращает данные о состоянии счета найденного пользователя;
     * в случае не нахождения пользователя в базе возвращает null;
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        Optional<Account>rsl = Optional.empty();
        if (user.isPresent()) {
             rsl = users.get(user.get())
                    .stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst();
        }
        return rsl;
    }

    /**
     * Метод перечисляет деньги с одного счета на другой.
     * @param srcPassport паспорт, по которому ищем пользователя - отправителя.
     * @param srcRequisite реквизиты пользователя - отправителя.
     * @param destPassport паспорт, по которому ищем пользователя - получателя.
     * @param destRequisite реквизиты пользователя - получателя.
     * В случае не нахождения в базе одного из вышеперечисленных параметров метод возвращает false
     * и перевод не производится!
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
        Optional<Account> srcAcc = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAcc = findByRequisite(destPassport, destRequisite);
        if (srcAcc.isPresent()
                && destAcc.isPresent()
                && srcAcc.get().getBalance() >= amount) {
            rsl = true;
            srcAcc.get().setBalance(srcAcc.get().getBalance() - amount);
            destAcc.get().setBalance(destAcc.get().getBalance() + amount);
        }

        return rsl;
    }
}
