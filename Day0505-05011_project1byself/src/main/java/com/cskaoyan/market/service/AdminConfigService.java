package com.cskaoyan.market.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminConfigService {

    void mallPost(HashMap<String, String> map);


    HashMap<String, String> mallGet(ArrayList<String> list);
}
