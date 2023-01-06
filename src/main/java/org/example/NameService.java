package org.example;

import java.util.HashMap;
import java.util.Map;

public class NameService {
  private Map<String, Integer> nicknameToPinMap;

  public NameService() {
    nicknameToPinMap = new HashMap<>();
  }

  public void registerNickname(String nickname, int pin) {
    if (nicknameToPinMap.containsKey(nickname)) {
      throw new IllegalArgumentException("Nickname already exists.");
    }
    nicknameToPinMap.put(nickname, pin);
  }

  public int getPin(String nickname) {
    if (!nicknameToPinMap.containsKey(nickname)) {
      throw new IllegalArgumentException("Nickname does not exist.");
    }
    return nicknameToPinMap.get(nickname);
  }
}
