import java.io.*;
import java.util.*;

class 오픈채팅방 {
    static ArrayList<String[]> messages;
    static HashMap<String, String> nicknames;
    
    public String[] solution(String[] record) {
        setUp();
        
        for (String r : record) {
            String[] words = r.split(" ");
            String command = words[0];
            String userId = words[1];
            
            if (words[0].startsWith("Enter")) {
                String nickname = words[2];
                
                messages.add(new String[]{command, userId});
                nicknames.put(userId, nickname);
            } else if (words[0].startsWith("Leave")) {
                messages.add(new String[]{command, userId});
            } else if (words[0].startsWith("Change")) {
                String nickname = words[2];
                
                nicknames.put(userId, nickname);
            }
        }
            
        String[] answer = new String[messages.size()];
        int answerSize = 0;
        
        for (String[] message : messages) {
            String command = message[0];
            String userId = message[1];
            
            if (command.equals("Enter")) {
                answer[answerSize] = nicknames.get(userId) + "님이 들어왔습니다.";
            } else if (command.equals("Leave")) {
                answer[answerSize] = nicknames.get(userId) + "님이 나갔습니다.";
            }
            
            answerSize += 1;
        }
        
        return answer;
    }
    
    static void setUp() {
        messages = new ArrayList<>();
        nicknames = new HashMap<>();
    }
}