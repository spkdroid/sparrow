package com.crypto.sparrow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    private static ArrayList<CoinData> coinURLList = new ArrayList<CoinData>();

    public static void main(String[] args) {
        File fp = new File("response.json");

        if (fp.canRead())
            System.out.println("Read Success");
        else
            System.exit(0);

        try {
            String jsonResponse = ReadFile(fp);
            JSONObject jp = new JSONObject(jsonResponse);
            String fetchCoin = jp.get("Data").toString();
            LoadListData(fetchCoin);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (CoinData coinUrl : coinURLList) {
            try {
                saveImage(coinUrl.getCoinPicture(), "tmp/" + coinUrl.getCoinName() + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Success");
    }

    private static String ReadFile(File fp) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream input = new FileInputStream(fp);
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void LoadListData(String data) {
        try {
            JSONObject jr = new JSONObject(data);
            Iterator<String> x = jr.keys();
            JSONArray jsonArray = new JSONArray();
            while (x.hasNext()) {
                String key = x.next();
                jsonArray.put(jr.get(key));
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                CoinData c = new CoinData();
                JSONObject fetch = (JSONObject) jsonArray.get(i);
                try {
                    c.setCoinName(fetch.get("Name").toString());
                    c.setCoinFullName(fetch.get("FullName").toString());
                    c.setCoinCode(fetch.get("Symbol").toString());
                    c.setCoinAlgorithm(fetch.get("Algorithm").toString());
                    c.setCoinPicture(fetch.get("ImageUrl").toString());
                    coinURLList.add(c);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }
}