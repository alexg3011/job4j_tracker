package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        List<Account> userAc = users.get(user);
        if(!userAc.contains(account.getRequisite())) {
            userAc.add(account);
            users.put(user, userAc);
        }

    }

    public User findByPassport(String passport) {
        User rsl = null;
        for(User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
            }
        }
        return rsl;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        List<Account> requis = users.get(user);
        Account result = null;
        if (requis != null) {
            for (Account account : requis) {
                if (account.getRequisite().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
            return result;

    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        User srcUser = findByPassport(srcPassport);
        User destUser = findByPassport(destPassport);
        Account srcAcc = findByRequisite(srcUser.getPassport(), srcRequisite);
        Account destAcc = findByRequisite(destUser.getPassport(), destRequisite);
        if (srcAcc.getRequisite() != null
                || destAcc.getRequisite() != null
                || srcAcc.getBalance() < amount) {
            rsl = true;
            srcAcc.setBalance(srcAcc.getBalance() - amount);
            destAcc.setBalance(destAcc.getBalance() + amount);
        }

        return rsl;
    }
}
