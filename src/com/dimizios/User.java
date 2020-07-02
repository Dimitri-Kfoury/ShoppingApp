package com.dimizios;

import java.util.Map;

public class User implements Comparable {

    private String username;
    private int userId;

    @Override
    public int compareTo(Object o) {
        if (o instanceof User) {

            if (this.userId > ((User) o).userId)
                return 1;
            else if (this.userId == ((User) o).userId)
                return 0;

        }

        return -1;
    }
}
