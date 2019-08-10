package ru.otus.homework.services;

import ru.otus.homework.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void add(User user) throws SQLException;

    List<User> getAll() throws SQLException;
}
