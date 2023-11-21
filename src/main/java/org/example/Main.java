package org.example;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int countOfKeysVar;
    public static void main(String[] args) throws IOException {

        String JsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject world = JsonObject.readFrom(JsonString);


        Scanner sc = new Scanner(System.in);


        JsonObject continent = JsonObject.readFrom(String.valueOf(world.get("continent")));


        System.out.println(count_of_keys(continent, sc.nextLine()));



    }

    static int count_of_keys(JsonObject continent, String keyName) throws IOException {
        if(!continent.isEmpty()){
            continent.forEach((continenetName)-> {
                        if (!continent.get(continenetName.getName()).isNumber()) {
                            JsonObject rec = JsonObject.readFrom(String.valueOf(continent.get(continenetName.getName())));
//                            System.out.println(continenetName.getName());
                            if(keyName.equals(continenetName.getName())){
                                countOfKeysVar += 1;
                            }
                            try {
                                count_of_keys(rec, keyName);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            if(keyName.equals(continenetName.getName())){
                                countOfKeysVar += 1;
                            }
                        }

                    }
            );

        }
        return countOfKeysVar;

    }
}
