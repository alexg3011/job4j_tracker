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
        if (user != null) {
            List<Account> userAc = users.get(user);
            if (!userAc.contains(account)) {
                userAc.add(account);
            }
        }

    }

    public User findByPassport(String passport) {
        User rsl = null;
        for(User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
                break;
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
