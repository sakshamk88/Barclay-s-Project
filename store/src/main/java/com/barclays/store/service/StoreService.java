package com.barclays.store.service;

import com.barclays.store.entity.Store;
import com.barclays.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class StoreService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    StoreRepository storeRepository;
    public Boolean getEmployees() {
        final String uri = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/datab79e8b2.txt";
        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            Integer initial_index = result.indexOf("[");
            result = result.strip().substring(initial_index.intValue(), result.length() - 2);
            String[] kv = result.split(",");

            List<Store> storeList = new ArrayList<>();
            for (int i = 0; i < kv.length; i += 5) {
                Store s = new Store();
                s.setStoreName(kv[i].split(":")[1].substring(2,kv[i].split(":")[1].length()-1));
                s.setArea(kv[i + 1].split(":")[1].substring(2,kv[i + 1].split(":")[1].length()-1));
                s.setPinCode(kv[i + 2].split(":")[1].substring(2,kv[i + 2].split(":")[1].length()-1));
                s.setLatitude(kv[i + 3].split(":")[1].substring(2,kv[i + 3].split(":")[1].length()-1));
                s.setLongitude(kv[i + 4].split(":")[1].substring(1, kv[i + 4].split(":")[1].length() - 2).split("\"")[1]);

                storeList.add(s);

            }

            storeRepository.saveAll(storeList);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
//        result = result.substring(1, result.length()-1);
//        String[] keyValuePairs = result.split(",");
//        System.out.println(keyValuePairs);
//        Map<String,String> map = new HashMap<>();
//
//        for(String pair : keyValuePairs)
//        {
//            String[] entry = pair.split("=");
//            map.put(entry[0].trim(), entry[1].trim());
//        }
//        System.out.println(map);
//        JSONObject jsonObject = new JSONObject(map);
//        System.out.println(jsonObject);
//        System.out.println(result);
//    }
    }

    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }


    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public String getMinimumDistance(Double lat, Double lon){
        try{
            List<Store> storeList = storeRepository.findAll();
            Double minimumDistance = Double.MAX_VALUE;
            String storeName="";
            for (Store store: storeList
                 ) {
                Double curr_dist=distance(Double.parseDouble(store.getLatitude()),Double.parseDouble(store.getLongitude()),lat,lon,'K');
                if(minimumDistance>curr_dist)
                {
                    minimumDistance=curr_dist;
                    storeName=store.getStoreName();

                }

            }
            return storeName;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return " ";
        }
        }
    }


