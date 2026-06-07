package com.test.RainbowProject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ReadTextFile {
	public static Object[][] readLoginData(String filePath) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Object[]> data = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i);

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] values = line.split("\\|\\|", -1);

            String username = values[0];
            String password = values[1];
            String expectedResult = values[2];

            data.add(new Object[] { username, password, expectedResult });
        }

        return data.toArray(new Object[0][]);
    }

}
