package com.example.smart_meal;

import java.util.HashMap;

public interface Communicator {
    public void respond(HashMap<Integer,Integer> itemIdAndQty);
}
